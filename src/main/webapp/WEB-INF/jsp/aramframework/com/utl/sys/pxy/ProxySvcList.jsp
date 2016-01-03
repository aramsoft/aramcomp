<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ProxySvcList.jsp
  * @Description : 프록시설정 목록
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
	<h2>프록시설정 목록</h2> 
</div>

<form:form commandName="proxySvcVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="proxyId"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
		프록시명 : 
		<form:input path="strProxyNm" size="30" title="검색" onkeypress="press();" />
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="프록시설정에 대한 목록을 제공한다.">
<caption>프록시설정 목록</caption>
<thead>
  	<tr>
  		<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">프록시ID</th>
	    <th scope="col"            >프록시명</th>
	    <th scope="col" width="10%">프록시Port</th>
	    <th scope="col" width="20%">대상서비스명</th>
	    <th scope="col" width="20%">서비스IP/Port</th>
	    <th scope="col" width="10%">상태</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="7"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
 	<c:set var="startIndex" value="${(proxySvcVO.pageIndex-1) * proxySvcVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.proxyId}"/>'); return false;">
 	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${proxySvcVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3"><c:out value="${result.proxyId}"/></td>
    	<td class="lt_text3"><c:out value="${result.proxyNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.proxyPort}"/></td>
    	<td class="lt_text3"><c:out value="${result.trgetSvcNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.svcIp}"/>:<c:out value="${result.svcPort}"/></td>
    	<td class="lt_text3"><c:out value="${result.svcSttusNm}"/></td>
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
    var varForm = document.getElementById("proxySvcVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxySvc.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("proxySvcVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxySvc.do";
    varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(proxyId) {
    var varForm = document.getElementById("proxySvcVO");
    varForm.proxyId.value = proxyId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/detailProxySvc.do";
    varForm.submit();   
}

/* ********************************************************
 * 등록 처리화면
 ******************************************************** */
function fn_aram_regist() {
    var varForm = document.getElementById("proxySvcVO");
    varForm.proxyId.value = "";
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/registProxySvc.do";
    varForm.submit(); 
}

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){
    var varForm = document.getElementById("proxySvcVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxyLog.do";
    varForm.submit();  
}

</script>
