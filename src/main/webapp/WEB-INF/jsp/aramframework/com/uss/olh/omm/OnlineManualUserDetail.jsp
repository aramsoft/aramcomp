<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlineManualUserDetail.jsp
  * @Description : 온라인메뉴얼 상세조회
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
<DIV id="main">

<div class="content_title">
	<h2>온라인메뉴얼 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!--  등록  폼 영역  -->
<form:form commandName="onlineManualVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="onlineMnlId" />

<table class="table-detail">
  	<tr>
    	<th width="20%">
    		온라인메뉴얼명
    	</th>
    	<td width="80%">
    		<c:out value="${onlineManualVO.onlineMnlNm}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		온라인메뉴얼구분
    	</th>
    	<td>
 			<c:forEach items="${COM041_onlineMnlSe}" var="comCode" varStatus="pollKindStatus">
			<c:if test="${comCode.code eq onlineManualVO.onlineMnlSeCode}">
				<c:out value="${comCode.codeNm}" />
			</c:if>
			</c:forEach>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		온라인메뉴얼정의
    	</th>
    	<td>
		 	<c:out value="${onlineManualVO.onlineMnlDfn}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		온라인메뉴얼설명
    	</th>
    	<td>
      		<form:textarea path="onlineMnlDc" class="textarea" title="온라인메뉴얼설명" cols="90" rows="20"  readonly="true" />            
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlineManualVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/omm/listOnlineManualUser.do";
    varForm.submit();
}

</script>
