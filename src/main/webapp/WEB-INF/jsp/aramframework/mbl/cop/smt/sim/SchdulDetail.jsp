<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일정 상세조회 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
	
</head>

<body>
	
<div id="view" data-role="page">
	
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1 id="viewTitle">일정관리상세보기</h1>
	    <a href="${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo" data-icon="search" rel="external">일지관리</a>
	</div>
	<!-- header end -->
	
	<div data-role="content" class="com-copContent">

		<form:form commandName="schdulManageVO" method="post">
			<form:hidden path="schdulId" />

			<form:hidden path="searchSchdulSe" />
			<form:hidden path="year" value="" />
			<form:hidden path="month" value="" />
			<form:hidden path="week" value="" />
			<form:hidden path="day" value="" />
			
			<ul class="uss-hpcDetail">
				<li>
					<span class="uss-tit">일정구분 </span>
					<span class="uss-con gray">
					<c:forEach items="${COM030_schdulSe}" var="schdulSeInfo" varStatus="status">
						<c:if test="${schdulSeInfo.code eq schdulManageVO.schdulSe}">	
							<c:out value="${schdulSeInfo.codeNm}" escapeXml="false" />
						</c:if>
					</c:forEach>
					</span>
				</li>
				
				<li>
					<span class="uss-tit">중요도 </span>
					<span class="uss-con">
					<c:forEach items="${COM019_schdulIpcr}" var="schdulSeInfo" varStatus="status">
						<c:if test="${schdulSeInfo.code eq schdulManageVO.schdulIpcrCode}">	
							<c:out value="${schdulSeInfo.codeNm}" escapeXml="false" />
						</c:if>
					</c:forEach>
					</span>
				</li>
				
				<li>
					<span class="uss-tit">일정명 </span>
					<span class="uss-con">
						<c:out value="${schdulManageVO.schdulNm}" />
					</span>
				</li>	
				
				<li>			
					<span class="uss-tit">일정 내용 </span>
				</li>
				<li class="uss-contentsView">
					${fn:replace(schdulManageVO.schdulCn , crlf , '<br/>')}
				</li>
				
				<li>
					<span class="uss-tit">반복구분 </span>
					<span class="uss-con">
					<c:forEach items="${COM031_reptitSe}" var="schdulSeInfo" varStatus="status">
						<c:if test="${schdulSeInfo.code eq schdulManageVO.reptitSeCode}">	
							<c:out value="${schdulSeInfo.codeNm}" escapeXml="false" />
						</c:if>
					</c:forEach>
					</span>
				</li>
				
				<li>
					<span class="uss-tit">날짜/시간 </span>
					<span class="uss-con">
						${fn:substring(schdulManageVO.schdulBgnde, 0, 4)}-${fn:substring(schdulManageVO.schdulBgnde, 4, 6)}-${fn:substring(schdulManageVO.schdulBgnde, 6, 8)} ${fn:substring(schdulManageVO.schdulBgnde, 8, 10)}시  ${fn:substring(schdulManageVO.schdulBgnde, 10, 12)}분 ~      
						${fn:substring(schdulManageVO.schdulEndde, 0, 4)}-${fn:substring(schdulManageVO.schdulEndde, 4, 6)}-${fn:substring(schdulManageVO.schdulEndde, 6, 8)} ${fn:substring(schdulManageVO.schdulEndde, 8, 10)}시  ${fn:substring(schdulManageVO.schdulEndde, 10, 12)}분
					</span>
				</li>
				
				<li>
					<span class="uss-tit">담당자 </span>
					<span class="uss-con">
						<c:out value="${schdulManageVO.schdulChargerName}" />
					</span>
				</li>	
			</ul>
		</form:form>
		
		<div class="ui-grid-b">
			<div class="ui-block-a"><a href="javascript:fn_aram_edit();" data-role="button" data-theme="b">수정</a></div>
			<div class="ui-block-b"><a href='javascript:fn_aram_delete();' data-role="button" data-theme="b">삭제</a></div>					
			<div class="ui-block-c"><a href='javascript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
		</div>
		
	</div>
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
	
<script type="text/javaScript">

function fn_aram_delete() {
      	
	jConfirm('삭제하시겠습니까?', '알림', 'a', 
		function(r) {
			if(r) {
				var url = "${pageContext.request.contextPath}/cop/smt/sim/deleteSchdul.mdo";
				$('#schdulManageVO').attr('action', url);
				$('#schdulManageVO').attr('data-ajax', 'false');
				$('#schdulManageVO').submit();	
			}
		}
	);
	
}

function fn_aram_list() {

	var url = "";
	var path = $('#path').val();
	
	if(path == "daily") {
		url = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.mdo";
	}
	else {
		url = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.mdo";
	}
	
	$('#schdulManageVO').attr('action', url);
	$('#schdulManageVO').attr('data-ajax', 'false');
	$('#schdulManageVO').submit();
}

function fn_aram_edit() {
	
	var url = "${pageContext.request.contextPath}/cop/smt/sim/editSchdul.mdo";
	
	$('#schdulManageVO').attr('action', url);
	$('#schdulManageVO').attr('data-ajax', 'false');
	$('#schdulManageVO').submit();
}

</script>
	
</body>
</html>

