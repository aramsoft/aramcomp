<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : HttpMonList.jsp
  * @Description : HTTP모니터링 목록
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
	<h2>HTTP모니터링 목록</h2>
</div>

<form:form commandName="httpMntrngVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="sysId"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_log(); return false;">로그</a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="MNGR_NM" label="관리자명" />			   
	   		<form:option value="HTTP_STTUS" label="상태" />			   
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

<table class="table-list" summary="이 표는 HTTP서비스모니터링 대상 정보를 제공하며, 웹서비스종료, 시스템URL, 상태, 관리자명, 생성일시 정보로 구성되어 있습니다 .">
<caption>HTTP모니터링 목록</caption>			
<thead>
	<tr>
		<th scope="col" width="10%">순번</th>					  
		<th scope="col" width="15%">웹서비스종류</th>
		<th scope="col" width="20%">시스템URL</th>						
		<th scope="col" width="15%">상태</th>						
		<th scope="col" width="20%">관리자명</th>
		<th scope="col" width="25%">생성일시</th>						
	</tr>
</thead>    
<tbody>					  										
	<!-- 데이터가 없을때 화면에 메세지를 출력해준다 -->	
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<c:set var="startIndex" value="${(httpMntrngVO.pageIndex-1) * httpMntrngVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.sysId}'); return false;">	
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${httpMntrngVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">${result.webKind}</td>
		<td class="lt_text3"><c:out value="${result.siteUrl}"/></td>							
		<td class="lt_text3">${result.httpSttusCd}</td>
		<td class="lt_text3">${result.mngrNm}</td>													
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
    var varForm = document.getElementById("httpMntrngVO");
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
    var varForm = document.getElementById("httpMntrngVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("httpMntrngVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 상세정보화면 
 ******************************************************** */
function fn_aram_detail(sysId) {
    var varForm = document.getElementById("httpMntrngVO");
    varForm.sysId.value = sysId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/detailHttpMntrng.do";
    varForm.submit();	
}

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fn_aram_regist(){	
    var varForm = document.getElementById("httpMntrngVO");
    varForm.sysId.value ="";
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/registHttpMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 로그조회 함수 
 ******************************************************** */
function fn_aram_log(){
    location.href = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrngLog.do";
}

</script>
