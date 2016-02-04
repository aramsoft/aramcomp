<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : LoginSesionCheck.jsp
  * @Description : 로그인 세션정보 체크
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>로그인 세션정보 체크</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

 <%
 	// 세션 정보를 가져온다. LoginVO 
          aramframework.com.cmm.domain.LoginVO user = (aramframework.com.cmm.domain.LoginVO)aramframework.com.cmm.userdetails.UserDetailsHelper.getAuthenticatedUser();
          if(user.getUrl() == null || user.getUrl().equals("")) user.setUrl("/sym/mnu/mpm/MainMenuHome.do");
 %>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>로그인 세션정보 체크</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="loginSessionCheck" method="post" action="${pageContext.request.contextPath}/utl/sys/rsc/setLoginSession.do">

<div id="search_area">
	<div class="button_area">
   		<span class="button"><a href="${pageContext.request.contextPath}/utl/sys/rsc/loginSessionView.do" onclick="fncSelectLoginSession(); return false;"><spring:message code="button.inquire" /></a></span>
   		<span class="button"><input type="submit" value="설정" onclick="fncSetLoginSession(); return false;"></span>
   		<span class="button"><a href="<%=user.getUrl() %>" target=“_blank” title="새창" onclick="fncgetLoginSessionView('<%=user.getUrl() %>'); return false;">이동</a></span>
	</div>
</div>

<table class="table-list" summary="로그인 세션정보를 설정 및 체크한다.">
<caption>로그인 세션정보 체크</caption>
  	<tr>
    	<th width="20%">
    		URL
    		<span class="required_icon"></span>
    	</th>
    	<td>
    		<input name="url" id="url" type="text" maxLength="1000" size="80">
    	</td>
  	</tr>
  	<tr>
    	<th>
    		세션 URL
    		<span class="required_icon"></span>
    	</th>
   	 	<td>
   	 		<input name="sessionUrl" id="sessionUrl" value="<%=user.getUrl() %>" type="text" maxLength="1000" size="80" readOnly class="readOnlyClass">
   	 	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="proxySvc" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fncSelectLoginSession() {
    var varForm = document.getElementById("loginSessionCheck");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/rsc/loginSessionView.do";
    varForm.submit();
}

function fncSetLoginSession() {
    var varForm = document.getElementById("loginSessionCheck");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/rsc/setLoginSession.do";

    if(confirm("설정 하시겠습니까?")){
        varForm.submit();
    }
}

function fncgetLoginSessionView(url) {
    window.open(url,"notice","width=800, height=600, top=50, left=20, scrollbars=no, resizable=no");
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php?id=aramframework:%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%84%B8%EC%85%98%EC%A0%95%EB%B3%B4%EC%B2%B4%ED%81%AC";	
	window.open(url, "도움말");
}

</script>

</body>
</html>
