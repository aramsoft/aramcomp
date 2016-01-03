<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ImgFileList.jsp
  * @Description : 이미지 파일 조회화면
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
<table>
    <c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<tr>
	   	<td></td>
	</tr>
	<tr>
	   	<td>
	   		<img src='${pageContext.request.contextPath}/content/imagefiles/${fileVO.pathId}/file/${fileVO.fileSn}?width=550'  width="550" alt="해당파일이미지"/>
	   	</td>
	</tr>
	<tr>
	   	<td></td>
    </tr>
    </c:forEach>
</table>
