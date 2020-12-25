<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV id="main">

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/com/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	한자 동형이음 사전 업로드 
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<form id="commandForm" method="post" action="/uploadHanjaDic.do" enctype="multipart/form-data">
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
		<td><input type="file" name="uploadFile" id="uploadFile" ></td>
      	<td class="title_left"> &nbsp;&nbsp;</td>
	  	<td><input id="btnTest" type="submit" value="제출" /></td>
 	</tr>   
  </table>	
</form>

</DIV>

