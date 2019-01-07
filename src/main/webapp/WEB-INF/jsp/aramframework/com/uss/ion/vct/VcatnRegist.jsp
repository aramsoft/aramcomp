<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : VcatnRegist.jsp
 * @Description : 휴가 신청
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
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>휴가 신청</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="vcatnManageVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="applcntId" id="applcntId"/>

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>휴가 신청자</h2>
</div>

<table class="table-register" summary="신청자 정보">
<caption>신청자 정보</caption>
  	<tr>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		신청자
    	</th>          
    	<td width="30%">
    		<c:out value='${vcatnManageVO.applcntNm}'/>
    	</td>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		소속
    	</th>          
    	<td width="30%">
    		<c:out value='${vcatnManageVO.orgnztNm}'/>
    	</td>
  	</tr> 
</table>

<table summary="신청자 연차 정보">
<caption>신청자 연차 정보</caption>
	<tr>
		<td colspan="4">&nbsp; </td>
	</tr>
	<tr>
		<td width="120px">[발생연차: ${indvdlYrycManageVO.occrncYrycCo}  ]</td>
		<td width="120px">[사용연차: ${indvdlYrycManageVO.useYrycCo   }  ]</td>
		<td width="120px">[잔여연차: ${indvdlYrycManageVO.remndrYrycCo}  ]</td>
		<td width="340px"> </td>
	</tr>
	<tr>
		<td colspan="4">&nbsp; </td>
	</tr>
</table>

<table class="table-register" summary="휴가관리 등록">
<caption>휴가관리 등록</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="vcatnSe">휴가구분</label>
    	</th>          
    	<td colspan="3">
      		<form:select path="vcatnSe" onchange="javascript:fncNoonSeSpan(this.value);" title="휴가구분">
	      		<form:options items="${COM056_vcatnSe}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="bgnde">시작일자</label>
    	</th>          
    	<td width="30%">
      		<form:hidden path="bgnde" />
	    	<c:if test="${!empty vcatnManageVO.bgnde}">
 				<c:set var="bgndeVal" value="${fn:substring(vcatnManageVO.bgnde, 0,4)}-${fn:substring(vcatnManageVO.bgnde, 4,6)}-${fn:substring(vcatnManageVO.bgnde, 6,8)}"/>
      		</c:if>
      		<input name="bgndeView" id="bgndeView" type="text" size="10" title="휴가 시작일자" value="${bgndeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].bgnde, document.forms[0].bgndeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
     	<th width="20%">
     		<span class="required_icon"></span>
     		<span id="nameSpan"><label for="endde">종료일자</label></span>
     	</th>          
    	<td width="30%">
     		<span id="noonSeSpan"> 
      		<form:hidden path="endde" />
	    	<c:if test="${!empty vcatnManageVO.endde}">
 				<c:set var="enddeVal" value="${fn:substring(vcatnManageVO.endde, 0,4)}-${fn:substring(vcatnManageVO.endde, 4,6)}-${fn:substring(vcatnManageVO.endde, 6,8)}"/>
      		</c:if>
      		<input name="enddeView" id="enddeView" type="text" size="10" title="휴가 종료일자" value="${enddeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].endde, document.forms[0].enddeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    		</span>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="vcatnResn">휴가사유</label>
    	</th>          
    	<td colspan="3">
           <textarea id="vcatnResn" name="vcatnResn" class="txArea" rows="4" cols="70" title="휴가사유"><c:out value='${vcatnManage.vcatnResn}' escapeXml="false" /></textarea>
    	</td>
  	</tr>
</table> 

<!-- 결재권자 지정 Include -->
<jsp:include page="/WEB-INF/jsp/aramframework/com/uss/ion/ism/InfrmlSanctnRegist.jsp" flush="true"/> 
<!-- //결재권자 지정 Include -->

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="vcatnManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("vcatnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/listVcatn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert() {
	var varForm = document.getElementById("vcatnManageVO");
	
    if(!validateVcatnManageVO(varForm)){           
        return;
    }

    if(varForm.bgnde.value == ""){
        alert("휴가 시작일자가 존재하지 않습니다. 휴가일자를 확인하세요.");
        return;
    }
    if(varForm.endde.value == "" && varForm.vcatnSe.value != "02"){
        alert("휴가종료일자가 존재하지 않습니다. 휴가일자를 확인하세요.");
        return;
    }
    
    if(varForm.bgnde.value> varForm.endde.value){
        if(varForm.vcatnSe.value != "02"){
	        alert("휴가일자 검색조건의 시작일자가 종료일자보다  늦습니다. 휴가일자를 확인하세요.");
	        return;
        }
	}

    if(varForm.vcatnSe.value == "02"){
    	varForm.endde.value = varForm.bgnde.value;
    }

	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/insertVcatn.do";
       	varForm.submit();
    }
}

function fncNoonSeSpan(vValue){
	var vTemp = "";
    if(vValue != "02"){
		vTemp += "  <input name='enddeView' type='text' size='10' value='<c:out value='${resultInfo.endde  }'/>' readOnly";
		vTemp += "  <img src='${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif' onClick='javascript:fn_aram_NormalCalendar(document.forms[0].endde, document.forms[0].enddeView);' alt='달력' width='15' height='15'> ";
        nameSpan.innerHTML = "<label for='endde'>종료일자</label>";
    }
    if(vValue == "02"){
    	vTemp += "  <input name='noonSe' type='radio' value='1' checked>오전";
    	vTemp += "  <input name='noonSe' type='radio' value='2'>오후";
        nameSpan.innerHTML = "<label for='noonSe'>정오구분</label>";
    }
    noonSeSpan.innerHTML = vTemp;
}

</script>
