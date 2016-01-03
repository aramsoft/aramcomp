<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
/**
 * @Class Name : OpenApiInquiryHistoryDetail.jsp
 * @Description : Open-API 조회 이력 상세조회
 * @Modification Information
 * @
 * @ 수정일                    수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2011.08.10   조재만          최초 생성
 *
 *  @author 모바일 신규공통컴포넌트개발팀 조재만
 *  @since 2011.08.10
 *  @version 1.0 
 *  @see
 *  
 */
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Open-API 조회 이력 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/mcomd/egovMcomdAdmin.css" type="text/css">

<script type="text/javascript">
function fn_aram_select_openApiList(pageNo) {
	document.listFrm.action = "${pageContext.request.contextPath}/mbl/com/oas/selectOpenApiInquiryHistoryList.mdo";
	document.listFrm.submit();
}
</script>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div>

	<!-- header Start -->
	<div id="header">
		<a href="${pageContext.request.contextPath}/mindex.jsp"><span class="btnHome"></span></a>
		<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/h1_logo.png" /></h1>
		<a href="${pageContext.request.contextPath}/mbl/com/oas/selectOpenApiInquiryHistoryList.mdo"><span class="btnBack"></span></a>
	</div>
	<!-- header End -->
	
	<div id="openApi" class="contents2">
		<table width="100%" cellpadding="8" class="table-search" border="0">
		 	<tr>
		  		<td width="100%"class="title_left">
		   			<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align: middle" alt="">&nbsp;Open-API 조회 이력 - 상세 조회
		   		</td>
		 	</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="Open-API 조회 이력에 대한 상세정보를 제공합니다.">
			<tbody>
		  		<tr> 
		    		<th width="20%">Open-API 제공기관&nbsp;</th>
		    		<td width="80%"><c:out value="${openApi.openApiProvdInsttNm}"/></td>
		  		</tr>
		  		<tr> 
				    <th width="20%">Open-API 서비스구분&nbsp;</th>
				    <td width="80%"><c:out value="${openApi.openApiSvcNm}"/></td>
		  		</tr>
		  		<tr> 
		    		<th width="20%" height="138">서비스 내용&nbsp;</th>
		    		<td width="80%">
				    	<c:if test="${openApi.openApiSvcNm == '날씨'}">
				    		${openApi.openApiSvcCn}
				    	</c:if>
				    	<c:if test="${openApi.openApiSvcNm == '검색'}">
				    		<c:out value="${openApi.openApiSvcCn}"/>
				    	</c:if>
		    		</td>
		  		</tr>
				<tr> 
					<th width="20%">수집일시&nbsp;</th>
				    <td width="80%">
				    	<fmt:formatDate value="${openApi.collectDt}" pattern="yyyy-MM-dd"/>
				    </td>
				</tr>
			</tbody>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr> 
		    	<td height="10"></td>
		  	</tr>
		</table>
	  	<div align="center">
			<table border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td>
				    	<form name="listFrm" method="post" action="${pageContext.request.contextPath}/mbl/com/oas/selectOpenApiInquiryHistoryList.mdo">
				      		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
				      		<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>">
				      		<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>">
				      		<span class="button"><input type="submit" value="목록" onclick="fn_aram_select_openApiList('<c:out value='${searchVO.pageIndex}'/>'); return false;"></span>
				      	</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- footer Start-->
	<div id="footer2">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer End -->

</div>
</body>
</html>