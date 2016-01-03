<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ClubListByCmmntyId.jsp
  * @Description : 동호회 목록 조회화면 - 해당 커뮤니티에 속하는
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
<DIV id="main" style="width:100%;">

<div class="content_title">
	<h2>소속 동호회 목록</h2>
</div>

<div style="margin-top:10px; width:100%"></div>

<form:form commandName="clubVO" action="" method="post">
<input type="hidden" name="cmmntyId"  value='${cmmntyId}'/>
<input type="hidden" name="clbId" />
</form:form>

<table class="table-list">
<thead>
	<tr>
	    <th width="7%" >No.</th>
	    <th            >동호회명</th>
	    <th width="20%">등록일</th>
	    <th width="10%">사용여부</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(clubVO.pageIndex-1) * clubVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.clbId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<td class="lt_text3"><c:out value="${index}"/></td>

		<td class="lt_text3"><c:out value="${result.clbNm}"/></td>
		<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
		<td class="lt_text3">
		  	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
		   	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</DIV>

<script type="text/javascript">

function fn_aram_detail(clbId) {
    var varForm = document.getElementById("clubVO");
    varForm.clbId.value = clbId;
    varForm.action = "${pageContext.request.contextPath}/cop/clb/detailClub.do";
    varForm.submit();
}

</script>
