<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>행사/이벤트/캠페인 수정</title>

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
	
<div id="edit" data-role="page">
	
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_detail();" data-icon="arrow-l" >뒤로</a>
	    <h1>행사/이벤트/캠페인 수정</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">

		<form:form commandName="eventCmpgnVO" method="post">
			<form:hidden path="eventId" />
			
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword"  />
			<form:hidden path="pageIndex"  />

			<dl class="uss-registOk">
				<dt><label for="eventTyCode"><strong>행사유형</strong></label></dt>
				<dd class="last">
		      		<form:select path="eventTyCode" title="행사유형선택">
			      		<form:options items="${COM035_eventTy}" itemValue="code" itemLabel="codeNm"/>
		      		</form:select>
				</dd>
				
				<dt><label for="eventCn"><strong>행사내용</strong></label></dt>
				<dd><form:input path="eventCn" maxlength="300"/></dd>
				
				<dt><label for="eventSvcBeginDe"><strong>행사시작일자</strong></label></dt>
				<dd><form:input path="eventSvcBeginDe" type="text" data-role="datebox" id="eventSvcBeginDe" readonly="true" /></dd>
				
				<dt><label for="eventSvcEndDe"><strong>행사종료일자</strong></label></dt>
				<dd><form:input path="eventSvcEndDe" type="text" data-role="datebox" id="eventSvcEndDe" readonly="true" /></dd>
				
				<dt><label for="svcUseNmprCo"><strong>서비스이용인원수</strong></label></dt>
				<dd><form:input path="svcUseNmprCo"/></dd>
				
				<dt><label for="chargerNm"><strong>담당자명</strong></label></dt>
				<dd><form:input path="chargerNm" maxlength="300"/></dd>
				
				<dt><label for="prparetgCn"><strong>준비물내용</strong></label></dt>
				<dd><form:input path="prparetgCn" maxlength="300"/></dd>
				
				<%-- 업무사용자만 승인 가능하도록 처리 --%>
				<c:if test="${SUserSe eq 'USR'}">
				<dt><label for="eventConfmAt"><strong>승인여부</strong></label></dt>
				<dd><fieldset data-role="controlgroup" data-type="horizontal"  data-inline="true"> 				
						<input type="radio" name="eventConfmAt" id="radio-1" value="Y" <c:if test="${eventCmpgnVO.eventConfmAt eq 'Y'}">checked</c:if>/> 
						<label for="radio-1">승인</label> 
						<input type="radio" name="eventConfmAt" id="radio-2" value="N" <c:if test="${eventCmpgnVO.eventConfmAt eq 'N'}">checked</c:if> /> 
						<label for="radio-2">미승인</label> 
					</fieldset></dd>
				<dt><label for="eventConfmDe"><strong>승인일</strong></label></dt>
				<dd><form:input path="eventConfmDe" type="text" data-role="datebox" id="eventConfmDe" readonly="true" style="width:60%;" /></dd>
				</c:if>	
	       	</dl>
  		</form:form>
      						
		<div class="ui-grid-a uss-clear">	
			<div class="ui-block-a"><a href="javascript:fn_aram_update()" data-role="button" data-theme="b">저장</a></div>
			<div class="ui-block-b"><a href='JavaScript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
		</div>				
	</div>
	
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
		
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="eventCmpgnVO" staticJavascript="false" xhtml="true" cdata="false"/> 

<script type="text/javaScript">

$(function(){
	$('input[type="radio"]').change(function(){
		var value = $('input[name="eventConfmAt"]:checked').val();
		if(value == "N") {
			$('#eventConfmDe').val("");
		} 
	});
});

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varFrom = document.getElementById("eventCmpgnVO");;
	varFrom.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo";
	varFrom.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
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
	if(varFrom.svcUseNmprCo.value == ""){
		varFrom.svcUseNmprCo.value = "0" ;
	}
	if($('#eventSvcBeginDe').val()> $('#eventSvcEndDe').val()) {
		jAlert("행사종료일자가 \n행사시작일자보다\n빠릅니다.", '알림', 'a');
		return;
	}
	
	varFrom.action = "${pageContext.request.contextPath}/uss/ion/ecc/updateEventCmpgn.mdo";
	varFrom.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_aram_detail(){
	var varFrom = document.getElementById("eventCmpgnVO");;
	varFrom.action = "${pageContext.request.contextPath}/uss/ion/ecc/detailEventCmpgn.mdo";
	varFrom.submit();
}

</script>

</body>
</html>

