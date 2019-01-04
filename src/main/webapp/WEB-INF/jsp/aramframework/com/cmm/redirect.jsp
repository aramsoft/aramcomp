<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Redirect</title>
</head>

<body>
<form name="form" id="form" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 검색조건 유지 -->
<input type="hidden" name="curTrgetId" value="${searchVO.searchCondition}" />
<input type="hidden" name="curTrgetId" value="${searchVO.searchKeyword}" />
<input type="hidden" name="curTrgetId" value="${searchVO.pageIndex}" />
<input type="hidden" name="curTrgetId" value="${searchVO.recordPerPage}" />
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

