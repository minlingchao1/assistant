define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var FANSGROUP={
		init:function(){
			FANSGROUP.wechatId=until.getUrlParam("wechatId");
			FANSGROUP.bind();
			FANSGROUP.fansList();
		},
		bind:function(){
		},
		

	}
	module.exports=FANSGROUP
})
