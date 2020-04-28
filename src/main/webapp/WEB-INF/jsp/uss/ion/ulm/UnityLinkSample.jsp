<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : UnityLinkSample.jsp
 * @Description : 통합링크
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
<title>통합링크</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:250px;">

<form name="formUnityLink" action="" method="post" target="_blank">

<table class="table-register" summary="통합링크 사이트를 표시한다.">
<thead>
	<tr>
		<th style="text-align:center;">통합링크</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>
		<div id="divUnityLink1" style="float: left;">
	  		<select name="selUnityLink" id="selUnityLink" style="width:190px;" title="통합링크선택">
		   		<option value=''>--선택하세요--</option>
		   		<c:forEach items="${resultList}" var="result" varStatus="status">
		   		<option value='${result.unityLinkUrl}'>${result.unityLinkNm}</option>
		   		</c:forEach>
	   		</select>
	   	</div>
	    <span class="button"><a href="#" onclick="javascript:fn_aram_link(); return false;">이동</a></span>
		</td>
	</tr>
</tbody>
</table>

</form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_link(){

	var sLink = fn_aram_SelectBoxValue("selUnityLink");
	if(sLink == ""){
		alert("통합링크를 선택해주세요!");
		document.formUnityLink.selUnityLink.focus();
		return;
	}else{
		document.formUnityLink.action=sLink;
		document.formUnityLink.submit();
	}
}

</script>

</body>
</html>