<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV id="main">

<c:if test="${loginVO == null}">
	 아람소프트 컴포넌트에 오신 것을 환영합니다. !!!<br/>
    <a href="${pageContext.request.contextPath}/uat/uia/loginUsr.do?targetUrl=/UnitContent.do"><b>로그인</b></a>
    <br/><br/>
</c:if>

<c:if test="${loginVO != null}">
	${loginVO.name}님 환영합니다. !!! <br/>
	<c:if test="${loginVO.userSe == 'GNR' }">
 	<a href="${pageContext.request.contextPath}/uss/umt/editMber.do?mberId=${loginVO.userId}"><b>일반회원수정</b></a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${loginVO.userSe == 'ENT' }">
	<a href="${pageContext.request.contextPath}/uss/umt/editEntrprsMber.do?entrprsmberId=${loginVO.userId}"><b>기업회원수정</b></a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${loginVO.userSe == 'USR' }">
	<a href="${pageContext.request.contextPath}/uss/umt/editEmplyr.do?emplyrId=${loginVO.userId}"><b>업무회원수정</b></a>&nbsp;&nbsp;
	</c:if>
	<a href="${pageContext.request.contextPath}/uat/uia/actionLogout.do?targetUrl=/UnitContent.do"><b>로그아웃</b></a>
	<br/><br/>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	사용자  메인화면
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/sym/mnu/mpm/MainMenuHome.do" target="_new">
      		사용자  메인화면(포탈)
      	</a>
      </td>
    </tr>
</table>

<a href="/uat/uia/listActiveUsers.do">Active Users</a>
</c:if>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	게시판 목록
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/1/list">
      		공지사항 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/11/list">
      		일반게시판 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/21/list">
      		유효게시판 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/31/list">
      		익명게시판 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/41/list">
      		프로그램팁 
      	</a>
      </td>
    </tr>
<!-- 
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/51/list">
      		버그리포트
      	</a>
      </td>
    </tr>
-->
</table>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	커뮤니티 목록
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/apps/opensource" target="_new">
      		오픈소스모임
      	</a>
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	홈페이지 목록
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/home/sample/main.do" target="_new">
      		샘플 홈페이지
      	</a>
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	KOMORAN 
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
		<a href="${pageContext.request.contextPath}/testKomoran.do">
      	한글 형태소 분석기 테스트
      	</a>
      </td>
    </tr>
</table>
</DIV>
