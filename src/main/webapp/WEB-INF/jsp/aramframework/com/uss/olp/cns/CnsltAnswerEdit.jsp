<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : CnsltAnswerEdit.jsp
  * @Description : 상담답변 수정
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
	<h2>상담답변 수정</h2>
</div>

<form:form commandName="cnsltManageVO" action="${pageContext.request.contextPath}/uss/olp/cnm/updateCnsltAnswer.do" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cnsltId" />

<form:hidden path="writngPassword" />

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!--  등록  폼 영역  -->
<table class="table-register" summary="이 표는 상담내역답변 정보를  수정하기 위한 표이며, 작성자명, 전화번호, 휴대폰전화번호, 이메일, 상담정보, 상담내용, 진행처리상태, 답변내용 정보로 구성되어 있습니다 .">
<caption>상담내역답변 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		<label for="cnsltCn">작성자명</label>
    	</th>
    	<td width="80%">
    		<form:input path="wrterNm" size="22" maxlength="20" readonly="true" title="작성자명" />
    	</td>
  	</tr>
  	<tr>
    	<th>
   			<span class="norequired_icon"></span>
    		<label for="areaNo">전화번호</label>
     	</th>
    	<td>
			<form:input path="areaNo" size="5" maxlength="5"  readonly="true"  title="전화번호(지역)" />-
			<form:input path="middleTelno" size="5" maxlength="5"  readonly="true"  title="전화번호(국번)" />-
			<form:input path="endTelno" size="5" maxlength="5"  readonly="true"  title="전화번호(지번)" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="firstMoblphonNo">휴대폰전화번호</label>
    	</th>
    	<td>
			<form:input path="firstMoblphonNo" 	size="5" maxlength="5" title="휴대폰전화번호(앞번)" readonly="true"  />-
			<form:input path="middleMbtlnum" 	size="5" maxlength="5" title="휴대폰전화번호(국번)" readonly="true"  />-
			<form:input path="endMbtlnum" 		size="5" maxlength="5" title="휴대폰전화번호(지번)" readonly="true"  />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		<label for="emailAdres">이메일</label>
    	</th>
    	<td>
			<form:input path="emailAdres" size="30" maxlength="30" title="이메일" readonly="true"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:checkbox path="emailAnswerAt" disabled="true" value="Y" title="이메일답변여부" /> 이메일답변여부

  			<c:if test="${cnsltManageVO.emailAnswerAt == 'Y'}">
  			    &nbsp;&nbsp;&nbsp;
				<a href = "${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" onClick="javascript:fn_aram_pop_mailform(); return false;"></a>
 			</c:if>
    	</td>
  	</tr>
 	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="cnsltSj">상담제목</label>&nbsp;&nbsp;
    	</th>
    	<td>
    		<form:input path="cnsltSj" size="70" maxlength="70" readonly="true" title="상담제목" />
   		</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="cnsltCn">상담내용</label>&nbsp;&nbsp;
    	</th>
    	<td>
      		<form:textarea path="cnsltCn" cols="300" rows="20"  style="width:450px;" title="상담내용" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		<label for="qnaProcessSttusCode">진행처리상태</label>
    	</th>
    	<td>
        	<form:select path="qnaProcessSttusCode" title="진행처리상태">
                <form:options items="${COM028_qnaProcessSttus}" itemValue="code" itemLabel="codeNm"/>
            </form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
      		<span class="required_icon"></span>
    		<label for="managtCn">답변내용</label>
    	</th>
    	<td>
      		<form:textarea path="managtCn" cols="300" rows="20" style="width:450px;" title="답변내용"/>
      		<form:errors path="managtCn" cssClass="error"/>
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

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cnsltManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cnm/listCnsltAnswer.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("cnsltManageVO");
    
	if (!validateCnsltManageVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/cnm/updateCnsltAnswer.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 메일발송을 위한 화면을 호출
 ******************************************************** */
function fn_aram_pop_mailform() {

	var url 	= "${pageContext.request.contextPath}/ems/insertSndngMailView.do";
	var	status 	= "width=640,height=480,top=200,left=200";

	window.open(url,'popup', status);
}

</script>

