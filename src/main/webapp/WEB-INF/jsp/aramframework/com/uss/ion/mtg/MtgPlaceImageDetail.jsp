<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceImageDetail.jsp
 * @Description : 회의실 이미지 상세조회
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
<title>회의실 이미지 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<!--  등록  폼 영역  -->
<table class="table-list" summary="회의실 이미지">
<caption>회의실 이미지</caption>
<thead>
  	<tr>
    	<th width="100%">회의실 이미지</th>
  	</tr>
</thead> 
<tbody>
	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<tr>
 		<td>
 		  <img src='${pageContext.request.contextPath}/content/imagefiles/${fileVO.fileArchId}/file/0'  width="640" />
 		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

<table border="0"  >
  	<tr> 
		<td><span class="button"><a href="#" onclick="javascript:fn_aram_image_close(); return false;"><spring:message code="button.close" /></a></span></td>
 	</tr>
</table>

</div>

<script type="text/javascript">

/* ********************************************************
 * Window Close()  처리
 ******************************************************** */
function fn_aram_image_close() {
	parent.window.close();
}
 
</script>

</body>
</html>