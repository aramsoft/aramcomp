<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CntcSttusList.jsp
  * @Description : 연계현황 목록
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
	<h2>연계현황 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" hspace="3" style="vertical-align:middle; display:inline-block;" alt="도움말아이콘이미지">
	</a>
</div>

<form:form commandName="cntcSttusVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="cntcId">

<div id="search_area">
	<div class="button_area">
     	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" class="select" title="검색조건선택">
	   		<form:option value="" label="--선택하세요--" />
	   		<form:option value="CNTC_NM" label="연계명" />
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
		<th scope="col"            >연계명</th>
		<th scope="col" width="10%">연계유형</th>
		<th scope="col" width="10%">제공기관</th>
		<th scope="col" width="10%">제공시스템</th>
		<th scope="col" width="10%">제공서비스</th>
		<th scope="col" width="10%">요청기관</th>
		<th scope="col" width="10%">요청시스템</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="searchVO" value="${cntcSttusVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.cntcId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.cntcId}"/></td>
		<td class="lt_text3"><c:out value="${result.cntcType}"/></td>
		<td class="lt_text3"><c:out value="${result.provdInsttNm}"/></td>
		<td class="lt_text3"><c:out value="${result.provdSysNm}"/></td>
		<td class="lt_text3"><c:out value="${result.provdSvcNm}"/></td>
		<td class="lt_text3"><c:out value="${result.requstInsttNm}"/></td>
		<td class="lt_text3"><c:out value="${result.requstSysNm}"/></td>
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
    var varForm = document.getElementById("cntcSttusVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/ist/listCntcSttus.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("cntcSttusVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/ist/listCntcSttus.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(cntcId){
    var varForm = document.getElementById("cntcSttusVO");
	varForm.cntcId.value = cntcId;
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/ist/detailCntcSttus.do";
	varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:ssi:" + encodeURIComponent("연계현황관리");	
	window.open(url, "도움말");
}

</script>
