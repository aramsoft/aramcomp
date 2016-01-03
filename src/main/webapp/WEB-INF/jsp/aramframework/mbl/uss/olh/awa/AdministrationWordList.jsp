<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>행정용어사전목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 용어사전 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h5>행정용어사전목록</h5>
	    <a href="javascript:fn_aram_show_searchMenu();" data-icon="search">초성검색</a>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		
		<form:form commandName="administrationWordVO" method="post" data-role="none">

			<div class="uss-Search">
               	<form:select path="searchCondition" data-role="none">
              		<form:option value="ADMINIST_WORD_NM" label="행정용어명" />
               		<form:option value="ADMINIST_WORD_ENG_NM" label="행정용어영문명" />
              		<form:option value="ADMINIST_WORD_ABRV_NM" label="행정용어약어명" />
               		<form:option value="ADMINIST_WORD_DFN" label="행정용어정의" />
               		<form:option value="ADMINIST_WORD_DC" label="행정용어설명" />
              	</form:select>
	        	<div class="uss-SearchBox">
               		<form:input path="searchKeyword" class="type-text" data-role="none" />
	         	</div>
	          	<input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search('', '', ''); return false;" data-role="none" />
			</div>
			
			<form:hidden path="choseongA" />
			<form:hidden path="choseongB" />
			<form:hidden path="choseongSe" />
 			<form:hidden path="pageIndex" />
 		</form:form>
		
		<ul data-role="listview">
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
		</div>
		
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright (c) Ministry of Public Administration and Security.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 용어사전 List end -->	

<div id="searchMenu" data-role="page">

	<!-- header start -->
	<div data-role="header">
		<a href="#list" data-icon="arrow-l" data-ajax="false">뒤로</a>
	    <h1>행정전문용어조회</h1>
	</div>
	<!-- header end -->
	
	<div data-role="navbar">
		<ul>
            <li><a href="#" onclick="javascript:fn_aram_search('가', '나', 'H'); return false;" data-role="button">ㄱ</a></li>
            <li><a href="#" onclick="javascript:fn_aram_search('나', '다', 'H'); return false;" data-role="button">ㄴ</a></li>
            <li><a href="#" onclick="javascript:fn_aram_search('다', '라', 'H'); return false;" data-role="button">ㄷ</a></li>
        </ul>
        <ul>  
            <li><a href="#" onclick="javascript:fn_aram_search('라', '마', 'H'); return false;" data-role="button">ㄹ</a></li>
           	<li><a href="#" onclick="javascript:fn_aram_search('마', '바', 'H'); return false;" data-role="button">ㅁ</a></li>
            <li><a href="#" onclick="javascript:fn_aram_search('바', '사', 'H'); return false;" data-role="button">ㅂ</a></li>
		</ul>
		<ul>
			<li><a href="#" onclick="javascript:fn_aram_search('사', '아', 'H'); return false;" data-role="button">ㅅ</a></li>
			<li><a href="#" onclick="javascript:fn_aram_search('아', '자', 'H'); return false;" data-role="button">ㅇ</a></li>
			<li><a href="#" onclick="javascript:fn_aram_search('자', '카', 'H'); return false;" data-role="button">ㅈ-ㅊ</a></li>
		</ul>
		<ul>						
			<li><a href="#" onclick="javascript:fn_aram_search('카', '타', 'H'); return false;" data-role="button">ㅋ</a></li>
			<li><a href="#" onclick="javascript:fn_aram_search('타', '파', 'H'); return false;" data-role="button">ㅌ</a></li>
			<li><a href="#" onclick="javascript:fn_aram_search('파', '하', 'H'); return false;" data-role="button">ㅍ</a></li>
		</ul>
		<ul>
			<li><a href="#" onclick="javascript:fn_aram_search('하', '힣', 'E'); return false;" data-role="button">ㅎ</a></li>
			<li><a href="#" onclick="javascript:fn_aram_search('A', 'E', 'E'); return false;" data-role="button">A-E</a></li>
			<li><a href="#" onclick="javascript:fn_aram_search('E', 'J', 'E'); return false;" data-role="button">E-J</a></li>
		</ul>
		<ul>
			<li><a href="#" onclick="javascript:fn_show_search('K', 'O', 'E'); return false;" data-role="button">K-O</a></li>
			<li><a href="#" onclick="javascript:fn_show_search('P', 'T', 'E'); return false;" data-role="button">P-T</a></li>
			<li><a href="#" onclick="javascript:fn_show_search('U', 'Z', 'E'); return false;" data-role="button">U-Z</a></li>
		</ul>
										
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>

</div>	
	
<script type="text/javaScript">

$('#list').bind('pageshow', initList);

function initList() {
	
	var url = "${pageContext.request.contextPath}/uss/olh/awa/listAdministrationWordJson.mdo";
    $.getJSON(url, $('#administrationWordVO').serialize(), function(json) {
		
		$("#searchCondition option[value='" + json.administrationWordVO.searchCondition + "']").attr('selected', 'selected');

		var html = "";
		if(json.resultList.length == 0) {
			html = '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';
		}
		else {
			html = "";
			for ( var i = 0; i < json.resultList.length; i++) {
				
				var result =  json.resultList[i];
				
				html += '<li>';
				html += '	<a href="${pageContext.request.contextPath}/uss/olh/awa/detailAdministrationWord.mdo?administWordId='+ result.administWordId +'">';
				html += '		<h3>' + result.administWordNm + '</h3>';
				html += '		<p><span class="uss-txtBlack">' + result.administWordAbrvNm +'</span> <span class="uss-txtBlack">' + result.themaRelm + '</span><span class="uss-txtDate">' + result.frstRegisterPnttm + '</span></p>';
				html += '	</a>';
				html += '</li>';			
							
			}
		}
		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');

		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_aram_linkPage"));
    });
}

function fn_aram_show_searchMenu() {
	$.mobile.changePage( $("#searchMenu"), { transition: "slidedown" } );
}

function fn_aram_linkPage(pageIndex) {
	$('#pageIndex').val(pageIndex);		
	initList();
}

function fn_aram_search(choseongA, choseongB, choseongSe) {
	
	if(choseongSe != ''){
		$('#searchKeyword').val("");
		$('#searchCondition').val("");
		$.mobile.changePage( $("#list"), { transition: "slideup" });
	}
					
	$('#pageIndex').val(1);
	$('#choseongA').val(choseongA);
	$('#choseongB').val(choseongB);
	$('#choseongSe').val(choseongSe);

	initList();
}
	        	
function fn_aram_listPage() {
	$.mobile.changePage( $("#list"), { transition: "slideup" });
}			

</script>

</body>
</html>
