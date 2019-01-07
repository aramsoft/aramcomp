<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ReprtStatsList.jsp
  * @Description : 보고서통계 목록
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
<title>보고서통계 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body onload="javascript:initDate();">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>보고서통계 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle;" alt="도움말아이콘이미지">
	</a>
</div>

<form:form commandName="reprtStatsVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="reprtTy">
<input type="hidden" name="reprtSttus">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area" >
  		유형:
       	<form:select path="pmReprtTy" title="유형">
            <form:option value="00" label="전체"/>
            <form:options items="${COM040_reprtType}" itemValue="code" itemLabel="codeNm"/>
     	</form:select>
		기간구분:
       	<form:select path="pmDateTy" title="기간구분">
            <form:option value="" label="선택"/>
            <form:options items="${COM042_dateType}" itemValue="code" itemLabel="codeNm"/>
     	</form:select>
		기간:
		<form:hidden path="pmFromDate" />
		<input type="text" id="pmFromDateView" name="pmTyFromDate" size="10" maxlength="10" title="시작일자(새창)">
		<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].pmFromDate, document.forms[0].pmFromDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
		</a>
		~
		<form:hidden path="pmToDate" />
		<input type="text" name="pmToDateView" size="10" maxlength="10" title="종료일자(새창)">
		<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].pmToDate, document.forms[0].pmToDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
		</a>
	</div>
</div>

<form:hidden path="searchCondition" value="1" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="보고서통계에 대한 보고서유형, 진행상태 건수에 대한 정보를 제공한다.">
<CAPTION>보고서 통계</CAPTION>
<thead>
 	<tr>
   		<th scope="col" width="50%">보고서유형</th>
   		<th scope="col" width="30%">진행상태</th>
   		<th scope="col" width="20%">건수</th>
 	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="3"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.reprtTy}"/>', '<c:out value="${result.reprtSttus}"/>'); return false;">
  		<td class="lt_text3"><c:out value="${result.reprtTyNm}"/></td>
  		<td class="lt_text3"><c:out value="${result.reprtSttusNm}"/></td>
  		<td class="lt_text3"><c:out value="${result.cnt}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

<div class="content_title" style="clear:both; margin-bottom:20px;">
	<h2>기간별 통계 결과</h2>
</div>
		
<div style="width:80%; margin-bottom:20px;">
   	<table class="table-list" border="0">
	 	<c:forEach items="${reprtStatsBarList}" var="resultInfo" varStatus="status">
		<tr>
		   	<td width="100" height="10" class="lt_text3">${resultInfo.grpRegDate}</td>
		   	<td width="350" height="10">
		  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.grpCnt * reprtStatsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.grpCnt}&nbsp;개
			</td>
		</tr>
		</c:forEach>
		
		<c:if test="${fn:length(dtaUseStatsBarList) == 0}">
    		<tr><td></td></tr>
    	</c:if>
	 </table>
</div>

<div class="content_title" style="clear:both; margin-bottom:20px;">
	<h2>보고서유형별 통계 결과</h2>
</div>
		
<div style="width:80%; margin-bottom:20px;">
   	<table class="table-list" border="0">
	 	<c:forEach items="${reprtStatsByReprtTyList}" var="resultInfo" varStatus="status">
		<tr>
		   	<td width="100" height="10" class="lt_text3">${resultInfo.grpReprtTyNm}</td>
		   	<td width="350" height="10">
		  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.grpReprtTyCnt * reprtStatsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.grpReprtTyCnt}&nbsp;개
			</td>
		</tr>
		</c:forEach>
		
		<c:if test="${fn:length(reprtStatsByReprtTyList) == 0}">
    		<tr><td></td></tr>
    	</c:if>
	 </table>
</div>

<div class="content_title" style="clear:both; margin-bottom:20px;">
	<h2>진행상태별 통계 결과</h2>
</div>
		
<div style="width:80%; margin-bottom:20px;">
   	<table class="table-list" border="0">
	 	<c:forEach items="${reprtStatsByReprtSttusList}" var="resultInfo" varStatus="status">
		<tr>
		   	<td width="100" height="10" class="lt_text3">${resultInfo.grpReprtSttusNm}</td>
		   	<td width="350" height="10">
		  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.grpReprtSttusCnt * reprtStatsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.grpReprtSttusCnt}&nbsp;개
			</td>
		</tr>
		</c:forEach>
		
		<c:if test="${fn:length(reprtStatsByReprtSttusList) == 0}">
    		<tr><td></td></tr>
    	</c:if>
	 </table>
</div>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" defer="defer">

function initDate() {

    var varForm = document.getElementById("reprtStatsVO");
    var fromDate = '<c:out value="${reprtStatsVO.pmFromDate}"/>';
    var toDate = '<c:out value="${reprtStatsVO.pmToDate}"/>';

    var pmFromDate = varForm.pmFromDate.value;
    var pmToDate = varForm.pmToDate.value;

    if(varForm.pmFromDate.value == null || varForm.pmFromDate.value == '') {
        if(fromDate == '' || fromDate == null){

        }else{
        	varForm.pmFromDate.value = fromDate;
        	varForm.pmToDate.value = toDate;
	
        	varForm.pmTyFromDate.value = fromDate.substring(0,4) + '-' + fromDate.substring(4,6) + '-' + fromDate.substring(6,8);
        	varForm.pmTyToDate.value = toDate.substring(0,4) + '-' + toDate.substring(4,6) + '-' + toDate.substring(6,8);
        }
    } else {
    	varForm.pmTyFromDate.value = pmFromDate.substring(0,4) + '-' + pmFromDate.substring(4,6) + '-' + pmFromDate.substring(6,8);
    	varForm.pmTyToDate.value = pmToDate.substring(0,4) + '-' + pmToDate.substring(4,6) + '-' + pmToDate.substring(6,8);
    }
}

function press() {

    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("reprtStatsVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sts/rst/listReprtStats.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("reprtStatsVO");
	if(!checkDateTy()) return;
	varForm.pageIndex.value = '1';
	varForm.action = "${pageContext.request.contextPath}/sts/rst/listReprtStats.do";
	varForm.submit();
}

function fn_aram_detail(reprtTy, reprtSttus) {
    var varForm = document.getElementById("reprtStatsVO");
    varForm.reprtTy.value = reprtTy;
    varForm.reprtSttus.value = reprtSttus;
    varForm.action = "${pageContext.request.contextPath}/sts/rst/detailReprtStats.do";
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("reprtStatsVO");
    varForm.reprtTy.value = "";
    varForm.reprtSttus.value = "";
    varForm.action = "${pageContext.request.contextPath}/sts/rst/registReprtStats.do";
    varForm.submit();
}

function checkDateTy() {

    var varForm = document.getElementById("reprtStatsVO");
	var fromDate = varForm.pmFromDate.value;
	var toDate = varForm.pmToDate.value;

	if(varForm.pmDateTy.value == '') {
        alert("기간구분을 선택하세요.");
		return false;
	} else if(fromDate> toDate) {
        alert("종료일자는 시작일자보다  이후날짜로 선택하세요.");
        return false;
    }
	else
		return true;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sts:" + encodeURIComponent("보고서통계");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
