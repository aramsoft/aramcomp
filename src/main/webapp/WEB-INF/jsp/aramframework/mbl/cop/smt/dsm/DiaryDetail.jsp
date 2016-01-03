<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일지관리상세보기 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
		
</head>

<body>
	
<!-- View start -->
<div id="detail" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1 id="viewTitle">일지관리상세보기</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">
			
		<form:form commandName="diaryManageVO" method="post">
			<form:hidden path="diaryId" />

			<!-- 검색조건 유지 -->
			<form:hidden path="searchCondition" />
			<form:hidden path="searchKeyword" />
			<form:hidden path="pageIndex" />
			<!-- 검색조건 유지 -->
			
			<ul class="uss-hpcDetail">
				<li>	
					<span class="uss-tit">일정명 </span>
					<span class="uss-con">
			        	<c:out value="${diaryManageVO.schdulNm}" />
					</span>
				</li>
				<li>	
					<span class="uss-tit">일지명 </span>
					<span class="uss-con">
		        		<c:out value="${diaryManageVO.diaryNm}" />
					</span>
				</li>
				<li>
					<span class="uss-tit">지시사항 </span>
					<span class="uss-contents">
						${fn:replace(diaryManageVO.drctMatter , crlf , '<br>')}
					</span>
				</li>
				<li>
					<span class="uss-tit">특이사항</span>
					<span class="uss-contents">
						${fn:replace(diaryManageVO.partclrMatter , crlf , '<br>')}
					</span>
				</li>
				<li>
					<span class="uss-tit">진척률 </span>
					<span class="uss-con">								
						<c:out value="${diaryManageVO.diaryProcsPte}" />%
		      		</span>
				</li>
			</ul>
		</form:form>
			
		<div class="ui-grid-b">
			<div class="ui-block-a"><a href="JavaScript:fn_aram_edit();" data-role="button" data-theme="b">수정</a></div>
			<div class="ui-block-b"><a href='JavaScript:fn_aram_delete();' data-role="button" data-theme="b">삭제</a></div>
			<div class="ui-block-c"><a href='JavaScript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
		</div>
			
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
						
</div>
<!-- view end -->
	
<script type="text/javaScript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.mdo";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/editDiary.mdo";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("diaryManageVO");
	jConfirm('삭제 하시겠습니까?', 'EgovFrame', 'a', function(r) {
	
	    if(r){	   			
	    	varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/deleteDiary.mdo";
	    	varForm.submit();
		}else{
			varForm.cmd.value = '';
		}
	});
}
	
</script>

</body>
</html>

