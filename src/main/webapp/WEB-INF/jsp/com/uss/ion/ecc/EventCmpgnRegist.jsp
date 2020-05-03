<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
/**
 * @Class Name : EventCmpgnRegist.jsp
 * @Description : 행사/이벤트/캠페인 등록
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
	<h2>행사/이벤트/캠페인 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>	
</div>

<form:form modelAttribute="eventCmpgnVO"  action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="eventId" />

<table class="table-register" summary="행사유형, 행사내용, 행사시작일자, 행사종료일자, 서비스이용인원수, 담당자명, 준비물내용, 승인내용, 승인일 입니다">
	<tr>
		<th width="20%">
    		<span class="required_icon"></span>
			<label for="eventTyCode">행사유형</label>
		</th>
		<td width="80%">
      		<form:select path="eventTyCode" title="행사유형선택">
	      		<form:option value="" label="선택" />
	      		<form:options items="${COM035_eventTy}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
			<form:errors path="eventTyCode" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
    		<span class="required_icon"></span>
			<label for="eventCn">행사내용</label>
		</th>
		<td>
			<textarea name="eventCn" class="textarea" cols="75" rows="4" style="width: 100%;" title="행사내용"></textarea>
			<form:errors path="eventCn" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
   			<span class="required_icon"></span>
			<label for="eventSvcBeginDe">행사시작일자</label>
 		</th>
		<td>
      		<form:hidden path="eventSvcBeginDe" />
	    	<c:if test="${!empty eventCmpgnVO.eventSvcBeginDe}">
 				<c:set var="eventSvcBeginDeVal" value="${fn:substring(eventCmpgnVO.eventSvcBeginDe, 0,4)}-${fn:substring(eventCmpgnVO.eventSvcBeginDe, 4,6)}-${fn:substring(eventCmpgnVO.eventSvcBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="eventSvcBeginDeView" id="eventSvcBeginDeView" type="text" size="10" title="행사시작일자입력" value="${eventSvcBeginDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].eventSvcBeginDe, document.forms[0].eventSvcBeginDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="eventSvcBeginDe" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			<label for="eventSvcEndDe">행사종료일자</label>
		</th>
		<td>
      		<form:hidden path="eventSvcEndDe" />
	    	<c:if test="${!empty eventCmpgnVO.eventSvcEndDe}">
 				<c:set var="eventSvcEndDeVal" value="${fn:substring(eventCmpgnVO.eventSvcEndDe, 0,4)}-${fn:substring(eventCmpgnVO.eventSvcEndDe, 4,6)}-${fn:substring(eventCmpgnVO.eventSvcEndDe, 6,8)}"/>
      		</c:if>
      		<input name="eventSvcEndDeView" id="eventSvcEndDeView" type="text" size="10" title="행사종료일자입력" value="${eventSvcEndDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].eventSvcEndDe, document.forms[0].eventSvcEndDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="eventSvcEndDe" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="svcUseNmprCo">서비스이용인원수</label>
		</th>
		<td>
			<input name="svcUseNmprCo" id="svcUseNmprCo" type="text" size="73" value="" maxlength="10" style="width: 60px;" title="서비스이용인원수">
		</td>
	</tr>
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="chargerNm">담당자명</label>
		</th>
		<td>
			<input name="chargerNm" id="chargerNm" type="text" size="73" value="" maxlength="50" style="width: 100px;" title="담당자명">
		</td>
	</tr>
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="prparetgCn">준비물내용</label>
		</th>
		<td>
			<textarea name="prparetgCn" class="textarea" cols="75" rows="4" style="width: 100%;" title="준비물내용"></textarea>
			<form:errors path="prparetgCn" cssClass="error" />
		</td>
	</tr>
	<%-- 업무사용자만 승인 가능하도록 처리--%>
	<c:if test="${SUserSe eq 'USR'}">
	<tr>
		<th>
			<span class="norequired_icon"></span>
			승인여부
		</th>
		<td>
			승인:<input name="eventConfmAt" type="radio" value="Y" />
			미승인:<input name="eventConfmAt" type="radio" value="N" onClick="document.forms[0].eventConfmDe.value='';" checked />
		</td>
	</tr>
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="eventConfmDe">승인일</label>
		</th>
		<td>
      		<form:hidden path="eventConfmDe" />
	    	<c:if test="${!empty eventCmpgnVO.eventConfmDe}">
 				<c:set var="eventConfmDeVal" value="${fn:substring(eventCmpgnVO.eventConfmDe, 0,4)}-${fn:substring(eventCmpgnVO.eventConfmDe, 4,6)}-${fn:substring(eventCmpgnVO.eventConfmDe, 6,8)}"/>
      		</c:if>
      		<input name="eventConfmDeView" id="eventConfmDeView" type="text" size="10" title="승인일입력" value="${eventConfmDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].eventConfmDe, document.forms[0].eventConfmDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
		</td>
	</tr>
	</c:if>
</table>

<c:if test="${SUserSe ne 'USR'}">
<input name="eventConfmAt" id="eventConfmAt" type="hidden" value="" />
<input name="eventConfmDe" id="eventConfmDe" type="hidden" value="" />
</c:if>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="eventCmpgnVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("eventCmpgnVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("eventCmpgnVO");

	if(!validateEventCmpgnVO(varForm)){
		return;
	}

	<%-- 업무사용자만 승인 가능하도록 처리--%>
	<c:if test="${SUserSe eq 'USR'}">
	if(document.getElementsByName("eventConfmAt")[0].checked == true ){
		if(document.getElementById("eventConfmDe").value == ""){
			alert("승인 신청일을 입력해주세요!");
			document.getElementById("eventConfmDe").focus();
			return;
		}
	}
	</c:if>
	if(document.getElementById("svcUseNmprCo").value == ""){
		document.getElementById("svcUseNmprCo").value = "0" ;
	}

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/ion/ecc/insertEventCmpgn.do";
		varForm.submit();
	}
}

</script>

