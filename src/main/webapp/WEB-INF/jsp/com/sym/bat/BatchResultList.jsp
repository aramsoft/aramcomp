<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BatchResultList.jsp
 * @Description : 배치결과 목록
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
	<h2>배치결과 목록</h2>
</div>

<form:form modelAttribute="batchResultVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<input name="batchResultId" type="hidden" />

<div id="search_area">
	<div class="search_left">
	 	<strong>전체 : ${batchResultVO.totalRecordCount} 건</strong>	
	</div>
	<div class="search_right">
		<span class="keyword_area">
	 	   	<form:select path="searchCondition" class="select" title="검색유형선력">
			   	<form:option value="" label="--선택하세요--" />
			   	<form:option value="BATCH_OPERT_NM" label="배치작업명" />
			   	<form:option value="BATCH_SCHDUL_ID" label="배치스케줄ID" />
		   	</form:select>
	   		<form:input path="searchKeyword" size="15" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
	                      실행시작일자:
	        <input type="text" name="searchStartDate" size="10" title="검색시작일자">
	        <a href="#" onClick="javascript:fn_aram_NormalCalendar('',  frm.searchStartDate ); return false;">
	        	<img src="/images/com/sym/cal/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
	        </a>
	    	~
	        <input type="text" name="searchEndDate" size="10" title="검색종료일자">
	        <a href="#" onClick="javascript:fn_aram_NormalCalendar('', frm.searchEndDate ); return false;">
	        	<img src="/images/com/sym/cal/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
	        </a>
			<form:select path="sttus" class="select" title="검색조건구분">
		   		<form:option value="00" label="전체" />
		   		<form:option value="01" label="정상" />
		   		<form:option value="02" label="비정상" />
		   		<form:option value="03" label="수행중" />
			</form:select>
			<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
		   		<form:option value="10" label="10" />
		   		<form:option value="20" label="20" />
		   		<form:option value="30" label="30" />
		   		<form:option value="50" label="50" />
			</form:select>
		</span>
		<span class="button_area">
			<span class="button"><a href="#" onclick="fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		</span>
	</div>	
</div>

<form:hidden path="pageIndex" />
<form:hidden path="searchKeywordFrom" />
<form:hidden path="searchKeywordTo" />
</form:form>

<table class="table-list" summary="배치결과에 대한 목록을 제공합니다.">
<caption>배치결과 목록</caption>
<thead>
    <tr>
		<th scope="col" width="7%" >No.</th>
        <th scope="col" width="20%">배치결과ID</th>
        <th scope="col"            >배치작업명</th>
        <th scope="col" width="10%">상태</th>
        <th scope="col" width="15%">실행시작시각</th>
        <th scope="col" width="15%">실행종료시각</th>
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
	<c:set var="tempDate" value=""/>
 	<c:set var="startIndex" value="${(batchResultVO.pageIndex-1) * batchResultVO.recordPerPage}"/>
    <c:forEach items="${resultList}" var="result" varStatus="status">
    <tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${batchResultVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

        <td class="lt_text3"><c:out value='${result.batchResultId}'/></td>
        <td class="lt_text3">
			<span class="link">
    		<a href="#" onclick="javascript:fn_aram_detail('<c:out value="${result.batchResultId}"/>'); return false;">
        		${result.batchOpertNm}
    		</a>
			</span>
        </td>
        <td class="lt_text3">${result.sttusNm}</td>
        <td class="lt_text3">
            <fmt:parseDate value="${result.executBeginTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
            <fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
        <td class="lt_text3">
            <fmt:parseDate value="${result.executEndTime}" pattern="yyyyMMddHHmmss" var="tempDate"/>
            <fmt:formatDate value="${tempDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
    </tr>
    </c:forEach>
</tbody>
</table>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

window.onload = function() {

    var varForm = document.getElementById("batchResultVO");

    if (varForm.searchKeywordFrom.value == "") {
        // 조회일자에 현재날짜 세팅
        //varForm.searchStartDate.value = fn_aram_getToday();
        //varForm.searchEndDate.value = fn_aram_getToday();
        //varForm.searchEndHour.options[23].selected = true;
    } else {
        // 조회조건 지정된 것 설정하기.
        // 조회시작일자
        varForm.searchStartDate.value = varForm.searchKeywordFrom.value.substring(0,4) + '-' + varForm.searchKeywordFrom.value.substring(4,6) + '-' + varForm.searchKeywordFrom.value.substring(6,8);
        // 조회종료일자
        varForm.searchEndDate.value = varForm.searchKeywordTo.value.substring(0,4) + '-' + varForm.searchKeywordTo.value.substring(4,6) + '-' + varForm.searchKeywordTo.value.substring(6,8);
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
    var varForm = document.getElementById("batchResultVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchResult.do";
    varForm.submit();
}

function fn_aram_search() {
    var varForm = document.getElementById("batchResultVO");

    if (varForm.searchKeyword.value != "") {
        if (varForm.searchCondition.value == "") {
            alert("검색조건을 선택하세요.");
            varForm.searchCondition.focus();
            return;
        }
    }

    /* 폼전송 데이타 조립. */
    var startDate = "";
    var endDate = "";
    if (varForm.searchStartDate.value != "")   {
        startDate = varForm.searchStartDate.value;
        // startDate = fn_aram_remove_string(startDate,"-");
    }
    if (varForm.searchEndDate.value != "")   {
        endDate = varForm.searchEndDate.value;
        // endDate = fn_aram_remove_string(endDate,"-");
    }

    /**  lee.m.j 2010-10-27 추가   **/
    var tmpFromDate = startDate.split("-");
    var tmpEndDate = endDate.split("-");

    var strTmpFromDate = "";
    var strTmpEndDate = "";

    for(var i=0;i<tmpFromDate.length;i++) {
        strTmpFromDate = strTmpFromDate + tmpFromDate[i];
    }

    for(var j=0;j<tmpEndDate.length;j++) {
        strTmpEndDate = strTmpEndDate + tmpEndDate[j];
    }
    /**  lee.m.j 2010-10-27 추가   **/

    varForm.searchKeywordFrom.value = strTmpFromDate ;
    varForm.searchKeywordTo.value = strTmpEndDate ;

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

        /*
        if(varForm.searchKeywordFrom.value> varForm.searchKeywordTo.value) {
            alert("검색종료시각가 검색시작시각보다 빠를수 없습니다.");
            return ;
        }*/

        /**  lee.m.j 2010-10-27 추가   **/
        if(strTmpFromDate.length != 8 || strTmpEndDate.length != 8 || !checknum(strTmpFromDate) || !checknum(strTmpEndDate)) {
            alert("날짜 형식이 잘못되었습니다");
            return;
        } else {
            if(strTmpFromDate> strTmpEndDate) {
                alert("시작일자는 종료일자보다 클 수 없습니다");
                return;
            }
        }
        /**  lee.m.j 2010-10-27 추가   **/
    }

    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchResult.do";
    varForm.submit();
}

function fn_aram_detail(batchResultId) {
    var varForm = document.getElementById("batchResultVO");
    varForm.batchResultId.value = batchResultId;
    varForm.action = "${pageContext.request.contextPath}/sym/bat/detailBatchResult.do";
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
function fn_aram_getLpad(rtnSize, sourceStr){
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

function checknum(number) {

    var rtnMsg = true;
    var pattern = /^[0-9]+$/;

    if(!pattern.test(number)) {
        rtnMsg = false;
    }

    return rtnMsg;
}

</script>
