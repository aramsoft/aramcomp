<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ProgramChangeRequstList.jsp
  * @Description : 프로그램변경요청 목록
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
	<h2>프로그램변경요청 목록</h2> 
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
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
       	<label for="searchKeyword">프로그램파일명</label>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="프로그램변경요청  조회화면으로 요청번호,프로그램파일명,요청제목,요청자,요청일자,처리여부로 구성.">
<caption>프로그램변경요청 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="10%">요청번호</th>
	    <th scope="col" width="15%">프로그램파일명</th>
	    <th scope="col"            >요청제목</th>
	    <th scope="col" width="15%">요청자</th>
	    <th scope="col" width="15%">요청일자</th>
	    <th scope="col" width="10%">처리여부</th>
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
	    <td class="lt_text"><c:out value="${result.progrmFileNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.rqestSj}"/></td>
	    <td class="lt_text3"><c:out value="${result.rqestPersonId}"/></td>
	    <td class="lt_text3">
	    	<c:out value="${fn:substring(result.rqestDe, 0, 4)}-${fn:substring(result.rqestDe, 4, 6)}-${fn:substring(result.rqestDe, 6, 8)}"/>
	    </td>
	    <td class="lt_text3">
	      	<c:if test="${empty result.processSttus}">N/A</c:if>
	      	<c:if test="${result.processSttus == 'A'}">신청중</c:if>
	      	<c:if test="${result.processSttus == 'P'}">진행중</c:if>
	      	<c:if test="${result.processSttus == 'R'}">반려</c:if>
	      	<c:if test="${result.processSttus == 'C'}">처리완료</c:if>
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

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeRequst.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeRequst.do";
    varForm.submit();
}

/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function fn_aram_detail(progrmFileNm, rqestNo) {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.progrmFileNm.value  = progrmFileNm;
    varForm.rqestNo.value = rqestNo;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/editProgramChangeRequst.do";
    varForm.submit();
}

/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function fn_aram_regist() {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.progrmFileNm.value  = "";
    varForm.rqestNo.value = 0;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/registProgramChangeRequst.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("프로그램관리");	
	window.open(url, "도움말");
}

</script>
