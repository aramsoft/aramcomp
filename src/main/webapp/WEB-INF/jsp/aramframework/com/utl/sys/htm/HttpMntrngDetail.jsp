<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : HttpMonDetail.jsp
  * @Description : HTTP모니터링 상세조회
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
	<h2>HTTP모니터링 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="httpMntrngVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sysId" />

<table class="table-detail" summary="이 표는 HTTP서비스모니터링 대상 정보를 제공하며, 웹서비스종료, 시스템URL, 상태, 관리자명, 관리자이메일 정보로 구성되어 있습니다 .">
<caption>HTTP모니터링 상세조회</caption>
  	<tr> 
    	<th width="20%">
    		웹서비스종류
     	</th>
    	<!-- <td>
	    <c:if test="${result.webKind == '001'}">TOMCAT</c:if>
	    <c:if test="${result.webKind == '002'}">WEBLOGIC</c:if>
	    <c:if test="${result.webKind == '003'}">JEUS</c:if>
	    <c:if test="${result.webKind == '004'}">JBOSS</c:if>							    				    	    					    	
		</td>  -->
    	<td>${httpMntrngVO.webKind}</td>   							
  	</tr>
  	<tr>
    	<th>
    		시스템URL
    	</th>          
    	<td>${httpMntrngVO.siteUrl}</td>    
  	</tr> 					  	
  	<tr>
    	<th>
    		관리자명
    	</th>          
    	<td>${httpMntrngVO.mngrNm}</td>    
  	</tr> 
  	<tr>
    	<th>
    		관리자이메일
    	</th>          
    	<td>${httpMntrngVO.mngrEmailAddr}</td>    
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
    var varForm = document.getElementById("httpMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("httpMntrngVO");
	varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/editHttpMntrng.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("httpMntrngVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/deleteHttpMntrng.do";
		varForm.submit();
	}
}

</script>
