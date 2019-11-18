<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : MenuBndeRegist.jsp
  * @Description : 메뉴일괄 등록
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
<title>메뉴일괄 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴일괄등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:deleteMenuList(); return false;">일괄삭제</a></span>
		<span class="button"><a href="#" onclick="javascript:insertMenuManage(); return false;">일괄등록</a></span>
	</div>
</div>

<form:form modelAttribute="menuManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register">
	<tr>
 		<th width="20%">
    		<span class="required_icon"></span>
 			일괄파일
 		</th>
 		<td width="80%">
   		<table border="0" >
     		<tr>
       			<td>&nbsp;<input type = "file" name="file" size="40" title="일괄파일"/></td>
       			<td width="5%"></td>
     		</tr>
   		</table>
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

</div>

<script type="text/javascript">

/* ********************************************************
 * 메뉴일괄생성처리 함수
 ******************************************************** */
function insertMenuManage() {
    var varForm = document.getElementById("menuManageVO");
	if(confirm("메뉴일괄등록을 하시겠습니까?. \n 메뉴정보와  프로그램목록, 프로그램 변경내역 존재시 삭제 하실 수 없습니다.")){
	   if(checkFile()){
		   varForm.action ="${pageContext.request.contextPath}/sym/mnu/mpm/insertMenuBnde.do";
		   varForm.submit();
	   }
	}
}

/* ********************************************************
 * 메뉴일괄삭제처리 함수
 ******************************************************** */
function deleteMenuList() {
    var varForm = document.getElementById("menuManageVO");
	if(confirm("메뉴일괄삭제를 하시겠습니까?. \n 메뉴정보와  프로그램목록, 프로그램 변경내역 데이타 모두  삭제 삭제처리 됩니다.")){
		varForm.action ="${pageContext.request.contextPath}/sym/mnu/mpm/deleteMenuBnde.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 메뉴일괄등록시 등록파일 체크 함수
 ******************************************************** */
function checkFile(){
    var varForm = document.getElementById("menuManageVO");
	if(varForm.file.value==""){
	   alert("업로드 할 파일을 지정해 주세요");
	   return false;
	}

	var  str_dotlocation,str_ext,str_low;
	str_value  = varForm.file.value;
	str_low   = str_value.toLowerCase(str_value);
	str_dotlocation = str_low.lastIndexOf(".");
	str_ext   = str_low.substring(str_dotlocation+1);

	switch (str_ext) {
	  	case "xls" :
	  	case "xlsx" :
		 	return true;
	     	break;
	  	default:
	     	alert("파일 형식이 맞지 않습니다.\n xls,XLS,xlsx,XLSX 만\n 업로드가 가능합니다!");
	     	return false;
	}
}

</script>

</body>
</html>

