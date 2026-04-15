<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MainView.jsp 
  * @Description : 메인화면
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>표준프레임워크 경량환경 홈페이지 템플릿</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home/common.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/jquery-1.7.1.min.js"></script>
</head>

<body> 
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<header>
	    <c:import url="/home/include/IncHeader.do" />
	</header>
	<div id="topnavi">
	    <c:import url="/home/include/IncTopnav.do" />
	</div>		
	<!-- //header 끝 -->
	<!-- container 시작 -->
	<div id="main_container">
		<div class="lefttitle_image">
		    <img src="${pageContext.request.contextPath}/images/home/index/main_titleimage.gif" width="200px"
		         alt="경량환경 내부업무 홈페이지/전자정부 표준프레임워크의 경량환경 내부 업무에 대한 최신정보와 기술을 제공하고 있습니다.">
			<br><br><br><br><br>
		</div>
		
		<!-- code layer -->
		<div class="rightmain_wrap">			
			<div class="board_wrap">
				<!-- 공지사항 시작 -->
				<div class="notice_area">
					<h3 class="notice_title">
						<img src="${pageContext.request.contextPath}/images/home/index/img_subtitle01.gif" alt="공지사항">
					</h3>
					<a href="#" onclick="javascript:fn_main_headPageAction('43','/home/board/7/list'); return false;" class="more">
						<img src="${pageContext.request.contextPath}/images/home/index/btn_more.gif" alt="more">
					</a>
					<ul>
					<c:forEach var="result" items="${notiList}" varStatus="status">
					<li>
						<div class="notice_lefttext">
							<a href="#" onclick="javascript:fn_main_headPageAction('43','/home/board/7/article/${result.nttId}'); return false;">
					        <c:if test="${result.replyLc!=0}">
                                <c:forEach begin="0" end="${result.replyLc}" step="1">
                                    &nbsp;
                                </c:forEach>
                                <img src="${pageContext.request.contextPath}/images/home/reply_arrow.gif" alt="reply arrow">
                            </c:if>
                            <c:choose>
                                <c:when test="${result.isExpired=='Y' || result.useAt == 'N'}">
                                    <c:out value="${result.nttSj}" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${result.nttSj}" />
                                </c:otherwise>
                            </c:choose>
                            </a>
					    </div>
					    <div class="notice_righttext"><c:out value="${result.frstRegisterPnttm}"/></div>
					</li>
					</c:forEach>
					</ul>
				</div>	 
				<!-- //공지사항 끝 -->
				<!--  QA시작 -->
				<div class="notice_area">
					<h3 class="notice_title">
						<img src="${pageContext.request.contextPath}/images/home/index/img_subtitle02.gif" alt="묻고 답하기">
					</h3> 
					<a href="#" onclick="javascript:fn_main_headPageAction('44','/home/board/8/list'); return false;" class="more">
						<img src="${pageContext.request.contextPath}/images/home/index/btn_more.gif" alt="more">
					</a>
					<ul>
					<c:forEach var="result" items="${galList}" varStatus="status">
                    <li> 
                    	<div class="notice_lefttext">
							<a href="#" onclick="javascript:fn_main_headPageAction('44','/home/board/8/article/${result.nttId}'); return false;">
                            <c:if test="${result.replyLc!=0}">
                                <c:forEach begin="0" end="${result.replyLc}" step="1">
                                    &nbsp;
                                </c:forEach>
                                <img src="${pageContext.request.contextPath}/images/home/reply_arrow.gif" alt="reply arrow">
                            </c:if>
                            <c:choose>
                                <c:when test="${result.isExpired=='Y' || result.useAt == 'N'}">
                                    <c:out value="${result.nttSj}" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${result.nttSj}" />
                                </c:otherwise>
                            </c:choose>
                            </a>
                        </div>
                        <div class="notice_righttext"><c:out value="${result.frstRegisterPnttm}"/></div>
                    </li>
                    </c:forEach>
					</ul>
				</div>
				<!--  //QA끝 -->				
			</div>
			<div class="info_divwrap"> 
				<div class="righttop_banner"> 
					<ul>
						<li>
							<a href="#" onclick="javascript:fn_main_headPageMove('21','/home/page/EgovProductInfo.do'); return false;">
								<img src="${pageContext.request.contextPath}/images/home/index/img_product.gif" alt="주요사업 소개" title="샘플화면으로 이동합니다.(주요사업 소개)">
							</a>
						</li>
						<li>
							<a href="#" onclick="javascript:fn_main_headPageMove('22','/home/page/EgovServiceInfo.do'); return false;">
								<img src="${pageContext.request.contextPath}/images/home/index/img_service.gif" alt="대표서비스 소개" title="샘플화면으로 이동합니다.(대표서비스 소개)">
							</a>
						</li>
					</ul>
				</div>
				<div class="middle_banner" style="">
					<ul>
						<li class="li_btm">
							<a href="#" onclick="javascript:fn_main_headPageMove('31','/home/page/EgovDownload.do'); return false;">
								<img src="${pageContext.request.contextPath}/images/home/index/img_download.gif" alt="자료실:다양한자료를 다운로드 받으실 수 있습니다." title="샘플화면으로 이동합니다.(자료실)">
							</a>
						</li>
						<li>
							<a href="#" onclick="javascript:fn_main_headPageMove('14','/home/page/EgovLocation.do'); return false;">
								<img src="${pageContext.request.contextPath}/images/home/index/img_location.gif" alt="표준프레임워크센터:찾아오시는 길" title="샘플화면으로 이동합니다.(찾아오시는길)">
							</a>
						</li>
					</ul>
				</div>
			</div>
				
		</div>
		<!-- 하단 서비스 영역  -->
		<div id="main_btm_area">
			<ul>
				<li class="img_div">
					<a href="#" onclick="javascript:fn_main_headPageMove('33','/home/page/EgovService.do'); return false;">
				    	<img src="${pageContext.request.contextPath}/images/home/index/img_service_btm.gif" alt="서비스 신청" title="샘플화면으로 이동합니다.(서비스 신청)">
				    </a>
				</li>
				<li class="text_div">
					표준프레임워크 경량환경 홈페이지의 다양한 서비스를 신청 하실 수 있습니다.
				</li>  
				<li>
					<img src="${pageContext.request.contextPath}/images/home/index/img_vline.gif" alt="">
				</li> 
				<li class="img_div">
			    	<a href="#" onclick="javascript:fn_main_headPageAction('40','/home/cop/smt/sim/listSchdulMonth.do'); return false;">
			    		<img src="${pageContext.request.contextPath}/images/home/index/img_schedule.gif" alt="일정 현황">
			    	</a>
				</li>
				<li class="text_div">
					표준프레임워크 경량환경 홈페이지의 전체적인 일정현황을 조회하실 수 있습니다.
				</li>   
			</ul>
		</div>
		<!-- //code layer -->		
	</div>
	<!-- container 끝-->
	
	<footer>
		<c:import url="/home/include/IncFooter.do" />
	</footer>
</div>
<!-- //전체 레이어 끝 -->

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments);},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m);
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-50032174-1', 'aramsoft.co.kr');
  ga('send', 'pageview');

</script>

</body>
</html>

