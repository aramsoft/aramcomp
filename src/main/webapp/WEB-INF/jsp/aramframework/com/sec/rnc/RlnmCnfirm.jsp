<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : RlnmCnfirm.jsp
  * @Description : 주민번호 실명확인 JSP
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>실명인증</title>

<!-- base target="_self" / -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript" defer="defer">

function fnIhidnumCnfirm(){
	return;
	
	//현재 화면의 입력정보로 재호출하여 확인..
	document.cnfirmForm.action = "${pageContext.request.contextPath}/sec/rnc/confirmRlnm.do";
    document.cnfirmForm.submit();
}

function fnGpinMove(){
	return;

	//GPIN 실명인증 화면으로 이동한다.
    document.cnfirmForm.action = "${pageContext.request.contextPath}/sec/rnc/confirmRlnmPin.do";
    document.cnfirmForm.result.value="";
    document.cnfirmForm.submit();
}

function fnNextMove(){

	// 상담화면에서 실명인증을 위한 파라미터
	var ls_cnsltName 	= document.cnfirmForm.nextUrl.value;

	//다음단계로 이동한다. parameter로 받아서 설정
	//alert("!!!"+"${pageContext.request.contextPath}${nextUrl}'/>");
	document.cnfirmForm.result.value = "success.user.rlnmCnfirm"; 	// for debugging only
	
	if(document.cnfirmForm.result.value=="success.user.rlnmCnfirm") {

		// 상담 및 Q&A를 위한 코드
		if (ls_cnsltName == "C")	{

			var opener = window.dialogArguments;

			// 부모화면으로 실명을 넘긴다.
			ls_realname = document.cnfirmForm.realname.value;
			opener.document.getElementById("realname").value = ls_realname;

			// 부모화면으로 결과값을 True 넘긴다.
			window.returnValue = true;
			window.close();

		} else	{
    		document.cnfirmForm.action = "${pageContext.request.contextPath}${nextUrl}'/>";
    		document.cnfirmForm.submit();
		}

	}else{
    	alert("<spring:message code="${result}" />");
	}
}

</script>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>주민등록 실명확인</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fnIhidnumCnfirm(); return false;"><spring:message code="button.realname" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fnGpinMove(); return false;"><spring:message code="button.moveToGpin" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fnNextMove(); return false;"><spring:message code="${nextUrlName}" /></a></span>
	</div>
</div>

<form name="cnfirmForm" method="post" action="">
	<input type="hidden" name="sbscrbTy" value="${sbscrbTy}">
	<!-- GPIN 실명인증 으로 가기위한 초기화값 -->
	<input type="hidden" name="realName" value="${realName}"><!-- 주민등록확인의 realname 변수와 구별  -->
	<!-- 실명인증을 재귀적으로 호출하는 경우 다음경로와 다음경로 버튼명을 셋팅함 -->
	<input type="hidden" name="nextUrl" value="${nextUrl}">
	<input type="hidden" name="nextUrlName" value="${nextUrlName}">

<table class="table-register" >
    <tr>
     	<td>
         	&nbsp;이름:<input type="text" name="realname" value="${realname}" title="이름입력"  />
         	&nbsp;주민등록번호 <input type="text" name="ihidnum" value="${ihidnum}" title="주민등록번호입력" readonly="readonly">(-없이 13자리)
            <!-- 사용자유형 --><input type="hidden" name="sbscrbTy_view" value="${sbscrbTy}">
            <!-- 결과코드 -->
            <input type="hidden" name="result" value="${result}">
            <input type="hidden" name="result_tmp" value="${result_tmp}">
            <br><c:if test="${not empty result}"><spring:message code="${result}" /></c:if>
            <br>
            <br>
     	</td>
    </tr>
    <tr>
     	<td>
     		* 입력하신 주민등록번호는  실명인증을 하는데만 이용되며 저장되지 않습니다<br>
     		* 개정 "주민등록법"에 의해 타인의 주민등록번호를 부정사용하는 자는 3년 이하의 징역 또는 1천만원. 이하의 벌금이 부과될 수 있습니다.
    		 관련법률: 주민등록법 제37조(벌칙) 제9호(시행일 2006.09.24)
     		만약, 타인의 주민번호를 도용하여 온라인 회원 가입을 하신 이용자분들은 지금 즉시 명의 도용을 중단하시길 바랍니다.
     	</td>
    </tr>
    <tr>
     	<td>
			<B>테스트중에는 실명확인하지 않습니다. - 그냥 가입신청버튼 누르세요!!!</B>     	
		</td>
    </tr>
</table>
</form>
<!-- content end -->

</DIV>
</body>
</html>
