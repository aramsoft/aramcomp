<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollResultList.jsp
  * @Description : 온라인POLL 결과  목록
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
	<h2>온라인POLL 결과  목록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<form:form commandName="onlinePollResultVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="pollId" />
<input name="pollResultId" type="hidden" value="">
</form:form>

<table class="table-list">
<thead>
	<tr>  
	    <th scope="col" width="7%" >No.</th>
		<th scope="col"            >온라인POLL항목</th>
		<th scope="col" width="10%">등록자</th>       
		<th scope="col" width="15%">등록일자</th>  
		<th scope="col" width="10%"></th>               
	</tr>
</thead> 

<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(onlinePollResultVO.searchVO.pageIndex-1) * onlinePollResultVO.searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${onlinePollResultVO.searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3L" align="center"><c:out value="${result.pollIemNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	    <td class="lt_text3">
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_delete('<c:out value="${result.pollResultId}"/>'); return false;">
				삭제
			</a>
			</span>
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
 function fn_aram_list(){
    var varForm = document.getElementById("onlinePollResultVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/listOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제함수
 ******************************************************** */
function fn_aram_delete(pollResultId){
    var varForm = document.getElementById("onlinePollResultVO");

	if(confirm("삭제 하시겠습니까?")){
		varForm.pollResultId.value = pollResultId;
		varForm.action = "${pageContext.request.contextPath}/uss/olp/opr/deleteOnlinePollResult.do";	
		varForm.submit();
	}
}

</script>

