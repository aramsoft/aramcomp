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
 * @Class Name : SchdulRegist.jsp
 * @Description : 일정관리 등록 페이지
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
	<h2>일정관리 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!--  등록  폼 영역  -->
<form:form modelAttribute="schdulManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="trgetId" value="${curTrgetId}" />
<table class="table-register">
	<tr>
	    <th width="20%">
	    	일정구분
	    	<span class="required_icon"></span>
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
	    	중요도
	    	<span class="required_icon"></span>
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
	    	일정명
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<form:input path="schdulNm" size="73" cssClass="txInput" maxlength="255" />
	      	<form:errors path="schdulNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	일정 내용
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	        <form:textarea path="schdulCn" rows="3" cols="20" cssClass="txArea"/>
	    	<form:errors path="schdulCn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<spring:message code="cop.othbcScope" />
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	     	<input type="radio" name="othbcScope" id="othbcScopeP" class="radio2" value="P" checked><label for="othbcScopeP">개인</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeC" class="radio2" value="C"><label for="othbcScopeC">커뮤니티</label>&nbsp;
	     	<input type="radio" name="othbcScope" id="othbcScopeG" class="radio2" value="G"><label for="othbcScopeG">전체</label>
	     	<form:errors path="othbcScope" cssClass="error"/> 
	    </td>
	</tr>
	<tr>
	    <th>
	    	반복구분
	    	<span class="required_icon"></span>
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
	    	날짜/시간
	    	<span class="required_icon"></span>
	    </th>
	    <td>
			<input type="hidden" name="schdulBgnde" id="schdulBgnde" value="" />
	    	<form:input path="schdulBgndeYYYMMDD" size="10" readonly="true" maxlength="10" />
		    <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].schdulBgndeYYYMMDD); return false;">
		    	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
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
		    <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].schdulEnddeYYYMMDD); return false;">
		    	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">
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
	    	담당자
	    	<span class="norequired_icon"></span>
	    </th>
	    <td>
			<form:input path="schdulChargerName" size="73" cssClass="txInput" readonly="true" maxlength="10" />
			<a href="#" title="새 창으로 이동"  onClick="fn_aram_get_schdulCharger('담당자', 'schdulChargerId', 'schdulChargerName'); return false;">
				<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="담당자" title="담당자">
			</a>
			<form:errors path="schdulChargerName" cssClass="error"/>
	    	<form:hidden path="schdulChargerId" />
	    </td>
	</tr>
	<!--  첨부파일 테이블 레이아웃 설정 Start..-->
	<tr>
		<th>
			파일첨부
			<span class="norequired_icon"></span>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
		    <table>
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
		   	</table>
		</td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 End.-->
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/fms/MultiFile.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!--  첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
   	document.getElementsByName('reptitSeCode')[0].checked = true;
   	if("${schdulManageVO.schdulBgnde}".length> 0){
	   	var schdulBgnde = "${schdulManageVO.schdulBgnde}";
	   	document.getElementById("schdulBgndeYYYMMDD").value = schdulBgnde.substring(0,4) + "-" + schdulBgnde.substring(4,6) + "-" + schdulBgnde.substring(6,8);
   	}
   	if("${schdulManageVO.schdulEndde}".length> 0){
	   	var schdulEndde = "${schdulManageVO.schdulEndde}";
	   	document.getElementById("schdulEnddeYYYMMDD").value = schdulEndde.substring(0,4) + "-" + schdulEndde.substring(4,6) + "-" + schdulEndde.substring(6,8);
   	}
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("schdulManageVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("schdulManageVO");

    if(!validateSchdulManageVO(varForm)){
		return;
	}
	
	var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
	var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;

	varForm.schdulBgnde.value = schdulBgndeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('schdulBgndeHH') +  fn_aram_SelectBoxValue('schdulBgndeMM');
	varForm.schdulEndde.value = schdulEnddeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('schdulEnddeHH') +  fn_aram_SelectBoxValue('schdulEnddeMM');

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/insertSchdul.do";
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


