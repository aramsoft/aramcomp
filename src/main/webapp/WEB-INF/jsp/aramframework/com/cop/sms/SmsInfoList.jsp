<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : SmsInfoList.jsp
  * @Description : 문자메시지 목록
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
	<h2>문자메시지 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="smsVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="smsId" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;">전송</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" class="select" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
	   		<form:option value="RECPTN_TELNO" label="수신전화번호" />
	   		<form:option value="TRNSMIS_CN" label="내용" />
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="문자메시지 전송에 대한 목록을 제공합니다.">
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col"            >전송일시</th>
    	<th scope="col" width="20%">발신전화번호</th>
    	<th scope="col" width="15%">수신전화번호수</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

 	<c:set var="startIndex" value="${(smsVO.pageIndex-1) * smsVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
  		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${smsVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.smsId}"/>'); return false;">
    			<fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/>
	   		</a>
	   		</span>
    	</td>
	    <td class="lt_text3"><c:out value="${result.trnsmitTelno}"/></td>
	    <td class="lt_text3"><c:out value="${result.recptnCnt}"/></td>
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
		fn_aram_search_sms();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("smsVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/sms/listSms.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("smsVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/sms/listSms.do";
    varForm.submit();
}

function fn_aram_detail(smsId) {
    var varForm = document.getElementById("smsVO");
    varForm.smsId.value = smsId;
    varForm.action = "${pageContext.request.contextPath}/cop/sms/detailSms.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("smsVO");
    varForm.smsId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/sms/registSms.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("문자메시지서비스");	
	window.open(url, "도움말");
}

</script>
