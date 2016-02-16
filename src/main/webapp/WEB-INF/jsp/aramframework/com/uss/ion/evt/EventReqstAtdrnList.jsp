<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="aramframework.com.utl.fcc.service.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EventReqstAtdrnList.jsp
 * @Description : 행사참석자 목록
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
<title>행사참석자 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%;">

<div class="content_title">
	<h2>행사참석자 목록</h2>
</div>

<form:form commandName="eventAtdmVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
</div>

<form:hidden path="searchVO.searchCondition" value="1" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="행사 참석자 목록 순번,참석자명,소속,E-Mail,구분 구성">
<caption>행사 참석자 목록</caption>
<thead>
	<tr>   
		<th scope="col" width="10%">순번</th>
		<th scope="col"            >참석자명</th>
		<th scope="col" width="30%">소속</th>
		<th scope="col" width="20%">E-Mail</th>
		<th scope="col" width="20%">구분</th>
	</tr>
</thead>     
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan=5><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>

 	<c:set var="startIndex" value="${(eventAtdmVO.pageIndex-1) * eventAtdmVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${eventAtdmVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.userNm}"/></td>
		<td class="lt_text3"><c:out value="${result.orgnztNm}"/></td>
		<td class="lt_text3"><c:out value="${result.userEmail}"/></td>
		<td class="lt_text3">
			<c:if test="${result.confmAt eq 'A'}">신청</c:if>
			<c:if test="${result.confmAt eq 'C'}">승인</c:if>
			<c:if test="${result.confmAt eq 'R'}">반려</c:if>
		</td>
	</tr>    
	</c:forEach>
</tbody>  
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("eventAtdmVO");
	varForm["searchVO.pageIndex"].value    = pageNo;
	varForm.action             = "${pageContext.request.contextPath}/uss/ion/evt/listEventReqst.do";
	varForm.submit();
}

</script>

</body>
</html>

