<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : MtgPlaceRegist.jsp
 * @Description : 회의실 등록
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
	<h2>회의실 등록</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_reset(); return false;">초기화</a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="mtgPlaceManageVO" method="post" action="" enctype="multipart/form-data"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input type="hidden" name="checkedMtgPlacesForInsert">

<table class="table-register" summary="회의실  등록">
<caption>회의실 등록</caption>
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="mtgPlaceNm">회의실 명</label>
    	</th>
    	<td colspan ="3">
       		<form:input  path="mtgPlaceNm" title="회의실명" size="80" />
    	</td>
  	</tr> 
  	<tr> 
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="aceptncPosblNmpr">수용가능인원</label>
    	</th>
    	<td width="20%">
        	<select name="aceptncPosblNmpr" title="수용가능인원">
            	<option value="0">선택하세요</option>
	       		<option value="5" selected>5명</option>
	       		<option value="10">10명</option>
	       		<option value="15">15명</option>
	       		<option value="20">20명</option>
	       		<option value="25">25명</option>
	       		<option value="30">30명</option>
	       		<option value="50">50명</option>
	       		<option value="70">70명</option>
	       		<option value="100">100명</option>
      		</select>  
    	</td>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="opnBeginTm">개방시간</label>
    	</th>
    	<td width="40%"> 
        	<select name="opnBeginTm" title="개방시작시간">
	            <option value="">선택하세요</option>
		       	<option value="08:00" selected>08:00</option>
		       	<option value="09:00">09:00</option>
		       	<option value="10:00">10:00</option>
		       	<option value="11:00">11:00</option>
		       	<option value="12:00">12:00</option>
		       	<option value="13:00">13:00</option>
		       	<option value="14:00">14:00</option>
		       	<option value="15:00">15:00</option>
		       	<option value="16:00">16:00</option>
		       	<option value="17:00">17:00</option>
		       	<option value="18:00">18:00</option>
		       	<option value="19:00">19:00</option>
		       	<option value="20:00">20:00</option>
		       	<option value="21:00">21:00</option>
      		</select>  
			~        	
			<select name="opnEndTm" title="개방종료시간">
	            <option value="">선택하세요</option>
		       	<option value="08:00">08:00</option>
		       	<option value="09:00">09:00</option>
		       	<option value="10:00">10:00</option>
		       	<option value="11:00">11:00</option>
		       	<option value="12:00">12:00</option>
		       	<option value="13:00">13:00</option>
		       	<option value="14:00">14:00</option>
		       	<option value="15:00">15:00</option>
		       	<option value="16:00">16:00</option>
		       	<option value="17:00">17:00</option>
		       	<option value="18:00">18:00</option>
		       	<option value="19:00">19:00</option>
		       	<option value="20:00">20:00</option>
		       	<option value="21:00" selected>21:00</option>
      		</select> 
    	</td>
  	</tr>
  	<tr> 
    	<th>
    		<span class="required_icon"></span>
    		<label for="lcSe">위치</label>
    	</th>
    	<td colspan="3">
	        <form:select path="lcSe" title="위치선택">
		      	<form:options items="${COM070_lcSe}" itemValue="code" itemLabel="codeNm"/>
	      	</form:select>
          	<form:input  path="lcDetail" title="위치상세" size="70"/>
     	</td>
  	</tr>
  	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="txtComFileUploader">이미지 파일첨부</label>
		</th>
		<td colspan="3">
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	    	<table>
				<tr>
					<td><input name="txtComFileUploader" id="egovComFileUploader" type="file"  title="첨부파일"/></td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>		      
	 	</td>
  	</tr>
</table>

</form:form>

<table>
	<tr> 
		<td>
		※ 비품체크박스와  수량을 기입하셔야 비품 해당 정보가 저장됩니다.
		</td>
	</tr>
</table>

<table class="table-register" summary="회의실  비품정보 등록">
<caption>회의실  비품정보 등록</caption>
<tr> 
   	<th width="20%" >
   		<span class="norequired_icon"></span>
   		<label for="fxtrsForm">비품정보</label>
   	</th>

   	<td width="80%" align="center">
    	<form:form commandName="fxtrsForm" name="fxtrsForm" method="post" action="#">  
		<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>

		<table class="table-list" >
          	<c:forEach var="mtgPlaceFxtrs" items="${mtgPlaceFxtrsList}" varStatus="status">
           		<c:if test="${(status.count-1)%2 == 0}"> 
					<c:out value="<tr>" escapeXml="false"/>
           		</c:if>
				<td>
				   	<input type="checkbox" name="mtgPlaceCheck" style="border:0px;" <c:if test="${(mtgPlaceFxtrs.quantity)> 0}"> checked </c:if>  title="체크박스">
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

<!-- 첨부파일 업로드 가능화일 설정 Start..-->
<script type="text/javascript">
	fn_init_FileAttachment();
</script>
<!--  첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

function fn_aram_reset() {
	var varForm = document.getElementById("mtgPlaceManageVO");
	var fxtrsForm = document.getElementById("fxtrsForm");
	var checkField = fxtrsForm.mtgPlaceCheck;
	var mtgPlaceId = fxtrsForm.mtgPlaceId;
	var fxtrsCd    = fxtrsForm.fxtrsCd;
	var quantity   = fxtrsForm.quantity;
	
	varForm.mtgPlaceNm.value       = "";
	varForm.aceptncPosblNmpr.value = 5;
	varForm.opnBeginTm.value       = "08:00";
	varForm.opnEndTm.value         = "21:00";
	varForm.lcDetail.value         = "";
	varForm.lcSe[0].selected       = true;

	if(checkField){
		if(checkField.length> 1){
	        for(var i=0; i < checkField.length; i++){
	        	checkField[i].checked = false;
	            quantity[i].value = 0;
	        }
	    }else{
	        checkField.checked = false;
	        quantity.value = 0;
	    }
	} 
}

function fn_aram_list() {
    var varForm = document.getElementById("mtgPlaceManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/listMtgPlace.do";
    varForm.submit();       
}

 /* ********************************************************
  * 멀티입력 처리 함수
  ******************************************************** */
function fn_aram_insert(){
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

  	if(varForm.opnBeginTm.value == ""){
	  	alert("개방 오픈 시간을 선택하세요");
	  	return;
  	}
  	if(varForm.opnEndTm.value == ""){
	 	 alert("개방 종료 시간을 선택하세요");
	  	return;
  	}
    if(varForm.opnBeginTm.value.substring(0,2)>= varForm.opnEndTm.value.substring(0,2)){
        alert("개방오픈시간이 개방종료시간보다 늦거나 같습니다. 개방시간을 확인하세요.");
        return;
  	}

    if(varForm.opnBeginTm.value.substring(0,2)>= varForm.opnEndTm.value.substring(0,2)){
        alert("개방오픈시간이 개방종료시간보다 늦거나 같습니다. 개방시간을 확인하세요.");
        return;
  	}
     
    if(checkField){
     	if(checkField.length> 1){
            for(var i=0; i < checkField.length; i++){
                if(checkField[i].checked) {
                 	checkMtgPlaces += ((checkedCount==0? "" : "$")+fxtrsCd[i].value+","+quantity[i].value);
                    checkedCount++;
                }
            }
        }else{
            if(checkField.checked){
             	checkMtgPlaces = fxtrsCd.value+","+quantity.value;
            }
        }
    }   
    varForm.checkedMtgPlacesForInsert.value=checkMtgPlaces;

	if(confirm("<spring:message code='common.regist.msg'/>")){
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/mtg/insertMtgPlace.do";
	    varForm.submit();
   	}
}	
  	
</script>


