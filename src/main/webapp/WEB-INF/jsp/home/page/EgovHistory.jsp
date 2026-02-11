<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovHistory.jsp
  * @Description : 샘플화면 - 사이트 연혁(sample)
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
		<div id="content">
		<!-- 현재위치 네비게이션 시작 -->
			<div id="cur_loc">
				<ul>
					<li>HOME</li>
					<li>&gt;</li>
					<li>사용자관리</li>
					<li>&gt;</li>
					<li><strong>연혁</strong></li> 
				</ul>
			</div>				
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/subtitle/img_subtitle01.gif" width="776" height="230" alt="그림테스트">
			</div>
			   
			<!-- main content 시작 -->
			<div class="content_header">
				<h2>표준프레임워크 연혁</h2>
			</div>
			<div class="content_field">
				<h3>History</h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<p>test</p>
				</fieldset>			
			</div>
			<!-- //main content 끝 -->
		</div>
