<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>행정안전부 공통서비스 테스트 사이트(기업사용자)</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/main.css" type="text/css">

</head>

<body>

<c:import url="/sym/mnu/mpm/MainMenuHead.do" />

<div id="#main_home">

	<div class="wrap">
		<div>
			<iframe width="900" height="600" name="vew" id="vew" title="마이페이지 보기"  src = "${pageContext.request.contextPath}/uss/mpe/detailIndvdlpge.do" seamless="seamless" ></iframe>
		</div>
	</div> <!-- wrap -->
	
</div>

<!-- bottom -->
<c:import url="/sym/mnu/mpm/MainMenuBottom.do" />
</body>
</html>
