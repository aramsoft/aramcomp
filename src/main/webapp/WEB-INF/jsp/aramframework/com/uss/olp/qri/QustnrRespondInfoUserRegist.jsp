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
  * @Class Name : QustnrRespondInfoUserRegist.jsp
  * @Description : 설문참여 등록
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>설문참여 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrRespondInfoVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrId" />

<div class="content_title">
	<h2>설문응답자 정보</h2>
</div>

<table class="table-register" summary="이 표는 설문제목 정보를 제공하며, 성별, 직업, 생년월일, 응답자명, 설문정보 , 설문제목, 설문작성 안내내용, 설문대상, 설문기간 정보로 구성되어 있습니다 .">
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		성별
    	</th>
    	<td width="80%">
			<select name="sexdstnCode" title="성별 선택">
				<option value="">선택</option>
				<c:forEach items="${COM014_sexdstn}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq Emplyrinfo.sexdstnCode}">selected</c:if>>${comCodeList.codeNm}</option>
				</c:forEach>
			</select>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		직업
    	</th>
    	<td>
			<select name="occpTyCode" title="직업 선택">
				<option value="">선택</option>
				<c:forEach items="${COM034_occpType}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}">${comCodeList.codeNm}</option>
				</c:forEach>
			</select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		생년월일
    	</th>
    	<td>
       		<select name="brthYYYY" id="brthYYYY" title="연도 선택">
       			<option value="">선택</option>
	     		<c:forEach var="h" begin="1960" end="2009" step="1">
	      		<option value="${h}" <c:if test="${fn:substring(emplyrinfo.brth, 0, 4) ==  h}">selected</c:if>>${h}</option>
	      		</c:forEach>
       		</select>년

       		<select name="brthMM" id="brthMM" title="월 선택">
       			<option value="">선택</option>
	     		<c:forEach var="h" begin="1" end="12" step="1">
				<c:choose>
			    <c:when test="${h < 10}">
			 		<c:set var="brthMM" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    	<c:set var="brthMM" value="${h}"/>
			    </c:otherwise>
				</c:choose>
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(Emplyrinfo.brth, 4, 6) ==  brthMM}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
	   		</select>월
       		<select name="brthDD" id="brthDD" title="일 선택">
       			<option value="">선택</option>
	     		<c:forEach var="h" begin="1" end="31" step="1">
				<c:choose>
			    <c:when test="${h < 10}">
			 		<c:set var="brthDD" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    	<c:set var="brthDD" value="${h}"/>
			    </c:otherwise>
				</c:choose>
	      		<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(Emplyrinfo.brth, 6, 8) ==  brthDD}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      		</c:forEach>
       		</select>일

       		<input name="brth" id="brth" type="hidden" value="">
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		응답자명
    	</th>
    	<td>
			<input name="respondNm" type="text" title="응답자명 입력" size="73" value="${Emplyrinfo.userNm}" maxlength="50" style="width:120px;">
    	</td>
  	</tr>
</table>
<!-- 등록  폼 영역  -->

<div class="content_title">
	<h2>설문정보</h2>
</div>

<table class="table-register">
	<tr>
    	<th scope="row" width="20%" height="35" class="column_title">
    		<span class="norequired_icon"></span>
    		설문제목 
    	</th>
    	<td width="80%">
			<c:out value="${fn:replace(comtnqestnrinfo[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
    	</td>
	</tr>
	<tr>
	    <th scope="row" width="20%" height="35" class="column_title">
    		<span class="norequired_icon"></span>
	    	설문목적 
	    </th>
	    <td width="80%">
			<c:out value="${fn:replace(comtnqestnrinfo[0].qestnrPurps , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr>
	    <th scope="row" width="20%" height="35" class="column_title">
    		<span class="norequired_icon"></span>
	    	설문작성 안내내용 
	    </th>
	    <td width="80%">
			<c:out value="${fn:replace(comtnqestnrinfo[0].qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
	    </td>
	</tr>
	<tr>
	    <td height="35" colspan="2">
	    <table>
	    	<tr>
	    		<td width="50%" align="center">
	    			<b>설문대상  :</b>
					<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '1'}">학생</c:if>
					<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '2'}">대학생</c:if>
					<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '3'}">군인</c:if>
					<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '4'}">교사</c:if>
					<c:if test="${comtnqestnrinfo[0].qestnrTrget ==  '5'}">기타</c:if>
	    		</td>
	    		<td width="50%" align="center">
	    			<b>설문기간 :</b>
	    			<c:out value="${comtnqestnrinfo[0].qestnrBeginDe}" /> ~ <c:out value="${comtnqestnrinfo[0].qestnrEndDe}" />
	    		</td>
	    	</tr>
    	</table>
    	</td>
	</tr>

	<%-- 설문템플릿설정 --%>
	<c:import charEncoding="utf-8" url="/uss/olp/qri/template/template.do">
		<c:param name="templateUrl" value="${comtnqestnrinfo[0].qestnrTmplatCours}" />
	</c:import>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrRespondInfoVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/listQustnrRespondInfoUser.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("qustnrRespondInfoVO");

	//설문응답자  Validtation

	if(varForm.sexdstnCode.selectedIndex == 0){
		alert('설문응답자정보 성별을  택해주세요!');
		varForm.sexdstnCode.focus();
		return;
	}else if(varForm.occpTyCode.selectedIndex == 0){
		alert('설문응답자정보 직업을 선택해주세요!');
		varForm.occpTyCode.focus();
		return;
	}else if(varForm.brthYYYY.selectedIndex == 0){
		alert('설문응답자정보 생년월일(년)을 선택해주세요!');
		varForm.brthYYYY.focus();
		return;
	}else if(varForm.brthMM.selectedIndex == 0){
		alert('설문응답자정보 생년월일(월)을 선택해주세요!');
		varForm.brthMM.focus();
		return;
	}else if(varForm.brthDD.selectedIndex == 0){
		alert('설문응답자정보 생년월일(일)을 선택해주세요!');
		varForm.brthDD.focus();
		return;
	}else if(varForm.respondNm.value == ""){
		alert('설문응답자정보 응답자명을 입력해주세요!');
		varForm.respondNm.focus();
		return;
	}

	//설문정보 Validtation
	<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
	<c:if test="${QestmInfo.qestnTyCode ==  '1'}">
	if((!fn_aram_selectBoxChecking("${QestmInfo.qestnrQesitmId}"))){
		alert('${status1.count}번 설문문항을 작성해 주세요!');
		document.getElementsByName("${QestmInfo.qestnrQesitmId}")[0].focus();
		return;
	}

		<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status01">
		<c:if test="${QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">

			<c:if test="${QestmItem.etcAnswerAt eq  'Y'}">
			//기타답변을 선택했는체크
			if(fn_aram_RadioBoxValue("${QestmInfo.qestnrQesitmId}") == "${QestmItem.qustnrIemId}"){
				if(document.getElementById("ETC_${QestmItem.qustnrIemId}").value == ""){
					alert('${status1.count}번 설문문항 기타답변을 작성해주세요!');
					document.getElementById("ETC_${QestmItem.qustnrIemId}").focus();
					return;
				}
			}

			</c:if>
		</c:if>
		</c:forEach>
	</c:if>

	<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
	if( document.getElementById("${QestmInfo.qestnrQesitmId}").value == "" ){
		//alert('${status1.count}. ${QestmInfo.qestnCn}       \n\n설문문항을 작성해 주세요!');
		alert('${status1.count}번 설문문항을 작성해 주세요!');
		document.getElementById("${QestmInfo.qestnrQesitmId}").focus();
		return;
	}
	</c:if>
	</c:forEach>

	varForm.brth.value = fn_aram_SelectBoxValue('brthYYYY') + "" + fn_aram_SelectBoxValue('brthMM') + "" + fn_aram_SelectBoxValue('brthDD');

	if(confirm("<spring:message code="common.save.msg" />")){
	    varForm.action = "${pageContext.request.contextPath}/uss/olp/qri/insertQustnrRespondInfoUser.do";
	    varForm.submit();
	}
}

/************************************************************************
//라디오박스 : 몇개선택했는데 체크해주는함수
************************************************************************/
function fn_aram_checkbox_amout_max( sbName){
	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	var reusltCount = 0;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reusltCount++;
		}
	}

	return reusltCount;
}

/************************************************************************
//라디오박스 : 최대선택건수 체크
************************************************************************/
function fn_aram_checkbox_amout( sbName, sbCount, sbObj){

	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	var reusltCount = 0;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reusltCount++;
		}
	}

	if(reusltCount> sbCount){
	 	alert("최대선택건수 [" + sbCount + "]건을  초과하였습니다!" );
	 	sbObj.checked=false;
	 	return;
	}
}

</script>

