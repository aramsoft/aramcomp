<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : IncHeader.jsp
  * @Description : 화면상단 Header(include)
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *
  */
%>
<div id="skipNav" class="invisible">
    <dl>
        <dt>콘텐츠 바로가기</dt>
        <dd><a href="#main_container">컨텐츠 바로가기</a></dd>
        <dd><a href="#topnavi">메인메뉴 바로가기</a></dd>
    </dl>
</div>
<!-- 행정안전부 로고 및 타이틀 시작 -->
<div id="logoarea">
    <h1>
    <a href="${pageContext.request.contextPath}/home/main.do">
    	<img src="${pageContext.request.contextPath}/images/home/header/logo.jpg" alt="템플릿 샘플 홈페이지" height="30">
    </a>
    </h1>
</div>
<!-- 
<div id="nia_logo">
    <img src="${pageContext.request.contextPath}/images/home/header/limg_lt_nia_logo.gif" alt="NIA한국정보화진흥원" />
</div>
 -->
<div id="project_title">
<span class="maintitle">표준프레임워크 </span><strong>샘플 홈페이지 </strong>
<a href="${pageContext.request.contextPath}/pageLink.do?link=home/page/Intro" target="_blank"><img width="20" height="20" src="${pageContext.request.contextPath}/images/home/header/question.jpg" alt="메뉴구성 설명" title="메뉴구성 설명"></a>
</div>
<!-- //행정안전부 로고 및 타이틀 끝 -->
<div class="header_login">
	<c:if test="${loginVO == null}">
    <div id="header_loginname">
        <a href="#"></a>
    </div>
    <div class="header_loginconnection"></div>
    <ul class="login_bg_area">
        <li class="righttop_bgleft">&nbsp;</li>
        <li class="righttop_bgmiddle"><a href="${pageContext.request.contextPath}/uat/uia/loginUsr.do?targetUrl=/home/main.do">로그인</a></li>
        <li class="righttop_bgright">&nbsp;</li>
    </ul>
	</c:if>
	<c:if test="${loginVO != null}">
    <div id="header_loginname">
        <a href="#" onclick="alert('개인정보 확인 등의 링크 제공'); return false;"><c:out value="${loginVO.name}"/></a>
    </div>
    <div class="header_loginconnection"> 님으로 로그인하셨습니다.</div>
    <ul class="login_bg_area">
		<c:if test="${loginVO.userSe == 'GNR' }">
	        <li class="righttop_bgleft">&nbsp;</li>
		 	<li class="righttop_bgmiddle"><a href="${pageContext.request.contextPath}/${jspPrefix}/uss/umt/editMber.do?userId=${loginVO.userId}">회원수정</a></li>
        	<li class="righttop_bgright">&nbsp;</li>
		</c:if>
		<c:if test="${loginVO.userSe == 'ENT' }">
	        <li class="righttop_bgleft">&nbsp;</li>
			<li class="righttop_bgmiddle"><a href="${pageContext.request.contextPath}/${jspPrefix}/uss/umt/editEntrprsMber.do?userId=${loginVO.userId}">회원수정</a></li>
        	<li class="righttop_bgright">&nbsp;</li>
		</c:if>
		<c:if test="${loginVO.userSe == 'USR' }">
        	<li class="righttop_bgleft">&nbsp;</li>
			<li class="righttop_bgmiddle"><a href="${pageContext.request.contextPath}/${jspPrefix}/uss/umt/editEmplyr.do?userId=${loginVO.userId}">회원수정</a></li>
        	<li class="righttop_bgright">&nbsp;</li>
		</c:if>
        <li class="righttop_bgleft">&nbsp;</li>
        <li class="righttop_bgmiddle"><a href="${pageContext.request.contextPath}/uat/uia/actionLogout.do?targetUrl=/home/main.do">로그아웃</a></li>
        <li class="righttop_bgright">&nbsp;</li>
    </ul>
	</c:if>
</div>