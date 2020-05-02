<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>▒▒▒ 아람컴포넌트  ▒▒▒</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/info.css" type="text/css">

<script type="text/javascript">

	function fncGoAfterErrorPage(){
		history.back();
	}

</script>
</head>

<body>

<DIV id="wrap">

<div id="content">

	<div class="logo">
        <img src="${pageContext.request.contextPath}/images/com/cmm/er_logo.jpg" width="380" height="57" />
    </div>
	<div class="content_main">
        <div class="icon">
           	<img src="${pageContext.request.contextPath}/images/com/cmm/danger.jpg" width="74" height="74" />
        </div>
        <div>
            <h3>접근 권한이 없습니다.</h3>
			<c:if test="${loginVO == null}">
				로그인 후 사용하시기 바랍니다.
			</c:if>
		</div>	
	</div>
	<div class="buttons">              
        <a href="javascript:fncGoAfterErrorPage();"><img src="${pageContext.request.contextPath}/images/com/cmm/go_history.jpg" width="90" height="29" border="0"/></a>
	</div>

</div>

</div>

</body>
</html>
