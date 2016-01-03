/*********************************************************
 * 초기화
 ******************************************************** */
var resultList;
var fileInfoList;

function fn_egov_initl_mobilemultimedialist() {
	
	$.getJSON(contextPath + "/mbl/com/mlt/listMobileMultimediaJson.mdo", function(json) {
		var list_html = "";

		resultList = json.resultList;
		fileInfoList = json.fileInfoList;
		
		for (var i = 0; i < resultList.length; i++) {
			var result = resultList[i];

			list_html += "<li><a href=\"javascript\:fn_multimediaplay(" + i + ", '" + result.atchFileId + "')\">";
			list_html += "<h3>" + result.mltmdSj + " ( " + result.mltmdNm + " ) </h3><p>" + result.creatDt + "</p>";
			list_html += "<p>지원 : " + result.browserNm + "</p>";
			list_html += "</a></li>";
		}
		
		$('div[id="multimediaList"] ul[data-role="listview"]').html(list_html).listview('refresh');;
	});
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
 function fn_multimediaplay(index, atchFileId) {
		
	// 선택된 항목의 첨부된 파일 리스트를 가져온다.
	$.getJSON(contextPath + "/mbl/com/mlt/getMultimediaFileList.mdo?atchFileId=" + atchFileId, function(json) {

		var detail_html = "";
		var fileList = json.fileList;

		if(resultList[index].mltmdCode == "MLT01"){
			detail_html += "<video controls=\"true\" style=\"width:100%;max-width:100%;\">"
		}else if(resultList[index].mltmdCode == "MLT02"){
			detail_html += "<audio controls=\"true\">"
		}

		for (var i = 0; i < fileList.length; i++) {
			// 최상위 폴더 내에서 파일 참조
			detail_html += "<source src=\"../../../" + json.relativePath + fileList[i].streFileNm + "." + fileList[i].fileExtsn + "\"/>";
		}
		
		detail_html += "<p>지원 안함</p>";

		if(resultList[index].mltmdCode == "MLT01"){
			detail_html += "</video>"
		}else if(resultList[index].mltmdCode == "MLT02"){
			detail_html += "</audio>"
		}
		
		$('div[id="playview"]').html(detail_html);
		
		$.mobile.changePage("#multimediaDetail", "slide", false, false);
	});				
}

function fn_egov_getType(classification, extension){
	var type = "";

	return type;				
}	