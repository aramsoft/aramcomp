<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : NotificationDetail.jsp
 * @Description : 정보알림이 상세조회
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
	<h2>정보알림이 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="notificationVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="ntfcNo" />

<table class="table-detail" summary="등록된 정보알림이에 대한 상세정보를 제공합니다.">
<tbody>
  	<tr> 
    	<th width="20%">
     		제목
    	</th>
    	<td colspan="3">
    		<c:out value="${notificationVO.ntfcSj}" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		내용
    	</th>
    	<td colspan="3">
			<c:out value="${notificationVO.ntfcCn}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th width="20%">
     		알림시간
    	</th>
    	<td width="30%" class="lt_text3">
   			<c:out value="${notificationVO.ntfcTime}" />
    	</td>
    	<th width="20%">
       		사전알림간격
    	</th>
    	<td width="30%" class="lt_text3">
    		<c:out value="${notificationVO.bhNtfcIntrvlString}" />
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		작성자
    	</th>
    	<td class="lt_text3">
   			<c:out value="${notificationVO.frstRegisterNm}" />
    	</td>
   		<th >
    		작성시간
   		</th>
    	<td class="lt_text3">
    		<fmt:formatDate value="${notificationVO.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/>
    	</td>
  	</tr>
</tbody> 
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("notificationVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/listNotification.do";
	varForm.submit();
}

function fn_aram_edit() {
    var varForm = document.getElementById("notificationVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/editNotification.do";
    varForm.submit();			
}

function fn_aram_delete() {		
    var varForm = document.getElementById("notificationVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/uss/ion/noi/deleteNotification.do";
		varForm.submit();
	}	
}

</script>

</body>
</html>
