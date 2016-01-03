<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : UnityLinkRegist.jsp
 * @Description : 통합링크관리 등록
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
	<h2>통합링크관리 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="unityLinkVO" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="unityLinkId">

<!--  등록  폼 영역  -->
<table class="table-register" summary="통합링크관리 입력을 제공한다..">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="unityLinkNm">통합링크명</label>
    	</th>
	    <td width="80%">
	      	<form:input path="unityLinkNm" size="73" cssClass="txInput" maxlength="255"/>
	      	<form:errors path="unityLinkNm" cssClass="error"/>
	    </td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="unityLinkSeCode">통합링크그룹</label>
    	</th>
    	<td>
	        <form:select path="unityLinkSeCode">
	            <form:option value="" label="선택"/>
	            <form:options items="${COM039_pollKind}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="unityLinkUrl">통합링크URL</label>
    	</th>
	    <td>
	     	<form:input path="unityLinkUrl" size="73" cssClass="txInput" maxlength="255"/>
	      	<form:errors path="unityLinkUrl" cssClass="error"/>
	    </td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="unityLinkDc">통합링크설명</label>
     	</th>
    	<td>
   			<form:textarea path="unityLinkDc" rows="75" cols="14" cssClass="txArea2"/>
			<form:errors path="unityLinkDc" cssClass="error"/>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="unityLinkVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "unityLinkDc";
_editor_lang = "kr";
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/html/htmlarea4.0/htmlarea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/HtmlEditor.js"></script>

<script type="text/javascript">

window.onload = function() {
	HTMLArea.onload = initEditor;
	HTMLArea.init(); 
};

/* ********************************************************
 * 목록화면
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("unityLinkVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ulm/listUnityLink.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("unityLinkVO");
	varForm.onsubmit();		// for ending of htmleditor
	
	if(!validateUnityLinkVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/ion/ulm/insertUnityLink.do";
		varForm.submit();
	}
}

</script>
