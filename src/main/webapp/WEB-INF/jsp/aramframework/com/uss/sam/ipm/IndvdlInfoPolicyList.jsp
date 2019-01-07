<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : IndvdlInfoPolicyList.jsp
  * @Description : 개인정보보호정책 목록
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
	<h2>개인정보보호정책 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="indvdlInfoPolicyVO" action=""  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="indvdlInfoId"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select title="개인정보보호정책조회 조건" path="searchCondition" class="select">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value='INDVDL_INFO_POLICY_NM' label="개인정보보호정책명" />
	   		<form:option value='INDVDL_INFO_POLICY_CN' label="개인정보보호정책내용" />
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

<table class="table-list">
<thead>
	<tr>  
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="10%">동의여부</th>
	    <th scope="col"            >개인정보보호정책명</th>
	    <th scope="col" width="10%">등록자</th>       
	    <th scope="col" width="15%">등록일자</th>                 
	</tr>
</thead> 
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<%-- 데이터를 화면에 출력해준다 --%>
   	<c:set var="searchVO" value="${indvdlInfoPolicyVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.indvdlInfoId}"/>'); return false;">
	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>
 
    	<td class="lt_text3">
    		<c:if test="${result.indvdlInfoYn == 'Y'}">예</c:if>
    		<c:if test="${result.indvdlInfoYn == 'N'}">아니오</c:if>
    	</td>
    	<td class="lt_text3"><c:out value="${result.indvdlInfoNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
    	<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>  
	</c:forEach>
</tbody>  
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/listIndvdlInfoPolicy.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/listIndvdlInfoPolicy.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(indvdlInfoPolicyId){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
    varForm.indvdlInfoId.value = indvdlInfoPolicyId; 
    varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/detailIndvdlInfoPolicy.do"; 
    varForm.submit();
}

/*********************************************************
 * 등록화면 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("indvdlInfoPolicyVO");
    varForm.indvdlInfoId.value = ""; 
    varForm.action = "${pageContext.request.contextPath}/uss/sam/ipm/registIndvdlInfoPolicy.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("개인정보보호정책확인");	
	window.open(url, "도움말");
}

</script>
