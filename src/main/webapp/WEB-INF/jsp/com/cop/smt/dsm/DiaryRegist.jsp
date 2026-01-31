<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : DiaryRegist.jsp
 * @Description : 일지 등록
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
	<h2>일지 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<form:form modelAttribute="diaryManageVO"  action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<form:hidden path="diaryId"/>

<!-- 등록  폼 영역  -->
<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		일정명
    	</th>
    	<td width="80%">
		<table>
			<tr>
				<td width="300px">
					<form:input path="schdulNm" size="40" cssClass="txInput" readonly="true" maxlength="255" />
				</td>
				<td>
					<a href="#" onClick="fn_aram_get_Schdule(); return false;">
						<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif"  align="middle" style="border:0px" alt="일정정보" title="일정정보">
					</a>
				</td>
			</tr>
		</table>
		<form:hidden path="schdulId" />
		<form:errors path="schdulId" cssClass="error"/>
		<form:errors path="schdulNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		일지명
    	</th>
    	<td>
      		<form:input path="diaryNm" size="73" cssClass="txInput" maxlength="255"/>
      		<form:errors path="diaryNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		지시사항
    	</th>
    	<td>
        	<form:textarea path="drctMatter" rows="3" cols="20" cssClass="txArea"/>
    		<form:errors path="drctMatter" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		특이사항
    	</th>
    	<td>
        	<form:textarea path="partclrMatter" rows="3" cols="20" cssClass="txArea"/>
    		<form:errors path="partclrMatter" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		진척율
    	</th>
    	<td>
			<form:input path="diaryProcsPte" size="3" cssClass="txInput" style="width:50px;" maxlength="3" /> %
    	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
  	<tr>
		<th>
			<span class="norequired_icon"></span>
			파일첨부
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	    	<table>
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>
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
<validator:javascript formName="diaryManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/fms/MultiFile.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!--  첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javaScript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("diaryManageVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/dsm/listDiary.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("diaryManageVO");

    if(!validateDiaryManageVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action =  "${pageContext.request.contextPath}/cop/smt/dsm/insertDiary.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 일정정보 팝업열기
 ******************************************************** */
function fn_aram_get_Schdule(){
	var varForm = document.getElementById("diaryManageVO");
	gArguments["schdulId"] = varForm.schdulId;
	gArguments["schdulNm"] = varForm.schdulNm;
	
	var url = "/cop/smt/sim/listSchdulPopup.do";

	window.open(url, "p_schdulInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
} 

</script>


