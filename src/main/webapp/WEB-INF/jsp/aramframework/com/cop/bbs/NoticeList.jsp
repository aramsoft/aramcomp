<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NoticeList.jsp
  * @Description : 게시물 목록
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
<c:if test="${anonymous == 'true'}">
	<c:set var="prefix" value="/anonymous"/>
</c:if>

<DIV id="main"> 

<div class="content_title">
	<h2><c:out value="${boardVO.boardMasterVO.bbsNm}"/></h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle;" alt="도움말아이콘이미지">
	</a>
</div>

<form:form commandName="boardVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bbsId" />
<input type="hidden" name="nttId" value="0"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
		<form:select path="searchCondition" class="select" title="검색조건선택">
			<form:option value="" label="--선택하세요--" />
	   		<form:option value="NTT_SJ" label="제목" />
	   		<form:option value="NTT_CN" label="내용" />
	   		<c:if test="${anonymous != 'true'}">
	   		<form:option value="USER_NM" label="작성자" />
	   		</c:if>
		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="번호, 제목, 게시시작일, 게시종료일, 작성자, 작성일, 조회수   입니다">
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col"            >제목</th>
   		<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
		    <th scope="col" width="12%">게시시작일</th>
		    <th scope="col" width="12%">게시종료일</th>
   		</c:if>
   		<c:if test="${anonymous != 'true'}">
	    	<th scope="col" width="10%">작성자</th>
    	</c:if>
    	<th scope="col" width="12%">작성일</th>
    	<th scope="col" width="8%" >조회수</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
    	<c:set var="colNo" value="4" />
   		<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
    		<c:set var="colNo" value="${colNo + 2}" />
  		</c:if>
   		<c:if test="${anonymous != 'true'}">
    		<c:set var="colNo" value="${colNo + 1}" />
    	</c:if>
 		<td class="lt_text3" colspan="${colNo}"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="searchVO" value="${boardVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
 
	<tr<c:if test="${result.useAt == 'Y' && result.isExpired != 'Y' || role == 'ROLE_ADMIN'}">
		class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.nttId}"/>'); return false;"
	   </c:if>>
	   
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
   		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text">
	    	<c:if test="${result.threadDepth!=0}">
	    		<c:forEach begin="0" end="${result.threadDepth}" step="1">
	   			&nbsp;
	    		</c:forEach>
	    		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/reply_arrow.gif" alt="reply arrow">
	    	</c:if>
	    	<c:choose>
	    	<c:when test="${(editAuthFlag != 'Y' && result.isExpired=='Y') || result.useAt == 'N'}">
	    		<span class="bbs_useless"><c:out value="${result.nttSj}" /></span>
	    	</c:when>
	    	<c:otherwise>
	    		<c:out value="${result.nttSj}"/>
	    	</c:otherwise>
	    	</c:choose>
    	</td>
    	
   		<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
	    <td class="lt_text3"><c:out value="${fn:substring(result.ntceBgnde, 0, 4)}-${fn:substring(result.ntceBgnde, 4, 6)}-${fn:substring(result.ntceBgnde, 6, 8)}"/></td>
	    <td class="lt_text3"><c:out value="${fn:substring(result.ntceEndde, 0, 4)}-${fn:substring(result.ntceEndde, 4, 6)}-${fn:substring(result.ntceEndde, 6, 8)}"/></td>
   		</c:if>
   		
   		<c:if test="${anonymous != 'true'}">
    	<td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
    	</c:if>
    	
    	<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
    	<td class="lt_text3"><c:out value="${result.rdcnt}"/></td>
  	</tr>
 	</c:forEach>
	 	
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<c:if test="${preview == null || preview !='true'}">
<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("boardVO");
    varForm.pageIndex.value = pageNo;
    if( varForm.curTrgetId.value != "") {
    	varForm.action = "${pageContext.request.contextPath}/content/apps/${targetVO.pathId}/board/${boardVO.pathId}/articles";
    }
    else {
    	varForm.action = "${pageContext.request.contextPath}/content/board${prefix}/${boardVO.pathId}/articles";
    }
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("boardVO");
    varForm.pageIndex.value = '1';
    if( varForm.curTrgetId.value != "") {
    	varForm.action = "${pageContext.request.contextPath}/content/apps/${targetVO.pathId}/board/${boardVO.pathId}/articles";
    }
    else {
    	varForm.action = "${pageContext.request.contextPath}/content/board${prefix}/${boardVO.pathId}/articles";
    }
    varForm.submit();
}

function fn_aram_detail(nttId) {
    var varForm = document.getElementById("boardVO");
    if( varForm.curTrgetId.value != "") {
    	varForm.action = "${pageContext.request.contextPath}/content/apps/${targetVO.pathId}/board/${boardVO.pathId}/article/" + nttId;
    }
    else {
    	varForm.action = "${pageContext.request.contextPath}/content/board${prefix}/${boardVO.pathId}/article/" + nttId;
    }
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("boardVO");
    varForm.nttId.value = 0;
    varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/registBoardArticle.do";
    varForm.submit();
}

</script>
</c:if>

<script type="text/javascript">
/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("게시물관리");	
	window.open(url, "도움말");
}
</script>
