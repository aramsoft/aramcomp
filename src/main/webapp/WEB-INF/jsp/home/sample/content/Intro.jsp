<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : Intro.jsp
  * @Description : 샘플화면 - 구성설명(sample)
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>표준프레임워크 경량환경 홈페이지템플릿 소개</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home/sample/common.css" type="text/css">
</head>
<body style="margin-left:10px">
<!-- 전체 레이어 시작 -->
<div id="wrap">

	<h2>홈페이지 템플릿 소개</h2>
	
	<pre>
	- 경량환경 템플릿은 개발자가 프레임워크 쉽게 이해하고 활용할 수 있도록 지원합니다.
	- 홈페이지 템플릿은 공통컴포넌트를 기반으로 아래 그림과 같이 메뉴가 구성됩니다.
	- 관리자로 로그인하면 관리자용 메뉴를 추가로 사용할 수 있습니다.
	- 기울임체로 표시된 메뉴는 구성을 위한 샘플페이지가 제공되며 기능은 구현되지 않은 상태입니다.
	</pre>
	<br>
	<img src="${pageContext.request.contextPath}/images/home/sample/menu_sht.jpg" alt="단순 홈페이지 메뉴구성">
	
	<br>
	<a href="http://www.egovframe.org/wiki/doku.php?id=aramframework:let" target="_blank">
	<font color="blue">경량환경 템플릿 위키가이드 보기</font>
	</a> 
	<br>

</div>
<!-- //전체 레이어 끝 -->
</body>
</html>