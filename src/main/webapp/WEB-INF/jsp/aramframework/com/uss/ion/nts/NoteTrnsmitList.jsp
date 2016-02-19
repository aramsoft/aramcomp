<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NoteTrnsmitList.jsp
 * @Description : 보낸쪽지함 목록
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
<title>보낸쪽지함 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>보낸쪽지함 목록</h2>
</div>

<form:form commandName="noteTrnsmitVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="noteId" type="hidden" value="">
<input name="noteTrnsmitId" type="hidden" value="">

<input name="cmd" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search_noteTrnsmit(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete_noteTrnsmit(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
		보낸날짜:&nbsp;
		<label for="searchFromDate"></label>
      	<form:input path="searchFromDate" size="10" maxlength="10" title="보낸날짜 시작일자" readonly="true" />
      	<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchFromDate); return false;" style="selector-dummy:expression(this.hideFocus=false);">
      		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지" width="15" height="15">
      	</a>
	     ~
	    <label for="searchToDate"> </label>
	   	<form:input path="searchToDate" size="10" maxlength="10"  title="보낸날짜  종료일자" readonly="true" />
      	<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchToDate); return false;" style="selector-dummy:expression(this.hideFocus=false);">
      		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지" width="15" height="15">
      	</a>
      	
		<label for="searchCondition"> </label>
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="NOTE_SJ" label="쪽지제목" />			   
	   		<form:option value="NOTE_CN" label="쪽지내용" />			   
   		</form:select>
   		
 		<label for="searchKeyword"> </label>
   		<form:input path="searchVO.searchKeyword" size="25" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search_noteTrnsmit();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<!-- 목록 -->
<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%">No.</th>
		<th scope="col" width="5%">
			<input type="checkbox" name="checkAll" id="checkAll" title="전체선택" value="1" onClick="fn_aram_checkAll_NoteTrnsmit();">
		</th>
	    <th scope="col"            >제목</th>
	    <th scope="col" width="15%">받는사람</th>
	    <th scope="col" width="10%">개봉/미개봉</th>
	    <th scope="col" width="25%">보낸시각</th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(noteTrnsmitVO.searchVO.pageIndex-1) * noteTrnsmitVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${noteTrnsmitVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>
		<td class="lt_text3">
			<input type="checkbox" name="checkList" title="선택" value="${result.noteId},${result.noteTrnsmitId}">
		</td>

		<td class="lt_text3L">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail_noteTrnsmit('${result.noteId}','${result.noteTrnsmitId}'); return false;">
				<c:out value="${result.noteSj}"/>
			</a>
			</span>
	   	</td>
		<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_confirm_noteTrnsmit('${result.noteId}'); return false;" title="수신상태조회 새창으로">
				<c:out value="${result.rcverNm}"/><c:if test="${result.rcverCnt ne '0'}">&nbsp;외&nbsp; ${result.rcverCnt}명</c:if>
			</a>
			</span>
		</td>
		<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_confirm_noteTrnsmit('${result.noteId}'); return false;" title="수신상태조회 새창으로">
				${result.openY}/${result.openN}
			</a>
			</span>
		</td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_noteTrnsmit();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/listNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search_noteTrnsmit(){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/listNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail_noteTrnsmit(noteId,noteTrnsmitId){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm.noteId.value = noteId;
    varForm.noteTrnsmitId.value = noteTrnsmitId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/detailNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
* 수신자 목록 팝업
******************************************************** */
function fn_aram_confirm_noteTrnsmit(noteId){
	var left = (screen.width-800)/2;
	var top = (screen.height-500)/3;
	var url = "/uss/ion/nts/confirmNoteTrnsmit.do?noteId=" + noteId;
	var name = "";
	var width = 755;
	var height = 500;

	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus()}
}

/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_aram_delete_noteTrnsmit(){
	var vFrom = document.listForm;

	if(fn_aram_delCnt_noteRecptn() == 0){alert("삭제할 목록을 선택해주세요!   "); document.getElementById('checkAll').focus();return;}

	if(confirm("선택된 보낸쪽지함을 삭제 하시겠습니까?")){
		vFrom.action = "/uss/ion/nts/listNoteTrnsmit.do";
		vFrom.cmd.value = 'del';
		vFrom.submit();
	}
}

/* ********************************************************
* 체크 박스 선태 건수
******************************************************** */
var g_nDelCount  = 0;
function fn_aram_delCnt_noteRecptn(){

	g_nDelCount = 0;
	var FLength = document.getElementsByName("checkList").length;

	//undefined
	if( FLength == 1){
		if(document.listForm.checkList.checked == true){g_nDelCount++;}
	} else {
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName("checkList")[i].checked == true){g_nDelCount++;}
		}
	}

	return g_nDelCount;
}

/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_aram_checkAll(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.listForm.checkList.checked = checkAllValue;
	} else {
		for(var i=0; i < FLength; i++)
		{
			document.getElementsByName("checkList")[i].checked = checkAllValue;
		}
	}
}

</script>

</body>
</html>