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
  * @Class Name : CommentList.jsp
  * @Description : 댓글
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%;"> 

<div class="content_title">
	<h2>댓글</h2> - <c:out value="${commentVO.totalRecordCount}"/>개
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle;" alt="도움말아이콘이미지">
	</a>
</div>

<div style="margin-top:10px; width:100%;"></div>

<form:form modelAttribute="commentVO"  method="post" action="">
<form:hidden path="bbsId" />
<form:hidden path="nttId" />
<form:hidden path="commentNo" />

<input name="modified" type="hidden" value="false">
<input name="anonymous" type="hidden" value="${anonymous}">

<c:if test="${anonymous == 'true'}">
<input type="hidden" name="confirmPassword">
</c:if>

<%pageContext.setAttribute("crlf", "\r\n"); %>
<table class="table-list">
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3"  colspan="2">댓글이 없습니다.</td>
	</tr>
	</c:if>
	
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td class="lt_text" width="70%" style="padding-left:10px;">
	     	<div class="bbs_cn">
			   	<c:choose>
			   	<c:when test="${not empty result.wrterNm}">
			   		<b><c:out value="${result.wrterNm}" /></b>&nbsp;
			  	</c:when>
			  	<c:otherwise>
			  		<b><c:out value="${result.frstRegisterNm}" /></b>&nbsp;
			  	</c:otherwise>
			 	</c:choose>
				<fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
				<c:out value="${fn:replace(result.commentCn , crlf , '<br />')}" escapeXml="false" />
			</div>
		</td>
		<td class="lt_text3" style="padding-left:10px;">
			<c:if test="${anonymous == 'true' || result.wrterId == uniqId}">
		     <a href="#" onclick="javascript:fn_aram_edit_comment('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />'); return false;"><spring:message code="button.update" /></a>
		      | <a href="#" onclick="javascript:fn_aram_delete_comment('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />'); return false;"><spring:message code="button.delete" /></a>
		    </c:if>
		    <c:if test="${anonymous == 'true'}">
		    <br>패스워드 : <input name="testPassword" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
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

<c:if test="${ anonymous == 'true' || not empty uniqId }">

<div id="search_area">
	<div class="button_area">
		<c:choose>
		<c:when test="${commentVO.commentNo == ''}">
     		<span class="button"><a href="#" onclick="javascript:fn_aram_insert_comment(); return false;">댓글등록</a></span>
		</c:when>
		<c:otherwise>
     		<span class="button"><a href="#" onclick="javascript:fn_aram_update_comment(); return false;">댓글수정</a></span>
		</c:otherwise>
		</c:choose>
     	<span class="button"><a href="#" onclick="javascript:fn_aram_reset_comment(); return false;">댓글초기화</a></span>
	</div>
</div>

<table class="table-register">
	<tr>
		<th width="20%">
			<label for="wrterNm">작성자</label>
			<span class="required_icon"></span>
		</th>
	    <td >
	      	<input name="wrterNm" id="wrterNm" type="text" size="20" value='<c:out value="${commentVO.wrterNm}" />' maxlength="20" title="작성자이름입력">
	    </td>
	</tr>
	<tr>
		<th>
			<label for="commentCn">내용</label>(<span id="cnLength"></span>)
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<textarea name="commentCn" id="commentCn" class="textarea"  onkeyup="javascript:fn_check_length(); return false;" cols="50" rows="4"  style="width:90%;" title="댓글내용입력">
<c:out value="${commentVO.commentCn}" />
	      	</textarea>
	    </td>
	</tr>
	
	<c:choose>
	<c:when test="${anonymous == 'true'}">
		<tr>
			<th>
				<label for="commentPassword">패스워드</label>
			    <span class="required_icon"></span>
			</th>
			<td>
			    <input name="commentPassword" id="commentPassword" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
			</td>
		</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="commentPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
	</c:otherwise>
	</c:choose>
	
</table>

<script>
function fn_check_length() {
	var cnLength = document.getElementById("cnLength");
	var commentCn = document.getElementById("commentCn");
	cnLength.innerHTML = commentCn.value.length; 
}	
</script>

</c:if>

<form:hidden path="pageIndex" />
</form:form>

<c:if test="${not empty subMsg}">
<script>
	alert("<c:out value='${subMsg}'/>");
</script>
</c:if>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="commentVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("commentVO");
	varForm.pageIndex.value = pageNo;
	varForm.commentNo.value = '';
	varForm.action = "${pageContext.request.contextPath}/content/board/${commentVO.bbsId}/article/${commentVO.nttId}/comments";
	varForm.submit();
}

function fn_aram_reset_comment() {
    var varForm = document.getElementById("commentVO");
	varForm.pageIndex.value = 1;
	varForm.commentNo.value = '';
	varForm.action = "${pageContext.request.contextPath}/content/board/${commentVO.bbsId}/article/${commentVO.nttId}/comments";
	varForm.submit();
}

function fn_aram_insert_comment() {
    var varForm = document.getElementById("commentVO");
    
	if (!validateCommentVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.modified.value = "true";
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertComment.do";
		varForm.submit();
	}
}

function fn_aram_edit_comment(commentNo, index) {
    var varForm = document.getElementById("commentVO");
<c:if test="${anonymous == 'true'}">

	if (typeof(varForm.testPassword.length) == 'undefined') {
		password = varForm.testPassword;
	} else {
		password = varForm.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	varForm.confirmPassword.value = password.value;
</c:if>
	varForm.modified.value = "false";
	varForm.commentNo.value = commentNo;
	varForm.action = "${pageContext.request.contextPath}/content/board/${commentVO.bbsId}/article/${commentVO.nttId}/comments";
	varForm.submit();
}

function fn_aram_update_comment() {
    var varForm = document.getElementById("commentVO");
    
	if (!validateCommentVO(varForm)){
		return;
	}

<c:if test="${anonymous == 'true'}">
	varForm.confirmPassword.value = varForm.commentPassword.value;
</c:if>

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.modified.value = "true";
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateComment.do";
		varForm.submit();
	}
}

function fn_aram_delete_comment(commentNo, index) {
    var varForm = document.getElementById("commentVO");
<c:if test="${anonymous == 'true'}">

	if (typeof(varForm.testPassword.length) == 'undefined') {
		password = varForm.testPassword;
	} else {
		password = varForm.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	varForm.confirmPassword.value = password.value;
</c:if>

	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.modified.value = "true";
		varForm.commentNo.value = commentNo;
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/deleteComment.do";
		varForm.submit();
	}
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("댓글관리");	
	window.open(url, "도움말");
}
</script>

</body>
</html>


