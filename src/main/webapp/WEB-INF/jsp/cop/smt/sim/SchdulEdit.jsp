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
 * @Class Name : SchdulEdit.jsp
 * @Description : 일정 수정
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
	<h2>일정 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="schdulManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="schdulId" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	일정구분
	    </th>
	    <td width="80%">
	        <form:select path="schdulSe">
	            <form:option value="" label="선택"/>
	            <form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
	        <form:errors path="schdulSe" cssClass="error"/>
	    </td>
	</tr>   
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	중요도
	    </th>
	    <td>
	        <form:select path="schdulIpcrCode">
	            <form:option value="" label="선택"/>
	            <form:options items="${COM019_schdulIpcr}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
	        <form:errors path="schdulIpcrCode" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	일정명
	    </th>
	    <td>
	      	<form:input path="schdulNm" size="73" cssClass="txInput" maxlength="255" />
	      	<form:errors path="schdulNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	일정 내용
	    </th>
	    <td>
	        <form:textarea path="schdulCn" rows="3" cols="20" cssClass="txArea"/>
	    	<form:errors path="schdulCn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.othbcScope" />
	    </th>
	    <td>
	     	<input type="radio" name="othbcScope" id="othbcScopeP" class="radio2" value="P"<c:if test="${schdulManageVO.othbcScope == 'P'}"> checked</c:if> <c:if test="${writer != true}"> disabled</c:if>><label for="othbcScopeP">개인</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeC" class="radio2" value="C"<c:if test="${schdulManageVO.othbcScope == 'C'}"> checked</c:if> <c:if test="${writer != true}"> disabled</c:if>><label for="othbcScopeC">커뮤니티</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeG" class="radio2" value="G"<c:if test="${schdulManageVO.othbcScope == 'G'}"> checked</c:if> <c:if test="${writer != true}"> disabled</c:if>><label for="othbcScopeG">전체</label>
	     	<form:errors path="othbcScope" cssClass="error"/> 
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	반복구분
	    </th>
	    <td>
	       	<form:radiobutton path="reptitSeCode" value="1" />당일
	       	<form:radiobutton path="reptitSeCode" value="2"/>반복
	       	<form:radiobutton path="reptitSeCode" value="3"/>연속
	       	<form:errors path="reptitSeCode" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	날짜/시간
	    </th>
	    <td>
			<input type="hidden" name="schdulBgnde" id="schdulBgnde" value="" />
	    	<form:input path="schdulBgndeYYYMMDD" size="10" readonly="true" maxlength="10" />
	        <a href="#" onclick="javascript:fn_aram_NormalCalendar('', document.forms[0].schdulBgndeYYYMMDD); return false;">
	    		<img src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
	    	</a>
	        <form:select path="schdulBgndeHH">
	            <form:options items="${schdulListHH}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>시
	        <form:select path="schdulBgndeMM">
	            <form:options items="${schdulListMM}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>분
	        
		    &nbsp;&nbsp;~&nbsp;&nbsp;
		    
			<input type="hidden" name="schdulEndde" id="schdulEndde" value="" />
		    <form:input path="schdulEnddeYYYMMDD" size="10" readonly="true" maxlength="10" />
		    <a href="#" onclick="javascript:fn_aram_NormalCalendar('', document.forms[0].schdulEnddeYYYMMDD); return false;">
		    	<img src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">
		    </a>&nbsp;
	        <form:select path="schdulEnddeHH">
	            <form:options items="${schdulListHH}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>시
	        <form:select path="schdulEnddeMM">
	            <form:options items="${schdulListMM}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>분
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	담당자
	    </th>
	    <td>
			<form:input path="schdulChargerName" size="73" cssClass="txInput" readonly="true" maxlength="10" />
			<a href="#" title="새 창으로 이동"  onClick="fn_aram_get_schdulCharger('담당자', 'schdulChargerId', 'schdulChargerName'); return false;">
				<img src="${pageContext.request.contextPath}/images/cmm/icon/search.gif" align="middle" style="border:0px" alt="담당자" title="담당자">
			</a>
		    <form:hidden path="schdulChargerId" />
			<form:errors path="schdulChargerName" cssClass="error"/>
	    </td>
	</tr>
	<!--첨부목록을 보여주기 위한-->
	<c:choose>
	<c:when test="${schdulManageVO.atchFileId ne null && schdulManageVO.atchFileId ne ''}">
	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
		<td>
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/cop/smt/sim/editSchdul.do"/>
			<c:import url="/files/${schdulManageVO.atchFileId}/edit" />
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
			파일첨부
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

		    <div id="file_upload_posbl"  style="display:none;">
	            <table>
				    <tr>
				        <td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
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
 	<!--첨부화일 업로드를 위한 end.. -->
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchSchdulSe" value="" />
<form:hidden path="year" value="" />
<form:hidden path="month" value="" />
<form:hidden path="week" value="" />
<form:hidden path="day" value="" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="schdulManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cmm/fms/MultiFile.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

var gOpener = parent.document.all? parent.document.communityVO : parent.document.getElementById("communityVO") ;

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("schdulManageVO");
	if( gOpener != null) {
		varForm.trgetId.value = gOpener.cmmntyId.value;
	}
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("schdulManageVO");

    if(!validateSchdulManageVO(varForm)){
		return;
	}
	
	if( varForm.othbcScopeC.checked == true ) {
		if( gOpener == null) {
			alert("커뮤니티 ID를 얻을 수 없습니다. ");
			return;
		} else {	
			varForm.trgetId.value = gOpener.cmmntyId.value;
		}
	}
	
	var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
	var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;

	varForm.schdulBgnde.value = schdulBgndeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('schdulBgndeHH') +  fn_aram_SelectBoxValue('schdulBgndeMM');
	varForm.schdulEndde.value = schdulEnddeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('schdulEnddeHH') +  fn_aram_SelectBoxValue('schdulEnddeMM');

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/updateSchdul.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_aram_get_schdulCharger(strTitle, empId, empName){
	gArguments["title"]    = strTitle;
	if( empId != "" )    gArguments["userId"]   = document.getElementById(empId);
	if( empName != "" )  gArguments["emplyrNm"] = document.getElementById(empName);

	var url = "/cop/smt/sim/listEmplyrPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>


