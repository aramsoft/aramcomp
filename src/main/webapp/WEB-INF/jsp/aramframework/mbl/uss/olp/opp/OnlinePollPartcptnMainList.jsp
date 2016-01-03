<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>온라인POLL 메인리스트</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javaScript">
<!--
function fncSelectPoll(pollId) {
    document.pollForm.pollId.value = pollId;
    document.pollForm.submit();     
}
-->
</script>
</head>
<body>
<form name="pollForm" method="post" action="${pageContext.request.contextPath}/uss/olp/opp/insertOnlinePollPartcptn.do">
<table width="200" class="table-list" border="0">
<tbody>
<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
<c:if test="${fn:length(resultList) == 0}">
<tr> 
<td class="lt_text6" colspan="2">
	<spring:message code="common.nodata.msg" />
</td>
</tr>   	          				 			   
</c:if>
<%-- 데이터를 화면에 출력해준다 --%>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<c:if test="${status.count < 6}">
<tr class="link" onclick="fncSelectPoll('<c:out value="${resultInfo.pollId}"/>');">
    <td class="lt_text6" width="80">
    <div class="divDotText" style="width:80px; border:solid 0px;" align="center">
		<c:out value="${resultInfo.pollNm}"/> 	   	
    </div>
    </td>
    <td class="lt_text6" width="60px"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
</tr>
</c:if>
</c:forEach>
</tbody>  
</table>
<input name="pollId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="1"/>
</form>
</body>
</html>
