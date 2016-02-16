<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name  : HttpMonLogList.jsp
 * @Description : HTTP모니터링 로그 목록
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
	<h2>HTTP모니터링 로그 목록</h2>
</div>

<form:form commandName="httpMntrngLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="webKind">
<input type="hidden" name="logId"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_httpMon(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area">
		<form:input path="searchBgnDe" size="10" maxlength="10" title="조회시작일자 입력" />
		<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchBgnDe); return false;">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" style="border:0px;vertical-align: bottom" alt="달력창팝업버튼이미지">
		</a>
		<select name="searchBgnHour" class="select" title="조회시작 시 선택">
			<c:forEach var="bgnHour" items="${searchBgnHour}" varStatus="status">
     			<option value="<c:out value="${bgnHour.code}"/>"><c:out value="${bgnHour.codeNm}"/></option>
     		</c:forEach>
		</select>
		~
		<form:input path="searchEndDe" size="10" maxlength="10" title="조회종료일자 입력" />
 		<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchEndDe); return false;">
 			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" style="border:0px;vertical-align: bottom" alt="달력창팝업버튼이미지">
 		</a>
		<select name="searchEndHour" class="select" title="조회종료 시 선택">
			<c:forEach var="endHour" items="${searchEndHour}" varStatus="status">
        		<option value="<c:out value="${endHour.code}"/>"><c:out value="${endHour.codeNm}"/></option>
        	</c:forEach>
    	</select>
  		<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="WEBSVC_KND" label="웹서비스종류" />			   
	   		<form:option value="HTTP_STTUS" label="상태" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="25" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="이 표는 HTTP모니터링 로그 정보를 제공하며, 순번, 로그ID, 웹서비스종류, 시스템URL, 상태, 생성일시 정보로 구성되어 있습니다 .">
<thead>
	<tr>
  		<th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">로그ID</th>
	    <th scope="col" width="15%">웹서비스종류</th>
	    <th scope="col"            >시스템URL</th>
	    <th scope="col" width="10%">상태</th>
	    <th scope="col" width="20%">생성일시</th>
	</tr>
</thead>
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
 		<tr>
    		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
  		</tr>
 	</c:if>
 	
 	<c:set var="startIndex" value="${(httpMntrngLogVO.pageIndex-1) * httpMntrngLogVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.webKind}"/>', '<c:out value="${result.logId}"/>'); return false;">
 	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${httpMntrngLogVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.logId}"/></td>
	    <td class="lt_text3"><c:out value="${result.webKind}"/></td>
		<td class="lt_text3"><c:out value="${result.siteUrl}"/></td>
		<td class="lt_text3"><c:out value="${result.httpSttusCd}"/></td>
	    <td class="lt_text3"><c:out value="${result.creatDt}"/></td>
 		</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

window.onload = function() {
    var varForm = document.getElementById("httpMonLogVO");
    varForm.searchBgnHour.value = '<c:out value="${searchVO.searchBgnHour}"/>';
    varForm.searchEndHour.value = '<c:out value="${searchVO.searchEndHour}"/>';
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("httpMntrngLogVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrngLog.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("httpMntrngLogVO");

	var bgnDe = varForm.searchBgnDe.value;
	var endDe = varForm.searchEndDe.value;

	if(bgnDe != ""){
		if(isDate(bgnDe, "검색시작일자") == false) {
	        return;
	    }
	}

	if(endDe != ""){
	    if(isDate(endDe, "검색종료일자") == false) {
	        return;
	    }
	}

	var bgnHour = varForm.searchBgnHour.value;
	var endHour = varForm.searchEndHour.value;

	var bgnDeHour = "";
	var endDeHour = "";
	if(bgnDe != "" && endDe != ""){
		if(bgnHour == ""){
			varForm.searchBgnHour.value = "00";
			bgnHour = "00";
		}
		if(endHour == ""){
			varForm.searchEndHour.value = "00";
			endHour = "00";
		}
		bgnDeHour = bgnDe + bgnHour;
		endDeHour = endDe + endHour;

		if(bgnDeHour> endDeHour){
			alert("검색종료일시가 검색시작일시보다 빠를수 없습니다.");
			return;
		}
	}
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrngLog.do";
	varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(webKind, logId) {
    var varForm = document.getElementById("httpMntrngLogVO");
    varForm.webKind.value = webKind;
    varForm.logId.value = logId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/detailHttpMntrngLog.do";
    varForm.submit();
}

/* ********************************************************
 * 마스터목록조회 함수
 ******************************************************** */
function fn_aram_list_httpMon(){
    location.href = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrng.do";
}

</script>
