<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : WebLogDetail.jsp
  * @Description : 웹 로그 상세조회
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
<title>웹 로그 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>웹 로그 정보</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
 	  		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="webLogVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="requstId" />
	
<table class="table-detail">
 	<tr>
    	<th width="20%">
    		요청ID
    	</th>
    	<td width="80%">
      		<c:out value="${webLogVO.requstId}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		발생일자
    	</th>
    	<td>
      		<c:out value="${webLogVO.occrrncDe}"/>
    	</td>
  	</tr>
 	<tr>
    	<th> 
    		URL
    	</th>
    	<td>
      		<c:out value="${webLogVO.url}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		요청자
    	</th>
    	<td>
      		<c:out value="${webLogVO.rqesterNm}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		요청자IP
    	</th>
    	<td>
      		<c:out value="${webLogVO.rqesterIp}"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
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
    var varForm = document.getElementById("webLogVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/wlg/listWebLog.do";
    varForm.submit();	
}	

</script>
	
</body>
</html>
