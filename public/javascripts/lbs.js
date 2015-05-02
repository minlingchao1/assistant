define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var LBS={
		init:function(){
			LBS.wechatId=until.getUrlParam("wechatId");
			LBS.bind();
			LBS.sceneList();
		},
		bind:function(){
		},
		sceneList:function(){
			$.ajax({
				type:"get",
				url:"/lbs/LBSInfoCtrl/getLBSInfoList",
				dataType:"json",
				data:{
					wechatId:LBS.wechatId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						LBS.data=data.results;
						LBS.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(LBS.data).each(function(){
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
	module.exports=LBS
})
