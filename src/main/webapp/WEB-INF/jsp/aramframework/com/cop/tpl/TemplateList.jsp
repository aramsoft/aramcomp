<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TemplateList.jsp
  * @Description : 템플릿 목록
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
	<h2>템플릿 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="templateInfVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="tmplatId" value="" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
   		<form:select path="searchVO.searchCondition" class="select" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
		   	<form:option value="TMPLAT_NM" label="템플릿명" />
		   	<form:option value="TMPLAT_SE_CODE" label="템플릿구분" />
	   	</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list">
<thead>
	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="15%">템플릿명</th>
    	<th scope="col" width="10%">템플릿구분</th>
    	<th scope="col"            >템플릿경로</th>
    	<th scope="col" width="10%">사용여부</th>
    	<th scope="col" width="10%">등록일자</th>
  	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
    	<td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="searchVO" value="${templateInfVO.searchVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.tmplatId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.tmplatNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.tmplatSeCodeNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.tmplatCours}"/></td>
	    <td class="lt_text3">
	    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    </td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("templateInfVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("templateInfVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();
}

function fn_aram_detail(tmplatId){
    var varForm = document.getElementById("templateInfVO");
    varForm.tmplatId.value = tmplatId;
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/editTemplate.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("templateInfVO");
    varForm.tmplatId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/registTemplate.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("디자인템플릿");	
	window.open(url, "도움말");
}
</script>
