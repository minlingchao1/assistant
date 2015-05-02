define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var SCENEIMG={
		init:function(){
			SCENEIMG.sceneId=until.getUrlParam("sceneId");
			SCENEIMG.bind();
			SCENEIMG.sceneList();
		},
		bind:function(){
		},
		sceneList:function(){
			$.ajax({
				type:"get",
				url:"/scene/SceneImgCtrl/getSceneImgList",
				dataType:"json",
				data:{
					sceneId:SCENEIMG.sceneId
				},
				success:function(data){
					//alert(data.results);
					if(data.success){
						SCENEIMG.data=data.results;
						SCENEIMG.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(SCENEIMG.data).each(function(){
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
	module.exports=SCENEIMG
})
