define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var emoji=require('emotion.js');
	var Dropzone = require("dropzone");
	var FIRSTFOLLOW={
		init:function(){
			FIRSTFOLLOW.bind();
			FIRSTFOLLOW.wechatId=until.getUrlParam("wechatId");
			FIRSTFOLLOW.emotion();
			FIRSTFOLLOW.uploadFile();
		},
		bind:function(){
			$("#subTxt").click(FIRSTFOLLOW.subTxt);
			$("#subImg").click(FIRSTFOLLOW.subImg);
			$("#subVoice").click(FIRSTFOLLOW.subVoice);
			$("#subVideo").click(FIRSTFOLLOW.subVideo);
			$("#cancelTxt").click(FIRSTFOLLOW.removeTextMsg);
		},
		subTxt:function(){
            var txtMsg=$.trim($("#txtMsg").val());
//          alert(txtMsg);
            if(txtMsg==""){
            	alert("回复内容不能为空!");
            }else{
            	$.ajax({
				type:"post",
				url:"/subscribe/SubscribeCtrl/add",
				dataType:"json",
				data:{
					wechatId:FIRSTFOLLOW.wechatId,
					type:0,
					replyMsg:txtMsg
				},
				success:function(data){
					if(data.success){
						alert("添加成功！");
					}else{
						alert(data.message);
					}
				}
			});
            }
            
			
		},
		subImg:function(){
			alert();
			var imgMsg=$.trim(FIRSTFOLLOW.img);
			alert(imgMsg);
			if(imgMsg==""){
				alert("请上传图片！");
			}else{
				$.ajax({
				type:"post",
				url:"/subscribe/SubscribeCtrl/add",
				dataType:"json",
				data:{
					wechatId:FIRSTFOLLOW.wechatId,
					type:1,
					replyMsg:imgMsg
				},
				success:function(data){
					if(data.success){
						alert("添加成功!");
					}else{
						alert(data.message);
					}
				}
			});
			}
			
			
		},
		subVoice:function(){
			var voiceMsg=$.trim(FIRSTFOLLOW.voice);
			alert(voiceMsg);
			if(voiceMsg==""){
				alert("请上传语音！");
			}else{
				$.ajax({
				type:"post",
				url:"/subscribe/SubscribeCtrl/add",
				dataType:"json",
				data:{
					wechatId:FIRSTFOLLOW.wechatId,
					type:1,
					replyMsg:voiceMsg
				},
				success:function(data){
					if(data.success){
						alert("添加成功!");
					}else{
						alert(data.message);
					}
				}
			});
			}
			
			
		},
		subVideo:function(){
			var videoMsg=$.trim(FIRSTFOLLOW.video);
			alert(videoMsg);
			if(videoMsg==""){
				alert("请上传视频！");
			}else{
				$.ajax({
				type:"post",
				url:"/subscribe/SubscribeCtrl/add",
				dataType:"json",
				data:{
					wechatId:FIRSTFOLLOW.wechatId,
					type:1,
					replyMsg:videoMsg
				},
				success:function(data){
					if(data.success){
						alert("添加成功!");
					}else{
						alert(data.message);
					}
				}
			});
			}
			
			
		},
		removeTextMsg:function(){
			document.getElementById("txtMsg").value = "";
			document.getElementById("txtMsgArea").innerHTML = "";
		},
        emotion:function(){
        	var $textarea = $(".editArea textarea");
			var $contentDiv = $(".editArea div");
			$(".wysiwyg-toolbar-icon").click(function(){
				//Emotion.saveRange();
				//alert();
				$(".emotions").show();
			});
			$(".emotions").hover(function(){
				
			},function(){
				$(".emotions").fadeOut();
			});
			$(".emotions .eItem").mouseenter(function(){
				$(".emotionsGif").html('<img src="'+$(this).attr("data-gifurl")+'">');
			}).click(function(){
				Emotion.insertHTML('<img src="' + $(this).attr("data-gifurl") + '"' + 'alt="mo-' + $(this).attr("data-title") + '"' + "/>");
				$(".emotions").fadeOut();
				$textarea.trigger("contentValueChange");
			});
			$contentDiv.bind("keyup",function(){
				$textarea.trigger("contentValueChange");
				Emotion.saveRange();
			}).bind("keydown",function(e){
			    switch (e.keyCode) {
			    case 8:
			        var t = Emotion.getSelection();
			        t.type && t.type.toLowerCase() === "control" && (e.preventDefault(), t.clear());
			        break;
			    case 13:
			        e.preventDefault(),
			        Emotion.insertHTML("<br/>");
			        Emotion.saveRange();
			    }
			}).bind("mouseup",function(e){
			    Emotion.saveRange();
			    if (!$.support.boxModel && />$/.test($contentDiv.html())) {
			        var n = Emotion.getSelection();
			        n.extend && (n.extend(cursorNode, cursorNode.length), n.collapseToEnd()),
			        Emotion.saveRange();
			        Emotion.insertHTML(" ");
			    }
			});
			$textarea.bind("contentValueChange",function(){
				$(this).val(Emotion.replaceInput($contentDiv.html()));
			});
			$contentDiv.html(Emotion.replaceEmoji($.trim($contentDiv.html())));
	  },
	  uploadFile:function(){
		$(".dropz-img").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 1,
            acceptedFiles:"image/jpg,.jpeg,.jpg",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   FIRSTFOLLOW.img="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	FIRSTFOLLOW.img=response.url;
                	alert(FIRSTFOLLOW.img);
                })
            }
        });
        $(".dropz-voice").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 2,
            acceptedFiles:"audio/mp3,.amr",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   FIRSTFOLLOW.voice="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	FIRSTFOLLOW.voice=response.url;
                	alert(FIRSTFOLLOW.voice);
                })
            }
        });
        $(".dropz-video").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 10,
            acceptedFiles:"video/mp4",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   FIRSTFOLLOW.video="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	FIRSTFOLLOW.video=response.url;
                	alert(FIRSTFOLLOW.video);
                })
            }
        });
	  }
	}
	module.exports=FIRSTFOLLOW
});
