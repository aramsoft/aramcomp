<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="twitter4j.*"%>
<%@ page import="twitter4j.Paging"%>
<%@ page import="twitter4j.Status"%>
<%@ page import="twitter4j.http.*"%>
<%@ page import="aramframework.com.cmm.util.WebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TwitterNormalAuth.jsp
  * @Description : 트위터 Non-Authenticate 수신(목록)
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
	int nPageSize = request.getParameter("pageSize") == null ? 20: Integer.parseInt((String)request.getParameter("pageSize"));
	String sTwitterId = request.getParameter("twitter_id") == null ? "egovframe": (String)request.getParameter("twitter_id");

	sTwitterId = WebUtil.clearXSSMinimum(sTwitterId);		// 2011.10.25 보안점검 후속조치

	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	try{
		 Twitter twitter = new TwitterFactory().getInstance();
	
	 	 //트위터 페이징 객체
	     Paging twitterPage = new Paging();
	     //페이지 갯수 설정
	     twitterPage.count(nPageSize);
	     //인덱스 설정
	     twitterPage.setPage(1);
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터(Twitter)-목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript">

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
	<h2>트위터 Non-Authenticate 수신(목록)</h2>
</div>

<!-- LIST 출력 -->
<form id="twitterInfo" name="twitterInfo" action="/PageLink.do?link=aramframework/com/uss/ion/tir/TwitterNormalAuth" method="post">

<div id="search_area">
	<div id="search_area">
		<div class="button_area">
			<span class="button"><a href="#" onclick="if(numchk() == false){return false;}"><spring:message code="button.inquire" /></a></span>
		</div>
	</div>
	<div class="keyword_area">
		<label for="twitter_id">아이디:</label>
		<input name="twitter_id" title="검색건수" type="text" size="3" value="<c:out value='<%=sTwitterId %>'/>" maxlength="255" style="width:80px;">
		<label for="pageSize"></label>
		<input name="pageSize" title="검색건수" type="text" size="3" value="<c:out value='<%=nPageSize %>' />" maxlength="5" style="width:25px;IME-MODE: disabled">건
	</div>
</div>
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
<%
List<Status> statuses = twitter.getUserTimeline(sTwitterId, twitterPage);
if(statuses == null){
%>
	<tr>
		<td class="lt_text3" colspan="2">
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
<%}else{ %>
<!-- 데이터를 화면에 출력해준다 -->
<%
	for (Status status : statuses) {
		//2010.10.27 3차 보안 점검 조치 사항 반영
	 	String profileImageUrl = status.getUser().getProfileImageURL().toString();
		if(profileImageUrl != null){
			profileImageUrl = profileImageUrl.replaceAll("<","&lt;");
			profileImageUrl = profileImageUrl.replaceAll(">","&gt;");
		}else{
			profileImageUrl = "";
		}
%>
	<tr>
		<td class="lt_text3">
			<img src="<%=profileImageUrl %>" alt="<%=status.getUser().getName()%> 프로필이미지" width="48px" height="48px">
		</td>
		<td class="lt_text3L">
			<%=status.getUser().getName()%>(<%=status.getUser().getScreenName()%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=format.format(status.getCreatedAt())%><br>
			<c:out value="<%=status.getText()%>" /><br>
			<%if(status.getUser().getURL() != null){%>
				<font color="#0080C0"><a href="<%=status.getUser().getURL()%>" target="_blank"  title="새 창으로 이동"><%=status.getUser().getURL()%></a></font>
			<%}%>
		</td>
	</tr>
	<%} %>
<%} %>

</tbody>
</table>

</DIV>
</body>
</html>
<%
	}catch(TwitterException e){
%>
	<spring:message code="common.nodata.msg" />
<%
	}catch(Exception e){
		System.out.println(e);	// 2011.10.10 보안점검 후속조치
	}finally{
	}
%>