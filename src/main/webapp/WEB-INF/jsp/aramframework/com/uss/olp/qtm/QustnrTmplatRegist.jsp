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
  * @Class Name : QustnrTmplatRegist.jsp
  * @Description : 설문템플릿 등록
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
	<h2>설문템플릿 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrTmplatManageVO"  action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrTmplatId" />

<!-- 등록  폼 영역  -->
<table class="table-register" summary="등록 을 제공한다.">
<caption>등록 을 제공한다</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="qestnrTmplatTy">템플릿유형</label>
    	</th>
    	<td>
     		<form:input path="qestnrTmplatTy" size="70" cssClass="txInput" maxlength="100" title="템플릿유형 입력" />
      		<form:errors path="qestnrTmplatTy" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		템플릿유형&nbsp;&nbsp;&nbsp;<br>이미지
    	</th>
    	<td>
    	<table summary="템플릿 이미지를 제공한다.">
    	<caption>템플릿 이미지를 제공한다</caption>
    		<tr>
    			<th><input type="file" id="qestnrTmplatImage" name="qestnrTmplatImage" onChange="fnImgChange(this)" title="템플릿유형이미지 선택"></th>
    			<td>
	     			<div id="DIV_IMG_VIEW" style="display:none;">
	     			<img src="" name="IMG_VIEW" id="IMG_VIEW" align="middle" alt="이미지미리보기" title="이미지미리보기">
	     	<!-- onload="if(this.width>65){this.width=65}" -->
	     			</div>
	     		</td>
	     	</tr>
	     	<tr>
	     		<td colspan="2"><font color="red">가로:65px 세로:50px 포멧:GIF/JPG 형식으로로 업로드 해주세요</font></td>
	     	</tr>
	    </table>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnrTmplatCn">템플릿설명</label>
    	</th>
    	<td>
    		<form:textarea path="qestnrTmplatCn" rows="8" cols="75" cssClass="txArea" title="템플릿설명 입력"/>
    		<form:errors path="qestnrTmplatCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="qestnrTmplatCours">템플릿파일(경로)</label>
    	</th>
    	<td>
      		<form:input path="qestnrTmplatCours" size="73" cssClass="txInput" maxlength="100" title="템플릿경록 입력"/>
      		<form:errors path="qestnrTmplatCours" cssClass="error"/>
    	</td>
  	</tr>
</table>

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="qustnrTmplatManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/listQustnrTmplat.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    
	if(!validateQustnrTmplatManageVO(varForm)){
		return;
	}
	
	if(varForm.qestnrTmplatImage.value == ""){
	 	alert("템플릿유형 이미지를 선택해주세요!");
	 	varForm.focus();
		return;
	}
	
	if(confirm("<spring:message code='common.regist.msg'/>")){
	    varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/insertQustnrTmplat.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 선택이미지 미리보기
 ******************************************************** */
function fnImgChange(obj){

	if(obj.value != "") {
		if(obj.value.search(/(gif)/)!=-1 || obj.value.search(/(jpg)/)!=-1 || obj.value.search(/(bmp)/)!=-1 || obj.value.search(/(GIF)/)!=-1 || obj.value.search(/(JPG)/)!=-1 ){
			document.getElementById("DIV_IMG_VIEW").style.display = "";

			// IE8 일때 가상경로 확인
			if (obj.value.indexOf("\\fakepath\\") < 0) {
				document.getElementById("IMG_VIEW").src = obj.value;
				}
			// IE8 일때 가상경로 변경
			else {
		 		 obj.select();
				 document.getElementById("IMG_VIEW").src = document.selection.createRange().text.toString();
			alert(document.getElementById("IMG_VIEW").src);
				 obj.blur();
		    	 }
		}else{
		   alert('GIF/JPG 형식의 이미지만 업로드 가능합니다!');
		   obj.select();
		   document.execCommand('Delete');
		   obj.focus();
		   return;
	 	}

	}{return;}
}

</script>

