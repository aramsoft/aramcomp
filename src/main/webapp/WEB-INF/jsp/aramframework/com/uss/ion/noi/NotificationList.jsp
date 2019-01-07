<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NotificationList.jsp
  * @Description : 정보알림이 목록
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
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<DIV id="main">

<div class="content_title">
	<h2>정보알림이 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="notificationVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="ntfcNo" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" class="select" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="NTCN_TM" label="알림일자" />			   
	   		<form:option value="NTCN_SJ" label="제목" />			   
	   		<form:option value="NTCN_CN" label="내용" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="등록된 정보알림이에 대한 목록을 제공합니다.">
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >제목</th>
	    <th scope="col" width="20%">알림시간</th>
	    <th scope="col" width="20%">사전알림간격</th>
	    <th scope="col" width="15%">생성일</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="searchVO" value="${notificationVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.ntfcNo}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.ntfcSj}"/></td>
	    <td class="lt_text3"><c:out value="${result.ntfcTime}"/></td>
	    <td class="lt_text3"><c:out value="${result.bhNtfcIntrvlString}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<!-- 정보알림이 표시를 위한 스크립트  -->
<script type="text/javascript">
	var noi_url = "${pageContext.request.contextPath}/uss/ion/noi/getNotifications.do";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/uss/ion/noi/Notification.js"></script>

<script type="text/javascript">

window.onload= function() {
    var varForm = document.getElementById("notificationVO");
	if (varForm.searchCondition.value == '0' && varForm.searchKeyword.value != '') {
		var str = varForm.searchKeyword.value;

		varForm.searchKeyword.value = str.substr(0,4) + '-' + str.substr(4,2) + '-' + str.substr(6,2);
	}
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("notificationVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/listNotification.do";
	varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("notificationVO");
	if (varForm.searchCondition.value == '0' && varForm.searchKeyword.value != '') {
		if (rawDateString(varForm.searchKeyword)) {
			// no-op
		} else {
			alert('<spring:message code="errors.date" arguments="알림일자" />');
			varForm.searchKeyword.focus();
			return;
		}
	}

	varForm.pageIndex.value = "1";
	varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/listNotification.do";
	varForm.submit();
}

function fn_aram_detail(ntfcNo) {
    var varForm = document.getElementById("notificationVO");
    varForm.ntfcNo.value = ntfcNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/detailNotification.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("notificationVO");
    varForm.ntfcNo.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/registNotification.do";
    varForm.submit();
}

function isValidDate(str) {
   	// var test = /^\d{4}-\d{2}-\d{2}$/;
    var test = /^\d{4}\d{2}\d{2}$/;

    if (!test.test(str)) {
	    return false;
    }

    var y, m, d;

    y =  parseInt(str.substr(0,4), 10);
	m = parseInt(str.substr(4,2), 10) - 1;
	d = parseInt(str.substr(6,2), 10);

    var dt = new Date(y, m, d);

    if (dt.getFullYear() == y && dt.getMonth() == m && dt.getDate() == d) {
        return true;
    } else {
        return false;
    }
}

function rawDateString(obj) {
	var intValue = '0123456789';

	var result = "";
	var str =  String(obj.value);

    if (str.length < 1) {
    	result = "";
    	return true;
    }

    for (var i = 0; i < str.length; i++) {
	    if (intValue.indexOf(str.charAt(i))>= 0) {	// 숫자
			result += str.charAt(i);
	    }
    }

    if (isValidDate(result)) {
	    obj.value = result;
	    return true;
    }

    return false;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("정보알림이");	
	window.open(url, "도움말");
}

</script>
