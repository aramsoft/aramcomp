<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>설문참여목록조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

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
	    <h1>설문참여목록조회</h1>
	</div>			
	<!-- header end -->
		
	<!-- contents start -->
	<div data-role="content">
		
		<form:form commandName="qustnrRespondInfoVO" name="qustnrRespondInfoVO" method="post" data-role="none">
			<form:hidden path="qestnrId" />

  			<div class="uss-Search">
               	<form:select path="searchCondition" data-role="none">
					<form:option value='QUSTNR_SJ' label="설문제목" />
                </form:select>
                <div class="uss-SearchBox">
                	<form:input path="searchKeyword" class="type-text" data-role="none"/>
                </div>
               	<input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:initList(); return false;" data-role="none" />
			</div>
			
			<form:hidden path="pageIndex" />
		</form:form>
		
		<ul data-role="listview" data-split-icon="search">
							
		</ul>
		
		<div id="pageNavi" class="com-egovPaging">
			
		</div>
		
	</div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
	
</div>

<script type="text/javascript">

$('#list').bind('pageshow', initList);

function initList() {
				
	var url = "${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfoUserJson.mdo";
	$.getJSON(url, $('#qustnrRespondInfoVO').serialize(), function(json) {
     			
        $("#searchCondition option[value='" + json.qustnrRespondInfoVO.searchCondition + "']").attr('selected', 'selected');

        var html = "";
              
		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg" />';
            html += '</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
				
				var result = json.resultList[i];
				
				html += '<li>'; 
				html += '	<a href="javascript:fn_show_info(\'' + result.qestnrId + '\', \'' + result.qestnrBeginDe + '\', \'' + result.qestnrEndDe + '\');">';
				html += '		<h3>' + result.qestnrSj + '</h3>';
				html += ' 		<p><span class="uss-darkgray">' + result.qestnrBeginDe + ' ~ ' + result.qestnrEndDe + '</span></p>';
				html += '		<p><span class="uss-txtBlack"> ' + result.frstRegisterNm + '</span><span class="uss-txtDate">' + result.frstRegisterPnttm + '</span></p>';
				html += '	</a>';
				html += '	<a href="javascript:fn_show_stat(\'' + result.qestnrId + '\');">통계</a>';
				html += '</li>';			
							
			}
		}
		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');

		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_show_list"));
 	});
 		 	
}

function fn_show_list(pageIndex) {
	$('#pageIndex').val(pageIndex);
	initList();
}

function fn_show_info(qestnrId, sDate, eDate) {

	var date = new Date();
	var day = date.getMonth() + 1;

	day = (day < 10) ? "0" + day : day;
	var curDate = date.getFullYear() + "" + day + "" + ((date.getDate() < 10) ? "0" + date.getDate() : date.getDate());
	curDate = Number(curDate);

	var beginDate = Number(sDate.replace(/-/gi,""));
	var endDate = Number(eDate.replace(/-/gi,""));

	if(curDate>= beginDate && curDate <= endDate){
		
		var url = "${pageContext.request.contextPath}/uss/olp/qri/registQustnrRespondInfoUser.mdo";

		$('#qestnrId').val(qestnrId);
		
		$('#qustnrRespondInfoVO').attr('action', url);
		$('#qustnrRespondInfoVO').attr('data-ajax', 'false');
		$('#qustnrRespondInfoVO').submit();
		
	}
	else {

		jAlert("설문조사 기간이 아닙니다.", "알림", "a");
	}
	
}

function fn_show_stat(qestnrId) {
	var url = "${pageContext.request.contextPath}/uss/olp/qri/statisticsQustnrRespondInfo.mdo";		
	$.mobile.changePage(url,{
		data: {qestnrId: qestnrId},
		type: 'post',
		changeHash: false,
		reloadPage: true
	});
	
}
				
</script>

</body>
</html>
