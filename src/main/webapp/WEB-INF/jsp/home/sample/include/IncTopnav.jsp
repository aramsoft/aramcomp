<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : IncTopnav.jsp
  * @Description : 상단메뉴(include)
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
<script type="text/javascript">

	function fn_main_headPageMove(menuNo, url){
		var pageUrl = "${pageContext.request.contextPath}"
            		+ "/home/sample/"+url;
		document.formMove.menuNo.value=menuNo;
		document.formMove.action = pageUrl;
		document.formMove.method = "get";
		document.formMove.submit();
    }
    
    function fn_main_headPageAction(menuNo, url){
		var pageUrl = "${pageContext.request.contextPath}"
    			    + "/home/sample/"+url;
		if( pageUrl.indexOf("?") != -1 ) {
			pageUrl = pageUrl + "&menuNo="+menuNo;
		} else {
			pageUrl = pageUrl + "?menuNo="+menuNo;
		}
		location.href = pageUrl;
    }
    
</script>

<!-- topmenu start -->
<form name="formMove" action="#" style="display:none;">
	<input name="menuNo" type="hidden">
</form>

<ul>
	<li><a href="javascript:fn_main_headPageMove('10','page/EgovAboutSite.do')">사이트소개</a></li>
    <li><a href="javascript:fn_main_headPageMove('20','page/EgovProductInfo.do')">정보마당</a></li>
    <li><a href="javascript:fn_main_headPageMove('30','page/EgovDownload.do')">고객지원</a></li>
    <li><a href="javascript:fn_main_headPageAction('40','listSchdulMonth.do')">알림마당</a></li>
    <c:if test="${role == 'ROLE_ADMIN'}">
    <li><a href="javascript:fn_main_headPageAction('50','listSchdulMonth.do')">사이트관리(관리자)</a></li>
	</c:if>
</ul>

<!-- //topmenu end -->