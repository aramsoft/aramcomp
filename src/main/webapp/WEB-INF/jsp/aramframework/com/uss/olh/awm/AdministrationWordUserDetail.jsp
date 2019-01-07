<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AdministrationWordUserDetail.jsp
 * @Description : 행정전문용어사전 상세조회
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
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>행정전문용어사전 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="administrationWordVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="administWordId" />

<table class="table-detail">
  	<tr>
    	<th width="20%">
    		행정용어명
    	</th>
    	<td width="80%">
			<c:out value="${administrationWordVO.administWordNm}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		행정용어영문명
    	</th>
    	<td>
			<c:out value="${administrationWordVO.administWordEngNm}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		행정용어약어명
    	</th>
    	<td>
			<c:out value="${administrationWordVO.administWordAbrv}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		주제영역
    	</th>
    	<td>
			<c:out value="${administrationWordVO.themaRelm}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		용어구분
    	</th>
    	<td>
    		<c:if test="${administrationWordVO.wordDomn == '1'}">표준어</c:if>
    		<c:if test="${administrationWordVO.wordDomn == '2'}">동의어</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		행정전문용어 정의
    	</th>
    	<td>
			<c:out value="${administrationWordVO.administWordDf}" escapeXml="false" />
     	</td>
  	</tr>
  	<tr>
    	<th>
     		행정전문용어 설명
    	</th>
    	<td>
			<c:out value="${administrationWordVO.administWordDc}" escapeXml="false" />
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

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("administrationWordVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/awm/listAdministrationWordUser.do";
    varForm.submit();
}

</script>
