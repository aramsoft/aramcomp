<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name  : CtsnnConfmEdit.jsp
 * @Description : 경조사 승인 수정
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
	<h2>경조사 승인 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${ctsnnManageVO.confmAt eq 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_confirm(); return false;">승인</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_reject(); return false;">반려</a></span>
	  	</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="ctsnnManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="ctsnnId"/>
<form:hidden  path="usid"/>
<form:hidden  path="ctsnnCd"/>
<form:hidden  path="reqstDe"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="confmAt"/>
<form:hidden  path="sanctnerId"/>
<form:hidden  path="ctsnnNm"/>
<form:hidden  path="occrrDe"/>
<form:hidden  path="brth"/>
<form:hidden  path="relate"/>
<form:hidden  path="trgterNm"/>
<form:hidden  path="returnResn"/>

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

<table class="table-register" summary="경조  승인">
<caption>경조승인</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		경조명
    	</th>          
    	<td colspan="3">
    		<c:out value='${ctsnnManageVO.ctsnnNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		경조구분
    	</th>          
    	<td width="30%">
    		<c:out value='${ctsnnManageVO.ctsnnCdNm}'/>
    	</td>
    	<th width="20%">
     		<span class="required_icon"></span>
    		발생일
    	</th>          
    	<td width="30%">
			<c:out value="${fn:substring(ctsnnManageVO.occrrDe, 0,4)}-${fn:substring(ctsnnManageVO.occrrDe, 4,6)}-${fn:substring(ctsnnManageVO.occrrDe, 6,8)}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		대상자명
    	</th>          
    	<td colspan="3">
    		<c:out value='${ctsnnManageVO.trgterNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		관계
    	</th>          
    	<td>
    		<c:out value='${ctsnnManageVO.relateNm}'/>
    	</td>
    	<th>
     		<span class="required_icon"></span>
    		생년월일
    	</th>          
    	<td>
			<c:out value="${fn:substring(ctsnnManageVO.brth, 0,4)}-${fn:substring(ctsnnManageVO.brth, 4,6)}-${fn:substring(ctsnnManageVO.brth, 6,8)}" escapeXml="false" />
    	</td>
  	</tr>
  	<tr>
    	<th>
			<span class="norequired_icon"></span>
    		비고
    	</th>          
    	<td colspan="3">
      		<textarea id="remark" name="remark" class="txArea" rows="4" cols="70" readonly title="비고"><c:out value='${ctsnnManageVO.remark}'/></textarea>
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
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="ctsnnManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 승인목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("ctsnnManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/listCtsnnConfm.do";
	varForm.submit();
}

<c:if test="${ctsnnManageVO.confmAt eq 'A' }">

var gArguments = new Array();

/* ********************************************************
 * 승인 처리  팝업창열기
 * fncCtsnnReturn
 * param 반려구분
 ******************************************************** */
function fn_aram_confirm(){
	gArguments["title"]    = "경조신청 - 승인";
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
	gArguments["title"]   = "경조신청 - 반려";
	gArguments["retFunc"] = fncConfm;

	var url = "/uss/ion/ism/ReturnPopup.do";

	window.open(url, "p_infrmSanctn", "width=780px,height=170px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 승인처리
 ******************************************************** */
function fncConfm(confmAt, returnResn) {
	var varForm = document.getElementById("ctsnnManageVO");
	varForm.confmAt.value = confmAt;
	varForm.returnResn.value = returnResn;

	if(!validateCtsnnManageVO(varForm)){           
        return;
    }
	
	if(confirm("<spring:message code='common.save.msg'/>")){
    	varForm.action = "${pageContext.request.contextPath}/uss/ion/ctn/updateCtsnnConfm.do";
        varForm.submit();
    } 
}

</c:if>	

</script>
