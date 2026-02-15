<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : SchdulDetail.jsp
 * @Description : 일정관리 상세보기
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
<%pageContext.setAttribute("crlf", "\r\n"); %>

		<div id="content">
			<div id="cur_loc">
				<ul>
					<li>HOME</li>
					<li>&gt;</li>
					<li>사용자관리</li>
					<li>&gt;</li>
					<li><strong>일정관리 상세보기</strong></li>
				</ul>
			</div>
			<!-- 검색 필드 박스 시작 -->
			<div id="search_field">
				<div id="search_field_loc"><h2><strong>일정관리 상세보기</strong></h2></div>
			</div>
			
			<form:form modelAttribute="schdulManageVO" action="" method="post">
				<input type="hidden" name="menuNo" value="${menuNo}" />
				<form:hidden path="schdulId" />
			
				<div class="modify_user">
				<table>
					<tr>
						<th width="20%"  class="required_text">일정구분</th>
					    <td width="80%">
					    	<c:forEach items="${COM030_schdulSe}" var="schdulSeInfo" varStatus="status">
					    	<c:if test="${schdulSeInfo.code eq schdulManageVO.schdulSe}">    
					     	<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
					    	</c:if>
					   	 	</c:forEach>
					    </td>
					</tr>
					<tr>
						<th width="20%"  class="required_text">중요도</th>
					    <td width="80%">
					    	<c:forEach items="${schdulIpcrCode}" var="schdulSeInfo" varStatus="status">
					    	<c:if test="${schdulSeInfo.code eq schdulManageVO.schdulIpcrCode}">  
					     	<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
					    	</c:if>
					    	</c:forEach>
					    </td>
					</tr>
					<tr>
						<th width="20%"  class="required_text">일정명</th>
					    <td width="80%">
					      	<c:out value="${fn:replace(schdulManageVO.schdulNm , crlf , '<br/>')}" escapeXml="false" />
					    </td>
					</tr>
					<tr>
						<th  class="required_text">일정 내용</th>
					    <td>
					        <c:out value="${fn:replace(schdulManageVO.schdulCn , crlf , '<br/>')}" escapeXml="false" />
					    </td>
					</tr>
				    <tr> 
				      	<th width="20%"  class="required_text">반복구분</th>
				      	<td width="80%">
				          	<c:forEach items="${reptitSeCode}" var="schdulSeInfo" varStatus="status">
				          	<c:if test="${schdulSeInfo.code eq schdulManageVO.reptitSeCode}">    
				          	<c:out value="${fn:replace(schdulSeInfo.codeNm , crlf , '<br/>')}" escapeXml="false" />
				         	</c:if>
				          	</c:forEach>
				      	</td>
				    </tr>
					<tr> 
					    <th width="20%"  class="required_text">날짜/시간</th>
					    <td width="80%">
					    	${fn:substring(schdulManageVO.schdulBgnde, 0, 4)}-${fn:substring(schdulManageVO.schdulBgnde, 4, 6)}-${fn:substring(schdulManageVO.schdulBgnde, 6, 8)} ${fn:substring(schdulManageVO.schdulBgnde, 8, 10)}시  ${fn:substring(schdulManageVO.schdulBgnde, 10, 12)}분 ~      
					    	${fn:substring(schdulManageVO.schdulEndde, 0, 4)}-${fn:substring(schdulManageVO.schdulEndde, 4, 6)}-${fn:substring(schdulManageVO.schdulEndde, 6, 8)} ${fn:substring(schdulManageVO.schdulEndde, 8, 10)}시  ${fn:substring(schdulManageVO.schdulEndde, 10, 12)}분 
					    </td>
					</tr>
					  
					<!-- 첨부파일 테이블 레이아웃 설정 Start.. -->
					<tr>
					    <th  class="required_text">파일첨부</th>
					    <td>
							<c:import url="/content/files/${schdulManageVO.atchFileId}" />
					     </td>
					</tr>
					<!-- 첨부파일 테이블 레이아웃 End. -->

				</table>
			</div>
			</form:form>
			
			<!-- 버튼 시작(상세지정 style로 div에 지정) -->
            <div class="buttons buttons_padding">
				<!-- 목록/저장버튼 -->
                <div class="buttons_center">
			      	<a href="#" onclick="JavaScript:fn_aram_list(); return false;"><spring:message code="button.list" /></a> 
					<c:if test="${loginVO != null}">
				      	<a href="#" onclick="JavaScript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a> 
					</c:if>
					<c:if test="${loginVO != null}">
					    <a href="#" onclick="JavaScript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a> 
					</c:if>
				</div>
			</div>
			<!-- 버튼 끝 -->							
		</div>	
		
<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("schdulManageVO");
	varForm.action = "${pageContext.request.contextPath}/home/cop/smt/sim/listSchdulMonth.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_edit(){
    var varForm = document.getElementById("schdulManageVO");
	varForm.action = "${pageContext.request.contextPath}/home/cop/smt/sim/editSchdul.do";
    varForm.submit();
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_aram_delete(){
    var varForm = document.getElementById("schdulManageVO");
    
	if (confirm("<spring:message code='common.delete.msg' />"))    {  
    	varForm.action = "${pageContext.request.contextPath}/home/cop/smt/sim/deleteSchdul.do";
    	varForm.submit();
    }
}

</script>
