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
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2009.03.31   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.31
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
       			<img src="${pageContext.request.contextPath}/content/mbl/imagefiles/${fileVO.pathId}/file/${fileVO.fileSn}?width=480" style="max-width:100%;" />
       		</td>
      	</tr>
      	<tr>
      		<td></td>
      	</tr>  
   </c:forEach>
</table>
