<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : AdressBookPopup.jsp
  * @Description : 주소록 구성원등록을 위한 사용자및 명함 목록화면
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
<title> 
	<c:if test="${adressBookUserVO.searchCondition  == 'USERLIST'}">사용자 목록</c:if>
	<c:if test="${adressBookUserVO.searchCondition  == 'NAMECARD'}">명함목록</c:if> 
</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>
	<c:if test="${adressBookUserVO.searchCondition == 'USERLIST'}">사용자 목록</c:if>
	<c:if test="${adressBookUserVO.searchCondition == 'NAMECARD'}">명함목록</c:if>
	</h2>
</div>

<form:form modelAttribute="adressBookUserVO" action ="" method="post">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${adressBookUserVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
		 	<form:select path="searchCondition" class="select" title="검색조건선택">
		   		<form:option value="" label="--선택하세요--" />
		   		<form:option value="USERLIST" label="사용자 목록" />
		   		<form:option value="NAMECARD" label="명함목록" />
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
	      	<span class="button"><a href="#" onclick="javascript:fn_aram_close(); return false;"><spring:message code="button.close" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="주소록 구성원에 대한 조회및 목록을 제공한다.">
<thead>
	<tr>
	    <th scope="col" width="5%" >번호</th>

	    <c:if test="${adressBookUserVO.searchCondition == 'USERLIST'}">
	  	<th scope="col" width="200px">사용자ID</th>
	  	<th scope="col" width="10%">사용자명</th>
	    </c:if>

	    <c:if test="${adressBookUserVO.searchCondition == 'NAMECARD'}">
	  	<th scope="col" width="200px">명함ID</th>
	  	<th scope="col" width="10%">명함명</th>
	    </c:if>

	  	<th scope="col" >이메일</th>
	  	<th scope="col" width="12%">집전화번호</th>
	  	<th scope="col" width="12%">휴대폰번호</th>
	    <th scope="col" width="12%">회사번호</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	   <td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

 	<c:set var="startIndex" value="${(adressBookUserVO.pageIndex-1) * adressBookUserVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${adressBookUserVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<c:if test="${adressBookUserVO.searchCondition == 'USERLIST'}">
		<td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_choose('<c:out value="${result.emplyrId}"/>'); return false;">
				<c:out value="${result.emplyrId}" />
			</a>
			</span>	
		</td>
		</c:if>
		
		<c:if test="${adressBookUserVO.searchCondition == 'NAMECARD'}">
		<td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_choose('<c:out value="${result.ncrdId}"/>'); return false;">
				<c:out value="${result.ncrdId}" />
			</a>
			</span>	
		</td>
		</c:if>

    	<td class="lt_text3"><c:out value="${result.nm}"/></td>
    	<td class="lt_text3"><c:out value="${result.emailAdres}"/></td>
    	<td class="lt_text3"><c:out value="${result.homeTelno}"/></td>
    	<td class="lt_text3"><c:out value="${result.moblphonNo}"/></td>
    	<td class="lt_text3"><c:out value="${result.offmTelno}"/></td>
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
    var varForm = document.getElementById("adressBookUserVO");
    varForm.pageIndex.value = pageIndex;
    varForm.action = "${pageContext.request.contextPath}/cop/adb/listUserPopup.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("adressBookUserVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/adb/listUserPopup.do";
    varForm.submit();
}

function fn_aram_choose(userId){
	var retFunc = window.opener.gArguments["retFunc"];
	if( retFunc != undefined ) {
		retFunc(userId);		
	}
	window.close();
}

function fn_aram_close(){
	window.close();
}

</script>

</body>
</html>
