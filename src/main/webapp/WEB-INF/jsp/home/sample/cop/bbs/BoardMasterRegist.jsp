<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardMasterRegist.jsp
  * @Description : 게시판 생성 화면
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
                <div id="search_field_loc"><h2><strong>게시판 생성</strong></h2></div>
            </div>
			<form:form commandName="boardMasterVO" method="post" action="">
				
                <div id="border" class="modify_user">
                    <table summary="게시판명,게시판소개,게시판 유형,게시판 속성,답장가능여부,파일첨부가능여부, ..  입니다">
                        <tr>
                            <th width="20%"  class="required_text">
					        	<label for="bbsNm"><spring:message code="cop.bbsNm" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
					        </th>
					        <td width="80%"  colspan="3">
					            <form:input title="게시판명입력" path="bbsNm" size="60" cssStyle="width:100%" />
					            <form:errors path="bbsNm" cssClass="error"/>
					        </td>
				      	</tr>
				      	<tr> 
				        	<th  class="required_text">
				            	<label for="bbsIntrcn"><spring:message code="cop.bbsIntrcn" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>
				        	<td colspan="3">
				           		<form:textarea title="게시판소개입력" path="bbsIntrcn" cols="75" rows="4" cssStyle="width:100%" />
				           		<form:errors path="bbsIntrcn" cssClass="error"/>
				        	</td>
				      	</tr>
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="bbsTyCode"><spring:message code="cop.bbsTyCode" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>
				        	<td width="30%">
				            	<form:select path="bbsTyCode" title="게시판유형선택">
				                	<form:option value='' label="--선택하세요--" />
				                	<form:options items="${typeList}" itemValue="code" itemLabel="codeNm"/>
				            	</form:select>
				           		<form:errors path="bbsTyCode" cssClass="error"/>
				        	</td>
				        	<th width="20%"  class="required_text">
				            	<label for="bbsAttrbCode"><spring:message code="cop.bbsAttrbCode" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>    
				        	<td width="30%">
				            	<form:select path="bbsAttrbCode" title="게시판속성선택">
				                	<form:option value='' label="--선택하세요--" />
				                	<form:options items="${attrbList}" itemValue="code" itemLabel="codeNm"/>
				            	</form:select>      
				            	<form:errors path="bbsAttrbCode" cssClass="error"/>
				        	</td>    
				      	</tr> 
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="replyPosblAt"><spring:message code="cop.replyPosblAt" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>
				        	<td width="30%">
				            	<spring:message code="button.possible" /> : <form:radiobutton path="replyPosblAt"  value="Y" />&nbsp;
				            	<spring:message code="button.impossible" /> : <form:radiobutton path="replyPosblAt"  value="N"  />
				             	<form:errors path="replyPosblAt" cssClass="error"/>
				        	</td>
				        
				        	<th width="20%"  class="required_text">
				            	<label for="fileAtchPosblAt"><spring:message code="cop.fileAtchPosblAt" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>    
				        	<td width="30%">
				            	<spring:message code="button.possible" /> : <form:radiobutton path="fileAtchPosblAt"  value="Y" 
				               		onclick="document.boardMaster.posblAtchFileNumber.disabled='';" />&nbsp;
				            	<spring:message code="button.impossible" /> : <form:radiobutton path="fileAtchPosblAt"  value="N" 
				               		onclick="document.boardMaster.posblAtchFileNumber.disabled='disabled';" />
				             	<form:errors path="fileAtchPosblAt" cssClass="error"/>
				        	</td>    
				      	</tr> 
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="posblAtchFileNumber"><spring:message code="cop.posblAtchFileNumber" /></label>    
				        	</th>
				        	<td width="30%"  colspan="3">
				            	<form:select path="posblAtchFileNumber" title="첨부가능파일 숫자선택">
				               		<form:option value="0"  label="---선택하세요--" />
				               		<form:option value='1'>1개</form:option>
				               		<form:option value='2'>2개</form:option>
				               		<form:option value='3'>3개</form:option>
				           		</form:select>
				           		<form:errors path="posblAtchFileNumber" cssClass="error"/>
				        	</td>
				      	</tr>   
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="tmplatNm"><spring:message code="cop.tmplatId" /></label>    
						        <img src="${pageContext.request.contextPath}/images/home/sample/required.gif" width="15" height="15" alt="required" />
				        	</th>
				        	<td width="30%"  colspan="3">
				         		<form:input path="tmplatNm" size="20" readonly="true" title="템플릿정보입력"/>
				         		<form:hidden path="tmplatId"  />&nbsp;
				         		<a href="#" onclick="fn_aram_get_tmplat(); return false;" style="selector-dummy: expression(this.hideFocus=false);">
				         			<img src="${pageContext.request.contextPath}/images/home/sample/img_search.gif" width="15" height="15" align="middle" alt="새창" />
				         		</a>
				         		<form:errors path="tmplatId" cssClass="error"/>
				        	</td>
				      	</tr>
				        <!-- 2009.06.26 : 2단계 기능 추가  -->
				        <c:if test="${addedOptions == 'true'}">
				        <tr> 
				            <th width="20%"  class="">
				            	<label for="option">추가 선택사항</label>
				            </th>
				            <td width="30%"  colspan="3">
				                <form:select path="option" title="추가선택사항선택">
				                   <form:option value=""  label="미선택" />
				                   <form:option value='comment'>댓글</form:option>
				                   <form:option value='stsfdg'>만족도조사</form:option>
				               </form:select>
				            </td>
				        </tr>          
				        </c:if>
				        <!-- 2009.06.26 : 2단계 기능 추가  -->       
				    </table>
                </div>
            </form:form>
 
            <!-- 버튼 시작(상세지정 style로 div에 지정) -->
            <div class="buttons">
            	<div class="buttons_center">
	  				<a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a>
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

function fn_aram_insert(){
    var varForm = document.getElementById("boardMasterVO");
    
    if (!validateBoardMasterVO(varForm)){
        return;
    }

    if (confirm("<spring:message code='common.regist.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/cop/bbs/insertBoardMaster.do";
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
