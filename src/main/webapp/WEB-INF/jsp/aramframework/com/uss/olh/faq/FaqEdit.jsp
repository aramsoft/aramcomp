<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : FaqEdit.jsp
  * @Description : FAQ 수정
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
  **/
%>
<DIV id="main">

<div class="content_title">
	<h2>FAQ 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="faqManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="faqId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="FAQ에 대한 정보를 수정합니다.">
<caption>FAQ내용수정</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="qestnSj">질문제목</label>
    	</th>
    	<td width="80%">
        	<form:input path="qestnSj" size="70" maxlength="70" title="질문제목"/>
    		<form:errors path="qestnSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnCn">질문내용</label>
    	</th>
    	<td>
      		<form:textarea path="qestnCn" cols="300" rows="15" cssClass="txArea"  title="질문내용"/>
      		<form:errors path="qestnCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="answerCn">답변내용</label>
    	</th>
    	<td>
      		<form:textarea path="answerCn" cols="300" rows="15" cssClass="txArea"  title="답변내용"/>
      		<form:errors path="answerCn" cssClass="error"/>
    	</td>
  	</tr>
 	<!-- 첨부목록을 보여주기 위한 -->
	<c:choose>
	<c:when test="${faqManageVO.atchFileId != ''}">
	<tr>
		<th>
    		<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
		<td>
			<!-- 첨부파일 삭제 후 리턴 URL -->
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/uss/olh/faq/editFaq.do"/>
			<c:import url="/content/files/${faqManageVO.atchFileId}/editform" />
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
    		<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />  
			<div id="file_upload_posbl">
	        <table>
		    	<tr>
		        	<td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부" /></td>
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
 	<!-- 첨부화일 업로드를 위한 end..-->
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="faqManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!-- 첨부파일 업로드 가능화일 설정(Update) Start..-->
<script type="text/javascript">
	fn_edit_FileAttachment();
</script>
<!-- 첨부파일 업로드 가능화일 설정(Update) End.-->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("faqManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/faq/listFaq.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("faqManageVO");
    
	if (!validateFaqManageVO(varForm)) {
		return;
	} 

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/faq/updateFaq.do";
		varForm.submit();
	}
}

</script>

