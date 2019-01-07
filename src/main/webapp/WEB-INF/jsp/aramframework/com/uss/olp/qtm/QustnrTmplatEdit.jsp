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
  * @Class Name : QustnrTmplatEdit.jsp
  * @Description : 설문템플릿 수정
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
	<h2>설문템플릿 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="qustnrTmplatManageVO"  action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="qestnrTmplatId" />

<!--  등록  폼 영역  -->
<table class="table-register" summary="수정 을 제공한다.">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="qestnrTmplatTy">템플릿유형</label>
    	</th>
    	<td>
      		<form:input path="qestnrTmplatTy" size="70" cssClass="txInput" title="템플릿유형 입력"/>
      		<form:errors path="qestnrTmplatTy" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="qestnrTmplatImage">템플릿유형</label>&nbsp;&nbsp;&nbsp;<br>이미지정보
    	</th>
    	<td>
    	<table>
    		<tr>
    			<td><input type="file" name="qestnrTmplatImage" id="qestnrTmplatImage" onChange="fnImgChange(this)" title="템플릿유형이미지 첨부"></td>
    			<td>
	     			<c:if test="${qustnrTmplatManageVO.qestnrTmplatImageInfo ne null}">
	      			<c:if test="${qustnrTmplatManageVO.qestnrTmplatImageInfo ne ''}">
	    			<img src="${pageContext.request.contextPath}/uss/olp/qtm/getQustnrTmplatImage.do' />?qestnrTmplatId=${qustnrTmplatManageVO.qestnrTmplatId}" id="IMG_VIEW" name="IMG_VIEW" alt="이미지미리보기" title="이미지미리보기" align="middle">
	    	<!-- onload="if(this.width>65){this.width=65}" -->
	    			</c:if>
					</c:if>
	     		</td>
	     	</tr>
	     	<tr>
	     		<td colspan="2"><font color="red">가로:65px 세로:50px 포멧:JPGE 형식으로로 업로드 해주세요</font></td>
	     	</tr>
	     </table>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="qestnrTmplatCn">템플릿설명</label>
    		<span class="required_icon"></span>
    	</th>
    	<td>
    		<textarea name="qestnrTmplatCn" class="textarea" cols="75" rows="8" title="템플릿설명" style="width:99%;">${qustnrTmplatManageVO.qestnrTmplatCn}</textarea>
    		<form:errors path="qestnrTmplatCn" cssClass="error"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<label for="qestnrTmplatCours">템플릿파일(경로)</label>
    		<span class="required_icon"></span>
    	</th>
    	<td>
    		<form:input path="qestnrTmplatCours" size="10" cssClass="txInput" title="템플릿파일 경로 입력"/>
    		<form:errors path="qestnrTmplatCours" cssClass="error"/>
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
<validator:javascript formName="qustnrTmplatManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
window.onload = function() {
	document.getElementById("qestnrTmplatTy").value = "${qustnrTmplatManageVO.qestnrTmplatTy}";
	document.getElementById("qestnrTmplatCours").value = "${qustnrTmplatManageVO.qestnrTmplatCours}";
};

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
function fn_aram_update(){
    var varForm = document.getElementById("qustnrTmplatManageVO");
    
	if(!validateQustnrTmplatManageVO(varForm)){
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action = "${pageContext.request.contextPath}/uss/olp/qtm/updateQustnrTmplat.do";
		varForm.submit();
	}
}

/* ********************************************************
 * 선택이미지 미리보기
 ******************************************************** */
function fnImgChange(obj){

	if(obj.value != "") {
		if(obj.value.search(/(gif)/)!=-1 || obj.value.search(/(jpg)/)!=-1 || obj.value.search(/(bmp)/)!=-1 || obj.value.search(/(GIF)/)!=-1 || obj.value.search(/(JPG)/)!=-1 ){

			// IE8 일때 가상경로 변경
			if (obj.value.indexOf("\\fakepath\\") < 0) {
				document.getElementById("IMG_VIEW").src = obj.value;
			} else {
		 		   obj.select();
				   document.getElementById("IMG_VIEW").src = document.selection.createRange().text.toString();
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

