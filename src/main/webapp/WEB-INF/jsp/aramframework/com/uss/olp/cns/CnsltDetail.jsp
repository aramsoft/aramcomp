<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : CnsltDetail.jsp
  * @Description : 상담 상세조회
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
	<h2>상담 상세조회</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_edit(); return false;"><spring:message code="button.update" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_delete(); return false;"><spring:message code="button.delete" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="cnsltManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="cnsltId" />

<input name="writngPassword" 	type="hidden" value="">

<!-- 등록  폼 영역  -->
<table class="table-detail" summary="이 표는 상담 정보를 제공하며, 작성자명, 전화, 휴대폰전화번호, 이메일, 작성일자, 조회횟수, 처리상태, 상담제목, 상담내용 정보로 구성되어 있습니다 .">
<caption>상담 상세조회</caption>
  	<tr>
    	<th width="20%">
     		작성자명
    	</th>
    	<td width="80%">
    		<c:out value="${cnsltManageVO.wrterNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		전화
    	</th>
    	<td>
    		<c:if test="${cnsltManageVO.areaNo != null}">
      		<c:out value="${cnsltManageVO.areaNo}"/>-<c:out value="${cnsltManageVO.middleTelno}"/>-<c:out value="${cnsltManageVO.endTelno}"/>
			</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		휴대폰전화번호
     	</th>
    	<td>
    		<c:if test="${cnsltManageVO.firstMoblphonNo != null}">
      		<c:out value="${cnsltManageVO.firstMoblphonNo}"/>-<c:out value="${cnsltManageVO.middleMbtlnum}"/>-<c:out value="${cnsltManageVO.endMbtlnum}"/>
			</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		이메일
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.emailAdres}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="emailAnswerAt" type="checkbox"  disabled <c:if test="${cnsltManageVO.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부
    	</td>
  	</tr>
  	<tr>
    	<th>
     		작성일자
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.writngDe}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		조회횟수
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.inqireCo}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		처리상태
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.qnaProcessSttusCodeNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		상담제목
    	</th>
    	<td>
      		<c:out value="${cnsltManageVO.cnsltSj}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		상담내용
    	</th>
    	<td>
      		<textarea name="cnsltCn" class="textarea_readonly"  cols="300" rows="15"  style="width:450px;" readonly title="상담내용"><c:out value="${cnsltManageVO.cnsltCn}"/>
      		</textarea>
    	</td>
  	</tr>
  	<c:if test="${cnsltManageVO.atchFileId != ''}">
	<tr>
		<th>
			첨부파일 목록
 		</th>
		<td>
			<c:import url="/content/files/${cnsltManageVO.atchFileId}" />
		</td>
	</tr>
  	</c:if>
	<!--답변내용이 있을경우 Display... -->
	<c:if test="${cnsltManageVO.qnaProcessSttusCode == '3'}">
  	<tr>
    	<th>
    		답변내용
    	</th>
    	<td>
      		<textarea name="managtCn" class="textarea"  cols="300" rows="15"  style="width:450px;" readonly title="답변내용"><c:out value="${cnsltManageVO.managtCn}"/>
      		</textarea>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		담당부서
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.orgnztNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		답변일자
    	</th>
    	<td>
  			<c:if test="${cnsltManageVO.managtDe != null}">
  			<c:out value="${cnsltManageVO.managtDe}"/>
 			</c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		답변자
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.emplyrNm}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		전화번호
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.offmTelno}"/>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		이메일
    	</th>
    	<td>
    		<c:out value="${cnsltManageVO.aemailAdres}"/>
    	</td>
  	</tr>
	</c:if>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

<c:if test="${passwordConfirmAt == 'N'}">
	<script>
	fn_aram_passwordConfirm();
	</script>
</c:if>

</DIV>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list() {
    var varForm = document.getElementById("cnsltManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/listCnslt.do";
    varForm.submit();
}

var gArguments = new Array();

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_aram_edit(){
	gArguments["retFunc"] = fn_aram_password_confirm;
	
	var url = "/uss/olp/cns/CnsltPasswordPopup.do";

	window.open(url, "p_password", "width=320px,height=140px,top=100px,left=100px,location=no");
}

function fn_aram_password_confirm(writngPassword) {
    var varForm = document.getElementById("cnsltManageVO");
	varForm.writngPassword.value = writngPassword;
	varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/CnsltPasswordConfirm.do";
	varForm.submit();
}

/**********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_aram_delete(){
	gArguments["retFunc"] = fn_aram_password_confirmDel;
	
	var url = "/uss/olp/cns/CnsltPasswordPopup.do";

	window.open(url, "p_password", "width=320px,height=140px,top=100px,left=100px,location=no");
}

function fn_aram_password_confirmDel(writngPassword) {
    var varForm = document.getElementById("cnsltManageVO");
	varForm.writngPassword.value = writngPassword;
	varForm.action = "${pageContext.request.contextPath}/uss/olp/cns/CnsltPasswordConfirmDel.do";
	varForm.submit();
}

/*********************************************************
 * 작성비밀번호.체크..
 ******************************************************** */
function fn_aram_passwordConfirm(){

	alert("작성 비밀번호를 확인 바랍니다!");
}

</script>

