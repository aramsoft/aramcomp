<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>나의 주소록목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>
		
</head>

<body>

<!-- 주소록 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>나의 주소록목록</h1>
	    <a href="javascript:fn_aram_regist();" data-icon="plus" data-ajax="false">등록</a>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		<form:form commandName="adressBookVO" method="post">
			<input type="hidden" id="adbkId" name="adbkId" value=""/>

 			<div class="uss-Search">
               	<form:select path="searchCondition" data-role="none">
			   		<form:option value="ADBK_NM" label="주소록명" />
			   		<form:option value="OTHBC_SCOPE" label="공개범위" />
			   		<form:option value="WRTER_ID" label="등록자" />
               	</form:select>
               	<div class="uss-SearchBox">
               		<form:input path="searchKeyword" class="type-text" data-role="none" />
               	</div>
               	<input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search(); return false;" data-role="none" />
			</div>
			<form:hidden path="pageIndex" />
		</form:form>
		
		<ul data-role="listview" data-split-icon="delete">
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
		</div>
		
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 주소록 List end -->		
	
<script type="text/javaScript" >

$('#list').bind('pageshow', initList);

function initList() {

	var url = "${pageContext.request.contextPath}/cop/adb/listAdressBookJson.mdo"
    $.getJSON(url, $('#adressBookVO').serialize(), function(json) {

    	$("#searchCondition option[value='" + json.adressBookVO.searchCondition + "']").attr('selected', 'selected');

        var html = "";
        
		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
				var result =  json.resultList[i];
				html += '<li>';
				html += '	<a href="javascript:fn_aram_detail(\'' + result.adbkId + '\');" data-ajax="false">';
				html += '		<h3>' + result.adbkNm + '<span class="uss-txtAddress">주소록</span><span class="uss-graySmall">' + result.othbcScope + '</span></h3>';
				html += '		<p><span class="uss-txtBlack">' + result.wrterId + '</span><span class="uss-txtDate">' + result.frstRegisterPnttm.substring(0, 10) + '</span></p>';
				html += '	</a>';
				
				if (result.wrterId == json.userId) {
					html += '<a href="javascript:fn_aram_delete(\'' + result.adbkId + '\');">삭제</a>';
				}
				
				html += '</li>';			
			}
		}

		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_aram_linkPage"));
    });
}

function fn_aram_delete(adbkId) {
	jConfirm('삭제하시겠습니까?', '알림', 'a', function(r) {

		if(r) {
			$.getJSON("${pageContext.request.contextPath}/cop/adb/deleteAddressBook.mdo", {adbkId:adbkId}, function(json){
				initList();
			});
		}
	});
}

function fn_aram_regist() {
    var varForm = document.getElementById("adressBookVO");
    varForm.action = "${pageContext.request.contextPath}/cop/adb/registAddressBook.mdo";
    varForm.submit();
}

function fn_aram_detail(adbkId) {
    var varForm = document.getElementById("adressBookVO");
	$('#adbkId').val(adbkId);
    varForm.action = "${pageContext.request.contextPath}/cop/adb/editAddressBook.mdo";
	varForm.submit();
}

function fn_aram_search() {
	$('#pageIndex').val(1);
	initList();
}

function fn_aram_linkPage(pageNo){
	$('#pageIndex').val(pageNo);
	initList();
}
	        	
</script>

</body>
</html>
