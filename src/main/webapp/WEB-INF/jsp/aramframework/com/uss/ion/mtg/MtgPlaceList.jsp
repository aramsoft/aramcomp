<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : MtgPlaceList.jsp
 * @Description : 회의실 목록
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
	<h2>회의실 목록</h2>
</div>

<form:form commandName="mtgPlaceManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="mtgPlaceId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
   		<label for="searchKeyword">회의실명</label>&nbsp;&nbsp;
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.searchCondition" value="1"/>
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="회의실관리에 대한 목록을 제공한다.">
<caption>회의실관리에 대한 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">회의실명</th>
	    <th scope="col" width="25%">개방시간(From~To)</th>
	    <th scope="col" width="10%">수용인원</th>
	    <th scope="col"            >위치</th> 
	</tr>
</thead>   
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(mtgPlaceManageVO.pageIndex-1) * mtgPlaceManageVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.mtgPlaceId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${mtgPlaceManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3"><c:out value="${result.mtgPlaceNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.opnBeginTm}"/> ~ <c:out value="${result.opnEndTm}"/></td>
    	<td class="lt_text3"><c:out value="${result.aceptncPosblNmpr}"/>명</td>
    	<td class="lt_text3"><c:out value="${result.lcSeNm}"/> <c:out value="${result.lcDetail}"/></td> 
  	</tr>
 	</c:forEach>
</tbody>  
</table>
 
<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*설명 : 회의실 목록 조회 enter키 처리 */
function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("mtgPlaceManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlace.do";
    varForm.submit();
}

/*설명 : 회의실  목록 조회 */
function fn_aram_search(){
    var varForm = document.getElementById("mtgPlaceManageVO");
    varForm["searchVO.pageIndex"].value = "1";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlace.do";
    varForm.submit();
}

/*설명 : 회의실 상세조회 */
function fn_aram_detail(mtgPlaceId) {
    var varForm = document.getElementById("mtgPlaceManageVO");
    varForm.mtgPlaceId.value = mtgPlaceId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/detailMtgPlace.do";
    varForm.submit();   
}

/*설명 : 회의실 신규등록 화면 호출 */
function fn_aram_regist() {
    var varForm = document.getElementById("mtgPlaceManageVO");
    varForm.mtgPlaceId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/registMtgPlace.do";
    varForm.submit(); 
}

</script>

