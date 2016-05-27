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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴관리목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="menuManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="menuNo" value="0"/>
<input name="checkedMenuNoForDel" type="hidden" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_batch(); return false;">일괄등록</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
  		<label for="searchKeyword">메뉴명</label>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<table class="table-list" style="table-layout:fixed" summary="메뉴관리 목록 조회화면으로 메뉴ID,메뉴한글명,프로그램파일명,메뉴설명,상위메뉴ID로 구성.">
<caption>메뉴관리 목록 조회</caption>
<thead>
  	<tr>
     	<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="5%" >
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택" />
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
	
 	<c:set var="searchVO" value="${menuManageVO.searchVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text">
	       	<input type="checkbox" name="checkField" class="check2" title="선택"/>
	       	<input name="checkMenuNo" type="hidden" value="<c:out value='${result.menuNo}'/>" disabled />
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

<form:hidden path="searchVO.pageIndex" />
</form:form>

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
    var varForm = document.getElementById("menuManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("menuManageVO");
    varForm["searchVO.pageIndex"].value = 1;
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
    var varForm = document.getElementById("menuManageVO");
    var checkField = varForm.checkField;
    var menuNo = varForm.checkMenuNo;
    var checkMenuNos = "";
    var checkedCount = 0;

    if(checkField) {
    	if(checkField.length> 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkMenuNos += ((checkedCount==0? "" : ",") + menuNo[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkMenuNos = menuNo.value;
            }
        }
    }
    if(checkMenuNos.length ==0){
		alert("선택된 메뉴가 없습니다.");
		return false;
    }

    varForm.checkedMenuNoForDel.value=checkMenuNos;
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/deleteListMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fnCheckAll() {
    var varForm = document.getElementById("menuManageVO");
    var checkField = varForm.checkField;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = varForm.checkAll.checked;
        }
    } else {
        checkField.checked = varForm.checkAll.checked;
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

