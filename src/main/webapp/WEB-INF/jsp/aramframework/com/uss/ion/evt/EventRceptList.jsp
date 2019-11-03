<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EventRceptList.jsp
 * @Description : 행사접수신청 목록
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
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>
<DIV id="main">

<div class="content_title">
	<h2>행사접수신청 목록</h2>
</div>

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
</div>

<form:form commandName="eventAtdrnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="eventId" type="hidden" value="">
<input name="applcntId" type="hidden" value="">
<input type="hidden" name="popup">

<table class="table-register"  summary="행사접수관리 검색조건">
<caption>행사접수관리 검색조건</caption>
  	<tr>
    	<th width="20%"  scope="row">
    		<span class="norequired_icon"></span>
    		<label for="searchSe">행사구분</label>
    	</th>          
    	<td width="30%">
        	<form:select path="searchSe" title="행사구분">
                <form:option value="" label="전체"/>
                <form:options items="${COM053_eventSe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
    	<th width="20%"  scope="row">
    		<span class="norequired_icon"></span>
    		<label for="searchYear">행사년월</label>
    	</th>          
    	<td width="30%">
        	<form:select path="searchYear" title="행사구분">
                <form:option value="" label="전체"/>
                <form:options items="${yearList}" />
      		</form:select>년
        	<form:select path="searchMonth" title="행사월">
                <form:option value="" label="전체"/>
                <form:option value="01" label="01"/>
                <form:option value="02" label="02"/>
                <form:option value="03" label="03"/>
                <form:option value="04" label="04"/>
                <form:option value="05" label="05"/>
                <form:option value="06" label="06"/>
                <form:option value="07" label="07"/>
                <form:option value="08" label="08"/>
                <form:option value="09" label="09"/>
                <form:option value="10" label="10"/>
                <form:option value="11" label="11"/>
                <form:option value="12" label="12"/>
      		</form:select>월
    	</td>
  	</tr> 
  	<tr>
	    <th width="20%"  scope="row">
	    	<span class="norequired_icon"></span>
	    	<label for="searchNm">행사명</label>
	    </th>          
	    <td width="30%">
    		<form:input path="searchNm" size="20" maxlength="100" title="행사명" /> 
	    </td>
	    <th width="20%"  scope="row">
	    	<span class="norequired_icon"></span>
	    	<label for="searchConfmAt">진행구분</label>
	    </th>          
	    <td width="30%">
        	<form:select path="searchConfmAt" title="행사구분">
                <form:option value="" label="전체"/>
                <form:option value="NON" label="신청전"/>
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

<table class="table-list" summary="행사접수관리 목록으로 행사명, 행사장소, 행사구분, 행사일자, 기간, 참여/정원, 행사접수기간, 신청(상세)로 구성.">
<caption>행사접수관리 목록</caption>
<thead>
	<tr>  
		<th scope="col" width="7%" >No.</th>
		<th scope="col"            >행사명</th>
		<th scope="col" width="12%">행사장소</th>
		<th scope="col" width="10%">행사구분</th>
		<th scope="col" width="12%">행사일자</th>
		<th scope="col" width="8%">기간</th>
		<th scope="col" width="10%">참여/정원</th>
		<th scope="col" width="12%">행사접수기간</th>
		<th scope="col" width="15%">신청(상세)</th>
	</tr>
</thead>     
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<c:set var="searchVO" value="${eventAtdrnVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_popup_eventManage('${result.eventId}'); return false;">
				<c:out value="${result.eventNm}"/>
			</a>
			</span>
		</td>
		<td class="lt_text3"><c:out value="${result.eventPlace}"/></td>
		<td class="lt_text3"><c:out value="${result.eventSeNm}"/></td>
		<td class="lt_textL">
			<c:out value="${fn:substring(result.eventBeginDe, 0,4)}-${fn:substring(result.eventBeginDe, 4,6)}-${fn:substring(result.eventBeginDe, 6,8)}" escapeXml="false" />
			 ~ <br>
			<c:out value="${fn:substring(result.eventEndDe, 0,4)}-${fn:substring(result.eventEndDe, 4,6)}-${fn:substring(result.eventEndDe, 6,8)}" escapeXml="false" />
		</td>
		<td class="lt_text3"><c:out value="${result.eventDayCount}"/>일간</td>
		<td class="lt_text3"><c:out value="${result.eventAtdrnCount}"/>/<c:out value="${result.garden}"/></td>
		<td class="lt_textL">
			<c:out value="${fn:substring(result.rceptBeginDe, 0,4)}-${fn:substring(result.rceptBeginDe, 4,6)}-${fn:substring(result.rceptBeginDe, 6,8)}" escapeXml="false" />
			 ~ <br>
			<c:out value="${fn:substring(result.rceptEndDe, 0,4)}-${fn:substring(result.rceptEndDe, 4,6)}-${fn:substring(result.rceptEndDe, 6,8)}" escapeXml="false" />
		</td>
		<td class="lt_text3">
			<c:if test="${empty result.confmAt}"> 
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_regist('${result.eventId}'); return false;">
				신청
			</a>
			</span>
	       	</c:if>
	       	<c:if test="${!empty result.confmAt}">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('${result.eventId}','${result.applcntId }'); return false;">
				<c:if test="${result.confmAt eq 'A'}">신청중</c:if>
				<c:if test="${result.confmAt eq 'C'}">승인</c:if>
				<c:if test="${result.confmAt eq 'R'}">반려</c:if>
			</a>
			</span>
	       	</c:if>
		</td>
	</tr>    
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("eventAtdrnVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/listEventRcrpt.do";
	varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
function fn_aram_search(){
	var varForm = document.getElementById("eventAtdrnVO");
	if(varForm.searchMonth.value !=""){
		if(varForm.searchYear.value ==""){
			alert("전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요");
			return;
		} 
	}
	varForm.pageIndex.value = 1;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/listEventRcrpt.do";
	varForm.submit();
}

/* ********************************************************
 * 상세화면 호출함수
 ******************************************************** */
function fn_aram_detail(eventId, applcntId){
	var varForm = document.getElementById("eventAtdrnVO");
	varForm.eventId.value    = eventId;
	varForm.applcntId.value  = applcntId;
	varForm.action           = "${pageContext.request.contextPath}/uss/ion/evt/detailEventRcrpt.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 화면 호출 함수 
 ******************************************************** */
function fn_aram_regist(eventId){
	var varForm = document.getElementById("eventAtdrnVO");
	varForm.eventId.value  = eventId;
	varForm.action         = "${pageContext.request.contextPath}/uss/ion/evt/registEventRcrpt.do";
	varForm.submit();
}

/* ********************************************************
 * 행사 상세화면 팝업 호출함수
 ******************************************************** */
function fn_aram_popup_eventManage(eventId){
	var varForm = document.getElementById("eventAtdrnVO");
	var openParam            = "left=10, top=0, width=750, height=600";
	varForm.eventId.value    = eventId;
	varForm.popup.value      = "true";
	var myWin = window.open("about:blank","EventReqstDetailPop",openParam);
	
	varForm.method = "post";
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/detailEventReqst.do";
	varForm.target = "EventReqstDetailPop";
	varForm.submit();
}

</script>
