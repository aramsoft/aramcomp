<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : RssManageDetail.jsp
  * @Description : RSS서비스관리 상세조회
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>RSS서비스관리 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="rssManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="rssId" />

<!--  등록  폼 영역  -->
<table class="table-detail" summary="상세조회  목록을  제공한다.">
<caption>상세조회  목록을  제공한다</caption>
	<tr> 
		<th width="20%">
			대상서비스명
		</th>
		<td width="80%">
			<c:out value="${rssManageVO.trgetSvcNm}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			대상테이블명
		</th>
		<td>
			<c:out value="${rssManageVO.trgetSvcTable}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			대상서비스목록갯수
		</th>
		<td>
			<c:out value="${rssManageVO.trgetSvcListCo}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			헤더TITLE
		</th>
		<td>
			<c:out value="${rssManageVO.hderTitle}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			헤더LINK
		</th>
		<td>
			<c:out value="${rssManageVO.hderLink}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			헤더DESCRIPTION
		</th>
		<td>
			<c:out value="${fn:replace(rssManageVO.hderDescription , crlf , '<br/>')}" escapeXml="true" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			헤더TAG
		</th>
		<td>
			<c:out value="${rssManageVO.hderTag}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			헤더ETC
		</th>
		<td>
			<c:set var="hderEtc" value="${fn:escapeXml(rssManageVO.hderEtc)}"/>
			<c:set var="hderEtc" value="${fn:replace(hderEtc , crlf , '<br>')}"/>
			<c:out value="${hderEtc}" escapeXml="false" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			본문TITLE
		</th>
		<td>
			<c:out value="${rssManageVO.bdtTitle}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			본문LINK
		</th>
		<td>
			<c:out value="${rssManageVO.bdtLink}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			본문DESCRIPTION
		</th>
		<td>
			<c:out value="${fn:replace(rssManageVO.bdtDescription , crlf , '<br/>')}" escapeXml="true" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			본문TAG
		</th>
		<td>
			<c:out value="${rssManageVO.bdtTag}" />&nbsp;
		</td>
	</tr>
	<tr> 
		<th>
			본문ETC
		</th>
		<td>
			<c:set var="bdtEtc" value="${fn:escapeXml(rssManageVO.bdtEtc)}"/>
			<c:set var="bdtEtc" value="${fn:replace(bdtEtc , crlf , '<br>')}"/>
			<c:out value="${bdtEtc}" escapeXml="false" />&nbsp;
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
    var varForm = document.getElementById("rssManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/listRssManage.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("rssManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/editRssManage.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("rssManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/deleteRssManage.do";
		varForm.submit();
	}
}

</script>
