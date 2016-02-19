<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ServerResrceMntrngList.jsp
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

<input type="hidden" name="logId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_mntrngServer(); return false;">목록</a></span>
	</div>
	<div class="keyword_area">
		<label for="strServerNm">서버H/W 명 : </label>
		<form:input path="strServerNm" size="8" title="검색" onkeypress="press();" />
        <label for="strStartDt">기간 : </label>
        <form:input path="strStartDt" size="10" maxlength="10" title="서버자원 모니터링 시작일자" />
        <a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].strServerResrceMntrngDate, document.forms[0].strStartDt); return false;" style="selector-dummy:expression(this.hideFocus=false);">
         	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" title="새창" alt="달력창팝업버튼이미지">
        </a>
        ~ 
        <form:input path="strEndDt" size="10" maxlength="10" title="서버자원 모니터링 종료일자" />
        <a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].strServerResrceMntrngDate, document.forms[0].strEndDt); return false;" style="selector-dummy:expression(this.hideFocus=false);">
         	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" title="새창" alt="달력창팝업버튼이미지">
        </a>
		<input type="hidden" name="strServerResrceMntrngDate" value=""/>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="서버자원모니터링 결과정보에 대한 목록을 제공한다.">
<caption>서버자원모니터링 목록</caption>
 	<thead>
  	<tr>
    	<!--
    	<th width="20%">로그ID</th>
    	-->
  		<th scope="col" width="7%" >No.</th>
    	<th scope="col"            >서버H/W 명</th>
    	<th scope="col" width="15%">서버H/W IP</th>
    	<th scope="col" width="15%">CPU사용률</th>
    	<th scope="col" width="15%">메모리사용률</th>
    	<th scope="col" width="15%">서비스상태</th>
    	<th scope="col" width="20%">생성일시</th>
  	</tr>
 	</thead>
 	<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="7"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
 	<c:set var="startIndex" value="${(serverResrceMntrngVO.searchVO.pageIndex-1) * serverResrceMntrngVO.searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('${result.logId}'); return false;">
  	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${serverResrceMntrngVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

     	<td class="lt_text3"><c:out value="${result.serverNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.serverEqpmnIp}"/></td>
    	<td class="lt_text3"><c:out value="${result.cpuUseRt}"/><c:if test="${result.cpuUseRt != null}">%</c:if></td>
    	<td class="lt_text3"><c:out value="${result.moryUseRt}"/><c:if test="${result.cpuUseRt != null}">%</c:if></td>
    	<td class="lt_text3"><c:out value="${result.svcSttusNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.creatDt}"/></td>
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
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listServerResrceMntrng.do";
    varForm.submit();
}

function fn_aram_search() {

    var varForm = document.getElementById("serverResrceMntrngVO");
    var fromDate = varForm.strStartDt.value;
    var endDate = varForm.strEndDt.value;

    // var tmpFromDate = fromDate.substring(0,4)+fromDate.substring(5,7)+fromDate.substring(8,10);
    // var tmpEndDate = endDate.substring(0,4)+endDate.substring(5,7)+endDate.substring(8,10);

    var tmpFromDate = fromDate.split("-");
    var tmpEndDate = endDate.split("-");

    var strTmpFromDate = "";
    var strTmpEndDate = "";

    for(var i=0;i<tmpFromDate.length;i++) {
    	strTmpFromDate = strTmpFromDate + tmpFromDate[i];
    }

    for(var j=0;j<tmpEndDate.length;j++) {
    	strTmpEndDate = strTmpEndDate + tmpEndDate[j];
    }

    if(strTmpFromDate.length != 8 || strTmpEndDate.length != 8 || !checknum(strTmpFromDate) || !checknum(strTmpEndDate)) {
        alert("날짜 형식이 잘못되었습니다");
        return;
    } else {
        if(strTmpFromDate> strTmpEndDate) {
            alert("시작일자는 종료일자보다 클 수 없습니다");
            return;
        }
    }

    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listServerResrceMntrng.do";
    varForm.submit();
}

function fn_aram_detail(logId) {
    var varForm = document.getElementById("serverResrceMntrngVO");
    varForm.logId.value = logId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/detailServerResrceMntrng.do";
    varForm.submit();
}

/*********************************************************
 *목록 함수
 ******************************************************** */
function fn_aram_list_mntrngServer(){
    var varForm = document.getElementById("serverResrceMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/srm/listMntrngServer.do";
    varForm.submit();
}

function checknum(number) {

    var rtnMsg = true;
	var pattern = /^[0-9]+$/;

	if(!pattern.test(number)) {
        rtnMsg = false;
    }

    return rtnMsg;
}

</script>
