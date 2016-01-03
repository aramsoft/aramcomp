<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EventCmpgnDetail.jsp
 * @Description : 행사/이벤트/캠페인 상세조회
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
	<h2>행사/이벤트/캠페인 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="eventCmpgnVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="eventId" />

<!-- 등록  폼 영역  -->
<table class="table-detail">
  	<tr>
    	<th width="20%">
    		행사유형
    	</th>
    	<td width="80%">
			<c:forEach items="${COM035_eventTy}" var="comCode" varStatus="status">
				<c:if test="${comCode.code eq fn:trim(eventCmpgnVO.eventTyCode)}">
				<c:out value="${fn:replace(comCode.codeNm , crlf , '<br/>')}" escapeXml="false" />
				</c:if>
			</c:forEach>
   		</td>
  	</tr>
  	<tr>
    	<th>
    		행사내용
    	</th>
    	<td>
     	 	${eventCmpgnVO.eventCn}
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		행사시작일자
    	</th>
    	<td width="80%">
			<c:out value="${fn:substring(eventCmpgnVO.eventSvcBeginDe, 0,4)}-${fn:substring(eventCmpgnVO.eventSvcBeginDe, 4,6)}-${fn:substring(eventCmpgnVO.eventSvcBeginDe, 6,8)}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		행사종료일자
    	</th>
    	<td>
			<c:out value="${fn:substring(eventCmpgnVO.eventSvcEndDe, 0,4)}-${fn:substring(eventCmpgnVO.eventSvcEndDe, 4,6)}-${fn:substring(eventCmpgnVO.eventSvcEndDe, 6,8)}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		서비스이용인원수
    	</th>
    	<td>
     		${eventCmpgnVO.svcUseNmprCo}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		담당자명
    	</th>
    	<td>
     		${eventCmpgnVO.chargerNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		준비물내용
    	</th>
    	<td>
     		${eventCmpgnVO.prparetgCn}
    	</td>
  	</tr>
<!--
  	<tr>
    	<th>
    		파일첨부
    	</th>
    	<td>
      		<input type="file" name="upFile">
    	</td>
  	</tr>
-->
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>


<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("eventCmpgnVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
	var varForm = document.getElementById("eventCmpgnVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/editEventCmpgn.do";;
	varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
	var varForm = document.getElementById("eventCmpgnVO");

	if(confirm("삭제시 행사/이벤트/캠페인, 외부인사정보 \n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/deleteEventCmpgn.do";
		varForm.submit();
	}
}

</script>

