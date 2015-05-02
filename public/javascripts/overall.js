define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var OVERALL={
		init:function(){
			OVERALL.wechatId=until.getUrlParam("wechatId");
			OVERALL.bind();
			OVERALL.overallList();
		},
		bind:function(){
		},
		overallList:function(){
			//
			//alert(OVERALL.wechatId);
			$.ajax({
				type:"get",
				url:"/overall/OverallCtrl/getList",
				dataType:"json",
				data:{
					wechatId:OVERALL.wechatId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						OVERALL.data=data.results;
						OVERALL.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(OVERALL.data).each(function(){
				res.push(this);
			});
			$('.scene-list').fadeIn().bootstrapTable('destroy').bootstrapTable({
				data:res,
				pagination:true,
				pageList:[10,25,50,100],
				pageNumber:1,
				pageSize:20,
				maintainSelected:true
			})
		}

	}
	module.exports=OVERALL
})
