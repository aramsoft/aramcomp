<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : BatchSchdulRegist.jsp
 * @Description : 배치스케줄 등록
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
	<h2>배치스케줄 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="batchSchdulVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register" summary="배치스케줄등록을 위한 테이블.">
<caption>배치스케줄 등록</caption>
  	<!--
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="batchSchdulId">배치스케줄ID</label>
    	</th>
    	<td width="80%">
        	<form:input path="batchSchdulId" size="20" maxlength="20" disabled="true"  />
        	<form:errors path="batchSchdulId" cssClass="error" />
    	</td>
  	</tr>
   -->
  	<tr>
    	<th width="20%">
    		<label for="batchOpertId">배치작업ID</label>
    		<span class="required_icon"></span>
    	</th>
    	<td width="80%">
        	<form:input path="batchOpertId" size="20" maxlength="20" readonly="true" />
        	<a href="#" onClick="javascript:fn_aram_get_batchOpert(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
        		<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" width="15" height="15" style="vertical-align: middle" alt="배치작업조회팝업 제공">
        	</a>
        	<form:errors path="batchOpertId" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="batchOpertNm">배치작업명</label>
    	</th>
    	<td>
        	<form:input path="batchOpertNm" size="60" maxlength="60" readonly="true"  />
        	<form:errors path="batchOpertNm" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="executCycle">실행주기</label>
    	</th>
    	<td>
         	<form:select path="executCycle" onchange="javascript:fn_aram_executCycleOnChange(); return false;" cssStyle="padding:0px">
            	<form:options items="${COM047_executCycle}" itemValue="code" itemLabel="codeNm"/>
         	</form:select>
         	<form:errors path="executCycle" cssClass="error"/>
 
        	<span id="spnYyyyMMdd">
			<input type="hidden" name="executSchdulDe" />
        	<input name="executSchdulDeNm" id="executSchdulDeNm" type="text" size="10" maxlength="10" title="실행스케줄일자" readonly="readonly">
        	<a href="#" onClick="javascript:fn_aram_NormalCalendar('',  document.forms[0].executSchdulDeNm ); return false;">
        		<img src="/images/com/sym/cal/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
        	</a>
        	</span>
 
        	<span id="spnMonth">
            <select name="executSchdulMonth" id="executSchdulMonth" title="실행스케줄월">
                <option value="" selected="selected">선택</option>
                <c:forEach var="i" begin="1" end="12" step="1">
                  	<c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                  	<c:if test="${i>= 10}"><option value="${i}">${i}</option></c:if>
                </c:forEach>
            </select> 월
          	</span>
   
          	<span id="spnDay">
            <select name="executSchdulDay" id="executSchdulDay" title="실행스케줄일">
                <option value="" selected="selected">선택</option>
                <c:forEach var="i" begin="1" end="31" step="1">
                  	<c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                  	<c:if test="${i>= 10}"><option value="${i}">${i}</option></c:if>
                </c:forEach>
            </select> 일
        	</span>
   
        	<span id="spnDfk">
        	<form:checkboxes path="executSchdulDfkSes" items="${COM074_executSchdulDfkSe}" itemValue="code" itemLabel="codeNm" cssClass="check2"  cssStyle="padding:0px; vertical-align : middle;"/>
        	<form:errors path="executSchdulDfkSes" cssClass="error"/>
        	</span>

        	<span id="spnHHmmss">
        	<form:select path="executSchdulHour" title="실행스케줄시" cssStyle="padding:0px">
            <form:options items="${executSchdulHourList}" />
        	</form:select> 시
        	<form:select path="executSchdulMnt" title="실행스케줄분" cssStyle="padding:0px">
            	<form:options items="${executSchdulMntList}" />
        	</form:select> 분
        	<form:select path="executSchdulSecnd"  title="실행스케줄초" cssStyle="padding:0px">
            	<form:options items="${executSchdulSecndList}" />
        	</form:select> 초
        	<form:errors path="executSchdulHour" cssClass="error"/>
        	<form:errors path="executSchdulMnt" cssClass="error"/>
        	<form:errors path="executSchdulSecnd" cssClass="error"/>
        	</span>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="startDelay">반복주기</label>
    	</th>
    	<td>
        	지연시간 : <form:input path="startDelay" size="10" maxlength="10" />&nbsp;/&nbsp;반복기간  : <form:input path="repeatInterval" size="10" maxlength="10" />  
        	<form:errors path="startDelay" cssClass="error" />
        	<form:errors path="repeatInterval" cssClass="error" />
    	</td>
  	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	사용여부
	    </th>
	    <td>
	     	<label for="useAtY"><spring:message code="button.use" /></label> : <input type="radio" name="useAt" id="useAtY" class="radio2" value="Y"  checked="checked">&nbsp;
	     	<label for="useAtN"><spring:message code="button.notUsed" /></label> : <input type="radio" name="useAt" id="useAtN" class="radio2" value="N">
	     	<form:errors path="useAt" cssClass="error"/>
	    </td>
	</tr>
  	
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="batchSchdulVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
    fn_aram_executCycleOnChange();
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

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("batchSchdulVO");
    varForm.action = "${pageContext.request.contextPath}/sym/bat/listBatchSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("batchSchdulVO");

    if(!confirm("<spring:message code='common.save.msg' />") ){
      return ;
    }

    // 실행스케줄일자 validation체크
    if (varForm.executCycle.value == "02") {
      	var i = 0;
      	for (i = 0 ; i < varForm.executSchdulDfkSes.length; i++) {
        	if (batchSchdul.executSchdulDfkSes[i].checked == true) {
          		break;
        	}
      	}
      	if (i == varForm.executSchdulDfkSes.length) {
        	alert("실행주기를 매주로 선택했을 때 요일은 필수 입력값입니다.");
        	return ;
      	}
    }

    if (varForm.executCycle.value == "03") {
        if (varForm.executSchdulDay.value == "") {
            alert("실행주기가 매월일때 실행스케줄일은(는) 필수 입력값입니다.");
            return ;
        }
    }

    if (varForm.executCycle.value == "04") {
        if (varForm.executSchdulMonth.value == "")   {
            alert("실행주기가 매년일때 실행스케줄월은(는) 필수 입력값입니다.");
            return ;
        }
        if (varForm.executSchdulDay.value == "") {
            alert("실행주기가 매년일때 실행스케줄일은(는) 필수 입력값입니다.");
            return ;
        }
         // 2월 29일도 입력가능하도록 윤년인 해를 년도값으로 사용
        if (!checkDate('0400', varForm.executSchdulMonth.value, varForm.executSchdulDay.value, "실행스케줄월, 일이 유효하지 않습니다."))   {
        	return ;
        }
    }

    if ( varForm.executCycle.value == "05" )  {
        // 스케줄주기가 한번만일때 스케줄일자값이 존재해야 한다.
        if (varForm.executSchdulDeNm.value == "")   {
            alert("실행주기가 한번만일때 실행스케줄일자은(는) 필수 입력값입니다.");
            return ;
        }
        if (!isDate(varForm.executSchdulDeNm.value, "실행스케줄일자가 유효하지 않습니다."))   {
            return ;
        }
    }

    /* 폼전송 데이타 조립. */
    var executSchdulDe = "";
    if (varForm.executCycle.value == "03") {
      	executSchdulDe = '0000' + '00' + varForm.executSchdulDay.value;
    } else if (varForm.executCycle.value == "04") {
      	executSchdulDe = '0000' + varForm.executSchdulMonth.value + varForm.executSchdulDay.value;
    } else if ( varForm.executCycle.value == "05" ) {
      	executSchdulDe = varForm.executSchdulDeNm.value;
      	executSchdulDe = fn_aram_remove_string(executSchdulDe,"-");
    }
    varForm.executSchdulDe.value = executSchdulDe;

    if(!validateBatchSchdulVO(varForm)){
        return;
    }
    
	if (confirm("<spring:message code='common.regist.msg' />")) {
        varForm.action = "${pageContext.request.contextPath}/sym/bat/insertBatchSchdul.do";
        varForm.submit();
    }
}

var gArguments = new Array();

/* ********************************************************
* 배치작업목록조회 팝업화면
******************************************************** */
function fn_aram_get_batchOpert(){
    var varForm = document.getElementById("batchSchdulVO");
	gArguments["batchOpertId"] = varForm.batchOpertId;
	gArguments["batchOpertNm"] = varForm.batchOpertNm;

	var url = "/sym/bat/listBatchOpertPopup.do";

	window.open(url, "p_batchInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * executCycle onChange 이벤트 핸들러
 ******************************************************** */
function fn_aram_executCycleOnChange() {

    var varForm = document.getElementById("batchSchdulVO");
    if (varForm.executCycle.value == "01") {
        // 실행주기가 매일인 경우
        fn_aram_displayExecutSchdulSpan(false, false, false, false, true);
        fn_aram_clearExecutSchdulValue(true, true, true, true, false);

    } else if (varForm.executCycle.value == "02") {
        // 실행주기가 매주인 경우
        fn_aram_displayExecutSchdulSpan(false, false, false, true, true);
        fn_aram_clearExecutSchdulValue(true, true, true, false, false);

    } else if (varForm.executCycle.value == "03") {
        // 실행주기가 매월인 경우
        fn_aram_displayExecutSchdulSpan(false, false, true, false, true);
        fn_aram_clearExecutSchdulValue(true, true, false, true, false);

    } else if (varForm.executCycle.value == "04") {
        // 실행주기가 매년인 경우
        fn_aram_displayExecutSchdulSpan(false, true, true, false, true);
        fn_aram_clearExecutSchdulValue(true, false, false, true, false);

    } else if (varForm.executCycle.value == "05") {
        // 실행주기가 한번만인 경우
        fn_aram_displayExecutSchdulSpan(true, false, false, false, true);
        fn_aram_clearExecutSchdulValue(false, true, true, true, false);
    }
}

/* ********************************************************
 * 실행스케줄관련 SPAN Visibility 조절 함수
 ******************************************************** */
function fn_aram_displayExecutSchdulSpan(bYyyyMMdd, bMonth, bDay, bDfk, bHHmmss) {
  	if (bYyyyMMdd) {
    	spnYyyyMMdd.style.visibility ="visible";
    	spnYyyyMMdd.style.display ="inline";
  	} else {
    	spnYyyyMMdd.style.visibility ="hidden";
    	spnYyyyMMdd.style.display ="none";
  	}

  	if (bMonth) {
    	spnMonth.style.visibility ="visible";
    	spnMonth.style.display ="inline";
  	} else {
    	spnMonth.style.visibility ="hidden";
    	spnMonth.style.display ="none";
  	}

  	if (bDay) {
    	spnDay.style.visibility ="visible";
    	spnDay.style.display ="inline";
  	} else {
    	spnDay.style.visibility ="hidden";
    	spnDay.style.display ="none";
  	}

  	if (bDfk) {
    	spnDfk.style.visibility ="visible";
    	spnDfk.style.display ="inline";
  	} else {
    	spnDfk.style.visibility ="hidden";
    	spnDfk.style.display ="none";
  	}

  	if (bHHmmss) {
    	spnHHmmss.style.visibility ="visible";
    	spnHHmmss.style.display ="inline";
  	} else {
    	spnHHmmss.style.visibility ="hidden";
    	spnHHmmss.style.display ="none";
  	}
}

/* ********************************************************
 * 실행스케줄관련 컴포넌트 값 clear 함수
 ******************************************************** */
function fn_aram_clearExecutSchdulValue(bYyyyMMdd, bMonth, bDay, bDfk, bHHmmss) {
    var varForm = document.getElementById("batchSchdulVO");

    if (bYyyyMMdd) {
 		varForm.executSchdulDeNm.value = "";
  	}

  	if (bMonth) {
  		varForm.executSchdulMonth[0].selected = true;
  	}

  	if (bDay) {
  		varForm.executSchdulDay[0].selected = true;
  	}

  	if (bDfk) {
    	// 실행스케줄 요일 값 클리어
    	for (var i = 0 ; i < varForm.executSchdulDfkSes.length; i++) {
    		varForm.executSchdulDfkSes[i].checked = false;
    	}
  	}

  	if (bHHmmss) {
    	// 시분초는 클리어 할 필요가 없다.
  	}
}

</script>
