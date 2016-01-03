<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>행정전문용어사전상세보기 </title>

</head>

<body>
	
<!-- View start -->
<div id="detail" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="#list" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1 id="viewTitle">행정용어사전상세</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">

		<ul class="uss-hpcDetail">
			<li>	
				<span class="uss-tit">행정용어명</span>
				<span class="uss-con">
	        		<c:out value="${administrationWordVO.administWordNm}" />
				</span>
			</li>
			
			<li>
				<span class="uss-tit">행정용어영문명</span>
				<span class="uss-con">
					<c:out value="${administrationWordVO.administWordEngNm}" />
				</span>
			</li>
			
			<li>
				<span class="uss-tit">행정용어약어명</span>
				<span class="uss-con">
					<c:out value="${administrationWordVO.administWordAbrv}" />
				</span>
			</li>		
			
			<li>			
				<span class="uss-tit">주제영역 </span>
				<span class="uss-con">
					<c:out value="${administrationWordVO.themaRelm}" />
				</span>
			</li>
			
			<li>			
				<span class="uss-tit">용어구분</span>
				<span class="uss-con">
					<c:if test="${administrationWordVO.wordDomn == '1'}">표준어</c:if>
					<c:if test="${administrationWordVO.wordDomn == '2'}">동의어</c:if>
				</span>
			</li>

			<li>
				<span class="uss-tit">행정용어정의</span>
				<span class="uss-con">
					<c:out value="${administrationWordVO.administWordDf}" />
				</span>
			</li>
			
			<li>			
				<span class="uss-tit">행정용어설명</span>
			</li>
			<li class="uss-contentsView">
				<c:out value="${administrationWordVO.administWordDc}" />
			</li>
			
		</ul>
		
		<div class="com-addBgBtn">
			<a href='javascript:fn_aram_listPage();' data-role="button" data-theme="b">목록</a>			
		</div>							
			
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- view end -->
	
</body>
</html>

