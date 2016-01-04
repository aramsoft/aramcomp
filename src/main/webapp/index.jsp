<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String requestUrl = request.getRequestURL().toString();
	if( requestUrl.startsWith("http://m") ) {
%>
<jsp:forward page="/UnitMain.mdo"/> 
<%  } else { %>
<jsp:forward page="/UnitMain.do"/>
<%  } %> 