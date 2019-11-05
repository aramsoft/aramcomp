<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : LoginPolicyList.jsp
 * @Description : 로그인정책 목록
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
	<h2>로그인정책 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="loginPolicyVO" action="${pageContext.request.contextPath}/uat/uap/listLoginPolicy.do" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="emplyrId"/>

<div id="search_area">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
       	사용자 명 : 
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchCondition" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="로그인정책에 대한 목록을 제공한다.">
<caption>로그인정책 관리</caption>
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="20%">사용자 ID</th>
    	<th scope="col" width="20%">사용자 명</th>
    	<th scope="col"            >IP 정보</th>
    	<th scope="col" width="15%">제한여부</th>
  	</tr>
</thead>
<tbody>
  	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(loginPolicyVO.pageIndex-1) * loginPolicyVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.emplyrId}"/>'); return false;">
  	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${loginPolicyVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3"><c:out value="${result.emplyrId}"/></td>
    	<td class="lt_text3"><c:out value="${result.emplyrNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.ipInfo}"/></td>
    	<td class="lt_text3">
    		<c:if test="${result.lmttAt == 'Y'}">Y</c:if>
    		<c:if test="${result.lmttAt == 'N'}">N</c:if>
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" defer="defer">

function press() {

    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("loginPolicyVO");
    varForm.searchCondition.value = "1";
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uat/uap/listLoginPolicy.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("loginPolicyVO");
    varForm.searchCondition.value = "1";
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uat/uap/listLoginPolicy.do";
    varForm.submit();
}

function fn_aram_detail(emplyrId) {
    var varForm = document.getElementById("loginPolicyVO");
    varForm.emplyrId.value = emplyrId;
    varForm.action = "${pageContext.request.contextPath}/uat/uap/editLoginPolicy.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uat:" + encodeURIComponent("로그인정책관리");	
	window.open(url, "도움말");
}

</script>

