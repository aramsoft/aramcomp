<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Q&amp;A 목록조회</title> 

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 용어사전 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>Q&amp;A 목록조회</h1>
		<a href="javascript:fn_aram_regist();" data-icon="plus">등록</a>
	</div>
	<!-- header end -->

	<!-- contents start -->
	<div data-role="content">
	
		<form:form commandName="qnaManageVO" method="post" data-role="none">
			<input type="hidden" name="qaId" id="qaId" >

 			<div class="uss-Search">
                <form:select path="searchCondition" data-role="none">
			   		<form:option value="WRTER_NM" label="작성자명" />			   
			   		<form:option value="QESTN_SJ" label="질문제목" />			   
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
				<c:when test="${empty qnaManageList}">
					<li class="com-egovNodata">
               			<spring:message code="common.nodata.msg"/>
               		</li>			
				</c:when>
				<c:otherwise>
					<c:forEach var="qnaManage" items="${qnaManageList}">
						<li>
							<a href="javascript:fn_aram_detail('${qnaManage.qaId}')" data-ajax="false">
								<h3> ${qnaManage.qestnSj }</h3>
								<p><span class="uss-txtBlue">${qnaManage.qnaProcessSttusCodeNm }</span><span class="uss-txtBlack">${qnaManage.wrterNm}</span><span class="uss-txtDate">${fn:substring(qnaManage.writngDe, 0, 4)}-${fn:substring(qnaManage.writngDe, 4, 6)}-${fn:substring(qnaManage.writngDe, 6, 8)}</span></p>
								<p class="ui-li-count">${qnaManage.inqireCo}</p>
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
	<div data-role="footer"  data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 용어사전 List end -->		
	
<script type="text/javaScript">
	
 /*********************************************************
  * 페이징 처리 함수
  ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("qnaManageVO");
	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.mdo";		
	varForm.submit();
}
 
function fn_aram_search(){
    var varForm = document.getElementById("qnaManageVO");
	varForm.pageIndex.value = '1';
	varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.mdo";		
	varForm.submit();
 }

function fn_aram_detail(qaId) {
    var varForm = document.getElementById("qnaManageVO");
    varForm.qaId.value = qaId;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/detailQna.mdo";					         
    varForm.submit();
}

function fn_aram_regist() {
    var varForm = document.getElementById("qnaManageVO");
    varForm.qaId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/registQna.mdo";					         
    varForm.submit();
}

</script>

</body>
</html>
