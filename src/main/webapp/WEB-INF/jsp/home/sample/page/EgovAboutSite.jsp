<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovAboutSite.jsp
  * @Description : 샘플화면 - 사이트소개(sample)
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
		<article id="content">
			<!-- 현재위치 네비게이션 시작 -->
			<div id="cur_loc">
				<ul>
					<li>HOME</li>
					<li>&gt;</li>
					<li>사용자관리</li>
					<li>&gt;</li>
					<li><strong>사이트소개</strong></li>
				</ul>
			</div>
			<!-- //현재위치 네비게이션 끝 -->				
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/sample/subtitle/img_subtitle01.gif" width="776" height="230" alt="사이트소개:표준프레임워크 경량환경의 개요와 연혁,조직소개,약도등의 정보를 제공합니다.">
			</div>
			
			<!-- main content 시작 -->
			<div class="content_header">
				<h2>전자정부표준프레임워크 소개</h2>
			</div>
			
			<div class="content_field">
				<h3>개요</h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<p>전자정부 표준 프레임워크는 응용SW의 구성기반이 되며 응용SW실행 시 필요한 기본 기능을 제공하는 환경이다. 전자정부 표준 프레임워크는 ‘전자정부 서비스의 품질향상 및 정보화 투자 효율성 향상’을 위해 개발 프레임워크 표준을 정립하고, 개발 프레임워크 표준 적용을 통한 응용 SW의 표준화 및 품질과 재사용성 향상을 목표로 한다.</p>
				</fieldset>	

				<p><img src="${pageContext.request.contextPath}/images/home/sample/img_content/temp01.gif" alt="그림 테스트"></p> 

				<h3>배경</h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<p>현재 전자정부는 유사한 기능을 가지는 다양한 종류 및 버전의 프레임워크를 개별 시스템 단위로 적용/관리하고 있으며, 이에 따라 다양한 문제점들이 발생하고 있다. 전자정부에 적용된 개발프레임워크는 Black Box 형태로 제공되어 사업자의 기술지원 없이는 응용 SW를 유지보수하기 어렵기 때문에 사업자에 대한 의존성이 발생한다. 복수개의 개발프레임워크가 적용된 사업의 경우, 프레임워크에 따라 개발표준 정의, 개발자수급, 교육시행 등 별도의 유지보수 체계를 갖추는 중복 투자가 발생하며, 개발프레임워크의 체계적인 관리절차의 미비로 동일 개발프레임워크라 하더라도 버전 관리에 어려움이 있다.전자정부의 프레임워크의 표준화는 사업자 고유 개발프레임워크에 대한 기술 종속성을 배제하고 표준화를 통해 응용 SW의 표준화와 품질, 재사용성을 향상시키며, 개발 프레임워크의 유지 보수 단일화를 통한 투자 효율성을 높인다.</p>
				</fieldset>				

				<h3>특징</h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<p>현재 전자정부는 유사한 기능을 가지는 다양한 종류 및 버전의 프레임워크를 개별 시스템 단위로 적용/관리하고 있으며, 이에 따라 다양한 문제점들이 발생하고 있다. 전자정부에 적용된 개발프레임워크는 Black Box 형태로 제공되어 사업자의 기술지원 없이는 응용 SW를 유지보수하기 어렵기 때문에 사업자에 대한 의존성이 발생한다. 복수개의 개발프레임워크가 적용된 사업의 경우, 프레임워크에 따라 개발표준 정의, 개발자수급, 교육시행 등 별도의 유지보수 체계를 갖추는 중복 투자가 발생하며, 개발프레임워크의 체계적인 관리절차의 미비로 동일 개발프레임워크라 하더라도 버전 관리에 어려움이 있다.전자정부의 프레임워크의 표준화는 사업자 고유 개발프레임워크에 대한 기술 종속성을 배제하고 표준화를 통해 응용 SW의 표준화와 품질, 재사용성을 향상시키며, 개발 프레임워크의 유지 보수 단일화를 통한 투자 효율성을 높인다.</p>
				</fieldset>				
			</div>
			<!-- //main content 끝 -->
		</article>
