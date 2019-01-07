<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="aramframework.com.utl.fcc.service.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : CtsnnList.jsp
 * @Description : 경조사 목록
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
	<h2>경조사 목록</h2> 
</div> 

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
</div>

<form:form commandName="ctsnnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="ctsnnId" value="">

<table class="table-register" summary="경조목록 검색조건">
<caption>경조목록 검색조건</caption>
  	<tr>
    	<th width="20%" scope="row">
			<span class="norequired_icon"></span>
    		<label for="searchKeyword">경조구분</label>
    	</th>
    	<td width="30%">
        	<form:select path="searchVO.searchKeyword" title="경조구분">
                <form:option value="" label="전체"/>
                <form:options items="${COM054_ctsnn}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
    	<th width="20%"   scope="row">
 			<span class="norequired_icon"></span>
    		<label for="searchFromDate">발생일자</label>
    	</th>
    	<td width="30%">
      		<form:hidden path="searchFromDate" />
	    	<c:if test="${!empty ctsnnManageVO.searchFromDate}">
 				<c:set var="searchFromDateVal" value="${fn:substring(ctsnnManageVO.searchFromDate, 0,4)}-${fn:substring(ctsnnManageVO.searchFromDate, 4,6)}-${fn:substring(ctsnnManageVO.searchFromDate, 6,8)}"/>
      		</c:if>
      		<input name="searchFromDateView" id="searchFromDateView" type="text" size="10" title="경조신청 시작일자" value="${searchFromDateVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchFromDate, document.forms[0].searchFromDateView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	     	~
      		<form:hidden path="searchToDate" />
	    	<c:if test="${!empty ctsnnManageVO.searchToDate}">
 				<c:set var="searchToDateVal" value="${fn:substring(ctsnnManageVO.searchToDate, 0,4)}-${fn:substring(ctsnnManageVO.searchToDate, 4,6)}-${fn:substring(ctsnnManageVO.searchToDate, 6,8)}"/>
      		</c:if>
      		<input name="searchToDateView" id="searchToDateView" type="text" size="10" title="경조신청 종료일자" value="${searchToDateVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].searchToDate, document.forms[0].searchToDateView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%"   scope="row">
			<span class="norequired_icon"></span>
    		<label for="searchNm">신청자</label>
    	</th>
    	<td width="30%">
    		<form:input path="searchNm" size="20" maxlength="100" title="신청자" /> 
    	</td>
    	<th width="20%"  scope="row">
 			<span class="norequired_icon"></span>
    		<label for="searchConfmAt">진행구분</label>
    	</th>
    	<td width="30%">
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

<div style="margin-top:10px; width:100%"></div>

<table class="table-list" summary="이 표는 경조관리리스트의 정보를 제공하며 순번, 경조구분, 경조명,신청자,소속,발생일자,진행구분,승인일자,승인자로 구성되어 있습니다.">
<caption>경조관리 목록</caption>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="10%">경조구분</th>
		<th scope="col"            >경조명</th>
		<th scope="col" width="10%">신청자</th>
		<th scope="col" width="12%">소속</th>
		<th scope="col" width="10%">발생일자</th>
		<th scope="col" width="10%">진행구분</th>
		<th scope="col" width="10%">승인일자</th>
		<th scope="col" width="10%">승인자</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="searchVO" value="${ctsnnManageVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.ctsnnId}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.ctsnnCdNm  }"/></td>
		<td class="lt_text3"><c:out value="${result.ctsnnNm}"/></td>
		<td class="lt_text3"><c:out value="${result.usNm  }"/></td>
		<td class="lt_text3"><c:out value="${result.orgnztNm   }"/></td>
		<td class="lt_text3">
			<c:out value="${fn:substring(result.occrrDe, 0,4)}-${fn:substring(result.occrrDe, 4,6)}-${fn:substring(result.occrrDe, 6,8)}" escapeXml="false" />
		</td>
		<td class="lt_text3">
	         <c:if test="${result.confmAt eq 'A'}">신청중</c:if>
	         <c:if test="${result.confmAt eq 'C'}">승인</c:if>
	         <c:if test="${result.confmAt eq 'R'}">반려</c:if>
		</td>
		<td class="lt_textL"><c:out value="${result.sanctnDt   }"/></td>
		<td class="lt_textL"><c:out value="${result.sanctnerNm }"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("ctsnnManageVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/listCtsnn.do";
	varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
 /*설명 : 목록 조회 */
function fn_aram_search(){
	var varForm = document.getElementById("ctsnnManageVO");
	if(varForm.searchFromDate.value != ""){
	    if(varForm.searchFromDate.value> varForm.searchToDate.value){
	        alert("경조신청일자 검색조건의 시작일자가 종료일자보다  늦습니다. 경조신청일자를 확인하세요.");
	        return;
		 }
	} 
	else varForm.searchToDate.value = "";
	varForm.pageIndex.value = '1';
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/listCtsnn.do";
	varForm.submit();
}

/* ********************************************************
 * 상세화면 호출함수
 ******************************************************** */
function fn_aram_detail(ctsnnId){
	var varForm = document.getElementById("ctsnnManageVO");
	varForm.ctsnnId.value    = ctsnnId;
	varForm.action           = "${pageContext.request.contextPath}/uss/ion/ctn/detailCtsnn.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 화면 호출 함수
 ******************************************************** */
function fn_aram_regist(){
	var varForm = document.getElementById("ctsnnManageVO");
	varForm.ctsnnId.value    = "";
	varForm.action           = "${pageContext.request.contextPath}/uss/ion/ctn/registCtsnn.do";
	varForm.submit();
}

</script>
