<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html> 
<head>
<title>모바일 화면</title> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
       
<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css"/>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/mcg/mcg.js"></script>

</head>

<body onload="fn_egov_initl_mobilechartgraphlist();">

<!-- 차트/그래프 초기 페이지 -->
<div id="ChartGraphIntro" data-role="page" >
	
	<div data-role="header" data-position="inline" data-theme="a">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>모바일 차트/그래프</h1>
	</div>
	
	<div data-role="content">
		<div data-role="none">
			<p class="chartTitle">차트를 선택 하세요</p> 
			<select name="select-chart" id="select-chart" multiple="multiple" data-native-menu="false">
			</select>
			<a href="javascript:fn_egov_show_chartGraph();" data-role="button">차트/그래프 보기</a>
		</div>
	</div>
	
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>

</div>

<!--차트/그래프 페이지 -->
<div id="ChartGraph" data-role="page" data-theme="d">
	
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>모바일 차트/그래프</h1>
		<a href="${pageContext.request.contextPath}/mbl/com/mcg/goMobileChartGraph.mdo" data-ajax="false" data-icon="back" class="ui-btn-right">이전</a>
		<div data-role="tabs">
			<ul id="ChartGraphNavi">
			</ul>
		</div>
	</div>

	<div data-role="content">
		<div id="lineChartGraph">
			<canvas id="lineChartGraphCanvas" width="500" height="400">
			</canvas>
		</div>
		<div id="columnChartGraph">
			<canvas id="columnChartGraphCanvas" width="500" height="400">
			</canvas>
		</div>
		<div id="pieChartGraph">
			<canvas id="pieChartGraphCanvas" width="500" height="400">
			</canvas>
		</div>
		<div id="scatterChartGraph">
			<canvas id="scatterChartGraphCanvas" width="500" height="400">
			</canvas>
		</div>
		<div id="barChartGraph">
			<canvas id="barChartGraphCanvas" width="500" height="400">
			</canvas>
		</div>
	</div>

	<div data-role="footer" data-theme="a" data-position="fixed" >
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	
</div>
	
</body>
</html>