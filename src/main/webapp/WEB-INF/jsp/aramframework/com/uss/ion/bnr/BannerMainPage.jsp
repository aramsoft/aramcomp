<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BannerMainPage.jsp
 * @Description : 메인화면 배너 목록조회
 * @Modification Information
 * @
 * @ 수정일              수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2014.11.11   조헌철          최초 생성
 *
 *  @author 아람컴포넌트 조헌철
 *  @since 2014.11.11
 *  @version 1.0
 *  @see
 *  
 */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>메인화면 배너 목록조회</title>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="margin:0 auto;width:200px;">

<form name="items" method="post" action="${pageContext.request.contextPath}/uss/ion/bnr/getBanner.do">
<table class="table-list" summary="메인화면에서 배너에 대한 목록을 제공한다.">
<caption>메인화면 배너 목록조회</caption>
 	<tbody>
 	<c:forEach var="banner" items="${bannerList}" varStatus="status">
  	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${banner.bannerId}"/>'); return false;">
    	<td class="lt_text6">
			<c:out value="${banner.bannerNm}"/>
    	</td>
  	</tr>
 	</c:forEach>
 	</tbody> 
</table>

<input type="hidden" name="bannerId" value="">
</form>

</DIV>

<script type="text/javascript">

function fn_aram_detail(bannerId) {
    document.items.bannerId.value = bannerId;
    document.items.action = "${pageContext.request.contextPath}/uss/ion/bnr/getBannerImage.do";
    document.items.submit();     
}

</script>

</body>
</html>
