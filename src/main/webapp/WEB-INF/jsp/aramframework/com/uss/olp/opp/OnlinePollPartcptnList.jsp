<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : OnlinePollPartcptnList.jsp
  * @Description : 온라인POLL참여 목록
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
	<h2>온라인POLL참여 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="onlinePollPartcptnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="pollId" type="hidden" value="">
<input name="returnUrl" id="returnUrl" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
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
	    <th scope="col" width="10%">등록자</th>
	    <th scope="col" width="15%">등록일자</th>
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="startIndex" value="${(onlinePollPartcptnVO.pageIndex-1) * onlinePollPartcptnVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${onlinePollPartcptnVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    <td class="lt_text3L">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_regist('<c:out value="${result.pollId}"/>','<c:out value="${fn:substring(result.pollBeginDe, 0, 10)}"/>','<c:out value="${fn:substring(result.pollEndDe, 0, 10)}"/>'); return false;">
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

<jsp:useBean id="now" class="java.util.Date"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

window.onload = function() {
	if("${reusltScript}" != ''){ alert("${reusltScript}");}
};

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("onlinePollPartcptnVO");
    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.do";
    varForm.submit();
}

/* ********************************************************
 * 등록화면처리 함수
 ******************************************************** */
function fn_aram_regist(pollId,sDate,eDate){
    var varForm = document.getElementById("onlinePollPartcptnVO");

    var iToDate = <fmt:formatDate value="${now}" pattern="yyyyMMdd" />;
	var iBeginDate = Number(sDate.replaceAll("-",""));
	var iEndDate = Number(eDate.replaceAll("-",""));

	if(iToDate>= iBeginDate && iToDate <= iEndDate){
		varForm.pollId.value = pollId;
		varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/registOnlinePollPartcptn.do";
		varForm.submit();
	}else{
		alert("지금은 온라인POLL 투표기간이 아닙니다!");
		return;
	}
}

/* ********************************************************
 * 통계보기
 ******************************************************** */
function fn_aram_statistics(pollId){
    var varForm = document.getElementById("onlinePollPartcptnVO");
	varForm.pollId.value = pollId;
    varForm.returnUrl.value = "${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.do";
    varForm.action = "${pageContext.request.contextPath}/uss/olp/opp/statisticsOnlinePollPartcptn.do";
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

