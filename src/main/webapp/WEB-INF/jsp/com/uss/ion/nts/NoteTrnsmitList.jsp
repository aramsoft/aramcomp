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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/jquery-1.7.1.min.js"></script>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>보낸쪽지함 목록</h2>
</div>

<form:form modelAttribute="noteTrnsmitVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="noteId" type="hidden" value="">
<input name="noteTrnsmitId" type="hidden" value="">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${noteTrnsmitVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
			보낸날짜:&nbsp;
			<label for="searchFromDate"></label>
	      	<form:input path="searchFromDate" size="10" maxlength="10" title="보낸날짜 시작일자" readonly="true" />
	      	<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchFromDate); return false;" style="selector-dummy:expression(this.hideFocus=false);">
	      		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지" width="15" height="15">
	      	</a>
		     ~
		    <label for="searchToDate"> </label>
		   	<form:input path="searchToDate" size="10" maxlength="10"  title="보낸날짜  종료일자" readonly="true" />
	      	<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchToDate); return false;" style="selector-dummy:expression(this.hideFocus=false);">
	      		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지" width="15" height="15">
	      	</a>
	      	
			<label for="searchCondition"> </label>
	    	<form:select path="searchCondition" title="조회조건 선택">
		   		<form:option value='' label="--선택하세요--" />
		   		<form:option value="NOTE_SJ" label="쪽지제목" />			   
		   		<form:option value="NOTE_CN" label="쪽지내용" />			   
	   		</form:select>
	   		
	 		<label for="searchKeyword"> </label>
	   		<form:input path="searchKeyword" size="25" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<!-- 목록 -->
<table class="table-list" id="tblData" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%">No.</th>
        <th scope="col" width="5%" >
    		<input type="checkbox" id="checkAll" class="check2" title="전체선택" />
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
 	<c:set var="searchVO" value="${noteTrnsmitVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

        <td class="lt_text3">
			<input type="checkbox" class="check2" id="uniqIds" name="uniqIds" value="${result.noteId}-${result.noteTrnsmitId}" />
        </td>
		<td class="lt_text3L">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('${result.noteId}','${result.noteTrnsmitId}'); return false;">
				<c:out value="${result.noteSj}"/>
			</a>
			</span>
	   	</td>
		<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_confirm('${result.noteId}'); return false;" title="수신상태조회 새창으로">
				<c:out value="${result.rcverNm}"/><c:if test="${result.rcverCnt ne '0'}">&nbsp;외&nbsp; ${result.rcverCnt}명</c:if>
			</a>
			</span>
		</td>
		<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_confirm('${result.noteId}'); return false;" title="수신상태조회 새창으로">
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

$(function() {
	$("#checkAll").on("click", function(){
		if( $(this).is(":checked") ){
			$("#tblData input[name=uniqIds]").prop("checked", true);
		}else{
			$("#tblData input[name=uniqIds]").prop("checked", false); 
		}
	});
});

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/listNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/listNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(noteId, noteTrnsmitId){
    var varForm = document.getElementById("noteTrnsmitVO");
    varForm.noteId.value = noteId;
    varForm.noteTrnsmitId.value = noteTrnsmitId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nts/detailNoteTrnsmit.do";
    varForm.submit();
}

/* ********************************************************
* 수신자 목록 팝업
******************************************************** */
function fn_aram_confirm(noteId){
	var width = 900;
	var height = 500;
	var left = (screen.width-width)/2;
	var top = (screen.height-height)/3;
	var url = "/uss/ion/nts/confirmNoteTrnsmit.do?noteId=" + noteId;
	var name = "";

	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus()}
}

/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_aram_deleteList(){
	if( $("#tblData input[name=uniqIds]:checked").length == 0) {
		alert("선택한 항목이 없습니다.");
		return false;
	}

    var varForm = document.getElementById("noteTrnsmitVO");
	if(confirm("선택된 보낸쪽지함을 삭제 하시겠습니까?")){
		vFrom.action = "/uss/ion/nts/deleteListNoteTrnsmit.do";
		vFrom.submit();
	}
}

</script>

</body>
</html>