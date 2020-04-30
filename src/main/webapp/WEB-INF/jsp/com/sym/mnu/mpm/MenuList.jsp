<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MenuList.jsp
  * @Description : 메뉴 목록
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
<title>메뉴 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴관리목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="menuManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="menuNo" value="0"/>

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${menuManageVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		<label for="searchKeyword">메뉴명</label>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_batch(); return false;">일괄등록</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
		</span>
	</div>	
</div>

<table class="table-list" id="tblData" style="table-layout:fixed" summary="메뉴관리 목록 조회화면으로 메뉴ID,메뉴한글명,프로그램파일명,메뉴설명,상위메뉴ID로 구성.">
<caption>메뉴관리 목록 조회</caption>
<thead>
  	<tr>
     	<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="5%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
	    </th>
	    <th scope="col" width="10%">메뉴ID</th>
	    <th scope="col" width="25%">메뉴한글명</th>
	    <th scope="col"            >프로그램파일명</th>
	    <th scope="col" width="10%">상위메뉴ID</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(menuManageVO.pageIndex-1) * menuManageVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${menuManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.menuNo}" />
	    </td>
	    <td class="lt_text"><c:out value="${result.menuNo}"/></td>
	    <td class="lt_text" style="cursor:hand;">
	       	<span class="link">
	       	<a href="#" onclick="fn_aram_detail('<c:out value="${result.menuNo}"/>'); return false;">
	       		<c:out value="${result.menuNm}"/>
	       	</a>
	       	</span>
	    </td>
	    <td class="lt_text"><c:out value="${result.progrmFileNm}"/></td>
	    <td class="lt_text"><c:out value="${result.upperMenuId}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

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

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("menuManageVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("menuManageVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function fn_aram_detail(menuNo) {
	var varForm = document.getElementById("menuManageVO");
	varForm.menuNo.value = menuNo;
	varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/editMenu.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 화면 호출 함수
 ******************************************************** */
function fn_aram_regist() {
    var varForm = document.getElementById("menuManageVO");
    varForm.menuNo.value = 0;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/registMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 일괄처리 화면호출 함수
 ******************************************************** */
function fn_aram_batch() {
	var varForm = document.getElementById("menuManageVO");
	varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/registMenuBnde.do";
	varForm.submit();
}

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fn_aram_deleteList() {
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}
    
    var varForm = document.getElementById("menuManageVO");
    if(confirm("삭제하시겠습니까?")) {
   	 	varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/deleteListMenu.do";
    	varForm.submit();
    }	
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("메뉴관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>

