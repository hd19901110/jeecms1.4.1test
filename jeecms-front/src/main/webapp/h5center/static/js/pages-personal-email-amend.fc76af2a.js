(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-personal-email-amend"],{"1a0a":function(e,t,n){t=e.exports=n("2350")(!1),t.push([e.i,'@charset "UTF-8";\n/**\r\n * 这里是uni-app内置的常用样式变量\r\n *\r\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\r\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\r\n *\r\n */\n/**\r\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\r\n *\r\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\r\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.content.email .email-box[data-v-1c18bd7a]{padding:0 %?40?%;padding-top:%?150?%}.content.email .email-box .code-box[data-v-1c18bd7a]{padding:0 %?74?%}.content.email .email-box .code-box .code-conent[data-v-1c18bd7a]{padding-bottom:%?25?%;border-bottom:%?1?% solid #f0f0f0;display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-pack:justify;-webkit-justify-content:space-between;justify-content:space-between}.content.email .email-box .code-box .code-conent .code-input[data-v-1c18bd7a]{height:%?60?%;line-height:%?60?%}.content.email .email-box .code-box .code-conent .code-btn[data-v-1c18bd7a]{width:%?184?%;height:%?60?%;line-height:%?60?%;text-align:center;font-size:%?22?%;color:#fff;background-color:#3b3b3b;border-radius:%?10?%}.content.email .email-box .code-box .code-next[data-v-1c18bd7a]{width:%?458?%;height:%?86?%;font-size:%?30?%;color:#fff;line-height:%?86?%;text-align:center;margin:%?60?% auto;border-radius:%?43?%;background-color:#e6e6e6}',""])},"399e":function(e,t,n){"use strict";var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("v-uni-view",{staticClass:"content email"},[n("page-head",{attrs:{pageTitle:"修改注册邮箱"}}),n("v-uni-view",{staticClass:"email-box"},[1==e.status?n("v-uni-view",{staticClass:"code-box"},[n("v-uni-view",{staticClass:"code-conent"},[n("v-uni-input",{staticClass:"code-input",attrs:{type:"text",placeholder:"邮箱验证码",maxlength:"6"},model:{value:e.code,callback:function(t){e.code=t},expression:"code"}}),n("v-uni-view",{staticClass:"code-btn",style:{color:(e.time,"#fff")},domProps:{textContent:e._s(e.codeBtn)},on:{click:function(t){arguments[0]=t=e.$handleEvent(t),e.fetchsendEmailMsg()}}})],1),n("v-uni-view",{staticClass:"code-next Medium",style:{"background-color":e.code.length>=6?"#D20505":"#E6E6E6"},on:{click:function(t){arguments[0]=t=e.$handleEvent(t),e.verifyCode()}}},[e._v("下一步")])],1):e._e(),2==e.status?n("v-uni-view",{staticClass:"code-box"},[n("v-uni-view",{staticClass:"code-conent"},[n("v-uni-input",{staticClass:"code-input",attrs:{type:"text",maxlength:"50",placeholder:"请输入新邮箱"},model:{value:e.newEmail,callback:function(t){e.newEmail=t},expression:"newEmail"}})],1),n("v-uni-view",{staticClass:"code-conent",staticStyle:{"margin-top":"20upx"}},[n("v-uni-input",{staticClass:"code-input",attrs:{type:"text",placeholder:"新邮箱验证码",maxlength:"6"},model:{value:e.newCode,callback:function(t){e.newCode=t},expression:"newCode"}}),n("v-uni-view",{staticClass:"code-btn",style:{color:(e.newTime,"#fff")},domProps:{textContent:e._s(e.newCodeBtn)},on:{click:function(t){arguments[0]=t=e.$handleEvent(t),e.newFetchsendEmailMsg()}}})],1),n("v-uni-view",{staticClass:"code-next Medium",style:{"background-color":e.newCode.length>=6?"#D20505":"#E6E6E6"},on:{click:function(t){arguments[0]=t=e.$handleEvent(t),e.accomplish()}}},[e._v("完成修改")])],1):e._e()],1)],1)},a=[];n.d(t,"a",function(){return i}),n.d(t,"b",function(){return a})},4457:function(e,t,n){var i=n("1a0a");"string"===typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);var a=n("4f06").default;a("12dac4c2",i,!0,{sourceMap:!1,shadowMode:!1})},"4aee":function(e,t,n){"use strict";n.r(t);var i=n("b9b8"),a=n.n(i);for(var o in i)"default"!==o&&function(e){n.d(t,e,function(){return i[e]})}(o);t["default"]=a.a},"5b7d":function(e,t,n){"use strict";n.r(t);var i=n("399e"),a=n("4aee");for(var o in a)"default"!==o&&function(e){n.d(t,e,function(){return a[e]})}(o);n("878e");var s=n("2877"),c=Object(s["a"])(a["default"],i["a"],i["b"],!1,null,"1c18bd7a",null);t["default"]=c.exports},"878e":function(e,t,n){"use strict";var i=n("4457"),a=n.n(i);a.a},b9b8:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,n("3b2b");var i={name:"amend",data:function(){return{email:"",codeBtn:"获取验证码",time:60,code:"",status:1,newEmail:"",isNew:!1,newTime:60,newCode:"",newCodeBtn:"获取验证码"}},mounted:function(){this.fetchMemberinfo()},methods:{accomplish:function(){var e=this;this.newEmail&&this.newCode&&this.$request.fetchMemberinfoEmail({code:this.code,newEmail:this.newEmail,newEmailCode:this.newCode}).then(function(t){200==t.code?(e.$message("完成修改"),uni.navigateTo({url:"/pages/personal/index/index"})):e.$message(t.message)})},fetchsendEmailMsg:function(){var e=this;this.time>=60&&this.$request.fetchsendEmailMsg({type:3,targetNumber:this.email}).then(function(t){200==t.code?e.countDown():e.$message(t.message)})},newFetchsendEmailMsg:function(){var e=this,t=new RegExp(/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/);t.test(this.newEmail)?this.newTime>=60&&this.$request.fetchsendEmailMsg({type:4,targetNumber:this.newEmail}).then(function(t){200==t.code?e.newCountDown():e.$message(t.message)}):this.$message("请正确输入邮箱")},fetchMemberinfo:function(){var e=this;this.$request.fetchMemberinfo().then(function(t){200==t.code&&(e.email=t.data.dataField.email)})},countDown:function(){var e=this,t=setInterval(function(){e.time>0?(e.codeBtn="获取验证码("+e.time+")",e.time-=1):(clearTimeout(t),e.time=60,e.codeBtn="获取验证码")},1e3)},newCountDown:function(){var e=this,t=setInterval(function(){e.newTime>0?(e.newCodeBtn="获取验证码("+e.newTime+")",e.newTime-=1):(clearTimeout(t),e.newTime=60,e.newCodeBtn="获取验证码")},1e3)},verifyCode:function(){var e=this;this.code&&this.$request.verifyEmailCode({email:this.email,type:3,code:this.code}).then(function(t){200==t.code?e.status=2:e.$message(t.message)})}}};t.default=i}}]);