<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String trgetId = request.getParameter("trgetId") == null? "" : (String)request.getParameter("trgetId");
	String requestUrl = request.getRequestURL().toString();
	if( !"".equals(trgetId) ) {
%>
<% response.sendRedirect("/cop/cmy/CmmntyMainPage.mdo?cmmntyId="+trgetId); %> 
<%	} else if( requestUrl.startsWith("http://m.aramsoft.co.kr/") ) { %>
<jsp:forward page="/apps/mbl/aramht"/> 
<%  } else { %>
<jsp:forward page="/UnitMain.mdo"/> 
<%  } %> 