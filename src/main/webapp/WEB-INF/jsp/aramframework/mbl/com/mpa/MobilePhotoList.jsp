<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html> 
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>모바일 화면</title>
     
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/mpa/mpa.js"></script>

</head>
	
<body onload="fn_egov_initl_mobilephotolist();">

<!--리스트 페이지 -->
<div id="photoList" data-role="page" data-theme="d">

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>모바일사진앨범</h1>
	</div>
	
	<div data-role="content">
		<form name="listForm" method="post">
			<ul data-role="listview" class="photoList">
			</ul>
		</form>
	</div>
	
	<div data-role="footer" data-theme="a" data-position="fixed" >
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>

</div>

<!--상세 페이지 -->
<div id="photoDetail" data-role="page"  data-theme="d">

	<div data-role="header" data-position="inline" data-theme="a">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>모바일사진 상세화면</h1>
		<a href="#photoList" data-ajax="true" data-icon="back" class="ui-btn-right">이전</a>
	</div>	
	
	<div data-role="content">
		<form name="detailForm" method="post">
			<div id="detailview" class="photoView">
			</div>
		</form>
	</div>
		
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>

</div>

</body>
</html>