<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%pageContext.setAttribute("useComment", "false"); %> 
<%pageContext.setAttribute("crlf", "\r\n"); %> 

<html> 
<head>
<title><c:out value='${boardVO.boardMasterVO.bbsNm}'/> - 글조회</title> 
<meta name="viewport" content="width=device-width, initial-scale=1">
      
<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>		  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
 
<script type="text/javascript">
	
function fn_aram_list_notice() {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);

	varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/" + bbsId + "/articles";
    varForm.submit();	
}

function fn_aram_edit_notice() {
    var varForm = document.getElementById("boardVO");

    if ("<c:out value='${anonymous}'/>" == "true" 
			&& document.getElementById("password").value == '') {
		jAlert('등록시 사용한 비밀번호를 입력해 주세요.','알림', 'a');
		varForm.password.focus();
		return;
	}
	
	if ("<c:out value='${anonymous}'/>" == "true"){
		varForm.password.value = document.getElementById("password").value;
	}
	
	varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/editBoardArticle.mdo";
	varForm.submit();	
}

function fn_aram_delete_notice() {
    var varForm = document.getElementById("boardVO");

    if ("<c:out value='${anonymous}'/>" == "true" 
			&& document.getElementById("password").value == '') {
		jAlert('등록시 사용한  비밀번호를 입력해 주세요.','알림', 'a');
		varForm.password.focus();
		return;
	}

	if ("<c:out value='${anonymous}'/>" == "true"){
		varForm.password.value = document.getElementById("password").value;
	}
	
	jConfirm('삭제하시겠습니까?', '알림', 'a', function(r) {
		if(r){
			varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/deleteBoardArticle.mdo";
			varForm.submit();
		}
	});		
}

function fn_aram_reply_notice() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs${prefix}/replyBoardArticle.mdo";
    varForm.submit();
}

function init()
{
	var message = "${msg}";
	var subMsg = "${subMsg}";
	if (message != "") {
		jAlert(message,'알림', 'a');
		message = ""; 
	}
	if (subMsg != ""){
		jAlert(subMsg,'알림', 'a');
	}
}
	
</script>

<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useComment == 'true'}">
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="commentVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">

function fn_aram_reset_comment() {
    var varForm = document.getElementById("commentVO");
    varForm.reset();
}

function fn_aram_regist_comment() {
    var varForm = document.getElementById("commentVO");
	<c:if test="${anonymous == 'true'}">
		varForm.wrterNm.value = "";
	</c:if>
	varForm.commentNo.value = "";
	varForm.commentCn.value = "";
	document.location.href="#CommentPage"
}				

function fn_aram_edit_comment(commentNo, wrterNm, frstRegisterNm, commentCn, index) {
    var varForm = document.getElementById("commentVO");
    var varFormBoard = document.getElementById("boardVO");
	if("" == wrterNm){
		varForm.wrterNm.value = frstRegisterNm;
	}else{
		varForm.wrterNm.value = wrterNm;
	}

	<c:if test="${anonymous == 'true'}">
		var password;
		if (typeof(varFormBoard.testPassword.length) == 'undefined') {
			password = varFormBoard.testPassword.value;
		} else {
			password = varFormBoard.testPassword[index].value;
		}
		varForm.commentPassword.value = password;
	</c:if>
		
	varForm.commentNo.value = commentNo;
	varForm.commentCn.value = commentCn;
	document.location.href="#CommentPage";
}

function fn_aram_insert_comment() {
    var varForm = document.getElementById("commentVO");
    var varFormBoard = document.getElementById("boardVO");
    
	if (!validateCommentVO(varForm)){
		return;
	}

	<c:if test="${anonymous == 'true'}">

		if ("<c:out value='${anonymous}'/>" == "true" && varForm.wrterNm.value == '') {
			jAlert('작성자를 입력해 주세요.','알림', 'a');
			return;
		}
		
		if ("<c:out value='${anonymous}'/>" == "true" && varForm.commentPassword.value == '') {
			jAlert('비밀번호를 입력해 주세요.','알림', 'a');
			commentPassword.focus();
			return;
		}

	</c:if>
	
	if(varForm.commentNo.value != ""){
		varForm.action = "${pageContext.request.contextPath}/cop/cmt${prefix}/updateComment.mdo";
	}else{
		varForm.action = "${pageContext.request.contextPath}/cop/cmt${prefix}/insertComment.mdo";
	}
	varForm.submit();
}

function fn_aram_delete_comment(commentNo, index) {
    var varForm = document.getElementById("commentVO");
    var varFormBoard = document.getElementById("boardVO");

    <c:if test="${anonymous == 'true'}">
		var password;
		if (typeof(varFormBoard.testPassword.length) == 'undefined') {
			password = varFormBoard.testPassword;
		} else {
			password = varFormBoard.testPassword[index];
		}
		if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
			jAlert('등록시 사용한 비밀번호를 입력해 주세요.','알림', 'a');
			password.focus();
			return;
		}
		varForm.commentPassword.value = password.value;
	</c:if>

	jConfirm('삭제하시겠습니까?', '알림', 'a', function(r) {
		if(r){
			varForm.modified.value = "true";
			varForm.commentNo.value = commentNo;
			varForm.action = "${pageContext.request.contextPath}/cop/cmt${prefix}/deleteComment.mdo";
			varForm.submit();
		}
	});	
}

</script>

</c:if>

<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useScrap == 'true'}">
<script type="text/javascript">
function fn_aram_add_scrap() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/cop/scp/registScrap.mdo";
    varForm.submit();			
}
</script>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->

</head>

<body onload="init()">

<!-- 메인 페이지 -->
<div data-role="page" >

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list_notice();" data-icon="arrow-l" >뒤로</a>
		<h1>게시글 상세보기</h1> 
	</div>
	<!-- header end -->

	<!-- contents start -->
	<div data-role="content" class="com-copContent">
		<div class="com-notSubject">
			<span><strong><c:out value="${boardVO.nttSj}" /></strong></span>
		</div>
		
		<div class="com-notDaNahit">
			<span>
				<c:choose>
				<c:when test="${boardVO.ntcrNm == ''}">
					<c:out value="${boardVO.frstRegisterNm}" />
				</c:when>
				<c:otherwise>
				    <c:out value="${boardVO.ntcrNm}" />
				</c:otherwise>
				</c:choose>
			</span>
			<span class="line">|</span>
			<span><fmt:formatDate value="${boardVO.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span>
			<span class="line">|</span>
			<span class="coHit"><c:out value="${boardVO.rdcnt}" /></span>
		</div>

		<div class="com-notViewContents">
			<span><c:out value="${fn:replace(boardVO.nttCn , crlf , '<br>')}"  escapeXml="false"/></span>    
		</div>
		
		<div class="com-notViewContents">
			<c:if test="${not empty boardVO.atchFileId}">
			  	<c:if test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA02'}">
					<c:import url="/cmm/fms/selectImageFileInfs.mdo" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${boardVO.atchFileId}" />
					</c:import>				
			  	</c:if>
				<c:import url="/cmm/fms/selectFileInfs.mdo" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${boardVO.atchFileId}" />
				</c:import>
			</c:if>
        </div>
		
		<form:form commandName="boardVO"  method="post" action="">
			<form:hidden path="bbsId" />
			<form:hidden path="nttId" />
	
			<c:if test="${anonymous == 'true'}">
				<dl class="com-egovModify">
					<dt><label for="password">비밀번호</label></dt>
					<dd><input type="password" name="password" id="password" /></dd>
				</dl>
			</c:if>
	
			<c:if test="${useComment == 'true'}">
				<c:import url="/cop/bbs${prefix}/listComment.mdo" charEncoding="utf-8">
					<c:param name="type" value="body" />
				</c:import>
			</c:if>
	
			<!-- 검색조건 유지 -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword" />
			<form:hidden path="searchVO.pageIndex" />
			<!-- 검색조건 유지 -->
		</form:form>

		<div data-role="controlgroup" data-type="horizontal" style="text-align:center;" >
			<!-- 댓글 -->
			<c:if test="${useComment == 'true'}">
				<a href="javascript:fn_aram_regist_comment();" data-role="button" data-theme="b" >댓글</a>
			</c:if>
 		  <c:if test="${editAuthFlag == 'Y'}">
    		<c:if test="${boardVO.frstRegisterId == sessionUniqId}">
				<a href="javascript:fn_aram_edit_notice();" data-role="button" data-theme="b" >수정</a>
				<a href="javascript:fn_aram_delete_notice();" data-role="button" data-theme="b">삭제</a>
            </c:if>    
			<!-- 답글 -->
			<c:if test="${boardVO.boardMasterVO.replyPosblAt == 'Y'}">     
				<a href="javascript:fn_aram_reply_notice();" data-role="button" data-theme="b" >답글</a>
			</c:if>
			<!-- 스크랩 -->
			<c:if test="${useScrap == 'true'}"> 
				<a href="javascript:fn_aram_add_scrap();" data-role="button" data-theme="b" >스크랩</a>
			</c:if>
  		  </c:if>
			<a href="javascript:fn_aram_list_notice();" data-role="button" data-theme="b">목록</a>
		</div>

	</div>

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>
	
<c:if test="${useComment == 'true'}">
	
<!--**********************
    ** 댓글 등록 페이지 **
    ***********************-->
<div data-role="page" id="CommentPage">

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="#"  data-rel="back" data-icon="arrow-l">뒤로</a>
		<h1>댓글 쓰기</h1>
	</div>

	<div data-role="content" class="com-ussContent">
		<form id="commentVO" name="commentVO" method="post">
			<input type="hidden" name="nttId" value="<c:out value='${boardVO.nttId}'/>" />
			<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
			<input name="commentNo" type="hidden" value="">
			
			<input name="modified" type="hidden" value="false">
			
			<div data-role="fieldcontain" data-inline="true">
				<div class="uss-regist" data-inline="true">
					<label for="commentCn">댓글</label><br>
					<textarea cols="40" rows="8" name="commentCn" id="commentCn" class="com-textContents"></textarea>
				</div>
				<div class="uss-regist" data-inline="true">
					<label for="wrterNm">작성자</label><br>
					<input type="text" maxlength="20" name="wrterNm" id="wrterNm" value=""/>
				</div>
				<div class="uss-regist" data-inline="true">
				<c:if test="${anonymous == 'true'}">
					<label for="password">비밀번호</label><br>
					<input type="password" maxlength="20" name="commentPassword" id="commentPassword" />
				</c:if>
				<c:if test="${anonymous != 'true'}">
					<input type="hidden" name="commentPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
				</c:if>
				</div>
			</div>
	
			<div class="ui-grid-a addBgBtn">
				<div class="ui-block-a">
	            	<a href="javascript:fn_aram_insert_comment()" data-role="button" data-theme="b" data-icon="plus">저장</a>
				</div>
	            <div class="ui-block-b">  
	                <a href="javascript:fn_aram_reset_comment()" data-role="button" data-theme="b" data-icon="refresh">초기화</a>
	            </div>
			</div>	
			
		</form>
	</div>
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>

</c:if>
	
</body>
</html>