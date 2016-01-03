<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : MmsTransResultList.jsp
 * @Description : MMS 전송 결과 목록조회
 * @Modification Information
 * @
 * @ 수정일                    수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2011.08.30   조재만          최초 생성
 *
 *  @author 모바일 신규공통컴포넌트개발팀 조재만
 *  @since 2011.08.30
 *  @version 1.0 
 *  @see
 *  
 */
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/mcomd/egovMcomdAdmin.css" type="text/css">
<script type="text/javascript">
	
function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_mmsTransResult();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "${pageContext.request.contextPath}/mbl/com/mms/selectMmsTransResultList.mdo";
	document.frm.submit();
}

function fn_aram_search_mmsTransResult() {
	document.frm.pageIndex.value = '1';
	document.frm.action = "${pageContext.request.contextPath}/mbl/com/mms/selectMmsTransResultList.mdo";
	document.frm.submit();
}

function fn_aram_get_trnsmisResult(messageId) {
	document.frm.mssageId.value = messageId;
	document.frm.action = "${pageContext.request.contextPath}/mbl/com/mms/getMmsTransmissionResult.mdo";
	document.frm.submit();
}
	
</script>
<title>MMS 전송 결과 목록조회</title>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
	
<div>

	<!-- header Start -->
	<div id="header">
		<a href="${pageContext.request.contextPath}/mindex.jsp"><span class="btnHome"></span></a>
		<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/h1_logo.png" /></h1>
		<a href="${pageContext.request.contextPath}/mbl/com/mms/selectMmsTransResultList.mdo"><span class="btnBack"></span></a>
	</div>
	<!-- header End -->
	
	<div id="attachFile" class="contents2">
	
		<form name="frm" method="post" action="${pageContext.request.contextPath}/mbl/com/mms/selectMmsTransResultList.mdo">
	
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
			<input type="hidden" name="sn" value="0">
			<input type="hidden" name="mssageId" value="">
			
			<table width="100%" cellpadding="8" class="table-search" border="0">
			<tbody>
			 	<tr>
			  		<td width="40%" class="title_left">
			   			<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/tit_icon.gif" width="16" height="16" hspace="3" style="vertical-align: middle" alt="">
			   			&nbsp;MMS 전송 결과 목록
			   		</td>
					<td width="10%">
				   		<select name="searchCondition" class="select">
							<option value=''>--선택하세요--</option>
							<option value="0" <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if>>송신번호</option>
							<option value="1" <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>수신번호</option>
							<option value="2" <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>>전송결과</option>
							<option value="3" <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if>>요청결과</option>
					   	</select>
					</td>
				  	<td width="35%">
				    	<input name="searchKeyword" type="text" size="35" value='<c:out value="${searchVO.searchKeyword}"/>' maxlength="35" onkeypress="press(event);"> 
				  	</td>
			  		<th width="10%">
			   		<table border="0" cellspacing="0" cellpadding="0">
			    		<tr>
			      			<td><span class="button"><input type="submit" value="조회" onclick="javascript:fn_aram_search_mmsTransResult(); return false;"></span></td>
			      			<td>&nbsp;&nbsp;</td>
			    		</tr>
			   		</table>
			  		</th>  
			 	</tr>
			</tbody>
			</table>
				
			<table width="100%" cellpadding="8" class="table-list" summary="MMS 전송 결과에 대한 목록을 제공합니다.">
			<thead>
			  	<tr>
				    <th width="5%">번호</th>
				    <th width="15%">송신번호</th>
				    <th width="15%">수신번호</th>
				    <th width="10%">요청결과</th>
				    <th width="15%">전송결과</th>
				    <th width="15%">전송일자</th>
				    <th width="15%">전송확인일자</th>
				    <th width="10%">결과확인</th>
			  	</tr>
			</thead>    
			<tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
				    <td class="lt_text3"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	
				    <td class="lt_text3"><c:out value="${result.trnsmisNo}"/></td>
				    <td class="lt_text3"><c:out value="${result.recptnNo}"/></td>
				    <td class="lt_text3"><c:out value="${result.rqesterResultNm}"/></td>
				    <td class="lt_text3">
					    <c:if test="${result.trnsmisResultNm == null}">
					    	<c:if test="${result.rqesterResultNm == '요청실패'}">
				    			-
				    		</c:if>
				    		<c:if test="${result.rqesterResultNm == '요청성공'}">
				    			<c:out value="${result.trnsmisResultNm}"/>
				    		</c:if>
					    </c:if>
					    <c:if test="${result.trnsmisResultNm != null}">
				    		<c:out value="${result.trnsmisResultNm}"/>
					    </c:if>
				    </td>
				    <td class="lt_text3"><c:out value="${result.trnsmisDt}"/></td>
				    <td class="lt_text3"><c:out value="${result.trnsmisConfirmDt}"/></td>
				    <td class="lt_text3">
			    		<c:if test="${result.rqesterResultNm == '요청성공'}">
			    			<c:if test="${result.trnsmisResultNm != '전송수신성공'}">
				    			<span class="button">
				    				<input type="button" value="확인" onclick="javascript:fn_aram_get_trnsmisResult('<c:out value="${result.mssageId}"/>'); return false;"/>
				    			</span>
				    		</c:if>
				    	</c:if>
				    </td>
				</tr>
				</c:forEach>	  
				<c:if test="${fn:length(resultList) == 0}">
				<tr>
				    <td class="lt_text3"  colspan="8"><spring:message code="common.nodata.msg" /></td>  
				</tr>		 
				</c:if>
			</tbody>
			</table>
		</form>
			
		<div align="center">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
		</div>
		
	</div>
	
	<!-- footer Start-->
	<div id="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer End -->
	
</div>	
</body>
</html>