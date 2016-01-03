<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : HttpMonRegist.jsp
  * @Description : HTTP모니터링 등록
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
	<h2>HTTP모니터링 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="httpMntrngVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 HTTP서비스모니터링 대상 정보를 제공하며, 웹서비스종료, 시스템URL, 상태, 관리자명, 관리자이메일 정보로 구성되어 있습니다 .">
<caption>HTTP모니터링 등록</caption>
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	웹서비스종류
	    </th>          
	    <td width="80%">
		   	<select name="webKind" title="웹서비스종류">
			   	<option value="TOMCAT">TOMCAT</option>
			   	<option value="WEBLOGIC">WEBLOGIC</option>
			   	<option value="JEUS">JEUS</option>
			   	<option value="JBOSS">JBOSS</option>								   							   	
		  	</select>
	    </td>
	</tr>					  	
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	시스템URL
	    </th>          
	    <td>
	      <form:input  path="siteUrl" size="30" maxlength="30"/>
	      <form:errors path="siteUrl" cssClass="error"/>
	    </td>
  	</tr> 				  	
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	관리자명
	    </th>          
	    <td>
	      <form:input  path="mngrNm" size="60" maxlength="60"/>
	      <form:errors path="mngrNm" cssClass="error"/>
	    </td>    
  	</tr> 
  	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	관리자이메일
	    </th>          
	    <td>
	      <form:input  path="mngrEmailAddr" size="60" maxlength="60"/>
	      <form:errors path="mngrEmailAddr" cssClass="error"/>
	    </td>    
  	</tr>    
</table>

</form:form>
	
</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="httpMntrngVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("httpMntrngVO");
    varForm.webKind.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("httpMntrngVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/listHttpMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("httpMntrngVO");
    
	if(!validateHttpMntrngVO(varForm)){ 			
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/utl/sys/htm/insertHttpMntrng.do";
		varForm.submit();
	}
}

</script>
