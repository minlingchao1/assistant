define(function(require,exports,module){
	var $=require('jquery');
	var until=require('until');
	var dialog=require('dialog');
	var bootstrapTable=require('bootstrapTable');
	var validate=require("validate");
	var citySelect=require("city");
	var Dropzone=require('dropzone');
	var LBSADD={
		init:function(){
			LBSADD.wechatId=until.getUrlParam("wechatId");
			LBSADD.bind();
			LBSADD.getAMap();
			LBSADD.showMapByCityName();
			LBSADD.uploadFile();
		},
		bind:function(){
			$("#city").citySelect({
			 nodata:"none",
			 required:false
		  }); 
		  $(".showmap,.search-map a").click(LBSADD.getAddress);
		  $("#subLbs").click(LBSADD.subLbs);
		},
		subLbs:function(){
			for(var i=0;i<$(".lbsInfo .need").length;i++){
				if(!$(".lbsInfo .need").eq(i).val()){
					dialog({
						width:400,
						title: '创建失败!',
						content: '<p class="bg-warning text-warning" style="padding:15px;">请填写'+$(".lbsInfo .need").eq(i).parents(".form-group").find("label").html()+"</p>"
					}).showModal();
					return
					break;
				}
			}
			$.ajax({
				type:"post",
				url:"/lbs/LBSInfoCtrl/addLBSInfo",
                dataType:"json",
                data:{
                	wechatId:LBSADD.wechatId,
                	sellerName:$("#sellerName").val(),
                	descr:$("#descr").val(),
                	prov:$("#prov").val(),
                	city:$("#cityName").val(),
                	dist:$("#dist").val(),
                	category:$("#category").val(),
                	address:$("#address").val(),
                	mobile:$("#mobile").val(),
                	lngLat:$("#address").attr("point"),
                	img:LBSADD.storeImg
                },
                success:function(data){
                	if(data.success){
        				alert("LBS设置成功!");
        				location.href="/AssistantUI/lbs?wechatId="+SCENEADD.wechatId;
        			}else{
        				alert(data.message);
        			}
                }
			});
		},
	    getAMap:function(){
			window.mapObj = new AMap.Map("mapcont",{
				rotateEnable:true,
				dragEnable:true,
				zoomEnable:true,
				//二维地图显示视口
				view: new AMap.View2D({
					zoom:12 //地图显示的缩放级别
				})
			});
			var MGeocoder;
			LBSADD.marker=new AMap.Marker({
				draggable:true, //点标记可拖拽
				cursor:'move',  //鼠标悬停点标记时的鼠标样式
				raiseOnDrag:true//鼠标拖拽点标记时开启点标记离开地图的效
			});
			AMap.event.addListener(LBSADD.marker,"dragend",function(e){
				var lnglatXY = new AMap.LngLat(e.lnglat.getLng(),e.lnglat.getLat());
				$("#address").attr("point",e.lnglat.getLng()+","+e.lnglat.getLat());
				AMap.service(["AMap.Geocoder"], function() {
					MGeocoder = new AMap.Geocoder({
						radius: 1000,
						extensions: "all"
					});
					//逆地理编码
					MGeocoder.getAddress(lnglatXY, function(status, result){
						if(status === 'complete' && result.info === 'OK'){
							address = result.regeocode.formattedAddress;
							$("#address").val(address);
						}
					});
				});
				$("#address").attr("point",e.lnglat.getLng()+","+e.lnglat.getLat());
			})
			AMap.event.addListener(mapObj,'click',function(e){
				var lnglatXY = new AMap.LngLat(e.lnglat.getLng(),e.lnglat.getLat());
				LBSADD.marker.setMap(mapObj)
				LBSADD.marker.setPosition(e.lnglat);
				//加载地理编码插件
				AMap.service(["AMap.Geocoder"], function() {
					MGeocoder = new AMap.Geocoder({
						radius: 1000,
						extensions: "all"
					});
					//逆地理编码
					MGeocoder.getAddress(lnglatXY, function(status, result){
						if(status === 'complete' && result.info === 'OK'){
							address = result.regeocode.formattedAddress;
							$("#address").val(address);
						}
					});
				});
				$("#address").attr("point",e.lnglat.getLng()+","+e.lnglat.getLat());
			});
		},
		getAddress:function(e){
			
			var word=$(".search-map input").val();
			alert(word);
			var MSearch;
			var city="";
			var marker = new Array();
			var windowsArr = new Array();
			//添加marker&infowindow
			function addmarker(i, d) {
				var lngX = d.location.getLng();
				var latY = d.location.getLat();
				var markerOption = {
					map:mapObj,
					icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
					position:new AMap.LngLat(lngX, latY),
					topWhenClick:true

				};
				var mar = new AMap.Marker(markerOption);
				marker.push(new AMap.LngLat(lngX, latY));

				var infoWindow = new AMap.InfoWindow({
					content:"<h3><font color=\"#00a6ac\">  " + (i + 1) + ". " + d.name + "</font></h3>" + TipContents(d.type, d.address, d.tel),
					size:new AMap.Size(300, 0),
					autoMove:true,
					offset:new AMap.Pixel(0,-20)
				});
				windowsArr.push(infoWindow);
				var aa = function (e) {
					var lnglatXY = new AMap.LngLat(e.lnglat.getLng(),e.lnglat.getLat());
					LBSADD.marker.setMap(mapObj)
					LBSADD.marker.setPosition(e.lnglat);
					//加载地理编码插件
					AMap.service(["AMap.Geocoder"], function() {
						MGeocoder = new AMap.Geocoder({
							radius: 1000,
							extensions: "all"
						});
						//逆地理编码
						MGeocoder.getAddress(lnglatXY, function(status, result){
							if(status === 'complete' && result.info === 'OK'){
								address = result.regeocode.formattedAddress;
								$("#address").val(address);
							}
						});
					});
					$("#address").attr("point",e.lnglat.getLng()+","+e.lnglat.getLat());
					infoWindow.open(mapObj, mar.getPosition());
				};
				AMap.event.addListener(mar, "click", aa);
			}

			function TipContents(type, address, tel) {  //窗体内容
				if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
					type = "暂无";
				}
				if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
					address = "暂无";
				}
				if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
					tel = "暂无";
				}
				var str = "  地址：" + address + "<br />  电话：" + tel + " <br />  类型：" + type;
				return str;
			}
			//加载城市查询插件
			AMap.service(["AMap.CitySearch"], function() {
				//实例化城市查询类
				var citysearch = new AMap.CitySearch();
				//自动获取用户IP，返回当前城市
				citysearch.getLocalCity(function(status, result){
					if(status === 'complete' && result.info === 'OK'){
						if(result && result.city && result.bounds) {
							city= result.city;
						}
					}else{
						city=result.info;
					}
					AMap.service(["AMap.PlaceSearch"], function() {
						MSearch = new AMap.PlaceSearch({ //构造地点查询类
							pageSize:10,
							pageIndex:1,
							city:city
						});
						//关键字查询
						MSearch.search(word, function(status, data){
							var resultStr = "";
							var poiArr =data.poiList? data.poiList.pois:[];
							var resultCount = poiArr.length;
							for (var i = 0; i < resultCount; i++) {
								resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h3><font color=\"#00a6ac\">名称: " + poiArr[i].name + "</font></h3>";
									resultStr += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";
									addmarker(i, poiArr[i]);
							}
							mapObj.setFitView();
						});
					});
				});
			});
		},
		
		showMapByCityName:function(){
			
			$(".prov").change(function(){
				//$("#search").attr("disabled","disabled");
				var w=$(".prov").find('option:selected').text();
		        window.mapObj.setCity(w);
			});
			$(".city").change(function(){
				//$("#search").attr("disabled","disabled");
				var w=$(".city").find('option:selected').text();
				//alert(w);
		        window.mapObj.setCity(w);
			});
			$(".dist").change(function(){
				//$("#search").attr("disabled","disabled");
				var w=$(".dist").find('option:selected').text();
				//alert(w);
		        window.mapObj.setCity(w);
			});
	      },
	      uploadFile:function(){
	      	$(".dropz-outImg").dropzone({
	            autoProcessQueue: true,
	            uploadMultiple: true,
	            parallelUploads: 100,
	            maxFiles: 1,
	            maxFilesize: 2,
	            acceptedFiles:"image/bmp,.png,.jpeg,.jpg,.gif",
	            uploadMultiple:false,
	            addRemoveLinks:true,
	            init:function(){
	            	this.on("removedfile", function(file) {
	            	   LBSADD.storeImg="";
	                   alert("删除成功!");
	                });
	                this.on("success",function(file,response){
	                	LBSADD.storeImg=response.url;
	                	//alert(BINDWECHAT.headImage);
	                })
	            }
	        });
	      } 
		
	}
	module.exports=LBSADD
})
