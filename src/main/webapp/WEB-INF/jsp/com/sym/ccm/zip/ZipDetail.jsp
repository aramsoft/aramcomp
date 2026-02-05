<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : ZipDetail.jsp
  * @Description : 우편번호 상세조회
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>우편번호 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="zipVO" action="" method="post">

<form:hidden path="zip" />
<form:hidden path="sn" />

<table class="table-detail" summary="우편번호, 시도명, 시군구명, 읍면동명, 리건물명, 번지동호를 가지고 있는 우편번호 상세조회 테이블이다.">
<CAPTION>우편번호 상세조회</CAPTION>
  	<tr>
    	<th width="20%">
     		우편번호
    	</th>
    	<td width="80%">
    		<c:out value='${fn:substring(zipVO.zip, 0,3)}-${fn:substring(zipVO.zip, 3,6)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		시도명
    	</th>
    	<td>
    		${zipVO.ctprvnNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
     		시군구명
    	</th>
    	<td>
    		${zipVO.signguNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		읍면동명
    	</th>
    	<td>
    		${zipVO.emdNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		리건물명
    	</th>
    	<td>
    		${zipVO.liBuldNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		번지동호
    	</th>
    	<td>
    		${zipVO.lnbrDongHo}
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
   	var varForm = document.getElementById("zipVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/listZip.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
   	var varForm = document.getElementById("zipVO");
	varForm.action  = "${pageContext.request.contextPath}/sym/ccm/zip/editZip.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
   	var varForm = document.getElementById("zipVO");
   	
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/zip/deleteZip.do";
		varForm.submit();
	}
}

</script>
