<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TemplatePopup.jsp
  * @Description : 템플릿 목록 조회 팝업화면
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
<title>템플릿 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">   	      

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>템플릿 목록</h2>
</div>

<form:form modelAttribute="templateInfVO" action ="" method="post">
<input type="hidden" name="tmplatId" value="" />
<input name="typeFlag" type="hidden" value="<c:out value='${typeFlag}'/>"/>

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${templateInfVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<form:select path="searchCondition" class="select" title="검색조건선택">
				<form:option value="" label="--선택하세요--" />
			   	<form:option value="TMPLAT_NM" label="템플릿명" />
			   	<form:option value="TMPLAT_SE_CODE" label="템플릿구분" />
		   	</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="번호, 템플릿명, 템플릿구분, 템플릿경로, 사용여부, 등록일자, 선택   목록입니다">
 <thead>
	<tr>
	    <th scope="col" width="5%" >번호</th>
	    <th scope="col" width="15%">템플릿명</th>
	    <th scope="col" width="10%">템플릿구분</th>
	    <th scope="col" width="47%">템플릿경로</th>
	    <th scope="col" width="5%">사용여부</th>
	    <th scope="col" width="10%">등록일자</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="startIndex" value="${(templateInfVO.pageIndex-1) * templateInfVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${templateInfVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
			<c:if test="${result.useAt == 'Y'}">
		   		<span class="link">
		   		<a href="#" onclick="javascript:fn_aram_choose('<c:out value="${result.tmplatId}"/>'); return false;">
					<c:out value="${result.tmplatNm}"/>
		   		</a>
		   		</span>
			</c:if>	    	
			<c:if test="${result.useAt == 'N'}">
					<c:out value="${result.tmplatNm}"/>
			</c:if>	    	
	    </td>
	    <td class="lt_text3"><c:out value="${result.tmplatSeCodeNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.tmplatCours}"/></td>
	    <td class="lt_text3">
	    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    </td>
		<td class="lt_text3"><c:out value="${result.frstRegisterPnttm}"/></td	>
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

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("templateInfVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplatePopup.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("templateInfVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplatePopup.do";
    varForm.submit();
}

function fn_aram_choose(tmplatId, tmplatNm){
	if( window.opener.gArguments["tmplatId"] ) window.opener.gArguments["tmplatId"].value = tmplatId;
	if( window.opener.gArguments["tmplatNm"] ) window.opener.gArguments["tmplatNm"].value = tmplatNm;
	window.close();
}

</script>

</body>
</html>
