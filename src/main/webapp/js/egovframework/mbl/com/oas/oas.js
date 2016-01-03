/*********************************************************
 * 대한민국정부포털 검색 관련 함수
 ******************************************************** */

/*********************************************************
 * 검색조건명 취득
 ******************************************************** */
function fn_egov_get_conditionNm(condition) {
	var collectionSet = ["site" , "service" , "web" , "newstoday" , "pubword" , "mobapp" , "mobweb" , "law", "map"];
	var collectionName = ["사이트" , "서비스" , "웹문서" , "뉴스" , "행정용어" , "모바일앱" , "모바일웹" , "법령", "서비스맵"];

	for (var i = 0; i < collectionSet.length; i++) {
		if (collectionSet[i] == condition) {
			return collectionName[i];
		}    
	}

	// 매칭되는 것이 없을 때 기본값 리턴
	return collectionName[0];
}



/*********************************************************
 * 기상청 날씨 조회 관련 함수
 ******************************************************** */

/*********************************************************
 * 일반 날씨 판정
 ******************************************************** */
function fn_egov_judge_weather(weatherStatusNumber, cloudAmouant) {
	var icon = "";
	var desc = "";

  	if (weatherStatusNumber == "" || weatherStatusNumber < 5) {
  		icon = fn_egov_judge_cloudAmouant(cloudAmouant);
  	}

  	if ( (weatherStatusNumber >= 80 && weatherStatusNumber <= 82) || (weatherStatusNumber >= 89 && weatherStatusNumber <= 90) )
    	icon = "07";
 	else if (weatherStatusNumber >= 50 && weatherStatusNumber <= 67)
    	icon = "08";
  	else if ( (weatherStatusNumber >= 36 && weatherStatusNumber <= 39) || (weatherStatusNumber >= 70 && weatherStatusNumber <= 79) || (weatherStatusNumber >= 85 && weatherStatusNumber <= 88) )
    	icon = "11";
  	else if ( (weatherStatusNumber >= 68 && weatherStatusNumber <= 69) || (weatherStatusNumber >= 83 && weatherStatusNumber <= 84) )
	    icon = "13";
  	else if ( weatherStatusNumber == 13 || weatherStatusNumber == 17 || (weatherStatusNumber >= 91 && weatherStatusNumber <= 99) )
	    icon = "14";
	else if ( (weatherStatusNumber >= 11 && weatherStatusNumber <= 12) || (weatherStatusNumber >= 40 && weatherStatusNumber <= 49) )  // 안개
		icon = "15";
  	else if ( weatherStatusNumber == 10 ) //박무
    	icon = "17";
  	else if (weatherStatusNumber == 6 || (weatherStatusNumber >= 30 && weatherStatusNumber <= 35))
    	icon = "16";
  	else if (weatherStatusNumber >= 14 && weatherStatusNumber <= 16)
    	icon = "08";

  	return icon;
}

/*********************************************************
 * 전운량(cloud_amount)을 통한 날씨 판정
 ******************************************************** */
function fn_egov_judge_cloudAmouant(cloudAmouant) { 
	icon = "";
  
	if (cloudAmouant == "" || cloudAmouant < 0) return icon;
  
  	if      (cloudAmouant <= 2) icon = "01";
  	else if (cloudAmouant <= 5) icon = "02";
  	else if (cloudAmouant <= 8) icon = "03";
  	else                   		icon = "04";
    
  	return icon;
}

/*********************************************************
 * 날씨명 취득
 ******************************************************** */
function fn_egov_get_weather(icon) {
	var desc = "";
	
	switch (icon) {
    	case "01": desc = "맑음"; break;
        case "02": desc = "구름조금"; break;
        case "03": desc = "구름많음"; break;
        case "04": desc = "흐림"; break;
        case "07": desc = "소나기"; break;
        case "08": desc = "비"; break;
        case "11": desc = "눈"; break;
        case "13": desc = "비 또는 눈"; break;
        case "14": desc = "천둥번개"; break;
        case "15": desc = "안개"; break;
        case "16": desc = "황사"; break;
        case "17": desc = "박무"; break;
        default:   desc = "-";
	}

	return desc;
}

/*********************************************************
 * 원하는 포맷으로 현재날짜 변환
 * dateFormat : 출력 포맷
 * yyyy : 년, mm : 월(2자리), m : 월, mmm : 월(영어), dd : 일(2자리), d : 일, ddd : 요일(영어), D : 요일(한글)
 ******************************************************** */
function fn_egov_get_today(dateFormat) {
	var date = new Date();
	var weekEng = new Array("Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat");
	var weekKor = new Array("일", "월", "화", "수", "목", "금", "토");
	var monthEng = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

	for (var i = 0; i < dateFormat.length; i++) {
		if (dateFormat.substr(i, 4) == 'yyyy') {
			dateFormat = dateFormat.substr(0, i) + date.getFullYear() + dateFormat.substr(i + 4, dateFormat.length);
			i += 4;
		} else if (dateFormat.substr(i, 3) == 'mmm') {
			dateFormat = dateFormat.substr(0, i) + monthEng[date.getMonth()] + dateFormat.substr(i + 3, dateFormat.length);
			i += 3;
		} else if (dateFormat.substr(i, 2) == 'mm') {
			dateFormat = dateFormat.substr(0, i) + fn_egov_fill_char(date.getMonth() + 1, 2, "0") + dateFormat.substr(i + 2, dateFormat.length);
			i += 2;
		} else if (dateFormat.substr(i, 1) == 'm') {
			dateFormat = dateFormat.substr(0, i) + (date.getMonth() + 1) + dateFormat.substr(i + 1, dateFormat.length);
			i += 1;
		} else if (dateFormat.substr(i, 3) == 'ddd') {
			dateFormat = dateFormat.substr(0, i) + weekEng[date.getDay()] + dateFormat.substr(i + 3, dateFormat.length);
			i += 3;
		} else if (dateFormat.substr(i, 2) == 'dd') {
			dateFormat = dateFormat.substr(0, i) + fn_egov_fill_char(date.getDate(), 2, "0") + dateFormat.substr(i + 2, dateFormat.length);
			i += 2;
		} else if (dateFormat.substr(i, 1) == 'd') {
			dateFormat = dateFormat.substr(0, i) + date.getDate() + dateFormat.substr(i + 1, dateFormat.length);
			i += 1;
		} else if (dateFormat.substr(i, 1) == 'D') {
			dateFormat = dateFormat.substr(0, i) + weekKor[date.getDay()] + dateFormat.substr(i + 1, dateFormat.length);
			i += 1;
		}
	}
	
	return dateFormat;
}

/*********************************************************
 * 원하는 문자로 원하는 크기만큼 채우는 함수
 * orgStr : 적용할 문자열
 * n : 원하는 크기
 * c : 채울 문자
 ******************************************************** */
function fn_egov_fill_char(orgStr, n, c) {
	var fillChar = "";
	orgStr = orgStr.toString();
	
	if (orgStr.length < n) {
		for (var i = 0; i < n - orgStr.length; i++) {
			fillChar += c;
		}
	}
			
	return fillChar + orgStr;
}