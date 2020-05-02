<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : UserListPop.jsp
  * @Description : 사용자 목록
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
<title>사용자 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>사용자 목록</h2>
</div>

<form:form modelAttribute="userInfVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="targetMethod" value="${targetMethod}" />

<input type="hidden" name="PopFlag" value="Y" />

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${userInfVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<form:select path="searchCondition" class="select" title="검색조건선택">
				<form:option value="" label="--선택하세요--" />
			   	<form:option value="USER_NM" label="사용자명" />
		   	</form:select>
	  		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
			<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;"><spring:message code="button.close" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="번호, 사용자아이디, 사용자명, 주소, 이메일, 사용여부, 선택   목록입니다">
<thead>
	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col" width="15%">사용자아이디</th>
	    <th scope="col" width="15%">사용자명</th>
	    <th scope="col" width="23%">주소</th>
	    <th scope="col" width="12%">이메일</th>
	    <th scope="col" width="8%" >사용여부</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

 	<c:set var="startIndex" value="${(userInfVO.pageIndex-1) * userInfVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${userInfVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.userId}" /></td>
	    <td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_choose('<c:out value="${result.userNm}" />','<c:out value="${result.userId}" />'); return false;">
	    		<c:out value="${result.userNm}" />
	   		</a>
	   		</span>
	    </td>
	    <td class="lt_text3"><c:out value="${result.userAdres}" /></td>
	    <td class="lt_text3"><c:out value="${result.userEmail}" /></td>
	    <td class="lt_text3">
	    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	    </td>
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

function fn_aram_linkPage(pageIndex){
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	varForm.pageIndex.value = pageIndex;
	if(_target == 'selectCmmntyMngrList'){
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyMngr.do";
	}else if(_target == 'selectCmmntyUserList'){
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyUser.do";
	}else{
		varForm.action = "${pageContext.request.contextPath}/cop/com/listUser.do";
	}
	varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	varForm.pageIndex.value = 1;
	if(_target == 'selectCmmntyMngrList'){
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyMngr.do";
	}else if(_target == 'selectCmmntyUserList'){
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyUser.do";
	}else{
		varForm.action = "${pageContext.request.contextPath}/cop/com/listUser.do";
	}
	varForm.submit();
}

function fn_aram_choose(userNm, userId){
	if(window.opener.gArguments["userId"]) window.opener.gArguments["userId"].value = userId;
	if(window.opener.gArguments["userNm"]) window.opener.gArguments["userNm"].value = userNm;
	window.close();
}

function fn_aram_close(){
	window.close();
}

</script>

</body>
</html>
