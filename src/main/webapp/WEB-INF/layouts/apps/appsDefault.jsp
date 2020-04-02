<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmmntyBaseTmpl.jsp
  * @Description : 커뮤니티 기본 템플릿
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
<meta charset="UTF-8" />
<title>
<c:if test="${fn:length(mainTitle) == 0}"><tiles:insertAttribute name="title"/> - <c:out value='${targetVO.cmmntyNm}' /></c:if>
<c:if test="${fn:length(mainTitle) != 0}"><c:out value="${boardVO.nttSj}" /></c:if>
</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/mcm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/cmm/jquery-1.7.1.min.js"></script>

</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap">

<!-- header -->
<header id="header">
	<div id="logo">
		<a href="#" onclick="javascript:fn_aram_goCmmntyHome(); return false;">
    	<c:if test="${targetVO.cmmntyLogoImage ne null}">
   			<img src="${pageContext.request.contextPath}/apps/id/${targetVO.pathId}/logo" alt="커뮤니티로고이미지">
   		</c:if>
     	<c:if test="${targetVO.cmmntyLogoImage eq null}">
   			<b>Home</b>
   		</c:if>
   		</a>
    </div> <!-- logo -->
    <div id="login">
		<c:if test="${loginVO == null}">
		    <a href="#" onclick="javascript:fn_aram_login(); return false;"><b>로그인</b></a>
		 	&nbsp;&nbsp;
		 	<a href="#" onclick="javascript:fn_aram_subscribe(); return false;"><b>회원가입</b></a>
		</c:if>
		<c:if test="${loginVO != null}">
			${loginVO.name}님 환영합니다. &nbsp;&nbsp; 
		 	<a href="#" onclick="javascript:fn_aram_updateUser('${loginVO.userSe}'); return false;"><b>회원수정</b></a>
		 	&nbsp;&nbsp;
			<a href="#" onclick="javascript:fn_aram_logout(); return false;"><b>로그아웃</b></a>
		</c:if>
	</div> <!-- login -->

	<!-- banner -->
	<div id="banner">
		<div id="heading">
			<div class="cmmty_title"><c:out value='${targetVO.cmmntyNm}' /></div>
	        <div><c:out value='${targetVO.cmmntyIntrcn}' /></div>
		</div>
	</div> <!-- banner -->

	<!-- topmenu -->
	<div id="topmenu">
		<div id="leftbar"></div>             
		<nav id="user_menu">
			<ul>
			<c:forEach var="menu" items="${targetVO.topMenuList}" varStatus="status">
		        <li><a href="#" onclick="javascript:fn_aram_loadMenu('<c:out value="${menu.menuNm}"/>'); return false;"><c:out value="${menu.menuKnm}"/></a></li>
			</c:forEach>
			</ul>
		</nav>
	
		<div id="rightbar"></div>             
		<c:if test="${targetUserVO.mngrAt=='Y'}">
		<!-- 관리자 메뉴 부분 Start -->
		<nav id="manager_menu">
			<ul>
			<c:forEach var="menu" items="${targetVO.mgrMenuList}" varStatus="status">
		        <li><a href="#" onclick="javascript:fn_aram_loadMenu('<c:out value="${menu.menuNm}"/>'); return false;"><c:out value="${menu.menuKnm}"/></a></li>
			</c:forEach>
		    <li><a href="#" onclick="javascript:fn_aram_loadMenuUrl('<c:url value="/cop/cmy/listMenu.do"/>', '000000'); return false;">메뉴관리</a></li>
			</ul>
	    </nav>    
	    <!-- 관리자 메뉴 부분 End -->
	    </c:if>
	</div> <!-- topmenu -->
</header>

<!-- submenu & main -->
<div id="middle">
	<aside id="leftmenu">	
		<div id="topbar"></div>             
		<div id="middlebar">
			<!-- 서브메뉴 목록 부분 : Start -->
			<nav class="menubox">
      		<ul>
				<c:forEach var="submenu" items="${targetVO.subMenuList}" varStatus="status">
	       		<c:choose>
	       		<c:when test="${status.count == 1}">
	       			<li class="leftmenu_dept01">
	       				<span class="leftbar"></span>
	                	<span class="menubar"><c:out value="${submenu.menuKnm}"/></span>	
	       				<span class="rightbar"></span>
	      			</li>
	       		</c:when>
	          			
	       		<c:otherwise>
	      			<li class="leftmenu_dept02">
	          			<span class="menubar"></span>
	          			<a href="#" onclick="javascript:fn_aram_loadMenu('<c:out value="${submenu.menuNm}"/>'); return false;">
	          				<c:out value="${submenu.menuKnm}"/>
	          			</a>
	      			</li>
	       		</c:otherwise>
	       		</c:choose>
	       		</c:forEach>
	       	</ul>
	       	</nav>
 			<!-- 서브메뉴 목록 부분 : End -->

			<!-- 데모 화면 부분 : Start -->
			<nav class="menubox">
       		<ul>
	       		<li class="leftmenu_dept01">
	       			<span class="leftbar"></span>
	                <span class="menubar">
						<a href="/UnitMain.do" target="_blank">데모전체화면</a>
	                </span>	
	       			<span class="rightbar"></span>
      			</li>
			</ul>
			</nav>
			<!-- 데모 화면 부분 : End -->

		    <c:if test="${targetVO.memberAt == 'Y'}">
			<c:if test="${loginVO != null}">
			<!-- 회원관련 부분 : Start -->
			<nav class="menubox">
       		<ul>
	       		<li class="leftmenu_dept01">
	       			<span class="leftbar"></span>
	                <span class="menubar">
						<c:choose>
						<c:when test="${targetUserVO.authenticatedAt=='Y'}">
						<a href="#" onclick="javascript:fn_aram_deleteUser('<c:out value="${targetVO.cmmntyId}" />'); return false;">탈퇴신청</a>
						</c:when>
						<c:otherwise>
						<a href="#" onclick="javascript:fn_aram_registUser('<c:out value="${targetVO.cmmntyId}" />'); return false;">가입신청</a>
						</c:otherwise>
						</c:choose>
	                </span>	
	       			<span class="rightbar"></span>
      			</li>
			</ul>
			</nav>
			<!-- 회원관련 부분 : End -->
			</c:if>
 			</c:if>
       	</div>
		<div id="bottombar"></div>             
		<div id="additional">${targetVO.additionalInfo}</div>	
	</aside> <!-- leftmenu -->
	
	<section id="content">
		<tiles:insertAttribute name="body"/> 
	</section>
	
</div> <!-- middle -->

<footer id="footer">
	<div id="copyright">Copyright(c) 2012-2016 Aram High-Tech.</div>
</footer>

</div> <!-- wrap -->

<c:if test="${preview == null || preview !='true'}">
<script type="text/javascript">

var cur_menuPos = "${curMenuPos}";
var cur_menuNm = "${targetVO.curMenuNm}";

function fn_aram_goCmmntyHome(){
	var url = "${pageContext.request.contextPath}${targetVO.homeUrl}";
	fn_aram_load(url);
}

function fn_aram_login(){
	var url = "${pageContext.request.contextPath}/uat/uia/loginUsr.do";
	url = url + "?targetUrl=" + get_curmenu_url();
	url = url + "&curTrgetId=${targetVO.cmmntyId}&curMenuPos="+cur_menuPos;
	fn_aram_load(url);
}

function fn_aram_logout(){
	var url = "${pageContext.request.contextPath}/uat/uia/actionLogout.do";
	url = url + "?targetUrl=${pageContext.request.contextPath}${targetVO.homeUrl}";
	fn_aram_load(url);
}

function fn_aram_loadMenu(menuNm){
	url = "${pageContext.request.contextPath}${targetVO.homeUrl}/" + menuNm;
	fn_aram_load(url);
}

function fn_aram_loadMenuUrl(url, menuPos){
  	if( url.indexOf('?') != -1 ) {
		url = url+"&curTrgetId=${targetVO.cmmntyId}&curMenuPos="+menuPos;
	} else {
		url = url+"?curTrgetId=${targetVO.cmmntyId}&curMenuPos="+menuPos;
	}
	fn_aram_load(url);
}

function get_curmenu_url() {
	return "${pageContext.request.contextPath}${targetVO.homeUrl}/" + cur_menuNm;
}

function fn_aram_load(url) {
	location.href  = url;
}

function fn_aram_subscribe(){
	var url = "${pageContext.request.contextPath}/uss/umt/stplatMberView.do";
	url = url + "?curTrgetId=${targetVO.cmmntyId}&curMenuPos="+cur_menuPos;
	fn_aram_load(url);
}

</script>
</c:if>

<c:if test="${loginVO != null}">
<script type="text/javascript">
function fn_aram_updateUser(userSe){
   	var url = "";
   	if ( userSe == 'GNR' ) {
		url = "${pageContext.request.contextPath}/uss/umt/editMber.do";
	} else if ( userSe == 'ENT' ) {	
		url = "${pageContext.request.contextPath}/uss/umt/editEntrprsMber.do";
	} else if ( userSe == 'USR' ) {
		url = "${pageContext.request.contextPath}/uss/umt/editUser.do";
	}	
	url = url + "?uniqId=${loginVO.uniqId}";
	url = url + "&curTrgetId=${targetVO.cmmntyId}&curMenuPos="+cur_menuPos;
	fn_aram_load(url);
}

function fn_aram_registUser(cmmntyId){
	window.open("${pageContext.request.contextPath}/cop/cmy/insertCmmntyUserBySelf.do?trgetId="+cmmntyId+"&PopFlag=S", "userRegist","width=400, height=100");
}

function fn_aram_deleteUser(cmmntyId){
	window.open("${pageContext.request.contextPath}/cop/com/listCmmntyMngr.do?trgetId="+cmmntyId+"&PopFlag=S", "userDelete","width=800, height=400");
}

</script>
</c:if>

</body>
</html>
