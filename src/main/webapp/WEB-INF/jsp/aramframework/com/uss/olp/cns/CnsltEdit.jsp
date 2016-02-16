<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : CnsltEdit.jsp
  * @Description : 상담 수정
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
	<h2>상담 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<!--  등록  폼 영역  -->
<form:form commandName="cnsltManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cnsltId" />

<c:choose>
	<c:when test="${cnsltManageVO.managtCn == null}">
	<input name="managtCn" type="hidden" value="미답변">
	</c:when>
	<c:otherwise>
	<input name="managtCn" type="hidden" value="<c:out value='${cnsltManageVO.managtCn}'/>">
	</c:otherwise>
</c:choose>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="상담에 대한 정보를 수정합니다.">
<caption>상담 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		작성자명
    	</th>
    	<td width="80%">
    		<form:input path="wrterNm" size="20" maxlength="20" title="작성자명"/>
    		<form:errors path="wrterNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		작성 비밀번호
    	</th>
    	<td>
    		<form:password path="writngPassword" value="${cnsltManageVO.writngPassword}" size="20" maxlength="20" title="작성 비밀번호" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		전화번호
    	</th>
    	<td>
        	<form:input path="areaNo" size="4" maxlength="4" title="전화번호(지역)"/>-
        	<form:input path="middleTelno" size="4" maxlength="4" title="전화번호(국번)"/>-
        	<form:input path="endTelno" size="4" maxlength="4" title="전화번호(지번)"/>
    		<form:errors path="areaNo" cssClass="error"/>
    		<form:errors path="middleTelno" cssClass="error"/>
    		<form:errors path="endTelno" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		휴대폰전화번호
    	</th>
    	<td>
			<form:input path="firstMoblphonNo"  size="4" maxlength="4" title="휴대폰전화번호(앞번)" />-
			<form:input path="middleMbtlnum" 	size="4" maxlength="4" title="휴대폰전화번호(국번)" />-
			<form:input path="endMbtlnum" 		size="4" maxlength="4" title="휴대폰전화번호(지번)" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		이메일
    	</th>
    	<td>
    		<form:input path="emailAdres" 	size="30" maxlength="30" title="이메일" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:checkbox path="emailAnswerAt" value="Y" title="이메일답변여부" /> 이메일답변여부
    	</td>
  	</tr>
  	<tr>
    	<th>
      		<span class="required_icon"></span>
    		상담제목
    	</th>
    	<td>
        	<form:input path="cnsltSj" size="70" maxlength="70"  title="상담제목"/>
    		<form:errors path="cnsltSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
   			상담내용
    	</th>
    	<td>
      		<form:textarea path="cnsltCn" cols="300" rows="20" cssClass="txArea" title="상담내용"/>
      		<form:errors path="cnsltCn" cssClass="error"/>
    	</td>
  	</tr>
 	<!-- 첨부목록을 보여주기 위한 -->
	<c:choose>
	<c:when test="${cnsltManageVO.atchFileId != ''}">
	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
		<td>
			<!-- 첨부파일 삭제 후 리턴 URL -->
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/uss/olp/cns/editCnslt.do"/>
			<c:import url="/content/files/${cnsltManageVO.atchFileId}/editform" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
  	
 	<!-- 첨부화일 업로드를 위한 Start -->
  	<tr>
		<th>
    		<span class="norequired_icon"></span>
			파일첨부
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />  
			<div id="file_upload_posbl"  style="display:none;">
		    <table>
			    <tr>
			        <td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/></td>
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
 	<!-- 첨부화일 업로드를 위한 end.. -->
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
<validator:javascript formName="cnsltManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!-- 첨부파일 업로드 가능화일 설정(Update) Start.. -->
<script type="text/javascript">
	fn_edit_FileAttachment();
</script>
<!-- 첨부파일 업로드 가능화일 설정(Update) End. -->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/listCnslt.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("cnsltManageVO");
    
	if (!validateCnsltManageVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/updateCnslt.do";
		varForm.submit();
	}
}

</script>

