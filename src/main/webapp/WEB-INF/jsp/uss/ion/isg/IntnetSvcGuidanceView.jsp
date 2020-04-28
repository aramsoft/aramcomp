<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : IntnetSvcGuidanceView.jsp
 * @Description : 인터넷서비스안내
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>인터넷서비스안내 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<table class="table-list">
    <c:forEach var="result" items="${resultList}" varStatus="status">
    <tr>
        <th><c:out value="${result.intnetSvcNm}"/></th>
    </tr>
    <tr>
        <td>${result.intnetSvcDc}</td>
    </tr> 
    </c:forEach>
</table>

</DIV>
</body>
</html>
 <!-- 
    <c:forEach var="intnetSvcGuidance" items="${intnetSvcGuidanceList}" varStatus="status">
        <p><c:out value="${intnetSvcGuidance.intnetSvcNm}"/></p>
        <p><c:out value="${intnetSvcGuidance.intnetSvcDc}"/></p>
    </c:forEach>
 -->    
