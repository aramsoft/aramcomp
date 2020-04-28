<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : IdPasswordSearch.jsp
  * @Description : 아이디/비밀번호 찾기 화면
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2009.03.09   박지욱          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.09
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>아람컴포넌트 아이디/비밀번호 찾기</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

<script type="text/javascript">

function fnCheckUsrId(userSe) {
	// 일반회원
	if (userSe == "GNR") {
		document.idForm.userSe.value = "GNR";
	// 기업회원
	} else if (userSe == "ENT") {
		document.idForm.userSe.value = "ENT";
	// 업무사용자
	} else if (userSe == "USR") {
		document.idForm.userSe.value = "USR";
	}
}

function fnCheckUsrPassword(userSe) {
	// 일반회원
	if (userSe == "GNR") {
		document.passwordForm.userSe.value = "GNR";
	// 기업회원
	} else if (userSe == "ENT") {
		document.passwordForm.userSe.value = "ENT";
	// 업무사용자
	} else if (userSe == "USR") {
		document.passwordForm.userSe.value = "USR";
	}
}

function fnSearchId() {
	if (document.idForm.name.value =="") {
		alert("이름을 입력하세요");
	} else if (document.idForm.email.value =="") {
		alert("가입시 이메일주소를 입력하세요");
	} else {
		//window.open("${pageContext.request.contextPath}/cmm/uat/uia/searchId.do?id=");
		document.idForm.submit();
	}
}

function fnSearchPassword() {
	if (document.passwordForm.userId.value =="") {
		alert("아이디를 입력하세요");
	} else if (document.passwordForm.name.value =="") {
		alert("이름을 입력하세요");
	} else if (document.passwordForm.email.value =="") {
		alert("가입시 이메일주소를 입력하세요");
	} else if (document.passwordForm.passwordHint.value =="") {
		alert("비밀번호 힌트를 선택하세요");
	} else if (document.passwordForm.passwordCnsr.value =="") {
		alert("비밀번호 정답을 입력하세요");
	} else {
		document.passwordForm.submit();
	}
}

</script>

<style type="text/css">

#wrap {
	margin:100px auto; width:50%;
}

.content { 
	float:left;	
	width:330px;
	padding:10px;
 	text-aligh:center;  
}

.content_main {
	width:303px; height: 210px; margin-top:5px;
	border:1px solid #C0C0C0;
	background-color:#F8F8F8;
}

.buttons {
	margin-top:20px;
}

</style>
</head>

<body>

<DIV id="wrap">

<div class="content">

	<div class="content_title">
		<h2>아이디 찾기</h2>
	</div>
	<div class="content_main">
        <!--아이디 찾기 테이블 시작-->
	    <form name="idForm" action ="${pageContext.request.contextPath}/uat/uia/searchId.do" method="post">
 
        <div style="height:15px;"></div>
        <div style="width:250px;margin-left:20px;">
            <table border="0" >
                <tr>
                    <td width="30"></td>
                    <td>
                    	<input name="rdoSlctUsr" type="radio" value="GNR" checked onClick="fnCheckUsrId('GNR');" style="border:0;background:#ffffff;" tabindex="1">일반
                    	&nbsp;&nbsp;
                    	<input name="rdoSlctUsr" type="radio" value="ENT" onClick="fnCheckUsrId('ENT');" style="border:0;background:#ffffff;" tabindex="2">기업
                    	&nbsp;&nbsp;
                    	<input name="rdoSlctUsr" type="radio" value="USR" onClick="fnCheckUsrId('USR');" style="border:0;background:#ffffff;" tabindex="3">업무
                    </td>
                </tr>
                <tr><td colspan="2" height="5"></td></tr>
               	<tr>
                    <td colspan="2">
                    <table border="0">
                        <tr>
                            <td><label for="id">이름&nbsp;&nbsp;</label></td>
                            <td align="left"><input type="text" name="name" id="name" style="height:16px; width:85px; border:1px solid #CCCCCC; margin:0px; padding:0px;" tabindex="4" maxlength="10"/></td>
                        </tr>
                        <tr>
                            <td><label for="password">이메일&nbsp;&nbsp;</label></td>
                            <td align="left"><input type="text" name="email" id="email" value="" style="height: 16px; width: 140px; border: 1px solid #CCCCCC; margin: 0px; padding: 0px; ime-mode: disabled;" maxlength="100" tabindex="5" /></td>
                         </tr>
                    </table>
                    </td>
                </tr>
            </table>
 		</div>		
		<div class="buttons">              
			<span class="button"><a href="#" onClick="fnSearchId(); return false;" tabindex="7">아이디찾기</a></span>
		</div>
        
        <input name="userSe" type="hidden" value="GNR"/>
        </form>
        <!--아이디 찾기 테이블 끝-->
        
    </div>    
      
</div>

<div class="content">

	<div class="content_title">
		<h2>비밀번호 찾기</h2>
	</div>
	<div class="content_main">
        <!--비밀번호 찾기 테이블 시작-->
	    <form name="passwordForm" action ="${pageContext.request.contextPath}/uat/uia/searchPassword.do" method="post">
 
        <div style="height:15px;"></div>
        <div style="width:250px;margin-left:20px;">
            <table border="0" >
                <tr>
                    <td width="30"></td>
                    <td>
                    	<input name="rdoSlctUsr" type="radio" value="GNR" checked onClick="fnCheckUsrPassword('GNR');" style="border:0;background:#ffffff;" tabindex="1">일반
                    	&nbsp;&nbsp;
                    	<input name="rdoSlctUsr" type="radio" value="ENT" onClick="fnCheckUsrPassword('ENT');" style="border:0;background:#ffffff;" tabindex="2">기업
                    	&nbsp;&nbsp;
                    	<input name="rdoSlctUsr" type="radio" value="USR" onClick="fnCheckUsrPassword('USR');" style="border:0;background:#ffffff;" tabindex="3">업무
                    </td>
                </tr>
                <tr><td colspan="2" height="5"></td></tr>
               	<tr>
                    <td colspan="2">
                    <table border="0">
                        <tr>
                            <td><label for="id">아이디&nbsp;&nbsp;</label></td>
                            <td align="left"><input type="text" name="userId" id="userId" value="" style="height: 16px; width: 85px; border: 1px solid #CCCCCC; margin: 0px; padding: 0px; ime-mode: disabled;" maxlength="12" tabindex="5" /></td>
                        </tr>
                        <tr>
                            <td><label for="name">이름&nbsp;&nbsp;</label></td>
                            <td align="left"><input type="text" name="name" id="name" style="height:16px; width:85px; border:1px solid #CCCCCC; margin:0px; padding:0px;" tabindex="4" maxlength="10"/></td>
                        </tr>
                        <tr>
                            <td><label for="email">이메일&nbsp;&nbsp;</label></td>
                            <td align="left"><input type="text" name="email" id="email" value="" style="height: 16px; width: 140px; border: 1px solid #CCCCCC; margin: 0px; padding: 0px; ime-mode: disabled;" maxlength="100" tabindex="5" /></td>
                        </tr>
                        <tr>
                            <td><label for="passwordHint">비밀번호힌트</label></td>
                            <td align="left">
						      <select name="passwordHint" style="height: 16px; width: 140px; border: 1px solid #CCCCCC; margin: 0px; padding: 0px;" class="select" tabindex="12" title="비밀번호힌트선택">
							    <option selected value=''>--선택하세요--</option>
							    <c:forEach var="result" items="${COM022_passwordHint}" varStatus="status">
								<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
								</c:forEach>
							  </select>
							</td>  
                        </tr>
                        <tr>
                            <td><label for="passwordCnsr">비밀번호 정답</label></td>
                            <td align="left"><input type="text" name="passwordCnsr" id="passwordCnsr" value="" style="height: 16px; width: 85px; border: 1px solid #CCCCCC; margin: 0px; padding: 0px; ime-mode: disabled;" maxlength="12" tabindex="5" /></td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
 		</div>		
		<div class="buttons">              
			<span class="button"><a href="#" onClick="javascript:fnSearchPassword(); return false;" tabindex="7">비밀번호찾기</a></span>
		</div>
        
        <input name="userSe" type="hidden" value="GNR"/>
        </form>
        <!--비밀번호 찾기 테이블 끝-->
        
    </div>    
      
</div>

</DIV>

</body>
</html>

