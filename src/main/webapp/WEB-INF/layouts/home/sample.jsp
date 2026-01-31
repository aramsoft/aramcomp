<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
 /**
  * @Class Name : sample.jsp
  * @Description : 샘플홈페이지화면  
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *
  */ 
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:insertAttribute name="title"/></title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home/sample/common.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/jquery-1.7.1.min.js"></script>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <header>
	    <c:import url="/home/sample/include/IncHeader.do" />
    </header>
    
    <nav id="topnavi">
        <c:import url="/home/sample/include/IncTopnav.do" />
    </nav>
    
    <!-- //header 끝 -->
	<!-- container 시작 -->
	<div id="container">
		<!-- 좌측메뉴 시작 -->
		<nav id="leftmenu">
			<c:import url="/home/sample/include/IncLeftmenu.do" />
		</nav>
		<!-- //좌측메뉴 끝 -->
		<!-- content 시작 -->
		
<tiles:insertAttribute name="body"/> 

		<!-- //content 끝 -->
	</div>	
	<!-- //container 끝 -->
	
	<footer>
		<c:import url="/home/sample/include/IncFooter.do" />
	</footer>
</div>
<!-- //전체 레이어 끝 -->

</body>
</html>