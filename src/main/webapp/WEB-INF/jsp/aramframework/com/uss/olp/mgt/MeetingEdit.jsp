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
  * @Class Name : MeetingEdit.jsp
  * @Description : 회의록 수정
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
	<h2>회의록 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="meetingManageVO"  action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="mtgId" />

<!-- - 등록  폼 영역  -->
<table class="table-register">
  	<tr>
    	<th width="20%">
     		<span class="required_icon"></span>
    		<label for="mtgNm">회의제목</label>
    	</th>
    	<td width="80%">
     	   	<form:input path="mtgNm" size="73" maxlength="100" style="width:99%;" title="회의제목 입력" />
     	   	<form:errors path="mtgNm" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="mtgMtrCn">회의 안건 내용</label>
    	</th>
    	<td>
      		<form:textarea path="mtgMtrCn" class="textarea"  cols="75" rows="4"  style="width:99%;" title="회의안간내용 입력" />
      		<form:errors path="mtgMtrCn" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="mtgSn">회의순서</label>
    	</th>
    	<td>
      		<form:input path="mtgSn" size="73" maxlength="10" style="width:60px;" title="회의순서 입력" />
      		<form:errors path="mtgSn" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="mtgCo">회의회차</label>
    	</th>
    	<td>
      		<form:input path="mtgCo" size="73" maxlength="5" style="width:60px;" title="회의회차 입력" />
      		<form:errors path="mtgCo" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="mtgDe">회의일자</label>
    	</th>
    	<td>
      		<form:hidden path="mtgDe" />
	    	<c:if test="${!empty meetingManageVO.mtgDe}">
 				<c:set var="mtgDeVal" value="${fn:substring(meetingManageVO.mtgDe, 0,4)}-${fn:substring(meetingManageVO.mtgDe, 4,6)}-${fn:substring(meetingManageVO.mtgDe, 6,8)}"/>
      		</c:if>
      		<input name="mtgDeView" id="mtgDeView" type="text" size="10" title="회의일자" value="${mtgDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].mtgDe, document.forms[0].mtgDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="mtgDe" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="mtgPlace">회의장소</label>
    	</th>
    	<td>
      		<form:input path="mtgPlace" size="73" maxlength="70" style="width:200px;" title="회의장소 입력" />
      		<form:errors path="mtgPlace" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="mtgBeginHH">회의시작시간</label>
    	</th>
    	<td>
			<c:forTokens var="one"
			            items="${meetingManageVO.mtgBeginTime}"
			            delims=":" varStatus="sts">
	         	<c:if test="${sts.count == 1}">
	         	<select name="mtgBeginHH" id="mtgBeginHH" title="시 선택">
		     		<c:forEach var="h" begin="1" end="24" step="1">
		      		<option value="${h}" <c:if test="${h == one}">selected</c:if>>${h}시</option>
		      		</c:forEach>
		     	</select>
	         	</c:if>
	         	<c:if test="${sts.count == 2}">
			    <select name="mtgBeginMM" id="mtgBeginMM" title="분 선택">
			     	<option value="0">0분</option>
			      	<c:forEach var="m" begin="1" end="60" step="1">
			      	<option value="${m}" <c:if test="${m == one}">selected</c:if>>${m}분</option>
			      	</c:forEach>
			    </select>
	         	</c:if>
			</c:forTokens>
			<form:hidden path="mtgBeginTime" />
			<form:errors path="mtgBeginHH" cssClass="error" />
			<form:errors path="mtgBeginMM" cssClass="error" />
	    </td>
  	</tr>
  	<tr>
    	<th>
     		<span class="required_icon"></span>
    		<label for="mtgEndHH">회의종료시간</label>
    	</th>
    	<td>
			<c:forTokens var="one"
		            items="${meetingManageVO.mtgEndTime}"
		            delims=":" varStatus="sts">
		    	<c:if test="${sts.count == 1}">
		        <select name="mtgEndHH" id="mtgEndHH" title="시 선택">
			    	<c:forEach var="h" begin="1" end="24" step="1">
			      	<option value="${h}" <c:if test="${h == one}">selected</c:if>>${h}시</option>
			    	</c:forEach>
			    </select>
		        </c:if>
		        <c:if test="${sts.count == 2}">
				<select name="mtgEndMM" id="mtgEndMM" title="분 선택">
				    <option value="0">0분</option>
				    <c:forEach var="m" begin="1" end="60" step="1">
				    <option value="${m}" <c:if test="${m == one}">selected</c:if>>${m}분</option>
				    </c:forEach>
				</select>
		        </c:if>
			</c:forTokens>
			<form:hidden path="mtgEndTime" />
       		<form:errors path="mtgEndHH" cssClass="error" />
       		<form:errors path="mtgEndMM" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="clsdrMtgAt">비공개 회의여부</label>
    	</th>
    	<td>
     		<input name="clsdrMtgAt" type="checkbox" style="border:0px;" value="1" title="비공개회의여부 선택" <c:if test="${meetingManageVO.clsdrMtgAt == '1'}">checked</c:if>>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="readngBeginDe">열람 개시일</label>
    	</th>
    	<td>
      		<form:hidden path="readngBeginDe" />
	    	<c:if test="${!empty meetingManageVO.readngBeginDe}">
 				<c:set var="readngBeginDeVal" value="${fn:substring(meetingManageVO.readngBeginDe, 0,4)}-${fn:substring(meetingManageVO.readngBeginDe, 4,6)}-${fn:substring(meetingManageVO.readngBeginDe, 6,8)}"/>
      		</c:if>
      		<input name="readngBeginDeView" id="readngBeginDeView" type="text" size="10" title="열람개시일" value="${readngBeginDeVal}"  readonly />
      		<a href="#" onClick="javascript:fn_aram_NormalCalendar(document.forms[0].readngBeginDe, document.forms[0].readngBeginDeView); return false;">
      			<img name="calendarImg" src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/bu_icon_carlendar.gif"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
      		</a>
      		<form:errors path="readngBeginDe" cssClass="error" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="required_icon"></span>
    		<label for="readngAt">열람 여부</label>
    	</th>
    	<td>
       		<form:select path="readngAt" title="열람여부 선택">
       			<form:option value="N" lable="N" />
       			<form:option value="Y" lable="Y" />
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
  			<label for="mtgResultCn">회의결과 내용</label>
  		</th>
    	<td>
      		<form:textarea path="mtgResultCn" class="textarea"  cols="75" rows="4"  title="회의결과내용 입력" style="width:99%;" />
    	</td>
  	</tr>
  	<tr>
    	<th>
      		<span class="norequired_icon"></span>
  			<label for="mtgResultEnnc">회의결과 여부</label>
  		</th>
    	<td>
     		<input name="mtgResultEnnc" type="checkbox" style="border:0px;" value="1" title="회의결과여부 선택" <c:if test="${meetingManageVO.mtgResultEnnc == '1'}">checked</c:if>>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
  			<label for="etcMatter">기타 사항</label>
  		</th>
    	<td>
      		<form:textarea path="etcMatter" class="textarea"  cols="75" rows="2" title="기타사항 입력" style="width:99%;" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
  			<label for="mngtDeptNm">주관부서</label>
  		</th>
    	<td>
      		<form:input path="mngtDeptNm" size="73" maxlength="2000" title="주관부서 입력" style="width:100px;" readonly="true" />
      		<a href="${pageContext.request.contextPath}/uss/olp/mgt/listMeetingDeptPopup.do" target="_blank" title="주관부서 선택  팝업 새창으로" onclick="fn_aram_get_mngtDeptNm();return false">
      			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="주관부서 찾기버튼" title="주관부서 찾기버튼">
      		</a>
      		<form:hidden path="mngtDeptId" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
 			<label for="mnaerNm">주관자명</label>
  		</th>
    	<td>
      		<form:input path="mnaerNm" size="73" title="주관자명 입력" maxlength="2000" style="width:100px;" readonly="true" />
      		<a href="${pageContext.request.contextPath}/uss/olp/mgt/listMeetingEmpLyrPopup.do" target="_blank" title="주관자ID 선택  팝업 새창으로" onclick="fn_aram_get_mnaer();return false">
      			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="주관자ID 찾기버튼" title="주관자ID 찾기 버튼">
      		</a>
     		<form:hidden path="mnaerId" />
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<span class="norequired_icon"></span>
  			<label for="mnaerDeptNm">주관자부서</label>
  		</th>
    	<td>
      		<form:input path="mnaerDeptNm" size="73" title="주관부서 입력" maxlength="2000" style="width:100px;" readonly="true" />
      		<a href="${pageContext.request.contextPath}/uss/olp/mgt/listMeetingDeptPopup.do" target="_blank" title="주관자부서 선택  팝업 새창으로" onclick="fn_aram_get_mnaerDept();return false">
      			<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/search.gif" align="middle" style="border:0px" alt="주관자부서 찾기버튼" title="주관자부서 찾기 버튼">
      		</a>
     		<form:hidden path="mnaerDeptId" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
   			<label for="mtnAt">회의여부</label>
   		</th>
    	<td>
       		<form:select path="mtnAt" title="회의여부 선택">
       			<form:option value="N" lable="N" />
       			<form:option value="Y" lable="Y" />
      		</form:select>
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
  			<label for="nonatdrnCo">불참석자수</label>
  		</th>
    	<td>
      		<form:input path="nonatdrnCo" size="73" maxlength="10" style="width:60px;" title="불참석자수 입력" />
    	</td>
  	</tr>
  	<tr>
    	<th>
     		<span class="norequired_icon"></span>
  			<label for="atdrnCo">참석자수</label>
  		</th>
    	<td>
      		<form:input path="atdrnCo" size="73" maxlength="10" style="width:60px;" title="참석자수 입력" />
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
<validator:javascript formName="meetingManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/sym/cal/CalPopup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/aramframework/com/cmm/utl/CmmUtl.js"></script>

<script type="text/javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("meetingManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/olp/mgt/listMeeting.do";
    varForm.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_aram_update(){
	var varForm = document.getElementById("meetingManageVO");
	
	if(!validateMeetingManageVO(varForm)){
		return;
	}
	
	var mtgBeginHH = eval(document.getElementById("mtgBeginHH").value);
	var mtgBeginMM = eval(document.getElementById("mtgBeginMM").value);
	var mtgEndHH = eval(document.getElementById("mtgEndHH").value);
	var mtgEndMM = eval(document.getElementById("mtgEndMM").value);

	if(mtgBeginHH> mtgEndHH){
		alert("회의 시작시간이 종료 시간보다 늦습니다.");
		return;
	}else if(mtgBeginHH == mtgEndHH){
		if(mtgBeginMM> mtgEndMM){
			alert("회의 시작시간이 종료 시간보다 늦습니다.");
			return;
		}
	}

	if(varForm.nonatdrnCo.value == ""){varForm.nonatdrnCo.value = "0";}
	if(varForm.atdrnCo.value == ""){varForm.atdrnCo.value = "0";}

	varForm.mtgBeginTime.value = fn_aram_SelectBoxValue('mtgBeginHH') + ":" + fn_aram_SelectBoxValue('mtgBeginMM');
	varForm.mtgEndTime.value = fn_aram_SelectBoxValue('mtgEndHH') + ":" + fn_aram_SelectBoxValue('mtgEndMM');

	if(confirm("<spring:message code='common.update.msg'/>")){
		varForm.action =  "${pageContext.request.contextPath}/uss/olp/mgt/updateMeeting.do";
		varForm.submit();
	}
}

var gArguments = new Array();

/* ********************************************************
 * 아이디  팝업창열기
 ******************************************************** */
function fn_aram_get_mnaer(){
	var varForm = document.getElementById("meetingManageVO");
	gArguments["mnaerId"] = varForm.mnaerId;
	gArguments["mnaerNm"] = varForm.mnaerNm;

	var url = "/uss/olp/mgt/listMeetingEmpLyrPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

/* ********************************************************
 * 주관자 부서 팝업창열기
 ******************************************************** */
function fn_aram_get_mnaerDept(){
	var varForm = document.getElementById("meetingManageVO");
	gArguments["deptId"] = varForm.mnaerDeptId;
	gArguments["deptNm"] = varForm.mnaerDeptNm;

	var url = "/uss/olp/mgt/listMeetingDeptPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}
 
/* ********************************************************
 * 주관 부서 팝업창열기
 ******************************************************** */
function fn_aram_get_mngtDeptNm(){
	var varForm = document.getElementById("meetingManageVO");
	gArguments["deptId"] = varForm.mngtDeptId;
	gArguments["deptNm"] = varForm.mngtDeptNm;

	var url = "/uss/olp/mgt/listMeetingDeptPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>

