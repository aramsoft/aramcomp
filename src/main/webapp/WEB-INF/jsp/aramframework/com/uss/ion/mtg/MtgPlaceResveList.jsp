<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceResveList.jsp
 * @Description : 회의실예약 목록
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
<style>
.table-time    { 
	width:100%; 
	BORDER-TOP:#1A90D8 2px solid; BORDER-bottom:#BABABA 1px solid; border-collapse:collapse; table-layout:fixed; 
}
.table-time th { 
	BORDER-bottom:#A3A3A3 1px solid; background-color:#E4EAF8; 
	padding-top:6px; padding-bottom:6px; padding-left:2px; padding-right:2px; 
	vertical-align:middle; height:20px; text-align:center; 
	font-family:"돋움"; font-size:10pt; color:#2E4B90; font-weight:bold;  
	white-space: nowrap;
}
.table-time td { 
	BORDER-bottom:#E0E0E0 1px solid; background-color:#FCFCFC; 
	padding-top:2px; padding-bottom:2px; padding-left:0px; padding-right:0px; 
	vertical-align:middle; height:20px; 
	font-family:"돋움"; font-size:10pt; 
	white-space: nowrap;
}

</style>

<DIV id="main">

<div class="content_title">
	<h2>회의실예약 목록</h2>
</div>

<div id="search_area">
	<div class="button_area">
     	<span class="button"><input type="submit" value="<spring:message code="button.inquire" />" onclick="fn_aram_list(); return false;"></span>
	</div>
</div>

<form:form commandName="mtgPlaceResveVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="mtgPlaceId">

<input type="hidden" name="resveId">
<input type="hidden" name="resveBeginTm">
<input type="hidden" name="resveEndTm">

<table class="table-register" summary="회의실예약 검색조건">
<caption>회의실예약 검색조건</caption>
  	<tr> 
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		회의일자
    	</th>
    	<td width="80%">
      		<form:hidden path="resveDe" />
	    	<c:if test="${!empty mtgPlaceResveVO.resveDe}">
 				<c:set var="resveDeVal" value="${fn:substring(mtgPlaceResveVO.resveDe, 0,4)}-${fn:substring(mtgPlaceResveVO.resveDe, 4,6)}-${fn:substring(mtgPlaceResveVO.resveDe, 6,8)}"/>
      		</c:if>
      		<input name="resveDeView" id="resveDeView" type="text" size="10" title="예약일자" value="${resveDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].resveDe, document.forms[0].resveDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			※회의일자 변경시 조회 버튼 클릭하셔야 예약 리스트가 조회됩니다.
    	</td>
  	</tr> 
</table>
<br>
<form:hidden path="searchCondition" value="1"/>
<form:hidden path="pageIndex" />
</form:form>
  
<table>
	<tr> 
		<td>
			※회의실 예약은  회의실의 색이 없는 빈 시간을 클릭하시면 예약신청화면으로 이동합니다. (그래프 클릭시 상세화면 이동.)<p>
		</td>
	</tr>	
</table>

<table class="table-time" border="1" style="table-layout:fixed" summary="회의실예약 목록">
<caption>회의실예약 목록</caption>
<thead>
  	<tr>
	    <th scope="col"                         >회의실명</th>
	    <th scope="col" width="40px" colspan="2">08</th>
	    <th scope="col" width="40px" colspan="2">09</th>
	    <th scope="col" width="40px" colspan="2">10</th>
	    <th scope="col" width="40px" colspan="2">11</th>
	    <th scope="col" width="40px" colspan="2">12</th>
	    <th scope="col" width="40px" colspan="2">13</th>
	    <th scope="col" width="40px" colspan="2">14</th>
	    <th scope="col" width="40px" colspan="2">15</th>
	    <th scope="col" width="40px" colspan="2">16</th>
	    <th scope="col" width="40px" colspan="2">17</th>
	    <th scope="col" width="40px" colspan="2">18</th>
	    <th scope="col" width="40px" colspan="2">19</th>
	    <th scope="col" width="40px" colspan="2">20</th>
  	</tr>
</thead>    
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="27"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
    	<td class="lt_text3"><c:out value='${result.mtgPlaceNm}'/></td> 
    
	    <c:if test="${result.resve0800!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve0800,20,fn:length(result.resve0800))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve0800,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve0800=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','0800','0830'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	   	</td>
   		</c:if>
   		
 	    <c:if test="${result.resve0830!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve0830,20,fn:length(result.resve0830))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve0830,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve0830=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','0830','0900'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve0900!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve0900,20,fn:length(result.resve0900))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve0900,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve0900=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','0900','0930'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve0930!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve0930,20,fn:length(result.resve0930))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve0930,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve0930=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','0930','1000'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1000!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1000,20,fn:length(result.resve1000))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1000,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1000=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1000','1030'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1030!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1030,20,fn:length(result.resve1030))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1030,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1030=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1030','1100'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1100!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1100,20,fn:length(result.resve1100))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1100,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1100=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1100','1130'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1130!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1130,20,fn:length(result.resve1130))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1130,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1130=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1130','1200'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1200!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1200,20,fn:length(result.resve1200))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1200,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1200=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1200','1230'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1230!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1230,20,fn:length(result.resve1230))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1230,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1230=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1230','1300'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1300!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1300,20,fn:length(result.resve1300))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1300,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1300=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1300','1330'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1330!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1330,20,fn:length(result.resve1330))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1330,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1330=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1330','1400'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1400!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1400,20,fn:length(result.resve1400))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1400,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1400=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1400','1430'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>

	    <c:if test="${result.resve1430!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1430,20,fn:length(result.resve1430))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1430,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1430=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1430','1500'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1500!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1500,20,fn:length(result.resve1500))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1500,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1500=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1500','1530'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1530!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1530,20,fn:length(result.resve1530))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1530,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1530=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1530','1600'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1600!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1600,20,fn:length(result.resve1600))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1600,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1600=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1600','1630'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1630!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1630,20,fn:length(result.resve1630))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1630,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1630=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1630','1700'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1700!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1700,20,fn:length(result.resve1700))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1700,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1700=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1700','1730'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1730!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1730,20,fn:length(result.resve1730))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1730,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1730=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1730','1800'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1800!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1800,20,fn:length(result.resve1800))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1800,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1800=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1800','1830'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1830!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1830,20,fn:length(result.resve1830))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1830,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1830=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1830','1900'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1900!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1900,20,fn:length(result.resve1900))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1900,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1900=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1900','1930'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve1930!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve1930,20,fn:length(result.resve1930))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve1930,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve1930=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','1930','2000'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve2000!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve2000,20,fn:length(result.resve2000))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve2000,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve2000=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','2000','2030'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
	    
	    <c:if test="${result.resve2030!='0' }">
	    <td>
			<span class="link" style="background:pink;">
	            <abbr Title="<c:out value='${fn:substring(result.resve2030,20,fn:length(result.resve2030))}'/>">
				<a href="#" onclick="javascript:fn_aram_detail('${result.mtgPlaceId}','${fn:substring(result.resve2030,0,20) }'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
				</abbr>
			</span>
	    </td>
	    </c:if>
	    <c:if test="${result.resve2030=='0' }">
	    <td>
			<span class="link">
				<a href="#" onclick="javascript:fn_aram_regist('${result.mtgPlaceId}','2030','2100'); return false;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a>
			</span>
	    </td>
	    </c:if>
  	</tr>   
 	</c:forEach>
</tbody>  
</table>
 
</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">

/*설명 : 회의실  예약목록 조회 */
function fn_aram_list(){
    var varForm = document.getElementById("mtgPlaceResveVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlaceResve.do";
    varForm.submit();
}

/*설명 : 회의실 상세 화면 호출 */
function fn_aram_detail(mtgPlaceId, resveId) {
    var varForm = document.getElementById("mtgPlaceResveVO");
    varForm.mtgPlaceId.value = mtgPlaceId;
    varForm.resveId.value = resveId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/detailMtgPlaceResve.do";
    varForm.submit(); 
}

/*설명 : 회의실 예약 화면 호출 */
function fn_aram_regist(mtgPlaceId, resveBeginTm, resveEndTm) {
    var varForm = document.getElementById("mtgPlaceResveVO");
    varForm.mtgPlaceId.value = mtgPlaceId;
    varForm.resveBeginTm.value = resveBeginTm;
    varForm.resveEndTm.value = resveEndTm;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/registMtgPlaceResve.do";
    varForm.submit(); 
}

</script>

