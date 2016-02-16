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
  * @Class Name : QustnrItemEdit.jsp
  * @Description : 설문항목 수정
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
<%pageContext.setAttribute("crlf", "\r\n"); %>
<DIV id="main">

<div class="content_title">
	<h2>설문항목 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrItemManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qustnrIemId" />
<form:hidden path="qestnrQesitmId" />

<%-- 설문지정보 상태유지 --%>
<form:hidden path="qestnrId" />
<form:hidden path="searchMode" />
<%-- 설문지정보 상태유지 --%>

<!--등록  폼 영역  -->
<table class="table-register" summary="이 표는 설문항목 목록 정보를 제공하며, 설문정보, 설문문항정보, 항목순번, 항목내용, 기타답변여부 정보로 구성되어 있습니다 .">
	<tr> 
		<th width="20%">
			<span class="required_icon"></span>
			설문정보
		</th>
		<td width="80%">
	  		<c:out value="${fn:replace(qustnrItemManageVO.qestnrSj , crlf , '<br/>')}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			설문문항정보
		</th>
		<td>
	  		<c:out value="${fn:replace(qustnrItemManageVO.qestnCn , crlf , '<br/>')}" escapeXml="false" />
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			항목 순번
		</th>
		<td>
	  		<form:input path="qustnrIemSn" size="5" title="항목 순번"  maxlength="5" style="width:100px;" />
	  		<form:errors path="qustnrIemSn" cssClass="error" />
		</td>
	</tr> 
	<tr> 
		<th>
			<span class="required_icon"></span>
			항목 내용
		</th>
		<td>
	  		<form:textarea path="qustnrIemCn" class="textarea"  cols="75" rows="5" title="항목 내용" style="width:100%;" />
	  		<form:errors path="qustnrIemCn" cssClass="error" />  
		</td>
	</tr> 
	<tr> 
		<th>
			기타답변여부
			<span class="required_icon"></span>
		</th>
		<td>
	   		<form:select path="etcAnswerAt" title="기타답변여부">
	   			<form:option value="N" label="N" />
	   			<form:option value="Y" label="Y" />
	  		</form:select>
		</td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qustnrItemManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("qustnrItemManageVO");
	varForm.action =  "${pageContext.request.contextPath}/uss/olp/qqm/listQustnrQestn.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("qustnrItemManageVO");
	
	if(!validateQustnrItemManageVO(varForm)){ 			
		return;
	}

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/qim/updateQustnrItem.do";
		varForm.submit();
	}
}

</script>

