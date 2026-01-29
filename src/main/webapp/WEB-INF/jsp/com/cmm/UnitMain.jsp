<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>▒▒▒ 아람소프트 컴포넌트   ▒▒▒</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/unit.css" type="text/css">

<script type="text/javascript">

function resize() {
	var height = 0;
	if( typeof( window.innerHeight ) == 'number' ) { //Non-IE
		height = window.innerHeight - 100;
	} else if( document.documentElement && document.documentElement.clientHeight  ) {
		height = document.documentElement.clientHeight - 100;
	}
	if( height < document.getElementById("contentFrame").height ) {
		height = document.getElementById("contentFrame").height;
	}
	document.getElementById("leftmenu").style.height = height + "px";
	document.getElementById('contentFrame').height = height;
}

</script>

</head>

<body onload="javascript:resize();"  onresize="javascript:resize();">

<div id="wrapper">

	<header>
		<h1>아람컴포넌트</h1>
	</header>
	
	<div id="main_container">
	    <div id="leftmenu">
	    	<c:import url="/UnitLeft.do" />
	    </div>
		<div id="content">
			<!--  컨텐츠 영역 : Start -->
			<iframe id="contentFrame" name="contentFrame" src="/UnitContent.do" width="100%" height="400" seamless="seamless" ></iframe>
	   		<!--  컨텐츠 영역 : End -->
		</div>
	</div>
	<footer>
		<p>Copyright(c)2012-2026 Aram High-Tech.</p>
	</footer>
</div>
</body>
</html>
