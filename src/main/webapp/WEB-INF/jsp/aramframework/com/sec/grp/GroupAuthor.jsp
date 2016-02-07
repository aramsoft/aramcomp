<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : GroupAuthor.java
  * @Description : 그룹 권한 관리
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
<DIV id="main">

<div class="content_title">
	<h2>그룹 권한 관리</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="groupAuthorVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="userIds"/>
<input type="hidden" name="authorCodes"/>
<input type="hidden" name="regYns"/>
<input type="hidden" name="mberTyCodes"/>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insertList(); return false;"><spring:message code="button.create" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
	</div>
	<div class="keyword_area">
 		조회조건 : 
 		<form:select path="searchCondition" class="select" onchange="onSearchCondition()" title="조회조건선택">
			<form:option value="" label="--선택하세요--" />
       		<form:option value="USER_ID" label="사용자 ID" />
            <form:option value="USER_NM" label="사용자 명" />
            <form:option value="GROUP_ID" label="그룹" />
        </form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
        <span class="button"><a href="#" onclick="javascript:fn_aram_get_group(); return false;">그룹조회 팝업</a></span>
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search(); return false;" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />

<table class="table-list" summary="그룹내 권한을 관리하는 테이블입니다.사용자 ID,사용자 명,사용자 유형,권한,등록 여부의 정보를 담고 있습니다.">
<thead>
  	<tr>
		<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="5%" >
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택" />
    	</th>
    	<th scope="col" width="15%">사용자 ID</th>
    	<th scope="col" width="15%">사용자 명</th>
    	<th scope="col"            >사용자 유형</th>
    	<th scope="col" width="20%">권한</th>
    	<th scope="col" width="10%">등록 여부</th>
  	</tr>
</thead>
<tbody>
  	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(groupAuthorVO.pageIndex-1) * groupAuthorVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${groupAuthorVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
    		<input type="checkbox" name="delYn" class="check2" title="선택">
    		<input type="hidden" name="checkId" value="<c:out value="${result.uniqId}"/>" disabled />
    	</td>

    	<td class="lt_text3"><c:out value="${result.userId}"/></td>
    	<td class="lt_text3"><c:out value="${result.userNm}"/></td>
    	<td class="lt_text3">
    		<c:out value="${result.mberTyNm}"/>
    		<input type="hidden" name="mberTyCode" value="${result.mberTyCode}" disabled />
    	</td>
    	<td class="lt_text3">
    		<select name="authorManageCombo" title="권한">
	    		<c:forEach var="author" items="${authorList}" varStatus="status">
	            <option value="<c:out value="${author.authorCode}"/>" <c:if test="${author.authorCode == result.authorCode}">selected</c:if>><c:out value="${author.authorNm}"/></option>
	        	</c:forEach>
	    	</select>
	    </td>
    	<td class="lt_text3">
    		<c:out value="${result.regYn}"/>
    		<input type="hidden" name="regYn" value="<c:out value="${result.regYn}"/>">
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript" defer="defer">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("groupAuthorVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sec/grp/listGroupAuthor.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("groupAuthorVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/sec/grp/listGroupAuthor.do";
    varForm.submit();
}

var gArguments = new Array();

/* ********************************************************
 * 그룹  팝업창열기
 ******************************************************** */
function fn_aram_get_group() {
    var varForm = document.getElementById("groupAuthorVO");
    if(varForm.searchCondition.value != 'GROUP_ID') {
        alert("그룹을 선택하세요.");
        return;
    }
    
	gArguments["groupId"] = varForm.searchKeyword;
	var url = "/sec/grp/listGroupPopup.do";

	window.open(url, "p_deptInqire", "width=500px,height=485px,top=100px,left=100px,location=no");
}

function onSearchCondition() {
    var varForm = document.getElementById("groupAuthorVO");
    varForm.searchKeyword.value = "";
	if(varForm.searchCondition.value == 'GROUP_ID') {
		varForm.searchKeyword.readOnly = true;
	} else {
		varForm.searchKeyword.readOnly = false;
	}
}

function fn_aram_insertList() {
    var varForm = document.getElementById("groupAuthorVO");
	if(!fncManageChecked()) return;

    if(confirm("등록하시겠습니까?")) {
    	varForm.action = "${pageContext.request.contextPath}/sec/grp/insertGroupAuthor.do";
    	varForm.submit();
    }
}

function fn_aram_deleteList() {
    var varForm = document.getElementById("groupAuthorVO");
	if(!fncManageChecked()) return;

    if(confirm("삭제하시겠습니까?")) {
    	varForm.action = "${pageContext.request.contextPath}/sec/grp/deleteGroupAuthor.do";
    	varForm.submit();
    }
}

function fnCheckAll() {
    var varForm = document.getElementById("groupAuthorVO");
    var checkField = varForm.delYn;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = varForm.checkAll.checked;
        }
    } else {
        checkField.checked = varForm.checkAll.checked;
    }
}

function fncManageChecked() {
    var varForm = document.getElementById("groupAuthorVO");
	var returnBoolean = false;

    var checkField = varForm.delYn;
    var checkId = varForm.checkId;
    var selectAuthor = varForm.authorManageCombo;
    var booleanRegYn = varForm.regYn;
    var listMberTyCode = varForm.mberTyCode;

    var returnId = "";
    var returnAuthor = "";
    var returnRegYn = "";
    var returnmberTyCode = "";

    var checkedCount = 0;

    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	returnId += ((checkedCount==0? "" : ";") + checkId[i].value);
                	returnAuthor += ((checkedCount==0? "" : ";") + selectAuthor[i].value);
                	returnRegYn += ((checkedCount==0? "" : ";") + booleanRegYn[i].value);
                	returnmberTyCode += ((checkedCount==0? "" : ";") + listMberTyCode[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                returnId = checkId.value;
                returnAuthor = selectAuthor.value;
                returnRegYn = booleanRegYn.value;
                returnmberTyCode = listMberTyCode.value;
            }
        }
    } 
    
    if(returnId.length> 0) {
        varForm.userIds.value = returnId;
        varForm.authorCodes.value = returnAuthor;
        varForm.regYns.value = returnRegYn;
        varForm.mberTyCodes.value = returnmberTyCode;
        returnBoolean = true;
    } else {
        alert("선택 항목이 없습니다.");
        returnBoolean = false;
    }
    
    return returnBoolean;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:sec:"+ + encodeURIComponent("권한그룹관리");	
	window.open(url, "도움말");
}

</script>
