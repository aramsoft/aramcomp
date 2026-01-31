<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : IdDplctCheck.jsp
  * @Description : ID중복확인
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ID중복확인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:280px;margin:20px;">

<div class="content_title">
	<h2>아이디 중복확인</h2>
</div>

<form name="checkForm" action ="${pageContext.request.contextPath}/uss/umt/checkIdDplct.do">

<table border="0" class="table-register">
    <tr>
        <td width="40%">사용할아이디&nbsp;&nbsp;</td>
        <td>
            <input type="hidden" name="resultId" value="<c:out value="${checkId}"/>" />
         	<input type="hidden" name="usedCnt" value="<c:out value="${usedCnt}"/>" />
         	<input type="text" name="checkId" value="<c:out value="${checkId}"/>" size="15" maxlength="20" tabindex="1" title="아이디입력"/>
     	</td>
 	</tr>
 	<tr>
        <td colspan="2">결과&nbsp;&nbsp;:&nbsp;
            <c:choose>
            <c:when test="${usedCnt eq -1}">
                &nbsp; 
            </c:when>
            <c:when test="${usedCnt eq 0}">
                ${checkId} 는 사용가능한 아이디입니다.
            </c:when>
            <c:otherwise>
                ${checkId} 는 사용할수 없는 아이디입니다.
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>

</form>

<!-- 줄간격조정  -->
<div style="margin-top:20px; width:100%"></div>

<div style="width:100%; text-align:center;">
	<div class="button_area">
       	<span class="button"><a href="#" onclick="javascript:fnCheckId(); return false;"><spring:message code="button.inquire" /></a></span>
       	<span class="button"><a href="#" onclick="javascript:fnReturnId(); return false;"><spring:message code="button.use" /></a></span>
       	<span class="button"><a href="#" onclick="javascript:fnClose(); return false;"><spring:message code="button.close" /></a></span>
	</div>
</div>

</DIV>

<script type="text/javascript">

function fnCheckId(){
	if(fnCheckNotKorean(document.checkForm.checkId.value) && fnCheck(document.checkForm.checkId.value)){
		document.checkForm.submit();
    }else{
        return;
    }
}

function fnReturnId(){
    if (document.checkForm.usedCnt.value == 0){
    	window.opener.gArguments["userId"].value = document.checkForm.resultId.value;
    	window.close();
    }else if (document.checkForm.usedCnt.value == 1){
        alert("이미사용중인 아이디입니다.");
        return;
    }else{
    	alert("먼저 중복확인을 실행하십시오");
        return;
    }
}

function fnClose(){
    window.close();
}

function fnCheckNotKorean(koreanStr){
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) {
        }else{
            //hangul finding....
           	alert("한글은 사용할 수 없습니다.");
            return false;
        }
    }
    return true;
}

// 2011.10.25 보안점검 후속조치
function fnCheck(str){
    var blank_pattern = /[\s]/g;
    var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    if( special_pattern.test(str) == true || blank_pattern.test(str) == true ){
        alert("공백 및 특수문자는 사용할 수 없습니다.");
        return false;
    } else {
   		return true;
	}
}

</script>

</body>
</html>
