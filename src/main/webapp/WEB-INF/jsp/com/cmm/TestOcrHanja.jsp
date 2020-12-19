<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV id="main">

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	한자 OCR 테스트
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<form id="commandForm" method="post" action="/testOcrHanja.do" enctype="multipart/form-data">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuPos" value="${curMenuPos}" />

  <table class="table-list" style="width:100%" >
  	<colgroup>
   		<col width="80px"/>
   		<col width="100px"/>
  		<col width="80px"/>
  		<col />
  	</colgroup>
	<tr>
      	<td class="title_left"> &nbsp;&nbsp;</td>
		<td><input type="file" name="cmmntyImageName" id="cmmntyImageName" title="커뮤니티로고이미지 첨부"></td>
      	<td class="title_left"> &nbsp;&nbsp;</td>
	  	<td><input id="btnTest" type="submit" value="제출" /></td>
 	</tr>   
  </table>	
</form>

<c:if test="${orgFileName ne null}">
<div style="margin-top:10px; width:100%">
	이미지 파일 : <Strong>${orgFileName}</Strong><br><br>
	<img style="border: 1px solid #ddd; padding: 5px;" src="${pageContext.request.contextPath}/getHanjaImage.do?imageId=${imageId}">
</div>

<div style="margin-top:10px; width:100%">${responseText}</div>

<div style="margin-top:10px; width:100%">
json_parsing_result:<br>
image_id = ${resultWrapVO.image_id}<br>
image = ${resultWrapVO.image}<br>
owner = ${resultWrapVO.owner}<br>
meta = ${resultWrapVO.meta}<br>
created_date = ${resultWrapVO.created_date}<br>
status = ${resultWrapVO.status}<br>
ocr_result.count = ${resultWrapVO.ocr_result.size()}<br>
sorted_hanja_list = ${hanjaList}<br>
<br>
hanja_text:<br>
${hanjaText}<br>
</div>
</c:if>

</DIV>

