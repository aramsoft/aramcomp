<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ServerDetail.jsp
 * @Description : 서버S/W 상세조회
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
	<h2>서버S/W 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="serverVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="serverId" />
<input type="hidden" name="serverEqpmnCount" value="<c:out value="${serverEqpmnRelateDetailCount}"/>" />

<table class="table-detail" summary="서버S/W를 상세조회 한다.">
<caption>서버S/W 상세조회</caption>
	<tr>
	  	<th width="20%">
	  		서버S/W ID
	  	</th>
	  	<td>
	  		<c:out value='${serverVO.serverId}'/>
	  	</td>
	</tr>
	<tr>
  		<th>
  			서버S/W 명
  		</th>
  		<td>
  			<c:out value='${serverVO.serverNm}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			서버S/W 종류
  		</th>
  		<td>
  			<c:out value='${serverVO.serverKndNm}'/>
  		</td>
	</tr>
	<tr>
  		<th>
  			등록일자
  		</th>
  		<td>
	    	<c:out value="${fn:substring(serverVO.regstYmd, 0, 4)}-${fn:substring(serverVO.regstYmd, 4, 6)}-${fn:substring(serverVO.regstYmd, 6, 8)}"/>
  		</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="strServerNm" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>서버H/W 목록</h2>
</div>

<table class="table-list" summary="서버H/W에 대한 목록을 제공한다.">
<thead>
  	<tr>
    	<th width="15%">서버H/W 명</th>
    	<th width="15%">서버H/W IP</th>
    	<th width="35%">CPU정보</th>
    	<th width="35%">메모리정보</th>
  	</tr>
  	<tr>
    	<th width="15%">관리자</th>
    	<th width="15%">OS정보</th>
    	<th width="35%">하드디스크</th>
    	<th width="35%">기타정보</th>
  	</tr>
 </thead>
 <tbody>
 	<c:forEach var="result" items="${serverEqpmnRelateDetailList}" varStatus="status">
  	<tr>
	    <td class="lt_text3"><c:out value="${result.serverEqpmnNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.serverEqpmnIp}"/></td>
	    <td class="lt_text3"><c:out value="${result.cpuInfo}"/></td>
	    <td class="lt_text3"><c:out value="${result.moryInfo}"/></td>
  	</tr>
  	<tr>
	    <td class="lt_text3"><c:out value="${result.serverEqpmnMngrNm}"/></td>
	    <td class="lt_text3"><c:out value="${result.opersysmInfo}"/></td>
	    <td class="lt_text3"><c:out value="${result.hdDisk}"/></td>
	    <td class="lt_text3"><c:out value="${result.etcInfo}"/></td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("serverVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServer.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit() {
    var varForm = document.getElementById("serverVO");
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/editServer.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("serverVO");

    if(varForm.serverEqpmnCount.value> 0) {
        alert("서버장비가 등록되어 삭제할 수 없습니다.");
        return;
    }

	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/deleteServer.do";
        varForm.submit();
    }
}

</script>
