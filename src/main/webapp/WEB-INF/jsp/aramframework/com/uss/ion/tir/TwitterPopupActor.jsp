<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.*"%>
<%@ page import="twitter4j.http.*"%>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%
String sCONSUMER_KEY = request.getParameter("ConsumerKey") == null ? "": (String)request.getParameter("ConsumerKey");
String sCONSUMER_SECRET= request.getParameter("ConsumerSecret") == null ? "": (String)request.getParameter("ConsumerSecret");
String sCmd = request.getParameter("cmd") == null ? "": (String)request.getParameter("cmd");
String sAt = request.getParameter("at") == null ? "": (String)request.getParameter("at");

Twitter twitter = new TwitterFactory().getInstance();
twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
RequestToken requestToken = null;

try {
	requestToken = twitter.getOAuthRequestToken();
	
	if(requestToken != null){
	
	//out.println("<p><a href='http://twitter.com/oauth/authorize?oauth_token="
	//+ requestToken.getToken() + "' target='_blank'>인증하러가기 </a>");
	// 해당키 임시 저장
	session.setAttribute("rtoken",requestToken.getToken());
	session.setAttribute("rstoken",requestToken.getTokenSecret());
	
	session.setAttribute("sCONSUMER_KEY", sCONSUMER_KEY);
	session.setAttribute("sCONSUMER_SECRET", sCONSUMER_SECRET);
	
	//현재모드저장
	session.setAttribute("sTWITTER_SE", sCmd);
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터(Twitter)-인증</title>

<script type="text/javascript">
	location.href = 'http://twitter.com/oauth/authorize?oauth_token=<%=requestToken.getToken()%>';
</script>
</head>

<body>
</body>
</html>
<%
	}
//인증 실페 이전 페이지로 돌아가기
}catch(TwitterException e){
	
	out.println("<script type=\"text/javaScript\" language=\"javascript\">");
	if(sAt.equals("1")){
		out.println("location.href='/uss/ion/tir/selectTwitterPopup.do?cmd="+sCmd+"&verify=Y&at=1';");
	}else{
		out.println("location.href='/uss/ion/tir/selectTwitterPopup.do?cmd="+sCmd+"&verify=Y';");
	}
}
%>
