(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-login-login"],{"05f5":function(t,n,e){"use strict";e.r(n);var a=e("d35f"),i=e.n(a);for(var o in a)"default"!==o&&function(t){e.d(n,t,(function(){return a[t]}))}(o);n["default"]=i.a},"364e":function(t,n,e){"use strict";var a=e("5a3a"),i=e.n(a);i.a},"43ac":function(t,n,e){"use strict";e.r(n);var a=e("b19e"),i=e("05f5");for(var o in i)"default"!==o&&function(t){e.d(n,t,(function(){return i[t]}))}(o);e("364e");var s,r=e("f0c5"),c=Object(r["a"])(i["default"],a["b"],a["c"],!1,null,"f3da7b94",null,!1,a["a"],s);n["default"]=c.exports},"595a":function(t,n,e){var a=e("24fb");n=a(!1),n.push([t.i,'@charset "UTF-8";\r\n/**\r\n * 这里是uni-app内置的常用样式变量\r\n *\r\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\r\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\r\n *\r\n */\r\n/**\r\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\r\n *\r\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\r\n */\r\n/* 颜色变量 */\r\n/* 行为相关颜色 */\r\n/* 文字基本颜色 */\r\n/* 背景颜色 */\r\n/* 边框颜色 */\r\n/* 尺寸变量 */\r\n/* 文字尺寸 */\r\n/* 图片尺寸 */\r\n/* Border Radius */\r\n/* 水平间距 */\r\n/* 垂直间距 */\r\n/* 透明度 */\r\n/* 文章场景相关 */.content.login[data-v-f3da7b94]{width:100%;min-height:100vh;position:relative}.content.login .uni-form-wrap .uni-title-h1[data-v-f3da7b94]{padding:%?60?% 0 %?26?%;font-size:%?50?%;font-family:PingFang SC;color:#333}.content.login .uni-form-wrap .box[data-v-f3da7b94]{height:%?122?%;position:relative;border-bottom:1px solid #f0f0f0}.content.login .uni-form-wrap .box .uni-icon[data-v-f3da7b94]{position:absolute;top:%?49?%;right:0}.content.login .uni-form-wrap .box .uni-icon img[data-v-f3da7b94]{width:%?32?%;height:%?32?%}.content.login .uni-form-wrap .box .inputbox[data-v-f3da7b94]{font-size:%?28?%;position:absolute;top:%?54?%;left:0;font-family:PingFang SC;color:#333;line-height:1}.content.login .uni-form-wrap .uni-button[data-v-f3da7b94]{margin-top:%?60?%}.content.login .uni-form-wrap .forget-pass[data-v-f3da7b94]{padding-top:%?40?%;text-align:center}.content.login .fast-login-wrap[data-v-f3da7b94]{margin-top:%?250?%;width:100%;text-align:center}.content.login .fast-login-wrap .fast-login-title .line[data-v-f3da7b94]{width:%?30?%;height:%?1?%;background:#e6e6e6}.content.login .fast-login-wrap .fast-login-title .uni-text-md[data-v-f3da7b94]{padding:0 %?28?%}.content.login .fast-login-wrap .oauth-row[data-v-f3da7b94]{padding:%?60?% 0}.content.login .fast-login-wrap .oauth-row .oauth-image[data-v-f3da7b94]{width:%?80?%;height:%?80?%;border-radius:50%;margin:0 %?35?%;background-color:#f5f5f5}.content.login .fast-login-wrap .oauth-row .oauth-image[data-v-f3da7b94]:hover{background-color:#f2f2f2}.content.login .fast-login-wrap .oauth-row .oauth-image uni-image[data-v-f3da7b94]{width:%?40?%;height:%?40?%;margin:%?20?%}.content.login .code-img[data-v-f3da7b94]{position:absolute;top:%?30?%;right:%?10?%;height:%?70?%;width:%?250?%}',""]),t.exports=n},"5a3a":function(t,n,e){var a=e("595a");"string"===typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);var i=e("4f06").default;i("d0b4269c",a,!0,{sourceMap:!1,shadowMode:!1})},"886d":function(t,n,e){t.exports=e.p+"static/img/qingchu.8338863c.png"},b19e:function(t,n,e){"use strict";var a={"uni-icons":e("9efb").default},i=function(){var t=this,n=t.$createElement,a=t._self._c||n;return a("v-uni-view",{staticClass:"content login"},[a("v-uni-input",{staticStyle:{position:"absolute","z-index":"-1",width:"0",opacity:"0"},attrs:{type:"password"}}),a("v-uni-input",{staticStyle:{position:"absolute","z-index":"-1",width:"0",opacity:"0"},attrs:{type:"text"}}),a("page-head",{attrs:{title:t.register},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.handleReg.apply(void 0,arguments)}}}),a("v-uni-view",{staticClass:"uni-form-wrap"},[a("v-uni-view",{staticClass:"uni-title-h1 Medium"},[t._v("登录")]),a("v-uni-view",{staticClass:"box"},[a("v-uni-input",{staticClass:"inputbox Regular",attrs:{focus:!0,placeholder:"输入用户名",autocomplete:"off",maxlength:"50"},model:{value:t.username,callback:function(n){t.username=n},expression:"username"}}),t.username?a("v-uni-view",{staticClass:"uni-icon",on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.clearIcon.apply(void 0,arguments)}}},[a("img",{attrs:{src:e("886d"),alt:""}})]):t._e()],1),a("v-uni-view",{staticClass:"box"},[a("v-uni-input",{staticClass:"inputbox Regular",attrs:{password:t.showPassword,placeholder:"输入密码",autocomplete:"off",maxlength:"50"},model:{value:t.password,callback:function(n){t.password=n},expression:"password"}})],1),t.errorNumber>=3?a("v-uni-view",{staticClass:"box"},[a("v-uni-input",{staticClass:"inputbox Regular",attrs:{placeholder:"输入验证码",autocomplete:"off",maxlength:"4"},model:{value:t.captcha,callback:function(n){t.captcha=n},expression:"captcha"}}),a("img",{staticClass:"code-img",attrs:{src:"data:image/png;base64,"+t.codeImage},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.genCapatch()}}})],1):t._e(),a("v-uni-button",{staticClass:"uni-button",attrs:{disabled:!t.username||!t.password},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.handleSubmit.apply(void 0,arguments)}}},[t._v("立即登录")]),t.showForgetPass?a("v-uni-view",{staticClass:"forget-pass uni-text-sm uni-text",on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.find()}}},[t._v("忘记密码？ 点击"),a("v-uni-text",{staticClass:"uni-link-a uni-text-66"},[t._v("找回")])],1):t._e()],1),t.speediness?a("v-uni-view",{staticClass:"fast-login-wrap"},[a("v-uni-view",{staticClass:"fast-login-title uni-layout-center"},[a("v-uni-view",{staticClass:"line"}),a("v-uni-text",{staticClass:"uni-text uni-text-md"},[t._v("快速登录")]),a("v-uni-view",{staticClass:"line"})],1),a("v-uni-view",{staticClass:"oauth-row uni-layout-center"},[t.qqOpen?a("v-uni-view",{staticClass:"uni-layout-center oauth-image"},[a("uni-icons",{staticClass:"iconfont iconqq-fill",attrs:{color:"#41C0F6",size:"40"},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.qqQauth()}}})],1):t._e(),t.weiboOpen?a("v-uni-view",{staticClass:"uni-layout-center oauth-image"},[a("uni-icons",{staticClass:"iconfont iconxinlang",attrs:{color:"#FF5D5D",size:"46"},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.sinaQauth()}}})],1):t._e()],1),a("v-uni-text",{staticClass:"uni-text-sm uni-text-b3"},[t._v("登录即代表您已阅读并同意"),a("v-uni-text",{staticClass:"uni-link",on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.gotoAgreement()}}},[t._v("《JEECMS用户协议》")])],1)],1):t._e()],1)},o=[];e.d(n,"b",(function(){return i})),e.d(n,"c",(function(){return o})),e.d(n,"a",(function(){return a}))},d35f:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var a=e("c9ca"),i={name:"login",data:function(){return{speediness:!0,username:"",password:"",positionTop:0,showPassword:!0,showForgetPass:!1,qqOpen:!1,wechatOpen:!1,weiboOpen:!1,memberRegisterOpen:!1,register:"",errorNumber:0,codeImage:"",sessionId:""}},methods:{clearIcon:function(){this.username=""},changePassword:function(){this.showPassword=!this.showPassword},oauth:function(t){console.log(t)},handleSubmit:function(){var t=this;this.$loading("登录中...");var n={};n.pStr=this.password;var e=JSON.stringify(n),i=(0,a.desEncrypt)(e);this.$request.fetchLogin({identity:this.username,desStr:i,sessionId:this.sessionId,captcha:this.captcha}).then((function(n){200==n.code?(localStorage.setItem("JEECMS-Auth-Token",n.data["JEECMS-Auth-Token"]),uni.navigateTo({url:"/pages/personal/index/index"})):(t.errorNumber+=1,t.showForgetPass=!0,t.$message(n.message),t.errorNumber>=3&&t.genCapatch())}))},handleReg:function(){this.memberRegisterOpen&&this.$nav("../register/register")},find:function(){uni.navigateTo({url:"/pages/login/findpswd"})},fetchGlobalInfo:function(){var t=this;this.$request.fetchGlobalInfo().then((function(n){200==n.code?(t.wechatOpen=n.data.wechatOpen,t.weiboOpen=n.data.weiboOpen,t.qqOpen=n.data.qqOpen,t.wechatOpen||t.weiboOpen||t.qqOpen||(t.speediness=!1)):t.$message(n.message)}))},gotoAgreement:function(){uni.navigateTo({url:"/pages/register/agreement"})},qqQauth:function(){location.href=this.$baseUrl+"/thirdParty/qq/mobile"},sinaQauth:function(){location.href=this.$baseUrl+"/thirdParty/sina/mobile"},judgeCapatch:function(){var t=this;this.$request.judgeCapatch().then((function(n){200==n.code&&(t.memberRegisterOpen=n.data.memberRegisterOpen,t.memberRegisterOpen?t.register="注册":t.register="")}))},genCapatch:function(){var t=this;this.$request.genCapatch().then((function(n){200==n.code&&(t.codeImage=n.data.img,t.sessionId=n.data.sessionId)}))}},mounted:function(){this.judgeCapatch(),this.fetchGlobalInfo(),console.log(this.baseUrl)}};n.default=i}}]);