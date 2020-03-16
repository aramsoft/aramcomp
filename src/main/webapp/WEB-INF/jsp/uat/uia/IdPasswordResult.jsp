<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : IdPasswordResult.jsp
  * @Description : 아이디/비밀번호 찾기 결과화면
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2009.03.17   박지욱          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.17
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>아람컴포넌트 아이디/비밀번호 찾기 결과</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmm/button.css" type="text/css">

<script type="text/javascript">

/* ********************************************************
 * 뒤로 처리 함수
 ******************************************************** */
function fnBack(){
    history.back();
}

</script>

<style type="text/css">

#wrap { 
	text-align:center; height:100%;	width:100%; 
}

.content { 	
	display: inline-block; width:330px;
	margin-top: 100px; padding:10px;
 	text-aligh:center;  
}

.content_main {
	width:303px; height: 210px; margin-top:5px;
	background:url('/images/uat/uia/login_bg01.gif') no-repeat;	
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
		<h2>아이디/비밀번호 찾기</h2>
	</div>
	<div class="content_main">
        <div style="height:70px;"></div>
        <div style="width:250px;margin-left:20px;">
		    ${resultInfo}
		</div>    
		<div class="buttons">              
			<span class="button"><a href="#" onClick="fnBack(); return false;" tabindex="7">뒤로</a></span>
		</div>
    </div>    
      
</div>

</DIV>

</body>
</html>

