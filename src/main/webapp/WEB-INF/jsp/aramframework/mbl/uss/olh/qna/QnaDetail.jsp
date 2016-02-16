<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Q&amp;A 상세조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>
		
</head>

<body>

<div id="detail" data-role="page">
	
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">		
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1 id="viewTitle">Q&amp;A 상세조회</h1>
	</div>
	<!-- header end -->

	<!-- contents start -->
	<div data-role="content" class="com-copContent">

		<form:form commandName="qnaManageVO" method="post">
			<form:hidden path="qaId"  />
			<form:hidden path="writngPassword"  />
					
			<!-- searchVO start -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword"  />
			<form:hidden path="pageIndex"  />
			<!-- searchVO end -->
			
			<div class="qus">
				<span class="ic_qa">qustion</span>
				<strong><c:out value="${qnaManageVO.qestnSj}" /></strong>
				<p>${fn:replace(qnaManageVO.qestnCn , crlf , '<br>')}</p>
				<span class="time">
				<span class="uss-txtBlack"><c:out value="${qnaManageVO.wrterNm}" /></span>
				<span class="uss-txtBlack"><c:out value="${fn:substring(qnaManageVO.writngDe, 0, 4)}-${fn:substring(qnaManageVO.writngDe, 4, 6)}-${fn:substring(qnaManageVO.writngDe, 6, 8)}" /></span>
				조회&nbsp;<span class="uss-txtBlack"><c:out value="${qnaManageVO.inqireCo}" /></span>
				</span>
				<ul class="qusDetail">
					<li><span>전화번호</span> <span class="txtDeRight"><c:out value="${qnaManageVO.areaNo}-${qnaManageVO.middleTelno}-${qnaManageVO.endTelno}" /></span></li>
					<li><span>이메일</span> <span class="txtDeRight"><c:out value="${qnaManageVO.emailAdres}" /></span></li>
					<li><span>이메일답변여부</span> <span class="txtDeRight"><c:out value='${qnaManageVO.emailAnswerAt == "Y" ? "Yes" : "No"}' /></span></li>
					<li><span>진행처리상태</span><span class="txtDeRight"><c:out value="${qnaManageVO.qnaProcessSttusCodeNm}" /></span></li>
				</ul>
			</div>
			<c:if test="${qnaManageVO.qnaProcessSttusCode == '3'}">
			<div class="ans">
				<span class="ic_qa pos">answer</span>
					${fn:replace(qnaManageVO.answerCn , crlf , '<br>')}
				<span class="time">
				<span class="uss-txtBlack"><c:out value="${qnaManageVO.emplyrNm}" /></span>
				<span class="uss-txtBlack"><c:out value="${fn:substring(qnaManageVO.answerDe, 0, 4)}-${fn:substring(qnaManageVO.answerDe, 4, 6)}-${fn:substring(qnaManageVO.answerDe, 6, 8)}" /></span>
				<span class="uss-txtBlack"><c:out value="${qnaManageVO.orgnztNm}" /></span>
				</span>
				<ul class="ansDetail">
					<li><span>전화번호</span> <span class="txtDeRight"><c:out value="${qnaManageVO.offmTelno}" /></span></li>
					<li><span>이메일</span> <span class="txtDeRight"><c:out value="${qnaManageVO.aemailAdres}" /></span></li>							
					<li><span>이메일답변여부</span> <span class="txtDeRight"><c:out value='${qnaManageVO.emailAnswerAt == "Y" ? "Yes" : "No"}' /></span></li>
				</ul>
			</div>	
			</c:if>
			
			<div class="ui-grid-b">
				<div class="ui-block-a"><a href="javascript:fn_aram_edit()" data-role="button" data-theme="b" data-ajax="false">수정</a></div>
				<div class="ui-block-b"><a href='javascript:fn_aram_delete()' data-role="button" data-theme="b">삭제</a></div>
				<div class="ui-block-c"><a href='javascript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
			</div>
				
		</form:form>	
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer"  data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- 게시판 View end -->	
				
<script type="text/javaScript" >

function fn_password_confirm(url) {
	
    jPassword("작성글 비밀번호 입력", "알림", "a", function(writngPassword){
    	if(writngPassword != null) {
       	    var varForm = document.getElementById("qnaManageVO");
    	    varForm.writngPassword.value = writngPassword;
	
    	    $.getJSON("${pageContext.request.contextPath}/uss/olh/qna/confirmQnaPasswordJson.mdo", $("#qnaManageVO").serialize(), function(json){
	
				if(json.passwordConfirmAt == "Y") {
					varForm.action = url;
					varForm.submit();
				}
				else {
					jAlert("비밀번호 오류", "알림", "a");
				}
	
			});
		}
  	});
}

function fn_aram_delete() {
	jConfirm('삭제하시겠습니까?', '알림', 'a', function(r) {
		if(r) {
			var url = "${pageContext.request.contextPath}/uss/olh/qna/deleteQna.mdo";
			fn_password_confirm(url);
		}
	});
}

function fn_aram_edit() {
	var url = "${pageContext.request.contextPath}/uss/olh/qna/editQna.mdo";
	fn_password_confirm(url);
}

function fn_aram_list() {
    var varForm = document.getElementById("qnaManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.mdo";
    varForm.submit();
}
	
</script>

</body>
</html>