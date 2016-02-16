<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AnnvrsryMainPage.jsp
 * @Description : 기념일 목록 메인리스트
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
<title>기념일 목록 메인리스트</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<form:form commandName="annvrsryManageVO" action="" method="post">
<input type="hidden" name="annId" value="">
<table class="table-list" summary="기념일에 대한 목록을 제공한다.(마이페이지용)">	
<tbody>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('${result.annId}'); return false;">
	    <td class="lt_text6" width="100">
		    <div class="divDotText" style="width:100px; border:solid 0px;">
				<c:out value="${result.annvrsryNm}"/>
		    </div>
		</td>
  		<td class="lt_text6" width="100">
        	<c:out value='${fn:substring(result.annvrsryDe, 0,4)}-${fn:substring(result.annvrsryDe, 4,6)}-${fn:substring(result.annvrsryDe, 6,8)}'/>
  			<c:if test="${!empty result.cldrSe }">(<c:if test='${result.cldrSe == "1"}'>양</c:if><c:if test='${result.cldrSe == "2"}'>음</c:if>)</c:if>	
  		</td>
	</tr>
	</c:forEach>		 
</tbody>  	
</table>		
<form:hidden path="searchVO.searchCondition" value="1" />
</form:form>

</DIV>

<script type="text/javascript">

/*설명 : 기념일 안내문 조회 */
function fn_aram_detail(annId) {
    var varForm = document.getElementById("annvrsryManageVO");
    varForm.annId.value = annId;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ans/detailAnnvrsryGdcc.do";
    varForm.submit();   
}

</script>

</body>
</html>