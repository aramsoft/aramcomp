<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : AnnvrsryRegist.jsp
 * @Description : 기념일 등록
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
	<h2>기념일 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_reset(); return false;">초기화</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="annvrsryManageVO" method="post" action=""> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="usid" />

<table class="table-register" summary="기념일관리 등록">
<caption>기념일관리 등록</caption>
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="annvrsryTemp1">신청자</label>
     	</th>
    	<td width="20%">
    		<c:out value='${annvrsryManageVO.usNm}'/>
    	</td>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="annvrsryTemp2">소속</label>
    	</th>
    	<td width="40%">
    		<c:out value='${annvrsryManageVO.orgnztNm}'/>
    	</td>	
  	</tr> 
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		<label for="annvrsrySe">기념일구분</label>
    	</th>
    	<td>
    		<label for="annvrsrySe">
      		<form:select path="annvrsrySe" title="기념일구분">
	      		<form:options items="${COM069_annvrsrySe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
      		</label>
    	</td>
    	<th>
    		<label for="annvrsryDe">기념일</label>
    		<span class="required_icon"></span>
    	</th>
    	<td>
      		<form:hidden path="annvrsryDe" />
	    	<c:if test="${!empty annvrsryManageVO.annvrsryDe}">
 				<c:set var="annvrsryDeVal" value="${fn:substring(annvrsryManageVO.annvrsryDe, 0,4)}-${fn:substring(annvrsryManageVO.annvrsryDe, 4,6)}-${fn:substring(annvrsryManageVO.annvrsryDe, 6,8)}"/>
      		</c:if>
      		<input name="annvrsryDeView" id="annvrsryDeView" type="text" size="10" title="기념일" value="${annvrsryDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].annvrsryDe, document.forms[0].annvrsryDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	     	양력 : <form:radiobutton path="cldrSe"  value="1" title="양력"/>&nbsp;
	     	음력 : <form:radiobutton path="cldrSe"  value="2" title="음력"/>
	     	<form:errors path="cldrSe" cssClass="error"/>
	     	&nbsp;&nbsp;매년반복 : <input type="checkbox" name="reptitSe" id="reptitSe" title="반복여부" value="1">
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="annvrsryNm">기념일명</label>
    	</th>
    	<td colspan="3">
      		<label for="annvrsryNm">
      		<form:input  path="annvrsryNm" size="80" maxlength="255" title="기념일명"/>
      		<form:errors path="annvrsryNm" cssClass="error"/>
      		</label>
    	</td>
  	</tr>
  	<tr> 
    	<th>
			<span class="norequired_icon"></span>
    		<label for="memo">메모</label>
    	</th>
    	<td colspan="3">
      		<label for="memo">
      		<form:textarea path="memo" rows="4" cols="70" cssClass="txArea" title="메모"/>
      		<form:errors path="memo" cssClass="error"/>
      		</label>
    	</td>
  	</tr>
  	<tr> 
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="annvrsryBeginDe">알림시작일</label>
    	</th>
    	<td>
    		D-
        	<label for="memo">
        	<select name="annvrsryBeginDe" title="알림시작일">
	       		<option value="7">일주일</option>
	       		<option value="3">3일</option>
	       		<option value="2">2일</option>
	       		<option value="1">1일</option>
      		</select>
      		</label> 전 부터 알림
    	</td>
    	<th>
 			<span class="norequired_icon"></span>
    		<label for="annvrsrySetup">알림설정</label>
    	</th>
    	<td>
    		<label for="annvrsrySetup">
	     	ON :  <form:radiobutton path="annvrsrySetup"  value="Y" title="알림ON"/>&nbsp;&nbsp;
	     	OFF : <form:radiobutton path="annvrsrySetup"  value="N" title="알림OFF"/>
	     	<form:errors path="annvrsrySetup" cssClass="error"/>
	     	</label>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="annvrsryManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_aram_reset() {
	var varForm = document.getElementById("annvrsryManageVO");
	varForm.annvrsrySe.value         = "";
	varForm.annvrsryDe.value         = "";
	varForm.cldrSe[0].checked        = true;
	varForm.annvrsryNm.value         = "";
	varForm.memo.value               = "";
	varForm.annvrsryBeginDe.value    = "";
	varForm.annvrsrySetup[0].checked = true;
}

/* ********************************************************
* 목록화면으로 이동
******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsry.do";
    varForm.submit();       
}

/* ********************************************************
* 등록 처리
******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("annvrsryManageVO");

    if(!validateAnnvrsryManageVO(varForm)){    
        return;
    }

	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/insertAnnvrsry.do";
        varForm.submit();
    }
}

</script>
