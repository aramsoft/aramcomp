<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import	= "aramframework.com.cmm.util.LogUtil" %>
<%@ page isErrorPage="true" %>
<%
	LogUtil.logErrorMessage(exception, 100);
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>▒▒▒ 아람컴포넌트  ▒▒▒</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/info.css" type="text/css">

<script type="text/javascript">

function fncGoAfterErrorPage(){
	history.back();
}

</script>
</head>

<%
	String errorMsg = ""; 
	String errorDesc = "";
		
	if (exception != null) {
		errorMsg = exception.getMessage();
		errorDesc = "관리자에게 문의 하시기 바랍니다.";
	} 
%>

<body>

<DIV id="wrap">

<div id="content">

	<div class="logo">
        <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/er_logo.jpg" width="380" height="57" />
    </div>
	<div class="content_main" style="width:350px;">
        <div class="icon" style="height:120px;">
           	<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/danger.jpg" width="74" height="74" />
        </div>
        <div style="text-align:left;">
            <h3>처리 오류</h3>
			<%= errorMsg%><br />
			관리자에게 문의하시기 바랍니다.
		</div>	
	</div>
	<div class="buttons">              
        <a href="javascript:fncGoAfterErrorPage();"><img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/go_history.jpg" width="90" height="29" border="0"/></a>
	</div>

</div>

</div>

</body>
</html>
