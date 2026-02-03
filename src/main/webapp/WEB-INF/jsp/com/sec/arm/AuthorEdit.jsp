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
  * @Class Name : AuthorEdit.java
  * @Description : 권한 수정
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
	<h2>권한 수정</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="authorVO" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuNm" value="${curMenuNm}" />

<table class="table-register" summary="권한을 수정하는 테이블입니다.권한 코드,권한 명,설명,등록일자 정보를 담고 있습니다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		권한  코드
    	</th>
    	<td>
    		<form:input path="authorCode" readonly="true" size="40" title="권한코드"/>
    		<form:errors path="authorCode" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="authorNm">권한 명</label>
    	</th>
    	<td>
    		<form:input path="authorNm" maxLength="50" size="40" title="권한명"/>
    		<form:errors path="authorNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="authorDc">설명</label>
    	</th>
    	<td>
    		<form:input path="authorDc" maxLength="50" size="50" title="설명"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		등록일자
    	</th>
    	<td>
    		<form:input path="authorCreatDe" maxLength="50" size="20" readonly="true" title="등록일자"/>
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
<validator:javascript formName="authorVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
	var varForm = document.getElementById("authorVO");
	varForm.action = "${pageContext.request.contextPath}/sec/arm/listAuthor.do";
	varForm.submit();
}

function fn_aram_update() {
	var varForm = document.getElementById("authorVO");
	
    if(!validateAuthorVO(varForm)){
        return;
    }

	if (confirm("<spring:message code='common.update.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/arm/updateAuthor.do";
       	varForm.submit();
    }
}

function fn_aram_delete() {
	var varForm = document.getElementById("authorVO");
	
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sec/arm/deleteAuthor.do";
		varForm.submit();
	}
}

</script>
