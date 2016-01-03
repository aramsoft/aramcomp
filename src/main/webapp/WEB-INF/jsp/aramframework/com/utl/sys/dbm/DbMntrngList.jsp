<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : DbMntrngList.jsp
 * @Description : DB서비스모니터링 목록
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
	<h2>DB서비스모니터링 목록</h2> 
</div>

<form:form commandName="dbMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="dataSourcNm"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="DATA_SOURC_NM" label="데이타소스명" />			   
	   		<form:option value="SERVER_NM" label="서버명" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
	   		<form:option value="CODE_NM" label="상태" />			   
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

<table class="table-list" summary="등록된 DB서비스모니터링에 대한 목록을 제공합니다.">
<thead>
    <tr>
        <th scope="col" width="7%" >No.</th>
        <th scope="col" width="15%">데이타소스명</th>
        <th scope="col" width="10%">서버명</th>
        <th scope="col" width="10%">DBMS종류</th>
        <th scope="col" width="10%">관리자명</th>
        <th scope="col"            >관리자이메일주소</th>
        <th scope="col" width="10%">상태</th>
        <th scope="col" width="20%">모니터링시각</th>   
    </tr>
</thead>    
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr> 
        <td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
    </tr>                                              
    </c:if>
    
    <%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(dbMntrngVO.pageIndex-1) * dbMntrngVO.recordPerPage}"/>
    <c:forEach items="${resultList}" var="result" varStatus="status">
    <tr class="link" onclick="javascript:fn_aram_detail('${result.dataSourcNm}'); return false;">
    
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${dbMntrngVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

  		<td class="lt_text3"><c:out value="${result.dataSourcNm}"/></td>
        <td class="lt_text6">${result.serverNm}</td>
        <td class="lt_text3">${result.dbmsKindNm}</td>
        <td class="lt_text6">${result.mngrNm}</td>
        <td class="lt_text6">${result.mngrEmailAddr}</td>
        <td class="lt_text3">${result.mntrngSttusNm}</td>
        <td class="lt_text3">${result.creatDt}</td>
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
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("dbMntrngVO");
    varForm.pageIndex.value = pageNo; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrng.do";
    varForm.submit();  
}
 
/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search() {
    var varForm = document.getElementById("dbMntrngVO");
    if (varForm.searchKeyword.value != "") {
        if (varForm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            return;
        }
    }
    varForm.pageIndex.value = '1'; 
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrng.do";
    varForm.submit();  
}
 
/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(dataSourcNm) {
    var varForm = document.getElementById("dbMntrngVO");
    varForm.dataSourcNm.value = dataSourcNm;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/detailDbMntrng.do";
    varForm.submit();          
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("dbMntrngVO");
    varForm.dataSourcNm.value = "";
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/registDbMntrng.do";
    varForm.submit();  
}   

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){
    var varForm = document.getElementById("dbMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrngLog.do";
    varForm.submit();  
}

</script>
