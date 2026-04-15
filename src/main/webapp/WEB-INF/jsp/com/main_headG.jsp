<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">

function fn_main_headPageMove(menuNo, url){
	document.selectOne.menuNo.value=menuNo;
	document.selectOne.chkURL.value=url;
    document.selectOne.action = "${pageContext.request.contextPath}/sym/mnu/mpm/MainMenuIndex.do";
    document.selectOne.submit();
}

</script>

<form name="selectOne">
<input name="menuNo" type="hidden" />
<input name="chkURL" type="hidden" />
</form>
 
<div id="gnb">
	<div id="top_logo"><a href="${pageContext.request.contextPath}/sym/mnu/mpm/MainMenuHome.do" target="_top"><img src="${pageContext.request.contextPath}/images/com/logo_01.gif" alt="egovframe" /></a></div>
	<div id="use_descri">
  		<ul>
      		<li>공통서비스 테스트 사이트(일반사용자용)</li>
            <li><a href="${pageContext.request.contextPath}/uat/uia/actionLogout.do?targetUrl=/sym/mnu/mpm/MainMenuHome.do"><img src="${pageContext.request.contextPath}/images/com/logout_btn.gif" alt="로그아웃" /></a></li>
  		</ul>
	</div>
</div>
<div id="new_topnavi">
    <ul>
		<li><a href="${pageContext.request.contextPath}/sym/mnu/mpm/MainMenuHome.do" target="_top">HOME</a></li>
		<c:forEach var="result" items="${list_headmenu}" varStatus="status">
 		<li class="gap"> l </li>
 		<li><a href="javascript:fn_main_headPageMove('<c:out value="${result.menuNo}"/>','<c:out value="${result.chkURL}"/>')"><c:out value="${result.menuNm}"/></a></li>
		</c:forEach>
   	</ul>
</div>

