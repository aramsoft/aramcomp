<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : UserList.jsp
  * @Description : 사용자 목록
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
	<h2>사용자 목록</h2>
</div>

<form:form commandName="userInfVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="targetMethod" value="${targetMethod}" />
<input type="hidden" name="trgetId" value="${curTrgetId}" />

<input type="hidden" name="param_emplyrId" />
<input type="hidden" name="param_cmmntyId" />
<input type="hidden" name="param_clbId" />

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
   		<form:select path="searchCondition" class="select" title="검색조건선택">
		   	<form:option value="USER_NM" label="사용자명" />
	   	</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색단어입력" />
		<form:select path="recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="번호, 사용자아이디 , 사용자명, 주소, 이메일, 비고   목록입니다">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="15%">사용자아이디</th>
	    <th scope="col" width="15%">사용자명</th>
	    <th scope="col"            >주소</th>
	    <th scope="col" width="20%">이메일</th>
	    <th scope="col" width="20%">비고</th>
	</tr>
</thead>
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
	    <td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<c:set var="startIndex" value="${(userInfVO.pageIndex-1) * userInfVO.recordPerPage}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${userInfVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3"  <c:if test="${result.oprtrAt == 'Y'}">style="background-color:#e4eaf8;"</c:if>>
	    	<c:out value="${result.userId}" />
	    </td>
	    <td class="lt_text3"  <c:if test="${result.oprtrAt == 'Y'}">style="background-color:#e4eaf8;"</c:if>>
	    	<c:out value="${result.userNm}" />
	    </td>
	    <td class="lt_text3"><c:out value="${result.userAdres}" /></td>
	    <td class="lt_text3"><c:out value="${result.userEmail}" /></td>
	    <td class="lt_text3">
	    	<c:choose>
	    	<c:when test="${result.useAt == 'Y'}">
	    		<input type="button" value="탈퇴"  onClick="javascript:fn_aram_delete_user('<c:out value="${result.uniqId}"/>'); return false;" />
				|
		    	<c:choose>
		    	<c:when test="${result.oprtrAt == 'Y'}">
		    		<input type="button" value="운영진탈퇴" onClick="javascript:fn_aram_delete_manager('<c:out value="${result.uniqId}"/>'); return false;" />
		    	</c:when>
		    	<c:otherwise>
		    		<input type="button" value="운영진등록" onClick="javascript:fn_aram_regist_manager('<c:out value="${result.uniqId}"/>'); return false;" />
		    	</c:otherwise>
		    	</c:choose>
		    </c:when>
	    	<c:otherwise>
	    		<input type="button" value="재가입" onClick="javascript:fn_aram_reRegist_user('<c:out value="${result.uniqId}"/>'); return false;" />
				|
	    		<input type="button" value="삭제" onClick="javascript:fn_aram_erase_user('<c:out value="${result.uniqId}"/>'); return false;" />
	    	</c:otherwise>
	    	</c:choose>
	    </td>
	</tr>
	</c:forEach>
</tbody>
</table>

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

function fn_aram_linkPage(pageIndex) {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	varForm.pageIndex.value = pageIndex;
	if (_target == 'selectCmmntyMngrList') {
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyMngr.do";
	} else if (_target == 'selectCmmntyUserList') {
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyUser.do";
	} else {
		varForm.action = "${pageContext.request.contextPath}/cop/com/listUser.do";
	}
	varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	varForm.pageIndex.value = '1';
	if (_target == 'selectCmmntyMngrList') {
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyMngr.do";
	} else if (_target == 'selectCmmntyUserList') {
		varForm.action = "${pageContext.request.contextPath}/cop/com/listCmmntyUser.do";
	} else {
		varForm.action = "${pageContext.request.contextPath}/cop/com/listUser.do";
	}
	varForm.submit();
}

function fn_aram_delete_user(emplyrId) {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	if (confirm('<spring:message code="cop.withdraw.msg" />')) {
		varForm.param_emplyrId.value = emplyrId;
		if (_target == 'selectCmmntyUserList') {
			varForm.param_cmmntyId.value = varForm.trgetId.value;
			varForm.action = "${pageContext.request.contextPath}/cop/com/deleteCmmntyUser.do";
		} 
		varForm.submit();
	}
}

function fn_aram_erase_user(emplyrId) {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	if (confirm('<spring:message code="cop.delete.msg" />')) {
		varForm.param_emplyrId.value = emplyrId;
		if (_target == 'selectCmmntyUserList') {
			varForm.param_cmmntyId.value = varForm.trgetId.value;
			varForm.action = "${pageContext.request.contextPath}/cop/com/eraseCmmntyUser.do";
		} 
		varForm.submit();
	}
}

function fn_aram_reRegist_user(emplyrId){
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	if (confirm('<spring:message code="cop.reregist.msg" />')) {
		varForm.param_emplyrId.value = emplyrId;
		if (_target == 'selectCmmntyUserList') {
			varForm.param_cmmntyId.value = varForm.trgetId.value;
			varForm.action = "${pageContext.request.contextPath}/cop/com/reRegistCmmntyUser.do";
		} 
		varForm.submit();
	}
}

function fn_aram_regist_manager(emplyrId) {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	if (confirm('<spring:message code="cop.registmanager.msg" />')) {
		varForm.param_emplyrId.value = emplyrId;
		if (_target == 'selectCmmntyUserList') {
			varForm.param_cmmntyId.value = varForm.trgetId.value;
			varForm.action = "${pageContext.request.contextPath}/cop/com/registCmmntyManager.do";
		} 
		varForm.submit();
	}
}

function fn_aram_delete_manager(emplyrId) {
    var varForm = document.getElementById("userInfVO");
	var _target = varForm.targetMethod.value;

	if (confirm('<spring:message code="cop.deletemanager.msg" />')) {
		varForm.param_emplyrId.value = emplyrId;
		if (_target == 'selectCmmntyUserList') {
			varForm.param_cmmntyId.value = varForm.trgetId.value;
			varForm.action = "${pageContext.request.contextPath}/cop/com/deleteCmmntyManager.do";
		} 
		varForm.submit();
	}
}

</script>

