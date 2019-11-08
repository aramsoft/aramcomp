<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Redirect</title>
</head>

<body>
<form:form commandName="searchVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<script type="text/javascript">

window.onload = function() {

	if("${message}" != ''){	alert("${message}");}

	var varForm = document.getElementById("searchVO");
	varForm.action = "${redirectURL}";
	varForm.submit();
};

</script>

</body>
</html>

