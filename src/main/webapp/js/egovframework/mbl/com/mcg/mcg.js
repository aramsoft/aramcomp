// 기본 설정
var maxChartGraph = 5;					// 최대 표현 차트 색
var chartGraphColor = [	"#ECD078",		// 차트색 (총 5종)
               			"#D95B43",
               			"#C02942",
               			"#542437",
               			"#53777A" ];
var defaultWidth = 500;					// 기본 사이즈(넓이)

var clientWidth = document.documentElement.clientWidth > defaultWidth ? defaultWidth: document.documentElement.clientWidth;
var ratio = clientWidth/(defaultWidth*1.0);

var line_column_data = new Array();		// Line, Column 차트를 위한 2차원 배열
var bar_data = new Array();				// Bar 차트를 위한 2차원 배열
var pie_data = new Array();				// Pie 차트를 위한 2차원 배열
var scatter_data = new Array();			// scatter 차트를 위한 3차원 배열

/* 브라우저 크기 변경 시 차트 새로 그리기 */
$(window).resize(function(){
	// 브라우저 크기 관련 변수 업데이트
	clientWidth = document.documentElement.clientWidth > 500 ? 500: document.documentElement.clientWidth;
	ratio = clientWidth/(defaultWidth*1.0);

	// 차트/그래프 그리기
	fn_egov_draw_linechartgraph();
	fn_egov_draw_columnchartgraph();
	fn_egov_draw_piechartgraph();
	fn_egov_draw_scatterchartgraph();
	fn_egov_draw_barchartgraph();
});

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_mobilechartgraphlist(){
	
	$.getJSON(contextPath + "/mbl/com/mcg/goMobileChartGraphJson.mdo", function(json) {

		resultList = json.resultList;
		totcnt = resultList.length;

		// 정렬(올림차순) 1차 기준: 범례명, 2차 기준: X축 값, 3차 기준 : Y축 값
		for (var i = 0; i < totcnt-1; i++) {
			for(var j = totcnt - 1; j > i; j--){
				if(	( resultList[j-1].lgdNm > resultList[j].lgdNm ) ||
					( (resultList[j-1].lgdNm == resultList[j].lgdNm)&&(resultList[j-1].xaxis > resultList[j].xaxis) ) ||
					( (resultList[j-1].lgdNm == resultList[j].lgdNm)&&(resultList[j-1].xaxis == resultList[j].xaxis)&&(resultList[j-1].yaxis > resultList[j].yaxis) )
				){
					var temp = resultList[j];
					resultList[j] = resultList[j-1];
					resultList[j-1] = temp;
				}
			}
		}
		
		for (var i = 0; i < totcnt; i++) {
			var result = resultList[i];

			// Line, Column 차트 데이터 정리
			var line_column_data_index;
			for (line_column_data_index = 0; line_column_data_index < line_column_data.length; line_column_data_index++) {
				if( line_column_data[line_column_data_index].lgdNm == result.lgdNm ){
					break;
				}
			}
			if( line_column_data_index >= line_column_data.length ){
				line_column_data[line_column_data_index] = {};
				line_column_data[line_column_data_index].lgdNm = result.lgdNm;
				line_column_data[line_column_data_index].xaxis = new Array();
				line_column_data[line_column_data_index].yaxis = new Array();
				line_column_data[line_column_data_index].xOrder = new Array();
			}
			if(line_column_data[line_column_data_index].xaxis[line_column_data[line_column_data_index].xaxis.length-1] != result.xaxis){
				line_column_data[line_column_data_index].xaxis[line_column_data[line_column_data_index].xaxis.length] = result.xaxis;
				line_column_data[line_column_data_index].yaxis[line_column_data[line_column_data_index].yaxis.length] = result.yaxis;
			}

			// Bar 차트 데이터 정리
			var bar_data_index;
			for (bar_data_index = 0; bar_data_index < bar_data.length; bar_data_index++) {
				if( bar_data[bar_data_index].lgdNm == result.lgdNm ){
					break;
				}
			}
			if( bar_data_index >= bar_data.length ){
				bar_data[bar_data_index] = {};
				bar_data[bar_data_index].lgdNm = result.lgdNm;
				bar_data[bar_data_index].xaxis = new Array();
				bar_data[bar_data_index].yaxis = new Array();
				bar_data[bar_data_index].yOrder = new Array();
			}
			if(bar_data[bar_data_index].yaxis[bar_data[bar_data_index].yaxis.length-1] != result.xaxis){
				bar_data[bar_data_index].xaxis[bar_data[bar_data_index].xaxis.length] = result.yaxis;
				bar_data[bar_data_index].yaxis[bar_data[bar_data_index].yaxis.length] = result.xaxis;
			}
			
			// Pie 차트 데이터 정리
			var pie_data_index;
			for (pie_data_index = 0; pie_data_index < pie_data.length; pie_data_index++) {
				if( pie_data[pie_data_index].lgdNm == result.lgdNm ){
					break;
				}
			}
			if( pie_data_index >= pie_data.length ){
				pie_data[pie_data_index] = {};
				pie_data[pie_data_index].lgdNm = result.lgdNm;
				pie_data[pie_data_index].yaxis = 0;
			}
			pie_data[pie_data_index].yaxis += result.yaxis;

			// Scatter 차트 데이터 정리
			if(isNumber(result.xaxis)){			// X축 값이 숫자 여부 판단
				var scatter_data_index;
				for (scatter_data_index = 0; scatter_data_index < scatter_data.length; scatter_data_index++) {
					if( scatter_data[scatter_data_index].lgdNm == result.lgdNm ){
						break;
					}
				}
				if( scatter_data_index >= scatter_data.length ){
					scatter_data[scatter_data_index] = {};
					scatter_data[scatter_data_index].lgdNm = result.lgdNm;
					scatter_data[scatter_data_index].xaxis = new Array();
					scatter_data[scatter_data_index].yaxis = new Array();
				}
				scatter_data[scatter_data_index].xaxis[scatter_data[scatter_data_index].xaxis.length] = result.xaxis;
				scatter_data[scatter_data_index].yaxis[scatter_data[scatter_data_index].yaxis.length] = result.yaxis;
			}
		}

		// 차트/그래프 그리기
		fn_egov_draw_linechartgraph();
		fn_egov_draw_columnchartgraph();
		fn_egov_draw_piechartgraph();
		fn_egov_draw_barchartgraph();
		if(scatter_data.length!=0){
			fn_egov_draw_scatterchartgraph();
		}
		
		// Selector 옵션에 표시 가능한 그래프 추가
		var selector = $("#select-chart");
		var graph_value_array = new Array("lineChartGraph", "columnChartGraph", "pieChartGraph", "scatterChartGraph", "barChartGraph");
		var graph_array = new Array("Line", "Column", "Pie", "Scatter", "Bar");
		var selector_html = "";
		
		$("#select-chart").children().remove();
		
		selector_html = "<option>모바일 차트/그래프</option>";
		for(var i = 0; i < graph_array.length; i++){
			if(scatter_data.length==0 && graph_array[i]=="Scatter"){
				selector_html += "<option value=\"" + graph_value_array[i] +"\" disabled=\"disabled\">" + graph_array[i] +"</option>";
				continue;
			}
			selector_html += "<option value=\"" + graph_value_array[i] +"\">" + graph_array[i] +"</option>";
		}
		$("#select-chart").append(selector_html);
		$("select#select-chart").selectmenu("refresh");
	});			
}

/*********************************************************
 * Line 차트 그리기
 ******************************************************** */
function fn_egov_draw_linechartgraph(){
	var canvas;
	var context;
	var scale_rect = [50*ratio, 50*ratio, 300*ratio, 300*ratio];	// 위치(X, Y좌표) 및 사이즈(Width, Height)
	var x_value_interval = 1;										// X축 눈금 값의 간격 (반영안됨)
	var y_value_interval = 200;										// Y축 눈금 값의 간격
	var line_width = 5*ratio;										// 선 굵기
	var radius = 5*ratio;											// 점의 반지름
	var x_max = 0;
	var x_min = Number.MAX_VALUE;
	var y_max = 0;
	var y_min = Number.MAX_VALUE;
	var x_gradations = new Array();
	var y_gradations = new Array();
	var index;
	

	canvas = document.getElementById("lineChartGraphCanvas");
	context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);

	// 눈금선 그리기 위해 눈금의 최대 최소값 설정 및 눈금 그리기
	for (var i = 0; i < line_column_data.length; i++) {
		for (var j = 0; j < line_column_data[i].xaxis.length; j++) {
			x_max = Math.max(x_max, line_column_data[i].xaxis[j]);
			x_min = Math.min(x_min, line_column_data[i].xaxis[j]);
			y_max = Math.max(y_max, line_column_data[i].yaxis[j]);
			y_min = Math.min(y_min, line_column_data[i].yaxis[j]);
			
			// X축 구분값
			for (index = 0; index < x_gradations.length; index++){
				if(line_column_data[i].xaxis[j]==x_gradations[index]){
					line_column_data[i].xOrder[j] = index;		// X축 순서 지정
					break;
				}
			}
			if(index >= x_gradations.length){
				x_gradations[index] = line_column_data[i].xaxis[j];
				line_column_data[i].xOrder[j] = index;			// X축 순서 지정
			}
		}
	}
	x_max = Math.ceil(x_max/(x_value_interval*1.0))*x_value_interval;
	x_min = Math.floor(x_min/(x_value_interval*1.0))*x_value_interval;
	y_max = Math.ceil(y_max/(y_value_interval*1.0))*y_value_interval;
	y_min = Math.floor(y_min/(y_value_interval*1.0))*y_value_interval;

	// Y축 구분값
	index = 0;
	do{
		y_gradations[index] = y_min + (y_value_interval*index);
		index++;
	}while(y_gradations[index-1]<y_max);
	
	fn_egov_draw_scalemark(context, scale_rect, x_gradations, y_gradations, false, true);

	// Line을 그리기 전 데이터 정렬 (순서대로 Line이 그려지도록 하기 위해)
	for (var i = 0; i < line_column_data.length; i++) {
		for(var j = 0; j < line_column_data[i].xOrder.length-1; j++){
			for(var k = line_column_data[i].xOrder.length - 1; k > j; k--){
				if(	line_column_data[i].xOrder[k-1] > line_column_data[i].xOrder[k] ){
					var temp = line_column_data[i].xOrder[k];
					line_column_data[i].xOrder[k] = line_column_data[i].xOrder[k-1];
					line_column_data[i].xOrder[k-1] = temp;
					
					temp = line_column_data[i].xaxis[k];
					line_column_data[i].xaxis[k] = line_column_data[i].xaxis[k-1];
					line_column_data[i].xaxis[k-1] = temp;
					
					temp = line_column_data[i].yaxis[k];
					line_column_data[i].yaxis[k] = line_column_data[i].yaxis[k-1];
					line_column_data[i].yaxis[k-1] = temp;
				}
			}
		}
	}
	
	// Line 차트/그래프 데이터 그리기
	for (var i = 0; i < line_column_data.length; i++) {
		context.fillStyle = chartGraphColor[i];
		context.strokeStyle = chartGraphColor[i];
		context.lineWidth = line_width;

		var x_point;		// X 좌표
		var y_point;		// Y 좌표
		for (var j = 0; j < line_column_data[i].xaxis.length; j++) {
			x_point = scale_rect[0]+(scale_rect[2]/(x_gradations.length*1.0))*(line_column_data[i].xOrder[j]+0.5);
			y_point = (scale_rect[1]+scale_rect[3])-(line_column_data[i].yaxis[j]-y_min)*scale_rect[3]/(y_max-y_min);

			// 점 그리기
			context.beginPath();
			context.arc(x_point, y_point, radius, 0, Math.PI*2, false);
			context.fill();
			context.closePath();
		}

		context.beginPath();
		for (var j = 0; j < line_column_data[i].xaxis.length; j++) {
			x_point = scale_rect[0]+(scale_rect[2]/(x_gradations.length*1.0))*(line_column_data[i].xOrder[j]+0.5);
			y_point = (scale_rect[1]+scale_rect[3])-(line_column_data[i].yaxis[j]-y_min)*scale_rect[3]/(y_max-y_min);

			// 선 그리기					
			context.lineTo(x_point, y_point);
		}
		context.stroke();
		context.closePath();
		
	}
	
	// 범례 그리기
	fn_egov_draw_legend(context, pie_data);
}

/*********************************************************
 * Column 차트 그리기
 ******************************************************** */
function fn_egov_draw_columnchartgraph(){
	var canvas;
	var context;
	var scale_rect = [50*ratio, 50*ratio, 300*ratio, 300*ratio];// 위치(X, Y좌표) 및 사이즈(Width, Height)
	var x_value_interval = 1;									// X축 눈금 값의 간격 (반영안됨)
	var y_value_interval = 200;									// Y축 눈금 값의 간격
	var x_margin = 5*ratio;										// X축 눈금 간 여백
	var x_bar_interval = 2*ratio;								// X축 막대 간 간격
	var x_max = 0;
	var x_min = Number.MAX_VALUE;
	var y_max = 0;
	var y_min = Number.MAX_VALUE;
	var x_gradations = new Array();
	var y_gradations = new Array();
	var index;
	

	canvas = document.getElementById("columnChartGraphCanvas");
	context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);

	// 눈금선 그리기 위해 눈금의 최대 최소값 설정 및 눈금 그리기
	for (var i = 0; i < line_column_data.length; i++) {
		for (var j = 0; j < line_column_data[i].xaxis.length; j++) {
			x_max = Math.max(x_max, line_column_data[i].xaxis[j]);
			x_min = Math.min(x_min, line_column_data[i].xaxis[j]);
			y_max = Math.max(y_max, line_column_data[i].yaxis[j]);
			y_min = Math.min(y_min, line_column_data[i].yaxis[j]);

			// X축 구분값
			for (index = 0; index < x_gradations.length; index++){
				if(line_column_data[i].xaxis[j]==x_gradations[index]){
					line_column_data[i].xOrder[j] = index;		// X축 순서 지정
					break;
				}
			}
			if(index >= x_gradations.length){
				x_gradations[index] = line_column_data[i].xaxis[j];
				line_column_data[i].xOrder[j] = index;			// X축 순서 지정
			}
		}
	}
	x_max = Math.ceil(x_max/(x_value_interval*1.0))*x_value_interval;
	x_min = Math.floor(x_min/(x_value_interval*1.0))*x_value_interval;
	y_max = Math.ceil(y_max/(y_value_interval*1.0))*y_value_interval;
	y_min = Math.floor(y_min/(y_value_interval*1.0))*y_value_interval;

	// Y축 구분값
	index = 0;
	do{
		y_gradations[index] = y_min + (y_value_interval*index);
		index++;
	}while(y_gradations[index-1]<y_max);
	
	fn_egov_draw_scalemark(context, scale_rect, x_gradations, y_gradations, false, true);

	// Column 차트/그래프 데이터 그리기
	for (var i = 0; i < line_column_data.length; i++) {
		context.fillStyle = chartGraphColor[i];
		
		context.beginPath();

		var x_point;		// X 좌표
		var y_point;		// Y 좌표
		var bar_width;		// 막대 넓이
		var bar_height;		// 막대 높이

		for (var j = 0; j < line_column_data[i].xaxis.length; j++) {
			bar_width = ((scale_rect[2]/(x_gradations.length*1.0))-(x_margin*2)-(x_bar_interval*line_column_data.length))/(line_column_data.length*1.0);
			x_point = scale_rect[0]+((scale_rect[2]/(x_gradations.length*1.0))*line_column_data[i].xOrder[j])+x_margin+(bar_width*i)+(x_bar_interval*i);
			y_point = (scale_rect[1]+scale_rect[3])-(line_column_data[i].yaxis[j]-y_min)*scale_rect[3]/(y_max-y_min);
			bar_height = (scale_rect[1]+scale_rect[3])-y_point;

			context.fillRect(x_point, y_point, bar_width, bar_height);
		}
		
		context.closePath();
	}
	
	// 범례 그리기
	fn_egov_draw_legend(context, pie_data);
}

/*********************************************************
 * Pie 차트 그리기
 ******************************************************** */
function fn_egov_draw_piechartgraph(){
	var canvas;
	var context;
	var center_x = 200*ratio;			// 중점의 X좌표
	var center_y = 200*ratio;			// 중점의 Y좌표
	var radius = 150*ratio;				// 반지름
	var lastend = Math.PI*(3.0/2.0);	// Pie 시작 위치 지정
	var line_color = "#FFF";			// 구분선 색
	var line_width = 2*ratio;			// 구분선 굵기
	var totalValue = 0;

	canvas = document.getElementById("pieChartGraphCanvas");
	context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);

	for (var i = 0; i < pie_data.length; i++) {
		totalValue += (typeof pie_data[i].yaxis == 'number') ? pie_data[i].yaxis : 0;
	}

	// Pie 차트/그래프 데이터 그리기
	for (var i = 0; i < pie_data.length && i < maxChartGraph; i++) {

		context.fillStyle = chartGraphColor[i];
		
		context.strokeStyle = line_color;
		context.lineWidth = line_width;

		context.beginPath();
		context.moveTo(center_x, center_y);
		context.arc(center_x, center_y, radius, lastend, lastend+(Math.PI*2*(pie_data[i].yaxis/totalValue)), false);
		context.lineTo(center_x, center_y);
		context.fill();
		context.stroke();
		
		// 비율 표시하기
		var percentage = (pie_data[i].yaxis/totalValue) * 1000;
		percentage = parseInt(percentage);
		percentage = percentage / 10.0;
		
		var angle = (lastend + lastend + Math.PI*2*(pie_data[i].yaxis/totalValue))/2;
		var normalisedAngle = angle;
		if(normalisedAngle > Math.PI*2){
			normalisedAngle = normalisedAngle - Math.PI*2;
		}
		else{
			normalisedAngle = normalisedAngle + Math.PI*2;
		}
		var labely = center_y + Math.sin(normalisedAngle)*(radius/2) + 10*ratio;
		var labelx = center_x + Math.cos(normalisedAngle)*(radius/2) - 20*ratio;
		
		var font_size = 15*ratio + "px";// 폰트 크기
		var font_name = "sans-serif";	// 폰트명
		var font_style = "bold";		// 폰트 스타일
		
		context.fillStyle = '#FFF';
		context.font = font_style + " " + font_size + " " + font_name;
		context.fillText(" " + percentage + "%", labelx, labely);
		
		lastend += Math.PI*2*(pie_data[i].yaxis/totalValue);
		context.closePath();
	}

	// 범례 그리기
	fn_egov_draw_legend(context, pie_data);
}

/*********************************************************
 * Scatter 차트 그리기
 ******************************************************** */
function fn_egov_draw_scatterchartgraph(){
	var canvas;
	var context;
	var scale_rect = [50*ratio, 50*ratio, 300*ratio, 300*ratio];	// 위치(X, Y좌표) 및 사이즈(Width, Height)
	var x_value_interval = 1;										// X축 눈금 값의 간격
	var y_value_interval = 200;										// Y축 눈금 값의 간격
	var radius = 5*ratio;											// 점의 반지름
	var x_max = 0;
	var x_min = Number.MAX_VALUE;
	var y_max = 0;
	var y_min = Number.MAX_VALUE;
	var x_gradations = new Array();
	var y_gradations = new Array();
	var index;
	
	canvas = document.getElementById("scatterChartGraphCanvas");
	context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);

	// 눈금선 그리기 위해 눈금의 최대 최소값 설정 및 눈금 그리기
	for (var i = 0; i < scatter_data.length; i++) {
		for (var j = 0; j < scatter_data[i].xaxis.length; j++) {
			x_max = Math.max(x_max, scatter_data[i].xaxis[j]);
			x_min = Math.min(x_min, scatter_data[i].xaxis[j]);
			y_max = Math.max(y_max, scatter_data[i].yaxis[j]);
			y_min = Math.min(y_min, scatter_data[i].yaxis[j]);
		}
	}
	x_max = Math.ceil(x_max/(x_value_interval*1.0))*x_value_interval;
	x_min = Math.floor(x_min/(x_value_interval*1.0))*x_value_interval;
	y_max = Math.ceil(y_max/(y_value_interval*1.0))*y_value_interval;
	y_min = Math.floor(y_min/(y_value_interval*1.0))*y_value_interval;

	// X축 구분값
	index = 0;
	do{
		x_gradations[index] = x_min + (x_value_interval*index);
		index++;
	}while(x_gradations[index-1]<x_max);

	// Y축 구분값
	index = 0;
	do{
		y_gradations[index] = y_min + (y_value_interval*index);
		index++;
	}while(y_gradations[index-1]<y_max);
	
	fn_egov_draw_scalemark(context, scale_rect, x_gradations, y_gradations, true, true);

	// Scatter 차트/그래프 데이터 그리기
	for (var i = 0; i < scatter_data.length; i++) {
		context.fillStyle = chartGraphColor[i];
		for (var j = 0; j < scatter_data[i].xaxis.length; j++) {
			var xaxis = scale_rect[0]+(scatter_data[i].xaxis[j]-x_min)*scale_rect[2]/(x_max-x_min);
			var yaxis = (scale_rect[1]+scale_rect[3])-(scatter_data[i].yaxis[j]-y_min)*scale_rect[3]/(y_max-y_min);
			
			context.beginPath();
			context.arc(xaxis, yaxis, radius, 0, Math.PI*2, false);
			context.fill();
			context.closePath();
		}
	}			
	
	// 범례 그리기
	fn_egov_draw_legend(context, scatter_data);
}

/*********************************************************
 * Bar 차트 그리기
 ******************************************************** */
function fn_egov_draw_barchartgraph(){
	var canvas;
	var context;
	var scale_rect = [50*ratio, 50*ratio, 300*ratio, 300*ratio];	// 위치(X, Y좌표) 및 사이즈(Width, Height)
	var x_value_interval = 200;										// X축 눈금 값의 간격
	var y_value_interval = 1;										// Y축 눈금 값의 간격 (반영안됨)
	var y_margin = 5*ratio;											// Y축 눈금 간 여백
	var y_bar_interval = 2*ratio;									// Y축 막대 간 간격
	var x_max = 0;
	var x_min = Number.MAX_VALUE;
	var y_max = 0;
	var y_min = Number.MAX_VALUE;
	var x_gradations = new Array();
	var y_gradations = new Array();
	var index;
	

	canvas = document.getElementById("barChartGraphCanvas");
	context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);

	// 눈금선 그리기 위해 눈금의 최대 최소값 설정 및 눈금 그리기
	for (var i = 0; i < bar_data.length; i++) {
		for (var j = 0; j < bar_data[i].yaxis.length; j++) {
			x_max = Math.max(x_max, bar_data[i].xaxis[j]);
			x_min = Math.min(x_min, bar_data[i].xaxis[j]);
			y_max = Math.max(y_max, bar_data[i].yaxis[j]);
			y_min = Math.min(y_min, bar_data[i].yaxis[j]);

			// Y축 구분값
			for (index = 0; index < y_gradations.length; index++){
				if(bar_data[i].yaxis[j]==y_gradations[index]){
					bar_data[i].yOrder[j] = index;		// Y축 순서 지정
					break;
				}
			}
			if(index >= y_gradations.length){
				y_gradations[index] = bar_data[i].yaxis[j];
				bar_data[i].yOrder[j] = index;			// Y축 순서 지정
			}
		}
	}
	
	x_max = Math.ceil(x_max/(x_value_interval*1.0))*x_value_interval;
	x_min = Math.floor(x_min/(x_value_interval*1.0))*x_value_interval;
	y_max = Math.ceil(y_max/(y_value_interval*1.0))*y_value_interval;
	y_min = Math.floor(y_min/(y_value_interval*1.0))*y_value_interval;

	// X축 구분값
	index = 0;
	do{
		x_gradations[index] = x_min + (x_value_interval*index);
		index++;
	}while(x_gradations[index-1]<x_max);
	
	fn_egov_draw_scalemark(context, scale_rect, x_gradations, y_gradations, true, false);

	// Bar 차트/그래프 데이터 그리기
	for (var i = 0; i < bar_data.length; i++) {
		context.fillStyle = chartGraphColor[i];
		
		context.beginPath();

		var x_point;		// X 좌표
		var y_point;		// Y 좌표
		var bar_width;		// 막대 넓이
		var bar_height;		// 막대 높이

		for (var j = 0; j < bar_data[i].yaxis.length; j++) {
			bar_height = ((scale_rect[3]/(y_gradations.length*1.0))-(y_margin*2)-(y_bar_interval*bar_data.length))/(bar_data.length*1.0);
			y_point = (scale_rect[1]+scale_rect[3])-(((scale_rect[3]/(y_gradations.length*1.0))*bar_data[i].yOrder[j])+y_margin+(bar_height*(i+1))+(y_bar_interval*i));
			x_point = scale_rect[0];
			bar_width = (bar_data[i].xaxis[j]-x_min)*scale_rect[2]/((x_max-x_min)*1.0);
			
			context.fillRect(x_point, y_point, bar_width, bar_height);
		}
		
		context.closePath();
	}
	
	// 범례 그리기
	fn_egov_draw_legend(context, bar_data);
}

/*********************************************************
 * 눈금선 그리기
 ******************************************************** */
function fn_egov_draw_scalemark(context, scale_rect, x_gradations, y_gradations, x_line, y_line){

	var main_line_color = "#000";			// 메인 눈금선 색
	var assist_line_color = "#CCC";			// 보조 눈금선 색
	var main_line_width = 2*ratio;			// 메인 눈금선 굵기
	var assist_line_width = 1*ratio;		// 보조 눈금선 굵기
	var font_size = 15*ratio + "px";		// 폰트 크기
	var font_name = "sans-serif";			// 폰트명
	var font_style = "bold";				// 폰트 스타일
	var x_line_margin = 10;					// X축 눈금값 여백
	var y_line_margin = 40*ratio;			// y축 눈금값 여백
	var x_label_position = 0.2;				// x축 눈금값 위치 조정 (0~1 : 값에 따라 눈금값 이동)
	var x_line_cnt = 0;
	var y_line_cnt = 0;
	var x_line_interval = 0.0;
	var y_line_interval = 0.0;

	if(x_line == true){
		x_line_cnt = x_gradations.length-1;
	}else{
		x_line_cnt = x_gradations.length;
	}
	if(y_line == true){
		y_line_cnt = y_gradations.length-1;
	}else{
		y_line_cnt = y_gradations.length;
	}
				
	x_line_interval = scale_rect[2]/(x_line_cnt*1.0);
	y_line_interval = scale_rect[3]/(y_line_cnt*1.0);

	// 메인 눈금선 그리기
	context.strokeStyle = main_line_color;
	context.lineWidth = main_line_width;
	
	context.beginPath();
	context.moveTo(scale_rect[0], scale_rect[1]+scale_rect[3]);
	context.lineTo(scale_rect[0], scale_rect[1]);
	context.moveTo(scale_rect[0], scale_rect[1]+scale_rect[3]);
	context.lineTo(scale_rect[0]+scale_rect[2], scale_rect[1]+scale_rect[3]);			
	context.stroke();
	context.closePath();


	// 보조 눈금선 그리기
	context.strokeStyle = assist_line_color;
	context.lineWidth = assist_line_width;
	context.font = font_style + " " + font_size + " " + font_name;
	
	// X축 보조 눈금선(세로선) 그리기		
	context.beginPath();
	context.textBaseline = "top";			
	context.fillStyle = "#000";
	if(x_line == true){				
		for(var i = 0; i < x_line_cnt; i++){
			context.moveTo(scale_rect[0]+(x_line_interval*(i+1)), scale_rect[1]+scale_rect[3]);
			context.lineTo(scale_rect[0]+(x_line_interval*(i+1)), scale_rect[1]);

			context.fillText(x_gradations[i], scale_rect[0]+(x_line_interval*i)-(x_line_interval*x_label_position), scale_rect[1] + scale_rect[3]+x_line_margin);
		}
		context.fillText(x_gradations[i], scale_rect[0]+(x_line_interval*i)-(x_line_interval*x_label_position), scale_rect[1] + scale_rect[3]+x_line_margin);
	}else{
		for(var i = 0; i < x_line_cnt; i++){
			context.fillText(x_gradations[i], scale_rect[0]+(x_line_interval*i)+(x_line_interval*x_label_position), scale_rect[1] + scale_rect[3]+x_line_margin);
		}
	}
	context.stroke();
	context.closePath();
	
	// Y축 보조 눈금선(가로선) 그리기
	context.beginPath();
	context.textBaseline = "middle";					
	if(y_line == true){
		
		for(var i = 0; i < y_line_cnt; i++){
			context.moveTo(scale_rect[0], scale_rect[1]+scale_rect[3]-(y_line_interval*(i+1)));
			context.lineTo(scale_rect[0] + scale_rect[2], scale_rect[1]+scale_rect[3]-(y_line_interval*(i+1)));

			context.fillText(y_gradations[i], scale_rect[0]-y_line_margin, scale_rect[1]+scale_rect[3]-(y_line_interval*i));	
		}
		context.fillText(y_gradations[i], scale_rect[0]-y_line_margin, scale_rect[1]+scale_rect[3]-(y_line_interval*i));
	}else{
		for(var i = 0; i < y_line_cnt; i++){
			context.fillText(y_gradations[i], scale_rect[0]-y_line_margin, scale_rect[1]+scale_rect[3]-(y_line_interval*i)-(y_line_interval*0.5));	
		}
	}
	context.stroke();
	context.closePath();
}

/*********************************************************
 * 범례 그리기
 ******************************************************** */
function fn_egov_draw_legend(context, data){
	
	var start_x = 400*ratio;		// 시작 위치의 X좌표
	var start_y = 50;				// 시작 위치의 Y좌표
	var interval = 20;				// 범례 간격
	var mark_size = 15*ratio;		// 마크 크기
	var mark_interval = 5*ratio;	// 마크와 범례명 간격
	var font_size = 15*ratio + "px";// 폰트 크기
	var font_name = "sans-serif";	// 폰트명
	var font_style = "bold";		// 폰트 스타일

	for (var i = 0; i < data.length && i < maxChartGraph; i++) {
		context.save();
		context.fillStyle = chartGraphColor[i];
		context.translate(start_x, start_y + interval*i);
		context.fillRect(0, 0, mark_size, mark_size);
		context.font = font_style + " " + font_size + " " + font_name;
		context.textBaseline = "top";
		context.fillText(data[i].lgdNm, mark_size + mark_interval, 0);
		context.restore();
	}
}

/*********************************************************
 * 문자열에 대한 숫자 여부 판단
 ******************************************************** */
function isNumber(str) {
	str += '';								// 문자열로 변환
	str = str.replace(/^\s*|\s*$/g, ''); 	// 좌우 공백 제거
	if (str == '' || isNaN(str)) return false;
	return true;
}

/*********************************************************
 * 차트 페이지로 이동
 ******************************************************** */ 
function fn_egov_show_chartGraph() {
	 var selection = $("#select-chart");
	 var selected;

	 $("#ChartGraphNavi").children().remove();
	 for(var i = 0; i < selection.val().length; i++){
		 selected = selection.val()[i];
		 if(selected == "lineChartGraph"){
			 selected = "Line 차트 / 그래프";
		 }
		 else if(selected == "columnChartGraph"){
			 selected = "Column 차트 / 그래프";
		 }
		 else if(selected == "pieChartGraph"){
			 selected = "Pie 차트 / 그래프";
		 }
		 else if(selected == "scatterChartGraph"){
			 selected = "Scatter 차트 / 그래프";
		 }
		 else if(selected == "barChartGraph"){
			 selected = "Bar 차트 / 그래프";
		 }
		 		 
		 var navi_html = "<li><a href=\"#"+ selection.val()[i] + "\" data-ajax=\"false\">" + selected +"</a></li>";
		 $("#ChartGraphNavi").append(navi_html);
	 }
	 
	 // 'Location Hash' 비 생성
	 $.mobile.changePage("#ChartGraph", "none", false, false);
}
