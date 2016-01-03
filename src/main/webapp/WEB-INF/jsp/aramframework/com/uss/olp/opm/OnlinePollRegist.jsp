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
  * @Class Name : OnlinePollRegist.jsp
  * @Description : 온라인POLL 등록
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
	<h2>온라인POLL 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="onlinePollManageVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pollId" />

<!--  등록  폼 영역  -->
<table class="table-register" summary="온라인POLL관리 입력을 제공한다..">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="pollNm">POLL명</label>
    	</th>
    	<td width="80%">
      		<form:input path="pollNm" size="70" cssClass="txInput" maxlength="255"/>
      		<form:errors path="pollNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="pollBeginDe">POLL시작일자</label>
    	</th>
    	<td>
      		<form:hidden path="pollBeginDe" />
	    	<c:if test="${!empty onlinePollManageVO.pollBeginDe}">
 				<c:set var="pollBeginDeVal" value="${fn:substring(onlinePollManageVO.pollBeginDe, 0,4)}-${fn:substring(onlinePollManageVO.pollBeginDe, 4,6)}-${fn:substring(onlinePollManageVO.pollBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="pollBeginDeView" id="pollBeginDeView" type="text" size="10" title="POLL시작일자" value="${pollBeginDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].pollBeginDe, document.forms[0].pollBeginDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="pollBeginDe" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="pollEndDe">POLL종료일자</label>
    	</th>
    	<td>
      		<form:hidden path="pollEndDe" />
	    	<c:if test="${!empty onlinePollManageVO.pollEndDe}">
 				<c:set var="pollEndDeVal" value="${fn:substring(onlinePollManageVO.pollEndDe, 0,4)}-${fn:substring(onlinePollManageVO.pollEndDe, 4,6)}-${fn:substring(onlinePollManageVO.pollEndDe, 6,8)}"/>
      		</c:if>
      		<input name="pollEndDeView" id="pollEndDeView" type="text" size="10" title="POLL종료일자" value="${pollEndDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].pollEndDe, document.forms[0].pollEndDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="pollEndDe" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="pollKindCode">POLL구분</label>
    	</th>
    	<td>
        	<form:select path="pollKindCode">
            	<form:option value="" label="선택"/>
            	<form:options items="${COM039_pollKind}" itemValue="code" itemLabel="codeNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="pollDsuseYn">POLL페기유무</label>
    	</th>
    	<td>
   			<input type="radio" name="pollDsuseYn" value="N"  checked>N
   			<input type="radio" name="pollDsuseYn" value="Y">Y
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="pollAutoDsuseYn">POLL자동페기유무</label>
    	</th>
    	<td>
    		<input type="radio" name="pollAutoDsuseYn" value="N">N
   			<input type="radio" name="pollAutoDsuseYn" value="Y" checked>Y
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="onlinePollManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/listOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("onlinePollManageVO");
    
	if(!validateOnlinePollManageVO(varForm)){
		return;
	}
	
	var pollBeginDe = Number( document.getElementById('pollBeginDe').value );
	var pollEndDe = Number( document.getElementById('pollEndDe').value );

	if(pollBeginDe> pollEndDe || pollEndDe < pollBeginDe ){
		alert("POLL시작일자는 POLL종료일자 보다 클수 없고,\nPOLL종료일자는 POLL시작일자 보다 작을수 없습니다. ");
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/opm/insertOnlinePoll.do";
		varForm.submit();
	}
}

</script>

