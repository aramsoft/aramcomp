<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : KnoPersonalEdit.jsp
  * @Description : 개인지식 수정
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
	<h2>개인지식 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 파일첨부를 위한 폼명 및 Enctype 설정 -->
<form:form commandName="knoPersonalVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="orgnztId"/>
<form:hidden path="knoTypeCd"/>
<form:hidden path="knoId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 개인지식관리 정보를 제공하며, 조직명, 지식유형명, 지식명, 지식내용, 수집일자, 공개여부, 파일첨부 정보로 구성되어 있습니다 .">
<caption>개인지식관리 수정</caption>
	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		조직명
    	</th>
 		<td>
 			${knoPersonalVO.orgnztNm}
 		</td>
  	</tr>
	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지식유형명
    	</th>
 		<td>
 			${knoPersonalVO.knoTypeNm}
 		</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지식명
    	</th>
    	<td>
      		<form:input  path="knoNm" title="지식명" size="60" maxlength="60"/>
      		<form:errors path="knoNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지식내용
    	</th>
    	<td>
      		<textarea name="knoCn" class="textarea" title="지식내용" cols="300" rows="10" style="width:450px;">${knoPersonalVO.knoCn}</textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		수집일자
    	</th>
    	<td>
      		<form:hidden path="colYmd" />
	    	<c:if test="${!empty knoPersonalVO.colYmd}">
 				<c:set var="colYmdVal" value="${fn:substring(knoPersonalVO.colYmd, 0,4)}-${fn:substring(knoPersonalVO.colYmd, 4,6)}-${fn:substring(knoPersonalVO.colYmd, 6,8)}"/>
      		</c:if>
      		<input name="colYmdView" id="colYmdView" type="text" size="10" title="수집일자" value="${colYmdVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].colYmd, document.forms[0].colYmdView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	  		<form:errors path="colYmd" cssClass="error"/>
    	</td>
  	</tr>
	<tr>
 		<th>
    		<span class="norequired_icon"></span>
 			공개여부
 		</th>
 		<td>
     		<spring:message code="cop.public" /> : <input type="radio" name="othbcAt" class="radio2" value="Y"
     		<c:if test="${knoPersonalVO.othbcAt == 'Y'}"> checked="checked"</c:if> />&nbsp;
     		<spring:message code="cop.private" /> : <input type="radio" name="othbcAt" class="radio2" value="N"
     		<c:if test="${knoPersonalVO.othbcAt == 'N'}"> checked="checked"</c:if> />
     		<form:errors path="othbcAt" cssClass="error"/>
 		</td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start -->
	<c:choose>
	<c:when test="${knoPersonalVO.atchFileId ne ''}">
	<tr>
		<th>
   			<span class="norequired_icon"></span>
			첨부파일목록
 		</th>
		<td>
			<input type="hidden" name="returnUrl" value="/dam/per/editKnoPersonal.do">
			<c:import url="/content/files/${knoPersonalVO.atchFileId}/editform" />
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
<validator:javascript formName="knoPersonalVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("knoPersonalVO");
    varForm.knoNm.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("knoPersonalVO");
    varForm.action = "${pageContext.request.contextPath}/dam/per/listKnoPersonal.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("knoPersonalVO");

    if(!validateKnoPersonalVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "/dam/per/updateKnoPersonal.do";
		varForm.submit();
	}
}

</script>
