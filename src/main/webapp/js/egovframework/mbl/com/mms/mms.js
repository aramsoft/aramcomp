/*********************************************************
 * validation 체크
 ******************************************************** */
function validateMmsTransInfo(form) {                                                                   
	return validateRequired(form) && validateMinLength(form) && validateMask(form); 
}

/*********************************************************
 * 필수입력 체크
 ******************************************************** */
function validateRequired(form) {
	var isValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oRequired = new required();

	for (x in oRequired) {
		var field = form[oRequired[x][0]];

		if (trim(field.value).length == 0) {
			if (i == 0) {
				focusField = field;
			}
			fields[i++] = oRequired[x][1];
			isValid = false;
		}
	}

	if (fields.length > 0) {
		if (focusField != null) {
			focusField.focus();
		}
		jAlert(fields.join('\n'), '입력 오류', 'a');
	}

	return isValid;
}

/*********************************************************
 * 좌, 우 공백 제거
 ******************************************************** */
function trim(s) {
	return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}


function required() { 
	this.aa = new Array("trnsmisNo", "송신번호를 입력해 주십시오.");
	this.ab = new Array("recptnNo", "수신번호를 입력해 주십시오.");
	this.ac = new Array("mssageSj", "제목을 입력해 주십시오.");
	this.ad= new Array("mssageCn", "전송내용을 입력해 주십시오.");
}

/*********************************************************
 * 최소 입력 길이 체크
 ******************************************************** */
function validateMinLength(form) {
	var isValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oMinLength = new minlength();

	for (x in oMinLength) {
		var field = form[oMinLength[x][0]];
		var iMin = parseInt(oMinLength[x][2]("minlength"));

		if ((trim(field.value).length > 0) && (field.value.length < iMin)) {
			if (i == 0) {
				focusField = field;
			}

			fields[i++] = oMinLength[x][1];
			isValid = false;
		}
	}

	if (fields.length > 0) {
		focusField.focus();
		jAlert(fields.join('\n'), '입력 오류', 'a');
	}

	return isValid;
}

function minlength () { 
	this.aa = new Array("trnsmisNo", "송신번호는 9자 이상 입력해야 합니다.", new Function ("varName", "this.minlength='9';  return this[varName];"));
	this.ab = new Array("recptnNo", "수신번호는 9자 이상 입력해야 합니다.", new Function ("varName", "this.minlength='9';  return this[varName];"));
}

/*********************************************************
 * Mask 체크
 ******************************************************** */
function validateMask(form) {
	var isValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oMasked = new mask();

	for (x in oMasked) {
		var field = form[oMasked[x][0]];

		if (!matchPattern(field.value, oMasked[x][2]("mask"))) {
			if (i == 0) {
				focusField = field;
			}

			fields[i++] = oMasked[x][1];
			isValid = false;
		}
	}

	if (fields.length > 0) {
		focusField.focus();
		jAlert(fields.join('\n'), '입력 오류', 'a');
	}

	return isValid;
}

function matchPattern(value, mask) {
	return mask.exec(value);
} 

function mask () { 
	this.aa = new Array("trnsmisNo", "송신번호가 유효하지 않습니다.", new Function ("varName", "this.mask=/^01[0-9]{8,9}/;  return this[varName];"));
	this.ab = new Array("recptnNo", "수신번호가 유효하지 않습니다.", new Function ("varName", "this.mask=/^01[0-9]{8,9}/;  return this[varName];"));
}