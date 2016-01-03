<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : UserLogList.jsp
  * @Description : 사용자 로그 목록
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>사용자 로그 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>사용자 로그조회</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="userLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type=hidden name="occrrncDe">
<input type=hidden name="rqesterId">
<input type=hidden name="srvcNm">
<input type=hidden name="methodNm">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
  		&nbsp;발생일자 : &nbsp;
     	<form:hidden path="searchBgnDe" />
    	<c:if test="${!empty userLogVO.searchBgnDe}">
			<c:set var="searchBgnDeVal" value="${fn:substring(userLogVO.searchBgnDe, 0,4)}-${fn:substring(userLogVO.searchBgnDe, 4,6)}-${fn:substring(userLogVO.searchBgnDe, 6,8)}"/>
     	</c:if>
     	<input name="searchBgnDeView" id="searchBgnDeView" type="text" size="10" title="조회시작일자" value="${searchBgnDeVal}"  readonly />
     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchBgnDe, document.forms[0].searchBgnDeView); return false;">
     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
     	</a>
   		~
     	<form:hidden path="searchEndDe" />
    	<c:if test="${!empty userLogVO.searchEndDe}">
			<c:set var="searchEndDeVal" value="${fn:substring(userLogVO.searchEndDe, 0,4)}-${fn:substring(userLogVO.searchEndDe, 4,6)}-${fn:substring(userLogVO.searchEndDe, 6,8)}"/>
     	</c:if>
     	<input name="searchEndDeView" id="searchEndDeView" type="text" size="10" title="조회종료일자" value="${searchEndDeVal}"  readonly />
     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchEndDe, document.forms[0].searchEndDeView); return false;">
     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
     	</a>
  		&nbsp;사용자 : &nbsp;
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

<table class="table-list">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">발생일자</th>
	    <th scope="col" width="15%">사용자</th>
	    <th scope="col"            >메소드명</th>
	    <th scope="col" width="5%" >생성</th>
	    <th scope="col" width="5%" >수정</th>
	    <th scope="col" width="5%" >조회</th>
	    <th scope="col" width="5%" >삭제</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	 	<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(userLogVO.pageIndex-1) * userLogVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr  class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.occrrncDe}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${userLogVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.occrrncDe}"/></td>
	    <td class="lt_text3"><c:out value="${result.rqesterNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.methodNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.creatCo}"/></td>
	    <td class="lt_text3"><c:out value="${result.updtCo}"/></td>
	    <td class="lt_text3"><c:out value="${result.rdCnt}"/></td>
	    <td class="lt_text3"><c:out value="${result.deleteCo}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
	
<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
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
    var varForm = document.getElementById("userLogVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/sym/log/ulg/listUserLog.do";
	varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("userLogVO");

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
	varForm.action = "${pageContext.request.contextPath}/sym/log/ulg/listUserLog.do";
	varForm.submit();
}

function fn_aram_detail(occrrncDe, rqesterId, srvcNm, methodNm){
    var varForm = document.getElementById("userLogVO");
	varForm.occrrncDe.value = occrrncDe;
	varForm.rqesterId.value = rqesterId;
	varForm.srvcNm.value = srvcNm;
	varForm.methodNm.value = methodNm;
	varForm.action = "${pageContext.request.contextPath}/sym/log/ulg/detailUserLog.do";
	varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("사용로그관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
