<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.cop.smt.lsm.domain.LeaderSchdulVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% 
/**
 * @Class Name : LeaderSchdulMonthList.jsp
 * @Description : 월별 간부일정 조회
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
<%
	java.util.Calendar cal = java.util.Calendar.getInstance();
	
	LeaderSchdulVO leaderSchdulVO = (LeaderSchdulVO) request.getAttribute("leaderSchdulVO");
	String strYear = leaderSchdulVO.getYear();
	String strMonth = leaderSchdulVO.getMonth();

	int year = cal.get(java.util.Calendar.YEAR);
	int month = cal.get(java.util.Calendar.MONTH);
	int date = cal.get(java.util.Calendar.DATE);
	
	if(!strYear.isEmpty())
	{
	  	year = Integer.parseInt(strYear);
	  	month = Integer.parseInt(strMonth);
	}
	
	//년도/월 셋팅
	cal.set(year, month, 1);
	
	int startDay = cal.getMinimum(java.util.Calendar.DATE);
	int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
	int newLine = 0;

%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>월별 간부일정 조회</title>

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

	<div class="select_area" style="width:400px;" >
	   	<label for="searchSchdulSe">일정구분 : </label>
   		<form:select path="searchSchdulSe" class="select" onChange="fn_aram_select_schdulSe(); return false;" title="일정구분선택">
			<form:option value="" label="--선택--" />
            <form:options items="${COM057_schdulSeLeader}" itemValue="code" itemLabel="codeNm"/>
		</form:select>
		<label for="leaderName">간부명 : </label>
		<form:input path="searchUser" size="10" maxlength="10" title="간부명" onkeypress="javascript:press(event); return false;" />
   		<span class="button"><a href="#" onclick="javascript:fn_aram_search_schdulSe(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	
	<div class="select_area" style="width:200px;" >
		<a href="javascript:fn_aram_move_year('<%=year-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전년도" />
		</a>
		<b><%=year%>년</b>
		<a href="javascript:fn_aram_move_year('<%=year+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음년도"/>
		</a>

		<%if(month> 0 ){ %>
		<a href="javascript:fn_aram_move_month('<%=month-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전월">
		</a>
		<%}%>
		<b><%=month+1%>월</b>
		<%if(month < 11 ){ %>
		<a href="javascript:fn_aram_move_month('<%=month+1%>')">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음월">
		</a>
		<%}%>
	</div>
</div>	

</form:form>

<!-- //날짜 네비게이션  -->
<br>
<table class="table-calendar" style="background-color:#FFFFFF;" summary="이 표는 월별 간부일정 목록을 제공하며, 해당일자의 일정명 정보를 제공합니다.">
<caption>월별간부일정목록</caption>
<thead>
	<tr bgcolor="#CECECE">
		<th scope="col" width='100px'>
			<div align="center"><font color="red">일</font></div>
		</th>
		<th scope="col" width='100px'>
			<div align="center">월</div>
		</th>
		<th scope="col" width='100px'>
			<div align="center">화</div>
		</th>
		<th scope="col" width='100px'>
			<div align="center">수</div>
		</th>
		<th scope="col" width='100px'>
			<div align="center">목</div>
		</th>
		<th scope="col" width='100px'>
			<div align="center">금</div>
		</th>
		<th scope="col" width='100px'>
			<div align="center"><font color="#529dbc">토</font></div>
		</th>
	</tr>
</thead>
<tbody>
	<tr style='border:#000000'>
	<%
	
	@SuppressWarnings("unchecked")
	List<EgovMap> listResult = (List<EgovMap>)request.getAttribute("resultList");
	EgovMap egovMap = new EgovMap();
	
	@SuppressWarnings("unchecked")
	List<EgovMap> restdeList = (List<EgovMap>)request.getAttribute("restdeList");
	EgovMap restde = null;
	//처음 빈공란 표시
	for(int index = 1; index < start ; index++ ) {
	  	out.println("<td>&nbsp;</td>");
	  	newLine++;
	}
	
	for(int index = 1; index <= endDay; index++) {
		String color = "";
		
		if(newLine == 0){			color = "red";
		}else if(newLine == 6){ 	color = "#529dbc";
		}else{						color = "black"; };
		
		String restdeNm = "";
		if(restdeList != null){
			
			for(int i=0;i < restdeList.size(); i++){
				restde = (EgovMap)restdeList.get(i);
	
				int iYear = Integer.parseInt(restde.get("year").toString());
				int iMonth = Integer.parseInt(restde.get("month").toString());
				int iDay = Integer.parseInt(restde.get("day").toString());
	
				if(iYear == year && iMonth == (month+1) && iDay == index ){
					color = "RED";
					restdeNm = restde.get("restdeNm").toString();
				}
			}
		}

		String sUseDate = Integer.toString(year);
		
		sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1); 
		sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
		
		int iUseDate = Integer.parseInt(sUseDate);
	
		out.println("<td valign='top' align='left' height='92px' bgcolor='#EFEFEF'>");
		
		out.println("<a href=\"JavaScript:fn_aram_regist_leaderSchdul('"+iUseDate+"');\"><font color='"+color+"'>"+index+"</font></a>");
		out.println("<font color='"+color+"'>"+restdeNm+"</a></font>");
		out.println("<BR>");
		/*
		out.println(iUseDate);
		out.println("<BR>");
		*/ 
		
		if(listResult != null){
	
			for(int i=0;i < listResult.size(); i++){
				egovMap = (EgovMap)listResult.get(i);
				
				int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
				int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
	
				//if(iBeginDate>= iUseDate && iUseDate <= iBeginEnd){
				if(iUseDate>= iBeginDate && iUseDate <= iBeginEnd){
					out.print("<table><tr><td><div class='divDotText' style='width:92px;border:solid 0px;'><a href=\"JavaScript:fn_aram_detail_leaderSchdul('" + (String)egovMap.get("schdulId") + "')\">");
					out.print((String)egovMap.get("schdulNm"));
					out.println("</a></div></td></tr></table>");
					/*
					out.println(iBeginDate);
					out.println("<BR>");
					out.println(iBeginEnd);
					*/
				}
			}
		}
	
		out.println("</td>");
		newLine++;
		
		if(newLine == 7) {
		  	out.println("</tr>");
		  	if(index <= endDay) {
		    	out.println("<tr style='border:#000000'>");
		  	}
		  	newLine=0;
		}
	}
	//마지막 공란 LOOP
	while(newLine> 0 && newLine < 7) {
	  	out.println("<td>&nbsp;</td>");
	  	newLine++;
	}
	%>
	</tr> 
</tbody>
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
	gOpener.action = "${pageContext.request.contextPath}/cop/smt/lsm/detailLeaderSchdul.do";
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
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulMonth.do";
	varForm.submit();
}

function fn_aram_search_schdulSe() {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulMonth.do";
	varForm.submit();
}

function fn_aram_move_year(value) {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.year.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulMonth.do";
	varForm.submit();
}

function fn_aram_move_month(value) {
	var varForm = document.getElementById("leaderSchdulVO");
	varForm.month.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulMonth.do";
	varForm.submit();
}

</script>

</body>
</html>
