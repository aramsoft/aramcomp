<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ScrapDetail.jsp
 * @Description : 스크랩 상세조회
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
	<h2>스크랩 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<c:if test="${scrapVO.frstRegisterId == uniqId}">
    		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
    		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		</c:if>
    	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="scrapVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="bbsId" />
<form:hidden path="nttId" />
<form:hidden path="scrapId" />

<table class="table-detail" summary="등록된 스크랩에 대한 상세정보를 제공합니다.">
	<tr>
	    <th width="14%">스크랩명</th>
	    <td width="86%"  colspan="5"><c:out value="${scrapVO.scrapNm}" /></td>
	</tr>
	<tr>
	    <th width="14%">게시판 명</th>
	    <td width="20%"><c:out value="${boardVO.boardMasterVO.bbsNm}" /></td>
	    <th width="13%">스크랩 작성자</th>
	    <td width="20%"><c:out value="${scrapVO.frstRegisterNm}" /></td>
	    <th width="13%">스크랩 작성시간</th>
	    <td width="20%"><fmt:formatDate value="${scrapVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
	    <th>게시물 제목</th>
	    <td colspan="5"><c:out value="${boardVO.nttSj}" /></td>
	</tr>
	<tr>
	    <th>게시물 작성자</th>
	    <td>
			<c:out value="${boardVO.frstRegisterNm}" />
	    </td>
	    <th>게시물 작성시간</th>
	    <td><fmt:formatDate value="${boardVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	    <th>게시물 조회수</th>
	    <td><c:out value="${boardVO.rdcnt}" /></td>
	</tr>
	<tr>
	    <th>글내용</th>
	    <td colspan="5">
	     	<div class="bbs_cn">
<c:out value="${boardVO.nttCn}" escapeXml="false" />
	     	</div>
	    </td>
	</tr>
	<c:if test="${not empty boardVO.atchFileId}">
	<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA02'}">
	<tr>
	    <th>첨부이미지</th>
	    <td colspan="5">
			<c:import url="/files/image/${boardVO.atchFileId}" charEncoding="utf-8" />
	    </td>
	</tr>
	</c:if>
	<tr>
	    <th>첨부파일 목록</th>
		<td colspan="5">
			<c:import url="/files/${boardVO.atchFileId}" />
		</td>
	</tr>
	</c:if>
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

function fn_aram_list() {
    var varForm = document.getElementById("scrapVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/listScrap.do";
    varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("scrapVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/editScrap.do";
    varForm.submit();
}

function fn_aram_delete() {
    var varForm = document.getElementById("scrapVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/scp/deleteScrap.do";
		varForm.submit();
	}
}

</script>

