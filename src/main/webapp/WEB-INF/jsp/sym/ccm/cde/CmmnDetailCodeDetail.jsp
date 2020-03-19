<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CmmnDetailCodeDetail.jsp
  * @Description : 공통상세코드 상세조회
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
	<h2>공통상세코드 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="cmmnDetailCodeVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="codeId" />
<form:hidden path="code" />

<table class="table-detail" summary="코드ID명, 코드, 코드명, 코드설명, 사용여부가 나타나 있는 공통상세코드 상세조회 테이블이다.">
<CAPTION>공통상세코드 상세조회</CAPTION>
  	<tr>
    	<th width="20%">
    		코드ID명
    	</th>
    	<td width="80%">
    		<c:out value="${cmmnDetailCodeVO.codeIdNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		코드
    	</th>
    	<td>
    		<c:out value="${cmmnDetailCodeVO.code}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		코드명
    	</th>
    	<td>
    		<c:out value="${cmmnDetailCodeVO.codeNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="codeDc">코드설명</label>
    	</th>
    	<td>
      		<form:textarea path="codeDc" cols="75" rows="14"   style="width:450px;" readonly="true"  />                 
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
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

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
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("cmmnDetailCodeVO");
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/cde/editCmmnDetailCode.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("cmmnDetailCodeVO");
    
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/cde/deleteCmmnDetailCode.do";
		varForm.submit();
	}
}

</script>

