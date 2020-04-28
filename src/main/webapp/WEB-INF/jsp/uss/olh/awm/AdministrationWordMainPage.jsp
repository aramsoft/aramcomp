<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AdministrationWordMainPage.jsp
 * @Description : 행정전문용어사전 메인 목록
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
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>행정전문용어사전 메인 목록</title>

</head>

<body>

<DIV id="main" style="width:200px;">

<form name="wordForm" method="post" action="${pageContext.request.contextPath}/uss/olh/awm/detailAdministrationWord.do">

<!--리스트역영 -->
<table class="table-list" border="0">
<tbody>
 	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="fn_aram_detail('<c:out value="${result.administWordId}"/>');">
    	<td class="lt_text6">
    		<div class="divDotText" style="width:60px; border:solid 0px;">
    		<span class="link">
    			<c:out value="${result.administWordNm}"/>
    		</span>
    		</div>
    	</td>
    	<td class="lt_text6" width="60px">
    		<fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
    	</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</form>

</DIV>

<script type="text/javascript">

function fn_aram_detail(wordId) {
    document.wordForm.administWordId.value = wordId;
    document.wordForm.submit();
}

</script>

</body>
</html>

