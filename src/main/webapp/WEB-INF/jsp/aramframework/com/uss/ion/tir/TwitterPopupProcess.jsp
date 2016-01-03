<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="twitter4j.*"%>
<%@ page import="twitter4j.http.*"%>
<%@ page import="twitter4j.util.*"%>
<%@ page import="twitter4j.api.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TwitterPopupProcess.jsp
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
//조회 모드 세션에서 가져오기
String sCmd = session.getAttribute("sTWITTER_SE") == null ? "": (String)session.getAttribute("sTWITTER_SE");

String sCONSUMER_KEY = session.getAttribute("sCONSUMER_KEY").toString().trim();
String sCONSUMER_SECRET = session.getAttribute("sCONSUMER_SECRET").toString().trim();

Twitter twitter = new TwitterFactory().getInstance();
twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
String oauthToken = request.getParameter("oauth_token");

String rtoken = session.getAttribute("rtoken").toString().trim();
String rstoken = session.getAttribute("rstoken").toString().trim();

AccessToken accessToken = null;
if(rtoken.equals(oauthToken)){
	try {
		accessToken = twitter.getOAuthAccessToken(oauthToken, rstoken);
	}catch(TwitterException te) {
		//out.println("<br>" + te.toString());
		System.out.println(te);	// 2011.10.10 보안점검 후속조치
	}
}

try{
	twitter.setOAuthAccessToken(accessToken);
	//해당키 저장
	if (accessToken != null) {	// 2011.10.21 보안점검 후속조치
		session.setAttribute("atoken", accessToken.getToken());
		session.setAttribute("astoken", accessToken.getTokenSecret());
	}
	
}catch(Exception e) {
	//out.println("<br>" + e.toString());
	//e.printStackTrace();
	System.out.println(e);	// 2011.10.10 보안점검 후속조치
}

%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터(Twitter)-인증</title>

<script type="text/javascript">

<%if(sCmd.equals("RECPTN")){%>
	opener.location.href = '/uss/ion/tir/listTwitterRecptn.do';
<%}else{%>
	opener.location.href = '/uss/ion/tir/registTwitterTrnsmit.do';
<%}%>

window.close();

</script>
</head>

<body>
</body>
</html>
