<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.*"%>
<%@ page import="twitter4j.http.*"%>
<%
 /**
  * @Class Name : TwitterTest.jsp
  * @Description : 
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
String sCONSUMER_KEY = "jZolrlAWY2wgseQjukGw";
String sCONSUMER_SECRET = "y2alndDN9R3VDU9wmEabmnhfacwVDqfVIrWYwMeFGSw";

Twitter twitter = new TwitterFactory().getInstance();
twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);

RequestToken requestToken = null;
try {
	requestToken = twitter.getOAuthRequestToken();
	out.println("<p><a href='http://twitter.com/oauth/authorize?oauth_token="
	+ requestToken.getToken() + "' target='_blank' title='새 창으로 이동'>인증하러가기 </a>");
	// 해당키 임시 저장
	session.setAttribute("rtoken",requestToken.getToken());
	session.setAttribute("rstoken",requestToken.getTokenSecret());
}catch(TwitterException e) {
	//e.printStackTrace();
	System.out.println(e.getMessage());		// 2011.10.21 보안점검 후속조치
}
out.println("<br>");
if (requestToken != null) {	// 보안점검 후속조치
	out.println("rtoken>"+requestToken.getToken());
}
out.println("<br>");
if (requestToken != null) {	// 보안점검 후속조치
	out.println("rstoken>"+requestToken.getTokenSecret());
}
out.println("<br>");
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터(Twitter)-수신</title>
</head>

<body>
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

TEST

</DIV>
</body>
</html>
