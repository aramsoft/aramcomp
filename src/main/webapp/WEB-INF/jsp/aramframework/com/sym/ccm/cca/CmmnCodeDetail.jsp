<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CmmnCodeDetail.jsp
  * @Description : 공통코드 상세조회
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
	<h2>공통코드 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="cmmnCodeVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="codeId" />

<table class="table-detail" summary="분류코드명, 코드ID, 코드ID명, 코드ID설명, 사용여부를 보여주는 공통코드 상세조회 페이지이다.">
<CAPTION>공통코드 상세조회</CAPTION>
  	<tr>
    	<th width="20%">
    		분류코드명
    	</th>
    	<td width="80%">
    		<c:out value="${cmmnCodeVO.clCodeNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		코드ID
    	</th>
    	<td>
    		<c:out value="${cmmnCodeVO.codeId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		코드ID명
    	</th>
    	<td>
    		<c:out value="${cmmnCodeVO.codeIdNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="codeIdDc">코드ID설명</label>
    	</th>
    	<td>
      		<form:textarea path="codeIdDc" cols="75" rows="14"   style="width:450px;" readonly="true" title="저작권보호정책내용" />                 
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="useAt">사용여부</label>
    	</th>
    	<td>
			<form:select path="useAt" disabled="true">
				<form:option value="Y" label="사용" />
				<form:option value="N" label="미사용" />
			</form:select>
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

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("cmmnCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/listCmmnCode.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("cmmnCodeVO");
	varForm.action  = "${pageContext.request.contextPath}/sym/ccm/cca/editCmmnCode.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("cmmnCodeVO");
    
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/cca/deleteCmmnCode.do";
		varForm.submit();
	}
}

</script>
