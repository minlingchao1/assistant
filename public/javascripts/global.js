// JavaScript Document
define(function (require, exports, module) {var $ = require('jquery');
	//载入框
	Global={
		center:function(obj){
			var _scrollHeight = $(document).scrollTop(), 
				_windowHeight = $(window).height(), 
				_posiTop = _windowHeight / 2 + _scrollHeight - obj.height()/2;	
			obj.css({
				"top": _posiTop,
				"left": $(window).width() / 2 - obj.width()/2
			});
		},
		tip:function(Class,html,postion){			
			$('<div class="'+Class+'" style="display:none;"></div>').appendTo("body").html(html).css({
				"position": "absolute",				
				"z-index": 200000,
				"display": 'block'
			});	
			if(!postion){
				Global.center($("."+Class));
			}else{
				$("."+Class).css(postion)
			}			
		},
		loadingTip:function(text){
			var t=text||'';
			if(text!="hide"){
				Global.tip("loadingModal",t+'<br /><img src="http://img03.taobaocdn.com/imgextra/i3/424372210/T2_YmbXqNaXXXXXXXX-424372210.gif" alt="" />');
			}else{
				$(".loadingModal").remove();
			}		
		},
		webLoading:function(text){
			var t=text||'';
			if(text!="hide"){
				Global.tip("webLoading",'<img src="http://img01.taobaocdn.com/imgextra/i1/424372210/TB28WVZbpXXXXbnXXXXXXXXXXXX_!!424372210.gif" alt="" />');
			}else{
				$(".webLoading").remove();
			}		
		},
		//成功提示
		successTip:function(text){
			Global.tip("successModal",'<img src="http://img03.taobaocdn.com/imgextra/i3/424372210/T269FyXttaXXXXXXXX-424372210.png" style="vertical-align: middle;"  />'+text);
			$('.successModal').addClass("alert-info alert");
			setTimeout(closeDialog,3000); 
			function closeDialog(){
				$('.successModal').remove();		
			}		
		},
		//错误提示
		errorTip:function(text){
			Global.tip("errorModal",'<img src="http://img04.taobaocdn.com/imgextra/i4/424372210/T245NLXxBaXXXXXXXX_!!424372210.png" style="vertical-align: middle;" />'+text);
			$('.errorModal').addClass("alert-danger alert");
			setTimeout(closeDialog,3000); 
			function closeDialog(){
				$('.errorModal').remove();		
			}
		},
		//警告
		warnTip:function(text){
			Global.tip("wranModal",'<img src="http://img03.taobaocdn.com/imgextra/i3/424372210/T2TH4UXqXXXXXXXXXX_!!424372210.png" />'+text);
			setTimeout(closeDialog,3000); 
			function closeDialog(){
				$('.wranModal').remove();		
			}
		},
		vaildFalse:function(text,target,offset){
			$('<div class="vaildFalse" data-num="'+$(".vaildFalse").length+'"><img src="http://img04.taobaocdn.com/imgextra/i4/424372210/T245NLXxBaXXXXXXXX_!!424372210.png" />'+text+"</div>").appendTo("body").css({
				"position": "absolute",				
				"z-index": 200000,
				"display": 'block',
				"background-color": "#f2dede",
				"border": "1px solid #ebccd1",
				"padding":"2px 6px",
				"color": "a94442",
				"left":$(target).offset().left+offset[0],
				"top":$(target).offset().top+offset[1]
			});
			$(target).on("change keyup",function(){
				$(".vaildFalse"+$(".vaildFalse").length).remove();
			})
		},
		//输入框
		promoptTip:function(text,fun){			
			Global.tip("promoptModal",text+':<input type="text" class="form-control"><br><button type="button" class="btn btn-primary btn-sm">确定</button><button type="button" class="btn btn-default btn-sm">取消</button>');
			$(".promoptModal .btn-default").css({"margin-left":"10px"}).click(function(){
				$(".promoptModal").remove();
			});
			$(".promoptModal .btn-primary").css({"margin-left":"45px"}).click(function(){
				fun($(".promoptModal input").val());
				$(".promoptModal").remove();
			});
			$(".promoptModal").addClass("alert alert-info")
		},
		//确认框
		comfirmTip:function(text,leftText,leftFun,rightText,rightFun){				
			Global.tip("comfirmModal",text+'<br><button type="button" class="btn btn-primary btn-sm">'+(leftText||'确认')+'</button><button type="button" class="btn btn-default btn-sm">'+(rightText||'取消')+'</button>');
			$(".comfirmModal .btn-default").css({"margin-left":"10px","margin-top":"15px"}).click(function(){
				$(".comfirmModal").remove();
				rightFun&&rightFun()				
			});
			$(".comfirmModal .btn-primary").css({"margin-left":"30px","margin-top":"15px"}).click(function(){
				leftFun&&leftFun();
				$(".comfirmModal").remove();
			});
			$(".comfirmModal").addClass("alert alert-info").css({width:"260px"})
		},
		//mask
		showMask:function(cssOption,data){
			var cssOption=$.extend({},{
					"top": "0",
					"left": "0",
					"position":"fixed",
					"background":"#000",
					"opacity": ".50",
					"filter": "Alpha(Opacity=50)",
					"width":"100%",
					"height":"100%"
			},cssOption);
			$('<div class="MaskModal"></div>').css(cssOption).appendTo("body").attr("data",typeof data=="undefined"?'':data);
		},
		//mask
		hideMask:function(){
			$('.MaskModal').remove();
		},
		//分页
		setPage:function(pageDom,ajaxUrl,data,callback){
			pageDom.pagination({
				currPage: 1,
				pageCount:10,
				ajax: {
					param:data,
					on: true,
					callback:callback||"",				
					url: ajaxUrl,
					dataType: 'json'
				}
			})
		},	
		setCookie:function(c_name, value, expiredays) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + expiredays);
			document.cookie = c_name + "=" + escape(value) + ";path=/" + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
		},
		getCookie:function(Name) {
			var search = Name + "=";
			var returnvalue = "";
			if (document.cookie.length > 0) {
				offset = document.cookie.indexOf(search);
				if (offset != -1) {
					offset += search.length;
					end = document.cookie.indexOf(";", offset);
					if (end == -1) end = document.cookie.length;
					returnvalue = unescape(document.cookie.substring(offset, end));
				}
			}
			return returnvalue;
		},
		delCookie:function(name)//删除cookie
		{
			var exp = new Date();
			exp.setTime(exp.getTime() - 1);
			var cval=this.getCookie(name);
			if(cval!=null) 
			document.cookie = name + "=" + escape(cval) +";path=/;expires="+exp.toGMTString();
			
		},
		isMobile:function(mobile){
			var re=/^[0-9]+$/;
			if(mobile.length==11&&re.test(mobile)){
				return true;
			}else{
				return false;			
			}
		},
		isEmail:function(mail){
			var re=/^(.+)@(.+)\.(.+)$/;
			if(re.test(mail)){
				return true;
			}else{
				return false;			
			}
		},
		isUrl:function(url){
			var re=/((http\:\/\/)|(www\.))*\.(com|cn|net)/g;
			if(re.test(url)){
				return true;
			}else{
				return false;			
			}
		},
		isPrice:function(price){
			var re = /^[0-9]+.?[0-9]*$/;
			if (re.test(price)) {  
				return true;  
			}else{
				return false;
			}
		},
		getByteLen:function(str) { 
			var l=str.length; 
			var n=l; 
			for(var i=0;i<l;i++) {
				if(str.charCodeAt(i)<0||str.charCodeAt(i)>255) 
				{
					n++; 
				}
			}
			return n 
		},
		getUrlParam:function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r!=null) return unescape(r[2]); return null;
		},
		jsonToString :function(obj){   
			var THIS = this;    
			switch(typeof(obj)){   
				case 'string':   
					return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';   
				case 'array':   
					return '[' + obj.map(THIS.jsonToString).join(',') + ']';   
				case 'object':   
					 if(obj instanceof Array){   
						var strArr = [];   
						var len = obj.length;   
						for(var i=0; i<len; i++){   
							strArr.push(THIS.jsonToString(obj[i]));   
						}   
						return '[' + strArr.join(',') + ']';   
					}else if(obj==null){   
						return 'null';   
	   
					}else{   
						var string = [];   
						for (var property in obj) string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));   
						return '{' + string.join(',') + '}';   
					}   
				case 'number':   
					return obj;   
				case false:   
					return obj;   
			}   
		},
		contains:function (arr,elem) {
			for (var i = 0; i < arr.length; i++) {
			if (arr[i] == elem) {
				return true;
				}
			}
			return false;
		}
	}
	module.exports=Global;
})