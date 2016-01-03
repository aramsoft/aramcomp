<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="none"%>
<%@ page import="java.util.*"  %>
<%@ page import="java.util.regex.*"  %>
<%@ page import="java.text.*"  %>
<%@ page import="java.io.*"  %>
<%
 /**
  * @Class Name  : WebStandardInspection.jsp
  * @Description : 웹표준검사
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
<title>웹표준 검사</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>웹표준 검사</h2>
</div>

<!--  줄간격조정  -->
<div style="margin-top:3px; width:100%"></div>

<!--  상단  등롬폼 영역 START -->
<table class="table-list" summary="이 표는 웹표준검사 대상 정보를 제공하며, URL명, Public IP 여부, Warniing, 상세보기 정보로 구성되어 있습니다 .">
<tr> 
	<th scope="col"               align="center">URL명</th>
	<th scope="col" width="170px" align="center">Public IP 여부</th>
	<th scope="col" width="90px"  align="center">Errors</th>
	<th scope="col" width="90px"  align="center">Warniing</th>
	<th scope="col" width="70px"  align="center">상세보기</th>

</tr>
</table>
<%
	for(int i=1 ; i<10; i++){ 
%>
<form name="webInspection<%=i%>" method="post" action="" target="ifr_hidden">
<table class="table-list" summary="이 표는 웹표준검사 대상 정보를 제공하며, URL명, Public IP 여부, Warniing, 상세보기 정보로 구성되어 있습니다 .">
<tr> 
	<td>
		<input name="uri" type="text" size="70" value="" maxlength="250" >
	</td>
	<td width="170px" align="center">
		<input type="radio" name="rdoUri" value="dUri">Private<input type="radio" name="rdoUri" value="uri" checked>Public&nbsp;&nbsp;
		<span class="button"><a href="#" onclick="fn_aram_submit_WebStandardInspection(document.forms[<%=i-1%>],'<%=i%>'); return false;">검색</a></span>
	</td>
	<td width="90px" align="center"><div id="divErr<%=i%>">&nbsp;</div></td>
	<td width="90px" align="center"><div id="divWar<%=i%>">&nbsp;</div></td>
	<td width="70px" align="center">
		<span class="button"><a href="#" onclick="fn_aram_submit_WebStandardInspectionLink(document.forms[<%=i-1%>]); return false;">보기</a></span>
		<input name="num" type="hidden" value="<%=i%>">
	</td>
</tr> 
</table>
</form>
<%
	}
%>

<!--  줄간격조정  -->
<div style="margin-top:3px; width:100%"></div>

<!--  Hiden frame  visibility: hidden;  -->
<iframe name="ifr_hidden" id="ifr_hidden1" src="about:blank;" style="width:100%;height:400px;visibility: hidden;"></iframe>

<form name="formHidden" id="formHidden" action="http://validator.w3.org/check" method="post" target="_blank">
	<input name="uri" type="hidden" value="">
</form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 화면 검사
 ******************************************************** a*/
function fn_aram_submit_WebStandardInspection(form, nNmm){
	alert(form);	
	
	document.getElementById('divErr'+nNmm).innerHTML='';
	document.getElementById('divWar'+nNmm).innerHTML='';
	
	//url 검사 모드시
	/*
	if(form.rdoUri[1].checked){
		var name=form.uri.value;
		var reg_name=/(127\.0\.0\.1|localhost)/g;     
		  
		if( reg_name.test(name) ){ 
		alert("Url 검사 에서는[localhost/127.0.0.1] 해당 주소를 검사 할수 없습니다.!");
		return false;
		} 
	
	}
	*/
	
	if(form.uri.value == ''){
		alert('웹표준검사 URL을 입력해주세요!');
		form.uri.focus();
		return false;
	} 
	
	if(form.rdoUri[0].checked == true){
		form.action='/PageLink.do?link=aramframework/com/utl/sys/wsi/WebStandardInspectionUriDirect';
	}else{
		form.action='/PageLink.do?link=aramframework/com/utl/sys/wsi/WebStandardInspectionUri';
	}
	form.submit();
}

/* ********************************************************
 * 상세보기 스크립트
 ******************************************************** */
function fn_aram_submit_WebStandardInspectionLink(form){
	alert(form);	
	if(form.uri.value == ''){
		alert('웹표준검사 URL을 입력해주세요!');
		form.uri.focus();
		return;
	} 
	
 	document.formHidden.uri.value = form.uri.value;

	if(form.rdoUri[0].checked == true){
		 document.formHidden.action = "/PageLink.do?link=aramframework/com/utl/sys/wsi/WebStandardInspectionUriDirectLink";
	}else{
		 document.formHidden.action = "http://validator.w3.org/check";
	}
	 
	document.formHidden.submit();
}

</script>

</body>
</html>
