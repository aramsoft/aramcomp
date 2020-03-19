<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : MenuCreateDetail.jsp
  * @Description : 메뉴생성 상세조회
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
<title>메뉴생성 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴생성 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;">메뉴생성</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="menuCreateVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input name="checkedMenuNoForInsert" type="hidden">
<input name="checkedAuthorForInsert"  type="hidden">

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
			<input type="hidden" name="tmp_menuNmVal" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.chkYeoBu}|">
			</c:forEach>
    	</td>
  	</tr>
</table>

<table class="table-list" summary="메뉴일괄등록">
<caption>메뉴일괄등록 </caption>
  	<tr>
    	<td width='20'>&nbsp;</td>
    	<td>
    <!-- div class="tree" style="position:absolute; left:24px; top:70px; width:179px; height:25px; z-index:10;" -->
    		<div class="tree" style="width:700px;">

<script type="text/javascript">
var imgpath = "${pageContext.request.contextPath}/images/cmm/utl/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sym/mnu/mcm/MenuCreate.js"></script>

<script type="text/javaScript">
   	var varForm = document.getElementById("menuCreateVO");
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
        alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.");
        window.close();
    }
</script>

			</div>
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

</div>

<script type="text/javascript">

/* ********************************************************
 * 조회 함수
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("menuCreateVO");
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/listMenuCreate.do";
    varForm.submit();
}

/* ********************************************************
 * 멀티입력 처리 함수
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("menuCreateVO");
    var checkField = varForm.checkField;
    var checkMenuNos = "";
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length> 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkMenuNos += ((checkedCount==0? "" : ",") + checkField[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkMenuNos = checkField.value;
            }
        }
    }
    if(checkedCount == 0){
        alert("선택된 메뉴가 없습니다.");
        return false;
    }
    varForm.checkedMenuNoForInsert.value=checkMenuNos;
    varForm.checkedAuthorForInsert.value=varForm.authorCode.value;
    
	if (confirm("<spring:message code='common.regist.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/sym/mnu/mcm/insertMenuCreate.do";
    	varForm.submit();
	}	
}

</script>

</body>
</html>

