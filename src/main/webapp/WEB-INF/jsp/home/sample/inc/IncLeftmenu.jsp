<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "java.util.HashMap" %>
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
<%
String menuNo = ((String)session.getAttribute("menuNo")!=null)?(String)session.getAttribute("menuNo"):"10";
%>

<div id="leftnavi">	
	<div class="leftmenu_top"></div>             
	<div class="leftmenu_middle">
	<ul>
	    <% if (menuNo.indexOf("1")== 0) {%>
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageMove('10','EgovAboutSite.do')">사이트소개</a>
			<ul>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('11','EgovAboutSite.do')">소개</a></li>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('12','EgovHistory.do')">연혁</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('13','EgovOrganization.do')">조직소개</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('14','EgovLocation.do')">찾아오시는 길</a></li>
			</ul> 
		</li>
		<% } %>
		<% if (menuNo.indexOf("2")== 0) {%>
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageMove('20','EgovProductInfo.do')">정보마당</a>
			<ul>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('21','EgovProductInfo.do')">주요사업 소개</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('22','EgovServiceInfo.do')">대표서비스 소개</a></li>
			</ul> 
		</li>
		<% } %>
        <% if (menuNo.indexOf("3")== 0) {%>
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageMove('30','EgovDownload.do')">고객지원</a>
			<ul>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('31','EgovDownload.do')">자료실</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('32','EgovQA.do')">묻고답하기</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageMove('33','EgovService.do')">서비스신청</a></li>
			</ul>
		</li>
		<% } %>
        <% if (menuNo.indexOf("4")== 0) {%>
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageAction('40','listSchdulMonth.do')">알림마당</a>
			<ul>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('41','listSchdulMonth.do')">이번달 행사</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('42','listSchdulWeek.do')">금주의 행사</a></li>
			    <li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('43','listSchdulDaily.do')">오늘의 행사</a></li>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('44','content/board/BBSMSTR_AAAAAAAAAAAA/articles')">공지사항</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('45','content/board/BBSMSTR_BBBBBBBBBBBB/articles')">사이트갤러리</a></li>
			</ul> 
		</li>
		<% } %>
        <% if (menuNo.indexOf("5")== 0) {%>
		<li class="leftmenu_dept01">
			<a href="javascript:fn_main_headPageAction('50','listSchdulMonth.do')">사이트관리</a>
			<ul>	
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('51','listSchdulMonth.do')">일정관리</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('52','listTemplate.do')">게시판템플릿관리</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('53','listBoardMaster.do')">게시판생성관리</a></li>
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('54','listBoardUseInf.do')">게시판사용관리</a></li> 
				<li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('55','content/board/BBSMSTR_AAAAAAAAAAAA/articles')">공지사항관리</a></li>
                <li class="leftmenu_dept02"><a href="javascript:fn_main_headPageAction('56','content/board/BBSMSTR_BBBBBBBBBBBB/articles')">사이트갤러리관리</a></li>
			</ul> 
		</li>
		<% } %>
	</ul>
	<br><br>
	</div>
	<div class="leftmenu_bottom"></div>		
</div>
<!-- //메뉴 끝 -->	
