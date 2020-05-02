<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : SchdulListPopup.jsp
 * @Description : 일정 목록  팝업
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
<title>일정 목록 팝업</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css"> 
<base target="_self" />

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>일정관리 목록</h2>
</div>

<form:form modelAttribute="schdulManageVO" action="" method="post">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${schdulManageVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<form:select path="searchCondition" class="select" title="검색조건선택">
				<form:option value="" label="--선택하세요--" />
			   	<form:option value="SCHDUL_NM" label="일정명" />
		   	</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
			<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;"><spring:message code="button.close" /></a></span>
		</span>
	</div>	
</div>
<form:hidden path="pageIndex" />
	
<table class="table-list">
<thead>
	<tr>
	    <th width="10%">순번</th>
	    <th width="20%">일정구분</th>
	    <th            >일정명</th>
	    <th width="20%">시작일자</th>
	    <th width="20%">종료일자</th>
	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
    	<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
  	</tr>
 	</c:if>

	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(schdulManageVO.pageIndex-1) * schdulManageVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${schdulManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			 <c:if test="${result.othbcScope == 'P'}">개인</c:if>
			 <c:if test="${result.othbcScope == 'G'}">전체</c:if>
			 <c:if test="${result.othbcScope == 'C'}">커뮤니티</c:if>
		</td>
		<td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_choose('${result.schdulId}', '${result.schdulNm}'); return false;">
				${result.schdulNm}
	   		</a>
	   		</span>
		</td>
		<td class="lt_text3">${fn:substring(result.schdulBgnde, 0, 4)}-${fn:substring(result.schdulBgnde, 4, 6)}-${fn:substring(result.schdulBgnde, 6, 8)}</td>
		<td class="lt_text3">${fn:substring(result.schdulEndde, 0, 4)}-${fn:substring(result.schdulEndde, 4, 6)}-${fn:substring(result.schdulEndde, 6, 8)}</td>
	  </tr>
	</c:forEach>
</tbody>
</table>
	
</form:form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

 </DIV>
 
 <script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_schdulManage();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("searchVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("searchVO");

	if(varForm.searchKeyword.value == ""){
		alert('검색어를 입력해주세요!');
		varForm.searchKeyword.focus();
		return;
	}

	if(varForm.searchCondition.selectedIndex == 0){
		alert('검색 구분을 선택해주세요!');
		varForm.searchCondition.focus();
		return;
	}

	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulPopup.do";
	varForm.submit();
}

/* ********************************************************
* 선택 처리 함수
******************************************************** */
function fn_aram_choose(schdulId, schdulNm){
	if(window.opener.gArguments["schdulId"]) window.opener.gArguments["schdulId"].value = schdulId;
	if(window.opener.gArguments["schdulNm"]) window.opener.gArguments["schdulNm"].value = schdulNm;
	window.close();
}
 
function fn_aram_close(){
	window.close();
}

</script>
 
 </body>
 </html>
