<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BkmkMenuPreview.jsp
  * @Description : 바로가기메뉴 미리보기 
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
<title>바로가기메 뉴미리보기</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:360px;">

<div class="content_title">
	<h2>바로가기 메뉴관리</h2>
</div>

<form name="menuListForm" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="req_RetrunPath" value="${pageContext.request.contextPath}/sym/mnu/bmm/MenuList'/>">

<table class="table-list" border="0">
    <tr>
    	<td width="10%">
		    <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/utl/menu_base.gif" width="20" height="20" hspace="3" style="vertical-align: middle" alt = "base icon">
		</td>
		<td class="lt_text2" width="90%" colspan = "2"> 바로가기</td>
    </tr>
	<c:forEach var="result" items="${list_menulist}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_move('<c:url value="${result.progrmStrePath}"/>'); return false;">
		<td></td>
		<td>
		    <img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/utl/menu_page.gif" width="16" height="16" hspace="3" style="vertical-align: middle" alt = "menu_page icon">
		</td>
		<td class="lt_text1"><c:out value="${result.menuNm}" escapeXml="false"/></td>
	</tr>
	</c:forEach>
</table>
</form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 파일검색 화면 호출 함수
 ******************************************************** */
function fn_aram_move(progrmStrePath){
	var retFunc = window.opener.gArguments["retFunc"];
	if( retFunc != undefined ) {
		retFunc(progrmStrePath);		
	}
	window.close();
}

</script>

</body>
</html>

