define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var KEYWORDREPLY={
		init:function(){
			KEYWORDREPLY.wechatId=until.getUrlParam("wechatId");
			KEYWORDREPLY.bind();
			KEYWORDREPLY.keywordList();
		},
		bind:function(){
		},
		keywordList:function(){
			$.ajax({
				type:"get",
				url:"/reply/KeywordReplyRuleCtrl/getKeywordList",
				dataType:"json",
				data:{
					wechatId:KEYWORDREPLY.wechatId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						KEYWORDREPLY.data=data.results;
						KEYWORDREPLY.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(KEYWORDREPLY.data).each(function(){
				res.push(this);
			});
			$('.keyword-list').fadeIn().bootstrapTable('destroy').bootstrapTable({
				data:res,
				pagination:true,
				pageList:[10,25,50,100],
				pageNumber:1,
				pageSize:20,
				maintainSelected:true
			})
		}

	}
	module.exports=KEYWORDREPLY
})
