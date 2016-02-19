<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : TnextrlHrInfoList.jsp
 * @Description : 외부인사정보 목록
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>외부인사정보 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="tnextrlHrInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="extrlHrId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="EXTRL_HR_NM" label="외부인사명" />			   
	   		<form:option value="PSITN_INSTT_NM" label="소속기관" />			   
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

<table class="table-list" summary="순번, 성별, 외부인사명, 소속기관, 작성자명, 등록일자  목록입니다">
<thead> 
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="10%">성별</th>
	    <th scope="col"            >외부인사명</th>
	    <th scope="col" width="15%">소속기관</th>
	    <th scope="col" width="15%">작성자명</th>
	    <th scope="col" width="15%">등록일자</th>
  	</tr>
</thead>
<tbody>
 	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(tnextrlHrInfoVO.searchVO.pageIndex-1) * tnextrlHrInfoVO.searchVO.recordPerPage}"/>
 	<c:forEach items="${resultList}" var="result" varStatus="status">
    <tr class="link" onclick="javascript:fn_aram_detail('${result.extrlHrId}'); return false;">
    
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${tnextrlHrInfoVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
			<c:forEach items="${COM014_sexdstn}" var="comCode" varStatus="status">
			<c:if test="${comCode.code eq result.sexdstnCode}">
			<c:out value="${fn:replace(comCode.codeNm , crlf , '<br/>')}" escapeXml="false" />
			</c:if>
			</c:forEach>
    	</td>
    	<td class="lt_text3"><c:out value="${result.extrlHrNm}"/></td>
    	<td class="lt_text3">${result.psitnInsttNm}</td>
    	<td class="lt_text3">${result.frstRegisterNm}</td>
    	<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
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
    var varForm = document.getElementById("tnextrlHrInfoVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listTnextrlHrInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("tnextrlHrInfoVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listTnextrlHrInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(extrlHrId){
    var varForm = document.getElementById("tnextrlHrInfoVO");
    varForm.extrlHrId.value = extrlHrId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/detailTnextrlHrInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("tnextrlHrInfoVO");
    varForm.extrlHrId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/registTnextrlHrInfo.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("행사_이벤트_캠페인");	
	window.open(url, "도움말");
}

</script>

