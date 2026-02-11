<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovService.jsp
  * @Description : 샘플화면 - 대표서비스 조회화면(sample)
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
                    <li>고객지원</li>
                    <li>&gt;</li>
                    <li><strong>서비스신청</strong></li>
				</ul>
			</div>	
			<!-- //현재위치 네비게이션 끝 -->
			<!-- 타이틀 이미지 -->			
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/subtitle/img_subtitle03-03.gif" width="776" height="230" alt="">
			</div>
			
			<!-- main content 시작 -->
			<div class="content_header">
				<h2>서비스 신청</h2>
			</div>
			<div class="content_field">
				<h3>주요서비스안내</h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<p>서비스필요시 다음과 같은 절차로 신청하시면 됩니다.</p>
					<p>1. 필요한 서비스 확인</p>
					<p>2. 자료실에서 필요한 서비스 존재여부 확인</p>
					<p>3. 서비스요청을 통해 필요한 서비스 신청</p>
					<a href="${pageContext.request.contextPath}/home/page/EgovServiceInfo.do">대표 서비스 자세히 보기</a>
				</fieldset>			
			</div>
			<!-- //main content 끝 -->	
		</div>				
