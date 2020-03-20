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
  * @Class Name : SysHistList.jsp
  * @Description : 시스템 이력 목록
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
<title>시스템 이력 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>시스템이력 조회</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="sysHistoryVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input name="histId" type="hidden" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
 	   	<form:select path="searchCondition" class="select" title="검색유형선력">
		   	<form:option value="" label="--선택하세요--" />
		   	<form:option value="SYS_NM" label="시스템명" />
		   	<form:option value="CODE_NM" label="이력구분" />
	   	</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list">
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="25%">이력 ID</th>
	    <th scope="col"            >시스템명</th>
	    <th scope="col" width="15%">이력구분</th>
	    <th scope="col" width="10%">등록자</th>
	    <th scope="col" width="20%">등록일자</th>
  	</tr>
</thead>
<tbody>
 	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 	<c:if test="${fn:length(resultList) == 0}">
 	<tr>
 		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
 	</tr>
 	</c:if>
 	
  	<c:set var="startIndex" value="${(sysHistoryVO.pageIndex-1) * sysHistoryVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${sysHistoryVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.histId}"/>'); return false;">
	    		<c:out value="${result.histId}"/></td>
    		</a>
			</span>
	    <td class="lt_text3"><c:out value="${result.sysNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.histSeCodeNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage"	/>
</div>

</DIV>

<script type="text/javascript">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/listSysHistory.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/listSysHistory.do";
    varForm.submit();
}

function fn_aram_detail(histId){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.histId.value = histId;
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/detailSysHistory.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.histId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/registSysHistory.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("시스템_이력관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>