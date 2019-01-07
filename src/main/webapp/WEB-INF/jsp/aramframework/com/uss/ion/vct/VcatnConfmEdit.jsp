<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name  : VcatnConfmEdit.jsp
 * @Description : 휴가 승인 수정
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
	<h2>휴가 승인 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${vcatnManageVO.confmAt eq 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_confirm(); return false;">승인</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_reject(); return false;">반려</a></span>
	  	</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="vcatnManageVO" method="post" action="#">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="applcntId"/>
<form:hidden  path="vcatnSe"/>
<form:hidden  path="bgnde"/>
<form:hidden  path="endde"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="occrrncYear"/>
<form:hidden  path="confmAt"/>
<form:hidden  path="sanctnerId"/>
<form:hidden  path="returnResn"/>
<form:hidden  path="vcatnResn"/>

<div style="margin-top:10px;"></div>

<div class="content_title">
	<h2>휴가 신청자</h2>
</div>

<table class="table-register" summary="신청자 정보">
<caption>신청자 정보</caption>
  	<tr>
    	<th width="20%"  class="column_title">
    		<span class="norequired_icon"></span>
    		신청자
    	</th>          
    	<td width="30%">
    		<c:out value='${vcatnManageVO.applcntNm}'/>
    	</td>
    	<th width="20%"  class="column_title">
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

<table class="table-register"  summary="휴가승인/반려">
<caption>휴가승인/반려</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		휴가구분
    	</th>          
    	<td colspan="3">
    		<c:out value='${vcatnManageVO.vcatnSeNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		시작일자
    	</th>          
    	<td width="30%">
        	<c:out value='${fn:substring(vcatnManageVO.bgnde, 0,4)}-${fn:substring(vcatnManageVO.bgnde, 4,6)}-${fn:substring(vcatnManageVO.bgnde, 6,8)}'/>
    	</td>
    	<c:if test="${vcatnManageVO.vcatnSe ne '02' }">
	    	<th width="20%">
	    		<span class="required_icon"></span>
	    		종료일자
	    	</th>          
	    	<td width="30%">
	        	<c:out value='${fn:substring(vcatnManageVO.endde, 0,4)}-${fn:substring(vcatnManageVO.endde, 4,6)}-${fn:substring(vcatnManageVO.endde, 6,8)}'/>
	    	</td>
    	</c:if>
    	<c:if test="${vcatnManageVO.vcatnSe eq '02' }">
	    	<th width="20%">
	    		<span class="required_icon"></span>
	    		정오구분
	    	</th>          
	    	<td width="30%">
	       		<c:if test="${vcatnManageVO.noonSe eq '1' }">오전 </c:if>
	       		<c:if test="${vcatnManageVO.noonSe eq '2' }">오후 </c:if>
	    	</td>
    	</c:if>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		휴가사유
    	</th>          
    	<td colspan="3">
       		<textarea id="vcatnResnView" name="vcatnResnView" class="txArea" rows="4" cols="70" title="휴가사유" readonly><c:out value='${vcatnManageVO.vcatnResn}' escapeXml="false"/></textarea>
    	</td>
  	</tr>
</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"/> 
<!-- //결재권자 정보 Include -->

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="vcatnManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 휴가승인목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("vcatnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/listVcatnConfm.do";
	varForm.submit();
}

<c:if test="${vcatnManageVO.confmAt eq 'A' }">

var gArguments = new Array();

/* ********************************************************
 * 승인 처리  팝업창열기
 * fncConfmVcatnManage
 * param 반려구분
 ******************************************************** */
function fn_aram_confirm(){
	gArguments["title"]    = "휴가신청 - 승인";
	gArguments["retFunc"] = fncConfm;

	var url = "/uss/ion/ism/ConfmPopup.do";

	window.open(url, "p_infrmSanctn", "width=780px,height=170px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 반려 처리  팝업창열기
 * fncCtsnnReturn
 * param 반려구분
 ******************************************************** */
function fn_aram_reject(){
	gArguments["title"]   = "휴가신청 - 반려";
	gArguments["retFunc"] = fncConfm;

	var url = "/uss/ion/ism/ReturnPopup.do";

	window.open(url, "p_infrmSanctn", "width=780px,height=170px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 휴가처리화면
 ******************************************************** */
function fncConfm(confmAt, returnResn) {
	var varForm = document.getElementById("vcatnManageVO");
	varForm.confmAt.value = confmAt;
	varForm.returnResn.value = returnResn;

    if(!validateVcatnManageVO(varForm)){           
        return;
    }
    
	if(confirm("<spring:message code='common.save.msg'/>")){
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/vct/updateVcatnConfm.do";
        varForm.submit();
    } 
}

</c:if>

function fncHTMLTagFilter(vValue) {
    var tempValue ="";
	for (var i = 0; i < vValue.length; i++) {
	   c = vValue.charAt(i);
		switch (c) {
		case '<':
			tempValue += "&lt;";
			break;
		case '>':
			tempValue += "&gt;";
			break;
		case '&':
			tempValue += "&amp;";
			break;
		case '"':
			tempValue += "&quot;";
			break;
		case '\'':
			tempValue += "&apos;";
			break;	
		default:
			tempValue += c;
			break;
		}
	}
	if(tempValue.length> 200){
		alert("휴가사유 항목의 입력값이 200자(한글100자)를 초과하였습니다. \n \n특수문자('<','>','&','\"', '\'')를 사용하신 경 우  \n\n해당 문자('&lt;','&gt;','&amp;','&quot;','&apos;')로 치환 처리되어 문자수가 초과처리 될수도 있습니다 .")
		return false;
	}
}

</script>
