<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일지 목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/cop/egovBoard.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

</head>

<body>

<div id="view_schdul" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:history.back();" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1>일정관리 목록조회</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">

		<div id="searchview">
			<div class="uss-Search">
				<form id="searchVO" name="searchVO" method="post" data-role="none">
					<select id="searchCondition" name="searchCondition" data-role="none">
					   <!-- <option value='SCHDUL_SE'>일정구분</option>  -->
					   <option value='SCHDUL_NM'>일정명</option>
				    </select>
	               	<div class="uss-SearchBox">
		                <input type="text" name="searchKeyword" id="searchKeyword" class="type-text" data-role="none" />
					</div>
		            <input type="button" value="조회" onclick='javascript:fn_schdul_list("${pageContext.request.contextPath}/cop/smt/sim/listSchdulPopupJson.mdo");' class="uss-SearchBtn" data-role="none" />
				</form>
			</div>
		</div>
		
		<ul data-role="listview">
		</ul>

	</div>
	<!-- contents end -->
	
</div>

</body>
</html>
