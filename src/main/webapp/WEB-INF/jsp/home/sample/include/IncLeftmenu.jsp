<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
 /**
  * @Class Name : IncLeftmenu.jsp
  * @Description : 좌메뉴 화면(include)
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

<c:set var="menuNo" value="${menuNo}"/>
<div id="leftnavi">	
	<div class="leftmenu_top"></div>             
	<div class="leftmenu_middle">
	<ul>
		<c:if test="${fn:substring(menuNo,0,1) == '1'}">
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageMove('10','page/EgovAboutSite.do')">사이트소개</a>
			<ul>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('11','page/EgovAboutSite.do')">소개</a></li>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('12','page/EgovHistory.do')">연혁</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('13','page/EgovOrganization.do')">조직소개</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('14','page/EgovLocation.do')">찾아오시는 길</a></li>
			</ul> 
		</li>
		</c:if>
		<c:if test="${fn:substring(menuNo,0,1) == '2'}">
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageMove('20','page/EgovProductInfo.do')">정보마당</a>
			<ul>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('21','page/EgovProductInfo.do')">주요사업 소개</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('22','page/EgovServiceInfo.do')">대표서비스 소개</a></li>
			</ul> 
		</li>
		</c:if>
		<c:if test="${fn:substring(menuNo,0,1) == '3'}">
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageMove('30','page/EgovDownload.do')">고객지원</a>
			<ul>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('31','page/EgovDownload.do')">자료실</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('32','page/EgovQA.do')">묻고답하기</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('33','page/EgovService.do')">서비스신청</a></li>
			</ul>
		</li>
		</c:if>
		<c:if test="${fn:substring(menuNo,0,1) == '4'}">
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageAction('40','listSchdulMonth.do')">알림마당</a>
			<ul>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('41','listSchdulMonth.do')">이번달 행사</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('42','listSchdulWeek.do')">금주의 행사</a></li>
			    <li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('43','listSchdulDaily.do')">오늘의 행사</a></li>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('44','board/BBSMSTR_AAAAAAAAAAAA/list')">공지사항</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('45','board/BBSMSTR_BBBBBBBBBBBB/list')">사이트갤러리</a></li>
			</ul> 
		</li>
		</c:if>
		<c:if test="${fn:substring(menuNo,0,1) == '5'}">
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageAction('50','listSchdulMonth.do')">사이트관리</a>
			<ul>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('51','listSchdulMonth.do')">일정관리</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('52','listTemplate.do')">게시판템플릿관리</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('53','listBoardMaster.do')">게시판생성관리</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('54','listBoardUseInf.do')">게시판사용관리</a></li> 
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('55','board/BBSMSTR_AAAAAAAAAAAA/list')">공지사항관리</a></li>
                <li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('56','board/BBSMSTR_BBBBBBBBBBBB/list')">사이트갤러리관리</a></li>
			</ul> 
		</li>
		</c:if>
	</ul>
	<br><br>
	</div>
	<div class="leftmenu_bottom"></div>		
</div>
<!-- //메뉴 끝 -->	
