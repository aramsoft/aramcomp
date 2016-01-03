<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>주소록 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

</head>

<body>
	
<div id="view" data-role="page">

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1>주소록 수정</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">
		<form:form commandName="adressBookVO" method="post">
			<form:hidden path="adbkId" />
			<form:hidden path="userIds" />
			<input type="hidden" name="checkWord" value="">
			<input type="hidden" name="checkCnd" value="">
			
			<!-- 검색조건 유지 -->
			<form:hidden path="searchCondition" />
			<form:hidden path="searchKeyword" />
			<form:hidden path="pageIndex" />
			<!-- 검색조건 유지 -->
		
			<div data-inline="true">
				<dl class="uss-registOk">
					<dt><label for="adbkNm"><strong>주소록명</strong></label></dt>
					<dd>
						<c:if test="${writer == true}">
		        		<input name="adbkNm" type="text" value='<c:out value="${adressBookVO.adbkNm}" />'>
		        		</c:if>
		        		<c:if test="${writer != true}"> <c:out value="${adressBookVO.adbkNm}" /> </c:if>
					</dd>
					
					<dt><label for="othbcScope"><strong>공개범위</strong></label></dt>
					<dd>
						<fieldset data-role="controlgroup" data-type="horizontal"  data-inline="true"> 				
							<input type="radio" name="othbcScope" id="othbcScope1" value="개인" <c:if test="${adressBookVO.othbcScope == '개인'}"> checked="checked"</c:if> <c:if test="${writer != true}"> disabled </c:if> />
							<label for="othbcScope1">개인</label>
							<input type="radio" name="othbcScope" id="othbcScope2" value="부서" <c:if test="${adressBookVO.othbcScope == '부서'}"> checked="checked"</c:if> <c:if test="${writer != true}"> disabled </c:if> />
							<label for="othbcScope2">부서</label> 
							<input type="radio" name="othbcScope" id="othbcScope4" value="전체" <c:if test="${adressBookVO.othbcScope == '전체'}"> checked="checked"</c:if> <c:if test="${writer != true}"> disabled </c:if> />
							<label for="othbcScope4">전체</label>
						</fieldset>
					</dd>
					<dt><label for="trgetOrgnztId"><strong>공개부서선택</strong></label></dt>
					<dd>
						<form:select path="trgetOrgnztId" data-role="none">
				            <form:options items="${orgnztList}" itemValue="code" itemLabel="codeNm"/>
				        </form:select>
					</dd>
					
					<c:if test="${writer == true}">
					<dt><label for="adbkUser"><strong>구성원</strong></label></dt>
					<dd>
						<div class="uss-typ3"><input name="adbkUser" type="text" readonly="true" /></div>
						<div class="uss-typ4"><a href="javascript:fn_aram_get_user();" data-role="button" data-icon="search" data-iconpos="notext" data-ajax="false">조회</a></div>
					</dd>
					</c:if>
				</dl>
			</div>

			<c:forEach var="result" items="${adressBookVO.adbkMan}" varStatus="status">
				<ul data-role="none" class="memberList">
					<li>
						<div class="memLeft">
						<c:if test="${result.ncrdId == '' || result.ncrdId == NULL}"> 
							<p><span class="uss-txtId"><c:out value="${result.emplyrId}" /></span>
							<span class="uss-txtName"><c:out value="${result.nm}"/></span>
							<span class="uss-txtEmail">[<c:out value="${result.emailAdres}"/>]</span></p>
						</c:if>
						<c:if test="${result.emplyrId == '' || result.emplyrId == NULL}">
							<p><span class="uss-txtId"><c:out value="${result.ncrdId}" /></span>
							<span class="uss-txtName"><c:out value="${result.nm}"/></span>
							<span class="uss-txtEmail">[<c:out value="${result.emailAdres}"/></span>]</p>
						</c:if>
							<p><span class="uss-txtPhone">집 : <c:out value="${result.homeTelno}"/></span><span class="uss-txtPhone">휴대폰 : <c:out value="${result.moblphonNo}"/></span></p>
							<p><span class="uss-txtPhone">회사 : <c:out value="${result.offmTelno}"/></span><span class="uss-txtPhone">팩스 : <c:out value="${result.fxnum}"/></span></p>
						</div>
						<div class="memRight">
						<c:if test="${writer == true}">	 
							<c:if test="${result.ncrdId == '' || result.ncrdId == NULL}"> 
								<a href="javascript:fn_aram_delete_user('<c:out value="${result.emplyrId}"/>');" data-role="button" data-icon="delete" data-iconpos="notext" data-ajax="false">삭제</a>
							</c:if>
							<c:if test="${result.emplyrId == '' || result.emplyrId == NULL}">
								<a href="javascript:fn_aram_delete_user('<c:out value="${result.ncrdId}"/>');" data-role="button" data-icon="delete" data-iconpos="notext" data-ajax="false">삭제</a>
							</c:if>
						</c:if>
						</div>
					</li>
				</ul>
			</c:forEach>
		</form:form>
		
		<c:if test="${writer == true}">
			<div class="ui-grid-a addBtn">
				<div class="ui-block-a"><a href="javascript:fn_aram_update();" data-role="button" data-theme="b">저장</a></div>
				<div class="ui-block-b"><a href="javascript:fn_aram_list();" data-role="button" data-theme="b">목록</a></div>					
			</div>
		</c:if>
		<c:if test="${writer != true}">
			<div class="com-addBgBtn">
				<a href="javascript:fn_aram_list();" data-role="button" data-theme="b">목록</a>					
			</div>
		</c:if>
		
	</div>

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
			
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="adressBookVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){				
    var varForm = document.getElementById("adressBookVO");
    varForm.action = "${pageContext.request.contextPath}/cop/adb/listAdressBook.mdo";
    varForm.submit();
}

function fn_aram_update(){		
	var varForm = document.getElementById("adressBookVO");
	
	if(!validateAdressBookVO(varForm)) {
		return;
	}
	
	varForm.action = "${pageContext.request.contextPath}/cop/adb/updateAdressBook.mdo";
	varForm.submit();
}	

function fn_aram_delete_user(userId){	
    var varForm = document.getElementById("adressBookVO");
    varForm.checkWord.value = userId;
    varForm.checkCnd.value = "update";
    varForm.action = "${pageContext.request.contextPath}/cop/adb/deleteAdressBookUser.mdo";
    varForm.submit();	
}

function fn_aram_get_user(){	
    var varForm = document.getElementById("adressBookVO");
    varForm.checkCnd.value = "update";			
    varForm.action = "${pageContext.request.contextPath}/cop/adb/listUserPopup.mdo";
    varForm.submit();
}
</script>

</body>
</html>

