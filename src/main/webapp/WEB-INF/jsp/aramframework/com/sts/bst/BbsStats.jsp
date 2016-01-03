<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BbsStats.jsp
  * @Description : 게시물통계 조회 
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
<title>게시물통계 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body onload="javascript:fnInitAll();">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>게시물 통계 검색</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle; display:inline-block;" alt="도움말아이콘이미지">
	</a>
</div>

<form:form commandName="statsVO" action="${pageContext.request.contextPath}/sts/ust/selectBbsStats.do"  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pdKind" />
<form:hidden path="statsKind" />
<form:hidden path="detailStatsKind" value=""/>
<form:hidden path="tabKind" />

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
       	<input type="text" name="tDateView" value="" size="10" tabindex="2" title="종료일자(새창)" id="tDateView" />
       	<a href="#" onclick="fn_aram_NormalCalendar(document.forms[0].toDate, document.forms[0].tDateView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
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
  			<option selected value=''>선택하세요</option>
  			<option value='COM004'>게시판유형별</option>
  			<option value='COM005'>게시판템플릿별</option>
  			<option value='COM009'>게시판속성별</option>
   		</select>
   		<select id="DTSTKIND" name="DTSTKIND" class="select" onChange="javascript:fnChangeDetailKind()" tabindex="5" title="통계구분2">
  			<option selected value=''>전체</option>
   		</select>
   		<!-- 아래 3개 콤보는 DTSTKIND 콤보로 세부통계구분 데이터 교체를 위한 콤보임 -->
   		<select id="COM004" name="COM004" class="select" style="display:none; width:150px;" title="">
  			<c:forEach var="result" items="${COM004_bbsType}" varStatus="status">
			<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
			</c:forEach>
   		</select>
   		<select id="COM005" name="COM005" class="select" style="display:none; width:150px;" title="">
  			<c:forEach var="result" items="${COM005_tmplatSe}" varStatus="status">
			<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
			</c:forEach>
   		</select>
   		<select id="COM009" name="COM009" class="select" style="display:none; width:150px;" title="">
  			<c:forEach var="result" items="${COM009_bbsAttrb}" varStatus="status">
			<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
			</c:forEach>
   		</select>
	</div>
</div>

</form:form>

<!-- 생성글수, 총조회수, 평균조회수, 최고/최소조회수, 최고게시자 탭 -->
<table border="0" summary="기간, 기간구분, 통계구분을 입력하여 MOPAS게시물통계를 조회한다.">
<CAPTION style="display: none;">게시물 통계 검색</CAPTION>
	<tr>
     	<td><span class="button"><a href="#" onclick="javascript:fnChangeTab('tab1'); return false;">생성글수</a></span></td>
      	<td>&nbsp;&nbsp;</td>
      	<td><span class="button"><a href="#" onclick="javascript:fnChangeTab('tab2'); return false;">총조회수</a></span></td>
      	<td>&nbsp;&nbsp;</td>
     	<td><span class="button"><a href="#" onclick="javascript:fnChangeTab('tab3'); return false;">평균조회수</a></span></td>
      	<td>&nbsp;&nbsp;</td>
     	<td><span class="button"><a href="#" onclick="javascript:fnChangeTab('tab4'); return false;">최고/최소조회수</a></span></td>
      	<td>&nbsp;&nbsp;</td>
     	<td><span class="button"><a href="#" onclick="javascript:fnChangeTab('tab5'); return false;">최고게시자</a></span></td>
    </tr>
</table>
<br/>

<!-- 생성글수 탭 -->
<div id="tab1" style="display:none;">

	<div class="content_title" style="clear:both; margin-bottom:20px;">
		<h2>생성글수 통계 결과</h2>
	</div>
		
	<div style="clear:both;">
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>그래프 (단위, 개)</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
		   	<table class="table-list" border="0">
			 	<c:forEach items="${bbsStatsList}" var="resultInfo" varStatus="status">
				<tr>
				   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
				   	<td width="350" height="10">
				  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.statsCo * statsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
					</td>
				</tr>
				</c:forEach>
				
				<c:if test="${fn:length(bbsStatsList) == 0}">
		    		<tr><td></td></tr>
		    	</c:if>
			 </table>
		</div>
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>텍스트 (단위, 개)</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
		   	<table class="table-list" border="0">
			    <c:forEach items="${bbsStatsList}" var="resultInfo" varStatus="status">
			    <tr>
				   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
				   	<td width="350" height="10">
				  		&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
					</td>
			    </tr>
			    </c:forEach>
			    
				<c:if test="${fn:length(bbsStatsList) == 0}">
		    		<tr><td></td></tr>
		    	</c:if>
			</table>
		</div>
	
	</div>

</div>

<!-- 총조회수 탭 -->
<div id="tab2" style="display:none;">

	<div class="content_title" style="clear:both; margin-bottom:20px;">
		<h2>총조회수 통계 결과</h2>
	</div>
		
	<div style="clear:both;">
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>그래프 (단위, 회)</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
		   	<table class="table-list" border="0">
			 	<c:forEach items="${bbsStatsList}" var="resultInfo" varStatus="status">
				<tr>
				   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
				   	<td width="350" height="10">
				  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.statsCo * statsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
					</td>
				</tr>
				</c:forEach>
				
				<c:if test="${fn:length(bbsStatsList) == 0}">
		    		<tr><td></td></tr>
		    	</c:if>
			 </table>
		</div>
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>텍스트 (단위, 개)</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
		   	<table class="table-list" border="0">
			    <c:forEach items="${bbsStatsList}" var="resultInfo" varStatus="status">
			    <tr>
				   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
				   	<td width="350" height="10">
				  		&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
					</td>
			    </tr>
			    </c:forEach>
			    
				<c:if test="${fn:length(bbsStatsList) == 0}">
		    		<tr><td></td></tr>
		    	</c:if>
			</table>
		</div>
	
	</div>
	
</div>

<!-- 평균조회수 탭 -->
<div id="tab3" style="display:none;">

	<div class="content_title" style="clear:both; margin-bottom:20px;">
		<h2>평균조회수 통계 결과</h2>
	</div>
		
	<div style="clear:both;">
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>그래프 (단위, 회)</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
		   	<table class="table-list" border="0">
			 	<c:forEach items="${bbsStatsList}" var="resultInfo" varStatus="status">
				<tr>
				   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
				   	<td width="350" height="10">
				  		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/left_bg.gif" width="<c:out value='${resultInfo.statsCo * statsVO.maxUnit}' />" height="10" align="left" alt="">&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
					</td>
				</tr>
				</c:forEach>
				
				<c:if test="${fn:length(bbsStatsList) == 0}">
		    		<tr><td></td></tr>
		    	</c:if>
			 </table>
		</div>
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>텍스트 (단위, 개)</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
		   	<table class="table-list" border="0">
			    <c:forEach items="${bbsStatsList}" var="resultInfo" varStatus="status">
			    <tr>
				   	<td width="100" height="10" class="lt_text3">${resultInfo.statsDate}</td>
				   	<td width="350" height="10">
				  		&nbsp;&nbsp;${resultInfo.statsCo}&nbsp;회
					</td>
			    </tr>
			    </c:forEach>
			    
				<c:if test="${fn:length(bbsStatsList) == 0}">
		    		<tr><td></td></tr>
		    	</c:if>
			</table>
		</div>
	
	</div>
	
</div>

<!-- 최고/최소조회수 탭 -->
<div id="tab4" style="display:none;">

	<div class="content_title" style="clear:both; margin-bottom:20px;">
		<h2>최고/최소조회수 통계 결과</h2>
	</div>
		
	<div style="clear:both;">
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>최고 조회 게시물 정보</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
	      	<table class="table-list" border="0">
	        <thead>
	          	<tr>
	    			<th width="10%" height="10" class="lt_text3">게시일자</th>
	            	<th width="10%" height="10" class="lt_text3">게시번호</th>
	            	<th width="40%" height="10" class="lt_text3">제목</th>
	            	<th width="10%" height="10" class="lt_text3">조회수</th>
	          	</tr>
	        </thead>
	        <tbody>
		    	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(bbsMaxStatsList) == 0}">
				<tr>
					<td class="lt_text3" colspan="4">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
				</c:if>
	        	<c:forEach items="${bbsMaxStatsList}" var="resultInfo" varStatus="status">
		    	<tr>
		      		<td class="lt_text3">${resultInfo.statsDate}</td>
		      		<td class="lt_text3">${resultInfo.mxmmInqireBbsId}</td>
		      		<td class="lt_text6">${resultInfo.mxmmInqireBbsNm}</td>
		      		<td class="lt_text3">${resultInfo.maxStatsCo}</td>
		    	</tr>
		    	</c:forEach>
		    </tbody>
	      	</table>
	   	</div>
	      	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>최소 조회 게시물 정보</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
	      	<table class="table-list" border="0">
	        <thead>
	          	<tr>
	    			<th width="10%" height="10" class="lt_text3">게시일자</th>
	            	<th width="10%" height="10" class="lt_text3">게시번호</th>
	            	<th width="40%" height="10" class="lt_text3">제목</th>
	            	<th width="10%" height="10" class="lt_text3">조회수</th>
	          	</tr>
	        </thead>
	        <tbody>
		    	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(bbsMinStatsList) == 0}">
				<tr>
					<td class="lt_text3" colspan="4">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
				</c:if>
	        	<c:forEach items="${bbsMinStatsList}" var="resultInfo" varStatus="status">
		    	<tr>
		      		<td class="lt_text3">${resultInfo.statsDate}</td>
		      		<td class="lt_text3">${resultInfo.mummInqireBbsId}</td>
		      		<td class="lt_text6">${resultInfo.mummInqireBbsNm}</td>
		      		<td class="lt_text3">${resultInfo.minStatsCo}</td>
		    	</tr>
		    	</c:forEach>
		    </tbody>
	      	</table>
	    </div>
	</div>
</div>

<!-- 최고게시자 탭 -->
<div id="tab5" style="display:none;">

	<div class="content_title" style="clear:both; margin-bottom:20px;">
		<h2>최고게시자 통계 결과</h2>
	</div>
		
	<div style="clear:both;">
	
		<div class="content_title" style="margin-bottom:20px;">
			<h2>최고 게시자 정보</h2>
		</div>
		<div style="width:80%; margin-bottom:20px;">
	      	<table class="table-list" border="0">
	        <thead>
	          	<tr>
	    			<th width="10%" height="10" class="lt_text3">게시자ID</th>
	            	<th width="10%" height="10" class="lt_text3">게시건수</th>
	            	<th width="10%" height="10" class="lt_text3">집계일자</th>
	          	</tr>
	        </thead>
	        <tbody>
		    	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(bbsMaxNtcrList) == 0}">
				<tr>
					<td class="lt_text3" colspan="3">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
				</c:if>
	        	<c:forEach items="${bbsMaxNtcrList}" var="resultInfo" varStatus="status">
		    	<tr>
		      		<td class="lt_text3">${resultInfo.topNtcepersonId}</td>
		      		<td class="lt_text3">${resultInfo.topNtcepersonCo}</td>
		      		<td class="lt_text3">${resultInfo.statsDate}</td>
		    	</tr>
		    	</c:forEach>
		    </tbody>
	      	</table>
	   	</div>
	</div>

</div>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

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
	var v_com004 = document.getElementById("COM004");
	var v_com005 = document.getElementById("COM005");
	var v_com009 = document.getElementById("COM009");
	var code = v_statsKind.options[v_statsKind.selectedIndex].value;
	varForm.statsKind.value = code;

	if (code == "COM004") {
		for(var j = 0; j < v_com004.options.length; j++) {
			v_detailStatsKind.appendChild(v_com004.options[j].cloneNode(true));
		}
	} else if (code == "COM005") {
		for(var j = 0; j < v_com005.options.length; j++) {
			v_detailStatsKind.appendChild(v_com005.options[j].cloneNode(true));
		}
	} else if (code == "COM009") {
		for(var j = 0; j < v_com009.options.length; j++) {
			v_detailStatsKind.appendChild(v_com009.options[j].cloneNode(true));
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
 * 조회 처리
 *********************************************************/
function fnSearch(){
    var varForm = document.getElementById("statsVO");

    var fromDate = varForm.fromDate.value;
	var toDate = varForm.toDate.value;
	var pdKind = varForm.pdKind.value;
	var statsKind = varForm.statsKind.value;
	var tabKind = varForm.tabKind.value;

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

	if (tabKind == "") {
		varForm.tabKind.value = "tab1";
	}

	if (tabKind == "tab4" || tabKind == "tab5") {
		alert("게시물 통계검색 최고/최소조회정보, 최고게시자 정보는 일별 검색만 적용됩니다.");
	}

	varForm.action = "${pageContext.request.contextPath}/sts/bst/selectBbsStats.do";
	varForm.submit();
}

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

	// 통계구분
	var statsKind = varForm.statsKind.value;
	var v_statsKind = document.getElementById("STKIND");
	for(var j = 0; j < v_statsKind.options.length; j++) {
		if (statsKind == v_statsKind.options[j].value) {
			v_statsKind.selectedIndex = j;
			fnChangeStsKind();
		}
	}

	// 탭 초기화
	var tabKind = varForm.tabKind.value;
	var objTab1 = document.getElementById("tab1");
	objTab1.style.display = "none";
	var objTab2 = document.getElementById("tab2");
	objTab2.style.display = "none";
	var objTab3 = document.getElementById("tab3");
	objTab3.style.display = "none";
	var objTab4 = document.getElementById("tab4");
	objTab4.style.display = "none";
	var objTab5 = document.getElementById("tab5");
	objTab5.style.display = "none";
	var obj = document.getElementById(tabKind);
	obj.style.display = "block";

	/*
	// 세부통계구분
	var detailStatsKind = document.listForm.detailStatsKind.value;
	var v_detailStatsKind = document.getElementById("DTSTKIND");
	for(var k = 0; k < v_detailStatsKind.options.length; k++) {
		if (detailStatsKind == v_detailStatsKind.options[k].value) {
			v_detailStatsKind.selectedIndex = k;
		}
	}
	*/
}

/* ********************************************************
 * 탭 변경 + 검색
 ******************************************************** */
function fnChangeTab(tab) {
    var varForm = document.getElementById("statsVO");

    varForm.tabKind.value = tab;
	fnSearch();
}

function getNextWeek(v,t){
	var str=new Array();
	var b=v.split("-");
	var c=new Date(b[0],b[1]-1,b[2]);
	var d=c.valueOf()+1000*60*60*24*t;
	var e=new Date(d);

	str[str.length]=e.getYear();
	str[str.length]=e.getMonth()+1;
	str[str.length]=e.getDate();
	return str.join("");
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sts:" + encodeURIComponent("게시물통계");	
	window.open(url, "도움말");
}

</script>
	
</body>
</html>
