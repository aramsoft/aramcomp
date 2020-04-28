<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovLocation.jsp
  * @Description : 샘플화면 - 찾아오시는길(sample)
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
					<li><strong>찾아오시는길</strong></li>
				</ul>
			</div>				
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/sample/subtitle/img_subtitle01.gif" width="776" height="230" alt="">
			</div>  
			
			<!-- main content 시작 -->
			<div class="content_header">
				<h2>찾아오시는길</h2>
			</div>
			<div class="content_field">
				<h3>무교청사 찾아 오시는 길 </h3>
				<p><img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_egovframelocation.gif" width="656" height="402" alt="무교청사 약도"></p>

				<h3>상세안내 </h3>
				<fieldset>
					<legend>조건정보 영역</legend>
					<ul>
						<li>-<strong>지하철</strong>
							<ul>
								<li>[1호선]1호선 시청역 5번출구 ▶ 시청삼거리에서 좌회전 ▶서울파이낸스빌딩 옆</li>
								<li>[2호선]2호선 을지로입구역 1번출구 ▶ 시청삼거리에서 우회전 ▶ 맥도날드 건너편</li>
								<li>[5호선]5호선 광화문역 5번출구▶ 동아일보사 건너편</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li>-<strong>버스</strong>
							<ul>
								<li>파이낸셜빌딩 앞 서울신문사 정류장 하차. 청계천 방면 우회전, 동아일보사 건너편</li>
								<li>중앙인사위원회 건물 우측 한국정보화진흥원 빌딩 파랑색 간선노선버스와 초록색 지선노선버스인</li>
								<li>150, 162, 402, 0014, 0015, 1011, 1711, 7017, 7020, 7021 이용</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li>-<strong>승용차</strong>
							<ul>
								<li> 건물뒷편 주차장 입구에서 차량용 리프트를 이용하여 지하 주차장에 주차한 후 엘리베이터를     이용하여</li>
								<li> 1F 안내데스크에서 안내를 받아 주십시오.</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li>-<strong>공항에서 오시는 길(KAL LIMOUSINE BUS)</strong>
							<ul>
								<li>승차위치 : 인천공항(동4B, 11A), 코리아나호텔 정문 앞</li>
								<li>인천공항 → 코리아나호텔(첫차 : 05:55 / 간격 : 15~30분 / 막차 : 22:25분 / 소요시간 : 80분)</li>
								<li>코리아나호텔 → 인천공항(첫차 : 05:55 / 간격 : 15~30분 / 막차 : 18:45분 / 소요시간 : 80분)</li>
								<li>김포공항 → 인천공항(첫차 : 05:00 / 간격 : 15~30분 / 막차 : 21:30분 / 소요시간 : 40분)</li>
								<li>요금 : 코리아나호텔 ↔ 인천공항 13,000원 / 김포공항 → 인천공항 6,000원</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li>-<strong>(605번)</strong>
							<ul>
								<li>승차위치 : 광화문 빌딩 앞 좌석버스 정류장</li>
								<li>인천공항 → 코리아나호텔(첫차 : 06:20 / 간격 : 15분 / 막차 : 21:00분 / 소요시간 : 80~90분)</li>
								<li>코리아나호텔 → 인천공항(첫차 : 06:20 / 간격 : 15분 / 막차 : 23:00분 / 소요시간 : 80~90분)</li>
								<li>요금 : 코리아나호텔 → 인천공항 5,500원 </li>
							</ul>
						</li>
					</ul>								
				</fieldset>						
			</div>
			<!-- //main content 끝 -->
		</div>
