<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : EventReqstDetail.jsp
 * @Description : 행사 상세조회
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
	<h2>행사 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${empty check_popup}">
        	<c:if test="${eventManageVO.eventAtdrnCount == 0 && eventManageVO.eventAtdrnDayCount >= 0}">
				<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
				<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
	  		</c:if>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
        </c:if>
        <c:if test="${check_popup == 'Y'}">
			<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
	  	</c:if>
	</div>
</div>

<form:form commandName="eventManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="eventId" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="행사관리 상세">
<caption>행사관리 상세</caption>
  	<tr>
    	<th width="20%">
    		행사구분
    	</th>          
    	<td width="80%">
    		<c:out value='${eventManageVO.eventSeNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		행사명
    	</th>
    	<td>
    		<c:out value='${eventManageVO.eventNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		행사목적
    	</th>          
    	<td>
    		<c:out value='${eventManageVO.eventPurps}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
     		행사기간
    	</th>          
    	<td>
 			<c:out value="${fn:substring(eventManageVO.eventBeginDe, 0,4)}-${fn:substring(eventManageVO.eventBeginDe, 4,6)}-${fn:substring(eventManageVO.eventBeginDe, 6,8)}" escapeXml="false" />
    		 ~ 
 			<c:out value="${fn:substring(eventManageVO.eventEndDe, 0,4)}-${fn:substring(eventManageVO.eventEndDe, 4,6)}-${fn:substring(eventManageVO.eventEndDe, 6,8)}" escapeXml="false" />
    		(<c:out value='${eventManageVO.eventDayCount}'/>일간)
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		행사주최
    	</th>          
    	<td>
    		<c:out value='${eventManageVO.eventAuspcInsttNm}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
     		행사주관
    	</th>          
    	<td>
    		<c:out value='${eventManageVO.eventMngtInsttNm}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		행사장소
    	</th>          
    	<td>
    		<c:out value='${eventManageVO.eventPlace}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		참조URL
    	</th>          
    	<td>
    		<c:if test="${!empty eventManageVO.refrnUrl}">
       		<a href="<c:out value='${eventManageVO.refrnUrl}'/>" target="_blank"  title="새 창으로 이동"><c:out value='${eventManageVO.refrnUrl}'/></a>
    		</c:if>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		행사내용
    	</th>          
    	<td>
       		<textarea id="remark" name="eventCn" class="txArea" rows="4" cols="70" title="행사내용" readonly><c:out value='${eventManageVO.eventCn}'/></textarea>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		참가비용
    	</th>          
    	<td> 
       		<c:if test="${eventManageVO.ctOccrrncAt == '1'}"> 무료 </c:if>
       		<c:if test="${eventManageVO.ctOccrrncAt == '2'}"> 유료       
          	<c:out value='${eventManageVO.partcptCt}'/>만원
       		</c:if>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		정원
    	</th>          
    	<td>
    		<c:out value='${eventManageVO.garden}'/>
    	</td>    
  	</tr>
  	<tr>
    	<th>
     		행사접수기간
    	</th>          
    	<td>
 			<c:out value="${fn:substring(eventManageVO.rceptBeginDe, 0,4)}-${fn:substring(eventManageVO.rceptBeginDe, 4,6)}-${fn:substring(eventManageVO.rceptBeginDe, 6,8)}" escapeXml="false" />
       		 ~ 
 			<c:out value="${fn:substring(eventManageVO.rceptEndDe, 0,4)}-${fn:substring(eventManageVO.rceptEndDe, 4,6)}-${fn:substring(eventManageVO.rceptEndDe, 6,8)}" escapeXml="false" />
    	</td>    
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(pageNo){
	var varForm = document.getElementById("eventManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/listEventReqst.do";
	varForm.submit();
}

/* ********************************************************
* 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit() {
	var varForm = document.getElementById("eventManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/editEventReqst.do";
	varForm.submit();   
}

/* ********************************************************
* 삭제처리화면
******************************************************** */
function fn_aram_delete() {
	var varForm = document.getElementById("eventManageVO");
	
	if(confirm("<spring:message code='common.delete.msg'/>")){
   	   	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/deleteEventReqst.do";
       	varForm.submit();
   	}
}

</script>
