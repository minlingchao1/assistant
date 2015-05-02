define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var Dropzone=require('dropzone');
	var step=require('step');
    var BINDWECHAT={
    	init:function(){
    		BINDWECHAT.wechatId=until.getUrlParam("wechatId");
    		if(BINDWECHAT.wechatId){
    			BINDWECHAT.editWXInfo();
    		}
    		BINDWECHAT.bind(); 
    		BINDWECHAT.uploadFile();
    		BINDWECHAT.step();
    	},
    	bind:function(){
    		$(".manualBind").click(BINDWECHAT.subWechatInfo);
    	},
    	step:function(){
    		$("#wizard").steps();
    	},
    	subWechatInfo:function(){
    		for(var i=0;i<$(".wechatInfo .need").length;i++){
    			if(!$(".wechatInfo .need").eq(i).val()){
    				dialog({
    					width:400,
    					title:'创建失败！',		
    					content:'<p class="bg-warning text-warning" style="padding:15px;">请填写'+$(".wechatInfo .need").eq(i).parents(".form-group").find("label").html()+"</p>"
    				}).showModal();
    				return 
    				break;
    			}
    	     }
//  		 BINDWECHAT.creatPOP=dialog({
//  		 	width:400,
//  		 	title:'添加中',
//  		 	content:'正在添加相关微信公众号信息,请稍候'
//  		 }).showModal();
//    		 
    		 $.ajax({
    		 	type:"post",
    		 	url:"/merchant/MerchantCtrl/manualBind",
    		 	dataType:"json",
    		 	data:{
    		 		wechatId:BINDWECHAT.wechatId,
    		 		accountName:$("#accountName").val(),
    		 		type:$("#type").val(),
    		 		authenticate:$("#authenticate").val(),
    		 		appId:$("#appId").val(),
    		 		appSecret:$("#appSecret").val(),
    		 		wechatNumber:$("#wechatNumber").val(),
    		 		accountId:$("#accountId").val(),
    		 		qrCode:BINDWECHAT.qrcode,
    		 		headImage:BINDWECHAT.headImage
    		 	},
    		 	success:function(data){
    		 		if(data.success){
    		 			alert("添加微信账号成功!");
    		 			location.href="/AssistantUI/index";
    		 		}else{
    		 			alert(data.message);
    		 		}
    		 	},
    		 	error:function(){
					BINDWECHAT.creatPOP.remove();
				}
    		 });
       },
       editWXInfo:function(){
       	     BINDWECHAT.getWXInfo();
       },
       getWXInfo:function(){
       	    $.ajax({
       	    	url:"/merchant/MerchantCtrl/getWXInfo",
       	    	dataType:"json",
       	    	data:{
       	    		wechatId:BINDWECHAT.wechatId
       	    	},
       	    	success:function(data){
       	    		$("#accountName").val(data.results.accountName);
       	    		$("#type").val(data.results.type);
       	    		$("#authenticate").val(data.results.authenticate);
       	    		$("#appId").val(data.results.appId);
       	    		$("#appSecret").val(data.results.appSecret);
       	    		$("#wechatNumber").val(data.results.wechatNumber);
       	    		$("#accountId").val(data.results.accountId);
       	    		$("#qrcode-img").attr("src",data.results.qrcode);
       	    		$("#headImage-img").attr("src",data.results.headImage);
       	    		$("#qrcode-review").show();
       	    		$("#headImage-review").show();
       	    	},
       	    	error:function(){
       	    		alert(data.message);
       	    	}
       	    })
       },
       uploadFile:function(){
		$(".dropz-qrcode").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 2,
            acceptedFiles:"image/bmp,.png,.jpeg,.jpg,.gif",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   BINDWECHAT.qrcode="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	BINDWECHAT.qrcode=response.url;
                	//alert(BINDWECHAT.qrcode);
                })
            }
        });
        $(".dropz-headimage").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 2,
            acceptedFiles:"image/bmp,.png,.jpeg,.jpg,.gif",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   BINDWECHAT.headImage="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	BINDWECHAT.headImage=response.url;
                	//alert(BINDWECHAT.headImage);
                })
            }
        });
    }
  }
    module.exports=BINDWECHAT
});
