<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : AdministCodePopup.jsp
  * @Description : 행정코드 목록
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
<title>행정코드 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body >
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>행정코드 목록</h2> 
</div>

<form:form commandName="administCodeVO" action=""  method="post">
<input type=hidden name="administZoneSe">
<input type=hidden name="administZoneCode">

<div id="search_area">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
	   	<c:if test="${administCodeVO.searchCondition == '1'}">법정동 지역명</c:if>
	   	<c:if test="${administCodeVO.searchCondition == '2'}">행정동 지역명</c:if>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="구분, 행정구역코드, 행정구역명이 나오는 행정코드 목록을 조회한다.">
<CAPTION>행정코드 목록</CAPTION>
<thead>
	<tr>
		<th scope="col" width="10%">순번</th>
		<th scope="col" width="15%">구분</th>
		<th scope="col" width="15%">행정구역코드</th>
		<th scope="col" width="60%">행정구역명</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan=4>
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
	</c:if>

	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_choose('${result.administZoneCode}','${result.administZoneNm}'); return false;">

		<td class="lt_text3"><c:out value="${(administCodeVO.pageIndex - 1) * administCodeVO.pageSize + status.count}"/></td>
		<td class="lt_text3">
			<c:choose>
			<c:when test='${result.administZoneSe == "1"}'>법정동</c:when>
			<c:when test='${result.administZoneSe == "2"}'>행정동</c:when>
			</c:choose>
		</td>
		<td class="lt_text3">${result.administZoneCode}</td>
		<td class="lt_text">${result.administZoneNm}</td>
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

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("administCodeVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/listAdministCodePopup.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("administCodeVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/listAdministCodePopup.do";
    varForm.submit();
}

/* ********************************************************
* 결과 반환
******************************************************** */
function fn_aram_choose(administZoneCode, administZoneNm){
	if( window.opener.gArguments["administZoneCode"] ) window.opener.gArguments["administZoneCode"].value = administZoneCode;
	if( window.opener.gArguments["administZoneNm"] )   window.opener.gArguments["administZoneNm"].value = administZoneNm;
	window.close();
}

</script>

</body>
</html>
