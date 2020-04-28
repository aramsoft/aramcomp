<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : UserAbsnceMainPage.jsp
 * @Description : 사용자부재 목록
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
<title>사용자부재 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>

<form name="listForms" action="${pageContext.request.contextPath}/uss/ion/uas/detailUserAbsnce.do" method="post">
<div id="all" style="margin:0 auto;width:200px;" class="divDotText" align="left">
<table class="table-list" summary="사용자부재에 대한 목록을 제공한다.">
<tbody>
    <c:forEach var="result" items="${resultList}" varStatus="status">
    <tr class="link" onclick="fn_aram_detail('<c:out value="${result.userId}"/>','<c:out value="${result.regYn}"/>'); return false;">
        <td class="lt_text6">
        	<c:out value="${result.userNm}"/>
            <c:if test="${result.userAbsnceAt eq 'Y'}">(<c:out value="부재중"/>)</c:if>
        </td>
    </tr>
    </c:forEach>
</tbody>
</table>
</DIV>
<input type="hidden" name="userId" value="" />
<input type="hidden" name="selAbsnceAt" value="A" />
<input type="hidden" name="regYn" value="" />
<input type="hidden" name="pageIndex" value="1"/>
<input type="hidden" name="searchCondition" value="1"/>
</form>

<script type="text/javascript">

function fn_aram_detail(userId, regYn) {
	if(regYn == 'N') {
        location.replace("${pageContext.request.contextPath}/uss/ion/uas/registUserAbsnce.do?userId="+userId);
	}
    document.listForms.userId.value = userId;
    document.listForms.regYn.value = regYn;
    document.listForms.action = "${pageContext.request.contextPath}/uss/ion/uas/detailUserAbsnce.do";
    document.listForms.submit();
}

</script>

</body>
</html>
