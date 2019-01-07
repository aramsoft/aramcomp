<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /** 
  * @Class Name : IndndlpgeCntntsList.jsp
  * @Description : 마이페이지 컨텐츠 목록
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
<title>마이페이지 컨텐츠 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<c:set var="tabindex" value="7" />
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>마이페이지 컨텐츠</h2> 
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="indvdlPgeCntntsVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="cntntsId" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_preview(); return false;"><spring:message code="button.preview" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" class="select" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="CNTNTS_NM" label="컨텐츠명" />			   
	   		<form:option value="CNTNTS_USE_AT" label="사용상태" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<table class="table-list" summary="마이페이지에 추가하기 위한 컨텐츠  목록을 제공한다.">
<thead>
  	<tr>
     	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="5%" >
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택" />
    	</th>  
    	<th scope="col" width="20%">컨텐츠 명</th>
    	<th scope="col"            >연계 URL</th>
    	<th scope="col" width="20%">사용 상태 </th>
  	</tr>
</thead>
<tbody>  
 	<c:if test="${fn:length(resultList) == 0}">
   	<tr>
		<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
   	</tr>         
 	</c:if>
 	
 	<c:set var="searchVO" value="${indvdlPgeCntntsVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
    		<input name="delYn" type="checkbox" class="check2" value="<c:out value="${result.cntntsId}"/>" title="<c:out value="${cntntslist.cntntsNm}"/>">
    		<input type="hidden" name="checkedCntntsID" value="<c:out value='${result.cntntsId}'/>">
    		<input type="hidden" name="checkedCntntsNm" value="<c:out value="${result.cntntsNm}"/>">
    		<input type="hidden" name="checkedCntcUrl" value="<c:out value="${result.cntcUrl}"/>">
    	</td>
    	<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('<c:out value='${result.cntntsId}'/>'); return false;">
				<c:out value="${result.cntntsNm}"/>
			</a>
			</span>
    	</td>
    	<td class="lt_text3"><c:out value="${result.cntcUrl}"/></td>
    	<td class="lt_text3"><c:out value="${result.cntntsUseAt}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.pageIndex.value=pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/listIndvdlpgeCntnts.do";
    varForm.submit();
}

/* ********************************************************
* 특정 키워드 검색
******************************************************** */
function fn_aram_search(){
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/listIndvdlpgeCntnts.do";
    varForm.submit();
}

/* ********************************************************
* 상세보기 화면 이동
******************************************************** */
function fn_aram_detail(cntntsId) {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.cntntsId.value = cntntsId;
    varForm.action = "${pageContext.request.contextPath}/uss/mpe/detailIndvdlpgeCntnts.do";
    varForm.submit();     
}

/* ********************************************************
* 새 컨텐츠 등록
******************************************************** */
function fn_aram_regist() {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    varForm.cntntsId.value = "";
   	varForm.action = "${pageContext.request.contextPath}/uss/mpe/registIndvdlpgeCntnts.do";
    varForm.submit();
}

/* ********************************************************
* 목록에서 체크된 컨텐츠 삭제(사용 유무 N으로 변경)
******************************************************** */
function fn_aram_deleteList() {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            varForm.action = "${pageContext.request.contextPath}/uss/mpe/deleteIndvdlpgeCntnts.do";
            varForm.submit();
        }
    }
}

/* ********************************************************
* 컨텐츠 미리보기 시 목록에서 체크박스 체크 확인
******************************************************** */
function fn_aram_preview(){
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    var checkField = varForm.delYn;
    var checkCount = 0;
    var checkedItem = 0;
    var cntcUrl = "";
    var cntntsNm = "";
    
    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkCount++;
                    checkedItem = i;                   
                }
            }
        }else{
            if(checkField.checked){
            	checkCount = 1;
            	checkedItem = 0;
            }
        }
    }    
    if(checkCount == 0) {
        alert("미리보기 할 하나의 컨텐츠를 선택해 주세요.");
    }else if(checkCount> 1){
        alert("미리보기 할 컨텐츠를 하나만 선택해 주세요.");
    }else{
        if(checkField.length> 1){
            cntcUrl = varForm.checkedCntcUrl[checkedItem].value;
            cntntsNm = varForm.checkedCntntsNm[checkedItem].value;         
        }else{
            cntcUrl = varForm.checkedCntcUrl.value;
            cntntsNm = varForm.checkedCntntsNm.value;
        }
    	var url = "/uss/mpe/previewIndvdlpgeCntnts.do?cntcUrl="+cntcUrl;
    	window.open(url, "p_preview", "width=850px,height=420px,top=100px,left=100px,location=no");
    }
}

/* ********************************************************
 * 목록에서 체크박스 전체 선택, 선택 해제 기능
 ******************************************************** */
 function fnCheckAll() {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    var checkField = varForm.delYn;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = varForm.checkAll.checked;
        }
    } else {
        checkField.checked = varForm.checkAll.checked;
    }
}

 /* ********************************************************
  * 컨텐츠 삭제 시 목록에서 체크박스 체크 확인
  ******************************************************** */
function fncManageChecked() {
	var varForm = document.getElementById("indvdlPgeCntntsVO");
    var checkField = varForm.delYn;
    var checkId = varForm.checkedCntntsID;
    var returnValue = "";
    var returnBoolean = false;
    var checkedCount = 0;

    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	returnValue += ((checkedCount==0? "" : ";") + checkId[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                returnValue = checkId.value;
            }
        }
    } 

    if(returnValue.length> 0) {
        varForm.cntntsId.value = returnValue;
        returnBoolean = true;
    } else {
        alert("선택된  컨텐츠가  없습니다.");
        returnBoolean = false;
    }

    return returnBoolean;
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("마이페이지");	
	window.open(url, "도움말");
}

</script>

</body>
</html>
