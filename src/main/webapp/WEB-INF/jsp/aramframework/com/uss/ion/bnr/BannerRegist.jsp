<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : BannerRegist.jsp
 * @Description : 배너 등록
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
	<h2>배너 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="bannerVO" method="post" action="" enctype="multipart/form-data"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="bannerImage">
<input type="hidden" name="bannerId">

<table class="table-register" summary="배너정보를 입력하는 항목">
<caption>배너 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="bannerNm">배너명</label>
    	</th>
    	<td width="80%">
        	<form:input path="bannerNm" title="배너명" maxLength="10" size="30" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="linkUrl">링크URL</label>
    	</th>
    	<td>
    		<form:input path="linkUrl" title="링크URL" maxLength="255" size="50" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="egovComFileUploader">배너이미지</label>
    	</th>
    	<td>
			<input type="hidden" name="posblAtchFileNumber" value="1">
        	<input type="file" name="file_1" id="egovComFileUploader" title="배너이미지" onchange="javascript:fn_aram_change_image();">
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
    		<label for="bannerDc">배너설명</label>
    	</th>
    	<td>
    		<form:input path="bannerDc" title="배너설명" maxLength="100" size="80" />
    	</td>
  	</tr>  
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="sortOrdr">정렬순서</label>
    	</th>
    	<td>
    		<form:input path="sortOrdr" title="정렬순서" maxLength="5" size="10" />
    		<form:errors path="sortOrdr" cssClass="error"/>
    	</td>
  	</tr>   
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="reflctAt">반영여부</label>
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
    		<label for="regDate">등록일시</label>
    	</th>
    	<td>
     		<form:input path="regDate" title="등록일시" maxLength="50" size="20" class="readOnlyClass" readonly="true" />
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="bannerVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("bannerVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/listBanner.do";
    varForm.submit();       
}

function fn_aram_insert() {
    var varForm = document.getElementById("bannerVO");
    
    if(!validateBannerVO(varForm)){           
        return;
    }
    
    if(varForm.bannerImage.value == '') {
        alert("배너이미지는 필수 입력값입니다.");
        return;
    }
    
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/bnr/insertBanner.do";
        varForm.submit();
    }
}

function fn_aram_change_image() {
	var varForm = document.getElementById("bannerVO");
	varForm.bannerImage.value = varForm.file_1.value;
}

</script>
