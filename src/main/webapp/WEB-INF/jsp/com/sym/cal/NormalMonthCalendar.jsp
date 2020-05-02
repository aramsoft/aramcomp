<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : NormalMonthCalendar.jsp
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
<c:set var="year_b3" value="${resultList[0].year-3}"/>
<c:set var="year_b2" value="${resultList[0].year-2}"/>
<c:set var="year_b1" value="${resultList[0].year-1}"/>
<c:set var="year"    value="${resultList[0].year}"  />
<c:set var="year_a1" value="${resultList[0].year+1}"/>
<c:set var="year_a2" value="${resultList[0].year+2}"/>
<c:set var="year_a3" value="${resultList[0].year+3}"/>
<c:set var="month"   value="${resultList[0].month}" />

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>일반달력 월간</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>일반달력 월간</h2>
</div>

<form name="normalMonthCalendar" action ="" method="post">
<input type="hidden" name="init" value="${init}" />
<input type="hidden" name="day" />

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	  		<select name="year" onChange="javascript:fn_aram_change_Calendar(document.normalMonthCalendar);" title="연도선택">
	  			<c:if test="${year_b3> 0 && year_b3 < 10000}"><option value="${year_b3}"               >${year_b3}</option></c:if>
	  			<c:if test="${year_b2> 0 && year_b2 < 10000}"><option value="${year_b2}"               >${year_b2}</option></c:if>
	  			<c:if test="${year_b1> 0 && year_b1 < 10000}"><option value="${year_b1}"               >${year_b1}</option></c:if>
	  			<c:if test="${year> 0 && year    < 10000}"><option value="${year   }" selected="selected">${year   }</option></c:if>
	  			<c:if test="${year_a1> 0 && year_a1 < 10000}"><option value="${year_a1}"               >${year_a1}</option></c:if>
	  			<c:if test="${year_a2> 0 && year_a2 < 10000}"><option value="${year_a2}"               >${year_a2}</option></c:if>
	  			<c:if test="${year_a3> 0 && year_a3 < 10000}"><option value="${year_a3}"               >${year_a3}</option></c:if>
	  		</select> 년
	  		&nbsp;&nbsp;
	  		<select name="month" onChange="javascript:fn_aram_change_Calendar(document.normalMonthCalendar);" title="월선택">
	  			<option value=1  <c:if test="${month==1 }">selected="selected"</c:if>>01</option>
	  			<option value=2  <c:if test="${month==2 }">selected="selected"</c:if>>02</option>
	  			<option value=3  <c:if test="${month==3 }">selected="selected"</c:if>>03</option>
	  			<option value=4  <c:if test="${month==4 }">selected="selected"</c:if>>04</option>
	  			<option value=5  <c:if test="${month==5 }">selected="selected"</c:if>>05</option>
	  			<option value=6  <c:if test="${month==6 }">selected="selected"</c:if>>06</option>
	  			<option value=7  <c:if test="${month==7 }">selected="selected"</c:if>>07</option>
	  			<option value=8  <c:if test="${month==8 }">selected="selected"</c:if>>08</option>
	  			<option value=9  <c:if test="${month==9 }">selected="selected"</c:if>>09</option>
	  			<option value=10 <c:if test="${month==10}">selected="selected"</c:if>>10</option>
	  			<option value=11 <c:if test="${month==11}">selected="selected"</c:if>>11</option>
	  			<option value=12 <c:if test="${month==12}">selected="selected"</c:if>>12</option>
	  		</select> 월
		</span>
	</div>
</div>

</form>

<table class="table-list" >
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;"  width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<c:choose>
			<c:when test='${result.day == ""}'>
		 		<c:choose>
			 		<c:when test='${result.weeks != 6}'>
						<td class="lt_text3" height="50px"> </td>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
			 		<c:when test='${result.restAt == "Y" }'>
   						<td class="lt_text3" style="color:red;">${result.day}
   						<c:forEach var="restde" items="${RestdeList}" varStatus="status">
   							<c:if test="${result.year eq restde.year && result.month eq restde.month && result.day eq restde.day}">&nbsp;${restde.restdeNm}</c:if>
   						</c:forEach>
   						</td>
					</c:when>
					<c:otherwise>
	    				<td class="lt_text3" >${result.day}</td>
					</c:otherwise>
				</c:choose>
				<c:if test='${result.week == 7}'>
				    <c:out value="</tr>" escapeXml="false"/>
				    <c:out value="<tr>" escapeXml="false"/>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</tr>
</tbody>
</table>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 연월변경
 ******************************************************** */
function fnChangeCalendar(year, month){
	var varForm			= document.all["Form"];
	varForm.action      = "${pageContext.request.contextPath}/sym/cal/NormalMonthCalendar.do";
	varForm.year.value  = year;
	varForm.month.value = month;
	varForm.submit();
}

/* ********************************************************
* 연월변경
******************************************************** */
function fn_aram_change_Calendar(form){
	form.submit();
}

</script>

</body>
</html>
