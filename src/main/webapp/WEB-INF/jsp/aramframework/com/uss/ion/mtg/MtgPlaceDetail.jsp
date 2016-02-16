<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceDetail.jsp
 * @Description : 회의실 상세조회
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
	<h2>회의실 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
   		<span class="button"><a href="#" onclick="fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
   		<span class="button"><a href="#" onclick="fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>     
   		<span class="button"><a href="#" onclick="fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mtgPlaceManageVO" method="post" action="">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mtgPlaceId" />

<table class="table-detail" summary="회의실  상세">
<caption>회의실 상세</caption>
  	<tr> 
    	<th width="20%">
    		회의실 명
    	</th>
    	<td colspan ="3">
    		<c:out value='${mtgPlaceManageVO.mtgPlaceNm}'/>
    	</td>
  	</tr> 
  	<tr> 
    	<th width="20%">
    		수용가능인원
    	</th>
    	<td width="20%">
    		<c:out value='${mtgPlaceManageVO.aceptncPosblNmpr}'/>명
    	</td>
    	<th width="20%">
     		개방시간
    	</th>
    	<td width="40%">
    		<c:out value='${mtgPlaceManageVO.opnBeginTm}'/> ~ <c:out value='${mtgPlaceManageVO.opnEndTm}'/>
    	</td>
  	</tr>
  	<tr> 
    	<th>
     		위치
    	</th>
    	<td colspan ="3">	 
    		<c:out value='${mtgPlaceManageVO.lcSeNm}'/> 
    		<c:out value='${mtgPlaceManageVO.lcDetail}'/>
    	</td> 
  	</tr>
<!--  첨부파일 테이블 레이아웃 설정 Start.. -->
  	<c:if test="${!empty mtgPlaceManageVO.atchFileId}">
	<tr> 
		<th>
			이미지 파일목록
		</th>
	    <td colspan="3">
			<c:import url="/content/files/${mtgPlaceManageVO.atchFileId}" />
	    </td>
	</tr>
  	</c:if>	
<!--  첨부파일 테이블 레이아웃 End. -->
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<table class="table-detail" summary="회의실  비품정보 상세">
<caption>회의실  비품정보 상세</caption>
<tr> 
	<th width="20%">
    	비품정보
    </th>
    <td width="80%" align="center">
		<table class="table-list">
        	<c:forEach var="mtgPlaceFxtrs" items="${mtgPlaceFxtrsList}" varStatus="status">
                <c:if test="${(status.count-1)%2 == 0}"> 
					<c:out value="<tr>" escapeXml="false"/>
                </c:if>
                	<td width="10%" height="15" align="left"> </td>
			    	<td width="20%" height="15" align="left">${mtgPlaceFxtrs.fxtrsNm}  </td> 
                	<td width="10%" height="15" align="right">${mtgPlaceFxtrs.quantity}개 </td>
                	<td width="10%" height="15" align="left"> </td>
                <c:if test="${(status.count-1)%2 == 1}"> 
				    <c:out value="</tr>" escapeXml="false"/>
                </c:if>
			</c:forEach>
		</table>
    </td>
</tr>
</table>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="mtgPlaceManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/*설명 : 회의실 목록 조회 */
function fn_aram_list(){
	var varForm = document.getElementById("mtgPlaceManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlace.do";
	varForm.submit();
}

/*설명 : 회의실 수정조회 */
function fn_aram_edit() {
	var varForm = document.getElementById("mtgPlaceManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/editMtgPlace.do";
	varForm.submit();   
}

/*설명 : 회의실 삭제 */
function fn_aram_delete() {
    var varForm = document.getElementById("mtgPlaceManageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/deleteMtgPlace.do";
        varForm.submit();
    }
}

</script>

