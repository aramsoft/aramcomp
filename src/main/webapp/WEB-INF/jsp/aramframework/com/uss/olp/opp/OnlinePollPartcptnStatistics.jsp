<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollPartcptnStatistics.jsp
  * @Description : 온라인POLL 통계
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
<DIV id="main">

<div class="content_title">
	<h2>온라인POLL 통계</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="onlinePollPartcptnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pollId" />

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<!-- 검색조건 유지 -->
</form:form>

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		POLL명
    	</th>
    	<td width="80%">
			<c:out value="${onlinePollPartcptnVO.pollNm}" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		POLL시작일자
    	</th>
    	<td>
    		<c:out value="${fn:substring(onlinePollPartcptnVO.pollBeginDe, 0, 10)}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		POLL종료일자
    	</th>
    	<td>
    		<c:out value="${fn:substring(onlinePollPartcptnVO.pollEndDe, 0, 10)}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		POLL종류
    	</th>
    	<td>
			<c:forEach items="${COM039_pollKind}" var="comCode" varStatus="pollKindStatus">
			<c:if test="${comCode.code eq onlinePollPartcptnVO.pollKindCode}">
				<c:out value="${comCode.codeNm}" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
</table>

<!--  줄간격조정  -->
<div style="margin-top:10px; width:100%"></div>

<!-- 그래프출력  -->
<table class="table-register">
	<tr>
		<td style="padding:0;margin:0;background-color:#E3E3E3;">
   		<table border="0" >

 			<c:set var="chartCheck" value="0" />
 			<c:forEach items="${pollItemList}" var="result" varStatus="status2">
 				<tr>
 					<td style="background-color:#E3E3E3;" width="10px" height="28px"></td>
 					<td style="background-color:#E3E3E3;" width="100px"><c:out value="${result.pollIemNm}" escapeXml="false" /></td>
 					<td style="background-color:#E3E3E3;">
						<c:forEach items="${statisticsList}" var="statisticsInfo" varStatus="status3">
  						<c:if test="${result.pollIemId eq statisticsInfo.pollIemId}">
  						<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/chart/chart${status2.count}.JPG" width="${statisticsInfo.pollIemPercent}px" height="8" alt="차트"> (${statisticsInfo.pollIemIdCnt})건
  						<c:set var="chartCheck" value="${chartCheck+1}" />
  						</c:if>
  						</c:forEach>
						<c:if test="${chartCheck eq '0'}">
		   				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/chart/chart${status2.count}.JPG" width="1 px" height="8" alt="차트"> (0)건
		   				</c:if>
	 				</td>
 				</tr>
  				<c:set var="chartCheck" value="${0}" />
			</c:forEach>
		</table>
  		</td>
	</tr>
</table>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm.action = "${pageContext.request.contextPath}${returnUrl}'/>";
    varForm.submit();
}

</script>

