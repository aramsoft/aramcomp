<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OfflineWebList.jsp
  * @Description : 오프라인웹 서비스 목록
  * @Modification Information
  * @
  * @ 수정일                    수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2011.08.25   서형주          최초 생성
  *
  *  @author 서형주 
  *  @since 2011.08.25
  *  @version 1.0
  *  @see
  *  
  */
%>

<html>
<head>
<title>오프라인웹 서비스 목록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/mcomd/egovMcomdAdmin.css" type="text/css">

<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_aram_init_offlineWeb(){

    // 첫 입력란에 포커스..
    var varForm = document.getElementById("offlineWebVO");
    varForm.searchKeyword.focus();
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_offlineWeb();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("offlineWebVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/ows/listOfflineWeb.mdo";
    varForm.submit();
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_aram_search_offlineWeb(){
    var varForm = document.getElementById("offlineWebVO");
    varForm["searchVO.pageIndex"].value = 1;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/ows/listOfflineWeb.mdo";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail_offlineWeb(sn) {       
    var varForm = document.getElementById("offlineWebVO");
    varForm.sn.value = sn;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/ows/detailOfflineWeb.mdo";
    varForm.submit(); 
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist_offlineWeb(){
    var varForm = document.getElementById("offlineWebVO");
    varForm.sn.value = 0;
    varForm.action = "${pageContext.request.contextPath}/mbl/com/ows/registOfflineWeb.mdo";
    varForm.submit(); 
}

</script>
</head>
<body onload="fn_aram_init_offlineWeb();">

<div>

	<!-- header Start -->
	<div id="header">
		<a href="${pageContext.request.contextPath}/mindex.jsp"><span class="btnHome"></span></a>
		<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/h1_logo.png"  /></h1>
		<a href="javascript:fn_aram_detail_OfflineWeblist();"><span class="btnBack"></span></a>
	</div>
	<!-- header End -->
	
	<div id="content" class="contents">
	
		<div class="content_title">
			<h2>오프라인 웹서비스 목록</h2>
		</div>

		<form:form commandName="offlineWebVO" action="" method="post">
			<input name="sn" type="hidden" value="0">
		
			<div id="search_area">
				<div class="button_area">
	      			<span class="button"><a href="#" onclick="javascript:fn_aram_search_OfflineWeb(); return false;"><spring:message code="button.inquire" /></a></span>
	      			<span class="button"><a href="#" onclick="javascript:fn_aram_regist_OfflineWeb(); return false;"><spring:message code="button.create" /></a></span>
				</div>
				<div class="keyword_area">
		   			<form:select path="searchVO.searchCondition" title="조회조건 선택">
				   		<form:option value='' label="--선택하세요--" />
				   		<form:option value="MBER_ID" label="회원ID" />			   
				   		<form:option value="OFFLINE_SJ" label="제목" />			   
				   		<form:option value="OFFLINE_ALL" label="전체 " />			   
			   		</form:select>
   					<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
					<form:select path="searchVO.recordPerPage" class="select" onchange="javascript:fn_aram_search_adressBook(); return false;" title="recordPerPage">
				   		<form:option value="10" label="10" />
				   		<form:option value="20" label="20" />
				   		<form:option value="30" label="30" />
				   		<form:option value="50" label="50" />
					</form:select>
				</div>
			</div>
		
			<form:hidden path="searchVO.pageIndex" />
		</form:form>
			
		<table width="100%" cellpadding="6" class="table-list" border="0" summary="오프라인웹서비스목록을조회한다.">
		<thead>
			<tr>      
			    <th scope="col" width="10%">번호</th>        
			    <th scope="col" width="30%">제목</th>        
			    <th scope="col" width="20%">작성자</th>               
			    <th scope="col" width="20%">생성일시</th>
			</tr>
		</thead>
		
		<tbody>      
		 	<c:if test="${fn:length(resultList) == 0}">
		 	<tr> 
			    <td class="lt_text3" colspan=10>
			        <spring:message code="common.nodata.msg" />
			    </td>
		  	</tr>                                            
		 	</c:if>
		    
 			<c:set var="startIndex" value="${(offlineWebVO.pageIndex-1) * offlineWebVO.recordPerPage}"/>
			<c:forEach items="${resultList}" var="result" varStatus="status">
		  	<tr> 
		 		<c:set var="index" value="${startIndex + status.count}"/>
				<c:set var="reverseIndex" value="${offlineWebVO.totalRecordCount - index + 1}"/>
		        <td class="lt_text3"><c:out value="${reverseIndex}"/></td>                 

		        <td class="lt_text3">
		            <a href="${pageContext.request.contextPath}/mbl/com/ows/detailOfflineWeb.mdo'/>"
		               onclick="javascript:fn_aram_detail_OfflineWeb('<c:out value="${result.sn}"/>'); return false;"><c:out value="${result.offlineSj}"/></a>
		        </td>
		        <td class="lt_text3">
		            <a href="${pageContext.request.contextPath}/mbl/com/ows/detailOfflineWeb.mdo'/>"
		               onclick="javascript:fn_aram_detail_OfflineWeb('<c:out value="${result.sn}"/>'); return false;"><c:out value="${result.mberId}"/></a>
		        </td>
		        <td class="lt_text3">
		            <a href="${pageContext.request.contextPath}/mbl/com/ows/detailOfflineWeb.mdo'/>"
		               onclick="javascript:fn_aram_detail_OfflineWeb('<c:out value="${result.sn}"/>'); return false;"><c:out value="${result.creatDt}"/></a>
		        </td>
		  	</tr>   
			</c:forEach>
		</tbody>  
		</table>
		
		<div align="center">
		    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
		</div>
			
	</div>
	
	<!-- footer Start-->
	<div id="footer">
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer End -->
	
</div>	
</body>
</html>