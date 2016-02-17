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
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<DIV id="main"> 

<div class="content_title">
	<h2><c:out value='${boardVO.boardMasterVO.bbsNm}'/> - 글조회</h2>
</div>

<div id="search_area">
	<div class="sns_area">
		<a href="#" onclick="javascript:fn_aram_scrap_sns('twitter'); return false;">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_twitter.gif" alt="트위터" />
		</a>
		<a href="#" onclick="javascript:fn_aram_scrap_sns('facebook'); return false;">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_facebook.gif" alt="페이스북" />
		</a>
		<a href="#" onclick="javascript:fn_aram_scrap_sns('me2day'); return false;">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_me2day.gif" alt="미투데이" />
		</a>
		<a href="#" onclick="javascript:fn_aram_scrap_sns('yozm'); return false;">
			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_yozm.gif" alt="요즘" />
		</a>
	</div>	
	<div class="button_area">
 	  <c:if test="${editAuthFlag == 'Y'}">
    	<c:if test="${boardVO.frstRegisterId == uniqId}">
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
	</div>
</div>

<form:form commandName="boardVO"  method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

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
			<c:import url="/content/imagefiles/${boardVO.atchFileId}" />
		</td>
	</tr>
	</c:if>
	<tr>
	    <th>첨부파일 목록</th>
	    <td colspan="5">
			<c:import url="/content/files/${boardVO.atchFileId}" />
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
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<div style="margin-top:10px; width:100%"></div>

<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useComment == 'true'}">
<iframe id="commentFrame" onload="javascript:changeFrameSize(); return false;" src="/content/board${prefix}/${boardVO.bbsId}/article/${boardVO.nttId}/comments" seamless="seamless" width="100%" height="0" title="컨텐츠영역"></iframe>
</c:if>

<c:if test="${useSatisfaction == 'true'}">
<iframe id="commentFrame" onload="javascript:changeFrameSize(); return false;" src="/content/board${prefix}/${boardVO.bbsId}/article/${boardVO.nttId}/satisfactions" seamless="seamless" width="100%" height="0" title="컨텐츠영역"></iframe>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->

</DIV>

<script type="text/javascript">

function changeFrameSize(){
	var childWindow = document.getElementById('commentFrame').contentWindow; 
	var the_height = childWindow.document.body.scrollHeight;
	document.getElementById('commentFrame').height = the_height + 20;
}

function fn_aram_scrap_sns(sns) {

	var trget_url = document.getElementById("directurl").innerHTML;
	
	var target = encodeURIComponent(trget_url);
	var msg = encodeURIComponent("${boardVO.nttSj}");
	var tags = encodeURIComponent("");
	var url = "";
//	alert(target);

	if ( sns == 'twitter' ) {
		url = "http://twitter.com/home?status=" + msg + " " + target;
	} else if( sns == 'facebook' ) {
		url = "http://www.facebook.com/sharer.php?u=" + target + "&t=" + msg;
	} else if( sns == 'me2day' ) {
	   	url = "http://me2day.net/posts/new?new_post[body]=" + msg + " " + target + "&new_post[tags]=" + tags;
	} else if( sns == 'yozm' ) {
		url = "http://yozm.daum.net/api/popup/prePost?link=" + target + "&prefix=" + msg;
	}
	window.open(url, "scrap", "width=640px, height=400px;");
}

function fn_aram_list() {
    var varForm = document.getElementById("boardVO");
	varForm.action = "${pageContext.request.contextPath}/content/board${prefix}/" 
					+ fn_aram_get_idString(varForm.bbsId.value) + "/articles";
    varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("boardVO");
    
	if ("<c:out value='${anonymous}'/>" == "true" && varForm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		varForm.password.focus();
		return;
	}

	varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/editBoardArticle.do";
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
		varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/deleteBoardArticle.do";
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
    varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/replyBoardArticle.do";
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

