<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : NtwrkSvcMntrngLogDetail.jsp
 * @Description : 네트워크서비스모니터링 로그 상세조회
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
	<h2>네트워크서비스모니터링 로그 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="ntwrkSvcMntrngLogVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sysIp" />
<form:hidden path="sysPort" />
<form:hidden path="logId" />

<table class="table-detail" summary="이 표는 네트워크서비스모니터링 로그 정보를 제공하며, 로그ID, 시스템IP, 시스템포트, 시스템명, 모니터링상태, 로그정보, 생성일시 정보로 구성되어 있습니다 .">
<caption>네트워크서비스모니터로그 상세보기</caption>
<tbody>
	<tr> 
	    <th width="20%">
	    	로그ID
	    </th>
	    <td width="80%">
	      	<c:out value="${ntwrkSvcMntrngLogVO.logId}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
		<th>
			시스템IP
		</th>
		<td>
			<c:out value="${ntwrkSvcMntrngLogVO.sysIp}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
	    <th>
	    	시스템포트
	    </th>
	    <td>
	      	<c:out value="${ntwrkSvcMntrngLogVO.sysPort}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	시스템명
	    </th>
	    <td>
	      	<c:out value="${ntwrkSvcMntrngLogVO.sysNm}" escapeXml="false" />
	    </td>
	</tr>
	<tr> 
	    <th>
	    	모니터링상태
	    </th>
	    <td>
	      	<c:out value="${ntwrkSvcMntrngLogVO.mntrngSttus}" escapeXml="false" />
	    </td>
	</tr>
	<c:if test="${ntwrkSvcMntrngLogVO.mntrngSttus} ne '정상'">
	<tr> 
	    <th>
	    	로그정보
	    </th>
	    <td>
	    	<textarea id="logInfo" name="logInfo" rows="10" cols="75" title="로그정보"><c:out value="${ntwrkSvcMntrngLog.logInfo}" escapeXml="false" /></textarea>
	    </td>
	</tr>
	</c:if>
	<tr> 
	    <th>
	    	생성일시
	    </th>
	    <td>
	      	<c:out value="${ntwrkSvcMntrngLogVO.creatDt}" escapeXml="false" />
	    </td>
	</tr>
</tbody>
</table>
	
<!-- 검색조건 유지 -->
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<form:hidden path="searchBgnHour" />
<form:hidden path="searchEndHour" />
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="ntwrkSvcMntrngLogVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("ntwrkSvcMntrngLogVO");
	varForm.action = "${pageContext.request.contextPath}/utl/sys/nsm/listNtwrkSvcMntrngLog.do";
	varForm.submit();
}	

</script>
