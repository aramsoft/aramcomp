<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ProcessMonList.jsp
  * @Description : 프로세스모니터링 목록
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
	<h2>프로세스모니터링 목록</h2> 
</div>

<form:form commandName="processMonVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="processId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="PROCS_NM" label="프로세스명" />			   
	   		<form:option value="PROCS_STTUS" label="상태" />			   
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

<table class="table-list" summary="이 표는 프로세스모니터링 대상 정보를 제공하며, 순번, 프로세스명, 상태, 관리자명, 관리자이메일, 생성일시로 구성되어 있습니다 .">
<caption>프로세스모니터링 목록</caption>
<thead>
	<tr>
		<th scope="col" width="7%" >순번</th>					
		<th scope="col" width="15%">프로세스명</th>					
		<th scope="col" width="10%">상태</th>						
		<th scope="col" width="15%">관리자명</th>
		<th scope="col"            >관리자이메일</th>
		<th scope="col" width="20%">생성일시</th>										
	</tr>
</thead>    
<tbody>					  										
	<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
    <c:set var="searchVO" value="${processMonVO.searchVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.processId}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.processNm}"/></td>						
		<td class="lt_text3">${result.procsSttus}</td>
		<td class="lt_text3">${result.mngrNm}</td>														
		<td class="lt_text3">${result.mngrEmailAddr}</td>
		<td class="lt_text3">${result.creatDt}</td>												
	</tr>   
	</c:forEach>									
</tbody>  
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="/js/aramframework/com/cmm/utl/CmmUtl.js"></script>		
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("processMonVO");
    varForm.searchCondition.focus();
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("processMonVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/listProcessMon.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("processMonVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/listProcessMon.do";
    varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(processId) {
    var varForm = document.getElementById("processMonVO");
    varForm.processId.value = processId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/detailProcessMon.do";
    varForm.submit();	
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){	
    var varForm = document.getElementById("processMonVO");
    varForm.processId.value = '';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/registProcessMon.do";
    varForm.submit();
}

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){
    var varForm = document.getElementById("processMonVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/prm/listProcessMonLog.do";
    varForm.submit();  
}

</script>
