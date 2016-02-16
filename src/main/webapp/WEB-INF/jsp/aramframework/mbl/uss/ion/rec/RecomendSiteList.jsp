<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>추천사이트 목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 추천사이트 List start -->
<div id="list" data-role="page">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>추천사이트 목록조회</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content">
		
		<form:form commandName="recomendSiteVO" method="post" data-role="none">
 			<div class="uss-Search">
               	<form:select path="searchVO.searchCondition" data-role="none">
					<form:option value="" label="--선택하세요--" />
			   		<form:option value="RECOMEND_SITE_NM" label="추천사이트명" />			   
			   		<form:option value="RECOMEND_SITE_URL" label="추천사이트 URL" />			   
		        </form:select>
	        	<div class="uss-SearchBox">
               		<form:input path="searchVO.searchKeyword" class="type-text" data-role="none" />
	         	</div>
	          	<input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_linkPage(1); return false;" data-role="none" />
			</div>
			
			<form:hidden path="searchVO.pageIndex" />
 		</form:form>
		
		<ul data-role="listview">
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
		</div>
		
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c)2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>
<!-- 추천사이트 List end -->		
	
<script type="text/javaScript" >

$('#list').bind('pageshow', initList);

function initList() {
				
	var url = "${pageContext.request.contextPath}/uss/ion/rec/listRecomendSiteJson.mdo";
	$.getJSON(url, $('#recomendSiteVO').serialize(), function(json) {
        			
       	$("#searchCondition option[value='" + json.recomendSiteVO.searchCondition + "']").attr('selected', 'selected');
 
		var html = "";
		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
				
				var result =  json.resultList[i];
				var date = (result.confmDe) == "" ? "" : (result.confmDe.substring(0, 4) + '-' + result.confmDe.substring(4, 6) + '-' + result.confmDe.substring(6, 8));
				var confmAt = result.recomendConfmAt == "Y" ? "승인" : "미승인";
				
				html += '<li>';
				html += '	<a href="${pageContext.request.contextPath}/uss/ion/rec/detailRecomendSite.mdo?recomendSiteId=' + result.recomendSiteId +'">';
				html += '		<h3>' + result.recomendSiteNm + '</h3>';
				html += '		<p>' + result.recomendSiteUrl + '</p>';
				html += '		<p><span class="uss-txtBlue">' + confmAt + '</span><span class="uss-txtDate"> ' + date + '</span></p>';
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
