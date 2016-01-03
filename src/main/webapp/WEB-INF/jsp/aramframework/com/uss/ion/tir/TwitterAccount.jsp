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
  * @Class Name : TwitterAccount.jsp
  * @Description : 트위터(Twitter)-인증키ConsumerKey/ConsumerSecret) 관리
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
String sConsumerKey = request.getAttribute("consumerKey") == null ? "": (String)request.getAttribute("consumerKey");
String sConsumerSecret = request.getAttribute("consumerSecret") == null ? "": (String)request.getAttribute("consumerSecret");
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터 인증키ConsumerKey/ConsumerSecret) 관리</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript">

/* ********************************************************
* 전송 체크
******************************************************** */
function fn_aram_search_TwitterAccount(){
	var vFrom = document.twitterInfo;

	if(vFrom.ConsumerKey.value == ""){
		alert("Consumer key 를 입력해주세요!");
		vFrom.ConsumerKey.focus();
		return;
	}

	if(vFrom.ConsumerSecret.value == ""){
		alert("Consumer secret 를 입력해주세요!");
		vFrom.ConsumerSecret.focus();
		return;
	}

	vFrom.submit();
}

</script>

<style type="text/css">
.txInput80 {
width : 99%;
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
	<h2>트위터 인증키(ConsumerKey/ConsumerSecret) 관리</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search_TwitterAccount(); return false;">저장</a></span>
	</div>
</div>

<form id="twitterInfo" name="twitterInfo" action="/uss/ion/tir/selectTwitterAccount.do" method="post" enctype="multipart/form-data">

<!--  등록폼  -->
<table class="table-register" summary="트위터  입력을 제공한다">
<caption>트위터  입력을 제공한다</caption>
	<tr> 
		<th scope="row" width="20%"  class="column_title">
			<span class="required_icon"></span>
			<label for="ConsumerKey">Consumer key</label>
		</th>
		<td width="80%">
			<input type="text" id="ConsumerKey" name="ConsumerKey" title="ConsumerKey" value="<%=sConsumerKey %>" size="10" maxlength="255" class="txInput80">
		</td>
	</tr>
	<tr> 
		<th scope="row" width="20%"  class="column_title">
			<span class="required_icon"></span>
			<label for="ConsumerSecret">Consumer secret</label>
		</th>
		<td width="80%">
			<input type="password" id="ConsumerSecret" name="ConsumerSecret" title="ConsumerSecret" value="<%=sConsumerSecret %>" size="10" maxlength="255" class="txInput80">
		</td>
	</tr>
</table>

<input type="hidden" name="cmd" value="">
</form>
<br>
<br>

<div class="content_title">
	<h2>트위터 OAuth 인증키 발급절차</h2>
</div>

<table class="table-register" summary="인증키 발급 절차를 제공한다">
<caption>인증키 발급 절차를 제공한다</caption>
	<tr>
		<th scope="col"> </th>
		<th scope="col"> </th>
	</tr>
	<tr>
		<td colspan="2">
			<b>아람소프트 트위터계정</b><br>
			테스트 Consumer key : FMVfKIa43csF9O6iNpISg<br>
			테스트 Consumer secret : rKSL4U7AOvYQ08cJ5E9R0OR5hgzlGPLH8JbbkXuQmo<br><br>
			<b>전자정부 트위터계정</b><br>
			테스트 Consumer key : saaeVLrxerfGqBaTO2SQQ<br>
			테스트 Consumer secret : wKMiMTnO44Bs4znEkx9NP1Aag4klPX5tisfccrokdxg<br>
		</td>
	</tr>
	<tr>
		<td width="20px" valign="top">1)</td>
		<td><a href="http://twitter.com/apps" target="_blank"  title="새 창으로 이동">http://twitter.com/apps</a> 트위터 사이트에 로그인한다.</td>
	</tr>
	<tr>
		<td width="20px" valign="top">2)</td>
		<td>
			Register a new application&gt;&gt; 버튼을 클릭하고 등록 페이지에 아래와 같이 트위터 어플리케이션 서비스를 등록한다.<br>
			<img src="/images/aramframework/com/cmm/tir/twitter_regist.jpg" alt="트위터 어플 등록 이미지" title="트위터 어플 등록 이미지">
			<br>※ Callback URL(아람소프트): http://www.aramsoft.co.kr:9090/uss/ion/tir/selectTwitterPopupProcess.do
			<br>※ Callback URL(전자정부): http://common.egovframe.go.kr/uss/ion/tir/selectTwitterPopupProcess.do
			<br>(키 발급 후 리턴되는 URL)<br><br>
		</td>
	</tr>
	<tr>
		<td width="20px" valign="top">3)</td>
		<td> 
			Consumer key, Consumer secret 등이 아래와 같이 발급된다.<br> 
			<img src="/images/aramframework/com/cmm/tir/twitter_regist_detail.jpg" alt="트위터 어플 등록 완료 이미지" title="트위터 어플 등록 완료 이미지">
		</td>
	</tr>
</table>

</DIV>
</body>
</html>
