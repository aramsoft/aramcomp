<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : AdministCodeList.jsp
  * @Description : 행정코드 목록
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
	<h2>행정코드 목록</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="administCodeVO" action=""  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type=hidden name="administZoneSe">
<input type=hidden name="administZoneCode">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
  		<form:select path="searchCondition" class="select" title="">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value='1' label="법정동 지역명" />
	   		<form:option value='2' label="행정동 지역명" />
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

<table class="table-list" summary="구분, 행정구역코드, 행정구역명, 사용여부가 담긴 행정코드 목록을 조회한다.">
<CAPTION>행정코드 목록</CAPTION>
<thead>
	<tr>
		<th scope="col" width="7%" >No.</th>
		<th scope="col" width="15%">구분</th>
		<th scope="col" width="15%">행정구역코드</th>
		<th scope="col"            >행정구역명</th>
		<th scope="col" width="10%">사용여부</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan=5><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="searchVO" value="${administCodeVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.administZoneSe}', '${result.administZoneCode}'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<c:choose>
				<c:when test='${result.administZoneSe == "1"}'>법정동</c:when>
				<c:when test='${result.administZoneSe == "2"}'>행정동</c:when>
			</c:choose>
		</td>
		<td class="lt_text3">${result.administZoneCode}</td>
		<td class="lt_text3">${result.administZoneNm}</td>
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
    var varForm = document.getElementById("administCodeVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/listAdministCode.do";
    varForm.submit();
}

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("administCodeVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/listAdministCode.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(administZoneSe, administZoneCode){
    var varForm = document.getElementById("administCodeVO");
	varForm.administZoneSe.value   = administZoneSe;
	varForm.administZoneCode.value = administZoneCode;
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/detailAdministCode.do";
	varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("administCodeVO");
	varForm.administZoneSe.value   = "";
	varForm.administZoneCode.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/registAdministCode.do";
    varForm.submit();	
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:" + encodeURIComponent("행정코드관리");	
	window.open(url, "도움말");
}

</script>
