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
  * @Class Name : MenuTreeList.jsp
  * @Description : 메뉴 트리 목록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>메뉴 트리 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴 트리 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	    	<span class="button"><a href="#" onclick="initlMenuList(); return false;">초기화</a></span>
	        <span class="button"><a href="#" onclick="insertMenuList(); return false;"><spring:message code="button.save" /></a></span>
	        <span class="button"><a href="#" onclick="updateMenuList(); return false;"><spring:message code="button.update" /></a></span>
	        <span class="button"><a href="#" onclick="deleteMenuList(); return false;"><spring:message code="button.delete" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="menuManageVO" action ="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuNm" value="${curMenuNm}" />

<input type="hidden" name="req_RetrunPath" value="/sym/mnu/mpm/MenuList">
<input type="hidden" name="tmp_CheckVal" value="">

<c:forEach var="result" items="${list_menulist}" varStatus="status">
	<input type="hidden" name="tmp_menuNmVal" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.menuNo}|${result.menuOrdr}|${result.menuNm}|${result.upperMenuId}|${result.menuDc}|${result.relateImagePath}|${result.relateImageNm}|${result.progrmFileNm}|">
</c:forEach>

<div class="tree" style="position:absolute; left:50px; top:100px; width:240px; height:500px; z-index:10;">

<script type="text/javascript">
var imgpath = "${pageContext.request.contextPath}/images/com/cmm/utl/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/mnu/mpm/MenuList.js"></script>

<script type="text/javaScript">
  	var varForm = document.getElementById("menuManageVO");
    var chk_Object = true;
    var chk_browse = "";
	if (eval(varForm.req_RetrunPath)=="[object]") chk_browse = "IE";
	if (eval(varForm.req_RetrunPath)=="[object NodeList]") chk_browse = "Fox";
	if (eval(varForm.req_RetrunPath)=="[object Collection]") chk_browse = "safai";

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
        alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.");
    }
</script>
</div>

<div style="width:70%;clear:both;float:right;">

<table class="table-register" summary="메뉴리스트정보">
<caption>메뉴리스트정보 </caption>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="menuNo">메뉴No</label>
    	</th>
    	<td width="70%">
      		&nbsp; <input name="menuNo" type="text" size="10" value=""  maxlength="10" title="메뉴No">
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="menuOrdr">메뉴순서</label>
    	</th>
    	<td width="70%">
      		&nbsp; <input name="menuOrdr" type="text" size="10" value=""  maxlength="10" title="메뉴순서">
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="menuNm">메뉴명</label>
    	</th>
    	<td width="70%">
      		&nbsp; <input name="menuNm" type="text" size="30" value=""  maxlength="30" title="메뉴명">
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="upperMenuId">상위메뉴No</label>
    	</th>
    	<td width="70%">&nbsp;
    		<input name="upperMenuId" type="text" size="10" value=""  maxlength="10" title="상위메뉴No">
       		<a href="#" title="새창으로" onClick="mvmnMenuList();return false;"  style="selector-dummy:expression(this.hideFocus=false);"><img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif"
        			alt='' width="15" height="15" />(메뉴선택 검색)</a>
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="progrmFileNm">파일명</label>
    	</th>
    	<td width="70%">
       		&nbsp;
       		<input name="progrmFileNm" type="text" size="30" value=""  maxlength="60" title="파일명">
       		<a href="#" title="새창으로" onClick="searchFileNm();return false;"  style="selector-dummy:expression(this.hideFocus=false);"><img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif"
        			alt='' width="15" height="15" />(프로그램파일명 검색)</a>
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="relateImageNm">관련이미지명</label>
    	</th>
    	<td width="70%">
      		&nbsp; <input name="relateImageNm" type="text" size="30" value=""  maxlength="30" title="관련이미지명">
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
    		<span class="required_icon"></span>
    		<label for="relateImagePath">관련이미지경로</label>
    	</th>
    	<td width="70%">
      		&nbsp; <input name="relateImagePath" type="text" size="30" value=""  maxlength="60" title="관련이미지경로">
    	</td>
  	</tr>
  	<tr>
    	<th width="30%"   scope="row">
			<span class="norequired_icon"></span>
    		<label for="menuDc">메뉴설명</label>
    	</th>
    	<td width="70%">
      		&nbsp; <textarea name="menuDc" class="textarea"  cols="45" rows="8"  style="width:350px;" title="메뉴설명"></textarea>
    	</td>
  	</tr>
</table>

</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${menuManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${menuManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${menuManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${menuManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

var gArguments = new Array();

/* ********************************************************
 * 파일검색 화면 호출 함수
 ******************************************************** */
function searchFileNm() {
    var varForm = document.getElementById("menuManageVO");
	gArguments["progrmFileNm"] = varForm.progrmFileNm;
	
	var url = "/sym/prm/listProgramPopup.do";

	window.open(url, "p_programInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 메뉴등록 처리 함수
 ******************************************************** */
function insertMenuList() {
    var varForm = document.getElementById("menuManageVO");
	if(!fn_validatorMenuList()){return;}
    if(varForm.tmp_CheckVal.value == "U"){alert("상세조회시는 수정혹은 삭제만 가능합니다."); return;}
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/insertMenuTree.do";
    varForm.submit();
}

/* ********************************************************
 * 메뉴수정 처리 함수
 ******************************************************** */
function updateMenuList() {
    var varForm = document.getElementById("menuManageVO");
    if(!fn_validatorMenuList()){return;}
    if(varForm.tmp_CheckVal.value != "U"){alert("상세조회시는 수정혹은 삭제만 가능합니다. 초기화 하신 후 등록하세요."); return;}
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/updateMenuTree.do";
    varForm.submit();
}

/* ********************************************************
 * 메뉴삭제 처리 함수
 ******************************************************** */
function deleteMenuList() {
    var varForm = document.getElementById("menuManageVO");
    if(!fn_validatorMenuList()){return;}
    if(varForm.tmp_CheckVal.value != "U"){alert("상세조회시는 수정혹은 삭제만 가능합니다."); return;}
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/deleteMenuTree.do";
    varForm.submit();
}

/* ********************************************************
 * 메뉴리스트 조회 함수
 ******************************************************** */
function selectMenuList() {
    var varForm = document.getElementById("menuManageVO");
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/listMenuTree.do";
    varForm.submit();
}

/* ********************************************************
 * 상위메뉴화면 호출 함수
 ******************************************************** */
function mvmnMenuList() {
	window.open("${pageContext.request.contextPath}/sym/mnu/mpm/moveMenuTree.do",'Pop_Mvmn','scrollbars=yes,width=600,height=600');
}

/* ********************************************************
 * 초기화 함수
 ******************************************************** */
function initlMenuList() {
    var varForm = document.getElementById("menuManageVO");
    varForm.menuNo.value="";
    varForm.menuOrdr.value="";
    varForm.menuNm.value="";
    varForm.upperMenuId.value="";
    varForm.menuDc.value="";
    varForm.relateImagePath.value="";
    varForm.relateImageNm.value="";
    varForm.progrmFileNm.value="";
    varForm.menuNo.readOnly=false;
    varForm.tmp_CheckVal.value = "";
}

/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
function choiceNodes(nodeNum) {
    var varForm = document.getElementById("menuManageVO");
	var nodeValues = treeNodes[nodeNum].split("|");
    varForm.menuNo.value = nodeValues[4];
    varForm.menuOrdr.value = nodeValues[5];
    varForm.menuNm.value = nodeValues[6];
    varForm.upperMenuId.value = nodeValues[7];
    varForm.menuDc.value = nodeValues[8];
    varForm.relateImagePath.value = nodeValues[9];
    varForm.relateImageNm.value = nodeValues[10];
    varForm.progrmFileNm.value = nodeValues[11];
    varForm.menuNo.readOnly=true;
    varForm.tmp_CheckVal.value = "U";
}

/* ********************************************************
 * 입력값 validator 함수
 ******************************************************** */
function fn_validatorMenuList() {
    var varForm = document.getElementById("menuManageVO");
	if(varForm.menuNo.value == ""){alert("메뉴번호는 Not Null 항목입니다."); return false;}
	if(!checkNumber(varForm.menuNo.value)){alert("메뉴번호는 숫자만 입력 가능합니다."); return false;}

	if(varForm.menuOrdr.value == ""){alert("메뉴순서는 Not Null 항목입니다."); return false;}
	if(!checkNumber(varForm.menuOrdr.value)){alert("메뉴순서는 숫자만 입력 가능합니다."); return false;}

	if(varForm.upperMenuId.value == ""){alert("상위메뉴번호는 Not Null 항목입니다."); return false;}
	if(!checkNumber(varForm.upperMenuId.value)){alert("상위메뉴번호는 숫자만 입력 가능합니다."); return false;}

	if(varForm.progrmFileNm.value == ""){alert("프로그램파일명은 Not Null 항목입니다."); return false;}
	if(varForm.menuNm.value == ""){alert("메뉴명은 Not Null 항목입니다."); return false;}

    return true;
}

/* ********************************************************
 * 필드값 Number 체크 함수
 ******************************************************** */
function checkNumber(str) {
    var flag=true;
    if (str.length> 0) {
        for (i = 0; i < str.length; i++) {
            if (str.charAt(i) < '0' || str.charAt(i)> '9') {
                flag=false;
            }
        }
    }
    return flag;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sym:%EB%A9%94%EB%89%B4%EA%B4%80%EB%A6%AC";	
	window.open(url, "도움말");
}

</script>

</body>
</html>

