define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var Dropzone=require('dropzone');
	var OVERALLADD={
		init:function(){
			OVERALLADD.sceneId=until.getUrlParam("wechatId");
			OVERALLADD.bind();
			OVERALLADD.uploadFile();
		},
		bind:function(){
			 $("#subOverall").click(OVERALLADD.subOverallInfo);
		},

		subOverallInfo:function(){
				   //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
            for(var i=0;i<$(".overallInfo .need").length;i++){
				if(!$(".overallInfo .need").eq(i).val()){
					dialog({
						width:400,
						title: '创建失败!',
						content: '<p class="bg-warning text-warning" style="padding:15px;">请填写'+$(".overallInfo .need").eq(i).parents(".form-group").find("label").html()+"</p>"
					}).showModal();
					return
					break;
				}
			}
		   
            $.ajax({
        		type:"post",
        		url:"/overall/OverallCtrl/addOverall",
        		dataType:"json",
        		data:{
        			name:$("#name").val(),
        			keyword:$("#keyword").val(),
        			descr:$("#descr").val(),
        			pageImg:OVERALLADD.pageImg,
        			left:OVERALLADD.left,
        			right:OVERALLADD.right,
        			top:OVERALLADD.top,
        			bottom:OVERALLADD.bottom,
        			front:OVERALLADD.front,
        			behind:OVERALLADD.behind,
        			musicSet:$("#musicSet").val(),
        			backMusic:OVERALLADD.backMusic
        		},
        		success:function(data){
        			if(data.success){
        				alert("全景图创建成功!");
        				location.href="/AssistantUI/overall?wechatId="+OVERALLADD.wechatId;
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
	            	   OVERALLADD.pageImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.pageImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-left").dropzone({
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
	            	   OVERALLADD.left="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.left=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        
	        $(".dropz-right").dropzone({
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
	            	   OVERALLADD.right="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.right=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-top").dropzone({
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
	            	   OVERALLADD.top="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.top=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        
	        $(".dropz-bottom").dropzone({
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
	            	   OVERALLADD.bottom="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.bottom=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        $(".dropz-front").dropzone({
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
	            	   OVERALLADD.front="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.front=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        
	        $(".dropz-behind").dropzone({
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
	            	   OVERALLADD.behind="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.behind=response.url;
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
	            	   OVERALLADD.backMusic="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	OVERALLADD.backMusic=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	        
       }
	}
	module.exports=OVERALLADD
})
