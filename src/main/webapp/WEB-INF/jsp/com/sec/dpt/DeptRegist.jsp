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
  * @Class Name : DeptRegist.jsp
  * @Description : 부서 등록
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
	<h2>부서 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="deptVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	부서ID
	    </th>
	    <td>
	    	<form:input path="orgnztId" size="30" readonly="true" title="부서ID" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="orgnztNm">부서명</label>
	    </th>
	    <td>
	        <form:input path="orgnztNm" maxLength="10" size="30" />
	        <form:errors path="orgnztNm" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="orgnztDc">설명</label>
	    </th>
	    <td>
	    	<form:input path="orgnztDc" maxLength="255" size="50" />
	    	<form:errors path="orgnztDc" cssClass="error"/>
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
<validator:javascript formName="deptVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("deptVO");
    varForm.action = "${pageContext.request.contextPath}/sec/dpt/listDept.do";
    varForm.submit();
}

function fn_aram_insert() {
    var varForm = document.getElementById("deptVO");
    
    if(!validateDeptVO(varForm)){
        return;
    }

	if (confirm("<spring:message code='common.regist.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sec/dpt/insertDept.do";
        varForm.submit();
    }
}

</script>
