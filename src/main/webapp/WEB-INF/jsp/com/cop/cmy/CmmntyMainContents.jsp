<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CmmntyMainContents.jsp
  * @Description : 커뮤니티 기본 템플릿
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

<style type="text/css">
.comun_board {
	margin-bottom:10px;
	overflow:hidden;
}

.comun_board_title {
	float:left;
	height:28px;
	font-size: 12pt;
	font-weight: bold;
	font-family: "gulim";
	color:#5e5e5e;
}

.comun_board_more {
	float:right;
	height:22px;
}

.comun_board_content {
	clear:both;
	border:10px solid #DDDDDD;
}

.comun_board_content > table {
	cellpadding:2px;
}

</style>

<div id="main">

<c:forEach var="board" items="${bbsList}" varStatus="bstatus">

<div class="comun_board">

	<div class="comun_board_title"><c:out value="${board.bbsNm}" /></div>
	<div class="comun_board_more"> 
		<a href="#" onclick="javascript:fn_aram_loadArticleList('<c:out value="${board.bbsId}" />'); return false;"><img src="${pageContext.request.contextPath}/images/com/cop/tpl/more_08.gif" width="36" height="12" alt="더보기이미지아이콘" ></a>
	</div>
	
	<div class="comun_board_content">
		<table border="0">
			<c:if test="${fn:length(articleList[bstatus.count-1]) == 0}">
			<tr>
				<td height="22" align="left">등록된 내용이 없습니다.</td>
			</tr>
			</c:if>
			<c:forEach var="article" items="${articleList[bstatus.count-1]}" varStatus="status">
			<tr>
				<td height="22" align="left">
					<fmt:parseDate value='${article.frstRegisterPnttm}' pattern="yyyy-MM-dd" var='parsedDate'/>
					<span class="right_board_date"><img src="${pageContext.request.contextPath}/images/com/cop/tpl/dot.gif" width="12" height="4" alt="도트이미지">[<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd"/>]</span> 
	    		<c:choose>
		    	<c:when test="${article.isExpired=='Y' || article.useAt == 'N'}">
		    		<span class="bbs_useless"><c:out value="${article.nttSj}" /></span>
		    	</c:when>
	    		<c:otherwise>
					<a href="#" onclick="javascript:fn_aram_loadArticle('<c:out value="${article.bbsId}" />','<c:out value="${article.nttId}" />'); return false;" class="right_list">
						<c:out value="${article.nttSj}" />
					</a>
	    		</c:otherwise>
	    		</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		
	</div>
	
</div>

</c:forEach>

</div>

<c:if test="${preview == null || preview !='true'}">

<script type="text/javascript">

function fn_aram_get_idString(original) {
	 var index = original.lastIndexOf("_")+1;
	 if( original.charAt(index) != '0' ) return original;

	 for( ; index < original.length; index++ ) {
		 if( original.charAt(index) != '0' ) break;
	 }
	 return (index == original.length) ? original : original.substring(index);
}

function fn_aram_loadArticleList(bbsId) {
	bbsId = fn_aram_get_idString(bbsId);
	location.href = "/apps/id/${targetVO.pathId}/board/"+bbsId+"/list";
}

function fn_aram_loadArticle(bbsId, nttId) {
	bbsId = fn_aram_get_idString(bbsId);
	location.href = "/apps/id/${targetVO.pathId}/board/"+bbsId+"/id/"+nttId;
}

</script>

</c:if>

