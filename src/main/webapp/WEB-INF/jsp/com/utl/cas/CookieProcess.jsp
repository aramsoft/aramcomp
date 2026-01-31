<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ page import="aramframework.com.utl.cas.service.SessionCookieUtil"  %>
<%@ page import="java.util.*"  %>
<%
 /**
  * @Class Name : CookieProcess.jsp
  * @Description : 쿠키생성, 등록, 사용, 폐기하는 기능 제공
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
<%!String safeGetParameter (HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (value == null) {
            value = "";
        }
        return value;
    }%>

<%
	// 준비화면, 실행결과 출력화면의 구분
String execFlag = safeGetParameter(request,"execFlag");
if(execFlag==null || execFlag.equals("")) {
	execFlag="READY";
}
%>
<%
	if(execFlag.equals("READY")){
	// 실행을 위한 화면 준비
	System.out.println("READY");
%>

<!-- 준비화면  시작-->
<form name="fm108_1" action ="/PageLink.do" method=post>
<input type = "hidden" name="execFlag" value="REQ-COM-108_1">
<input type = "hidden" name="cmdStr" value="REQ-COM-108">
<input type = "hidden" name="link" value="cmm/utl/CookieProcess">
<table border="1">
	<tr>
		<td>
		기능설명:
		</td>
		<td>
		    쿠키생성 &nbsp;&nbsp;&nbsp;<br>
		</td>
		<td>
		    쿠키명:<input type = "text" name="cookieNm"  size=10 title="쿠키명">
		</td>
		<td>
		    쿠키값:<input type = "text" name="cookieVal"  size=10 title="쿠키값">
		</td>
		<td> 
			<input type = "button" value="실행!" onclick="fm108_1.submit()">
		</td>				
	</tr>
</table>
</form>
<!--  준비화면 끝 -->

<!-- 준비화면  시작-->
<form name="fm108_2" action ="/PageLink.do" method=post>
<input type = "hidden" name="execFlag" value="REQ-COM-108_2">
<input type = "hidden" name="cmdStr" value="REQ-COM-108">
<input type = "hidden" name="link" value="cmm/utl/CookieProcess">
<table border="1">
	<tr>
		<td>
		기능설명:
		</td>
		<td>
		    쿠키값 얻기 <br>
		</td>
		<td>
		    쿠키명:<input type = "text" name="cookieNm"  size=10 title="쿠키명">
		</td>
		<td>
		</td>
		<td> 
			<input type = "button" value="실행!" onclick="fm108_2.submit()">
		</td>				
	</tr> 	 
</table>
</form>
<!--  준비화면 끝 -->

<!-- 준비화면  시작-->
<form name="fm108_3" action ="/PageLink.do" method=post>
<input type = "hidden" name="execFlag" value="REQ-COM-108_3">
<input type = "hidden" name="cmdStr" value="REQ-COM-108">
<input type = "hidden" name="link" value="cmm/utl/CookieProcess">
<table border="1">
	<tr>
		<td>
		기능설명:
		</td>
		<td>
		    쿠키삭제 &nbsp;&nbsp;&nbsp;<br>
		</td>
		<td>
		    쿠키명:<input type = "text" name="cookieNm"  size=10 title="쿠키명">
		</td>
		<td> 
			<input type = "button" value="실행!" onclick="fm108_3.submit()">
		</td>				
	</tr>
</table>
</form>
<!--  준비화면 끝 -->


<%
	}else if(execFlag.equals("REQ-COM-108_1")){
%>

<%
	//실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인

String cookieNm = safeGetParameter(request,"cookieNm");
String cookieVal = safeGetParameter(request,"cookieVal");

SessionCookieUtil.setCookie(response, cookieNm, cookieVal);
%>

<!-- 결과화면 시작 -->
<form name="fm108_1" action ="/PageLink.do" method=post>
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" 	value="REQ-COM-108">
<input type = "hidden" name="link" value="cmm/utl/CookieProcess">
<table border="1">
   	<tr>
   		<td>쿠키생성 : 
   		</td>
   		<td>쿠키명: <%=cookieNm%> &nbsp;&nbsp;
   		</td>
   		<td>쿠키값: <%=cookieVal%> &nbsp;&nbsp;
   		</td>
   	</tr>
</table> 
<br>
<input type = "button" value="화면으로 돌아가기" onclick="fm108_1.submit()">
</form>
<!--  결과화면 끝 -->


<%
	}else if(execFlag.equals("REQ-COM-108_2")){
%>

<%
	//실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인

String cookieNm = safeGetParameter(request,"cookieNm");

String	resultStr = SessionCookieUtil.getCookie(request, cookieNm);
%>

<!-- 결과화면 시작 -->
<form name="fm108_2" action ="/PageLink.do" method=post>
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" 	value="REQ-COM-108">
<input type = "hidden" name="link" value="cmm/utl/CookieProcess">
<table border="1">
   	<tr>
   		<td>쿠키생성, 등록, 사용, 폐기하는 기능 제공 : 
   		</td>
   		<td>쿠키명: <%=cookieNm%> &nbsp;&nbsp;
   		</td>
   		<td>쿠키값: <%=resultStr%> &nbsp;&nbsp;
   		</td>
   	</tr>
</table> 
<br>
<input type = "button" value="화면으로 돌아가기" onclick="fm108_2.submit()">
</form>
<!--  결과화면 끝 -->


<%
	}else if(execFlag.equals("REQ-COM-108_3")){
%>

<%
	//실행결과 출력화면인 경우 결과정보 확인 - util 형태로 바로 확인

String cookieNm = safeGetParameter(request,"cookieNm");

// 쿠키값 세팅
SessionCookieUtil.setCookie(response, cookieNm);
%>

<!-- 결과화면 시작 -->
<form name="fm108_3" action ="/PageLink.do" method=post>
<input type = "hidden" name="execFlag" value="READY">
<input type = "hidden" name="cmdStr" 	value="REQ-COM-108">
<input type = "hidden" name="link" value="cmm/utl/CookieProcess">
<table border="1">
   	<tr>
   		<td>쿠키삭제&nbsp;&nbsp;&nbsp;&nbsp; : 
   		</td>
   		<td>쿠키명: <%=cookieNm%> &nbsp;&nbsp;
   		</td>
   		<td>쿠키값: 
   		</td>
   	</tr>
</table> 
<br>
<input type = "button" value="화면으로 돌아가기" onclick="fm108_3.submit()">
</form>
<!--  결과화면 끝 -->

<%
}
%>

