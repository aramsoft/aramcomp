<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollList.jsp
  * @Description : 온라인POLL 목록
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
	<h2>온라인POLL 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="onlinePollManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="pollId" type="hidden" value="">
<input name="returnUrl" id="returnUrl" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="POLL_NM" label="POLL명" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list">
<thead>
	<tr>
	    <th scope="col" width="7%" >No.</th>
	    <th scope="col"            >온라인POLL명</th>
	    <th scope="col" width="10%">시작일</th>
	    <th scope="col" width="10%">종료일</th>
	    <th scope="col" width="10%">통계</th>
	    <th scope="col" width="10%">결과</th>
	    <th scope="col" width="10%">등록자</th>
	    <th scope="col" width="15%">등록일자</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(onlinePollManageVO.pageIndex-1) * onlinePollManageVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${onlinePollManageVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3L">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.pollId}"/>'); return false;">
				<c:out value="${result.pollNm}"/>
			</a>
			</span>
	    </td>
	    <td class="lt_text3">
	    	<c:out value="${fn:substring(result.pollBeginDe, 0, 4)}-${fn:substring(result.pollBeginDe, 4, 6)}-${fn:substring(result.pollBeginDe, 6, 8)}"/>
	    </td>
	    <td class="lt_text3">
	    	<c:out value="${fn:substring(result.pollEndDe, 0, 4)}-${fn:substring(result.pollEndDe, 4, 6)}-${fn:substring(result.pollEndDe, 6, 8)}"/>
	    </td>
	    <td class="lt_text3">
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_statistics('<c:out value="${result.pollId}"/>'); return false;">
				보기
			</a>
			</span>
	    </td>
	    <td class="lt_text3">
			<span class="button">
			<a href="#" onclick="javascript:fn_aram_result('<c:out value="${result.pollId}"/>'); return false;">
				보기
			</a>
			</span>
		</td>
	    <td class="lt_text3"><c:out value="${result.frstRegisterNm}"/></td>
	    <td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
	
<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>


<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/listOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/listOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(pollId){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm.pollId.value = pollId;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/detailOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_regist(){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm.pollId.value = "";
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opm/registOnlinePoll.do";
    varForm.submit();
}

/* ********************************************************
 * 통계보기
 ******************************************************** */
function fn_aram_statistics(pollId){
    var varForm = document.getElementById("onlinePollManageVO");
    varForm.returnUrl.value = "${pageContext.request.contextPath}/uss/olp/opm/listOnlinePoll.do";
	varForm.pollId.value = pollId;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/statisticsOnlinePollPartcptn.do";
    varForm.submit();
}

/* ********************************************************
 * 결과보기
 ******************************************************** */
function fn_aram_result(pollId){
    var varForm = document.getElementById("onlinePollManageVO");
	varForm.pollId.value = pollId;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opr/listOnlinePollResult.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("온라인poll관리");	
	window.open(url, "도움말");
}

</script>


