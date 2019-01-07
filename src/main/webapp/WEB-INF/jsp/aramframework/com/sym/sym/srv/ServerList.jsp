<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : ServerList.jsp
 * @Description : 서버S/W 목록
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
	<h2>서버S/W 목록</h2> 
</div>

<form:form commandName="serverVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type=hidden name="serverId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
 		<label for="strServerNm">서버S/W 명 : </label>
    	<form:input path="strServerNm" size="30" maxlength="30" title="검색어 입력" onkeypress="press();"/> 
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="서버정보에 대한 목록을 제공한다.">
<caption>서버S/W 목록</caption>
<thead>
  	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col" width="20%">서버S/W ID</th>
	    <th scope="col"            >서버S/W 명</th>
	    <th scope="col" width="20%">서버S/W 종류</th>
	    <th scope="col" width="10%">등록일자</th>
	    <th scope="col" width="10%">서버등록</th>
  	</tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr>
        <td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
    </tr>
    </c:if>
    
 	<c:set var="searchVO" value="${serverVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
 	<c:forEach var="result" items="${resultList}" varStatus="status">
  	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('${result.serverId}'); return false;">
				${result.serverId}
			</a>
			</span>
    	</td>
    	<td class="lt_text3"><c:out value="${result.serverNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.serverKndNm}"/></td>
    	<td class="lt_text3">
	    	<c:out value="${fn:substring(result.regstYmd, 0, 4)}-${fn:substring(result.regstYmd, 4, 6)}-${fn:substring(result.regstYmd, 6, 8)}"/>
    	</td>
    	<td class="lt_text3">
    		<a href="#" onclick="javascript:fn_aram_relate('<c:out value="${result.serverId}"/>'); return false;">
    			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" width="15" height="15" alt="">
    		</a>
    	</td>
  	</tr>
 	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press() {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("serverVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServer.do";
    varForm.submit();
}

function fn_aram_search(){
    var varForm = document.getElementById("serverVO");
    varForm.pageIndex.value = '1';
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/listServer.do";
    varForm.submit();
}

function fn_aram_detail(serverId) {
    var varForm = document.getElementById("serverVO");
    varForm.serverId.value = serverId;
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/detailServer.do";
    varForm.submit();   
}

function fn_aram_regist() {
    var varForm = document.getElementById("serverVO");
    varForm.serverId.value = "";
    varForm.action = "${pageContext.request.contextPath}/sym/sym/srv/registServer.do";
    varForm.submit(); 
}

function fn_aram_relate(serverId) {
	var url = "/sym/sym/srv/listServerEqpmnRelate.do?serverId="+serverId;
	window.open(url,'','toolbar=no,menubar=no,location=no,directions=no, scrollbars=yes,status=yes,fullscreen=no,width=770,height=520'); 
}

</script>
