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
  * @Class Name : FaqRegist.jsp
  * @Description : FAQ 등록
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
	<h2>FAQ 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="faqManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="faqId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="FAQ에 대한 정보를 등록합니다.">
<caption>FAQ내용등록</caption>
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="qestnSj">질문제목</label>
    	</th>
    	<td width="80%">
        	<form:input path="qestnSj" size="70" maxlength="70" title="질문제목"/>
    		<form:errors path="qestnSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnCn">질문내용</label>
    	</th>
    	<td>
      		<form:textarea path="qestnCn" cols="300" rows="15" cssClass="txArea"  title="질문내용"/>
      		<form:errors path="qestnCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
      		<span class="required_icon"></span>
    		<label for="answerCn">답변내용</label>
    	</th>
    	<td>
      		<form:textarea path="answerCn" cols="300" rows="15" cssClass="txArea" title="답변내용"/>
      		<form:errors path="answerCn" cssClass="error"/>
    	</td>
  	</tr>
	<!--첨부파일 테이블 레이아웃 설정 Start..-->
  	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />  
	    	<table>
				<tr>
					<td>
						<input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/>
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
<validator:javascript formName="faqManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/fms/MultiFile.js"></script>

<!--첨부파일 업로드 가능화일 설정 Start..-->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!-- 첨부파일 업로드 가능화일 설정 End.-->

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
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("faqManageVO");
    
	if (!validateFaqManageVO(varForm)) {
		return;
	} 
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olh/faq/insertFaq.do";
		varForm.submit();
	}
}

</script>

