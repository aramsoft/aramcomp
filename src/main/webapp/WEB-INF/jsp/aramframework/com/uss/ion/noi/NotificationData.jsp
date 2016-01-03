<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/**
 * @Class Name : NotificationData.jsp
 * @Description : 정보알림이 표시XML 화면
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
<root>
<c:forEach var="result" items="${resultList}" varStatus="status">
	<list>
		<time><c:out value='${result.ntfcTime}'/></time>
		<title><c:out value='${result.ntfcSj}'/></title>
		<contents><c:out value='${result.ntfcCn}'/></contents>
	</list>
</c:forEach>
</root>
