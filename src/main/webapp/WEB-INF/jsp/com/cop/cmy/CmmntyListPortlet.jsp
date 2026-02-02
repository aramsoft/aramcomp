<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmmntyListPortlet.jsp
  * @Description : 커뮤니티 목록 조회 포틀릿화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<ul>
<c:forEach var="result" items="${resultList}" varStatus="status">
   	<li style="padding-top:5px">
		<form name="cmmntyForm" method="get"  action="${pageContext.request.contextPath}/apps/${result.cmmntyAlias}">
		<input type="submit" value="<c:out value="${result.cmmntyNm}"/>"/>
		</form>
	</li>
</c:forEach>
</ul>
