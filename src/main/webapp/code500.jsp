<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isErrorPage="true" %>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>▒▒▒ 아람컴포넌트  ▒▒▒</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/info.css" type="text/css">

<script type="text/javaScript">

function fncGoAfterErrorPage(){
    history.back();
}
	
</script>
</head>

<body>

<DIV id="wrap">

<div id="content">

	<div class="logo">
        <img src="${pageContext.request.contextPath}/images/cmm/er_logo.jpg" width="380" height="57" />
    </div>
	<div class="content_main">
        <div class="icon">
           	<img src="${pageContext.request.contextPath}/images/cmm/danger.jpg" width="74" height="74" />
        </div>
        <div>
            <h3>500 Internal Server error</h3>
			관리자에게 문의하시기 바랍니다.
		</div>	
	</div>
	<div class="buttons">              
        <a href="javascript:fncGoAfterErrorPage();"><img src="${pageContext.request.contextPath}/images/cmm/go_history.jpg" width="90" height="29" border="0"/></a>
	</div>

</div>

</div>

</body>
</html>

