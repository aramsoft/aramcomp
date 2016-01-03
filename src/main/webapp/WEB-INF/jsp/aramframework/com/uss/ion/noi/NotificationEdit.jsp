<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : NotificationEdit.jsp
 * @Description : 정보알림이 수정
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
<DIV id="main">

<div class="content_title">
	<h2>정보알림이 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="notificationVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="ntfcNo" />

<input name="ntfcTime" id="ntfcTime" type="hidden" value='<c:out value="${fn:substring(notificationVO.ntfcTime,11,16)}"/>'>

<table class="table-register" summary="정보알림이에 대한 정보를 수정합니다.">
<tbody>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="ntfcSj"><spring:message code="uss.ion.noi.ntfcSj" /></label>
    	</th>
    	<td width="80%" colspan="3">
    		<form:input path="ntfcSj" size="25" />
    		<form:errors path="ntfcSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="ntfcCn"><spring:message code="uss.ion.noi.ntfcCn" /></label>
    	</th>
    	<td colspan="3">
       		<textarea id="ntfcCn" name="ntfcCn" class="textarea" cols="75" rows="2" ><c:out value="${notificationVO.ntfcCn}" escapeXml="true" /></textarea>
       		<form:errors path="ntfcCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="ntfcDate"><spring:message code="uss.ion.noi.ntfcDate" /></label>
    	</th>
    	<td width="30%">
 			<input name="ntfcDate" id="ntfcDate" type="text" size="10" value="<c:out value='${fn:substring(notificationVO.ntfcTime, 0, 10)}'/>" maxlength="10" style="width:80px;" readonly="readonly" >
			<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].ntfcDate); return false;">
     			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
			</a>
  	   	<form:errors path="ntfcDate" cssClass="error"/>
    	</td>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="ntfcHH"><spring:message code="uss.ion.noi.ntfcTime" /></label>
     	</th>
    	<td width="30%">
			<select name="ntfcHH" id="ntfcHH">
				<option value="">선택</option>
				<c:forEach var="h" begin="0" end="23" step="1">
				<option value="${h}">${h}시</option>
				</c:forEach>
			</select>
	
			<select name="ntfcMM" id="ntfcMM">
				<option value="">선택</option>
				<c:forEach var="m" begin="0" end="59" step="1">
				<option value="${m}">${m}분</option>
				</c:forEach>
			</select>
	  	    <form:errors path="ntfcHH" cssClass="error"/>
	  	    <form:errors path="ntfcMM" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="bhNtfcIntrvl"><spring:message code="uss.ion.noi.bhNtfcIntrvl" /></label>
    	</th>
    	<td colspan="3">
    		<c:set var="data">,<c:out value="${notificationVO.bhNtfcIntrvlString}" /></c:set>
			1분:<input id="bhNtfcIntrvl" name="bhNtfcIntrvl" type="checkbox" value="1" <c:if test="${fn:contains(data, ',1분')}">checked="checked"</c:if> title="시간간격체크(1분)">&nbsp;&nbsp;
			3분:<input name="bhNtfcIntrvl" type="checkbox" value="3" <c:if test="${fn:contains(data, ',3분')}">checked="checked"</c:if> title="시간간격체크(3분)">&nbsp;&nbsp;
			5분:<input name="bhNtfcIntrvl" type="checkbox" value="5" <c:if test="${fn:contains(data, ',5분')}">checked="checked"</c:if> title="시간간격체크(5분)">&nbsp;&nbsp;
			10분:<input name="bhNtfcIntrvl" type="checkbox" value="10" <c:if test="${fn:contains(data, ',10분')}">checked="checked"</c:if> title="시간간격체크(10분)">&nbsp;&nbsp;
			30분:<input name="bhNtfcIntrvl" type="checkbox" value="30" <c:if test="${fn:contains(data, ',30분')}">checked="checked"</c:if> title="시간간격체크(30분)">&nbsp;&nbsp;
	  	   <form:errors path="bhNtfcIntrvl" cssClass="error"/>
   		</td>
  	</tr>
</tbody>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="notificationVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

function onloading() {
	if ("<c:out value='${msg}'/>" != "") {
		alert("<c:out value='${msg}'/>");
	}
}

window.onload = function() {
	onloading();

    var varForm = document.getElementById("notificationVO");

	var hh = varForm.ntfcTime.value.substr(0, 2);
	var mm = varForm.ntfcTime.value.substr(3, 2);

	if (hh.charAt(0) == '0') {
		hh = hh.charAt(1);
	}

	if (mm.charAt(0) == '0') {
		mm = mm.charAt(1);
	}

	for (var i = 0; i < varForm.ntfcHH.length; i++) {
		if (varForm.ntfcHH[i].value == hh) {
			varForm.ntfcHH[i].selected = true;
		}
	}

	for (var i = 0; i < varForm.ntfcMM.length; i++) {
		if (varForm.ntfcMM[i].value == mm) {
			varForm.ntfcMM[i].selected = true;
		}
	}
}

function fn_aram_list() {
    var varForm = document.getElementById("notificationVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/listNotification.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("notificationVO");
    
	if (!validateNotificationVO(varForm)){
		return;
	}

	var checked = false;
	for (var i = 0; i < varForm.bhNtfcIntrvl.length; i++) {
		if (varForm.bhNtfcIntrvl[i].checked) {
			checked = true;
			break;
		}
	}

	if (!checked) {
		alert('<spring:message code="uss.ion.noi.bhNtfcIntrvl.msg" />');
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.ntfcTime.value = fn_aram_SelectBoxValue('ntfcHH') + ":" + fn_aram_SelectBoxValue('ntfcMM');
		varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/updateNotification.do";
		varForm.submit();
	}
}

</script>
