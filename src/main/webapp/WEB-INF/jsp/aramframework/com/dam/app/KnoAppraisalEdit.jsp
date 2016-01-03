<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : KnoAppraisalEdit.jsp
  * @Description : 지식평가 수정
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
	<h2>지식평가 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="knoAppraisalVO" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<!-- 등록  폼 영역  -->
<table class="table-register"  
summary="이 표는 지식평가관리 대상 정보를 제공하며 조직명, 지식유형명, 지식명, 지식내용, 첨부파일 목록, 평가일자, 평가결과 정보로 구성되어 있습니다 .">
<caption>지식평가관리 수정</caption>
	<tr>
    	<th width="20%">
	    	<span class="required_icon"></span>
    		조직명
    	</th>
 		<td>
 			${knoAppraisalVO.orgnztNm}
 		</td>
  	</tr>
	<tr>
    	<th>
	    	<span class="required_icon"></span>
    		지식유형명
    	</th>
 		<td>
 			${knoAppraisalVO.knoTypeNm}
 		</td>
  	</tr>
  	<tr>
    	<th>
	    	<span class="required_icon"></span>
    		지식명
    	</th>
    	<td>${knoAppraisalVO.knoNm}</td>
  	</tr>
  	<tr>
    	<th>
	    	<span class="required_icon"></span>
    		지식내용
    	</th>
    	<td>
      		<textarea name="knoCn" class="textarea" title="지식내용" cols="300" rows="10" style="width:450px;" readonly>${knoAppraisalVO.knoCn}</textarea>
    	</td>
  	</tr>
 	<!-- 첨부목록을 보여주기 위한 -->
  	<c:if test="${knoAppraisalVO.atchFileId ne null && knoAppraisalVO.atchFileId ne ''}">
	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
	    <td>
			<c:import url="/content/files/${knoAppraisalVO.atchFileId}" />
	    </td>
	</tr>
  	</c:if>
  	<tr>
    	<th>
	    	<span class="required_icon"></span>
    		평가일자
    	</th>
    	<td>
      		<form:hidden path="appYmd" />
	    	<c:if test="${!empty knoAppraisalVO.appYmd}">
 				<c:set var="appYmdVal" value="${fn:substring(knoAppraisalVO.appYmd, 0,4)}-${fn:substring(knoAppraisalVO.appYmd, 4,6)}-${fn:substring(knoAppraisalVO.appYmd, 6,8)}"/>
      		</c:if>
      		<input name="appYmdView" id="appYmdView" type="text" size="10" title="평가일달력" value="${appYmdVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].appYmd, document.forms[0].appYmdView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
	  		<form:errors path="appYmd" cssClass="error"/>
    	</td>
  	</tr>
	<tr>
    	<th>
	    	<span class="required_icon"></span>
    		평가결과
    	</th>
 		<td>
			<select name="knoAps" title="평가결과 선택">
			    <option value="1" <c:if test="${knoAppraisalVO.knoAps == '1'}">selected</c:if>>승인</option>
			    <option value="2" <c:if test="${knoAppraisalVO.knoAps == '2'}">selected</c:if>>반려</option>
			</select>
 		</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="knoAppraisalVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("knoAppraisalVO");
    varForm.action = "${pageContext.request.contextPath}/dam/app/listKnoAppraisal.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("knoAppraisalVO");

    if(!validateKnoAppraisalVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
	    varForm.action = "${pageContext.request.contextPath}/dam/app/updateKnoAppraisal.do";
	    varForm.submit();
	}
}

</script>
