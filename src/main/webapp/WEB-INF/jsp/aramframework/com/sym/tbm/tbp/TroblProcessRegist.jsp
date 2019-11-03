<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : TroblProcessRegist.jsp
 * @Description : 장애처리결과 등록
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
<c:set var="troblProcessTime" value='${troblProcessVO.troblProcessTime}' />
<c:set var="troblProcessDtmp" value="${fn:substring(troblProcessTime,0,10)}" />
<c:set var="troblProcessHtmp" value="${fn:substring(troblProcessTime,11,13)}" />
<c:set var="troblProcessMtmp" value="${fn:substring(troblProcessTime,14,16)}" />
<c:set var="troblProcessStmp" value="${fn:substring(troblProcessTime,17,19)}" />
<c:if test="${troblProcessHtmp < 10}"><c:set var="troblProcessHtmp" value="${fn:substring(troblProcessTime,12,13)}" /></c:if>
<c:if test="${troblProcessMtmp < 10}"><c:set var="troblProcessMtmp" value="${fn:substring(troblProcessTime,15,16)}" /></c:if>
<c:if test="${troblProcessStmp < 10}"><c:set var="troblProcessStmp" value="${fn:substring(troblProcessTime,18,19)}" /></c:if>

<DIV id="main">

<div class="content_title">
	<h2>장애처리결과 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
       	<c:if test="${troblProcessVO.processSttus == 'C'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
        </c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<form:form commandName="troblProcessVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="troblId" />
<form:hidden path="processSttus" />

<table class="table-register" summary="장애처리에 대한 결과를 등록한다.">
<caption>장애처리결과 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		장애ID
    	</th>
    	<td width="80%">
    		<c:out value='${troblProcessVO.troblId}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		장애명
    	</th>
    	<td>
    		<c:out value='${troblProcessVO.troblNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		장애종류
    	</th>
    	<td>
    		<c:out value='${troblProcessVO.troblKndNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		장애설명
    	</th>
    	<td>
    		<label for="troblDc"><textarea name="troblDc" rows="5" cols="80" title="장애설명" readOnly>
<c:out value='${troblProcessVO.troblDc}'/></textarea>
    		</label>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		장애발생시간
    	</th>
    	<td>
    		<c:out value='${troblProcessVO.troblOccrrncTime}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		장애등록자
    	</th>
    	<td>
    		<c:out value='${troblProcessVO.troblRqesterNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		장애요청시간
    	</th>
    	<td>
    		<c:out value='${troblProcessVO.troblRequstTime}'/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		처리상태
    	</th>
    	<td>
    		<c:out value='${troblProcessVO.processSttusNm}'/>
    	</td>
  	</tr>
</table>
<br/>
<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		처리결과
    	</th>
    	<td>
    		<textarea name="troblProcessResult" rows="5" cols="80" title="처리결과"><c:out value='${troblProcessVO.troblProcessResult}'/></textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		처리시간
    	</th>
    	<td>
        	<input type="hidden" name="troblProcessDate" value=""/>
         	<input type="text" name="troblProcessDateView" id="troblProcessDateView" value="<c:out value="${troblProcessDtmp}" />" size="10" maxlength="10" title="처리시간">
        	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].troblProcessDate, document.forms[0].troblProcessDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
        		<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" title="새창" alt="달력창팝업버튼이미지">
        	</a>
        	<select name="troblProcessH" id="troblProcessH" title="시">
          		<c:forEach var="i" begin="0" end="23" step="1" varStatus="status">
            	<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblProcessHtmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
          		</c:forEach>
        	</select>시&nbsp;
        	<select name="troblProcessM" id="troblProcessM" title="분">
          		<c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
            	<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblProcessMtmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
          		</c:forEach>
        	</select>분&nbsp;
        	<select name="troblProcessS" id="troblProcessS" title="초">
          		<c:forEach var="i" begin="0" end="59" step="1" varStatus="status">
            	<option value="<c:if test="${i < 10}">0</c:if><c:out value="${i}"/>" <c:if test="${i == troblProcessStmp}">selected</c:if>><c:if test="${i < 10}">0</c:if><c:out value="${i}"/></option>
          		</c:forEach>
        	</select>초
        	<input type="hidden" name="troblProcessTime" />&nbsp;
        	<form:errors path="troblProcessTime" cssClass="error"/>
   	 	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		처리자
    	</th>
    	<td>
    		<form:input path="troblOpetrNm" maxLength="30" size="30" />
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="strProcessSttus" />
<form:hidden path="strTroblKnd" />
<form:hidden path="strTroblNm" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="troblProcessVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("troblProcessVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbp/listTroblProcess.do";
    varForm.submit();
}

function fn_aram_insert() {
	var varForm = document.getElementById("troblProcessVO");

	var requstTimeTmp;
	var requstTime = "<c:out value='${troblProcess.troblRequstTime}'/>";
	requstTimeTmp = requstTime.substring(0,4);
    requstTimeTmp = requstTimeTmp + requstTime.substring(5,7);
    requstTimeTmp = requstTimeTmp + requstTime.substring(8,10);
    requstTimeTmp = requstTimeTmp + requstTime.substring(11,13);
	requstTimeTmp = requstTimeTmp + requstTime.substring(14,16);
	requstTimeTmp = requstTimeTmp + requstTime.substring(17,19);

	var processTime = varForm.troblProcessD.value;
	var processTimeTmp = processTime.substring(0,4);
	processTimeTmp = processTimeTmp + processTime.substring(5,7);
    processTimeTmp = processTimeTmp + processTime.substring(8,10);
    processTimeTmp = processTimeTmp + varForm.troblProcessH.value +
                                      varForm.troblProcessM.value +
                                      varForm.troblProcessS.value;

    if(requstTimeTmp> processTimeTmp) {
        alert("처리시간은 장애요청시간 이후로 설정하셔야 합니다.");
        return;
    }

    varForm.troblProcessTime.value = varForm.troblProcessD.value +
                                     varForm.troblProcessH.value +
                                     varForm.troblProcessM.value +
                                     varForm.troblProcessS.value;

    if(!validateTroblProcessVO(varForm)) {
        return;
    }

    if(confirm("저장 하시겠습니까?")){
        varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbp/insertTroblProcess.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("troblProcessVO");

	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/tbm/tbp/deleteTroblProcess.do";
        varForm.submit();
    }
}

</script>
