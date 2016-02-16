<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html> 
<head>
<title>EgovFrame 모바일게시판</title> 
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
      
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/cop/bbs/EgovBBSMng.js" ></script>
      
<script type="text/javascript">

function fn_aram_list_notice() {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);

	varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/" + bbsId + "/articles";
    varForm.submit();	
}
	
<c:if test="${editAuthFlag == 'Y'}">
function fn_aram_update_notice(){
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

	varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/updateBoardArticle.mdo";
	varForm.submit();
}
</c:if>

//상세조회
function fn_aram_detail_notice() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/detailBoardArticle.mdo";
    varForm.submit();			
}	

</script>
</head>

<body>

<!-- 메인 페이지 -->
<div data-role="page">

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_detail_notice();" data-icon="arrow-l" >뒤로</a>
		<h1>게시글 수정</h1> 	
	</div>
	
    <div data-role="content" class="com-ussContent">
        
		<form:form commandName="boardVO" method="post" action=""  enctype="multipart/form-data">
			<form:hidden path="bbsId" />
			<form:hidden path="nttId" />
	
			<!-- 검색조건 유지 -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword" />
			<form:hidden path="searchVO.pageIndex" />
			<!-- 검색조건 유지 -->
		
			<div data-role="fieldcontain" data-inline="true">
				<div class="uss-regist" data-inline="true">
					<label for="nttSj"><strong>제목</strong></label><br>
	        		<input name="nttSj"  type="text" id="name" maxlength="20"  size="20"  value="<c:out value='${boardVO.nttSj}'/>">
				</div>
				<div class="uss-regist" data-inline="true">
					<label for="nttCn"><strong>글내용</strong></label><br>
		        	<textarea cols="40" rows="40" name="nttCn" id="nttCn"class="com-textContents"><c:out value="${boardVO.nttCn}" escapeXml="false" /></textarea>
				</div>
				<c:choose>
				  	<c:when test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}"> 
				    	<c:if test="${!empty boardVO.ntceBgnde}">
			 				<c:set var="ntceBgndeVal" value="${fn:substring(boardVO.ntceBgnde, 0,4)}-${fn:substring(boardVO.ntceBgnde, 4,6)}-${fn:substring(boardVO.ntceBgnde, 6,8)}"/>
			      		</c:if>
						<div class="uss-regist" data-inline="true">
							<label for="ntceBgnde">시작일자</label><br>
			        		<input name="ntceBgnde" id="defandroid" type="text"  value="<c:out value='${ntceBgndeVal}'/>" data-role="datebox" data-options='{"mode": "datebox"}'>
						</div>
				    	<c:if test="${!empty boardVO.ntceEndde}">
			 				<c:set var="ntceEnddeVal" value="${fn:substring(boardVO.ntceEndde, 0,4)}-${fn:substring(boardVO.ntceEndde, 4,6)}-${fn:substring(boardVO.ntceEndde, 6,8)}"/>
			      		</c:if>
						<div class="uss-regist" data-inline="true">
							<label for="ntceEndde">종료일자</label><br>
				        	<input name="ntceEndde" id="defandroid" type="text"  value="<c:out value='${ntceEnddeVal}'/>" data-role="datebox" data-options='{"mode": "datebox"}'>
						</div>
					</c:when>
					<c:otherwise>
						<form:hidden path="ntceBgnde" value="10000101"/>
						<form:hidden path="ntceEndde" value="99991231"/>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${anonymous == 'true'}">
						<div class="uss-regist" data-inline="true">
							<label for="ntcrNm"><strong>작성자</strong></label><br>
			        		<input name="ntcrNm" type="text" id="ntcrNm" maxlength="10" size="20" value="<c:out value='${boardVO.ntcrNm}'/>">
						</div>
						<div class="uss-regist" data-inline="true">
							<label for="password"><strong>비밀번호</strong></label><br>
				        	<input name="password" type="password" id="password" maxlength="20" autocomplete="off">
						</div>
					</c:when>
					<c:otherwise>
						<form:hidden path="ntcrNm" value="dummy" /> <!-- validator 처리를 위해 지정 -->
						<form:hidden path="password" value="dummy" /> <!-- validator 처리를 위해 지정 -->
				 	</c:otherwise>
			  	</c:choose>

				<!-- 파일첨부  -->
				<c:choose>
					<c:when test="${not empty boardVO.atchFileId}">
						<spring:message code="cop.atchFileList" />
						<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/cop/bbs${prefix}/editBoardArticle.mdo"/>
						<c:import url="/cmm/fms/editFileInfs.mdo" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${boardVO.atchFileId}" />
						</c:import>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="atchFileId" value="">
						<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
				 	</c:otherwise>
			  	</c:choose>
				
				<c:if test="${boardVO.boardMasterVO.fileAtchPosblAt == 'Y'}">
					<strong><spring:message code="cop.atchFile" /></strong>
					<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="<c:out value='${boardVO.boardMasterVO.posblAtchFileNumber}'/>" />
			
					<div id="file_upload_posbl" style="display:none;">
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
					</div>
			
					<div id="file_upload_imposbl"  style="display:none;">
			        	<spring:message code="common.imposbl.fileupload" />
					</div>
			  	</c:if>
			</div>
    	</form:form> 

		<div class="ui-grid-a addBgBtn">
			<c:if test="${editAuthFlag == 'Y'}">
			<div class="ui-block-a">
                <input type="button" value="저장" onclick="javascript:fn_aram_update_notice(); return false;" data-icon="plus" data-theme="b" data-ajax="false" >
			</div>
 			</c:if>
 			<div class="ui-block-b">
                <input type="button" value="목록" onclick="javascript:fn_aram_list_notice(); return false;" data-icon="plus" data-theme="b" data-ajax="false" >
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
	fn_edit_FileAttachment();
</script>
</c:if>
<!--  첨부파일 업로드 가능화일 설정 End. -->
	
</body>
</html>