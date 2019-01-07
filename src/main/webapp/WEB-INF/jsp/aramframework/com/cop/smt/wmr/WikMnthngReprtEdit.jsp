<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : WikMnthngReprtEdit.jsp
 * @Description : 주간/월간보고 수정
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
	<h2>주간/월간보고 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="wikMnthngReprtVO" action="" method="post" enctype="multipart/form-data"> 
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="reprtId" />

<table class="table-register"  summary="이 표는 주간/월간보고 정보를 수정하기 위한 표이며, 보고유형, 보고일자, 해당일자, 작성자, 보고대상자, 보고서제목, 금주보고내용, 차주보고내용, 특이사항, 파일첨부 , 보고서상태 정보로 구성되어 있습니다 .">
<caption>주간/월간보고 수정</caption>
<tbody>
	<tr>
		<th width="20%">
	    	<span class="required_icon"></span>
			<label for="reprtSe">보고유형</label>
		</th>
		<td width="80%">
			<form:radiobutton path="reprtSe" value="1" />주간보고
	       	<form:radiobutton path="reprtSe" value="2" />월간보고
	       	<form:errors path="reprtSe" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>
	    	<span class="required_icon"></span>
			<label for="reprtDe">보고일자</label>
		</th>
		<td>
      		<form:hidden path="reprtDe" />
	    	<c:if test="${!empty wikMnthngReprtVO.reprtDe}">
 				<c:set var="reprtDeVal" value="${fn:substring(wikMnthngReprtVO.reprtDe, 0,4)}-${fn:substring(wikMnthngReprtVO.reprtDe, 4,6)}-${fn:substring(wikMnthngReprtVO.reprtDe, 6,8)}"/>
      		</c:if>
      		<input name="reprtDeView" id="reprtDeView" type="text" size="10" title="보고일자" value="${reprtDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].reprtDe, document.forms[0].reprtDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="reprtDe" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>
	    	<span class="required_icon"></span>
			<label for="reprtBgnDe">해당일자</label>
		</th>
		<td>
      		<form:hidden path="reprtBgnDe" />
	    	<c:if test="${!empty wikMnthngReprtVO.reprtBgnDe}">
 				<c:set var="reprtBgnDeVal" value="${fn:substring(wikMnthngReprtVO.reprtBgnDe, 0,4)}-${fn:substring(wikMnthngReprtVO.reprtBgnDe, 4,6)}-${fn:substring(wikMnthngReprtVO.reprtBgnDe, 6,8)}"/>
      		</c:if>
      		<input name="reprtBgnDeView" id="reprtBgnDeView" type="text" size="10" title="해당 시작일자" value="${reprtBgnDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].reprtBgnDe, document.forms[0].reprtBgnDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>

      		<form:hidden path="reprtEndDe" />
	    	<c:if test="${!empty wikMnthngReprtVO.reprtEndDe}">
 				<c:set var="reprtEndDeVal" value="${fn:substring(wikMnthngReprtVO.reprtEndDe, 0,4)}-${fn:substring(wikMnthngReprtVO.reprtEndDe, 4,6)}-${fn:substring(wikMnthngReprtVO.reprtEndDe, 6,8)}"/>
      		</c:if>
      		<input name="reprtEndDeView" id="reprtEndDeView" type="text" size="10" title="해당 종료일자" value="${reprtEndDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].reprtEndDe, document.forms[0].reprtEndDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
			<form:errors path="reprtBgnDe" cssClass="error"/>
			<form:errors path="reprtEndDe" cssClass="error"/>
		</td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	작성자
	    </th>
	    <td>
	      	<c:out value="${wikMnthngReprtVO.wrterClsfNm}" escapeXml="false" />
	      	&nbsp;
	      	<c:out value="${wikMnthngReprtVO.wrterNm}" escapeXml="false" />
	      	<input type="hidden" name="wrterId" id="wrterId" value="${wikMnthngReprtVO.wrterId}"/>
	      	<input type="hidden" name="wrterNm" id="wrterNm" value="${wikMnthngReprtVO.wrterNm}"/>
	      	<input type="hidden" name="wrterClsfNm" id="wrterClsfNm" value="${wikMnthngReprtVO.wrterClsfNm}"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reportrNm">보고대상자</label>
	    </th>
	    <td>
		<table>
			<tr>
				<td width="100px" >
					<form:input path="reportrNm" size="15" readonly="true" maxlength="10" title="보고대상명"/>
				</td>
				<td >
					<a href="#" title="새 창으로 이동"  onClick="fn_aram_get_reportr('보고대상자', 'reportrId', 'reportrNm'); return false;">
						<img src="/images/aramframework/com/cmm/icon/search.gif" style="border:0px" alt="보고대상자 검색" title="보고대상자 검색">
					</a>
				</td>
			</tr>
		</table>
		<form:errors path="reportrNm" cssClass="error"/>
	    <form:hidden path="reportrId" />
    	</td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reprtSj">보고서제목</label>
	    </th>
	    <td>
	      	<form:input path="reprtSj" size="75" maxlength="255" title="보고서제목"/>
	      	<form:errors path="reprtSj" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reprtThswikCn">금주보고내용</label>
	    </th>
	    <td>
	      	<form:textarea path="reprtThswikCn" rows="7" cols="90" title="금주보고내용"/>
    	  	<form:errors path="reprtThswikCn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="required_icon"></span>
	    	<label for="reprtLesseeCn">차주보고내용</label>
	    </th>
	    <td>
	      	<form:textarea path="reprtLesseeCn" rows="7" cols="90" title="차주보고내용"/>
    	  	<form:errors path="reprtLesseeCn" cssClass="error"/>
	    </td>
	</tr>
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	<label for="partclrMatter">특이사항</label>
	    </th>
	    <td>
	      	<form:textarea path="partclrMatter" rows="5" cols="90" title="특이사항"/>
    	  	<form:errors path="partclrMatter" cssClass="error"/>
	    </td>
	</tr>
	<!-- 첨부목록 -->
	<c:choose>
	<c:when test="${wikMnthngReprtVO.atchFileId ne null && wikMnthngReprtVO.atchFileId ne ''}">
	<tr>
		<th>
			<span class="norequired_icon"></span>
			첨부파일 목록
		</th>
		<td>
			<input type="hidden" name="returnUrl" value="${pageContext.request.contextPath}/cop/smt/wmr/modifyWikMnthngReprt.do" />
			<c:import url="/content/files/${wikMnthngReprtVO.atchFileId}/editform" />
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="atchFileId" value="">
		<input type="hidden" name="fileListCnt" id="fileListCnt" value="0">
 	</c:otherwise>
  	</c:choose>
	<!-- //첨부목록 -->

	<!-- 첨부화일 업로드 -->
	<tr>
		<th>
			<span class="norequired_icon"></span>
			<label for="file_1">파일첨부</label>
		</th>
		<td>

			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />

			<div id="file_upload_posbl"  style="display:none;">
			<table>
			    <tr>
			        <td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/></td>
			    </tr>
			    <tr>
			        <td><div id="egovComFileList"></div></td>
				</tr>
			</table>
			</div>

			<div id="file_upload_imposbl"  style="display:none;">
	        	<spring:message code="common.imposbl.fileupload" />
			</div>
		</td>
	</tr>
    <!-- //첨부화일 업로드 -->
	<tr>
	    <th>
	    	<span class="norequired_icon"></span>
	    	보고서 상태
	    </th>
		<td>
		    <c:out value="${wikMnthngReprtVO.reprtSttus}" escapeXml="false" />
		</td>
	</tr>
</tbody>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchSttus" />
<form:hidden path="searchDe" />
<form:hidden path="searchBgnDe" />
<form:hidden path="searchEndDe" />
<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
<input type="hidden" name="searchKeyword"   value="${searchVO.searchKeyword}" />
<input type="hidden" name="pageIndex"       value="${searchVO.pageIndex}" />
<input type="hidden" name="recordPerPage"   value="${searchVO.recordPerPage}" />
<!-- 검색조건 유지 -->
</form:form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="wikMnthngReprtVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/fms/MultiFile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>

<!--  첨부파일 업로드 가능화일 설정 Start.. -->  
<script type="text/javascript">
	fn_edit_FileAttachment();
</script> 
<!--  첨부파일 업로드 가능화일 설정 End. -->

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("wikMnthngReprtVO");
    varForm.action = "${pageContext.request.contextPath}/cop/smt/wmr/listWikMnthngReprt.do";
    varForm.submit();
}

function fn_aram_update() {
    var varForm = document.getElementById("wikMnthngReprtVO");

    if (!validateWikMnthngReprtVO(varForm)){
		return;
	}

	var bgnDe = varForm.reprtBgnDe.value;
	var endDe = varForm.reprtEndDe.value;

	if(bgnDe != ""){
		if(isDate(bgnDe, "해당시작일자") == false) {
	        return;
	    }
	}

	if(endDe != ""){
	    if(isDate(endDe, "해당종료일자") == false) {
	        return;
	    }
	}

	if(bgnDe != "" && endDe != ""){
		if(eval(bgnDe)> eval(endDe)){
			alert("해당종료일자가 해당시작일자보다 빠를수 없습니다.");
			return;
		}
	}

	if (confirm("<spring:message code='common.update.msg' />")) {
		varForm.action = "${pageContext.request.contextPath}/cop/smt/wmr/updateWikMnthngReprt.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
* 아이디  팝업창열기
******************************************************** */
function fn_aram_get_reportr(strTitle, empId, empName){
	gArguments["title"]    = strTitle;
	if( empId != "" )    gArguments["uniqId"]   = document.getElementById(empId);
	if( empName != "" )  gArguments["emplyrNm"] = document.getElementById(empName);

	var url = "/cop/smt/wmr/listReportrPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

