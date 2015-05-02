define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var CARDADD={
		init:function(){
			CARDADD.wechatId=until.getUrlParam("wechatId");
			CARDADD.bind();
			CARDADD.fansList();
		},
		bind:function(){
		},
		qrcodeChange:function(){
			
		}
	}
	module.exports=CARDADD
})
