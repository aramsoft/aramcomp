<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QnaPasswordPopup.jsp
  * @Description : 작성비밀번호 확인
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *  
  */
%>
<html lang="ko">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>작성비밀번호 확인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body onload="fn_aram_initl_qnacn();">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:280px;margin:20px;">

<div class="content_title">
	<h2>작성 비밀번호 확인</h2>
</div>

<form name="QnaManageForm" method="post" onsubmit="fn_aram_confirm_qnapassword();">  

<!-- 작성 비밀번호를 확인하기 위한. ---------------------------->
	  	  
<!-- 등록  폼 영역  -->
<table style="border:0;" class="table-register">
<caption>작성 비밀번호 확인</caption>
  	<tr> 
    	<th width="50%">
    		<label for="writngPassword">작성글 비밀번호</label>
    		<span class="required_icon"></span>
    	</th>
    	<td >
    		<input name="writngPassword" id="writngPassword" type="password" size="15" value="" maxlength="20" title="작성글 비밀번호">  
    	</td>
  	</tr>
</table>

<!-- 줄간격조정  -->
<div style="margin-top:20px; width:100%"></div>

<!-- 확인/취소버튼  -->
<div style="width:100%; text-align:center;">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_confirm_qnapassword(); return false;">확인</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_cancel_qnapassword(); return false;">취소</a></span>
	</div>
</div>

</form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_aram_initl_qnacn(){

	// 작성글 비밀번호 입력란에 포커스..
	document.QnaManageForm.writngPassword.focus();
}

/* ********************************************************
 * 확인처리
 ******************************************************** */
function fn_aram_confirm_qnapassword(){
	var writngPassword = document.getElementById("writngPassword").value;	
	var retFunc = window.opener.gArguments["retFunc"];
	if( retFunc != undefined ) {
		retFunc(writngPassword);		
	}
	window.close();	
}

/* ********************************************************
 * 취소처리
 ******************************************************** */
function fn_aram_cancel_qnapassword() {
	window.close();	
}

</script>

</body>
</html>
