<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : MmsAttachFileRegist.jsp
 * @Description : 첨부파일 등록
 * @Modification Information
 * @
 * @ 수정일                    수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2011.08.29   조재만          최초 생성
 *
 *  @author 모바일 신규공통컴포넌트개발팀 조재만
 *  @since 2011.08.29
 *  @version 1.0 
 *  @see
 *  
 */
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/mbl/mcomd/egovMcomdAdmin.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="atchFileVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

function fn_aram_select_mmsAttachFileList(pageNo) {
	document.atchFile.action = "${pageContext.request.contextPath}/mbl/com/mms/selectMmsAttachFileList.mdo";
	document.atchFile.submit();
}

function fn_aram_insert_mmaAttachFile(){
	
	if(!validateAtchFileVO(document.atchFile)) {
		return;
	}

	if (!fn_aram_check_fileType(document.getElementById("egovComFileUploader").value)){
		alert("jpq, sis, mmf, k3g, skm 파일만 업로드 가능합니다.");
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />"))    {  
    	document.atchFile.action = "${pageContext.request.contextPath}/mbl/com/mms/insertMmsAttachFile.mdo";
    	document.atchFile.submit();
	}	
}

// 파일 확장자 체크 함수
function fn_aram_check_fileType(fileNm) {
	var lastIndex = fileNm.lastIndexOf('.');
	var fileType;

	if (lastIndex != -1) {
		fileType = fileNm.substring(lastIndex + 1, fileNm.len);

		if (fileType != 'jpg' && fileType != 'sis' && fileType != 'mmf' && fileType != 'k3g' && fileType != 'skm') {
			return false;
		}
	} else {
		return false;
	}

	return true;
}

// 첨부파일 크기 validation check
function fn_aram_check_fileValidation(flag) {
	if (flag == "movieFalse") {
		alert("300KB가 넘는 동영상 파일을 첨부할 수 없습니다.");
	} else if (flag == "otherFalse") {
		alert("20KB가 넘는 파일을 첨부할 수 없습니다.");
	}
}
	
</script>
<title>MMS 첨부파일 등록</title>

</head>

<body onload="javascript:fn_aram_check_fileValidation('<c:out value="${fileValidation}"/>');">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	
<div>

	<!-- header Start -->
	<div id="header">
		<a href="${pageContext.request.contextPath}/mindex.jsp"><span class="btnHome"></span></a>
		<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/h1_logo.png" /></h1>
		<a href="${pageContext.request.contextPath}/mbl/com/mms/selectMmsAttachFileList.mdo"><span class="btnBack"></span></a>
	</div>
	<!-- header End -->
	
	<div id="content" class="contents2">
		
		<form name="atchFile" action="" method="post" enctype="multipart/form-data"> 
		
			<table width="100%" cellpadding="8" class="table-search" border="0">
			 	<tr>
			  		<td width="100%"class="title_left">
			   			<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align: middle" alt="">
			   			&nbsp;MMS 첨부파일 - 등록
			   		</td>
			 	</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="MMS 첨부파일 정보를 등록합니다.">
			<tbody>
			  	<tr> 
				    <th width="20%"  class="required_text">제목
				    	<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
				    </th>
				    <td width="80%">
				    	<input name="mmsAtchFileSj" type="text" style="width:50%;height:100%;" value='<c:out value="${attachFile.mmsAtchFileSj}"/>' />
				    </td>
			  	</tr>
			  	<tr> 
				    <th width="20%"  class="required_text">첨부파일
				    	<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
				    </th>
				    <td width="80%">
						<!-- ----------------------------- 첨부파일 갯수를 위한 hidden --------------------------->	
						<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="1" />  
				    	<input name="file_1" id="egovComFileUploader" type="file" style="width:50%;height:100%;" title="첨부파일 입력" />
				    </td>
			  	</tr>
			</tbody>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  	<tr> 
			    	<td height="10"></td>
			  	</tr>
			</table>
			
		  	<div align="center">
			<table border="0" cellspacing="0" cellpadding="0" align="center">
				<tr> 
					<td>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/button/bu2_left.gif" width="8" height="20" alt="등록버튼이미지">
					</td>
					<td background="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/button/bu2_bg.gif" class="text_left">
						<a href="JavaScript:fn_aram_insert_mmaAttachFile();">등록</a>
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/button/bu2_right.gif" width="8" height="20" alt="등록버튼이미지">
					</td>
					<td>&nbsp;</td>  
					<td>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/button/bu2_left.gif" width="8" height="20" alt="목록버튼이미지">
					</td>
					<td background="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/button/bu2_bg.gif" class="text_left">
						<a href="javascript:fn_aram_select_mmsAttachFileList('<c:out value='${searchVO.pageIndex}'/>');">목록</a> 
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		      			<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>">
		      			<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>">
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/button/bu2_right.gif" width="8" height="20" alt="목록버튼이미지">
					</td>              
				</tr>
			</table>
			</div>
		</form>
	</div>
	
	<!-- footer Start-->
	<div id="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer End -->
	
</div>	
</body>
</html>