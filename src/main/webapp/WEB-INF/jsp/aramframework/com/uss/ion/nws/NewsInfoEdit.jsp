<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : NewsInfoEdit.jsp
  * @Description : 뉴스정보 수정
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
	<h2>뉴스정보 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="newsManageVO" action="" method="post"  enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="newsId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="뉴스정보 수정테이블.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="newsSj">뉴스제목</label>
    	</th>
	    <td width="80%">
	        <form:input path="newsSj" size="70" maxlength="70" />
	        <form:errors path="newsSj" cssClass="error"/>
	    </td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="newsCn">뉴스내용</label>
    	</th>
    	<td>
      		<form:textarea path="newsCn" cols="300" rows="30" cssClass="txArea" />
      		<form:errors path="newsCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="newsOrigin">뉴스출처</label>
    	</th>
    	<td>
			<form:input path="newsOrigin" size="60" maxlength="60" title="뉴스 출처 입력" />
    	</td>
  	</tr>

  	<tr>
		<th>
    		<span class="norequired_icon"></span>
			게시일자
		</th>
		<td>
      		<form:hidden path="ntceDe" />
	    	<c:if test="${!empty newsManageVO.ntceDe}">
 				<c:set var="ntceDeVal" value="${fn:substring(newsManageVO.ntceDe, 0,4)}-${fn:substring(newsManageVO.ntceDe, 4,6)}-${fn:substring(newsManageVO.ntceDe, 6,8)}"/>
      		</c:if>
      		<input name="ntceDeView" id="ntceDeView" type="text" size="10" title="게시일자 입력" value="${ntceDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceDe, document.forms[0].ntceDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="ntceDe" cssClass="error"/>
		</td>
   	</tr>

 <!--첨부목록을 보여주기 위한 -->
	<c:choose>
	<c:when test="${newsManageVO.atchFileId != ''}">
    <tr>
        <th>
    		<span class="norequired_icon"></span>
        	첨부파일 목록
        </th>
        <td>
			<!-- 첨부파일 삭제 후 리턴 URL -->
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/uss/ion/nws/editNewsInfo.do"/>
			<c:import url="/content/files/${newsManageVO.atchFileId}/editform" />
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
    		<label for="egovComFileUploader">파일첨부</label>
    	</th>
        <td colspan="3">
        
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

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
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="newsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<!--첨부파일 업로드 가능화일 설정(Update) Start..-->
<script type="text/javascript">
	fn_edit_FileAttachment();
</script>
<!--첨부파일 업로드 가능화일 설정(Update) End.-->

<script type="text/javascript">

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("newsManageVO");
	
    if (!validateNewsManageVO(varForm)) {
        return;
    }
    
    if (confirm("<spring:message code='common.update.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/updateNewsInfo.do";
        varForm.submit();
    }
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("newsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/listNewsInfo.do";
    varForm.submit();
}

</script>
</head>

