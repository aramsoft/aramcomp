<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : SystemCntcEdit.jsp
  * @Description : 시스템연계 수정
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
	<h2>시스템연계 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
 	</div>
</div>

<!-- 상단타이틀 -->
<form:form commandName="systemCntcVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cntcId"/>
<form:hidden path="confmAt"/>

<!-- 등록  폼 영역  -->
<table class="table-register" summary="시스템연계  수정을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		연계ID
    	</th>
    	<td width="80%">
    		<c:out value="${systemCntcVO.cntcId}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		연계명
    	</th>
    	<td>
      		<form:input  path="cntcNm" size="60" maxlength="60" title="연계명" />
      		<form:errors path="cntcNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		연계유형
    	</th>
    	<td>
      		<form:input  path="cntcType" size="60" maxlength="60" title="연계유형"/>
      		<form:errors path="cntcType" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		제공기관
    	</th>
    	<td>
        	<form:select path="provdInsttId"  onChange="javascript:fn_aram_get_CodeList('provdInsttId');" title="제공기관선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcInsttList}" itemValue="insttId" itemLabel="insttNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		제공시스템
    	</th>
    	<td>
        	<form:select path="provdSysId"  onChange="javascript:fn_aram_get_CodeList('provdSysId');" title="제공시스템선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcProvdSystemList}" itemValue="sysId" itemLabel="sysNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		제공서비스
    	</th>
    	<td>
        	<form:select path="provdSvcId"  title="제공서비스선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcProvdServiceList}" itemValue="svcId" itemLabel="svcNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		요청기관
    	</th>
    	<td>
        	<form:select path="requstInsttId"  onChange="javascript:fn_aram_get_CodeList('requstInsttId');" title="요청기관선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcInsttList}" itemValue="insttId" itemLabel="insttNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		요청시스템
    	</th>
    	<td>
        	<form:select path="requstSysId"  title="요청시스템선택 ">
            	<form:option value="" label="--선택하세요--"/>
            	<form:options items="${cntcRequstSystemList}" itemValue="sysId" itemLabel="sysNm"/>
        	</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		유효시작일자
    	</th>
    	<td>
      		<form:hidden path="validBeginDe" />
 	    	<c:if test="${!empty systemCntcVO.validBeginDe}">
 				<c:set var="validBeginDeVal" value="${fn:substring(systemCntcVO.validBeginDe, 0,4)}-${fn:substring(systemCntcVO.validBeginDe, 4,6)}-${fn:substring(systemCntcVO.validBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="validBeginDeView" id="validBeginDeView" type="text" size="10" title="시작일자입력" value="${validBeginDeVal}"  readonly />
	  		<a href="#" onclick="javascript:fn_aram_NormalCalendar(document.forms[0].validBeginDe, document.forms[0].validBeginDeView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
	  			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
	  		</a>
    	</td>
  	</tr>  
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		유효종료일자
    	</th>
    	<td>
      		<form:hidden path="validEndDe" />
 	    	<c:if test="${!empty systemCntcVO.validEndDe}">
 				<c:set var="validEndDeVal" value="${fn:substring(systemCntcVO.validEndDe, 0,4)}-${fn:substring(systemCntcVO.validEndDe, 4,6)}-${fn:substring(systemCntcVO.validEndDe, 6,8)}"/>
      		</c:if>
      		<input name="validEndDeView" id="validEndDeView" type="text" size="10" title="종료일자입력" value="${validEndDeVal}"  readonly />
	  		<a href="#" onclick="javascript:fn_aram_NormalCalendar(document.forms[0].validEndDe, document.forms[0].validEndDeView); return false;" style="selector-dummy:expression(this.hideFocus=false);">
	  			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif" alt="달력창팝업버튼이미지">
	  		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		사용여부
    	</th>
    	<td>
      		<form:select path="useAt" title="사용여부">
	      		<form:option value="Y" label="Y"/>
	      		<form:option value="N" label="N"/>
      		</form:select>
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
<validator:javascript formName="systemCntcVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("systemCntcVO");
    varForm.action = "${pageContext.request.contextPath}/ssi/syi/sim/listSystemCntc.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("systemCntcVO");
    
	if(!validateSystemCntcVO(varForm)){
		return;
	}
	
    var validBeginDe = Number(document.getElementById('validBeginDe').value);
    var validEndDe   = Number(document.getElementById('validEndDe'  ).value);

    if(validEndDe < validBeginDe){
        alert("유효시작일자는 유효종료일자 보다 클수 없다.");
        return;
    }
    
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/ssi/syi/sim/updateSystemCntc.do";
		varForm.submit();
	}
}

/* ********************************************************
* CodeList 가져오기
******************************************************** */
function fn_aram_get_CodeList(choose){
    var varForm = document.getElementById("systemCntcVO");

	if(choose == 'provdInsttId') {
		varForm.provdSysId.value = "";
		varForm.provdSvcId.value = "";
	} else
	if(choose == 'provdSysId') {
		varForm.provdSvcId.value = "";
	} else
	if(choose == 'requstInsttId') {
		varForm.requstSysId.value = "";
	}

    varForm.action = "${pageContext.request.contextPath}/ssi/syi/sim/editSystemCntc.do";
    varForm.submit();
}

</script>
