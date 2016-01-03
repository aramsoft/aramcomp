<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.Status"%>
<%@ page import="twitter4j.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TwitterTrnsmitResult.jsp
  * @Description : 트위터 송신(등록) 페이지
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
<%
Status status = (Status)request.getAttribute("status");
User user = (User)status.getUser();
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터(Twitter)-송신(등록)결과확인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>트위터 송신 결과</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="/uss/ion/tir/registTwitterTrnsmit.do" >확인</a></span>
	</div>
</div>
 
<!--  등록  폼 영역  -->
<table class="table-register" summary="트위터  입력을 제공한다..">
	<tr> 
		<th width="20%">
			등록ID&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=status.getId() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			계정ID&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=user.getScreenName() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			별명&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=user.getName() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			URL
		</th>
		<td width="80%" colspan="3">
			<%=user.getURL() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			트위터 내용&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=status.getText() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			Profile Image
		</th>
		<td width="80%" colspan="3">
			<img src="<%=user.getProfileImageURL() %>" title="profileImageUrl" alt="profileImageUrl">
		</td>
	</tr>
	<%
	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	%>
	<tr> 
		<th width="20%">
			등록일
		</th>
		<td width="80%" colspan="3">
			<%=format.format(user.getCreatedAt()) %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			favorite&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=status.isFavorited() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			retweet&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=status.isRetweet() %>
		</td>
	</tr>
	<tr> 
		<th width="20%">
			truncated&nbsp;
		</th>
		<td width="80%" colspan="3">
			<%=status.isTruncated() %>
		</td>
	</tr>
</table>

</DIV>
</body>
</html>