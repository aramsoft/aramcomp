<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : KnoSpecialistEdit.jsp
  * @Description : 지식전문가 수정
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
	<h2>지식전문가 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoSpecialistVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="orgnztId"/>
<form:hidden path="knoTypeCd"/>
<form:hidden path="speId"/>
<form:hidden path="appTypeCd"/>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 지식전문가 정보를 제공하며, 조직명, 지식유형명, 전문가성명, 등급, 전문가설명, 승인일자 정보로 구성되어 있습니다 .">
<caption>지식전문가 수정</caption>
	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		조직명
    	</th>
 		<td>
 			${knoSpecialistVO.orgnztNm}
 		</td>
  	</tr>
	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지식유형
    	</th>
 		<td>
 			${knoSpecialistVO.knoTypeNm}
 		</td>
  	</tr>
	<tr>
    	<th>
    		<span class="required_icon"></span>
    		전문가명
    	</th>
 		<td>
 			${knoSpecialistVO.userNm}
 		</td>
  	</tr>
	<tr>
    	<th>
    		<span class="required_icon"></span>
    		등급
    	</th>
 		<td>
			<select name="appTypeNm" title="등급 선택">
			    <option value="1" <c:if test="${knoSpecialistVO.appTypeCd == '1'}">selected</c:if>>수석</option>
			    <option value="2" <c:if test="${knoSpecialistVO.appTypeCd == '2'}">selected</c:if>>책임</option>
			    <option value="3" <c:if test="${knoSpecialistVO.appTypeCd == '3'}">selected</c:if>>선임</option>
			</select>
 		</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		전문가설명
    	</th>
    	<td>
      		<textarea name="speExpCn" class="textarea" title="전문가설명" cols="300" rows="10" style="width:450px;">${knoSpecialistVO.speExpCn}</textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		승인일자
    	</th>
    	<td>
      		<form:hidden path="speConfmDe" />
	    	<c:if test="${!empty knoSpecialistVO.speConfmDe}">
				<c:set var="speConfmDeVal" value="${fn:substring(knoSpecialistVO.speConfmDe, 0,4)}-${fn:substring(knoSpecialistVO.speConfmDe, 4,6)}-${fn:substring(knoSpecialistVO.speConfmDe, 6,8)}"/>
      		</c:if>
      		<input name="speConfmDeView" id="speConfmDeView" type="text" size="10" title="승인일자" value="${speConfmDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].speConfmDe, document.forms[0].speConfmDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	  		<form:errors path="speConfmDe" cssClass="error"/>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="knoSpecialistVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
 window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.appTypeNm.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("knoSpecialistVO");
    varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/listKnoSpecialist.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("knoSpecialistVO");

    if(!validateKnoSpecialistVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/spe/spe/updateKnoSpecialist.do";
		varForm.submit();
	}
}

</script>
