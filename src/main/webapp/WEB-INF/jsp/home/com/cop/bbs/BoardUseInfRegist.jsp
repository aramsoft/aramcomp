<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : BoardUseInfRegist.jsp
  * @Description : 게시판  사용정보  등록화면
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @since 2014.11.11
  *  @version 1.0
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
                    <li><strong>일정관리 상세보기</strong></li>
                </ul>
            </div>
            <!-- 검색 필드 박스 시작 -->
            <div id="search_field">
                <div id="search_field_loc"><h2><strong>게시판 사용등록</strong></h2></div>
            </div>
            
			<form:form modelAttribute="boardUseInfVO" method="post">
				<input type="hidden" name="menuNo" value="${menuNo}" />

                <div class="modify_user">
                    <table summary="게시판명, 커뮤니티 동호회 정보  입니다">
					    <tr>   
					        <th width="30%"  class="required_text">
					        	<spring:message code="cop.bbsNm" />
						        <img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
					        </th>
					        <td width="70%"  colspan="3">
					          	<input name="bbsId" type="hidden" /> 
					          	<input name="bbsNm" type="text" size="40" value=""  maxlength="40" title="게시판명" readonly /> 
					          	&nbsp;<a href="#" onclick="fn_aram_get_board(); return false;" style="selector-dummy: expression(this.hideFocus=false);"><img src="${pageContext.request.contextPath}/images/home/img_search.gif"
					                    width="15" height="15" align="middle" alt="새창" /></a>
					        	<form:errors path="bbsId" cssClass="error"/>                   
					        </td>
					    </tr>
					    <tr> 
					        <th width="30%"  class="required_text">
					            <label for="trgetType">
					                <spring:message code="cop.trgetNm" />
					            </label>    
						        <img src="${pageContext.request.contextPath}/images/home/required.gif" width="15" height="15" alt="required" />
					        </th>
					        <td width="70%"  colspan="3">
					           	<select name="trgetType" class="select" title="" onChange="javascript:fn_aram_selectTargetType(this)">
					               	<option selected value=''>--선택하세요--</option>
					               	<!-- option value="CMMNTY">커뮤니티</option-->
					               	<!-- option value="CLUB">동호회</option-->              
					               	<option value="SYSTEM">시스템</option>            
					           	</select>
					  	      	<form:hidden path="trgetId" />
         						<input type="text" name="trgetNm" value=""  size="40" title="" readOnly />
					           	<form:errors path="trgetId"  cssClass="error"/>
					        </td>
					    </tr>
                    </table>
                </div>

                <!-- 버튼 시작(상세지정 style로 div에 지정) -->
                <div class="buttons">
            		<div class="buttons_center">
                        <a href="#" onclick="JavaScript:fn_aram_insert(); return false;"><spring:message code="button.create" /></a>
                        <a href="#" onclick="JavaScript:fn_aram_list(); return false;"><spring:message code="button.list" /></a>
                    </div>
                </div>
                <!-- 버튼 끝 -->                           
            </form:form>

        </div>  
        
<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="boardUseInfVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

function fn_aram_list(){
    var varForm = document.getElementById("boardUseInfVO");
    varForm.action = "${pageContext.request.contextPath}/home/cop/bbs/listBoardUseInf.do";
    varForm.submit();
}

function fn_aram_insert(){
    var varForm = document.getElementById("boardUseInfVO");
    
    if (!validateBoardUseInfVO(varForm)){
        return;
    }
    
    if (confirm("<spring:message code='common.regist.msg' />")) {
    	varForm.action = "${pageContext.request.contextPath}/home/cop/bbs/insertBoardUseInf.do";
    	varForm.submit();      
    }
}

function fn_aram_select_targetType(obj) {
    var varForm = document.getElementById("boardUseInfVO");

	var _strType = obj.value;
	if (_strType == 'CMMNTY') {
		fn_aram_get_cmmnty();
	} else if (_strType == 'CLUB') {
		fn_aram_get_club();
	} else {
		varForm.trgetId.value = "SYSTEM_DEFAULT_BOARD";
		varForm.trgetNm.value = "시스템 활용";
	}
}

var gArguments = new Array();

/* ********************************************************
* 게시판 팝업창열기
******************************************************** */
function fn_aram_get_board(){
    var varForm = document.getElementById("boardUseInfVO");
	gArguments["bbsId"] = varForm.bbsId;
	gArguments["bbsNm"] = varForm.bbsNm;
	
	var url = "/home/cop/bbs/listBoardMasterPopup.do";

	window.open(url, "p_boardInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_get_cmmnty(){
    var varForm = document.getElementById("boardUseInfVO");
	gArguments["trgetId"] = varForm.trgetId;
	gArguments["trgetNm"] = varForm.trgetNm;
	
	var url = "/home/cop/cmy/listCommunityPopup.do";

	window.open(url, "p_cmmntyInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

function fn_aram_get_club(){
    var varForm = document.getElementById("boardUseInfVO");
	gArguments["trgetId"] = varForm.trgetId;
	gArguments["trgetNm"] = varForm.trgetNm;
	
	var url = "/home/cop/clb/listClubPopup.do";

	window.open(url, "p_clubInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}


</script>
