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
  * @Class Name : SatisfactionList.jsp
  * @Description : 만족도
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
 	<h2>만족도조사</h2> - <c:out value="${satisfactionVO.totalRecordCount}"/>개
 	&nbsp;&nbsp;&nbsp;(전체 :
 	<c:choose>
	<c:when test="${summary> 4.0}"><span title="<c:out value='${summary}'/>">★★★★★</span></c:when>
	<c:when test="${summary> 3.0}"><span title="<c:out value='${summary}'/>">★★★★☆</span></c:when>
	<c:when test="${summary> 2.0}"><span title="<c:out value='${summary}'/>">★★★☆☆</span></c:when>
	<c:when test="${summary> 1.0}"><span title="<c:out value='${summary}'/>">★★☆☆☆</span></c:when>
	<c:when test="${summary> 0.0}"><span title="<c:out value='${summary}'/>">★☆☆☆☆</span></c:when>
	<c:otherwise></c:otherwise>
 	</c:choose>)
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<div style="margin-top:10px; width:100%"></div>

<form:form modelAttribute="satisfactionVO"  method="post" action="">
<form:hidden path="bbsId" />
<form:hidden path="nttId" />
<form:hidden path="stsfdgNo" />

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
  		<td class="lt_text3"  colspan="2">만족도가 없습니다.</td>
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
	     		<c:choose>
	     		<c:when test="${result.stsfdg == '5'}"><span title="매우만족">★★★★★</span></c:when>
	     		<c:when test="${result.stsfdg == '4'}"><span title="만족">★★★★☆</span></c:when>
	     		<c:when test="${result.stsfdg == '3'}"><span title="보통">★★★☆☆</span></c:when>
	     		<c:when test="${result.stsfdg == '2'}"><span title="불만족">★★☆☆☆</span></c:when>
	     		<c:when test="${result.stsfdg == '1'}"><span title="매우불만족">★☆☆☆☆</span></c:when>
	     		<c:otherwise><span title="해당없음">☆☆☆☆☆</span></c:otherwise>
	     		</c:choose><br/>
	     		<c:out value="${fn:replace(result.stsfdgCn , crlf , '<br />')}" escapeXml="false" />
			</div>
    	</td>
    	<td class="lt_text3" style="padding-left:10px;">

    		<c:if test="${anonymous == 'true' || result.wrterId == uniqId}">
     		<a href="#" onclick="javascript:fn_aram_edit_satisfaction('<c:out value="${result.stsfdgNo}" />', '<c:out value="${status.index}" />'); return false;"><spring:message code="button.update" /></a>
      		| <a href="#" onclick="javascript:fn_aram_delete_satisfaction('<c:out value="${result.stsfdgNo}" />', '<c:out value="${status.index}" />'); return false;"><spring:message code="button.delete" /></a>
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
		<c:when test="${satisfactionVO.stsfdgNo == ''}">
     		<span class="button"><a href="#" onclick="javascript:fn_aram_insert_satisfaction(); return false;">만족도등록</a></span>
		</c:when>
		<c:otherwise>
     		<span class="button"><a href="#" onclick="javascript:fn_aram_update_satisfaction(); return false;">만족도수정</a></span>
		</c:otherwise>
		</c:choose>
     	<span class="button"><a href="#" onclick="javascript:fn_aram_reset_satisfaction(); return false;">만족도초기화</a></span>
	</div>
</div>

<table class="table-register">
	<tr>
		<th width="20%">
			<label for="wrterNm">작성자</label>
			<span class="required_icon"></span>
		</th>
	    <td width="80%">
	      	<input name="wrterNm" id="wrterNm" type="text" size="20" value='<c:out value="${satisfactionVO.wrterNm}" />' maxlength="20" title="작성자입력">
	      	<br>
	    </td>
	</tr>
	<tr>
		<th>
			만족도
			<span class="required_icon"></span>
		</th>
	    <td>
    	<table border="0" >
    		<tr>
       			<td width="20%" align="center">
       				<input type="radio" name="stsfdg" value="5" <c:if test="${satisfactionVO.stsfdg == '5'}"> checked="checked"</c:if>>
       				<span title="매우만족">★★★★★</span>
       			</td>
       			<td width="20%" align="center">
       				<input type="radio" name="stsfdg" value="4" <c:if test="${satisfactionVO.stsfdg == '4'}"> checked="checked"</c:if>>
       				<span title="만족">★★★★☆</span>
       			</td>
       			<td width="20%" align="center">
       				<input type="radio" name="stsfdg" value="3" <c:if test="${satisfactionVO.stsfdg == '3'}"> checked="checked"</c:if>>
       				<span title="보통">★★★☆☆</span>
       			</td>
       			<td width="20%" align="center">
       				<input type="radio" name="stsfdg" value="2" <c:if test="${satisfactionVO.stsfdg == '2'}"> checked="checked"</c:if>>
       				<span title="불만족">★★☆☆☆</span>
       			</td>
       			<td width="20%" align="center">
       				<input type="radio" name="stsfdg" value="1" <c:if test="${satisfactionVO.stsfdg == '1'}"> checked="checked"</c:if>>
       				<span title="매우불만족">★☆☆☆☆</span>
       			</td>
      		</tr>
      	</table>
	    </td>
	</tr>
	<tr>
		<th>
			<label for="stsfdgCn">내용</label>(<span id="cnLength"></span>)
	    	<span class="norequired_icon"></span>
		</th>
	    <td>
	      	<textarea name="stsfdgCn" id="stsfdgCn" class="textarea" cols="50" rows="2"  onkeyup="javascript:fn_check_length();" style="width:90%;" title="내용입력">
<c:out value="${satisfactionVO.stsfdgCn}" />
			</textarea>
	    </td>
	</tr>
	
	<c:choose>
	<c:when test="${anonymous == 'true'}">
		<tr>
			<th>
				<label for="stsfdgPassword">패스워드</label>
			    <span class="required_icon"></span>
			</th>
			<td>
			    <input name="stsfdgPassword" id="stsfdgPassword" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
			</td>
		</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="stsfdgPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
	</c:otherwise>
	</c:choose>
	
</table>

<script type="text/javascript">
function fn_check_length() {
	var cnLength = document.getElementById("cnLength");
	var stsfdgCn = document.getElementById("stsfdgCn");
	cnLength.innerHTML = stsfdgCn.value.length; 
}	
</script>

</c:if>

<form:hidden path="pageIndex" />
</form:form>

<c:if test="${not empty subMsg}">
<script type="text/javascript">
	alert("<c:out value='${subMsg}'/>");
</script>
</c:if>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="satisfactionVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("satisfactionVO");
	varForm.pageIndex.value = pageNo;
	varForm.stsfdgNo.value = '';
	varForm.action = "${pageContext.request.contextPath}/content/board/${satisfactionVO.bbsId}/article/${satisfactionVO.nttId}/satisfactions";
	varForm.submit();
}

function fn_aram_reset_satisfaction() {
    var varForm = document.getElementById("satisfactionVO");
	varForm.pageIndex.value = 1;
	varForm.stsfdgNo.value = '';
	varForm.action = "${pageContext.request.contextPath}/content/board/${satisfactionVO.bbsId}/article/${satisfactionVO.nttId}/satisfactions";
	varForm.submit();
}

function fn_aram_insert_satisfaction() {
    var varForm = document.getElementById("satisfactionVO");

    if (!validateSatisfactionVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.modified.value = "true";
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertSatisfaction.do";
		varForm.submit();
	}
}

function fn_aram_edit_satisfaction(satisfactionNo, index) {
    var varForm = document.getElementById("satisfactionVO");
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
	varForm.stsfdgNo.value = satisfactionNo;
	varForm.action = "${pageContext.request.contextPath}/content/board/${satisfactionVO.bbsId}/article/${satisfactionVO.nttId}/satisfactions";
	varForm.submit();
}

function fn_aram_update_satisfaction() {
    var varForm = document.getElementById("satisfactionVO");

    if (!validateSatisfactionVO(varForm)){
		return;
	}

<c:if test="${anonymous == 'true'}">
	varForm.confirmPassword.value = varForm.stsfdgPassword.value;
</c:if>

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.modified.value = "true";
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateSatisfaction.do";
		varForm.submit();
	}
}

function fn_aram_delete_satisfaction(satisfactionNo, index) {
    var varForm = document.getElementById("satisfactionVO");
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
		varForm.stsfdgNo.value = satisfactionNo;
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/deleteSatisfaction.do";
		varForm.submit();
	}
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("만족도조사");	
	window.open(url, "도움말");
}

</script>

</body>
</html>

