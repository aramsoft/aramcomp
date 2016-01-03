<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>첨부파일 선택</title> 
      
<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css'/>"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css'/>"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js'/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js'/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js'/>"></script>

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css'/>"/>

</head>

<body>

<!-- 첨부파일 선택 페이지 start -->
<div id="selectAtchFile" data-role="page" data-theme="d">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>첨부파일 선택</h1>
		<a href="#writeMms" data-icon="back" class="ui-btn-right">이전</a>
	</div>
    <!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		<ul data-role="listview">
		
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
		
		</div>
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>
<!-- 첨부파일 선택 페이지 end -->	

</body>
</html>