<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : FaqDetail.jsp
  * @Description : FAQ 상세조회
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
	<h2>FAQ 상세조회</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="faqManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="faqId" />

<table class="table-detail" summary="FAQ에 대한 정보를  조회합니다.">
<caption>FAQ상세조회</caption>
  	<tr> 
    	<th width="20%">
    		질문제목
    	</th>
    	<td width="80%">
    		<c:out value="${faqManageVO.qestnSj}"/>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		<label for="qestnCn">질문내용</label>
    	</th>
    	<td>
      		<textarea name="qestnCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="질문내용">
<c:out value="${faqManageVO.qestnCn}"/>
      		</textarea>                   
    	</td>
  	</tr>
    <tr> 
    	<th>
    		<label for="answerCn">답변내용</label>
    	</th>
    	<td>
      		<textarea name="answerCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="답변내용">
<c:out value="${faqManageVO.answerCn}"/>
      		</textarea>                   
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		조회수
    	</th>
    	<td>
    		<c:out value="${faqManageVO.inqireCo}"/>
    	</td>
  	</tr>
    <c:if test="${result.atchFileId != ''}">
	<tr> 
		<th>
 			첨부파일 목록
		</th>
		<td>
			<c:import url="/files/${faqManageVO.atchFileId}" />
		</td>
	</tr>
  	</c:if>	   
    <tr> 
    	<th>
    		수정일자
    	</th>
    	<td>
    		<fmt:formatDate value="${faqManageVO.lastUpdusrPnttm}" pattern="yyyy-MM-dd"/>
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

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("faqManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/faq/listFaq.do";
    varForm.submit();
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("faqManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olh/faq/editFaq.do";
    varForm.submit();	
}

function fn_aram_delete(faqId){
    var varForm = document.getElementById("faqManageVO");
    
	if	(confirm("<spring:message code='common.delete.msg' />"))	{	
		varForm.action = "${pageContext.request.contextPath}/uss/olh/faq/deleteFaq.do";
		varForm.submit();
	}
}

</script>

