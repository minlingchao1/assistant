define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var echart=require('echarts');
	
	var WXINDEX={
		init:function(){
			WXINDEX.wechatId=until.getUrlParam("wechatId");
			WXINDEX.userAddTrend();
			WXINDEX.totalFans();
			WXINDEX.scanCount();
            
		},
		bind:function(){
			$("#firstFollow").bind("click",WXINDEX.firstFollow);
			$("#keywordReply").bind("click",WXINDEX.keywordReply);
		},
        totalFans:function(){
        	 $.ajax({
        	 	type:"get",
        	 	url:"/custom/FansCtrl/countByWechatId",
        	 	data:{
        	 		wechatId:WXINDEX.wechatId
        	 	},
        	 	dataType:"json",
        	 	success:function(data){
        	 		//var result=100;
        	 		//$("#totalFans").innerHTML(result);
        	 		if(data.success){
        	 			 document.getElementById("totalFans").innerHTML = "<h1 >"+data.results+"</h1>";
        	 	         //  $(".totalFans").innerHTML(result);
        	 	         document.getElementById("fansLoading").style.display = "none";
        	 		}else{
        	 			alert(data.message);
        	 		}
        	 	 
        	 	}
        	 });
        },
       
		firstFollow:function(){
			//alert();
			/**
			 * 必须通过一个节点访问getAttribute、setAttribute方法
			 */
			var fisrtFollowUrl=document.getElementById("firstFollow").getAttribute("href")+"?wechatId="+WXINDEX.wechatId;
			//alert(fisrtFollowUrl);
			document.getElementById("firstFollow").setAttribute("href",fisrtFollowUrl);
		},
		keywordReply:function(){
			  var fisrtFollowUrl=document.getElementById("keywordReply").getAttribute("href")+"?wechatId="+WXINDEX.wechatId;
			 //alert(fisrtFollowUrl);
			 document.getElementById("keywordReply").setAttribute("href",fisrtFollowUrl);
		},
		userAddTrend:function(){
			   
	            var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
	            //图表显示提示信息
				lineChart.showLoading({
				 text: "图表数据正在努力加载..."
				});
				var option = {
				    
				    tooltip : {
				        trigger: 'item',
				        formatter : function (params) {
				            var date = new Date(params.value[0]);
				            data = date.getFullYear() + '-'
				                   + (date.getMonth() + 1) + '-'
				                   + date.getDate()
				            return data + '<br/>'
				                   + params.value[1]+"人"
				        }
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    //calculable : true,
				     dataZoom: {
				        show: true,
				        start : 0
				    },
				    legend : {
				        data : ['关注人数']
				    },
				    grid: {
				        y2: 80
				    },
				    xAxis : [
				        {
				            type : 'time',
				            splitNumber:10,
				            min:new Date(2015,0,1)
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name: '关注人数',
				            type: 'line',
				            showAllSymbol: true,
				            symbolSize: function (value){
				                return Math.round(value[1]/100) + 2;
				            },
				            data:[],
				        }
				    ]
				};

			    $.ajax({
			    	type:"post",
			    	url:"/custom/FansCtrl/countByDays",
			    	async:false,
			    	data:{
			    		wechatId:WXINDEX.wechatId
			    	},
			    	dataType:"json",
			    	success:function(data){
			    		//alert(data)
			    		if(data.success){
			    			
			    			var seriesData=data.results.seriesList[0].data;
			    			if(seriesData.length!=0){
			    				var categorys=data.results.categoryList;
				    			var d = [];
				    			for(var i=0;i<seriesData.length;i++){
				    				var cateArr=categorys[i].split("-");
				    				d.push([new Date(cateArr[0],cateArr[1]-1,cateArr[2]),
				    				seriesData[i]])
				    			}
			    			    option.series[0]['data']=d;
			    			}
			    			lineChart.setOption(option);
		    		 }else{
		    		 	alert(data.message);
		    		 }
			    	 lineChart.hideLoading();
			    	},
			    	error:function(errMsg){
			    		alert("图表加载出错！")
			    	}
			    });
//			    var scan = echarts.init(document.getElementById("qrcode-scan"));
//			    var scanoption = {
//			       
//			        tooltip : {
//			            trigger: 'item',
//			             formatter : function (params) {
//			             var date = new Date(params.value[0]);
//			             data = date.getFullYear() + '-'
//			                   + (date.getMonth() + 1) + '-'
//			                   + date.getDate() + ' '
//			                   + date.getHours() + ':'
//			                   + date.getMinutes();
//			             return data + '<br/>'
//			                   + params.value[1] + ', ' 
//			                   + params.value[2];
//			        }
//			        },
//			        legend: {
//			            data:['文本回复','菜单点击','位置请求','总请求数']
//			        },
//			        toolbox: {
//		                show : true,
//		                feature : {
//		                    mark : {show: true},
//		                    dataView : {show: true, readOnly: false},
//		                    magicType : {show: true, type: ['line', 'bar']},
//		                    restore : {show: true},
//		                    saveAsImage : {show: true}
//		                }
//		            },
//			        //calculable : true,
//			        xAxis : [
//			            {
//			                type : 'category',
//			                boundaryGap : false,
//			                data : ['周一','周二','周三','周四','周五','周六','周日']
//			            }
//			        ],
//			        yAxis : [
//			            {
//			                type : 'value',
//			                axisLabel : {
//			                    formatter: '{value} °C'
//			                }
//			            }
//			        ],
//			        series : [
//			            {
//			                name:'文本回复',
//			                type:'line',
//			                data:[],
//			                markPoint : {
//			                    data : [
//			                        {type : 'max', name: '最大值'},
//			                        {type : 'min', name: '最小值'}
//			                    ]
//			                },
//			                markLine : {
//			                    data : [
//			                        {type : 'average', name: '平均值'}
//			                    ]
//			                }
//			            },
//			            {
//			                name:'菜单点击',
//			                type:'line',
//			                data:[1, -2, 2, 5, 3, 2, 0],
//			                markPoint : {
//			                    data : [
//			                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
//			                    ]
//			                },
//			                markLine : {
//			                    data : [
//			                        {type : 'average', name : '平均值'}
//			                    ]
//			                }
//			            }
//			        ]
//			    };
//			    scan.setOption(scanoption); 
     },
     scanCount:function(){
     	var lineChart = echarts.init(document.getElementById("click-scan"));
	            //图表显示提示信息
		lineChart.showLoading({
				 text: "图表数据正在努力加载..."
				});
				var option = {
				    
				    tooltip : {
				        trigger: 'item',
				        formatter : function (params) {
				            var date = new Date(params.value[0]);
				            data = date.getFullYear() + '-'
				                   + (date.getMonth() + 1) + '-'
				                   + date.getDate()
				            return data + '<br/>'
				                   + params.value[1]+"次"
				        }
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    //calculable : true,
				     dataZoom: {
				        show: true,
				        start : 0
				    },
				    legend : {
				        data:['文本回复','菜单点击','位置请求','总请求数']
				    },
				    grid: {
				        y2: 80
				    },
				    xAxis : [
				        {
				            type : 'time',
				            splitNumber:10,
				            min:new Date(2015,0,1)
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name: '文本回复',
				            type: 'line',
				            showAllSymbol: true,
				            symbolSize: function (value){
				                return Math.round(value[1]/100) + 2;
				            },
				            data:[],
				        },
				        {
				            name: '菜单点击',
				            type: 'line',
				            showAllSymbol: true,
				            symbolSize: function (value){
				                return Math.round(value[1]/100) + 2;
				            },
				            data:[],
				        },
				         {
				            name: '位置请求',
				            type: 'line',
				            showAllSymbol: true,
				            symbolSize: function (value){
				                return Math.round(value[1]/100) + 2;
				            },
				            data:[],
				        },
				        {
				            name: '总请求数',
				            type: 'line',
				            showAllSymbol: true,
				            symbolSize: function (value){
				                return Math.round(value[1]/100) + 2;
				            },
				            data:[],
				        }
				    ]
				};

			    $.ajax({
			    	type:"post",
			    	url:"/reply/ScanCountCtrl/count",
			    	async:false,
			    	data:{
			    		wechatId:WXINDEX.wechatId
			    	},
			    	dataType:"json",
			    	success:function(data){
			    		//alert(data)
			    		if(data){
			    			
			    			var textScan=data.results.dataText;
			    			var menuScan=data.results.dataMenu;
			    			var locationScan=data.results.dataLocation;
			    			var allScan=data.results.dataAll;
			    			//var seriesData=data.results.seriesList[0].data;
			    			//文本
			    			if(textScan.length!=0){
			    				var categorys=data.results.categoryList;
				    			var d = [];
				    			for(var i=0;i<textScan.length;i++){
				    				var cateArr=categorys[i].split("-");
				    				d.push([new Date(cateArr[0],cateArr[1]-1,cateArr[2]),
				    				textScan[i]])
				    			}
			    			    option.series[0]['data']=d;
			    			}
			    			//菜单
			    			if(menuScan.length!=0){
			    				var categorys=data.results.categoryList;
				    			var d = [];
				    			for(var i=0;i<menuScan.length;i++){
				    				var cateArr=categorys[i].split("-");
				    				d.push([new Date(cateArr[0],cateArr[1]-1,cateArr[2]),
				    				menuScan[i]])
				    			}
			    			    option.series[1]['data']=d;
			    			}
			    			//地理位置
			    			if(locationScan.length!=0){
			    				var categorys=data.results.categoryList;
				    			var d = [];
				    			for(var i=0;i<locationScan.length;i++){
				    				var cateArr=categorys[i].split("-");
				    				d.push([new Date(cateArr[0],cateArr[1]-1,cateArr[2]),
				    				locationScan[i]])
				    			}
			    			    option.series[2]['data']=d;
			    			}
			    			//总请求数
			    			if(allScan.length!=0){
			    				var categorys=data.results.categoryList;
				    			var d = [];
				    			for(var i=0;i<allScan.length;i++){
				    				var cateArr=categorys[i].split("-");
				    				d.push([new Date(cateArr[0],cateArr[1]-1,cateArr[2]),
				    				allScan[i]])
				    			}
			    			    option.series[3]['data']=d;
			    			}
			    			lineChart.setOption(option);
		    		 }
			    		lineChart.hideLoading();
			    	},
			    	error:function(errMsg){
			    		alert("图表加载出错！")
			    	}
			    });
     }

	}
	module.exports=WXINDEX
	
	/*
	 * var scan = echarts.init(document.getElementById("qrcode-scan"));
			    var scanoption = {
			       
			        tooltip : {
			            trigger: 'axis'
			        },
			        legend: {
			            data:['文本回复','菜单点击','位置请求','总请求数']
			        },
			        toolbox: {
		                show : true,
		                feature : {
		                    mark : {show: true},
		                    dataView : {show: true, readOnly: false},
		                    magicType : {show: true, type: ['line', 'bar']},
		                    restore : {show: true},
		                    saveAsImage : {show: true}
		                }
		            },
			        calculable : true,
			        xAxis : [
			            {
			                type : 'category',
			                boundaryGap : false,
			                data : ['周一','周二','周三','周四','周五','周六','周日']
			            }
			        ],
			        yAxis : [
			            {
			                type : 'value',
			                axisLabel : {
			                    formatter: '{value} °C'
			                }
			            }
			        ],
			        series : [
			            {
			                name:'文本回复',
			                type:'line',
			                data:[11, 11, 15, 13, 12, 13, 10],
			                markPoint : {
			                    data : [
			                        {type : 'max', name: '最大值'},
			                        {type : 'min', name: '最小值'}
			                    ]
			                },
			                markLine : {
			                    data : [
			                        {type : 'average', name: '平均值'}
			                    ]
			                }
			            },
			            {
			                name:'菜单点击',
			                type:'line',
			                data:[1, -2, 2, 5, 3, 2, 0],
			                markPoint : {
			                    data : [
			                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
			                    ]
			                },
			                markLine : {
			                    data : [
			                        {type : 'average', name : '平均值'}
			                    ]
			                }
			            }
			        ]
			    };
			    scan.setOption(scanoption); 
	 */
});
