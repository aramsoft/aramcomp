<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : NoticeView.jsp
  * @Description : 게시물 조회 화면
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
<DIV id="main"> 

<div class="content_title">
	<h2><c:out value='${boardVO.nttSj}'/></h2>
	<c:if test="${boardVO.frstRegisterId == uniqId}">
		<a href="${directUrl}" target="new">[수정]</a>
	</c:if>
</div>

<div style="margin:0; width:100%;">
   	<div class="bbs_cn">
	<c:out value="${boardVO.nttCn}" escapeXml="false" />
   	</div>
</div>

</DIV>
