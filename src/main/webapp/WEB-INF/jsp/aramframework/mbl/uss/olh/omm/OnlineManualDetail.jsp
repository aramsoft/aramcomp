<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>온라인매뉴얼 상세조회</title>

</head>

<body>
	
<!-- 게시판 View start -->
<div id="detail" data-role="page">
	
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="#list" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1>온라인매뉴얼상세</h1>
	</div>
	<!-- header end -->

	<div data-role="content" class="com-copContent">
									
		<ul class="uss-hpcDetail">
			<li>
				<span class="uss-tit">매뉴얼명</span>
				<span class="uss-con"><c:out value="${onlineManualVO.onlineMnlNm}" /></span>
			</li>
			<li>
				<span class="uss-tit">매뉴얼구분</span>
				<span class="uss-con">
					<c:forEach items="${onlineMnlSeCodeList}" var="result" varStatus="pollKindStatus">
						<c:if test="${result.code eq onlineManualVO.onlineMnlSeCode}">
						<c:out value="${result.codeNm}" escapeXml="false" />
						</c:if>
					</c:forEach>
				</span>
			</li>
			<li>
				<span class="uss-tit">매뉴얼정의</span>
				<span class="uss-con">${fn:replace(onlineManualVO.onlineMnlDfn , crlf , '<br>')}</span>
			</li>
			
			<li>
				<span class="uss-tit">매뉴얼설명</span>
			</li>
			<li class="uss-contentsView">${fn:replace(onlineManualVO.onlineMnlDc , crlf , '<br>')}</li>
		</ul>

		<div class="com-addBgBtn">
          	<a href='javascript:fn_aram_listPage();' data-role="button" data-theme="b">목록</a>	
		</div>
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer"  data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- 게시판 View end -->	
						
</body>
</html>
