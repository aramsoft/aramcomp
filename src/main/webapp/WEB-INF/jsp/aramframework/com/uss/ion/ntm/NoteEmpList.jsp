<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : NoteEmpList.jsp
 * @Description : 수신자 /참조자 선택
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
<title>수신자/참조자 선택</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>수신자/참조자 선택</h2>
</div>

<form:form commandName="searchVO" action="" method="post">

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_search_noteEmp(); return false;"><spring:message code="button.inquire" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_close_noteEmp(); return false;">전체선택</a></span>
		<span class="button"><a href="#" onclick="javascript:window.close(); return false;"><spring:message code="button.close" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchVO.searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="EMPLYR_NM" label="이름" />			   
	   		<form:option value="EMPLYR_ID" label="아이디" />			   
	   		<form:option value="OFFM_TELNO" label="전화번호" />			   
   		</form:select>
   		<form:input path="searchVO.searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="searchVO.recordPerPage" class="select" onchange="fn_aram_search_noteEmp();" title="recordPerPage">
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="searchVO.pageIndex" />
</form:form>

<!--  목록  -->
<table class="table-list" summary="목록 을 제공한다.">
<caption>목록 을 제공한다</caption>
<thead>
	<tr>  
	    <th scope="col" width="35px" >순번</th>
	    <th scope="col" width="100px">아이디</th>
	    <th scope="col" width="100px">이름</th>
	    <th scope="col" width="100px">전화번호</th>
	    <th scope="col" class="title">주소</th>   
	    <th scope="col" width="30px" align="center"><input type="checkbox" name="checkAll" title="전체선택" id="checkAll" value="1" onClick="fn_aram_checkAll();"></th>       
	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr> 
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>   	          				 			   
	</c:if>
	
	<%-- 데이터를 화면에 출력해준다  --%>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

		<td class="lt_text3L"><a href="#" onclick="fn_aram_close_noteEmpOne(${status.count-1}); return false;">${result.emplyrId}</a></td>
		<td class="lt_text3L"><a href="#" onclick="fn_aram_close_noteEmpOne(${status.count-1}); return false;">${result.emplyrNm}</a></td>
		<td class="lt_text3"><a href="#" onclick="fn_aram_close_noteEmpOne(${status.count-1}); return false;">${result.offmTelno}</a></td>
		<td class="lt_text3L"><a href="#" onclick="fn_aram_close_noteEmpOne(${status.count-1}); return false;">${result.homeAdres} ${result.detailAdres}</a></td>
		
    	<td class="lt_text3"><input type="checkbox" name="checkList" title="선택" value="${result.uniqId}|${result.emplyrId}|${result.emplyrNm}"></td>
    	<!--  <a href="#" onclick="JavaScript:fn_aram_open_Popup('${status.count}', '${resultInfo.uniqId}'); return false;">선택 </a> -->
	</tr>   
	</c:forEach>
</tbody>  
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search_noteEmp();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("searchVO");
    varForm["searchVO.pageIndex"].value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ntm/listNoteEmpPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search_noteEmp(){
    var varForm = document.getElementById("searchVO");
    varForm["searchVO.pageIndex"].value = "1";
    varForm.action = "${pageContext.request.contextPath}/uss/ion/ntm/listNoteEmpPopup.do";
    varForm.submit();
}

/* ********************************************************
 * 선택 처리 함수
 ******************************************************** */
function fn_aram_open_Popup(cnt, uniqId){
	
	if(opener != null){
		alert( opener.document.getElementById("noteCn").value );
	}
}

/* ********************************************************
* 화면 닫기 함수
******************************************************** */
function fn_aram_close_NoteEmp(){

	var FLength = document.getElementsByName("checkList").length;

	var strSplit;
	var arrSplit;
	var strRecptnSe;
	var strRecptnSeCode;
	//select 박스 수신자 객체
	var selRecptnEmp = opener.document.getElementById("recptnEmp");
	
	if( FLength == 1){
		if(document.getElementsByName("checkList")[0].checked == true){
			strSplit = document.getElementsByName("checkList")[0].value;
			arrSplit = strSplit.split("|");
			
			//수신 체크시
			if(opener.document.getElementsByName("recptnSe")[0].checked == true){
				strRecptnSe = "수신";
				strRecptnSeCode = "1";
			}else{
				strRecptnSe = "참조";
				strRecptnSeCode = "2";
			}
			//추가할 option 객체 
			var option = document.createElement("option");
			option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
			option.setAttribute("value", arrSplit[0]);
	
			opener.fn_aram_recptnEmpOption_noteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);
		}
	}else{
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName("checkList")[i].checked == true){

				strSplit = document.getElementsByName("checkList")[i].value;
				arrSplit = strSplit.split("|");
				
				//수신 체크시
				if(opener.document.getElementsByName("recptnSe")[0].checked == true){
					strRecptnSe = "수신";
					strRecptnSeCode = "1";
				}else{
					strRecptnSe = "참조";
					strRecptnSeCode = "2";
				}
				//추가할 option 객체 
				var option = document.createElement("option");
				option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
				option.setAttribute("value", arrSplit[0]);
		
				opener.fn_aram_recptnEmpOption_noteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);
			}	
		}
	}	
	
	window.close();
}

/* ********************************************************
* 이름/이이디 클릭시 단건 입력
******************************************************** */
function fn_aram_close_noteEmpOne(i){

	var strSplit;
	var arrSplit;
	var strRecptnSe;
	var strRecptnSeCode;
	//select 박스 수신자 객체
	var selRecptnEmp = opener.document.getElementById("recptnEmp");
	
	if(document.getElementsByName("checkList")[i] != null && document.getElementsByName("checkList")[i] != undefined){

		strSplit = document.getElementsByName("checkList")[i].value;
		arrSplit = strSplit.split("|");
		
		//수신 체크시
		if(opener.document.getElementsByName("recptnSe")[0].checked == true){
			strRecptnSe = "수신";
			strRecptnSeCode = "1";
		}else{
			strRecptnSe = "참조";
			strRecptnSeCode = "2";
		}
		//추가할 option 객체 
		var option = document.createElement("option");
		option.appendChild(document.createTextNode(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")"));
		option.setAttribute("value", arrSplit[0]);

		opener.fn_aram_recptnEmpOption_noteManage(strRecptnSe+":"+arrSplit[1]+"("+arrSplit[2]+")",arrSplit[0],strRecptnSeCode);
	}	
	
	window.close();
}

/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_aram_checkAll(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;
	
	//undefined
	if( FLength == 1){
		document.getElementsByName("checkList")[0].checked = checkAllValue;
	}else{
		for(var i=0; i < FLength; i++)
		{
			document.getElementsByName("checkList")[i].checked = checkAllValue;	
		}
	}	
}

</script>

</body>
</html>