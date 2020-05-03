<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : EventRceptRegist.jsp
 * @Description : 행사접수신청 등록
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
	<h2>행사접수신청 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>	
</div>

<form:form modelAttribute="eventAtdrnVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="eventId" />
<form:hidden  path="applcntId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="행사접수관리 등록">
<caption>행사접수관리 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		신청자
    	</th>          
    	<td width="30%">
    		<c:out value='${eventAtdrnVO.applcntNm}'/>
    	</td>
    	<th width="20%">
     		<span class="norequired_icon"></span>
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${eventAtdrnVO.orgnztNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		<span class="required_icon"></span>
    		행사구분
    	</th>
    	<td>
    		<c:out value='${eventManageVO.eventSeNm}'/>
    	</td>
    	<th>
     		<span class="required_icon"></span>
    		행사명
    	</th>
    	<td>
    		<c:out value='${eventManageVO.eventNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		행사목적
    	</th>          
    	<td colspan="3">
    		<c:out value='${eventManageVO.eventPurps}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		행사기간
    	</th>          
    	<td colspan="3">
 			<c:out value="${fn:substring(eventManageVO.eventBeginDe, 0,4)}-${fn:substring(eventManageVO.eventBeginDe, 4,6)}-${fn:substring(eventManageVO.eventBeginDe, 6,8)}" escapeXml="false" />
    		 ~ 
 			<c:out value="${fn:substring(eventManageVO.eventEndDe, 0,4)}-${fn:substring(eventManageVO.eventEndDe, 4,6)}-${fn:substring(eventManageVO.eventEndDe, 6,8)}" escapeXml="false" />
    		(<c:out value='${eventManageVO.eventDayCount}'/>일간)
    	</td>    
  	</tr> 
  	<tr>
    	<th>
     		<span class="required_icon"></span>
   			행사주최
    	</th>          
    	<td colspan="3">
    		<c:out value='${eventManageVO.eventAuspcInsttNm}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		행사주관
    	</th>          
    	<td colspan="3">
    		<c:out value='${eventManageVO.eventMngtInsttNm}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		행사장소
    	</th>          
    	<td colspan="3">
    		<c:out value='${eventManageVO.eventPlace}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		참조URL
    	</th>          
    	<td colspan="3">
    		<c:if test="${!empty eventManageVO.refrnUrl}">
       		<a href="<c:out value='${eventManageVO.refrnUrl}'/>" target="_blank"  title="새 창으로 이동"><c:out value='${eventManageVO.refrnUrl}'/></a>
    		</c:if>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		행사내용
    	</th>          
    	<td colspan="3">
       		<textarea id="remark" name="eventCn" class="txArea" rows="4" cols="70" title="행사내용" readonly><c:out value='${eventManageVO.eventCn}'/></textarea>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		참가비용
    	</th>          
    	<td colspan="3"> 
       		<c:if test="${eventManageVO.ctOccrrncAt == '1'}">(무료)</c:if>
       		<c:if test="${eventManageVO.ctOccrrncAt == '2'}">(유료) <c:out value='${eventManageVO.partcptCt}'/>만원</c:if>      
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		정원
    	</th>          
    	<td colspan="3">
    		<c:out value='${eventManageVO.garden}'/>
    	</td>    
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		행사접수기간
    	</th>          
    	<td colspan="3">
 			<c:out value="${fn:substring(eventManageVO.rceptBeginDe, 0,4)}-${fn:substring(eventManageVO.rceptBeginDe, 4,6)}-${fn:substring(eventManageVO.rceptBeginDe, 6,8)}" escapeXml="false" />
       		 ~ 
 			<c:out value="${fn:substring(eventManageVO.rceptEndDe, 0,4)}-${fn:substring(eventManageVO.rceptEndDe, 4,6)}-${fn:substring(eventManageVO.rceptEndDe, 6,8)}" escapeXml="false" />
    	</td>    
  	</tr>
</table>

<!-- 결재권자 지정 Include -->
<jsp:include page="/WEB-INF/jsp/aramframework/com/uss/ion/ism/InfrmlSanctnRegist.jsp" flush="true"/> 
<!-- //결재권자 지정 Include -->

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="eventAtdrnVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("eventAtdrnVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/listEventRcrpt.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("eventAtdrnVO");

    if(!validateEventAtdrnVO(varForm)){           
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/insertEventRcrpt.do";
        varForm.submit();
    }
}
		
</script>
