define(function(require,exports,module){
   var $=require('jquery');
   var until=require('until');
   var yyucadapter=require('yyucadapter.js');
   var emoji=require('emotion.js');
   var MENU={
   	  init:function(){
   	  	MENU.wechatId=until.getUrlParam("wechatId");
   	  	MENU.getData();
   	  	//MENU.setMenu();
   	  	MENU.emotion();
   	  	MENU.bind();  	
   	  },
   	  bind:function(){
   	  	$("#saveOrder").click(MENU.saveData);
   	  	$("#sync").click(MENU.publishMenu);
// 	  	$("#newsMsgId").bind("onpropertychange",function(e){
// 	  		if(e.propertyName!='value'){
// 	  			alert("你好!");
// 	  		}
// 	  	})
// 	  	$("#newsMsgId").change(function () {
//		  alert("你好");
//		});
// 	  	$("#newsMsgId").bind('input propertychange', function() {
//		    MENU.suitData();
//		});
   	  	//$("#newsMsgId").bind("propertychange input",MENU.suitData);
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
   	  setMenu:function(){
   	  	$('.maincd').click(function(){
			MENU.suitData();
			window.curcd = this;
			window.curctyp = '1';
			MENU.backData();		
		});
		
		$('.zizicd').click(function(){
			MENU.suitData();
			window.curcd = this;
			window.curctyp = '2';
			MENU.backData();		
		});
		$('#answertype').change(function(){
			$('.szcjbt').hide();
			$('#'+$(this).val()).show();
			
		});
		
		$('.szcjbt').find('input,textarea,.editArea').on('change keyup click mouseup',function(){
			MENU.suitData();
			
		});
		
	
		/**
		 * 检测iframe
		 */
		
		$(".editArea textarea").on("contentValueChange",MENU.suitData);
		$("#newsMsgId").on("contentValueChange",MENU.suitData);
		$('#cdmcinpo').on('keyup click change mouseup',function(){
			MENU.suitData();
		});
		
		
		$('.maincd').eq(0).trigger('click');
		
		$('.zizicd,.maincd').click(function(){
			//$('.zizicd,.maincd').unmask();
			//$(this).mask();
		});
		
		//把菜单信息转化为控制信息
		$('#res_ejcd').find('input[type="text"]').val('');
		$(".editArea div").html('');
		$('#xsejcdopt').remove();
		if(window.curctyp == '1'){
			$('#answertype').prepend('<option value="res_ejcd" id="xsejcdopt">显示二级菜单</option>');
		}
		var cdid = $(window.curcd).attr('id');
		var cddata = $.trim($(window.curcd).attr('reldata'));
		if(cddata!=''){
			cddata = $.evalJSON(cddata);
			$('#answertype').val(cddata.typ);
			$('#answertype').trigger('change');
			if(cddata.typ=='res_ejcd'){
				//回填菜单
				var zcddiv = $('div[zcdrel="'+cdid+'"]');
				if($.trim(cddata.data) !=''){
					var ds = cddata.data.split('@');
					zcddiv.find('.zizicd').hide();
					for(var i=0;i<ds.length;i++){
						$('#res_ejcd').find('input[type="text"]').eq(parseInt(ds[i])-1).val(zcddiv.find('.zizicd').eq(parseInt(ds[i])-1).show().text());
					}
				}			
			}else if(cddata.typ=='res_wb'){//Emotion.replaceInput
				$(".editArea div").html(Emotion.replaceEmoji(cddata.data));
			}else if($('#'+cddata.typ).find('input[type="radio"]').size()>0){
				 $('#'+cddata.typ).find('input[value="'+cddata.data+'"]').prop('checked',true);
			}else{
				$('#'+cddata.typ).find('input[type="text"]').val(cddata.data);
			}
			
		}else{
			cddata = {};
			if(window.curctyp == '1'){
				$('#answertype').val('res_ejcd');
			}else{
				$('#answertype').val('res_wb');
			}
			$('#answertype').trigger('change');
		}
		
		$('#czselarea').show();
		$('#cdmcinpo').val($(window.curcd).text());
		
		
	   	 //选择菜单数量
		$("#menunum").change(function(){
			$(".maskdivgen").css("background-color","");
			$len =  $(".caiduannum").find("tr  td  div").css("display" , "block");
			$val = $(this).val();
			//maincd
			if($val==1)
				{
				 $(".caiduannum").find("tr td").eq(1).find("div").css("display" , "none");
				 $(".caiduannum").find("tr td").eq(2).find("div").css("display" , "none");
				 $(".zicaidan2").find(".zizicd").css("display" , "none");
				 $(".zicaidan3").find(".zizicd").css("display" , "none");
				 $(".bgdpng").css("background-image","url('/public/images/3CustomLt1.png')");
				 $(".maincd").css("width","271px");
				 $(".zizicd").css("width","253px");
				}
			
				if($val==2)
				{
				 $(".caiduannum").find("tr td").eq(2).find("div").css("display" , "none");
				 $(".zicaidan3").find(".zizicd").css("display" , "none");
				 $(".bgdpng").css("background-image","url('/public/images/3CustomLt2.png')");
				 $(".maincd").css("width","135px");
				 $(".zizicd").css("width","127px"); 
				 $(".zicaidan2").css("left","176px");
				}
				
				
				if($val==3)
				{
				 $(".bgdpng").css("background-image","url('/public/images/3CustomLt.png')");
				 $(".maincd").css("width","85px");
				 $(".zizicd").css("width","83px"); 
				 $(".zicaidan2").css("left","131px");
				}
			});
   	  	
   	 },
   	 suitData:function(){
   	 	 
   	 	 if(window.curcd){
			var cdid = $(window.curcd).attr('id');
			var cddata = $.trim($(window.curcd).attr('reldata'));
			if(cddata!=''){
				cddata = $.evalJSON(cddata);
			}else{
				cddata = {};
			}
			cddata.typ = $('#answertype').val();
			
			var zcddiv = $('div[zcdrel="'+cdid+'"]');
			if(cddata.typ=='res_ejcd'){
				zcddiv.show();
				//填充菜单
				var sjarr = [];
				$('#res_ejcd').find('input[type="text"]').each(function(i){
					var temp_zcd = zcddiv.find('.zizicd').eq(i);
					if($.trim($(this).val())!=''){					
						temp_zcd.text($.trim($(this).val())).show();
						sjarr[sjarr.length] = i+1;
					}else{
						temp_zcd.hide();
					}
				});
				cddata.data = sjarr.join('@');
			}else{
				zcddiv.hide();
				if(cddata.typ=='res_wb'){
					cddata.data = Emotion.replaceInput($.trim($(".editArea div").html()));
				}else if(cddata.typ=='res_news'){
					$('#multiArticle11if').attr('src','/reply/NewsTemplCtrl/sourceShow?id='+cddata.data);
					//alert(cddata.data);
					//document.getElementById('newsMsgId').value = window.frames["newspaper"].document.getElementById("newsId").value;
					//$("#newsMsgId").attr("value",);
					//alert("你好");
					cddata.data=$("#newsMsgId").val();
				}else{
					cddata.data = $('#'+cddata.typ).find('input[type="text"]').val();
				}
				
			}
			$(window.curcd).text($('#cdmcinpo').val());
			$(window.curcd).attr('reldata',$.toJSON(cddata));
		}
   	},
   	initTheData:function(){
   		var idata = $.trim($('#initdatat').val());
   		//alert(idata);
		if(idata !=''){
			var csdata = $.evalJSON(idata);
			
			//初始化菜单信息
			$(".caiduannum").find("tr td").eq(csdata.length).find("div").css("display" ,"none");
			$(".caiduannum").find("tr td").eq(csdata.length+1).find("div").css("display" ,"none");
			//初始化菜单信息    menunum   $(".selector").find("option[text='pxx']").attr("selected",true);
			if(csdata.length==1)
				{
				 $(".bgdpng").css("background-image","url('/public/images/3CustomLt1.png')");
				 $(".maincd").css("width","271px");
				 $(".zizicd").css("width","253px");
				}
			
			
			if(csdata.length==2)
			{
			 $(".bgdpng").css("background-image","url('/public/images/3CustomLt2.png')");
			 $(".maincd").css("width","135px");
			 $(".zizicd").css("width","127px"); 
			 $(".zicaidan2").css("left","176px");
			}
			
			
			for(var i=0;i<csdata.length;i++){
				var mcd = $('.maincd').eq(i);
				var zcddiv = $('div[zcdrel="'+mcd.attr('id')+'"]');
				var cdjda = csdata[i];
				if(cdjda.typ=='res_ejcd'){
					for(var ejcd in cdjda.subdata){
						zcddiv.find('[rel="'+ejcd+'"]').attr('reldata',$.toJSON(cdjda.subdata[ejcd])).text(cdjda.subdata[ejcd].tit).show();
					}
				}
				cdjda.subdata = null;
				mcd.attr('reldata',$.toJSON(cdjda));
				mcd.text(cdjda.tit);			
			}
		}
		
   	},
   	backData:function(){
   		$('#res_ejcd').find('input[type="text"]').val('');
		$(".editArea div").html('');
		$('#xsejcdopt').remove();
		if(window.curctyp == '1'){
			$('#answertype').prepend('<option value="res_ejcd" id="xsejcdopt">显示二级菜单</option>');
		}
		var cdid = $(window.curcd).attr('id');
		var cddata = $.trim($(window.curcd).attr('reldata'));
		if(cddata!=''){
			cddata = $.evalJSON(cddata);
			$('#answertype').val(cddata.typ);
			$('#answertype').trigger('change');
			if(cddata.typ=='res_ejcd'){
				//回填菜单
				var zcddiv = $('div[zcdrel="'+cdid+'"]');
				if($.trim(cddata.data) !=''){
					var ds = cddata.data.split('@');
					zcddiv.find('.zizicd').hide();
					for(var i=0;i<ds.length;i++){
						$('#res_ejcd').find('input[type="text"]').eq(parseInt(ds[i])-1).val(zcddiv.find('.zizicd').eq(parseInt(ds[i])-1).show().text());
					}
				}			
			}else if(cddata.typ=='res_wb'){//Emotion.replaceInput
				$(".editArea div").html(Emotion.replaceEmoji(cddata.data));
			}else if(cddata.typ=='res_news'){
				 $('#multiArticle11if').attr('src','/reply/NewsTemplCtrl/sourceShow?id='+cddata.data);
				 $("#newsMsgId").val(cddata.data);
//				 $('#'+cddata.typ).find('input[value="'+cddata.data+'"]').prop('checked',true);
			}else{
				$('#'+cddata.typ).find('input[type="text"]').val(cddata.data);
			}
			
		}else{
			cddata = {};
			if(window.curctyp == '1'){
				$('#answertype').val('res_ejcd');
			}else{
				$('#answertype').val('res_wb');
			}
			$('#answertype').trigger('change');
		}
		
		$('#czselarea').show();
		$('#cdmcinpo').val($(window.curcd).text());
   },
   getData:function(){
   	   $.ajax({
   	   	type:"get",
   	   	url:"/menu/MenuCtrl/getMenu",
   	   	dataType:"json",
   	   	data:{
   	   		wechatId:MENU.wechatId
   	   	},
   	   	success:function(data){
   	   		if(data.success){
   	   			//alert(data.results);
   	   			if(data.results!=null){
   	   				$("#initdatat").val(data.results.menuJson);
   	   				MENU.initTheData();
   	   			}
   	   			MENU.setMenu();
   	   		}else{
   	   			alert(data.message);
   	   		}
   	   	}
   	   });
   },
   publishMenu:function(){
       
       $nonel  =  $(".caiduannum").find("tr  td  div:hidden").parent().remove();
		var alldata = [];
		var xxwz = true;
		$('.maincd').each(function(i){
			if($.trim($(this).attr('reldata'))==''){
				xxwz = false;
				return;
			}
			var cddata = $.evalJSON($.trim($(this).attr('reldata')));
			if(!xxwz || $.trim(cddata.data)==''){
				xxwz = false;
				return;
			}
			var cdid = $(this).attr('id');
			cddata.tit = $.trim($(this).text());
			if(cddata.typ=='res_ejcd'){
				//回填菜单
				var zcddiv = $('div[zcdrel="'+cdid+'"]');
				if($.trim(cddata.data) !=''){
					var ds = cddata.data.split('@');
					cddata.subdata = {};
					for(var i=0;i<ds.length;i++){
						var ind = parseInt(ds[i])-1;
						var zcd = zcddiv.find('.zizicd').eq(ind);
						var ttstr = $.trim(zcd.attr('reldata'));
						var ssdata = {};
						if(ttstr != ''){
							ssdata = $.evalJSON(zcd.attr('reldata'));
						}
						if(!xxwz || $.trim(ssdata.data)==''){
							xxwz = false;
							return;
						}
						ssdata.tit = $.trim(zcd.text());
						cddata.subdata['zizicd'+ds[i]] = ssdata;
					}
				}			
			}
			alldata[alldata.length] = cddata;
		});
		if(!xxwz){
			alert("菜单信息不完整请看教程");
			//tusi('菜单信息不完整请看教程');
			return;
		}
	   	$.ajax({
	   	   	type:"post",
	   	   	url:"/menu/MenuCtrl/createMenu",
	   	   	dataType:"json",
	   	   	data:{
	   	   		wechatId:MENU.wechatId,
	   	   		menuJson:$.toJSON(alldata)
	   	   	},
	   	   	success:function(data){
	   	   		if(data.success){
	   	   			alert("创建菜单成功!");
	   	   		}else{
	   	   			alert(data.message);
	   	   		}
	   	   	}
	   	   });
   },
   saveData:function(fun){
   	   
   	   $nonel  =  $(".caiduannum").find("tr  td  div:hidden").parent().remove();
		var alldata = [];
		var xxwz = true;
		$('.maincd').each(function(i){
			if($.trim($(this).attr('reldata'))==''){
				xxwz = false;
				return;
			}
			var cddata = $.evalJSON($.trim($(this).attr('reldata')));
			if(!xxwz || $.trim(cddata.data)==''){
				xxwz = false;
				return;
			}
			var cdid = $(this).attr('id');
			cddata.tit = $.trim($(this).text());
			if(cddata.typ=='res_ejcd'){
				//回填菜单
				var zcddiv = $('div[zcdrel="'+cdid+'"]');
				if($.trim(cddata.data) !=''){
					var ds = cddata.data.split('@');
					cddata.subdata = {};
					for(var i=0;i<ds.length;i++){
						var ind = parseInt(ds[i])-1;
						var zcd = zcddiv.find('.zizicd').eq(ind);
						var ttstr = $.trim(zcd.attr('reldata'));
						var ssdata = {};
						if(ttstr != ''){
							ssdata = $.evalJSON(zcd.attr('reldata'));
						}
						if(!xxwz || $.trim(ssdata.data)==''){
							xxwz = false;
							return;
						}
						ssdata.tit = $.trim(zcd.text());
						cddata.subdata['zizicd'+ds[i]] = ssdata;
					}
				}			
			}
			alldata[alldata.length] = cddata;
			alert(cddata.data);
		});
		if(!xxwz){
			alert("菜单信息不完整请看教程");
			//tusi('菜单信息不完整请看教程');
			return;
		}
		
		//alert(alldata);
		$.ajax({
			type:"post",
			url:"/menu/MenuCtrl/add",
			dataType:"json",
			data:{
				wechatId:MENU.wechatId,
				
				menuJson:$.toJSON(alldata)
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
   }
   module.exports=MENU
});
