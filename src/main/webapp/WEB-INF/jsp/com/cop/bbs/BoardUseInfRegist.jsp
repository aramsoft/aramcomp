<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardUseInfRegist.jsp
  * @Description : 게시판  사용  등록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>게시판 사용 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	     	<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
	       	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="boardUseInfVO"  method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuNm" value="${curMenuNm}" />
<input type="hidden" name="fullScrYn" value="${fullScrYn}" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.bbsNm" />
	    </th>
	    <td>
	      	<form:hidden path="bbsId" />
	      	<input name="bbsNm" type="text" size="40" value=""  maxlength="40" title="게시판명" readonly />&nbsp;
	      	<a href="#" onclick="fn_aram_get_board(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	      		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="새창" />
	      	</a>
 			<form:errors path="bbsId" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.trgetNm" />
	    </th>
	    <td>
		   	<select name="trgetType" class="select" onChange="javascript:fn_aram_select_targetType(this); return false;">
			   <option selected value=''>--선택하세요--</option>
			   <c:if test="${useCommunity == 'true'}">
			   <option value="CMMNTY">커뮤니티</option>
			   </c:if>
			   <option value="SYSTEM">시스템</option>
		   	</select>
			<form:hidden path="trgetId" />
		   	<input type="text" name="trgetNm" value="" size="40" title="<spring:message code="cop.trgetNm" />" readOnly>
		   	<form:errors path="trgetId" cssClass="error"/>
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

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardUseInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardUseInfVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardUseInf.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("boardUseInfVO");
    
	if (!validateBoardUseInfVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertBoardUseInf.do";
		varForm.submit();
	}
}

function fn_aram_select_targetType(obj) {
    var varForm = document.getElementById("boardUseInfVO");

	var _strType = obj.value;
	if (_strType == 'CMMNTY') {
		fn_aram_get_cmmnty();
	} else {
		varForm.trgetId.value = "SYSTEM_DEFAULT_BOARD";
		varForm.trgetNm.value = "시스템 활용";
	}
}

var gArguments = new Array();

/* ********************************************************
* 게시판 팝업창열기
******************************************************** */
function fn_aram_get_board(){
    var varForm = document.getElementById("boardUseInfVO");
	gArguments["bbsId"] = varForm.bbsId;
	gArguments["bbsNm"] = varForm.bbsNm;
	
	var url = "/cop/bbs/listBoardMasterPopup.do";

	window.open(url, "p_boardInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_get_cmmnty(){
    var varForm = document.getElementById("boardUseInfVO");
	gArguments["trgetId"] = varForm.trgetId;
	gArguments["trgetNm"] = varForm.trgetNm;
	
	var url = "/cop/cmy/listCommunityPopup.do";

	window.open(url, "p_cmmntyInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

