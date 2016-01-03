<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>사용자목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>
		
</head>

<body>

<!-- 사용자 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:history.back();" data-icon="arrow-l" data-rel="back">뒤로</a>
		<h1 id="headerText"></h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		
		<div id="searchview">
			<div class="uss-Search">
				<form id="adressBookUserVO" name="adressBookUserVO" method="post">
                	<select id="searchCondition" name="searchCondition" onchange="javascript:fn_aram_change_titleText();" data-role="none">
                    	<option value="0">사용자목록</option>
                    	<option value="1" selected="selected">명함목록</option>
                	</select>
                	<div class="uss-SearchBox">
                		<input type="text" name="searchKeyword" id="searchKeyword" class="type-text" value="" data-role="none"/>
                	</div>
					<input type="hidden" name="pageIndex" id="pageIndex" value="1"/>
					<input type="hidden" name="mainPageIndex" id="mainPageIndex" value="${adressBookVO.pageIndex}"/>
					<input type="hidden" name="mainSearchKeyword" id="mainSearchKeyword" value="${adressBookVO.searchKeyword}"/>
					<input type="hidden" name="mainSearchCondition" id="mainSearchCondition" value="${adressBookVO.searchCondition}"/>
					<input type="hidden" name="adbkId" id="adbkId" value="${adressBookVO.adbkId}"/>
					<input type="hidden" name="adbkNm" id="adbkNm" value="${adressBookVO.adbkNm}"/>
					<input type="hidden" name="othbcScope" id="othbcScope" value="${adressBookVO.othbcScope}"/>
					<input type="hidden" name="checkCnd" id="checkCnd" value="${checkCnd}"/>
					<input type="hidden" name="checkWord" id="checkWord" value="${checkWord}"/>
					<input type="hidden" name="userIds" id="userIds" value="${userIds}"/>
                	<input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search(); return false;" data-role="none"/>
				</form>
			</div>
		</div>
		
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
<!-- 사용자 List end -->	

<script type="text/javaScript">

$('#list').bind('pageshow', initList);

function initList() {

	var url = "${pageContext.request.contextPath}/cop/adb/listUserPopupJson.mdo";	
   	$.getJSON(url, $('#adressBookUserVO').serialize(), function(json) {
		
		if (json.adressBookUserVO.searchCondition == '0') { 
    		$('#headerText').text('사용자 목록');
		}
		else {
			$('#headerText').text('명함 목록');
		}

		var html = "";

		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';
		} else {
			for ( var i = 0; i < json.resultList.length; i++) {
				
				var result =  json.resultList[i];
				html += '<li class="uss-listView">';

				if (json.adressBookUserVO.searchCondition == '0') {
					if (fn_aram_judge_sameUser(result.emplyrId)) {
						html += '<a href="javascript:fn_aram_add_User(\'' + result.emplyrId + '\')" data-ajax="false">';
					} else {
						html += '<a href="javascript:jAlert(\'이미 등록된 사용자입니다.\', \'정보\', \'a\');">';
					}
					html += '	<p><span class="uss-txtId">' + result.emplyrId + '</span><span class="uss-txtName">' + result.userNm + '</span><span class="uss-txtEmail">[' + result.emailAdres + ']</span></p>';
				}
				else {
					if (fn_aram_judge_sameUser(json.userId, result.emplyrId)) {
						html += '<a href="javascript:fn_aram_add_User(\'' + result.ncrdId + '\')" data-ajax="false">';
					} else {
						html += '<a href="javascript:jAlert(\'이미 등록된 사람입니다.\', \'정보\', \'a\');">';
					}
					html += '	<p><span class="uss-txtId">' + result.ncrdId + '</span><span class="uss-txtName">' + result.nm + '</span><span class="uss-txtEmail">[' + result.emailAdres + ']</span></p>';
				}
				
				html += '		<p><span class="uss-txtHome">집&nbsp;:&nbsp;' + result.homeTelno + '</span><span class="uss-txtPhone">휴대폰&nbsp;:&nbsp;' + result.moblphonNo + '</span></p>';
				html += '		<p><span class="uss-txtHome">회사&nbsp;:&nbsp;' + result.offmTelno + '</span><span class="uss-txtPhone">팩스&nbsp;:&nbsp;' + result.fxnum + '</span></p>';
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

function fn_aram_search(){
	$('#pageIndex').val(1);
	initList();
}

function fn_aram_judge_sameUser(targetId) {
	var baseList = $('#userIds').val();
	var checkId = baseList.split(",");
	
	for(var i = 0; i < checkId.length; i++){
		if(targetId == checkId[i]){
			return false;
		}
	}

	return true;
}

function fn_aram_add_User(addUserId) {
	$('#userIds').val($('#userIds').val() + ',' + addUserId);
	$('#pageIndex').val($('#mainPageIndex').val());
	$('#searchKeyword').val($('#mainSearchKeyword').val());
	$('#searchCondition').val($('#mainSearchCondition').val());
	document.adressBookUserVO.action = "${pageContext.request.contextPath}/cop/adb/insertAdressBookUser.mdo";
	document.adressBookUserVO.submit();
}

function fn_aram_change_titleText() {
	if ($('#searchCondition').val() == '0') { 
		$('#headerText').text('사용자 목록');
	}
	else {
		$('#headerText').text('명함 목록');
	}
}
	        	
</script>

</body>
</html>
