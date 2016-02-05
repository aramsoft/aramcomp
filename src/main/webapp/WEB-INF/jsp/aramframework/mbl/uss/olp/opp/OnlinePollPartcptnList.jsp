<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<jsp:useBean id="now" class="java.util.Date"/>

<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>온라인POLL참여 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 온라인POLL 목록 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>온라인POLL참여 목록</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">

		<form:form commandName="onlinePollPartcptnVO" method="post" data-role="none">
			<input type="hidden" id="pollId" name="pollId" />

			<div class="uss-Search">
				<form:select path="searchCondition" data-role="none">
					<form:option value="" label="--선택하세요--" />
				   	<form:option value='POLL_NM' label="POLL명" />
			    </form:select>
               	<div class="uss-SearchBox">
	                <form:input path="searchKeyword" class="type-text" data-role="none" />
				</div>
	            <input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search(); return false;" data-role="none" />
			</div>
			
			<form:hidden path="pageIndex" />
		</form:form>
		
		<ul data-role="listview" data-split-icon="search">
			<c:choose>
				<c:when test="${empty resultList}">
					<li class="com-egovNodata">
               			<spring:message code="common.nodata.msg"/>
               		</li>			
				</c:when>
				<c:otherwise>
					<c:forEach var="resultInfo" items="${resultList}">
						<li>
							<a href="javascript:fn_aram_insert('${resultInfo.pollId}','${resultInfo.pollBeginDe}','${resultInfo.pollEndDe}')" data-ajax="false">
								<h3> ${resultInfo.pollNm } </h3>
								<p class="uss-gray"><strong>${resultInfo.pollBeginDe } ~  ${resultInfo.pollEndDe }</strong></p>
								<p> <span class="uss-txtBlack">${resultInfo.frstRegisterNm }</span><span class="uss-txtDate"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span></p>
							</a>
							<a href="javascript:fn_show_stat('${resultInfo.pollId}');">통계</a>
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
	<div data-role="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
	
<script type="text/javaScript">
		
function fn_list_page() {
	$.mobile.changePage( $("#list"), { transition: "slideup" });
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.mdo";
    varForm.submit();
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_aram_insert(pollId,sDate,eDate){
    var varForm = document.getElementById("onlinePollPartcptnVO");
	var iToDate = <fmt:formatDate value="${now}" pattern="yyyyMMdd" />;
	var iBeginDate = Number(sDate.replaceAll("-",""));
	var iEndDate = Number(eDate.replaceAll("-",""));

	if(iToDate>= iBeginDate && iToDate <= iEndDate){

		varForm.pollId.value = pollId; 
		varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/registOnlinePollPartcptn.mdo"; 
		varForm.submit();
	}else{
		jAlert("지금은 온라인POLL 투표기간이 아닙니다!", '알림', 'a');
		return;
	}
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(pageIndex){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm.pageIndex.value = pageIndex;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.mdo";
    varForm.submit();
}

/* ********************************************************
 * 통계보기
 ******************************************************** */
function fn_show_stat(pollId) {

	$.mobile.changePage("${pageContext.request.contextPath}/uss/olp/opp/statisticsOnlinePollPartcptn.mdo",{
		data: {pollId: pollId},
		type: 'post',
		changeHash: false,
		reloadPage: true
	});
	
}

/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	var str = this;
	if(src == repl){return str;}
	while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	}
	return str;
}
      	
</script>
		
</body>
</html>	
