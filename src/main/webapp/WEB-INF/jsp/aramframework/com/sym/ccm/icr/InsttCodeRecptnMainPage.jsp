<!doctype html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : InsttCodeRecptnMainPage.jsp
  * @Description : 기관코드수신 목록조회
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>기관코드수신 목록조회</title>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" style="width:200px">

<form name="codeForm" method="post" action="${pageContext.request.contextPath}/sym/ccm/icr/detailInsttCodeRecptn.do">
<table class="table-list" border="0">
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text6" >
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link">
	    <td width="30" onclick="fn_aram_detail('<c:out value="${result.insttCode}"/>'); return false;">
	    	<div class="divDotText" style="width:30px; border:solid 0px;">
				<c:out value="${result.insttCode}"/>
	    	</div>
	    </td>
	    <td>
	    	<c:out value="${result.lowestInsttNm}"/>
	    </td>
	</tr>
	</c:forEach>
</tbody>
</table>
<input name="insttCode" type="hidden"   value="" />
</form>

</DIV>

<script type="text/javascript">

/* ********************************************************
* 상세화면 처리 함수
******************************************************** */
function fn_aram_detail(insttCode){
	var varForm				 = document.codeForm;
	varForm.insttCode.value  = insttCode;
	varForm.submit();
}

</script>

</body>
</html>
