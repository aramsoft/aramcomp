<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : TrsmrcvLogDetail.jsp
  * @Description : 송수신 로그 상세조회
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
<title>송수신 로그 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>송수신 로그 정보</h2>
</div>

<div id="search_area">
	<div class="button_area">
   		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="trsmrcvLogVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="requstId" />
	
<table class="table-detail" summary="요청ID, 발생일자, 송수신구분, 연계ID, 제공기관ID, 제공시스템ID, 제공서비스ID, 요청기관ID, 요청시스템ID, 요청자 정보를 보는 송수신 로그 상세보기 테이블이다.">
<CAPTION>송수신 로그 정보</CAPTION>
 	<tr>
    	<th width="20%">
    		요청ID
    	</th>
    	<td width="80%">
      		<c:out value="${trsmrcvLogVO.requstId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		발생일자
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.occrrncDe}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		송수신구분
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.trsmrcvSeCodeNm}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		연계ID
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.cntcId}"/>
   	 	</td>
  	</tr>
 	<tr>
    	<th>
    		제공기관ID
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.provdInsttId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		제공시스템ID
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.provdSysId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		제공서비스ID
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.provdSvcId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		요청기관ID
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.requstInsttId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		요청시스템ID
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.requstSysId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		요청자
    	</th>
    	<td>
      		<c:out value="${trsmrcvLogVO.rqesterNm}"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
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
    var varForm = document.getElementById("trsmrcvLogVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/tlg/listTrsmrcvLog.do";
    varForm.submit();
}	

</script>
	
</body>
</html>
