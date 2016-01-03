<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : PhotoEdit.java
  * @Description : 사진 수정
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
	<h2>사진 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 상단타이틀(파일첨부를 위한 폼명 및 Enctype 설정 -->
<form:form commandName="photoVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="사진정보 수정테이블.">
  	<tr> 
	    <th width="20%">
	    	<label id="photoSj" for="photoSj">사진 제목</label>
			<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	        <form:input path="photoSj" size="70" maxlength="70" />
	        <form:errors path="photoSj" cssClass="error"/>                               
	    </td>
  	</tr>
  	<!--첨부목록을 보여주기 위한 -->  
	<c:choose>
	<c:when test="${photoVO.atchFileId != ''}">
    <tr> 
        <th>
        	사진 파일
			<span class="required_icon"></span>
        </th>
        <td>
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/mbl/com/mpa/editPhoto.do"/>      
			<c:import url="/content/files/${photoVO.atchFileId}/editform" />
        </td>
    </tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
  
		<!--첨부화일 업로드를 위한 Start -->
	<tr> 
		<th>
			<label for="egovComFileUploader">파일 수정</label>
			<span class="norequired_icon"></span>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="1" />  
      
			<div id="file_upload_posbl"  style="display:none;">    
			<table>
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file"></td>
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
	<!--첨부화일 업로드를 위한 end.. -->
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->

</form:form>
	
</div>	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="photoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
	fn_edit_FileAttachment();
</script>   

<script type="text/javaScript">

/* ********************************************************
 * 초기화
 ******************************************************** */
 window.onload = function(){

    // 첫 입력란에 포커스..
    var varForm = document.getElementById("photoVO");
    varForm.photoSj.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("photoVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/mpa/listPhoto.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("photoVO");
	// 파일 Validator
	var existFileNum;
	var newFileNum;
	if (document.getElementById('fileListCnt') != null) {
        existFileNum = document.getElementById('fileListCnt').value;
	}else{
		existFileNum =0;
	}
	
	if(document.getElementById('egovComFileList') != null){
		newFileNum = document.getElementById('egovComFileList').childNodes.length;
	}else{
		newFileNum = 0;
	}
	if((existFileNum+newFileNum) <= 0){
		alert("첨부파일은 필수 입력입니다.");
		return;
	}
	
	if (!validatePhotoVO(varForm)) {
		return;				
	} 
	
	if (confirm("<spring:message code='common.update.msg' />")) {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/mpa/updatePhoto.do";
		varForm.submit();
	}
}

</script>
