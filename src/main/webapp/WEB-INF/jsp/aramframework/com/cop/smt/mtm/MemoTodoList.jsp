<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : MemoTodoList.jsp
 * @Description : 메모할일 목록
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
	<h2>메모할일 목록</h2>
</div>

<form:form commandName="memoTodoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="todoId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_memoToday(); return false;">오늘의할일</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area1">
 		<label for="searchDe">일자 조회조건 : </label>
 		<form:select path="searchDe" class="select" title="일자 조회조건 선택">
			<form:option value='' label="--선택하세요--" />
			<form:option value="0" label="할일일자" />
			<form:option value="1" label="작성일자" />
		</form:select>
  		<form:hidden path="searchBgnDe" />
    	<c:if test="${!empty memoTodoVO.searchBgnDe}">
			<c:set var="searchBgnDeVal" value="${fn:substring(memoTodoVO.searchBgnDe, 0,4)}-${fn:substring(memoTodoVO.searchBgnDe, 4,6)}-${fn:substring(memoTodoVO.searchBgnDe, 6,8)}"/>
     	</c:if>
     	<input name="searchBgnDeView" id="searchBgnDeView" type="text" size="10" title="조회시작일자" value="${searchBgnDeVal}"  readonly />
     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchBgnDe, document.forms[0].searchBgnDeView); return false;">
     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
     	</a>
   		~
     	<form:hidden path="searchEndDe" />
    	<c:if test="${!empty memoTodoVO.searchEndDe}">
			<c:set var="searchEndDeVal" value="${fn:substring(memoTodoVO.searchEndDe, 0,4)}-${fn:substring(memoTodoVO.searchEndDe, 4,6)}-${fn:substring(memoTodoVO.searchEndDe, 6,8)}"/>
     	</c:if>
     	<input name="searchEndDeView" id="searchEndDeView" type="text" size="10" title="조회종료일자" value="${searchEndDeVal}"  readonly />
     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchEndDe, document.forms[0].searchEndDeView); return false;">
     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
     	</a>
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
	<div class="keyword_area2">
 		<form:select path="searchCondition" class="select" title="조회조건 선택">
			<form:option value="" label="--선택하세요--" />
			<form:option value="TODO_SJ" label="할일제목" />
			<form:option value="TODO_CN" label="할일내용" />
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
 	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list"  summary="이 표는 메모할일 정보를 제공하며, 할일일시, 할일제목, 작성자, 작성일자 정보로 구성되어 있습니다 .">
<caption>메모할일 목록</caption>
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="20%">할일일시</th>
    	<th scope="col"            >할일제목</th>
    	<th scope="col" width="15%">작성자</th>
    	<th scope="col" width="15%">작성일자</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

  	<c:set var="searchVO" value="${memoTodoVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.todoId}'); return false;">

 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
	    	<c:out value="${fn:substring(result.todoBeginTime, 0, 4)}-${fn:substring(result.todoBeginTime, 4, 6)}-${fn:substring(result.todoBeginTime, 6, 8)}"/>
	    	<c:out value="${fn:substring(result.todoBeginTime, 8, 10)}"/>:<c:out value="${fn:substring(result.todoBeginTime, 10, 12)}"/>
	    	~
	    	<c:out value="${fn:substring(result.todoEndTime, 8, 10)}"/>:<c:out value="${fn:substring(result.todoEndTime, 10, 12)}"/>
	    </td>
	    <td class="lt_text3"><c:out value="${result.todoNm}"/></td>
		<td class="lt_text3"><c:out value="${result.wrterNm}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_memoTodo();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("memoTodoVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodo.do";
	varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("memoTodoVO");

	var bgnDe = varForm.searchBgnDe.value;
	var endDe = varForm.searchEndDe.value;

	if(bgnDe != ""){
		if(isDate(bgnDe, "검색시작일자") == false) {
	        return;
	    }
	}

	if(endDe != ""){
	    if(isDate(endDe, "검색종료일자") == false) {
	        return;
	    }
	}

	if(bgnDe != "" && endDe != ""){
		if(eval(bgnDe)> eval(endDe)){
			alert("검색종료일자가 검색시작일자보다 빠를수 없습니다.");
			return;
		}
	}

    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodo.do";
	varForm.submit();
}

function fn_aram_list_memoToday() {
    var varForm = document.getElementById("memoTodoVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/listMemoTodoToday.do";
    varForm.submit();
}

function fn_aram_detail(todoId) {
    var varForm = document.getElementById("memoTodoVO");
    varForm.todoId.value = todoId;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/detailMemoTodo.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("memoTodoVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mtm/registMemoTodo.do";
    varForm.submit();
}

</script>


