<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : DiaryDetail.jsp
 * @Description : 일지 상세조회
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
	<h2>일지 상세조회</h2>
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

<!--  등록  폼 영역  -->
<form:form modelAttribute="diaryManageVO"  action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="diaryId" />

<table class="table-detail">
  	<tr>
    	<th width="20%">
    		일정명
    	</th>
    	<td width="80%">
			<c:out value="${diaryManageVO.schdulNm }" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		일지명
    	</th>
    	<td>
			<c:out value="${diaryManageVO.diaryNm }"  />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		지시사항
    	</th>
    	<td>
			<c:out value="${fn:replace(diaryManageVO.drctMatter , crlf , '<br/>')}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		특이사항
    	</th>
    	<td>
			<c:out value="${fn:replace(diaryManageVO.partclrMatter , crlf , '<br/>')}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		진척율
    	</th>
    	<td>
			<c:out value="${diaryManageVO.diaryProcsPte}"  />%
    	</td>
  	</tr>

	<!--첨부파일 테이블 레이아웃 설정 Start..-->
  	<tr>
		<th>
			파일첨부
		</th>
		<td>
			<c:import url="/files/${diaryManageVO.atchFileId}" />
	 	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 End.-->
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
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/editDiary.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("diaryManageVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/deleteDiary.do";
		varForm.submit();
	}
}

</script>

