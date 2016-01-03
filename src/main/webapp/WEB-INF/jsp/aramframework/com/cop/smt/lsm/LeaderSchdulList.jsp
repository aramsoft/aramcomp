<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
/**
 * @Class Name : LeaderSchdulList.jsp
 * @Description : 긴부일정 월/주간/일별 조회
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
	<h2>긴부일정 월/주간/일별 조회</h2> 
</div>

<div style="margin-top:10px; width:100%"></div>

<!-- 상단 텝 -->
<table border="0" summary="간부일정목록의 보기방식을 지정합니다.">
	<tr> 
  		<td height="20px" width="100px" bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu0" onClick="fnTabMenuSelect(0);"><b>월별일정보기</b></td>
  		<td height="20px" width="1x" bgcolor="#FFFFFF"></td>
  		<td height="20px" width="100px"  bgcolor="#DDDDDD" style="cursor:hand;cursor:pointer;" align="center" id="tabMenu1" onClick="fnTabMenuSelect(1);"><b>주간별일정보기</b></td>
  		<td height="20px" width="1x" bgcolor="#FFFFFF"></td>
  		<td height="20px" width="100px" style="cursor:hand;cursor:pointer;" bgcolor="#DDDDDD" align="center" id="tabMenu2" onClick="fnTabMenuSelect(2);"><b>일별일정보기</b></td>
  		<td height="20px" width="1x" bgcolor="#FFFFFF"> </td>
	</tr>
</table>
<!-- //상단 텝 -->

<div style="height:3px; width:700px; background-color:#DDDDDD;"></div>

<!-- iframe -->
<iframe id="SchdulView" name="SchdulView" src="" width="700" height="700" seamless="seamless" title="월별/주별/일별 간부일정목록">
</iframe>

<!-- //iframe -->
<form:form commandName="leaderSchdulVO" name="leaderSchdulVO" action="?" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="schdulId" />
<form:hidden path="schdulBgnde" />
<form:hidden path="schdulEndde" />

<form:hidden path="searchSchdulSe" />
<form:hidden path="searchUser" />
<form:hidden path="year" />
<form:hidden path="month" />
<form:hidden path="week" />
<form:hidden path="day" />
</form:form>

</div>

<script type="text/javascript">

/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fnTabMenuSelect(objArr){
	document.getElementById("tabMenu0").bgColor = '#DDDDDD';
	document.getElementById("tabMenu1").bgColor = '#DDDDDD';
	document.getElementById("tabMenu2").bgColor = '#DDDDDD';

	var varForm = document.getElementById("leaderSchdulVO");
	varForm.target = "SchdulView";

	getSearchData();
	
//	alert(varForm.year.value);
	
	if(objArr == 0){
		document.getElementById("tabMenu0").bgColor = '#BBBBBB';
		varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulMonth.do";
	}else if(objArr == 1){
		document.getElementById("tabMenu1").bgColor = '#BBBBBB';
		varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulWeek.do";
	}else if(objArr == 2){
		document.getElementById("tabMenu2").bgColor = '#BBBBBB';
		varForm.action = "${pageContext.request.contextPath}/cop/smt/lsm/listLeaderSchdulDaily.do";
	}
	varForm.submit();
	//do_resize();
}

function getSearchData() {
	var childWindow = document.getElementById('SchdulView').contentWindow; 
	if( childWindow.fnEgovSchdulSe != null ){ 
		var childForm = childWindow.document.getElementById("leaderSchdulVO");
		var varForm = document.getElementById("leaderSchdulVO");

		varForm.searchSchdulSe.value = childForm.searchSchdulSe.value;
		varForm.searchUser.value = childForm.searchUser.value;
		varForm.year.value = childForm.year.value;
		varForm.month.value = childForm.month.value;
		varForm.week.value = childForm.week.value;
		varForm.day.value = childForm.day.value;
	}
}

/* ********************************************************
* 등록 처리 함수 
******************************************************** */
window.onload = function() {
	fnTabMenuSelect(0);
	resizeFrame("SchdulView",1);	// 추가...
};

/* ********************************************************
* IFRAME AUTO HEIGHT
******************************************************** */
function resizeFrame(ifr_id,re){
	//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
 	var ifr= document.getElementById(ifr_id) ;
 	var innerBody = ifr.contentWindow.document.body;
 	var innerHeight = innerBody.scrollHeight + (innerBody.offsetHeight - innerBody.clientHeight);
 	//var innerWidth = document.body.scrollWidth + (document.body.offsetWidth - document.body.clientWidth);

 	if (ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
 	{
  		ifr.style.height = innerHeight;
  		//ifr.style.width = innerWidth;
 	}

 	if(!re) {
	  	try{
	   		innerBody.attachEvent('onclick',parent.do_resize);
	   		innerBody.attachEvent('onkeyup',parent.do_resize);
	   		//글작성 상황에서 클릭없이 타이핑하면서 창이 늘어나는 상황이면 윗줄 주석제거
	  	} catch(e) {
	   		innerBody.addEventListener("click", parent.do_resize, false);
	   		innerBody.addEventListener("keyup", parent.do_resize, false);
	   		//글작성 상황에서 클릭없이 타이핑하면서 창이 늘어나는 상황이면 윗줄 주석제거
	  	}
 	}
}

</script>
