<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ProxyLogList.jsp
  * @Description : 프록시 로그 목록
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
	<h2>프록시 로그 목록</h2>
</div>

<form:form commandName="proxySvcLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_proxySvc(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area">
 		기간 : 
        <input type="text" name="strStartDate" id="strStartDate" value="<c:out value='${proxyLogVO.strStartDate}'/>" size="10" maxlength="10" title="프록시로그 시작일자">
        <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].strStartDate); return false;" >
           	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" title="새창" alt="달력창팝업버튼이미지">
        </a>
        ~ 
        <input type="text" name="strEndDate" id="strEndDate" value="<c:out value='${proxyLogVO.strEndDate}'/>" size="10" maxlength="10" title="프록시로그 종료일자">
        <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].strEndDate); return false;" >
           	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" title="새창" alt="달력창팝업버튼이미지">
        </a>
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="프록시로그에 대한 목록을 제공한다.">
<caption>프록시로그 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="20%">프록시ID</th>
	    <th scope="col" width="25%">대상서비스</th>
	    <th scope="col" width="15%">프록시Port</th>
	    <th scope="col" width="20%">클라이언트IP</th>
	    <th scope="col" width="20%">접속일시</th>
  	</tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr>
        <td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
    </tr>
    </c:if>
    
    <%-- 데이터를 화면에 출력해준다 --%>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
	    <td class="lt_text3"><c:out value="${result.proxyId}"/></td>
	    <td class="lt_text3"><c:out value="${result.proxyNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.clntPort}"/></td>
	    <td class="lt_text3"><c:out value="${result.clntIp}"/></td>
	    <td class="lt_text3"><c:out value="${result.conectTime}"/></td>
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
    var varForm = document.getElementById("proxySvcLogVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxyLog.do";
    varForm.submit();
}

function fn_aram_search(){

    var varForm = document.getElementById("proxySvcLogVO");
    var fromDate = varForm.strStartDate.value;
    var endDate = varForm.strEndDate.value;

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

    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/pxy/listProxyLog.do";
    varForm.submit();
}

/* ********************************************************
 * 마스터목록조회 함수
 ******************************************************** */
function fn_aram_list_proxySvc(){
	location.href = "${pageContext.request.contextPath}/utl/sys/pxy/listProxySvc.do";
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
