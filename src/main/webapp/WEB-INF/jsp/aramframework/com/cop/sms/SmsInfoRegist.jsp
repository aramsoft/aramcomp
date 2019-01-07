<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : SmsInfoRegist.jsp
 * @Description : 문자메시지 전송
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
	<h2>문자메시지 전송</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="cop.sms.send" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="smsVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />


<table class="table-register" summary="문자메시지에 대한 정보를  등록 및 전송합니다.">
<tbody>
	<tr> 
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="trnsmitTelno"><spring:message code="cop.sms.trnsmitTelno" /></label>
	    </th>
	    <td width="80%">
	    	<form:input path="trnsmitTelno" size="14" maxlength="14" cssStyle="width:90%" />
	    	<form:errors path="trnsmitTelno" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="trnsmitCn"><spring:message code="cop.sms.trnsmitCn" /></label>
	    </th>
	    <td>
	       <form:textarea path="trnsmitCn" cols="75" rows="2" cssStyle="width:90%" />
	       <form:errors path="trnsmitCn" cssClass="error"/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="recptnTelno"><spring:message code="cop.sms.recptnTelno" /></label>
	    </th>
	    <td>
		<table border="0" >
		   	<tr>
		   	  	<td align="left">
		   	  		1 : <form:input id="recptnTelno" path="recptnTelno" size="14" maxlength="14" /><br>
		   	  		<c:forEach begin="2" end="5" step="1" var="index">
		   	  		<c:out value='${index}'/> : <form:input id="recptnTelno${index-1}" path="recptnTelno" size="14" maxlength="14" /><br>
		   	  		</c:forEach>
		   		</td>
		   	</tr>
		</table>
	  	<form:errors path="recptnTelno" cssClass="error"/>
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
<validator:javascript formName="smsVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("smsVO");
    varForm.action = "${pageContext.request.contextPath}/cop/sms/listSms.do";
    varForm.submit();
}
	
function fn_aram_insert() {
    var varForm = document.getElementById("smsVO");

    if (!validateSmsVO(varForm)){
		return;
	}

	var checked = false;
	for (var i = 0; i < varForm.recptnTelno.length; i++) {
		if (varForm.recptnTelno[i].value != '') {
			checked = true;
			break;
		}
	}

	if (!checked) {
		alert('<spring:message code="cop.sms.recptnTelno.msg" />');
		varForm.recptnTelno[0].focus();
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/sms/insertSms.do";
		varForm.submit();					
	}
}

</script>
