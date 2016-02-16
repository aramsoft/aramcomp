<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : BndtCeckManageDetail.jsp
 * @Description : 당직체크 상세조회
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
<title>당직체크 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직체크 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bndtCeckManageVO" method="post" action=""> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bndtCeckSe" />
<form:hidden path="bndtCeckCd" />

<table class="table-detail" summary="당직체크 상세">
<caption>당직체크 상세</caption>
  	<tr> 
    	<th width="20%">
    		당직체크구분
    	</th>
    	<td width="80%">
    		<c:out value='${bndtCeckManageVO.bndtCeckSeNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		당직체크코드
    	</th>          
    	<td>
    		<c:out value='${bndtCeckManageVO.bndtCeckCd}'/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		당직체크코드명
    	</th>          
    	<td>
    		<c:out value='${bndtCeckManageVO.bndtCeckCdNm}'/>
    	</td>    
  	</tr> 
  	<tr> 
    	<th>
    		사용여부
    	</th>
    	<td> 
    		<c:if test="${bndtCeckManageVO.useAt == 'Y' }"> 사용 </c:if>
         	<c:if test="${bndtCeckManageVO.useAt == 'N' }"> 미사용  </c:if>
        </td>    
  	</tr>     
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript">

/* ********************************************************
* 목록 으로 가기
******************************************************** */
function fn_aram_list() {
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtCeckManage.do";
	varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit() {
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/editBndtCeckManage.do";
	varForm.submit();   
}

 /* ********************************************************
  * 삭제처리화면
  ******************************************************** */

function fn_aram_delete() {
	var varForm = document.getElementById("bndtCeckManageVO");
	
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/deleteBndtCeckManage.do";
        varForm.submit();
    }
}

</script>

</body>
</html>
