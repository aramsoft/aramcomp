<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : MntrngServerList.jsp
  * @Description : 서버자원모니터링 목록
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
	<h2>서버자원모니터링 목록</h2>
</div>

<form:form commandName="serverResrceMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
		<label for="strServerNm">서버H/W 명 : </label>
		<form:input path="strServerNm" size="20" title="검색" onkeypress="press();" />
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="모니터링  대상서버에 대한 정보 목록을 제공한다.">
<caption>서버자원모니터링  대상목록</caption>
<thead>
  	<tr>
  		<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">서버H/W ID</th>
	    <th scope="col" width="20%">서버H/W 명</th>
	    <th scope="col" width="20%">서버H/W IP</th>
	    <th scope="col"            >담당자 E-Mail</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="5"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
    <c:set var="searchVO" value="${serverResrceMntrngVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.serverEqpmnId}"/></td>
	    <td class="lt_text3"><c:out value="${result.serverNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.serverEqpmnIp}"/></td> 
	    <td class="lt_text3"><c:out value="${result.mngrEamilAddr}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
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
    var varForm = document.getElementById("serverResrceMntrngVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listMntrngServer.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("serverResrceMntrngVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listMntrngServer.do";
    varForm.submit();
}

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){
    var varForm = document.getElementById("serverResrceMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listServerResrceMntrng.do";
    varForm.submit();  
}

</script>
