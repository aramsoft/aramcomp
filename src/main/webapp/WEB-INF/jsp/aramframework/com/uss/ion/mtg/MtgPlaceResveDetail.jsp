<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceResveDetail.jsp
 * @Description : 회의실 예약 상세조회
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
	<h2>회의실 예약 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<c:if test="${mtgPlaceResveVO.resveManId eq mtgPlaceResveVO.usidTemp}">
        	<span class="button"><a href="#" onclick="fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
        	<span class="button"><a href="#" onclick="fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>    
		</c:if> 
        <span class="button"><a href="#" onclick="fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mtgPlaceResveVO"  method="post" action=""> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mtgPlaceId" />

<form:hidden path="resveDe" />
<form:hidden path="resveId" />

<table class="table-detail" summary="회의실예약 상세">
<caption>회의실예약 상세</caption>
  	<tr> 
    	<th width="20%">
     		제목
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceResveVO.mtgSj}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		회의실명
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceManageVO.mtgPlaceNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		회의실 위치
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceManageVO.lcSeNm}'/> 
    		<c:out value='${mtgPlaceManageVO.lcDetail}'/>
	    	<c:if test="${!empty mtgPlaceManageVO.atchFileId}"> 
	    	<span class="button"><a href="#" onclick="fn_aram_getImage(); return false;" title="새창으로">회의실 이미지</a></span>
	    	</c:if>   
    	</td>
  	</tr>  
  	<tr> 
    	<th width="20%">
     		예약자
    	</th>
    	<td width="30%">
    		<c:out value='${mtgPlaceResveVO.resveManNm}'/>
    	</td>
    	<th width="20%">
     		소속
    	</th>
    	<td width="30%">
    		<c:out value='${mtgPlaceResveVO.resevOrgnztNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th>
     		예약시간
    	</th>
    	<td colspan ="3">
 			<c:out value="${fn:substring(mtgPlaceResveVO.resveDe, 0,4)}-${fn:substring(mtgPlaceResveVO.resveDe, 4,6)}-${fn:substring(mtgPlaceResveVO.resveDe, 6,8)}" escapeXml="false" />
    		&nbsp;&nbsp;<c:out value='${mtgPlaceResveVO.resveBeginTm}'/> ~ <c:out value='${mtgPlaceResveVO.resveEndTm}'/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		참석인원
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceResveVO.atndncNmpr}'/>명
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		회의내용
    	</th>
    	<td colspan ="3">
      		<textarea id="mtgCn" name="mtgCn" class="txArea" rows="4" cols="70" disabled title="회의내용"><c:out value='${mtgPlaceResveVO.mtgCn}'/></textarea>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="mtgPlaceManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list() {
	var varForm = document.getElementById("mtgPlaceResveVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlaceResve.do";
	varForm.submit();       
}

<c:if test="${mtgPlaceResveVO.resveManId eq mtgPlaceResveVO.usidTemp}">
/*설명 : 회의실 예약 수정조회 */
function fn_aram_edit() {
	var varForm = document.getElementById("mtgPlaceResveVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/editMtgPlaceResve.do";
	varForm.submit();   
}

/*설명 : 회의실 예약 삭제 */
function fn_aram_delete() {
    var varForm = document.getElementById("mtgPlaceResveVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/deleteMtgPlaceResve.do";
        varForm.submit();
    }
}
</c:if>

/* ********************************************************
* 회의실 이미지  팝업창열기
* fn_aram_dplact_ceck
* ******************************************************** */
function fn_aram_getImage(){
	var varForm = document.getElementById("mtgPlaceResveVO");
	var sTempValue = "sTmMtgPlaceId="+varForm.mtgPlaceId.value;
	var url = "/uss/ion/mtg/detailMtgPlaceImage.do?"+sTempValue

	window.open(url, "p_imageInqire", "width=720px,height=500px,top=100px,left=100px,location=no");
}

</script>

