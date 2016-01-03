<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<% 
/**
 * @Class Name : DeptJobRegist.jsp
 * @Description : 부서 업무 등록
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
	<h2>부서 업무 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="deptJobVO" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="deptId" /> 
<form:hidden path="deptJobId" />
<form:hidden path="deptJobBxId" />

<table class="table-register" summary="이 표는 부서업무 정보를  등록하기 위한 표이며, 부서, 부서업무함명, 제목, 내용, 업무담당자, 우선순위, 파일첨부 정보로 구성되어 있습니다 .">
<caption>부서업무 등록</caption>
<tbody>
  	<tr> 
		<th width="20%">
	    	<span class="required_icon"></span>
			<label for="deptNm">부서</label>
		</th>
		<td width="80%">
			<form:input path="deptNm" size="20" maxlength="20" title="부서" class="readOnlyClass" readonly="true" />
			<form:errors path="deptNm" cssClass="error"/>
		</td>
  	</tr>
  	<tr> 
		<th>
	    	<span class="required_icon"></span>
			<label for="deptJobBxNm">부서업무함명</label>
		</th>
		<td>
			<form:input path="deptJobBxNm" size="30" readonly="true" maxlength="255" title="부서업무함명"/>
			<a href="#" title="새 창으로 이동"  onClick="fn_aram_get_deptJobBx(); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="부서업무함" title="부서업무함">
			</a>
			<form:errors path="deptJobBxNm" cssClass="error"/>
		</td>
  	</tr>
  	<tr> 
    	<th>
  	    	<span class="required_icon"></span>
    		<label for="deptJobNm">제목</label>
    	</th>
    	<td>
      		<form:input path="deptJobNm" size="75" maxlength="255" title="제목"/>
      		<form:errors path="deptJobNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
	    	<span class="required_icon"></span>
    		<label for="deptJobCn">내용</label>
    	</th>
    	<td>
      		<form:textarea path="deptJobCn" rows="5" cols="90" title="내용"/>
   	  		<form:errors path="deptJobCn" cssClass="error"/>
    	</td>
  	</tr> 
   	<tr> 
    	<th>
	    	<span class="required_icon"></span>
    		<label for="chargerNm">업무담당자</label>
    	</th>
    	<td>
			<form:input path="chargerNm" size="30" readonly="true" maxlength="10" title="담당자"/>
			<a href="#" title="새 창으로 이동" onClick="fn_aram_get_charger('담당자', 'chargerId', 'chargerNm'); return false;">
				<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="담당자" title="담당자">
			</a>
			<form:errors path="chargerNm" cssClass="error"/>
 	      	<form:hidden path="chargerId" />       
    	</td>
  	</tr>
  	<tr> 
    	<th>
	    	<span class="required_icon"></span>
    		<label for="priort">우선순위</label>
    	</th>
    	<td>
       		<form:radiobutton path="priort" value="1" label="높음"/>
       		<form:radiobutton path="priort" value="2" label="보통"/>
       		<form:radiobutton path="priort" value="3" label="낮음"/>
       		<form:errors path="priort" cssClass="error"/>
    	</td>
  	</tr>
  	<!-- 첨부파일 테이블 레이아웃 설정 -->
  	<tr>
		<th>
	    	<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
		</th>
		<td>
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	   		<table>
				<tr>
					<td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/></td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>		      
	 	</td>
  	</tr>
  	<!-- //첨부파일 테이블 레이아웃 설정 -->
</tbody>
</table>

</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="deptJobVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!--  첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

window.onload = function() {
	document.getElementsByName("priort")[1].checked = true;
}

function fn_aram_list() {
    var varForm = document.getElementById("deptJobVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJob.do";
    varForm.submit();	
}	

function fn_aram_insert() {
	var varForm = document.getElementById("deptJobVO");

	if (!validateDeptJobVO(varForm)){
		return;
	}
	
	if (confirm("<spring:message code='common.regist.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/insertDeptJob.do";
		varForm.submit();					
	}
}

var gArguments = new Array();

/* ********************************************************
* 부서업무함  팝업창열기
******************************************************** */
function fn_aram_get_deptJobBx(){
	var varForm = document.getElementById("deptJobVO");
	gArguments["deptId"] 	  = varForm.deptId;
	gArguments["deptNm"] 	  = varForm.deptNm;
	gArguments["deptJobBxId"] = varForm.deptJobBxId;
	gArguments["deptJobBxNm"] = varForm.deptJobBxNm;
	
	var url = "/cop/smt/djm/listDeptJobBx.do?deptId="+varForm.deptId.value;

	window.open(url, "p_deptInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_aram_get_charger(strTitle, empId, empName){
	gArguments["title"]    = strTitle;
	if( empId != "" )    gArguments["uniqId"]   = document.getElementById(empId);
	if( empName != "" )  gArguments["emplyrNm"] = document.getElementById(empName);

	var url = "/cop/smt/djm/listChargerPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>
