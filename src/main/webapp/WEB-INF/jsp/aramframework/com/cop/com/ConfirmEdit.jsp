<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ConfirmEdit.jsp
  * @Description : 승인 정보 수정
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
	<h2>승인 정보 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="confirmHistoryVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="confmNumber" />
<form:hidden path="trgetJobId" />
<form:hidden path="confmRqesterId" />
<form:hidden path="confmerId" />
<form:hidden path="confmTyCode" />
<form:hidden path="opertId" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	승인유형
	    </th>
	    <td width="80%">
	      	<c:out value="${confirmHistoryVO.confmTyCodeNm}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	승인요청자
	    </th>
	    <td>
	      	<c:out value="${confirmHistoryVO.confmRqesterNm}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	승인상태
	    </th>
	    <td>
            <form:select path="confmSttusCode" class="select" title="승인상태선택">
                <form:option value="" label="--선택하세요--"/>
                <form:options items="${COM007_confmSttus}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
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

<script type="text/javascript">

function fn_aram_list() {
	var varForm = document.getElementById("confirmHistoryVO");
	varForm.action = "${pageContext.request.contextPath}/cop/com/listConfirmByTrget.do";
	varForm.submit();
}

function fn_aram_update() {
	var varForm = document.getElementById("confirmHistoryVO");
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/com/updateConfirm.do";
		varForm.submit();
	}
}

</script>

