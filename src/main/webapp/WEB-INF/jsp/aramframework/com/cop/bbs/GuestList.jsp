<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="aramframework.com.cmm.constant.AramProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : GuestList.jsp
  * @Description : 방명록
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
<title>방명록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main"> 

<div class="content_title">
	<h2><spring:message code="cop.guestList" /></h2>
</div>

<form:form commandName="boardVO" method="post">
<form:hidden path="bbsId" />
<form:hidden path="nttId" />

<input type="hidden" name="nttSj" value='<spring:message code="cop.guestList.subject" />' />
<input type="hidden" name="ntceBgnde" value="10000101" />
<input type="hidden" name="ntceEndde" value="99991231" />
<input type="hidden" name="password" value="dummy" /> <!-- validator 처리를 위해 지정 -->
<input name="guestModified" type="hidden" value="false">

<table class="table-list">
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3"  colspan="2"><spring:message code="common.nodata.msg" /></td>
  	</tr>
	</c:if>
	
 	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td class="lt_text">
		    <c:choose>
		    <c:when test="${not empty result.ntcrNm}">
		    <b><c:out value="${result.ntcrNm}" /></b>&nbsp;
		    </c:when>
		    <c:otherwise>
		    <b><c:out value="${result.frstRegisterNm}" /></b>&nbsp;
		    </c:otherwise>
		    </c:choose>
		    
		    <fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
		    <c:out value="${result.nttCn}" />
		</td>
		<td class="lt_text">
		    <c:if test="${result.frstRegisterId == sessionUniqId}">
		    <a href="#" onclick="javascript:fn_aram_edit_guestList('<c:out value="${result.nttId}" />'); return false;"><spring:message code="button.update" /></a>
		      | <a href="#" onclick="javascript:fn_aram_delete_guestList('<c:out value="${result.nttId}" />'); return false;"><spring:message code="button.delete" /></a>
		    </c:if>
		</td>
	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

<div style="margin-top:10px; width:100%"></div>

<div id="search_area">
	<div class="button_area">
		<c:choose>
		<c:when test="${boardVO.nttId == '0'}">
     		<span class="button"><a href="#" onclick="javascript:fn_aram_insert_guestList(); return false;"><spring:message code="button.create" /></a></span>
		</c:when>
		<c:otherwise>
     		<span class="button"><a href="#" onclick="javascript:fn_aram_update_guestList(); return false;"><spring:message code="button.update" /></a></span>
		</c:otherwise>
		</c:choose>
     	<span class="button"><a href="#" onclick="javascript:fn_aram_reset_guestList(); return false;">초기화</a></span>
	</div>
</div>

<table class="table-register">
	<tr>
	    <th width="20%">
			<span class="norequired_icon"></span>
	    	<spring:message code="cop.ntcrNm" />
	    </th>
	    <td>
	      	<input name="ntcrNm" type="text" size="20" value='<c:out value="${boardVO.ntcrNm}" />' maxlength="20" title="작정자이름" readonly="readonly">
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.guestListCn" />
	    </th>
	    <td>
	      	<textarea name="nttCn" class="textarea"  cols="50" rows="4"  style="width:100%;" title="방명록내용입력"><c:out value="${boardVO.nttCn}" /></textarea>
	      	<form:errors path="nttCn" cssClass="error"/>
	    </td>
	</tr>
</table>

<form:hidden path="searchVO.pageIndex" />
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("boardVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/selectGuestList.do";
    varForm.submit();
}

function fn_aram_reset_guestList(pageNo) {
    var varForm = document.getElementById("boardVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/selectGuestList.do";
    varForm.submit();
}

function fn_aram_insert_guestList() {
   var varForm = document.getElementById("boardVO");
   
	if (!validateBoardVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.guestModified.value = "true";
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertGuestList.do";
		varForm.submit();
	}
}

function fn_aram_edit_guestList(nttId) {
    var varForm = document.getElementById("boardVO");
	varForm.guestModified.value = "false";
    varForm.nttId.value = nttId;
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/editGuestList.do";
    varForm.submit();
}

function fn_aram_update_guestList() {
    var varForm = document.getElementById("boardVO");
    
	if (!validateBoardVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.guestModified.value = "true";
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateGuestList.do";
		varForm.submit();
	}
}

function fn_aram_delete_guestList(nttId) {
	var varForm = document.getElementById("boardVO");
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.guestModified.value = "true";
		varForm.nttId.value = nttId;
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/deleteGuestList.do";
		varForm.submit();
	}
}

</script>

</body>
</html>
