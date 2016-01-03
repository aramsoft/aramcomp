<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NoteTrnsmitCnfirm.jsp
 * @Description : 보낸쪽지함 수신상태 조회
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
<title>보낸쪽지함 수신상태 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:100%;">

<div class="content_title">
	<h2>수신상태 조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_close_trnsmitCnfirm(); return false;"><spring:message code="button.close" /></a></span>
	</div>
</div>

<!-- 보낸쪽지정보 -->
<table class="table-register" summary="발신 상세조회  목록을  제공한다.">
<caption>발신 상세조회  목록을  제공한다</caption>
	<tr> 
		<th width="20%">
			제목
		</th>
		<td width="80%">
			<c:out value="${resultList[0].noteSj}"/>
		</td>
	</tr>
	<tr> 
		<th>
			발신자 ID
		</th>
		<td>
			<c:out value="${resultList[0].trnsmiterIds}"/>
		</td>
	</tr>
	<tr> 
		<th>
			발신자 명
		</th>
		<td>
			<c:out value="${resultList[0].trnsmiterNm}"/>
		</td>
	</tr>
	<tr> 
		<th>
			발신 시각
		</th>
		<td>
			<c:out value="${resultList[0].trnsmiterPnttm}"/>
		</td>
	</tr>
</table>

<!-- 보낸사람정보 -->
<table class="table-list" summary="수신  목록을  제공한다.">
<caption>수신  목록을  제공한다</caption>
<thead>
	<tr>  
	    <th scope="col" width="35px">순번</th>
	    <th scope="col" width="100px">수신자ID</th>
	    <th scope="col" width="100px">받는사람</th>
	    <th scope="col" width="100px">개봉/미개봉</th>
	    <th scope="col" width="100px">구분</th>
	    <th scope="col" class="title">수신시각</th>   
	    <th scope="col" width="40px"></th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	<%-- 데이터를 화면에 출력해준다  --%>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
		<td class="lt_text3"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="lt_text3"><c:out value="${result.rcverIds}"/></td>
		<td class="lt_text3"><c:out value="${result.rcverNm}"/></td>
		<td class="lt_text3">
			<c:if test="${result.openYn eq 'Y'}">개봉</c:if>
			<c:if test="${result.openYn eq 'N'}">미개봉</c:if>
		</td>
		<td class="lt_text3">
			<c:if test="${result.recptnSe eq '1'}">수신</c:if>
			<c:if test="${result.recptnSe eq '2'}">참조</c:if>
		</td>
	    <td class="lt_text3"><c:if test="${result.openYn eq 'Y'}"><c:out value="${result.rcverPnttm}"/></c:if></td>
	    <td class="lt_text3">
	    <%-- 미개봉만 삭제가능하게  --%>
			<c:if test="${result.openYn eq 'N'}">
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_delete_trnsmitCnfirm('${result.noteId}','${result.noteTrnsmitId}','${result.noteRecptnId}'); return false;">
				삭제
			</a>
			</span>
	    	</c:if>  
	    </td>
	</tr>
	</c:forEach>
</tbody>  
</table>

<form:form commandName="searchVO" action="" method="post">
<input name="noteId" type="hidden" value="">
<input name="noteTrnsmitId" type="hidden" value="">
<input name="noteRecptnId" type="hidden" value="">

<input name="cmd" type="hidden" value="del"/>
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
* 화면 닫기 함수
******************************************************** */
function fn_aram_close_trnsmitCnfirm(){
	window.close();
}

/* ********************************************************
* 보낸쪽지함 삭제
******************************************************** */
function fn_aram_delete_trnsmitCnfirm(noteId,noteTrnsmitId,noteRecptnId){
    var varForm = document.getElementById("searchVO");
	
	if(confirm("선택된 보낸쪽지 삭제 하시겠습니까?")){
	    varForm.noteId.value = noteId;
	    varForm.noteTrnsmitId.value = noteTrnsmitId;
	    varForm.noteRecptnId.value = noteRecptnId;
		varForm.cmd.value = 'del';
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/confirmNoteTrnsmit.do";
	    varForm.submit();
	}
}

</script>

</body>
</html>
