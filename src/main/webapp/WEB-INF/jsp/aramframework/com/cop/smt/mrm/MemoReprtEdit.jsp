<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MemoReprtEdit.jsp
 * @Description : 메모보고 수정
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
	<h2>메모보고 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="memoReprtVO" action="" method="post" enctype="multipart/form-data"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="reprtId" />

<table class="table-register"  summary="이 표는 메모보고 정보를  등록하기 위한 표이며, 보고일자, 작성자, 보고대상자, 보고제목, 보고내용, 파일첨부 정보로 구성되어 있습니다 .">
<caption>메모보고 수정</caption>
<tbody>
	<tr>
		<th width="20%">
	    	<span class="required_icon"></span>
			<label for="reprtDe">보고일자</label>
		</th>
		<td width="80%">
      		<form:hidden path="reprtDe" />
	    	<c:if test="${!empty memoReprtVO.reprtDe}">
 				<c:set var="reprtDeVal" value="${fn:substring(memoReprtVO.reprtDe, 0,4)}-${fn:substring(memoReprtVO.reprtDe, 4,6)}-${fn:substring(memoReprtVO.reprtDe, 6,8)}"/>
      		</c:if>
      		<input name="reprtDeView" id="reprtDeView" type="text" size="10" title="보고일자" value="${reprtDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].reprtDe, document.forms[0].reprtDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="reprtDe" cssClass="error"/>
		</td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	작성자
	    </th>
	    <td>
	      	<c:out value="${memoReprtVO.wrterClsfNm}" escapeXml="false" />
	      	&nbsp;
	      	<c:out value="${memoReprtVO.wrterNm}" escapeXml="false" />
	      	<input type="hidden" name="wrterId" id="wrterId" value="${memoReprtVO.wrterId}"/>
	      	<input type="hidden" name="wrterNm" id="wrterNm" value="${memoReprtVO.wrterNm}"/>
	      	<input type="hidden" name="wrterClsfNm" id="wrterClsfNm" value="${memoReprtVO.wrterClsfNm}"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reportrNm">보고대상자</label>
	    </th>
	    <td>
		<table>
			<tr>
				<td width="100px" >
					<form:input path="reportrNm" size="15" readonly="true" maxlength="10" title="보고대상명"/>
				</td>
				<td >
					<a href="#" title="새 창으로 이동" onClick="fn_aram_get_reportr('보고대상', 'reportrId', 'reportrNm'); return false;">
						<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="보고대상 검색" title="보고대상 검색">
					</a>
				</td>
			</tr>
		</table>
		<form:errors path="reportrNm" cssClass="error"/>
	    <form:hidden path="reportrId" />
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reprtSj">보고제목</label>
	    </th>
	    <td>
	      	<form:input path="reprtSj" size="75" maxlength="255" title="제목"/>
	      	<form:errors path="reprtSj" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reprtCn">보고내용</label>
	    </th>
	    <td>
	      	<form:textarea path="reprtCn" rows="35" cols="65" title="보고내용"/>
    	  	<form:errors path="reprtCn" cssClass="error"/>
	    </td>
	</tr>
	<!-- 첨부목록 -->
	<c:choose>
	<c:when test="${memoReprtVO.atchFileId ne null && memoReprtVO.atchFileId ne ''}">
	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
		<td>
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/cop/smt/mrm/modifyMemoReprt.do" />
			<c:import url="/content/files/${memoReprtVO.atchFileId}/editform" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
	<!-- //첨부목록 -->

	<!-- 첨부화일 업로드 -->
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

			<div id="file_upload_posbl"  style="display:none;">
			<table>
			    <tr>
			        <td>
			        	<input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/>
			        </td>
				</tr>
				<tr>
				    <td><div id="egovComFileList"></div></td>
				</tr>
			</table>
			</div>

			<div id="file_upload_imposbl"  style="display:none;">
	        	<spring:message code="common.imposbl.fileupload" />
			</div>
		</td>
	</tr>
    <!-- //첨부화일 업로드 -->
</tbody>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchSttus" />
<form:hidden path="searchDrctMatter" />
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="memoReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "reprtCn";
_editor_lang = "kr";
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/html/htmlarea4.0/htmlarea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/HtmlEditor.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

window.onload = function() {
	HTMLArea.onload = initEditor;
	HTMLArea.init(); 
};

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("memoReprtVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/mrm/listMemoReprt.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("memoReprtVO");
	varForm.onsubmit();		// for ending of htmleditor

    if( editor.getHTML() == "" ){
		alert("보고내용은 필수 입력값입니다.");
		return;
	}else{
		varForm.reprtCn.value = editor.getHTML();
	}

	if (!validateMemoReprtVO(varForm)){
		return;
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/mrm/updateMemoReprt.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_aram_get_reportr(strTitle, empId, empName){
	gArguments["title"]    = strTitle;
	if( empId != "" )    gArguments["uniqId"]   = document.getElementById(empId);
	if( empName != "" )  gArguments["emplyrNm"] = document.getElementById(empName);

	var url = "/cop/smt/mrm/listReportrPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>


