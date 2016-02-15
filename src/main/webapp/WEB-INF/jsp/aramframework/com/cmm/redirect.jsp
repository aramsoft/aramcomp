<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Redirect</title>
</head>

<body>
<%
	String searchCondition = request.getParameter("searchCondition") == null ? "" : (String) request.getParameter("searchCondition");
	String searchKeyword = request.getParameter("searchKeyword") == null ? "" : (String) request.getParameter("searchKeyword");
	String pageIndex = request.getParameter("pageIndex") == null ? "1" : (String) request.getParameter("pageIndex");
	String recordPerPage = request.getParameter("recordPerPage") == null ? "10" : (String) request.getParameter("recordPerPage");

	searchCondition = request.getParameter("searchVO.searchCondition") == null ? "" : (String) request.getParameter("searchVO.searchCondition");
	searchKeyword = request.getParameter("searchVO.searchKeyword") == null ? "" : (String) request.getParameter("searchVO.searchKeyword");
	pageIndex = request.getParameter("searchVO.pageIndex") == null ? "1" : (String) request.getParameter("searchVO.pageIndex");
	recordPerPage = request.getParameter("searchVO.recordPerPage") == null ? "10" : (String) request.getParameter("searchVO.recordPerPage");
%>
<form name="form" id="form" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<%=searchCondition%>" />
<input type="hidden" name="searchKeyword" value="<%=searchKeyword%>" />
<input type="hidden" name="pageIndex" value="<%=pageIndex%>" />
<input type="hidden" name="recordPerPage" value="<%=recordPerPage%>" />

<input type="hidden" name="searchVO.searchCondition" value="<%=searchCondition%>" />
<input type="hidden" name="searchVO.searchKeyword" value="<%=searchKeyword%>" />
<input type="hidden" name="searchVO.pageIndex" value="<%=pageIndex%>" />
<input type="hidden" name="searchVO.recordPerPage" value="<%=recordPerPage%>" />
<!-- 검색조건 유지 -->
</form>

<script type="text/javascript">

window.onload = function() {

	if("${message}" != ''){	alert("${message}");}

	var varForm = document.getElementById("form");
	varForm.action = "${redirectURL}";
	varForm.submit();
};

</script>

</body>
</html>

