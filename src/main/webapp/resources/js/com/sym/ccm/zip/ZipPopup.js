/****************************************************************
 *
 * 파일명 : ZipPopup.js
 * 설  명 : 전자정부 공통서비스 달력 팝업 JavaScript
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2009.03.30    이중호       1.0             최초생성
 * 2011.08.30	 이기하		  1.1			  contextpath 적용
 *
 *
 */

var gZipCode = new Array();

/**********************************************
 * 함수명 : fn_aram_ZipSearch
 * 설  명 : 우편번호찾기 팝업 호출 
 * 인  자 : 우편번호(123456), 우편번호(123-456), 주소
 * 사용법 : fn_aram_ZipSearch(sZip, vZip, sAddr)
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2009.03.30    이중호      신규작업
 * 2011.08.30	 이기하		 contextpath 적용
 *
 */

function fn_aram_ZipSearch(sZip, vZip, sAddr) {
	var url = "/sym/ccm/zip/listZipPopup.do";
	gZipCode["sZip"] = sZip;
	gZipCode["vZip"] = vZip;
	gZipCode["sAddr"] = sAddr;

	window.open(url, "zipPopup", "width=850px,height=420px,top=100px,left=100px,location=no");
}

/**********************************************
 * 함수명 : fn_aram_RdNmSearch
 * 설  명 : 우편번호찾기 팝업 호출 
 * 인  자 : 우편번호(123456), 우편번호(123-456), 주소
 * 사용법 : fn_aram_ZipSearch(sZip, vZip, sAddr)
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2009.03.30    이중호      신규작업
 * 2011.08.30	 이기하		 contextpath 적용
 *
 */

function fn_aram_RdNmSearch(sZip, vZip, sAddr) {
	var url = "/sym/ccm/zip/listRdNmPopup.do";
	gZipCode["sZip"] = sZip;
	gZipCode["vZip"] = vZip;
	gZipCode["sAddr"] = sAddr;

	window.open(url, "zipPopup", "width=950px,height=420px,top=100px,left=100px,location=no");
}
