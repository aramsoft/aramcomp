<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CnsltLoginRealnmChoice.jsp
  * @Description : 로그인 Or 실명확인
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *  
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>로그인 Or 실명확인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:300px;margin:20px;">

<div class="content_title">
	<h2>로그인 &amp; 실명확인 선택</h2>
</div>

<form name="LoginRealnmForm"  method="post" action="">
	 	  
<!-- 등록  폼 영역  -->
<table border="0" class="table-register">
<caption>로그인 실명확인 선택</caption>
  	<tr> 
    	<th width="100%">
    		로그인 혹은 실명확인을 선택하시기 바랍니다...
    	</th>
  	</tr>
</table>

<!-- 줄간격조정  -->
<div style="margin-top:20px; width:100%"></div>

<!-- 로그인/실명확인 버튼  -->
<div style="width:100%; text-align:center;">
	<div class="button_area">
		<span class="button"><a href="javascript:fn_aram_confirm_login();">로그인</a></span>
		<span class="button"><a href="JavaScript:fn_aram_confirm_realname();">실명확인</a></span>
		<span class="button"><a href="JavaScript:fn_aram_cancel_loginrealnm();">취소</a></span>
	</div>
</div>

</form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 로그인 처리
 ******************************************************** */
function fn_aram_confirm_login(){
	fn_aram_confirm_callback("L");
}

/* ********************************************************
 * 실명확인처리
 ******************************************************** */
function fn_aram_confirm_realname() {
	fn_aram_confirm_callback("R");
}

/* ********************************************************
 * 취소처리
 ******************************************************** */
function fn_aram_cancel_loginrealnm() {
	fn_aram_confirm_callback("C");
}

/* ********************************************************
 * 결과값 리턴
 ******************************************************** */
function fn_aram_confirm_callback(ls_loginRealnmAt){
	window.returnValue = ls_loginRealnmAt;	
	window.close();	
}

</script>

</body>
</html>
