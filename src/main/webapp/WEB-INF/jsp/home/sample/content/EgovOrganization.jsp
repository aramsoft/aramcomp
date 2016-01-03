<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovOrganization.jsp
  * @Description : 샘플화면 - 조직도(sample)
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
		<div id="content">
			<!-- 현재위치 네비게이션 시작 -->
			<div id="cur_loc">
				<ul>
					<li>HOME</li>
					<li>&gt;</li>
					<li>사용자관리</li>
					<li>&gt;</li>
					<li><strong>조직소개</strong></li>
				</ul>
			</div>				
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/sample/subtitle/img_subtitle01.gif" width="776" height="230" alt="">
			</div>
			   
			<!-- main content 시작 -->
			<div class="content_header">
				<h2>조직소개</h2>
			</div>
			<div class="content_field">
				<h3>조직</h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<p>오픈커뮤니티의 초기 정착을 위해 표준프레임워크 개발 참여자와 국내 주요 오픈커뮤니티의 운영자․전문가를 리딩그룹(PMC, 커미터)으로 구성</p>
					<p>오픈커뮤니티의 지속적인 확대․발전을 위해 프로젝트 활동에 적극적으로 참여하는 커뮤니티 회원이 리딩그룹의 역할을 획득할 수 있도록 투명하고 공정한 의사결정 체계를 수립</p>
				</fieldset>							
			</div>
			<!-- //main content 끝 -->
		</div>
