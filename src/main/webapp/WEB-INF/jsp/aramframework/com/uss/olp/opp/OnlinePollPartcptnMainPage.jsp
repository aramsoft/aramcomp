<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollPartcptnMainPage.jsp
  * @Description : 온라인POLL 참여 메인리스트
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
<%--  자마스크립트 메세지 출력 --%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>온라인POLL 참여 메인리스트</title>

</head>

<body>

<DIV id="main" style="width:200px;">

<form name="pollForm" method="post" action="${pageContext.request.contextPath}/uss/olp/opp/insertOnlinePollPartcptn.do">

<table class="table-list" border="0">
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text6" colspan="2"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<c:if test="${status.count < 6}">
	<tr class="link" onclick="fn_aram_insert('<c:out value="${result.pollId}"/>'); return false;">
	    <td class="lt_text6" width="80">
	    <div class="divDotText" style="width:80px; border:solid 0px;">
			<c:out value="${result.pollNm}"/>   	
	    </div>
	    </td>
	    <td class="lt_text6" width="60px"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:if>
	</c:forEach>
</tbody>  
</table>

</form>

</DIV>

<script type="text/javascript">

function fn_aram_insert(pollId) {
    document.pollForm.pollId.value = pollId;
    document.pollForm.submit();     
}

</script>

</body>
</html>
