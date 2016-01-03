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
 * @Class Name : TnextrlHrInfoEdit.jsp
 * @Description : 외부인사정보 수정
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
	<h2>외부인사정보 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="tnextrlHrInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="eventId" />
<form:hidden path="extrlHrId" />

<!-- 등록  폼 영역  -->
<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		행사/이벤트/캠페인/(내용)
    	</th>
    	<td width="80%">
      		${tnextrlHrInfoVO.eventCn}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		성별
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
    		외부인사명
    	</th>
    	<td>
       		<form:input path="extrlHrNm" size="73" maxlength="50" style="width:300px;" alt="외부인사명" title="외부인사명" />
       		<form:errors path="extrlHrNm" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		생년월일
    	</th>
    	<td>
       		<select name="brthYYYY" id="brthYYYY" title="생년월일선택">
	     		<c:forEach var="h" begin="1960" end="2009" step="1">
	      		<option value="${h}">${h}</option>
	      		</c:forEach>
       		</select>년
       		<select name="brthMM" id="brthMM" title="연도선택">
	     		<c:forEach var="h" begin="1" end="12" step="1">
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}"><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>월
       		<select name="brthDD" id="brthDD" title="월선택">
	     		<c:forEach var="h" begin="1" end="31" step="1">
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}"><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>일
       		<input name="brth" id="brth" type="hidden" value="">
       		<form:errors path="brthYYYY" cssClass="error" />
       		<form:errors path="brthMM" cssClass="error" />
       		<form:errors path="brthDD" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		직업유형
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
    		<span class="norequired_icon"></span>
	    	소속기관
	    </th>
	    <td>
	       <input name="psitnInsttNm" type="text" size="73" value="${tnextrlHrInfoVO.psitnInsttNm}" maxlength="70" title="소속기관입력">
	    </td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		전화번호
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
    		이메일주소
    	</th>
    	<td>
       		<form:input path="emailAdres" size="73" maxlength="50" alt="이메일주소" title="이메일주소" />
       		<form:errors path="emailAdres" cssClass="error" />
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="tnextrlHrInfoVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	fn_aram_SelectBox("brthYYYY", "${fn:substring(tnextrlHrInfoVO.brth, 0, 4)}");
	fn_aram_SelectBox("brthMM", "${fn:substring(tnextrlHrInfoVO.brth, 4, 6)}");
	fn_aram_SelectBox("brthDD", "${fn:substring(tnextrlHrInfoVO.brth, 6, 8)}");
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("tnextrlHrInfoVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("tnextrlHrInfoVO");

	if(!validateTnextrlHrInfoVO(varForm)){
		return;
	}

	varForm.brth.value = fn_aram_SelectBoxValue('brthYYYY') + "" + fn_aram_SelectBoxValue('brthMM') + "" + fn_aram_SelectBoxValue('brthDD');

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/ion/ecc/updateTnextrlHrInfo.do";
		varForm.submit();
	}
}

</script>
