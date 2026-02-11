<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : TemplateRegist.jsp
  * @Description : 템플릿 속성 등록화면
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
                    <li><strong>게시판템플릿관리</strong></li>
                </ul>
            </div>
            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
                <div id="search_field_loc"><h2><strong>템플릿 등록</strong></h2></div>
            </div>
			<form:form modelAttribute="templateInfVO" action ="" method="post">

                <div class="modify_user">
                    <table>
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="tmplatNm">
				                <spring:message code="cop.tmplatNm" />
				            	</label>    
				            	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required"/>
				        	</th>
				        	<td width="80%">
				          		<form:input path="tmplatNm" size="60" value="" maxlength="60" style="width:90%" title="템플릿명" />
				          		<form:errors path="tmplatNm" cssClass="error"/> 
				        	</td>
				      	</tr>
				      
				      	<tr> 
				        	<th  class="required_text">
				            	<label for="tmplatSeCode">  
				                	<spring:message code="cop.tmplatSeCode" />
				            	</label>    
                               	<img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required"/>
				        	</th>
				        	<td>
				        		<select name="tmplatSeCode" class="select" onchange="fn_aram_select_tmplatType(this)" id="tmplatSeCode" title="템플릿구분">
				               		<option selected value=''>--선택하세요--</option>
				            		<c:forEach var="result" items="${COM005_tmplatSe}" varStatus="status">
				                		<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
				            		</c:forEach>    
				        		</select>&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
				           		<form:errors path="tmplatSeCode" cssClass="error"/>
				        	</td>
				      	</tr> 
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="tmplatCours">   
				                <spring:message code="cop.tmplatCours" />
				            	</label>    
                               <img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required"/>
				        	</th>
				        	<td width="80%">
				          		<form:input path="tmplatCours" size="60" value="" maxlength="60" style="width:90%" title="템플릿경로" />
				          		<form:errors path="tmplatCours" cssClass="error"/> 
				        	</td>
				     	 </tr>
				      	<tr> 
				        	<th width="20%"  class="required_text">
				            	<label for="useAt"> 
				                <spring:message code="cop.useAt" />
				            	</label>    
                               <img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required"/>
				        	</th>
				        	<td width="80%">
				           	 	Y : <input type="radio" name="useAt" class="radio2" value="Y"  checked>&nbsp;
				            	N : <input type="radio" name="useAt" class="radio2" value="N">
				            	<form:errors path="useAt" cssClass="error"/>
				        	</td>
				      	</tr>  
                   	</table>
                </div>
			</form:form>


            <!-- 버튼 시작(상세지정 style로 div에 지정) -->
            <div class="buttons buttons_padding">
                <!-- 목록/저장버튼  -->
                <div class="buttons_center">
                   	<a href="#" onclick="fn_aram_insert(); return false;"><spring:message code="button.save" /></a> 
                   	<a href="#" onclick="fn_aram_list(); return false;"><spring:message code="button.list" /></a>
                   	<a href="#" onclick="fn_aram_preview();; return false;" title="새창"><spring:message code="button.preview" /></a>
				</div>
            </div>
            <!-- 버튼 끝 -->                           

        </div>  
        
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="templateInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("templateInfVO");
    varForm.action = "${pageContext.request.contextPath}/cop/tpl/listTemplate.do";
    varForm.submit();  
}

function fn_aram_insert(){
    var varForm = document.getElementById("templateInfVO");
    
    if (!validateTemplateInfVO(varForm)){
        return;
    }
    
    if (confirm("<spring:message code='common.regist.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/cop/tpl/insertTemplate.do";
    	varForm.submit();
    }
}

function fn_aram_select_tmplatType(){
    var varForm = document.getElementById("templateInfVO");
	if (varForm.tmplatSeCode.value == 'TMPT01') {
		document.getElementById('sometext').innerHTML = "게시판 템플릿은 CSS만 가능합니다.";
	} else if (varForm.tmplatSeCode.value == '') {
		document.getElementById('sometext').innerHTML = "";
	} else {
		document.getElementById('sometext').innerHTML = "템플릿은 JSP만 가능합니다.";
	}
}

function fn_aram_preview() {
    var varForm = document.getElementById("templateInfVO");
    
    var url = varForm.tmplatCours.value;

    var target = "";

    if (varForm.tmplatSeCode.value == 'TMPT01') {
        target = "${pageContext.request.contextPath}/cop/bbs/previewBoardList.do";
        width = "1024";
    } else {
    	alert('<spring:message code="cop.tmplatCours" /> 지정 후 선택해 주세요.');
    }

    if (target != "") {
        window.open(target + "?searchWrd="+url, "preview", "width=" + width + "px, height=500px;");
    }
}

</script>
