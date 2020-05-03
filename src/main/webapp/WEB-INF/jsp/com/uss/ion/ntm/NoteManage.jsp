<!doctype html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : NoteManage.jsp
 * @Description : 쪽지 관리(보내기)
 * @Modification Information
 * @
 * @ 수정일              수정자          수정내용
 * @ ----------   ------   ---------------------------
 * @ 2014.11.11   조헌철          최초 생성
 *
 *  @author 아람컴포넌트 조헌철
 *  @since 2014.11.11
 *  @version 1.0
 *  @see
 *
 */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>쪽지 관리(보내기)</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/com.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/com/cmm/button.css" type="text/css">

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<DIV id="main">

<div class="content_title">
	<h2>쪽지 관리(보내기)</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
			<span class="button"><a href="#" onclick="javascript:fn_aram_insert_noteManage(); return false;"><spring:message code="button.save" /></a></span>
		</span>
	</div>	
</div>

<!-- 등록폼 시작  -->
<form:form modelAttribute="noteManageVO" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<!--  등록  폼 영역  -->
<table class="table-register" summary="쪽지관리  입력을 제공한다.">
<caption>쪽지관리  입력을 제공한다</caption>
	<tr>
		<th width="20%">
			<span class="required_icon"></span>
			<label for="noteSj">제목</label>
		</th>
		<td width="80%">
			<form:input path="noteSj" title="제목" size="73" cssClass="txInput" maxlength="255"/>
			<form:errors path="noteSj" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			<label id="IdTwitterPw">수신자</label>
		</th>
		<td>
	   		<!--  수신자  목록/찾기 영역  -->
			<div style="float:left;">
				<select name="recptnEmp" title="수신자목록" id="recptnEmp" size="7" style="width:230px;height:80px;" multiple>
					<option value=''></option>
				</select>
				<form:errors path="recptnEmpList" cssClass="error"/>
	   		</div>
	   		<div style="float:left;">
		   		<div style="float:left;padding:10px 10px 10px 30px;">
					<form:radiobutton path="recptnSe" value="1"/>수신
					<form:radiobutton path="recptnSe" value="2"/>참조
					<form:errors path="recptnSe" cssClass="error"/>
					<a href="/uss/ion/ntm/listNoteEmpPopup.do" target="_blank" title="수신자/참조자 선택  팝업 새창으로" 
						onclick="fn_aram_popupOpen_PopupManage('/uss/ion/ntm/listNoteEmpPopup.do','empPopup',760,400); return false;">
						<img src="${pageContext.request.contextPath}/images/com/cmm/icon/search.gif" alt="수신자찾기버튼" title="수신자찾기버튼">
					</a>
		   		</div>
				<div class="button_area" style="clear:both;float:left;padding:10px;">
					<span class="button"><a href="#" onclick="javascript:fn_aram_delete_noteManage(1); return false;"><spring:message code="button.delete" /></a></span>
					<span class="button"><a href="#" onclick="javascript:fnMenuMoveUp(document.getElementById('recptnEmp')); return false;">&nbsp;▲&nbsp;</a></span>
					<span class="button"><a href="#" onclick="javascript:fnMenuMoveDown(document.getElementById('recptnEmp')); return false;">&nbsp;▼&nbsp;</a></span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<th>
			<span class="required_icon"></span>
			<label for="noteCn">쪽지 내용</label>
		</th>
		<td>
			<form:textarea path="noteCn" title="쪽지내용" rows="75" cols="14" cssClass="txArea2"/>
			<form:errors path="noteCn" cssClass="error"/>
		</td>
	</tr>
</table>

<!-- 수신자목록리스트 -->
<input type="hidden" name="recptnEmpList" id="recptnEmpList" value="">
<!-- 수신자구분리스트 -->
<input type="hidden" name="recptnSeList" id="recptnSeList" value="">

</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="noteManageVO" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javascript">
_editor_url = "${pageContext.request.contextPath}/html/htmlarea4.0/";
_editor_area = "noteCn";
_editor_lang = "kr";
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/html/htmlarea4.0/htmlarea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/com/cmm/utl/HtmlEditor.js"></script>

<script type="text/javascript">

window.onload = function() {
	HTMLArea.onload = initEditor;
	HTMLArea.init(); 
	fn_aram_init_noteManage();
};

/* ********************************************************
* 초기화
******************************************************** */
function fn_aram_init_noteManage(){

	//수신구분 초기화
	document.getElementsByName("recptnSe")[0].checked = true;

   //초기 recptnEmp 삭제 0
   	document.getElementById("recptnEmp").options[0].selected = true;
	fn_aram_delete_noteManage(0);

	<c:if test="${cmd eq 'reply'}">
	//답변 수신자 처리
	var option = document.createElement("option");
	option.appendChild(document.createTextNode("수신:${noteManageMap.trnsmiterNm}(${noteManageMap.trnsmiterId})"));
	option.setAttribute("value", "${noteManageMap.trnsmiterOrgId}");
	option.recptnSe = "1";
	document.getElementById("recptnEmp").appendChild(option);
	</c:if>
}

/* ********************************************************
* 저장
******************************************************** */
function fn_aram_insert_noteManage(){
    var varForm = document.getElementById("noteManageVO");
	varForm.onsubmit();		// for ending of htmleditor

	//수신자 처리
	fn_aram_empList_noteManage();

	if(!validateNoteManageVO(varForm)){
		return;
	}

	if(confirm("<spring:message code="common.save.msg" />")){
		varForm.action = "/uss/ion/ntm/insertNote.do";
		varForm.submit();
	}
}

/* ********************************************************
* 팝업창에서 수진자 목록에서 값받기
******************************************************** */
function fn_aram_recptnEmpOption_noteManage(sText,sValue,sRecptnSe){
	//수신자가 중복 될때 빠져 나가기
	if(fn_aram_recptnEmpSearch_noteManage(sValue)){return;};

	var option = document.createElement("option");
	option.appendChild(document.createTextNode(sText));
	option.setAttribute("value", sValue);
	option.recptnSe = sRecptnSe;
	document.getElementById("recptnEmp").appendChild(option);
}

/* ********************************************************
* 수신자 목록 / 참조목록
******************************************************** */
function fn_aram_empList_noteManage(){
	var sbName = "recptnEmp";
	var FValue = document.getElementById(sbName).length;
	var sEmpList = "";
	var sRecptnSeList = "";
	var a = document.getElementById(sbName).options[0].value;
	for(var i=0; i < FValue; i++)
	{
		if(document.getElementById(sbName).options[i].value != ""){
			sEmpList = sEmpList + document.getElementById(sbName).options[i].value + ",";
			sRecptnSeList = sRecptnSeList + document.getElementById(sbName).options[i].recptnSe + ",";
		}

		if(document.getElementById(sbName).value != ""){
			sEmpList = sEmpList + document.getElementById(sbName).value;
		}
	}
	sEmpList = sEmpList.substring(0,sEmpList.length-1);

	sRecptnSeList = sRecptnSeList.substring(0,sRecptnSeList.length-1);

	document.getElementById("recptnEmpList").value = sEmpList;
	document.getElementById("recptnSeList").value = sRecptnSeList;
}

/* ********************************************************
* 수신자 삭제
******************************************************** */
function fn_aram_delete_noteManage(nChk){
	var sbName = "recptnEmp";
	var FValue = document.getElementById(sbName).length;
	var DValue = 0;

	//삭제시 삭제 갯수 체크
	if(nChk){
		if(FValue == 0 || document.getElementById(sbName).selectedIndex == -1){
			alert("삭제 가능한 수신자  목록이 없습니다!");
			document.getElementById(sbName).focus();
			return;
		}
	}

	for(var i=FValue-1; i>= 0; i--)
	{
		if(document.getElementById(sbName).options[i].selected == true){
			DValue++;
			document.getElementById(sbName).options[i] = null;
		}
	}

	document.getElementById(sbName).length = FValue-DValue;
}

/* ********************************************************
* 수신자 찾기
******************************************************** */
function fn_aram_recptnEmpSearch_noteManage(sSearchName){
	var sbName = "recptnEmp";
	var FValue = document.getElementById(sbName).length;

	for(var i=0; i < FValue; i++)
	{
		if(document.getElementById(sbName).options[i].value == sSearchName){

			return true;
		}
	}

	return false;
}

/* ********************************************************
* 팝업창  오픈
******************************************************** */
function fn_aram_popupOpen_PopupManage(url,name,width,height){
	var left = (screen.width-684)/2;
	var top = (screen.height-500)/3;

	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus();}
}

/* ********************************************************
* 수신자 목록 이동
******************************************************** */
/* 메뉴 끝으로 이동 */
function fnMenuMoveEnd(oMenu) {
    var cnt = oMenu.length-1;
    var i=0;

    for (i=oMenu.length-1; i>=0; i--) {
        if (Menulist_isSelected(oMenu, i)) {
            if (i==oMenu.length-1) return;
            var idx = i;

            for (j=idx;j<cnt;j++) {
                Menulist_downMenu(oMenu, idx);
                idx = idx + 1;
            }
            cnt = cnt - 1;
        }
    }
}

/* 메뉴 맨 위로 이동 */
function fnMenuMoveStart(oMenu) {
    var i=0;
    var len = oMenu.length;
    var cnt = 0;
    for (i=0; i<oMenu.length; i++) {
    if (Menulist_isSelected(oMenu, i)) {
        if (i==0) return;
        var idx = i;

        for (j=idx;j>cnt;j--) {
            Menulist_upMenu(oMenu, idx);
            idx = idx - 1;
        }
        cnt = cnt + 1;
        }
    }
}

/* 메뉴 위로 이동 */
function fnMenuMoveUp(oMenu) {
    var i=0;
    for (i=0; i<oMenu.length; i++) {
        if (Menulist_isSelected(oMenu, i)) {
            if (i==0) return;
            Menulist_upMenu(oMenu, i);
        }
    }
}

/* 메뉴 아래로 이동 */
function fnMenuMoveDown(oMenu) {
    var i=0;
    for (i=oMenu.length-1; i>=0; i--) {
        if (Menulist_isSelected(oMenu, i)) {
            if (i==oMenu.length-1) return;
            Menulist_downMenu(oMenu, i);
        }
    }
}

function Menulist_downMenu(oMenu, index) {
    if (index < 0) return;
    if (index == oMenu.length-1) {
        return; // 더 이상 아래로 이동할 수 없을때
    }
    Menulist_moveMenu(oMenu, index, 1);
}

function Menulist_upMenu(oMenu, index) {
    if (index < 0) return;
    if (index == 0) {
        return; // 더 이상 위로 이동할 수 없을때
    }
    Menulist_downMenu(oMenu, index-1);
}

function Menulist_isSelected(oMenu, idx) {
    return (oMenu.options[idx].selected==true);
}

function Menulist_moveMenu(oMenu, index, distance) {
    var tmpOption = new Option(oMenu.options[index].text, oMenu.options[index].value, false, oMenu.options[index].selected);
	tmpOption.recptnSe = oMenu.options[index].recptnSe;
    for (var i=index; i<index+distance; i++) {
        oMenu.options[i].text = oMenu.options[i+1].text;
        oMenu.options[i].value = oMenu.options[i+1].value;
        oMenu.options[i].recptnSe = oMenu.options[i+1].recptnSe;
        oMenu.options[i].selected = oMenu.options[i+1].selected;
    }
    oMenu.options[index+distance] = tmpOption;
}

</script>

</body>
</html>