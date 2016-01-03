<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>행사/이벤트/캠페인 입력</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>
   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script> 	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

<!-- datebox  import-->        
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/datepicker/jqm-datebox.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.calbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.datebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.flipbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jquery.mobile.datebox.i18n.ko.utf8.js"></script>
      
</head>

<body>
	
<div id="regist" data-role="page">

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1>행사/이벤트/캠페인 입력</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">
		<form:form commandName="eventCmpgnVO" method="post">
			<form:hidden path="eventId" value=""/>

			<dl class="uss-registOk">
				<dt><label for="eventTyCode"><strong>행사유형</strong></label></dt>
				<dd>
					<form:select path="eventTyCode" title="행사유형선택">
			      		<form:options items="${COM035_eventTy}" itemValue="code" itemLabel="codeNm"/>
		      		</form:select>
				</dd>
					
				<dt><label for="eventCn"><strong>행사내용</strong></label></dt>
				<dd><form:input path="eventCn" maxlength="300" /></dd>
				
				<dt><label for="eventSvcBeginDe"><strong>행사시작일자</strong></label></dt>
				<dd><input name="eventSvcBeginDe" id="eventSvcBeginDe" type="text" data-role="datebox" readonly="readonly" class="uss-restBtn" /></dd>
				<dt><label for="eventSvcEndDe"><strong>행사종료일자</strong></label></dt>
				<dd><input name="eventSvcEndDe" id="eventSvcEndDe" type="text" data-role="datebox" readonly="readonly" class="uss-restBtn" /></dd>
				
				<dt><label for="svcUseNmprCo"><strong>서비스이용인원수</strong></label></dt>
				<dd><form:input path="svcUseNmprCo" maxlength="30" /></dd>
				
				<dt><label for="chargerNm"><strong>담당자명</strong></label></dt>
				<dd><form:input path="chargerNm" maxlength="30" /></dd>
				
				<dt><label for="prparetgCn"><strong>준비물내용</strong></label></dt>
				<dd><form:input path="prparetgCn" maxlength="30" /></dd>
				
				<%-- 업무사용자만 승인 가능하도록 처리 --%>
				<c:if test="${SUserSe eq 'USR'}">
				<dt><label for="eventConfmAt"><strong>승인여부</strong></label></dt>
				<dd><fieldset data-role="controlgroup" data-type="horizontal"  data-inline="true"> 				
						<form:radiobutton path="eventConfmAt" value="Y" label="승인" />
						<form:radiobutton path="eventConfmAt" value="N" label="미승인" checked="checked" /> 
					</fieldset></dd>
				
				<dt><label for="eventConfmDe"><strong>승인일</strong></label></dt>
				<dd><input name="eventConfmDe" id="eventConfmDe" type="text" data-role="datebox" readonly="readonly"/></dd>
				</c:if>
			</dl>
		</form:form>
		
		<div class="ui-grid-a">
			<div class="ui-block-a"><a href="javascript:fn_aram_insert()" data-role="button" data-theme="b">등록</a></div>
			<div class="ui-block-b"><a href='javascript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
		</div>
		
	</div>
		
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
		
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="eventCmpgnVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript">

$(function(){
	$('input[type="radio"]').change(function(){
		var value = $('input[name="eventConfmAt"]:checked').val();
		if(value == "N") {
			$('#eventConfmDe').val("");
		} 
	});
});

/* ********************************************************
 * 저장처리화면(등록창에서 사용)
 ******************************************************** */
function fn_aram_insert(){
	var varFrom = document.getElementById("eventCmpgnVO");
	if(!validateEventCmpgnVO(varFrom)){
		return;
	}
	
	<%-- 업무사용자만 승인 가능하도록 처리--%>
	<c:if test="${SUserSe eq 'USR'}">
	
	if($('input[name="eventConfmAt"]:checked').val() == "Y" && $('#eventConfmDe').val() == ""){
		jAlert("승인일을 입력하세요.", '알림', 'a');
		document.getElementById("eventConfmDe").focus();
		return;
	}
	if($('input[name="eventConfmAt"]:checked').val() == "N" && $('#eventConfmDe').val() != ""){
		$('#eventConfmDe').val("");
	}
	</c:if>
	if(document.getElementById("svcUseNmprCo").value == ""){
		document.getElementById("svcUseNmprCo").value = "0" ;
	}
	if($('#eventSvcBeginDe').val()> $('#eventSvcEndDe').val()) {
		jAlert("행사종료일자가 \n행사시작일자보다\n빠릅니다.", '알림', 'a');
		return;
	}
	varFrom.action =  "${pageContext.request.contextPath}/uss/ion/ecc/insertEventCmpgn.mdo";
	varFrom.submit();
}

function fn_aram_list() {
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo";
    varForm.submit();
}

</script>

</body>
</html>

