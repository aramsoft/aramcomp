<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ClubMsg.jsp
  * @Description : 동호회 관리 공통 메시지 처리화면
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
<title>승인(탈퇴) 요청</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<br/>
<div align="center">
<table>
	<tr>
		<td>
			<c:choose>
			<c:when test="${returnMsg==''}">
				<spring:message code="cop.request.msg" />
			</c:when>
			<c:when test="${returnMsg=='DEL_REQ_SUCCESS'}">
				<spring:message code="success.request.msg" />
			</c:when>
			<c:when test="${returnMsg=='ING'}">
				<spring:message code="cop.ing.msg" />
			</c:when>
			<c:otherwise>
				<spring:message code="common.isExist.msg" />
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
</div>

</div>
</body>
</html>
