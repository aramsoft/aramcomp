<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
 /**
  * @Class Name : NameCardPopup.jsp
  * @Description : 명함 정보 조회	
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *  
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>명함 정보 조회</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>명함정보조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	      	<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
		</span>
	</div>
</div>

<table class="table-register">
	<tr> 
	    <th width="20%">
	    	이름
	    </th>
	    <td width="80%" colspan="3"><c:out value="${nameCardVO.ncrdNm}" /></td>
	</tr>
	<tr> 
	    <th width="20%">
	    	회사명
	    </th>
	    <td width="30%"><c:out value="${nameCardVO.cmpnyNm}" /></td>
	    <th width="20%">
	    	부서명
	    </th>
	    <td width="30%"><c:out value="${nameCardVO.deptNm}" /></td>    
	</tr>
	<tr> 
	    <th>
	    	직위
	    </th>
	    <td><c:out value="${nameCardVO.ofcpsNm}" /></td>
	    <th>
	    	직급
	    </th>
	    <td><c:out value="${nameCardVO.clsfNm}" /></td>    
	</tr>    
	<tr> 
	    <th>
	   		 이메일주소
	   	</th>
	    <td colspan="3"><c:out value="${nameCardVO.emailAdres}" /></td>
	</tr>
	<tr> 
	    <th>
	    	전화번호
	    </th>
	    <td colspan="3">
	    	<c:out value="${nameCardVO.nationNo}" />-<c:out value="${nameCardVO.areaNo}" />-<c:out value="${nameCardVO.middleTelNo}" />-<c:out value="${nameCardVO.endTelNo}" />
	    </td>
	</tr>  
	<tr> 
	    <th>
	   		 휴대폰번호
	   	 </th>
	    <td colspan="3">
	    	<c:out value="${nameCardVO.idntfcNo}" />-<c:out value="${nameCardVO.middleMbtlNum}" />-<c:out value="${nameCardVO.endMbtlNum}" />
	    </td>
	</tr> 
	<tr> 
	    <th>
	    	주소
	    </th>
	    <td colspan="3">${nameCardVO.adres}" /></td>
	</tr>   
	<tr> 
	    <th >
	   		 비고
	   	</th>
	    <td colspan="3"><c:out value="${nameCardVO.remark}" /></td>
	</tr>  
</table>

</DIV>	
</body>
</html>
