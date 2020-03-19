<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MenuCreateList.jsp
  * @Description : 메뉴생성 목록
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
<title>메뉴생성 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴생성관리</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="menuCreateVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input name="authorCode" type="hidden" />

<div id="search_area">
	<div class="button_area">
    	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
   		<label for="searchKeyword">보안설정대상ID</label>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="메뉴생성관리  목록화면으로 권한코드, 권한명, 권한설명, 메뉴생성여부, 메뉴생성으로 구성됨">
<caption>메뉴생성관리 목록 </caption>
	<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">권한코드</th>
	    <th scope="col" width="15%">권한명</th>
	    <th scope="col" 		   >권한 설명</th>
	    <th scope="col" width="7%" >메뉴생성<br>여부</th>
	    <th scope="col" width="15%">메뉴생성</th>
	    <th scope="col" width="15%">사이트맵생성</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

 	<c:set var="startIndex" value="${(menuCreateVO.pageIndex-1) * menuCreateVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${menuCreateVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.authorCode}"/></td>
	    <td class="lt_text3"><c:out value="${result.authorNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.authorDc}"/></td>
	    <td class="lt_text3">
	    	<c:if test="${result.chkYeoBu> 0}">Y</c:if>
	        <c:if test="${result.chkYeoBu == 0}">N</c:if>
	    </td>
	    <td class="lt_text3" style="height:30px;">
   	   		<span class="button">
   	   		<a href="#"  onclick="fn_aram_create_menu('<c:out value="${result.authorCode}"/>'); return false;">
   	   			메뉴생성
   	   		</a>
	    	</span>
	    </td>
	    <td class="lt_text3">
   	   		<span class="button">
    	   	<a href="#"  onclick="fn_aram_create_siteMap('<c:out value="${result.authorCode}"/>'); return false;">
    	   		사이트맵생성
    	   	</a>
    	   	</span>
   		</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

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
    var varForm = document.getElementById("menuCreateVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/listMenuCreate.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("menuCreateVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/listMenuCreate.do";
    varForm.submit();
}

/* ********************************************************
 * 메뉴생성 화면 호출
 ******************************************************** */
function fn_aram_create_menu(authorCode) {
    var varForm = document.getElementById("menuCreateVO");
    varForm.authorCode.value = authorCode;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/detailMenuCreate.do";
    varForm.submit();
}

/* ********************************************************
 * 사이트맵생성 화면 호출
 ******************************************************** */
function fn_aram_create_siteMap(authorCode) {
    var varForm = document.getElementById("menuCreateVO");
    varForm.authorCode.value = authorCode;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/detailMenuCreateSiteMap.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("메뉴생성관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>

