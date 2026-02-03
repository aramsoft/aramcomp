<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : UserLogDetail.jsp
  * @Description : 사용자 로그 상세조회
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>사용자 로그 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>사용자 로그 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
 	  		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="userLogVO" action="" method="post"> 
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-detail">
 	<tr>
    	<th width="20%">
    		발생일자
    	</th>
    	<td width="80%">
      		<c:out value="${userLogVO.occrrncDe}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		사용자
    	</th>
    	<td>
      		<c:out value="${userLogVO.rqesterNm}"/>
    	</td>
  	</tr>
 	<tr>
   		<th>
   			서비스명
   		</th>
    	<td>
      		<c:out value="${userLogVO.srvcNm}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		메소드명
    	</th>
    	<td>
      		<c:out value="${userLogVO.methodNm}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		생성
    	</th>
    	<td>
      		<c:out value="${userLogVO.creatCo}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		수정
    	</th>
    	<td>
      		<c:out value="${userLogVO.updtCo}"/>
    	</td>
  	</tr>
	<tr>
    	<th>
    		조회
    	</th>
    	<td>
      		<c:out value="${userLogVO.rdCnt}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		삭제
    	</th>
    	<td>
      		<c:out value="${userLogVO.deleteCo}"/>
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
    var varForm = document.getElementById("userLogVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/ulg/listUserLog.do";
    varForm.submit();	
}	

</script>

</body>
</html>
