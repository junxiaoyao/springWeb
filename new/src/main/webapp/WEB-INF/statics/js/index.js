$(function() {
/*	var allstatus;//全局变量(给采集器点击使用)
	$side = $("#side");
	//部门列表-改为企业列表
	var $orgnizations = $("#orgnizations");
	$.get(CTX + '/dab/orgnizations', function(data) {
		for (var i = 0; i < data.length; i++) {
			if(i==0){
				$orgnizations.append("<option selected='selected' value='" + data[i].id + "' >" + data[i].shortName + "</option>");
				$orgnizations.change();
			}else{
				$orgnizations.append("<option value='" + data[i].id + "' >" + data[i].shortName + "</option>");
			}
		}
	});

	//部门改变
	$(document).on('change', "#orgnizations", function() {
		var collectDevices = $("#collectDevices");
		var params = {
				orgnizationId: $('#orgnizations').val(),
				status: $("#status").val()
		};
		$.get(CTX + '/dab/collectDevices', params, function(data) {
			collectDevices.html("");
			if(data.length>0){
				collectDevices.append("<option value='' >采集器</option>");
				for (var i = 0; i < data.length; i++) {
					collectDevices.append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
				}
			}else{
				collectDevices.append("<option value=''>没有采集器</option>");
			}
		});
		loadOrganizaitonPage($('#orgnizations').val());
		loadMarker(params);
	});

	//状态改变
	$(document).on('change', "#status", function() {
		var collectDevices = $("#collectDevices");
		collectDevices.html("");
		allstatus = $("#status").val();
		var params = {
				orgnizationId: $('#orgnizations').val(),
				status: $("#status").val()
		};
		$.get(CTX + '/dab/collectDevices', params, function(data) {
			if(data.length>0){
				collectDevices.append("<option value='' >采集器</option>");
				for (var i = 0; i < data.length; i++) {
					collectDevices.append("<option value='" + data[i].id + "' >" + data[i].name + "</option>");
				}
			}else{
				collectDevices.append("<option value='' >没有采集器</option>");
			}
		});
		//更新图上标记
		loadMarker(params);
		loadOrganizaitonPage($('#orgnizations').val());
	});
	
	//采集设备改变
	$(document).on('change', "#collectDevices", function() {
		var params = {
				orgnizationId: $('#orgnizations').val(),
				status: $("#status").val(),
				collectDeviceId: $("#collectDevices").val()
		};
		if(!params.collectDeviceId==""){
			//更新图上标记
			loadMarker(params);
			loadCollectDevicePage($("#collectDevices ").val(), $("#collectDevices ").find("option:selected").text());
		}else{
			params.status = allstatus;
			loadMarker(params);
			loadOrganizaitonPage($('#orgnizations').val());
		}
	});


	//先选企业才能选择采集设备
	$("#collectDevices").click(function() {
		var organizaitonId = $("#orgnizations").val();
		if (organizaitonId == '') {
			toastr.error("请先选择部门");
			return false;
		}
	});

	//首次进来后无信息展示企业信息和设备列表
//	loadOrganizaitonPage(null);

	//选择企业信息后添加企业信息和设备列表
	function loadOrganizaitonPage(orgnizationId) {
		$side.load(CTX + "/dab/orgnization", {
			orgnizationId: orgnizationId
		});
	}

	var $distMap = $('#dist-map');
	var baiduMap = new baiDuMap("dist-map", CTX + "/ext_res/map/tiles/", 8);
	baiduMap.center = [106.5324, 29.4121];
	var map = baiduMap.createMap();
	$(".ol-attribution").css("display", "none");

	$(map.getViewport()).on("contextmenu", function(e) {
		e.preventDefault();
		var coordinate = map.getEventCoordinate(e);
	});

	//地图标注
	//loadMarker(null);
	var vectorLayer;

	function loadMarker(params) {
		$.get(CTX + '/dab/marker', params, function(parm) {
			//从地图上移除overlays
			var overlays = map.getOverlays();
			if (overlays.getLength() > 0) {
				var overlayLength = overlays.getLength();
				for (var i = 0; i < overlayLength; i++) {
					var pupupi = 'popup' + i;
					// 清除设备名称
					if (document.getElementById(pupupi) != null) {
						pupupi = '#'+pupupi;
						$(pupupi).remove();
					}
				}
			}
			//从地图上移除vectorLayer
			if (vectorLayer != null) {
				map.removeLayer(vectorLayer);
				vectorLayer = null;
			}
			map.clearOverlays;//清除所有覆盖物   好像并未生效

			var featurese = [];

			var zoomview; //定义中心点
			for (var i = 0; i < parm.length; i++) {
				var ent = parm[i],
					lat = ent.latitude,
					log = ent.longitude;


				if ($.type(lat) != 'number' || $.type(log) != 'number') {
					continue;
				}
				// 地图标记图标
				var point = new ol.geom.Point([log, lat]);
				var imgUrl = CTX + "/webjars/map/images/mark3.png";;
				var iconFeature = new Maker(point, imgUrl, ent.id, ent.name);
				featurese.push(iconFeature);
				//   			console.log(featurese)
				
				$distMap.after("<span class='bmap-popup-text' id='popup" + i + "'>" + ent.name + "</span>");
				var container = document.getElementById('popup' + i);
				var overlay = new ol.Overlay(({
					element: container,
					position: point.getCoordinates(),
					autoPan: true,
					offset: [10, -20]
				}));
				
				map.addOverlay(overlay);
			}
			
			var vectorSource = new ol.source.Vector({
				features: featurese
			});

			vectorLayer = new ol.layer.Vector({
				source: vectorSource
			});
			var clusterSource = new ol.source.Cluster({
				distance: 20,
				source: vectorSource
			});
			map.addLayer(vectorLayer);
			
			//做定位移动
			if(parm.length==1){
				zoomview = new ol.View({
								center:([log,lat]),
								zoom:13
							});
//                var duration = 2000;  
//                var start = +new Date();  
//  
//                var pan = ol.animation.pan({  
//                    duration: duration,  
//                    source: zoomview.getCenter(),  
//                    start:start  
//                });  
//  
//                var bounce = ol.animation.bounce({  
//                    duration: duration,  
//                    resolution: 2 * zoomview.getResolution(),  
//                    start:start  
//                }); 
//
//                //在地图渲染之前执行动画
//                map.beforeRender(pan, bounce);  
				
			}else{
				zoomview = new ol.View({
								center:([11859132.520985337, 3428202.4219069392]),
//								center:(ol.proj.transform([106.5324, 29.4121], 'EPSG:4326', 'EPSG:3857')),
								zoom:8
							});
			}
			var pan = ol.animation.pan({
		        duration: 10,
		        source: (zoomview.getCenter())
			});
			map.beforeRender(pan);
			map.setView(zoomview);
		})
	}
	//移动到图标鼠标样式
	map.on('pointermove', function(e) {
		if (e.dragging) return;
		var pixel = map.getEventPixel(e.originalEvent);
		var hit = map.hasFeatureAtPixel(pixel);
		$distMap.css('cursor', hit ? 'pointer' : '');
	});
	
	 * 获取图标名称电量值
	 
	var selectClick = new ol.interaction.Select({
		condition: ol.events.condition.click
	});
	map.addInteraction(selectClick);
	var deviceCode;
	selectClick.on('select', function(e) {
		if (e.selected.length > 0) {
			deviceCode = e.target.getFeatures().a[0].get('id');
			var name = e.target.getFeatures().a[0].get('name');
			loadCollectDevicePage(deviceCode, name);
		} else {
			loadOrganizaitonPage($('#orgnizations').val());
		}
	});



	//点击地图上采集设备后，显示当前设备的信息以及相应的监控设备
	function loadCollectDevicePage(deviceCode, name) {
		if(deviceCode!=""){
			$side.load(CTX + "/dab/collectDevice", {
				collectDeviceId: deviceCode
			});
		}
	}

	//监控设备改变
	$(document).on('change', "#monitorDevices", function() {
		var monitorDeviceDataItems = $("#monitorDeviceDataItems");
		var params = {
				monitorDeviceId: $('#monitorDevices').val()
		};
		$.get(CTX + '/dab/monitorDeviceDataItems', params, function(data) {
			monitorDeviceDataItems.empty();
			for (var i = 0; i < data.length; i++) {
				if(i==0){
					monitorDeviceDataItems.append("<option selected='selected' value='" + data[i].id + "' data-dataType='" + data[i].dataType + "'>" + data[i].name + "</option>");
					monitorDeviceDataItems.change();
				}else{
					monitorDeviceDataItems.append("<option value='" + data[i].id + "' data-dataType='" + data[i].dataType + "'>" + data[i].name + "</option>");
				}
			}
		});
	});

	
  //反弹值  
    function bounce(t) {  
        var s = 7.5625, p = 2.75, l;  
        if (t < (1 / p)) {  
            l = s * t * t;  
        } else {  
            if (t < (2 / p)) {  
                t -= (1.5 / p);  
                l = s * t * t + 0.75;  
            } else {  
                if (t < (2.5 / p)) {  
                    t -= (2.25 / p);  
                    l = s * t * t + 0.9375;  
                } else {  
                    t -= (2.625 / p);  
                    l = s * t * t + 0.984375;  
                }  
            }  
        }  
        return l;  
    }  
    */
});