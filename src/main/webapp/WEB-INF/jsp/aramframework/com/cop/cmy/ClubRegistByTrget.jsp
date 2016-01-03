<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : ClubRegistByTrget.jsp
  * @Description : 동호회 등록
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
	<h2>동호회 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="clubVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="cmmntyId" type="hidden" value="<c:out value='${clubVO.trgetId}'/>"/>

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="clbNm">동호회 명</label>
	    </th>
	    <td width="80%">
	      	<form:input path="clbNm" size="60" value=""  maxlength="60" style="width:100%" title="동호회이름입력" />
	      	<form:errors path="clbNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	동호회 소개
	    </th>
	    <td> 
	      	<form:textarea path="clbIntrcn" class="textarea"  cols="75" rows="4"  style="width:100%" title="동호회소개입력 " />
	       	<form:errors path="clbIntrcn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	템플릿 정보
	    </th>
	    <td>
	     	<form:input path="tmplatNm" size="20" maxlength="20" readonly="true" title="템플릿이름" />
	     	<form:hidden path="tmplatId" size="20" />
	     	<a href="#" onclick="javascript:fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search">
	     	</a>
	     	<form:errors path="tmplatId" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	동호회 운영자
	    </th>
	    <td>
	     	<input name="emplyrId" type="hidden" value="">
	     	<input name="emplyrNm" type="text" size="20" value=""  maxlength="20" readonly id="emplyrNm">
	     	<a href="#" onclick="javascript:fn_aram_get_clbAdmin('<c:out value="${clubVO.trgetId}"/>'); return false;" style="selector-dummy: expression(this.hideFocus=false);">
	     		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" align="middle" alt="search">
	     	</a>
	     	<form:errors path="emplyrId" cssClass="error"/>
	    </td>
	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="clubVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("clubVO");
    varForm.action = "${pageContext.request.contextPath}/cop/cmy/listClubByTrget.do";
    varForm.submit();
}

function fn_aram_insert() {
    var varForm = document.getElementById("clubVO");

    if (!validateClubVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/cmy/insertClubByTrget.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 템플릿 팝업창열기
******************************************************** */
function fn_aram_get_tmplat() {
    var varForm = document.getElementById("clubVO");
	gArguments["tmplatId"] = varForm.tmplatId;
	gArguments["tmplatNm"] = varForm.tmplatNm;
	
	var url = "/cop/tpl/listTemplatePopup.do?typeFlag=CLB";

	window.open(url, "p_tmplatInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_get_clbAdmin(clbId) {
    var varForm = document.getElementById("clubVO");
	gArguments["uniqId"] = varForm.emplyrId;
	gArguments["userNm"] = varForm.emplyrNm;

	var url = "/cop/com/listCmmntyUser.do?trgetId"+clbId+"&PopFlag=Y";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>


