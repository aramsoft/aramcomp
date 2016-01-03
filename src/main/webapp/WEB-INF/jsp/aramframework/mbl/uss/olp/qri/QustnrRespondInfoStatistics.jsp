<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>설문문항 통계 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>	
       
</head>

<body>
	
<!-- View start -->
<div id="staticsView" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:history.back();" data-icon="arrow-l" >뒤로</a>
	    <h1 id="viewTitle">설문문항 통계</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">
			
		<ul class="uss-hpcDetail">
		
			<li>
				<span class="uss-tit">설문제목 </span>
				<span class="uss-con gray">
					<strong><c:out value="${comtnqestnrinfo[0].qestnrSj}" /></strong>
				</span>
			</li>
			
			<li>
				<span class="uss-tit">설문목적 </span>
				<span class="uss-con gray">
					<strong><c:out value="${comtnqestnrinfo[0].qestnrPurps}" /></strong>
				</span>
			</li>
			
			<li>
				<span class="uss-tit">설문안내내용 </span>
				<span class="uss-con gray">
					<strong><c:out value="${comtnqestnrinfo[0].qestnrWritngGuidanceCn}" /></strong>
				</span>
			</li>
			
			<li>
				<span class="uss-tit">설문대상 </span>
				<span class="uss-con gray">
					<strong>
						<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '1'}">학생</c:if>
						<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '2'}">대학생</c:if>
						<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '3'}">직장인</c:if>
						<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '4'}">군인</c:if>
						<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '5'}">교사</c:if>
						<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '6'}">기타</c:if>
					</strong>
				</span>
			</li>
									
			<li>
				<span class="uss-tit">설문기간 </span>
				<span class="uss-con gray">
					<c:out value="${comtnqestnrinfo[0].qestnrBeginDe} ~ ${comtnqestnrinfo[0].qestnrEndDe}" />
				</span>
			</li>
			
		</ul>
		
		<div id="accordion">
			<c:forEach items="${comtnqustnrqesitm}" var="qestmInfo" varStatus="status1">
			<c:set var="chartCount" value="1" />
			<dl class="uss-qustnStatis">
				<dt>${status1.count}. <c:out value="${fn:replace(qestmInfo.qestnCn , crlf , '<br/>')}" escapeXml="false" /></dt>
				<dd>
					<%-- 객관식 --%>
					<c:if test="${qestmInfo.qestnTyCode ==  '1'}">
						<c:forEach items="${comtnqustnriem}" var="qestmItem" varStatus="status2">
							<c:set var="chartCheck" value="0" />
								<dl class="uss-qusAnswer">
								<c:if test="${qestmInfo.qestnrTmplatId eq qestmItem.qestnrTmplatId && qestmInfo.qestnrId eq qestmItem.qestnrId && qestmInfo.qestnrQesitmId eq qestmItem.qestnrQesitmId}">
								<dt><c:out value="${fn:replace(qestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" /></dt>
								<dd>	
									<c:forEach items="${qestnrStatistic1}" var="qestmStatistic1" varStatus="status3">
				    					<c:if test="${qestmInfo.qestnrTmplatId eq qestmStatistic1.qestnrTmplatId && qestmInfo.qestnrId eq qestmStatistic1.qestnrId && qestmStatistic1.qestnrQesitmId eq qestmItem.qestnrQesitmId && qestmStatistic1.qustnrIemId eq qestmItem.qustnrIemId}">
				    						<img src="${pageContext.request.contextPath}/images/aramframework/com/uss/olp/qnn/chart/chart${chartCount}.JPG" width="${qestmStatistic1.qustnrPercent}px" height="8"> ${qestmStatistic1.qustnrPercent}%							    					
				    						<c:set var="chartCheck" value="${chartCheck+1}" />
				    					</c:if>
				    				</c:forEach>

				    				<c:if test="${chartCheck eq 0}">
				    					<img src="${pageContext.request.contextPath}/images/aramframework/com/uss/olp/qnn/chart/chart${chartCount}.JPG" width="0 px" height="8"> 0%
				    				</c:if>
				    			</dd>
						    	<c:set var="chartCount" value="${chartCount+1}" />	
								</c:if>
								</dl>
						</c:forEach>
					</c:if>
					
					<%-- 주관식 --%>
			   		<c:if test="${qestmInfo.qestnTyCode ==  '2'}">
			   			<dl class="uss-qusAnswer">
			   			<c:choose>
			   				<c:when test="${empty qestnrStatistic2}">
			   					<dt>결과가 없습니다.</dt>
			   				</c:when>
			   				<c:otherwise>
					   			<c:forEach items="${qestnrStatistic2}" var="qestmStatistic2" varStatus="status4">
					   			<dd>
				    				<c:if test="${qestmInfo.qestnrTmplatId eq qestmStatistic2.qestnrTmplatId && qestmInfo.qestnrId eq qestmStatistic2.qestnrId && qestmInfo.qestnrQesitmId eq qestmStatistic2.qestnrQesitmId}">
					    				<p class="uss-paddT5"><strong><c:out value="${fn:replace(qestmStatistic2.respondNm , crlf , '<br/>')}" escapeXml="false" /></strong></p>
					    				<p><c:out value="${fn:replace(qestmStatistic2.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" /></p>
				    				</c:if>
				    			</dd>
   								</c:forEach>
 							</c:otherwise>
 						</c:choose>
 						</dl>
			   		</c:if>
			   		
					<input type="hidden" name="TY_${qestmInfo.qestnrQesitmId}" value="${qestmInfo.qestnTyCode}">
				</dd>
			</dl>
			</c:forEach>
		</div>
		
		<a href='#list' data-role="button" data-theme="b">목록</a>
		
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- view end -->
	
</body>
</html>

