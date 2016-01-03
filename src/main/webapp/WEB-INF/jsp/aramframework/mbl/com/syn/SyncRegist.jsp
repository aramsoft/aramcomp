<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /** 
  * @Class Name : SyncRegist.java
  * @Description : 동기화 서비스 등록
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
	<h2>동기화 서비스 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="syncVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sn" />

<!-- 등록  폼 영역  -->
<table class="table-register"  summary="동기화목록저장테이블.">
  	<tr> 
	    <th width="20%">
		    <label id="syncSj" for="syncSj">제목</label>
			<span class="required_icon"></span>
	    </th>
	    <td width="80%">
	        <form:input path="syncSj" size="71" maxlength="71" />
	        <form:errors path="syncSj" cssClass="error"/>                               
	    </td>
  	</tr>
    <tr> 
	    <th>
		    <label id="syncCn" for="syncCn">내용</label>
			<span class="required_icon"></span>
	    </th>
	    <td>
	        <form:textarea path="syncCn" rows="15" cols="70" maxlength="70" />
	        <form:errors path="syncCn" cssClass="error"/>                             
	    </td>
	</tr>    
</table>
	
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="syncVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" >

/* ********************************************************
 * 초기화
 ******************************************************** */
 window.onload = function(){

    // 첫 입력란에 포커스..
    //document.syncForm.syncSj.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("syncVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/listSync.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("syncVO");
    
	if (!validateSyncVO(varForm)) {
		return;				
	} 
	
	if (confirm("<spring:message code='common.regist.msg' />")) {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/syn/insertSync.do";
		varForm.submit();
	}
}

</script>
