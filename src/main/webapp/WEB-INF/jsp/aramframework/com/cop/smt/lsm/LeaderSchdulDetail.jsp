<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : LeaderSchdulDetail.jsp
 * @Description : 간부일정 상세조회
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
<%
String sLinkType = request.getParameter("linkType") == null ? "" : (String)request.getParameter("linkType");
%>
<c:set var="sLinkType" value="<%=sLinkType %>"/>

<DIV id="main">

<div class="content_title">
	<h2>간부일정 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>
	
<form:form commandName="leaderSchdulVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="schdulId" />

<table class="table-register"  summary="이 표는 간부일정정보를 상세조회하기 위한 표이며, 일정구분, 간부, 일정명, 일정내용, 일정장소, 반복구분, 날짜/시간, 담당자 정보로 구성되어 있습니다 .">
<caption>간부일정관리 상세조회</caption>
  	<tr> 
	    <th width="20%">
	    	일정구분
	    	<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	    	<c:forEach items="${COM057_schdulSeLeader}" var="schdulSeInfo" varStatus="status">
	    	<c:if test="${schdulSeInfo.code eq leaderSchdulVO.schdulSe}">	
	     		<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
	    	</c:if>
	    	</c:forEach>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	간부
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	    	<c:out value="${fn:replace(leaderSchdulVO.leaderName , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	일정명
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	      	<c:out value="${fn:replace(leaderSchdulVO.schdulNm , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	일정내용
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	        <br/>
	    	<c:out value="${fn:replace(leaderSchdulVO.schdulCn , crlf , '<br/>')}" escapeXml="false" />
	    	<br/><br/>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	일정장소
	    	<span class="norequired_icon"></span>
	    </th>
	    <td>
	      	<c:out value="${fn:replace(leaderSchdulVO.schdulPlace , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	반복구분
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	    	<c:forEach items="${COM058_reptitSeLeader}" var="schdulSeInfo" varStatus="status">
	    	<c:if test="${schdulSeInfo.code eq leaderSchdulVO.reptitSeCode}">	
	     		<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
	    	</c:if>
	    	</c:forEach>
	    </td>
	</tr>
	<tr> 
	    <th>
	    	날짜/시간
	    	<span class="required_icon"></span>
	    <td>
		    ${fn:substring(leaderSchdulVO.schdulBgnde, 0, 4)}-${fn:substring(leaderSchdulVO.schdulBgnde, 4, 6)}-${fn:substring(leaderSchdulVO.schdulBgnde, 6, 8)}  
		    ~ 
		    <c:choose>
		    <c:when test="${leaderSchdulVO.reptitSeCode eq '1'}">	
		    	
		    </c:when>
		    <c:otherwise>
			${fn:substring(leaderSchdulVO.schdulEndde, 0, 4)}-${fn:substring(leaderSchdulVO.schdulEndde, 4, 6)}-${fn:substring(leaderSchdulVO.schdulEndde, 6, 8)}  
			</c:otherwise>   
		    </c:choose>
		    ${fn:substring(leaderSchdulVO.schdulBgnde, 8, 10)}시  ${fn:substring(leaderSchdulVO.schdulBgnde, 10, 12)}분
		    ~
		    ${fn:substring(leaderSchdulVO.schdulEndde, 8, 10)}시  ${fn:substring(leaderSchdulVO.schdulEndde, 10, 12)}분 
	    </td>
	</tr>
	<tr> 
	    <th>
	    	담당자
	    	<span class="required_icon"></span>
	    </th>
	    <td>
	    	<c:out value="${fn:replace(leaderSchdulVO.schdulChargerName , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchSchdulSe" value="" />
<form:hidden path="searchUser" value="" />
<form:hidden path="year" value="" />
<form:hidden path="month" value="" />
<form:hidden path="week" value="" />
<form:hidden path="day" value="" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("leaderSchdulVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("leaderSchdulVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/editLeaderSchdul.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("leaderSchdulVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/deleteLeaderSchdul.do";
		varForm.submit();
	}
}

</script>
