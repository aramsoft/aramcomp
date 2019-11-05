<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : MenuMvmn.jsp
  * @Description : 메뉴 이동
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
<title>메뉴 이동</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript">
var imgpath = "${pageContext.request.contextPath}/images/aramframework/com/cmm/utl/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/mnu/mpm/MenuList.js" /></script>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>상위메뉴선택</h2>
</div>

<form:form commandName="menuManageVO"  action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="req_RetrunPath" value="/sym/mnu/mpm/MenuMvmn">
<c:forEach var="result" items="${list_menulist}" varStatus="status">
	<input type="hidden" name="tmp_menuNmVal" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.progrmFileNm}|${result.menuNo}|${result.menuOrdr}|${result.menuNm}|${result.upperMenuId}|${result.menuDc}|${result.relateImagePath}|${result.relateImageNm}|${result.progrmFileNm}|">
</c:forEach>

<table class="table-list">
  	<tr>
    	<td>
 			<div class="tree" style="width:400px;">
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
			    createTree(Tree, true);
            }else{
                alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요");
                window.close();
            }
        </script>
			</div>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${menuManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${menuManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${menuManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${menuManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 상세내역조회 함수
 ******************************************************** */
function choiceNodes(nodeNum) {
	var nodeValues = treeNodes[nodeNum].split("|");
	var parentFrom = opener.document.getElementsByTagName('form');
	parentFrom[0].upperMenuId.value = nodeValues[4];
    window.close();
}

</script>

</body>
</html>

