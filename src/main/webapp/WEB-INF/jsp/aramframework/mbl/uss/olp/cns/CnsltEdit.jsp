<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>상담내역 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

</head>

<body>
	
<div id="edit" data-role="page">

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_detail();" data-icon="arrow-l" >뒤로</a>
	    <h1>상담내역 수정</h1>
	</div>
	
	<div data-role="content" class="com-ussContent">

		<form:form commandName="cnsltManageVO" method="post">
			<form:hidden path="cnsltId" />
			
			<!-- searchVO start -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword" />
			<form:hidden path="searchVO.pageIndex" />
			<!-- searchVO end -->
			
			<div data-inline="true">
				<dl class="uss-registOk">
					<dt><label for="name"><strong>작성자명</strong> </label></dt>
					<dd><form:input path="wrterNm" maxlength="30" /></dd>
					
					<dt><label for="writingPassword"><strong>비밀번호</strong> </label></dt>
					<dd><form:password path="writngPassword" value="${cnsltManageVO.writngPassword}" maxlength="30" autocomplete="off" /></dd>
					
					<dt><label for="useYn"><strong>공개여부</strong> </label></dt>
					<dd><fieldset data-role="controlgroup" data-type="horizontal">				
							<form:radiobutton path="othbcAt" value="Y" label="공개" />
							<form:radiobutton path="othbcAt" value="N" label="비공개" /> 
							<form:radiobutton path="othbcAt" value="C" label="내용공개" />
						</fieldset>
					</dd>
					
					<dt><label for="useYn"><strong>이메일</strong> </label></dt>
					<dd><form:input path="emailAdres" maxlength="30" /></dd>
					
					<dt><label for="useYn"><strong>이메일답변여부</strong></label></dt>
					<dd>
						<fieldset data-role="controlgroup" data-type="horizontal"  data-inline="true"> 	
							<form:radiobutton path="emailAnswerAt" value="Y" label="Yes"/>
							<form:radiobutton path="emailAnswerAt" value="N" label="No"/>
						</fieldset>
					</dd>
					
					<dt><label for="areaNo"><strong>전화번호</strong> </label></dt>
					<dd><fieldset data-role="controlgroup" data-type="horizontal">
							<div class="uss-phone">
								<div class="uss-wid2"><form:input path="areaNo" size="4" maxlength="4" title="전화번호(지역)" /></div>
								<div class="uss-wid3"><form:input path="middleTelno" size="4" maxlength="4" title="전화번호(국번)" /></div>
								<div class="uss-wid3"><form:input path="endTelno" size="4" maxlength="4"  title="전화번호(지번)" /></div>
							</div>
					    </fieldset>
					</dd>
					
					<dt><label for="description"><strong>휴대전화번호</strong></label></dt>
					<dd><fieldset data-role="controlgroup" data-type="horizontal">
							<div class="uss-phone">
								<div class="uss-wid2"><form:input path="firstMoblphonNo" type="text" size="5"  maxlength="5" title="휴대폰전화번호(앞번)" /></div>
								<div class="uss-wid3"><form:input path="middleMbtlnum" type="text" size="5"  maxlength="5" title="휴대폰전화번호(국번)" /></div>
								<div class="uss-wid3"><form:input path="endMbtlnum" type="text" size="5"  maxlength="5" title="휴대폰전화번호(지번)" /></div>
							</div>
						</fieldset></dd>
					
					<dt><label for="useYn"><strong>상담제목</strong> </label></dt>
					<dd><form:input path="cnsltSj" size="70" maxlength="70" title="상담제목"/></dd>
					
					<dt><label for="useYn"><strong>상담내용</strong> </label></dt>
					<dd><form:textarea path="cnsltCn" cols="300" rows="20" cssClass="txArea" title="상담내용"/></dd>
				</dl>
			</div>
			<form:hidden path="managtCn" />
			<form:hidden path="managtDe" />
			<form:hidden path="atchFileId" />
		</form:form>
		
		<div class="ui-grid-a">	
			<div class="ui-block-a"><a href="javascript:fn_aram_update();" data-role="button" data-theme="b" data-ajax="false">수정</a></div>
			<div class="ui-block-b"><a href='javascript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>				
		</div>
	</div>
	
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
			
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cnsltManageVO" staticJavascript="false" xhtml="true" cdata="false"/> 

<script type="text/javaScript">
	
function fn_aram_update() {
    var varForm = document.getElementById("cnsltManageVO");
		
	if(!validateCnsltManageVO(varForm)) {
		return;
	}
	
	varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/updateCnslt.mdo";			
	varForm.submit();
}

function fn_aram_list() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/listCnslt.mdo";
    varForm.submit();
}

function fn_aram_detail() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/detailCnslt.mdo";
    varForm.submit();
}

</script>

</body>
</html>

