<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일지관리목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 일지관리 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>일지관리목록</h1>
	    <a href='javascript:fn_aram_regist();' data-icon="plus">등록</a>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">

		<form:form commandName="diaryManageVO" action ="" method="post">
			<input name="diaryId" type="hidden" value="">
		
			<div class="uss-Search">
				<form:select path="searchVO.searchCondition"  data-role="none">
					<form:option value="" label="--선택하세요--" />
			   		<form:option value='DIARY_NM' label="일지명" />
			   		<form:option value='DRCT_MATTER' label="지시사항" />
			   		<form:option value='PARTCLR_MATTER' label="특이사항" />
		   		</form:select>
               	<div class="uss-SearchBox">
	                <form:input path="searchVO.searchKeyword"  class="type-text" data-role="none" />
				</div>
	            <input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search(); return false;" data-role="none" />
			</div>
			
			<form:hidden path="searchVO.pageIndex" />
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
							<a href="javascript:fn_aram_detail('${resultInfo.diaryId}')" data-ajax="false">
								<h3> ${resultInfo.diaryNm } </h3>
								<p> 
									<span class="uss-txtBlue">${resultInfo.diaryProcsPte}%</span>
									<span class="uss-txtBlack">${resultInfo.frstRegisterNm }</span> 
									<span class="uss-txtDate"><fmt:formatDate value="${resultInfo.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span>
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

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 일지관리 List end -->		
	
<script type="text/javaScript">

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("diaryManageVO");
	varForm["searchVO.pageIndex"].value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo";
	varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("diaryManageVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(diaryId){
    var varForm = document.getElementById("diaryManageVO");
    varForm.diaryId.value = diaryId; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/detailDiary.mdo"; 
    varForm.submit();
}

/* ********************************************************
 * 등록화면 처리 함수 
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.diaryId.value = ""; 
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/registDiary.mdo"; 
    varForm.submit();
}

</script>
			
</body>
</html>
