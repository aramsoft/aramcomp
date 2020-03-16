<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>행정안전부 공통서비스 테스트 사이트(업무사용자)</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/mpm.css" type="text/css">

<script type="text/javascript">

function changeFrameSize(){
	var childWindow = document.getElementById('contentFrame').contentWindow; 
	var the_height = childWindow.document.body.scrollHeight;
	if (childWindow.document.getElementById('SchdulView') != null
		&& the_height < 700 ) {
		the_height =  the_height + 700;
	} else {
		the_height = the_height + 60;
	}
//	if( the_height < 1000 ) the_height = 1000;
	
	document.getElementById('contentFrame').height = the_height;
}

function resize() {
	var height = 0;
	if( typeof( window.innerHeight ) == 'number' ) { //Non-IE
		height = window.innerHeight - 176;
	} else if( document.documentElement && document.documentElement.clientHeight  ) {
		height = document.documentElement.clientHeight - 176;
	}
	if( height < document.getElementById("contentFrame").height ) {
		height = document.getElementById("contentFrame").height;
	}
	document.getElementById("leftmenu").style.height = height + "px";
}

</script>
</head>

<body onload="javascript:resize();"  onresize="javascript:resize();">
<div id="wrapper">
	<c:import url="/sym/mnu/mpm/MainMenuHead.do" />
	<div id="main_container">
	    <div id="leftmenu">
			<c:import url="/sym/mnu/mpm/MainMenuLeft.do?vStartP=${menuManageVO.menuNo}" />
	    </div>
		<div id="content">
			<!--  컨텐츠 영역 : Start -->
			<iframe id="contentFrame" name="contentFrame" onload="javascript:changeFrameSize(); return false;" src="/sym/mnu/mpm/MainMenuRight.do?vStartP=${menuManageVO.menuNo}" width="100%" height="400" seamless="seamless"  title="컨텐츠영역"></iframe>
	   		<!--  컨텐츠 영역 : End -->
		</div>
	</div>
<!-- 
	<c:import url="/sym/mnu/mpm/MainMenuBottom.do" />
-->
</div>
</body>
</html>
