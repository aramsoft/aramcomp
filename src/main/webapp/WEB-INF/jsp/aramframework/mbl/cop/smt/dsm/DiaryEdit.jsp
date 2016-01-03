<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일지관리 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

</head>

<body>
	
<div id="edit" data-role="page">
							
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_detail();" data-icon="arrow-l" >뒤로</a>
	    <h1>일지관리 수정</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">

		<form:form commandName="diaryManageVO" method="post">
			<form:hidden path="diaryId"/>
		
			<!-- 검색조건 유지 -->
			<form:hidden path="searchCondition" />
			<form:hidden path="searchKeyword" />
			<form:hidden path="pageIndex" />
			<!-- 검색조건 유지 -->
			
			<div data-inline="true">
				<dl class="uss-registOk">
					<dt><label for="name"><strong>일정명</strong></label></dt>
					<dd><form:input path="schdulNm" maxlength="30" readonly="true"/>
        			<form:hidden path="schdulId" /></dd>
        			
        			<dt><label for="diaryNm"><strong>일지명</strong></label></dt>
        			<dd><form:input path="diaryNm" maxlength="300" /></dd>
        			
        			<dt><label for="drctMatter"><strong>지시사항</strong></label></dt>
        			<dd><form:textarea path="drctMatter" maxlength="300" /></dd>
        			
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
			<div class="ui-block-a"><a href="javascript:fn_aram_update();" data-role="button" data-theme="b">수정</a></div>
			<div class="ui-block-b"><a href='JavaScript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
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

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("diaryManageVO");
    
	if(!validateDiaryManageVO(varForm)){ 			
		return;
	}
	
	var process = varForm.diaryProcsPte.value;
	if(process> 100 || process < 0){
		jAlert("진척률을 확인해 주세요", "알림", "a");
		return;
	}
	
    varForm.action =  "${pageContext.request.contextPath}/cop/smt/dsm/updateDiary.mdo";
    varForm.submit();
}

function fn_aram_detail(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/detailDiary.mdo"; 
    varForm.submit();
}

</script>

</body>
</html>

