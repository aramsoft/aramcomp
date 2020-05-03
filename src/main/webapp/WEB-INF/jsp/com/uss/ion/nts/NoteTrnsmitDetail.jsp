<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NoteTrnsmitDetail.jsp
 * @Description : 보낸쪽지함 상세조회
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
<title>보낸쪽지함 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>보낸쪽지함 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete_noteTrnsmit(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list_noteTrnsmit(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>	
</div>

<form:form modelAttribute="noteTrnsmitVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="noteId" />
<form:hidden path="noteTrnsmitId" />

<input name="cmd" type="hidden" value="<c:out value=''/>"/>

<!--  등록  폼 영역  -->
<table class="table-detail" summary="상세조회  목록을  제공한다.">
<caption>상세조회  목록을  제공한다</caption>
	<tr> 
		<th width="20%">
			쪽지 제목
		</th>
		<td colspan="3">
			<c:out value="${noteTrnsmit.noteSj}" />
		</td>
	</tr>
	<tr> 
		<th width="20%">
			발신자
		</th>
		<td width="45%">
			<c:out value="${noteTrnsmit.frstRegisterNm}" />
		</td>
		<th width="15%">
			발신시각
		</th>
		<td width="20%">
			<fmt:formatDate value="${noteTrnsmit.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr> 
		<th>
			수신자
		</th>
		<td colspan="3">
			<div style="float:left;padding:0px 0px 0px 3px; ">
			<c:forEach items="${resultRecptnEmp}" var="result" varStatus="status">
				<c:out value="${result.rcverNm}" />
				<c:if test="${fn:length(resultRecptnEmp) != status.count}">,</c:if>
			</c:forEach>
			</div>

			<div style="float:right;">
				<font color="green">[전체:${noteTrnsmit.rcverTotal}]</font>&nbsp;&nbsp;
				<font color="blue">[개봉:${noteTrnsmit.openY}]</font>&nbsp;&nbsp;
				<font color="red">[미개봉:${noteTrnsmit.openN}]</font>&nbsp;&nbsp;
			</div>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			쪽지 내용
		</th>
		<td colspan="3">
			<br>
			<c:set var="noteTrnsmitNoteCn" value="${fn:escapeXml(noteTrnsmit.noteCn)}"/>
			<c:set var="noteTrnsmitNoteCn" value="${fn:replace(noteTrnsmitNoteCn , crlf , '<br>')}"/>
			<c:out value="${noteTrnsmitNoteCn}" escapeXml="false" />
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
function fn_aram_list_noteTrnsmit(){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/listNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete_noteTrnsmit(){
    var varForm = document.getElementById("noteTrnsmitVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.cmd.value = 'del';
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/detailNoteTrnsmit.do";
	    varForm.submit();
	}
}

</script>

</body>
</html>