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
 * @Class Name : LeaderSchdulEdit.jsp
 * @Description : 간부일정 수정 
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
	<h2>간부일정 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="leaderSchdulVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="schdulId" />

<table class="table-register"  summary="이 표는 간부일정정보를  수정하기 위한 표이며, 일정구분, 간부, 일정명, 일정내용, 일정장소, 반복구분, 날짜/시간, 담당자 정보로 구성되어 있습니다 .">
	<tr>
	    <th width="20%">
	    	<label for="schdulSe">일정구분</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	        <form:select path="schdulSe" title="일정구분 선택">
	            <form:option value="" label="선택"/>
	            <form:options items="${COM057_schdulSeLeader}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
	        <form:errors path="schdulSe" cssClass="error"/>
	    </td>
	 </tr>
	 <tr>
	    <th>
	    	<label for="leaderName">간부</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
			<form:input path="leaderName" size="15" readonly="true" maxlength="10" title="간부명"/>
			<a href="#" target="_blank"  title="새 창으로 이동" onClick="fn_aram_get_schdulCharger('간부', 'leaderId', 'leaderName'); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="간부선택 검색" >
			</a>
			<form:errors path="leaderName" cssClass="error"/>
	       	<form:hidden path="leaderId" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<label for="schdulNm">일정명</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<form:input path="schdulNm" size="92" maxlength="255" title="일정명"/>
	      	<form:errors path="schdulNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<label for="schdulCn">일정내용</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	        <form:textarea path="schdulCn" rows="5" cols="90" title="일정내용"/>
	    	<form:errors path="schdulCn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<label for="schdulPlace">일정장소</label>
	    	<span class="norequired_icon"></span>
	    </th>
	    <td>
	      	<form:input path="schdulPlace" size="92" maxlength="255" title="일정장소"/>
	      	<form:errors path="schdulPlace" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<label for="reptitSeCode">반복구분</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	       <form:radiobutton path="reptitSeCode" value="1" onchange="viewEndDay('1')" onclick="viewEndDay('1')"/>반복없음
	       <form:radiobutton path="reptitSeCode" value="2" onchange="viewEndDay('2')" onclick="viewEndDay('2')"/>매일
	       <form:radiobutton path="reptitSeCode" value="3" onchange="viewEndDay('3')" onclick="viewEndDay('3')"/>매주
	       <form:radiobutton path="reptitSeCode" value="4" onchange="viewEndDay('4')" onclick="viewEndDay('4')"/>매월
	       <form:errors path="reptitSeCode" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<label for="schdulBgndeYYYMMDD">날짜/시간</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
			<input type="hidden" name="schdulBgnde" id="schdulBgnde" value="">
		    <form:input path="schdulBgndeYYYMMDD" size="10" maxlength="10" title="일정시작일자"/>
			<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].schdulBgndeYYYMMDD); return false;">
			   	<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" style="border:0px;vertical-align: bottom" alt="달력창팝업버튼이미지">
			</a>
			&nbsp;&nbsp;
		    <div id="endDay">~&nbsp;&nbsp;
			<input type="hidden" name="schdulEndde" id="schdulEndde" value="">
		    <form:input path="schdulEnddeYYYMMDD" size="10" maxlength="10" title="일정종료일자"/>
		    <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].schdulEnddeYYYMMDD); return false;">
		    	<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" style="border:0px;vertical-align: bottom" alt="달력창팝업버튼이미지">
		    </a>&nbsp;
			</div>
	        <form:select path="schdulBgndeHH" title="일정시작 시 선택">
	            <form:options items="${schdulBgndeHH}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>시
	        <form:select path="schdulBgndeMM" title="일정시작 분 선택">
	            <form:options items="${schdulBgndeMM}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>분
				~
	        <form:select path="schdulEnddeHH" title="일정종료 시 선택">
	            <form:options items="${schdulEnddeHH}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>시
	        <form:select path="schdulEnddeMM" title="일정종료 분 선택">
	            <form:options items="${schdulEnddeMM}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>분
	    </td>
	</tr>
	<tr>
	    <th>
	    	<label for="schdulChargerName">담당자</label>
	    	<span class="required_icon"></span>
	    </th>
	    <td>
			<form:input path="schdulChargerName" size="15" readonly="true" maxlength="10" title="담당자명"/>
			<a href="#" title="새 창으로 이동"  onClick="fn_aram_get_schdulCharger('담당자', 'schdulChargerId', 'schdulChargerName'); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="담당자선택 검색" >
			</a>
			<form:errors path="schdulChargerName" cssClass="error"/>
	       	<form:hidden path="schdulChargerId" />
	    </td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchSchdulSe" value="" />
<form:hidden path="searchUser" value="" />
<form:hidden path="year" value="" />
<form:hidden path="month" value="" />
<form:hidden path="week" value="" />
<form:hidden path="day" value="" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="leaderSchdulVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	if("${leaderSchdulVO.reptitSeCode}" == "1"){
		document.getElementById("endDay").style.display = "none";
	}
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("leaderSchdulVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("leaderSchdulVO");

    if(varForm.reptitSeCode[0].checked){
		varForm.schdulEnddeYYYMMDD.value = varForm.schdulBgndeYYYMMDD.value;
	}

	if(!validateLeaderSchdulVO(varForm)){
		return;
	}

	var schdulBgndeYYYMMDD = document.getElementById('schdulBgndeYYYMMDD').value;
	var schdulEnddeYYYMMDD = document.getElementById('schdulEnddeYYYMMDD').value;

	if(isDate(schdulBgndeYYYMMDD, "일정시작일자") == false) {
        return;
    }

    if(isDate(schdulEnddeYYYMMDD, "일정종료일자") == false) {
        return;
    }

    var schdulBgnHourMin = fn_aram_SelectBoxValue('schdulBgndeHH') +  fn_aram_SelectBoxValue('schdulBgndeMM');
	var schdulEndHourMin = fn_aram_SelectBoxValue('schdulEnddeHH') +  fn_aram_SelectBoxValue('schdulEnddeMM');

	if(schdulBgnHourMin> schdulEndHourMin){
		alert("일정종료시분이 일정시작시분보다 빠를수 없습니다.");
		return;
	}

	varForm.schdulBgnde.value = schdulBgndeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('schdulBgndeHH') +  fn_aram_SelectBoxValue('schdulBgndeMM');
	varForm.schdulEndde.value = schdulEnddeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('schdulEnddeHH') +  fn_aram_SelectBoxValue('schdulEnddeMM');

	if(parseInt(varForm.schdulBgnde.value)> parseInt(varForm.schdulEndde.value)){
		alert("일정종료일시가 일정시작일시보다 빠를수 없습니다.");
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/updateLeaderSchdul.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 사용자 팝업창열기
******************************************************** */
function fn_aram_get_schdulCharger(strTitle, empId, empName){
	gArguments["title"]    = strTitle;
	if( empId != "" )    gArguments["uniqId"]   = document.getElementById(empId);
	if( empName != "" )  gArguments["emplyrNm"] = document.getElementById(empName);

	var url = "/cop/smt/lsm/listEmplyrPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function viewEndDay(num){
	if(num == "1"){
		document.getElementById("endDay").style.display = "none";
	}else{
		document.getElementById("endDay").style.display = "";
	}
}

</script>
