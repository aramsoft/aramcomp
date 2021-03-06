<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CmmnCodeList.jsp
  * @Description : 공통코드 목록
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
	<h2>공통코드 목록</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="cmmnCodeVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type=hidden name="codeId">

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${cmmnCodeVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<form:select path="searchCondition">
		   		<form:option value='' label="--선택하세요--" />
		   		<form:option value='CODE_ID' label="코드ID" />
		   		<form:option value='CODE_ID_NM' label="코드ID명" />
	   		</form:select>	   
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="분류명, 코드ID, 코드ID명, 사용여부를 가지고있는 공통코드 목록 테이블이다.">
<CAPTION>공통코드 목록</CAPTION>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="30%">분류명</th>
		<th scope="col" width="15%">코드ID</th>
		<th scope="col"            >코드ID명</th>
		<th scope="col" width="15%">사용여부</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan=5><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(cmmnCodeVO.pageIndex-1) * cmmnCodeVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
  		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${cmmnCodeVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>
	
		<td class="lt_text3">${result.clCodeNm}</td>
		<td class="lt_text3">${result.codeId}</td>
		<td class="lt_text">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.codeId}"/>'); return false;">
				${result.codeIdNm}
    		</a>
			</span>
		</td>
		<td class="lt_text3">
			<c:if test="${result.useAt == 'Y'}">사용</c:if>
			<c:if test="${result.useAt == 'N'}">미사용</c:if>
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

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("cmmnCodeVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/listCmmnCode.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("cmmnCodeVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/listCmmnCode.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(codeId){
    var varForm = document.getElementById("cmmnCodeVO");
	varForm.codeId.value = codeId;
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/detailCmmnCode.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("cmmnCodeVO");
	varForm.codeId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/registCmmnCode.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("공통코드");	
	window.open(url, "도움말");
}

</script>
