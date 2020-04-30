/*
 * 요소기술 스크립트  
 */

/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
};

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
};

String.prototype.getLpad = function(rtnSize){
    var str = this;
    for(var i= str.length; i<parseInt(rtnSize); i++)
    	str =  "0" + str;
    return str;
};

/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */

// 숫자체크
function isNumber(control, msg) {
	
	var val = control;
	var Num = "1234567890";
	for (var i=0; i<val.length; i++) {
		if(Num.indexOf(val.substring(i,i+1))<0) {
			alert(msg+' 형식이 잘못되었습니다.');
			return false;
		}
	}
	return true;
}

// 날짜체크
function isDate(control, msg) {
	
	// '/'나 '-' 구분자 제거
	var val = getRemoveFormat(control);
	
	// 숫자, length 확인
	if (isNumber(val, msg) && val.length == 8) {
		var year = val.substring(0,4);
		var month = val.substring(4,6);
		var day = val.substring(6,8);
		
		// 유효날짜 확인
		if(checkDate(year,month,day,msg)){
			return true;
		} else {
			return false;
		}
	} else {
		alert(msg + " 유효하지 않은 년,월,일(YYYYMMDD)입니다. 다시 확인해 주세요!");
		return false;
	}
}

// 구분자 제거
function getRemoveFormat(val) {
	if(val.length == 10) {
		var arrDate = new Array(3);
		arrDate = val.split("/");
		if(arrDate.length != 3) {
			arrDate = val.split("-");
		}
		return arrDate[0] + arrDate[1] + arrDate[2];
	} else {
		return val;
	}	
}

// 유효날짜 확인
function checkDate(varCk1, varCk2, varCk3, msg) {
	if (varCk1>="0001" && varCk1<="9999" && varCk2>="01" && varCk2<="12") {
		febDays = "29";
		if ((parseInt(varCk1,10) % 4) == 0) {
			if ((parseInt(varCk1,10) % 100) == 0 && (parseInt(varCk1,10) % 400) != 0){
				febDays = "28";
			}
		}else{
			febDays = "28";
		}
		if (varCk2=="01" && varCk3>="01" && varCk3<="31") return true;
		if (varCk2=="02" && varCk3>="01" && varCk3<=febDays) return true;
		if (varCk2=="03" && varCk3>="01" && varCk3<="31") return true;
		if (varCk2=="04" && varCk3>="01" && varCk3<="30") return true;
		if (varCk2=="05" && varCk3>="01" && varCk3<="31") return true;
		if (varCk2=="06" && varCk3>="01" && varCk3<="30") return true;
		if (varCk2=="07" && varCk3>="01" && varCk3<="31") return true;
		if (varCk2=="08" && varCk3>="01" && varCk3<="31") return true;
		if (varCk2=="09" && varCk3>="01" && varCk3<="30") return true;
		if (varCk2=="10" && varCk3>="01" && varCk3<="31") return true;
		if (varCk2=="11" && varCk3>="01" && varCk3<="30") return true;
		if (varCk2=="12" && varCk3>="01" && varCk3<="31") return true;
	}
	alert(msg + " 유효하지 않은 년,월,일(YYYYMMDD)입니다. 다시 확인해 주세요!");
	return false;
}

/************************************************************************
함수명 : fn_aram_RadioBoxChecked                                   
설   명 : 라디오박스 입력된 값 체크설정                
인   자 : ssbName 객체이름    setValue 값        
사용법 :   fn_aram_RadioBoxChecked(sbName,setValue) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
 2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_RadioBoxChecked(sbName, setValue)
{
	var FLength = document.getElementsByName(sbName).length;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].value == setValue){
			document.getElementsByName(sbName)[i].checked = true;
			return;
		}
	}
}

/************************************************************************
함수명 : fn_aram_RadioBoxValue                                   
설   명 : 라디오박스 값가져오기                
인   자 : ssbName 객체이름        
사용법 :   fn_aram_RadioBoxValue(sbName) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
 2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++) {
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	
	return FValue;
}

/************************************************************************
함수명 : fn_aram_RadioBoxDisabled                                   
설   명 : 라디오박스 값가져오기                
인   자 : ssbName 객체이름, sbStatus 상태값 true,false      
사용법 :   fn_aram_RadioBoxDisabled(sbName,sbStatus) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
 2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_RadioBoxDisabled(sbName, sbStatus)
{
	var FLength = document.getElementsByName(sbName).length;
	for(var i=0; i < FLength; i++) {
			document.getElementsByName(sbName)[i].disabled=sbStatus;
	}
}

/************************************************************************
함수명 : fn_aram_RadioBox                                   
설   명 : 라디오박스 index 설정               
인   자 : ssbName 객체이름, sbValue value값       
사용법 : fn_aram_RadioBox(sbName, sbValue) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_RadioBox(sbName, sbValue)
{
	var FLength= document.getElementsByName(sbName).length;
	for(var i=0; i < FLength; i++) {
		if(document.getElementsByName(sbName)[i].value == sbValue){
			document.getElementsByName(sbName)[i].checked=true;
		}
	}
}

/************************************************************************
함수명 : fn_aram_SelectBoxText                                   
설   명 : 셀렉트박스 텍스트 가져오기                
인   자 : ssbName 객체이름, sbStatus       
사용법 :   fn_aram_SelectBoxText(sbName) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
 2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_SelectBoxText(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++) {
		if(document.getElementById(sbName).options[i].selected == true){
			FValue=document.getElementById(sbName).options[i].text;
		}
	}
	return  FValue;
}

/************************************************************************
함수명 : fn_aram_SelectBoxText                                   
설   명 : 셀렉트박스 값 가져오기                
인   자 : ssbName 객체이름, sbStatus       
사용법 :   fn_aram_SelectBoxText(sbName) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
 2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++) {
		if(document.getElementById(sbName).options[i].selected == true){
			FValue=document.getElementById(sbName).options[i].value;
		}
	}
	return  FValue;
}

/************************************************************************
함수명 : fn_aram_SelectBox                                   
설   명 : 셀렉트박스 index 설정               
인   자 : ssbName 객체이름, sbValue value값       
사용법 : fn_aram_SelectBox(sbName, sbValue) 
작성일 : 2009-02-01   
작성자 : 각팀명(예. 공통서비스 개발팀) 홍길동       
수정일       수정자             수정내용
------      ------     -------------------
 2009.03.12    장동한        신규작업                                       
************************************************************************/
function fn_aram_SelectBox(sbName, sbValue)
{
	for(var i=0; i < document.getElementById(sbName).length; i++) {
		if(document.getElementById(sbName).options[i].value == sbValue){
			document.getElementById(sbName).options[i].selected = true;
		}
	}
}

/************************************************************************
//셀렉트 박스 선택했는 찾는 함수
************************************************************************/
function fn_aram_selectBoxChecking(sbName){
	var result = false;
	for(var i=0; i < document.getElementsByName(sbName).length; i++){
		if(document.getElementsByName(sbName)[i].checked == true){
			result=true;
		}
	}
	return result;
}

/**
 * prefix 로부터 숫자 id를 추출
 * @param paginationInfo
 * @return
 */
function fn_aram_get_idString(original) {
	var index = original.lastIndexOf("_")+1;
	if( original.charAt(index) != '0' ) return original;

	while( index < original.length ) {
		if( original.charAt(index) != '0' ) break;
		index++;
	}
	return (index == original.length) ? original : original.substring(index);
}
