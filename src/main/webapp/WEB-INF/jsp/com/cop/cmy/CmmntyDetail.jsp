<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmmntyDetail.jsp
  * @Description : 커뮤니티 상세조회
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
	<h2>커뮤니티 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	  		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<c:if test="${isAdmin=='true' and curTrgetId == ''}">
	  			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	 		</c:if>
		</span>
	</div>
</div>

<form:form modelAttribute="communityVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="cmmntyId" />

<table class="table-detail">
	<tr>
	    <th width="20%">
	    	커뮤니티 명
	    </th>
	    <td colspan="3">
	    	<c:out value="${communityVO.cmmntyNm}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	커뮤니티 소개
	    </th>
	    <td colspan="3">
	    	<c:out value="${communityVO.cmmntyIntrcn}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	템플릿 정보
	    </th>
	    <td colspan="3">
	    	<c:out value="${communityVO.tmplatNm}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	게시판 정보
	    </th>
	    <td colspan="3">
	    	<c:forEach var="result" items="${bbsList}" varStatus="status">
	     	<c:out value="${result.bbsNm}" /> /
	     	</c:forEach>
	    </td>
	</tr>
	<tr>
	    <th>
	    	사용여부
	    </th>
	    <td colspan="3">
	    	<c:if test="${communityVO.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
	    	<c:if test="${communityVO.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
	    </td>
	</tr>
	<tr>
	    <th>
	    	회원인증여부
	    </th>
	    <td colspan="3">
	    	<c:if test="${communityVO.memberAt == 'Y'}">예</c:if>
	    	<c:if test="${communityVO.memberAt == 'N'}">아니오</c:if>
	    </td>
	</tr>
	<tr>
	    <th width="20%">
	    	생성자
	    </th>
	    <td width="30%">
	    	<c:out value="${communityVO.frstRegisterNm}" />
	    </td>
	    <th width="20%">
	    	생성일시
	    </th>
	    <td width="30%">
	     	<fmt:formatDate value="${communityVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	Home URL
	    </th>
	    <td colspan="3">
			<a href="<c:out value="${communityVO.homeUrl}" />" target="_new">
	    	   	<c:out value="${communityVO.homeUrl}" />
			</a>
	    </td>
	</tr>
	<tr>
	    <th>
	    	제공 URL
	    </th>
	    <td colspan="3">
			<a href="<c:out value="${communityVO.provdUrl2}" />" target="_new">
	    	   	<c:out value="${communityVO.provdUrl2}" />
			</a>
	    </td>
	</tr>
  	<tr>
    	<th>
    		커뮤니티<br>로고이미지
    	</th>
    	<td colspan="3">
     		<c:if test="${communityVO.cmmntyLogoImage ne null}">
    		<img src="${pageContext.request.contextPath}/apps/id/${communityVO.pathId}/logo" alt="커뮤니티로고이미지" title="커뮤니티로고이미지">
    		</c:if>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

<div style="margin-top:10px; width:100%"></div>

</DIV>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("communityVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listCommunity.do";
    varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("communityVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/editCommunity.do";
    varForm.submit();
}

</script>

