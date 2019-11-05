<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : MailRegist.jsp
  * @Description : 발송메일 등록
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
	<h2>발송메일 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;">전송</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="sndngMailVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="link"  />

<input type="hidden" name="closeYn" value="${closeYn}" />

<table class="table-register" summary="받는사람, 제목, 파일첨부 및 발신 내용을 입력하여 발송메일을 등록한다.">
<CAPTION style="display: none;">발송메일 등록</CAPTION>
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="recptnPerson">받는사람</label>
	    </th>
	    <td width="80%">
	      	<form:input path="recptnPerson" size="74" maxlength="60" style="ime-mode: disabled;" tabindex="1" title="" />
		</td>
	</tr>
	<tr>
		<th>
	    	<span class="required_icon"></span>
			<label for="sj">제목</label>
		</th>
		<td>
		    <form:input path="sj" size="74" maxlength="250" tabindex="2" title="" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="egovComFileUploader">파일첨부</label>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
			<table>
			    <tr>
			      	<td><input name="file_1" id="egovComFileUploader" type="file" tabindex="3" title="" /></td>
				</tr>
				<tr>
				    <td>
				        <div id="egovComFileList"></div>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
	    <th>
			<span class="norequired_icon"></span>
	    	<label for="emailCn">발신 내용</label>
	    </th>
	    <td>
	      	<form:textarea path="emailCn" cols="75" rows="20" tabindex="4" title="" />
		</td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${sndngMailVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${sndngMailVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${sndngMailVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${sndngMailVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="sndngMailVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

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
    var varForm = document.getElementById("sndngMailVO");
	var closeYn = varForm.closeYn.value;
	if (closeYn == "Y") {
		window.close();
	}
	varForm.recptnPerson.focus();
};

/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("sndngMailVO");
    varForm.action = "${pageContext.request.contextPath}/cop/ems/listSndngMail.do";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("sndngMailVO");

    if(!validateSndngMailVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg' />")){
		varForm.action = "${pageContext.request.contextPath}/cop/ems/insertSndngMail.do";
		varForm.submit();
	}
}

</script>


