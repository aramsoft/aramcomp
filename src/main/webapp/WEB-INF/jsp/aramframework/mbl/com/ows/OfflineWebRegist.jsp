<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : OfflineWebRegist.jsp
  * @Description : 오프라인웹서비스 등록
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2011.08.25   서형주          최초 생성
  *
  *  @author 서형주 
  *  @since 2011.08.25
  *  @version 1.0
  *  @see
  *  
  */
%>

<html>
<head>
<title>오프라인웹서비스 등록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/mcomd/egovMcomdAdmin.css" type="text/css">

<!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="offlineWebVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_aram_init_offlineWeb(){

    // 첫 입력란에 포커스..
    //document.offlineWebForm.offlineWebSj.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list_offlineWeb() {
    var varForm = document.getElementById("offlineWebVO");
    varForm.action = "${pageContext.request.contextPath}/mbl/com/ows/listOfflineWeb.mdo";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update_offlineWeb(){
    var varForm = document.getElementById("offlineWebVO");

    if (!validateOfflineWebVO(varForm)) {
		return;				
	} 
    
	if (confirm("<spring:message code='common.regist.msg' />")) {  
		varForm.action = "${pageContext.request.contextPath}/mbl/com/ows/insertOfflineWeb.mdo";
		varForm.submit();
	}
}

</script>
</head>

<body onload="fn_aram_init_offlineWeb();">

<div>

	<!-- header Start -->
	<div id="header">
		<a href="${pageContext.request.contextPath}/mindex.jsp"><span class="btnHome"></span></a>
		<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/h1_logo.png"  /></h1>
		<a href="${pageContext.request.contextPath}/mbl/com/ows/listOfflineWeb.mdo"
	           onclick="fn_aram_detail_OfflineWeblist(); return false;"><span class="btnBack"></span></a>
	</div>
	<!-- header End -->
	
	<div id="content" class="contents">
	
		<div class="content_title">
			<h2>오프라인웹 서비스 등록</h2>
		</div>
		
		<div id="search_area">
			<div class="button_area">
				<span class="button"><a href="#" onclick="javascript:fn_aram_update_offlineWeb(); return false;"><spring:message code="button.save" /></a></span>
				<span class="button"><a href="#" onclick="javascript:fn_aram_list_offlineWeb(); return false;"><spring:message code="button.list" /></a></span>
			</div>
		</div>

		<!-- 등록  폼 영역  -->
		<form:form commandName="offlineWebVO" action="" method="post">
			<form:hidden path="sn" />
		
			<!-- 등록  폼 영역  -->
			<table width="100%" cellpadding="6" class="table-register"  summary="오프라인웹목록저장테이블.">
			  	<tr> 
				    <th width="20%">
				    	<label id="offlineWebSj" for="offlineWebSj">제목</label>
						<span class="required_icon"></span>
				    </th>
				    <td width="80%">
				        <form:input path="offlineWebSj" size="71" maxlength="71" />
				        <form:errors path="offlineWebSj" cssClass="error"/>                               
				    </td>
			  	</tr>
			    <tr> 
				    <th>
				    	<label id="offlineWebCn" for="offlineWebCn">내용</label>
						<span class="required_icon"></span>
				    </th>
				    <td>
				        <form:textarea path="offlineWebCn" rows="15" cols="70" maxlength="70" />
				        <form:errors path="offlineWebCn" cssClass="error"/>                              
				    </td>
				</tr>    
			</table>
		
		</form:form>
	</div>
	
	<!-- footer Start-->
	<div id="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer End -->
	
</div>	
</body>
</html>