<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name  : EventRceptConfm.jsp
 * @Description : 행사접수신청 승인
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
	<h2>행사접수신청 승인</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_confirm(); return false;">승인</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_reject(); return false;">반려</a></span>
		</span>
	</div>	
</div>

<form:form modelAttribute="eventAtdrnVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="eventId" type="hidden" value="">
<input type="hidden" name="popup">
<input type="hidden" name="confmAt">
<input type="hidden" name="checkedEventRceptForConfm">
<input type="hidden" name="returnResn">

<table class="table-register" summary="행사접수승인 검색조건">
<caption>행사접수승인 검색조건</caption>
  	<tr>
    	<th width="20%"  scope="row">
     		<span class="norequired_icon"></span>
    		<label for="searchSe">행사구분</label>
    	</th>          
    	<td width="30%">
        	<form:select path="searchSe" title="행사구분">
                <form:option value="" label="전체"/>
                <form:options items="${COM053_eventSe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
    	<th width="20%"  scope="row">
    		<span class="norequired_icon"></span>
    		<label for="searchYear">행사년월</label>
    	</th>          
    	<td width="30%">
        	<form:select path="searchYear" title="행사구분">
                <form:option value="" label="전체"/>
                <form:options items="${yearList}" />
      		</form:select>년
        	<form:select path="searchMonth" title="행사월">
                <form:option value="" label="전체"/>
                <form:option value="01" label="01"/>
                <form:option value="02" label="02"/>
                <form:option value="03" label="03"/>
                <form:option value="04" label="04"/>
                <form:option value="05" label="05"/>
                <form:option value="06" label="06"/>
                <form:option value="07" label="07"/>
                <form:option value="08" label="08"/>
                <form:option value="09" label="09"/>
                <form:option value="10" label="10"/>
                <form:option value="11" label="11"/>
                <form:option value="12" label="12"/>
      		</form:select>월
    	</td>
  	</tr> 
  	<tr>
    	<th width="20%"  scope="row">
     		<span class="norequired_icon"></span>
    		<label for="searchNm">행사명</label>
    	</th>          
    	<td width="80%" colspan="3">
    		<form:input path="searchNm" size="20" maxlength="100" title="행사명" /> 
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px; width:100%"></div>

<table class="table-list" summary="행사접수승인 목록으로 행사명, 행사장소, 행사구분, 행사일자, 기간, 신청자, 승인일자로 구성.">
<caption>행사접수승인 목록</caption>
<thead>
	<tr>  
     	<th scope="col" width="7%" >No.</th>
		<th scope="col" width="5%" >
			<input type="checkbox" name="checkAll" id="checkAll" class="check2" onClick="javascript:fn_aram_checkAll()" title="전체선택">
		</th>
		<th scope="col"            >행사명</th>
		<th scope="col" width="10%">행사장소</th>
		<th scope="col" width="10%">행사구분</th>
		<th scope="col" width="12%">행사일자</th>
		<th scope="col" width="10%">기간</th>
		<th scope="col" width="10%">신청자</th>
		<th scope="col" width="12%">승인일자</th>
	</tr> 
</thead>     
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>

 	<c:set var="searchVO" value="${eventAtdrnVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3">
			<c:if test="${result.confmAt eq 'A'}">
		        <input type="checkbox" name="eventRceptCheck" style="border:0px;" value ="${status.count-1}">
				<input type="hidden" name="pEventId"        value="${result.eventId}"/>
				<input type="hidden" name="applcntId"       value="${result.applcntId}"/>
				<input type="hidden" name="infrmlSanctnId"  value="${result.infrmlSanctnId}"/>
				<input type="hidden" name="reqstDe"         value="${result.reqstDe}"/>
			</c:if>
			<c:if test="${result.confmAt eq 'C'}">승인</c:if>
			<c:if test="${result.confmAt eq 'R'}">반려</c:if>
		</td>
	    <td class="lt_text3">
			<span class="link">
			<a href="#" onclick="javascript:fn_aram_popup_eventManage('${result.eventId}'); return false;">
				<c:out value="${result.eventNm}"/>
			</a>
			</span> 
	    </td>     
		<td class="lt_text3"><c:out value="${result.eventPlace}"/></td>
		<td class="lt_text3"><c:out value="${result.eventSeNm}"/></td>
		<td class="lt_textL">
			<c:out value="${fn:substring(result.eventBeginDe, 0,4)}-${fn:substring(result.eventBeginDe, 4,6)}-${fn:substring(result.eventBeginDe, 6,8)}" escapeXml="false" />
			 ~ <br>
			<c:out value="${fn:substring(result.eventEndDe, 0,4)}-${fn:substring(result.eventEndDe, 4,6)}-${fn:substring(result.eventEndDe, 6,8)}" escapeXml="false" />
		</td>
		<td class="lt_text3"><c:out value="${result.eventDayCount}"/>일간</td>
		<td class="lt_text3"><c:out value="${result.applcntNm}"/></td>
		<td class="lt_text3"><c:out value="${result.sanctnDt}"/></td>
	</tr>    
	</c:forEach>
</tbody>  
</table>

<form:hidden path="searchCondition" value="1" />
<form:hidden path="pageIndex" />
</form:form>

<div id="page_navigation">
    <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("eventAtdrnVO");
	varForm.pageIndex.value   = pageNo;
	varForm.action            = "${pageContext.request.contextPath}/uss/ion/evt/listEventRceptConfm.do";
	varForm.submit();
}

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
function fn_aram_search(){
    var varForm = document.getElementById("eventAtdrnVO");
	if(varForm.searchMonth.value !=""){
		if(varForm.searchYear.value ==""){
		 	alert("전체년도에 월만 조회할 수 없습니다. 년도는 선택해주세요");
			return;
		} 
	}
	varForm.pageIndex.value  = 1;
	varForm.action           = "${pageContext.request.contextPath}/uss/ion/evt/listEventRceptConfm.do";
	varForm.submit();
}

var gArguments = new Array();

/* ********************************************************
 * 승인 처리  팝업창열기
 * fncCtsnnReturn
 * param 반려구분
 ******************************************************** */
function fn_aram_confirm(){
    var varForm = document.getElementById("eventAtdrnVO");
   	varForm.checkedEventRceptForConfm.value = fncEventConfmProcess();
   	if(varForm.checkedEventRceptForConfm.value == "F"){
   	    alert("승인처리할 대상이 존재하지 않습니다. 승인처리할 대상을 선택하신 후 승인처리 가능합니다.");
       	return;
  	}
	
	gArguments["title"]   = "행사참여신청 - 승인";
	gArguments["retFunc"] = fncConfm;

	var url = "/uss/ion/ism/ConfmPopup.do";

	window.open(url, "p_infrmSanctn", "width=780px,height=170px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 반려 처리  팝업창열기
 * fncCtsnnReturn
 * param 반려구분
 ******************************************************** */
function fn_aram_reject(){
    var varForm = document.getElementById("eventAtdrnVO");
   	varForm.checkedEventRceptForConfm.value = fncEventConfmProcess();
   	if(varForm.checkedEventRceptForConfm.value == "F"){
   	    alert("승인처리할 대상이 존재하지 않습니다. 승인처리할 대상을 선택하신 후 승인처리 가능합니다.");
       	return;
  	}

	gArguments["title"]   = "행사접수신청 - 반려";
	gArguments["retFunc"] = fncConfm;

	var url = "/uss/ion/ism/ReturnPopup.do";

	window.open(url, "p_infrmSanctn", "width=780px,height=170px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 승인 처리 
 ******************************************************** */
/*설명 : 행사접수 승인 처리 */
function fncConfm(confmAt, returnResn){
    var varForm = document.getElementById("eventAtdrnVO");
	varForm.confmAt.value = confmAt;
	varForm.returnResn.value = returnResn;
	
    varForm.action    = "${pageContext.request.contextPath}/uss/ion/evt/updateEventRceptConfm.do";
  	varForm.submit();
}

/* ********************************************************
 * 멀티처리 프로세스
 * fncEventConfmProcess
 * param null
 ******************************************************** */
function fncEventConfmProcess(){
    var varForm = document.getElementById("eventAtdrnVO");
    var checkField        = varForm.eventRceptCheck;
    var eventId           = varForm.pEventId;
    var applcntId         = varForm.applcntId;
    var infrmlSanctnId    = varForm.infrmlSanctnId;
    var reqstDe           = varForm.reqstDe;
    var checkedEventRcept = "";
    var checkedValue;
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length> 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
               	  	checkedValue = checkField[i].value;
               	  	checkedEventRcept += ((checkedCount==0? "" : "$")+eventId[checkedValue].value+","+applcntId[checkedValue].value+","+infrmlSanctnId[checkedValue].value+","+reqstDe[checkedValue].value);
                    checkedCount++;
                }
            }
            if(checkedCount == 0) checkedEventRcept = "F"; 
        } else {
			if(checkField.checked) {
        	 	checkedValue = checkField.value;
           	  	if(eventId.length> 1) checkedEventRcept = eventId[checkedValue].value+","+applcntId[checkedValue].value+","+infrmlSanctnId[checkedValue].value+","+reqstDe[checkedValue].value;
           	  	else checkedEventRcept = eventId.value+","+applcntId.value+","+infrmlSanctnId.value+","+reqstDe.value;
            }else{
            	checkedEventRcept = "F"; 
            }
        }
    }else checkedEventRcept = "F"; 
    return checkedEventRcept;
}
 
/* ********************************************************
 * 행사 상세화면 팝업 호출함수
 ******************************************************** */
function fn_aram_popup_eventManage(eventId){
    var varForm = document.getElementById("eventAtdrnVO");
 	var openParam            = "left=10, top=0, width=750, height=600";
 	varForm.eventId.value    = eventId;
 	varForm.popup.value      = "true";
 	var myWin = window.open("about:blank","EventReqstDetailPop",openParam);
 	
 	varForm.method = "post";
 	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/detailEventReqst.do";
 	varForm.target = "EventReqstDetailPop";
 	varForm.submit();
}

/* ********************************************************
 * 체크 박스 선택 함수
 ******************************************************** */
function fn_aram_checkAll(){

 	var FLength = document.getElementsByName("eventRceptCheck").length;
 	var checkAllValue = document.getElementById('checkAll').checked;

 	//undefined
 	if( FLength == 1){
	 	document.listForm.eventRceptCheck.checked = checkAllValue;
 	}else if(FLength> 1){
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("eventRceptCheck")[i].checked = checkAllValue;	
			}
 	}
}

</script>
