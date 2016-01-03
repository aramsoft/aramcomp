<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
var syncSn = "";		// 동기화 서비스 상세글 일련번호
var rowData = "";		// 동기화 서비스 상세조회 데이터
var seperator = "|";	// 동기화 서비스 상세조회 내역 구분자         

/*********************************************************
 * 동기화 서비스 수정 init
 ******************************************************** */
$(document).ready(function() {
   	rowData = localStorage.getItem(localStorage.getItem("localSn")).split("|");
   	fn_modify_view();
});
</script>
<!-- 동기화 서비스 JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/com/syn/syn.js"></script>
</head>

<body>
	
<!-- 동기화 서비스 등록 -->
<div data-role="page" data-theme="d">

    <!-- header start -->
    <div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>동기화서비스 수정</h1>
	</div>
    <!-- header end -->

    <!-- content start -->
	<div data-role="content"  class="egov-mcomdContent">
		<!-- form -->
		<form:form commandName="syn" name="syn" method="post">
			<input type="hidden" id="syncSn" />
		</form:form>		

		<dl class="realtimeView">
			<dt><label for="syncSj"><strong>제목</strong></label></dt>
			<dd><input type="text" id="syncSj" size="30" value=""/></dd>
			<dt><label for="syncCn"><strong>내용</strong></label></dt>
			<dd><textarea id="syncCn" rows="8" cols="40"></textarea></dd>
		</dl>
		<div class="ui-grid-b realBtn">
			<div class="ui-block-a"><a href="javascript:fn_cancel();" data-role="button" data-theme="f">취소</a></div>
			<div class="ui-block-b"><a href="javascript:fn_save();" data-role="button" data-theme="b">저장</a></div>
			<div class="ui-block-c"><a href="${pageContext.request.contextPath}/mbl/com/syn/listMobileSync.mdo" data-ajax="false" data-role="button" data-theme="a">목록</a></div>			
		</div>
	</div>
	<!-- content end -->

    <!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
</body>
</html>    