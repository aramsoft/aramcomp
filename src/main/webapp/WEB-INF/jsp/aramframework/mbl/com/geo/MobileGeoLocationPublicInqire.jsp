<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>위치정보연계 - 국가공간정보통합체계 Map</title>

<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/geo/geo.js"></script>

<!-- 국가공간정보통합체계 API -->
<script type="text/javascript" src="http://www.nsdi.go.kr/use/openapi/key/certifyKey.do?oap_key=26db51c8bca09872cd1bc782a8dac390" charset="utf-8"></script>

</head>

<body>

<div id="main" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>위치정보연계</h1>
	</div>
	<!-- header end -->
	
	<!-- content start -->
	<div data-role="content">
		<h3 class="mcom-h3">위도, 경도 좌표</h3>
		<div id="latlngInfo" class="ui-body-c">
			
		</div>
		<div id="mapTitle">
			<div style="padding:10px 0">
               	<div class="egov-grid">
               		<div class="egov-wid2">
               			<div align="left">							
               				<b>MAP</b>
               			</div>
               		</div>
               		<div class="egov-wid10">
               			<div align="right">	
               				<form id="geoLocationVO" name="geoLocationVO" method="post">
               					<p>조회범위
               					<select id="searchCondition" name="searchCondition" onchange="javascript:init();" data-role="none">
               						<option value="100">100m 이내</option>
               						<option value="200">200m 이내</option>
               						<option value="300">300m 이내</option>
               						<option value="500">500m 이내</option>
               						<option value="1000">1km 이내</option>
               						<option value="2000">2km 이내</option>
              						</select></p>
              					</form>
              				</div>
              			</div>
              		</div>
              	</div>
		</div>
		<div id="mapCanvas" class="ui-body-c" style="font-size:0.75em">
			
		</div>
	</div>
	<!-- content end -->
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>

<script type="text/javascript" >
var buildingList = new Array();

$('#main').bind('pageshow', init);

/*********************************************************
 * 건물 위치정보 조회
 ******************************************************** */
function init() {
    if (!navigator.geolocation) {
       	jAlert("This browser doesn't support geolocation", "GeoLocation", "a");
    } else {
    	
    	var url = "${pageContext.request.contextPath}/mbl/com/geo/listMobileBuildingLocationInfoJson.mdo'/>";
        $.getJSON(url, $('#geoLocationVO').serialize(),	function(json) {
      	
        	$("#searchCondition option[value='" + json.geoLocationVO.searchCondition + "']").attr('selected', 'selected');
			$('#mapCanvas').html("");
		
      		buildingList = json.resultList;
            		
			// 현재 위치정보 취득
           	navigator.geolocation.getCurrentPosition(successCallback, errorCallback, { maximumAge: 0, timeout: 30000, enableHighAccuracy: true });
        });
        
    }
}

/*********************************************************
 * 위치정보 취득 성공시 처리
 ******************************************************** */
function successCallback(position) {

	// 현재 위치정보 표시
   	var html = "";
  		html += '<ul class="mcom-posi">';
  		html += '<li><strong>위도 :</strong> ' + position.coords.latitude + '</li>';
  		html += '<li><strong>경도 :</strong> ' + position.coords.longitude + '</li>';
    	html += '<li><strong>정확도 :</strong> ' + position.coords.accuracy + '</li>';
  		html += '</ul>';

  	$('#latlngInfo').html(html);
       
	var zoomLevel = 16;
	
    // 조회 범위에 따른 ZoomLevel 설정*
    if ($('#searchCondition').val() == "100") {
		zoomLevel = 17;
    } else if ($('#searchCondition').val() == "500") {
		zoomLevel = 15;
	} else if ($('#searchCondition').val() == "1000") {
		zoomLevel = 14;
	} else if ($('#searchCondition').val() == "2000") {
		zoomLevel = 13;
	}

	// 지도 생성*
	var map = null;
	NSDIMapLoader.init(document.getElementById("mapCanvas"), function(){
		map = this.nmap;
		map.setBaseLayer(map.nsdiBaseMap);	
		map.enableTouch();
		map.removeNSDIControl("indexMap");
		map.removeNSDIControl("mousePos");
		map.removeNSDIControl("layerSwitch");
	});

	// 현재 위치 좌표*
	var latlng = map.convertXY(position.coords.longitude, position.coords.latitude, "EPSG:4326", "EPSG:900913");
	
    // 현재 위치 마커 표시*
    var curMarker = new NSDIMarker(latlng.x, latlng.y, "현재위치", "");
    curMarker.setIconImage("/marker01.png");
	map.setCenterAndZoom(latlng.x, latlng.y, zoomLevel);
	map.addMarker(curMarker);

	var info = new OpenLayers.Popup("info",
			new OpenLayers.LonLat(latlng.x, latlng.y),
			new OpenLayers.Size(100, 30),
			"현재위치",
			true);
	map.addPopup(info);
	
    // 조회 범위 내의 건물 데이터 조회
    var resultList = fn_select_building(position.coords.latitude, position.coords.longitude, $('#searchCondition').val(), buildingList);

	// 조회 범위 내의 건물 마커 표시*
    for (var i = 0; i < resultList.length; i++) {
    	var buildingLatlng = map.convertXY(resultList[i].lo, resultList[i].la, "EPSG:4326", "EPSG:900913");	
       	var buildingMarker = new NSDIMarker(buildingLatlng.x, buildingLatlng.y, resultList[i].buldNm + '<br>' + resultList[i].telno, "");
       	buildingMarker.setIconImage("/office.png");
		map.addMarker(buildingMarker);

		var buildingInfo = new OpenLayers.Popup("info" + i,
				new OpenLayers.LonLat(buildingLatlng.x, buildingLatlng.y),
				new OpenLayers.Size(100, 30),
				resultList[i].buldNm + '<br>' + resultList[i].telno,
				true);
		map.addPopup(buildingInfo);
    }

    // 조회 범위 원 그리기*
    NSDICircle.show(map, new OpenLayers.LonLat(latlng.x, latlng.y), $('#searchCondition').val()*(1.23), "");
}

/*********************************************************
 * 위치정보 취득 실패시 처리
 ******************************************************** */
function errorCallback(error) {
	jAlert("에러발생, 에러코드: " + error.code + " 메시지: " + error.message, "Error", "a");
}
</script>

</body>
</html> 