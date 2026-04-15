<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>행정안전부 공통서비스 테스트 사이트(일반사용자)</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/main.css" type="text/css">

</head>

<body >
<c:import url="/sym/mnu/mpm/MainMenuHead.do" />

<div id="#main_home">

	<div class="wrap">
		<div class="side">
		
	    	<section class="section">
	    	
				<div class="section_title">
					<h2>진행중인 설문</h2>
				</div>
				
				<div class="section_body">
		    	</div>		
			</section>
			
	    	<section class="section">
	    	
				<div class="section_title">
					<h2>가입한 커뮤니티 목록</h2>
				</div>
				
				<div class="section_body">
		    	</div>		
			</section>

	    	<section class="section">
	    	
				<div class="section_title">
					<h2>등록한 Q&amp;A</h2>
				</div>
				
				<div class="section_body">
		    	</div>		
			</section>
		</div>
		
		<div class="side">
		
	    	<section class="section">
	    	
				<div class="section_title">
					<h2>등록한 상담 목록</h2>
				</div>
				
				<div class="section_body">
		    	</div>		
			</section>
			
	    	<section class="section" style="height:240px;">
	    	
				<div class="section_title">
					<h2>사이트 들겨찾기 목록</h2>
				</div>
				
				<div class="section_body">
		    	</div>		
			</section>
					
		</div>
	</div> <!-- wrap -->
	
</div>

<!-- bottom -->
<c:import url="/sym/mnu/mpm/MainMenuBottom.do" />
</body>
</html>
