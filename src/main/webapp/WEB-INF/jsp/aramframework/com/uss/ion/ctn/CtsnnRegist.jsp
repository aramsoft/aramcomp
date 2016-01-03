<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : CtsnnRegist.jsp
 * @Description : 경조사 신청
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
	<h2>경조사 신청</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="ctsnnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>경조 신청자</h2>
</div>

<table class="table-register" summary="소속정보">
<caption>소속정보</caption>
  	<tr>
    	<th width="20%"  scope="row">
 			<span class="norequired_icon"></span>
    		<label for="usid">경조신청자</label>
    	</th>          
    	<td width="30%">
        	<input name="usNm" id="usNm" type="text" size="10" value="" title="경조신청자" readonly>
        	<form:hidden path="usid"/>
	    	<span class="link"><!-- a href="/uss/ion/ism/listSanctnerPopup.do" target="_blank"  title="새 창으로 이동" onClick="fn_aram_schdulCharger_LeaderSchdule('경조신청자', 'usid', '', 'usNm', 'usOrgnztNm');return false;"-->
	    	<a href="/uss/ion/ism/listSanctnerPopup.do" target="_blank"  title="새 창으로 이동"  onClick="fn_aram_get_ctsnn('경조신청자', 'usid', '', 'usNm', 'usOrgnztNm');return false;">
	    		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="검색"  width="15" height="15">
	    	</a>
	    	</span>
    	</td>
    	<th width="20%"  scope="row">
 			<span class="norequired_icon"></span>
    		<label for="usOrgnztNm">소속</label>
    	</th>          
    	<td width="30%">
    		<input name="usOrgnztNm" id="usOrgnztNm" type="text" size="25" value="" title="소속" readonly>
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-register"  summary="경조신청">
<caption>경조신청</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="ctsnnNm">경조명</label>
    	</th>          
    	<td colspan="3">
    		<form:input  path="ctsnnNm" title="경조명" />
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="ctsnnCd">경조구분</label>
    	</th>          
    	<td width="30%">
      		<form:select path="ctsnnCd" title="경조구분">
	      		<form:options items="${COM054_ctsnn}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="occrrDe">발생일</label>
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
    		<form:input  path="trgterNm" title="대상자명"/>
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

<!-- 결재권자 지정 Include -->
<jsp:include page="/WEB-INF/jsp/aramframework/com/uss/ion/ism/InfrmlSanctnRegist.jsp" flush="true"/> 
<!-- //결재권자 지정 Include -->

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
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("ctsnnManageVO");

    if(!validateCtsnnManageVO(varForm)){           
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/insertCtsnn.do";
        varForm.submit();
    }
}
		
var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
* ******************************************************** */
function fn_aram_get_ctsnn(strTitle, uniqId, emplNo, emplyrNm, orgnztNm){
	gArguments["title"]    = strTitle;
	if( uniqId != "" )   gArguments["uniqId"]   = document.getElementById(uniqId);
	if( emplNo != "" )   gArguments["emplNo"]   = document.getElementById(emplNo);
	if( emplyrNm != "" ) gArguments["emplyrNm"] = document.getElementById(emplyrNm);
	if( orgnztNm != "" ) gArguments["orgnztNm"] = document.getElementById(orgnztNm);

	var url = "/uss/ion/ism/listSanctnerPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>
