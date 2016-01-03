/*********************************************************
 * 초기화
 ******************************************************** */
var totcnt;

function fn_egov_initl_mobileDeviceInfolist(){

	$.getJSON(contextPath + "/mbl/com/mdi/goMobileDeviceIdentJson.mdo", function(json) {
	
		var deviceident_html = "";
		var result = json.result;
		
		deviceident_html += "<li>";
		deviceident_html += "<dl class='mcom-device'>";
		deviceident_html += "<dt>모바일 기기 정보</dt>";
		deviceident_html += "<dd><strong>브라우저 명</strong></dd>";
		deviceident_html += "<dd>" + result.browserNm + "</dd>";
		deviceident_html += "<dd><br></dd>";
		deviceident_html += "<dd><strong>운영체제 명</strong></dd>";
		deviceident_html += "<dd>" + result.osNm + "</dd>";
		deviceident_html += "</dl>";
		deviceident_html += "<dl class='mcom-device'>";
		deviceident_html += "<dt>User-Agent</dt>";
		deviceident_html += "<dd>" + result.uagentInfo + "</dd>";
		deviceident_html += "</dl>";
		deviceident_html += "</li>";
		
		$('div[id="deviceIdent"] ul[data-role="deviceidentview"]').html(deviceident_html);
	});
}