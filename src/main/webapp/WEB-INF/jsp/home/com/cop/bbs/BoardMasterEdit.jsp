<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardMasterEdit.jsp
  * @Description : 게시판 정보수정
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
                    <li><strong>게시판생성관리</strong></li>
                </ul>
            </div>
            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
                <div id="search_field_loc"><h2><strong>게시판 정보수정 및 상세보기</strong></h2></div>
			</div>
			
			<form:form modelAttribute="boardMasterVO" action="" method="post">
				<form:hidden path="bbsId" />
				<form:hidden path="bbsTyCode" />
				<form:hidden path="bbsAttrbCode" />
				<form:hidden path="replyPosblAt" />

              	<div id="border" class="modify_user">
                   	<table summary="게시판명,게시판 소개,게시판 유형,게시판 속성,답장가능여부, ..   입니다">
                       	<tr>
                           	<th width="20%"  class="required_text">
            					<label for="bbsNm">게시판명</label>    
					        	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
                           	</th>
                           	<td width="80%"  colspan="3">
	         				 	<form:input title="게시판명입력" path="bbsNm" size="60" maxlength="60" style="width:100%" /> 
	          					<form:errors path="bbsNm" cssClass="error"/>
                           	</td>
                     	</tr>
                       	<tr> 
                           	<th  class="required_text">
                               	<label for="bbsIntrcn">게시판 소개</label>    
					        	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
                           	</th>
                           	<td colspan="3">
			           			<form:textarea title="게시판소개입력" path="bbsIntrcn" cols="75" rows="4" cssStyle="width:100%" />
				           		<form:errors path="bbsIntrcn" cssClass="error"/>
                           	</td>
                       	</tr>
                       	<tr> 
                           	<th width="20%"  class="required_text">
                           		게시판 유형
                           	</th>
				        	<td width="30%">
				        		<c:out value="${boardMasterVO.bbsTyCodeNm}"/>   
				        	</td>
                           
				        	<th width="20%"  class="">
				        		게시판 속성
				        	</th>    
				        	<td width="30%">
				        		<c:out value="${boardMasterVO.bbsAttrbCodeNm}"/>    
				        	</td>    
                       	</tr> 
                         
                       	<tr> 
                           	<th width="20%"  class="">
								 답장가능여부
							</th>
				        	<td>
				            <c:choose>
				                <c:when test="${boardMasterVO.replyPosblAt == 'Y'}">
				                    <spring:message code="button.possible" /> 
				                </c:when>
				                <c:otherwise>
				                    <spring:message code="button.impossible" />
				                </c:otherwise>
				            </c:choose>
				        	</td>
				        	<th width="20%"  class="required_text">
				            	<label for="fileAtchPosblAt">파일첨부가능여부</label>    
					        	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
				        	</th>    
				        	<td width="30%">
				            	<spring:message code="button.possible" /> : <form:radiobutton path="fileAtchPosblAt"  value="Y" id="fileAtchPosblAtY" />&nbsp;
				            	<spring:message code="button.impossible" /> : <form:radiobutton path="fileAtchPosblAt"  value="N" id="fileAtchPosblAtN" />
				            	<form:errors path="fileAtchPosblAt" cssClass="error"/>
				        	</td>    
                       	</tr> 
                         
                       	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="posblAtchFileNumber">첨부가능파일 숫자</label>    
                           	</th>
				        	<td width="30%"  colspan="3">
						     	<form:select title="첨부가능파일 숫자선택" path="posblAtchFileNumber" class="select">
						  		    <form:option value="0" label="--선택하세요--" />
						  		   	<form:option value='1' label="1개" />
						  		   	<form:option value='2' label="2개" />
						  		   	<form:option value='3' label="3개" />
						  	   	</form:select>
				           		<form:errors path="posblAtchFileNumber" cssClass="error"/>
				        	</td>
                       	</tr>   
                       	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="tmplatNm">템플릿 정보</label>    
					        	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
                           	</th>
                           	<td width="30%"  colspan="3">
			         			<form:input path="tmplatNm" size="20" readonly="true" title="템플릿정보입력"/>
			         			<form:hidden path="tmplatId"  />&nbsp;
			         			<a href="#" onclick="fn_aram_get_tmplat(); return false;">
			         				<img src="${pageContext.request.contextPath}/images/home/img_search.gif" width="15" height="15" align="middle" alt="새창">
			         			</a>
					         	<form:errors path="tmplatId" cssClass="error"/>
                           	</td>
                       	</tr>
					    <!-- 2009.06.26 : 2단계 기능 추가  -->
					    <c:if test="${addedOptions == 'true'}">
					    <tr> 
					        <th width="20%"  class="">추가 선택사항</th>
				            <td width="30%"  colspan="3">
				                <select title="추가선택사항선택" name="option" class="select" <c:if test="${boardMasterVO.option != 'na'}">disabled="disabled"</c:if>>
				                    <option value='na' <c:if test="${boardMasterVO.option == 'na'}">selected="selected"</c:if>>---선택하세요--</option>
				                    <option value='' <c:if test="${boardMasterVO.option == ''}">selected="selected"</c:if>>미선택</option>
				                    <option value='comment' <c:if test="${boardMasterVO.option == 'comment'}">selected="selected"</c:if>>댓글</option>
				                    <option value='stsfdg' <c:if test="${boardMasterVO.option == 'stsfdg'}">selected="selected"</c:if>>만족도조사</option>
				               </select>
				                                        ※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함)
				            </td>
					    </tr>
					    </c:if>
					    <!-- 2009.06.26 : 2단계 기능 추가  -->            
					</table>
               	</div>

				<!-- 검색조건 유지 -->
				<form:hidden path="searchCondition" />
				<form:hidden path="searchKeyword" />
				<form:hidden path="pageIndex" />
				<form:hidden path="recordPerPage" />
				<!-- 검색조건 유지 -->
           	</form:form>
 
            <!-- 버튼 시작 -->
            <div class="buttons">
            	<div class="buttons_center">
	            	<a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a>
	            	<a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a>
	            	<a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a>
            	</div>
            </div>
            <!-- 버튼 끝 -->  
            
        </div>  
        
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardMasterVO");
    varForm.action = "${pageContext.request.contextPath}/cop/bbs/listBoardMaster.do";
    varForm.submit();  
}   

function fn_aram_update(){
    var varForm = document.getElementById("boardMasterVO");
    
    if (!validateBoardMasterVO(varForm)){
        return;
    }
    
    if(confirm("<spring:message code='common.update.msg' />")){
    	varForm.action = "${pageContext.request.contextPath}/cop/bbs/updateBoardMaster.do";
    	varForm.submit();                  
    }
}   

function fn_aram_delete(){
    var varForm = document.getElementById("boardMasterVO");
    
    if(confirm("<spring:message code='common.delete.msg' />")){
    	varForm.action = "${pageContext.request.contextPath}/cop/bbs/deleteBoardMaster.do";
    	varForm.submit();  
    }       
}

var gArguments = new Array();

/* ********************************************************
* 템플릿 팝업창열기
******************************************************** */
function fn_aram_get_tmplat(){
    var varForm = document.getElementById("boardMasterVO");
	gArguments["tmplatId"] = varForm.tmplatId;
	gArguments["tmplatNm"] = varForm.tmplatNm;
	
	var url = "/cop/tpl/listTemplatePopup.do?typeFlag=BBS";

	window.open(url, "p_tmplatInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
} 

</script>
