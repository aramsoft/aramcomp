<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : QnaAnswerDetail.jsp
  * @Description : Q&amp;A 답변 상세조회
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
	<h2>Q&amp;A 답변 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;">답변</a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="qnaManageVO" action="" method="post">
<input type="hidden" name="curTarget" value="${curTarget}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="qaId" />

<input name="writngPassword" 	type="hidden" value="">

<table class="table-detail" summary="Q&amp;A에 대한 답변을 조회합니다.">
<caption>Q&amp;A답변상세조회</caption>
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
    		<input name="emailAnswerAt" type="checkbox" <c:if test="${qnaManageVO.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부  
    	</td>
  	</tr>
  	<tr> 
    	<th>
      		작성일자
    	</th>
    	<td>
  			<c:if test="${qnaManageVO.writngDe != null}">
    			<c:out value='${fn:substring(qnaManageVO.writngDe, 0,4)}-${fn:substring(qnaManageVO.writngDe, 4,6)}-${fn:substring(qnaManageVO.writngDe, 6,8)}'/>       			   	          				 			   
 			</c:if>
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
    		진행처리상태
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
      		<textarea name="qestnCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="질문내용">
<c:out value="${qnaManageVO.qestnCn}"/>
      		</textarea>                       
    	</td>
  	</tr> 

	<!--답변내용이 있을경우 Display...-->
	<c:if test="${qnaManageVO.qnaProcessSttusCode == '3' || qnaManageVO.answerCn != null}">
  	<tr> 
    	<th>
     		답변내용
    	</th>
    	<td>    
      		<textarea name="answerCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="답변내용">
<c:out value="${qnaManageVO.answerCn}"/>
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
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

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
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("qnaManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/qnm/editQnaAnswer.do";
    varForm.submit();
}

</script>

