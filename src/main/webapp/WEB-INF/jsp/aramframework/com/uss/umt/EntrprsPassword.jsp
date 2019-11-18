<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EntrprsPassword.jsp
  * @Description : 기업회원 암호변경
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
	<h2>기업회원 암호변경</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.update" /></a></span>
		<c:if test="${isAdmin=='true'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_reset(); return false;"><spring:message code="button.reset" /></a></span>
	</div>
</div>

<form:form modelAttribute="entrprsManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="sbscrbSttus" />

<table class="table-register">
    <tr>
        <th width="20%">
			<span class="required_icon"></span>
			기업회원아이디
        </th>
        <td width="80%">
            <form:input path="entrprsmberId" size="20" maxlength="20" readonly="true" />
            <form:hidden path="uniqId" />
            <form:hidden path="userTy"  />
        </td>
    </tr>
    <tr> 
        <th>
			<span class="required_icon"></span>
			<label for="oldPassword">기존 비밀번호</label>
        </th>
        <td>
        	<input name="oldPassword" id="oldPassword" type="password" size="20" value="" maxlength="100" />
        </td>
    </tr>
    <tr> 
        <th>
  			<span class="required_icon"></span>
 			<label for="newPassword">비밀번호</label>
        </th>
        <td>
        	<input name="newPassword" id="newPassword" type="password" size="20" value=""  maxlength="100" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="newPassword2">비밀번호확인</label>
        </th>
        <td>
        	<input name="newPassword2" id="newPassword2" type="password" size="20" value=""  maxlength="100" />
        </td>
    </tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${entrprsManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${entrprsManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${entrprsManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${entrprsManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("entrprsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listEntrprsMber.do";
    varForm.submit();
}

function fn_aram_update(){
    var varForm = document.getElementById("entrprsManageVO");
    if(validatePasswordChgVO(varForm)){
    	if(varForm.newPassword.value != varForm.newPassword2.value){
            alert("<spring:message code="fail.user.passwordUpdate2" />");
            return;
        }
        varForm.action = "${pageContext.request.contextPath}/uss/umt/updateEntrprsMberPassword.do";
    	varForm.submit();
    }
}

function fn_aram_reset(){
    var varForm = document.getElementById("entrprsManageVO");
    varForm.reset();
}

</script>


