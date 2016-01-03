<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="aramframework.com.cmm.constant.AramProperties" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>  
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${boardVO.boardMasterVO.bbsNm}</title> 

<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>
<!-- datebox  import-->        
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/datepicker/jqm-datebox.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.calbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.datebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.flipbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jquery.mobile.datebox.i18n.ko.utf8.js"></script> 

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/com/cop/bbs/EgovBBSMng.js" ></script>

<script type="text/javascript">

function fn_aram_list_notice() {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);

	varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/" + bbsId + "/articles";
    varForm.submit();
}

<c:if test="${editAuthFlag == 'Y'}">
function fn_aram_insert_notice() {
	var varForm = document.getElementById("boardVO");
	
	if (!validateBoardVO(varForm)){
		return;
	}

	var ntceBgnde = varForm.ntceBgnde.value; 
	var ntceEndde = varForm.ntceEndde.value;

	if(ntceBgnde> ntceEndde){
		alert("게시기간 종료일이 시작일보다 빠릅니다.");
		return;		
	}

	varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/insertBoardArticle.mdo";
	varForm.submit();
}
</c:if>

function fn_aram_reset_notice() {
    var varForm = document.getElementById("boardVO");
    varForm.reset();
}

</script>
</head>

<body>

<!--등록 페이지 -->
<div data-role="page" >

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list_notice();" data-icon="arrow-l" >뒤로</a>
		<h1>게시글 쓰기</h1> 
	</div>

	<div data-role="content" class="com-ussContent">

		<form:form commandName="boardVO" method="post" enctype="multipart/form-data">
			<form:hidden path="bbsId" />
			<form:hidden path="nttId" />

			<div data-role="fieldcontain" data-inline="true">
				<div class="uss-regist" data-inline="true">
					<label for="nttSj"><strong>제목</strong></label><br>
	        		<form:input path="nttSj" maxlength="20"  size="20" placeholder="제목을 입력해주세요." />
				</div>
				<div class="uss-regist" data-inline="true">
					<label for="nttCn"><strong>글내용</strong></label><br>
		        	<form:textarea cols="40" rows="40" path="nttCn" placeholder="글내용을 입력해주세요." class="com-textContents" />
				</div>
				<c:choose>
				  	<c:when test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}"> 
						<div class="uss-regist" data-inline="true">
							<label for="ntceBgnde"><strong>시작일자</strong></label><br>
			        		<input name="ntceBgnde" id="defandroid" type="text"  data-role="datebox"  data-options='{"mode": "datebox"}'>
						</div>
						<div class="uss-regist" data-inline="true">
							<label for="ntceEndde"><strong>종료일자</strong></label><br>
				        	<input name="ntceEndde" id="defandroid" type="text"  data-role="datebox"  data-options='{"mode": "datebox"}'>
						</div>
					</c:when>
				  	<c:otherwise>
						<input name="ntceBgnde" type="hidden" value="10000101">
						<input name="ntceEndde" type="hidden" value="99991231">
				  	</c:otherwise> 
				</c:choose>
				<c:choose>
				  	<c:when test="${anonymous == 'true'}"><!-- 익명글 -->
						<div class="uss-regist" data-inline="true">
							<label for="ntcrNm"><strong>작성자</strong></label><br>
			        		<input name="ntcrNm" type="text" maxlength="10" size="20">
						</div>
						<div class="uss-regist" data-inline="true">
							<label for="password"><strong>비밀번호</strong></label><br>
				        	<input name="password" type="password" maxlength="20">
						</div>
					</c:when>
				  	<c:otherwise>
						<input type="hidden" name="ntcrNm" value="dummy">   <!-- validator 처리를 위해 지정 -->
						<input type="hidden" name="password" value="dummy"> <!-- validator 처리를 위해 지정 -->
				  	</c:otherwise> 
				</c:choose>
				
				<!-- 파일첨부  -->
				<c:if test="${boardVO.boardMasterVO.fileAtchPosblAt == 'Y'}">
					<strong><spring:message code="cop.atchFile" /></strong>
					<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="<c:out value='${boardVO.boardMasterVO.posblAtchFileNumber}'/>" />
			
			        <table border="0" >
						<tr>
						    <td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
						</tr>
						<tr>
						    <td>
						        <div id="egovComFileList">&nbsp;</div>
						    </td>
						</tr>
			   	    </table>
			  	</c:if>
			</div>
		</form:form>
		
		<div class="ui-grid-a addBgBtn">
			<c:if test="${editAuthFlag == 'Y'}">
			<div class="ui-block-a">
                <input type="button" value="저장" onclick="javascript:fn_aram_insert_notice(); return false;" data-icon="plus" data-theme="b" data-ajax="false" >
			</div>
			</c:if>
			<div class="ui-block-b">
                <input type="reset" value="초기화" onclick="javascript:fn_aram_reset_notice(); return false;" data-theme="b" data-icon="refresh"/>
			</div>
		</div>
				
	</div>

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
		
</div>	

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<c:if test="${boardVO.boardMasterVO.fileAtchPosblAt == 'Y'}"> 
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
</c:if>
<!--  첨부파일 업로드 가능화일 설정 End. -->

</body>
</html>
