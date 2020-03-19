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
  * @Class Name : ProgramChangeRequstEdit.jsp
  * @Description : 프로그램변경요청 수정
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
	<h2>프로그램변경요청 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form modelAttribute="progrmManageDtlVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>변경요청내역</h2>
</div>

<table class="table-register" summary="변경요청내역 화면">
<caption>변경요청내역</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		요청번호
    	</th>
    	<td width="80%">
      		<c:out value="${progrmManageDtlVO.rqestNo}"/>
			<form:hidden path="rqestNo" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		프로그램파일명
    	</th>
    	<td>
    		<c:out value="${progrmManageDtlVO.progrmFileNm}"/>
			<form:hidden path="progrmFileNm" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="rqestPersonId">요청자ID</label>
    	</th>
    	<td>
      		<c:out value="${progrmManageDtlVO.rqestPersonId}"/>
			<form:hidden path="rqestPersonId" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="rqestDe">요청일자</label>
    	</th>
    	<td>
      		<c:out value="${fn:substring(progrmManageDtlVO.rqestDe, 0, 4)}-${fn:substring(progrmManageDtlVO.rqestDe, 4, 6)}-${fn:substring(progrmManageDtlVO.rqestDe, 6, 8)}"/>
			<form:hidden path="rqestDe" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="rqestSj">요청제목</label>
    	</th>
    	<td>
			<form:input path="rqestSj" size="60"  maxlength="60"  title="요청제목"/>
			<form:errors path="rqestSj" cssClass="error"/>
    	</td>
  	</tr>
 	<tr>
    	<th>
      		<span class="norequired_icon"></span>
    		<label for="changeRqestCn">변경요청내용</label>
    	</th>
    	<td>
      		<form:textarea path="changeRqestCn" rows="4" cols="75"  title="변경요청내용"/>
      		<form:errors path="changeRqestCn" cssClass="error"/>
    	</td>
  	</tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<div class="content_title">
	<h2>변경처리내역</h2>
</div>

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		변경처리일자
    	</th>
    	<td width="80%">
	    	<c:if test="${fn:length(progrmManageDtlVO.processDe) != 0}">
      			<c:out value="${fn:substring(progrmManageDtlVO.processDe, 0, 4)}-${fn:substring(progrmManageDtlVO.processDe, 4, 6)}-${fn:substring(progrmManageDtlVO.processDe, 6, 8)}"/>
      		</c:if>
      		<form:hidden path="processDe" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		변경처리자
    	</th>
    	<td>
      		<c:out value="${progrmManageDtlVO.opetrId}"/>
      		<form:hidden path="opetrId" />
     	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		변경처리상태
    	</th>
    	<td>
      		<c:if test="${empty progrmManageDtlVO.processSttus}">N/A</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'A'}">신청중</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'P'}">진행중</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'R'}">반려</c:if>
      		<c:if test="${progrmManageDtlVO.processSttus == 'C'}">처리완료</c:if>
      		<form:hidden path="processSttus" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		변경처리내용
    	</th>
    	<td>
      		<form:textarea path="rqestProcessCn" rows="5" cols="75" readonly="true" cssClass="txArea" title="변경처리내용"/>
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

</div>

<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageDtlVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

var gArguments = new Array();

/* ********************************************************
 * 파일검색 화면 호출 함수
 ******************************************************** */
function searchFileNm() {
    var varForm = document.getElementById("progrmManageDtlVO");
	gArguments["progrmFileNm"] = varForm.progrmFileNm;
	
	var url = "/sym/prm/listProgramPopup.do";

	window.open(url, "p_programInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 목록조회 처리 함수
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.action = "${pageContext.request.contextPath}/sym/prm/listProgramChangeRequst.do";
    varForm.submit();
}

/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_aram_update() {
    var varForm = document.getElementById("progrmManageDtlVO");
    
	if(!validateProgrmManageDtlVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action ="${pageContext.request.contextPath}/sym/prm/updateProgramChangeRequst.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 삭제처리 함수
 ******************************************************** */
function fn_aram_delete() {
    var varForm = document.getElementById("progrmManageDtlVO");
    varForm.action = "${pageContext.request.contextPath}/sym/prm/deleteProgramChangeRequst.do";
    varForm.submit();
}

</script>
