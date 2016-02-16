<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>상담상세조회 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>
	
<!-- View start -->
<div id="view" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1 id="viewTitle">상담상세조회</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">
		<form:form commandName="cnsltManageVO"  method="post">
			<form:hidden path="cnsltId"  />
			<form:hidden path="writngPassword" />
			
			<!-- searchVO start -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword" />
			<form:hidden path="searchVO.pageIndex" />
			<!-- searchVO end -->
			
			<div class="qus">
				<span class="ic_result">qustion</span>
				<strong><c:out value="${cnsltManageVO.cnsltSj}" /></strong>
				<p>${fn:replace(cnsltManageVO.cnsltCn , crlf , '<br/>')}</p>
				<span class="time">
				<span class="uss-txtBlue">
					<c:choose>
				 		<c:when test="${cnsltManageVO.othbcAt == 'Y'}">		
							공개
						</c:when>
						<c:when test="${cnsltManageVO.othbcAt == 'N'}">
							비공개
						</c:when>
						<c:otherwise>
							내용만 공개
						</c:otherwise>
					</c:choose>
				</span>
				<span class="uss-txtBlack"><c:out value="${cnsltManageVO.wrterNm}" /></span>
				<span class="uss-txtBlack"><c:out value="${fn:substring(cnsltManageVO.writngDe, 0, 10)}" /></span>
				조회&nbsp;<span class="uss-txtCommon"><c:out value="${cnsltManageVO.inqireCo}" /></span>
				</span>
				<c:if test="${cnsltManageVO.othbcAt != 'C'}">
					<ul class="qusDetail">
						<li><span>이메일</span><span class="txtDeRight"><c:out value="${cnsltManageVO.emailAdres}" /></span></li>
						<li><span>이메일답변여부</span><span class="txtDeRight"><c:out value='${cnsltManageVO.emailAnswerAt == "Y" ? "Yes" : "No"}' /></span></li>
						<li><span>전화번호</span><span class="txtDeRight"><c:out value="${cnsltManageVO.areaNo}-${cnsltManageVO.middleTelno}-${cnsltManageVO.endTelno}" /></span></li>
						<li><span>휴대폰전화번호</span>
							<span class="txtDeRight">
							<c:if test='${cnsltManageVO.firstMoblphonNo ne ""}'>
								 <c:out value="${cnsltManageVO.firstMoblphonNo}-${cnsltManageVO.middleMbtlnum}-${cnsltManageVO.endMbtlnum}" />
							</c:if>
							</span>
						</li>
						<li><span>진행처리상태</span><span class="txtDeRight"><c:out value="${cnsltManageVO.qnaProcessSttusCodeNm }" /></span></li>
					</ul>
				</c:if>			
			</div>
			
			<c:if test='${cnsltManageVO.qnaProcessSttusCode == 3}'>
				<div class="ans">
					<span class="ic_result result_ans">answer</span>
						${fn:replace(cnsltManageVO.managtCn , crlf , '<br>')}
					<span class="time">
					<span class="uss-txtBlack"><c:out value="${cnsltManageVO.emplyrNm}" /></span>
					<span class="uss-txtBlack"><c:out value="${cnsltManageVO.managtDe}" /></span>
					<span class="uss-txtCommon"><c:out value="${cnsltManageVO.orgnztNm}" /></span>
					</span>
					<ul class="ansDetail">
						<li><span>전화번호</span><span class="txtDeRight"><c:out value="${fn:substring(cnsltManageVO.offmTelno, 0, 12)}" /></span></li>
						<li><span>이메일</span><span class="txtDeRight"><c:out value="${cnsltManageVO.aemailAdres}" /></span></li>							
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
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- view end -->
	
<script type="text/javaScript" >

function fn_password_confirm(url, msg) { 
      
    jPassword("비밀번호 입력", "알림", "a", function(writngPassword){
		if(writngPassword != null) {
		    var varForm = document.getElementById("cnsltManageVO");
		    varForm.writngPassword.value = writngPassword;
				
			$.getJSON("${pageContext.request.contextPath}/uss/olp/cns/confirmCnsltPasswordJson.mdo", $("#cnsltManageVO").serialize(), function(json){
					
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
			var url = "${pageContext.request.contextPath}/uss/olp/cns/deleteCnslt.mdo";
			var msg = "삭제";
			fn_password_confirm(url, msg);
		}
	});
}

function fn_aram_edit() {
	var url = "${pageContext.request.contextPath}/uss/olp/cns/editCnslt.mdo";
	var msg = "수정";
	fn_password_confirm(url, msg);
}

function fn_aram_list() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/listCnslt.mdo";
    varForm.submit();
}

</script>
       
</body>
</html>

