<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : MobileKoreaGovPortalSearchResultList.jsp
 * @Description : 대한민국정부포털 검색 서비스 화면
 * @Modification Information
 * @
 * @ 수정일                    수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2011.08.11   조재만          최초 생성
 *
 *  @author 모바일 신규공통컴포넌트개발팀 조재만
 *  @since 2011.08.11
 *  @version 1.0 
 *  @see
 *  
 */
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>OPEN-API연계서비스 - 대한민국정부포털 검색</title>

<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js'/>"></script>

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/oas/oas.js"></script>

<script type="text/javaScript" language="javascript" defer="defer">
<!--		

$('#list').live('pageshow', initList);

/*********************************************************
 * 검색
 ******************************************************** */
function initList() {
	$('#searchConditionText').text(fn_aram_get_conditionNm($('#searchCondition').val()));

    $.getJSON("${pageContext.request.contextPath}/mbl/com/oas/selectKoreaGovPortalSearchResultList.mdo", $('#searchVO').serialize(), function(json){
             	
    	var collectionSet = ["site" , "service" , "web" , "newstoday" , "pubword" , "mobapp" , "mobweb" , "law", "map"];
    	var collectionName = ["사이트" , "서비스" , "웹문서" , "뉴스" , "행정용어" , "모바일앱" , "모바일웹" , "법령", "서비스맵"];
             
        // 검색 조건 콤보박스 생성
        var html = "";

		for (var i = 0; i < collectionSet.length; i++) {
			html += '<li><span>';
			html += '	<a href="javascript:fn_aram_search_choice(\'' + collectionSet[i] +'\', \'' + collectionName[i] + '\');" data-role="none">' + collectionName[i] + '</a>';
			html += '</span></li>';
	    }

		$('#searchSelect').html(html);
 
        if ($('#searchOrder').val() == '0') {
           	$("#chkSearchOrder").attr('checked', 'checked');
        }

        html = "";

        // 검색 결과 출력 부분
        // 최초 페이지 로딩 시
		if(json.resultCnt == null) {
			html += '';
			$('#pageNavi').hide();
		// 검색 결과가 없을 시
		} else if(json.resultCnt == '0') {
			html += '<li class="com-egovNodata">';
            html += '    <spring:message code="common.nodata.msg"/>';
            html += '</li>';

            $('#explain').hide();
            $('#pageNavi').show();
           // 검색 결과가 존재할 시
		} else {
			html += '<p class="inquiry">' + fn_aram_get_conditionNm(json.searchVO.searchCondition) + ' <span>['  + + json.resultCnt + '건]</span></p>';
			
			for ( var i = 0; i < json.titleList.length; i++) {
                         
				// 행정용어 검색 결과 출력
                if (json.searchVO.searchCondition == 'pubword') {
                	html += '<li>';
                	html += '	<a href="javascript:fn_aram_detail_administWordDetail(\'' + json.titleList[i] + '\', \'' + json.contentsList[i] + '\');">';
                	html += '   	<h3>' + json.titleList[i] + '</h3>';
                    html += ' 		<p class="pubword">' + json.contentsList[i] + '</p>';
                    html += '	</a>';
                    html += '</li>'; 
               	
                // 모바일앱 검색 결과 출력
                } else if (json.searchVO.searchCondition == 'mobapp') {
                	 html += '<li>';
                	 html += '   <a href="' + json.urlList[i] +'" data-ajax="false" target="_blank">';
                	 html += '   	<h3>' + json.titleList[i] + '</h3>';
                     html += '      <p class="mcom-searchOS">지원 OS : ' + json.platformList[i] + '</p>';
                     html += '   	<p class="mcom-searchDate">등록일 : ' + json.registdateList[i].substr(0, 4) + '.' + json.registdateList[i].substr(4, 2) + '.' + json.registdateList[i].substr(6, 2) + ' / 수정일 : ' + json.modifydateList[i].substr(0, 4) + '.' + json.modifydateList[i].substr(4, 2) + '.' + json.modifydateList[i].substr(6, 2) + '</p>';
                     html += '		<span class="ui-li-count">' + json.downloadsList[i] + '</span>';
                     html += '   </a>';
                     html += '</li>'; 
                     
                // 일반 검색 결과 출력
                } else {
                	html += '<li>';
                	html += '   <a href="' + json.urlList[i] +'" data-ajax="false" target="_blank">';
               	 	html += '       <h3>' + json.titleList[i] + '</h3>';
                    html += '       <p class="adminTerms">' + json.contentsList[i] + '</p>';
                    html += '   <p class="mcom-searchUrl">' + json.urlList[i] + '</p>';
                    html += '   </a>';
                    html += '</li>'; 
                }
            }

			$('#explain').hide();
			$('#pageNavi').show();
		}

		$('#searchCondition').val(json.searchVO.searchCondition);

		$("#recordPerPage option[value='" + json.searchVO.recordPerPage + "']").attr('selected', 'selected');
        $('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
        $('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_show_list"));
         
	});
}

/*********************************************************
 * 페이지 이동
 ******************************************************** */
function fn_show_list(pageIndex) {
	if ($('#chkSearchOrder').attr('checked') == 'checked') {
        $('#searchOrder').val('0');
    } else {
        $('#searchOrder').val('1');
    }

    $('#pageIndex').val(pageIndex);
    initList();
}

/*********************************************************
 * 검색조건 제어
 ******************************************************** */
function fn_aram_search_choice(searchCondition, searchConditionText){
	$('#searchCondition').val(searchCondition);
	$('#searchConditionText').text(searchConditionText);		
    $('#searchSelect').toggle('selectDisplay');
}

/*********************************************************
 * 검색조건 toggle
 ******************************************************** */
function fn_aram_toggle_searchSelect(){
     $('#searchSelect').toggle('selectDisplay');
}

/*********************************************************
 * 행정용어 상세조회
 ******************************************************** */
function fn_aram_detail_administWordDetail(title, contents) {
	$.mobile.changePage( "${pageContext.request.contextPath}/mbl/com/oas/goAdministrationWordDetail.mdo", {
		type: "post",
		data: {title: title, contents: contents},
		reloadPage: true,
		transition: "slide"
	});
}

      	-->
</script>
</head>

<body>

<!-- 대한민국정부포털 검색 결과  List start -->
<div id="list" data-role="page" data-theme="d">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>대한민국정부포털 검색 서비스</h1>
	</div>
    <!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="egov-mcomdContent">
		<form id="searchVO" name="searchVO" method="post">
			<div id="searchView">
            	<div id="search">
					<fieldset>
						<div class="search-wrap">
							<div class="select-box">
								<a href="javascript:fn_egov_toggle_searchSelect();">
									<span id="searchConditionText">
											
									</span>
								</a>
							</div>
							<input type="text" name="searchKeyword" data-role="none" class="q" autocomplete="off" value="${searchVO.searchKeyword}" />
							<input type="hidden" name="searchCondition" id="searchCondition" value="${searchVO.searchCondition}"/>
				   			<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex}"/>
				   			<input type="hidden" name="searchOrder" id="searchOrder" value="" />
						</div>
						<button type="button" id="searchSubmit" data-role="none" onclick="javascript:fn_show_list(1); return false;"><span><strong>검색</strong></span></button>							
					</fieldset>
				</div>
				<div class="searchTerms">
					<div class="termsLeft">
	           			<input type="checkbox" name="chkSearchOrder" id="chkSearchOrder" data-role="none">
	           			<label for="chkSearchOrder">정확도 역순</label>
	            	</div>
	            	<div class="termsRight">
	            		<select id="recordPerPage" name="recordPerPage" data-role="none">
	                		<option value="1">1건 검색</option>
	                		<option value="5">5건 검색</option>
                    		<option value="10">10건 검색</option>
	                	</select>
	            	</div>
                </div>
            </div>
            <ul class="select-box-options" id="searchSelect" data-role="none" style="position:absolute;top:130px;left:14px;z-index:999;display:none;">
	            
	        </ul>
		</form>
		<ul data-role="listview" class="listview">

		</ul>
		<div id="explain">
			<div class="mcomd-openApi">
				<dl>
					<dt><img src="${pageContext.request.contextPath}/images/egovframework/mbl/com/oas/img_api.gif" style="max-width:100%" /></dt>
					<dd><span class="blue"><strong>Open API란?</strong></span><br>
					<span class="green">"플랫폼으로서의 웹" 이라는 특징을 기술적으로 구현한
					대표적인 Web 2.0의 기술</span>입니다.
					하나의 웹 사이트에서 자신이 가진 기능을 이용할 수 있도록
					공개한 프로그래밍 인터페이스를 OpenAPI라고 합니다. 
					대한민국정부에서 제공되는 서비스 중 아래의 사항을 지원하여 드립니다.</dd>
				</dl>
			</div>
			<div class="weaterService">
				<h3>제공 서비스 목록</h3>
				<ul class="weaterSerList">
					<li>사이트</li>
					<li>서비스</li>
					<li>웹문서</li>
					<li>뉴스</li>
					<li>행정용어</li>
					<li>모바일맵</li>
					<li>모바일웹</li>
					<li>법령</li>
					<li>서비스맵</li>
				</ul>
			</div>
		</div>
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
<!-- 대한민국정부포털 검색 결과 List end -->		
	
</body>
</html>