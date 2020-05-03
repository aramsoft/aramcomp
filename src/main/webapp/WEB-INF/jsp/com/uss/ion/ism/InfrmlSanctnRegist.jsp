<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
 * @Class Name : InfrmlSanctnRegist.java
 * @Description : 결재권자 등록
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

<div style="margin-top:10px;"></div>

<div class="content_title">
	<h2>결재권자 등록</h2>
</div>

<div id="search_area">
	<div class="search_right">
		<span class="button_area">
	   		<span class="button"><a href="/uss/ion/ism/listSanctnerPopup.do" target="_blank"  title="새 창으로 이동"  onClick="fn_aram_sanctner('승인권자', 'sanctnerId', '', 'sanctnDtNm', 'orgnztNm');return false;">결재자지정</a></span>
		</span>
	</div>	
</div>

<table class="table-register">
  	<tr>
    	<th width="20%">
    		<span class="required_icon"></span>
    		<label for="sanctnDtNm">승인권자명</label>
    	</th>
    	<td width="30%">
       		<input name="sanctnDtNm" id="sanctnDtNm" value="${infSanctnDtNm}" type="text" size="20" title="승인권자명" readOnly>
       		<form:hidden  path="sanctnerId" />
       		<form:errors  path="sanctnerId" cssClass="error"/>
    	</td>
    	<th width="20%">
     		<span class="norequired_icon"></span>
    		<label for="orgnztNm">소속</label>
    	</th>
    	<td width="30%">
       		<input name="orgnztNm" id="orgnztNm" value="${infOrgnztNm}" type="text" size="30" title="소속" readOnly>
    	</td>
  	</tr>
</table>

<script type="text/javascript">

var gArguments = new Array();

/* ********************************************************
* 사용자 팝업창열기
******************************************************** */
function fn_aram_sanctner(strTitle, uniqId, emplNo, emplyrNm, orgnztNm){
	gArguments["title"]    = strTitle;
	if( uniqId != "" )   gArguments["uniqId"]   = document.getElementById(uniqId);
	if( emplNo != "" )   gArguments["emplNo"]   = document.getElementById(emplNo);
	if( emplyrNm != "" ) gArguments["emplyrNm"] = document.getElementById(emplyrNm);
	if( orgnztNm != "" ) gArguments["orgnztNm"] = document.getElementById(orgnztNm);

	var url = "/uss/ion/ism/listSanctnerPopup.do";

	window.open(url, "p_userInqire", "width=850px,height=480px,top=100px,left=100px,location=no");
}

</script>


