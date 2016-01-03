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
  * @Class Name : QustnrRegist.jsp
  * @Description : 설문관리 등록
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
	<h2>설문관리 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrId" />

<table class="table-register" summary="등록 을 제공한다">
<caption>등록 을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="qestnrSj">설문제목</label>
    	</th>
    	<td width="80%">
      		<form:input path="qestnrSj" size="60" cssClass="txInput" maxlength="250" title="설문제목 입력"/>
      		<form:errors path="qestnrSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnrPurps">설문목적</label>
    	</th>
    	<td>
    		<form:textarea path="qestnrPurps" rows="3" cols="20" cssClass="txArea" title="설문목적 입력"/>
    		<form:errors path="qestnrPurps" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnrWritngGuidanceCn">설문작성안내 내용</label>
    	</th>
    	<td>
    		<form:textarea path="qestnrWritngGuidanceCn" rows="3" cols="20" cssClass="txArea" title="설문작성안내 내용 입력"/>
    		<form:errors path="qestnrWritngGuidanceCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnrTrget">설문대상</label>
    	</th>
    	<td>
			<form:select path="qestnrTrget" title="설문대상 선택">
				<form:option value="" label="선택" />
		    	<form:options items="${COM034_occpType}" itemValue="code" itemLabel="codeNm"/>
			</form:select>
			<form:errors path="qestnrTrget" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnrBeginDe">설문기간</label>
     	</th>
    	<td>
      		<form:hidden path="qestnrBeginDe" />
	    	<c:if test="${!empty qustnrManageVO.qestnrBeginDe}">
 				<c:set var="qestnrBeginDeVal" value="${fn:substring(qustnrManageVO.qestnrBeginDe, 0,4)}-${fn:substring(qustnrManageVO.qestnrBeginDe, 4,6)}-${fn:substring(qustnrManageVO.qestnrBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="qestnrBeginDeView" id="qestnrBeginDeView" type="text" size="10" title="POLL시작일자" value="${qestnrBeginDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].qestnrBeginDe, document.forms[0].qestnrBeginDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		~
      		<form:hidden path="qestnrEndDe" />
	    	<c:if test="${!empty qustnrManageVO.qestnrEndDe}">
 				<c:set var="qestnrEndDeVal" value="${fn:substring(qustnrManageVO.qestnrEndDe, 0,4)}-${fn:substring(qustnrManageVO.qestnrEndDe, 4,6)}-${fn:substring(qustnrManageVO.qestnrEndDe, 6,8)}"/>
      		</c:if>
      		<input name="qestnrEndDeView" id="qestnrEndDeView" type="text" size="10" title="POLL시작일자" value="${qestnrEndDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].qestnrEndDe, document.forms[0].qestnrEndDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="qestnrBeginDe" cssClass="error"/>
      		<form:errors path="qestnrEndDe" cssClass="error"/>
    	</td>
  	</tr>
    <tr>
    	<th>
     		<span class="required_icon"></span>
    		템플릿 유형
    	</th>
    	<td>
		<table>
			<tr>
				<c:forEach items="${listQustnrTmplat}" var="resultQustnrTmplat" varStatus="status">
				<td><img src="${pageContext.request.contextPath}/uss/olp/qtm/getQustnrTmplatImage.do?qestnrTmplatId=${resultQustnrTmplat.qestnrTmplatId}" align="middle" alt="템플릿유형 이미지" title="템플릿유형 이미지"></td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${listQustnrTmplat}" var="resultQustnrTmplat" varStatus="status">
		  		<td height="10" align="center"><input type="radio" name="qestnrTmplatId" value="${resultQustnrTmplat.qestnrTmplatId}" style="border:0px" checked>${resultQustnrTmplat.qestnrTmplatTy}</td>
				</c:forEach>
			</tr>
		</table>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qustnrManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function(){

	//document.getElementById("qestnrBeginDe").value = "2008-01-01";
	//document.getElementById("qestnrEndDe").value = "2008-01-30";
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qmc/listQustnr.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("qustnrManageVO");
    
	if(!validateQustnrManageVO(varForm)){
		return;
	}
	
	var sStartDay = varForm.qestnrBeginDe.value;
	var sEndDay = varForm.qestnrEndDe.value;

	var iStartDay = parseInt(sStartDay);
	var iEndDay = parseInt(sEndDay);

	if(iStartDay> iEndDay || iEndDay < iStartDay){
		alert("설문기간  시작일은 종료일  보다 클수 없고 \n\n설문기간 종료일은 시작일 보다 작을수 없습니다!");
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
	    varForm.action = "${pageContext.request.contextPath}/uss/olp/qmc/insertQustnr.do";
		varForm.submit();
	}
}

</script>

