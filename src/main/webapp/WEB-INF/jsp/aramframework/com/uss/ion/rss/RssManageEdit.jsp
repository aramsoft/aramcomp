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
  * @Class Name : RssManageEdit.jsp
  * @Description : RSS서비스관리 수정
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
<DIV id="main">

<div class="content_title">
	<h2>RSS서비스관리 수정</h2>
</div>

<div id="search_area">
	<div class="button_area">
		<span class="button"><a href="#" onclick="javascript:fn_aram_update(); return false;"><spring:message code="button.save" /></a></span>
		<span class="button"><a href="#" onclick="javascript:fn_aram_list(); return false;"><spring:message code="button.list" /></a></span>
	</div>
</div>

<form:form commandName="rssManageVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<form:hidden path="rssId" />

<!--  등록  폼 영역-대상서비스/서비스TABLE/헤더 Tag 정보  -->
<table class="table-register" summary="RSS태그관리  입력폼 을 제공한다..">
<caption>RSS태그관리  입력폼 을 제공한다</caption>
	<tr> 
		<th width="20%">
			<span class="required_icon"></span>
			<label for="trgetSvcNm">대상서비스명</label>
		</th>
		<td width="80%">
			<form:input path="trgetSvcNm" size="73" title="대성서비스명" cssClass="txInput" maxlength="255"/>
			<form:errors path="trgetSvcNm" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			<label for="trgetSvcTable">대상테이블명</label>
		</th>
		<td>
	        <form:select path="trgetSvcTable" cssClass="txInput" onchange="fn_aram_tableColumn();">
	            <form:option value="" label="선택"/>
	            <form:options items="${trgetSvcTableList}" itemValue="code" itemLabel="code"/>
	        </form:select>
			<form:errors path="trgetSvcTable" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			<label for="trgetSvcListCo">대상서비스목록갯수</label>
		</th>
		<td>
			<form:input path="trgetSvcListCo" size="73" title="대상서비스목록갯수" cssClass="txInput" maxlength="5"/>
			<form:errors path="trgetSvcListCo" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			<label for="hderTitle">헤더TITLE</label>
		</th>
		<td>
			<form:input path="hderTitle" size="73" title="헤더TITLE" cssClass="txInput" maxlength="255"/>
			<form:errors path="hderTitle" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			<label for="hderLink">헤더LINK</label>
		</th>
		<td>
			<form:input path="hderLink" size="73" title="헤더LINK" cssClass="txInput" maxlength="255"/>
			<form:errors path="hderLink" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="required_icon"></span>
			<label for="hderDescription">헤더DSCRIPTION</label>
		</th>
		<td>
			<form:textarea path="hderDescription" title="헤더DSCRIPTION" rows="3" cols="20" cssClass="txArea"/>
			<form:errors path="hderDescription" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="norequired_icon"></span>
			<label for="hderTag">헤더TAG</label>
		</th>
		<td>
			<form:input path="hderTag" size="73" title="헤더TAG" cssClass="txInput" maxlength="255"/>
			<form:errors path="hderTag" cssClass="error"/>
		</td>
	</tr>
	<tr> 
		<th>
			<span class="norequired_icon"></span>
			<label for="hderTag">헤더ETC</label>
		</th>
		<td width="80%">
			<form:textarea path="hderEtc" title="헤더ETC" rows="3" cols="20" cssClass="txArea"/>
			<form:errors path="hderEtc" cssClass="error"/>
		</td>
	</tr>
</table>
	
<!-- 등록  폼 영역-본문 Tag 정보 입력폼/컬럼선택  -->
<table class="table-register" summary="컬럼 목록/본문RSS입력을  제공한다.">
<caption>컬럼 목록/본문RSS입력을  제공한다</caption>
	<tr> 
		<!-- 컬럼선택 -->
		<th scope="col" width="20%">
		<table class="table-list" style="table-layout:fixed;" summary="컬럼 목록  제공한다.">
			<tr>
				<th class="lt_text3" valign="top">컬럼</th>
			</tr>
			<tr>
				<td>
					<select name="tableColumn" id="tableColumn" size="7" title="컬럼선택" style="width:99%;height:130px;">
						<option value=""></option>
					</select>
				</td>
			</tr>
		</table>
		</th>
		<!-- 본문입력폼 -->
		<td >
		<table class="table-register" summary="본문 Tag 정보 입력 제공한다.">
		<caption>본문 Tag 정보 입력 제공한다</caption>
			<tr> 
				<th width="20%">
					<span class="required_icon"></span>
					<label for="bdtTitle">본문TITLE</label>
				</th>
				<td width="80%">
					<a href="#" onClick="fn_aram_ColumnSetting('bdtTitle'); return false;"><img src="/images/aramframework/com/cmm/btn/btn_regist.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 0px 0px 2px; " alt="컬럼명추가" title="컬럼명추가"></a>
					<form:input path="bdtTitle" size="50" title="본문TITLE" maxlength="255"/>
					<a href="#" onClick="document.getElementById('bdtTitle').value=''; return false;"><img src="/images/aramframework/com/cmm/btn/btn_delete.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 2px 0px 0px; " alt="컬럼명삭제" title="컬럼명삭제"></a>
					<form:errors path="bdtTitle" cssClass="error"/>
				</td>
			</tr>
			<tr> 
				<th>
					<span class="required_icon"></span>
					<label for="bdtLink">본문LINK</label>
				</th>
				<td>
					<a href="#" onClick="fn_aram_ColumnSetting('bdtLink'); return false;"><img src="/images/aramframework/com/cmm/btn/btn_regist.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 0px 0px 2px; " alt="컬럼명추가" title="컬럼명추가"></a>	
					<form:input path="bdtLink" size="50" title="본문LINKE" maxlength="255"/>
					<a href="#" onClick="document.getElementById('bdtLink').value=''; return false;"><img src="/images/aramframework/com/cmm/btn/btn_delete.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 2px 0px 0px; " alt="컬럼명삭제" title="컬럼명삭제"></a>
					<form:errors path="bdtLink" cssClass="error"/>
				</td>
			</tr>
			<tr> 
				<th>
					<span class="required_icon"></span>
					<label for="bdtDescription">본문DESCRIPTION</label>
				</th>
				<td>
					<a href="#" onClick="fn_aram_ColumnSetting('bdtDescription'); return false;"><img src="/images/aramframework/com/cmm/btn/btn_regist.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 0px 0px 2px; " alt="컬럼명추가" title="컬럼명추가"></a>
					<form:input path="bdtDescription" size="50" title="본문DESCRIPTION" maxlength="255"/>
					<a href="#" onClick="document.getElementById('bdtDescription').value=''; return false;"><img src="/images/aramframework/com/cmm/btn/btn_delete.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 2px 0px 0px; " alt="컬럼명삭제" title="컬럼명삭제"></a>
					<form:errors path="bdtDescription" cssClass="error"/>
				</td>
			</tr>
			<tr> 
				<th>
					<span class="norequired_icon"></span>
					<label for="bdtTag">본문TAG</label>
				</th>
				<td>
					<a href="#" onClick="fn_aram_ColumnSetting('bdtTag'); return false;"><img src="/images/aramframework/com/cmm/btn/btn_regist.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 0px 0px 2px; " alt="컬럼명추가" title="컬럼명추가"></a>
					<form:input path="bdtTag" size="50" title="본문TAG" maxlength="255"/>
					<a href="#" onClick="document.getElementById('bdtTag').value=''; return false;"><img src="/images/aramframework/com/cmm/btn/btn_delete.gif" width="13px" height="14px" style="vertical-align:middle; display:inline-block;margin:0px 2px 0px 0px; " alt="컬럼명삭제" title="컬럼명삭제"></a>
					<form:errors path="bdtTag" cssClass="error"/>
				</td>
			</tr>
			<tr> 
				<th>
					<span class="norequired_icon"></span>
					<label for="bdtEtc">본문ETC</label>
				</th>
				<td>
					<form:textarea path="bdtEtc" title="본문ETC" rows="3" cols="20" cssClass="txInput99"/>
					<form:errors path="bdtEtc" cssClass="error"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<!-- 검색조건 유지 -->
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="pageIndex" />
<form:hidden path="recordPerPage" />
<!-- 검색조건 유지 -->
</form:form>

</DIV>

<script type="text/javascript" src="${pageContext.request.contextPath}/validator.do"></script>
<validator:javascript formName="rssManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">

/* ********************************************************
* 선택한 테이블 컬럼 가져오기
******************************************************** */
function fn_aram_tableColumn(sType){

	if( document.getElementById("trgetSvcTable").selectedIndex == 0){
		 $("#tableColumn").html("");
		return;
	}
	
	var tableName = $("#trgetSvcTable option:selected").val();
	
	//success:callbackFunction
	$.ajax({
		type:"GET",
	   	url:"/uss/ion/rss/listRssTableColumnJson.do?tableName="+tableName,
	   	dataType:"json",
	   	success:function(data){
	   		var columnList = data.resultList;
	   		
		   	$("#tableColumn").html("");
			for(var index=0 ; index < columnList.length ; index++) {
				$("#tableColumn").get(0).options[index] = new Option(columnList[index].codeNm, columnList[index].codeNm);
			}
	   	}, 
	   	error: function(data, status) { alert( status); }
	});

	if(sType != "Init"){
		//본문 데이터 삭제
		document.getElementById('bdtTitle').value='';
		document.getElementById('bdtLink').value='';
		document.getElementById('bdtDescription').value='';
		document.getElementById('bdtTag').value='';
	}
}

/* ********************************************************
*  선택한 테이블 컬럼 선택하기
******************************************************** */
function fn_aram_ColumnSetting(column){

	if( document.getElementById("tableColumn").selectedIndex> -1){

		var sColumnNm = $("#tableColumn option:selected").val();
		//해당컬럼 검색 똑같은거 입력 안되게 감리요청
		var regTest = new RegExp("#"+sColumnNm+"#");
		if( regTest.test(document.getElementById(column).value) ){
			alert(sColumnNm + " 컬럼명이 중복 되었습니다!");
			return;
		}else{
			document.getElementById(column).value = document.getElementById(column).value + "#"+$("#tableColumn option:selected").val()+"#";
		}
	}
}

/* ********************************************************
 * 초기화 함수
 ******************************************************** */
window.onload = function(){
	fn_aram_tableColumn('Init');
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_aram_list(){
    var varForm = document.getElementById("rssManageVO");
    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/listRssManage.do";
    varForm.submit();
}

/* ********************************************************
* 저장
******************************************************** */
function fn_aram_update(){
    var varForm = document.getElementById("rssManageVO");
    
	if(!validateRssManageVO(varForm)){ 			
		return;
	}
	
	if(confirm("<spring:message code='common.update.msg'/>")){
	    varForm.action = "${pageContext.request.contextPath}/uss/ion/rss/updateRssManage.do";
	    varForm.submit();
	}
}

</script>

</body>
</html>