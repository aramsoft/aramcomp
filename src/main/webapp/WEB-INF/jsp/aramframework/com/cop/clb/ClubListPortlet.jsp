<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ClubListPortlet.jsp
  * @Description : 동호회 목록 조회 포틀릿화면
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
<ul>
<c:forEach var="result" items="${resultList}" varStatus="status">
	<li style="padding-top:5px">
		<form name="clubForm3" method="post"  action="${pageContext.request.contextPath}/cop/clb/listClubPortlet.do">
		<input type="hidden" name="clbId"    value="<c:out value="${result.clbId}"/>"    />
		<input type="hidden" name="cmmntyId" value="<c:out value="${result.cmmntyId}"/>" />
		<input type="hidden" name="param_clbId"/>
		<input type="submit" value="<c:out value="${result.clbNm}"/>"/>
		</form>
	</li>	
</c:forEach>
</ul>
