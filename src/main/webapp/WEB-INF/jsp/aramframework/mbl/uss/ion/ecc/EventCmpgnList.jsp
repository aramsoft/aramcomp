<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>행사/이벤트/캠페인 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

<!-- datebox  import-->        
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/datepicker/jqm-datebox.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.calbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.datebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.flipbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jquery.mobile.datebox.i18n.ko.utf8.js"></script> 

</head>

<body>

<!-- 행사/이벤트/캠패인 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>행사/이벤트/캠페인 목록</h1>
	    <a href="javascript:fn_aram_regist();" data-icon="arrow-left" rel="external">등록</a>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">

		<form:form commandName="eventCmpgnVO"  method="post" data-role="none">
			<input type="hidden" id="eventId" name="eventId" />

			<div class="uss-Search">
				<form:select path="searchCondition" data-role="none">
				   <form:option value='EVENT_CN' label="행사내용" />
				   <form:option value='FRST_REGISTER_ID' label="작성자" />
			    </form:select>
               	<div class="uss-SearchBox">
	                <form:input path="searchKeyword" class="type-text" data-role="none" />
				</div>
	            <input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search(); return false;" data-role="none" />
			</div>
			
			<form:hidden path="pageIndex" />
		</form:form>
		
		<ul data-role="listview">
			
			<c:choose>
				<c:when test="${empty resultList}">
					<li class="com-egovNodata">
               			<spring:message code="common.nodata.msg"/>
               		</li>			
				</c:when>
				
				<c:otherwise>
					<c:forEach var="resultInfo" items="${resultList}">
						<li>
							<a href="javascript:fn_aram_detail('${resultInfo.eventId}')" data-ajax="false">
								<h3> ${resultInfo.eventCn }  </h3>
								<p> 
									<span class="uss-txtBlue">
										<c:forEach items="${COM035_eventTy}" var="comCodeList" varStatus="status">
											<c:if test="${comCodeList.code eq fn:trim(resultInfo.eventTyCode)}">	
												${comCodeList.codeNm}
											</c:if>
										</c:forEach>
									</span>
									<span class="uss-txtDate"> ${resultInfo.eventSvcBeginDe} ~ ${resultInfo.eventSvcEndDe} </span>
								</p>
								<p>
									<span class="uss-txtBlack"> ${resultInfo.frstRegisterNm } </span>
									<span class="uss-txtDate"> ${resultInfo.eventSvcBeginDe}</span>
								</p>
							</a>
						</li>
					</c:forEach>					
				</c:otherwise>
			</c:choose>
			
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
			<ui:pagination paginationInfo="${paginationInfo}" type="mblImage" jsFunction="fn_aram_linkPage" />
		</div>
		
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 행사/이벤트/캠패인 List end -->		
	
<script type="text/javaScript">

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("eventCmpgnVO");
	varFormpageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo";
	varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(eventId){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.eventId.value = eventId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/detailEventCmpgn.mdo";
    varForm.submit();
}
	
/* ********************************************************
 * 등록화면 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.eventId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/registEventCmpgn.mdo";
    varForm.submit();
}
      	
</script>
			
</body>
</html>
