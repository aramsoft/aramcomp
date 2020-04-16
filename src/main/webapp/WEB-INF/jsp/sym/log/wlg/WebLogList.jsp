<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : WebLogList.jsp
  * @Description : 웹 로그 목록
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
<title>웹 로그 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>웹 로그조회</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="webLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type=hidden name="requstId">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${webLogVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		&nbsp;발생일자 : &nbsp;
	     	<form:hidden path="searchBgnDe" />
	    	<c:if test="${!empty webLogVO.searchBgnDe}">
				<c:set var="searchBgnDeVal" value="${fn:substring(webLogVO.searchBgnDe, 0,4)}-${fn:substring(webLogVO.searchBgnDe, 4,6)}-${fn:substring(webLogVO.searchBgnDe, 6,8)}"/>
	     	</c:if>
	     	<input name="searchBgnDeView" id="searchBgnDeView" type="text" size="10" title="조회시작일자" value="${searchBgnDeVal}"  readonly />
	     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchBgnDe, document.forms[0].searchBgnDeView); return false;">
	     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
	     	</a>
	   		~
	     	<form:hidden path="searchEndDe" />
	    	<c:if test="${!empty webLogVO.searchEndDe}">
				<c:set var="searchEndDeVal" value="${fn:substring(webLogVO.searchEndDe, 0,4)}-${fn:substring(webLogVO.searchEndDe, 4,6)}-${fn:substring(webLogVO.searchEndDe, 6,8)}"/>
	     	</c:if>
	     	<input name="searchEndDeView" id="searchEndDeView" type="text" size="10" title="조회종료일자" value="${searchEndDeVal}"  readonly />
	     	<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchEndDe, document.forms[0].searchEndDeView); return false;">
	     		<img name="calendarImg" src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
	     	</a>
	  		&nbsp;URL : &nbsp;
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
	      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">요청ID</th>
	    <th scope="col" width="20%">발생일자</th>
	    <th scope="col"            >URL</th>
	    <th scope="col" width="10%">요청자</th>
	    <th scope="col" width="15%">요청자IP</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(webLogVO.pageIndex-1) * webLogVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${webLogVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.requstId}"/>'); return false;">
	    		<c:out value="${result.requstId}"/>
    		</a>
			</span>
	    </td>
	    <td class="lt_text3"><c:out value="${result.occrrncDe}"/></td>
	    <td class="lt_text3"><c:out value="${result.url}"/></td>
	    <td class="lt_text3"><c:out value="${result.rqesterNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.rqesterIp}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
	
<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage"	/>
</div>
	
</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("webLogVO");
   	varForm.pageIndex.value = pageNo;
   	varForm.action = "${pageContext.request.contextPath}/sym/log/wlg/listWebLog.do";
   	varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("webLogVO");

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

   	varForm.pageIndex.value = 1;
   	varForm.action = "${pageContext.request.contextPath}/sym/log/wlg/listWebLog.do";
   	varForm.submit();
}

function fn_aram_detail(requstId){
    var varForm = document.getElementById("webLogVO");
	varForm.requstId.value = requstId;
	varForm.action = "${pageContext.request.contextPath}/sym/log/wlg/detailWebLog.do";
	varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("웹로그관리");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
