<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : SmsInfoDetail.jsp
 * @Description : 문자메시지 상세조회
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
<DIV id="main">

<div class="content_title">
	<h2>문자메시지 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	       	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="smsVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="smsId" />

<table class="table-detail" summary="등록 및 전송된 문자메시지에 대한 상세정보를 제공합니다.">
<tbody>
	<tr>
	    <th width="25%">
	    	<spring:message code="cop.sms.trnsmitTelno" />
	    </th>
	    <td colspan="3">
	    	<c:out value="${smsVO.trnsmitTelno}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<spring:message code="cop.sms.trnsmitCn" />
	    </th>
	    <td colspan="3">
			<c:out value="${smsVO.trnsmitCn}" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	수신 및 전송결과
	    </th>
	    <td colspan="3">
	   		<ul>
	   			<c:forEach var="recptn" items="${smsVO.recptn}" varStatus="status">
	   			<li>
	   				<c:out value='${recptn.recptnTelno}'/> : (<c:out value='${recptn.resultCode}'/>) <c:out value='${recptn.resultMssage}'/>
	   			</li>
	   			</c:forEach>
	   		</ul>
	    </td>
	</tr>
	<tr>
	    <th width="25%">
	    	전송자
	    </th>
	    <td width="25%">
	   		<c:out value="${smsVO.frstRegisterNm}" />
	    </td>
	    <th width="25%">
	    	전송시간
	    </th>
	    <td width="25%">
	    	<fmt:formatDate value="${smsVO.frstRegisterPnttm}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
    var varForm = document.getElementById("smsVO");
    varForm.action = "${pageContext.request.contextPath}/cop/sms/listSms.do";
    varForm.submit();
}

</script>
