<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>행사/이벤트/캠페인 상세 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

       
</head>

<body>
	
<!-- View start -->
<div id="detail" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_aram_list();" data-icon="arrow-l" >뒤로</a>
	    <h1 id="viewTitle">행사/이벤트/캠페인 상세</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">
			
		<form:form commandName="eventCmpgnVO" method="post">
			<form:hidden path="eventId" value="" />

			<!-- searchVO start -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword" />
			<form:hidden path="searchVO.pageIndex" />
			<!-- searchVO end -->	
			
			<ul class="uss-hpcDetail">

				<li>	
					<span class="uss-tit">행사유형</span>
					<span class="uss-con">
		        		<c:forEach items="${COM035_eventTy}" var="comCodeList" varStatus="status">
							<c:if test="${comCodeList.code eq fn:trim(eventCmpgnVO.eventTyCode)}">	
								<pre><c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" /></pre>
							</c:if>
						</c:forEach>
					</span>
				</li>
				<li>	
					<span class="uss-tit">행사내용</span>
					<span class="uss-con">
	        			<c:out value="${eventCmpgnVO.eventCn}" />
					</span>
				</li>
				
				<li>
					<span class="uss-tit">행사시작일자 </span>
					<span class="uss-con">
						<label class="uss-gray">
							<c:out value="${eventCmpgnVO.eventSvcBeginDe}" />
						</label>
					</span>
				</li>
				<li>
					<span class="uss-tit">행사종료일자 </span>
					<span class="uss-con">
						<label class="uss-gray">
							<c:out value="${eventCmpgnVO.eventSvcEndDe}" />
						</label>
					</span>
				</li>
				<li>
					<span class="uss-tit">서비스이용인원수</span>
					<span class="uss-con">
						<c:out value="${eventCmpgnVO.svcUseNmprCo}" />
					</span>
				</li>
				<li>
					<span class="uss-tit">담당자명 </span>
					<span class="uss-con">								
						<c:out value="${eventCmpgnVO.chargerNm}" />
		      		</span>
				</li>
				<li>
					<span class="uss-tit">준비물내용 </span>
					<span class="uss-con">								
						<c:out value="${eventCmpgnVO.prparetgCn}" />
		      		</span>
				</li>
				<li>
					<span class="uss-tit">승인여부 </span>
					<span class="uss-con">								
						<c:if test="${eventCmpgnVO.eventConfmAt eq 'Y'}">승인</c:if>
 						<c:if test="${eventCmpgnVO.eventConfmAt eq 'N'}">미승인</c:if>
		      		</span>
				</li>
				<li>
					<span class="uss-tit">승인일 </span>
					<span class="uss-con">								
						<c:out value="${eventCmpgnVO.eventConfmDe}" />
		      		</span>
				</li>
			</ul>
			
		</form:form>
		
		<div class="ui-grid-b">
			<div class="ui-block-a"><a href="JavaScript:fn_aram_edit();" data-role="button" data-theme="b">수정</a></div>
			<div class="ui-block-b"><a href='JavaScript:fn_aram_delete();' data-role="button" data-theme="b">삭제</a></div>
			<div class="ui-block-c"><a href='JavaScript:fn_aram_list();' data-role="button" data-theme="b">목록</a></div>
		</div>
			
	</div>
	<!-- contents end -->
	 
	<!-- footer start -->
	<div data-role="footer" ddata-theme="a" ata-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
				
</div>
<!-- view end -->
	
<script type="text/javaScript" >

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/listEventCmpgn.mdo";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("eventCmpgnVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ecc/editEventCmpgn.mdo";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){

	jConfirm('삭제시 행사/이벤트/캠페인, 외부인사정보 \n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?', '알림', 'a', function(r) {
    	if(r){	   			
      		$('#eventCmpgnVO').attr('action', "${pageContext.request.contextPath}/uss/ion/ecc/deleteEventCmpgn.mdo");
			$('#eventCmpgnVO').attr('data-ajax', 'false');
      		$('#eventCmpgnVO').submit();
		}
	});
}
	
</script>

</body>
</html>

