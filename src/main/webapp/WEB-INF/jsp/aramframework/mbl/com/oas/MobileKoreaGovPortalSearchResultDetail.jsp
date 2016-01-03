<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
/**
 * @Class Name : MobileKoreaGovPortalSearchResultDetail.jsp
 * @Description : 대한민국정부포털 검색 서비스 상세조회 화면
 * @Modification Information
 * @
 * @ 수정일                    수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2011.08.26   조재만          최초 생성
 *
 *  @author 모바일 신규공통컴포넌트개발팀 조재만
 *  @since 2011.08.26
 *  @version 1.0 
 *  @see
 *  
 */
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>OPEN-API연계서비스 - 대한민국정부포털 검색</title>

<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css"/>

</head>

<body>

<!-- 대한민국정부포털 검색 결과 행정용어 상세조회 start -->
<div id="view" data-role="page" data-theme="d">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>대한민국정부포털 검색 서비스</h1>
		<a href="#list" data-icon="back" class="ui-btn-right">이전</a>
	</div>
    <!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="egov-mcomdContent">
		<dl class="searchDetail">
			<dt><c:out value="${title}" /></dt>
			<dd><c:out value="${contents}" /></dd>
		</dl>
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed" >
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 대한민국정부포털 검색 결과 행정용어 상세조회  end -->		
	
</body>
</html>