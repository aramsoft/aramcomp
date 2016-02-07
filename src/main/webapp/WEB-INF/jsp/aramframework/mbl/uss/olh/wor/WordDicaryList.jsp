<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>용어사전목록조회</title>

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
	    <h1>용어사전목록조회</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		
		<form:form commandName="wordDicaryVO" method="post" data-role="none">
 			<div class="uss-Search">
               	<form:select path="searchCondition" data-role="none">
					<form:option value="" label="--선택하세요--" />
			   		<form:option value="WORD_NM" label="용어명" />			   
			   		<form:option value="ENG_NM" label="영문명" />			   
              	</form:select>
	        	<div class="uss-SearchBox">
               		<form:input path="searchKeyword" class="type-text" data-role="none" />
	         	</div>
	          	<input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_linkPage(1); return false;" data-role="none" />
			</div>
			
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
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 용어사전 List end -->		
	
<script type="text/javaScript" >

$('#list').bind('pageshow', initList);

function initList() {
				
	var url = "${pageContext.request.contextPath}/uss/olh/wor/listWordDicaryJson.mdo";
	$.getJSON(url, $('#wordDicaryVO').serialize(), function(json) {
        			
        $("#searchCondition option[value='" + json.wordDicaryVO.searchCondition + "']").attr('selected', 'selected');
 
        var html = "";
		if(json.wordDicaryList.length == 0) {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';
		}
		else {
			for ( var i = 0; i < json.wordDicaryList.length; i++) {
				
				var wordDicary =  json.wordDicaryList[i];
				
				html += '<li>';
				html += '	<a href="${pageContext.request.contextPath}/uss/olh/wor/detailWordDicary.mdo?wordId=' + wordDicary.wordId + '">';
				html += '		<h3>' + wordDicary.wordNm + '</h3>'; 
				html += '		<p class="uss-Eng">' + wordDicary.engNm + '</p>'; 
				html += '		<p><span class="uss-txtBlack">' + wordDicary.emplyrNm + '</span><span class="uss-txtDate">' + wordDicary.frstRegisterPnttm + '</span></p>';
				html += '	</a>';
				html += '</li>';			
			}
		}
		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');

		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_aram_linkPage"));
    });
    		 	
}

function fn_aram_linkPage(pageIndex) {
	$('#pageIndex').val(pageIndex);
	initList();
}

function fn_aram_listPage() {
	$.mobile.changePage( $("#list"), { transition: "slideup" });
}
	        	
</script>
			
</body>
</html>
