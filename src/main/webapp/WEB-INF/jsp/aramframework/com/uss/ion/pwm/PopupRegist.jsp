<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : PopupRegist.jsp
  * @Description : 팝업창관리 등록
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
	<h2>팝업창관리 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="popupManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="popupId" />

<!--  등록  폼 영역  -->
<table class="table-register" summary="팝업창관리  입력을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="popupTitleNm">팝업창명</label>
    	</th>
    	<td width="80%">
      		<form:input path="popupTitleNm" size="73" maxlength="255"/>
      		<form:errors path="popupTitleNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="fileUrl">팝업창URL</label>
    	</th>
    	<td>
      		<form:input path="fileUrl" size="73" maxlength="255"/>
      		<form:errors path="fileUrl" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label id="popupWlc">팝업창위치</label>
    	</th>
    	<td>
       		가로 <form:input path="popupWlc" size="5" maxlength="10"/>  
       		세로<form:input path="popupHlc" size="5" maxlength="10"/>
  			<form:errors path="popupWlc" cssClass="error"/>
  			<form:errors path="popupHlc" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label id="popupWSize">팝업창사이즈</label>
    	</th>
    	<td>
  			WIDTH <form:input path="popupWSize" size="5" maxlength="10"/>  
  			HEIGHT<form:input path="popupHSize" size="5" maxlength="10"/>
  			<form:errors path="popupWSize" cssClass="error"/>
  			<form:errors path="popupHSize" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label id="ntceBgndeHH">게시 기간</label>
    	</th>
    	<td>
    		<input type="text" name="ntceBgndeYYYMMDD" id="ntceBgndeYYYMMDD" size="10" maxlength="10" readonly title="게시기간">
    		<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].ntceBgndeYYYMMDD); return false;">
    			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
    		</a>
    		<form:select path="ntceBgndeHH">
        		<form:options items="${ntceBgndeHH}" itemValue="code" itemLabel="codeNm"/>
    		</form:select>H
    		<form:select path="ntceBgndeMM">
        		<form:options items="${ntceBgndeMM}" itemValue="code" itemLabel="codeNm"/>
    		</form:select>M
    		&nbsp;&nbsp;~&nbsp;&nbsp;
    		<input type="text" name="ntceEnddeYYYMMDD" id="ntceEnddeYYYMMDD" size="10" maxlength="10" readonly title="게시기간">
    		<a href="#" onClick="javascript:fn_aram_NormalCalendar('', document.forms[0].ntceEnddeYYYMMDD); return false;">
    			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">
    		</a>
		    <form:select path="ntceEnddeHH">
		        <form:options items="${ntceEnddeHH}" itemValue="code" itemLabel="codeNm"/>
		    </form:select>H
		    <form:select path="ntceEnddeMM">
		        <form:options items="${ntceEnddeMM}" itemValue="code" itemLabel="codeNm"/>
		    </form:select>M
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label id="stopVewAt">그만보기 설정 여부</label>
    	</th>
    	<td>
			<input type="radio" name="stopVewAt" value="Y" checked>Y
			<input type="radio" name="stopVewAt" value="N">N
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label id="ntceAt">계시 상태</label>
    	</th>
    	<td>
			<input type="radio" name="ntceAt" value="Y" checked>Y
			<input type="radio" name="ntceAt" value="N">N
    	</td>
  	</tr>
</table>

<form:hidden path="ntceBgnde" />
<form:hidden path="ntceEndde" />
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="popupManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("popupManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/pwm/listPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("popupManageVO");
    
	if(!validatePopupManageVO(varForm)){
		return;
	}
	
	var ntceBgndeYYYMMDD = document.getElementById('ntceBgndeYYYMMDD').value;
	var ntceEnddeYYYMMDD = document.getElementById('ntceEnddeYYYMMDD').value;

	var iChkBeginDe = Number( ntceBgndeYYYMMDD.replaceAll("-","") );
	var iChkEndDe = Number( ntceEnddeYYYMMDD.replaceAll("-","") );

	if(iChkBeginDe> iChkEndDe || iChkEndDe < iChkBeginDe ){
		alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
		return;
	}

	varForm.ntceBgnde.value = ntceBgndeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('ntceBgndeHH') +  fn_aram_SelectBoxValue('ntceBgndeMM');
	varForm.ntceEndde.value = ntceEnddeYYYMMDD.replaceAll('-','') + fn_aram_SelectBoxValue('ntceEnddeHH') +  fn_aram_SelectBoxValue('ntceEnddeMM');

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/ion/pwm/insertPopup.do";
		varForm.submit();
	}
}

</script>
