<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BatchOpertDetail.jsp
  * @Description : 배치작업 상세조회
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
	<h2>배치작업 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="batchOpertVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="batchOpertId" />

<table class="table-detail" summary=" 배치작업 상세정보 테이블.">
<caption>배치작업 상세조회</caption>
  	<tr> 
    	<th width="20%">
    		배치작업ID
    	</th>
    	<td width="80%">
        	<c:out value="${batchOpertVO.batchOpertId}" escapeXml="false" /> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		배치작업명
    	</th>
    	<td>
        	<c:out value="${batchOpertVO.batchOpertNm}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		1. 배치프로그램
    	</th>
    	<td>
        	<c:out value="${batchOpertVO.batchProgrm}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		파라미터
    	</th>
    	<td>
        	<c:out value="${batchOpertVO.paramtr}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		2. 배치빈이름
    	</th>
    	<td>
        	<c:out value="${batchOpertVO.batchBean}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		3. 배치객체
    	</th>
    	<td>
        	<c:out value="${batchOpertVO.batchObject}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		배치메소드
    	</th>
    	<td>
        	<c:out value="${batchOpertVO.batchMethod}" escapeXml="false" /> 
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

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("batchOpertVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchOpert.do";
    varForm.submit();    
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("batchOpertVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/editBatchOpert.do";
    varForm.submit();    
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("batchOpertVO");
    
    if(confirm("<spring:message code='common.delete.msg' />")){
        varForm.action = "${pageContext.request.contextPath}/sym/bat/deleteBatchOpert.do";
        varForm.submit();
    }
}

</script>
