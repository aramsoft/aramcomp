<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : TroblReqstRegist.jsp
 * @Description : 장애신청 등록
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
	<h2>장애신청 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="troblReqstVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="valiDay" />

<table class="table-register" summary="장애신청 정보를 등록한다.">
<caption>장애신청 등록</caption>
  	<!--
  	<tr>
    	<th width="20%" scope="row">
    		<span class="required_icon"></span>
    		장애ID
    	</th>
    	<td width="80%" class="lt_text">
    		<form:input path="troblId" maxLength="23" size="30" class="readOnlyClass" readonly="true" />
    	</td>
  	</tr>
  -->
  	<tr>
    	<th width="20%" scope="row">
    		<span class="required_icon"></span>
    		장애명
    	</th>
    	<td width="80%" class="lt_text">
    		<form:input path="troblNm" maxLength="30" size="30" />
    		<form:errors path="troblNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th scope="row">
    		<span class="required_icon"></span>
    		장애종류
    	</th>
    	<td class="lt_text">
   			<form:select path="troblKnd">
                <form:options items="${COM065_troblKnd}" itemValue="code" itemLabel="codeNm"/>
	   		</form:select>	   
    	</td>
  	</tr>
  	<tr>
    	<th scope="row">
    		<span class="required_icon"></span>
    		장애설명
    	</th>
    	<td class="lt_text">
    		<textarea name="troblDc" rows="5" cols="80" title="장애설명"></textarea>
    		<form:errors path="troblDc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th scope="row">
    		<span class="required_icon"></span>
    		장애발생시간
    	</th>
    	<td class="lt_text">
        	<input type="hidden" name="troblOccrrncDate" value=""/>
        	<input name="troblOccrrncDateView" id="troblOccrrncDateView" size="10" maxlength="10" title="장애발생시간" />
        	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].troblOccrrncDate, document.forms[0].troblOccrrncDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
        		<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" title="새창" alt="달력창팝업버튼이미지">
        	</a>
         	<select name="troblOccrrncH" id="troblOccrrncH">
          		<c:forEach var="i" begin="0" end="23" step="1" varStatus="status">
            	<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>"><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
          		</c:forEach>
        	</select>시&nbsp;
        	<select name="troblOccrrncM" id="troblOccrrncM">
          		<c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
            	<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>"><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
          		</c:forEach>
        	</select>분&nbsp;
        	<select name="troblOccrrncS" id="troblOccrrncS">
          		<c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
            	<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>"><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
          		</c:forEach>
        	</select>초
        	<form:hidden path="troblOccrrncTime" value="" />
    	</td>
  	</tr>
  	<tr>
    	<th scope="row">
    		<span class="required_icon"></span>
    		장애등록자
    	</th>
    	<td class="lt_text">
    		<form:input path="troblRqesterNm" maxLength="30" size="30" />
    		<form:errors path="troblRqesterNm" cssClass="error"/>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="troblReqstVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("troblReqstVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/listTroblReqst.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert() {

    var varForm = document.getElementById("troblReqstVO");
/*
    varForm.troblOccrrncTime.value = varForm.troblOccrrncD.value + ' ' +
                                     varForm.troblOccrrncH.value + ':' +
                                     varForm.troblOccrrncM.value + ':' +
                                     varForm.troblOccrrncS.value;

    varForm.troblRequstTime.value = varForm.troblRequstD.value + ' ' +
                                    varForm.troblRequstH.value + ':' +
                                    varForm.troblRequstM.value + ':' +
                                    varForm.troblRequstS.value;
*/

    varForm.troblOccrrncTime.value = varForm.troblOccrrncD.value +
                                     varForm.troblOccrrncH.value +
                                     varForm.troblOccrrncM.value +
                                     varForm.troblOccrrncS.value;

/*
    varForm.troblRequstTime.value = varForm.troblRequstD.value +
                                    varForm.troblRequstH.value +
                                    varForm.troblRequstM.value +
                                    varForm.troblRequstS.value;
*/

    fncCheckValiDay();

    var tmp = varForm.troblOccrrncTime.value;
    tmp = tmp.substring(0,4) + tmp.substring(5,7) + tmp.substring(8,16);

    if(varForm.valiDay.value < tmp) {
        alert("장애발생시간은 현재시간 이전으로 설정하셔야 합니다.");
        return;
    }

    if(!validateTroblReqstVO(varForm)){
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbr/insertTroblReqst.do";
        varForm.submit();
    }
}

function fncCheckValiDay() {
	var varForm = document.getElementById("troblReqstVO");

	var date = new Date();
	var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();

    if(month < 10) month = "0" + month;
    if(day < 10) day = "0" + day;
    if(hour < 10) hour = "0" + hour;
    if(minute < 10) minute = "0" + minute;
    if(second < 10) second = "0" + second;

    // return year+month+day+hour+minute+second;
    varForm.valiDay.value = year+""+month+""+day+""+hour+""+minute+""+second;
}

</script>
