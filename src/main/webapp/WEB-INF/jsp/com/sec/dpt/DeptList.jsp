<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : DeptList.java
 * @Description : 부서 목록
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
	<h2>부서 목록</h2>
</div>

<form:form modelAttribute="deptVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="orgnztId"/>

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${deptVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		부서 명 : 
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="부서에 대한 목록을 제공한다.">
<thead>
	<tr>
     	<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">부서 ID</th>
	    <th scope="col" width="20%">부서 명</th>
	    <th scope="col"            >설명</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="startIndex" value="${(deptVO.pageIndex-1) * deptVO.recordPerPage}"/>
    <c:forEach var="result" items="${resultList}" varStatus="status">
    <tr>
  		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${deptVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.orgnztId}"/></td>
	    <td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.orgnztId}"/>'); return false;">
	    		<c:out value="${result.orgnztNm}"/>
    		</a>
			</span>
	    </td>
	    <td class="lt_text3"><c:out value="${result.orgnztDc}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("deptVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDept.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("deptVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDept.do";
    varForm.submit();
}

function fn_aram_detail(orgnztId) {
    var varForm = document.getElementById("deptVO");
    varForm.orgnztId.value = orgnztId;
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/editDept.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("deptVO");
    varForm.orgnztId.value = "";
	varForm.action = "${pageContext.request.contextPath}/sec/dpt/registDept.do";
	varForm.submit();
}

</script>
