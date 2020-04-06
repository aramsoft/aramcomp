<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : AdministYearCalendar.jsp
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
<c:set var="year_b3" value="${resultList_1[0].year-3}"/>
<c:set var="year_b2" value="${resultList_1[0].year-2}"/>
<c:set var="year_b1" value="${resultList_1[0].year-1}"/>
<c:set var="year"    value="${resultList_1[0].year}"  />
<c:set var="year_a1" value="${resultList_1[0].year+1}"/>
<c:set var="year_a2" value="${resultList_1[0].year+2}"/>
<c:set var="year_a3" value="${resultList_1[0].year+3}"/>
<c:set var="month"   value="${resultList_1[0].month}" />

<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>행정달력 연간</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>행정달력 연간</h2>
</div>

<form name="administYearCalendar" action ="" method="post">
<input type="hidden" name="init" value="${init}" />
<input type="hidden" name="day" />

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	  		<select name="year" onChange="javascript:fn_aram_change_Calendar(document.administYearCalendar);" title="연도선택">
	  			<c:if test="${year_b3> 0 && year_b3 < 10000}"><option value="${year_b3}"               >${year_b3}</option></c:if>
	  			<c:if test="${year_b2> 0 && year_b2 < 10000}"><option value="${year_b2}"               >${year_b2}</option></c:if>
	  			<c:if test="${year_b1> 0 && year_b1 < 10000}"><option value="${year_b1}"               >${year_b1}</option></c:if>
	  			<c:if test="${year> 0 && year    < 10000}"><option value="${year   }" selected="selected">${year   }</option></c:if>
	  			<c:if test="${year_a1> 0 && year_a1 < 10000}"><option value="${year_a1}"               >${year_a1}</option></c:if>
	  			<c:if test="${year_a2> 0 && year_a2 < 10000}"><option value="${year_a2}"               >${year_a2}</option></c:if>
	  			<c:if test="${year_a3> 0 && year_a3 < 10000}"><option value="${year_a3}"               >${year_a3}</option></c:if>
	  		</select> 년
		</span>
	</div>
</div>

</form>

<div style="clear:both;">
	<h4>1 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_1}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_1}" varStatus="status">
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

<div style="clear:both;">
	<h4>2 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_2}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_2}" varStatus="status">
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

<div style="clear:both;">
	<h4>3 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_3}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_3}" varStatus="status">
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

<div style="clear:both;">
	<h4>4 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_4}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_4}" varStatus="status">
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

<div style="clear:both;">
	<h4>5 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_5}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_5}" varStatus="status">
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

<div style="clear:both;">
	<h4>6 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_6}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_6}" varStatus="status">
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

<div style="clear:both;">
	<h4>7 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_7}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_7}" varStatus="status">
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

<div style="clear:both;">
	<h4>8 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_8}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_8}" varStatus="status">
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

<div style="clear:both;">
	<h4>9 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_9}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_9}" varStatus="status">
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

<div style="clear:both;">
	<h4>10 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_10}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_10}" varStatus="status">
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

<div style="clear:both;">
	<h4>11 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_11}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_11}" varStatus="status">
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

<div style="clear:both;">
	<h4>12 월</h4>
</div>
<table class="table-list">
<thead>
<tr>
	<th style="color:red;" width="100">일</th>
	<th width="100">월</th>
	<th width="100">화</th>
	<th width="100">수</th>
	<th width="100">목</th>
	<th width="100">금</th>
	<th style="color:red;" width="100">토</th>
</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="result" items="${resultList_12}" varStatus="status">
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
   						<c:forEach var="restde" items="${RestdeList_12}" varStatus="status">
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
function fn_aram_change_Calendar(form){
	form.submit();
}

</script>

</body>
</html>
