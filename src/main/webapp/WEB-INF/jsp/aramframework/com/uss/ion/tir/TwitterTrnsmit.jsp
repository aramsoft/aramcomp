<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TwitterTrnsmit.jsp
  * @Description : 트위터 송신(등록) 페이지
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>트위터(Twitter)-송신(등록)</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="twitterInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
* 저장
******************************************************** */
function fn_aram_save_Twitter(){
	var vFrom = document.twitterInfo;

	if(!validateTwitterInfoVO(vFrom)){ 			
		return;
	}
	
	if(confirm("작성된 Twitter를 전송 하시겠습니까?")){
		vFrom.action = "/uss/ion/tir/registTwitterTrnsmit.do";
		vFrom.submit();
	}
}

</script>

<style type="text/css">
.txInput80 {
width : 80px;
}

#divVerify {
font-size : 12px;
color : red;
}
</style>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>트위터 송신(등록)</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_save_Twitter(); return false;">전송</a></span>
	</div>
</div>

<form:form commandName="twitterInfo" name="twitterInfo" action="" method="post" enctype="multipart/form-data">

<!--  등록  폼 영역  -->
<table class="table-register" summary="트위터  입력을 제공한다">
<caption>트위터  입력을 제공한다</caption>
	<tr> 
		<th scope="row" width="20%"  class="column_title">
			<label for="twitterText">Twitter 내용</label>
			<span class="required_icon"></span>
		</th>
		<td width="80%">
			<form:textarea path="twitterText" title="Twitter 내용" rows="7" cols="20" cssClass="txArea"/>
			<form:errors path="twitterText" cssClass="error"/>
		</td>
	</tr>
</table>

</form:form>

</DIV>
</body>
</html>