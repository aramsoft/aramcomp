<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ProgramPopup.jsp
  * @Description : 프로그램 검색
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
<title>프로그램 검색</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body >
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>프로그램파일명 검색</h2>
</div>

<form:form commandName="progrmManageVO" action="" method="post">
<form:hidden path="searchUseAt"/>

<div id="search_area">
	<div class="button_area">
        <span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
  		<label for="searchKeyword">프로그램명</label>
  		<span class="required_icon"></span>
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

<div style="margin-top:10px;"></div>

<table class="table-list" style="table-layout:fixed" summary="프로그램파일명 검색목록으로 프로그램파일명 프로그램명으로 구성됨">
<caption>프로그램파일명 검색</caption>
<thead>
  	<tr>
    	<th scope="col" width="50%">프로그램파일명</th>
    	<th scope="col" width="50%">프로그램명</th>
  	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
    	<td class="lt_text3"  colspan="2"><spring:message code="common.nodata.msg" /></td>
  	</tr>
 	</c:if>

 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="fn_aram_choose('<c:out value="${result.progrmFileNm}"/>'); return false;">
  	
    	<td class="lt_text3"><c:out value="${result.progrmFileNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.progrmKoreanNm}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

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

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("progrmManageVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("progrmManageVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 프로그램목록 선택 처리 함수
 ******************************************************** */
function fn_aram_choose(vFileNm) {
	if(window.opener.gArguments["progrmFileNm"]) window.opener.gArguments["progrmFileNm"].value = vFileNm;
	window.close();
}

</script>

</body>
</html>

