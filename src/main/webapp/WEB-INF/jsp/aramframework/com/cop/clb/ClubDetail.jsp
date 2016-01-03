<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ClubDetail.jsp
  * @Description : 동호회 상세조회
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
	<h2>동호회 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
     	<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<c:if test="${isAdmin=='true'}">
     		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</c:if>
	</div>
</div>

<form:form commandName="clubVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cmmntyId" />
<form:hidden path="clbId" />

<table class="table-detail" summary="동호회 명,동호회 소개,템플릿 정보,게시판 정보,사용여부,생성자,제공 URL 입니다">
	<tr>
	    <th width="20%">동호회 명</th>
	    <td width="80%" colspan="3">
	      	<c:out value="${clubVO.clbNm}" />
	    </td>
	</tr>
	<tr>
	    <th>동호회 소개</th>
	    <td colspan="3">
	      	<c:out value="${clubVO.clbIntrcn}" />
	    </td>
	</tr>
	<tr>
	    <th>템플릿 정보</th>
	    <td colspan="3">
	     	<c:out value="${clubVO.tmplatNm}" />
	    </td>
	</tr>
	<tr>
	    <th>게시판 정보</th>
	    <td colspan="3">
	    	<c:forEach var="result" items="${bbsList}" varStatus="status">
	     	<c:out value="${result.bbsNm}" /> /
	     	</c:forEach>
	    </td>
	</tr>
	<tr>
	    <th>사용여부</th>
	    <td colspan="3">
	    	<c:if test="${clubVO.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    	<c:if test="${clubVO.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	    </td>
	</tr>
	<tr>
	    <th width="20%">생성자</th>
	    <td width="30%">
	     	<c:out value="${clubVO.frstRegisterNm}" />
	    </td>
	    <th width="20%">생성일시</th>
	    <td width="30%">
	     	<fmt:formatDate value="${clubVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
	    </td>
	</tr>
	<tr>
	    <th>제공 URL</th>
	    <td colspan="3">
			<a href="<c:out value="${clubVO.provdUrl}" />" target="_new">
	    	   	<c:out value="${clubVO.provdUrl}" />
			</a>
	    </td>
	</tr>
	<tr>
	    <th>제공 URL2</th>
	    <td colspan="3">
			<a href="<c:out value="${clubVO.provdUrl2}" />" target="_new">
	    	   	<c:out value="${clubVO.provdUrl2}" />
			</a>
	    </td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("clubVO");
    varForm.action = "${pageContext.request.contextPath}/cop/clb/listClub.do";
    varForm.submit();
}

function fn_aram_edit(){
    var varForm = document.getElementById("clubVO");
    varForm.action = "${pageContext.request.contextPath}/cop/clb/editClub.do";
    varForm.submit();
}

</script>
