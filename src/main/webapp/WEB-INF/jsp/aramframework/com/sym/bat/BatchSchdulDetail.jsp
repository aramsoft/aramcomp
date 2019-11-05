<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : BatchSchdulDetail.jsp
 * @Description : 배치스케줄 상세조회
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
	<h2>배치스케줄 상세조회 </h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="batchSchdulVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="batchSchdulId" />

<table class="table-detail" summary=" 배치스케줄 상세정보 테이블.">
<caption>배치스케줄 상세조회</caption>
  	<tr> 
    	<th width="20%">
    		배치스케줄ID
    	</th>
    	<td width="80%">
        	<c:out value="${batchSchdulVO.batchSchdulId}" escapeXml="false" /> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		배치작업ID
    	</th>
    	<td>
        	<c:out value="${batchSchdulVO.batchOpertId}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		배치작업명
    	</th>
    	<td>
        	<c:out value="${batchSchdulVO.batchOpertNm}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		실행주기
    	</th>
    	<td>
        	<c:out value="${batchSchdulVO.executCycleNm}" escapeXml="false" />&nbsp;<c:out value="${batchSchdulVO.executSchdul}" escapeXml="false" />  
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		반복주기
    	</th>
    	<td>
        	지연시간 : <c:out value="${batchSchdulVO.startDelay}" escapeXml="false" />&nbsp;/&nbsp;반복기간  : <c:out value="${batchSchdulVO.repeatInterval}" escapeXml="false" />  
    	</td>
  	</tr> 
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" 	value="${batchSchdulVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   	value="${batchSchdulVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       	value="${batchSchdulVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   	value="${batchSchdulVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("batchSchdulVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("batchSchdulVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/editBatchSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("batchSchdulVO");
    
    if(confirm("<spring:message code='common.delete.msg' />")){
        varForm.action = "${pageContext.request.contextPath}/sym/bat/deleteBatchSchdul.do";
        varForm.submit();
    }
}

</script>
