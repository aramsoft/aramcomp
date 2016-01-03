<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : UserStats.jsp
  * @Description : 사용자통계 조회
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
<title>사용자통계 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body onload="javascript:fnInitAll();">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main"> 

<div class="content_title">
	<h2>사용자통계 조회</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="statsVO" action="${pageContext.request.contextPath}/sts/ust/selectUserStats.do"  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pdKind" />
<form:hidden path="statsKind" />
<form:hidden path="detailStatsKind" value=""/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fnSearch(); return false;">검색</a></span>
		<span class="button"><a href="#" onclick="javascript:fnInitAll(); return false;">초기화</a></span>
	</div>
	<div class="keyword_area1" >
		기간 : 
       	<form:hidden path="fromDate" />
       	<input type="text" name="fromDateView" value="" size="10" tabindex="1" title="시작일자(새창)" id="fromDateView"/>
       	<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].fromDate, document.forms[0].fromDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
       		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
       	</a>
       	<form:hidden path="toDate" />
       	<input type="text" name="toDateView" value="" size="10" tabindex="2" title="종료일자(새창)" id="toDateView" />
       	<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].toDate, document.forms[0].toDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
       		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
       	</a>&nbsp;&nbsp;
		기간구분 :
		<select id="PD" name="PD" class="select" onChange="javascript:fnChangePdKind();" tabindex="3" title="기간구분">
 			<option selected value=''>구분</option>
 			<option value='Y'>연도별</option>
 			<option value='M'>월별</option>
 			<option value='D'>일별</option>
  		</select>&nbsp;&nbsp;
  	</div>	
	<div class="keyword_area2" style="width:50%; ">
  		통계구분 :
  		<select id="STKIND" name="STKIND" class="select" onChange="javascript:fnChangeStsKind();" tabindex="4" title="통계구분">
    		<option selected value=''>구분</option>
    		<option value='COM012'>회원유형별</option>
    		<option value='COM013'>회원상태별</option>
    		<option value='COM014'>성별</option>
     	</select>
     	<select id="DTSTKIND" name="DTSTKIND" class="select" onChange="javascript:fnChangeDetailKind();" tabindex="5" title="">
    		<option selected value=''>전체</option>
     	</select>
     	<!-- 아래 3개 콤보는 DTSTKIND 콤보로 세부통계구분 데이터 교체를 위한 콤보임 -->
     	<select id="COM012" name="COM012" class="select" style="display:none; width:150px;" title="">
    	<!-- <option selected value=''>전체</option>-->
    		<c:forEach var="result" items="${COM012_mberType}" varStatus="status">
			<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
			</c:forEach>
     	</select>
     	<select id="COM013" name="COM013" class="select" style="display:none; width:150px;" title="">
    	<!-- <option selected value=''>전체</option>-->
    		<c:forEach var="result" items="${COM013_mberSttus}" varStatus="status">
			<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
			</c:forEach>
     	</select>
  		<select id="COM014" name="COM014" class="select" style="display:none; width:150px;" title="">
   		<!-- <option selected value=''>전체</option>-->
    		<c:forEach var="result" items="${COM014_sexdstn}" varStatus="status">
			<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
			</c:forEach>
     	</select>
	</div>
</div>

</form:form>

<div class="content_title" style="clear:both; margin-bottom:20px;">
	<h2>사용자 통계 결과</h2>
</div>
	
<div style="clear:both;">

	<div class="content_title" style="margin-bottom:20px;">
		<h2>그래프 (단위, 명)</h2>
	</div>
	<div style="width:80%; margin-bottom:20px;">
	   	<table class="table-list" border="0">
		 	<c:forEach items="${userStats}" var="resultInfo" varStatus="status">
			<tr>
			   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
			   	<td width="350" height="10">
			  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.statsCo * statsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
				</td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(userStats) == 0}">
	    		<tr><td></td></tr>
	    	</c:if>
		 </table>
	</div>

	<div class="content_title" style="margin-bottom:20px;">
		<h2>텍스트 (단위, 횟수)</h2>
	</div>
	<div style="width:80%; margin-bottom:20px;">
	   	<table class="table-list" border="0">
		    <c:forEach items="${userStats}" var="resultInfo" varStatus="status">
		    <tr>
			   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
			   	<td width="350" height="10">
			  		&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
				</td>
		    </tr>
		    </c:forEach>
		    
			<c:if test="${fn:length(userStats) == 0}">
	    		<tr><td></td></tr>
	    	</c:if>
		</table>
	</div>

</div>

</DIV>	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInitAll(){
    var varForm = document.getElementById("statsVO");

	// 시작일자, 종료일자
	if (varForm.fromDate.value == "" && varForm.toDate.value == "") {
		var now = new Date();
	    var year= now.getFullYear();
	    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		var toDay = year + mon + day;

		varForm.fromDate.value = toDay;
		varForm.toDate.value = toDay;
		toDay = year + "-" + mon + "-" + day;

		varForm.fDate.value = toDay;
		varForm.tDate.value = toDay;
	} else if (varForm.fromDate.value != "" && varForm.toDate.value != "") {
		var fromDate = varForm.fromDate.value;
		var toDate = varForm.toDate.value;

		varForm.fDate.value = fromDate.substring(0, 4) + "-" + fromDate.substring(4, 6) + "-" + fromDate.substring(6, 8);
		varForm.tDate.value = toDate.substring(0, 4) + "-" + toDate.substring(4, 6) + "-" + toDate.substring(6, 8);
	}

	// 기간구분
	var pdKind = varForm.pdKind.value;
	var v_pdKind = document.getElementById("PD");
	for(var i = 0; i < v_pdKind.options.length; i++) {
		if (pdKind == v_pdKind.options[i].value) {
			v_pdKind.selectedIndex = i;
		}
	}
	// 사용자 통계 기간구분은 일자별로만 검색되도록 함
	v_pdKind.selectedIndex = 3;
	varForm.pdKind.value = "D";

	// 통계구분
	var statsKind = varForm.statsKind.value;
	var v_statsKind = document.getElementById("STKIND");
	for(var j = 0; j < v_statsKind.options.length; j++) {
		if (statsKind == v_statsKind.options[j].value) {
			v_statsKind.selectedIndex = j;
			fnChangeStsKind();
		}
	}
	// 세부통계구분
	var detailStatsKind = varForm.detailStatsKind.value;
	var v_detailStatsKind = document.getElementById("DTSTKIND");
	for(var k = 0; k < v_detailStatsKind.options.length; k++) {
		if (detailStatsKind == v_detailStatsKind.options[k].value) {
			v_detailStatsKind.selectedIndex = k;
		}
	}
}

/*********************************************************
 * 조회 처리
 *********************************************************/
function fnSearch(){
    var varForm = document.getElementById("statsVO");

    var fromDate = varForm.fromDate.value;
	var toDate = varForm.toDate.value;
	var pdKind = varForm.pdKind.value;
	var statsKind = varForm.statsKind.value;

	if (fromDate == "") {
		alert("기간 시작일자를 입력하세요");
		return;
	} else if (toDate == "") {
		alert("기간 종료일자를 입력하세요");
		return;
	} else if (pdKind == "") {
		alert("기간 구분을 선택하세요");
		return;
	} else if (statsKind == "") {
		alert("통계 구분을 선택하세요");
		return;
	}

	varForm.action = "${pageContext.request.contextPath}/sts/ust/selectUserStats.do";
	varForm.submit();
}

/* ********************************************************
 * 기간구분 변경
 ******************************************************** */
function fnChangePdKind(){
    var varForm = document.getElementById("statsVO");

    var v_pdKind = document.getElementById("PD");
    varForm.pdKind.value = v_pdKind.options[v_pdKind.selectedIndex].value;
}

/* ********************************************************
 * 통계구분 변경
 ******************************************************** */
function fnChangeStsKind(){
    var varForm = document.getElementById("statsVO");

    for (var i = varForm.DTSTKIND.options.length-1;i>=1;i--) {
    	varForm.DTSTKIND.options[i] = null;
	}
	var v_statsKind = document.getElementById("STKIND");
	var v_detailStatsKind = document.getElementById("DTSTKIND");
	var v_com012 = document.getElementById("COM012");
	var v_com013 = document.getElementById("COM013");
	var v_com014 = document.getElementById("COM014");
	var code = v_statsKind.options[v_statsKind.selectedIndex].value;
	varForm.statsKind.value = code;

	if (code == "COM012") {
		for(var j = 0; j < v_com012.options.length; j++) {
			v_detailStatsKind.appendChild(v_com012.options[j].cloneNode(true));
		}
	} else if (code == "COM013") {
		for(var j = 0; j < v_com013.options.length; j++) {
			v_detailStatsKind.appendChild(v_com013.options[j].cloneNode(true));
		}
	} else if (code == "COM014") {
		for(var j = 0; j < v_com014.options.length; j++) {
			v_detailStatsKind.appendChild(v_com014.options[j].cloneNode(true));
		}
	}
}

/* ********************************************************
 * 세부통계구분 변경
 ******************************************************** */
function fnChangeDetailKind(){
    var varForm = document.getElementById("statsVO");

    var v_detailStatsKind = document.getElementById("DTSTKIND");
    varForm.detailStatsKind.value = v_detailStatsKind.options[v_detailStatsKind.selectedIndex].value;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sts:" + encodeURIComponent("사용자통계");	
	window.open(url, "도움말");
}

</script>

</body>
</html>