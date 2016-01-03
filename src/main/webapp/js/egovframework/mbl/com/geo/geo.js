/*********************************************************
 * 조회 범위 내 건물 판단
 ******************************************************** */
function fn_select_building(lat1, lon1, scope, list) {

	var distance;
	var resultBuldList = new Array();
	var j = 0;

	for (var i = 0; i < list.length; i++) {
		distance = calcDistance(list[i].la, list[i].lo, lat1, lon1);

		if (distance <= scope) {
			resultBuldList[j++] = list[i];
		}
	}

	return resultBuldList;
}

/*********************************************************
* 거리 측정
******************************************************** */
function calcDistance(lat1, lon1, lat2, lon2){
	var EARTH_R, Rad, radLat1, radLat2, radDist; 
	var distance, ret;

	EARTH_R = 6371000.0;
	Rad = Math.PI/180;
	radLat1 = Rad * lat1;
	radLat2 = Rad * lat2;
	radDist = Rad * (lon1 - lon2);

	distance = Math.sin(radLat1) * Math.sin(radLat2);
	distance = distance + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radDist);
	ret = EARTH_R * Math.acos(distance);

	return  Math.round(Math.round(ret));
}