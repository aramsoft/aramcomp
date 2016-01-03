<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>방명록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>		  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
	document.board.pageIndex.value = pageNo; 
	document.board.action = "${pageContext.request.contextPath}/cop/bbs/selectGuestList.mdo";
	document.board.submit();
}

function fn_aram_selectForupdt(nttId, nttCn, ntcrNm) {

	document.board.nttId.value = nttId;

	document.registForm.nttCn.value = nttCn;
	document.registForm.ntcrNm.value = ntcrNm;

	document.registForm.flag.value="update";
	$.mobile.changePage($('#RegistPage'));
}

function fn_aram_deleteGuestList(nttId) {
	jConfirm("<spring:message code='common.delete.msg' />", '삭제', 'a', function(r) {
		if(r) {
			document.board.nttId.value = nttId;
			document.board.action = "${pageContext.request.contextPath}/cop/bbs/deleteGuestList.mdo";
			document.board.submit();
		}
	});
}

function fn_aram_insert_guestList() {
	
	document.board.nttCn.value = document.registForm.nttCn.value;
	document.board.ntcrNm.value = document.registForm.ntcrNm.value;
	
	if (!validateBoardVO(document.board)){
		return;
	}

	if("insert" == document.registForm.flag.value){
		document.board.action = "${pageContext.request.contextPath}/cop/bbs/insertGuestList.mdo";
		document.board.submit();
	}else{
		document.board.action = "${pageContext.request.contextPath}/cop/bbs/updateGuestList.mdo";
		document.board.submit();
	}
}

function fn_aram_registGuest() {
	
	document.registForm.flag.value="insert";
	document.registForm.nttCn.value="";
	document.registForm.reset();
	$.mobile.changePage($('#RegistPage'));
}	
	
</script>
</head>

<body>

<!-- 모바일 페이지 start -->

<div data-role="page" id="listPage">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>
		<h1>방명록</h1> 
		<a href="javascript:fn_aram_registGuest();" data-icon="plus" class="ui-btn-right">등록</a> 
	</div>
	<!-- header end -->

	<!-- content start -->
	<div data-role="content">
		<form:form commandName="boardVO" method="post">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			<input name="bbsId" type="hidden" value="<c:out value='${boardVO.bbsId}'/>"/>
			<input name="nttId" type="hidden" value='<c:out value="${boardVO.nttId}" />' />
			<input name="nttSj" type="hidden" value='<spring:message code="cop.guestList.subject" />' />
			<input name="ntceBgnde" type="hidden" value="10000101">
			<input name="ntceEndde" type="hidden" value="99991231">
			
			<input name="nttCn" type="hidden" value="">
			<input name="ntcrNm" type="hidden" value="">
			
			<input type="hidden" name="password" value="dummy">	<!-- validator 처리를 위해 지정 -->
			
			<c:forEach var="result" items="${resultList}" varStatus="status">        
				<div class="com-commentList">
					<p>
						<span>
							<c:choose>
						    	<c:when test="${not empty result.ntcrNm}">
						    		<b><c:out value="${result.ntcrNm}" /></b>&nbsp;
						    	</c:when>
						    	<c:otherwise>
						    		<b><c:out value="${result.frstRegisterNm}" /></b>&nbsp;
						    	</c:otherwise>
						    </c:choose>
						</span> 
						<span class="uss-txtDate"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</p>
		            <p class="com-delete">
		                 <a href='javascript:fn_aram_deleteGuestList("${result.nttId}");' data-ajax="false"><span>삭제</span></a>
		            </p>
		            <p class="com-modify">
		            	 <a href='javascript:fn_aram_selectForupdt("${result.nttId}", "${result.nttCn}", "${result.ntcrNm}");' data-ajax="false"><span>수정</span></a>
		            </p>
					<c:if test="${anonymous == 'true'}">
					    <dl class="com-egovCommentPw">
					    	<dt>댓글 삭제하기</dt>
					    	<dd>비밀번호 <input name="testPassword" type="password" size="20" value="" maxlength="20"></dd> 
					    </dl>
				    </c:if>
	            	<ul class="com-commContent2">
	            		<li><c:out value="${result.nttCn}" /></li>
	            	</ul>
						
				</div>          
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
				  <li class="com-egovNodata">
               			<p align="center"> 자료가 없습니다. </p>
               	  </li>		 
			</c:if>
			
			<div id="pageNavi" class="com-egovPaging">
   				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage" jsFunction="fn_aram_linkPage" />
			</div>
		</form:form>
	</div>
	<!-- content end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>
<!-- 모바일 페이지 end -->

<!--**********************
    ** 방명록 등록 페이지 **
    ***********************-->
<div data-role="page" id="RegistPage">

	<div data-role="header" data-position="inline">
		<a href="#listPage"  data-icon="arrow-l">뒤로</a>
		<h1>방명록 쓰기</h1>
	</div>

	<div data-role="content" data-theme="c"  class="com-addContents">
		<form:form commandName="boardRegist" name="registForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="flag" value=""/>
		
		<dl data-role="fieldcontain" class="com-addTxt">
			<dt><label for="textarea">방명록</label></dt>
			<dd><textarea cols="40" rows="8" name="nttCn" id="nttCn" value="<c:out value='${boardVO.nttCn}'/>" class="com-textContents"></textarea></dd>
			<dt>작성자</dt>
			<dd><input type="text" name="ntcrNm" id="ntcrNm" maxlength="20" value="<c:out value='${boardVO.ntcrNm}'/>"/></dd>
		</dl>
		
		<fieldset class="ui-grid-a addBgBtn">
			<div class="ui-block-a">
            	<a href="javascript:fn_aram_insert_guestList()" data-role="button" data-theme="b">저장</a>
			</div>
            <div class="ui-block-b">  
                <input type="reset" value="초기화" data-theme="b" data-icon="refresh"/>
            </div>
		</fieldset>
		
		</form:form>
	</div>
	
	<!-- footer start -->
	<div data-role="footer"  data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>

</body>
</html>