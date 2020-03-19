<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CmmnClCodeDetail.jsp
  * @Description : 공통분류코드 상세조회
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
	<h2>공통분류코드 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="cmmnClCodeVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="clCode" />

<table class="table-register" summary="분류코드, 분류코드명, 분류코드설명, 사용여부를 알 수 있는 공통분류코드 상세조회 테이블이다.">
<CAPTION>공통분류코드 상세조회</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		분류코드
    	</th>
    	<td width="80%">
    		<c:out value="${cmmnClCodeVO.clCode}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		분류코드명
    	</th>
    	<td>
    		<c:out value="${cmmnClCodeVO.clCodeNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		분류코드설명
    	</th>
    	<td>
      		<form:textarea path="clCodeDc" cols="75" rows="14" style="width:450px;" readonly="true" title="저작권보호정책내용" />                 
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
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
    var varForm = document.getElementById("cmmnClCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/ccc/listCmmnClCode.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("cmmnClCodeVO");
	varForm.action = "${pageContext.request.contextPath}/sym/ccm/ccc/editCmmnClCode.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("cmmnClCodeVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/ccc/deleteCmmnClCode.do";
		varForm.submit();
	}
}

</script>
