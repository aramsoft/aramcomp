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
  * @Class Name : RssServiceList.jsp
  * @Description : RSS서비스 목록
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
<title>RSS서비스 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>RSS서비스 목록</h2>
</div>

<form:form commandName="rssInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="rssId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
		<label for="searchCondition"> </label>
     	<form:select path="searchVO.searchCondition" title="조회조건 선택" class="select" >
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="TRGET_SVC_NM" label="대상서비스명" />			   
	   		<form:option value="TRGET_SVC_TABLE" label="대상테이블명" />			   
   		</form:select>
   		
		<label for="searchKeyword"> </label>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<!-- 목록  -->
<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
  	<tr> 
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >대상서비스명</th>
	    <th scope="col" width="20%">대상테이블명</th>
	    <th scope="col" width="10%">작성자</th>
	    <th scope="col" width="15%">등록일자</th>                 
  	</tr>
</thead> 
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(rssInfoVO.searchVO.pageIndex-1) * rssInfoVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.rssId}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${rssInfoVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.trgetSvcNm}"/></td>
		<td class="lt_text3"><c:out value="${result.trgetSvcTable}"/></td>
		<td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
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
    var varForm = document.getElementById("rssInfoVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rsn/listRssService.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("rssInfoVO");

	if( varForm.searchCondition.selectedIndex == 0){
		alert("검색조건을 선택 해주세요!");
		varForm.searchCondition.focus();
		return;
	}
	
	if( varForm.searchKeyword.value == ""){
		alert("검색어를 입력 해주세요!");
		varForm.searchKeyword.focus();
		return;
	}  
	
    varForm["searchVO.pageIndex"].value = "1";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rsn/listRssService.do";
	varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(rssId){
    var varForm = document.getElementById("rssInfoVO");
    varForm.rssId.value = rssId; 
    varForm.target="_blank";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rsn/detailRssService.do";
    varForm.submit();
}

</script>

</body>
</html>