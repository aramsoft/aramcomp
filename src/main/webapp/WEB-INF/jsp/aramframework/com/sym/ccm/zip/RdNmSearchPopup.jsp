<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : RdNmSearchPopup.jsp
  * @Description : 
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
<title>도로명 찾기</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body >
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>도로명 찾기 (참조자료:2013년 11월 30일 기준, 대표지번 5,976,051건, 관련지번 1,967,927건)</h2>
</div>

<form:form commandName="zipVO"  action="" method="post">

<div id="search_area">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
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

<table class="table-list" summary="도로명 검색 결과를 알려주는 테이블입니다.우편번호 및 주소 내용을 담고 있습니다">
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="13%">우편번호</th>
		<th scope="col" width="40%">도로명주소</th>
		<th scope="col"            >지번주소</th>
	</tr>
</thead>
<tbody>
<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="startIndex" value="${(zipVO.pageIndex-1) * zipVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_choose('${result.zip}', '${result.addr1}', '${result.addr2}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${zipVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value='${fn:substring(result.zip, 0,3)}-${fn:substring(result.zip, 3,6)}'/></td>
		<td class="lt_text">${result.addr1}</td>
		<td class="lt_text">${result.addr2}</td>
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
   	var varForm = document.getElementById("zipVO");
   	varForm.pageIndex.value = pageNo;
   	varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/listRdNmPopup.do";
   	varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
   	var varForm = document.getElementById("zipVO");
	varForm.pageIndex.value = 1;
   	varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/listRdNmPopup.do";
   	varForm.submit();
}

/* ********************************************************
 * 결과 우편번호,주소 반환
 ******************************************************** */
function fn_aram_choose(zip,addr,addr2){
	var sZip     = zip;
	var vZip     = zip.substring(0,3)+'-'+zip.substring(3,6);
	var sAddr    = addr.replace(/^\s+|\s+$/g,'');
//	var sAddr2   = addr2.replace(/^\s+|\s+$/g,'');
	window.opener.gZipCode["sZip"].value = sZip;
	window.opener.gZipCode["vZip"].value = vZip;
	window.opener.gZipCode["sAddr"].value = sAddr;
	window.close();
}

</script>

</body>
</html>


