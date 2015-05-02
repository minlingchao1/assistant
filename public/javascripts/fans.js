define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var FANS={
		init:function(){
			FANS.wechatId=until.getUrlParam("wechatId");
			FANS.bind();
			FANS.fansList();
		},
		bind:function(){
		},
		fansList:function(){
			$.ajax({
				type:"get",
				url:"/custom/FansCtrl/getFansList",
				dataType:"json",
				data:{
					wechatId:FANS.wechatId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						
						FANS.data=data.results;
						FANS.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(FANS.data).each(function(){
				res.push(this);
			});
			$('.fans-list').fadeIn().bootstrapTable('destroy').bootstrapTable({
				data:res,
				pagination:true,
				pageList:[10,25,50,100],
				pageNumber:1,
				pageSize:20,
				maintainSelected:true
			})
		}

	}
	module.exports=FANS
})
