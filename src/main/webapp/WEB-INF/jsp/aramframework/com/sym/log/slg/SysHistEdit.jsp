<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : SysHistEdit.jsp
  * @Description : 시스템 이력 수정
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>시스템 이력 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>시스템 이력수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="sysHistoryVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="histId" />

<table class="table-register" summary="이력구분, 시스템명, 이력내용, 첨부파일목록을 수정하는 시스템 이력 수정 테이블이다.">
<CAPTION>시스템 이력 수정</CAPTION>
 	<tr>
   		<th width="20%">
   			<span class="required_icon"></span>
   			<spring:message code="sym.log.histSeCode" scope="row" />
   		</th>
   		<td width="80%">
    		<form:select path="histSeCode" class="select" title="<spring:message code='sym.log.histSeCode' />">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM002_histSe}" itemValue="code" itemLabel="codeNm"/>
	   		</form:select>
   			<form:errors path="histSeCode" cssClass="error"/>
   		</td>
 	</tr>
	<tr>
   		<th>
   			<span class="required_icon"></span>
   			<label for="sysNm"><spring:message code="sym.log.sysNm" /></label>
   		</th>
   		<td>
      		<form:input path="sysNm" size="60" maxlength="60" />
     		<form:errors path="sysNm" cssClass="error"/>
   		</td>
 	</tr>
 	<tr>
   		<th>
   			<span class="required_icon"></span>
   			<label for="histCn"><spring:message code="sym.log.histCn" /></label>
   		</th>
   		<td>
      		<form:textarea path="histCn" class="textarea"  cols="50" rows="8"  style="width:450px;" />
     		<form:errors path="histCn" cssClass="error"/>
   		</td>
 	</tr>
	<tr>
   		<th>
    		<span class="norequired_icon"></span>
   			등록일자
   		</th>
   		<td>
     		<fmt:formatDate value="${sysHistoryVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
   		</td>
 	</tr>
	<!-- 첨부목록 -->  
	<c:choose>
	<c:when test="${sysHistoryVO.atchFileId != ''}">
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<spring:message code="sym.log.atchFileList" />
    	</th>
    	<td>
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/sym/log/slg/editSysHistory.do"/>
			<c:import url="/content/files/${sysHistoryVO.atchFileId}/editform" />
    	</td>
  	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
  	
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<spring:message code="sym.log.atchFile" />
    	</th>
    	<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	    	<div id="file_upload_posbl"  style="display:none;">
	        <table>
			    <tr>
			        <td><input name="file_1" id="egovComFileUploader" type="file" title="<spring:message code="sym.log.atchFile" />"/></td>
			    </tr>
			    <tr>
			        <td>
			        	<div id="egovComFileList"></div>
			        </td>
			    </tr>
	  	    </table>
			</div>
	
			<div id="file_upload_imposbl"  style="display:none;">
	        	<spring:message code="common.imposbl.fileupload" />
			</div>
		</td>	
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="sysHistoryVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/listSysHistory.do";
    varForm.submit();
}

function fn_aram_update(){
    var varForm = document.getElementById("sysHistoryVO");

    if(!validateSysHistoryVO(varForm)){
        return;
    }
    
	if (confirm("<spring:message code='common.update.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/sym/log/slg/updateSysHistory.do";
    	varForm.submit();
	}	
}

</script>

</body>
</html>
