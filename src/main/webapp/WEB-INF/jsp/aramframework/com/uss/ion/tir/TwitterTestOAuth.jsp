<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.*"%>
<%@ page import="twitter4j.http.*"%>
<%@ page import="twitter4j.util.*"%>
<%@ page import="twitter4j.api.*"%>
<%
 /**
  * @Class Name : TwitterTestOAuth.jsp
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
//========================================================================
//수신된 트위터 결과
//========================================================================
Twitter twitterR = new TwitterFactory().getInstance();
twitterR.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
String oauthToken = request.getParameter("oauth_token");
// 2011.10.10 보안점검 후속조치
String rtoken = "";
if (rtoken != null) {
    rtoken = session.getAttribute("rtoken").toString().trim();
}
String rstoken = "";
if (rstoken != null) {
    rstoken = session.getAttribute("rstoken").toString().trim();
}
//2011.10.10 보안점검 후속조치 끝
AccessToken accessToken = null;
if(rtoken.equals(oauthToken)){
	try {
		accessToken = twitterR.getOAuthAccessToken(oauthToken, rstoken);
	}catch(TwitterException te) {
		//out.println("<br>" + te.toString());
		System.out.println(te);	// 2011.10.10 보안점검 후속조치
	}
}

try{
	if (accessToken !=null) {	// 2011.10.21 보안검검 후속조치
		twitterR.setOAuthAccessToken(accessToken);
		//해당키 저장
		session.setAttribute("atoken", accessToken.getToken());
		session.setAttribute("astoken", accessToken.getTokenSecret());
	}
}catch(Exception e) {
	//out.println("<br>" + e.toString());
	//e.printStackTrace();
	System.out.println(e);	// 2011.10.10 보안점검 후속조치
}

//========================================================================
//보내기 테스트
//========================================================================
String tweetStsText ="Twitter OAuth TEST";

String atoken = session.getAttribute("atoken").toString().trim();
String astoken = session.getAttribute("astoken").toString().trim();
try {
	AccessToken accessTokenA = new AccessToken(atoken, astoken);
	Twitter twitterA = new TwitterFactory().getOAuthAuthorizedInstance(
			sCONSUMER_KEY, sCONSUMER_SECRET, accessTokenA);
	Status status = twitterA.updateStatus(tweetStsText);
	String result_msg = status.getText();
}catch (TwitterException e) {
	//e.printStackTrace();
	System.out.println(e);	// 2011.10.10 보안점검 후속조치
}

out.println("<br>");
out.println("rtoken>"+session.getAttribute("atoken"));
out.println("<br>");
out.println("rstoken>"+session.getAttribute("astoken"));
out.println("<br>");

%>
<html lang="ko">
<head>
<title>트위터(Twitter)-수신</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>

<body>
<DIV id="content" style="width:712px">
<!-- noscript 테그 -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

TEST
</DIV>
</body>
</html>