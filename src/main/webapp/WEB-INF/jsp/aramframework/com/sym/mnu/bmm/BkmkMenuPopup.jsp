<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BkmkMenuPopup.jsp
  * @Description : 바로가기메뉴 등록을 위한 메뉴목록
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
<title>메뉴목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:95%;">

<div class="content_title">
	<h2>메뉴 목록</h2>
</div>

<form:form commandName="bkmkMenuManageVO" action="" method="post">
<input type="hidden" name="PopFlag" value="Y">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
	<div class="keyword_area">
  		<label for="searchKeyword">메뉴명</label>
   		<span class="required_icon"></span>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<table class="table-list" summary="바로가기 메뉴관리에 등록할 메뉴의 조회">
<thead>
  	<tr>
    	<th width="10%">번호</th>
    	<th width="15%">메뉴명</th>
    	<th width="15%">메뉴DC</th>
  	</tr>
 </thead>
 <tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(bkmkMenuManageVO.pageIndex-1) * bkmkMenuManageVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onClick="javascript:fn_aram_choose('<c:out value="${result.menuId}" />','${result.menuNm}');return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${bkmkMenuManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.menuNm}" /></td>
	    <td class="lt_text3"><c:out value="${result.menuDc}" /></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<form:hidden path="searchVO.searchCondition" value="0" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

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

function fn_aram_linkPage(pageIndex){
    var varForm = document.getElementById("bkmkMenuManageVO");
    varForm["searchVO.pageIndex"].value = pageIndex;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/listBkmkMenuPopup.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("bkmkMenuManageVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/listBkmkMenuPopup.do";
    varForm.submit();
}

function fn_aram_choose(menuId, menuNm){
	var retFunc = window.opener.gArguments["retFunc"];
	if( retFunc != undefined ) {
		retFunc(menuId, menuNm);		
	}
	window.close();
}

function fn_aram_close(){
	window.close();
}

</script>

</body>
</html>
