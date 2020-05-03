<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TnextrlHrInfoRegist.jsp
  * @Description : 외부인사정보 등록
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
	<h2>외부인사정보 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>	
</div>

<form:form modelAttribute="tnextrlHrInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="extrlHrId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="행사/이벤트/캠페인 정보, 성별, 외부인사명, 생년월일, 직업유형, 소속기관, 전화번호, 이메일주소 입니다">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="eventCn">행사/이벤트/캠페인 정보</label>
    	</th>
    	<td width="80%">
      		<input name="eventCn" id="eventCn" size="73" value="" maxlength="70" style="width:300px;" readonly="readonly" title="eventCn" />
      		<a href="#" onclick="fn_aram_EventCmpgnListPopup(); return false;">
      			<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="행사/이벤트/캠페인 정보" title="행사/이벤트/캠페인 정보">
      		</a>
      		<input name="eventId" id="eventId" type="hidden" value="5">
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="sexdstnCode">성별</label>
    	</th>
    	<td>
      		<form:select path="sexdstnCode" title="성별선택">
	      		<form:option value="" label="선택" />
	      		<form:options items="${COM014_sexdstn}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
      		<form:errors path="sexdstnCode" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="extrlHrNm">외부인사명</label>
    	</th>
    	<td>
       		<form:input path="extrlHrNm" size="73" maxlength="50" alt="외부인사명" title="외부인사명" />
       		<form:errors path="extrlHrNm" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="brthYYYY">생년월일</label>
    	</th>
    	<td>
       		<select name="brthYYYY" id="brthYYYY" title="생년">
       			<option value="">선택</option>
	     		<c:forEach var="h" begin="1960" end="2009" step="1">
	      		<option value="${h}">${h}</option>
	      		</c:forEach>
       		</select>년
       		<select name="brthMM" id="brthMM" title="생년월">
       			<option value="">선택</option>
	     		<c:forEach var="h" begin="1" end="12" step="1">
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}"><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>월
       		<select name="brthDD" id="brthDD" title="생년일">
       			<option value="">선택</option>
	     		<c:forEach var="h" begin="1" end="31" step="1">
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}"><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>일
       		<form:hidden path="brth" />
       		<form:errors path="brthYYYY" cssClass="error" />
       		<form:errors path="brthMM" cssClass="error" />
       		<form:errors path="brthDD" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="occpTyCode">직업유형</label>
    	</th>
    	<td>
      		<form:select path="occpTyCode" title="직업유형선택">
	      		<form:option value="" label="선택" />
	      		<form:options items="${COM034_occpType}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
      		<form:errors path="occpTyCode" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="psitnInsttNm">소속기관</label>
    	</th>
    	<td>
       		<form:input path="psitnInsttNm" size="73" maxlength="70" alt="소속기관" title="소속기관" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="areaNo">전화번호</label>
    	</th>
    	<td>
		    <form:input path="areaNo" size="4" maxlength="4" title="전화번호(지역)" />-
		    <form:input path="middleTelno" size="4" maxlength="4" title="전화번호(국번)" />-
		    <form:input path="endTelno" size="4" maxlength="4" title="전화번호(지번)"  />
			<form:errors path="areaNo" cssClass="error" />
			<form:errors path="middleTelno" cssClass="error" />
			<form:errors path="endTelno" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="emailAdres">이메일주소</label>
    	</th>
    	<td>
       		<form:input path="emailAdres" size="73"maxlength="50" alt="이메일주소" title="이메일주소" />
       		<form:errors path="endTelno" cssClass="error" />
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="tnextrlHrInfoVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/utl/CmmUtl.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {

//	document.getElementById("bsnsYear").value = "2009";

};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("tnextrlHrInfoVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listTnextrlHrInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("tnextrlHrInfoVO");

    if(!validateTnextrlHrInfoVO(varForm)){
		return;
	}

	varForm.brth.value = fn_aram_SelectBoxValue('brthYYYY') + "" + fn_aram_SelectBoxValue('brthMM') + "" + fn_aram_SelectBoxValue('brthDD');
	if(varForm.eventCn.value == "" ||
			varForm.eventId.value == ""	){
		alert("행사/이벤트/캠페인 정보를  선택 해주세요!");
		varForm.eventCn.focus();
		return;
	}

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/ion/ecc/insertTnextrlHrInfo.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 이벤트 조회 팝업
 ******************************************************** */
function fn_aram_EventCmpgnListPopup(){
	var varForm = document.getElementById("tnextrlHrInfoVO");
	gArguments["eventId"] = varForm.eventId;
	gArguments["eventCn"] = varForm.eventCn;
	
	var url = "/uss/ion/ecc/listEventCmpgnPopup.do";

	window.open(url, "p_eventInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

