<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.cop.smt.sim.service.SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% 
/**
 * @Class Name : SchdulWeekList.jsp
 * @Description : 주별 일정 조회
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
	Calendar calNow = Calendar.getInstance();
	Calendar calBefore = Calendar.getInstance();
	Calendar calNext = Calendar.getInstance();
	
	SchdulManageVO schdulManageVO = (SchdulManageVO) request.getAttribute("schdulManageVO");
	String strYear = schdulManageVO.getYear();
	String strMonth = schdulManageVO.getMonth();
	String strWeek = schdulManageVO.getWeek();

	int iNowYear = calNow.get(java.util.Calendar.YEAR);
	int iNowMonth = calNow.get(java.util.Calendar.MONTH);
	int iNowWeek = calNow.get(java.util.Calendar.WEEK_OF_MONTH)-1;
	
	if(!strYear.isEmpty())
	{
		iNowYear = Integer.parseInt(strYear);
		iNowMonth = Integer.parseInt(strMonth);
		if( !strWeek.isEmpty() ) iNowWeek = Integer.parseInt(strWeek);
	}
	
	@SuppressWarnings("unchecked")
	ArrayList<ArrayList<String>> listWeekGrop = (ArrayList<ArrayList<String>>)request.getAttribute("listWeekGrop");
/*
	for(int i=0; i < listWeekGrop.size(); i++){
		out.println(listWeekGrop.get(i));
		out.println("<br>");
	}
*/	
	ArrayList<String> listWeek = (ArrayList<String>)listWeekGrop.get(iNowWeek);
	
	//요일설정
	String arrDateTitle[] = new String[7];
	
	arrDateTitle[0] = "일요일";
	arrDateTitle[1] = "월요일";
	arrDateTitle[2] = "화요일";
	arrDateTitle[3] = "수요일";
	arrDateTitle[4] = "목요일";
	arrDateTitle[5] = "금요일";
	arrDateTitle[6] = "토요일";
%>
<html lang="ko">
<HEAD>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<TITLE>주별 일정 조회</TITLE>

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
</HEAD>

<BODY>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%;">

<form:form commandName="schdulManageVO" action="" method="post">
<form:hidden path="year" />
<form:hidden path="month" />
<form:hidden path="week" />
<form:hidden path="day" />

<!-- 날짜 네비게이션  -->
<div id="date_navigation" >

	<div class="select_area" style="width:100px;" >
  		<form:select path="searchSchdulSe" class="select" onChange="fn_aram_select_schdulSe(); return false;" title="일정구분선택">
	   		<form:option value="" label="--선택--" />
            <form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
		</form:select>
	</div>
	
	<div class="select_area" style="width:250px;" >
		<a href="javascript:fn_aram_move_year('<%=iNowYear-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전으로가기아이콘표시">
		</a>
		<%=iNowYear%>년
		<a href="javascript:fn_aram_move_year('<%=iNowYear+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음으로가기아이콘표시">
		</a>
		
		<%if(iNowMonth> 0 ){ %>
		<a href="javascript:fn_aram_move_month('<%=iNowMonth-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전으로가기아이콘표시">
		</a>
		<%}%>
		<%=iNowMonth+1%>월
		<%if(iNowMonth < 11 ){ %>
		<a href="javascript:fn_aram_move_month('<%=iNowMonth+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음으로가기아이콘표시">
		</a>
		<%}%>
		
		<%if(iNowWeek> 0 ){ %>
		<a href="javascript:fn_aram_move_week('<%=iNowWeek-1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoPrev.gif" style="margin-right:4px;border:0px;" alt="이전으로가기아이콘표시">
		</a>
		<%}%>
		<%=iNowWeek+1%>주
		<%if(iNowWeek < listWeekGrop.size()-1 ){ %>
		<a href="javascript:fn_aram_move_week('<%=iNowWeek+1%>');">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icoNxt.gif" style="margin-left:4px;border:0px;" alt="다음으로가기아이콘표시">
		</a>
		<%}%>
	</div>	
</div>
</form:form>

<br>
<div align="right" style="font-size:9pt;">
<font color="black">■ 전체일정</font>, <font color="blue">■ 커뮤니티일정</font>, <font color="purple">■ 개인일정</font>
</div>

<table class="table-calendar" style="background-color:#828486;">
 	<tr>
   		<td width="150px" bgcolor="#E0E0E0" align="center" height="30px">날짜</td>
		<td width="120px" bgcolor="#E0E0E0" align="center">시간</td>
		<td               bgcolor="#E0E0E0" align="center">제목</td>
		<td width="100px" bgcolor="#E0E0E0" align="center">담당자</td>
	</tr>
<%
	@SuppressWarnings("unchecked")
	List<EgovMap> listResult = (List<EgovMap>)request.getAttribute("resultList");
	EgovMap egovMap = new EgovMap();
	
	for(int i=0; i < listWeek.size(); i++){
	
		String sTmpDate = (String)listWeek.get(i);
		int iUseDate = Integer.parseInt(sTmpDate);

		String color = "";
		if(i == 0){			color = "RED";
		}else if(i == 6){ 	color = "#529dbc";
		}else{				color = "BLACK"; };
%>
  	<tr>
   		<td bgcolor="#F5F5F5" align="center" valign="middle" height="50px">
		    <font color='<%=color%>'><%=sTmpDate.substring(0,4)%>년<%=sTmpDate.substring(4,6)%>월<%=sTmpDate.substring(6,8)%>일  <br /><%=arrDateTitle[i] %></font>
    	</td>
    	<td bgcolor="#FFFFFF"  valign="middle" align="center" style="padding-left:2px;">
    <%
	    if(listResult != null){
	
			for(int j=0;j < listResult.size(); j++){
				egovMap = (EgovMap)listResult.get(j);
				int iBeginDate = Integer.parseInt( ((String)egovMap.get("schdulBgnde")).substring(0, 8) );
				int iBeginEnd = Integer.parseInt( ((String)egovMap.get("schdulEndde")).substring(0, 8) );
		
				if(iUseDate>= iBeginDate && iUseDate <= iBeginEnd){
					out.print("<table><tr><td><div class='divDotText' style='width:120px;border:solid 0px;'><a href=\"JavaScript:fn_aram_detail_schdulManage('" + (String)egovMap.get("schdulId") + "')\">");
					out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시");
					out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분~");
					out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시");
					out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");
					out.println("</a></div></td></tr></table>");
				}
			}
		}
    %>
    	</td>
    	<td bgcolor="#FFFFFF"  valign="middle" style="padding-left:2px;">
    <%
	    if(listResult != null){
	
			for(int j=0;j < listResult.size(); j++){
				egovMap = (EgovMap)listResult.get(j);
				int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
				int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
				if(iUseDate>= iBeginDate && iUseDate <= iBeginEnd){
					out.print("<table><tr><td><div class='divDotText' style='width:350px;border:solid 0px;'>");
					out.print("<a href=\"#\" onclick=\"javascript:fn_aram_detail_schdulManage('" + (String)egovMap.get("schdulId") + "')\">");
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
				}
			}
		}
    %>
    	</td>
    	<td bgcolor="#FFFFFF"  valign="middle" align="center" style="padding-left:2px;">
	<%
	    if(listResult != null){
	
			for(int j=0;j < listResult.size(); j++){
				egovMap = (EgovMap)listResult.get(j);
				int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
				int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
				if(iUseDate>= iBeginDate && iUseDate <= iBeginEnd){
					out.print("<table><tr><td><div>");
					out.print((String)egovMap.get("schdulChargerName"));
					out.println("</div></td></tr></table>");
				}
			}
		}
    %>
    	</td>
  	</tr>
  <%
	}
  %>
</table>

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
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.do";
	varForm.submit();
}

function fn_aram_move_year(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.year.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.do";
	varForm.submit();
}

function fn_aram_move_month(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.month.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.do";
	varForm.submit();
}

function fn_aram_move_week(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.week.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.do";
	varForm.submit();
}

</script>

</BODY>
</HTML>
