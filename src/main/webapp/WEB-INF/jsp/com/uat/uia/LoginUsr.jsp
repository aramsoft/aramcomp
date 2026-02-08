<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : LoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2009.03.13   박지욱          최초 생성
  * @ 2011.09.25   서준식          사용자 관리 패키지가 미포함 되었을때에 회원가입 오류 메시지 표시
  * @ 2011.10.27   서준식          사용자 입력 탭 순서 변경
  *
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>아람컴포넌트 로그인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

<script type="text/javascript">

function checkLogin(userSe) {
    // 일반회원
    if (userSe == "GNR") {
        document.loginForm.rdoSlctUsr[0].checked = true;
        document.loginForm.rdoSlctUsr[1].checked = false;
        document.loginForm.rdoSlctUsr[2].checked = false;
        document.loginForm.userSe.value = "GNR";
    // 기업회원
    } else if (userSe == "ENT") {
        document.loginForm.rdoSlctUsr[0].checked = false;
        document.loginForm.rdoSlctUsr[1].checked = true;
        document.loginForm.rdoSlctUsr[2].checked = false;
        document.loginForm.userSe.value = "ENT";
    }
}

function actionLogin() {

    if (document.loginForm.username.value =="") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
    } else {
        document.loginForm.action="${pageContext.request.contextPath}/j_spring_security_check";
        document.loginForm.submit();
    }
}

function goFindId() {
    document.loginForm.action="${pageContext.request.contextPath}/uat/uia/searchIdPassword.do";
    document.loginForm.submit();
}

function goRegiUsr() {

	var useMemberManage = '${useMemberManage}';

	if(useMemberManage != 'true'){
		alert("사용자 관리 컴포넌트가 설치되어 있지 않습니다. \n관리자에게 문의하세요.");
		return false;
	}

    var userSe = document.loginForm.userSe.value;
    // 일반회원
    if (userSe == "GNR") {
    	var url = "${pageContext.request.contextPath}/uss/umt/stplatMberView.do";
        document.loginForm.action=url;
        document.loginForm.submit();
    // 기업회원
    } else if (userSe == "ENT") {
    	var url = "${pageContext.request.contextPath}/uss/umt/stplatEntrprsMberView.do";
        document.loginForm.action=url;
        document.loginForm.submit();
    } 
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length> 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if (form.checkId.checked)
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    else
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    setCookie("saveid", form.username.value, expdate);
}

function getid(form) {
	if(  getCookie("saveid") != "" ) {
		form.checkId.checked = ((form.username.value = getCookie("saveid")) != "");
	}
}

function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
        alert(message);
    }

    getid(document.loginForm);
    // 포커스
    //document.loginForm.rdoSlctUsr.focus();
}

window.onload = fnInit;

</script>

<style type="text/css">

#wrap { 
	text-align:center; height:100%;
	width:100%; 
}

.content { 	
	display: inline-block; width:330px;
	margin-top: 100px; padding:10px;
 	text-aligh:center;  
}

.content_main {
	width:303px; height: 210px; margin-top:5px;
	background:url('/images/com/uat/uia/login_bg01.gif') no-repeat;	
}

.buttons {
	margin-top:15px;
}

</style>
</head>

<body>

<DIV id="wrap">

<div class="content">

	<div class="content_title">
		<h2>일반로그인</h2>
	</div>
	<div class="content_main">
        <!--일반로그인 테이블 시작-->
        <form name="loginForm" action ="${pageContext.request.contextPath}/j_spring_security_check" method="post">
 
        <div style="height:70px;"></div>
        <div style="width:250px;margin-left:20px;">
            <table style="border:0;" >
                <tr>
                    <td width="30"></td>
                    <td>
                    	<input name="rdoSlctUsr" type="radio" value="radio" checked onClick="checkLogin('GNR');" style="border:0;background:#ffffff;" tabindex="1">일반
                    	&nbsp;&nbsp;
                    	<input name="rdoSlctUsr" type="radio" value="radio" onClick="checkLogin('ENT');" style="border:0;background:#ffffff;" tabindex="2">기업
                    </td>
                </tr>
               	<tr>
                    <td colspan="2">
                    <table style="border:0;">
                        <tr>
                            <td><label for="username">아이디&nbsp;&nbsp;</label></td>
                            <td><input type="text" name="username" id="username"  style="height:16px; width:85px; border:1px solid #CCCCCC; margin:0px; padding:0px; ime-mode:disabled;" tabindex="4" maxlength="10"/></td>
							<td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><label for="password">비밀번호&nbsp;&nbsp;</label></td>
                            <td><input type="password" name="password" id="password"  style="height: 16px; width: 85px; border: 1px solid #CCCCCC; margin: 0px; padding: 0px; ime-mode: disabled;" maxlength="12" tabindex="5" onKeyDown="javascript:if (event.keyCode == 13) { actionLogin(); }"/></td>
                            <td class="title">&nbsp;&nbsp;<label for="checkId"><input type="checkbox" name="checkId" class="check2" onClick="javascript:saveid(document.loginForm);" id="checkId" tabindex="6"/>ID저장</label></td>
                        </tr>
                        <tr>
                            <td>데모계정&nbsp;&nbsp;</td>
                            <td>TEST1/rhdxhd12</td>
                            <td class="title">&nbsp;&nbsp;</td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
 		</div>		
		<div class="buttons">              
			<span class="button"><a href="#" onClick="actionLogin(); return false;" tabindex="7">로그인</a></span>
	        <span class="button"><a href="#" onClick="goRegiUsr(); return false;" tabindex="8">회원가입</a></span>
	        <span class="button"><a href="#" onClick="goFindId(); return false;" tabindex="9">아이디/비밀번호 찾기</a></span>
		</div>
        
        <input name="userSe" type="hidden" value="GNR"/>
        <input name="targetUrl" type="hidden" value="${targetUrl}"/>
        <input type="hidden" name="message" value="${message}">
        <!-- input name="j_username" type="hidden"/ -->
        </form>
        <!--일반로그인 테이블 끝-->
        
    </div>    
      
</div>

</DIV>

</body>
</html>


