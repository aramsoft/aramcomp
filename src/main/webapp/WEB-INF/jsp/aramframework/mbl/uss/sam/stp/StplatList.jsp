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
<title>약관목록 조회</title>

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

	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
	    <h1>약관목록 조회</h1>		    
	    <a href="${pageContext.request.contextPath}/uss/umt/StplatCnfirmMber.mdo" data-icon="arrow-left">약관확인</a>
	</div>

	<div data-role="content">
	
		<form:form commandName="stplatManageVO" action ="" method="post">
			<div class="uss-Search">
				<form:select path="searchCondition"  data-role="none">
			   		<form:option value="USE_STPLAT_NM" label="이용약관명" />
			   		<form:option value="USE_STPLAT_CN" label="이용약관내용" />
		   		</form:select>
	            <div class="uss-SearchBox">
	                <form:input path="searchKeyword"  class="type-text" data-role="none" />
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
<!-- 게시판 List end -->

<script type="text/javaScript" >

$('#list').bind('pageshow', initList);

// 목록 function
function initList() {

	var url = "${pageContext.request.contextPath}/uss/sam/stp/listStplatJson.mdo"; 
	$.getJSON(url, $('#stplatManageVO').serialize(), function(json) {
		 
		$("#searchCondition option[value='" + json.stplatManageVO.searchCondition + "']").attr('selected', 'selected');

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
              	 html += "	   <a href='${pageContext.request.contextPath}/uss/sam/stp/detailStplat.mdo?useStplatId=" + resultList.useStplatId + "'>";
				 html += '       <h3>' + resultList.useStplatNm + '</h3>';
				 html += '       <p><span class="uss-gray">' + resultList.frstRegisterPnttm + '</span></p>';
              	 html += '    </a>';
                 html += '</li>';	
         	 }
		}
		$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
		
   		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_aram_linkPage"));
  	});
};

function fn_aram_linkPage(pageIndex) {
	$('#pageIndex').val(pageIndex);
	initList();
}

function fn_aram_listPage() {
	$.mobile.changePage( $("#list"), { transition: "slideup" });
}

// 약관 확인 화면에서 사용함
function fnAgree(){
	 
   var checkField = $('input[name=checkField]:checked');
      	
   if(checkField.length < 2) {
       jAlert("약관에 동의하지 않으면 회원으로 가입할 수 없습니다.", "알림", "a");
       return;
   }	                
         
   jAlert("약관 동의 하였습니다.", "알림", "a", function(){
       $('#stplatList').attr('action', "${pageContext.request.contextPath}/uss/sam/stp/listStplat.mdo");
       $('#stplatList').attr('data-ajax', "false");
       $('#stplatList').submit();
   });
}

</script>

</body>
</html>
