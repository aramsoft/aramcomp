<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : FileSysMntrngLogList.jsp
 * @Description : 파일시스템모니터링 로그 목록
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
	<h2>파일시스템모니터링 로그 목록</h2> 
</div>

<form:form commandName="fileSysMntrngLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="fileSysId">
<input type="hidden" name="logId"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_fileSysMntrng(); return false;"><spring:message code="button.list" /></a></span>
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
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="FILE_SYS_NM" label="파일시스템명" />			   
	   		<form:option value="FILE_SYS_MANAGE_NM" label="파일시스템관리명" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
	   		<form:option value="MNTRNG_STTUS" label="상태" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="25" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list"  summary="이 표는 파일시스템모니터링 로그 정보를 제공하며, 파일시스템명, 파일시스템관리명, 크기, 임계치, 사용량, 상태, 생성일시 정보로 구성되어 있습니다 .">
<thead>
  	<tr>
	    <th scope="col" width="10%">번호</th>
	    <th scope="col" width="17%">파일시스템명</th>
	    <th scope="col" width="17%">파일시스템관리명</th>
	    <th scope="col" width="8%" >크기</th>
	    <th scope="col" width="11%">임계치</th>
	    <th scope="col" width="11%">사용량</th>
	    <th scope="col" width="10%">상태</th>
	    <th scope="col" width="16%">생성일시</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
    <c:set var="searchVO" value="${fileSysMntrngLogVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.fileSysId}"/>', '<c:out value="${result.logId}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.fileSysNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.fileSysManageNm}"/></td>
		<td class="lt_text3"><c:out value="${result.fileSysMg}"/>G</td>
		<td class="lt_text3"><c:out value="${result.fileSysThrhldRt}"/>%(<c:out value="${result.fileSysThrhld}"/>G)</td>
		<td class="lt_text3"><c:out value="${result.fileSysUsgRt}"/>%(<c:out value="${result.fileSysUsgQty}"/>G)</td>
		<td class="lt_text3"><c:out value="${result.mntrngSttus}"/></td>
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
    var varForm = document.getElementById("fileSysMntrngLogVO");
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
    var varForm = document.getElementById("fileSysMntrngLogVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrngLog.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("fileSysMntrngLogVO");

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

	if(bgnDe.length == 8){
		bgnDe = bgnDe.substring(0,4) + "-" + bgnDe.substring(4,6) + "-" + bgnDe.substring(6,8);
		varForm.searchBgnDe.value = bgnDe;
	}

	if(endDe.length == 8){
		endDe = endDe.substring(0,4) + "-" + endDe.substring(4,6) + "-" + endDe.substring(6,8);
		varForm.searchEndDe.value = endDe;
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
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrngLog.do";
	varForm.submit();
}

function fn_aram_detail(fileSysId, logId) {
    var varForm = document.getElementById("fileSysMntrngLogVO");
    varForm.fileSysId.value = fileSysId;
    varForm.logId.value = logId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/detailFileSysMntrngLog.do";
    varForm.submit();
}

function fn_aram_list_fileSysMntrng(){
    var varForm = document.getElementById("fileSysMntrngLogVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrng.do";
    varForm.submit();
}

</script>
