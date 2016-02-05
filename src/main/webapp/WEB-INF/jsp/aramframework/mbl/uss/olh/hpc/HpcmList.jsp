<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>도움말목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

</head>

<body>

<!-- 게시판 List start -->
<div id="list" data-role="page">
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">				    
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>도움말목록조회</h1>	    
	</div>
	<!-- header end -->

	<!-- contents start -->
	<div data-role="content">
	
		<form:form commandName="hpcmManageVO" method="post" data-role="none">
 			<div class="uss-Search">
               	<form:select path="searchCondition" data-role="none">
					<form:option value="" label="--선택하세요--" />
	   				<form:option value="HPCM_DFN" label="도움말정의" />			   
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
	<div data-role="footer"  data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- 게시판 List end -->

<script type="text/javaScript" defer="defer">

$('#list').bind('pageshow', initList);
	
// 목록 function
function initList() {

	var url = "${pageContext.request.contextPath}/uss/olh/hpc/listHpcmJson.mdo";
	$.getJSON(url, $('#hpcmManageVO').serialize(), function(json) {
		
    	$("#searchCondition option[value='" + json.hpcmManageVO.searchCondition + "']").attr('selected', 'selected');

    	var html = "";
        if(json.reusltList.length == 0) {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';
		}
		else {
			 for (var i = 0; i < json.reusltList.length; i++) {
              	 var resultList = json.reusltList[i];
				
              	 html += '<li>';
              	 html += "	<a href='${pageContext.request.contextPath}/uss/olh/hpc/detailHpcm.mdo?hpcmId=" + resultList.hpcmId + "'>";
				 html += '		<h3>' + resultList.hpcmDfn+ '</h3>';
				 html += '		<p><span class="uss-txtBlack">' + resultList.hpcmSeCodeNm  + '</span><span class="uss-txtDate">' + resultList.frstRegisterPnttm + '</span></p>';
              	 html += '	</a>';
              	 html += '</li>';	
              	     
         	}
		}
		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
		
   		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_aram_linkPage"));
  	});
};

// 목록 function
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
