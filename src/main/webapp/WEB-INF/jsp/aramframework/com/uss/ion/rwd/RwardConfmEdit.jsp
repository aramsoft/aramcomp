<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name  : RwardConfmEdit.jsp
 * @Description : 포상승인 수정
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
	<h2>포상승인 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <c:if test="${rwardManageVO.confmAt eq 'A'}">
			<span class="button"><a href="#" onclick="javascript:fn_aram_confirm(); return false;">승인</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_reject(); return false;">반려</a></span>
	  	</c:if>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="rwardManageVO" method="post" action="#">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden  path="rwardId"/>
<form:hidden  path="rwardManId"/>
<form:hidden  path="rwardCd"/>
<form:hidden  path="rwardDe"/>
<form:hidden  path="infrmlSanctnId"/>
<form:hidden  path="confmAt"/>
<form:hidden  path="returnResn"/>
<form:hidden  path="rwardNm"/>
<form:hidden  path="pblenCn"/>
<form:hidden  path="sanctnerId"/>

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>포상자</h2>
</div>

<table class="table-register" summary="포상자정보">
<caption>포상자정보</caption>
  	<tr>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		포상자
    	</th>          
	    <td width="30%"><c:out value='${rwardManageVO.rwardManNm}'/></td>
	    <th width="20%">
	    	<span class="norequired_icon"></span>
	    	소속
	    </th>          
	    <td width="30%"><c:out value='${rwardManageVO.orgnztNm}'/></td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-register" summary="포상승인">
<caption>포상승인</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		포상구분
    	</th>          
    	<td colspan="3">
    		<c:out value='${rwardManageVO.rwardCdNm}'/>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		포상명
    	</th>          
    	<td width="30%">
    		<c:out value='${rwardManageVO.rwardNm}'/>
    	</td>
    	<th width="20%">
     		<span class="required_icon"></span>
    		포상일
    	</th>          
    	<td width="30%">
        	<c:out value='${fn:substring(rwardManageVO.rwardDe, 0,4)}-${fn:substring(rwardManageVO.rwardDe, 4,6)}-${fn:substring(rwardManageVO.rwardDe, 6,8)}'/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		공적사항
     	</th>          
    	<td colspan="3">
       		<textarea id="remark" name="pblenCn" class="txArea" rows="4" cols="70" title="공적사항" readOnly><c:out value='${rwardManageVO.pblenCn}'/></textarea>
    	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
  	<c:if test="${rwardManageVO.atchFileId != ''}">
	<tr> 
		<th>
			<span class="norequired_icon"></span>
			첨부파일목록
		</th>
	    <td colspan="3">
			<c:import url="/content/files/${rwardManageVO.atchFileId}" />
	    </td>
	</tr>
  	</c:if>	
	<!-- 첨부파일 테이블 레이아웃 End.-->
</table>

<!-- 결재권자 정보 Include -->
<jsp:include page="/uss/ion/ism/detailSanctner.do" flush="true"> 
	<jsp:param name="infrmlSanctnId" value="${rwardManageVO.infrmlSanctnId}"/>
</jsp:include>
<!-- 결재권자 정보 Include -->

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="rwardManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 포상승인목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("rwardManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/listRwardConfm.do";
	varForm.submit();
}

<c:if test="${rwardManageVO.confmAt eq 'A' }">

var gArguments = new Array();

/* ********************************************************
 * 승인 처리  팝업창열기
 * fncCtsnnReturn
 * param 반려구분
 ******************************************************** */
function fn_aram_confirm(){
	gArguments["title"]    = "포상신청 - 승인";
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
	gArguments["title"]   = "포상신청 - 반려";
	gArguments["retFunc"] = fncConfm;

	var url = "/uss/ion/ism/ReturnPopup.do";

	window.open(url, "p_infrmSanctn", "width=780px,height=170px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 포상승인처리화면
 ******************************************************** */
function fncConfm(confmAt, returnResn) {
	var varForm = document.getElementById("rwardManageVO");
	varForm.confmAt.value = confmAt;
	varForm.returnResn.value = returnResn;

    if(!validateRwardManageVO(varForm)){           
        return;
    }
    
	if(confirm("<spring:message code='common.save.msg' />")){
 	    varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/updateRwardConfm.do";
        varForm.submit();
    } 
}

</c:if>	

</script>
