<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : AdministCodeEdit.jsp
  * @Description : 행정코드 수정
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
	<h2>행정코드 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="administCodeVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="administZoneSe"/>
<form:hidden path="administZoneCode"/>

<table class="table-register" summary="행정구역명, 생성일자, 폐기일자, 상위행정구역코드, 사용여부를 수정할 수 있는 행정코드 수정 테이블이다.">
<CAPTION>행정코드 수정</CAPTION>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		행정구역구분
    	</th>
    	<td width="80%">
      		<form:select path="administZoneSe" disabled="true" title="행정구역구분">
	      		<form:option value="1" label="법정동" />
	      		<form:option value="2" label="행정동" />
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		행정구역코드
    	</th>
    	<td>
    		${administCodeVO.administZoneCode}
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
     		<label for="administZoneNm">행정구역명</label>
    	</th>
    	<td>
      		<form:input  path="administZoneNm" size="60" maxlength="60" title="행정구역명"/>
      		<form:errors path="administZoneNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		생성일자
    	</th>
    	<td>
      		<form:hidden path="creatDe"/>
	    	<c:if test="${!empty administCodeVO.creatDe}">
 				<c:set var="creatDeVal" value="${fn:substring(administCodeVO.creatDe, 0,4)}-${fn:substring(administCodeVO.creatDe, 4,6)}-${fn:substring(administCodeVO.creatDe, 6,8)}"/>
      		</c:if>
      		<input name="creatDeView" id="creatDeView" type="text" size="10" title="생성일자(새창)" value="${creatDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].creatDe, document.forms[0].creatDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="creatDe" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
			<span class="norequired_icon"></span>
    		폐기일자
    	</th>
    	<td>
      		<form:hidden path="ablDe"/>
	    	<c:if test="${!empty administCodeVO.ablDe}">
 				<c:set var="ablDeVal" value="${fn:substring(administCodeVO.ablDe, 0,4)}-${fn:substring(administCodeVO.ablDe, 4,6)}-${fn:substring(administCodeVO.ablDe, 6,8)}"/>
      		</c:if>
      		<input name="ablDeView" id="ablDeView" type="text" size="10" title="폐기일자(새창)" value="${ablDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ablDe, document.forms[0].ablDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="ablDe" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
			<span class="norequired_icon"></span>
    		상위행정구역코드
    	</th>
    	<td>
      		<form:input path="upperAdministZoneNm" size="60" maxlength="60" readonly="true" onClick="javascript:fn_aram_AdministCodePopup();" title="상위행정구역코드" />
			<a href="#" onclick="fn_aram_get_upperAdministCode(); return false;" style="selector-dummy:expression(this.hideFocus=false);">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="행정코드찾기">
			</a>
      		<form:hidden path="upperAdministZoneCode"/>
      		<form:errors path="upperAdministZoneCode" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		사용여부
    	</th>
    	<td>
      		<form:select path="useAt" title="사용여부">
				<form:option value="Y" label="사용" />
				<form:option value="N" label="미사용" />
      		</form:select>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="administCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("administCodeVO");
    varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/listAdministCode.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_aram_update(){
	var varForm = document.getElementById("administCodeVO");
	
	if(!validateAdministCodeVO(varForm)){
		return;
	}

	var creatDe = varForm.creatDe.value;
	var ablDe   = varForm.ablDe.value;

	if (creatDe> ablDe && ablDe != "") {
		alert("생성일, 폐기일 전후가 잘못되었습니다.\n확인 후 처리하시오.");
		return false;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/sym/ccm/adc/updateAdministCode.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 행정코드 팝업 창 열기
 ******************************************************** */
function fn_aram_get_upperAdministCode(){
 	var varForm = document.getElementById("administCodeVO");
	gArguments["administZoneCode"] = varForm.upperAdministZoneCode;
	gArguments["administZoneNm"]   = varForm.upperAdministZoneNm;

	var url = "/sym/ccm/adc/listAdministCodePopup.do?searchCondition=1";

	window.open(url, "p_codeInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>
