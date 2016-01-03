<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : ReturnPopup.java
 * @Description : 반려
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
<title>반려</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2><span id="title"></span></h2>
</div>

<div id="search_area">
	<div class="button_area">
    	<span class="button"><a href="#" onclick="javascript:fncPopUpReturn(); return false;">반려</a></span>     
        <span class="button"><a href="#" onclick="window.close(); return false;"><spring:message code="button.close" /></a></span>     
	</div>
</div>

<!-- ------------------------------------------------------------------ 등록  폼 영역  -->
<form id="targetForm" name="targetForm" method="post" action="#">
<input type="hidden" name="cmd">
<input type="hidden" name="checkedEventRceptForConfm">

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<label for="returnResn">반려사유</label>
    		<span class="required_icon"></span>
    	</th>          
    	<td width="80%">
      		<textarea id="returnResn" name="returnResn" class="txArea" rows="4" cols="70" title="반려사유"><c:out value='${rwardManageVO.returnResn}'/></textarea>
    	</td>    
  	</tr> 
</table>
</form>

</div>

<script type="text/javascript">

window.onload = function() {
	var title = window.opener.gArguments["title"];
	document.getElementById("title").innerText = title; 
	window.document.title = title; 
}

/* ********************************************************
* 반려 처리화면
******************************************************** */
function fncPopUpReturn() {
    if(confirm("반려처리  하시겠습니까?")){
    	var retVal = document.getElementById("returnResn").value;
    	var retFunc = window.opener.gArguments["retFunc"];
    	if( retFunc != undefined ) {
    		retFunc('R', retVal);		
    	}
     	window.close();
    }
}

</script>

</body>
</html>