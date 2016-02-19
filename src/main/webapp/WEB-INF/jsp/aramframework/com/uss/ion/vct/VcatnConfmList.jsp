<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="aramframework.com.utl.fcc.service.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : VcatnConfmList.jsp
 * @Description : 휴가 승인 목록
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
	<h2>휴가 승인 목록</h2>
</div>

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
</div>

<form:form commandName="vcatnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="rwardId">
<input type="hidden" name="applcntId">
<input type="hidden" name="vcatnSe">
<input type="hidden" name="bgnde">
<input type="hidden" name="endde">
<input type="hidden" name="infrmlSanctnId">

<table class="table-register" summary="휴가승인관리 검색조건">
<caption>휴가승인관리 검색조건</caption>
  	<tr>
    	<th width="20%" scope="row">
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

    	<th width="20%" scope="row">
    		<span class="norequired_icon"></span>
    		<label for="searchYear">휴가일자</label>
    	</th>          
    	<td width="30%">
        	<form:select path="searchYear" title="년도">
                <form:option value="" label="전체"/>
                <form:options items="${yearList}" />
      		</form:select>년
        	<form:select path="searchMonth" title="월">
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
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="searchNm">신청자</label>
    	</th>          
    	<td colspan="3">
    		<form:input path="searchNm" size="20" maxlength="100" title="신청자" /> 
    	</td>
  	</tr> 
</table>

<form:hidden path="searchVO.searchCondition" value="1" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="휴가승인관리 목록으로 순번 휴가구분 신청자 소속 시작일 종료일 진행구분 승인처리로 구성">
<caption>휴가승인관리 목록</caption>
<thead>
	<tr>  
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="10%">휴가구분</th>
		<th scope="col" width="12%">신청자</th>
		<th scope="col" 		   >소속</th>
		<th scope="col" width="12%">시작일</th>
		<th scope="col" width="12%">종료일</th>
		<th scope="col" width="10%">진행구분</th>
		<th scope="col" width="15%">승인처리</th>
	</tr>
</thead>     
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<c:set var="startIndex" value="${(vcatnManageVO.searchVO.pageIndex-1) * vcatnManageVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${vcatnManageVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.vcatnSeNm  }"/></td>
		<td class="lt_text3"><c:out value="${result.applcntNm  }"/></td>
		<td class="lt_text3"><c:out value="${result.orgnztNm   }"/></td>
		<td class="lt_text3">
        	<c:out value='${fn:substring(result.bgnde, 0,4)}-${fn:substring(result.bgnde, 4,6)}-${fn:substring(result.bgnde, 6,8)}'/>
		</td>
		<td class="lt_text3">
        	<c:out value='${fn:substring(result.endde, 0,4)}-${fn:substring(result.endde, 4,6)}-${fn:substring(result.endde, 6,8)}'/>
		</td>
		<td class="lt_text3">
	         <c:if test="${result.confmAt eq 'A'}">신청</c:if>
	         <c:if test="${result.confmAt eq 'C'}">승인</c:if>
	         <c:if test="${result.confmAt eq 'R'}">반려</c:if>
		</td>
		<td class="lt_text3">
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_confirm('<c:out value="${result.applcntId}"/>','<c:out value="${result.vcatnSe}"/>','<c:out value="${result.bgnde}"/>','<c:out value="${result.endde}"/>','<c:out value="${result.infrmlSanctnId  }"/>'); return false;">
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

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("vcatnManageVO");
	varForm["searchVO.pageIndex"].value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/listVcatnConfm.do";
	varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
function fn_aram_search(){
    var varForm = document.getElementById("vcatnManageVO");

	if(varForm.searchMonth.value !=""){
		if(varForm.searchYear.value ==""){
			alert("전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요");
			return;
		} 
	}
	 
	varForm["searchVO.pageIndex"].value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/listVcatnConfm.do";
	varForm.submit();
}

/* ********************************************************
 * 승인처리화면 호출함수
 ******************************************************** */
function fn_aram_confirm(applcntId,vcatnSe,bgnde,endde,infrmlSanctnId){
    var varForm = document.getElementById("vcatnManageVO");
	varForm.applcntId.value  = applcntId;
	varForm.vcatnSe.value    = vcatnSe;
	varForm.bgnde.value      = bgnde;
	varForm.endde.value      = endde;
	varForm.infrmlSanctnId.value = infrmlSanctnId;
	varForm.action           = "${pageContext.request.contextPath}/uss/ion/vct/editVcatnConfm.do";
	varForm.submit();
}

</script>
