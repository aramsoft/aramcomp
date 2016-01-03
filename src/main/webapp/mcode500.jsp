<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import	= "aramframework.com.cmm.util.Log4Jsp" %>
<%
	Log4Jsp.error("[mcode500.jsp] HTTP 500 Error - Internal Server error");
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>▒▒▒ 아람컴포넌트  ▒▒▒</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/mbl/uss/ussCommon.css" type="text/css">

<!-- History.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/cmm/jquery.history.js"></script>	  
<script>

function fncGoAfterErrorPage(){
	history.back();
}

</script>
</head>  
 
<body>
<!-- 모바일 페이지 start -->
<div class="ussPopup">
	<div class="popErrorBox">
		<div class="popHeader"></div>
		<div class="popContents">
			<dl>
			<dt><img src="${pageContext.request.contextPath}/images/egovframework/mbl/uss/ic_warning.png" /></dt>
			<dd>HTTP 500 Error<br>Internal Server error</dd>
			</dl>
		</div>
		<div class="popBtnBack"><a href="javascript:fncGoAfterErrorPage();"><img src="${pageContext.request.contextPath}/images/egovframework/mbl/uss/pop_btn_back.png" /></a></div>
	</div>
</div>
<!-- 모바일 페이지 end -->
</body>
</html>



