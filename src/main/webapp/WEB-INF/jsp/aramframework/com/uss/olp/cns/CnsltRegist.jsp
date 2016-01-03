<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : CnsltRegist.jsp
  * @Description : 상담 등록
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
	<h2>상담 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cnsltManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cnsltId" />

<input name="managtCn" type="hidden" value="Testing...">

<!-- 등록  폼 영역  -->
<table class="table-register" summary="상담에 대한 정보를 등록합니다.">
<caption>상담 등록</caption>
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
        	<form:password path="writngPassword" size="20" maxlength="20" title="작성 비밀번호"/>
    		<form:errors path="writngPassword" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="areaNo">전화번호</label>
    	</th>
    	<td>
        	<form:input path="areaNo" size="4" maxlength="4" title="전화번호(지역)"/>-
        	<form:input path="middleTelno" size="4" maxlength="4" title="전화번호(국번)"/>-
        	<form:input path="endTelno" size="4" maxlength="4"  title="전화번호(지번)"/>
    		<form:errors path="areaNo" cssClass="error"/>
    		<form:errors path="middleTelno" cssClass="error"/>
    		<form:errors path="endTelno" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="firstMoblphonNo">휴대폰전화번호</label>
    	</th>
    	<td>
			<form:input path="firstMoblphonNo" size="5"  maxlength="5" title="휴대폰전화번호(앞번)" />-
			<form:input path="middleMbtlnum" size="5"  maxlength="5" title="휴대폰전화번호(국번)" />-
			<form:input path="endMbtlnum" size="5"  maxlength="5" title="휴대폰전화번호(지번)" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="emailAdres">이메일</label>
    	</th>
    	<td>
			<form:input path="emailAdres" size="30" maxlength="30" title="이메일)" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="emailAnswerAt" type="checkbox" value="Y" title="이메일답변여부"> 이메일답변여부
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="cnsltSj">상담제목</label>
    	</th>
    	<td>
        	<form:input path="cnsltSj" size="70" maxlength="70" title="상담제목"/>
    		<form:errors path="cnsltSj" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="cnsltCn">상담내용</label>
    	</th>
    	<td>
      		<form:textarea path="cnsltCn" cols="300" rows="20" cssClass="txArea" title="상담내용"/>
      		<form:errors path="cnsltCn" cssClass="error"/>
    	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start.. -->
  	<tr>
		<th>
    		<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />  
	    	<table>
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file"  title="파일첨부"/></td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>
	 	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 End. -->
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="cnsltManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start.. -->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!-- 첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
	var varForm = document.getElementById("cnsltManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/listCnslt.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
	var varForm = document.getElementById("cnsltManageVO");
	
	if (!validateCnsltManageVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/insertCnslt.do";
		varForm.submit();
	}
}

</script>

