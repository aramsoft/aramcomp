<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : EventReqstEdit.jsp
 * @Description : 행사 수정
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
	<h2>행사 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="eventManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="eventId" />
<form:hidden  path="eventNm" />
<form:hidden  path="eventSe" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="행사관리 수정">
<caption>행사관리 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		행사구분
    	</th>          
    	<td width="80%">
    		<c:out value='${eventManageVO.eventSeNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		행사명
    	</th>
    	<td>
    		<c:out value='${eventManageVO.eventNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="eventPurps">행사목적</label>
    	</th>          
    	<td>
      		<form:input  path="eventPurps" size="80" maxlength="200" title="행사목적"/>
      		<form:errors path="eventPurps" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="eventBeginDe">행사기간</label>
    	</th>          
    	<td>
      		<form:hidden path="eventBeginDe" />
	    	<c:if test="${!empty eventManageVO.eventBeginDe}">
 				<c:set var="eventBeginDeVal" value="${fn:substring(eventManageVO.eventBeginDe, 0,4)}-${fn:substring(eventManageVO.eventBeginDe, 4,6)}-${fn:substring(eventManageVO.eventBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="eventBeginDeView" id="eventBeginDeView" type="text" size="10" title="행사 시작일자" value="${eventBeginDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].eventBeginDe, document.forms[0].eventBeginDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	    	~
      		<form:hidden path="eventEndDe" />
	    	<c:if test="${!empty eventManageVO.eventEndDe}">
 				<c:set var="eventEndDeVal" value="${fn:substring(eventManageVO.eventEndDe, 0,4)}-${fn:substring(eventManageVO.eventEndDe, 4,6)}-${fn:substring(eventManageVO.eventEndDe, 6,8)}"/>
      		</c:if>
      		<input name="eventEndDeView" id="eventEndDeView" type="text" size="10" title="행사 종료일자" value="${eventEndDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].eventEndDe, document.forms[0].eventEndDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="eventAuspcInsttNm">행사주최</label>
    	</th>          
    	<td>
      		<form:input  path="eventAuspcInsttNm" size="60" maxlength="60" title="행사주최"/>
      		<form:errors path="eventAuspcInsttNm" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="eventMngtInsttNm">행사주관</label>
    	</th>          
    	<td>
      		<form:input  path="eventMngtInsttNm" size="60" maxlength="60" title="행사주관"/>
      		<form:errors path="eventMngtInsttNm" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="eventPlace">행사장소</label>
    	</th>          
    	<td>
      		<form:input  path="eventPlace" size="80" maxlength="200" title="행사장소"/>
      		<form:errors path="eventPlace" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="refrnUrl">참조URL</label>
    	</th>          
    	<td>
      		<form:input  path="refrnUrl" size="80" maxlength="1024" title="참조URL"/>
      		<form:errors path="refrnUrl" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="eventCn">행사내용</label>
    	</th>          
    	<td>
      		<form:textarea path="eventCn" rows="4" cols="70" cssClass="txArea" title="참조URL"/>
      		<form:errors path="eventCn" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="ctOccrrncAt">참가비용</label>
    	</th>          
    	<td>
      		<input name="ctOccrrncAt" type="radio" value="1" onClick="javascript:fncOccrrncAt(this.value)" <c:if test="${eventManageVO.ctOccrrncAt == '1'}">checked</c:if> title="무료">무료
      		<input name="ctOccrrncAt" type="radio" value="2" onClick="javascript:fncOccrrncAt(this.value)" <c:if test="${eventManageVO.ctOccrrncAt == '2'}">checked</c:if> title="유료">유료
      		<form:input  path="partcptCt" size="10" maxlength="9" title="유료금액 "/> 만원
      		<form:errors path="partcptCt" cssClass="error"/>
    	</td>    
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="garden">정원</label>
    	</th>          
    	<td>
      		<form:input  path="garden" size="10" maxlength="9" title="정원 "/>
      		<form:errors path="garden" cssClass="error"/>
    	</td>    
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="rceptBeginDe">행사접수기간</label>
    	</th>          
    	<td>
      		<form:hidden path="rceptBeginDe" />
	    	<c:if test="${!empty eventManageVO.rceptBeginDe}">
 				<c:set var="rceptBeginDeVal" value="${fn:substring(eventManageVO.rceptBeginDe, 0,4)}-${fn:substring(eventManageVO.rceptBeginDe, 4,6)}-${fn:substring(eventManageVO.rceptBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="rceptBeginDeView" id="rceptBeginDeView" type="text" size="10" title="행사접수 시작일자" value="${rceptBeginDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].rceptBeginDe, document.forms[0].rceptBeginDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
		    ~
      		<form:hidden path="rceptEndDe" />
	    	<c:if test="${!empty eventManageVO.rceptEndDe}">
 				<c:set var="rceptEndDeVal" value="${fn:substring(eventManageVO.rceptEndDe, 0,4)}-${fn:substring(eventManageVO.rceptEndDe, 4,6)}-${fn:substring(eventManageVO.rceptEndDe, 6,8)}"/>
      		</c:if>
      		<input name="rceptEndDeView" id="rceptEndDeView" type="text" size="10" title="행사접수 종료일자" value="${rceptEndDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].rceptEndDe, document.forms[0].rceptEndDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>    
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="eventManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(pageNo){
	var varForm = document.getElementById("eventManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/listEventReqst.do";
	varForm.submit();
}

/* ********************************************************
* 저장처리화면
******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("eventManageVO");

	if(!validateEventManageVO(varForm)){           
        return;
    }

	vEventBeginDe = varForm.eventBeginDe.value.split("-").join("");
    vEventEndDe   = varForm.eventEndDe.value.split("-").join("");
    vRceptBeginDe = varForm.rceptBeginDe.value.split("-").join("");
    vRceptEndDe   = varForm.rceptEndDe.value.split("-").join("");
    vRefrnUrl     = varForm.refrnUrl.value.split("-").join("");
    vGarden       = varForm.garden.value;
    vPartcptCt    = varForm.partcptCt.value;

    if(vEventBeginDe> vEventEndDe){
	    alert("행사시작일이  행사종료일보다 늦습니다. 행사기간을 확인해 주세요");
	    return;
    }
    if(vRceptBeginDe> vRceptEndDe){
	    alert("행사접수시작일이  행사접수종료일보다 늦습니다. 행사접수기간을  확인해 주세요");
	    return;
    }
    
    if(vRceptEndDe> vEventBeginDe){
	    alert("행사접수는 행사시작일 이전에  접수종료되어어 합니다.  행사기간/행사접수기간을  확인해 주세요");
	    return;
    }

	if(!urlCheck(vRefrnUrl) && vRefrnUrl!=""){
		alert("참조URL의 형식이 URL 형식과 틀립니다. 확안해 주세요");
		return;
	}

    if(isNaN(vPartcptCt)){
        alert("참가비용은 숫자만 입력가능합니다.");
    	return;
	}

	if(varForm.ctOccrrncAt[1].checked){
		if(vPartcptCt <= 0){
			alert("참가비용이 유료인 경우  0원 이상 입력하셔야 합니다. 확안해 주세요");
			return;
		}
	}
    if(isNaN(vGarden)){
        alert("정원은 숫자만 입력가능합니다.");
    	return;
	}	
	if(vGarden <= 0){
		alert("정원은  0명을 이상 입력하셔야 합니다. 확안해 주세요");
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/evt/updateEventReqst.do";
        varForm.submit();
    }
}

/* ********************************************************
 * 참가비용체크
 ******************************************************** */
function fncOccrrncAt(vValue) {
  	var varForm = document.getElementById("eventManage");
  	if(vValue == "1"){ //무료
	  	varForm.partcptCt.value = 0;
      	varForm.partcptCt.readOnly = true;
  	}else if(vValue == "2"){ //유료
	  	varForm.partcptCt.value = 0;
      	varForm.partcptCt.readOnly = false;
  	}
}

/* ********************************************************
 * URL 여부 체크
 ******************************************************** */
function urlCheck(vValue){
 	return vValue.search(/^\s*['http://']+[\w\~\-\.]+\.[\w\~\-]+(\.[\w\~\-]+)+\s*$/g)>=0;
}

/* ********************************************************
 * 숫자 여부 체크
 ******************************************************** */
function checkNum(inputValue) 
{
	alert(isNaN(inputValue));
   	var checkCode = inputValue.charCodeAt(inputValue.length-1); 
   	var str; 
   	alert(checkCode)
   	if((checkCode>= 33 && checkCode <= 47) || (checkCode>= 58 && checkCode <= 125)) 
   	{
		return false;
   	}
   	return true;
}

</script>
