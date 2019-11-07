<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BkmkMenuList.jsp
  * @Description : 바로가기메뉴 목록
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
<title>바로가기 메뉴 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>바로가기 메뉴관리</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="bkmkMenuManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_preview(); return false;"><spring:message code="button.preview" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
  		<label for="searchKeyword">메뉴명</label>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<table class="table-list" id="tblData" summary="바로가기메뉴관리 목록조회">
<thead>
	<tr>
     	<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="5%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
	    </th>
	    <th scope="col" width="20%">메뉴명</th>
	    <th scope="col"            >메뉴URL</th>
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
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${bkmkMenuManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.menuId}" />
	   	</td>
	   	
	    <td class="lt_text3">
	    	<span><c:out value="${result.menuNm}" escapeXml="false"/></span>
	    </td>
	    <td class="lt_text"><c:out value="${result.progrmStrePath}"/></td>
  	</tr>
	</c:forEach>
</tbody>
</table>

<form:hidden path="searchCondition" value="0" />
<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

$(function() {
	$("#checkAll").on("click", function(){
		if( $(this).is(":checked") ){
			$("#tblData input[name=uniqIds]").prop("checked", true);
		}else{
			$("#tblData input[name=uniqIds]").prop("checked", false); 
		}
	});
});

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("bkmkMenuManageVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/listBkmkMenu.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("bkmkMenuManageVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/listBkmkMenu.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("bkmkMenuManageVO");
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/registBkmkMenu.do";
    varForm.submit();
}

var gArguments = new Array();

function fn_aram_preview(){
	gArguments["retFunc"] = fn_aram_preview_bookmark;
	
	var url = "/sym/mnu/bmm/previewBkmkMenuPopup.do";

	window.open(url, "p_bookmark", "width=400px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_preview_bookmark(progrmStrePath){
    var varForm = document.getElementById("bkmkMenuManageVO");
	varForm.action = progrmStrePath;
	varForm.submit();
}

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fn_aram_deleteList(){
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
    
    var varForm = document.getElementById("bkmkMenuManageVO");
    if(confirm("삭제하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/deleteListBkmkMenu.do";
		varForm.submit();
    }
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("바로가기메뉴관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
