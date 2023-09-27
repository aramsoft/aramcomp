<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.egovframe.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.cop.smt.sim.domain.SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% 
/**
 * @Class Name : SchdulMonthList.jsp
 * @Description : 월별 일정 조회
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
	
	SchdulManageVO schdulManageVO = (SchdulManageVO) request.getAttribute("schdulManageVO");
	String strYear = schdulManageVO.getYear();
	String strMonth = schdulManageVO.getMonth();

	int year = cal.get(java.util.Calendar.YEAR);
	int month = cal.get(java.util.Calendar.MONTH);
	int date = cal.get(java.util.Calendar.DATE);
	
	if(strYear != null)
	{
	  	year = Integer.parseInt(strYear);
	  	month = Integer.parseInt(strMonth);
	}
	
	//년도/월 셋팅
	cal.set(year, month, 1);
	
	int startDay = cal.getMinimum(java.util.Calendar.DATE);
	int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
	int weekPos = 0;
%>
<html lang="ko">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>월별 일정 조회</TITLE>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
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
</HEAD>

<BODY>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%">

<form:form modelAttribute="schdulManageVO" action="" method="post">
<form:hidden path="year" />
<form:hidden path="month" />
<form:hidden path="week" />
<form:hidden path="day" />

<!--날짜 네비게이션  -->
<div id="date_navigation" >

	<div class="select_area" style="width:100px;" >
		<form:select path="searchSchdulSe" class="select" onChange="fn_aram_select_schdulSe(); return false;" title="일정구분선택">
	   		<form:option value="" label="--선택--" />
	        <form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
		</form:select>
	</div>
	
	<div class="select_area" style="width:200px;" >
		<a href="javascript:fn_aram_move_year('<%=year-1%>');">
			<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전으로가기아이콘표시">
		</a>
		<%=year%>년
		<a href="javascript:fn_aram_move_year('<%=year+1%>');">
			<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음으로가기아이콘표시">
		</a>
		
		<%if(month> 0 ){ %>
		<a href="javascript:fn_aram_move_month('<%=month-1%>');">
			<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전으로가기아이콘표시">
		</a>
		<%}%>
		<%=month+1%>월
		<%if(month < 11 ){ %>
		<a href="javascript:fn_aram_move_month('<%=month+1%>')">
			<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음으로가기아이콘표시">
		</a>
		<%}%>
	</div>	
</div>

</form:form>

<br>
<div align="right" style="font-size:9pt;">
<font color="black">■ 전체일정</font>, <font color="blue">■ 커뮤니티일정</font>, <font color="purple">■ 개인일정</font>
</div>

<table class="table-calendar" style="background-color:#FFFFFF;">
<THEAD>
	<TR bgcolor="#CECECE">
		<TD width='100px'>
			<DIV align="center"><font color="red">일</font></DIV>
		</TD>
		<TD width='100px'>
			<DIV align="center">월</DIV>
		</TD>
		<TD width='100px'>
			<DIV align="center">화</DIV>
		</TD>
		<TD width='100px'>
			<DIV align="center">수</DIV>
		</TD>
		<TD width='100px'>
			<DIV align="center">목</DIV>
		</TD>
		<TD width='100px'>
			<DIV align="center">금</DIV>
		</TD>
		<TD width='100px'>
			<DIV align="center"><font color="#529dbc">토</font></DIV>
		</TD>
	</TR>
</THEAD>
<TBODY>
	<TR>
	<%
	
	@SuppressWarnings("unchecked")
	List<EgovMap> listResult = (List<EgovMap>)request.getAttribute("resultList");
	EgovMap egovMap = null;
	
	@SuppressWarnings("unchecked")
	List<EgovMap> restdeList = (List<EgovMap>)request.getAttribute("restdeList");
	EgovMap restde = null;
	//처음 빈공란 표시
	for(int index = 1; index < start ; index++ ) {
	  	out.println("<TD>&nbsp;</TD>");
	  	weekPos++;
	}
	
	for(int index = 1; index <= endDay; index++) {
		String color = "";
	
		if(weekPos == 0){			color = "RED";
		}else if(weekPos == 6){ 	color = "#529dbc";
		}else{						color = "BLACK"; };
	
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
	
		out.println("<TD valign='top' align='left' height='92px' bgcolor='#EFEFEF'>");
	
		out.println("<a href=\"#\" onclick=\"javascript:fn_aram_regist_schdulManage('"+iUseDate+"');\"><font color='"+color+"'>"+index+"</font></a>");
		out.println("<font color='"+color+"'>"+restdeNm+"</font>");
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
					out.print("<table><tr><td><div class='divDotText' style='width:92px;border:solid 0px;'>");
					out.print("<a href=\"#\" onclick=\"javascript:fn_aram_detail_schdulManage('" + (String)egovMap.get("schdulId") + "');\">");
					String othbcScope = (String)egovMap.get("othbcScope");
					switch (othbcScope.charAt(0)) {
						case 'C' : 
							out.print("<font color=\"blue\">");
							out.print((String)egovMap.get("schdulNm"));
							out.print("</font>");
							break;
						case 'P' :
							out.print("<font color=\"purple\">");
							out.print((String)egovMap.get("schdulNm"));
							out.print("</font>");
							break;
						default : 
							out.print((String)egovMap.get("schdulNm"));
					}	
					out.println("</a></div></td></tr></table>");
					/*
					out.println(iBeginDate);
					out.println("<BR>");
					out.println(iBeginEnd);
					*/
				}
			}
		}
	
		out.println("</TD>");
		weekPos++;
	
		if(weekPos == 7) {
		 	out.println("</TR>");
		  	if(index <= endDay)	{
		    	out.println("<TR>");
		  	}
		  	weekPos=0;
		}
	}
	//마지막 공란 LOOP
	while(weekPos> 0 && weekPos < 7) {
	  	out.println("<TD>&nbsp;</TD>");
	  	weekPos++;
	}
	%>
	</TR>
	
</TBODY>
</TABLE>

</DIV>

<script type="text/javascript">

var gOpener = parent.document.all? parent.document.schdulManageVO : parent.document.getElementById("schdulManageVO") ;

/* ********************************************************
* 개인일정 등록화면
******************************************************** */
function fn_aram_regist_schdulManage(sDate){
	gOpener.schdulBgnde.value = sDate;
	gOpener.schdulEndde.value = sDate;
	gOpener.target = "";
	gOpener.action = "${pageContext.request.contextPath}/cop/smt/sim/registSchdul.do";
	gOpener.submit();
}

/* ********************************************************
* 개인일정 상세보기
******************************************************** */
function fn_aram_detail_schdulManage(schdulId){
	gOpener.schdulId.value = schdulId;
	gOpener.target = "";
	gOpener.action = "${pageContext.request.contextPath}/cop/smt/sim/detailSchdul.do";
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

function fn_aram_select_schdulSe() {
	var varForm = document.getElementById("schdulManageVO");
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulMonth.do";
	varForm.submit();
}

function fn_aram_move_year(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.year.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulMonth.do";
	varForm.submit();
}

function fn_aram_move_month(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.month.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulMonth.do";
	varForm.submit();
}


</script>

</BODY>
</HTML>
