<!doctype html>
<%@ page language="java" contentType ="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BkmkMenuRegist.jsp
  * @Description : 바로가기메뉴 등록
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
<title>바로가기메뉴 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>바로가기메뉴등록</h2> 
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bkmkMenuManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="menuId" />

<table class="table-register">
  	<tr>
    	<th width="20%"> 
    		<span class="required_icon"></span>
    		메뉴명
    	</th>
    	<td width="80%">
      		<form:input path="menuNm" size="30" maxlength="60" style="width: 237px" readonly="true" class="readOnlyClass" title="메뉴명입력" />
      		<a href="javascript:fn_aram_detail_menu();" style="selector-dummy: expression(this.hideFocus=false);">
      			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" style="vertical-align: middle" alt="메뉴조회아이콘">
     		</a> 메뉴선택
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		메뉴 URL
    	</th>
    	<td>
      		<form:input path="progrmStrePath" size="30" maxlength="90" style="width: 235px" readonly="true" class="readOnlyClass" title="메뉴URL입력" />
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${bkmkMenuManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${bkmkMenuManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${bkmkMenuManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${bkmkMenuManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bkmkMenuManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("bkmkMenuManageVO");
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/listBkmkMenu.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("bkmkMenuManageVO");

    if (!validateBkmkMenuManageVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/insertBkmkMenu.do";
		varForm.submit();
	}
}

var gArguments = new Array();

function fn_aram_detail_menu(){
	gArguments["retFunc"] = fn_aram_regist_bookmark;
	
	var url = "/sym/mnu/bmm/listBkmkMenuPopup.do";

	window.open(url, "p_bookmark", "width=850px,height=420px,top=100px,left=100px,location=no");
}

function fn_aram_regist_bookmark(menuId, menuNm){
	var varForm = document.getElementById("bkmkMenuManageVO");

	varForm.menuId.value = menuId;
	varForm.menuNm.value = menuNm;

	varForm.action = "${pageContext.request.contextPath}/sym/mnu/bmm/registBkmkMenu.do";
	varForm.submit();
}

</script>

</body>
</html>
