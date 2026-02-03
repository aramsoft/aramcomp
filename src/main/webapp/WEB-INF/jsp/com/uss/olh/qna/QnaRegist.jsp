<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : QnaRegist.jsp
  * @Description : Q&amp;A 등록
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
  *  @see
  *
  */
%>
<DIV id="main">

<div class="content_title">
	<h2>Q&amp;A 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!--  등록  폼 영역  -->
<form:form modelAttribute="qnaManageVO" action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="qaId" />

<input name="answerCn" type="hidden" value="Testing...">

<table class="table-register" summary="Q&amp;A에 대한 정보를 등록합니다.">
<caption>Q&amp;A내용등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="wrterNm">작성자명</label>
    	</th>
    	<td width="80%">
        	<form:input path="wrterNm" size="20" maxlength="20" title="작성자명"/>
    		<form:errors path="wrterNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="writngPassword">작성 비밀번호</label>
    	</th>
    	<td>
        	<form:password path="writngPassword" size="20" maxlength="20"  title="작성 비밀번호"/>
    		<form:errors path="writngPassword" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="areaNo">전화번호</label>
    	</th>
    	<td>
        	<form:input path="areaNo" size="4" maxlength="4" title="전화번호(지역)"/>-<form:input path="middleTelno" size="4" maxlength="4"  title="전화번호(국번)"/>-<form:input path="endTelno" size="4" maxlength="4"  title="전화번호(지번)"/>
    		<form:errors path="areaNo" cssClass="error"/>
    		<form:errors path="middleTelno" cssClass="error"/>
    		<form:errors path="endTelno" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="emailAdres">이메일</label>
    	</th>
    	<td>
			<input name="emailAdres" 	type="text" size="30" value="<c:out value='${result.emailAdres}'/>" maxlength="30" title="이메일">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="emailAnswerAt" type="checkbox" value="Y" title="이메일답변여부"> 이메일답변여부
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnSj">질문제목</label>
    	</th>
    	<td>
        	<form:input path="qestnSj" size="70" maxlength="70"  title="질문제목"/>
    		<form:errors path="qestnSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnCn">질문내용</label>
    	</th>
    	<td>
      		<form:textarea path="qestnCn" cols="300" rows="20" cssClass="txArea"  title="질문내용"/>
      		<form:errors path="qestnCn" cssClass="error"/>
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
<validator:javascript formName="qnaManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("qnaManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("qnaManageVO");
    
	if (!validateQnaManageVO(varForm)) {
		return;
	} 
	
	if (varForm.emailAnswerAt.checked == false)
		varForm.emailAnswerAt.value = "N";

	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/insertQna.do";
		varForm.submit();
	}
}

</script>

