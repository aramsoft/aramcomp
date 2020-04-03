<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmmntyMngrListPop.jsp
  * @Description : 커뮤니티 관리자 목록
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
<title>커뮤니티 관리자 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>승인자 선택</h2>
</div>

<form:form modelAttribute="userInfVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="targetMethod" value="${targetMethod}" />

<input type="hidden" name="PopFlag" value="S" />
<input type="hidden" name="cmmntyId" value="${userInfVO.trgetId}" />
<input type="hidden" name="emplyrId" />

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list">
<thead>
	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col" width="30%">사용자아이디</th>
	    <th scope="col" width="40%">사용자명</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="3"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="startIndex" value="${(userInfVO.pageIndex-1) * userInfVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${userInfVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_choose('<c:out value="${result.userId}"/>'); return false;">
	    		<c:out value="${result.userId}" />
	   		</a>
	   		</span>
	    </td>
	    <td class="lt_text3"><c:out value="${result.userNm}" /></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;
	var actionUrl;

    if (_target == 'selectCmmntyMngrList') {
		actionUrl = "${pageContext.request.contextPath}/cop/com/listCmmntyMngr.do";
	} else if (_target == 'selectCmmntyUserList') {
		actionUrl = "${pageContext.request.contextPath}/cop/com/listCmmntyUser.do";
	} else {
		actionUrl = "${pageContext.request.contextPath}/cop/com/listUser.do";
	}
	varForm.pageIndex.value = pageNo;
	varForm.action = actionUrl;
	varForm.submit();
}

function fn_aram_choose(userId) {
    var varForm = document.getElementById("userInfVO");
    varForm.emplyrId.value = userId;
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/deleteCmmntyUserBySelf.do";
    varForm.submit();
}

function fn_aram_close(){
	window.close();
}

</script>

</body>
</html>
