<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : NoticeReply.jsp
  * @Description : 게시물 답글 등록
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
	<h2><c:out value='${boardVO.boardMasterVO.bbsNm}'/> - 답글 쓰기</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	   		<c:if test="${editAuthFlag == 'Y'}">
	    		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
	   		</c:if>
	    	<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="boardVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />
<input type="hidden" name="fullScrYn" value="${fullScrYn}" />

<form:hidden path="bbsId" />
<form:hidden path="nttId" />

<form:hidden path="parntsNttId" />
<form:hidden path="threadGroupNo" />
<form:hidden path="threadDepth" />

<form:hidden path="boardMasterVO.fileAtchPosblAt" readonly="true" />
<form:hidden path="boardMasterVO.posblAtchFileNumber" readonly="true" />

<table class="table-register">
	<tr>
	    <th width="20%">
	    	<span class="required_icon"></span>
	    	<label for="nttSj"><spring:message code="cop.nttSj" /></label>
	    </th>
	    <td>
	      	<input name="nttSj" id="nttSj" type="text" size="60" value="RE: "  maxlength="60" title="답글제목입력">
	      	<form:errors path="nttSj" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <td colspan="2">
   			<textarea id="nttCn" name="nttCn" class="textarea"  cols="75" rows="30"  style="width:100%;" title="답글내용입력"></textarea>
   			<form:errors path="nttCn" cssClass="error"/>
	   	</td>
	</tr>

	<!-- 익명 패스워드  -->
	<c:choose>
	<c:when test="${anonymous == 'true'}">
	<tr>
		<th>
		    <span class="required_icon"></span>
			<label for="ntcrNm">작성자</label>
		</th>
		<td>
		    <form:input path="ntcrNm" size="20" maxlength="10" title="작성자이름" />
		    <form:errors path="ntcrNm" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>
		    <span class="required_icon"></span>
			<label for="password"><spring:message code="cop.password" /></label>
		</th>
		<td>
		    <form:password path="password" size="20" value="" maxlength="20" title="비밀번호입력" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<form:hidden path="ntcrNm" value="dummy" /> <!-- validator 처리를 위해 지정 -->
		<form:hidden path="password" value="dummy" /> <!-- validator 처리를 위해 지정 -->
 	</c:otherwise>
  	</c:choose>
	
	<!-- 공지 기간 -->
	<c:choose>
	<c:when test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
	<tr>
		<th>
		    <span class="required_icon"></span>
			<spring:message code="cop.noticeTerm" />
		</th>
		<td>
	      	<form:hidden path="ntceBgnde" />
	    	<c:if test="${!empty boardVO.ntceBgnde}">
 				<c:set var="ntceBgndeVal" value="${fn:substring(boardVO.ntceBgnde, 0,4)}-${fn:substring(boardVO.ntceBgnde, 4,6)}-${fn:substring(boardVO.ntceBgnde, 6,8)}"/>
      		</c:if>
		    <input name="ntceBgndeView" type="text" size="10" value="${ntceBgndeVal}"  readOnly title="게시시작일자수정"
		    	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceBgnde, document.forms[0].ntceBgndeView); return false;" title="게시시작일자입력">
		    <img src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif" 
		    	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceBgnde, document.forms[0].ntceBgndeView); return false;" width="15" height="15" alt="달력창팝업버튼이미지">
		    ~
		    <form:hidden path="ntceEndde" />
	    	<c:if test="${!empty boardVO.ntceEndde}">
 				<c:set var="ntceEnddeVal" value="${fn:substring(boardVO.ntceEndde, 0,4)}-${fn:substring(boardVO.ntceEndde, 4,6)}-${fn:substring(boardVO.ntceEndde, 6,8)}"/>
      		</c:if>
		    <input name="ntceEnddeView" type="text" size="10" value="${ntceEnddeVal}"  readOnly title="게시종료일자수정"
		      	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceEndde, document.forms[0].ntceEnddeView); return false;" title="게시종료일자입력">
		    <img src="${pageContext.request.contextPath}/images/cmm/icon/bu_icon_carlendar.gif"
		      	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceEndde, document.forms[0].ntceEnddeView); return false;" width="15" height="15" alt="달력창팝업버튼이미지">
			<form:errors path="ntceBgndeView" cssClass="error"/>
			<form:errors path="ntceEnddeView" cssClass="error"/>
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<form:hidden path="ntceBgnde" value="10000101"/>
		<form:hidden path="ntceEndde" value="99991231"/>
	</c:otherwise>
  	</c:choose>
	
	<!-- 첨부파일  -->
	<c:if test="${boardVO.boardMasterVO.fileAtchPosblAt == 'Y'}">
	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			파일첨부
		</th>
	    <td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="<c:out value='${boardVO.boardMasterVO.posblAtchFileNumber}'/>" />
	        <table>
				<tr>
				    <td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명입력"/></td>
				</tr>
				<tr>
				    <td>
				        <div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "nttCn";
_editor_lang = "kr";
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/html/htmlarea4.0/htmlarea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cmm/utl/HtmlEditor.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sym/cal/CalPopup.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->
<c:if test="${boardVO.boardMasterVO.fileAtchPosblAt == 'Y'}"> 
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
</c:if>
<!--  첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

window.onload = function() {
	HTMLArea.onload = initEditor;
	HTMLArea.init(); 
};

function fn_aram_list() {
    var varForm = document.getElementById("boardVO");
	varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardArticle.do";
    varForm.submit();
}

<c:if test="${editAuthFlag == 'Y'}">
function fn_aram_insert() {
    var varForm = document.getElementById("boardVO");
	varForm.onsubmit();		// for ending of htmleditor

	if (!validateBoardVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertReplyBoardArticle.do";
		varForm.submit();
	}
}
</c:if>

</script>


