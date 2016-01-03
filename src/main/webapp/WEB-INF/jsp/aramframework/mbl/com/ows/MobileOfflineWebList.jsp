<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
var contextPath = "${pageContext.request.contextPath}";	// js uri 적용을 위한 contextpath
var fetchRowIdx = 1;
var deviceType = "M";

/*********************************************************
 * 오프라인 서비스 글 목록 조회 초기화
 ******************************************************** */				
$(document).ready(function() {
	if(window.navigator.onLine == true) {
  		// 1. 오프라인 서비스 목록 조회
  		fn_offlineList();
	} else {
        // 2. [오프라인] 오프라인 목록 조회 내용 webstorage에 적재
        var html = localStorage.getItem("localOfflineList");
        $('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
	}
});
</script>
      
<!-- 오프라인웹 서비스 js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/com/ows/ows.js"></script>
</head>

<body>
	
<!-- 오프라인 서비스 목록 -->
<div id="list" data-role="page" data-theme="d">

    <!-- header start -->
    <div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>오프라인서비스</h1>
	</div>
    <!-- header end -->

 	<form:form name="offlineWebVO" method="post" commandName="offlineWebVO">
 		<input type="hidden" name="searchSn"/>
 	</form:form>	
 		
    <!-- content start -->
	<div data-role="content">
		<ul data-role="listview" data-inset="true"></ul>
	</div>
	
	<div class="mcomd-more" id="pageNavi"><a href="javascript:fn_moreFetch();">더보기</a></div>
	<!-- content end -->

    <!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
    <!-- footer end -->
    
</div>
</body>
</html>    