<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : NtwrkSvcMntrngList.jsp
 * @Description : 네트워크서비스모니터링 목록
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
	<h2>네트워크서비스모니터링 목록</h2>
</div>

<form:form commandName="ntwrkSvcMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="sysIp">
<input type="hidden" name="sysPort">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="SYS_NM" label="시스템명" />			   
	   		<form:option value="SYS_IP" label="시스템IP" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
	   		<form:option value="MNTRNG_STTUS" label="상태" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="이 표는 네트워크서비스모니터링 대상 정보를 제공하며, 시스템IP, 시스템포트, 시스템명, 모니터링상태, 관리자명 정보로 구성되어 있습니다 .">
<caption>네트워크서비스모니터링대상 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">시스템IP</th>
	    <th scope="col" width="10%">시스템포트</th>
	    <th scope="col"            >시스템명</th>
	    <th scope="col" width="15%">모니터링상태</th>
	    <th scope="col" width="15%">관리자명</th>
  	</tr>
</thead>    
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
 	<c:set var="startIndex" value="${(ntwrkSvcMntrngVO.pageIndex-1) * ntwrkSvcMntrngVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.sysIp}"/>', '<c:out value="${result.sysPort}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${ntwrkSvcMntrngVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.sysIp}"/></td>
	    <td class="lt_text3"><c:out value="${result.sysPort}"/></td>
	    <td class="lt_text3"><c:out value="${result.sysNm}"/></td>
		<td class="lt_text3"><c:out value="${result.mntrngSttus}"/></td>
	    <td class="lt_text3"><c:out value="${result.mngrNm}"/></td>
	</tr>
	</c:forEach>	  
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("ntwrkSvcMntrngVO");
    varForm["searchVO.pageIndex"].value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/nsm/listNtwrkSvcMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("ntwrkSvcMntrngVO");
    varForm["searchVO.pageIndex"].value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/nsm/listNtwrkSvcMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(sysIp, sysPort) {
    var varForm = document.getElementById("ntwrkSvcMntrngVO");
    varForm.sysIp.value = sysIp;
    varForm.sysPort.value = sysPort;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/nsm/detailNtwrkSvcMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){	
    var varForm = document.getElementById("ntwrkSvcMntrngVO");
    varForm.sysIp.value = '';
    varForm.sysPort.value = '';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/nsm/registNtwrkSvcMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){	
	location.href =  "${pageContext.request.contextPath}/utl/sys/nsm/listNtwrkSvcMntrngLog.do";
}

</script>
