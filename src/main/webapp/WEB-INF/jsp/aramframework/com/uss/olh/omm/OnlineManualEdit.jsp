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
  * @Class Name : OnlineManualEdit.jsp
  * @Description : 온라인메뉴얼 수정
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
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>온라인메뉴얼 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!--  등록  폼 영역  -->
<form:form commandName="onlineManualVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="onlineMnlId" />

<table class="table-register" summary="온라인메뉴얼 입력을 제공한다..">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="onlineMnlNm">온라인메뉴얼명</label>
    	</th>
    	<td width="80%">
      		<form:input path="onlineMnlNm" size="73" cssClass="txInput" maxlength="255"/>
      		<form:errors path="onlineMnlNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="onlineMnlSeCode">온라인메뉴얼구분</label>
    	</th>
    	<td>
        	<form:select path="onlineMnlSeCode">
            	<form:option value="" label="선택"/>
            	<form:options items="${COM041_onlineMnlSe}" itemValue="code" itemLabel="codeNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="onlineMnlDfn">온라인메뉴얼정의</label>
    	</th>
    	<td>
        	<form:textarea path="onlineMnlDfn" rows="3" cols="20" cssClass="txArea"/>
    		<form:errors path="onlineMnlDfn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="onlineMnlDc">온라인 메뉴얼설명</label>
    	</th>
    	<td>
   			<form:textarea path="onlineMnlDc" rows="75" cols="14" cssClass="txArea2"/>
			<form:errors path="onlineMnlDc" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="onlineManualVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "onlineMnlDc";
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
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlineManualVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/omm/listOnlineManual.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("onlineManualVO");
	varForm.onsubmit();		// for ending of htmleditor
   
	if(!validateOnlineManualVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olh/omm/updateOnlineManual.do";
		varForm.submit();
	}
}

</script>
