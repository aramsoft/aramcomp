<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlineManualDetail.jsp
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
<%pageContext.setAttribute("crlf", "\r\n"); %>

<DIV id="main">

<div class="content_title">
	<h2>온라인메뉴얼 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
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
    		<c:out value="${onlineManualVO.onlineMnlNm}" escapeXml="false" />
		</td>
  	</tr> 
   	<tr> 
   	 	<th>
   	 		온라인메뉴얼구분
   	 	</th>
    	<td>
 			<c:forEach items="${COM041_onlineMnlSe}" var="comCode" varStatus="pollKindStatus">
			<c:if test="${comCode.code eq onlineManualVO.onlineMnlSeCode}">
				<c:out value="${comCode.codeNm}" escapeXml="false" />
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
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("onlineManualVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/omm/listOnlineManual.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("onlineManualVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/omm/editOnlineManual.do";;
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("onlineManualVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/omm/deleteOnlineManual.do";
		varForm.submit();
	}
}

</script>
