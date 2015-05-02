define(function(require,exports,module){
	
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	
	var LIST={
		init:function(){
			LIST.getData();
		},
		bind:function(){
			
		},
		getData:function(){
			$.ajax({
				type:"get",
				url:"/wx/WechatInfoCtrl/getInfo",
				dataType:"json",
				success:function(data){
					if(data.success){
						LIST.data=data.results;
						LIST.renderTable();
					}else{
						alert(data.message);
					}
				}
			});
		},
		renderTable:function(){
			var res=[];
			$(LIST.data).each(function(){
				res.push(this);
			});
			$('.wx-list').fadeIn().bootstrapTable('destroy').bootstrapTable({
				data:res,
				pagination:true,
				pageList:[10,25,50,100],
				pageNumber:1,
				pageSize:20,
				maintainSelected:true
			})
		},
		deleteWXInfo:function(){
		
	    }
	}
	
	module.exports=LIST
})
