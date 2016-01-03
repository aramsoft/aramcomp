<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="aramframework.com.utl.fcc.service.DateUtil"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.cop.smt.lsm.service.LeaderSchdulVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : LeaderSchdulDailyList.jsp
 * @Description : 일별 간부일정 조회
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
<%!
public String DateTypeIntForString(int iInput){
	String sOutput = "";
	if(Integer.toString(iInput).length() == 1){
		sOutput = "0" + Integer.toString(iInput);
	}else{
		sOutput = Integer.toString(iInput);
	}
		
    return sOutput;
}
%>

<%
	java.util.Calendar cal = java.util.Calendar.getInstance();
	
	LeaderSchdulVO leaderSchdulVO = (LeaderSchdulVO) request.getAttribute("leaderSchdulVO");
	String strYear = leaderSchdulVO.getYear();
	String strMonth = leaderSchdulVO.getMonth();
	String strDay = leaderSchdulVO.getDay();
	
	int iNowYear = cal.get(java.util.Calendar.YEAR);
	int iNowMonth = cal.get(java.util.Calendar.MONTH);
	int iNowDay = cal.get(java.util.Calendar.DATE);
 	
	if(!strYear.isEmpty())
	{
		iNowYear = Integer.parseInt(strYear);
		iNowMonth = Integer.parseInt(strMonth);
		if( !strDay.isEmpty() ) iNowDay = Integer.parseInt(strDay);
	}

	//년도/월 셋팅
	cal.set(iNowYear, iNowMonth, 1);
	
	int iEndDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
%> 
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>일별 간부일정 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<style TYPE="text/css">

#date_navigation {
	height:60px; background-color:#F3F9D7; border:1px solid #CED99C;
	font-family: "돋움"; font-size: 10pt; color:#595959; text-align:center; 
}
.select_area {
	margin:20px; display:inline-block; 
}
 
.select {font-family: "돋움"; font-size: 9pt; color:#595959;}
.divDotText { overflow:hidden; text-overflow:ellipsis; }

</style>

</head>

<body> 
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%;">

<form:form commandName="leaderSchdulVO" action="" method="post">
<form:hidden path="year" />
<form:hidden path="month" />
<form:hidden path="week" />
<form:hidden path="day" />

<!-- 날짜 네비게이션  -->
<div id="date_navigation" >

	<div class="select_area" style="width:350px;" >
	   	<label for="searchSchdulSe">일정구분 : </label>
   		<form:select path="searchSchdulSe" class="select" onChange="fn_aram_select_schdulSe(); return false;" title="일정구분선택">
			<form:option value="" label="--선택--" />
            <form:options items="${COM057_schdulSeLeader}" itemValue="code" itemLabel="codeNm"/>
		</form:select>
		<label for="leaderName">간부명 : </label>
		<form:input path="searchUser" size="10" maxlength="10" title="간부명" onkeypress="javascript:press(event); return false;" />
   		<span class="button"><a href="#" onclick="javascript:fn_aram_search_schdulSe(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	
	<div class="select_area" style="width:220px;" >
		<a href="javascript:fn_aram_move_year('<%=iNowYear-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전년도">
		</a>
		<b><%=iNowYear%>년</b>
		<a href="javascript:fn_aram_move_year('<%=iNowYear+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음년도">
		</a>

		<% if(iNowMonth> 0 ){ %>
		<a href="javascript:fn_aram_move_month('<%=iNowMonth-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전월">
		</a>
		<% } %>
		<b><%=iNowMonth+1%>월</b>
		<% if(iNowMonth < 11 ){	%>
		<a href="javascript:fn_aram_move_month('<%=iNowMonth+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음월">
		</a> 
		<% } %>

		<% if(iNowDay> 1 ){	%>
		<a href="javascript:fn_aram_move_day('<%=iNowDay-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전일">
		</a>
		<% } %>
		<b><%=iNowDay%>일</b>
		<% if(iNowDay < iEndDay ){ %>
		<a href="javascript:fn_aram_move_day('<%=iNowDay+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음일">
		</a> 
		<% } %>
	</div>
</div>
</form:form>
<!-- //날짜 네비게이션  -->

<br>
<table class="table-calendar" style="font-size:9px;font-weight: bold;background-color:#828486;" summary="이표는 일별 간부일정 목록을 제공하며, 시간, 일정명, 간부 정보로 구성되어 있습니다.">	
<caption>일별간부일정목록</caption> 
	<tr>
		<th scope="col" width="240px" bgcolor="#E0E0E0" align="center" height="30px" style="font-size:12px;font-weight: bold;">시간</th>
		<th scope="col" bgcolor="#E0E0E0" align="center" style="font-size:12px;font-weight: bold;">일정명</th>
		<th scope="col" width="100px" bgcolor="#E0E0E0" align="center" style="font-size:12px;font-weight: bold;">간부</th>
	</tr>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td bgcolor="#FFFFFFFF"  colspan="3" align="center" style="padding-center:2px;font-size:11px;" height="30px"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	<%
		@SuppressWarnings("unchecked")
		List<EgovMap> listResult = (List<EgovMap>)request.getAttribute("resultList");
		EgovMap egovMap = new EgovMap();
		if(listResult != null){
			for(int i=0; i < listResult.size(); i++){
		egovMap = (EgovMap)listResult.get(i);
	%>
	<tr>  
	    <td bgcolor="#FFFFFF"  valign="middle" align="center" style="padding-left:2px;font-size:11px;" height="20px">
	    <%
	    	out.print("<a href=\"JavaScript:fn_aram_detail_leaderSchdul('" + (String)egovMap.get("schdulId") + "')\">");
	    	    out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시 ");
	    		out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분 ~ ");
	    		out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시 ");
	    		out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");
	    		out.println("</a>");
	    %>
	    </td>
	    <td bgcolor="#FFFFFF"  valign="middle" style="padding-left:2px;font-size:11px;">
	    <%
	    	String sSchdulBgnDate = ((String)egovMap.get("schdulBgnde")).substring(0, 8);
	    		String sSchdulEndDate = ((String)egovMap.get("schdulEndde")).substring(0, 8);
	    		
	    		//out.print("<table><tr><td><div class='divDotText' style='width:350px;border:solid 0px;'><a href=\"${pageContext.request.contextPath}/cop/smt/lsm/detailLeaderSchdul.do?schdulId=" + (String)leaderSchdulVO.getSchdulId() + "&amp;schdulDe=" + sUseDate + "&amp;searchMode=DAILY&amp;year=" + iNowYear + "&amp;month=" + iNowMonth + "&amp;day=" + iNowDay + "\" target=\"_parent\" onClick=\"JavaScript:fn_aram_detail_LeaderSchdul('" + leaderSchdulVO.getSchdulId() + "','" + sUseDate + "')\">");
	    	    out.print("<a href=\"JavaScript:fn_aram_detail_leaderSchdul('" + (String)egovMap.get("schdulId") + "'); return false;\">");
	    		out.print("[");
	    		out.print(DateUtil.formatDate(sSchdulBgnDate, "-"));
	    		out.print(" ~ ");
	    		out.print(DateUtil.formatDate(sSchdulEndDate, "-"));
	    		out.print("] &nbsp;");
	    		out.print((String)egovMap.get("schdulNm"));
	    		out.println("</a>");
	    %>
	    </td> 
	    <td bgcolor="#FFFFFF"  valign="middle" align="center" style="padding-left:2px;font-size:11px;">
	    <%
		//out.print("<a href=\"${pageContext.request.contextPath}/cop/smt/lsm/detailLeaderSchdul.do?schdulId=" + (String)leaderSchdulVO.getSchdulId() + "&amp;schdulDe=" + sUseDate + "&amp;searchMode=DAILY&amp;year=" + iNowYear + "&amp;month=" + iNowMonth + "&amp;day=" + iNowDay + "\" target=\"_parent\" onClick=\"JavaScript:fn_aram_detail_LeaderSchdul('" + leaderSchdulVO.getSchdulId() + "','" + sUseDate + "')\">");
	    out.print("<a href=\"JavaScript:fn_aram_detail_leaderSchdul('" + (String)egovMap.get("schdulId") + "'); return false;\">");
		out.print((String)egovMap.get("leaderName"));
		//out.println("</a>");
		out.println("</a>");
		%>
	    </td>  
	</tr>
	 <%
		}
	}
	%>
</table>
	
</DIV>

<script type="text/javascript">

var gOpener = parent.document.all? parent.document.leaderSchdulVO : parent.document.getElementById("leaderSchdulVO") ;
/* ********************************************************
* 간부일정 등록
******************************************************** */
function fn_aram_regist_leaderSchdul(sDate){
	gOpener.schdulBgnde.value = sDate;
	gOpener.schdulEndde.value = sDate;
	gOpener.target = "";
	gOpener.action = '<c:url value="/cop/smt/lsm/registLeaderSchdul.do"/>';
	gOpener.submit();
}

/* ********************************************************
* 간부일정 상세보기
******************************************************** */
function fn_aram_detail_leaderSchdul(schdulId){
	gOpener.schdulId.value = schdulId;
	gOpener.target = "";
	gOpener.action = '<c:url value="/cop/smt/lsm/detailLeaderSchdul.do"/>';
	gOpener.submit();
}

var ifr= parent.document.all? parent.document.all.SchdulView : parent.document.getElementById("SchdulView") ;

window.onload = function() {
	resizeFrame(1);
};

//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
function resizeFrame(re) {

	if(ifr){

		var innerHeight = document.body.scrollHeight + (document.body.offsetHeight - document.body.clientHeight);
			
		if(ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
		{ifr.style.height = innerHeight;}

		/*
		if(!re) {
			try{ document.body.attachEvent('onclick',do_resize);
			}catch(e){document.body.addEventListener("click", do_resize, false);}
		}
		*/
	}
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_schdulSe();
	}
}

function fn_aram_select_schdulSe() {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulDaily.do";
	varForm.submit();
}

function fn_aram_search_schdulSe() {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulDaily.do";
	varForm.submit();
}

function fn_aram_move_year(value) {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.year.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulDaily.do";
	varForm.submit();
}

function fn_aram_move_month(value) {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.month.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulDaily.do";
	varForm.submit();
}

function fn_aram_move_day(value) {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.day.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulDaily.do";
	varForm.submit();
}


</script>

</body>
</html>
