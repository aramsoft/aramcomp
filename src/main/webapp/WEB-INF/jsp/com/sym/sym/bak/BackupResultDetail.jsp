<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : BackupResultDetail.jsp
  * @Description : 백업결과 상세조회
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
	<h2>백업결과 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="backupResultVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="backupResultId" />

<!-- 등록  폼 영역  -->
<table class="table-detail" summary=" 백업결과 상세정보 테이블.">
<caption>백업결과 상세조회</caption>
  	<tr> 
    	<th width="20%">
    		백업결과ID
    	</th>
    	<td width="80%">
        	<c:out value="${backupResultVO.backupResultId}" escapeXml="false" /> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		백업작업ID
    	</th>
    	<td>
        	<c:out value="${backupResultVO.backupOpertId}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		백업작업명
    	</th>
    	<td>
        	<c:out value="${backupResultVO.backupOpertNm}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		백업원본디렉토리
    	</th>
    	<td>
        	<c:out value="${backupResultVO.backupOrginlDrctry}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		백업저장디렉토리
    	</th>
    	<td>
        	<c:out value="${backupResultVO.backupStreDrctry}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		백업파일
    	</th>
    	<td>
        	<c:out value="${backupResultVO.backupFile}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		상태
    	</th>
    	<td>
        	<c:out value="${backupResultVO.sttusNm}" escapeXml="false" /> 
    	</td>
  	</tr> 
  	<tr> 
    	<th  scope="row" style="word-break:break-all;">
    		에러정보
    	</th>
    	<td>
        	<c:out value="${backupResultVO.errorInfo}" escapeXml="false" /> 
    	</td>
  	</tr> 
   	<tr> 
    	<th>
    		실행시작시각
    	</th>
    	<td>
        	<fmt:parseDate value="${backupResultVO.executBeginTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
        	<fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		실행종료시각
    	</th>
    	<td>
        	<fmt:parseDate value="${backupResultVO.executEndTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
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
    var varForm = document.getElementById("backupResultVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/bak/listBackupResult.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("backupResultVO");
    
    if(confirm("<spring:message code='common.delete.msg' />")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/bak/deleteBackupResult.do";
        varForm.submit();
    }
}

</script>
