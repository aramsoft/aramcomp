<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ScrapMainPage.jsp
 * @Description : 마이페이지 스크랩 목록조회
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>스크랩 목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<form name="scrapForm" method="post" action="${pageContext.request.contextPath}/cop/scp/detailScrap.do">
<table class="table-list" summary="등록된 스크랩에 대한 목록을 제공합니다.">
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text6" colspan="1">자료가 없습니다.</td>
	</tr>
	</c:if>
	
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.scrapId}"/>'); return false;" >
		<td class="lt_text6"><c:out value="${result.scrapNm}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<input name="pageIndex" type="hidden" value="1">
<input type="hidden" name="scrapId" value="">
</form>

</DIV>

<script type="text/javascript">

function fn_aram_detail(scrapId) {
	document.scrapForm.scrapId.value = scrapId;
	document.scrapForm.submit();
}

</script>

</body>
</html>
