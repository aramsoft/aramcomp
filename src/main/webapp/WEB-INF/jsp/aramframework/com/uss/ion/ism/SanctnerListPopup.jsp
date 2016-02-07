<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : SanctnerListPopup.jsp
 * @Description : 약식결재자 목록
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
<title>약식결재자 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2><span id="title"></span> 선택</h2>
</div>

<form:form commandName="sanctnerVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
	<div class="keyword_area">
		<label for="searchCondition">조회조건 : </label>
   		<form:select path="searchCondition" class="select" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
		   	<form:option value="ORGNZT_NM" label="부서명" />
		   	<form:option value="USER_NM" label="사원명 " />
	   	</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>
	
<table class="table-list"  summary="이 표는 사용자 정보를 제공하며, 부서, 직위, 사번, 사원명 정보로 구성되어 있습니다 .">
<caption>사용자 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col" width="25%">부서</th>
	    <th scope="col" width="15%">직위</th>
	    <th scope="col" width="15%">사번</th>
	    <th scope="col" width="20%">사원명</th> 
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
	<tr class="link" onclick="javascript:fn_aram_choose('<c:out value="${result.uniqId}"/>', '<c:out value="${result.emplNo}"/>', '<c:out value="${result.emplyrNm}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<td class="lt_text3"><c:out value="${index}"/></td>

	    <td class="lt_text3"><c:out value="${result.orgnztNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.ofcpsNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.emplNo}"/></td>
	    <td class="lt_text3"><c:out value="${result.emplyrNm}"/></td>
	</tr>
	</c:forEach>	  
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

window.onload = function() {
	var opener = parent.window.dialogArguments;
	document.getElementById("title").innerText = opener[0]; 
	parent.document.title = opener[0] + " 목록조회"; 
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
	var varForm = document.getElementById("sanctnerVO");
	varForm.pageIndex.value = pageNo; 
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ism/listSanctner.do";
	varForm.submit();	
}

function fn_aram_search() {
	var varForm = document.getElementById("sanctnerVO");
	varForm.pageIndex.value = '1'; 
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ism/listSanctner.do";
	varForm.submit();	
}

function fn_aram_choose(uniqId, emplNo, emplyrNm, orgnztNm) {
	if( window.opener.gArguments["uniqId"] )   window.opener.gArguments["uniqId"].value = uniqId;
	if( window.opener.gArguments["emplNo"] )   window.opener.gArguments["emplNo"].value = emplNo;
	if( window.opener.gArguments["emplyrNm"] ) window.opener.gArguments["emplyrNm"].value = emplyrNm;
	if( window.opener.gArguments["orgnztNm"] ) window.opener.gArguments["orgnztNm"].value = orgnztNm;
	window.close();
}

</script>

</body>
</html>