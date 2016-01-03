<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : QustnrRespondInfoStatistics.jsp
  * @Description : 설문 통계
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
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>설문 통계</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrRespondInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrId" />

<!-- 서브타이틀  -->
<table class="table-register" summary="이 표는 설문제목 정보를 제공하며, 성별, 직업, 생년월일, 응답자명, 설문정보 , 설문제목, 설문작성 안내내용, 설문대상, 설문기간 정보로 구성되어 있습니다 .">
	<tr>
	    <th width="20%">
    		<span class="norequired_icon"></span>
	    	설문제목 :
	    </th>
	    <td width="80%">
			<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr>
	    <th>
    		<span class="norequired_icon"></span>
	    	설문목적 :
	    </th>
	    <td>
			<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrPurps , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr>
	    <th>
    		<span class="norequired_icon"></span>
	    	설문작성 안내내용 :
	    </th>
	    <td>
			<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr>
	    <td colspan="2">
	    <table>
	    	<tr>
	    		<td width="50%" align="center">
	    			<b>설문대상  :</b>
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '1'}">학생</c:if>
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '2'}">대학생</c:if>
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '3'}">군인</c:if>
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '4'}">교사</c:if>
					<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '5'}">기타</c:if>
	    		</td>
	    		<td width="50%" align="center">
	    			<b>설문기간 :</b>
	    			${Comtnqestnrinfo[0].qestnrBeginDe}~${Comtnqestnrinfo[0].qestnrEndDe}
	    		</td>
	    	</tr>
	    </table>
	    </td>
	</tr>
	<%--
		객관식 : qestnrStatistic1
		주관식 : qestnrStatistic2
	--%>
	<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
	<tr>
	    <td colspan="2">
	    <table class="table-register">
	    	<tr>
	    		<td style="background-color:#E3E3E3;">
	    			<span class="required_icon"></span>
	    			${status1.count}. <c:out value="${fn:replace(QestmInfo.qestnCn , crlf , '<br/>')}" escapeXml="false"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td style="background-color:#E3E3E3;">
	    		<table>

	    			<%-- 객관식 일때  --%>
	    			<c:if test="${QestmInfo.qestnTyCode ==  '1'}">
		    			<%-- 설문항목 LOOP --%>
		    			<c:set var="chartCount" value="1" />
		    			<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status2">
		    			<c:set var="chartCheck" value="0" />
		    			<%-- 해당 설문문항 AND 설문문항  체크 --%>
		    			<c:if test="${QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">
		    			<tr>
		    				<td style="background-color:#E3E3E3;" width="10px" height="28px"></td>
		    				<td style="background-color:#E3E3E3;" width="100px"><c:out value="${fn:replace(QestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" /></td>
		    				<td style="background-color:#E3E3E3;">
		    					<%-- 설문통계(객관식) LOOP --%>
		    					<c:forEach items="${qestnrStatistic1}" var="QestmStatistic1" varStatus="status3">
		    					<c:if test="${QestmInfo.qestnrId eq QestmStatistic1.qestnrId && QestmStatistic1.qestnrQesitmId eq QestmItem.qestnrQesitmId && QestmStatistic1.qustnrIemId eq QestmItem.qustnrIemId}">
		    						<img src="/images/aramframework/com/cmm/chart/chart${chartCount}.JPG" width="${QestmStatistic1.qustnrPercent}px" height="8" alt="차트이미지"> ${QestmStatistic1.qustnrPercent}%
		    						<c:set var="chartCheck" value="${chartCheck+1}" />
		    					</c:if>
		    					</c:forEach>
		    					<c:if test="${chartCheck eq 0}">
		    						<img src="/images/aramframework/com/cmm/chart/chart${chartCount}.JPG" width="0 px" height="8" alt="차트이미지"> 0%
		    					</c:if>
		    				</td>
		    			</tr>
		    			<c:set var="chartCount" value="${chartCount+1}" />
		    			</c:if>
		    			</c:forEach>
	    			</c:if>
	
	    			<%-- 주관식 --%>
	    			<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
	    				<%-- 설문통계(객관식) LOOP --%>
	    				<c:forEach items="${qestnrStatistic2}" var="QestmStatistic2" varStatus="status4">
	    				<c:if test="${QestmInfo.qestnrId eq QestmStatistic2.qestnrId && QestmInfo.qestnrQesitmId eq QestmStatistic2.qestnrQesitmId}">
		    			<tr>
		    				<td style="background-color:#E3E3E3;" width="10px" align="right" valign="top"></td>
		    				<td style="background-color:#E3E3E3;">
		    					<br>
		    					<c:out value="${fn:replace(QestmStatistic2.respondNm , crlf , '<br/>')}" escapeXml="false" /> : <c:out value="${fn:replace(QestmStatistic2.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
		    					<br>
		    				</td>
		    			</tr>
	    				</c:if>
	    				</c:forEach>
	    			</c:if>
	    		</table>
				</td>
	    	</tr>
	    </table>
	    </td>
	</tr>
	<tr>
		<td colspan="2" height="1">
			<input type="hidden" name="TY_${QestmInfo.qestnrQesitmId}" value="${QestmInfo.qestnTyCode}">
		</td>
	</tr>
	</c:forEach>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.action = "${pageContext.request.contextPath}${returnUrl}'/>";
    varForm.submit();
}

</script>

