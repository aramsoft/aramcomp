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
 * @Class Name : MtgPlaceResveDplactCeck.jsp
 * @Description : 회의실예약 중복체크
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
<title>회의실예약 중복체크</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:420px;">

<div class="content_title">
	<h2>회의실 중복확인</h2>
</div>

<!-- ********** 여기서 부터 본문 내용 *************** -->
<form name="dplactCeckForm" method="post">
<input type="hidden" name="dplactCeck" value="<c:out value='${dplactCeck}'/>">
</form>

<!--  등록  폼 영역  -->
<table border="0" class="table-list" summary="중복여부">
<caption>중복여부</caption>
  	<tr>
    	<td class="lt_text3">
    	<c:if test="${dplactCeck> 0}">
			<b>
			회의실 예약이 불가능합니다.<br>
			회의실 중복 예약입니다.
			</b>
        </c:if>
    	<c:if test="${dplactCeck == 0}">
			<b>
			회의실 예약 가능합니다.
			</b>
        </c:if> 
    	</td>    
  	</tr> 
</table>

<!-- 줄간격조정  -->
<div style="margin-top:40px; width:100%"></div>

<div style="width:100%; text-align:center;">
	<div class="button_area">
   		<span class="button"><a href="#" onclick="javascript:fn_aram_dplact_check(); return false;"><spring:message code="button.close" /></a></span>     
	</div>
</div>

</div>

<script type="text/javascript">

/* ********************************************************
 * Window Close()  처리
 ******************************************************** */
 /*설명 : 중복체크 결과값 리턴및 화면 close */
function fn_aram_dplact_check() {
	var sDplactCeck = null;
	if(dplactCeckForm.dplactCeck.value == 0 ) sDplactCeck = "Y";
	else sDplactCeck = "N";

	window.opener.gArguments["dplactCeck"].value = sDplactCeck;
	window.close();
}
 
</script>

</body>
</html>