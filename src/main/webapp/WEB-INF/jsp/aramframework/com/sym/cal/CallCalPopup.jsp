<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CallCalPopup.jsp
  * @Description : 
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
<title>달력</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/cal.css" type="text/css">

</head>

<body>

<DIV id="main" style="width:300px;margin:0px;">

<form name="Form" action ="${pageContext.request.contextPath}/sym/cal/callCal.do" method="post">
<input type="hidden" name="init" value="${init}" />
<input type="hidden" name="year" value="${resultList[0].year}" />
<input type="hidden" name="month" value="${resultList[0].month}" />
<input type="hidden" name="day" />

<table class="table-list" style="cellpadding:1px;">
<thead>
<tr>
	<th width="36"  colspan=1>
	    <a href="javascript:fnChangeCalendar(${resultList[0].year-1},${resultList[0].month});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="${pageContext.request.contextPath}/images/aramframework/com/sym/cal/icon_pre_year.gif" alt="이전년도"></a>
	</th>
	<th width="36"  colspan=1>
	    <a href="javascript:fnChangeCalendar(${resultList[0].year},${resultList[0].month-1});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="${pageContext.request.contextPath}/images/aramframework/com/sym/cal/icon_pre_month.gif" alt="이전달"></a>
	</th>
	<th width="108" colspan=3>${resultList[0].year}년${resultList[0].month}월</th>
	<th width="36"  colspan=1>
	    <a href="javascript:fnChangeCalendar(${resultList[0].year},${resultList[0].month+1});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="${pageContext.request.contextPath}/images/aramframework/com/sym/cal/icon_aft_month.gif" alt="다음달"></a>
	</th>
	<th width="36"  colspan=1>
	    <a href="javascript:fnChangeCalendar(${resultList[0].year+1},${resultList[0].month});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="${pageContext.request.contextPath}/images/aramframework/com/sym/cal/icon_aft_year.gif" alt="다음년도"></a>
	</th>
</tr>
<tr>
    <th width="36">일</th>
    <th width="36">월</th>
    <th width="36">화</th>
    <th width="36">수</th>
    <th width="36">목</th>
    <th width="36">금</th>
    <th width="36">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<c:choose>
			<c:when test='${result.day == ""}'>
		 		<c:choose>
			 		<c:when test='${result.weeks != 6}'>
						<td></td>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
		 		<c:choose>
			 		<c:when test='${result.restAt == "Y" }'>
					    <td class="lt_text3"  STYLE="color:red;cursor:pointer;cursor:hand" onClick="javascript:fnReturnDay(${result.day});">
					    	${result.day}
					    </td>
					</c:when>
					<c:otherwise>
					    <td class="lt_text3"  STYLE="color:black;cursor:pointer;cursor:hand" onClick="javascript:fnReturnDay(${result.day});">
					    	${result.day}
					    </td>
					</c:otherwise>
				</c:choose>
		 		<c:choose>
			 		<c:when test='${result.week == 7}'>
					    <c:out value="</tr>" escapeXml="false"/>
					    <c:out value="<tr>" escapeXml="false"/>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</tr>
</tbody>
</table>

</form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInit(){
	var sDate        	= window.opener.gCalendars["sDate"].value;
	var initOK			= window.opener.gCalendars["init"];

	window.opener.gCalendars["init"] = "OK";
	//var varForm		= document.all["Form"];
	var varForm			= document.getElementsByName("Form")[0];
	if(sDate.length == 8) {
		if( initOK != "OK"
			&& (varForm.year.value != sDate.substr(0,4) 
				|| varForm.month.value != sDate.substr(4,2)) ) {
			varForm.action      = "${pageContext.request.contextPath}/sym/cal/CallCalPopup.do";
			varForm.year.value  = sDate.substr(0,4);
			varForm.month.value = sDate.substr(4,2);
			varForm.submit();
		} 
	}
}

/* ********************************************************
 * 연월변경
 ******************************************************** */
function fnChangeCalendar(year, month){
	var varForm			= document.all["Form"];
	varForm.action      = "${pageContext.request.contextPath}/sym/cal/CallCalPopup.do";
	varForm.year.value  = year;
	varForm.month.value = month;
	varForm.submit();
}

/* ********************************************************
 * 결과연월일 반환
 ******************************************************** */
function fnReturnDay(day){
	var retVal   = new Object();
	var sYear    = "0000"+document.Form.year.value;
	var sMonth   = "00"+document.Form.month.value;
	var sDay     = "00"+day;
	retVal.year  = sYear.substr(sYear.length-4,4);
	retVal.month = sMonth.substr(sMonth.length-2,2);
	retVal.day   = sDay.substr(sDay.length-2,2);
	window.opener.gCalendars["sDate"].value = retVal.year + retVal.month + retVal.day;
	window.opener.gCalendars["vDate"].value = retVal.year + "-" + retVal.month + "-" + retVal.day;
	window.close();
}

</script>

</body>
</html>
