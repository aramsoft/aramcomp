<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title><c:out value='${communityVO.cmmntyNm}' /></title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body >

<!-- 모바일 페이지 start -->
<div data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<h1><c:out value='${communityVO.cmmntyNm}' /></h1>
	</div>
	<!-- header end -->
	
	<!-- content start -->
	<div data-role="content">
		
		<div data-role="collapsible-set">

			<div data-role="collapsible" data-collapsed="false">
				<h3>게시판 목록</h3> 
				<!-- 게시판 목록 부분 : Start -->
				<ul data-role="listview" data-inset="true" data-theme="d">
					<c:forEach var="bbs" items="${bbsList}" varStatus="status">
	              	<li>
	              		<a href="javascript:fn_aram_loadBdList('<c:out value="${bbs.bbsId}"/>','<c:out value="${bbs.bbsTyCode}"/>');">
							<c:out value="${bbs.bbsNm}" />
						</a>
					</li>
					</c:forEach> 
	            </ul>
				<!-- 게시판 목록 부분 : End -->			
			</div>
			
			<div data-role="collapsible" data-collapsed="true">
				<h3>협업</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/cop/scp/listScrap.mdo?trgetId=${communityVO.cmmntyId}" rel="external">스크랩</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.mdo?trgetId=${communityVO.cmmntyId}" rel="external">일정관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo?trgetId=${communityVO.cmmntyId}" rel="external">일지관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/adb/listAdressBook.mdo?trgetId=${communityVO.cmmntyId}" rel="external">주소록관리</a></li>
				</ul>
			</div>
			
			<div data-role="collapsible" data-collapsed="true">
				<h3>사용자지원</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/uss/olp/cns/listCnslt.mdo?trgetId=${communityVO.cmmntyId}" rel="external">상담관리</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfoUser.mdo?trgetId=${communityVO.cmmntyId}" rel="external">설문참여</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.mdo?trgetId=${communityVO.cmmntyId}" rel="external">온라인POLL참여</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/faq/listFaq.mdo?trgetId=${communityVO.cmmntyId}" rel="external">FAQ</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/qna/listQna.mdo?trgetId=${communityVO.cmmntyId}" rel="external">Q&amp;A</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/nws/listNewsInfo.mdo?trgetId=${communityVO.cmmntyId}" rel="external">뉴스 목록</a></li>		
					<li><a href="${pageContext.request.contextPath}/uss/ion/rec/listRecomendSite.mdo?trgetId=${communityVO.cmmntyId}" rel="external">추천사이트</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo?trgetId=${communityVO.cmmntyId}" rel="external">행사/이벤트/캠페인</a></li>
				</ul>
			</div>
			
		    <c:if test="${communityVO.homeUrl == '/apps/aramht'}">
			<div data-role="collapsible" data-collapsed="true">
				<h3>데모 프로그램</h3> 
				<ul data-role="listview" data-inset="true" data-theme="d">
	              	<li>
	              		<a href="${pageContext.request.contextPath}/UnitMain.mdo" rel="external">
	              			데모 메인 화면
	              		</a>
	              	</li>
	            </ul>
			</div>
			</c:if>

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

<script type="text/javascript">

function fn_aram_loadBdList(bbsId, bbsTyCode){
	var url;
	bbsId2 = fn_aram_get_idString(bbsId);
	
	if(bbsTyCode == 'BBST04'){
		url = "${pageContext.request.contextPath}/cop/bbs/selectGuestList.mdo?bbsId="+bbsId;
	}else if(bbsTyCode == 'BBST02'){ // 익명게시판의 경우 (2011.9.7 수정분)
		url = "${pageContext.request.contextPath}/content/mbl/board/anonymous/"+bbsId2+"/articles";
	}else{
		url = "${pageContext.request.contextPath}/content/mbl/board/"+bbsId2+"/articles";
	}
   	if( url.indexOf('?') != -1 ) {
   		url = url+"&trgetId=${communityVO.cmmntyId}";
	} else {
   		url = url+"?trgetId=${communityVO.cmmntyId}";
	}
	location.href = url;
}

</script>

</body>
</html>
