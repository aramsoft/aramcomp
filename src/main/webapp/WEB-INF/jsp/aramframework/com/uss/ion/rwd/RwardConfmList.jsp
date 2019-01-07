<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="aramframework.com.utl.fcc.service.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : RwardConfmList.jsp
 * @Description : 포상승인 목록
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
	<h2>포상승인 목록</h2> 
</div>

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
</div>

<form:form commandName="rwardManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="rwardId">
<input type="hidden" name="infrmlSanctnId">

<table class="table-register" summary="포상승인관리 검색조건">
<caption>포상승인관리 검색조건</caption>
  	<tr>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		<label for="searchKeyword">포상구분</label>
    	</th>
    	<td width="30%">
        	<form:select path="searchKeyword" title="포상구분">
                <form:option value="" label="전체"/>
                <form:options items="${COM055_rward}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		<label for="searchFromDate">포상일자</label>
    	</th>
    	<td width="30%">
      		<form:hidden path="searchFromDate" />
	    	<c:if test="${!empty rwardManageVO.searchFromDate}">
 				<c:set var="searchFromDateVal" value="${fn:substring(rwardManageVO.searchFromDate, 0,4)}-${fn:substring(rwardManageVO.searchFromDate, 4,6)}-${fn:substring(rwardManageVO.searchFromDate, 6,8)}"/>
      		</c:if>
      		<input name="searchFromDateView" id="searchFromDateView" type="text" size="10" title="포상 시작일자" value="${searchFromDateVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchFromDate, document.forms[0].searchFromDateView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		~
      		<form:hidden path="searchToDate" />
	    	<c:if test="${!empty rwardManageVO.searchToDate}">
 				<c:set var="searchToDateVal" value="${fn:substring(rwardManageVO.searchToDate, 0,4)}-${fn:substring(rwardManageVO.searchToDate, 4,6)}-${fn:substring(rwardManageVO.searchToDate, 6,8)}"/>
      		</c:if>
      		<input name="searchToDateView" id="searchFromDateView" type="text" size="10" title="포상 시작일자" value="${searchToDateVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchToDate, document.forms[0].searchToDateView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="searchNm">포상자</label>
     	</th>
    	<td>
    		<form:input path="searchNm" size="20" maxlength="100" title="포상자" /> 
    	</td>
    	<th>
     		<span class="norequired_icon"></span>
    		<label for="searchConfmAt">진행구분</label>
    	</th>
    	<td>
        	<form:select path="searchConfmAt" title="진행구분">
                <form:option value="" label="전체"/>
                <form:option value="A" label="신청중"/>
                <form:option value="C" label="승인"/>
                <form:option value="R" label="반려"/>
      		</form:select>
    	</td>
  	</tr>
</table>

<form:hidden path="searchCondition" value="1" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="포상승인관리 목록">
<caption>포상승인관리 목록</caption>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="10%">포상구분</th>
		<th scope="col"            >포상명</th>
		<th scope="col" width="10%">포상일자</th>
		<th scope="col" width="10%">포상자명</th>
		<th scope="col" width="12%">소속</th>
		<th scope="col" width="10%">진행구분</th>
		<th scope="col" width="12%">승인처리</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="searchVO" value="${rwardManageVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.rwardCdNm    }"/></td>
		<td class="lt_text3"><c:out value="${result.rwardNm      }"/></td>
		<td class="lt_text3">
        	<c:out value='${fn:substring(result.rwardDe, 0,4)}-${fn:substring(result.rwardDe, 4,6)}-${fn:substring(result.rwardDe, 6,8)}'/>
		</td>
		<td class="lt_text3"><c:out value="${result.rwardManNm   }"/></td>
		<td class="lt_text3"><c:out value="${result.orgnztNm}"/></td>
		<td class="lt_text3">
	         <c:if test="${result.confmAt eq 'A'}">신청중</c:if>
	         <c:if test="${result.confmAt eq 'C'}">승인</c:if>
	         <c:if test="${result.confmAt eq 'R'}">반려</c:if>
		</td>
		<td class="lt_textL">
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_confirm('${result.rwardId}','${result.infrmlSanctnId}'); return false;">
				<c:if test="${result.confmAt eq 'A'}">승인처리 </c:if>
				<c:if test="${result.confmAt ne 'A'}">상세보기 </c:if>
			</a>
			</span>
	    </td>
	</tr>
</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("rwardManageVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/listRwardConfm.do";
	varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
 /*설명 : 목록 조회 */
function fn_aram_search(){
	var varForm = document.getElementById("rwardManageVO");
	if(varForm.searchFromDate.value != ""){
	    if(varForm.searchFromDate.value> varForm.searchToDate.value){
	        alert("포상일자 검색조건의 시작일자가 종료일자보다  늦습니다. 포상일자를 확인하세요.");
	        return;
		}
	}else varForm.searchToDate.value = "";

	varForm.pageIndex.value = "1";
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/listRwardConfm.do";
	varForm.submit();
}

/* ********************************************************
 * 승인처리회면 호출함수
 ******************************************************** */
function fn_aram_confirm(rwardId, infrmlSanctnId){
	var varForm = document.getElementById("rwardManageVO");
	varForm.rwardId.value = rwardId;
	varForm.infrmlSanctnId.value = infrmlSanctnId;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/editRwardConfm.do";
	varForm.submit();
}

</script>
