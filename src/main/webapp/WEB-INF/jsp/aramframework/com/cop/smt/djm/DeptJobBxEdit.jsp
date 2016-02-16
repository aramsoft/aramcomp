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
 * @Class Name : DeptJobBxEdit.jsp
 * @Description : 부서 업무함 수정
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
	<h2>부서 업무함 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="deptJobBxVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="deptId" />
<form:hidden path="deptJobBxId" />

<input type="hidden" name="deptIndictOrdr" value="<c:out value='${indictOrdrValue}'/>" />

<table class="table-register"   summary="이 표는 부서업무함 정보를  수정하기 위한 표이며, 부서, 부서업무함명, 표시순서 정보로 구성되어 있습니다 .">
<caption>부서업무함 수정</caption>
<tbody>
  	<tr> 
		<th width="20%">
	    	<span class="required_icon"></span>
			<label for="deptNm">부서</label>
		</th>
		<td width="80%">
		<table border="0">
			<tr>
				<td width="120px" >
					<form:input path="deptNm" size="20" readonly="true" maxlength="20" title="부서" />
				</td>
				<td >
					<a href="#" title="새 창으로 이동" onClick="fn_aram_get_dept(); return false;">
						<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="부서" title="부서">
					</a>
				</td>
			</tr>
		</table>
		<form:errors path="deptNm" cssClass="error"/>
		</td>
  	</tr>
  	<tr> 
		<th>
	    	<span class="required_icon"></span>
			<label for="deptJobBxNm">부서업무함명</label>
		</th>
		<td>
	  		<form:input path="deptJobBxNm" size="50" maxlength="255" title="부서업무함명"/>
	  		<form:errors path="deptJobBxNm" cssClass="error"/>
		</td>
  	</tr>
  	<tr> 
    	<th>
	    	<span class="required_icon"></span>
    		<label for="indictOrdr">표시순서</label>
    	</th>
    	<td>
      		<form:input path="indictOrdr" size="3" maxlength="6" title="표시순서"  />
      		<form:errors path="indictOrdr" cssClass="error"/>
    	</td>
  	</tr>
</tbody>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="deptJobBxVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("deptJobBxVO");
	if(varForm.indictOrdr.value == ""){
		varForm.indictOrdr.value = "0";
	}
	varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJobBx.do";
	varForm.submit();					
}	

function fn_aram_update() {
    var varForm = document.getElementById("deptJobBxVO");

    if (!validateDeptJobBxVO(varForm)){
		return;
	}

	var intIndictOrdr = eval(varForm.indictOrdr.value);
	var deptIndictOrdr = eval(varForm.deptIndictOrdr.value);
	 
	if(intIndictOrdr < 1){
		alert("표시순서의 값을 1 이상으로 입력해야 합니다.");
		return;
	}

	if(deptIndictOrdr < intIndictOrdr){
		alert("해당 부서의 부서업무함 표시순서의 최대 값은 " +  (deptIndictOrdr -1) + "이므로 " + deptIndictOrdr + "이하로 입력해야 합니다.");
		return;
	}
	
	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/updateDeptJobBx.do";
		varForm.submit();					
	}
}

function fn_aram_delete() {
    var varForm = document.getElementById("deptJobBxVO");
    
	if (confirm("<spring:message code='common.delete.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/deleteDeptJobBx.do";
		varForm.submit();					
	}
}

var gArguments = new Array();

/* ********************************************************
* 부서  팝업창열기
******************************************************** */
function fn_aram_get_dept(){
    var varForm = document.getElementById("deptJobBxVO");
	gArguments["deptId"] = varForm.deptId;
	gArguments["deptNm"] = varForm.deptNm;
	
	var url = "/cop/smt/djm/listDeptPopup.do";

	window.open(url, "p_deptInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>
