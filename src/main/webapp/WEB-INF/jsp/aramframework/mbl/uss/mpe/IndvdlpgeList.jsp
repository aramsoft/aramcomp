<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>마이페이지 컨텐츠 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 마이페이지 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:history.back();" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1>컨텐츠목록</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		
		<ul data-role="listview" data-split-icon="insert">
		
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
<!-- 마이페이지 List end -->		
	
<script type="text/javaScript" >

$('#list').bind('pageshow', initList);

$(document).ready(function(){
	$('#btn_detail').toggle(
		function(){ $('btn_detail#detailView').addClass("hidden"); },
		function(){ $('btn_detail#detailView').removeClass("hidden"); }
	);
});
			
function initList() {

	var url = "${pageContext.request.contextPath}/uss/mpe/EgovIndvdlpgeAddCntntsListActor.mdo";
	$.getJSON(url, $('#indvdlPgeCntntsVO').serialize(), function(json) {

		var html = "";
			html += '<form id="indvdlPgeCntntsVO" name="indvdlPgeCntntsVO" method="post">';
			html += '	<input type="hidden" name="pageIndex" id="pageIndex" value=\"'+ json.indvdlPgeCntntsVO.pageIndex +'\"/>';
			html += '</form>';
	
		if(json.indvdlCntntsList.length == 0) {
			html += '<li class="com-egovNodata">';
	   		html += '    등록된 컨텐츠가 없습니다.';
	    	html += '</li>';
		}
		else {
			for ( var i = 0; i < json.indvdlCntntsList.length; i++) {
				
				var indvdlCntnts =  json.indvdlCntntsList[i];
				var lastIndex = indvdlCntnts.cntcUrl.lastIndexOf(".");
				
				if(indvdlCntnts.cntcUrl.substr(lastIndex+1, 3) == "mdo") {
					html += '<li>';
					html += '	<a href="${pageContext.request.contextPath}/uss/mpe/indvdlCntntsPreview.mdo?cntntsId=' + indvdlCntnts.cntntsId +'" id="btn_detail">';
					html += '		<h3>' + indvdlCntnts.cntntsNm + '</h3>';
					html += '		<p class="uss-darkgray"><strong>' + indvdlCntnts.cntntsDc + '</strong> | ' + indvdlCntnts.cntntsLinkUrl + '</p>';
					html += '	</a>';
					html += '	<a href="javascript:fn_insert_cntnts(\'' + indvdlCntnts.cntntsId + '\');">등록</a>';
					html += '		<div id="detailView">';
					html += '		</div>';
					html += '</li>';
				}			
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

function fn_insert_cntnts(cntntsId) {

	var url = "${pageContext.request.contextPath}/uss/mpe/EgovIndvdlpgeAddCntnts.mdo";
	$.getJSON(url, {cntntsId:cntntsId},	function(json) {
		$(location).attr('href', '${pageContext.request.contextPath}/uss/mpe/selectIndvdlpgeResult.mdo');
	});
}

function fn_show_preview(url) {
	if($('#preview').css('display') == "none"){
		$('#preview').css('display', 'block');
		$('#preview').load(url);
	}
	else {
		$('#preview').css('display', 'none');
		$('#preview').unload();
	}
}

</script>
			
</body>
</html>
