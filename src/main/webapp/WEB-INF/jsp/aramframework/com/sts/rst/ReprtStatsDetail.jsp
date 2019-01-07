<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : ReprtStatsDetail.jsp
  * @Description : 보고서통계 상세조회
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
<title>보고서통계 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>보고서통계 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
     	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="reprtStatsVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 검색조건 유지 -->
<form:hidden path="pmReprtTy" />
<form:hidden path="pmDateTy" />
<form:hidden path="pmFromDate" />
<form:hidden path="pmToDate" />
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<!-- 검색조건 유지 -->
</form:form>

<table class="table-list" summary="보고서통계에 대한 상세정보 목록을 제공한다.">
<thead>
  	<tr>
	    <th width="3%"></th>
	    <th width="15%">보고서ID</th>
	    <th width="30%">보고서명</th>
	    <th width="20%">보고서유형</th>
	    <th width="10%">진행상태</th>
	    <th width="20%">등록일시</th>
	    <th width="2%"></th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(reprtStats) == 0}">
	<tr>
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<c:forEach var="reprt" items="${reprtStats}" varStatus="status">
	<tr>
	    <td class="lt_text3"></td>
	    <td class="lt_text3"><c:out value="${reprt.reprtId}"/></td>
	    <td class="lt_text3"><c:out value="${reprt.reprtNm}"/></td>
	    <td class="lt_text3"><c:out value="${reprt.reprtTyNm}"/></td>
	    <td class="lt_text3"><c:out value="${reprt.reprtSttusNm}"/></td>
	    <td class="lt_text3"><c:out value="${reprt.regDate}"/></td>
	    <td class="lt_text3"></td>
	</tr>
	</c:forEach>
</tbody>
</table>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("reprtStatsVO");
    varForm.action = "${pageContext.request.contextPath}/sts/rst/listReprtStats.do";
    varForm.submit();
}

</script>

</body>
</html>

