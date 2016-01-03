<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : RequestOfferEdit.jsp
  * @Description : 지식 정보제공/정보요청 수정
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
	<h2>지식 정보제공/정보요청 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 파일첨부를 위한 폼명 및 Enctype 설정 -->
<form:form commandName="requestOfferVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<form:hidden path="parentsKnoId" />
<form:hidden path="threadDepth" />
<form:hidden path="threadNo" />
<form:hidden path="threadGroupNo" />

<!--  등록  폼 영역-대상서비스/서비스TABLE/헤더 Tag 정보  -->
<table class="table-register" summary="이 표는 지식 정보제공/정보요청  정보를 제공하며, 조직명, 지식유형명, 지식명, 지식내용, 첨부파일  정보로 구성되어 있습니다 .">
<caption>지식 정보제공/정보요청 수정</caption>
	<tr> 
		<th width="20%">
    		<span class="required_icon"></span>
			조직명
		</th>
		<td>
			<select name="orgnztId" class="select" title="조직명" onChange="javascript:fn_aram_get_codeId()">
				<c:forEach var="result" items="${mapTeamList}" varStatus="status">
					<option value='<c:out value="${result.orgnztId}"/>' <c:if test="${result.orgnztId == knoPersonalVO.orgnztId}">selected="selected"</c:if>><c:out value="${result.orgnztNm}"/></option>
				</c:forEach>			  		   
			</select>
		</td>
	</tr>
	<tr> 
		<th>
    		<span class="required_icon"></span>
			지식유형명
		</th>
		<td>
			<select name="knoTypeCd" class="select" title="조직명">
				<c:forEach var="result" items="${mapMaterialList}" varStatus="status">
					<option value='<c:out value="${result.knoTypeCd}"/>'><c:out value="${result.knoTypeNm}"/></option>
				</c:forEach>			  		   
			</select>
		</td>
	</tr>
	<tr> 
		<th>
    		<span class="required_icon"></span>
			지식명
		</th>
		<td>
			<form:input path="knoNm" size="73" title="지식명" cssClass="txInput" maxlength="255"/>
			<form:errors path="knoNm" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
    		<span class="required_icon"></span>
			지식내용
		</th>
		<td>
			<form:textarea path="knoCn" title="지식내용" rows="5" cols="20" cssClass="txArea"/>
			<form:errors path="knoCn" cssClass="error"/>
		</td>
	</tr>

	<!-- 첨부파일 테이블 레이아웃 설정 Start -->
	<c:choose>
	<c:when test="${requestOfferVO.atchFileId ne ''}">
	<tr> 
		<th>
    		<span class="norequired_icon"></span>
			첨부파일목록
		</th>
		<td>
			<input type="hidden" name="returnUrl" value="/dam/spe/req/updtRequestOffer.do">
			<c:import url="/content/files/${requestOfferVO.atchFileId}/editform" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
	<!-- 첨부파일 테이블 레이아웃 End -->
 
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
			        <td><div id="egovComFileList"></div></td>
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
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="requestOfferVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("requestOfferVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/req/listRequestOffer.do";
    varForm.submit();
}

/* ********************************************************
* 저장
******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("requestOfferVO");

    if(!validateRequestOfferVO(varForm)){ 			
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/spe/req/updateRequestOffer.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 지식유형 가져오기
 ******************************************************** */
function fn_aram_get_CodeId(form){
    var varForm = document.getElementById("requestOfferVO");
    varForm.submit();
}

</script>
