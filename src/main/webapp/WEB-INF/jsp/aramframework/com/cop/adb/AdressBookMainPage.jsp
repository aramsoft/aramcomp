<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : AdressBookMainPage.jsp
  * @Description :주소록 목록 메인 페이지
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
<title>주소록 목록 메인 페이지</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="border" style="width:200px">

<form:form modelAttribute="searchVO" action ="" method="post">
<input type="hidden" name="adbkId" value=""/>

<table class="table-list" summary="주소록에 대한 목록을 제공한다.(마이페이지용)">
<tbody>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<c:if test="${status.count < 6}">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.adbkId}"/>'); return false;">
		<td class="lt_text6"><c:out value="${result.adbkNm}"/>"</td>
   		<td class="lt_text6" width="60px"><c:out value="${result.frstRegistPnttm}"/></td>
	</tr>
	</c:if>
	</c:forEach>
</tbody>
</table>

</form:form>

</div>

<script type="text/javaScript">

function fn_aram_detail(adbkId) { 
    var varForm = document.getElementById("searchVO");
    varForm.adbkId.value = adbkId;
    varForm.action = "${pageContext.request.contextPath}/cop/adb/editAdressBook.do";
    varForm.submit();
}

</script>

</body>
</html>
