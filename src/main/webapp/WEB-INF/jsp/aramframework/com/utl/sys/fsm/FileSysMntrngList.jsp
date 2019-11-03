<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : FileSysMntrngList.jsp
 * @Description : 파일시스템모니터링 목록
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
	<h2>파일시스템모니터링 목록</h2> 
</div>

<form:form commandName="fileSysMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="fileSysId"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="FILE_SYS_NM" label="파일시스템명" />			   
	   		<form:option value="FILE_SYS_MANAGE_NM" label="파일시스템관리명" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
	   		<form:option value="MNTRNG_STTUS" label="상태" />			   
   		</form:select>
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

<table class="table-list" summary="이 표는 파일시스템모니터링 대상 정보를 제공하며, 파일시스템명, 파일시스템관리명, 크기, 임계치, 사용량, 상태, 관리자명 정보로 구성되어 있습니다 .">
<caption>파일시스템모니터링대상 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >파일시스템명</th>
	    <th scope="col" width="20%">파일시스템관리명</th>
	    <th scope="col" width="8%" >크기</th>
	    <th scope="col" width="10%">임계치</th>
	    <th scope="col" width="10%">사용량</th>
	    <th scope="col" width="10%">상태</th>
	    <th scope="col" width="10%">관리자명</th>
  	</tr>
</thead>    
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="8"><spring:message code="common.nodata.msg" /></td>  
	</tr>		 
	</c:if>
	
    <c:set var="searchVO" value="${fileSysMntrngVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.fileSysId}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.fileSysNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.fileSysManageNm}"/></td>
		<td class="lt_text3"><c:out value="${result.fileSysMg}"/>G</td>
		<td class="lt_text3"><c:out value="${result.fileSysThrhldRt}"/>%(<c:out value="${result.fileSysThrhld}"/>G)</td>
		<td class="lt_text3"><c:out value="${result.fileSysUsgRt}"/>%(<c:out value="${result.fileSysUsgQty}"/>G)</td>
		<td class="lt_text3"><c:out value="${result.mntrngSttus}"/></td>
	    <td class="lt_text3"><c:out value="${result.mngrNm}"/></td>
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

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("fileSysMntrngVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("fileSysMntrngVO");
    varForm.pageIndex.value = 1; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(fileSysId) {
    var varForm = document.getElementById("fileSysMntrngVO");
    varForm.fileSysId.value = fileSysId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/detailFileSysMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 등록화면 
 ******************************************************** */
function fn_aram_regist(){	
    var varForm = document.getElementById("fileSysMntrngVO");
    varForm.fileSysId.value ="";
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/registFileSysMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){	
    var varForm = document.getElementById("fileSysMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/fsm/listFileSysMntrngLog.do";
    varForm.submit();
}

</script>
