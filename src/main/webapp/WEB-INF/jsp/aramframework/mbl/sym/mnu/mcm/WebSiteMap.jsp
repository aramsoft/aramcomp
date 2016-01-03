<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>사이트맵</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/sym/mnu/mcm/EgovMblMenuCreatSiteMap.js"></script>
			
</head>

<body>

<!-- 사이트맵 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" data-ajax="false">홈</a>
	    <h1>사이트맵</h1>
	    <a href="${pageContext.request.contextPath}/sym/mnu/mcm/SiteMapng.mdo" data-icon="forward" rel="external">Type B</a>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-siteContent">
		<!--
		<div data-role="collapsible-set">
			
		</div> -->
		<div class="uss-sitemap">
			<p><span>사용자지원</span></p>
			<dl>
				<dt>설문관리</dt>
				<dd><a href="${pageContext.request.contextPath}/uss/olp/qnn/listQustnrRespondInfo.mdo" data-role="none" rel="external">설문참여</a></dd>
			</dl>
			<dl>
				<dt class="link"><a href="${pageContext.request.contextPath}/uss/sam/stp/listStplat.mdo" data-role="none" rel="external">약관관리</a></dt>
			</dl>
			<dl>
				<dt>상담관리</dt>
				<dd><a href="${pageContext.request.contextPath}/uss/olp/cns/listCnslt.mdo" data-role="none" rel="external">상담목록조회</a></dd>
			</dl>
			<dl>
				<dt class="link"><a href="${pageContext.request.contextPath}/uss/olh/hpc/listHpcm.mdo" data-role="none" rel="external">도움말관리</a></dt>
			</dl>
			<dl>
				<dt class="link"><a href="${pageContext.request.contextPath}/uss/olh/wor/listWordDicary.mdo" data-role="none" rel="external">용어사전관리</a></dt>
			</dl>
			<dl>
				<dt class="link"><a href="${pageContext.request.contextPath}/uss/olh/faq/listFaq.mdo" data-role="none" rel="external">FAQ관리</a></dt>
			</dl>
			<dl>
				<dt>Q&amp;A관리</dt>
				<dd><a href="${pageContext.request.contextPath}/uss/olh/qna/listQna.mdo" data-role="none" rel="external">Q&amp;A목록조회</a></dd>
			</dl>
			<dl>
				<dt class="link"><a href="${pageContext.request.contextPath}/uss/ion/nws/listNewsInfo.mdo" data-role="none" rel="external">뉴스관리</a></dt>
			</dl>
			<dl>
				<dt>사이트관리</dt>
				<dd><a href="${pageContext.request.contextPath}/sym/mnu/mcm/WebSiteMap.mdo" data-role="none" rel="external">사이트맵</a></dd>
			</dl>
			<dl>
				<dt class="link"><a href="${pageContext.request.contextPath}/uss/ion/rec/listRecomendSite.mdo" data-role="none" rel="external">추천사이트관리</a></dt>
			</dl>
			<p><span>협업</span></p>
			<dl>
				<dt>게시판</dt>
				<dd><a href="${pageContext.request.contextPath}/cop/bbs/listBoardMaster.do" data-role="none" rel="external">게시판목록조회</a></dd>
				<dd><a href="${pageContext.request.contextPath}/cop/bbs/listBoardUseInf.do" data-role="none" rel="external">게시판사용정보조회</a></dd>
			</dl>
			<dl>
				<dt>커뮤니티/동호회</dt>
				<dd><a href="${pageContext.request.contextPath}/cop/cmy/CmmntyMainPage.mdo?cmmntyId=CMMNTY_0000000000001" data-role="none" rel="external">커뮤니티목록조회</a></dd>
			</dl>
			<dl>
				<dt>행사/이벤트/캠페인</dt>
				<dd><a href="${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo" data-role="none" rel="external">행사/이벤트/캠페인조회</a></dd>
			</dl>
			<dl>
				<dt>일정관리</dt>
				<dd><a href="${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.mdo" data-role="none" rel="external">일정관리</a></dd>
				<dd><a href="${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo" data-role="none" rel="external">일지관리</a></dd>
			</dl>
		</div>
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 사이트맵 List end -->		
	
</body>
</html>
