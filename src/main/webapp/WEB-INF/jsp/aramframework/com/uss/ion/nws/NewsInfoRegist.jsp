<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : NewsInfoRegist.jsp
  * @Description : 뉴스정보 등록
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
	<h2>뉴스정보 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="newsManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="newsId" />

<!--  등록  폼 영역  -->
<table class="table-register">
	<tr>
		<th width="20%">
    		<span class="required_icon"></span>
			뉴스제목
		</th>
		<td width="80%">
			<form:input path="newsSj" size="70" maxlength="70" />
			<form:errors path="newsSj" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>
    		<span class="required_icon"></span>
			뉴스내용
		</th>
	    <td>
			<form:textarea path="newsCn" cols="300" rows="30" cssClass="txArea" />
			<form:errors path="newsCn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
    	<th>
			<span class="norequired_icon"></span>
    		뉴스출처
    	</th>
		<td>
			<form:input path="newsOrigin" size="60" maxlength="60" title="뉴스 출처 입력" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="norequired_icon"></span>
			게시일자
		</th>
		<td>
      		<form:hidden path="ntceDe" />
	    	<c:if test="${!empty newsManageVO.ntceDe}">
 				<c:set var="ntceDeVal" value="${fn:substring(newsManageVO.ntceDe, 0,4)}-${fn:substring(newsManageVO.ntceDe, 4,6)}-${fn:substring(newsManageVO.ntceDe, 6,8)}"/>
      		</c:if>
      		<input name="ntceDeView" id="ntceDeView" type="text" size="10" title="게시일자 입력" value="${ntceDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceDe, document.forms[0].ntceDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="ntceDe" cssClass="error"/>
		</td>
	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
	<tr>
		<th>
			<span class="norequired_icon"></span>
			파일첨부
		</th>
		<td colspan="3">
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
			<table>
				<tr>
					<td>
						<input name="file_1" id="egovComFileUploader" type="file" title="첨부파일 입력" />
					</td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
  <!--첨부파일 테이블 레이아웃 End.-->
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
<validator:javascript formName="newsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!--  첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
 window.onload = function() {
    var varForm = document.getElementById("newsManageVO");

    // 게시일자에 현재날짜 세팅
    var today = fn_aram_getToday();
    varForm.ntceDeView.value = today;
    varForm.ntceDe.value = today.replaceAll('-','');
};

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("newsManageVO");
    
	if (!validateNewsManageVO(varForm)) {
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/insertNewsInfo.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("newsManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/nws/listNewsInfo.do";
    varForm.submit();
}

/* ********************************************************
 * 현재날짜 가져오기
 ******************************************************** */
function fn_aram_getToday(){

	var today = new	Date();
	var currentYear  = today.getFullYear();
	var currentMonth = (today.getMonth()+1).toString();
	var currentDay = today.getDate().toString();

	var	currentToday = currentYear + "-" + currentMonth.getLpad(2) + "-" + currentDay.getLpad(2);
	return	currentToday;
}

</script>

