<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TemplateEdit.jsp
  * @Description : 템플릿 수정
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
	<h2>템플릿 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="templateInfVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="tmplatId" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.tmplatNm" />
	    </th>
		<form:hidden path="tmplatNm" />
	    <td width="80%">
	      	<c:out value="${templateInfVO.tmplatNm}"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.tmplatSeCode" />
	    </th>
	    <td>
	   		<select name="tmplatSeCode" class="select" onchange="javascript:fn_aram_select_tmplatType(this); return false;" title="검색조건선택">
			   	<option selected value=''>--선택하세요--</option>
				<c:forEach var="result" items="${COM005_tmplatSe}" varStatus="status">
				<option value='<c:out value="${result.code}"/>' <c:if test="${templateInfVO.tmplatSeCode == result.code}">selected="selected"</c:if>><c:out value="${result.codeNm}"/></option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="tmplatCours"><spring:message code="cop.tmplatCours" /></label>
	    </th>
	    <td>
	      	<form:input path="tmplatCours" size="60" maxlength="60" title="템플릿경로입력" />
	      	<form:errors path="tmplatCours" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.useAt" />
	    </th>
	    <td>
	     	<label for="useAtY">Y</label> : <input type="radio" name="useAt" id="useAtY" class="radio2" value="Y" <c:if test="${templateInfVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
	     	<label for="useAtN">N</label> : <input type="radio" name="useAt" id="useAtN" class="radio2" value="N" <c:if test="${templateInfVO.useAt == 'N'}"> checked="checked"</c:if>>
	    </td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="templateInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

window.onload = fn_aram_select_tmplatType;

function fn_aram_select_tmplatType() {
    var varForm = document.getElementById("templateInfVO");
	if (varForm.tmplatSeCode.value == 'TMPT01') {
		document.getElementById('sometext').innerHTML = "게시판 템플릿은 CSS만 가능합니다.";
	} else if (varForm.tmplatSeCode.value == '') {
		document.getElementById('sometext').innerHTML = "";
	} else {
		document.getElementById('sometext').innerHTML = "템플릿은 JSP만 가능합니다.";
	}
}

function fn_aram_list() {
    var varForm = document.getElementById("templateInfVO");
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("templateInfVO");

    if (!validateTemplateInfVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/tpl/updateTemplate.do";
		varForm.submit();
	}
}

function fn_aram_delete() {
    var varForm = document.getElementById("templateInfVO");

    if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/tpl/deleteTemplate.do";
		varForm.submit();
	}
}

</script>
