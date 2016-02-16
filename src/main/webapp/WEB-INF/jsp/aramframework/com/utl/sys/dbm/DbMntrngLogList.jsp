<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : DbMntrngLogList.jsp
 * @Description : DB서비스모니터링로그 목록
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
	<h2>DB서비스모니터링로그 목록</h2> 
</div>

<form:form commandName="dbMntrngLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="logId"/>

<div id="search_area" >
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_dbMntrng(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area1">
                       기간:
        <input type="text" name="searchStartDate" size="10" title="검색시작일자">
        <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchStartDate ); return false;">
           	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
        </a>
        <select name="searchStartHour" id="searchStartHour" title="검색시작시간">
            <option value="">선택</option>
            <c:forEach var="h" begin="1" end="24" step="1">
               	<option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
            </c:forEach>
        </select>
        :
        <select name="searchStartMin" id="searchStartMin" title="검색시작분">
            <option value="">선택</option>
            <c:forEach var="h" begin="0" end="59" step="1">
            	<option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
            </c:forEach>
        </select>
       	~ 
        <input type="text" name="searchEndDate" size="10" title="검색종료일자">
        <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchEndDate ); return false;">
           	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
        </a>
        <select name="searchEndHour" id="searchEndHour" title="검색종료시간">
            <option value="">선택</option>
            <c:forEach var="h" begin="1" end="24" step="1">
               	<option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
            </c:forEach>
        </select>
        :
        <select name="searchEndMin" id="searchEndMin" title="검색종료분">
            <option value="">선택</option>
            <c:forEach var="h" begin="0" end="59" step="1">
               	<option value="<fmt:formatNumber value="${h}" pattern="00"/>"><fmt:formatNumber value="${h}" pattern="00"/></option>
            </c:forEach>
        </select>
	</div>
	<div class="keyword_area2">
  		<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="DATA_SOURC_NM" label="데이타소스명" />			   
	   		<form:option value="SERVER_NM" label="서버명" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search_dbMntrngLog();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchKeywordFrom" />
<form:hidden path="searchKeywordTo" />
<form:hidden path="searchVO.pageIndex" />
</form:form>

<table class="table-list" summary="등록된 DB서비스모니터링로그에 대한 목록을 제공합니다.">
<thead>
    <tr>
        <th scope="col" width="20%">데이타소스명</th>
        <th scope="col" width="10%">서버명</th>
        <th scope="col" width="10%">DBMS종류</th>
        <th scope="col" width="10%">관리자명</th>
        <th scope="col" width="20%">관리자이메일주소</th>
        <th scope="col" width="10%">상태</th>
        <th scope="col" width="20%">모니터링시각</th>
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
    <c:forEach items="${resultList}" var="result" varStatus="status">
    <tr class="link" onclick="javascript:fn_aram_detail('${result.logId}'); return false;">
        <td class="lt_text6"><c:out value="${result.dataSourcNm}"/></td>
        <td class="lt_text6">${result.serverNm}</td>
        <td class="lt_text3">${result.dbmsKindNm}</td>
        <td class="lt_text6">${result.mngrNm}</td>
        <td class="lt_text6">${result.mngrEmailAddr}</td>
        <td class="lt_text3">${result.mntrngSttusNm}</td>
        <td class="lt_text3">${result.creatDt}</td>
    </tr>
    </c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {

    var varForm = document.getElementById("dbMntrngLogVO");

    if (varForm.searchKeywordFrom.value == "") {
        // 조회일자에 현재날짜 세팅
        //varForm.searchStartDate.value = fn_aram_getToday();
        //varForm.searchEndDate.value = fn_aram_getToday();
        //varForm.searchEndHour.options[23].selected = true;
    } else {
        // 조회조건 지정된 것 설정하기.
        // 조회시작일자
        varForm.searchStartDate.value = varForm.searchKeywordFrom.value.substring(0,4) + '-' + varForm.searchKeywordFrom.value.substring(4,6) + '-' + varForm.searchKeywordFrom.value.substring(6,8);
        for(var i = 0; i < varForm.searchStartHour.options.length; i++) {
            if (varForm.searchStartHour.options[i].value == varForm.searchKeywordFrom.value.substring(8,10)) {
            	varForm.searchStartHour.options[i].selected = true;
                break;
            }
        }
        for(var i = 0; i < varForm.searchStartMin.options.length; i++) {
            if (varForm.searchStartMin.options[i].value == varForm.searchKeywordFrom.value.substring(10,12)) {
            	varForm.searchStartMin.options[i].selected = true;
                break;
            }
        }
        // 조회종료일자
        varForm.searchEndDate.value = varForm.searchKeywordTo.value.substring(0,4) + '-' + varForm.searchKeywordTo.value.substring(4,6) + '-' + varForm.searchKeywordTo.value.substring(6,8);
        for(var i = 0; i < varForm.searchEndHour.options.length; i++) {
            if (varForm.searchEndHour.options[i].value == varForm.searchKeywordTo.value.substring(8,10)) {
            	varForm.searchEndHour.options[i].selected = true;
                break;
            }
        }
        for(var i = 0; i < varForm.searchEndMin.options.length; i++) {
            if (varForm.searchEndMin.options[i].value == varForm.searchKeywordTo.value.substring(10,12)) {
            	varForm.searchEndMin.options[i].selected = true;
                break;
            }
        }
    }
};

function press(event) {
    if (event.keyCode==13) {
    	fn_aram_search();
    }
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("dbMntrngLogVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrngLog.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("dbMntrngLogVO");
    if (varForm.searchKeyword.value != "") {
        if (varForm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            return;
        }
    }
    /* 폼전송 데이타 조립. */
    var startDate = "";
    var endDate = "";
    if (varForm.searchStartDate.value != "")   {
        startDate = varForm.searchStartDate.value;
        startDate = fn_aram_remove_string(startDate,"-");
    }
    if (varForm.searchEndDate.value != "")   {
        endDate = varForm.searchEndDate.value;
        endDate = fn_aram_remove_string(endDate,"-");
    }
    varForm.searchKeywordFrom.value = startDate + varForm.searchStartHour.value + varForm.searchStartMin.value;
    varForm.searchKeywordTo.value = endDate +varForm.searchEndHour.value + varForm.searchEndMin.value;

    /*
         검색조건 체크
    */

    if (varForm.searchStartDate.value != "" || varForm.searchEndDate.value != "")   {
        if (varForm.searchStartDate.value == "") {
            alert("검색시작일자를 입력하세요");
            return ;
        }
        if (varForm.searchEndDate.value == "") {
            alert("검색종료일자를 입력하세요");
            return ;
        }
        if(isDate(varForm.searchStartDate.value, "조회시작일자") == false) {
            return;
        }

        if(isDate(varForm.searchEndDate.value, "조회종료일자") == false) {
            return;
        }
        if(varForm.searchKeywordFrom.value> varForm.searchKeywordTo.value) {
            alert("검색종료시각가 검색시작시각보다 빠를수 없습니다.");
            return ;
        }
    }

    varForm["searchVO.pageIndex"].value = '1';
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrngLog.do";
    varForm.submit();
}

function fn_aram_detail(logId) {
    var varForm = document.getElementById("dbMntrngLogVO");
    varForm.logId.value = logId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/detailDbMntrngLog.do";
    varForm.submit();
}

function fn_aram_list_dbMntrng(){
    var varForm = document.getElementById("dbMntrngLogVO");
    varForm.action = "${pageContext.request.contextPath}/utl/sys/dbm/listDbMntrng.do";
    varForm.submit();
}

/* ********************************************************
 * 현재날짜 가져오기
 ******************************************************** */
function fn_aram_getToday(){

    var today = new Date();
    var currentYear  = today.getFullYear();
    var currentMonth = (today.getMonth()+1).toString();
    var currentDay = today.getDate().toString();

    var currentToday = currentYear + "-" + fn_aram_getLpad(2,currentMonth) + "-" + fn_aram_getLpad(2,currentDay);

    return  currentToday;
}

/* ********************************************************
 * LPAD 처리
 ******************************************************** */
function fn_aram_getLpad(rtnSize, sourceStr)
{
    rtnStr = sourceStr;

    for(var i= sourceStr.length; i<parseInt(rtnSize); i++)
        rtnStr =  "0" + rtnStr;

    return rtnStr;
}

/* ********************************************************
 * 입력받은문자열중에서 제거 문자열을 제외 문자열을 리턴한다.
 ******************************************************** */
function fn_aram_remove_string(srcStr, cndStr) {

    var result = null;
    var rtnStr = null;

    for (var i = 0; i < srcStr.length; i++) {

        if (srcStr.charAt(i) == cndStr) {
            result = srcStr.substring(0, i);

            // 첫번째 제거 문자열을 제외한 전체 문자열
            srcStr = result + srcStr.substring(i+1, srcStr.length);

            // 최종변환 문자열
            rtnStr = srcStr;
        }
    }

    return rtnStr;
}

</script>
