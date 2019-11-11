<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : SysHistDetail.jsp
  * @Description : 시스템 이력 상세조회
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
<title>시스템 이력 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>시스템 이력정보</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="sysHistoryVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="histId" />

<table class="table-detail" summary="이력구분, 시스템명, 이력내용, 등록일자를 나타내는 시스템 이력 상세보기 테이블이다.">
<CAPTION>시스템 이력정보</CAPTION>
  	<tr>
    	<th width="20%">
    		이력 구분
    	</th>
    	<td width="80%">
    		<c:out value="${sysHistoryVO.histSeCodeNm}"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		시스템명
    	</th>
    	<td>
    		<c:out value="${sysHistoryVO.sysNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="histCn">이력내용</label>
    	</th>
    	<td>
      		<textarea name="histCn" class="textarea"  cols="40" rows="8"  style="width:450px;" id="histCn"><c:out value="${sysHistoryVO.histCn}"/></textarea>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		등록일자
    	</th>
    	<td>
			<fmt:formatDate value="${sysHistoryVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/>
		</td>
  	</tr>
  	<c:if test="${sysHistoryVO.atchFileId != ''}">
	<tr>
	    <th>
 	    	<spring:message code="sym.log.atchFileList" scope="row" />
	    </th>
	    <td>
			<c:import url="/content/files/${sysHistoryVO.atchFileId}" />
	    </td>
	</tr>
  	</c:if>
</table>
 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${sysHistoryVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${sysHistoryVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${sysHistoryVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${sysHistoryVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>
 
</DIV>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/listSysHistory.do";
    varForm.submit();
}

function fn_aram_edit(){
    var varForm = document.getElementById("sysHistoryVO");
    varForm.action = "${pageContext.request.contextPath}/sym/log/slg/editSysHistory.do";
    varForm.submit();
}

function fn_aram_delete(){
    var varForm = document.getElementById("sysHistoryVO");

	if (confirm("<spring:message code='common.delete.msg'/>")) {
    	varForm.action = "${pageContext.request.contextPath}/sym/log/slg/deleteSysHistory.do";
    	varForm.submit();
	}	
}

</script>

</body>
</html>
