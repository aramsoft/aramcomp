
function fn_emplyr_list(url) {
	
		$.getJSON(url, $('#searchVO').serialize().replace(/%/g,'%25'), function(json){

		$("#searchCondition option[value='" + json.searchVO.searchCondition + "']").attr('selected', 'selected');	
		var html = "";
            
		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
       		html += '    <spring:message code="common.nodata.msg"/>';
       		html += '</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
				
				var result =  json.resultList[i];
				
				html += '<li>';
				html += '	<a href="javascript:fn_sel_emp(\'' + result.uniqId + '\', \'' + result.emplyrNm + '\')">';
				html += '		<h3>' + result.emplyrNm + '</h3>';
				html += '		<p>' + result.emplNo + '</p>';
				html += '		<p>' + result.ofcpsNm + '</p>';
				html += '	</a>';
				html += '</li>';			
							
			}
		}
		$('div[id="view_emplyr"] ul[data-role="listview"]').html(html).listview('refresh');
		
	 	});
	 	
}

function fn_schdul_list(url) {
	
	$.getJSON(url, $('#searchVO').serialize().replace(/%/g,'%25'), function(json){

		var html = "";
            
		if(json.resultList.length == 0) {
			html += '<li class="com-egovNodata">';
       		html += '    <spring:message code="common.nodata.msg"/>';
       		html += '</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
				
				var result =  json.resultList[i];
				
				html += '<li>';
				html += '   <a href="javaScript:fn_sel_schdul(\'' + result.schdulId + '\', \'' + result.schdulNm + '\')" >';	
				html += '		<h3>' + result.schdulNm + '</h3>';
				html += '		<p>' + result.schdulCn + '</p>';
				html += '	</a>';
				html += '</li>';			
							
			}
		}

		$('div[id="view_schdul"] ul[data-role="listview"]').html(html).listview('refresh');
		
 	});
 	
}
