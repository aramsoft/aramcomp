<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name  : CntcInsttDetail.jsp
  * @Description : 연계기관 상세조회
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
	<h2>연계기관 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit_cntcInstt(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete_cntcInstt(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_cntcInstt(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cntcInsttVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="insttId" />

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

<table class="table-detail">
  	<tr>
    	<th width="20%">
    		연계기관ID
    	</th>
    	<td width="80%">
    		<c:out value="${cntcInsttVO.insttId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		연계기관명
    	</th>
    	<td>
    		<c:out value="${cntcInsttVO.insttNm}"/>
    	</td>
  	</tr>
</table>


<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>연계시스템</h2>
</div>

<div id="search_area">
	<div class="button_area">
    	<span class="button"><a href="#" onclick="javascript:fn_aram_regist_cntcSystem(); return false;"><spring:message code="button.create" /></a></span>
	</div>
</div>

<form:form commandName="cntcSystemVO" action ="" method="post">
<form:hidden path="insttId" />
<input type="hidden" name="sysId">
<input type="hidden" name="svcId">

</form:form>

<table class="table-list">
<thead>
	<tr>
		<th width="30%">연계시스템</th>
		<th>연계서비스</th>
	</tr>
</thead>

<tbody>
	<c:if test="${fn:length(cntcSystemList) == 0}">
	<tr>
		<td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>

	<c:forEach items="${cntcSystemList}" var="resultSystemInfo" varStatus="status">
	<tr>
		<td>
		<table class="table-list">
			<tr>
				<th width="60">순번</th>
				<td width="120" class="lt_text3"><c:out value="${(cntcSystemVO.pageIndex - 1) * cntcSystemVO.pageSize + status.count}"/></td>
			</tr>
			<tr>
				<th width="60">시스템ID</th>
				<td width="120" class="lt_text3"><c:out value="${resultSystemInfo.sysId}"/></td>
			</tr>
			<tr>
				<th width="60">시스템명</th>
				<td width="120" class="lt_text"><c:out value="${resultSystemInfo.sysNm}"/></td>
			</tr>
			<tr>
				<th width="60">시스템IP</th>
				<td width="120" class="lt_text"><c:out value="${resultSystemInfo.sysIp}"/></td>
			</tr>
			<tr>
				<td colspan="2">
				<table>
				<tr>
					<td>
				   		<span class="button">
				   		<a href="#" onclick="javascript:fn_aram_edit_cntcSystem('<c:out value="${resultSystemInfo.sysId}"/>'); return false;">
				   			<spring:message code="button.update" />
				   		</a>
				   		</span>
					</td>
		
					<td width="1"></td>
					<td>
				   		<span class="button">
				   		<a href="#" onclick="javascript:fn_aram_delete_cntcSystem('<c:out value="${resultSystemInfo.sysId}"/>'); return false;">
				   			<spring:message code="button.delete" />
				   		</a>
				   		</span>
					</td>
		
					<td width="1"></td>
					<td>
				   		<span class="button">
				   		<a href="#" onclick="javascript:fn_aram_regist_cntcService('<c:out value="${resultSystemInfo.sysId}"/>'); return false;">
				   			연계서비스등록
				   		</a>
				   		</span>
					</td>
				</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>

		<td valign="top">
		<table class="table-list">
		<thead>
			<tr>
				<th width="30%">서비스명</th>
				<th width="20%">요청메시지</th>
				<th width="20%">응답메시지</th>
				<th width="30%">처리</th>
			</tr>
		</thead>
	
		<tbody>
			<c:if test="${fn:length(cntcServiceList) == 0}">
				<tr>
					<td class="lt_text3" colspan="5">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>
			</c:if>
			
			<c:forEach items="${cntcServiceList}" var="resultServiceInfo">
			<c:if test="${resultSystemInfo.sysId == resultServiceInfo.sysId}">
			<tr>
				<td class="lt_text"><c:out value="${resultServiceInfo.svcNm}"/></td>
				<td class="lt_text3">
					<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
						<c:if test="${result.cntcMessageId == resultServiceInfo.requestMessageId}"><c:out value="${result.cntcMessageNm}"/></c:if>
					</c:forEach>
				</td>
				<td class="lt_text3">
					<c:forEach var="result" items="${cntcMessageList}" varStatus="status">
						<c:if test="${result.cntcMessageId == resultServiceInfo.rspnsMessageId}"><c:out value="${result.cntcMessageNm}"/></c:if>
					</c:forEach>
				</td>

				<td>
				<table >
				<tr>
					<td>
				   		<span class="button">
				   		<a href="#" onclick="javascript:fn_aram_edit_cntcService('<c:out value="${resultSystemInfo.sysId}"/>','<c:out value="${resultServiceInfo.svcId}"/>'); return false;">
				   			<spring:message code="button.update" />
				   		</a>
				   		</span>
					</td>
	
					<td width="1"></td>
					<td>
				   		<span class="button">
				   		<a href="#" onclick="javascript:fn_aram_delete_cntcService('<c:out value="${resultSystemInfo.sysId}"/>','<c:out value="${resultServiceInfo.svcId}"/>'); return false;">
				   			<spring:message code="button.delete" />
				   		</a>
				   		</span>
					</td>
				</tr>
				</table>
				</td>
			</tr>
			</c:if>
			</c:forEach>
	
		</tbody>
		</table>

		</td>
	</tr>
	</c:forEach>
	
</tbody>
</table>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list_cntcInstt(){
    var varForm = document.getElementById("cntcInsttVO");
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/listCntcInstt.do";
    varForm.submit();
}

/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fn_aram_edit_cntcInstt(){
    var varForm = document.getElementById("cntcInsttVO");
	varForm.action  = "${pageContext.request.contextPath}/ssi/syi/iis/editCntcInstt.do";
	varForm.submit();
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_aram_delete_cntcInstt(){
    var varForm = document.getElementById("cntcInsttVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action	= "${pageContext.request.contextPath}/ssi/syi/iis/deleteCntcInstt.do";
		varForm.submit();
	}
}

/* ********************************************************
* 연계시스템 등록 화면으로  바로가기
******************************************************** */
function fn_aram_regist_cntcSystem(){
    var varForm = document.getElementById("cntcSystemVO");
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/registCntcSystem.do";
	varForm.submit();
}

/* ********************************************************
* 연계시스템 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit_cntcSystem(sysId){
    var varForm = document.getElementById("cntcSystemVO");
	varForm.sysId.value	= sysId;
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/editCntcSystem.do";
	varForm.submit();
}

/* ********************************************************
* 연계시스템 삭제 처리 함수
******************************************************** */
function fn_aram_delete_cntcSystem(sysId){
    var varForm = document.getElementById("cntcSystemVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.sysId.value	= sysId;
		varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/deleteCntcSystem.do";
		varForm.submit();
	}
}

/* ********************************************************
* 연계서비스 등록화면으로  바로가기
******************************************************** */
function fn_aram_regist_cntcService(sysId){
    var varForm = document.getElementById("cntcSystemVO");
	varForm.sysId.value	= sysId;
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/registCntcService.do";
	varForm.submit();
}

/* ********************************************************
* 연계서비스 수정화면으로  바로가기
******************************************************** */
function fn_aram_edit_cntcService(sysId, svcId){
    var varForm = document.getElementById("cntcSystemVO");
	varForm.sysId.value	= sysId;
	varForm.svcId.value	= svcId;
	varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/editCntcService.do";
	varForm.submit();
}

/* ********************************************************
* 연계서비스 삭제 처리 함수
******************************************************** */
function fn_aram_delete_cntcService(sysId, svcId){
    var varForm = document.getElementById("cntcSystemVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.sysId.value	= sysId;
		varForm.svcId.value	= svcId;
		varForm.action = "${pageContext.request.contextPath}/ssi/syi/iis/deleteCntcService.do";
		varForm.submit();
	}
}

</script>
