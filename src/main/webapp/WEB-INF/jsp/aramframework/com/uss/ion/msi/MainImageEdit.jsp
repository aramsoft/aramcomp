<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MainImageEdit.jsp
 * @Description : 메인화면이미지 수정
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
	<h2>메인화면이미지 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mainImageVO" method="post" action="" enctype="multipart/form-data"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<table class="table-register" summary="메인화면이미지를 수정한다.">
<caption>메인화면이미지 수정</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		이미지ID
    	</th>
    	<td>
    		<form:input path="imageId" title="이미지ID" size="20" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		이미지명
    	</th>
    	<td>
     		<form:input path="imageNm" title="이미지명" maxLength="10" size="20" />
    		<form:errors path="imageNm" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		이미지
    	</th>
    	<td>
			<input type="hidden" name="posblAtchFileNumber" value="1">
        	<input type="file" name="file_1" id="egovComFileUploader" title="이미지">&nbsp;
        	<form:input path="image" title="이미지" maxLength="30" size="30" readonly="true" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		이미지설명
    	</th>
    	<td>
    		<form:input path="imageDc" title="이미지설명" maxLength="100" size="80" />
    	</td>
  	</tr> 
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		반영여부
    	</th>
    	<td>
      		<form:select path="reflctAt" title="반영여부">
          		<form:option value="Y" label="Y" />
          		<form:option value="N" label="N" />
      		</form:select>
   		</td> 
  	</tr>  
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
    		등록일시
    	</th>
    	<td>
    		<form:input path="regDate" title="등록일시" maxLength="50" size="20" readonly="true" />
    	</td>
  	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="mainImageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("mainImageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/msi/listMainImage.do";
    varForm.submit();       
}

function fn_aram_update() {
    var varForm = document.getElementById("mainImageVO");
    
    if(!validateMainImageVO(varForm)){           
        return;
    }

	if(confirm("<spring:message code='common.update.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/msi/updateMainImage.do";
        varForm.submit();
    }
}

function fn_aram_delete() {
    var varForm = document.getElementById("mainImageVO");
    
	if(confirm("<spring:message code='common.delete.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/msi/deleteMainImage.do";
        varForm.submit();
    }
}

</script>
