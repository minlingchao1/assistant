define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var datepicker=require("datepicker");
	var validate=require("validate");
	var Dropzone=require('dropzone');
	var SCENEADD={
		init:function(){
			SCENEADD.wechatId=until.getUrlParam("wechatId");
			SCENEADD.bind();
			SCENEADD.dateSet();
			SCENEADD.uploadFile();
			SCENEADD.sceneInit();
		},
		bind:function(){
			 $("#subScene").click(SCENEADD.subSceneInfo);
		},
		dateSet:function(){
			$('#date .input-daterange').datepicker({
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true
            });
           
		},
		sceneInit:function(){
			
		},
		subSceneInfo:function(){
				   //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
		    var sceneName=$("#sceneName").val();
		    var start=$("#start").val();
		    var end=$("#end").val();
		    var keyword=$("#end").val();
		    if(sceneName==""){
		    	alert("场景名不能为空！");
		    	return;
		    }
		    if(start==""){
		    	alert("起始时间不能为空!");
		    	return;
		    }
		    if(end==""){
		    	alert("结束时间不能为空!");
		    	return;
		    }
		    if(end<start){
		    	alert("结束时间不能小于起始时间!");
		    	return;
		    }
		    if(SCENEADD.pageImg==""){
		    	alert("图文封面不能为空!");
		    	return;
		    }
		    if(SCENEADD.openImg==""){
		    	alert("场景开场画面不能为空!");
		    	return;
		    }
		    if(SCENEADD.outImg==""){
		    	alert("场景过期画面不能为空!");
		    	return;
		    }
		   
            $.ajax({
        		type:"post",
        		url:"/scene/SceneCtrl/addScene",
        		dataType:"json",
        		data:{
        			wechatId:SCENEADD.wechatId,
        			sceneName:$("#sceneName").val(),
        			start:new Date($("#start").val()).getTime(),
        			end:new Date($("#end").val()).getTime(),
        			keyword:$("#keyword").val(),
        			pageImg:SCENEADD.pageImg,
        			descr:$("#descr").val(),
        			shareTitle:$("#shareTitle").val(),
        			shareContent:$("#shareContent").val(),
        			openSet:$("#openSet").val(),
        			openImg:SCENEADD.openImg,
        			openBtnImg:SCENEADD.openBtnImg,
        			openBtnPos:$("#openBtnPos").val(),
        			openBtnUrl:$("#openBtnUrl").val(),
        			outSet:$("#outSet").val(),
        			outImg:SCENEADD.outImg,
        			outBtnImg:SCENEADD.outBtnImg,
        			outBtnPos:$("#outBtnPos").val(),
        			outBtnUrl:$("#outBtnUrl").val(),
        			slide:$("#slide").val(),
        			musicSet:$("#musicSet").val(),
        			music:SCENEADD.backMusic
        		},
        		success:function(data){
        			if(data.success){
        				alert("微场景创建成功!");
        				location.href="/AssistantUI/scene?wechatId="+SCENEADD.wechatId;
        			}else{
        				alert(data.message);
        			}
        		}
        	});		    
	        
	
		},
		uploadFile:function(){
			$(".dropz-pageImg").dropzone({
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
	            	   SCENEADD.pageImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEADD.pageImg=response.url;
	                	//alert(BINDWECHAT.qrcode);
	                })
	            }
	        });
	        $(".dropz-openImg").dropzone({
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
	            	   SCENEADD.openImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEADD.openImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-openBtnImg").dropzone({
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
	            	   SCENEADD.openBtnImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEADD.openBtnImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-outImg").dropzone({
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
	            	   SCENEADD.outImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEADD.outImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-outBtnImg").dropzone({
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
	            	   SCENEADD.outBtnImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEADD.outBtnImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	         $(".dropz-backMusic").dropzone({
	            autoProcessQueue: true,
	            uploadMultiple: true,
	            parallelUploads: 100,
	            maxFiles: 1,
	            maxFilesize: 10,
	            acceptedFiles:"audio/mp3,.wma,.wav,.amr",
	            uploadMultiple:false,
	            addRemoveLinks:true,
	            init:function(){
	            	this.on("removedfile", function(file) {
	            	   SCENEADD.backMusic="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEADD.backMusic=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
       }
	}
	module.exports=SCENEADD
})
