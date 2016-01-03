<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ScrinStats.jsp
  * @Description : 화면통계 조회
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
<title>화면통계 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body onload="javascript:fnInitAll();">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main"> 

<div class="content_title">
	<h2>화면통계 조회</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="statsVO" action="${pageContext.request.contextPath}/sts/ust/selectScrinStats.do"  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pdKind" />
<form:hidden path="statsKind" />
<form:hidden path="detailStatsKind" value=""/>
<input type="hidden" name="req_RetrunPath" value="/sts/sst/ScrinStats">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fnInitAll(); return false;">초기화</a></span>
	</div>
	<div class="keyword_area" style="width:70%">
		기간 : 
       	<form:hidden path="fromDate" />
       	<input type="text" name="fromDateView" value="" size="10" tabindex="1" title="시작일자(새창)" id=fromDateView/>
       	<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].fromDate, document.forms[0].fromDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
       		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
       	</a>
       	<form:hidden path="toDate" />
       	<input type="text" name="toDateView" value="" size="10" tabindex="2" title="종료일자(새창)" id="toDateView" />
       	<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].toDate, document.forms[0].toDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
       		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
       	</a>
		기간구분 :
		<select id="PD" name="PD" class="select" onChange="javascript:fnChangePdKind();" tabindex="3" title="기간구분">
 			<option selected value=''>구분</option>
 			<option value='Y'>연도별</option>
 			<option value='M'>월별</option>
 			<option value='D'>일별</option>
  		</select>
	</div>
</div>

<div class="content_title" style="margin-bottom:20px;clear:both;">
	<h2>화면 통계 결과</h2>
</div>
	
<div style="width:40%; clear:both; float:right;">

	<div class="content_title" style="margin-bottom:20px;">
		<h2>공통서비스 메뉴</h2>
	</div>	
	
	<c:forEach var="result" items="${list_menulist}" varStatus="status">
	<input type="hidden" name="tmp_menuNm" value="<c:out value='${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.menuNo}|${result.menuOrdr}|${result.menuNm}|${result.upperMenuId}|${result.menuDc}|${result.relateImagePath}|${result.relateImageNm}|${result.progrmFileNm}|'/>">
	</c:forEach>
	
	<div class="tree" style="margin-left:50px; width:240px; height:500px; ">
	
		<script type="text/javascript">
		var imgpath = "${pageContext.request.contextPath}/images/aramframework/com/cmm/utl/";
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sts/sst/treemenu.js"></script>
		
		<script type="text/javaScript">
		    var chk_Object = true;
		    var chk_browse = "";
		    var varForm = document.getElementById("statsVO");
		    
			if (eval(varForm.req_RetrunPath)=="[object]") chk_browse = "IE";
			if (eval(varForm.req_RetrunPath)=="[object NodeList]") chk_browse = "Fox";
			if (eval(varForm.req_RetrunPath)=="[object Collection]") chk_browse = "safai";
		
			var Tree = new Array;
			if(chk_browse=="IE"&&eval(varForm.tmp_menuNm)!="[object]"){
			   	alert("메뉴 목록 데이타가 존재하지 않습니다.");
			   	chk_Object = false;
			}
			if(chk_browse=="Fox"&&eval(varForm.tmp_menuNm)!="[object NodeList]"){
			   	alert("메뉴 목록 데이타가 존재하지 않습니다.");
			   	chk_Object = false;
			}
			if(chk_browse=="safai"&&eval(varForm.tmp_menuNm)!="[object Collection]"){
				alert("메뉴 목록 데이타가 존재하지 않습니다.");
				chk_Object = false;
			}
			if( chk_Object ){
				for (var j = 0; j < varForm.tmp_menuNm.length; j++) {
					Tree[j] = varForm.tmp_menuNm[j].value;
			    }
				createTree(Tree, false);
		    }else{
		        alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.");
		    }
		</script>
	</div>
</div>	

<div style="width:60%; float:left;">

	<div class="content_title" style="margin-bottom:20px;">
		<h2>그래프 (단위, 횟수)</h2>
	</div>
	<div style="width:80%; margin-bottom:20px;">
	   	<table class="table-list" border="0">
		 	<c:forEach items="${scrinStats}" var="resultInfo" varStatus="status">
			<tr>
			   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
			   	<td width="350" height="10">
			  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.statsCo * statsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
				</td>
			</tr>
			</c:forEach>
			
			<c:if test="${fn:length(scrinStats) == 0}">
	    		<tr><td></td></tr>
	    	</c:if>
		 </table>
	</div>
		 
	<div class="content_title" style="margin-bottom:20px;">
		<h2>텍스트 (단위, 횟수)</h2>
	</div>
	<div style="width:80%; margin-bottom:20px;">
	   	<table class="table-list" border="0">
		    <c:forEach items="${scrinStats}" var="resultInfo" varStatus="status">
		    <tr>
			   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
			   	<td width="350" height="10">
			  		&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
				</td>
		    </tr>
		    </c:forEach>
		    
			<c:if test="${fn:length(scrinStats) == 0}">
	    		<tr><td></td></tr>
	    	</c:if>
		</table>
	</div>

</div>

</form:form>

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
}

/*********************************************************
 * 조회 처리
 *********************************************************/
function fnSearch(){
    var varForm = document.getElementById("statsVO");

    var fromDate = varForm.fromDate.value;
	var toDate = varForm.toDate.value;
	var pdKind = varForm.pdKind.value;

	if (fromDate == "") {
		alert("기간 시작일자를 입력하세요");
		return;
	} else if (toDate == "") {
		alert("기간 종료일자를 입력하세요");
		return;
	} else if (pdKind == "") {
		alert("기간 구분을 선택하세요");
		return;
	}

	varForm.action = "${pageContext.request.contextPath}/sts/sst/selectScrinStats.do";
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

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sts:" + encodeURIComponent("화면통계");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
