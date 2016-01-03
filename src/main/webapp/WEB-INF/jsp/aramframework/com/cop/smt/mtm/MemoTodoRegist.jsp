<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MemoTodoRegist.jsp
 * @Description : 메모할일 등록
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
	<h2>메모할일 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="memoTodoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="todoId" />

<table class="table-register" summary="이 표는 메모할일 정보를  등록하기 위한 표이며, 할일일시, 작성자, 할일제목, 할일내용 정보로 구성되어 있습니다 .">
<caption>메모할일 등록</caption>
<tbody>
	<tr>
		<th width="20%">
	    	<span class="required_icon"></span>
			<label for="todoDe">할일일시</label>
		</th>
		<td width="80%">
      		<form:hidden path="todoDe" />
	    	<c:if test="${!empty memoTodoVO.todoDe}">
 				<c:set var="todoDeVal" value="${fn:substring(memoTodoVO.todoDe, 0,4)}-${fn:substring(memoTodoVO.todoDe, 4,6)}-${fn:substring(memoTodoVO.todoDe, 6,8)}"/>
      		</c:if>
      		<input name="todoDeView" id="todoDeView" type="text" size="10" title="할일일자" value="${todoDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].todoDe, document.forms[0].todoDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>

		    <form:select path="todoBeginHour" title="할일시작 시 선택">
	            <form:options items="${todoListHour}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>시
	        <form:select path="todoBeginMin" title="할일시작 분 선택">
	            <form:options items="${todoListMin}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>분
			~
	        <form:select path="todoEndHour" title="할일종료 시 선택">
	            <form:options items="${todoListHour}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>시
	        <form:select path="todoEndMin" title="할일종료 분 선택">
	            <form:options items="${todoListMin}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>분
			<form:errors path="todoDe" cssClass="error"/>
		</td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	작성자
	    </th>
	    <td>
	      	<c:out value="${memoTodoVO.wrterNm}" escapeXml="false" />
	      	<input type="hidden" name="wrterId" id="wrterId" value="${memoTodoVO.wrterId}"/>
	      	<input type="hidden" name="wrterNm" id="wrterNm" value="${memoTodoVO.wrterNm}"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="todoNm">할일제목</label>
	    </th>
	    <td>
	      	<form:input path="todoNm" size="75" maxlength="255" title="할일제목"/>
	      	<form:errors path="todoNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="todoCn">할일내용</label>
	    </th>
	    <td>
	      	<form:textarea path="todoCn" rows="5" cols="75" title="할일내용"/>
    	  	<form:errors path="todoCn" cssClass="error"/>
	    </td>
	</tr>
</tbody>
</table>

</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="memoTodoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("memoTodoVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodo.do";
    varForm.submit();
}

function fn_aram_insert() {
    var varForm = document.getElementById("memoTodoVO");

    if (!validateMemoTodoVO(varForm)){
		return;
	}

	var todoBeginHourMin = varForm.todoBeginHour.value + varForm.todoBeginMin.value;
	var todoEndHourMin = varForm.todoEndHour.value + varForm.todoEndMin.value;

	if(todoBeginHourMin> todoEndHourMin){
		alert("할일종료시분이 할일시작시분보다 빠를수 없습니다.");
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/insertMemoTodo.do";
		varForm.submit();
	}
}

</script>


