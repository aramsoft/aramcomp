<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.uss.ion.bnt.domain.BndtManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BndtManageList.jsp
 * @Description : 당직 목록
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
	
	String strYear  = request.getParameter("year");
	String strMonth = request.getParameter("month");
	
	int year  = cal.get(java.util.Calendar.YEAR);
	int month = cal.get(java.util.Calendar.MONTH);
	int date  = cal.get(java.util.Calendar.DATE);
	
	if(strYear != null)
	{
	   	year  = Integer.parseInt(strYear);
	   	month = Integer.parseInt(strMonth);
	}else{
	 	
	}
	//년도/월 셋팅
	cal.set(year, month, 1);
	
	int startDay = cal.getMinimum(java.util.Calendar.DATE);
	int endDay   = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	int start    = cal.get(java.util.Calendar.DAY_OF_WEEK);
	int newLine  = 0;

%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>당직 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<style TYPE="text/css">

#date_navigation {
	height:60px; background-color:#F3F9D7; border:1px solid #CED99C; margin-top:10px;
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

<DIV id="main" style="width:710px;margin-left:0">

<div class="content_title">
	<h2>당직관리 목록</h2> 
</div>

<form:form commandName="bndtManageVO" action="" method="post">  
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="cmd">
<input type="hidden" name="bndtDe">
<input type="hidden" name="bndtId">

<!-- 검색조건 유지 -->
<form:hidden path="year" />
<form:hidden path="month" />
<!-- 검색조건 유지 -->

<!-- 날짜 네비게이션  -->
<div id="date_navigation" >

	<div class="select_area" style="width:100px;" >
		<font size='3'><b>당직년월</b></font>
	</div>
			
	<div class="select_area" style="width:200px;" >
		<a href="#" onClick="javascript:fn_aram_list_bndtManage('<%=year-1%>', '<%=month%>'); return false;"><img src="/images/aramframework/com/cmm/icon/icoPrev.gif" alt="연도 이전" style="margin-right:4px;border:0px;"></a>
		<font size='3'><b><%=year%>년</b></font>
		<a href="#" onClick="javascript:fn_aram_list_bndtManage('<%=year+1%>', '<%=month%>'); return false;"><img src="/images/aramframework/com/cmm/icon/icoNxt.gif"  alt="연도 이후" style="margin-left:4px;border:0px;"></a>

		<%if(month> 0 ){ %>
		<a href="#" onClick="javascript:fn_aram_list_bndtManage('<%=year%>', '<%=month-1%>'); return false;"><img src="/images/aramframework/com/cmm/icon/icoPrev.gif" alt="월 이전" style="margin-right:4px;border:0px;"></a>
		<%}%>
		<font size='3'><b><%=month+1%>월</b></font>
		<%if(month < 11 ){ %>
		<a href="#" onClick="javascript:fn_aram_list_bndtManage('<%=year%>', '<%=month+1%>'); return false;"><img src="/images/aramframework/com/cmm/icon/icoNxt.gif"  alt="월 이후" style="margin-left:4px;border:0px;"></a>
		<%}%>
	</div>
	
	<div class="select_area" style="width:100px;" >
		<span class="button"><a href="/uss/ion/bnt/BndtManageListPop.do" target="_blank" title="새 창으로 이동" onclick="fncBndtManageBnde(); return false;">당직엑셀등록</a></span>
	</div>
</div>	
</form:form>
<br>

<table class="table-calendar" style="background-color:#FFFFFF;" summary="당직 목록">
<caption>당직 목록</caption>
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
	List<BndtManageVO> listResult = (List<BndtManageVO>)request.getAttribute("bndtManageList");
	//List listResult = (List)request.getAttribute("bndtManageList");
	//EgovMap egovMap = new EgovMap();
	int iBndtDe    = 0;
	int iBndtCnt   = 0;
	int iDiaryCnt  = 0;
	//처음 빈공란 표시
	for(int index = 1; index < start ; index++ )
	{
	  out.println("<TD>&nbsp;</TD>");
	  newLine++;
	}
	
	for(int index = 1; index <= endDay; index++)
	{
		iBndtCnt   = 0;
		String color = "";
		
		if(newLine == 0){			color = "RED";
		}else if(newLine == 6){ 	color = "#529dbc";
		}else{						color = "BLACK"; };
		
		String sUseDate = Integer.toString(year);
		
		sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1); 
		sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
		int iUseDate = Integer.parseInt(sUseDate);
	
		out.println("<TD valign='top' align='left' height='92px' bgcolor='#EFEFEF'>");
		out.println("<font color='"+color+"'><b>"+index+"</b></font>");
		out.println("<BR>");
		/*
		out.println(iUseDate);
		out.println("<BR>");
		*/
		if(listResult != null ){
			for(int i=0;i < listResult.size(); i++){
				BndtManageVO bndtManageVO = listResult.get(i);
				iBndtDe = Integer.parseInt(bndtManageVO.getBndtDe().substring(0, 8));
				if(iUseDate ==  iBndtDe){
					out.print("<table><tr><td><div class='divDotText' style='width:92px;border:solid 0px;'>당직자:<a href='#' onclick=\"fn_aram_detail_bndtManage('" + (String)bndtManageVO.getBndtDe() + "','"+ (String)bndtManageVO.getBndtId() +"')\">");
					out.print(bndtManageVO.getBndtUserNm());
					out.println("</a></div></td></tr><tr><td>&nbsp;</td></tr>");
	/*
					out.print("<table><tr><td><div class='divDotText' style='width:92px;border:solid 0px;'>당직자:");
					out.print("<form id='selectForm' name='selectForm' action='/uss/bnt/ans/BndtManageRegist.do' method='post'>");  
					out.print("<input type='hidden' name='bndtDe'       value='" + (String)egovMap.getBndtDe() + "'>");
					out.print("<input type='hidden' name='bndtId'       value='"+ (String)egovMap.getBndtId() +"'>");
					out.print("<span class='button'><input type='submit' value='"+(String)egovMap.getBndtTemp1()+"' onclick='fncSelectAnnvrsryManage(); return false;'></span></form>");
					out.println("</div></td></tr><tr><td>&nbsp;</td></tr>");*/
					iDiaryCnt = bndtManageVO.getBndtDiaryCnt();
	
					/*설명 : 당직 신규등록 화면 호출 
					function fncInsertBndtManage(bndtDe) {
					    document.listForm.bndtDe.value = bndtDe;
					    document.listForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/BndtManageRegist.do";
					    document.listForm.submit(); 
					}
					*/
	
					/*설명 : 당직일지 등록 /상세 화면 호출 
					function fncSelectBndtDiary(bndtDe,bndtId, cmd) {
					    document.listForm.cmd.value = cmd;
						document.listForm.bndtId.value = bndtId;
					    document.listForm.bndtDe.value = bndtDe;
					    document.listForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/selectBndtDiary.do";
					    document.listForm.submit(); 
					}
					
					*/
					
					if(iDiaryCnt> 0 ){
						out.print("<tr><td><div class='divDotText' style='width:92px;border:solid 0px;'><a href='#' onclick=\"fn_aram_detail_bndtDiary('" + bndtManageVO.getBndtDe() + "','"+ bndtManageVO.getBndtId() +"')\">");
						out.print("작성완료");
						out.println("</a></div></td></tr>");
					}else{
						out.print("<tr><td><div class='divDotText' style='width:92px;border:solid 0px;'>일지:");
						out.print("<span class='button'><a href='#' onclick=\"fn_aram_regist_bndtDiary('" + sUseDate + "','"+ bndtManageVO.getBndtId() +"')\">등록</a></span>");
						out.println("</div></td></tr>");
					}
					out.println("</table>");
					iBndtCnt++;
				}
			}
		}
		if(	iBndtCnt == 0){
			out.print("<table><tr><td>당직자:");
			out.print("<span class='button'><a href='#' onclick=\"fn_aram_regist_bndtManage('" +sUseDate+ "')\">등록</a></span>");
			out.println("</td></tr><tr><td>&nbsp;</td></tr>");
			out.print("<tr><td><div class='divDotText' style='width:92px;border:solid 0px;'>");
			out.print("일지: 미등록");
			out.println("</div></td></tr></table>");
		}
	
		out.println("</TD>");
		newLine++;
		
		if(newLine == 7)
		{
		  out.println("</TR>");
		  if(index <= endDay)
		  {
		    out.println("<TR>");
		  }
		  newLine=0;
		}
	}
	//마지막 공란 LOOP
	while(newLine> 0 && newLine < 7)
	{
	  out.println("<TD>&nbsp;</TD>");
	  newLine++;
	}
%>
	</tr> 

</tbody>
</table>

</div>

<script type="text/javascript">

/*설명 : 당직  목록 조회 */
function fn_aram_list_bndtManage(year, month){
	var varForm = document.getElementById("bndtManageVO");
	varForm.year.value = year;
	varForm.month.value = month;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtManage.do";
	varForm.submit();
}

/*설명 : 당직 상세조회 */
function fn_aram_detail_bndtManage(bndtDe,bndtId) {
	var varForm = document.getElementById("bndtManageVO");
	varForm.bndtId.value = bndtId;
	varForm.bndtDe.value = bndtDe;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/detailBndtManage.do";
	varForm.submit();   
}

/*설명 : 당직 신규등록 화면 호출 */
function fn_aram_regist_bndtManage(bndtDe) {
	var varForm = document.getElementById("bndtManageVO");
	varForm.bndtDe.value = bndtDe;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/registBndtManage.do";
	varForm.submit(); 
}

/*설명 : 당직일지 등록 /상세 화면 호출 */
function fn_aram_detail_bndtDiary(bndtDe, bndtId) {
	var varForm = document.getElementById("bndtManageVO");
	varForm.bndtId.value = bndtId;
	varForm.bndtDe.value = bndtDe;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/detailBndtDiary.do";
	varForm.submit(); 
}

function fn_aram_regist_bndtDiary(bndtDe, bndtId) {
	var varForm = document.getElementById("bndtManageVO");
	varForm.bndtId.value = bndtId;
	varForm.bndtDe.value = bndtDe;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/registBndtDiary.do";
	varForm.submit(); 
}

/*설명 : 당직 엑셀등록 PopUp 화면 호출	 */
function fncBndtManageBnde() {
	var url = "/uss/ion/bnt/BndtManageListPop.do";
	window.open(url, "p_bndt", "width=850px,height=420px,top=100px,left=100px,location=no");
}

</script>

</BODY>
</HTML>
