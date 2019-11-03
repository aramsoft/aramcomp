<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : TrsmrcvMntrngLogList.jsp
 * @Description : 송수신모니터링로그 목록
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
	<h2>송수신모니터링로그 목록</h2>
</div>

<form:form commandName="trsmrcvMntrngLogVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="logId">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list_trsmrcvMntrng(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div class="keyword_area1">
                     기간:
        <input type="text" name="searchStartDate" size="10" title="검색시작일자">
        <a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].searchStartDate ); return false;">
           	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" alt="일자달력 (팝업으로 열림)">
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
           	<img src="/images/aramframework/com/sym/cal/bu_icon_carlendar.gif" alt="일자달력 (팝업으로 열림)">
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
  		<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="CNTC_NM" label="연계명" />			   
	   		<form:option value="MNGR_NM" label="관리자명" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
 		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchKeywordFrom" />
<form:hidden path="searchKeywordTo" />
<form:hidden path="pageIndex" />
</form:form>

<table class="table-list" summary="송수신모니터링로그 목록테이블">
<thead>
    <tr>
        <th scope="col" width="10%">연계ID</th>
        <th scope="col" width="15%">연계명</th>
        <th scope="col" width="27%">테스트클래스명</th>
        <th scope="col" width="10%">관리자명</th>
        <th scope="col" width="20%">관리자이메일주소</th>
        <th scope="col" width="18%">모니터링시각</th>
    </tr>
    <tr>
        <th scope="col">제공기관</th>
        <th scope="col">제공시스템</th>
        <th scope="col">제공서비스</th>
        <th scope="col">요청기관</th>
        <th scope="col">요청시스템</th>
        <th scope="col">상태</th>
    </tr>
</thead>
<tbody>
    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
    <c:if test="${fn:length(resultList) == 0}">
    <tr>
        <td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
    </tr>
    </c:if>
    
    <%-- 데이터를 화면에 출력해준다 --%>
    <c:forEach items="${resultList}" var="result" varStatus="status">
    <tr class="link" onclick="javascript:fn_aram_detail('${result.logId}'); return false;">
    
        <td class="lt_text3">${resultInfo.cntcId}</td>
        <td class="lt_text6"><c:out value="${result.cntcNm}"/></td>
        <td class="lt_text6">${result.testClassNm}</td>
        <td class="lt_text6">${result.mngrNm}</td>
        <td class="lt_text6">${result.mngrEmailAddr}</td>
        <td class="lt_text3">${result.creatDt}</td>
    </tr>
    <tr>
        <td class="lt_text6">${result.provdInsttNm}</td>
        <td class="lt_text6">${result.provdSysNm}</td>
        <td class="lt_text6">${result.provdSvcNm}</td>
        <td class="lt_text6">${result.requstInsttNm}</td>
        <td class="lt_text6">${result.requstSysNm}</td>
        <td class="lt_text3">${result.mntrngSttusNm}</td>
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

    var varForm = document.getElementById("trsmrcvMntrngLogVO");
    if (varForm.searchKeywordFrom.value == "") {
        // 조회일자에 현재날짜 세팅
        //alert("빈문자열입니다. ");
        //vForm.searchStartDate.value = fn_aram_getToday();
        //vForm.searchEndDate.value = fn_aram_getToday();
        //vForm.searchEndHour.options[23].selected = true;
    } else {
        // 조회조건 지정된 것 설정하기.
        // 조회시작일자
        varForm.searchStartDate.value = varForm.searchKeywordFrom.value.substring(0,4) + '-' + varForm.searchKeywordFrom.value.substring(4,6) + '-' + varForm.searchKeywordFrom.value.substring(6,8);
        for(i = 0; i < vForm.searchStartHour.options.length; i++) {
            if (varForm.searchStartHour.options[i].value == varForm.searchKeywordFrom.value.substring(8,10)) {
            	varForm.searchStartHour.options[i].selected = true;
                break;
            }
        }
        for(i = 0; i < varForm.searchStartMin.options.length; i++) {
            if (varForm.searchStartMin.options[i].value == varForm.searchKeywordFrom.value.substring(10,12)) {
            	varForm.searchStartMin.options[i].selected = true;
                break;
            }
        }
        // 조회종료일자
        varForm.searchEndDate.value = varForm.searchKeywordTo.value.substring(0,4) + '-' + varForm.searchKeywordTo.value.substring(4,6) + '-' + vForm.searchKeywordTo.value.substring(6,8);
        for(i = 0; i < vForm.searchEndHour.options.length; i++) {
            if (varForm.searchEndHour.options[i].value == varForm.searchKeywordTo.value.substring(8,10)) {
            	varForm.searchEndHour.options[i].selected = true;
                break;
            }
        }
        for(i = 0; i < varForm.searchEndMin.options.length; i++) {
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
    var varForm = document.getElementById("trsmrcvMntrngLogVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrngLog.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("trsmrcvMntrngLogVO");
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
    varForm.searchKeywordTo.value = endDate + varForm.searchEndHour.value + varForm.searchEndMin.value;

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

    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrngLog.do";
    document.frm.submit();
}

function fn_aram_detail(logId) {
    var varForm = document.getElementById("trsmrcvMntrngLogVO");
    varForm.logId.value = logId;
    varForm.action = "${pageContext.request.contextPath}/utl/sys/trm/detailTrsmrcvMntrngLog.do";
    varForm.submit();
}

function fn_aram_list_trsmrcvMntrng(){
	location.href = "${pageContext.request.contextPath}/utl/sys/trm/listTrsmrcvMntrng.do";
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

    for( i= sourceStr.length; i<parseInt(rtnSize); i++)
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
