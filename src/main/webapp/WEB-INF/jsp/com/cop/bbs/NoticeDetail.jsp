<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : NoticeDetail.jsp
  * @Description : 게시물 조회
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
	<h2><c:out value='${boardVO.boardMasterVO.bbsNm}'/> - 글조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	 	  	<c:if test="${editAuthFlag == 'Y'}">
	    	<c:if test="${boardVO.frstRegisterId == userId}">
	     		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
	     		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
	    	</c:if>
	    	<c:if test="${role == 'ROLE_ADMIN'}">
	     		<span class="button"><a href="#" onclick="javascript:fn_aram_erase(); return false;">완전삭제</a></span>
	    	</c:if>
	     	<c:if test="${boardVO.boardMasterVO.replyPosblAt == 'Y'}">
	     		<span class="button"><a href="#" onclick="javascript:fn_aram_reply(); return false;">답글작성</a></span>
	    	</c:if>
	      	<c:if test="${useScrap == 'true'}">
	    		<span class="button"><a href="#" onclick="javascript:fn_aram_add_scrap(); return false;">스크랩</a></span>
	        </c:if>
	  	  	</c:if>
	     	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	      	<c:if test="${fullScrYn == 'Y'}">
	    		<span class="button"><a href="#" onclick="javascript:fn_aram_screen('N'); return false;">원래화면</a></span>
	        </c:if>
	      	<c:if test="${fullScrYn != 'Y'}">
	    		<span class="button"><a href="#" onclick="javascript:fn_aram_screen('Y'); return false;">전체화면</a></span>
	        </c:if>
		</span>
	</div>	
</div>

<form:form modelAttribute="boardVO"  method="post" action="">
<input type="hidden" name="fullScrYn" value="${fullScrYn}" />

<form:hidden path="bbsId" />
<form:hidden path="nttId" />

<table class="table-detail">
	<tr>
	    <th width="15%">제목</th>
	    <td width="85%" colspan="5"><c:out value="${boardVO.nttSj}" /></td>
	</tr>
	<tr>
	    <th>바로가기URL</th>
	    <td colspan="5"><span id="directurl"><c:out value="${directUrl}" /></span></td>
	</tr>
	<tr>
	    <th width="15%">작성자</th>
	    <td width="15%" class="lt_text3">
	    	<c:choose>
	    	<c:when test="${anonymous == 'true'}">
	    		******
	    	</c:when>
	    	<c:when test="${boardVO.ntcrNm == null || boardVO.ntcrNm == ''}">
	    		<c:out value="${boardVO.frstRegisterNm}" />
	    	</c:when>
	    	<c:otherwise>
	    		<c:out value="${boardVO.ntcrNm}" />
	    	</c:otherwise>
	    	</c:choose>
	    </td>
	    <th width="15%">작성시간</th>
	    <td width="15%" class="lt_text3"><fmt:formatDate value="${boardVO.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    <th width="15%">조회수</th>
	    <td width="15%" class="lt_text3"><c:out value="${boardVO.rdcnt}" /></td>
	</tr>
	<tr>
	    <td colspan="6">
	     	<div class="bbs_cn">
			<c:out value="${boardVO.nttCn}" escapeXml="false" />
	     	</div>
	    </td>
	</tr>
	<c:if test="${not empty boardVO.atchFileId}">
	<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA02'}">
	<tr>
		<td colspan="6">
			<c:import url="/files/image/${boardVO.atchFileId}" />
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
	
	<c:if test="${anonymous == 'true'}">
	<tr>
	    <th><spring:message code="cop.password" /></th>
	    <td colspan="5">
	    	<input name="password" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
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

<div style="margin-top:10px; width:100%"></div>

<c:if test="${boardVO.boardMasterVO.replyPosblAt == 'Y'}">

<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useComment == 'true'}">
<iframe id="commentFrame" onload="javascript:changeFrameSize(); return false;" src="/board/${boardVO.bbsId}/article/${boardVO.nttId}/comments?anonymous=${anonymous}" seamless="seamless" width="100%" height="0" title="컨텐츠영역"></iframe>
</c:if>

<c:if test="${useSatisfaction == 'true'}">
<iframe id="commentFrame" onload="javascript:changeFrameSize(); return false;" src="/board/${boardVO.bbsId}/article/${boardVO.nttId}/satisfactions?anonymous=${anonymous}" seamless="seamless" width="100%" height="0" title="컨텐츠영역"></iframe>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->

</c:if>

</DIV>

<script type="text/javascript">

function changeFrameSize(){
	var childWindow = document.getElementById('commentFrame').contentWindow; 
	var the_height = childWindow.document.body.scrollHeight;
	document.getElementById('commentFrame').height = the_height + 20;
}

function fn_aram_list() {
    var varForm = document.getElementById("boardVO");
	varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardArticle.do";
    varForm.submit();
}

function fn_aram_screen(fullScrYn) {
    var varForm = document.getElementById("boardVO");
    varForm.fullScrYn.value = fullScrYn;
	varForm.action = "${pageContext.request.contextPath}/cop/bbs/detailBoardArticle.do";
    varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("boardVO");
    
	if ("<c:out value='${anonymous}'/>" == "true" && varForm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		varForm.password.focus();
		return;
	}

	varForm.action = "${pageContext.request.contextPath}/cop/bbs/editBoardArticle.do";
	varForm.submit();
}

function fn_aram_delete() {
    var varForm = document.getElementById("boardVO");
    
	if ("<c:out value='${anonymous}'/>" == "true" && varForm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		varForm.password.focus();
		return;
	}

	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/deleteBoardArticle.do";
		varForm.submit();
	}
}

function fn_aram_erase() {
    var varForm = document.getElementById("boardVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/eraseBoardArticle.do";
		varForm.submit();
	}
}

function fn_aram_reply() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/replyBoardArticle.do";
    varForm.submit();
}

</script> 

<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useScrap == 'true'}">
<script type="text/javascript">
function fn_aram_add_scrap() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/registScrap.do";
    varForm.submit();
}
</script>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->

