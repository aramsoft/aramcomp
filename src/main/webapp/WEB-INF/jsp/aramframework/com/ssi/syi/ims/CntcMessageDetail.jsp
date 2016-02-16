<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CntcMessageDetail.jsp
  * @Description : 연계메시지 상세조회
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
	<h2>연계메시지 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit_cntcMessage(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete_cntcMessage(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_cntcMessage(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cntcMessageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcMessageId" />

<table class="table-detail">
  	<tr>
    	<th width="20%">
    		연계메시지ID
    	</th>
    	<td width="80%">
    		<c:out value="${cntcMessageVO.cntcMessageId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		연계메시지명
    	</th>
    	<td>
    		<c:out value="${cntcMessageVO.cntcMessageNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		상위연계메시지ID
    	</th>
    	<td>
    		<c:out value="${cntcMessageVO.upperCntcMessageId}"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>연계메시지항목</h2>
</div>

<div id="search_area">
	<div class="button_area">
    	<span class="button"><a href="#" onclick="javascript:fn_aram_regist_cntcMessageItem(); return false;"><spring:message code="button.create" /></a></span>
	</div>
</div>

<form:form commandName="cntcMessageItemVO" action ="" method="post">
	<input type="hidden" name="itemId">
	<form:hidden path="cntcMessageId" />
	
	<!-- 검색조건 유지 -->
	<form:hidden path="searchVO.searchCondition" />
	<form:hidden path="searchVO.searchKeyword" />
	<form:hidden path="searchVO.pageIndex" />
	<form:hidden path="searchVO.recordPerPage" />
	<!-- 검색조건 유지 -->
</form:form>

<table class="table-list">
<thead>
	<tr>
		<th width="10%">순번</th>
		<th width="20%">항목ID</th>
		<th width="20%">항목명</th>
		<th width="10%">항목타입</th>
		<th width="20%">항목길이</th>
		<th width="20%">처리</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(cntcMessageItemList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

	<c:forEach items="${cntcMessageItemList}" var="result" varStatus="status">
	<tr>
		<td class="lt_text3"><c:out value="${(cntcMessageItemVO.pageIndex - 1) * cntcMessageItemVO.pageSize + status.count}"/></td>
		<td class="lt_text3"><c:out value="${result.itemId}"/></td>
		<td class="lt_text"><c:out value="${result.itemNm}"/></td>
		<td class="lt_text"><c:out value="${result.itemType}"/></td>
		<td class="lt_text3"><c:out value="${result.itemLt}"/></td>
		<td>
		<table >
			<tr>
				<td>
			   		<span class="button">
			   		<a href="#" onclick="javascript:fn_aram_edit_cntcMessageItem('<c:out value="${result.itemId}"/>'); return false;">
			   			<spring:message code="button.update" />
			   		</a>
			   		</span>
				</td>
				<td width="1"></td>
				<td>
			   		<span class="button">
			   		<a href="#" onclick="javascript:fn_aram_delete_cntcMessageItem('<c:out value="${result.itemId}"/>'); return false;">
			   			<spring:message code="button.delete" />
			   		</a>
			   		</span>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	</c:forEach>
	
</tbody>
</table>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list_cntcMessage(){
    var varForm = document.getElementById("cntcMessageVO");
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/ims/listCntcMessage.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit_cntcMessage(){
    var varForm = document.getElementById("cntcMessageVO");
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/ims/editCntcMessage.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete_cntcMessage(){
    var varForm = document.getElementById("cntcMessageVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action	= "${pageContext.request.contextPath}/ssi/syi/ims/deleteCntcMessage.do";
		varForm.submit();
	}
}

/* ********************************************************
* 연계메시지항목 등록 화면으로  바로가기
******************************************************** */
function fn_aram_regist_cntcMessageItem(){
    var varForm = document.getElementById("cntcMessageItemVO");
	varForm.itemId.value = "";
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/ims/registCntcMessageItem.do";
	varForm.submit();
}

/* ********************************************************
* 연계메시지항목 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit_cntcMessageItem(itemId){
    var varForm = document.getElementById("cntcMessageItemVO");
	varForm.itemId.value = itemId;
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/ims/editCntcMessageItem.do";
	varForm.submit();
}

/* ********************************************************
* 연계메시지항목 삭제 처리 함수
******************************************************** */
function fn_aram_delete_cntcMessageItem(itemId){
    var varForm = document.getElementById("cntcMessageItemVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.itemId.value = itemId;
		varForm.action	= "${pageContext.request.contextPath}/ssi/syi/ims/deleteCntcMessageItem.do";
		varForm.submit();
	}
}

</script>
