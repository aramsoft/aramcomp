<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QnaDetail.jsp
  * @Description : Q&amp;A 상세조회
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
	<h2>Q&amp;A 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="qnaManageVO" name="qnaManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qaId" />

<input name="writngPassword" 	type="hidden" value="">

<table class="table-detail" summary="Q&amp;A에 대한 정보를 조회합니다.">
<caption>Q&amp;A상세조회</caption>  
  	<tr> 
    	<th width="20%">
    		작성자명
    	</th>
    	<td width="80%">
    		<c:out value="${qnaManageVO.wrterNm}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		전화
    	</th>
    	<td>    
      		<c:out value="${qnaManageVO.areaNo}"/>-<c:out value="${qnaManageVO.middleTelno}"/>-<c:out value="${qnaManageVO.endTelno}"/>                      
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		이메일
    	</th>
    	<td>
    		<c:out value="${qnaManageVO.emailAdres}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="emailAnswerAt" type="checkbox"  disabled <c:if test="${qnaManageVO.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부  
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		작성일자
    	</th>
    	<td>
    		<c:out value='${fn:substring(qnaManageVO.writngDe, 0,4)}-${fn:substring(qnaManageVO.writngDe, 4,6)}-${fn:substring(qnaManageVO.writngDe, 6,8)}'/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		조회횟수
    	</th>
    	<td>
    		<c:out value="${qnaManageVO.inqireCo}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		질의응답처리상태
    	</th>
    	<td>
    		<c:out value="${qnaManageVO.qnaProcessSttusCodeNm}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		질문제목
    	</th>
    	<td>    
      		<c:out value="${qnaManageVO.qestnSj}"/>                 
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		질문내용
    	</th>
    	<td>    
      		<textarea name="qestnCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="질문내용"><c:out value="${qnaManageVO.qestnCn}"/>
      		</textarea>                       
    	</td>
  	</tr> 

	<!-- 답변내용이 있을경우 Display... -->
	<c:if test="${qnaManageVO.qnaProcessSttusCode == '3'}">
  	<tr> 
    	<th>
     		답변내용
    	</th>
    	<td>    
      		<textarea name="answerCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="답변내용"><c:out value="${qnaManageVO.answerCn}"/>
      		</textarea>                                        
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		담당부서
    	</th>
    	<td>
    		<c:out value="${qnaManageVO.orgnztNm}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		답변일자
    	</th>
    	<td>
  			<c:if test="${qnaManageVO.answerDe != null}">
    		<c:out value='${fn:substring(qnaManageVO.answerDe, 0,4)}-${fn:substring(qnaManageVO.answerDe, 4,6)}-${fn:substring(qnaManageVO.answerDe, 6,8)}'/>       			   	          				 			   
 			</c:if>
    	</td>
  	</tr>
  	<tr> 
    	<th>
       		답변자
    	</th>
    	<td>
    		<c:out value="${qnaManageVO.emplyrNm}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
      		전화번호
    	</th>
    	<td>
    		<c:out value="${qnaManageVO.offmTelno}"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		이메일
     	</th>
    	<td>
    		<c:out value="${qnaManageVO.aemailAdres}"/>
    	</td>
  	</tr>
  	</c:if>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${qnaManageVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${qnaManageVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${qnaManageVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${qnaManageVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

<c:if test="${passwordConfirmAt == 'N'}">
	<script>
	fn_aram_passwordConfirm();
	</script>  		
</c:if>

</DIV>


<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("qnaManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/listQna.do";
    varForm.submit();
}

var gArguments = new Array();

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
	gArguments["retFunc"] = fn_aram_password_confirm;
	
	var url = "/uss/olh/qna/QnaPasswordPopup.do";

	window.open(url, "p_password", "width=320px,height=140px,top=100px,left=100px,location=no");
}

function fn_aram_password_confirm(writngPassword) {
    var varForm = document.getElementById("qnaManageVO");
	varForm.writngPassword.value = writngPassword;
	varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/QnaPasswordConfirm.do"; 	 	
	varForm.submit();
}

/**********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_aram_delete(){
	gArguments["retFunc"] = fn_aram_password_confirmDel;
	
	var url = "/uss/olh/qna/QnaPasswordPopup.do";

	window.open(url, "p_password", "width=320px,height=140px,top=100px,left=100px,location=no");
}

function fn_aram_password_confirmDel(writngPassword) {
    var varForm = document.getElementById("qnaManageVO");
	varForm.writngPassword.value = writngPassword;
	varForm.action = "${pageContext.request.contextPath}/uss/olh/qna/QnaPasswordConfirmDel.do";
	varForm.submit();
}

/*********************************************************
 * 작성비밀번호.체크..
 ******************************************************** */
function fn_aram_passwordConfirm(){
	alert("작성 비밀번호를 확인 바랍니다!");
}

</script>

