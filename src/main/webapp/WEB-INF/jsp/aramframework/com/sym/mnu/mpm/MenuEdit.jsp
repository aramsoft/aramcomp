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
  * @Class Name : MenuEdit.jsp
  * @Description : 메뉴 수정
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
  /* Image Path 설정 */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>메뉴 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>메뉴상세조회및 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="menuManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="메뉴상세정보">
<caption>메뉴상세정보 </caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="menuNo">메뉴No</label>
    	</th>
    	<td width="30%">
      		<c:out value="${menuManageVO.menuNo}"/>
      		<form:hidden path="menuNo" />
      		<form:errors path="menuNo" cssClass="error"/>
    	</td>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="menuOrdr">메뉴순서</label>
    	</th>
    	<td width="30%">
       		<form:input path="menuOrdr" size="10" maxlength="10" title="메뉴순서"/>
      		<form:errors path="menuOrdr" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="menuNm">메뉴명</label>
    	</th>
    	<td>
      		<form:input path="menuNm" size="30" maxlength="30" title="메뉴명"/>
      		<form:errors path="menuNm" cssClass="error"/>
    	</td>
    	<th>
    		<span class="required_icon"></span>
    		<label for="upperMenuId">상위메뉴No</label>
    	</th>
    	<td>
      		<form:input path="upperMenuId" size="10" maxlength="10" title="상위메뉴No"/>
      		<a href="#" target="_blank" title="새창으로" onClick="mvmnMenuList(); return false;"  style="selector-dummy:expression(this.hideFocus=false);">
      			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt='' width="15" height="15" />(메뉴선택 검색)
      		</a>
      		<form:errors path="upperMenuId" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="progrmFileNm">파일명</label>
    	</th>
    	<td colspan="3">
    		<form:input path="progrmFileNm" size="60" maxlength="60" onkeypress="press();" title="파일명"/>
        	<a href="#" target="_blank" title="새창으로 이동" onclick="searchFileNm(); return false;"  style="selector-dummy:expression(this.hideFocus=false);">
        		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt='' width="15" height="15" />(프로그램파일명 검색)
        	</a>
	    	<form:errors path="progrmFileNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="relateImageNm">관련이미지명</label>
     	</th>
    	<td>
      		<form:input path="relateImageNm" size="30" maxlength="30" title="관련이미지명"/>
      		<form:errors path="relateImageNm" cssClass="error"/>
    	</td>
    	<th>
    		<span class="required_icon"></span>
    		<label for="relateImagePath">관련이미지경로</label>
    	</th>
    	<td>
      		<form:input path="relateImagePath" size="30" maxlength="30" title="관련이미지경로"/>
      		<form:errors path="relateImagePath" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="menuDc">메뉴설명</label>
    	</th>
    	<td colspan="3">
      		<form:textarea path="menuDc" rows="14" cols="75" title="메뉴설명"/>
      		<form:errors path="menuDc" cssClass="error"/>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="menuManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("menuManageVO");
    varForm.action = "${pageContext.request.contextPath}/sym/mnu/mpm/listMenu.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리 함수
 ******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("menuManageVO");
	
	if(!validateMenuManageVO(varForm)){
		return;
	}

	if(!fn_validatorMenuList()){return;}

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action="${pageContext.request.contextPath}/sym/mnu/mpm/updateMenu.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 삭제처리함수
 ******************************************************** */
function fn_aram_delete() {
	var varForm = document.getElementById("menuManageVO");

	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action="${pageContext.request.contextPath}/sym/mnu/mpm/deleteMenu.do";
		varForm.submit();
	}
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
       for (var i = 0; i < str.length; i++) {
           if (str.charAt(i) < '0' || str.charAt(i)> '9') {
               flag=false;
           }
       }
   }
   return flag;
}

/* ********************************************************
 * 상위메뉴화면 호출 함수
 ******************************************************** */
function mvmnMenuList() {
	window.open("${pageContext.request.contextPath}/sym/mnu/mpm/moveMenuTree.do",'Pop_Mvmn','scrollbars=yes,width=600,height=600');
}

/* ********************************************************
 * 파일명 엔터key 목록조회  함수
 ******************************************************** */
function press() {
    if (event.keyCode==13) {
    	searchFileNm();    // 원래 검색 function 호출
    }
}

var gArguments = new Array();

/* ********************************************************
 * 파일목록조회  함수
 ******************************************************** */
function searchFileNm() {
	var varForm = document.getElementById("menuManageVO");
	gArguments["progrmFileNm"] = varForm.progrmFileNm;
	
	var url = "/sym/prm/listProgramPopup.do";

	window.open(url, "p_programInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

</body>
</html>

