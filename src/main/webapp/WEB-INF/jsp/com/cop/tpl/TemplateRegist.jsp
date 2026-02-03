<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TemplateRegist.jsp
  * @Description : 템플릿 등록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>템플릿 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="templateInfVO" action ="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuNm" value="${curMenuNm}" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="tmplatNm"><spring:message code="cop.tmplatNm" /></label>
	    </th>
	    <td width="80%">
	      	<form:input path="tmplatNm" size="60" maxlength="60" title="템플릿명" />
	      	<form:errors path="tmplatNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.tmplatSeCode" />
	    </th>
	    <td>
		   	<select name="tmplatSeCode" class="select" onchange="javascript:fn_aram_select_tmplatType(this); return false;" id="tmplatSeCode" title="템플릿구분">
				<option selected value=''>--선택하세요--</option>
				<c:forEach var="result" items="${COM005_tmplatSe}" varStatus="status">
				<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
			<form:errors path="tmplatSeCode" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="tmplatCours"><spring:message code="cop.tmplatCours" /></label>
	    </th>
	    <td>
	      	<form:input path="tmplatCours" size="60" maxlength="60" title="템플릿경로" />
	      	<form:errors path="tmplatCours" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<spring:message code="cop.useAt" />
	    </th>
	    <td>
	     	<label for="useAtY">Y</label> : <input type="radio" name="useAt" id="useAtY" class="radio2" value="Y"  checked>&nbsp;
	     	<label for="useAtN">N</label> : <input type="radio" name="useAt" id="useAtN" class="radio2" value="N">
	     	<form:errors path="useAt" cssClass="error"/>
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
<validator:javascript formName="templateInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_select_tmplatType(obj){
	if (obj.value == 'TMPT01') {
		document.getElementById('sometext').innerHTML = "게시판 템플릿은 CSS만 가능합니다.";
	} else if (obj.value == '') {
		document.getElementById('sometext').innerHTML = "";
	} else {
		document.getElementById('sometext').innerHTML = "템플릿은 JSP만 가능합니다.";
	}
}

function fn_aram_list(){
    var varForm = document.getElementById("templateInfVO");
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("templateInfVO");

    if (!validateTemplateInfVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/tpl/insertTemplate.do";
		varForm.submit();
	}
}

</script>
