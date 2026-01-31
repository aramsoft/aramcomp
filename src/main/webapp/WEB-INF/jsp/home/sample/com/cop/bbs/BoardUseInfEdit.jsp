<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardUseInfEdit.jsp
  * @Description : 게시판  사용정보  조회화면
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
        <div id="content">
            <div id="cur_loc">
                <ul>
                    <li>HOME</li>
                    <li>&gt;</li>
                    <li>사이트관리</li>
                    <li>&gt;</li>
                    <li><strong>게시판 사용정보 수정</strong></li>
                </ul>
            </div>
            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
                <div id="search_field_loc"><h2><strong>게시판 사용정보 수정</strong></h2></div>
		    </div>
		    
			<form:form modelAttribute="boardUseInfVO" method="post" action="">
				<form:hidden path="bbsId" />
				<form:hidden path="trgetId" />

               	<div class="modify_user">
                    <table summary="게시판명, 커뮤니티/ 동호회명, 사용여부  입니다">
				      	<tr> 
				        	<th scope="col" width="20%"  class="">게시판명</th>
				        	<td width="80%"  colspan="3">
				        		<c:out value="${boardUseInfVO.bbsNm}" />
				        	</td>
				      	</tr>
				      	<tr> 
				        	<th scope="col" width="20%"  class="">커뮤니티/ 동호회명</th>
				        	<td width="80%"  colspan="3">
					        	<c:choose>
					            <c:when test="${not empty boardUseInfVO.cmmntyNm}">
					                <c:out value="${boardUseInfVO.cmmntyNm}" />
					            </c:when>
					            <c:when test="${not empty boardUseInfVO.clbNm}">
					                <c:out value="${boardUseInfVO.clbNm}" />
					            </c:when>
					            <c:otherwise>(시스템  활용)</c:otherwise>
					        	</c:choose>
			        		</td>
			      		</tr>
			      		<tr> 
				        	<th scope="col" width="20%"  class="required_text">
				            	<label for="useAt">사용여부</label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>
				        	<td width="80%"  colspan="3">
				            	<spring:message code="button.use" /> : <input type="radio" name="useAt" class="radio2" value="Y" <c:if test="${boardUseInfVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
				            	<spring:message code="button.notUsed" /> : <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${boardUseInfVO.useAt == 'N'}"> checked="checked"</c:if>>
				            	<form:errors path="useAt" cssClass="error"/> 
				        	</td>   
			      		</tr>
				      	<c:if test="${not empty boardUseInfVO.provdUrl}">
				      	<tr> 
				        	<th width="20%"  class="">제공 URL</th>
				        	<td width="80%"  colspan="3">
				            	<a href="<c:url value="${boardUseInfVO.provdUrl}" />" target="_new">
				                	<c:url value="${boardUseInfVO.provdUrl}" />
				            	</a>
				        	</td>
				      	</tr>
				      	</c:if>
				      	<c:if test="${not empty boardUseInfVO.provdUrl2}">
				      	<tr> 
				        	<th width="20%"  class="">제공 URL2</th>
				        	<td width="80%"  colspan="3">
				            	<a href="<c:url value="${boardUseInfVO.provdUrl2}" />" target="_new">
				                	<c:url value="${boardUseInfVO.provdUrl2}" />
				            	</a>
				        	</td>
				      	</tr>
				      	</c:if>
                   	</table>
               	</div>

				<!-- 검색조건 유지 -->
				<form:hidden path="searchCondition" />
				<form:hidden path="searchKeyword" />
				<form:hidden path="pageIndex" />
				<form:hidden path="recordPerPage" />
				<!-- 검색조건 유지 -->
			</form:form>

            <!-- 버튼 시작(상세지정 style로 div에 지정) -->
            <div class="buttons">
            	<div class="buttons_center">
                    <a href="#" onclick="fn_aram_update(); return false;"><spring:message code="button.save" /></a>
		    		<a href="#" onclick="fn_aram_list(); return false;"><spring:message code="button.list" /></a>
                </div>
            </div>
            <!-- 버튼 끝 --> 
                                      
        </div>  
        
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardUseInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardUseInfVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardUseInf.do";
    varForm.submit();      
}
    
function fn_aram_update(){
    var varForm = document.getElementById("boardUseInfVO");
    
    if (!validateBoardUseInfVO(varForm)){
        return;
    }
    
	if (confirm("<spring:message code='common.update.msg' />"))    {  
    	varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateBoardUseInf.do";
    	varForm.submit();
	}	
}

</script>
