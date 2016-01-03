<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>모바일 화면</title>
     
<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
 
<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css"/>

<script type="text/javascript"> 	

/*********************************************************
 * 오프라인웹 서비스 상세조회 초기화
 ******************************************************** */        
$(document).ready(function() {
	if(window.navigator.onLine == true) {

	// 1. 오프라인웹 서비스 상세조회
        $.ajax({
            type       : "POST",
            cache      : false,                   
            url        : "${pageContext.request.contextPath}/mbl/com/ows/goMobileOfflineWebJson.mdo",
            data       : {sn:<c:out value='${sn}'/>},            
            dataType   : "json",                 
            success :function(json) {

                var offlineWebVO = eval(json.offlineWebVO);
                
                $("#offlineWebSj").text(offlineWebVO.offlineWebSj);
                $("#mberId").text(offlineWebVO.mberId);
                $("#creatDt").text(offlineWebVO.creatDt);
                $("#updtDt").text(offlineWebVO.updtDt);
                $("#offlineWebCn").text(offlineWebVO.offlineWebCn);
                networkStatus = "ON";
            }
        });
                		    		
	} else {
	// 2. [오프라인] 오프라인웹 서비스 상세조회 webstorage에 적재
        $("#offlineWebSj").text(localStorage.getItem("localView_SJ"));
        $("#offlineWebCn").text(localStorage.getItem("localView_CN"));
        $("#creatDt").text(localStorage.getItem("localView_CREAT_DT"));
	}
});

</script>
</head>

<body>
	
<!-- 오프라인 서비스 상세화면 -->
<div id="regist" data-role="page" data-theme="d">

    <!-- header start -->
    <div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>오프라인서비스 상세화면</h1>
		<a href="${pageContext.request.contextPath}/mbl/com/ows/listMobileOfflineWeb.mdo" data-ajax="false" data-icon="back" class="ui-btn-right">이전</a>
	</div>
    <!-- header end -->

    <!-- content start -->
	<div data-role="content"  class="egov-mcomdContent">
		<!-- form -->
	 	<form:form commandName="offlineWebView" name="offlineWebView" method="post">
	 		<input type="hidden" name="sn"/>
	 	</form:form>	

		<dl class="realtimeView">
			<dt>제목</dt>
			<dd id="offlineWebSj" class="subj"></dd>
			<dt>생성일시</dt>
			<dd id="creatDt" class="date"></dd>
			<dt>내용</dt>
			<dd id="offlineWebCn"></dd>
		</dl>
		<div class="realBtn">
			<a href="${pageContext.request.contextPath}/mbl/com/ows/listMobileOfflineWeb.mdo" data-ajax="false"  data-role="button" data-theme="z">목록</a>
		</div>
	</div>
	<!-- content end -->

    <!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
    <!-- footer end -->

</div>
</body>
</html>    