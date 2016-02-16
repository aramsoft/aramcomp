<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : BackupOpertList.jsp
 * @Description : 백업작업 목록
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
	<h2>백업작업 목록</h2>
</div>

<form:form commandName="backupOpertVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="backupOpertId" type="hidden" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
 	   	<form:select path="searchVO.searchCondition" class="select" title="검색조건구분">
		   	<form:option value="" label="--선택하세요--" />
		   	<form:option value="BACKUP_OPERT_NM" label="백업작업명" />
		   	<form:option value="BACKUP_ORGINL_DRCTRY" label="백업원본디렉토리" />
	   	</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
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

<table class="table-list" summary="백업작업에 대한 목록을 제공합니다.">
<caption>백업작업관리 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
    	<th scope="col" width="12%">백업작업ID</th>
	    <th scope="col"            >백업작업명</th>
	    <th scope="col" width="18%">백업원본디렉토리</th>
	    <th scope="col" width="18%">백업저장디렉토리</th>
	    <th scope="col" width="10%">실행주기</th>
	    <th scope="col" width="20%">실행스케줄</th>
  	</tr>
</thead>    
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr> 
        <td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
    </tr>                                              
    </c:if>
    
    <%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(backupOpertVO.pageIndex-1) * backupOpertVO.recordPerPage}"/>
    <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
    <tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${resultInfo.backupOpertId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${backupOpertVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

        <td class="lt_text3"><c:out value='${resultInfo.backupOpertId}'/></td>
        <td class="lt_text6">${resultInfo.backupOpertNm}</td>
        <td class="lt_text6">${resultInfo.backupOrginlDrctry}</td>
        <td class="lt_text6">${resultInfo.backupStreDrctry}</td>
        <td class="lt_text3">${resultInfo.executCycleNm}</td>
        <td class="lt_text3">${resultInfo.executSchdul}</td>
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

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("backupOpertVO");
    varForm["searchVO.pageIndex"].value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/sym/sym/bak/listBackupOpert.do";
    varForm.submit();  
}

function fn_aram_search() {
    var varForm = document.getElementById("backupOpertVO");
    if (varForm.searchKeyword.value != "") {
        if (varForm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            return;
        }
    }
    varForm["searchVO.pageIndex"].value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/sym/sym/bak/listBackupOpert.do";
    varForm.submit();  
}

function fn_aram_detail(backupOpertId) {
    var varForm = document.getElementById("backupOpertVO");
    varForm.backupOpertId.value = backupOpertId;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/bak/detailBackupOpert.do";
    varForm.submit();          
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("backupOpertVO");
    varForm.backupOpertId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/sym/bak/registBackupOpert.do";
    varForm.submit();  
}   

</script>
