<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : DeptSearchPopup.java
  * @Description : 부서 조회 팝업
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
<title>부서 조회 팝업</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>부서 조회 팝업</h2>
</div>

<form:form modelAttribute="deptVO" method="post">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;">종료</a></span>
	</div>
	<div class="keyword_area">
 		부서 명 : 
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />

<table class="table-list" summary="부서를 검색한 결과를 보여주는 테이블입니다.부서 ID,부서 명의 정보를 담고 있습니다.">
<thead>
  	<tr>
    	<th scope="col" width="3%" ></th>  
    	<th scope="col" width="15%">부서 ID</th>
    	<th scope="col" width="25%">부서 명</th>
  	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
    	<td class="lt_text3"  colspan="3"><spring:message code="common.nodata.msg" /></td>
  	</tr>
 	</c:if>
 	
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_choose('<c:out value="${result.orgnztId}"/>', '<c:out value="${result.orgnztNm}"/>'); return false;">
  	
    	<td class="lt_text3"></td>
    	<td class="lt_text"><c:out value="${result.orgnztId}"/></td>
    	<td class="lt_text"><c:out value="${result.orgnztNm}"/></td>
  	</tr>
 	</c:forEach>
</tbody> 
</table>

</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" defer="defer">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("deptAuthorVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDept.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("deptAuthorVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDept.do";
    varForm.submit();
}

function fn_aram_choose(deptCode, deptNm) {
	if( window.opener.gArguments["deptCode"] ) window.opener.gArguments["deptCode"].value = deptCode;
	if( window.opener.gArguments["deptNm"] )   window.opener.gArguments["deptNm"].value = deptNm;
	window.close();
}

function fn_aram_close(){
	window.close();
}

</script>

</body>
</html>
