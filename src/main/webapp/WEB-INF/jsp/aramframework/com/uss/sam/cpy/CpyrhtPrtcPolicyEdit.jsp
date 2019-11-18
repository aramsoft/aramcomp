<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : CpyrhtPrtcPolicyEdit.jsp
  * @Description : 저작권보호정책 수정
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
	<h2>저작권보호정책 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="cpyrhtPrtcPolicyVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cpyrhtId" />

<table class="table-register" summary="이 표는  저작권보호정책내용 정보를  수정하기 위한 표이며, 저작권보호정책내용 정보로 구성되어 있습니다 .">
<caption>저작권보호정책내용 수정</caption>
  	<tr>
    	<th width="25%">
    		<span class="required_icon"></span>
    		<label for="cpyrhtPrtcPolicyCn">저작권보호정책내용</label>
    	</th>
    	<td>
      		<form:textarea path="cpyrhtPrtcPolicyCn" title="저작권보호정책내용" cols="300" rows="20"  style="width:500px;" />           
      		<form:errors path="cpyrhtPrtcPolicyCn" cssClass="error"/>
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
<validator:javascript formName="cpyrhtPrtcPolicyVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {

	// 첫 입력란에 포커스..
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
	varForm.cpyrhtPrtcPolicyCn.focus();
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/listCpyrhtPrtcPolicy.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("cpyrhtPrtcPolicyVO");
    
	if (!validateCpyrhtPrtcPolicyVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/sam/cpy/updateCpyrhtPrtcPolicy.do";
		varForm.submit();
	}
}

</script>
