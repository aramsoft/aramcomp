<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : AdministCodeRecptnRegist.jsp
  * @Description : 법정동코드수신 등록
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
	<h2>법정동코드수신 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 상단타이틀 -->
<form:form commandName="administCodeRecptnVO"  method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="insttId" type="hidden" value="AUTO"/>

<!-- 등록  폼 영역  -->
<table class="table-register">
  	<tr>
    	<th width="50%">
    		<span class="required_icon"></span>
    		파일내용취합...
    	</th>
    	<td width="50%">    
    		<textarea rows="10" cols="55" title="">${buf}</textarea>    
    	</td>
  	</tr>
  	<tr>
    	<th width="50%">
     		<span class="required_icon"></span>
    		${systemCmdFull_LIST}
    	</th>
    	<td>    
    		<textarea title="" rows="10" cols="55">${systemCmdFull_LIST_LOG}</textarea>
    	</td>
    </tr>	
  	<tr>
    	<th width="50%">
    		<span class="required_icon"></span>
    		${systemCmdFull_MSG}
    	</th>
    	<td>    
    		<textarea title="" rows="10" cols="55">${systemCmdFull_MSG_LOG}</textarea>    
    	</td>
  	</tr>
  	<tr>
    	<td colspan="2">    
    		<textarea title="" rows="10" cols="55"> </textarea>    
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="administCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("administCodeRecptnVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/acr/listAdministCodeRecptn.do";
    varForm.submit();
}

/* ********************************************************
 * 등록처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("administCodeRecptnVO");
    
	if(!validateAdministCodeVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
	    varForm.action = "${pageContext.request.contextPath}/sym/ccm/acr/insertAdministCodeRecptn.do";
		varForm.submit();
	}
}

</script>
