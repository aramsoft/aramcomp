<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : PhotoRegist.java
  * @Description : 사진 등록
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
	<h2>사진 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- ------------------------------------ 상단타이틀(파일첨부를 위한 폼명 및 Enctype 설정 ---------------------------->
<form:form commandName="photoVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<table class="table-register">
	<tr> 
		<th width="20%">
			<span class="required_icon"></span>
			사진 제목
		</th>
		<td width="80%">  
			<form:input path="photoSj" size="70" maxlength="100" />
			<form:errors path="photoSj" cssClass="error"/>      	  	      	      	
		</td>
	</tr>
   
	<!-------------------- 첨부파일 테이블 레이아웃 설정 Start..-------------------------------------------->
	<tr>
		<th>
			<span class="required_icon"></span>
			사진 파일
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="1" />  
			<table>
				<tr>
					<td>
						<input name="file_1" id="egovComFileUploader" type="file" title="첨부파일 입력" />
					</td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
					</td>
				</tr>
			</table>		      
		</td>
	</tr>
  <!-------------------- 첨부파일 테이블 레이아웃 End.--------------------------------------------------->
</table>

</form:form>
	
</div>	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="photoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
	fn_init_FileAttachment();
</script> 

<script type="text/javaScript" >
	
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
function fn_aram_insert(){
    var varForm = document.getElementById("photoVO");
	var aa = document.getElementById("egovComFileUploader");
	var bb = document.getElementById("egovComFileList");
	
	// 파일 Validator
	var newFileNum;		
	if(document.getElementById('egovComFileList') != null){
		newFileNum = document.getElementById('egovComFileList').childNodes.length;
	}else{
		newFileNum = 0;
	}
	if(newFileNum <= 0){
		alert("첨부파일은 필수 입력입니다.");
		return;
	}
	
	if (!validatePhotoVO(varForm)) {
		return;	
	} 
	
	if (confirm("<spring:message code='common.regist.msg' />")) {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/mpa/insertPhoto.do";
		varForm.submit();
	} 		
}

</script>
