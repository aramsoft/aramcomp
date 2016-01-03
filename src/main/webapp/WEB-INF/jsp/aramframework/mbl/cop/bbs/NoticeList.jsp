<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>${boardVO.boardMasterVO.bbsNm}</title> 

<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

<!-- datebox  import-->        
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/datepicker/jqm-datebox.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.calbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.datebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jqm-datebox.mode.flipbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/datepicker/jquery.mobile.datebox.i18n.ko.utf8.js"></script> 

<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/com/cop/bbs/EgovBBSMng.js" ></script>

<script type="text/javaScript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);

	varForm.pageIndex.value = pageNo;
	varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/" + bbsId + "/articles";
	varForm.submit();	
}

function fn_aram_search_notice() {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);
	
	varForm.pageIndex.value = '1';
	varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/" + bbsId + "/articles";
	varForm.submit();	
}

//상세조회
function fn_aram_detail_notice(nttId) {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);

	varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/"+bbsId+"/article/"+nttId;
    varForm.submit();			
}	

function fn_aram_regist_notice() {
    var varForm = document.getElementById("boardVO");
	var bbsId = fn_aram_get_idString(varForm.bbsId.value);
	
    varForm.action = "${pageContext.request.contextPath}/content/mbl/board${prefix}/"+bbsId+"/article/registform";
    varForm.submit();		
}

</script>
</head>

<body>
<!-- 메인 페이지 -->

<div data-role="page" >

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
		<a href="${pageContext.request.contextPath}/mindex.jsp" data-icon="home" rel="external">홈</a>			
		<h1 class="bodLogo">${boardVO.boardMasterVO.bbsNm}</h1>
		<a href="#" onclick="javascript:fn_aram_regist_notice(); return false;" data-icon="plus">등록</a>
	</div>
	<!-- header end -->
			
	<!-- contents start -->
    <div data-role="content">
    	<!-- 장규완 추가. submit 전달항목 -->

		<form:form commandName="boardVO" action ="" method="post">

			<form:hidden path="bbsId" />
			<form:hidden path="pageIndex" />

			<div class="uss-Search">
				<form:select path="searchCondition" title="검색조건선택" data-role="none">
			   		<form:option value="NTT_SJ" label="제목" />
			   		<form:option value="NTT_CN" label="내용" />
			   		<c:if test="${anonymous != 'true'}">
			   		<form:option value="USER_NM" label="작성자" />
			   		</c:if>
				</form:select>
	            <div class="uss-SearchBox">
  					<form:input path="searchKeyword" class="type-text" data-role="none"  />
				</div>
	            <input type="button" value="조회" class="uss-SearchBtn" onclick="javascript:fn_aram_search_notice(); return false;" data-role="none" />
			</div>
		</form:form>
		
		<ul data-role="listview">
			<c:choose>
			<c:when test="${empty resultList}">
				<li class="com-egovNodata">
              		<spring:message code="common.nodata.msg"/>
              	</li>			
			</c:when>
			<c:otherwise>
				<c:forEach var="result" items="${resultList}">
					<c:choose>
		    		<c:when test="${result.isExpired=='Y' || result.useAt == 'N'}">
			    		<li>
			    			<h3>
			    				<c:out value="${result.nttSj}" />
			    			</h3>
						</li>
		    		</c:when>
		    		<c:otherwise>
						<li>
							<a href="javascript:fn_aram_detail_notice('<c:out value="${result.nttId}"/>')" data-transition="slide">
								<h3>
									<c:if test="${result.threadDepth!=0}">
						    		<c:forEach begin="0" end="${result.threadDepth}" step="1">
						    		</c:forEach>
					    			<span><img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/reply_arrow.gif" alt="" /></span>
						    		</c:if>
						            <c:out value="${result.nttSj}"/><span class="ui-li-count">${result.rdcnt}</span>
								</h3>
								<p>
					            	<c:choose>
									<c:when test="${result.ntcrNm == ''}">
										<span class="uss-txtBlack"><c:out value="${result.frstRegisterNm}" /></span>
									</c:when>
									<c:otherwise>
									    <span class="uss-txtBlack"><c:out value="${result.ntcrNm}" /></span>
									</c:otherwise>
									</c:choose>
									<span class="uss-txtDate"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></span>
									<!-- <span class="uss-txtBlack">${qnaManage.wrterNm }</span><span class="uss-txtDate">${qnaManage.writngDe }</span> -->
								</p>
							</a>
						</li>
					</c:otherwise>
					</c:choose>
				</c:forEach>					
			</c:otherwise>
			</c:choose>
		</ul>
	
		<div id="pageNavi" class="com-egovPaging">
			<ui:pagination paginationInfo="${paginationInfo}" type="mblImage" jsFunction="fn_aram_linkPage" />
		</div>
		
    </div>
	<!-- contents end -->
	
	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>

</body>
</html>
