<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<html>
<head> 
<title>스크랩 상세조회</title> 
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
<div id="detail" data-role="page" >

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
		<h1>스크랩 상세조회</h1> 
	</div>
	
	<div data-role="content" class="com-copContent">
	
		<form:form commandName="scrapVO" action ="" method="post">
			<form:hidden path="bbsId" />
			<form:hidden path="nttId" />
			<form:hidden path="scrapId" />
			
			<!-- 검색조건 유지 -->
			<form:hidden path="searchCondition" />
			<form:hidden path="searchKeyword" />
			<form:hidden path="pageIndex" />
			<!-- 검색조건 유지 -->
		</form:form>
	
		<ul class="uss-hpcDetail">
			<li>
				<span class="uss-tit">스크랩명</span>
				<span class="uss-con"><c:out value="${scrapVO.scrapNm}" /></span>
			</li>
			<li>
				<span class="uss-tit">스크랩 작성자</span>
				<span class="uss-con"><c:out value="${scrapVO.frstRegisterNm}" /></span>
			</li>
			<li>
				<span class="uss-tit">스크랩 작성시간 </span>
				<span class="uss-con"><fmt:formatDate value="${scrapVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span>
			</li>
			<li>
				<span class="uss-tit">게시판 제목 </span>
				<span class="uss-con"><c:out value="${boardVO.boardMasterVO.bbsNm}" /></span>
			</li>
			<li>
				<span class="uss-tit">게시물 제목 </span>
				<span class="uss-con"><c:out value="${boardVO.nttSj}" /></span>
			</li>
			<li>
				<span class="uss-tit">게시물 작성자 </span>
				<span class="uss-con">
					<c:choose>
						<c:when test="${boardVO.ntcrNm == ''}">
							<c:out value="${boardVO.frstRegisterNm}" />
						</c:when>
						<c:otherwise>
						    <c:out value="${boardVO.ntcrNm}" />
						</c:otherwise>
					</c:choose>
				</span>
			</li>
			<li>
				<span class="uss-tit">게시물 작성시간 </span>
				<span class="uss-con"><fmt:formatDate value="${boardVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span>
			</li>
			<li>
				<span class="uss-tit">게시물 조회수 </span>
				<span class="uss-con"><c:out value="${boardVO.rdcnt}" /></span>
			</li>
			<li>
				<span class="uss-tit">글내용 </span>
			</li>		
			<li class="uss-contentsView">
				<c:out value="${fn:replace(boardVO.nttCn , crlf , '<br>')}"  escapeXml="false"/>  
			</li>
		</ul>

		<div class="ui-grid-b">
			<div class="ui-block-a"><a href="javascript:fn_aram_edit()" data-role="button" data-theme="b">수정</a></div>
			<div class="ui-block-b"><a href='javascript:fn_aram_delete()' data-role="button" data-theme="b">삭제</a></div>
			<div class="ui-block-c"><a href='javascript:fn_aram_list()' data-role="button" data-theme="b">목록</a></div>
		</div>

	</div>

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>

<script type="text/javaScript">

function fn_aram_list() {
    var varForm = document.getElementById("scrapVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/listScrap.mdo";
    varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("scrapVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/editScrap.mdo";
    varForm.submit();			
}

function fn_aram_delete() {	
	jConfirm('삭제하시겠습니까?', '알림', 'a', function(r) {
		if(r){
		    var varForm = document.getElementById("scrapVO");
			varForm.action = "${pageContext.request.contextPath}/cop/scp/deleteScrap.mdo";
			varForm.submit();
		}
    });	
}

</script>

</body>
</html>