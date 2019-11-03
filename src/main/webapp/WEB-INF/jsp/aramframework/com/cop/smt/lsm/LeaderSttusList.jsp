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
 * @Class Name : LeaderSttusList.jsp
 * @Description : 간부상태 목록
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
<title>간부상태 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>간부상태 목록</h2>
</div>

<form:form commandName="leaderSttusVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="leaderId" value="">
	
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" class="select" title="검색조건선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value='USER_NM' label="간부명" />
	   		<form:option value='LEADER_STTUS' label="간부상태" />
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="이 표는 간부상태 정보를 제공하며, 간부명, 간부상태, 최종수정자, 최종수정일자 정보로 구성되어 있습니다 .">
<caption>간부상태 목록</caption>
<thead>
	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col" width="30%">소속</th>
	    <th scope="col" width="30%">간부명</th>
	    <th scope="col" width="15%">간부상태</th>
	    <th scope="col" width="15%">최종수정일시</th>
	</tr>
</thead>    
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

  	<c:set var="searchVO" value="${leaderSttusVO}"/>
  	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.leaderId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.orgnztNm}"/></td>
		<td class="lt_text3"><c:out value="${result.leaderNm}"/></td>
		<td class="lt_text3"><c:out value="${result.leaderSttusNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>	  

</tbody>
</table>
	
<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage"	/>
</div>

</div>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_leaderSttus();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("leaderSttusVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSttus.do";
    varForm.submit();	
}

function fn_aram_search() {
    var varForm = document.getElementById("leaderSttusVO");
    varForm.pageIndex.value = 1; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSttus.do";
    varForm.submit();	
}

function fn_aram_detail(leaderId) {
    var varForm = document.getElementById("leaderSttusVO");
    varForm.leaderId.value = leaderId;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/editLeaderSttus.do";
    varForm.submit();	
}

function fn_aram_regist(){	
    var varForm = document.getElementById("leaderSttusVO");
    varForm.leaderId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/registLeaderSttus.do";
    varForm.submit();
}

</script>

</body>
</html>
