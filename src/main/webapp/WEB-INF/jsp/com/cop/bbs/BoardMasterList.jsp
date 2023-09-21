<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BoardMasterList.jsp
  * @Description : 게시판 목록
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
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="boardMasterVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="bbsId">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${boardMasterVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<form:select path="searchCondition" class="select" title="검색유형선력">
				<form:option value="" label="--선택하세요--" />
			   	<form:option value="BBS_NM" label="게시판명" />
			   	<form:option value="BBS_TY_CODE" label="게시판유형" />
		   	</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
			<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
	    	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	    	<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="번호,게시판명,게시판유형,게시판속성,생성일,사용여부  목록입니다">
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
 	   	<th scope="col" width="15%">게시판ID</th>
 	   	<th scope="col"            >게시판명</th>
    	<c:if test="${useCommunity == 'true'}">
			<th scope="col" width="10%">커뮤니티명</th>
    	</c:if>
    	<th scope="col" width="10%">게시판유형</th>
    	<th scope="col" width="10%">게시판속성</th>
    	<th scope="col" width="10%">생성일</th>
    	<th scope="col" width="8%" >사용여부</th>
  	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
  	<tr>
    	<c:set var="colNo" value="7" />
    	<c:if test="${useCommunity == 'true'}">
    	<c:set var="colNo" value="${colNo + 1}" />
  		</c:if>
 		<td class="lt_text3" colspan="${colNo}"><spring:message code="common.nodata.msg" /></td>
  	</tr>
 	</c:if>

 	<c:set var="startIndex" value="${(boardMasterVO.pageIndex-1) * boardMasterVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${boardMasterVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3"><c:out value="${result.bbsId}"/></td>
    	<td class="lt_text3">
	   		<span class="link">
	   		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.bbsId}"/>'); return false;">
    			<c:out value="${result.bbsNm}"/>
	   		</a>
	   		</span>
    	</td>
    	<c:if test="${useCommunity == 'true'}">
			<td class="lt_text3"><c:out value="${result.cmmntyNm}"/></td>
    	</c:if>
    	<td class="lt_text3"><c:out value="${result.bbsTyCodeNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.bbsAttrbCodeNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.frstRegisterPnttm}"/></td>
    	<td class="lt_text3">
    		<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
    		<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

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
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMaster.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("boardMasterVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMaster.do";
    varForm.submit();
}

function fn_aram_detail(bbsId){
    var varForm = document.getElementById("boardMasterVO");
    varForm.bbsId.value = bbsId;
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/editBoardMaster.do";
    varForm.submit();
}

function fn_aram_regist(){
    var varForm = document.getElementById("boardMasterVO");
    varForm.bbsId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/registBoardMaster.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:" + encodeURIComponent("게시판생성관리");	
	window.open(url, "도움말");
}
</script>


