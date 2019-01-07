<!doctype html>
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
  * @Class Name : ReprtStatsRegist.jsp
  * @Description : 보고서통계 등록
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
<title>보고서통계 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>보고서통계 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="reprtStatsVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="보고서명, 보고서유형, 진행상태를 입력하여 보고서통계자료를 등록한다.">
<CAPTION style="display: none;">보고서 등록</CAPTION>
  	<tr>
    	<th scope="row" width="20%">
    		<label for="reprtId">보고서ID</label>
    		<span class="required_icon"></span>
    	</th>
    	<td class="lt_text">
    		<form:input path="reprtId" size="30" class="readOnlyClass" readonly="true" title="" />
    	</td>
  	</tr>
  	<tr>
    	<th scope="row" width="20%">
    		<label for="reprtNm">보고서명</label>
    		<span class="required_icon"></span>
    	</th>
    	<td class="lt_text">
    		<form:input path="reprtNm" maxLength="10" size="30" title="" />
    		<form:errors path="reprtNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th scope="row" width="20%">
    		<label for="reprtTy">보고서유형</label>
    		<span class="required_icon"></span>
    	</th>
    	<td class="lt_text">
        	<form:select path="reprtTy" title="">
                <form:options items="${COM040_reprtType}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th scope="row" width="20%">
    		<label for="reprtSttus">진행상태</label>
    		<span class="required_icon"></span>
    	</th>
    	<td class="lt_text">
        	<form:select path="reprtSttus" title="">
                <form:options items="${COM036_reprtSttus}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th scope="row" width="20%">
    		<label for="regDate">등록일시</label>
    		<span class="norequired_icon"></span>
    	</th>
    	<td class="lt_text">
    		<form:input path="regDate" maxLength="20" size="20" class="readOnlyClass" readonly="true" title="" />
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="pmReprtTy" />
<form:hidden path="pmDateTy" />
<form:hidden path="pmFromDate" />
<form:hidden path="pmToDate" />
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="reprtStatsVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("reprtStatsVO");
    varForm.action = "${pageContext.request.contextPath}/sts/rst/listReprtStats.do";
    varForm.submit();
}

function fn_aram_insert() {
    var varForm = document.getElementById("reprtStatsVO");

    if(!validateReprtStatsVO(varForm)){
        return;
    }
    
	if (confirm("<spring:message code='common.regist.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sts/rst/insertReprtStats.do";
        varForm.submit();
    }
}

</script>

</body>
</html>

