<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : UserPassword.jsp
  * @Description : 업무사용자 암호변경
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
	<h2>업무사용자 암호변경</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.update" /></a></span>
			<c:if test="${isAdmin=='true'}">
				<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
			</c:if>
			<span class="button"><a href="#" onclick="javascript:fn_aram_reset(); return false;"><spring:message code="button.reset" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="emplyrManageVO" action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuNm" value="${curMenuNm}" />

<form:hidden path="sbscrbSttus" />

<table class="table-register">
    <tr>
        <th width="20%">
			<span class="required_icon"></span>
			사용자아이디
        </th>
        <td width="80%">
            <form:input path="emplyrId" title="사용자아이디" size="20" maxlength="20" readonly="true" />
            <form:hidden path="userTy" />
        </td>
    </tr>
    <tr> 
        <th>
			<span class="required_icon"></span>
			<label for="oldPassword">기존 비밀번호</label>
        </th>
        <td>
        	<input name="oldPassword" id="oldPassword" title="기존 비밀번호" type="password" size="20" value=""  maxlength="100" />
        </td>
    </tr>
    <tr> 
        <th>
			<span class="required_icon"></span>
			<label for="newPassword">비밀번호</label>
        </th>
        <td>
        	<input name="newPassword" id="newPassword" title="비밀번호" type="password" size="20" value=""  maxlength="100" />
        </td>
    </tr>
    <tr>
        <th>
			<span class="required_icon"></span>
			<label for="newPassword2">비밀번호확인</label>
        </th>
        <td>
        	<input name="newPassword2" id="newPassword2" title="비밀번호확인" type="password" size="20" value=""  maxlength="100" />
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
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("emplyrManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/umt/listEmplyr.do";
    varForm.submit();
}

function fn_aram_update(){
    var varForm = document.getElementById("emplyrManageVO");
    if(validatePasswordChgVO(varForm)){
        if(varForm.newPassword.value != varForm.newPassword2.value){
            alert("<spring:message code="fail.user.passwordUpdate2" />");
            return;
        }
        varForm.action = "${pageContext.request.contextPath}/uss/umt/updateEmplyrPassword.do";
        varForm.submit();
    }
}

function fn_aram_reset(){
    var varForm = document.getElementById("emplyrManageVO");
    varForm.reset();
}

</script>


