<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : BndtManageBndeListPop.jsp
 * @Description : 당직 일괄등록
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
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>당직 일괄등록 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<c:if test="${!empty message}">
	<c:if test="${message eq 'true'}"> 
//	   	var opener = parent.window.dialogArguments;
	   	alert("당직엑셀 등록처리 완료하였습니다"); 
//	   	opener[0].fncSelectAnnvrsryManageList();
	   	opener.fncSelectBndtManageList();
	   	window.close();
	</c:if>
	<c:if test="${message ne 'true'}"> alert("${message}"); </c:if>
</c:if>
	
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직일괄등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
        <span class="button"><a href="#" onclick="javascript:fncBndtManageBndeRegist(); return false;"><spring:message code="button.create" /></a></span>     
	</div>
</div>

<form name="listForm" id="listForm" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="searchCondition">
<input type="hidden" name="checkedBndtManageForInsert">
<input type="hidden" name="searchKeyword">
<input type="hidden" name="cmd">

<table class="table-register">
  	<tr>
    	<th width="20%"> 
    		당직자 엑셀파일
    	</th>          
    	<td width="80%">
    		<input type = "file" name="file" size="40" title="일괄파일"/>
    		<span class="button"><input type="submit" value="업로드" onclick="fncBndtManageBndeCheck(); return false;"></span>
    	</td>
  	</tr> 
</table>

<div style="margin-top:3px; width:100%"></div>

<table class="table-list" summary="당직자 엑셀파일">
<caption>당직자 엑셀파일</caption>
<thead>
	<tr>  
		<th width="30%" scope="col">당직일자</th>
		<th width="30%" scope="col">당직자명</th>
		<th             scope="col">소속명</th>
	</tr>
</thead>     
<tbody>
	<c:if test="${fn:length(bndtManageList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="5">
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>   	          				 			   
	</c:if>

	<c:forEach items="${bndtManageList}" var="resultInfo" varStatus="status">
	<input type="hidden" name="bndtDe" id="bndtDe" value="${resultInfo.bndtDe}">
	<input type="hidden" name="bndtId" id="bndtId" value="${resultInfo.bndtId}">
	<tr>
		<td class="lt_text3">
			<font <c:if test="${(resultInfo.dateWeek ) == 1}"> color="red" </c:if> <c:if test="${(resultInfo.dateWeek ) == 7}"> color="blue"</c:if> size='2'>
				<c:out value="${resultInfo.tempBndtWeek}"/>
			</font>
		</td>
		<td class="lt_text3">${resultInfo.tempBndtNm}</td>
		<td class="lt_text3">${resultInfo.tempOrgnztNm}</td>
	</tr>   
	</c:forEach>
</tbody>  
</table>

</form>

</div>

<script type="text/javascript">

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fncBndtManageBndeRegist(){
	  var varForm = document.getElementById("listForm");
      //var checkField = varForm.bndtCheck;
      var bndtId     = varForm.bndtId;
      var bndtDe     = varForm.bndtDe;
      var searchKeyword  = varForm.searchKeyword;
      var checkBndtManage = "";
      var checkedCount     = 0;

   	  if(bndtId.length> 1){
   		  searchKeyword.value = bndtDe[0].value.substring(0,6);
   		  for(var i=0; i < bndtId.length; i++){
        	   
              checkBndtManage += ((checkedCount==0? "" : "$")+bndtDe[i].value+","+bndtId[i].value);
              checkedCount++;
           }
      }else{
           	checkMtgPlaces = bndtDe.value+","+bndtDe.value;
      }

      varForm.checkedBndtManageForInsert.value=checkBndtManage;
      varForm.action = "/uss/ion/bnt/insertBndtManageBnde.do";

      if(confirm("저장 하시겠습니까?")){
	         varForm.submit();
	  }
}

/* ********************************************************
 * 엑셀체크 처리 함수 
 ******************************************************** */
function fncBndtManageBndeCheck(){
   if(checkFile()){
	   document.listForm.action ="/uss/ion/bnt/BndtManageListPop.do";
	   document.listForm.cmd.value = "bnde";
       document.listForm.submit();
   }
}

/* ********************************************************
* 당직엑셀일괄등록시 등록파일 체크 함수
******************************************************** */
function checkFile(){ 
	if(document.listForm.file.value==""){
	   alert("업로드 할 파일을 지정해 주세요");
	   return false;
	}

	var  str_dotlocation,str_ext,str_low;
	str_value  = document.listForm.file.value;
	str_low   = str_value.toLowerCase(str_value);
	str_dotlocation = str_low.lastIndexOf(".");
	str_ext   = str_low.substring(str_dotlocation+1);
	
	switch (str_ext) {
	  case "xls" :
	  case "xlsx" :
		 return true; 
	     break;
	  default:
	     alert("파일 형식이 맞지 않습니다.\n xls,XLS,xlsx,XLSX 만\n 업로드가 가능합니다!");
	     return false;
	}
}

</script>

</body>
</html>
