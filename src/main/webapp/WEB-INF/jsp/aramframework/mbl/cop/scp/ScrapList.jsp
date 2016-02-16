<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html> 
<head>
<title>스크랩 목록 조회</title> 
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
      
<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 메인 페이지 -->
<div id="list" data-role="page" >

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
		<h1>스크랩</h1> 
	</div>

    <div data-role="content">

    	<!-- 장규완 추가. submit 전달항목 -->
		<form:form commandName="scrapVO" method="post" data-role="none">
			<input type="hidden" name="scrapId">			

			<div class="uss-Search">
			    <form:select path="searchVO.searchCondition" data-role="none">
					<form:option value="" label="--선택하세요--" />
 					<form:option value="SCRAP_NM" label="스크랩명" />
 				</form:select>
               	<div class="uss-SearchBox">
	                <form:input path="searchVO.searchKeyword" class="type-text" data-role="none" />
				</div>
	            <input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search(); return false;" data-role="none" />
			</div>
			
			<form:hidden path="searchVO.pageIndex" />
		</form:form>
		
		<ul data-role="listview" style="clear:both;">
			<c:choose>
			<c:when test="${empty resultList}">
				<li class="com-egovNodata">
			   		<spring:message code="common.nodata.msg" />
				</li>
			</c:when>

			<c:otherwise>
				<c:forEach var="result" items="${resultList}" varStatus="status">
			   		<c:choose>
					<c:when test="${result.useAt == 'N'}">
						<li>
					    	<h3>
			    				<c:out value="${result.nttSj}" />
			    			</h3>
						</li>
		    		</c:when>
		    		
				    <c:otherwise>
					    <li>
					       <a href="javascript:fn_aram_detail('<c:out value="${result.scrapId}"/>')" data-transition="slide">
						        <h3>${result.scrapNm}</h3>
								<p>
									<span class="uss-txtBlack">${result.frstRegisterNm}</span>
									<span class="uss-txtDate"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span>
								</p>
					        </a>
					    </li>            
				    </c:otherwise>  
					</c:choose> 
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
			<ui:pagination paginationInfo="${paginationInfo}" type="mblImage" jsFunction="fn_aram_linkPage" />
		</div>

    </div>

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>
	
<script type="text/javascript">

$('#view').bind('pagehide', function(){ $(this).remove(); });	

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("scrapVO");
	varForm["searchVO.pageIndex"].value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/cop/scp/listScrap.mdo";
	varForm.submit();	
}

function fn_aram_search() {
    var varForm = document.getElementById("scrapVO");
	varForm["searchVO.pageIndex"].value = '1';
	varForm.action = "${pageContext.request.contextPath}/cop/scp/listScrap.mdo";
	varForm.submit();	
}

function fn_aram_detail(scrapId) {
    var varForm = document.getElementById("scrapVO");
    varForm.scrapId.value = scrapId;
    varForm.action = "${pageContext.request.contextPath}/cop/scp/detailScrap.mdo";
    varForm.submit();			
}

</script>

</body>
</html>
