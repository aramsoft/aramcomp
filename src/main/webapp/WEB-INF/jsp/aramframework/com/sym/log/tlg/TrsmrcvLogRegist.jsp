<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TrsmrcvLogRegist.jsp
  * @Description : 송수신 로그 등록
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
<title>송수신 로그 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>송수신 로그 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert('S01'); return false;">전송요청</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert('S02'); return false;">전송완료</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert('S03'); return false;">전송실패</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert('S04'); return false;">수신요청</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert('S05'); return false;">수신완료</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert('S06'); return false;">수신실패</a></span>
  		<span class="button"><a href="#" onclick="javascript:fn_aram_list_(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="trsmrcvLogVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="trsmrcvSeCode" />
	
<table class="table-register" summary="연계ID, 제공기관ID, 제공시스템ID, 제공서비스ID, 요청기관ID, 요청시스템ID를 입력하여 송수신 테스트를 하는 테이블이다.">
<CAPTION>송수신 테스트</CAPTION>
 	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="cntcId">연계ID</label>
    	</th>
    	<td width="80%">
      		<form:input path="cntcId" size="60" value="INT1" maxlength="4" />
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="provdInsttId">제공기관ID</label>
    	</th>
    	<td>
      		<form:input path="provdInsttId" size="60" value="PI01" maxlength="4" />
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="provdSysId">제공시스템ID</label>
    	</th>
    	<td>
      		<form:input path="provdSysId" size="60" value="PS01" maxlength="4" />
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="provdSvcId">제공서비스ID</label>
    	</th>
    	<td>
      		<form:input path="provdSvcId" size="60" value="PV01" maxlength="4" />
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="requstInsttId">요청기관ID</label>
    	</th>
    	<td>
      		<form:input path="requstInsttId" size="60" value="RI01" maxlength="4" />
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="requstSysId">요청시스템ID</label>
    	</th>
    	<td>
      		<form:input path="requstSysId" size="60" value="RS01" maxlength="4" />
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("trsmrcvLogVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/listTrsmrcvLog.do";
    varForm.submit();
}

function fn_aram_insert(pTrsmrcvSeCode){
    var varForm = document.getElementById("trsmrcvLogVO");
    varForm.trsmrcvSeCode.value = pTrsmrcvSeCode;
    varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/insertTrsmrcvLog.do";
    varForm.submit();
}

</script>

</body>
</html>
