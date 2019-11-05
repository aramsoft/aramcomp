<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : QnaAnswerEdit.jsp
  * @Description : Q&amp;A 답변 수정
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
	<h2>Q&amp;A 답변 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form commandName="qnaManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qaId" />

<form:hidden path="writngPassword" />

<table class="table-register" summary="Q&amp;A에 대한 답변을 수정합니다.">
<caption>Q&amp;A답변수정</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="wrterNm">작성자명</label>
    	</th>
    	<td width="80%">
    		<form:input path="wrterNm" size="22" readonly="true" maxlength="20" title="작성자명" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="areaNo">전화번호</label>
    	</th>
    	<td>
			<form:input path="areaNo" size="5" readonly="true" maxlength="5" title="전화번호(지역)" />-
			<form:input path="middleTelno" size="5" readonly="true" maxlength="5" title="전화번호(국번)" />-
			<form:input path="endTelno" size="5" readonly="true"  maxlength="5" title="전화번호(지번)" />
    	</td>
  	</tr>
 	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="emailAdres">이메일</label>
    	</th>
    	<td>
			<form:input path="emailAdres" size="30" readonly="true" maxlength="30" title="이메일" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="emailAnswerAt" type="checkbox" disabled value="Y" <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnSj">질문제목</label>
    	</th>
    	<td>
    		<form:input path="qestnSj"size="70" readonly="true" maxlength="70" title="질문제목" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnCn">질문내용</label>
    	</th>
    	<td>
      		<form:textarea path="qestnCn" class="textarea"  cols="300" rows="20"  readonly="true" style="width:450px;" title="질문내용" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qnaProcessSttusCode">진행처리상태</label>
    	</th>
    	<td>
     		<form:select path="qnaProcessSttusCode" class="select" title="진행처리상태">
                <form:options items="${COM028_qnaProcessSttus}" itemValue="code" itemLabel="codeNm"/>
  			</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="cnsltCn">답변내용</label>
    	</th>
    	<td>
      		<form:textarea path="answerCn" cols="300" rows="20" cssClass="txArea" title="답변내용"/>
      		<form:errors path="answerCn" cssClass="error"/>
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${qnaManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${qnaManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${qnaManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${qnaManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qnaManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("qnaManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qnm/listQnaAnswer.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("qnaManageVO");
    
	if (!validateQnaManageVO(varForm)) {
		return;
	} 
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/qnm/updateQnaAnswer.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 메일발송을 위한 화면을 호출
 ******************************************************** */
function fn_aram_pop_mailform() {
/*
	var url 	= "${pageContext.request.contextPath}/ems/insertSndngMailView.do";
	var	status 	= "width=640,height=480,top=200,left=200";
	window.open(url,'popup', status);
*/
}

</script>

