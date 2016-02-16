<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CntcInsttEdit.jsp
  * @Description : 연계기관 수정
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
	<h2>연계기관 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cntcInsttVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="insttId"/>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="연계기관 수정을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		기관ID
    	</th>
    	<td width="80%">
      		<c:out value='${cntcInsttVO.insttId}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		기관명
    	</th>
    	<td>
      		<form:input  path="insttNm" size="60" maxlength="60" title="기관명"/>
      		<form:errors path="insttNm" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cntcInsttVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("cntcInsttVO");
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/listCntcInstt.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("cntcInsttVO");
	
	if(!validateCntcInsttVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action  = "${pageContext.request.contextPath}/ssi/syi/iis/updateCntcInstt.do";
		varForm.submit();
	}
}

</script>
