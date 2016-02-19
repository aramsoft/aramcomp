<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : MemoTodoListToday.jsp
 * @Description : 오늘의 할일 목록
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
	<h2>오늘의 할일 목록</h2> [ <c:out value="${resultToday}"/> ]
</div>

<form:form commandName="memoTodoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="todoId">

<div id="search_area">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;">전체목록</a></span>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list"  summary="이 표는 메모할일 중 오늘의 할일 정보를 제공하며, 할일일시, 할일제목, 작성자, 작성일자 정보로 구성되어 있습니다 .">
<caption>오늘의 할일 목록</caption>
<thead>
	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col" width="20%">할일일시</th>
	    <th scope="col" width="40%">할일제목</th>
	    <th scope="col" width="15%">작성자</th>
	    <th scope="col" width="15%">작성일자</th>
	</tr>
</thead>    
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="5"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
 	<c:set var="startIndex" value="${(memoTodoVO.searchVO.pageIndex-1) * memoTodoVO.searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.todoId}'); return false;">

 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${memoTodoVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
	    	<c:out value="${fn:substring(result.todoBeginTime, 8, 10)}"/>:<c:out value="${fn:substring(result.todoBeginTime, 10, 12)}"/>
	    	~
	    	<c:out value="${fn:substring(result.todoEndTime, 8, 10)}"/>:<c:out value="${fn:substring(result.todoEndTime, 10, 12)}"/>
	    </td>
	    <td class="lt_text2"><c:out value="${result.todoNm}"/></td>
		<td class="lt_text3"><c:out value="${result.wrterNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>	  
</tbody>
</table>

</div>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("memoTodoVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodo.do";
    varForm.submit();	
}	

function fn_aram_detail(todoId) {
    var varForm = document.getElementById("memoTodoVO");
    varForm.todoId.value = todoId;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/detailMemoTodo.do";
    varForm.submit();	
}

</script>

