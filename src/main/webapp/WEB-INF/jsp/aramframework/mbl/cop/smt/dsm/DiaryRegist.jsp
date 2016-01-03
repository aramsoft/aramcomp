<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일지관리 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cop/smt/sim/schdul-popup.js"></script>

</head>

<body>
	
<div id="regist" data-role="page">
							
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1>일지관리 등록</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">
		
		<form:form commandName="diaryManageVO" method="post">
			<form:hidden path="diaryId" />

			<div data-inline="true">
				<dl class="uss-registOk">
					<dt><label for="name"><strong>일정정보</strong></label></dt>
					<dd>
						<div class="uss-typ3">
							<form:input path="schdulNm" maxlength="30"  readonly="true"/>
						</div>
		        		<div class="uss-typ4">
		        			<a href="${pageContext.request.contextPath}/cop/smt/sim/listSchdulPopup.mdo" data-rel="dialog" id="layer_1" data-role="button" data-icon="search" data-iconpos="notext"></a>
						</div>
						<form:hidden path="schdulId" /></dd>
						
					<dt><label for="diaryNm"><strong>일지명</strong></label></dt>
					<dd><form:input path="diaryNm" maxlength="300" /></dd>
					
					<dt><label for="drctMatter"><strong>지시사항</strong></label></dt>
					<dd><form:textarea path="drctMatter" maxlength="300"/></dd>
					
					<dt><label for="partclrMatter"><strong>특이사항</strong></label></dt>
					<dd><form:textarea path="partclrMatter" maxlength="300" /></dd>
					
					<dt><label for="diaryProcsPte"><strong>진척률</strong></label></dt>
					<dd>
						<div class="uss-typ1"><form:input path="diaryProcsPte" maxlength="30" style="width:100%;vertical-align:middle;" /></div>
						<div class="uss-typ2">%</div>
					</dd>
				</dl>
			</div>
		</form:form>
	
		<div class="ui-grid-a uss-clear">	
			<div class="ui-block-a"><a href="javascript:fn_aram_insert();" data-role="button" data-theme="b">등록</a></div>
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
<validator:javascript formName="diaryManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript">

function fn_sel_schdul(schdulId, schdulNm) {

	$('#schdulId').val(schdulId);
	$('#schdulNm').val(schdulNm);

	$.mobile.changePage($("#regist"));
}

function fn_aram_list() {
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo";
    varForm.submit();
}
		 			
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("diaryManageVO");
    
	if(!validateDiaryManageVO(varForm)){
		return;
	}

	var process = varForm.diaryProcsPte.value;
	if(process> 100 || process < 0){
		jAlert("진척률을 확인해 주세요", "알림", "a");
		return;
	}
					
	varForm.action =  "${pageContext.request.contextPath}/cop/smt/dsm/insertDiary.mdo"; 
	varForm.submit();
}  

</script>

</body>
</html>

