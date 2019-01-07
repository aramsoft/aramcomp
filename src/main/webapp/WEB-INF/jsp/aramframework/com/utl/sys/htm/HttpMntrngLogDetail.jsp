<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : HttpMonLogDetail.jsp
  * @Description : HTTP모니터링 로그 상세조회
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
	<h2>HTTP모니터링 로그 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="httpMntrngLogVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="webKind" />
<form:hidden path="logId" />

<table class="table-detail" summary="이 표는 HTTP서비스모니터링 대상 정보를 제공하며, 로그ID, 프로세스ID, 웹서비스종류, 상태, 로그 정보, 마지막생성일시로 구성되어 있습니다 .">
<caption>HTTP모니터링 상세조회</caption>
  	<tr> 
    	<th width="20%">
    		로그ID
    	</th>
    	<td>${httpMntrngLogVO.logId}</td>
  	</tr>
  	<tr> 
    	<th>
    		프로세스ID
    	</th>
    	<td>${httpMntrngLogVO.sysId}</td>
  	</tr>								  						
  	<tr> 
    	<th>
     		웹서비스종류
    	</th>
    	<td>${httpMntrngLogVO.webKind}</td>
  	</tr>
  	<tr>
    	<th>
     		상태
    	</th>          
    	<td>${httpMntrngLogVO.httpSttusCd}</td>    
  	</tr>
	<c:if test="${httpMntrngLogVO.httpSttusCd == '오류'}">
  	<tr>
    	<th>
     		로그
    	</th>          
    	<td>${httpMntrngLogVO.logInfo}</td>    
  	</tr>
	</c:if>
  	<tr>
    	<th>
     		마지막 생성일시
    	</th>          
    	<td>${httpMntrngLogVO.creatDt}</td>    
  	</tr>  
</table>

<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<form:hidden path="searchBgnHour" />
<form:hidden path="searchEndHour" />
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
    var varForm = document.getElementById("httpMntrngLogVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrngLog.do";
    varForm.submit();	
}

</script>
