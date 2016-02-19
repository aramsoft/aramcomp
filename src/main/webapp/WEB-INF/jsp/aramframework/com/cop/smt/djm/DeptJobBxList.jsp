<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : DeptJobBxList.jsp
 * @Description : 부서 업무함 목록
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
	<h2>부서 업무함 목록</h2>
</div>

<form:form commandName="deptJobBxVO" action ="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="deptJobBxId" />
<input type="hidden" name="deptId">
<input type="hidden" name="ordrCnd">
<input type="hidden" name="indictOrdr" value="0">
	
<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_regist(); return false;"><spring:message code="button.create" /></a></span>
	</div>
	<div class="keyword_area">
		<label for="searchCondition">조회조건 : </label>
  		<form:select path="searchVO.searchCondition" class="select" title="조회조건 선택">
			<form:option value='' label="--선택하세요--" />
			<form:option value="ORGNZT_NM" label="부서명" />
			<form:option value="DEPT_JOBBX_NM" label="부서업무함명" />
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="15" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" /> 
		<form:select path="searchVO.recordPerPage" class="select" onchange="javascript:fn_aram_search(); return false;" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>
<form:hidden path="searchVO.pageIndex" />
	
<div>
	<div style="width:90%;float:left;" >
		<table class="table-list" summary="이 표는 부서업무함 정보를 제공하며, 부서명, 부서업무함명, 최종수정자, 최종수정일  정보로 구성되어 있습니다.">
		<caption>부서업무함목록</caption> 
 		<thead>
  			<tr>
    			<th scope="col" width="7%" >No.</th>
    			<th scope="col" width="5%" ></th>
    			<th scope="col" width="25%">부서명</th>
    			<th scope="col"            >부서업무함명</th>
    			<th scope="col" width="15%">최종수정자</th>
    			<th scope="col" width="15%">최종수정일</th>
  			</tr>
 		</thead>    
 		<tbody>
	 		<c:if test="${fn:length(resultList) == 0}">
	  		<tr>
	    		<td class="lt_text3"  colspan="6"><spring:message code="common.nodata.msg" /></td>  
	  		</tr>		 
	 		</c:if>

 			<c:set var="startIndex" value="${(deptJobBxVO.searchVO.pageIndex-1) * deptJobBxVO.searchVO.recordPerPage}"/>
	 		<c:forEach var="result" items="${resultList}" varStatus="status">
	  		<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.deptJobBxId}"/>'); return false;">

		 		<c:set var="index" value="${startIndex + status.count}"/>
				<c:set var="reverseIndex" value="${deptJobBxVO.searchVO.totalRecordCount - index + 1}"/>
				<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

	    		<td class="lt_text3">  
	    			<input type="radio" name="check1" id="<c:out value="${result.deptJobBxId}"/>::<c:out value="${result.indictOrdr}"/>::<c:out value="${result.deptId}"/>" class="check2" onclick="fCheck('<c:out value="${status.count}"/>', '<c:out value="${result.deptJobBxId}"/>','<c:out value="${result.indictOrdr}"/>','<c:out value="${result.deptId}"/>'); return false;" title="선택">
	    		</td>
	    		<td class="lt_text3"><c:out value="${result.deptNm}"/></td>
	    		<td class="lt_text3"><c:out value="${result.deptJobBxNm}"/></td>
	    		<td class="lt_text3"><c:out value="${result.lastUpdusrNm}"/></td>
	    		<td class="lt_text3"><c:out value="${fn:substring(result.lastUpdtPnttm, 0, 10)}"/></td>
	  		</tr>
	 		</c:forEach>	  
 		</tbody>
		</table>
	</div>

	<div style="width:10%;text-align:center;valign:middle;float:right;">
		<table summary="부서업무함의 순서를 수정합니다.">
		<thead>
		  	<tr>
		    	<th width="10%"  style="vertical-align: middle;height : 37px;"></th>
		  	</tr>
		</thead>
		<tbody>
		  	<tr>
		  		<td>
		  			<a href = "#" onclick="javascript:fn_aram_order_deptjobbx('up'); return false;"><font style="font-size: 15pt">▲</font></a>
		  			<br><br>
		  			<a href = "#" onclick="javascript:fn_aram_order_deptjobbx('down'); return false;"><font style="font-size: 15pt">▼</font></a>
		  		</td>
		  	</tr>
		</tbody>
		</table>
	</div>
</div>

</form:form>

<div id="page_navigation" style="clear:both;">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_aram_linkPage" />
</div>

</div>

<script type="text/javascript">

window.onload = function() {
    var varForm = document.getElementById("deptJobBxVO");
	var resultNum = eval("<c:out value='${resultNum}'/>");
	var checkedDeptJobBxId = varForm.deptJobBxId.value;

    var radiocheck = document.getElementsByName("check1");
	if(checkedDeptJobBxId != "" && resultNum> 0){
        for(var i=0; i < resultNum; i++) {
			var checkIdValue = radiocheck[i].id;
	        var splitCheckIdValue = checkIdValue.split("::");
            if(splitCheckIdValue[0] == checkedDeptJobBxId){
            	radiocheck[i].checked = true;

                varForm.deptJobBxId.value = checkedDeptJobBxId;
                varForm.indictOrdr.value = splitCheckIdValue[1];
                varForm.deptId.value = splitCheckIdValue[2];
            }
        }
	}

	if("<c:out value='${indictOrdrChanged}'/>" == "false"){
		alert("동일 부서명내에서 부서업무함명의 순서를 변경할 수 있습니다."); 
	}
}

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo) {
    var varForm = document.getElementById("deptJobBxVO");
	varForm["searchVO.pageIndex"].value = pageNo; 
	varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJobBx.do";
	varForm.submit();	
}

function fn_aram_search() {
    var varForm = document.getElementById("deptJobBxVO");
	if(varForm.searchKeyword.value != ""){
		if(varForm.searchCondition.value == ""){
			alert("조회조건을 선택하지 않으셨습니다");
			return;
		}
	}
	
	varForm["searchVO.pageIndex"].value = '1'; 
	varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/listDeptJobBx.do";
	varForm.submit();	
}

function fn_aram_detail(deptJobBxId) {
    var varForm = document.getElementById("deptJobBxVO");
    varForm.deptJobBxId.value = deptJobBxId;
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/editDeptJobBx.do";
    varForm.submit();	
}

function fn_aram_regist(){	
    var varForm = document.getElementById("deptJobBxVO");
    varForm.deptJobBxId.value = "";
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/registDeptJobBx.do";
    varForm.submit();
}

function fn_aram_order_deptjobbx(ordrCnd) {
    var varForm = document.getElementById("deptJobBxVO");
	var num = 0;
	
	for(var i=0; i < <c:out value='${resultNum}'/>; i++) {
        if(varForm.check1[i].checked){
            num ++;
        } 
    }

    if(num == 0){
    	alert("순서를 수정할 부서업무함을 선택하세요");
    	return;
    }
    
    varForm.ordrCnd.value = ordrCnd;	// 'up', 'down'
    varForm.action = "${pageContext.request.contextPath}/cop/smt/djm/updateDeptJobBxOrdr.do";
    varForm.submit();	
}

function fCheck(num, deptJobBxId, indictOrdr, deptId) {
    var varForm = document.getElementById("deptJobBxVO");
    varForm.deptJobBxId.value = deptJobBxId;
    varForm.indictOrdr.value = indictOrdr;
    varForm.deptId.value = deptId;

	var checkNum = eval(num);
    var radiocheck = document.getElementsByName("check1");
    var checkField = radiocheck[checkNum - 1];
    
	if(checkField) {
        for(var i=0; i < <c:out value='${resultNum}'/>; i++) {
            if(radiocheck[i].checked){
                if((checkNum - 1) != i){ 
             	   radiocheck[i].checked = false;
                }
            } 
        }
    } else {
        checkField.checked = true;
    }
}

</script>
