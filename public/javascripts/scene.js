define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var SCENE={
		init:function(){
			SCENE.wechatId=until.getUrlParam("wechatId");
			SCENE.bind();
			SCENE.sceneList();
		},
		bind:function(){
		},
		sceneList:function(){
			$.ajax({
				type:"get",
				url:"/scene/SceneCtrl/getSceneList",
				dataType:"json",
				data:{
					wechatId:SCENE.wechatId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						SCENE.data=data.results;
						SCENE.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(SCENE.data).each(function(){
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
	module.exports=SCENE
})
