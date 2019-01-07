<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : HpcmRegist.jsp
  * @Description : 도움말 등록
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
	<h2>도움말 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="hpcmManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="hpcmId" />

<table class="table-register" summary="도움말에 대한 정보를 등록합니다.">
<caption>도움말등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="hpcmSeCode">도움말구분</label>
    	</th>
    	<td>
			<form:select path="hpcmSeCode" title="도움말구분">
				<form:option value="" label="-- 선택 --"/>
				<form:options items="${COM021_hpcmSe}" itemValue="code" itemLabel="codeNm"/>
 			</form:select>
 			<form:errors path="hpcmSeCode" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="hpcmDfn">도움말정의</label>
    	</th>
    	<td>
      		<form:textarea path="hpcmDfn" cols="70" rows="5" cssClass="txArea" title="도움말정의"/>
      		<form:errors path="hpcmDfn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="hpcmDc">도움말설명</label>
    	</th>
    	<td>
      		<form:textarea path="hpcmDc" cols="70" rows="30" cssClass="txArea" title="도움말설명"/>
      		<form:errors path="hpcmDc" cssClass="error"/>
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
<validator:javascript formName="hpcmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("hpcmManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/hpc/listHpcm.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("hpcmManageVO");
    
	if (!validateHpcmManageVO(varForm)) {
		return;
	} 
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/hpc/insertHpcm.do";
		varForm.submit();
	}
}

</script>

