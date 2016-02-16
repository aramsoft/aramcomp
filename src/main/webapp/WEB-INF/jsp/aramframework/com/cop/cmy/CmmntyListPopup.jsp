<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmmntyListPopup.jsp
  * @Description : 커뮤니티 목록
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
<title>커뮤니티 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>커뮤니티 목록</h2>
</div>

<form:form commandName="searchVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
	<div class="keyword_area">
   		<form:select path="searchVO.searchCondition" class="select" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
		   	<form:option value="CMMNTY_NM" label="커뮤니티명" />
	   	</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="번호, 커뮤니티명, 생성자, 생성일, 사용여부, 선택   목록입니다">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >커뮤니티명</th>
	    <th scope="col" width="20%">생성자</th>
	    <th scope="col" width="15%">생성일</th>
	    <th scope="col" width="10%">사용여부</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onClick="javascript:fn_aram_choose('<c:out value="${result.cmmntyId}"/>','<c:out value="${result.cmmntyNm}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.cmmntyNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	    <td class="lt_text3">
	    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
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

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("searchVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listCommunityPopup.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("searchVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listCommunityPopup.do";
    varForm.submit();
}

function fn_aram_choose(cmmntyId, cmmntyNm){
	if(window.opener.gArguments["trgetId"]) window.opener.gArguments["trgetId"].value = cmmntyId;
	if(window.opener.gArguments["trgetNm"]) window.opener.gArguments["trgetNm"].value = cmmntyNm;
	window.close();
}

function fn_aram_close(){
	window.close();
}

</script>

</body>
</html>
