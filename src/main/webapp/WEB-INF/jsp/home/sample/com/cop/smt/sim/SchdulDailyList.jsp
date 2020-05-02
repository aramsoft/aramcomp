<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="aramframework.com.cop.smt.sim.domain.SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% 
/**
 * @Class Name : SchdulDailyList.jsp
 * @Description : 일정관리 일별 조회
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
<%!public String DateTypeIntForString(int iInput){
        String sOutput = "";
        if(Integer.toString(iInput).length() == 1){
            sOutput = "0" + Integer.toString(iInput);
        }else{
            sOutput = Integer.toString(iInput);
        }
        
       return sOutput;
    }%>
<%
	
	SchdulManageVO schdulManageVO = (SchdulManageVO) request.getAttribute("schdulManageVO");
	String strYear = schdulManageVO.getYear();
	String strMonth = schdulManageVO.getMonth();
	String strDay = schdulManageVO.getDay();
	
	java.util.Calendar cal = java.util.Calendar.getInstance();
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

        <div id="content">
            <div id="cur_loc">
                <ul>
                    <li>HOME</li>
                    <li>&gt;</li>
                    <li>알림마당</li>
                    <li>&gt;</li>
                    <li><strong>오늘의 행사</strong></li>
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
                <div id="search_field_loc"><h2><strong>일정관리 일별 목록조회</strong></h2></div>
                <fieldset>
                	<legend>조건정보 영역</legend>
	                <div class="sf_start">
			   			<form:select path="searchSchdulSe" onChange="fn_aram_select_schdulSe();" title="일정구분선택">
					   		<option selected value=''>-- 선택 --</option>
			                <form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
						</form:select>
						&nbsp;&nbsp;&nbsp;&nbsp;					
						<a href="javascript:fn_aram_move_year('<%=iNowYear-1%>');">
			            	<img alt="이전년도로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_prev.gif" align="middle" style="margin-right:4px;border:0px;">
			            </a>
			            <%=iNowYear%>년
						<a href="javascript:fn_aram_move_year('<%=iNowYear+1%>');">
			            	<img alt="다음년도로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_next.gif" align="middle" style="margin-left:4px;border:0px;">
			            </a>&nbsp;&nbsp;&nbsp;
			            <%if(iNowMonth> 0 ){ %>
						<a href="javascript:fn_aram_move_Momnth('<%=iNowMonth-1%>');">
			            	<img alt="이전월로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_prev.gif" align="middle" style="margin-right:4px;border:0px;">
			            </a>
			            <%}%>
			            <%=iNowMonth+1%>월
			            <%if(iNowMonth < 11 ){ %>
						<a href="javascript:fn_aram_move_month('<%=iNowMonth+1%>');">
			            	<img alt="다음월로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_next.gif" align="middle" style="margin-left:4px;border:0px;">
			            </a> 
			            <%}%>&nbsp;&nbsp;&nbsp;
			            <%if(iNowDay> 0 ){ %>
						<a href="javascript:fn_aram_move_day('<%=iNowDay-1%>');">
			            	<img alt="이전날짜로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_prev.gif" align="middle" style="margin-right:4px;border:0px;">
			            </a>
			            <%}%>
			            <%=iNowDay%>일
			            <%if(iNowDay < iEndDay-1 ){ %>
						<a href="javascript:fn_aram_move_day('<%=iNowDay+1%>');">
			            	<img alt="다음날짜로 이동" src="${pageContext.request.contextPath}/images/home/sample/btn/btn_next.gif" align="middle" style="margin-left:4px;border:0px;">
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
            <div class="default_tablestyle">
                <table >
                    <caption></caption>
                    <colgroup>
                    <col width="20%">
                    <col width="65%">  
                    <col width="15%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col" class="f_field">시간</th>
                        <th scope="col">제목</th>
                        <th scope="col">담당자</th>
                    </tr>
                    </thead>
                    <tbody>                 
 
                    <!-- loop 시작 -->                                
					<%
				
					@SuppressWarnings("unchecked")
					List<EgovMap> listResult = (List<EgovMap>)request.getAttribute("resultList");
					EgovMap egovMap = null;
					if(listResult != null){
					    for(int i=0;i < listResult.size(); i++){
					    egovMap = listResult.get(i);
					%>
  					  	<tr>  
					    <td>
						    <%
						    out.print("<a href=\"JavaScript:fn_aram_detail_indvdlSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
						    //out.print("<a href=\"/cop/smt/sim/detailSchdul.do?schdulId=" + (String)egovMap.get("schdulId") + "\" target=\"_top\">"); 
						
						    out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시");
						    out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분~");
						    out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시");
						    out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");
						    out.println("</a>");
						    %>
					    </td>
					    <td  align="left">
						    <%
						    out.print("<a href=\"JavaScript:fn_aram_detail_indvdlSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
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
						    out.println("</a>");
						    %> 
					    <td></td>  
				  	</tr>
				 <%
				    }
				}
				%>
					</tbody>
		    	</table>
            </DIV>
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
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.do";
	varForm.submit();
}

function fn_aram_move_year(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.year.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.do";
	varForm.submit();
}

function fn_aram_move_month(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.month.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.do";
	varForm.submit();
}

function fn_aram_move_day(value) {
	var varForm = document.getElementById("schdulManageVO");
	varForm.day.value = value;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.do";
	varForm.submit();
}

</script>

