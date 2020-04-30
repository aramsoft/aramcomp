<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : RlnmPinCnfirm.jsp
  * @Description : 공공IPIN실명확인 JSP
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
<title>실명인증(GPIN)</title>

<!-- base target="_self" / -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

<script type="text/javascript" defer="defer">

function fnGpinCnfirm(){
	//현재 화면의 입력정보로 재호출하여 확인..
	//document.cnfirmForm.action = "${pageContext.request.contextPath}/sec/rnc/confirmRlnmPin.do";
	//핀인증화면을 팝업으로 띄운다.
	//document.cnfirmForm.action = "${pageContext.request.contextPath}/sec/rnc/callGPin.do";
    //jsp 팝업으로 띄운다.
    wWidth = 360;
    wHight = 120;
    wX = (window.screen.width - wWidth) / 2;
    wY = (window.screen.height - wHight) / 2;

    //open 형태로 호출
    //var w = window.open("${pageContext.request.contextPath}/PageLink.do?link=cmm/sec/rnc/CallGpin", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);

    //submit 형태로 호출
    var ww = window.open("#","_openWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
    document.cnfirmForm.action="${pageContext.request.contextPath}/PageLink.do?link=//sec/rnc/CallGpin";
    document.cnfirmForm.target="_openWin";
    document.cnfirmForm.submit();
}

function fnIhidnumMove(){
    //GPIN 실명인증 화면으로 이동한다.
    document.cnfirmForm.action = "${pageContext.request.contextPath}/sec/rnc/confirmRlnm.do";
    document.cnfirmForm.target="";
    document.cnfirmForm.submit();
}

function fnNextMove(){

    // 상담화면에서 실명인증을 위한 파라미터
    var ls_cnsltName    = document.cnfirmForm.nextUrl.value;

    // 상담화면에서 화면 호출시
    if (ls_cnsltName == "C")    {

        var opener = window.dialogArguments;

        // 부모화면으로 실명을 넘긴다.
        ls_realname = document.cnfirmForm.realname.value;

        opener.document.getElementById("realname").value = ls_realname;

        // 부모화면으로 결과값을 True 넘긴다.
        window.returnValue = true;
        window.close();

    } else  {
        //다음단계로 이동한다. parameter로 받아서 설정
        //alert("!!!"+"${pageContext.request.contextPath}${nextUrl}'/>");
        if(document.cnfirmForm.result.value=="success.user.rlnmPinCnfirm"){
            document.cnfirmForm.action = "${pageContext.request.contextPath}${nextUrl}'/>";
            document.cnfirmForm.submit();
        }else{
            alert("<spring:message code="${result}" />");
        }
    }
}

</script>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>공공IPIN 실명확인</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><input type="submit" value="<spring:message code="button.realname" />" title="<spring:message code="button.realname" />" onclick="javascript:fnGpinCnfirm(); return false;"></span>
			<span class="button"><input type="submit" value="<spring:message code="button.moveToIhidnum" />" title="<spring:message code="button.moveToIhidnum" />" onclick="javascript:fnIhidnumMove(); return false;"></span>
			<span class="button"><a href="http://www.g-pin.go.kr/jsp/members/find_id.jsp" target="_blank"  title="새 창으로 이동">아이디찾기</a></span>
			<span class="button"><a href="http://www.g-pin.go.kr/jsp/members/find_pw.jsp" target="_blank"  title="새 창으로 이동">비밀번호 찾기</a></span>
			<span class="button"><a href="http://www.g-pin.go.kr/" target="_blank"  title="새 창으로 이동">공공IPIN센터</a></span>
			<span class="button"><input type="submit" value="<spring:message code="${nextUrlName}" />" title="<spring:message code="${nextUrlName}" />" onclick="javascript:fnNextMov(); return false;"></span>
		</span>
	</div>
</div>

<!-- content start -->
<form name="cnfirmForm" method="post" action="${pageContext.request.contextPath}/sec/rnc/confirmRlnm.do">
	<input type="hidden" name="sbscrbTy" value="${sbscrbTy}">
    <!-- 주민번호 실명인증 으로 가기위한 초기화값 -->
    <input type="hidden" name="ihidnum" value="${ihidnum}">
    <input type="hidden" name="realname" value="${realname}"><!-- ipin실명확인된 이름realName변수와 구별 -->
    <!-- 실명인증을 재귀적으로 호출하는 경우 다음경로와 다음경로 버튼명을 셋팅함 -->
    <input type="hidden" name="realName" value="${realName}">
    <input type="hidden" name="nextUrl" value="${nextUrl}">
    <input type="hidden" name="nextUrlName" value="${nextUrlName}">
    
<table class="table-register" >
    <tr>
    	<td>
            <input type="hidden" name="sbscrbTy_view" value="${sbscrbTy}">
            <input type="hidden" name="result" value="${result}">
            <br><c:if test="${not empty result}"><spring:message code="${result}" /></c:if>
            <br><c:if test="${not empty realName}">실명확인이름: ${realName} </c:if>
            <br>
        </td>
    </tr>
    <tr>
        <td>* 이용자의 개인정보를 보호하기 위해 웹사이트에 주민등록번호를  제공하지 않고 본인확인 하는 인터넷상의 개인식별번호 서비스입니다.
        <br> (gpin정보는 실명확인시 입력되는 정보로 설정되며 저장되지 않습니다.)
        </td>
    </tr>
</table>
</form>
<!-- content end -->

</DIV>
</body>
</html>
