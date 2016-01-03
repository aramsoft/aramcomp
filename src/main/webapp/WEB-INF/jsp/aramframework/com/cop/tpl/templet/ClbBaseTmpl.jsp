<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ClbBaseTmpl.jsp
 * @Description : 동호회 기본 템플릿
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
<title><c:out value='${clubVO.clbNm}' /></title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/mcm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript">

function changeFrameSize(){
	var the_height = document.getElementById('contentFrame').contentWindow.document.body.scrollHeight;
	document.getElementById('contentFrame').height = the_height + 60;
	document.getElementById('contentFrame').width = document.body.clientWidth - 190;
}

</script>

<c:if test="${preview == null || preview !='true'}">

<script type="text/javascript">

function fn_aram_goClubHome(){
   	var varForm = document.getElementById("clubVO");
	var url = "${pageContext.request.contextPath}/content/club/" + varForm.clbId.value;
   	location.href = url;
}

function fn_aram_loadMenu(url) {
	var varForm = document.getElementById("clubVO");
	url = url+"?trgetId="+varForm.clbId.value;
	document.getElementById("contentFrame").src = url;
}

function fn_aram_loadBdList(bbsId, bbsTyCode) {
	var url;
	if(bbsTyCode == 'BBST04'){
		url = "${pageContext.request.contextPath}/cop/bbs/selectGuestList.do?bbsId="+bbsId;
	}else if(bbsTyCode == 'BBST02'){ // 익명게시판의 경우 (2011.9.7 수정분)
		url = "${pageContext.request.contextPath}/cop/bbs/anonymous/listBoardArticle.do?bbsId="+bbsId;
	}else{
		url = "${pageContext.request.contextPath}/cop/bbs/listBoardArticle.do?bbsId="+bbsId;
	}
	document.getElementById("contentFrame").src = url;
}

function fn_aram_registUser(clbId, cmmntyId) {
	window.open("${pageContext.request.contextPath}/cop/clb/insertClubUserBySelf.do?clbId="+clbId+"&cmmntyId="+cmmntyId, "userRegist");
}

function fn_aram_deleteUser(clbId, cmmntyId) {
	window.open("${pageContext.request.contextPath}/cop/com/listClubOprtr.do?trgetId="+clbId+"&PopFlag=S", "userDelete");
}

</script>

</c:if>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap">

<form:form commandName="clubVO" action ="" method="post">
<form:hidden path="cmmntyId" />
<form:hidden path="clbId" />
</form:form>

<header>
	<div>&nbsp;</div>
</header>

<!-- banner -->
<div id="banner">
	<div id="heading">
		<div class="cmmty_title"><c:out value='${clubVO.clbNm}' /></div>
        <div><c:out value='${clubVO.clbIntrcn}' /></div>
	</div>
</div>

<!-- topmenu -->
<div id="topmenu">
	<div id="leftbar"></div>             
	<div id="user_menu">
		<ul>
	        <li><a href="#" onclick="javascript:fn_aram_goClubHome(); return false;">처음화면</a></li>
		<!-- 
	    	<li><a href="#" onclick="javascript:fn_aram_help(); return false;" class="comun">도움말</a></li>
		-->
		</ul>
	</div>

	<div id="rightbar"></div>             
	<c:if test="${isOperator=='Y'}">
	<!-- 관리자 메뉴 부분 Start -->
	<div id="manager_menu">
		<ul>
	        <li><a href="#" onclick="javascript:fn_aram_loadMenu('<c:url value="/cop/com/listBdMstrByTrget.do"/>'); return false;">게시판관리</a></li>
	        <li><a href="#" onclick="javascript:fn_aram_loadMenu('<c:url value="/cop/com/listClubUser.do"/>'); return false;">사용자관리</a></li>
	        <li><a href="#" onclick="javascript:fn_aram_loadMenu('<c:url value="/cop/com/listConfirmByTrget.do"/>'); return false;">승인관리</a></li>
	        <li><a href="#" onclick="javascript:fn_aram_loadMenu('<c:url value="/cop/clb/detailClub.do"/>'); return false;">동호회관리</a></li>
		</ul>
    </div>    
    <!-- 관리자 메뉴 부분 End -->
    </c:if>
</div>

<!-- submenu & main -->
<div id="middle">
	<div id="leftmenu">	
		<div id="topbar"></div>             
		<div id="middlebar">
			<!-- 게시판 목록 부분 : Start -->
			<div class="menubox">
      		<ul>
       			<li class="leftmenu_dept01">
       				<span class="leftbar"></span>
                	<span class="menubar">게시판 목록</span>	
       				<span class="rightbar"></span>
      			</li>
	          			
				<c:forEach var="bbs" items="${bbsList}" varStatus="status">
	      			<li class="leftmenu_dept02">
	          			<span class="menubar"></span>
	          			<a href="#" onclick="javascript:fn_aram_loadBdList('<c:out value="${bbs.bbsId}"/>','<c:out value="${bbs.bbsTyCode}"/>'); return false;">
	          				<c:out value="${bbs.bbsNm}" />
	          			</a>
	      			</li>
	       		</c:forEach>
	       	</ul>
	       	</div>
 			<!-- 서브메뉴 목록 부분 : End -->

			<c:if test="${isAuthenticated=='Y'}">
			<!-- 회원관련 부분 : Start -->
			<div class="menubox">
       		<ul>
	       		<li class="leftmenu_dept01">
	       			<span class="leftbar"></span>
	                <span class="menubar">
						<c:choose>
						<c:when test="${clubUserVO.useAt=='Y'}">
						<a href="#" onclick="javascript:fn_aram_deleteUser('<c:out value="${clubVO.clbId}" />','<c:out value='${clubVO.cmmntyId}' />');  return false;">탈퇴신청</a>
						</c:when>
						<c:otherwise>
						<a href="#" onclick="javascript:fn_aram_registUser('<c:out value="${clubVO.clbId}" />','<c:out value='${clubVO.cmmntyId}' />'); return false;">가입신청</a>
						</c:otherwise>
						</c:choose>
	                </span>	
	       			<span class="rightbar"></span>
      			</li>
			</ul>
			</div>
			<!-- 회원관련 부분 : End -->
			</c:if>
       	</div>
		<div id="bottombar"></div>             
	</div>
	<div id="content">
		<!--  컨텐츠 영역 : Start -->
		<iframe id="contentFrame" onload="javascript:changeFrameSize(); return false;" src="${pageContext.request.contextPath}${contentUrl}" width="750" height="1200" seamless="seamless" title="컨텐츠영역"></iframe>
   		<!--  컨텐츠 영역 : End -->
	</div>
</div>

<footer>
	<div id="copyright">Copyright(c) 2012, 2013, 2014 Aram High-Tech.</div>
</footer>

</DIV>

</body>
</html>
