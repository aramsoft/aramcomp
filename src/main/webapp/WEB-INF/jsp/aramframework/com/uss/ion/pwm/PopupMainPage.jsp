<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : PopupMainPage.jsp
  * @Description : 팝업창관리 목록
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
<title>팝업창 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>현재 팝업창 목록</h2>
</div>

<table class="table-list" border="0">
<thead>
  	<tr>
	    <th width="35px">순번</th>
	    <th width="120px">제목</th>
	    <th width="180px">게시일</th>
	    <th>파일URL</th>
	    <th width="60px">게시상태</th>
  	</tr>
</thead>
<tbody>
<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	 <%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
	    <td class="lt_text3"><c:out value="${(popupManageVO.pageIndex-1) * popupManageVO.pageSize + status.count}"/></td>
	    <td class="lt_text3L">
	     	<div class="divDotText" style="width:200px; border:solid 0px;">
	     	<c:out value="${result.popupTitleNm}"/>
	     	</div>
	    </td>
	    <td class="lt_text3">
		 	<c:out value="${fn:substring(result.ntceBgnde, 0, 4)}-${fn:substring(result.ntceBgnde, 4, 6)}-${fn:substring(result.ntceBgnde, 6, 8)} ${fn:substring(result.ntceBgnde, 8, 10)}H ${fn:substring(result.ntceBgnde, 10, 12)}M"/>
		 	~
		 	<c:out value="${fn:substring(result.ntceEndde, 0, 4)}-${fn:substring(result.ntceEndde, 4, 6)}-${fn:substring(result.ntceEndde, 6, 8)} ${fn:substring(result.ntceEndde, 8, 10)}H ${fn:substring(result.ntceEndde, 10, 12)}M"/>
	    <td class="lt_text3L">
	    	<c:out value="${result.fileUrl}"/>
	    </td>
	    <td class="lt_text3">
	    	<c:out value="${result.ntceAt}"/>
	    </td>
	</tr>
	</c:forEach>
</tbody>
</table>

</DIV>
</body>
</html>

