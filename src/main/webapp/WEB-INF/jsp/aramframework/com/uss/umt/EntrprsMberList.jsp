<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EntrprsMberList.jsp
  * @Description : 기업회원 목록
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
	<h2>기업회원 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<!-- content start -->
<form:form commandName="entrprsManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="uniqId"/>
<input type="hidden" name="checkedIdForDel" />
<input type="hidden" name="returnUrl" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.search" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_deleteList(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
        <form:select path="sbscrbSttus" title="검색조건선택1">
            <form:option value="0" label="상태(전체)" />
            <form:option value="A" label="가입신청" />
            <form:option value="D" label="삭제" />
            <form:option value="P" label="승인" />
        </form:select>
        <form:select path="searchVO.searchCondition" title="검색조건선택2">
			<form:option value="" label="--선택하세요--" />
            <form:option value="ENTRPRS_MBER_ID" label="ID" />
            <form:option value="APPLCNT_NM" label="Name" />
        </form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>
<form:hidden path="searchVO.pageIndex" />
 
<table class="table-list">
<thead>
    <tr>
    	<th scope="col" width="7%" >No.</th>
        <th scope="col" width="5%" >
    		<input type="checkbox" name="checkAll" class="check2" onchange="javascript:fnCheckAll(); return false;" title="전체선택" />
        </th>
        <th scope="col" width="8%" >아이디</th>
        <th scope="col" width="15%">회사명</th>
        <th scope="col" width="8%" >신청자이름</th>
        <th scope="col"            >신청자이메일</th>
        <th scope="col" width="15%">전화번호</th>
        <th scope="col" width="15%">등록일</th>
        <th scope="col" width="20%">가입상태</th>
    </tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="9"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
  	<c:set var="startIndex" value="${(entrprsManageVO.pageIndex-1) * entrprsManageVO.recordPerPage}"/>
    <c:forEach var="result" items="${resultList}" varStatus="status">
    <tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${entrprsManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>
  
        <td class="lt_text3">
        	<input name="checkField" title="checkField <c:out value="${status.count}"/>" type="checkbox"/>
            <input name="checkId" type="hidden" value="<c:out value='${result.userTy}'/>:<c:out value='${result.uniqId}'/>" disabled />
        </td>
        <td class="lt_text3">
			<span class="link">
			<a href="#"  onclick="javascript:fn_aram_detail('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>'); return false;">
				<c:out value="${result.userId}"/>
			</a>
			</span>
		</td>
        <td class="lt_text3"><c:out value="${result.cmpnyNm}"/></td>
        <td class="lt_text3"><c:out value="${result.userNm}"/></td>
        <td class="lt_text3"><c:out value="${result.emailAdres}"/></td>
        <td class="lt_text3"><c:out value="${result.areaNo}"/>-<c:out value="${result.middleTelno}"/>-<c:out value="${result.endTelno}"/></td>
        <td class="lt_text3"><fmt:formatDate value="${result.sbscrbDe}" pattern="yyyy-MM-dd"/></td>
        <td class="lt_text3">
        <c:forEach var="mberSttus" items="${COM013_mberSttus}" varStatus="status">
            <c:if test="${result.sttus == mberSttus.code}"><c:out value="${mberSttus.codeNm}"/></c:if>
        </c:forEach>
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
    var varForm = document.getElementById("entrprsManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listEntrprsMber.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("entrprsManageVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listEntrprsMber.do";
    varForm.submit();
}

function fn_aram_detail(id) {
    var varForm = document.getElementById("entrprsManageVO");

    array = id.split(":");
    if(array[0] == "") {
    } else {
        userTy = array[0];
        userId = array[1];
    }
    varForm.uniqId.value = userId;
    varForm.action = "${pageContext.request.contextPath}/uss/umt/editEntrprsMber.do";
    varForm.submit();

}

function fn_aram_regist() {
    var varForm = document.getElementById("entrprsManageVO");
    varForm.uniqId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/umt/registEntrprsMber.do";
    varForm.submit();
}

function fn_aram_deleteList() {
    var varForm = document.getElementById("entrprsManageVO");
    var checkField = varForm.checkField;
    var id = varForm.checkId;
    var checkedIds = "";
    var checkedCount = 0;

    if(checkField) {
        if(checkField.length> 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
            }
        }
    }
    if(checkedIds.length> 0) {
    	//alert(checkedIds);
        if(confirm("<spring:message code="common.delete.msg" />")){
        	varForm.checkedIdForDel.value=checkedIds;
        	varForm.returnUrl.value="/uss/umt/listEntrprsMber.do";
        	varForm.action = "${pageContext.request.contextPath}/uss/umt/deleteIdsAll.do";
        	varForm.submit();
        }
    }
}

function fnCheckAll() {
    var varForm = document.getElementById("entrprsManageVO");
    var checkField = varForm.checkField;
    
    if(checkField.length> 1) {
        for(var i=0; i < checkField.length; i++) {
            checkField[i].checked = varForm.checkAll.checked;
        }
    } else {
        checkField.checked = varForm.checkAll.checked;
    }
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("기업회원관리");	
	window.open(url, "도움말");
}

</script>


