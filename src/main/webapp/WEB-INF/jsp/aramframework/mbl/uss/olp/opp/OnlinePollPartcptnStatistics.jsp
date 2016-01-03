<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>온라인POLL참여 통계</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

</head>

<body>
	 
<!-- View start -->
<div id="view" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:history.back();" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1 id="viewTitle">온라인POLL참여 통계</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">
			
		<form:form commandName="onlinePollPartcptnVO" method="post">
			<ul class="uss-hpcDetail">
				<li>	
					<span class="uss-tit">POLL명</span>
					<span class="uss-con">
		        		<c:out value="${onlinePollPartcptnVO.pollNm}" />
					</span>
				</li>
				
				<li>
					<span class="uss-tit">POLL시작일자 </span>
					<span class="uss-con">
						<label for="name-d" class="uss-gray">
							<c:out value="${onlinePollPartcptnVO.pollBeginDe}" />
						</label>
					</span>
				</li>
				<li>
					<span class="uss-tit">POLL종료일자 </span>
					<span class="uss-con">
						<label for="name-d" class="uss-gray">
							<c:out value="${onlinePollPartcptnVO.pollEndDe}" />
						</label>
					</span>
				</li>
				<li>
					<span class="uss-tit">POLL종류</span>
					<span class="uss-con">
						<c:forEach items="${pollKindCodeList}" var="result" varStatus="pollKindStatus">
							<c:if test="${result.code eq onlinePollPartcptnVO.pollKindCode}">
								<c:out value="${result.codeNm}" escapeXml="false" />
							</c:if>
						</c:forEach>
					</span>
				</li>
			
			</ul>
			<div class="uss-statistics">
				<c:set var="chartCheck" value="0" />
	   			<c:forEach items="${PollItemList}" var="resultInfo" varStatus="status2">
				<dl>	
					<dt><c:out value="${resultInfo.pollIemNm}" escapeXml="false" /></dt>
					<dd><c:forEach items="${statisticsList}" var="statisticsInfo" varStatus="status3">
   						<c:if test="${resultInfo.pollIemId eq statisticsInfo.pollIemId}">
   						<img src="${pageContext.request.contextPath}/images/aramframework/com/uss/olp/qnn/chart/chart${status2.count}.JPG" width="${statisticsInfo.pollIemPercent}px" height="8" alt="차트"> (${statisticsInfo.pollIemIdCnt})건
   						<c:set var="chartCheck" value="${chartCheck+1}" />
   						</c:if>
   						</c:forEach>
						<c:if test="${chartCheck eq '0'}">
		   				<img src="${pageContext.request.contextPath}/images/aramframework/com/uss/olp/qnn/chart/chart${status2.count}.JPG" width="1 px" height="8" alt="차트"> (0)건
		   				</c:if>
			   		</dd>
				</dl>
				<c:set var="chartCheck" value="${0}" />
				</c:forEach>
			</div>
		</form:form>
		
		<fieldset>
			<div class="com-addBgBtn">	
				<a href="#list" data-role="button" data-theme="b">목록</a>
			</div>
		</fieldset>
	</div>
	<!-- contents end -->
	 
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
						
</div>
<!-- view end -->
	
</body>
</html>