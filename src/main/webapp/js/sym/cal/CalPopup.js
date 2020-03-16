/****************************************************************
 *
 * 파일명 : CalPopup.js
 * 설  명 : 전자정부 공통서비스 달력 팝업 JavaScript
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2009.03.30    이중호       1.0             최초생성
 * 2011.08.30	 이기하		  1.1			  contextpath 적용
 *
 *
 */

var gCalendars = new Array();

/**********************************************
 * 함수명 : fn_aram_NormalCalendar
 * 설  명 : 일반달력 팝업 호출
 * 인  자 : 연월일(yyyy-MM-dd)
 * 사용법 : fn_aram_NormalCalendar(sDate)
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2009.03.30    이중호      신규작업
 *
 */
function fn_aram_NormalCalendar(sDate, vDate) {
	var url = "/sym/cal/NormalCalPopup.do";
	gCalendars["sDate"] = sDate;
	gCalendars["vDate"] = vDate;
	gCalendars["init"] = "";

	window.open(url, "calPopup", "width=280px,height=190px,top=100px,left=100px,location=no");
}

/**********************************************
 * 함수명 : fn_aram_AdministCalendar
 * 설  명 : 행정달력 팝업 호출
 * 인  자 : 연월일(yyyy-MM-dd)
 * 사용법 : fn_aram_AdministCalendar(sDate)
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2009.03.30    이중호      신규작업
 *
 */

function fn_aram_AdministCalendar(sDate, vDate) {
	var url = "/sym/cal/AdministCalPopup.do";
	gCalendars["sDate"] = sDate;
	gCalendars["vDate"] = vDate;
	gCalendars["init"] = "";

	window.open(url, "calPopup", "width=300px,height=215px,top=100px,left=100px,location=no");
}

/**********************************************
 * 함수명 : fn_aram_Calendar
 * 설  명 : 달력 팝업 호출
 * 인  자 : 연월일(yyyy-MM-dd)
 * 사용법 : fn_aram_Calendar(sDate)
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2009.10.13    이중호      신규작업
 *
 */
function fn_aram_Calendar(sDate, vDate) {
	var url = "/sym/cal/CallCalPopup.do";
	gCalendars["sDate"] = sDate;
	gCalendars["vDate"] = vDate;
	gCalendars["init"] = "";

	window.open(url, "calPopup", "width=300px,height=215px,top=100px,left=100px,location=no");
}