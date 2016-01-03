<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ClubListByTrget.jsp
  * @Description : 동호회 목록
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
	<h2>동호회 목록</h2>
</div>

<form:form commandName="clubVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cmmntyId" />
<form:hidden path="clbId" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
	   	<form:select path="searchCondition" class="select" title="검색유형선력">
		   	<form:option value="CLB_NM" label="동호회명" />
		   	<form:option value="CMMNTY_NM" label="커뮤니티명" />
	   	</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list">
 <thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >동호회명</th>
	    <th scope="col" width="20%">커뮤니티명</th>
	    <th scope="col" width="20%">등록일</th>
	    <th scope="col" width="10%">사용여부</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3"  colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(clubVO.pageIndex-1) * clubVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.clbId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<td class="lt_text3"><c:out value="${index}"/></td>

		<td class="lt_text3"><c:out value="${result.clbNm}"/></td>
		<td class="lt_text3"><c:out value="${result.cmmntyNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
		<td class="lt_text3">
		    <c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
		    <c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

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
    var varForm = document.getElementById("clubVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listClubByTrget.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("clubVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listClubByTrget.do";
    varForm.submit();
}

function fn_aram_detail(clbId) {
    var varForm = document.getElementById("clubVO");
    varForm.clbId.value = clbId;
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/detailClubByTrget.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("clubVO");
    varForm.clbId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/registClubByTrget.do";
    varForm.submit();
}

</script>

