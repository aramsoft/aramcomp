<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BdMstrListByTarget.jsp
  * @Description : 커뮤니티/동호회 사용 게시판 목록 
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
	<h2>게시판 목록</h2>
</div>

<form:form modelAttribute="boardMasterVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="bbsId">
<input type="hidden" name="useAt" />
<input type="hidden" name="publicAt" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
   		<form:select path="searchCondition" class="select" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
		   	<form:option value="BBS_NM" label="게시판명" />
		   	<form:option value="BBS_TY_CODE" label="게시판유형" />
		</form:select>
    	<form:input path="searchKeyword" type="text" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list">
<thead>
	<tr>
	    <th scope="col" width="7%">No.</th>
	    <th scope="col"           >게시판명</th>
	    <th scope="col" width="8%">게시판유형</th>
	    <th scope="col" width="8%">게시판속성</th>
	    <th scope="col" width="8%">생성일</th>
	    <th scope="col" width="8%">사용여부</th>
	    <th scope="col" width="8%">상태변경</th>
	    <th scope="col" width="8%">공개여부</th>
	    <th scope="col" width="8%">상태변경</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(boardMasterVO.pageIndex-1) * boardMasterVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${boardMasterVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.bbsId}"/>'); return false;">
			  	<c:out value="${result.bbsNm}"/>
			</a>
		</td>
		<td class="lt_text3"><c:out value="${result.bbsTyCodeNm}"/></td>
		<td class="lt_text3"><c:out value="${result.bbsAttrbCodeNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
    	<c:choose>
    	<c:when test="${result.useAt=='Y'}">
			<td class="lt_text3">
			   	<spring:message code="button.use" />
			</td>
			<td class="lt_text3">
			   	<input type="button" value="변경" onClick="javascript:fn_aram_notuse_bbsUse('<c:out value="${result.bbsId}"/>'); return false;" />
			</td>
    	</c:when>
    	<c:otherwise>
			<td class="lt_text3">
			   	<spring:message code="button.notUsed" />
			</td>
			<td class="lt_text3">
			  	<input type="button" value="변경" onClick="javascript:fn_aram_use_bbsUse('<c:out value="${result.bbsId}"/>'); return false;" />
			</td>
    	</c:otherwise>
    	</c:choose>
    	<c:choose>
    	<c:when test="${result.publicAt=='Y'}">
			<td class="lt_text3">
			   	공개
			</td>
			<td class="lt_text3">
			   	<input type="button" value="변경" onClick="javascript:fn_aram_private_bbsUse('<c:out value="${result.bbsId}"/>'); return false;" />
			</td>
    	</c:when>
    	<c:otherwise>
			<td class="lt_text3">
			   	비공개
			</td>
			<td class="lt_text3">
			  	<input type="button" value="변경" onClick="javascript:fn_aram_public_bbsUse('<c:out value="${result.bbsId}"/>'); return false;" />
			</td>
    	</c:otherwise>
    	</c:choose>
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
    var varForm = document.getElementById("boardMasterVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/com/listBdMstrByTrget.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("boardMasterVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/com/listBdMstrByTrget.do";
    varForm.submit();
}

function fn_aram_detail(bbsId){
    var varForm = document.getElementById("boardMasterVO");
    varForm.bbsId.value = bbsId;
    varForm.action = "${pageContext.request.contextPath}/cop/com/editBdMstrByTrget.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("boardMasterVO");
    varForm.bbsId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/com/registBdMstrByTrget.do";
    varForm.submit();
}

function fn_aram_use_bbsUse(bbsId) {
    var varForm = document.getElementById("boardMasterVO");
	if (confirm('<spring:message code="cop.use.msg" />')) {
		varForm.bbsId.value = bbsId;
		varForm.useAt.value = 'Y';
		varForm.action = "${pageContext.request.contextPath}/cop/com/updateBoardUseInfByTrget.do";
		varForm.submit();
	}
}

function fn_aram_notuse_bbsUse(bbsId) {
    var varForm = document.getElementById("boardMasterVO");
	if (confirm('<spring:message code="cop.unuse.msg" />')) {
		varForm.bbsId.value = bbsId;
		varForm.useAt.value = 'N';
		varForm.action = "${pageContext.request.contextPath}/cop/com/updateBoardUseInfByTrget.do";
		varForm.submit();
	}
}

function fn_aram_public_bbsUse(bbsId) {
    var varForm = document.getElementById("boardMasterVO");
	if (confirm('공개로 설정하시겠습니까?')) {
		varForm.bbsId.value = bbsId;
		varForm.publicAt.value = 'Y';
		varForm.action = "${pageContext.request.contextPath}/cop/com/updateBoardUseInfByTrget.do";
		varForm.submit();
	}
}

function fn_aram_private_bbsUse(bbsId) {
    var varForm = document.getElementById("boardMasterVO");
	if (confirm('비공개로 설정하시겠습니까?')) {
		varForm.bbsId.value = bbsId;
		varForm.publicAt.value = 'N';
		varForm.action = "${pageContext.request.contextPath}/cop/com/updateBoardUseInfByTrget.do";
		varForm.submit();
	}
}

</script>

