<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>아람 모바일 컴포넌트</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
</head>  
 
<body>

<!-- 모바일 페이지 start -->
<div data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>
		<h1>아람 컴포넌트</h1>
	</div>
	<!-- header end -->
	
	<!-- content start -->
	<div data-role="content">
		<p>아람 모바일 컴포넌트</p>
		<br>
	
		<div data-role="collapsible-set">
			<div data-role="collapsible" data-collapsed="true">
				<h3>협업</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/content/mbl/board/1/articles" rel="external">공지게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/content/mbl/board/11/articles" rel="external">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/content/mbl/board/21/articles" rel="external">유효게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/content/mbl/board/anonymous/31/articles" rel="external">익명게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/scp/listScrap.mdo" rel="external">스크랩</a></li>
					<li><a href="${pageContext.request.contextPath}/content/mbl/apps/1" rel="external">커뮤니티</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.mdo" rel="external">일정관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo" rel="external">일지관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/adb/listAdressBook.mdo" rel="external">주소록관리</a></li>
				</ul>
			</div>
			<div data-role="collapsible" data-collapsed="true">
				<h3>사용자지원</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/uss/sam/stp/listStplat.mdo" rel="external">약관</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/cns/listCnslt.mdo" rel="external">상담관리</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfoUser.mdo" rel="external">설문참여</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.mdo" rel="external">온라인POLL참여</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/hpc/listHpcm.mdo" rel="external">도움말</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/wor/listWordDicary.mdo" rel="external">용어사전</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/faq/listFaq.mdo" rel="external">FAQ</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/qna/listQna.mdo" rel="external">Q&amp;A</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/awa/listAdministrationWord.mdo" rel="external">행정전문용어사전</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/omm/listOnlineManual.mdo" rel="external">온라인매뉴얼</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/nws/listNewsInfo.mdo" rel="external">뉴스 목록</a></li>		
					<li><a href="${pageContext.request.contextPath}/sym/mnu/mcm/WebSiteMap.mdo" rel="external">사이트맵 Type A</a></li>
					<li><a href="${pageContext.request.contextPath}/sym/mnu/mcm/SiteMapng.mdo" rel="external">사이트맵 Type B</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/rec/listRecomendSite.mdo" rel="external">추천사이트</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo" rel="external">행사/이벤트/캠페인</a></li>
					<li>마이페이지 - 테스트중</li>
<!-- 
					<li><a href="${pageContext.request.contextPath}/uss/mpe/selectIndvdlpgeResult.mdo" rel="external">마이페이지</a></li>
-->
				</ul>
			</div>
			<div data-role="collapsible" data-collapsed="true">
				<h3>사용자인증</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/uat/uia/loginUsr.do?targetUrl=/mindex.jsp" rel="external">모바일 Login</a></li>
					<li><a href="${pageContext.request.contextPath}/index.jsp" rel="external">WEB Index</a></li>
				</ul> 
			</div>
			<div data-role="collapsible" data-collapsed="true">
				<h3>신규공통컴포넌트(Front)</h3>
				<ul data-role="listview" data-inset="true"> 
					<li><a href="${pageContext.request.contextPath}/mbl/com/rns/goRealtimeNoticeMsg.mdo" rel="external">실시간 공지 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mpa/listMobilePhoto.mdo" rel="external">모바일 사진 앨범</a></li>
					<li>OPEN-API 연계 서비스_대한민국정부포털 검색  - 작업중</li>
					<li>OPEN-API 연계 서비스_기상청 날씨 조회  - 작업중</li>
<!-- 
					<li><a href="${pageContext.request.contextPath}/mbl/com/oas/goKoreaGovPortalSearchResultList.mdo" rel="external">OPEN-API 연계 서비스_대한민국정부포털 검색</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/oas/goWeatherInqire.mdo" rel="external">OPEN-API 연계 서비스_기상청 날씨 조회</a></li>
-->
					<li><a href="${pageContext.request.contextPath}/mbl/com/mcg/goMobileChartGraph.mdo" rel="external">모바일 차트/그래프</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mdi/goMobileDeviceIdent.mdo" rel="external">모바일 기기 식별</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/syn/listMobileSync.mdo" rel="external">동기화 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mlt/listMobileMultimedia.mdo" rel="external">멀티미디어 제어</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/ows/listMobileOfflineWeb.mdo" rel="external">오프라인 웹 서비스</a></li>
					<li data-inset="false" >
						<div data-role="collapsible-set">
							<div data-role="collapsible" data-collapsed="true">
								<h3>위치 정보 연계</h3>
								<ul data-role="listview" data-inset="true" data-theme="d">
									<li><a href="${pageContext.request.contextPath}/mbl/com/geo/goMobileGeoLocationPublic.mdo" rel="external">공공 지도 연계 서비스</a></li>
									<li><a href="${pageContext.request.contextPath}/mbl/com/geo/goMobileGeoLocationPrivate.mdo" rel="external">민간 지도 연계 서비스</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>MMS 서비스 연계  - 작업중</li>
<!-- 
					<li><a href="${pageContext.request.contextPath}/mbl/com/mms/goMmsWrite.mdo" rel="external">MMS 서비스 연계</a></li>
-->
				</ul>
			</div>		
		</div>
	</div>
	<!-- content end -->
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>
<!-- 모바일 페이지 end -->
</body>
</html>

