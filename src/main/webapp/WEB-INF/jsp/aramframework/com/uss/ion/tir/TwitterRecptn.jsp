<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TwitterRecptn.jsp
  * @Description : 트위터 수신(목록) 페이지
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
<title>트위터(Twitter)-수신</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="twitterInfo" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.twitterInfo.pageIndex.value = pageNo;
	document.twitterInfo.action = "${pageContext.request.contextPath}/uss/ion/tir/listTwitterRecptn.do";
   	document.twitterInfo.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail_TwitterRecptn(unityLinkId){
	var vFrom = document.twitterInfo; 
}

function numchk(){
	var value = document.twitterInfo.pageSize.value;
	if(value == ""){
		alert("숫자를 입력해주세요!");
		document.twitterInfo.pageSize.value="";
		document.twitterInfo.pageSize.focus();
		return false;
	}else if(isNaN(value)){
		alert("숫자만 입력이 가능합니다");
		document.twitterInfo.pageSize.value="";
		document.twitterInfo.pageSize.focus();
		return false;
	}else{
		return true;	
	}
}

</script>

<style type="text/css">
.txInput80 {
width : 80px;
}

#divVerify {
font-size : 12px;
color : red;
}
</style>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	트위터 수신(목록)
</div>

<form id="twitterInfo" name="twitterInfo" action="/uss/ion/tir/listTwitterRecptn.do" method="post">

<div id="search_area">
	<div id="search_area">
		<div class="button_area">
			<span class="button"><a href="#" onclick="if(numchk() == false){return false;}"><spring:message code="button.inquire" /></a></span>
		</div>
	</div>
	<div class="keyword_area">
		<label for="pageSize"> </label>
		<input name="pageSize" title="검색건수" type="text" size="3" value="<c:out value='${pageSize}'/>" maxlength="5" style="width:25px;IME-MODE: disabled">건
	</div>
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

<table class="table-list" summary="트위터 목록 을 제공한다.">
<caption>트위터 목록 을 제공한다</caption>
<thead>
  	<tr>  
    	<th scope="col" width="50px">프로필<br>이미지</th>
    	<th scope="col">내용</th>               
  	</tr>
</thead> 
<tbody>
	<!-- 데이터를 없을때 화면에 메세지를 출력해준다 -->
	 <c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	<!-- 데이터를 화면에 출력해준다 -->
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td class="lt_text3">
			<img src="${pageContext.request.contextPath}${resultInfo.twitterProfileImageURL}'/>" alt="${resultInfo.twitterNmae}프로필이미지" width="48px" height="48px">
		</td>
		<td class="lt_text3L">
			${resultInfo.twitterNmae}(${resultInfo.twitterCreatedAt})<br>
			<c:out value="${resultInfo.twitterText}" escapeXml="false" /><br>
			<c:if test="${resultInfo.twitterURL ne 'null'}">
			<font color="#0080C0"><a href="${pageContext.request.contextPath}${resultInfo.twitterURL}'/>" target="_blank"  title="새 창으로 이동">${pageContext.request.contextPath}${resultInfo.twitterURL}'/></a></font>
			</c:if>
		</td>
	</tr>
	</c:forEach>
</tbody>  
</table>

</DIV>
</body>
</html>