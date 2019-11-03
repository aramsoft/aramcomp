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
  * @Class Name : QustnrQestnListPopup.jsp
  * @Description : 설문문항 목록 팝업
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
<title>설문문항 목록 팝업</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>설문문항 목록 팝업</h2>
</div>

<form:form commandName="qustnrQestnManageVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="QESTN_CN" label="질문내용" />			   
	   		<form:option value="MXMM_CHOISE_CO" label="최대선택건수" />			   
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

<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
  	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col"            >질문내용</th>
	    <th scope="col" width="20%">질문유형</th>
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
	
   	<c:set var="searchVO" value="${qustnrQestnManageVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_choose('${result.qestnrQesitmId}', '${result.qestnCn}', '${result.qestnTyCode}'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3L"><c:out value="${result.qestnCn}"/></td>
	    <td class="lt_text3">
	    	<c:if test="${result.qestnTyCode == '1'}">객관식</c:if>
	    	<c:if test="${result.qestnTyCode == '2'}">주관식</c:if>
	    </td>
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
    var varForm = document.getElementById("qustnrQestnManageVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qqm/listQustnrQestnPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("qustnrQestnManageVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qqm/listQustnrQestnPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 선택 처리 함수
 ******************************************************** */
function fn_aram_choose(qestnrQesitmId, qestnCn, qestnTyCode){
	if(window.opener.gArguments["qestnrQesitmId"]) window.opener.gArguments["qestnrQesitmId"].value = qestnrQesitmId;
	if(window.opener.gArguments["qestnCn"])        window.opener.gArguments["qestnCn"].value = qestnCn;
	if(window.opener.gArguments["divQestnTyCode"]) {
		if(qestnTyCode == '1')
			window.opener.gArguments["divQestnTyCode"].innerText = '객관식';
		else if(retVal.qestnTyCode == '2')
			window.opener.gArguments["divQestnTyCode"].innerText = '주관식';
	}
	window.close();
}

</script>

</body>
</html>

