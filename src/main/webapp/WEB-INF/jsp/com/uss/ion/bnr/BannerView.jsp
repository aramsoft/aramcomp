<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : BannerView.jsp
 * @Description : 배너이미지 배열
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
<!-- 세로배열 -->
<c:if test="${resultType == 'vertical'}">
<table summary="배너이미지 세로배열">
<caption>배너이미지 배열</caption>
    <c:forEach var="result" items="${fileList}" varStatus="status">
    <tr>
    	<td>
            <a href="<c:out value="${result.linkUrl}"/>" target="_blank"  title="새 창으로 이동"><img alt="배너 이미지" src='${pageContext.request.contextPath}/content/imagefiles/${result.bannerImageFile}/file/0'></a>
       	</td>
    </tr>
    </c:forEach>
</table>
</c:if>

<!-- 가로배열 -->
<c:if test="${resultType == 'horizontal'}">
<table style="width:700px; height:90px; table-layout:fixed;" summary="배너이미지 가로배열">
<caption>배너이미지 배열</caption>
  	<tr>
    <c:forEach var="result" items="${fileList}" varStatus="status">
       	<td>
       		<a href="<c:out value="${result.linkUrl}"/>" target="_blank"  title="새 창으로 이동"><img alt="배너 이미지" src='${pageContext.request.contextPath}/content/imagefiles/${result.bannerImageFile}/file/0'></a>
       	</td>
    </c:forEach>
  	</tr>
</table>
</c:if>
