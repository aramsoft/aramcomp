<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : BndtDiaryEdit.jsp
 * @Description : 당직일지  수정
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
<title>당직일지  수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aramframework/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>당직일지  수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bndtDiaryVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="diaryForUpdate" type="hidden" />
<form:hidden path="bndtId" />
<form:hidden path="bndtDe" />

<!-- 등록  폼 영역  -->
<table class="table-list" summary="당직일지 수정">
<caption>당직일지 수정</caption>
<thead>
	<tr>  
		<th width="40%" scope="col">당직체크목록</th>
		<th width="30%" scope="col">양호</th>
		<th width="30%" scope="col">불량</th>
	</tr>
</thead>     
<tbody>
	<c:forEach items="${bndtDiaryList}" var="result" varStatus="status">
	<input name="bndtCeckCd"    type="hidden" value="<c:out value='${result.bndtCeckCd}'/>"/>
	<input name="bndtCeckSe"    type="hidden" value="<c:out value='${result.bndtCeckSe}'/>"/>
	<input name="bndtCeckCdNm"  type="hidden" value="<c:out value='${result.bndtCeckCdNm}'/>"/>
	<tr>
		<td class="lt_text3">
			<c:out value="${result.bndtCeckCdNm}"/>
			<span class="required_icon"></span>
		</td>
		<c:if test="${result.bndtCeckSe == '99'}">
	    <td class="lt_text3" colspan=4>
	        <input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" id="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="text" size="70" value="<c:out value='${result.chckSttus}'/>" maxlength="2000" style="width:90%;" title="<c:out value="${result.bndtCeckCdNm}"/>">
	    </td>
		</c:if>
		<c:if test="${result.bndtCeckSe != '99'}">
		<c:if test="${result.chckSttus == '1'}">
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="1" title="양호"   checked></td>
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="2" title="불량"></td>
		</c:if>
		<c:if test="${result.chckSttus == '2'}">
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="1" title="양호"></td>
		<td class="lt_text3"><input name="chckSttus${result.bndtCeckSe}${result.bndtCeckCd}" type="radio" value="2" title="불량"   checked></td>
		</c:if>
		</c:if>
	</tr>   
	</c:forEach>
</tbody>  
</table>

<!-- 검색조건 유지 -->
<form:hidden path="year" />
<form:hidden path="month" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bndtDiaryVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
	var varForm = document.getElementById("bndtDiaryVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/listBndtManage.do";
	varForm.submit();   
}

/* ********************************************************
* 멀티입력 처리 함수
******************************************************** */
function fn_aram_update() {
	var varForm = document.getElementById("bndtDiaryVO");
   	var bndtCeckCd = varForm.bndtCeckCd;
   	var bndtCeckSe = varForm.bndtCeckSe;
   	var bndtCeckCdNm = varForm.bndtCeckCdNm;
   	var checkChckSttus = "";
   	var valBndtDiary = "";
   	var checkedCount = 0;
   	var validatorCount = 0;
   	var obj ;
   	if(bndtCeckCd.length> 1) {
      	for(var i=0; i < bndtCeckCd.length; i++) {
		   	if(bndtCeckSe[i].value == "99"){
			   	checkChckSttus = document.getElementById("chckSttus"+bndtCeckSe[i].value+bndtCeckCd[i].value).value;
			   	if(checkChckSttus == "" || checkChckSttus == null){
				   	alert(bndtCeckCdNm[i].value+" 필드는 필수 입력입니다.");
				   	validatorCount++;
			   	}
		   	}
		   	else
		   	{
	           	obj = document.getElementsByName("chckSttus"+bndtCeckSe[i].value+bndtCeckCd[i].value);
	           	for(var j=0; j < obj.length; j++) {
	              	if(obj[j].checked) checkChckSttus = obj[j].value;
	           	}
		   	}
         	valBndtDiary += ((checkedCount==0? "" : "@")+bndtCeckSe[i].value+"$"+bndtCeckCd[i].value+"$"+checkChckSttus);
         	checkedCount++;
      	}
   	} else {
	   	if(bndtCeckSe.value == "99"){
		    checkChckSttus = document.getElementById("chckSttus"+bndtCeckSe.value+bndtCeckCd.value).value;
		    if(checkChckSttus == "" || checkChckSttus == null){
			   	alert(bndtCeckCdNm.value+" 필드는 필수 입력입니다.");
			   	validatorCount++;
		    }
	   	}
	   	else
	   	{
         	obj = document.getElementsByName("chckSttus"+bndtCeckSe.value+bndtCeckCd.value);
         	for(var j=0; j < obj.length; j++) {
            	if(obj[j].checked) checkChckSttus = obj[j].value;
         	}
	   	}
       	valBndtDiary = bndtCeckSe.value+"$"+bndtCeckCd.value+"$"+checkChckSttus;
   	}
   	varForm.diaryForUpdate.value=valBndtDiary;

   	if(validatorCount!=0) return;
   	
  	if(confirm("<spring:message code='common.update.msg'/>")){
	   	varForm.action = "${pageContext.request.contextPath}/uss/ion/bnt/updateBndtDiary.do";
  		varForm.submit();
  	}
}

</script>

</body>
</html>