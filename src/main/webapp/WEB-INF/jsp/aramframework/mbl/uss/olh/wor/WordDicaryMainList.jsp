<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>용어사전 목록조회</title>
	
</head>

<body>

<div>
	<ul data-inset="true">
		<c:choose>
			<c:when test="${empty wordDicaryList}">
				<li><spring:message code="common.nodata.msg"/></li>
			</c:when>
			<c:otherwise>
				<c:forEach items="${wordDicaryList}" var="wordDicary" varStatus="status">
					<li class="uss-myList">
						<a href="${pageContext.request.contextPath}/uss/olh/wor/detailWordDicary.mdo?wordId=${wordDicary.wordId}">
							<dl class="uss-mylistView">
								<dt>${wordDicary.wordNm}</dt>
								<dd><strong>${wordDicary.engNm}</strong><br>
									${wordDicary.wordDc}</dd>
							</dl>
						</a>
					</li>
				</c:forEach>				
			</c:otherwise>	
		</c:choose>
	</ul>
</div>

<div class="uss-btnG">
	<a href="${pageContext.request.contextPath}/uss/olh/wor/listWordDicary.mdo" data-role="none" data-ajax="false" class="btnGogo">바로가기</a>
</div>

</body>
	
</html>
