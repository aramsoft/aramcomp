<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AnnvrsryBndeListPopup.jsp
 * @Description : 기념일 일괄 등록 
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
<title>기념일 일괄 등록 </title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

<base target="_self"/>
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main" >

<div class="content_title">
	<h2>기념일일괄등록</h2>
</div>

<form name="listForm" id="listForm" action="${pageContext.request.contextPath}/uss/ion/ans/listAnnvrsryBndePopup.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="searchCondition">
<input type="hidden" name="checkedAnnvrsryManageForInsert">
<input type="hidden" name="searchKeyword">
<input type="hidden" name="cmd">

<div id="search_area">
	<div class="button_area">
        <span class="button"><a href="#" onclick="fncAnnvrsryManageBndeRegist(); return false;"><spring:message code="button.create" /></a></span>     
	</div>
</div>

<table class="table-register">
  	<tr>
    	<th width="20%">
			<span class="norequired_icon"></span>
    		 기념일 엑셀파일
    	</th>          
    	<td width="80%">
    		<input type = "file" name="file" size="40" title="일괄파일"/>
    		<span class="button"><input type="submit" value="업로드" onclick="fncAnnvrsryManageBndeCheck(); return false;"></span>
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px; width:100%"></div>

<table class="table-list" summary="기념일 엑셀파일">
<caption>기념일 엑셀파일</caption>
<thead>
	<tr>  
		<th scope="col" width="25%">기념일명</th>
		<th scope="col" width="20%">기념일구분</th>
		<th scope="col"            >기념일자(양/음)</th>
		<th scope="col" width="20%">반복여부</th>
	</tr>
</thead>     
<tbody>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="4">
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>   	          				 			   
	</c:if>

	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
		<td class="lt_text3">
			<c:out value="${result.annvrsryNm}"/>
			<input type="hidden" name="usid"       id="usid"       value="${result.usid}">
			<input type="hidden" name="annvrsryDe" id="annvrsryDe" value="${result.annvrsryDe}">
			<input type="hidden" name="cldrSe"     id="cldrSe"     value="${result.cldrSe}">
			<input type="hidden" name="annvrsrySe" id="annvrsrySe" value="${result.annvrsrySe}">
			<input type="hidden" name="annvrsryNm" id="annvrsryNm" value="${result.annvrsryNm}">
			<input type="hidden" name="reptitSe"   id="reptitSe"   value="${result.reptitSe}">
		</td>
		<td class="lt_text3"><c:out value="${result.annvrsrySeNm}"/></td>
		<td class="lt_text3"><c:out value="${result.annvrsryDe}"/>
	      	<c:if test="${!empty result.cldrSe }">(<c:if test='${result.cldrSe == "1"}'>양</c:if><c:if test='${result.cldrSe == "2"}'>음</c:if>)</c:if>
	    </td>
		<td class="lt_text3"><c:out value="${result.reptitSe}"/></td>
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
function fncAnnvrsryManageBndeRegist(){
	var varForm = document.getElementById("listForm");
	  
    var usid           = varForm.usid;
    var annvrsryDe     = varForm.annvrsryDe;
    var cldrSe         = varForm.cldrSe;
    var annvrsrySe     = varForm.annvrsrySe;
    var annvrsryNm     = varForm.annvrsryNm;
    var reptitSe       = varForm.reptitSe;
     
    var checkAnnvrsryManage = "";
    var checkedCount     = 0;

  	if(usid.length> 1){
  		for(var i=0; i < usid.length; i++){
           checkAnnvrsryManage += ((checkedCount==0? "" : "$")+usid[i].value+","+annvrsryDe[i].value+","+cldrSe[i].value+","+annvrsrySe[i].value+","+annvrsryNm[i].value+","+reptitSe[i].value);
           checkedCount++;
        }
    }else{
   	  	checkAnnvrsryManage = usid.value+","+annvrsryDe.value+","+cldrSe.value+","+annvrsrySe.value+","+annvrsryNm.value+","+reptitSe.value;
    }

    varForm.checkedAnnvrsryManageForInsert.value=checkAnnvrsryManage;

   	if(confirm("저장 하시겠습니까?")){
   	    varForm.action = "/uss/ion/ans/insertAnnvrsryBnde.do";
        varForm.submit();
  	}
}

/* ********************************************************
 * 엑셀체크 처리 함수 
 ******************************************************** */
function fncAnnvrsryManageBndeCheck(){
   var varForm = document.getElementById("listForm");
   if(checkFile()){
	   varForm.action ="/uss/ion/ans/listAnnvrsryBndePopup.do";
	   varForm.cmd.value = "bnde";
	   varForm.submit();
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

<c:if test="${!empty message}">
   	<c:if test="${message eq 'true'}"> 
		alert("기념일 엑셀 등록처리 완료하였습니다");
		
		var retFunc = window.opener.gArguments["retFunc"];
		if( retFunc != undefined ) {
			retFunc();		
		}
		window.close();
		
   	</c:if>
   	<c:if test="${message ne 'true'}"> alert("${message}");  </c:if>
</c:if>
		
</script>

</body>
</html>
