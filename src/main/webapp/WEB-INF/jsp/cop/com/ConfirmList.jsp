<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ConfirmList.jsp
  * @Description : 승인 목록 
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
	<h2>승인 목록</h2>
</div>

<form:form modelAttribute="confirmHistoryVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="confmerId" />

<input type="hidden" name="confmNumber" value="0" />

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${confirmHistoryVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	   		<form:select path="searchCondition" class="select" title="검색조건선택">
				<form:option value="" label="--선택하세요--" />
			   	<form:option value="CONFM_TY_CODE" label="승인유형" />
			   	<form:option value="CONFM_STTUS_CODE" label="승인상태" />
		   	</form:select>
	   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
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

<table class="table-list">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >승인유형</th>
	    <th scope="col" width="10%">승인상태</th>
	    <th scope="col" width="20%">승인요청자</th>
	    <th scope="col" width="20%">대상업무유형</th>
	    <th scope="col" width="10%">승인일자</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	   	<td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(confirmHistoryVO.pageIndex-1) * confirmHistoryVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
   	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${confirmHistoryVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3"><c:out value="${result.confmTyCodeNm}"/></td>
		<td class="lt_text3">
			<c:choose>
		    <c:when test="${result.confmSttusCode == 'AP01'}">
		   		<span class="link">
		   		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.confmNumber}"/>'); return false;">
					<c:out value="${result.confmSttusCodeNm}"/>
		   		</a>
		   		</span>
			</c:when>
		    <c:otherwise>
				<c:out value="${result.confmSttusCodeNm}"/>
		    </c:otherwise>
		   	</c:choose>
		</td>
		<td class="lt_text3"><c:out value="${result.confmRqesterNm}"/></td>
		<td class="lt_text3"><c:out value="${result.opertTyCodeNm}"/></td>
		<td class="lt_text3"><c:out value="${fn:substring(result.confmDe, 0, 4)}-${fn:substring(result.confmDe, 4, 6)}-${fn:substring(result.confmDe, 6, 8)}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_confirm();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
	var varForm = document.getElementById("confirmHistoryVO");

	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/cop/com/listConfirmByTrget.do";
	varForm.submit();
}

function fn_aram_search() {
	var varForm = document.getElementById("confirmHistoryVO");

	varForm.pageIndex.value = 1;
	varForm.action = "${pageContext.request.contextPath}/cop/com/listConfirmByTrget.do";
	varForm.submit();
}

function fn_aram_detail(cnfmNo) {
	var varForm = document.getElementById("confirmHistoryVO");
	varForm.confmNumber.value = cnfmNo;
	varForm.action = "${pageContext.request.contextPath}/cop/com/editConfirm.do";
	varForm.submit();
}

</script>

