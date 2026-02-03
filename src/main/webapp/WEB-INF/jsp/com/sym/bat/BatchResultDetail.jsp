<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : BatchResultDetail.jsp
 * @Description : 배치결과 상세조회 
 * @Modification Information
 * @
 * @ 수정일              수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2014.11.11   조헌철          최초 생성
 *
 *  @since 2014.11.11
 *  @version 1.0
 *  @see
 *  
 */
%>
<c:set var="tempDate" value=""/>
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>배치결과 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="batchResultVO"  action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="batchResultId" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary=" 배치결과 상세정보 테이블.">
<caption>배치결과 상세조회</caption>
  	<tr> 
    	<th width="20%">
    		배치결과ID
    	</th>
    	<td width="80%">
        	<c:out value="${batchResultVO.batchResultId}" escapeXml="false" /> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		배치스케줄ID
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchSchdulId}" escapeXml="false" /> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		배치작업ID
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchOpertId}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		배치작업명
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchOpertNm}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		1.배치프로그램
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchProgrm}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		파라미터
    	</th>
    	<td>
        	<c:out value="${batchResultVO.paramtr}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		2.배치빈이름
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchBean}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		3.배치객체
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchObject}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		배치메소드
    	</th>
    	<td>
        	<c:out value="${batchResultVO.batchMethod}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		상태
    	</th>
    	<td>
        	<c:out value="${batchResultVO.sttusNm}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		에러정보
    	</th>
    	<td style="word-break:break-all;">
        	<c:out value="${batchResultVO.errorInfo}" escapeXml="false" /> 
    	</td>
  	</tr> 
    <tr> 
    	<th>
    		실행시작시각
    	</th>
    	<td>
        	<fmt:parseDate value="${batchResultVO.executBeginTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
        	<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		실행종료시각
    	</th>
    	<td>
        	<fmt:parseDate value="${batchResultVO.executEndTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
        	<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
    	</td>
  	</tr> 
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("batchResultVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchResult.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("batchResultVO");
    
    if(confirm("<spring:message code='common.delete.msg' />")){
        varForm.action = "${pageContext.request.contextPath}/sym/bat/deleteBatchResult.do";
    	varForm.submit();
    }
}

</script>
