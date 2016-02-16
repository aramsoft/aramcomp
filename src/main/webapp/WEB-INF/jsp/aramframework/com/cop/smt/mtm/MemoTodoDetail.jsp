<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : MemoTodoDetail.jsp
 * @Description : 메모할일 상세조회
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
	<h2>메모할일 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="memoTodoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="todoId" />

<table class="table-detail" summary="이 표는 메모할일 정보를 제공하며, 할일일시, 작성자, 할일제목, 할일내용 정보로 구성되어 있습니다 .">
<caption>메모할일 상세보기</caption>
<tbody>
	<tr> 
		<th width="20%">
			할일일시
		</th>
		<td width="80%">
			<c:out value="${fn:substring(memoTodoVO.todoDe, 0,4)}-${fn:substring(memoTodoVO.todoDe, 4,6)}-${fn:substring(memoTodoVO.todoDe, 6,8)}" escapeXml="false" />
			&nbsp;&nbsp;
			<c:out value="${memoTodoVO.todoBeginHour}" escapeXml="false" />:<c:out value="${memoTodoVO.todoBeginMin}" escapeXml="false" />
			~ 
			<c:out value="${memoTodoVO.todoEndHour}" escapeXml="false" />:<c:out value="${memoTodoVO.todoEndMin}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
	    <th>
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
	    	할일제목
	    </th>
	    <td>
	      	<c:out value="${memoTodoVO.todoNm}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	할일내용
	    </th>
	    <td>
	       <br/>
	       <c:out value="${fn:replace(memoTodoVO.todoCn , crlf , '<br>')}" escapeXml="false" />
	       <br/><br/>
	    </td>
	</tr>
</tbody>
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchDe" />
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="memoTodoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("memoTodoVO");
<% if(request.getHeader("REFERER").indexOf("Today") < 0){ %>
   	varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodo.do";
<% }else{ %>
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodoToday.do";
<% } %>
    varForm.submit();	
}	

function fn_aram_edit() {
    var varForm = document.getElementById("memoTodoVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/editMemoTodo.do";
    varForm.submit();					
}

function fn_aram_delete(){
    var varForm = document.getElementById("memoTodoVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/deleteMemoTodo.do";
		varForm.submit();
	}
}

</script>


