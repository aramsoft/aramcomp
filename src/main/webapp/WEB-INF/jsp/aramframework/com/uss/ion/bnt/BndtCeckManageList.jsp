<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BndtCeckManageList.jsp
 * @Description : 당직체크 목록
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
<title>당직체크 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직체크관리 목록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
</div>

<form:form commandName="bndtCeckManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="bndtCeckSe">
<input type="hidden" name="bndtCeckCd">

<table class="table-register" summary="당직체크관리 검색조건">
<caption>당직체크관리 검색조건</caption>
	<tr>
	    <th width="20%">
			<span class="norequired_icon"></span>
	    	<label for="searchBndtCeckSe">당직구분</label>
	    </th>          
	    <td width="30%">
        	<form:select path="searchBndtCeckSe" title="당직구분">
                <form:option value="" label="전체"/>
                <form:options items="${COM071_bndtCeckSe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
	    </td>
	    <th width="20%">
			<span class="norequired_icon"></span>
	    	<label for="searchKeyword">당직체크코드명</label>
	    </th>          
	    <td width="30%">
   			<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
        </td>
	</tr> 
	<tr>
	    <th>
			<span class="norequired_icon"></span>
	    	<label for="searchUseAt">사용여부</label>
	    </th>          
	    <td colspan=3>
	        <form:select path="searchUseAt" title="사용여부">
		       	<form:option value=""  label="전체" />
		       	<form:option value="Y" label="사용" />
		       	<form:option value="N" label="미사용" />
	      	</form:select>
	    </td>
	</tr> 
</table>

<form:hidden path="searchVO.searchCondition" value="1" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<div style="margin-top:3px; width:100%"></div>

<table class="table-list" summary="이 표는 당직체크관리리스트의 정보를 제공하며 당직체크구분, 당직체크코드, 당직체크코드명, 사용여부로 구성되어 있습니다.">
<caption>당직체크관리 목록</caption>
<thead>
	<tr>  
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="20%">당직체크구분</th>
		<th scope="col" width="30%">당직체크코드</th>
		<th scope="col"            >당직체크코드명</th>
		<th scope="col" width="10%">사용여부</th>
	</tr>
</thead>     
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<c:set var="searchVO" value="${bndtCeckManageVO.searchVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.bndtCeckSe}"/>','<c:out value="${result.bndtCeckCd}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.bndtCeckSeNm}"/></td>
		<td class="lt_text3"><c:out value="${result.bndtCeckCd}"/></td>
		<td class="lt_text3"><c:out value="${result.bndtCeckCdNm}"/></td>
		<td class="lt_text3">
			<c:if test="${result.useAt == 'Y'}">사용</c:if>
			<c:if test="${result.useAt == 'N'}">미사용</c:if>
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
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm["searchVO.pageIndex"].value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtCeckManage.do";
	varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
function fn_aram_search(){
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtCeckManage.do";
	varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(bndtCeckSe, bndtCeckCd){
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.bndtCeckSe.value = bndtCeckSe;
	varForm.bndtCeckCd.value = bndtCeckCd;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/detailBndtCeckManage.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.bndtCeckSe.value = "";
	varForm.bndtCeckCd.value = "";
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/registBndtCeckManage.do";
	varForm.submit();
}

</script>

</body>
</html>
