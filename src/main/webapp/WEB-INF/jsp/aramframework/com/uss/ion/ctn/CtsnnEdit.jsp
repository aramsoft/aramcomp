<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name  : CtsnnEdit.jsp
 * @Description : 경조사 수정
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
	<h2>경조사 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="ctsnnManageVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="ctsnnId"/>
<form:hidden  path="usid"/>
<form:hidden  path="reqstDe"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="sanctnerId"/>

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>경조 신청자</h2>
</div>

<table class="table-detail" summary="소속정보">
<caption>소속정보</caption>
  	<tr>
    	<th width="20%">
    		신청자
    	</th>          
    	<td width="30%">
    		<c:out value='${ctsnnManageVO.usNm}'/>
    	</td>
    	<th width="20%">
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${ctsnnManageVO.orgnztNm}'/>
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-register" summary="경조  수정">
<caption>경조수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		경조명
    	</th>          
    	<td colspan="3">
    		<form:input  path="ctsnnNm" title="경조명" />
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		경조구분
    	</th>          
    	<td width="30%">
      		<form:select path="ctsnnCd" title="경조구분">
	      		<form:options items="${COM054_ctsnn}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
      	</td>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="trgterNm">발생일</label>
    	</th>          
    	<td width="30%">
      		<form:hidden path="occrrDe" />
	    	<c:if test="${!empty ctsnnManageVO.occrrDe}">
 				<c:set var="occrrDeVal" value="${fn:substring(ctsnnManageVO.occrrDe, 0,4)}-${fn:substring(ctsnnManageVO.occrrDe, 4,6)}-${fn:substring(ctsnnManageVO.occrrDe, 6,8)}"/>
      		</c:if>
      		<input name="occrrDeView" id="occrrDeView" type="text" size="10" title="경조 발생일" value="${occrrDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].occrrDe, document.forms[0].occrrDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="trgterNm">대상자명</label>
    	</th>          
    	<td colspan="3">
    		<form:input  path="trgterNm" size="20" title="대상자명"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="relate">관계</label>
    	</th>          
    	<td>
      		<form:select path="relate" title="관계">
	      		<form:options items="${COM073_relate}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
    	<th>
    		<span class="required_icon"></span>
    		<label for="brth">생년월일</label>
    	</th>          
    	<td>
      		<form:hidden path="brth" />
	    	<c:if test="${!empty ctsnnManageVO.brth}">
 				<c:set var="brthVal" value="${fn:substring(ctsnnManageVO.brth, 0,4)}-${fn:substring(ctsnnManageVO.brth, 4,6)}-${fn:substring(ctsnnManageVO.brth, 6,8)}"/>
      		</c:if>
      		<input name="brthView" id="brthView" type="text" size="10" title="생년월일" value="${brthVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].brth, document.forms[0].brthView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
			<span class="norequired_icon"></span>
    		<label for="remark">비고</label>
    	</th>          
    	<td colspan="3">
      		<form:textarea path="remark" rows="4" cols="70" cssClass="txArea" title="비고"/>
      		<form:errors path="remark" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"> 
	<jsp:param name="infrmlSanctnId" value="${ctsnnManageVO.infrmlSanctnId}"/>
</jsp:include>
<!-- //결재권자 정보 Include -->

<!-- 검색조건 유지 -->
<form:hidden path="searchFromDate" />
<form:hidden path="searchToDate" />
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="ctsnnManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("ctsnnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/listCtsnn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("ctsnnManageVO");

	if(!validateCtsnnManageVO(varForm)){           
       	return;
   	}
   	
	if(confirm("<spring:message code='common.update.msg'/>")){
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/updateCtsnn.do";
        varForm.submit();
    }
}

</script>
