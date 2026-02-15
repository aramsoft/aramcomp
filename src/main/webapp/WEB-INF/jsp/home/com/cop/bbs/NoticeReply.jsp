<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : NoticeReply.jsp
  * @Description : 게시물 답글 생성 화면
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
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>

        <div id="content">
            <div id="cur_loc">
                <ul>
                    <li>HOME</li>
                    <li>&gt;</li>
                    <li>알림마당</li>
                    <li>&gt;</li>
                    <li><strong>${boardVO.boardMasterVO.bbsNm}</strong></li>
                </ul>
            </div>
            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
                <div id="search_field_loc"><h2><strong>답글쓰기</strong></h2></div>
            </div>
			<form:form modelAttribute="boardVO" method="post" enctype="multipart/form-data">
				<input type="hidden" name="menuNo" value="${menuNo}"/>
				<form:hidden path="bbsId" />
				<form:hidden path="nttId" />
			
				<form:hidden path="parntsNttId" />
				<form:hidden path="threadGroupNo" />
				<form:hidden path="threadDepth" />
			
				<c:if test="${anonymous != 'true'}">
					<input type="hidden" name="ntcrNm" value="dummy">   <!-- validator 처리를 위해 지정 -->
					<input type="hidden" name="password" value="dummy"> <!-- validator 처리를 위해 지정 -->
				</c:if>
				
                <div class="modify_user">
                    <table>
                        <tr> 
				        	<th width="20%">
				        		<LABEL for="nttSj"><spring:message code="cop.nttSj" /></LABEL>
				        		<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
				        	</th>
				        	<td width="80%"  colspan="3">
				          		<form:input path="nttSj" size="60" maxlength="60" /> 
				          		<form:errors path="nttSj" cssClass="error"/>
				        	</td>
				      	</tr>
					    <tr> 
					        <th>
					        	<label for="nttCn"><spring:message code="cop.nttCn" /></label>
                            	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
                            </th>
					        <td colspan="3">
					          	<textarea id="nttCn" name="nttCn" class="textarea"  cols="75" rows="20"  style="width:99%;"></textarea> 
					          	<form:errors path="nttCn" cssClass="error"/>
					        </td>
					    </tr>
					    
						<c:choose>
						<c:when test="${boardVO.boardMasterVO.bbsAttrbCode == 'BBSA01'}">
					    <tr> 
					        <th><spring:message code="cop.noticeTerm" />
						        <img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
	                        </th>
					        <td colspan="3">
								<form:hidden path="ntceBgnde" />
						    	<c:if test="${!empty boardVO.ntceBgnde}">
					 				<c:set var="ntceBgndeVal" value="${fn:substring(boardVO.ntceBgnde, 0,4)}-${fn:substring(boardVO.ntceBgnde, 4,6)}-${fn:substring(boardVO.ntceBgnde, 6,8)}"/>
					      		</c:if>
							    <input name="ntceBgndeView" type="text" size="10" value="${ntceBgndeVal}"  readOnly title="게시시작일자"
							      	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceBgnde, document.forms[0].ntceBgndeView); return false;">
							    <img src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"
							      	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceBgnde, document.forms[0].ntceBgndeView); return false;" width="15" height="15" alt="달력창팝업버튼이미지">
					              ~
		    					<form:hidden path="ntceEndde" />
						    	<c:if test="${!empty boardVO.ntceEndde}">
					 				<c:set var="ntceBgndeVal" value="${fn:substring(boardVO.ntceEndde, 0,4)}-${fn:substring(boardVO.ntceEndde, 4,6)}-${fn:substring(boardVO.ntceEndde, 6,8)}"/>
					      		</c:if>
							    <input name="ntceEnddeView" type="text" size="10" value="${ntceEnddeVal}"  readOnly title="게시종료일자"
							      	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceEndde, document.forms[0].ntceEnddeView); return false;">
							    <img src="${pageContext.request.contextPath}/images/com/cmm/icon/bu_icon_carlendar.gif"
							      	onClick="javascript:fn_aram_NormalCalendar(document.forms[0].ntceEndde, document.forms[0].ntceEnddeView); return false;" width="15" height="15" alt="달력창팝업버튼이미지">
					            <form:errors path="ntceBgnde" cssClass="error"/>    
					            <form:errors path="ntceEndde" cssClass="error"/>                  
					        </td>
					    </tr>
						</c:when>
						<c:otherwise>
							<form:hidden path="ntceBgnde" value="10000101"/>
							<form:hidden path="ntceEndde" value="99991231"/>
						</c:otherwise>
						</c:choose>
						
					    <c:if test="${boardVO.boardMasterVO.fileAtchPosblAt == 'Y'}">  
					    <tr>
					        <th>
					        	<label for="egovComFileUploader">파일첨부</label>
					        </th>
					        <td colspan="3">
								<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${boardVO.boardMasterVO.posblAtchFileNumber}'/>" />
					            <input name="file_1" id="egovComFileUploader" type="file" />
					            <div id="egovComFileList"></div>
					        </td>
					    </tr>
					    
						<script type="text/javascript">
							fn_init_FileAttachment();
						</script>
						
  						</c:if>
  						
                    </table>    
                </div>

                <!-- 버튼 시작(상세지정 style로 div에 지정) -->
                <div class="buttons buttons_padding">
                  	<!-- 목록/저장버튼  -->
                	<div class="buttons_center">
		     			<c:if test="${editAuthFlag == 'Y'}">
		          			<a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a> 
		      			</c:if>
		      			<a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a> 
					</div>
                </div>
                <!-- 버튼 끝 -->                           

            </form:form>

        </div>  
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("boardVO");
    varForm.action = "${pageContext.request.contextPath}/${jspPrefix}/cop/bbs/listBoardArticle.do";
    varForm.submit();    
}

function fn_aram_insert() {
    var varForm = document.getElementById("boardVO");

    if (!validateBoardVO(varForm)){
        return;
    }
    
    if (confirm("<spring:message code='common.regist.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/${jspPrefix}/cop/bbs/addReplyBoardArticle.do";
    	varForm.submit();                    
    }
}

</script>
