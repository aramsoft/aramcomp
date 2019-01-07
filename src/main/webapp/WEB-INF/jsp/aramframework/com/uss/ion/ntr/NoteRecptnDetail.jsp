<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NoteRecptnDetail.jsp
 * @Description : 받은쪽지함 상세조회
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>받은쪽지함 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>받은쪽지함 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_reply_noteRecptn(); return false;">답장</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete_noteRecptn(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_noteRecptn(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="noteRecptnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="noteId" />
<form:hidden path="noteTrnsmitId" />
<form:hidden path="noteRecptnId" />

<input name="cmd" type="hidden" value="<c:out value=''/>"/>

<!--  상세조회  폼 영역  -->
<table class="table-detail" summary="상세조회  목록을  제공한다.">
<caption>상세조회  목록을  제공한다</caption>
	<tr> 
		<th width="20%">
			쪽지 제목
		</th>
		<td colspan="3">&nbsp;
			<c:set var="noteRecptnNoteSj" value="${fn:escapeXml(noteRecptn.noteSj)}"/>
			<c:set var="noteRecptnNoteSj" value="${fn:replace(noteRecptnNoteSj , crlf , '<br>')}"/>
			<c:out value="${noteRecptnNoteSj}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
		<th width="20%">
			발신자
		</th>
		<td width="45%">
			<c:out value="${noteRecptn.trnsmiterNm}" />
		</td>
		<th width="15%">
			발신시각
		</th>
		<td width="20%">
			<c:out value="${noteRecptn.trnsmiterPnttm}" />
		</td>
	</tr>
	<tr> 
		<th>
			수신자
		</th>
		<td>
			<c:forEach items="${resultRecptnEmp}" var="result" varStatus="status">
				<c:if test="${noteRecptn.rcverNm eq result.rcverNm}">
					<b><c:out value="${noteRecptn.rcverNm}" /></b>
				</c:if>
				<c:if test="${noteRecptn.rcverNm ne result.rcverNm}">
					<c:out value="${result.rcverNm}" />
				</c:if>
				<c:if test="${fn:length(resultRecptnEmp) != status.count}">,</c:if>
			</c:forEach>
		</td>
		<th>
			수신시각
		</th>
		<td>
			<c:out value="${noteRecptn.rcverPnttm}" />
		</td>
	</tr>
	<tr> 
		<th>
			쪽지 내용
		</th>
		<td colspan="3">
			<br>
			<c:set var="noteRecptnNoteCn" value="${fn:escapeXml(noteRecptn.noteCn)}"/>
			<c:set var="noteRecptnNoteCn" value="${fn:replace(noteRecptnNoteCn , crlf , '<br>')}"/>
			<c:out value="${noteRecptnNoteCn}" escapeXml="false" />
			<br>
		</td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list_noteRecptn(){
    var varForm = document.getElementById("noteRecptnVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ntr/listNoteRecptn.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_reply_noteRecptn(){
    var varForm = document.getElementById("noteRecptnVO");
    varForm.cmd.value = 'reply';
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ntm/registNote.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete_noteRecptn(){
    var varForm = document.getElementById("noteRecptnVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.cmd.value = 'del';
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/ntr/detailNoteRecptn.do";
	    varForm.submit();
	}
}

</script>

</body>
</html>