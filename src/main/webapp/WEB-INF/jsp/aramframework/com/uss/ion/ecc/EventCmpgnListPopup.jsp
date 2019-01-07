<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EventCmpgnListPopup.jsp
 * @Description :  행사/이벤트/캠페인 목록 팝업
 *
 * @ 수정일              수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2014.11.11   조헌철          최초 생성
 *
 *  @author 아람컴포넌트 조헌철
 *  @since 2014.11.11
 *  @version 1.0
 *  @see
 */
%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<html lang="ko">
<head>
<meta http-equiv="Pragma" content="No-Cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>행사/이벤트/캠페인 목록 팝업</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css"> 

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>행사/이벤트/캠페인 목록 팝업</h2>
</div>

<form:form commandName="eventCmpgnVO" action="" method="post">
<input name="eventId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="EVENT_CN" label="행사내용" />			   
	   		<form:option value="FRST_REGISTER_ID" label="작성자" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="순번, 행사유형, 행사내용, 행사시작일자, 행사종료일자, 작성자명, 등록일자, 선택   목록입니다">
<thead>
  	<tr>
	    <!-- th width="3%"><input type="checkbox" name="all_check" class="check2"></th-->
	    <th scope="col" width="7%" >순번</th>
	    <th scope="col" width="10%">행사유형</th>
	    <th scope="col"            >행사내용</th>
	    <th scope="col" width="12%">행사시작일자</th>
	    <th scope="col" width="12%">행사종료일자</th>
	    <th scope="col" width="10%">작성자명</th>
	    <th scope="col" width="10%">등록일자</th>
  	</tr>
</thead>
<tbody>
 	<c:forEach items="${resultList}" var="result" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_choose('<c:out value="${result.eventCn}"/>','${result.eventId}'); return false;">
    	<td class="lt_text3"><c:out value="${status.count}"/></td>
    	<td class="lt_text3">

			<c:forEach items="${COM035_eventTy}" var="comCode">
			<c:if test="${comCode.code eq fn:trim(result.eventTyCode)}">
			<c:out value="${fn:replace(comCode.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>

    	</td>
    	<td class="lt_text3L">${result.eventCn}</td>

	    <td class="lt_text3">
			<c:out value="${fn:substring(result.eventSvcBeginDe, 0,4)}-${fn:substring(result.eventSvcBeginDe, 4,6)}-${fn:substring(result.eventSvcBeginDe, 6,8)}" escapeXml="false" />
	    </td>
	    <td class="lt_text3">
			<c:out value="${fn:substring(result.eventSvcEndDe, 0,4)}-${fn:substring(result.eventSvcEndDe, 4,6)}-${fn:substring(result.eventSvcEndDe, 6,8)}" escapeXml="false" />
	    </td>
	    <td class="lt_text3">${result.frstRegisterNm}</td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_choose(eventCn, eventId){
	if( window.opener.gArguments["eventCn"] ) window.opener.gArguments["eventCn"].value = eventCn;
	if( window.opener.gArguments["eventId"] ) window.opener.gArguments["eventId"].value = eventId;
	window.close();
}

</script>

</body>
</html>

