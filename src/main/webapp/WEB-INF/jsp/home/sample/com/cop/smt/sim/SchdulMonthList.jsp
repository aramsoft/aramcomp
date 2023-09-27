<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.egovframe.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.cop.smt.sim.domain.SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : SchdulMonthList.jsp
 * @Description : 일정관리 월별 조회
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
		<div id="content">
			<div id="cur_loc">
				<ul>
					<li>HOME</li>
					<li>&gt;</li>
					<li>사이트관리</li>
					<li>&gt;</li>
					<li><strong>일정관리</strong></li>
				</ul>
			</div>
			
			<form:form modelAttribute="schdulManageVO" action="" method="post">
				<form:hidden path="schdulId" />
				<form:hidden path="schdulBgnde" />
				<form:hidden path="schdulEndde" />

				<form:hidden path="year" />
				<form:hidden path="month" />
				<form:hidden path="week" />
				<form:hidden path="day" />

				<!-- 검색 필드 박스 시작 -->
				<div id="search_field">
					<div id="search_field_loc">
						<h2><strong>일정관리 월별 목록조회</strong></h2>
					</div>
					<fieldset>
					  	<legend>조건정보 영역</legend>	  
					  	<div class="sf_start">
				   			<form:select path="searchSchdulSe" onChange="fn_aram_select_schdulSe();" title="일정구분선택">
						   		<option selected value=''>-- 선택 --</option>
				                <form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
							</form:select>
							&nbsp;&nbsp;&nbsp;&nbsp;					
				            <%--<a href="#" onClick="location.href='/cop/smt/sim/listSchdulMonth.do?year=<%=year-1%>&amp;month=<%=month%>'; return false;"> << </a>--%>
				            <a href="javascript:fn_aram_move_year('<%=year-1%>');"> 
				            <img alt="이전년도로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_prev.gif" align="middle" style="margin-right:4px;border:0px;">
				            </a>
				            <%=year%>년
							<a href="javascript:fn_aram_move_year('<%=year+1%>');">
				            <img alt="다음년도로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_next.gif" align="middle" style="margin-left:4px;border:0px;">
				            </a>
				            <%if(month> 0 ){ %>
							<a href="javascript:fn_aram_move_month('<%=month-1%>');">
                                        <img alt="이전월로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_prev.gif" align="middle" style="margin-right:4px;border:0px;">
                                     </a>
				            <%}%>
				            <%=month+1%>월
				            <%if(month < 11 ){ %>
							<a href="javascript:fn_aram_move_month('<%=month+1%>')">
                                        <img alt="다음월로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_next.gif" align="middle" style="margin-left:4px;border:0px;">
                                  </a>
					        <%}%>
						</div>
					</fieldset>
				</div>
				<!-- //검색 필드 박스 끝 -->
				    
				<div id="page_info"></div>					
				<div align="right" style="width:700px;font-size:9pt;">
					<font color="black">■ 전체일정</font>, <font color="purple">■ 개인일정</font>
				</div>
				
				<!-- table add start -->
				<table class="table-calendar" style="background-color:#FFFFFF;">
				<THEAD>
					<TR bgcolor="#CECECE">
					    <TD width='100px' height='23'>
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
					EgovMap egovMap = new EgovMap();
					
					@SuppressWarnings("unchecked")
					List<EgovMap> restdeList = (List<EgovMap>)request.getAttribute("restdeList");
					EgovMap restde = null;
					//처음 빈공란 표시
					for(int index = 1; index < start ; index++ )
					{
					  out.println("<TD>&nbsp;</TD>");
					  weekPos++;
					}
					
					for(int index = 1; index <= endDay; index++)
					{
					    String color = "";
					    
					    if(weekPos == 0){           color = "RED";
					    }else if(weekPos == 6){     color = "#529dbc";
					    }else{                      color = "BLACK"; };
					    
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

					    out.println("<a href=\"JavaScript:fn_aram_regist_indvdlSchdulManage('"+iUseDate+"');\"><font color='"+color+"'>"+index+"</font></a>");
//					    out.println("<font color='"+color+"'><a href=\"EgovIndvdlSchdulManageRegist.do?schdulBgnde="+iUseDate+"&amp;schdulEndde="+iUseDate+"\" target=\"_top\">"+index+"</a></font>");
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
					                out.print("<table><tr><td><div class='divDotText' style='width:92px;border:solid 0px;'>");
					                out.println("<a href=\"JavaScript:fn_aram_detail_indvdlSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
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
					      	if(index <= endDay) {
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
			</form:form>
		</div>
		
<script type="text/javascript">

/* ********************************************************
* 개인일정 등록화면
******************************************************** */
function fn_aram_regist_indvdlSchdulManage(sDate){
	var varForm = document.getElementById("schdulManageVO");
	varForm.schdulBgnde.value = sDate;
	varForm.schdulEndde.value = sDate;
	varForm.target = "";
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/registSchdul.do";
	varForm.submit();
}

/* ********************************************************
* 개인일정 상세보기
******************************************************** */
function fn_aram_detail_indvdlSchdulManage(schdulId){
	var varForm = document.getElementById("schdulManageVO");
	varForm.schdulId.value = schdulId;
	varForm.target = "";
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/detailSchdul.do";
	varForm.submit();
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

