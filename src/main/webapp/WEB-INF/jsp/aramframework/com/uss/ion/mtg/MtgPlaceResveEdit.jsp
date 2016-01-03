<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceResveEdit.jsp
 * @Description : 회의실예약 수정
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
	<h2>회의실예약 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mtgPlaceResveVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mtgPlaceId" />

<form:hidden path="resveManId" />
<form:hidden path="resveId" />

<input type="hidden" name="dplactCeck" id="dplactCeck">

<table class="table-register" summary="회의실예약관리 수정">
<caption>회의실예약관리수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="mtgSj">제목</label>
    	</th>
    	<td colspan ="3">
       		<form:input path="mtgSj" size="50" maxlength="70" style="width:90%;" title="제목" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		회의실명
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceManageVO.mtgPlaceNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th >
    		<span class="required_icon"></span>
    		회의실 위치
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceManageVO.lcSeNm}'/> 
    		<c:out value='${mtgPlaceManageVO.lcDetail}'/>
	    	<c:if test="${!empty mtgPlaceManageVO.atchFileId}">
	    		<span class="button"><a href="#" onclick="fn_aram_getImage(); return false;" title="새창으로">회의실 이미지</a></span>
	    	</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		예약자
    	</th>
    	<td width="30%">
    		<c:out value='${mtgPlaceResveVO.resveManNm}'/>
    	</td>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		소속
    	</th>
    	<td width="30%">
    		<c:out value='${mtgPlaceResveVO.resevOrgnztNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="resveDe">예약시간</label>
    	</th>
    	<td colspan ="3">
      		<form:hidden path="resveDe" />
	    	<c:if test="${!empty mtgPlaceResveVO.resveDe}">
 				<c:set var="resveDeVal" value="${fn:substring(mtgPlaceResveVO.resveDe, 0,4)}-${fn:substring(mtgPlaceResveVO.resveDe, 4,6)}-${fn:substring(mtgPlaceResveVO.resveDe, 6,8)}"/>
      		</c:if>
      		<input name="resveDeView" id="resveDeView" type="text" size="10" title="예약일자" value="${resveDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].resveDe, document.forms[0].resveDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
        	<form:select path="resveBeginTm" title="예약시작시간">
				<form:option value="0800" label="08:00" />
				<form:option value="0830" label="08:30" />
				<form:option value="0900" label="09:00" />
				<form:option value="0930" label="09:30" />
				<form:option value="1000" label="10:00" />
				<form:option value="1030" label="10:30" />
				<form:option value="1100" label="11:00" />
				<form:option value="1130" label="11:30" />
				<form:option value="1200" label="12:00" />
				<form:option value="1230" label="12:30" />
				<form:option value="1300" label="13:00" />
				<form:option value="1330" label="13:30" />
				<form:option value="1400" label="14:00" />
				<form:option value="1430" label="14:30" />
				<form:option value="1500" label="15:00" />
				<form:option value="1530" label="15:30" />
				<form:option value="1600" label="16:00" />
				<form:option value="1630" label="16:30" />
				<form:option value="1700" label="17:00" />
				<form:option value="1730" label="17:30" />
				<form:option value="1800" label="18:00" />
				<form:option value="1830" label="18:30" />
				<form:option value="1900" label="19:00" />
				<form:option value="1930" label="19:30" />
				<form:option value="2000" label="20:00" />
				<form:option value="2030" label="20:30" />
				<form:option value="2100" label="21:00" />
      		</form:select>
			~   
        	<form:select path="resveEndTm" title="예약종료시간">
				<form:option value="0800" label="08:00" />
				<form:option value="0830" label="08:30" />
				<form:option value="0900" label="09:00" />
				<form:option value="0930" label="09:30" />
				<form:option value="1000" label="10:00" />
				<form:option value="1030" label="10:30" />
				<form:option value="1100" label="11:00" />
				<form:option value="1130" label="11:30" />
				<form:option value="1200" label="12:00" />
				<form:option value="1230" label="12:30" />
				<form:option value="1300" label="13:00" />
				<form:option value="1330" label="13:30" />
				<form:option value="1400" label="14:00" />
				<form:option value="1430" label="14:30" />
				<form:option value="1500" label="15:00" />
				<form:option value="1530" label="15:30" />
				<form:option value="1600" label="16:00" />
				<form:option value="1630" label="16:30" />
				<form:option value="1700" label="17:00" />
				<form:option value="1730" label="17:30" />
				<form:option value="1800" label="18:00" />
				<form:option value="1830" label="18:30" />
				<form:option value="1900" label="19:00" />
				<form:option value="1930" label="19:30" />
				<form:option value="2000" label="20:00" />
				<form:option value="2030" label="20:30" />
				<form:option value="2100" label="21:00" />
      		</form:select>
      		<span class="button"><a href="#" onclick="fn_aram_dplact_check(); return false;" title="새창으로">중복체크</a></span>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="atndncNmpr">참석인원</label>
    	</th>
    	<td colspan ="3">
    		<form:input path="atndncNmpr" size="10" maxlength="3" style="width:5%;" title="참석인원" />명
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="mtgCn">회의내용</label>
    	</th>
    	<td colspan ="3">
      		<form:textarea path="mtgCn" class="txArea" rows="4" cols="70" title="회의내용" />
    	</td>
  	</tr>
</table>
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="mtgPlaceResveVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("mtgPlaceResveVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlaceResve.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("mtgPlaceResveVO");

    if(!validateMtgPlaceResveVO(varForm)){
       	return;
    }

	if(varForm.dplactCeck.value == "") {
		alert("회의실 예약 중복확인을 하신 후 회의실 예약을 해주세요.");
		return;
	}	
	else if(varForm.dplactCeck.value == "N") {
		alert("이미 회의실이 예약되어 있습니다.");
		return;
	}

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/updateMtgPlaceResve.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 회의실 중복여부 확인  팝업창열기
* fn_aram_dplact_ceck
* ******************************************************** */
function fn_aram_dplact_check(){
	var varForm = document.getElementById("mtgPlaceResveVO");

	var beginTm = varForm.resveBeginTm.value;
	var endTm   = varForm.resveEndTm.value;

	if((endTm-beginTm)> 0){
		gArguments["dplactCeck"] = varForm.dplactCeck;

	    var sTempValue = "sTmResveDe="+varForm.resveDe.value+"&sTmResveBeginTm="+varForm.resveBeginTm.value+"&sTmResveEndTm="+varForm.resveEndTm.value+"&sTmMtgPlaceId="+varForm.mtgPlaceId.value+"&sTmResveId="+varForm.resveId.value;
		var url = "/uss/ion/mtg/checkMtgPlaceResveDplact.do?"+sTempValue

		window.open(url, "p_dplactCeck", "width=450px,height=150px,top=100px,left=100px,location=no");
   }
   else alert("예약시작시간이  예약종료시간보다 작거나 같습니다. 예약시간을 확인해  주세요.");
}

/* ********************************************************
* 회의실 이미지  팝업창열기
* fn_aram_dplact_ceck
* ******************************************************** */
function fn_aram_getImage(){
	var varForm = document.getElementById("mtgPlaceResveVO");
	var sTempValue = "sTmMtgPlaceId="+varForm.mtgPlaceId.value;
	var url = "/uss/ion/mtg/detailMtgPlaceImage.do?"+sTempValue

	window.open(url, "p_imageInqire", "width=720px,height=500px,top=100px,left=100px,location=no");
}

</script>

