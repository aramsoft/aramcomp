<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : WikiBookmarkList.jsp
 * @Description : 위키북마크 목록
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>위키북마크 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:400px">

<form name="listFormS" action="${pageContext.request.contextPath}/uss/ion/wik/bmk/listWikiBookmark.do" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div id="search_area">
	<div class="button_area">
  		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
  		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
		북마크명:
		<input type="text" name="searchKeyword" size="10" maxlength="35" title="검색어 입력" onkeypress="javascript:press(event);" /> 
	</div>
</div>

<!-- 목록  -->
<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
  	<tr>
    	<th scope="col" width="35px">순번</th>
		<th scope="col" width="30px">
			<input type="checkbox" name="checkAll" id="checkAll" title="전체선택" value="1" onClick="fn_aram_checkAll();">
		</th>
    	<th scope="col"             >북마크명</th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="3"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td class="lt_text3"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="lt_text3">
			<input type="checkbox" name="checkList" title="선택" value="${resultInfo.wikiBkmkId}">
		</td>
		
		<td class="lt_text3L" style="word-break;break-all">
			<div style="visibility:hidden;display:none;"><a href="#_PAGE${status.count}"></a></div>
			<a href="<c:url value="/JSPWiki/Wiki.jsp"><c:param name="page" value="${resultInfo.wikiBkmkNm}"/></c:url>" target="_blank"><c:out value="${resultInfo.wikiBkmkNm}"/></a>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

<input name="cmd" type="hidden" value="">
<input name="searchCondition" type="hidden" value="A.WIKI_BKMK_NM">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
	document.listFormS.pageIndex.value = pageNo;
	document.listFormS.action = "${pageContext.request.contextPath}/uss/ivp/mpe/selectIndvdlpgeResult.do";
   	document.listFormS.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
	var vFrom = document.listFormS;
	vFrom.pageIndex.value = "1";
	vFrom.action = "/uss/ion/wik/bmk/listWikiBookmark.do";
	vFrom.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(noteId,noteTrnsmitId){
	var vFrom = document.listFormS;
	vFrom.noteId.value = noteId;
	vFrom.noteTrnsmitId.value = noteTrnsmitId;
	vFrom.action = "/uss/ion/rss/detailRssTag.do";
	vFrom.submit();
}

/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_aram_checkAll(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.listFormS.checkList.checked = checkAllValue;
	}{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
	}
}
/* ********************************************************
* 체크 박스 선태 건수
******************************************************** */
var g_nDelCount  = 0;
function fn_aram_delCnt_NoteRecptn(){

	g_nDelCount = 0;
	var FLength = document.getElementsByName("checkList").length;

	//undefined
	if( FLength == 1){
		if(document.listFormS.checkList.checked == true){g_nDelCount++;}
	}{
			for(var i=0; i < FLength; i++)
			{
				if(document.getElementsByName("checkList")[i].checked == true){g_nDelCount++;}
			}
	}

	return g_nDelCount;
}

/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_aram_delete(){
	var vFrom = document.listFormS;

	if(fn_aram_delCnt_NoteRecptn() == 0){alert("삭제할 목록을 선택해주세요!   "); document.getElementById('checkAll').focus();return;}

	if(confirm("선택된 정보를 삭제 하시겠습니까?")){
		vFrom.action = "/uss/ion/wik/bmk/listWikiBookmark.do";
		vFrom.cmd.value = 'del';
		vFrom.submit();
	}
}

</script>

</body>
</html>