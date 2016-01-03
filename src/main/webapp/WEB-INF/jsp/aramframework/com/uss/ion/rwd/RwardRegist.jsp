<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : RwardRegist.jsp
 * @Description : 포상 신청
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
	<h2>포상 신청</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_insert(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<!-- 상단타이틀(파일첨부를 위한 폼명 및 Enctype 설정 -->
<form:form commandName="rwardManageVO" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<div style="margin-top:10px;"></div>
<div class="content_title">
	<h2>포상자</h2>
</div>

<table class="table-register" summary="포상자 정보">
<caption>포상자정보</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="rwardManId">포상자</label>
    	</th>          
    	<td width="30%">
        	<input name="rwardManNm" id="rwardManNm" type="text" size="20" value="" title="포상자" readonly>
        	<form:hidden path="rwardManId"/>
	    	<span class="link">
	    	<a href="/uss/ion/ism/listSanctnerPopup.do" target="_blank"  title="새 창으로 이동"  onClick="fn_aram_get_rward('포상자', 'rwardManId', '', 'rwardManNm', 'rwardManOrgnztNm');return false;">
	    		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" alt="검색"  width="15" height="15">
	    	</a>
	    	</span>
    	</td>
    	<th width="20%">
    		<span class="norequired_icon"></span>
    		<label for="rwardManOrgnztNm">소속 </label>
    	</th>          
    	<td width="30%">
    		<input name="rwardManOrgnztNm" id="rwardManOrgnztNm" type="text" size="25" value="" title="소속" readonly>
    	</td>
  	</tr> 
</table>

<div style="margin-top:10px;"></div>

<table class="table-register" Summary="포상 등록">
<caption>포상 등록</caption>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="rwardCd">포상구분</label>
    	</th>          
    	<td colspan="3">
      		<form:select path="rwardCd" title="포상구분">
	      		<form:options items="${COM055_rward}" itemValue="code" itemLabel="codeNm"/>
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="rwardNm">포상명</label>
    	</th>          
    	<td width="30%">
    		<form:input  path="rwardNm" title="포상명"/>
    	</td>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="rwardDe">포상일</label>
    	</th>          
    	<td width="30%">
      		<form:hidden path="rwardDe" />
	    	<c:if test="${!empty rwardManageVO.rwardDe}">
 				<c:set var="rwardDeVal" value="${fn:substring(rwardManageVO.rwardDe, 0,4)}-${fn:substring(rwardManageVO.rwardDe, 4,6)}-${fn:substring(rwardManageVO.rwardDe, 6,8)}"/>
      		</c:if>
      		<input name="rwardDeView" id="rwardDeView" type="text" size="10" title="포상일자" value="${rwardDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].rwardDe, document.forms[0].rwardDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="pblenCn">공적사항</label>
    	</th>          
    	<td colspan="3">
      		<form:textarea path="pblenCn" rows="4" cols="70" cssClass="txArea" title="공적사항"/>
      		<form:errors path="pblenCn" cssClass="error"/>
    	</td>
  	</tr>

	<!-- 첨부파일 테이블 레이아웃 설정 Start..-->
  	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="file_1">첨부파일</label>
		</th>
		<td colspan="3">
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	    	<table>
				<tr>
					<td>
						<input name="file_1" id="egovComFileUploader" type="file"  title="첨부파일"/>
					</td>
				</tr>
				<tr>
					<td>
				    	<div id="egovComFileList"></div>
				    </td>
				</tr>
	   	    </table>		      
	 	</td>
  	</tr>
	<!-- 첨부파일 테이블 레이아웃 End.-->
</table>

<!-- 결재권자 지정 Include -->
<jsp:include page="/WEB-INF/jsp/aramframework/com/uss/ion/ism/InfrmlSanctnRegist.jsp" flush="true"/> 
<!-- //결재권자 지정 Include -->

</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="rwardManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>

<!-- 첨부파일 업로드 가능화일 설정 Start..-->  
<script type="text/javascript">
	fn_init_FileAttachment();
</script> 
<!-- 첨부파일 업로드 가능화일 설정 End.-->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("rwardManageVO");
	varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/listRward.do";
	varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_insert() {
    var varForm = document.getElementById("rwardManageVO");
    
   	if(!validateRwardManageVO(varForm)){           
        return;
   	}
   	
	if(confirm("<spring:message code='common.regist.msg'/>")){
        varForm.action = "${pageContext.request.contextPath}/uss/ion/rwd/insertRward.do";
        varForm.submit();
    }
}
		
var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
* ******************************************************** */
function fn_aram_get_rward(strTitle, uniqId, emplNo, emplyrNm, orgnztNm){
	gArguments["title"]    = strTitle;
	if( uniqId != "" )   gArguments["uniqId"]   = document.getElementById(uniqId);
	if( emplNo != "" )   gArguments["emplNo"]   = document.getElementById(emplNo);
	if( emplyrNm != "" ) gArguments["emplyrNm"] = document.getElementById(emplyrNm);
	if( orgnztNm != "" ) gArguments["orgnztNm"] = document.getElementById(orgnztNm);

	var url = "/uss/ion/ism/listSanctnerPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>
