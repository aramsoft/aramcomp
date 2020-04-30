<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>행정안전부 공통서비스 테스트 사이트(업무사용자)</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/main.css" type="text/css">

<script type="text/javascript">

function fnEgovTabNavigation(objName){
	var objList = new Array('DIV_01','DIV_02','DIV_03');

	for(var i=0;i<objList.length;i++) {

		if(objList[i] == objName){
			document.getElementById(objList[i]).style.display  = '';
		}else{
			document.getElementById(objList[i]).style.display  = 'none';
		}
	}
}

</script>

</head>

<body>

<c:import url="/sym/mnu/mpm/MainMenuHead.do" />

<div id="main_home">
	<div class="wrap">
		<div class="side">
		
	    	<section class="section">
	    	
				<div class="section_title">
					<h2>공지사항</h2>
				</div>
				
				<div class="section_body">
		    	</div>		
			</section>
			
			<section class="section">
				<div class="section_title">
					<h2>생성된 게시판 목록</h2>
				</div>
				<div class="section_body">
				</div>
			</section>
			
			<section class="section">
			    			
				<div class="section_title">
					<h2>생성된 커뮤니티 목록</h2>
				</div>
				<div class="section_body" >
					<c:import url="/cop/cmy/listCommunityPortlet.do" />
				</div>
				
			</section>
		</div>
		
		<div class="side">	
			<section class="section">   
			 			
					<table border="0" >
						<tr>
							<td>
							<table border="0" >
								<tr>
									<td><img src="${pageContext.request.contextPath}/images/TAB_01.gif"  border="0" name="TAB_01" id="TAB_01" style="cursor:pointer;" onClick="fnEgovTabNavigation('DIV_01');"></td>
									<td><img src="${pageContext.request.contextPath}/images/TAB_02.gif"   border="0" name="TAB_02" id="TAB_02" style="cursor:pointer;" onClick="fnEgovTabNavigation('DIV_02');"></td>
									<td><img src="${pageContext.request.contextPath}/images/TAB_03.gif"   border="0" name="TAB_03" id="TAB_03" style="cursor:pointer;" onClick="fnEgovTabNavigation('DIV_03');"></td>
								</tr>
							</table>
							</td>
						</tr>
					
						<tr height="100">
							<td>
							<div class="section_body" style="height:100px;">
							<!-- 개인정보 -->
							<div id="DIV_01" style="display:none;">
								개인정보
							</div>
					
							<!-- 부서일정관리  -->
							<div id="DIV_02" style="display:;">
								<c:import charEncoding="utf-8" url="/cop/smt/sdm/listDeptSchdulMainPage.do"></c:import>
							</div>
					
							<!-- 일정관리 -->
							<div id="DIV_03" style="display:none;">
								<c:import charEncoding="utf-8" url="/cop/smt/sim/listSchdulMainPage.do"></c:import>
							</div>
							
							</div>
							</td>
						</tr>
		    		</table>
			</section>
			
			<section class="section">    			

				<div class="section_title">
					<h2>환율정보</h2>
				</div>
				<div class="section_body" style="height:300px;">
					<iframe src="http://community.fxkeb.com/fxportal/jsp/RS/DEPLOY_EXRATE/fxrate_all.html" width="430" height="300" seamless="seamless" ></iframe>
				</div>
			</section>
		</div>
	</div> <!-- wrap -->
	
</div>


<!-- bottom -->
<c:import url="/sym/mnu/mpm/MainMenuBottom.do" />

</body>
</html>
