<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name  : KnoPersonalRegist.jsp
  * @Description : 개인지식 등록
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
	<h2>개인지식 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 파일첨부를 위한 폼명 및 Enctype 설정 -->
<form:form commandName="knoPersonalVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="knoId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="이 표는 개인지식관리 정보를 제공하며, 조직명, 지식유형, 지식명, 지식내용, 수집일자, 공개여부, 파일첨부 정보로 구성되어 있습니다 .">
<caption>개인지식관리 등록</caption>
	<tr>
		<th width="20%">
			<span class="required_icon"></span>
			조직명
		</th>
	    <td width="80%">
			<select name="orgnztId" class="select" title="조직명" onChange="javascript:fn_aram_get_codeId()">
				<c:forEach var="result" items="${mapTeamList}" varStatus="status">
					<option value='<c:out value="${result.orgnztId}"/>' <c:if test="${result.orgnztId == knoPersonalVO.orgnztId}">selected="selected"</c:if>><c:out value="${result.orgnztNm}"/></option>
				</c:forEach>			  		   
			</select>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	지식유형명
	    </th>
	    <td>
			<select name="knoTypeCd" class="select" title="조직명">
				<c:forEach var="result" items="${mapMaterialList}" varStatus="status">
					<option value='<c:out value="${result.knoTypeCd}"/>'><c:out value="${result.knoTypeNm}"/></option>
				</c:forEach>			  		   
			</select>
	    </td>
	</tr>
	<tr>
  		<th>
  			<span class="required_icon"></span>
  			지식명
  		</th>
  		<td>
    		<form:input  path="knoNm" title="지식명" size="60" maxlength="60"/>
    		<form:errors path="knoNm" cssClass="error"/>
  		</td>
	</tr>
	<tr>
  		<th>
  			<span class="required_icon"></span>
  			지식내용
  		</th>
  		<td>
    		<form:textarea  path="knoCn" title="지식내용" cols="300" rows="10" cssClass="txArea"/>
    		<form:errors path="knoCn" cssClass="error"/>
  		</td>
	</tr>
	<tr>
  		<th>
    		<span class="required_icon"></span>
  			수집일자
  		</th>
  		<td>
      		<form:hidden path="colYmd" />
	    	<c:if test="${!empty knoPersonalVO.colYmd}">
 				<c:set var="colYmdVal" value="${fn:substring(knoPersonalVO.colYmd, 0,4)}-${fn:substring(knoPersonalVO.colYmd, 4,6)}-${fn:substring(knoPersonalVO.colYmd, 6,8)}"/>
      		</c:if>
      		<input name="colYmdView" id="colYmdView" type="text" size="10" title="수집일자" value="${colYmdVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].colYmd, document.forms[0].colYmdView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
 		</td>
	</tr>
	<tr>
  		<th>
    		<span class="norequired_icon"></span>
  			공개여부
  		</th>
  		<td>
	     	<spring:message code="cop.public" /> : <input type="radio" name="othbcAt" class="radio2" value="Y">&nbsp;
	     	<spring:message code="cop.private" /> : <input type="radio" name="othbcAt" class="radio2" value="N" checked>
	     	<form:errors path="othbcAt" cssClass="error"/>
  		</td>
	</tr>
  	<!-- 첨부파일 테이블 레이아웃 설정 Start -->
  	<tr>
  		<th>
    		<span class="norequired_icon"></span>
  			파일첨부
  		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	    	<table>
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
				</tr>
				<tr>
					<td>
				   		<div id="egovComFileList"></div>
				   	</td>
				</tr>
	   	    </table>
	 	</td>
  	</tr>
  	<!-- 첨부파일 테이블 레이아웃 End -->
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
<validator:javascript formName="knoPersonalVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="/js/aramframework/com/sym/cal/CalPopup.js"></script>

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
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("knoPersonalVO");
    varForm.orgnztId.focus();
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("knoPersonalVO");
    varForm.action = "${pageContext.request.contextPath}/dam/per/listKnoPersonal.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("knoPersonalVO");

    if(!validateKnoPersonalVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/dam/per/insertKnoPersonal.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 지식유형 가져오기
 ******************************************************** */
function fn_aram_get_codeId(){
	var varForm = document.getElementById("knoPersonalVO");
    varForm.action = "${pageContext.request.contextPath}/dam/per/registKnoPersonal.do";
	varForm.submit();
}			

</script>
