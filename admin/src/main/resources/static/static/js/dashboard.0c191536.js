(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["dashboard"],{"0a33":function(t,i,s){"use strict";var a=s("0d2b"),e=s.n(a);e.a},"0cfb":function(t,i,s){"use strict";s.r(i);var a=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",[s("div",{staticClass:"crumbs"},[s("el-breadcrumb",{attrs:{separator:"/"}},[s("el-breadcrumb-item",[s("i",{staticClass:"el-icon-lx-calendar"}),t._v(" 表单\n            ")]),s("el-breadcrumb-item",[t._v("基本表单")])],1)],1),s("div",{staticClass:"container"},[s("div",{staticClass:"form-box"},[s("el-form",{ref:"form",attrs:{model:t.form,"label-width":"80px"}},[s("el-form-item",{attrs:{label:"表单名称"}},[s("el-input",{model:{value:t.form.name,callback:function(i){t.$set(t.form,"name",i)},expression:"form.name"}})],1),s("el-form-item",{attrs:{label:"选择器"}},[s("el-select",{attrs:{placeholder:"请选择"},model:{value:t.form.region,callback:function(i){t.$set(t.form,"region",i)},expression:"form.region"}},[s("el-option",{key:"bbk",attrs:{label:"步步高",value:"bbk"}}),s("el-option",{key:"xtc",attrs:{label:"小天才",value:"xtc"}}),s("el-option",{key:"imoo",attrs:{label:"imoo",value:"imoo"}})],1)],1),s("el-form-item",{attrs:{label:"日期时间"}},[s("el-col",{attrs:{span:11}},[s("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期","value-format":"yyyy-MM-dd"},model:{value:t.form.date1,callback:function(i){t.$set(t.form,"date1",i)},expression:"form.date1"}})],1),s("el-col",{staticClass:"line",attrs:{span:2}},[t._v("-")]),s("el-col",{attrs:{span:11}},[s("el-time-picker",{staticStyle:{width:"100%"},attrs:{placeholder:"选择时间"},model:{value:t.form.date2,callback:function(i){t.$set(t.form,"date2",i)},expression:"form.date2"}})],1)],1),s("el-form-item",{attrs:{label:"城市级联"}},[s("el-cascader",{attrs:{options:t.options},model:{value:t.form.options,callback:function(i){t.$set(t.form,"options",i)},expression:"form.options"}})],1),s("el-form-item",{attrs:{label:"选择开关"}},[s("el-switch",{model:{value:t.form.delivery,callback:function(i){t.$set(t.form,"delivery",i)},expression:"form.delivery"}})],1),s("el-form-item",{attrs:{label:"多选框"}},[s("el-checkbox-group",{model:{value:t.form.type,callback:function(i){t.$set(t.form,"type",i)},expression:"form.type"}},[s("el-checkbox",{attrs:{label:"步步高",name:"type"}}),s("el-checkbox",{attrs:{label:"小天才",name:"type"}}),s("el-checkbox",{attrs:{label:"imoo",name:"type"}})],1)],1),s("el-form-item",{attrs:{label:"单选框"}},[s("el-radio-group",{model:{value:t.form.resource,callback:function(i){t.$set(t.form,"resource",i)},expression:"form.resource"}},[s("el-radio",{attrs:{label:"步步高"}}),s("el-radio",{attrs:{label:"小天才"}}),s("el-radio",{attrs:{label:"imoo"}})],1)],1),s("el-form-item",{attrs:{label:"文本框"}},[s("el-input",{attrs:{type:"textarea",rows:"5"},model:{value:t.form.desc,callback:function(i){t.$set(t.form,"desc",i)},expression:"form.desc"}})],1),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("表单提交")]),s("el-button",[t._v("取消")])],1)],1)],1)])])},e=[],o={name:"baseform",data:function(){return{options:[{value:"guangdong",label:"广东省",children:[{value:"guangzhou",label:"广州市",children:[{value:"tianhe",label:"天河区"},{value:"haizhu",label:"海珠区"}]},{value:"dongguan",label:"东莞市",children:[{value:"changan",label:"长安镇"},{value:"humen",label:"虎门镇"}]}]},{value:"hunan",label:"湖南省",children:[{value:"changsha",label:"长沙市",children:[{value:"yuelu",label:"岳麓区"}]}]}],form:{name:"",region:"",date1:"",date2:"",delivery:!0,type:["步步高"],resource:"小天才",desc:"",options:[]}}},methods:{onSubmit:function(){this.$message.success("提交成功！")}}},n=o,h=s("2877"),l=Object(h["a"])(n,a,e,!1,null,null,null);i["default"]=l.exports},"0d2b":function(t,i,s){},"312d":function(t,i,s){"use strict";s.r(i);var a=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",[s("div",{staticClass:"container"},[s("div",{staticClass:"form-box"},[s("el-form",{ref:"form",attrs:{"label-width":"80px"}},[s("el-form-item",{attrs:{label:"头像"}},[s("el-upload",{staticClass:"avatar-uploader",attrs:{action:t.uploadUrl,"show-file-list":!1,data:t.uploadData,name:"location","on-success":t.handleAvatarSuccess,"before-upload":t.beforeAvatarUpload}},[t.userInfo.imgUrl?s("img",{attrs:{size:90,src:t.userInfo.imgUrl}}):t._e()])],1),s("el-form-item",{attrs:{label:"密码"}},[s("el-input",{attrs:{"auto-complete":"off",type:"password"},model:{value:t.userInfo.password,callback:function(i){t.$set(t.userInfo,"password",i)},expression:"userInfo.password"}})],1),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("表单提交")])],1)],1)],1)])])},e=[],o=(s("7f7f"),{name:"baseform",data:function(){return{userInfo:{},uploadData:{supportType:"local",serveName:"movie",fileName:""},uploadUrl:""}},methods:{onSubmit:function(){var t=this;this.$POST(this.$API.ADMIN.AdminInsertUpdate,this.userInfo).then((function(i){t.$message.success("修改成功")}))},loadData:function(){var t=this;this.$GET(this.$API.ADMIN.AdminUseInfo,{}).then((function(i){t.userInfo=i.data,t.userInfo.imgUrl=t.userInfo.avatar}))},handleAvatarSuccess:function(t,i){0==t.code&&(this.userInfo.avatar=t.data.fileUrl),this.userInfo.imgUrl=URL.createObjectURL(i.raw)},beforeAvatarUpload:function(t){this.uploadData.fileName=t.name}},mounted:function(){this.loadData(),this.uploadUrl=this.$API.UPLOADURL}}),n=o,h=s("2877"),l=Object(h["a"])(n,a,e,!1,null,null,null);i["default"]=l.exports},5705:function(t,i,s){"use strict";var a=s("7fa7"),e=s.n(a);e.a},"7ed4":function(t,i,s){"use strict";var a=s("2b0e"),e=new a["default"];i["a"]=e},"7fa7":function(t,i,s){},"8c93":function(t,i,s){var a;
/*!
 * sChart JavaScript Library v2.0.1
 * http://blog.gdfengshuo.com/example/sChart/ | Released under the MIT license
 * Date: 2018-04-16T18:59Z
 */(function(e,o){a=function(){return o(e)}.call(i,s,i,t),void 0===a||(t.exports=a)})(this,(function(t){"use strict";function i(t,i,s,a){this.canvas=document.getElementById(t),this.ctx=this.canvas.getContext("2d"),this.dpi=window.devicePixelRatio||1,this.type=i,this.data=s,this.dataLength=this.data.length,this.showValue=!0,this.autoWidth=!1,this.width=this.canvas.width*this.dpi,this.height=this.canvas.height*this.dpi,this.topPadding=50*this.dpi,this.leftPadding=50*this.dpi,this.rightPadding=0*this.dpi,this.bottomPadding=50*this.dpi,this.yEqual=5,this.yLength=0,this.xLength=0,this.yFictitious=0,this.yRatio=0,this.bgColor="#ffffff",this.fillColor="#1E9FFF",this.axisColor="#666666",this.contentColor="#eeeeee",this.titleColor="#000000",this.title="",this.titlePosition="top",this.radius=100*this.dpi,this.innerRadius=70*this.dpi,this.colorList=["#1E9FFF","#13CE66","#F7BA2A","#FF4949","#72f6ff","#199475","#e08031","#726dd1"],this.legendColor="#000000",this.legendTop=40*this.dpi,this.totalValue=this.getTotalValue(),this.init(a)}return i.prototype={init:function(t){if(0===this.dataLength)return!1;if(t){var i=["topPadding","leftPadding","rightPadding","bottomPadding","radius","innerRadius","legendTop"];for(var s in t)"colorList"===s&&Array.isArray(t[s])?this[s]=t[s].concat(this[s]):i.indexOf(s)>-1?this[s]=t[s]*this.dpi:this[s]=t[s]}t.autoWidth?(this.width=this.canvas.width=this.canvas.parentNode.offsetWidth*this.dpi,this.height=this.canvas.height=this.canvas.parentNode.offsetHeight*this.dpi,this.canvas.setAttribute("style","width:"+this.canvas.parentNode.offsetWidth+"px;height:"+this.canvas.parentNode.offsetHeight+"px;")):(this.canvas.setAttribute("style","width:"+this.canvas.width+"px;height:"+this.canvas.height+"px;"),this.canvas.width*=this.dpi,this.canvas.height*=this.dpi),"bar"===this.type||"line"===this.type?(this.yLength=Math.floor((this.height-this.topPadding-this.bottomPadding-10)/this.yEqual),this.xLength=Math.floor((this.width-this.leftPadding-this.rightPadding-10)/this.dataLength),this.yFictitious=this.getYFictitious(this.data),this.yRatio=this.yLength/this.yFictitious,this.drawBarUpdate()):this.drawPieUpdate()},drawBarUpdate:function(){this.ctx.fillStyle=this.bgColor,this.ctx.fillRect(0,0,this.width,this.height),this.drawAxis(),this.drawPoint(),this.drawTitle(),this.drawBarChart()},drawPieUpdate:function(){this.ctx.fillStyle=this.bgColor,this.ctx.fillRect(0,0,this.width,this.height),this.drawLegend(),this.drawTitle(),this.drawPieChart()},drawBarChart:function(){this.ctx.fillStyle=this.fillColor,this.ctx.strokeStyle=this.fillColor;for(var t=0;t<this.dataLength;t++)this.data[t].left=this.leftPadding+this.xLength*(t+.25),this.data[t].top=this.height-this.bottomPadding-this.data[t].value*this.yRatio,this.data[t].right=this.leftPadding+this.xLength*(t+.75),this.data[t].bottom=this.height-this.bottomPadding,"line"===this.type?(this.ctx.beginPath(),this.ctx.arc(this.data[t].left+this.xLength/4,this.data[t].top,2,0,2*Math.PI,!0),this.ctx.fill(),0!==t&&(this.ctx.moveTo(this.data[t].left+this.xLength/4,this.data[t].top),this.ctx.lineTo(this.data[t-1].left+this.xLength/4,this.data[t-1].top)),this.ctx.stroke()):"bar"===this.type&&this.ctx.fillRect(this.data[t].left,this.data[t].top,this.data[t].right-this.data[t].left,this.data[t].bottom-this.data[t].top),this.showValue&&(this.ctx.font=12*this.dpi+"px Arial",this.ctx.fillText(this.data[t].value,this.data[t].left+this.xLength/4,this.data[t].top-5))},drawPieChart:function(){for(var t=this.width/2,i=this.height/2,s=0,a=0,e=0;e<this.dataLength;e++)this.ctx.beginPath(),this.ctx.fillStyle=this.colorList[e],this.ctx.moveTo(t,i),this.data[e].start=0===e?-Math.PI/2:this.data[e-1].end,this.data[e].end=this.data[e].start+this.data[e].value/this.totalValue*2*Math.PI,this.ctx.arc(t,i,this.radius,this.data[e].start,this.data[e].end),this.ctx.closePath(),this.ctx.fill(),this.data[e].middle=(this.data[e].start+this.data[e].end)/2,s=Math.ceil(Math.abs(this.radius*Math.cos(this.data[e].middle))),a=Math.floor(Math.abs(this.radius*Math.sin(this.data[e].middle))),this.ctx.strokeStyle=this.colorList[e],this.showValue&&(this.data[e].middle<=0?(this.ctx.textAlign="left",this.ctx.moveTo(t+s,i-a),this.ctx.lineTo(t+s+10,i-a-10),this.ctx.moveTo(t+s+10,i-a-10),this.ctx.lineTo(t+s+this.radius/2,i-a-10),this.ctx.stroke(),this.ctx.fillText(this.data[e].value,t+s+5+this.radius/2,i-a-5)):this.data[e].middle>0&&this.data[e].middle<=Math.PI/2?(this.ctx.textAlign="left",this.ctx.moveTo(t+s,i+a),this.ctx.lineTo(t+s+10,i+a+10),this.ctx.moveTo(t+s+10,i+a+10),this.ctx.lineTo(t+s+this.radius/2,i+a+10),this.ctx.stroke(),this.ctx.fillText(this.data[e].value,t+s+5+this.radius/2,i+a+15)):this.data[e].middle>Math.PI/2&&this.data[e].middle<Math.PI?(this.ctx.textAlign="right",this.ctx.moveTo(t-s,i+a),this.ctx.lineTo(t-s-10,i+a+10),this.ctx.moveTo(t-s-10,i+a+10),this.ctx.lineTo(t-s-this.radius/2,i+a+10),this.ctx.stroke(),this.ctx.fillText(this.data[e].value,t-s-5-this.radius/2,i+a+15)):(this.ctx.textAlign="right",this.ctx.moveTo(t-s,i-a),this.ctx.lineTo(t-s-10,i-a-10),this.ctx.moveTo(t-s-10,i-a-10),this.ctx.lineTo(t-s-this.radius/2,i-a-10),this.ctx.stroke(),this.ctx.fillText(this.data[e].value,t-s-5-this.radius/2,i-a-5)));"ring"===this.type&&(this.ctx.beginPath(),this.ctx.fillStyle=this.bgColor,this.ctx.arc(t,i,this.innerRadius,0,2*Math.PI),this.ctx.fill())},drawAxis:function(){this.ctx.beginPath(),this.ctx.strokeStyle=this.axisColor,this.ctx.moveTo(this.leftPadding+.5,this.height-this.bottomPadding+.5),this.ctx.lineTo(this.leftPadding+.5,this.topPadding+.5),this.ctx.moveTo(this.leftPadding+.5,this.height-this.bottomPadding+.5),this.ctx.lineTo(this.width-this.rightPadding-.5,this.height-this.bottomPadding+.5),this.ctx.stroke()},drawPoint:function(){this.ctx.beginPath(),this.ctx.font=12*this.dpi+"px Microsoft YaHei",this.ctx.textAlign="center",this.ctx.fillStyle=this.axisColor;for(var t=0;t<this.dataLength;t++){var i=this.data[t].name,s=this.xLength*(t+1);this.ctx.moveTo(this.leftPadding+s+.5,this.height-this.bottomPadding+.5),this.ctx.lineTo(this.leftPadding+s+.5,this.height-this.bottomPadding+5.5),this.ctx.fillText(i,this.leftPadding+s-this.xLength/2,this.height-this.bottomPadding+15*this.dpi)}this.ctx.stroke(),this.ctx.beginPath(),this.ctx.font=12*this.dpi+"px Microsoft YaHei",this.ctx.textAlign="right",this.ctx.fillStyle=this.axisColor,this.ctx.moveTo(this.leftPadding+.5,this.height-this.bottomPadding+.5),this.ctx.lineTo(this.leftPadding-4.5,this.height-this.bottomPadding+.5),this.ctx.fillText(0,this.leftPadding-10,this.height-this.bottomPadding+5);for(t=0;t<this.yEqual;t++){var a=this.yFictitious*(t+1),e=this.yLength*(t+1);this.ctx.beginPath(),this.ctx.strokeStyle=this.axisColor,this.ctx.moveTo(this.leftPadding+.5,this.height-this.bottomPadding-e+.5),this.ctx.lineTo(this.leftPadding-4.5,this.height-this.bottomPadding-e+.5),this.ctx.stroke(),this.ctx.fillText(a,this.leftPadding-10,this.height-this.bottomPadding-e+5),this.ctx.beginPath(),this.ctx.strokeStyle=this.contentColor,this.ctx.moveTo(this.leftPadding+.5,this.height-this.bottomPadding-e+.5),this.ctx.lineTo(this.width-this.rightPadding-.5,this.height-this.bottomPadding-e+.5),this.ctx.stroke()}},drawTitle:function(){this.title&&(this.ctx.beginPath(),this.ctx.textAlign="center",this.ctx.fillStyle=this.titleColor,this.ctx.font=16*this.dpi+"px Microsoft YaHei","bottom"===this.titlePosition&&this.bottomPadding>=40?this.ctx.fillText(this.title,this.width/2,this.height-5):this.ctx.fillText(this.title,this.width/2,this.topPadding/2+5))},drawLegend:function(){for(var t=0;t<this.dataLength;t++)this.ctx.fillStyle=this.colorList[t],this.ctx.fillRect(10,this.legendTop+15*t*this.dpi,20,11),this.ctx.fillStyle=this.legendColor,this.ctx.font=12*this.dpi+"px Microsoft YaHei",this.ctx.textAlign="left",this.ctx.fillText(this.data[t].name,35,this.legendTop+10+15*t*this.dpi)},getYFictitious:function(t){var i=t.slice(0);i.sort((function(t,i){return-(t.value-i.value)}));var s=Math.ceil(i[0].value/this.yEqual),a=s.toString().length-1;return a=a>2?2:a,Math.ceil(s/Math.pow(10,a))*Math.pow(10,a)},getTotalValue:function(){for(var t=0,i=0;i<this.dataLength;i++)t+=this.data[i].value;return t}},i}))},e2ad:function(t,i,s){"use strict";s.r(i);var a=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",[s("el-row",{attrs:{gutter:20}},[s("el-col",{attrs:{span:8}},[s("el-card",{staticClass:"mgb20",staticStyle:{height:"252px"},attrs:{shadow:"hover"}},[s("div",{staticClass:"user-info"},[s("img",{staticClass:"user-avator",attrs:{src:t.userInfo.avatar,alt:""}}),s("div",{staticClass:"user-info-cont"},[s("div",{staticClass:"user-info-name"},[t._v(t._s(t.userInfo.userName))]),1==t.userInfo.role?s("div",[t._v("超级管理员")]):t._e(),0==t.userInfo.role?s("div",[t._v("普通管理员")]):t._e()])]),s("div",{staticClass:"user-info-list"},[t._v("上次登录时间："),s("span",[t._v(t._s(t.userInfo.updateTime))])]),s("div",{staticClass:"user-info-list"},[t._v("上次登录地址："),s("span",[t._v(t._s(t.userInfo.ip))])])]),s("el-card",{staticStyle:{height:"252px"},attrs:{shadow:"hover"}},[s("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[s("span",[t._v("在线影院")])]),s("div",[t._v("一个专业与电影的平台")])])],1),s("el-col",{attrs:{span:16}},[s("el-row",{staticClass:"mgb20",attrs:{gutter:20}},[s("el-col",{attrs:{span:8}},[s("el-card",{attrs:{shadow:"hover","body-style":{padding:"0px"}}},[s("div",{staticClass:"grid-content grid-con-1"},[s("i",{staticClass:"el-icon-lx-people grid-con-icon"}),s("div",{staticClass:"grid-cont-right"},[s("div",{staticClass:"grid-num"},[t._v(t._s(t.statics.count))]),s("div",[t._v("下单量")])])])])],1),s("el-col",{attrs:{span:8}},[s("el-card",{attrs:{shadow:"hover","body-style":{padding:"0px"}}},[s("div",{staticClass:"grid-content grid-con-2"},[s("i",{staticClass:"el-icon-lx-notice grid-con-icon"}),s("div",{staticClass:"grid-cont-right"},[s("div",{staticClass:"grid-num"},[t._v(t._s(t.statics.fail))]),s("div",[t._v("退单量")])])])])],1),s("el-col",{attrs:{span:8}},[s("el-card",{attrs:{shadow:"hover","body-style":{padding:"0px"}}},[s("div",{staticClass:"grid-content grid-con-3"},[s("i",{staticClass:"el-icon-lx-goods grid-con-icon"}),s("div",{staticClass:"grid-cont-right"},[s("div",{staticClass:"grid-num"},[t._v(t._s(t.statics.total))]),s("div",[t._v("总收入")])])])])],1)],1),s("el-card",{staticStyle:{height:"403px"},attrs:{shadow:"hover"}},[s("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[s("span",[t._v("操作记录")])]),s("el-table",{staticStyle:{width:"100%","font-size":"14px"},attrs:{data:t.todoList,"show-header":!1,height:"304"}},[s("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(i){return[s("div",{staticClass:"todo-item"},[t._v(t._s(t._f("formatTime")(i.row.createTime,i.row.createTime))+"    |     "+t._s(i.row.text)+"   | "+t._s(i.row.ip))])]}}])})],1)],1)],1)],1),s("el-row",{attrs:{gutter:20}},[s("el-col",{attrs:{span:12}},[s("el-card",{attrs:{shadow:"hover"}},[s("schart",{ref:"bar",staticClass:"schart",attrs:{canvasId:"bar",data:t.data1,type:"bar",options:t.options}})],1)],1),s("el-col",{attrs:{span:12}},[s("el-card",{attrs:{shadow:"hover"}},[s("schart",{ref:"line",staticClass:"schart",attrs:{canvasId:"line",data:t.data2,type:"line",options:t.options2}})],1)],1)],1)],1)},e=[],o=(s("ac6a"),s("7f7f"),s("f5ac")),n=s("7ed4"),h={name:"dashboard",data:function(){return{statics:{count:0,fail:0,total:0},name:"",userInfo:{},todoList:[],data1:[],data2:[],options:{title:"最近七天每天的用户下单量",showValue:!1,fillColor:"rgb(45, 140, 240)",bottomPadding:30,topPadding:30},options2:{title:"最近七天交易趋势",fillColor:"#FC6FA1",axisColor:"#008ACD",contentColor:"#EEEEEE",bgColor:"#F5F8FD",bottomPadding:30,topPadding:30}}},components:{Schart:o["a"]},computed:{role:function(){return"admin"===this.name?"超级管理员":"普通用户"}},activated:function(){this.handleListener()},deactivated:function(){window.removeEventListener("resize",this.renderChart),n["a"].$off("collapse",this.handleBus)},methods:{changeDate:function(){var t=(new Date).getTime();this.data.forEach((function(i,s){var a=new Date(t-864e5*(6-s));i.name="".concat(a.getFullYear(),"/").concat(a.getMonth()+1,"/").concat(a.getDate())}))},handleListener:function(){n["a"].$on("collapse",this.handleBus),window.addEventListener("resize",this.renderChart)},handleBus:function(t){var i=this;setTimeout((function(){i.renderChart()}),300)},loadStaticsCount:function(){var t=this;this.$GET(this.$API.ADMIN.AdmingetCount).then((function(i){t.statics=i.data}))},renderChart:function(){this.$refs.bar.renderChart(),this.$refs.line.renderChart()}},mounted:function(){var t=this;this.$GET(this.$API.ADMIN.AdminUseInfo,{}).then((function(i){t.userInfo=i.data})),this.$GET(this.$API.ADMIN.LOGURL,{}).then((function(i){t.todoList=i.data})),setInterval((function(){t.loadStaticsCount()}),3e3),this.$GET(this.$API.ADMIN.AdminGetIndexCharts,{}).then((function(i){for(var s=i.data,a=0;a<s.length;a++){var e=s[a];t.$set(t.data1,a,{name:e.time,value:e.count}),t.$set(t.data2,a,{name:e.time,value:e.total})}}))}},l=h,r=(s("0a33"),s("2877")),d=Object(r["a"])(l,a,e,!1,null,"ff733c50",null);i["default"]=d.exports},f2af:function(t,i,s){"use strict";s.r(i);var a=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",[s("el-row",{attrs:{gutter:20}},[s("el-col",{attrs:{span:8}},[s("el-card",{staticClass:"mgb20",staticStyle:{height:"252px"},attrs:{shadow:"hover"}},[s("div",{staticClass:"user-info"},[s("img",{staticClass:"user-avator",attrs:{src:t.userInfo.avatar,alt:""}}),s("div",{staticClass:"user-info-cont"},[s("div",{staticClass:"user-info-name"},[t._v(t._s(t.userInfo.userName))]),1==t.userInfo.role?s("div",[t._v("超级管理员")]):t._e(),0==t.userInfo.role?s("div",[t._v("普通管理员")]):t._e()])]),s("div",{staticClass:"user-info-list"},[t._v("上次登录时间："),s("span",[t._v(t._s(t.userInfo.updateTime))])]),s("div",{staticClass:"user-info-list"},[t._v("上次登录地址："),s("span",[t._v(t._s(t.userInfo.ip))])])]),s("el-card",{staticStyle:{height:"252px"},attrs:{shadow:"hover"}},[s("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[s("span",[t._v("在线影院")])]),s("div",[t._v("一个专业与电影的平台")])])],1),s("el-col",{attrs:{span:16}},[s("el-row",{staticClass:"mgb20",attrs:{gutter:20}},[s("el-col",{attrs:{span:8}},[s("el-card",{attrs:{shadow:"hover","body-style":{padding:"0px"}}},[s("div",{staticClass:"grid-content grid-con-1"},[s("i",{staticClass:"el-icon-lx-people grid-con-icon"}),s("div",{staticClass:"grid-cont-right"},[s("div",{staticClass:"grid-num"},[t._v(t._s(t.statics.count))]),s("div",[t._v("下单量")])])])])],1),s("el-col",{attrs:{span:8}},[s("el-card",{attrs:{shadow:"hover","body-style":{padding:"0px"}}},[s("div",{staticClass:"grid-content grid-con-2"},[s("i",{staticClass:"el-icon-lx-notice grid-con-icon"}),s("div",{staticClass:"grid-cont-right"},[s("div",{staticClass:"grid-num"},[t._v(t._s(t.statics.fail))]),s("div",[t._v("系统消息")])])])])],1),s("el-col",{attrs:{span:8}},[s("el-card",{attrs:{shadow:"hover","body-style":{padding:"0px"}}},[s("div",{staticClass:"grid-content grid-con-3"},[s("i",{staticClass:"el-icon-lx-goods grid-con-icon"}),s("div",{staticClass:"grid-cont-right"},[s("div",{staticClass:"grid-num"},[t._v(t._s(t.statics.total))]),s("div",[t._v("总收入")])])])])],1)],1),s("el-card",{staticStyle:{height:"403px"},attrs:{shadow:"hover"}},[s("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[s("span",[t._v("操作记录")]),s("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"}},[t._v("添加")])],1),s("el-table",{staticStyle:{width:"100%","font-size":"14px"},attrs:{data:t.todoList,"show-header":!1,height:"304"}},[s("el-table-column",{attrs:{width:"40"},scopedSlots:t._u([{key:"default",fn:function(i){return[s("el-checkbox",{model:{value:i.row.status,callback:function(s){t.$set(i.row,"status",s)},expression:"scope.row.status"}})]}}])}),s("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(i){return[s("div",{staticClass:"todo-item",class:{"todo-item-del":i.row.status}},[t._v(t._s(i.row.title))])]}}])}),s("el-table-column",{attrs:{width:"60"},scopedSlots:t._u([{key:"default",fn:function(t){return[s("i",{staticClass:"el-icon-edit"}),s("i",{staticClass:"el-icon-delete"})]}}])})],1)],1)],1)],1),s("el-row",{attrs:{gutter:20}},[s("el-col",{attrs:{span:12}},[s("el-card",{attrs:{shadow:"hover"}},[s("schart",{ref:"bar",staticClass:"schart",attrs:{canvasId:"bar",data:t.data,type:"bar",options:t.options}})],1)],1),s("el-col",{attrs:{span:12}},[s("el-card",{attrs:{shadow:"hover"}},[s("schart",{ref:"line",staticClass:"schart",attrs:{canvasId:"line",data:t.data,type:"line",options:t.options2}})],1)],1)],1)],1)},e=[],o=(s("ac6a"),s("7f7f"),s("f5ac")),n=s("7ed4"),h={name:"dashboard",data:function(){return{name:"",userInfo:{},todoList:[{title:"今天要修复100个bug",status:!1},{title:"今天要修复100个bug",status:!1},{title:"今天要写100行代码加几个bug吧",status:!1},{title:"今天要修复100个bug",status:!1},{title:"今天要修复100个bug",status:!0},{title:"今天要写100行代码加几个bug吧",status:!0}],data:[],statics:{},options:{title:"最近七天每天的用户访问量",showValue:!1,fillColor:"rgb(45, 140, 240)",bottomPadding:30,topPadding:30},options2:{title:"最近七天用户访问趋势",fillColor:"#FC6FA1",axisColor:"#008ACD",contentColor:"#EEEEEE",bgColor:"#F5F8FD",bottomPadding:30,topPadding:30}}},components:{Schart:o["a"]},computed:{role:function(){return"admin"===this.name?"超级管理员":"普通用户"}},created:function(){this.loadStaticsData(),this.handleListener(),this.loadStaticsCount()},activated:function(){this.handleListener()},deactivated:function(){window.removeEventListener("resize",this.renderChart),n["a"].$off("collapse",this.handleBus)},methods:{changeDate:function(){var t=(new Date).getTime();this.data.forEach((function(i,s){var a=new Date(t-864e5*(6-s));i.name="".concat(a.getFullYear(),"/").concat(a.getMonth()+1,"/").concat(a.getDate())}))},handleListener:function(){n["a"].$on("collapse",this.handleBus),window.addEventListener("resize",this.renderChart)},handleBus:function(t){var i=this;setTimeout((function(){i.renderChart()}),300)},renderChart:function(){this.$refs.bar.renderChart(),this.$refs.line.renderChart()},loadStaticsData:function(){var t=this;this.$GET(this.$API.ADMIN.AdminLoadStaticsData).then((function(i){t.data=i.data.map((function(t){return{name:"".concat(t.year,"/").concat(t.mouth,"/").concat(t.day),value:t.count}}))}))},loadStaticsCount:function(){var t=this;this.$GET(this.$API.ADMIN.AdmingetCount).then((function(i){t.statics=i.data}))}},mounted:function(){var t=this;this.$GET(this.$API.ADMIN.AdminUseInfo,{}).then((function(i){t.userInfo=i.data}))}},l=h,r=(s("5705"),s("2877")),d=Object(r["a"])(l,a,e,!1,null,"30ea6305",null);i["default"]=d.exports},f5ac:function(t,i,s){"use strict";var a=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("div",[s("canvas",{attrs:{id:t.canvasId}})])},e=[],o=s("8c93"),n=s.n(o),h={data:function(){return{schart:null,opt:{}}},props:{canvasId:{type:String,default:""},type:{type:String,default:"bar"},data:{type:Array,default:[]},options:{type:Object,required:!1}},mounted:function(){this.renderChart()},methods:{renderChart:function(){this.schart=null,this.opt=this.options,this.width&&this.height||(this.opt?this.opt["autoWidth"]=!0:this.opt={autoWidth:!0}),this.schart=new n.a(this.canvasId,this.type,this.data,this.opt)}},watch:{data:function(){this.renderChart()},options:function(){this.renderChart()},type:function(){this.renderChart()}}},l=h,r=s("2877"),d=Object(r["a"])(l,a,e,!1,null,null,null);i["a"]=d.exports}}]);