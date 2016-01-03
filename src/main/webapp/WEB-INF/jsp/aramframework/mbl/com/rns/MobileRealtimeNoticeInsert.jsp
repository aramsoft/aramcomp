<!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : MobileRealtimeNoticeInsert.jsp
  * @Description : 
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2011.08.11   조준형          최초 생성
  *
  *  @author 조준형
  *  @since 2011.08.11
  *  @version 1.0
  *  @see
  *  
  */
%>
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
var contextPath = "${pageContext.request.contextPath}";	// js uri 적용을 위한 contextpath
/*********************************************************
 * 실시간 공지 서비스 등록  init
 ******************************************************** */
$(document).ready(function() {
  		// datepicker initialized
	$.mobile.page.prototype.options.degradeInputs.date = 'text';
});

</script>
<!-- History.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/cmm/jquery.history.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/cmm/jquery.mobile-1.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/cmm/EgovMobile-1.1.1.js"></script>
<!-- 실시간공지 서비스 js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/com/rns/rns.js"></script>        
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/mbl/cmm/datepicker/jquery.mobile.datebox.js"></script>
</head>

<body>
	
<!-- 실시간공지 서비스 등록 -->
<div id="regist" data-role="page" data-theme="d">

    <!-- header start -->
    <div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>실시간 공지서비스 등록</h1>
	</div>
    <!-- header end -->

    <!-- content start -->
	<div data-role="content"  class="egov-mcomdContent">

		<!-- form -->
		<form:form commandName="rns" name="rns" method="post">
		</form:form>
				
		<dl class="realtimeView">
			<dt><label for="noticeSj"><strong>제목</strong></label></dt>
			<dd><input type="text" id="noticeSj" size="30" value=""/></dd>
			<dt><label for="noticeCn"><strong>내용</strong></label></dt>
			<dd><textarea id="noticeCn" rows="8" cols="40"></textarea></dd>
			<dt><label for="defandroid">게시기간</label></dt>
			<dd>
				<input id="noticeBgnDt" name="defandroid" type="date" data-role="datebox" id="defandroid" readonly='readonly'/>
				<br>
		    	<input id="noticeEndDt" name="defandroid" type="date" data-role="datebox" id="defandroid" readonly='readonly'/>
	    	</dd>
		</dl>
		<div class="ui-grid-b realBtn">
			<div class="ui-block-a"><a href="javascript:fn_insert();" data-role="button" data-theme="f">등록</a></div>
			<div class="ui-block-b"><a href="javascript:fn_cancel();" data-role="button" data-theme="b">취소</a></div>
			<div class="ui-block-c"><a href="${pageContext.request.contextPath}/mbl/com/rns/listRealtimeNotice.mdo" data-ajax="false" data-role="button" data-theme="a">목록</a></div>			
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