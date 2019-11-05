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
  * @Class Name : GroupEdit.java
  * @Description : 그룹 수정
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
	<h2>그룹 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="groupVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="그룹을 수정하는 테이블입니다.그룹 ID,그룹 명,설명,등록일자 정보를 담고 있습니다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		그룹 ID
    	</th>
    	<td>
    		<form:input path="groupId" readonly="true" size="40" title="그룹 ID" />
    	</td>
  	</tr>
  	<tr>
    	<th>
   			<span class="required_icon"></span>
     		<label for="groupNm">그룹 명</label>
    	</th>
    	<td>
    		<form:input path="groupNm" type="text" maxLength="50" size="40" title="그룹명" />
    		<form:errors path="groupNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="groupDc">설명</label>
    	</th>
    	<td>
    		<form:input path="groupDc" maxLength="50" size="50" title="설명" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		등록일자
    	</th>
    	<td>
    		<form:input path="groupCreatDe"  maxLength="50" size="20" readonly="true" title="등록일자"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${groupVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${groupVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${groupVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${groupVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="groupVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("groupVO");
    varForm.action = "${pageContext.request.contextPath}/sec/grp/listGroup.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("groupVO");
    
    if(!validateGroupVO(varForm)){
        return;
    }

	if (confirm("<spring:message code='common.update.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/grp/updateGroup.do";
      	varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("groupVO");

	if (confirm("<spring:message code='common.delete.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/grp/deleteGroup.do";
    	varForm.submit();
    }
}

</script>
