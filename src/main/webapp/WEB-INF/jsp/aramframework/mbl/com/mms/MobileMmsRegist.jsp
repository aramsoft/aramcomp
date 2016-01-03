<!DOCTYPE html> 
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>MMS 전송</title> 
      
<!-- eGovFrame Common import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/EgovCom.js"></script>

<!-- 신규공통컴포넌트 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/mcomd/egovMcomd.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/com/mms/mms.js"></script>

<script type="text/javaScript" defer="defer">
<!--		

$('#selectAtchFile').live('pageshow', fn_egov_init_atchFileList);

/*********************************************************
 * MMS 전송
 ******************************************************** */
function fn_egov_send_mms() {
	
   	if(!validateMmsTransInfoVO(document.mmsTransInfo)) {
   		return;
   	}
    	
   	var atchFileSn = $('#atchFileSn').val();

	document.mmsTransInfo.action = "${pageContext.request.contextPath}/mbl/com/mms/sendMms.mdo?atchFileSn=" + atchFileSn ;
	document.mmsTransInfo.submit();
}

  	/*********************************************************
 * 첨부파일 목록 조회
 ******************************************************** */
function fn_egov_init_atchFileList() {
	
  	$.getJSON("${pageContext.request.contextPath}/mbl/com/mms/selectMblMmsAttachFileList.mdo", $('#searchVO').serialize(), function(json) {
   		
  		var html = "";
  			html += '<div>';
  			html += '	<form id="searchVO" type="post">';
  			html += '		<input type="hidden" name="pageIndex" id="pageIndex" value="' + json.searchVO.pageIndex + '" />';
  			html += '	</form>';
  			html += '</div>';
  		
  		if(json.resultList.length == 0) {
			html += '		<li class="com-egovNodata">';
           	html += '    		<p>등록된 첨부파일이 없습니다.</p>';
          	html += '		</li>';
		}
		else {
			for ( var i = 0; i < json.resultList.length; i++) {
				var result =  json.resultList[i];
				
				html += '	<li>';
				html += '		<a href="javascript:fn_aram_select_atchFile(\'' + result.atchFileNm + '\', \'' + result.sn + '\');">';
				html += '			<p></p>';
				html += '			<p>' + result.atchFileNm + '</p>';
				html += '			<p></p>';
				html += '		</a>';
				html += '	</li>';
			}
		}
  			
		$('div[id="selectAtchFile"] ul[data-role="listview"]').html(html).listview('refresh');;

		$('#pageNavi').html(pagenationRenderer(json.paginationInfo, "fn_show_list"));
	});
}

/*********************************************************
 * 페이지 이동(첨부파일 선택 화면)
 ******************************************************** */
function fn_show_list(pageIndex) {
	$('#pageIndex').val(pageIndex);
	fn_egov_init_atchFileList();
}

/*********************************************************
 * 첨부파일 선택 처리(첨부파일 선택 화면)
 ******************************************************** */
function fn_egov_select_atchFile(atchFileNm, atchFileSn) {
	$('#atchFileNm').val(atchFileNm);
	$('#atchFileSn').val(atchFileSn);
	$.mobile.changePage($('#writeMms'));
}

/*********************************************************
 * 화면 로드시 전송결과 출력
 ******************************************************** */
$(window).load(function() {
	var requestResult = $('#requestResult').val();

	if (requestResult == "true") {
		jAlert('전송 요청이 성공하였습니다.', 'MMS 전송 요청 결과', 'a');
	} else if (requestResult == "false") {
       	var btmItem = [{id : 'retransMission', value: "재전송"}];
        jActionSheet('전송 요청이 실패하였습니다.', 'MMS 전송 요청 결과', 'a', btmItem , function(r) {
           	if (r == "재전송") {
               	fn_aram_send_mms();
            } else {
                return;
            }
        });
	}
});
      -->
</script>

</head>

<body>

  	<!-- MMS 작성 페이지 start -->
<div id="writeMms" data-role="page" data-theme="d">

	<!-- header start -->
	<div data-role="header" data-theme="a" data-position="fixed">
  		<a href="${pageContext.request.contextPath}/mindex.jsp" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1>MMS 작성</h1>
	</div>
    <!-- header end -->
	
	<!-- contents start -->
	<div data-role="content" class="egov-mcomdContent">
		<form name="mmsTransInfo" id="mmsTransInfo" method="post" action="/mbl/com/mms/sendMms.mdo">
			<dl class="mcom-mmsNumber">
				<dt>
					<label for="trnsmisNo">
						<strong>송신번호</strong>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
					</label>
				</dt>
				<dd><input type="text" id="trnsmisNo" name="trnsmisNo" value="<c:out value="${mmsTransInfo.trnsmisNo}" />" data-role="none" class="mmsNum" maxlength="11"/></dd>
				<dt>
					<label for="recptnNo" data-role="none">
						<strong>수신번호</strong>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
					</label>
				</dt>
				<dd><input type="text" id="recptnNo" name="recptnNo" value="<c:out value="${mmsTransInfo.recptnNo}" />" data-role="none" class="mmsNum" maxlength="11"/></dd>
			</dl>
			
			<dl class="mcom-mmsContent" data-role="none">
				<dt>
					<label for="mssageSj">
						<strong>제목</strong>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
					</label>
				</dt>
				<dd><input type="text" id="mssageSj" name="mssageSj" value="<c:out value="${mmsTransInfo.mssageSj}" />"/></dd>
				
				<dt>
					<label for="mssageCn">
						<strong>전송내용</strong>
						<img src="${pageContext.request.contextPath}/images/egovframework/mbl/mcomd/icon/required.gif" width="15" height="15" alt="필수항목">
					</label>
				</dt>
				<dd><textarea cols="20" rows="8" id="mssageCn" name="mssageCn"><c:out value="${mmsTransInfo.mssageCn}" /></textarea></dd>
				
				<dt><label for="atchFileNm">첨부파일</label></dt>
				<dd>
					<span><input type="text" id="atchFileNm" name="atchFileNm" value="<c:out value="${mmsTransInfo.attachFile.atchFileNm}" />" data-role="none" class="mmsFile" readonly /></span>
					<span style="vertical-align:top"><a href="${pageContext.request.contextPath}/mbl/com/mms/goAtchFileSelect.mdo" data-role="button" data-icon="search" data-iconpos="notext" data-theme="h"></a></span>
				</dd>
			</dl>
	        <div class="mmsBtn">
				<a href="javascript:fn_egov_send_mms();" data-role="button" data-theme="b">전송</a>
			</div>
	        <input type="hidden" id="atchFileSn" value="<c:out value="${mmsTransInfo.attachFile.sn}" />" />
	        <input type="hidden" id="requestResult" value="<c:out value="${requestResult}" />" />
    	</form>
	</div>
	<!-- contents end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="a" data-position="fixed" >
		<h4>Copyright(c) 2012, 2013 Aram High-Tech.</h4>
	</div>
	<!-- footer end -->

</div>
<!-- MMS 작성 페이지 end -->

</body>
</html>