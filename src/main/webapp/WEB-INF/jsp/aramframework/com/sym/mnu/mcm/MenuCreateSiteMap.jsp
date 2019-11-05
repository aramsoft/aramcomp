<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="aramframework.com.cmm.constant.Globals"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MenuCreateSiteMap.jsp
  * @Description : 메뉴사이트맵 생성
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
<title>메뉴사이트맵 생성</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴사이트맵생성</h2>
</div>

<form:form commandName="menuSiteMapVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="creatPersonId"  />
<input name="valueHtml"      type="hidden" />
<input name="bndeFileNm"     type="hidden" />
<input name="bndeFilePath"   type="hidden" />
<input name="mapCreatId"     type="hidden" />
<input name="tmp_rootPath"   type="hidden" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;">사이트맵생성</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<table class="table-list">
  	<tr>
       	<th width="15%">
       		<label for="authorCode">권한코드&nbsp;</label>
       	</th>
       	<td width="40%">
       		<form:input path="authorCode" size="30"  maxlength="30" title="권한코드" readonly="true" />
       	</td>
    	<td>
			<c:forEach var="result" items="${list_menulist}" varStatus="status">
			<input type="hidden" name="tmp_menuNmVal" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.menuOrdr}|${result.chkURL}|">
			</c:forEach>
    	</td>
  	</tr>
</table>

<table class="table-list" summary="메뉴사이트맵생성 메뉴목록">
<caption>메뉴사이트맵생성 </caption>
  	<tr>
    	<td>
    		<div class="tree" style="width:480px;">

<script type="text/javascript">
var imgpath = "${pageContext.request.contextPath}/images/aramframework/com/cmm/utl/";
var getContextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/mnu/mcm/MenuCreateSiteMap.js"></script>

<script type="text/javaScript">
  	var varForm = document.getElementById("menuSiteMapVO");
    var chk_Object = true;
    var chk_browse = "";
	if (eval(varForm.authorCode)=="[object]") chk_browse = "IE";
	if (eval(varForm.authorCode)=="[object NodeList]") chk_browse = "Fox";
	if (eval(varForm.authorCode)=="[object Collection]") chk_browse = "safai";

	var Tree = new Array;
	if(chk_browse=="IE"&&eval(varForm.tmp_menuNmVal)!="[object]"){
	   	alert("메뉴 목록 데이타가 존재하지 않습니다.");
	   	chk_Object = false;
	}
	if(chk_browse=="Fox"&&eval(varForm.tmp_menuNmVal)!="[object NodeList]"){
	   	alert("메뉴 목록 데이타가 존재하지 않습니다.");
	   	chk_Object = false;
	}
	if(chk_browse=="safai"&&eval(varForm.tmp_menuNmVal)!="[object Collection]"){
		alert("메뉴 목록 데이타가 존재하지 않습니다.");
		chk_Object = false;
	}
	if( chk_Object ){
		for (var j = 0; j < varForm.tmp_menuNmVal.length; j++) {
			Tree[j] = varForm.tmp_menuNmVal[j].value;
	    }
	    createTree(Tree);
    }else{
        alert("사이트맵 생성 데이타가 존재하지 않습니다. \n 메뉴를 생성하신 후 작업하세요.");
        window.close();
    }
</script>
			</div>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${menuSiteMapVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${menuSiteMapVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${menuSiteMapVO.pageIndex}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript">

/*절대 path 사이트맵이 저장될 장소의  절대 패스*/
//var vRootPath    = "C:/aramframework/workspace2/sym.mnu.mcm/src/main/webapp";   // Window webapp 위치
//var vRootPath    = "/product/jeus/webhome/was_com/aramframework-com-1_0/aramframework-com-1_0_war___"; // Unix webapp 위치
/* 절대 path내  사이트맵 jsp를 저장할 장소 지정 */
//var vSiteMapPath = "/html/aramframework/com/sym/mnu/mcm/";

/* ********************************************************
 * 조회 함수
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("menuSiteMapVO");
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/listMenuCreate.do";
    varForm.submit();
}

/* ********************************************************
 * jsp 생성 함수
 ******************************************************** */
function fn_aram_insert() {
	fHtmlCreat_Head();
    var varForm = document.getElementById("menuSiteMapVO");
	usrID = varForm.creatPersonId.value;
	authorCode = varForm.authorCode.value;
	varForm.valueHtml.value    = vHtmlCode;
	varForm.bndeFileNm.value   = authorCode+"_SiteMap.jsp";
	//varForm.tmp_rootPath.value = vRootPath;
	//varForm.bndeFilePath.value = vSiteMapPath;
	varForm.mapCreatId.value   = authorCode;
	varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/insertMenuCreateSiteMap.do";
	varForm.submit();
}

</script>

</body>
</html>


