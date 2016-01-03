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
 * @Class Name : BndtCeckManageRegist.jsp
 * @Description : 당직체크  등록
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
<title>당직체크  등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<c:if test="${!empty dplctMessage}">alert("${dplctMessage}");</c:if>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직체크  등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_reset(); return false;">초기화</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bndtCeckManageVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="당직체크 등록">
<caption>당직체크 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="bndtCeckSe">당직체크구분</label>
    	</th>          
    	<td width="80%">
      		<form:select path="bndtCeckSe" title="당직체크구분">
	      		<form:options items="${COM071_bndtCeckSe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="bndtCeckCd">당직체크코드</label>
    	</th>
    	<td>
      		<form:input  path="bndtCeckCd" size="15" maxlength="10" title="당직체크코드"/>
      		<form:errors path="bndtCeckCd" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="bndtCeckCdNm">당직체크코드명</label>
    	</th>          
    	<td>
      		<form:input  path="bndtCeckCdNm" size="60" maxlength="60" title="당직체크코드명"/>
      		<form:errors path="bndtCeckCdNm" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
      		<form:select path="useAt" title="사용여부 ">
	      		<form:option value="Y" label="사용" />
	      		<form:option value="N" label="미사용" />
      		</form:select>
    	</td>    
  	</tr>     
</table>

</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bndtCeckManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
* 초기화 으로 가기
******************************************************** */
function fn_aram_reset() {
	var varForm = document.getElementById("bndtCeckManageVO");
	varForm.bndtCeckSe[0].selected = true;
	varForm.bndtCeckCd.value       = "";
	varForm.bndtCeckCdNm.value     = "";
	varForm.useAt.value            = "Y";
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
 	var varForm = document.getElementById("bndtCeckManageVO");
 	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtCeckManage.do";
 	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("bndtCeckManageVO");
 
    if(!validateBndtCeckManageVO(varForm)){           
        return;
    }

	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/insertBndtCeckManage.do";
        varForm.submit();
    }
}

</script>

</body>
</html>