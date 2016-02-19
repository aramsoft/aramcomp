<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MeetingDeptPopup.jsp
  * @Description : 부서 검색 팝업
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
<title>부서 검색 팝업</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>부서 목록</h2>
</div>

<form:form commandName="baseVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="ORGNZT_NM" label="부서명" />			   
	   		<form:option value="ORGNZT_DC" label="부서설명" />			   
   		</form:select>
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

<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
	<tr>  
    	<th scope="col" width="10%">순번</th>
    	<th scope="col" width="30%">부서명</th>
    	<th scope="col"            >부서설명</th>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="3"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(baseVO.searchVO.pageIndex-1) * baseVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_choose('${result.orgnztId}', '${result.orgnztNm}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<td class="lt_text3"><c:out value="${index}"/></td>

		<td class="lt_text3L">${result.orgnztNm}</td>
		<td class="lt_text3L">${result.orgnztDc}</td>
  	</tr>   
	</c:forEach>
</tbody>  
</table>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("searchVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/olp/mgt/listMeetingDeptPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 선택 처리 함수
 ******************************************************** */
function fn_aram_choose(deptId, deptNm){
	if( window.opener.gArguments["deptId"] ) window.opener.gArguments["deptId"].value = deptId;
	if( window.opener.gArguments["deptNm"] ) window.opener.gArguments["deptNm"].value = deptNm;
	window.close();
}

</script>

</body>
</html>
