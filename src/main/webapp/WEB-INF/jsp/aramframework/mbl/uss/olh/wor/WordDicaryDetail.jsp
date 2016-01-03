<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>용어사전상세조회 </title>

</head>

<body>
	
<div id="detail" data-role="page">
	
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="#list" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1 id="viewTitle">용어사전상세조회</h1>
	</div>
	<!-- header end -->
	
	<div data-role="content" class="com-copContent">
		
		<ul class="uss-hpcDetail">
			<li>
				<span class="uss-tit">용어명 </span>
				<span class="uss-con gray">
					<c:out value="${wordDicaryVO.wordNm}" />
				</span>
			</li>
			
			<li>
				<span class="uss-tit">영문명 </span>
				<span class="uss-con">
					<c:out value="${wordDicaryVO.engNm}" />
				</span>
			</li>		
			
			<li>			
				<span class="uss-tit">용어 설명 </span>
			</li>
			<li class="uss-contentsView uss-bodeB">
				${fn:replace(wordDicaryVO.wordDc , crlf , '<br>')}
			</li>
			<li>
				<span class="uss-tit">동의어 </span>
				<span class="uss-con">
					<c:out value="${wordDicaryVO.synonm}" />
				</span>
			</li>
			
			<li>
				<span class="uss-tit">등록일자 </span>
				<span class="uss-con">
					<fmt:formatDate value="${wordDicaryVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
				</span>
			</li>
			
			<li>
				<span class="uss-tit">등록자 </span>
				<span class="uss-con">
					<c:out value="${wordDicaryVO.emplyrNm}" />
				</span>
			</li>
		</ul>

		<div class="com-addBgBtn">
			<a href='javascript:fn_aram_listPage();' data-role="button" data-theme="b">목록</a>			
		</div>

	</div>
	
	<!-- footer start -->
	<div data-role="footer"  data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
</body>
</html>

