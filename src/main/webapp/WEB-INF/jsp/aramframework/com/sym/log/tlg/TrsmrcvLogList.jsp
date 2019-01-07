<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TrsmrcvLogList.jsp
  * @Description : 송수신 로그 목록
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
<title>송수신 로그 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>송수신 로그조회</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="trsmrcvLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type=hidden name="requstId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;">송수신테스트</a></span>
	</div>
	<div class="keyword_area">
  		&nbsp;발생일자 : &nbsp;
     	<form:hidden path="searchBgnDe" />
    	<c:if test="${!empty trsmrcvLogVO.searchBgnDe}">
			<c:set var="searchBgnDeVal" value="${fn:substring(trsmrcvLogVO.searchBgnDe, 0,4)}-${fn:substring(trsmrcvLogVO.searchBgnDe, 4,6)}-${fn:substring(trsmrcvLogVO.searchBgnDe, 6,8)}"/>
     	</c:if>
     	<input name="searchBgnDeView" id="searchBgnDeView" type="text" size="10" title="조회시작일자" value="${searchBgnDeVal}"  readonly />
     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchBgnDe, document.forms[0].searchBgnDeView); return false;">
     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
     	</a>
   		~
     	<form:hidden path="searchEndDe" />
    	<c:if test="${!empty trsmrcvLogVO.searchEndDe}">
			<c:set var="searchEndDeVal" value="${fn:substring(trsmrcvLogVO.searchEndDe, 0,4)}-${fn:substring(trsmrcvLogVO.searchEndDe, 4,6)}-${fn:substring(trsmrcvLogVO.searchEndDe, 6,8)}"/>
     	</c:if>
     	<input name="searchEndDeView" id="searchEndDeView" type="text" size="10" title="조회종료일자" value="${searchEndDeVal}"  readonly />
     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchEndDe, document.forms[0].searchEndDeView); return false;">
     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
     	</a>
    	&nbsp;<label for="searchKeyword">송수신구분</label> :
   		<form:input path="searchKeyword" size="20" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="번호, 요청ID, 발생일자, 송수신구분, 연계ID, 제공기관ID, 요청기관ID, 요청자 정보가 담긴 송수신 로그 목록 테이블">
<CAPTION>송수신 로그조회</CAPTION>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">요청ID</th>
	    <th scope="col"            >발생일자</th>
	    <th scope="col" width="10%">송수신구분</th>
	    <th scope="col" width="15%">연계ID</th>
	    <th scope="col" width="10%">제공기관ID</th>
	    <th scope="col" width="10%">요청기관ID</th>
	    <th scope="col" width="15%">요청자</th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="searchVO" value="${trsmrcvLogVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.requstId}"/>'); return false;">

 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.requstId}"/></td>
	    <td class="lt_text3"><c:out value="${result.occrrncDe}"/></td>
	    <td class="lt_text3"><c:out value="${result.trsmrcvSeCodeNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.cntcId}"/></td>
	    <td class="lt_text3"><c:out value="${result.provdInsttId}"/></td>
	    <td class="lt_text3"><c:out value="${result.requstInsttId}"/></td>
	    <td class="lt_text3"><c:out value="${result.rqesterNm}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage"	/>
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
    var varForm = document.getElementById("trsmrcvLogVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/listTrsmrcvLog.do";
	varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("trsmrcvLogVO");

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
	varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/listTrsmrcvLog.do";
	varForm.submit();
}

function fn_aram_detail(requstId){
    var varForm = document.getElementById("trsmrcvLogVO");
	varForm.requstId.value = requstId;
    varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/detailTrsmrcvLog.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("trsmrcvLogVO");
	varForm.requstId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/registTrsmrcvLog.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("송_수신_로그관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
