<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NameCardPublic.jsp
  * @Description : 공개 명함 목록
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
	<h2>공개 명함 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form modelAttribute="nameCardVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input type="hidden" name="ncrdId" />

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${nameCardVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	  		<form:select path="searchCondition" class="select" title="검색조건선택">
				<form:option value="" label="--선택하세요--" />
		   		<form:option value="NM" label="이름" />
		   		<form:option value="CMPNY_NM" label="회사명" />
		   		<form:option value="DEPT_NM" label="부서명" />
	   		</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
			<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
	       	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
</form:form>
	 
<table class="table-list"  summary="번호,이름,회사명,부서명,등록일자,사용등록   목록입니다">
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="20%">이름</th>
    	<th scope="col"            >회사명</th>
    	<th scope="col" width="20%">부서명</th>
    	<th scope="col" width="15%">등록일자</th>
    	<th scope="col" width="15%">사용등록</th>
  	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(nameCardVO.pageIndex-1) * nameCardVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${nameCardVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3">
			<span class="link">
   			<a href="#" onClick="javascript:fn_aram_popup('<c:out value="${result.ncrdId}"/>'); return false;">
   				<c:out value="${result.ncrdNm}"/>
   			</a>
			</span>
	    </td>
	    <td class="lt_text3"><c:out value="${result.cmpnyNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.deptNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td	>
		<td class="lt_text3">
	  	<c:if test="${result.emplyrId != userId}">
			<span class="button">
   			<a href="#" onClick="javascript:fn_aram_insert_nameCardUse('<c:out value="${result.ncrdId}"/>'); return false;">
				사용등록
			</a>
			</span>
		</c:if>
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

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("nameCardVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/cop/ncm/listNameCardPublic.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("nameCardVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/cop/ncm/listNameCardPublic.do";
    varForm.submit();
}

function fn_aram_popup(ncrdId){
	window.open("${pageContext.request.contextPath}/cop/ncm/detailNameCardPopup.do?ncrdId="+ncrdId,"명함조회","width=800, height=400");
}

function fn_aram_insert_nameCardUse(ncrdId){
    var varForm = document.getElementById("nameCardVO");
    varForm.ncrdId.value = ncrdId;
    varForm.action = "${pageContext.request.contextPath}/cop/ncm/insertNameCardUse.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:cop:%EB%AA%85%ED%95%A8%EA%B4%80%EB%A6%AC";	
	window.open(url, "도움말");
}

</script>

