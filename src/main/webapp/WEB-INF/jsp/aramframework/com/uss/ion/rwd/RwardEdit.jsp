<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name  : RwardEdit.jsp
 * @Description : 포상 수정
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
	<h2>포상 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="rwardManageVO" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="rwardId"/>
<form:hidden  path="rwardManId"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="sanctnerId"/>

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>포상자</h2>
</div>

<table class="table-detail" summary="포상자 정보">
<caption>포상자정보</caption>
  	<tr>
    	<th width="20%">
    		포상자
    	</th>          
    	<td width="30%">
    		<c:out value='${rwardManageVO.rwardManNm}'/>
    	</td>
    	<th width="20%">
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${rwardManageVO.orgnztNm}'/>
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-register" summary="포상 수정">
<caption>포상 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		포상구분
    	</th>          
    	<td colspan="3">
      		<form:select path="rwardCd" title="포상구분">
	      		<form:options items="${COM055_rward}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
      	</td>
  	</tr>
  	<tr>
    	<th width="20%">
   		<span class="required_icon"></span>
    		<label for="rwardNm">포상명</label>
     	</th>          
    	<td width="30%">
    		<form:input  path="rwardNm" id="rwardNm" title="포상명"/>
    	</td>
    	<th width="20%">
    		<span class="required_icon"></span>
    		포상일
    	</th>          
    	<td width="30%">
      		<form:hidden path="rwardDe" />
	    	<c:if test="${!empty rwardManageVO.rwardDe}">
 				<c:set var="rwardDeVal" value="${fn:substring(rwardManageVO.rwardDe, 0,4)}-${fn:substring(rwardManageVO.rwardDe, 4,6)}-${fn:substring(rwardManageVO.rwardDe, 6,8)}"/>
      		</c:if>
      		<input name="rwardDeView" id="rwardDeView" type="text" size="10" title="포상일자" value="${rwardDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].rwardDe, document.forms[0].rwardDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="pblenCn">공적사항</label>
    	</th>          
    	<td colspan="3">
      		<form:textarea path="pblenCn" rows="4" cols="70" cssClass="txArea" title="공적사항"/>
      		<form:errors path="pblenCn" cssClass="error"/>
    	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
	<c:choose>
	<c:when test="${rwardManageVO.atchFileId ne null && rwardManageVO.atchFileId ne ''}">
	<tr> 
		<th>
			<span class="norequired_icon"></span>
			첨부파일목록
		</th>
		<td colspan="3">
			<input type="hidden" name="returnUrl" value="/uss/ion/rwd/detailRward.do" />
			<c:import url="/content/files/${rwardManageVO.atchFileId}/editform" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
	<!-- 첨부파일 테이블 레이아웃 End.-->
  	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
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
	<!-- 첨부파일 테이블 레이아웃 End.-->
</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"> 
	<jsp:param name="infrmlSanctnId" value="${rwardManageVO.infrmlSanctnId}"/>
</jsp:include>
<!-- //결재권자 정보 Include -->

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="rwardManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!-- 첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("rwardManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/listRward.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update() {
    var varForm = document.getElementById("rwardManageVO");
    
    if(!validateRwardManageVO(varForm)){           
        return;
    }
    
	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/updateRward.do";
        varForm.submit();
    }
}

</script>
