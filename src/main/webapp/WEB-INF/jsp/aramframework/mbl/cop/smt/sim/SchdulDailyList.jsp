<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>일일 일정 보기</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

<!-- datebox javascript-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/datepicker/jqm-datebox.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.calbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.datebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.flipbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jquery.mobile.datebox.i18n.ko.utf8.js"></script>

</head>

<body>

<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>일정관리목록</h1>
		<a href="javascript:fn_aram_regist();" data-icon="plus" data-ajax="false">등록</a>
		<div data-role="navbar">
			<ul>
				<li><a href="${pageContext.request.contextPath}/cop/smt/sim/listSchdulWeek.mdo" id="week" data-ajax="false">주간별 일정보기</a></li>
				<li><a href="${pageContext.request.contextPath}/cop/smt/sim/listSchdulDaily.mdo" class="ui-btn-active" id="date" data-ajax="false">일별 일정보기</a></li>
			</ul>
		</div>
	</div>			
	<!-- header end -->
		
	<!-- contents start -->
	<div data-role="content">
		
		<form:form commandName="schdulManageVO" action="" method="post" data-role="none">
           	<input type="hidden" id="schdulId" name="schdulId" value="" />
           	<input type="hidden" id="path" name="path" value="daily" />

			<div class="uss-typ5">
               	<form:select path="searchSchdulSe"  data-role="none" onchange="javascript:initList();">
             		<form:options items="${COM030_schdulSe}" itemValue="code" itemLabel="codeNm"/>
               	</form:select>
			</div>
			<div class="uss-typ6">
				<input id="selDate" name="selDate" type="text" data-role="datebox" value="${selDate}" data-options='{"closeCallback": "javascript:initList();"}' readonly="readonly" />
			</div>
		</form:form>
		
		<ul data-role="listview" class="uss-clear">
		</ul>
		
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>

<script type="text/javascript" >

$('#list').bind('pageshow', initList);

function initList() {
	
	var url = "${pageContext.request.contextPath}/cop/smt/sim/listSchdulDailyJson.mdo";
 	$.getJSON(url, $('#schdulManageVO').serialize(), function(json) {
 		
		var html = "";
       	$("#searchCondition option[value='" + json.schdulManageVO.searchSchdulSe + "']").attr('selected', 'selected');
		$("#selDate").val(json.selDate);

		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
           	html += '    <h1> 일정이 없습니다 </h1>';
           	html += '</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
			
				var result =  json.resultList[i];
				var beginDe = result.schdulBgnde.substring(8, 10) + '시 ' + result.schdulBgnde.substring(10, 12) + '분';
				var endDe = result.schdulEndde.substring(8, 10) + '시 ' + result.schdulEndde.substring(10, 12) + '분';
			
				html += '<li>';
				html += '	<a href="javascript:fn_aram_detail(\'' + result.schdulId + '\');">';
				html += '		<h3>' + result.schdulNm + '</h3>';
				html += '		<p class="uss-darkgray">' + beginDe + ' ~ ' + endDe +'</p>';
				html += '	</a>';
				html += '</li>';	
						
			}
		}

		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
 	});
 }

function fn_aram_detail(id) {

	$('#schdulId').val(id);
	
	var url = "${pageContext.request.contextPath}/cop/smt/sim/detailSchdul.mdo";
	$('#schdulManageVO').attr('action', url);
	$('#schdulManageVO').attr('data-ajax', 'false');
	$('#schdulManageVO').submit();
}

function fn_aram_regist() {

	$('#schdulId').val('');
	
	var url = "${pageContext.request.contextPath}/cop/smt/sim/registSchdul.mdo";
	$('#schdulManageVO').attr('action', url);
	$('#schdulManageVO').attr('data-ajax', 'false');
	$('#schdulManageVO').submit();
}

</script>

</body>
</html>
