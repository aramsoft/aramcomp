<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceEdit.jsp
 * @Description : 회의실 수정
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
	<h2>회의실 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mtgPlaceManageVO" method="post" action=""  enctype="multipart/form-data"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mtgPlaceId" />

<input type="hidden" name="checkedMtgPlacesForInsert">

<table class="table-register" summary="회의실  수정">
<caption>회의실 수정</caption>
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="mtgPlaceNm">회의실 명</label>
    	</th>
    	<td colspan ="3">
       		<form:input  path="mtgPlaceNm" title="회의실명" size="80"  />
    	</td>
  	</tr> 
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="aceptncPosblNmpr">수용가능인원</label>
    	</th>
    	<td width="20%">
        	<form:select path="aceptncPosblNmpr" title="수용가능인원">
				<form:option value="5" label="5명" />
				<form:option value="10" label="10명" />
				<form:option value="15" label="15명" />
				<form:option value="20" label="20명" />
				<form:option value="25" label="25명" />
				<form:option value="30" label="30명" />
				<form:option value="50" label="50명" />
				<form:option value="70" label="70명" />
				<form:option value="100" label="100명" />
      		</form:select>  
    	</td>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="opnBeginTm">개방시간</label>
    	</th>
    	<td width="40%">
        	<form:select path="opnBeginTm" title="개방시작시간">
				<form:option value="08:00" label="08:00" />
				<form:option value="09:00" label="09:00" />
				<form:option value="10:00" label="10:00" />
				<form:option value="11:00" label="11:00" />
				<form:option value="12:00" label="12:00" />
				<form:option value="13:00" label="13:00" />
				<form:option value="14:00" label="14:00" />
				<form:option value="15:00" label="15:00" />
				<form:option value="16:00" label="16:00" />
				<form:option value="17:00" label="17:00" />
				<form:option value="18:00" label="18:00" />
				<form:option value="19:00" label="19:00" />
				<form:option value="20:00" label="20:00" />
				<form:option value="21:00" label="21:00" />
      		</form:select>  
			~ 
        	<form:select path="opnEndTm" title="개방종료시간">
				<form:option value="08:00" label="08:00" />
				<form:option value="09:00" label="09:00" />
				<form:option value="10:00" label="10:00" />
				<form:option value="11:00" label="11:00" />
				<form:option value="12:00" label="12:00" />
				<form:option value="13:00" label="13:00" />
				<form:option value="14:00" label="14:00" />
				<form:option value="15:00" label="15:00" />
				<form:option value="16:00" label="16:00" />
				<form:option value="17:00" label="17:00" />
				<form:option value="18:00" label="18:00" />
				<form:option value="19:00" label="19:00" />
				<form:option value="20:00" label="20:00" />
				<form:option value="21:00" label="21:00" />
      		</form:select>  
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="lcSe">위치</label>
    	</th>
    	<td colspan ="3">
	        <form:select path="lcSe" title="위치">
		      	<form:options items="${COM070_lcSe}" itemValue="code" itemLabel="codeNm"/>
	        </form:select>
	        <form:input  path="lcDetail" title="위치상세"  size="70" />
     	</td>
  	</tr>
<!--  첨부파일 테이블 레이아웃 설정 Start.. -->
	<c:choose>
	<c:when test="${mtgPlaceManageVO.atchFileId ne null && mtgPlaceManageVO.atchFileId ne ''}">
	<tr> 
		<th>
			<span class="norequired_icon"></span>
			<label for="atchFileId">이미지 파일목록</label>
		</th>
		<td colspan="3">
			<input type="hidden" name="returnUrl" value="/uss/ion/mtg/detailMtgPlace.do" />
			<c:import url="/content/files/${mtgPlaceManageVO.atchFileId}/editform" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>

  	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="file_1">이미지 파일첨부</label>
		</th>
		<td colspan="3">

			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

		    <div id="file_upload_posbl"  style="display:none;">
	        <table>
				<tr>
				    <td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
				</tr>
				<tr>
				    <td>
				      	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>
			</div>

			<div id="file_upload_imposbl"  style="display:none;">
			    <spring:message code="common.imposbl.fileupload" />
			</div>
	 	</td>
  	</tr>
<!--  첨부파일 테이블 레이아웃 End. -->
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchVO.searchCondition" />
<form:hidden path="searchVO.searchKeyword" />
<form:hidden path="searchVO.pageIndex" />
<form:hidden path="searchVO.recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<table >
	<tr> 
		<td>
		※ 비품체크박스와  수량을 기입하셔야 비품 해당 정보가 저장됩니다.
		</td>
	</tr>
</table>

<table class="table-register" summary="회의실  비품정보 수정">
<caption>회의실  비품정보 수정</caption>
<tr> 
   	<th width="20%">
   		<span class="norequired_icon"></span>
   		<label for="fxtrsForm">비품정보</label>
   	</th>
   	<td width="80%"  align="center">
    	<form:form commandName="fxtrsForm" name="fxtrsForm" method="post" action="#">  
    	<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div> 

		<table class="table-list">
          	<c:forEach var="mtgPlaceFxtrs" items="${mtgPlaceFxtrsList}" varStatus="status">
            	<c:if test="${(status.count-1)%2 == 0}"> 
					<c:out value="<tr>" escapeXml="false"/>
            	</c:if>
				<td>
				   	<input type="checkbox" name="mtgPlaceCheck" style="border:0px;" <c:if test="${(mtgPlaceFxtrs.quantity)> 0}"> checked </c:if> title="체크박스">
				   	<input type="hidden"   name="mtgPlaceId" value="<c:out value='${mtgPlaceFxtrs.mtgPlaceId}'/>">
				   	<input type="hidden"   name="fxtrsCd"    value="<c:out value='${mtgPlaceFxtrs.fxtrsCd}'/>">
				   	<input name="fxtrsNm"  type="text" size="15" value="<c:out value="${mtgPlaceFxtrs.fxtrsNm}"/>" maxlength="100" style="width:100;" title="비품명">
	               	<input name="quantity" type="text" size="4" value="<c:out value="${mtgPlaceFxtrs.quantity}"/>" maxlength="5" style="width:40;" title="비품갯수">개
			    </td> 
            	<c:if test="${(status.count-1)%2 == 1}"> 
				    <c:out value="</tr>" escapeXml="false"/>
            	</c:if>
		  	</c:forEach>
		</table>
		
    	</form:form>
    </td>
</tr>
</table>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="mtgPlaceManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

function fn_aram_list() {
    var varForm = document.getElementById("mtgPlaceManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlace.do";
    varForm.submit();       
}

function fn_aram_update() {
     var varForm = document.getElementById("mtgPlaceManageVO");

     if(!validateMtgPlaceManageVO(varForm)){           
         return;
     }

     var fxtrsForm = document.getElementById("fxtrsForm");
     var checkField = fxtrsForm.mtgPlaceCheck;
     var mtgPlaceId = fxtrsForm.mtgPlaceId;
     var fxtrsCd    = fxtrsForm.fxtrsCd;
     var quantity   = fxtrsForm.quantity;
     var checkMtgPlaces = "";
     var checkedCount = 0;

     if(checkField) {
     	if(checkField.length> 1) {
             for(var i=0; i < checkField.length; i++) {
                 if(checkField[i].checked) {
                 	checkMtgPlaces += ((checkedCount==0? "" : "$")+fxtrsCd[i].value+","+quantity[i].value);
                     checkedCount++;
                 }
             }
         } else {
             if(checkField.checked) {
             	checkMtgPlaces = fxtrsCd.value+","+quantity.value;
             }
         }
     }   
     varForm.checkedMtgPlacesForInsert.value=checkMtgPlaces;
     //alert("checkMtgPlaces:"+checkMtgPlaces);

	if(confirm("<spring:message code='common.update.msg'/>")){
         varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/updateMtgPlace.do";
         varForm.submit();
     }
}

</script>

