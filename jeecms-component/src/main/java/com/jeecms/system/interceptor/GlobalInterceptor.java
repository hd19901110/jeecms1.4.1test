package com.jeecms.system.interceptor;

import com.alibaba.fastjson.JSON;
import com.jeecms.auth.domain.CoreUser;
import com.jeecms.auth.service.CoreUserService;
import com.jeecms.auth.service.LoginService;
import com.jeecms.common.base.domain.RequestLoginTarget;
import com.jeecms.common.constants.ServerModeEnum;
import com.jeecms.common.constants.WebConstants;
import com.jeecms.common.exception.GlobalException;
import com.jeecms.common.exception.SystemExceptionEnum;
import com.jeecms.common.local.UserThreadLocal;
import com.jeecms.common.response.ResponseInfo;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.common.web.springmvc.MessageResolver;
import com.jeecms.common.web.util.CookieUtils;
import com.jeecms.common.web.util.ResponseUtils;
import com.jeecms.sso.constants.SsoContants;
import com.jeecms.sso.dto.response.SyncResponseUserVo;
import com.jeecms.sso.service.CmsSsoService;
import com.jeecms.system.domain.CmsSite;
import com.jeecms.system.domain.GlobalConfig;
import com.jeecms.system.domain.GlobalConfigAttr;
import com.jeecms.system.domain.dto.CmsSiteAgent;
import com.jeecms.system.service.CmsSiteService;
import com.jeecms.system.service.GlobalConfigService;
import com.jeecms.system.service.SysIptablesService;
import com.jeecms.util.SystemContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 全局拦截器，设置全局通用参数（不分管理平台）
 *
 * @author: tom
 * @date: 2018年4月14日 下午5:52:34
 * @Copyright: 江西金磊科技发展有限公司 All rights reserved. Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Component
public class GlobalInterceptor extends HandlerInterceptorAdapter {

    private final static Logger log = LoggerFactory.getLogger(GlobalInterceptor.class);

    private final transient ReentrantLock lock = new ReentrantLock();

    @Value("${spring.profiles.active}")
    private String active;

    static Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, GlobalException {
        if (ServerModeEnum.prod.toString().equals(active)) {
            String referer = request.getHeader("referer");
            List<CmsSite>sites = siteService.findAll(false,true);
            /**referer必须是来自数据库站点配置的域名范围内，不允许其他站点链接而来，否则有安全漏洞，不需要的可以关闭此处限制*/
            if(!CmsSiteAgent.validRefer(sites,referer)){
                logger.info("======访问地址发现非法字符，已拦截======其非法地址为：" + request.getRequestURL().toString());
                String msg = MessageResolver.getMessage(SystemExceptionEnum.ILLEGAL_PARAM.getCode(),
                        SystemExceptionEnum.ILLEGAL_PARAM.getDefaultMessage());
                String code = SystemExceptionEnum.ILLEGAL_PARAM.getCode();
                ResponseInfo responseInfo = new ResponseInfo(code, msg, request, null, null);
                ResponseUtils.renderJson(response, JSON.toJSONString(responseInfo));
                return false;
            }
        }
        /**不允许篡改host，否则存在安全软件报的http host头攻击漏洞*/
        String host = request.getHeader("Host");
        if(host!=null&&host.indexOf(":")!=-1){
            host = host.substring(0,host.indexOf(":"));
        }
        if(!host.equals(request.getServerName())) {
            logger.info("======host不允许篡改：" + request.getRequestURL().toString());
            return false;
        }
        final ReentrantLock lock = this.lock;
        CmsSite site;
        lock.lock();
        try {
            site = siteService.getCurrSite(request, response);
            SystemContextUtils.setSite(request, site);
        } finally {
            lock.unlock();
        }
        getCoreUser(request,response);
        GlobalConfig config = configMng.get();
        SystemContextUtils.setGlobalConfig(request, config);
        SystemContextUtils.setResponseConfigDto(request, configMng.filterConfig(request, config));
        response.setHeader("Content-Security-Policy", "none");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        /**分离开发情况下需要注释，否则iframe嵌入的方式嵌入不了*/
        response.setHeader("x-frame-options","SAMEORIGIN");
        return true;
    }


    private void getCoreUser(HttpServletRequest request, HttpServletResponse response) throws  GlobalException{
        CoreUser user = null;
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication authentication = sc.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !WebConstants.ANONYMOUSUSER.equals(authentication.getPrincipal())) {
            Object detailObj = authentication.getDetails();
            UserDetails userDetails;
            /**
             * WebAuthenticationDetails 下次登录认证对象放在了的auth principal属性中
             * token传递的认证对象放在了 details属性中
             */
            if (detailObj instanceof WebAuthenticationDetails) {
                detailObj = authentication.getPrincipal();
                userDetails = (UserDetails) detailObj;
            } else {
                userDetails = (UserDetails) authentication.getDetails();
            }
            user = coreUserService.findByUsername(userDetails.getUsername());
        }
        // 判断是否开启单点登录
        GlobalConfig config = globalConfigService.get();
        GlobalConfigAttr configAttr = config.getConfigAttr();
        if (configAttr.getSsoLoginAppOpen() && user == null) {
            /** 判断cookie是否存在SSO token,存在则 获取用户信息 */
            Cookie ssoCookie = CookieUtils.getCookie(request, SsoContants.CLIENT_AUTHTOKEN);
            if (ssoCookie != null && StringUtils.isNoneBlank(ssoCookie.getValue())) {
                // 拿到登录用户信息
                SyncResponseUserVo vo = cmsSsoService.userVaild(ssoCookie.getValue());
                if (vo != null) {
                    Map<String, Object> map = loginService.login(RequestLoginTarget.admin, vo.getUsername(), null);
                    user = coreUserService.findByUsername(vo.getUsername());
                    // JsonFilterResponseBodyAdvice 续期token
                    request.setAttribute(tokenHeader, map.get(tokenHeader));
                }
            }
        }
        // 如果不是回放操作直接判断操作
        /** 管理员登录后长时间未操作自动退出账号 */
        if (!SystemContextUtils.replay()) {
            boolean isForceLogout = loginService.isForceLogout();
            if (user!=null&&user.getAdmin()&&isForceLogout) {
                loginService.forceLogout(request, response);
                user = null;
            } else {
                SystemContextUtils.setCoreUser(user);
                /** 设置用户线程变量 */
                UserThreadLocal.setUser(user);
            }
        }else{
            SystemContextUtils.setCoreUser(user);
            /** 设置用户线程变量 */
            UserThreadLocal.setUser(user);
        }
    }

    @Autowired
    private GlobalConfigService configMng;
    @Autowired
    private CmsSiteService siteService;
    @Value("${token.header}")
    private String tokenHeader;
    @Autowired
    private CoreUserService coreUserService;
    @Autowired
    private SysIptablesService iptablesService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SessionProvider sessionProvider;
    @Autowired
    private CmsSsoService cmsSsoService;
    @Autowired
    private GlobalConfigService globalConfigService;

}
