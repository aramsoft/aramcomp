<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : CmmnDetailCodeRegist.jsp
  * @Description : 공통상세코드 등록
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
	<h2>공통상세코드 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="cmmnDetailCodeVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="코드ID, 코드, 코드명, 코드설명, 사용여부를 입력하는 공통상세코드 등록 테이블이다.">
<CAPTION>공통상세코드 등록</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="codeId">코드ID</label>
    	</th>
    	<td width="80%">
			<select name="clCode" onChange="javascript:fn_aram_get_codeId();" title="clCode">
				<c:forEach var="result" items="${cmmnClCodeList}" varStatus="status">
				<option value='<c:out value="${result.clCode}"/>' <c:if test="${result.clCode == cmmnCode.clCode}">selected="selected"</c:if>><c:out value="${result.clCodeNm}"/></option>
				</c:forEach>
			</select>
			<select name="codeId" id="codeId">
				<c:forEach var="result" items="${cmmnCodeList}" varStatus="status">
				<option value='<c:out value="${result.codeId}"/>'><c:out value="${result.codeIdNm}"/></option>
				</c:forEach>
			</select>
    	</td>
 	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="code">코드</label>
    	</th>
    	<td>
      		<form:input  path="code" size="15" maxlength="15" />
      		<form:errors path="code" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="codeNm">코드명</label>
    	</th>
    	<td>
      		<form:input  path="codeNm" size="60" maxlength="60" />
      		<form:errors path="codeNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="codeDc">코드설명</label>
    	</th>
    	<td>
      		<form:textarea path="codeDc" cols="75" rows="14"   style="width:450px;" />                 
      		<form:errors path="codeDc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
			<form:select path="useAt">
				<form:option value="Y" label="사용" />
				<form:option value="N" label="미사용" />
			</form:select>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${cmmnDetailCodeVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${cmmnDetailCodeVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${cmmnDetailCodeVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${cmmnDetailCodeVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cmmnDetailCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("cmmnDetailCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/cde/listCmmnDetailCode.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("cmmnDetailCodeVO");
    
	if(!validateCmmnDetailCodeVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/cde/insertCmmnDetailCode.do";
		varForm.submit();
	}
}

/* ********************************************************
 * CodeId 가져오기
 ******************************************************** */
function fn_aram_get_codeId(){
    var varForm = document.getElementById("cmmnDetailCodeVO");
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/cde/registCmmnDetailCode.do";
    varForm.submit();
}

</script>

