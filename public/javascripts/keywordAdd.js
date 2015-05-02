define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var emoji=require('emotion.js');
	var Dropzone = require("dropzone");
	var yyucadapter=require('yyucadapter.js');
	var KEYWORDADD={
		init:function(){		
			KEYWORDADD.wechatId=until.getUrlParam("wechatId");
			KEYWORDADD.keyId=until.getUrlParam("id");
			if(KEYWORDADD.keyId){
				KEYWORDADD.edit();
			}
			KEYWORDADD.emotion();
			KEYWORDADD.uploadFile();
			KEYWORDADD.bind();
		},
		bind:function(){
			$("#subTxt").click(KEYWORDADD.subTxt);
			$("#subImg").click(KEYWORDADD.subImg);
			$("#subVoice").click(KEYWORDADD.subVoice);
			$("#subVideo").click(KEYWORDADD.subVideo);
			$("#subNews").click(KEYWORDADD.subNews);
			$("#cancelTxt").click(KEYWORDADD.removeTextMsg);
			$("#addaline").click(KEYWORDADD.addItem);
			
		},
		subTxt:function(){
            var txtMsg=$.trim($("#txtMsg").val());
            var ruleTxt=$.trim($("#rule-txt").val());
            var keyword=$.trim($("#keyword-txt").val());
            if(ruleTxt==""){
            	alert("规则名不能为空!");
            }else if(keyword==""){
            	alert("关键字不能为空!");
            }else if(txtMsg==""){
            	alert("回复内容不能为空!");
            }else{
            	$.ajax({
				type:"post",
				url:"/reply/KeywordReplyRuleCtrl/add",
				dataType:"json",
				data:{
					wechatId:KEYWORDADD.wechatId,
					type:0,
					ruleName:$("#rule-txt").val(),
					keyword:$("#keyword-txt").val(),
					isAllMatch:$("#match-txt").val(),
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
            var ruleImg=$.trim($("#rule-img").val());
            var keyword=$.trim($("#keyword-img").val());
			var imgMsg=$.trim(KEYWORDADD.img);
			if(ruleImg==""){
            	alert("规则名不能为空!");
            }else if(keyword==""){
            	alert("关键字不能为空!");
            }else if(imgMsg==""){
				alert("请上传图片！");
			}else{
				$.ajax({
				type:"post",
				url:"/reply/KeywordReplyRuleCtrl/add",
				dataType:"json",
				data:{
					wechatId:KEYWORDADD.wechatId,
					type:1,
					ruleName:$("#rule-img").val(),
					keyword:$("#keyword-img").val(),
					isAllMatch:$("#match-img").val(),
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
			var ruleVoice=$.trim($("#rule-voice").val());
            var keyword=$.trim($("#keyword-voice").val());
			var voiceMsg=$.trim(KEYWORDADD.voice);
			if(ruleVoice==""){
            	alert("规则名不能为空!");
            }else if(keyword==""){
            	alert("关键字不能为空!");
            }else if(voiceMsg==""){
				alert("请上传语音资源！");
			}else{
			  $.ajax({
				type:"post",
				url:"/reply/KeywordReplyRuleCtrl/add",
				dataType:"json",
				data:{
					wechatId:KEYWORDADD.wechatId,
					type:2,
					ruleName:$("#rule-voice").val(),
					keyword:$("#keyword-voice").val(),
					isAllMatch:$("#match-voice").val(),
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
			var ruleVideo=$.trim($("#rule-video").val());
            var keyword=$.trim($("#keyword-video").val());
			var videoMsg=$.trim(KEYWORDADD.video);
			if(ruleVideo==""){
            	alert("规则名不能为空!");
            }else if(keyword==""){
            	alert("关键字不能为空!");
            }else if(videoMsg==""){
				alert("请上传视频资源！");
			}else{
				$.ajax({
				type:"post",
				url:"/reply/KeywordReplyRuleCtrl/add",
				dataType:"json",
				data:{
					wechatId:KEYWORDADD.wechatId,
					type:3,
					ruleName:$("#rule-video").val(),
					keyword:$("#keyword-video").val(),
					isAllMatch:$("#match-video").val(),
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
		subNews:function(){
			
			var ruleNews=$.trim($("#rule-news").val());
            var keyword=$.trim($("#keyword-news").val());
           
			alert(newsId);
			if(ruleNews==""){
            	alert("规则名不能为空!");
            	return;
            }
			if(keyword==""){
            	alert("关键字不能为空!");
            	return;
            }
			var isExist=window.frames["newspaper"].document.getElementById("newsId");
            if(isExist){
            	var newsId=window.frames["newspaper"].document.getElementById("newsId").value;
            }else{
            	alert("请选择图文消息！");
				return;
            }
			$.ajax({
			type:"post",
			url:"/reply/KeywordReplyRuleCtrl/addNews",
			dataType:"json",
			data:{
				wechatId:KEYWORDADD.wechatId,
				type:4,
				ruleName:$("#rule-news").val(),
				keyword:$("#keyword-news").val(),
				isAllMatch:$("#match-news").val(),
				msgId:window.frames["newspaper"].document.getElementById("newsId").value
			},
			success:function(data){
				if(data.success){
					alert("添加成功!");
				}else{
					alert(data.message);
				}
			}
		 });
			
		},
		removeTextMsg:function(){
			document.getElementById("rule-txt").value="";
			document.getElementById("keyword-txt").value="";
			document.getElementById("match-txt").value="0";
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
            maxFilesize: 2,
            acceptedFiles:"image/bmp,.png,.jpeg,.jpg,.gif",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   KEYWORDADD.img="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	KEYWORDADD.img=response.url;
                	alert(KEYWORDADD.img);
                })
            }
        });
        
        $(".dropz-voice").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 5,
            acceptedFiles:"audio/mp3,.wma,.wav,.amr",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   KEYWORDADD.voice="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	KEYWORDADD.voice=response.url;
                	alert(KEYWORDADD.voice);
                })
            }
        });
        $(".dropz-video").dropzone({
            autoProcessQueue: true,
            uploadMultiple: true,
            parallelUploads: 100,
            maxFiles: 1,
            maxFilesize: 20,
            acceptedFiles:"video/rm,.rmvb,.wmv,.avi,.mpg,.mpeg,.mp4",
            uploadMultiple:false,
            addRemoveLinks:true,
            init:function(){
            	this.on("removedfile", function(file) {
            	   KEYWORDADD.video="";
                   alert("删除成功!");
                });
                this.on("success",function(file,response){
                	KEYWORDADD.video=response.url;
                	alert(KEYWORDADD.video);
                })
            }
        });
	  },
	  edit:function(){
	  	 KEYWORDADD.getReplyInfo();
	  },
	  getReplyInfo:function(){
	  	 $.ajax({
	  	 	type:"get",
	  	 	url:"/reply/KeywordReplyRuleCtrl/edit",
	  	 	dataType:"json",
	  	 	data:{
	  	 		id:KEYWORDADD.keyId
	  	 	},
	  	 	success:function(data){
	  	 		if(data.results.type==0){
	  	 			var liText=document.getElementById("li_text");
	  	 			liText.setAttribute("className","active");
	  	 			$("#rule-txt").val(data.results.ruleName);
	  	 			$("#keyword-txt").val(data.results.keyword);
	  	 			$("#match-txt").val(data.results.isAllMatch);
	  	 			//var csdata = $.evalJSON(data.results.replyMsg);
	  	 			//alert(csdata.);
	  	 			var $contentDiv = $(".editArea div");
	  	 			$contentDiv.html(data.results.replyMsg);
	  	 			//$("#txtMsg").val(data.results.replyMsg);
	  	 		}else if(data.results.type==1){
	  	 			var liImg=document.getElementById("li_image");
	  	 			liImg.setAttribute("className","active");
	  	 			$("#rule-img").val(data.results.ruleName);
	  	 			$("#keyword-img").val(data.results.keyword);
	  	 			$("#match-img").val(data.results.isAllMatch);
	  	 			//$("#txtMsg").value(data.results.replyMsg);
	  	 			
	  	 		}else if(data.results.type==2){
	  	 			var liVoice=document.getElementById("li_voice");
	  	 			liVoice.setAttribute("className","active");
	  	 			$("#rule-voice").val(data.results.ruleName);
	  	 			$("#keyword-voice").val(data.results.keyword);
	  	 			$("#match-voice").val(data.results.isAllMatch);
	  	 			
	  	 		}else if(data.results.type==3){
	  	 			var liVideo=document.getElementById("li_video");
	  	 			liVideo.setAttribute("className","active");
	  	 			$("#rule-video").val(data.results.ruleName);
	  	 			$("#keyword-video").val(data.results.keyword);
	  	 			$("#match-video").val(data.results.isAllMatch);
	  	 			
	  	 		}else if(data.results.type==4){
	  	 			
	  	 		}
	  	 	}
	  	 });
	  }
	  
  }
  module.exports=KEYWORDADD
});