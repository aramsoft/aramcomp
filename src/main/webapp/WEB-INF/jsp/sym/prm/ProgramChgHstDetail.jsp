<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : ProgramChgHstDetail.jsp
  * @Description : 프로그램변경이력 상세조회
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
	<h2>프로그램변경이력 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="progrmManageDtlVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>변경요청내역</h2>
</div>

<table class="table-detail" summary="변경요청내역 상세 ">
<caption>변경요청내역 상세</caption>
  	<tr>
    	<th width="20%">
    		요청번호
    	</th>
    	<td width="80%">
    		<c:out value="${progrmManageDtlVO.rqestNo}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		프로그램파일명
    	</th>
    	<td>
    		<c:out value="${progrmManageDtlVO.progrmFileNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		요청자ID
    	</th>
    	<td>
    		<c:out value="${progrmManageDtlVO.rqestPersonId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		요청일자
    	</th>
    	<td>
    		<c:out value="${progrmManageDtlVO.rqestDe}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		요청제목
    	</th>
    	<td>
    		<c:out value="${progrmManageDtlVO.rqestSj}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		변경요청내용
    	</th>
    	<td>
      		<textarea name="changeRqestCn" class="textarea"  cols="75" rows="5" readOnly style="width:450px;border:0;background-color:transparent;filter: chroma(color=ffffff);" title="변경요청내용"><c:out value="${progrmManageDtlVO.changeRqestCn}"/></textarea>
    	</td>
  	</tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>변경처리내역</h2>
</div>

<table class="table-register" summary="변경처리내역 상세 ">
<caption>변경처리내역 상세</caption>
  	<tr>
    	<th width="20%">
    		변경처리일자
    	</th>
    	<td width="80%">
    		<c:out value="${progrmManageDtlVO.processDe}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		변경처리자
    	</th>
    	<td>
    		<c:out value="${progrmManageDtlVO.opetrId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		변경처리상태
    	</th>
    	<td>
      		<c:if test="${empty progrmManageDtlVO.processSttus}">N/A</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'A'}">신청중</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'P'}">진행중</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'R'}">반려</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'C'}">처리완료</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		변경처리내용
    	</th>
    	<td>
      		<textarea name="rqestProcessCn" class="textarea"  cols="75" rows="5" readOnly style="width:450px;border:0;background-color:transparent;filter: chroma(color=ffffff);" title="변경처리내용"><c:out value="${progrmManageDtlVO.rqestProcessCn}"/></textarea>
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

</div>

<script type="text/javascript">

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChgHst.do";
    varForm.submit();
}

</script>
