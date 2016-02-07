<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : DeptJobBxListPopup.jsp
 * @Description : 부서 업무함 목록
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
<title>부서 업무함 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body >
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>부서업무함 목록</h2>
</div>

<form:form commandName="deptJobBxVO" action ="" method="post">

<input type="hidden" name="PopFlag" value="Y" />
	
<div id="search_area">
	<div class="button_area">
      	<span class="button"><input type="submit" value="조회" onclick="fn_aram_search(); return false;"></span>
	</div>
	<div class="keyword_area">
		<label for="searchCondition">조회조건 : </label>
  		<form:select path="searchCondition" class="select" title="조회조건 선택">
			<form:option value='' label="--선택하세요--" />
			<form:option value="ORGNZT_NM" label="부서명" />
			<form:option value="DEPT_JOBBX_NM" label="부서업무함명" />
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
		
<table class="table-list" summary="이 표는 부서업무함 정보를 제공하며, 부서명, 부서업무함명 정보로 구성되어 있습니다.">
<caption>부서업무함목록</caption> 
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="30%">부서명</th>
	    <th scope="col"            >부서업무함명</th>
  	</tr>
</thead>    
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3" colspan="3"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
 	<c:set var="startIndex" value="${(deptJobBxVO.pageIndex-1) * deptJobBxVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_choose('<c:out value="${result.deptId}"/>', '<c:out value="${result.deptNm}"/>','<c:out value="${result.deptJobBxId}"/>', '<c:out value="${result.deptJobBxNm}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${deptJobBxVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.deptNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.deptJobBxNm}"/></td>
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

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("deptJobBxVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJobBx.do";
    varForm.submit();	
}

function fn_aram_search() {
    var varForm = document.getElementById("deptJobBxVO");
    varForm.pageIndex.value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJobBx.do";
    varForm.submit();	
}

function fn_aram_choose(deptId, deptNm, deptJobBxId, deptJobBxNm) {
	if( window.opener.gArguments["deptId"] )      window.opener.gArguments["deptId"].value = deptId;
	if( window.opener.gArguments["deptNm"] )      window.opener.gArguments["deptNm"].value = deptNm;
	if( window.opener.gArguments["deptJobBxId"] ) window.opener.gArguments["deptJobBxId"].value = deptJobBxId;
	if( window.opener.gArguments["deptJobBxNm"] ) window.opener.gArguments["deptJobBxNm"].value = deptJobBxNm;
	window.close();
}

</script>

</body>
</html>
