<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String requestUrl = request.getRequestURL().toString();
	if( requestUrl.startsWith("http://m.aramsoft.co.kr/") ) {
%>
<jsp:forward page="/apps/mbl/aramht"/> 
<%  } else if( requestUrl.startsWith("http://www.aramsoft.co.kr/") ) { %>
<jsp:forward page="/apps/aramht"/>
<%  } else { %>
<jsp:forward page="/UnitMain.do"/>
<%  } %> 