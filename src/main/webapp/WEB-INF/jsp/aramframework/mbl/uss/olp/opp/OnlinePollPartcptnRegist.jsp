<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>온라인POLL참여 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>

</head>

<body>
	
<!-- View start -->
<div id="regist" data-role="page">
				
	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="javascript:fn_list_page()" data-icon="arrow-l" data-rel="back">뒤로</a>
	    <h1 id="viewTitle">온라인POLL참여 등록</h1>
	</div>
	<!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="com-copContent">
			
		<form:form commandName="onlinePollPartcptnVO" method="post">
			<form:hidden path="pollId" />
			<input name="cmd" type="hidden" value="<c:out value='save'/>"/>

			<!-- 검색조건 유지 -->
			<form:hidden path="searchVO.searchCondition" />
			<form:hidden path="searchVO.searchKeyword" />
			<form:hidden path="searchVO.pageIndex" />
			<!-- 검색조건 유지 -->
			
			<ul class="uss-hpcDetail">

				<li>	
					<span class="uss-tit">POLL명</span>
					<span class="uss-con">
		        		<c:out value="${onlinePollPartcptnVO.pollNm}" />
					</span>
				</li>
				
				<li>
					<span class="uss-tit">POLL시작일자 </span>
					<span class="uss-con">
						<label for="name-d" class="uss-gray">
							<c:out value="${onlinePollPartcptnVO.pollBeginDe}" />
						</label>
					</span>
				</li>
				<li>
					<span class="uss-tit">POLL종료일자 </span>
					<span class="uss-con">
						<label for="name-d" class="uss-gray">
							<c:out value="${onlinePollPartcptnVO.pollEndDe}" />
						</label>
					</span>
				</li>
				<li>
					<span class="uss-tit">POLL종류</span>
					<span class="uss-con">
						<c:forEach items="${pollKindCodeList}" var="resultInfo" varStatus="pollKindStatus">
							<c:if test="${resultInfo.code eq onlinePollPartcptnVO.pollKindCode}">
								<c:out value="${resultInfo.codeNm}" escapeXml="false" />
							</c:if>
						</c:forEach>
					</span>
				</li>
			</ul>
			<div class="uss-poll">
				<fieldset data-role="controlgroup">
					<c:forEach items="${PollItem}" var="resultInfo" varStatus="status">
						<input type="radio" name="pollIemId" id="${status.count}" value="${resultInfo.pollIemId}" <c:if test="${status.count == '1'}">checked</c:if>>
			  				<label for="${status.count}"><c:out value="${resultInfo.pollIemNm}" escapeXml="false" /></label>
					</c:forEach>
				</fieldset>
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-a"><a href="javaScript:fn_aram_save();" data-role="button" data-theme="b" data-ajax="false">등록</a></div>
				<div class="ui-block-b"><a href='javascript:fn_list_page();' data-role="button" data-theme="b">목록</a></div>
			</div>
		</form:form>
	</div>
	<!-- contents end -->
	 
	<!-- footer start -->
	<div data-role="footer" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->
						
</div>
<!-- view end -->
	
<script type="text/javaScript" >
      
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_save(){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm.action =  "${pageContext.request.contextPath}/uss/olp/opp/registOnlinePollPartcptn.mdo";
    varForm.submit();
}

</script>
       
</body>
</html>
