<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : NotificationRegist.jsp
 * @Description : 정보알림이 등록
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
	<h2>정보알림이 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="notificationVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="ntfcNo" />

<input name="ntfcTime" id="ntfcTime" type="hidden" value="">

<table class="table-register" summary="정보알림이에 대한 정보를 등록합니다.">
<tbody>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="ntfcSj"><spring:message code="uss.ion.noi.ntfcSj" /></label>
    	</th>
    	<td width="80%" colspan="3">
    		<form:input path="ntfcSj" size="25"  />
    		<form:errors path="ntfcSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="ntfcCn"><spring:message code="uss.ion.noi.ntfcCn" /></label>
    	</th>
    	<td colspan="3">
       		<form:textarea path="ntfcCn" cols="75" rows="2"  />
       		<form:errors path="ntfcCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="ntfcDate"><spring:message code="uss.ion.noi.ntfcDate" /></label>
    	</th>
    	<td width="30%">
			<input name="ntfcDate" id="ntfcDate" type="text" size="10" value="" maxlength="10" readonly="readonly" >
			<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].ntfcDate); return false;">
     			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
			</a>
  	   		<form:errors path="ntfcDate" cssClass="error"/>
    	</td>
    	<th width="20%">
    		<label for="ntfcHH"><spring:message code="uss.ion.noi.ntfcTime" /></label>
    		<span class="required_icon"></span>
    	</th>
    	<td width="30%">
			<select name="ntfcHH" id="ntfcHH" title="시간선택">
				<option value="">선택</option>
				<c:forEach var="h" begin="0" end="23" step="1">
				<option value="${h}">${h}시</option>
				</c:forEach>
			</select>

			<select name="ntfcMM" id="ntfcMM" title="분선택">
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
    	<th >
    		<span class="required_icon"></span>
    		<label for="bhNtfcIntrvl"><spring:message code="uss.ion.noi.bhNtfcIntrvl" /></label>
    	</th>
    	<td colspan="3">
			1분:<form:checkbox id="bhNtfcIntrvl" class="check2" path="bhNtfcIntrvl" value="1"/>&nbsp;&nbsp;
			3분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="3"/>&nbsp;&nbsp;
			5분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="5"/>&nbsp;&nbsp;
			10분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="10"/>&nbsp;&nbsp;
			30분:<form:checkbox path="bhNtfcIntrvl" class="check2" value="30"/>&nbsp;&nbsp;
	  	   <form:errors path="bhNtfcIntrvl" cssClass="error"/>
    	</td>
  	</tr>
</tbody>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="notificationVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("notificationVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/listNotification.do";
    varForm.submit();
}

function fn_aram_insert(){
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

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.ntfcTime.value = fn_aram_SelectBoxValue('ntfcHH') + ":" + fn_aram_SelectBoxValue('ntfcMM');
		varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/insertNotification.do";
		varForm.submit();
	}
}

</script>
