define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var QRCODE={
		init:function(){
			QRCODE.wechatId=until.getUrlParam("wechatId");
			QRCODE.bind();
			QRCODE.qrcodeList();
		},
		bind:function(){
		},
		qrcodeList:function(){
			$.ajax({
				type:"get",
				url:"/qrcode/QrcodeCtrl/getQrcodeList",
				dataType:"json",
				data:{
					wechatId:QRCODE.wechatId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						QRCODE.data=data.results;
						QRCODE.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(QRCODE.data).each(function(){
				res.push(this);
			});
			$('.qrcode-list').fadeIn().bootstrapTable('destroy').bootstrapTable({
				data:res,
				pagination:true,
				pageList:[10,25,50,100],
				pageNumber:1,
				pageSize:20,
				maintainSelected:true
			})
		}

	}
	module.exports=QRCODE
})
