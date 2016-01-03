<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일정관리등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cop/smt/sim/schdul-popup.js"></script>

<!-- datebox javascript-->
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
	    <h1>일정관리등록</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">
		
		<form:form commandName="schdulManageVO" method="post">
			<form:hidden path="searchSchdulSe" />
			<form:hidden path="year" value="" />
			<form:hidden path="month" value="" />
			<form:hidden path="week" value="" />
			<form:hidden path="day" value="" />
			
			<div data-inline="true">
				<dl class="uss-registOk">
					<dt><label for="name"><strong>일정구분</strong></label></dt>
					<dd>
						<form:select path="schdulSe" data-role="none">
				            <form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
				        </form:select>
					</dd>
					
					<dt><label for="ipcr"><strong>중요도</strong></label></dt>
					<dd>
						<form:select path="schdulIpcrCode" data-role="none">
				            <form:options items="${COM019_schdulIpcr}" itemValue="code" itemLabel="codeNm"/>
				        </form:select>
					</dd>
					
					<dt><label for="reptitSeCode"><strong>반복구분</strong></label></dt>
					<dd>
						<fieldset data-role="controlgroup" data-type="horizontal"  data-inline="true"> 	
			        		<c:forEach items="${COM031_reptitSe}" var="reptitSe">
			        			<form:radiobutton path="reptitSeCode" value="${reptitSe.code}" label="${reptitSe.codeNm}"/>
			        		</c:forEach>
		        		</fieldset>
					</dd>
					
					<dt><label for="schdulNm"><strong>일정명</strong></label></dt>
					<dd><form:input path="schdulNm" size="70" maxlength="70" title="일정명"/></dd>
					
					<dt><label for="schdulCn"><strong>일정내용</strong></label></dt>
					<dd><form:textarea path="schdulCn" cols="300" rows="20" cssClass="txArea" title="일정내용"/></dd>
					
					<dt><label for="reptitSeCode"><strong>날짜/시간</strong></label></dt>
					<dd class="department">
						<div>
							<span class="uss-dataBox"><form:input path="schdulBgndeYYYMMDD" type="text" data-role="datebox" readonly="true" class="new" /></span>
			        		<span class="uss-dataBox"><form:input path="schdulBgndeHH" type="text" data-role="datebox" data-options='{"mode": "timebox"}' readonly="true" class="new" /></span>
						</div>
						<div class="uss-time">
		        			<span class="uss-dataBox"><form:input path="schdulEnddeYYYMMDD" type="text" data-role="datebox" readonly="true" class="new" /></span>
			        		<span class="uss-dataBox"><form:input path="schdulEnddeHH" type="text" data-role="datebox" data-options='{"mode": "timebox"}' readonly="true" class="new" /></span>
		        		</div>
					</dd>
					
					<dt><label for="dept"><strong>담당자</strong></label></dt>
					<dd>
						<div class="uss-typ3">
							<form:input path="schdulChargerName" maxlength="30" readonly="true" />
							<form:hidden path="schdulChargerId" />
						</div>
						<div class="uss-typ4"><a href="${pageContext.request.contextPath}/cop/smt/sim/listEmplyrPopup.mdo" data-rel="dialog" id="layer_2" data-role="button" data-icon="search" data-iconpos="notext">조회</a></div>
					</dd>
				</dl>
			
			</div>
		
		</form:form>

		<div class="ui-grid-a uss-clear">
			<div class="ui-block-a"><a href="javascript:fn_aram_insert();" data-role="button" data-theme="b">저장</a></div>
			<div class="ui-block-b"><a href='javascript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
		</div>						

	</div>
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
		
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="schdulManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript"> 

function fn_sel_emp(chargerId, chargerNm) {
	
	$('#schdulChargerId').val(chargerId);
	$('#schdulChargerName').val(chargerNm);

	$.mobile.changePage($("#regist"));
}

function fn_aram_insert() {
    var varForm = document.getElementById("schdulManageVO");
    
	if(!validateSchdulManageVO(varForm)) {
		return;
	}
							
	var url = "${pageContext.request.contextPath}/cop/smt/sim/insertSchdul.mdo";
	$('#schdulManageVO').attr('action', url);
	$('#schdulManageVO').attr('data-ajax', 'false');
	$('#schdulManageVO').submit();	
}

function fn_aram_list() {

	var url = "";
	var path = $('#path').val();
	
	if(path == "week"){
		url = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.mdo";
	}
	else {
		url = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.mdo";
	}
	
	$('#schdulManageVO').attr('action', url);
	$('#schdulManageVO').attr('data-ajax', 'false');
	$('#schdulManageVO').submit();
}

</script>
<!-- 레이어팝업 -->
</body>
</html>

