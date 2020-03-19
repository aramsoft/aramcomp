<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : IndndlpgeCntntsEdit.jsp
 * @Description : 마이페이지 컨텐츠 수정
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
<title>마이페이지 컨텐츠 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>마이페이지 컨텐츠 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="indvdlPgeCntntsVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="cntntsId" />

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
	    	컨텐츠 명
    	</th>
    	<td>
    		<form:input title="컨텐츠명" path="cntntsNm" maxLength="100"  size="80" />
    		<form:errors path="cntntsNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
	    	연계 URL
    	</th>
    	<td>
    		<form:input title="연계 URL" path="cntcUrl"  maxLength="256" size="80" />
    		<form:errors path="cntcUrl" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
	    	상세보기 URL
    	</th>
    	<td>
    		<form:input title="상세보기 URL" path="cntntsLinkUrl" maxLength="256" size="80" />
    		<form:errors path="cntntsLinkUrl" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		사용 여부
    	</th>
    	<td>
      		&nbsp;
      		<input name="cntntsUseAt" id="cntntsUseAtY" type="radio" <c:if test="${indvdlPgeCntntsVO.cntntsUseAt == 'Y'}">checked</c:if> value="Y">
	      	Y
      		&nbsp;&nbsp;
      		<input name="cntntsUseAt" id="cntntsUseAtN" type="radio" <c:if test="${indvdlPgeCntntsVO.cntntsUseAt == 'N'}">checked</c:if> value="N">
	      	N
      		<form:errors path="cntntsUseAt" cssClass="error"/>
      	</td>
  	</tr>
    <tr>
    	<th>
    		<span class="required_icon"></span>
	    	컨텐츠 설명
    	</th>
    	<td>
    		<form:textarea path="cntntsDc" cols="80" rows="10" title="컨텐츠 설명" />
    		<form:errors path="cntntsDc" cssClass="error"/>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="indvdlPgeCntntsVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 컨텐츠 목록 보기 화면으로 이동
 ******************************************************** */
function fn_aram_list() {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/listIndvdlpgeCntnts.do";
    varForm.submit();       
}

/* ********************************************************
* 컨텐츠 정보 수정
******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
	
	if(!validateIndvdlPgeCntntsVO(varForm)){           
   	   	return;
   	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
   	    varForm.action = "${pageContext.request.contextPath}/uss/mpe/updateIndvdlpgeCntnts.do";
        varForm.submit();
    }
}

</script>

</body>
</html>
