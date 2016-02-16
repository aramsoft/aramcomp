<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AnnvrsryList.jsp
 * @Description : 기념일 목록
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
	<h2>기념일 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_excel(); return false;">기념일엑셀등록</a></span>
	</div>
</div>

<form:form commandName="annvrsryManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="annId" value="">

<table class="table-register" summary="기념일관리목록 검색조건">
<caption>기념일관리목록 검색조건</caption>
  	<tr> 
    	<th width="20%"  scope="row" class="column_title">
    		<label for="searchKeyword">년도</label>
			<span class="norequired_icon"></span>
    	</th>
    	<td width="80%">
        	<form:select path="searchVO.searchKeyword" title="년도">
                <form:option value="" label="전체"/>
                <form:options items="${yearList}" />
      		</form:select>년
	    </td>
	</tr>
</table>

<form:hidden path="searchVO.searchCondition" value="1" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="이 표는 기념일관리리스트의 정보를 제공하며 기념일제목, 기념일(양/음), 메모, 알림여부, D-day로 구성되어 있습니다.">
<caption>기념일관리 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">기념일제목</th>
	    <th scope="col" width="15%">기념일(양/음)</th>
	    <th scope="col"            >메모</th>
	    <th scope="col" width="10%">알림여부</th>
	    <th scope="col" width="10%">D-day</th>  
	    <th scope="col" width="10%">반복여부</th> 
  	</tr>
</thead>  
<tbody>
 	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
 	<c:set var="startIndex" value="${(annvrsryManageVO.pageIndex-1) * annvrsryManageVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail_annvrsryManage('${result.annId}'); return false;">
  	
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${annvrsryManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"><c:out value="${result.annvrsryNm}"/></td>
	    <td class="lt_text3">
        	<c:out value='${fn:substring(result.annvrsryDe, 0,4)}-${fn:substring(result.annvrsryDe, 4,6)}-${fn:substring(result.annvrsryDe, 6,8)}'/>
	    	<c:if test="${!empty result.cldrSe }">(<c:if test='${result.cldrSe == "1"}'>양</c:if><c:if test='${result.cldrSe == "2"}'>음</c:if>)</c:if>
	    </td>
	    <td class="lt_text3"><c:out value="${result.memo}"/></td>
	    <td class="lt_text3">
	    	<c:if test='${result.annvrsrySetup == "Y"}'>ON</c:if>
			<c:if test='${result.annvrsrySetup == "N"}'>OFF</c:if>
		</td> <!-- 알림여부 -->
	    <td class="lt_text3">
	    	<c:if test="${!empty result.annvrsryBeginDe }">D-<c:out value='${result.annvrsryBeginDe}'/>일전</c:if>
		</td> <!-- D-day -->
	    <td class="lt_text3">
	    	<c:choose>
	    	<c:when test="${'1' eq result.reptitSe }">Y</c:when>
	    	<c:otherwise>N</c:otherwise>
	    	</c:choose>
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

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("annvrsryManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsry.do";
    varForm.submit();
}

/*설명 : 기념일 목록 조회 */
function fn_aram_search(){
    var varForm = document.getElementById("annvrsryManageVO");
    varForm["searchVO.pageIndex"].value = "1";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsry.do";
    varForm.submit();
}

/*설명 : 기념일 상세조회 */
function fn_aram_detail(annId) {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.annId.value = annId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/detailAnnvrsry.do";
    varForm.submit();   
}

/*설명 : 기념일 신규등록 화면 호출 */
function fn_aram_regist() {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.annId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/registAnnvrsry.do";
    varForm.submit(); 
}

var gArguments = new Array();

/*설명 : 기념일 엑셀등록 PopUp 화면 호출	 */
function fn_aram_excel_annvrsryManage() {
	gArguments["retFunc"] = fn_aram_search_annvrsryManage;
	
	var url = "/uss/ion/ans/listAnnvrsryBndePopup.do";

	window.open(url, "p_annvrsry", "width=850px,height=420px,top=100px,left=100px,location=no");
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php?id=aramframework:" + encodeURIComponent("기념일관리");	
	window.open(url, "도움말");
}

</script>
