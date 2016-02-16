<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ProgramChangeProcessList.jsp
  * @Description : 프로그램변경요청처리 목록
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
	<h2>프로그램변경요청처리 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="progrmManageDtlVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="progrmFileNm"/>
<input type="hidden" name="rqestNo" value="0"/>

<div id="search_area">
	<div class="button_area">
        <span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
        <label for="searchCondition">검색조건&nbsp;</label>
       	<select name="searchCondition" onchange="javascript:fncSearchSpan(this.value);" title="검색조건">
           	<option value="1">전체    </option>
           	<option value="2">처리상태    </option>
           	<option value="3">요청일자  </option>
           	<option value="4">요청자  </option>
       	</select>
   		<span id="searchSpan">&nbsp;&nbsp;전체조회&nbsp;<input name='searchKeyword' type='hidden' size='10' value='%'  maxlength='20' title="검색조건값"></span>
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="프로그램변경요청처리 목록화면으로 요청번호,프로그램파일명,처리상태,요청제목,요청자,요청일자로 구성.">
<caption>프로그램변경요청처리 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="10%">요청번호</th>
	    <th scope="col" width="15%">프로그램파일명</th>
	    <th scope="col" width="10%">처리상태</th>
	    <th scope="col"            >요청제목</th>
	    <th scope="col" width="15%">요청자</th>
	    <th scope="col" width="15%">요청일자</th>
  	</tr>
</thead>
<tbody>
   	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(progrmManageDtlVO.pageIndex-1) * progrmManageDtlVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.progrmFileNm}"/>','<c:out value="${result.rqestNo}"/>'); return false;">
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${progrmManageDtlVO.totalRecordCount - index + 1}"/>

	    <td class="lt_text3"><c:out value="${result.rqestNo}"/></td>
    	<td class="lt_text">c:out value="${result.progrmFileNm}"/></td>
    	<td class="lt_text3">
      		<c:if test="${empty result.processSttus}">N/A</c:if>
      		<c:if test="${result.processSttus == 'A'}">신청중</c:if>
      		<c:if test="${result.processSttus == 'P'}">진행중</c:if>
      		<c:if test="${result.processSttus == 'R'}">반려</c:if>
      		<c:if test="${result.processSttus == 'C'}">처리완료</c:if>
    	</td>
    	<td class="lt_text3"><c:out value="${result.rqestSj}"/></td>
    	<td class="lt_text3"><c:out value="${result.rqestPersonId}"/></td>
    	<td class="lt_text3">
	    	<c:out value="${fn:substring(result.rqestDe, 0, 4)}-${fn:substring(result.rqestDe, 4, 6)}-${fn:substring(result.rqestDe, 6, 8)}"/>
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sts/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeProcess.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeProcess.do";
    varForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function fn_aram_detail(progrmFileNm, rqestNo) {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.progrmFileNm.value = progrmFileNm;
    varForm.rqestNo.value = rqestNo;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/editProgramChangeProcess.do";
    varForm.submit();
}

/* ********************************************************
 * 검색조건 처리 함수
 ******************************************************** */
function fncSearchSpan(vSearch) {
	searchSpan.innerHTML = "&nbsp;&nbsp;"
	if(vSearch == "1"){
		searchSpan.innerHTML += "전체조회&nbsp; <input name='searchKeyword' type='hidden' size='10' value='%'  maxlength='20'>";
	}else if(vSearch == "2"){
		searchSpan.innerHTML += "처리상태&nbsp; <select name='searchKeyword'> <option value=A>신청중 <//option> <option value=P>진행중 <//option> <option value=R>반려      <//option> <option value=C>처리완료 <//option> <//select>";
	}else if(vSearch == "3"){
        searchSpan.innerHTML += "요청일자&nbsp;"
            + "<input name='searchKeywordFrom' type='text' size='8' value=''  maxlength='8'>"
            + " <a href=\"#\" onClick=\"javascript:fn_aram_NormalCalendar(document.forms[0].searchKeywordFrom,''); return false;\" style=\"selector-dummy:expression(this.hideFocus=false);\"><img src=\"${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif\" name=\"cal_img1\" id=\"cal_img1\" border=\"0\" title=\"달력\" alt=\"달력\"><//a> ~ "
            + "<input name='searchKeywordTo'   type='text' size='8' value=''  maxlength='8'>"
            + " <a href=\"#\" onClick=\"javascript:fn_aram_NormalCalendar(document.forms[0].searchKeywordTo,''); return false;\" style=\"selector-dummy:expression(this.hideFocus=false);\"><img src=\"${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif\" name=\"cal_img2\" id=\"cal_img2\" border=\"0\" title=\"달력\" alt=\"달력\"><//a>";
	}else if(vSearch == "4"){
        searchSpan.innerHTML += "요청자  &nbsp;<input name='searchKeyword' type='text' size='10' value=''  maxlength='20'>";
	}
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%EA%B4%80%EB%A6%AC";	
	window.open(url, "도움말");
}

</script>
