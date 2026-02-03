<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : RestdeDetail.jsp
  * @Description : 휴일 상세조회
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
	<h2>휴일 상세조회</h2>
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

<form:form modelAttribute="restdeVO" method="post" action="">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="restdeNo" />

<table class="table-detail" summary="선택한 휴일의 휴일일자, 휴일명, 휴일설명, 휴일구분의 정보를 상세조회한다.">
<CAPTION>휴일 상세조회</CAPTION>
  	<tr>
    	<th width="20%">
    		휴일일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(restdeVO.restdeDe, 0,4)}-${fn:substring(restdeVO.restdeDe, 4,6)}-${fn:substring(restdeVO.restdeDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		휴일명
    	</th>
    	<td>
    		${restdeVO.restdeNm}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		휴일설명
    	</th>
	    <td>
	    	<textarea class="textarea"  cols="75" rows="14"  style="width:450px;" disabled title="휴일설명">${restdeVO.restdeDc}</textarea>
	    </td>
	 </tr>
  	<tr>
    	<th>
    		휴일구분
    	</th>
    	<td>
    		${restdeVO.restdeSe}
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
    var varForm = document.getElementById("restdeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/cal/listRestde.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("restdeVO");
	varForm.action = "${pageContext.request.contextPath}/sym/cal/editRestde.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("restdeVO");
    
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		varForm.action = "${pageContext.request.contextPath}/sym/cal/deleteRestde.do";
		varForm.submit();
	}
}

</script>
