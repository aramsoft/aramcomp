<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BatchOpertList.jsp
 * @Description : 배치작업 목록
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
	<h2>배치작업 목록</h2> 
</div>

<form:form commandName="batchOpertVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="batchOpertId" type="hidden" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
 	   	<form:select path="searchCondition" class="select" title="검색유형선력">
		   	<form:option value="" label="--선택하세요--" />
		   	<form:option value="BATCH_OPERT_NM" label="배치작업명" />
		   	<form:option value="BATCH_PROGRM" label="배치프로그램" />
	   	</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="배치작업에 대한 목록을 제공합니다.">
<caption>배치작업관리 목록</caption>
<thead>
  	<tr> 
		<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">배치작업ID</th>
	    <th scope="col" width="15%">배치작업명</th>
	    <th scope="col" width="20%">배치프로그램</th>
	    <th scope="col" width="20%">배치빈이름</th>
	    <th scope="col"            >배치객체/메소드</th>
  	</tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr>
        <td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
    </tr>
    </c:if>
    
    <%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(batchOpertVO.pageIndex-1) * batchOpertVO.recordPerPage}"/>
    <c:forEach items="${resultList}" var="result" varStatus="status">
    <tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.batchOpertId}"/>'); return false;">
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${batchOpertVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

        <td class="lt_text3"><c:out value='${result.batchOpertId}'/></td>
        <td class="lt_text3">${result.batchOpertNm}</td>
        <td class="lt_text3">${result.batchProgrm}</td>
        <td class="lt_text3">${result.batchBean}</td>
        <td class="lt_text3">${result.batchObject} / ${result.batchMethod}</td>
    </tr>
    </c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

function press(event) {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("batchOpertVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchOpert.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("batchOpertVO");
    if (varForm.searchKeyword.value != "") {
        if (varForm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            return;
        }
    }
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchOpert.do";
    varForm.submit();
}

function fn_aram_detail(batchOpertId) {
    var varForm = document.getElementById("batchOpertVO");
    varForm.batchOpertId.value = batchOpertId;
    varForm.action = "${pageContext.request.contextPath}/sym/bat/detailBatchOpert.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("batchOpertVO");
    varForm.batchOpertId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/bat/registBatchOpert.do";
    varForm.submit();
}

</script>
