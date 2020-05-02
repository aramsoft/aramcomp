<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : StplatRegist.jsp
  * @Description : 약관 등록
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
	<h2>약관 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
			<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
		</span>
	</div>
</div>

<!-- 등록  폼 영역  -->
<form:form modelAttribute="stplatManageVO" action="" method="post"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

<table class="table-register" summary="이 표는 약관내용 대상 정보를 제공하며, 이용약관명, 이용약관내용, 정보제공동의내용 정보로 구성되어 있습니다 .">
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="useStplatNm">이용약관명</label>
    	</th>
    	<td width="80%">
        	<form:input path="useStplatNm"  title="이용약관명 입력" size="70" maxlength="70" />
    		<form:errors path="useStplatNm" cssClass="error"/>        
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="useStplatCn">이용약관내용</label>
    	</th>
    	<td>   
      		<form:textarea path="useStplatCn" class="textarea" title="이용약관내용" cols="90" rows="20"  />              
      		<form:errors path="useStplatCn" cssClass="error"/>                                
    	</td>
  	</tr> 
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="infoProvdAgreCn">정보제공동의내용</label>
    	</th>
    	<td> 
      		<form:textarea path="infoProvdAgreCn" class="textarea" title="정보제공동의내용" cols="90" rows="20" />            
      		<form:errors path="infoProvdAgreCn" cssClass="error"/>                               
    	</td>
  	</tr> 
</table>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="stplatManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	// 첫 입력란에 포커스..
    var varForm = document.getElementById("stplatManageVO");
    varForm.useStplatNm.focus();
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {

    var varForm = document.getElementById("stplatManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/listStplat.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("stplatManageVO");
    
	if (!validateStplatManageVO(varForm)) {
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/sam/stp/insertStplat.do";
		varForm.submit();
	} 
}

</script>
