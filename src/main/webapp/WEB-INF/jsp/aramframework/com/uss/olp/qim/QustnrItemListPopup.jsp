<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QustnrItemListPopup.jsp
  * @Description : 설문항목 목록
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
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>설문항목 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>설문항목 목록</h2>
</div>

<form:form commandName="qustnrItemManageVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="IEM_CN" label="설문항목" />			   
	   		<form:option value="FRST_REGISTER_ID" label="등록자" />			   
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

<table class="table-list" summary="이 표는 설문항목 목록 정보를 제공하며, 순번, 항목내용, 기타답변여부, 등록자, 등록일자 정보로 구성되어 있습니다 .">
<thead>
	<tr>
		<th scope="col" width="10%">순번</th>
		<th scope="col"            >항목내용</th>
		<th scope="col" width="20%">기타답변여부</th>
		<th scope="col" width="10%">등록자</th>
		<th scope="col" width="10%">등록일자</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="searchVO" value="${qustnrItemManageVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_choose('${result.qustnrIemId}', '${result.qustnrIemCn}'); return false;">
 	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3L"><c:out value="${result.qustnrIemCn}"/></td>
		<td class="lt_text3">${result.etcAnswerAt}</td>
		<td class="lt_text3">${result.frstRegisterNm}</td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
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
    var varForm = document.getElementById("qustnrItemManageVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qim/listQustnrItemPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("qustnrItemManageVO");

	if(varForm.searchKeyword.value == ""){
		alert('검색어를 입력해주세요!');
		varForm.searchKeyword.focus();
		return;
	}

	if(varForm.searchCondition.selectedIndex == 0){
		alert('검색 구분을 선택해주세요!');
		varForm.searchCondition.focus();
		return;
	}

    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qim/listQustnrItem.do";
    varForm.submit();
}

function fn_aram_choose(qustnrIemId, qustnrIemCn){
	if(window.opener.gArguments["qustnrIemId"]) window.opener.gArguments["qustnrIemId"].value = qustnrIemId;
	if(window.opener.gArguments["qustnrIemCn"]) window.opener.gArguments["qustnrIemCn"].value = qustnrIemCn;
	window.close();
}

</script>

</body>
</html>

