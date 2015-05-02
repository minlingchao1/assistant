define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var Dropzone=require('dropzone');
	var SCENEIMGADD={
		init:function(){
			SCENEIMGADD.sceneId=until.getUrlParam("sceneId");
			SCENEIMGADD.bind();
			SCENEIMGADD.uploadFile();
		},
		bind:function(){
			 $("#subSceneImg").click(SCENEIMGADD.subSceneImg);
		},

		subSceneImg:function(){
				   //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
		    var reorder=$("#reorder").val();
		   
		    if(reorder==""){
		    	alert("场景名不能为空！");
		    	return;
		    }
		    if(SCENEIMGADD.img==""){
		    	alert("场景过期画面不能为空!");
		    	return;
		    }
		   
            $.ajax({
        		type:"post",
        		url:"/scene/SceneImgCtrl/addSceneImg",
        		dataType:"json",
        		data:{
        			sceneId:SCENEIMGADD.sceneId,
        			reorder:$("#reorder").val(),
        		    img:SCENEIMGADD.img,
        		    imgSet:$("#imgSet").val(),
        		    btnImg:SCENEIMGADD.btnImg,
        		    btnPos:$("#btnPos").val(),
        		    btnUrl:$("#btnUrl").val(),
        		},
        		success:function(data){
        			if(data.success){
        				alert("微场景创建成功!");
        				location.href="/AssistantUI/sceneImg?sceneId="+SCENEIMGADD.sceneId;
        			}else{
        				alert(data.message);
        			}
        		}
        	});		    
	        
	
		},
		uploadFile:function(){
			
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
	            	   SCENEIMGADD.img="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEIMGADD.img=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-btnImg").dropzone({
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
	            	   SCENEIMGADD.btnImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	SCENEIMGADD.btnImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	      
       }
	}
	module.exports=SCENEIMGADD
})
