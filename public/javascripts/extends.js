define(function(require,exports,module){
     return function(jquery){
         (function($) {
             $.fn.mask= function(msg,maskDivClass,maskcolor) {
//               alert($("a").attr("href"))
//               // 代码区域。
                    var original=this;
			    	if(this[0]==document){
			    		var original=$('body');
			    	}
			    	var hasmasked = false;
			    	$('.maskdivgen').each(function(){
			    		if(!hasmasked&&$(this).data('YYUCMASKO')==original[0]){
			    			hasmasked = true;
			    			if(msg){
			        			$('.maskdivgen').find('#maskmsgdiv').html(msg);
			        		}else{
			        			$('.maskdivgen').find('#maskmsgdiv').remove();
			        		}
			    		}
			    	});
			    	if(hasmasked){
			    		return;
			    	}
			    	var maskDiv=$('<div class="maskdivgen">&nbsp;</div>');
			    	
			    	var maskWidth=original.outerWidth();
			        if(!maskWidth){
			            maskWidth=original.width();
			        }
			        var maskHeight=original.outerHeight();
			        if(!maskHeight){
			            maskHeight=original.height();
			        }
			        if((this[0]==$('body')[0])||(this[0]==$(document)[0])){
			        	maskWidth = $(window).width()+$(window).scrollLeft();
			        	maskHeight= $(window).height()+$(window).scrollTop();
			        }
			        maskcolor = maskcolor ? maskcolor : '#000';
			        maskDiv.css({
			            position: 'absolute',
			            top: original.offset().top,
			            left: original.offset().left,
			            'z-index': 811200,
			            width: maskWidth,
			            height:maskHeight,
			            'background-color':maskcolor,
			            opacity: 0
			        });
			        $('body').append(maskDiv);
			        if(maskDivClass){
			            maskDiv.addClass(maskDivClass);
			        }
			        maskDiv.data('YYUCMASKO',original[0]);
			        if(msg){
			        	var msgDiv=$('<div class="YYUCMASKMSG" style="border:#6593cf 1px solid; z-index: 811201;padding:2px;background:#fff;width:200px;position: absolute;"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px;text-align:center;" id="maskmsgdiv">'+msg+'</div></div>');
			        	maskDiv.after(msgDiv);
			            var heightspace=(maskDiv.height()-msgDiv.height())/2+maskDiv.offset().top;
			            var widthspace=(maskDiv.width()-msgDiv.width())/2+maskDiv.offset().left;
			            msgDiv.css({
			                cursor:'wait',
			                left:widthspace,
			                top:heightspace-20
			            });
			        }
			      	maskDiv.fadeIn(1, function(){$(this).fadeTo(1, 0.5);});
			      	original.data('YYUCisMasked',true);
			        return maskDiv;
             };
             $.fn.unmask= function(msg,maskDivClass,maskcolor){
             	    this.each(function(){
			 		var original=$(this);
			 		if(this[0]==document){
			 			var original=$('body');
			 		}
			 		$('.maskdivgen').each(function(){
			 			if($(this).data('YYUCMASKO')==original[0]){
			 				$(this).next('.YYUCMASKMSG').remove();
			 				$(this).remove();
			 			}
			 		});
			 		original.data('YYUCisMasked',false);
			 	});	
             };
             
         })(jquery);
     }
})