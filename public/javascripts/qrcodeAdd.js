define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var validate=require("validate")
	var QRCODEADD={
		init:function(){
			QRCODEADD.wechatId=until.getUrlParam("wechatId");
			QRCODEADD.bind();
			QRCODEADD.subQrcodeInfo();
		},
		bind:function(){
		},
		subQrcodeInfo:function(){
				   //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
	        $.validator.setDefaults({
	            highlight: function (element) {
	                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	            },
	            success: function (element) {
	                element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            },
	            errorElement: "span",
	            errorClass: "help-block m-b-none",
	            validClass: "help-block m-b-none",
	            onsubmit:true,// 是否在提交是验证  
	
	        });
	         //以下为官方示例
	        $().ready(function () {
	            // validate signup form on keyup and submit
	            $("#qrcodeForm").validate({
	                rules: {
	                    sceneName: {
	                        required: true,
	                        minlength: 2
	                    },
	                    sceneId:{
	                    	required:true,
	                    	maxlength:10000
	                    },
	                    actionInfo:{
	                    	required:true
	                    },
	                    expireSeconds:{
	                    	required:true,
	                    	min:30,
	                    	max:1800,
	                    	digits:true
	                    }
	                },
	                messages: {  
	                    sceneName: {
	                        required: "请输入场景名",
	                        minlength: "场景名必须两个字符以上"
	                    },
	                    sceneId:{
	                    	required:"请输入场景ID",
	                        maxlength:"最多输入10000"
	                    },
	                    actionInfo:{
	                    	required:"请输入二维码详细信息"
	                    },
	                    expireSeconds:{
	                    	required:"true",
	                    	min:"最小值为30",
	                    	max:"最大值为1800",
	                    	digits:"只能输入整数"
	                    }
	                },
	                submitHandler:function(form){
	                	$.ajax({
	                		type:"post",
	                		url:"/qrcode/QrcodeCtrl/saveQrcodeInfo",
	                		dataType:"json",
	                		data:{
	                			wechatId:QRCODEADD.wechatId,
	                			sceneName:$("#sceneName").val(),
	                			sceneId:$("#sceneId").val(),
	                			actionName:$("#actionName").val(),
	                			actionInfo:$("#actionInfo").val(),
	                			expireSeconds:$("#expireSeconds").val()
	                		},
	                		success:function(data){
	                			if(data.success){
	                				alert("创建二维码成功!");
	                				location.href="/AssistantUI/qrcodeCount?wechatId="+QRCODEADD.wechatId;
	                			}else{
	                				alert(data.message);
	                			}
	                		}
	                	});
	                },
	                invalidHandler: function(form, validator) {  //不通过回调  
	                  return false;  
	               }  
	            });
	        });
		}
	}
	module.exports=QRCODEADD
})
