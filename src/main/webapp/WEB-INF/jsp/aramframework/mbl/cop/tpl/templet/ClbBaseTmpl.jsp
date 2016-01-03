<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title><c:out value='${clubVO.clbNm}' /></title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

<script type="text/javascript">
<!--

function fn_aram_loadBdList(bbsId, bbsAttrbCode, bbsTyCode) {
	
	var url;
	if(bbsTyCode == 'BBST04'){
		url = "${pageContext.request.contextPath}/cop/bbs/selectGuestList.mdo?bbsId="+bbsId+"&bbsAttrbCode="+bbsAttrbCode;			
	}else if(bbsTyCode == 'BBST02'){ // 익명게시판의 경우 (2011.9.7 수정분)
		url = "${pageContext.request.contextPath}/cop/bbs/anonymous/listBoardArticle.mdo?bbsId="+bbsId+"&bbsAttrbCode="+bbsAttrbCode;
	}else{
		url = "${pageContext.request.contextPath}/cop/bbs/listBoardArticle.mdo?bbsId="+bbsId+"&bbsAttrbCode="+bbsAttrbCode;
	}
	
	location.href = url;
}

-->
</script>
</head>
<body>

	<!-- 모바일 페이지 start -->
	<div data-role="page">
	
		<!-- header start -->
		<div data-role="header" data-theme="z">
			<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>
			<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png" alt="logo"></h1>
			<div class="ui-body-a"><h3 class="uss-commTitle"><c:out value='${cmmntyVO.cmmntyNm}' /></h3></div>
		</div>
		<!-- header end -->
		
		<!-- content start -->
		<div data-role="content">

			<form action="${pageContext.request.contextPath}/cop/clb/ClubMainContents.do" name="frm" method="post">
				<input type="hidden" name="cmmntyId" value="<c:out value='${clubVO.cmmntyId}' />" />
				<input type="hidden" name="clbId" value="<c:out value='${clubVO.clbId}' />" />
				<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
			</form>

			<div data-role="collapsible-set">
				<div data-role="collapsible" data-collapsed="false">
					<h3>게시판 목록</h3>
						<!-- 게시판 목록 부분 : Start -->
						<ul data-role="listview" data-inset="true" data-theme="d">
							<c:forEach var="bbs" items="${bbsList}" varStatus="status">
			              	<li>
			              		<a href="javascript:fn_aram_loadBdList('<c:out value="${bbs.bbsId}"/>','<c:out value="${bbs.bbsAttrbCode}"/>','<c:out value="${bbs.bbsTyCode}"/>');">
								<c:out value="${bbs.bbsNm}" /></a>
							</li>
							</c:forEach> 
			            </ul>
						<!-- 게시판 목록 부분 : End -->			
				</div>
			</div>
			
		</div>
		<!-- content end -->
		
		<!-- footer start -->
		<div data-role="footer" data-theme="z" data-position="fixed">
			<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
		</div>
		<!-- footer end -->
	
	</div>
	<!-- 모바일 페이지 end -->
</body>
</html>
