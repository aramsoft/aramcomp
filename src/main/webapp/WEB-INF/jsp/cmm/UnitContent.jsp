<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV id="main">

<c:if test="${loginVO == null}">
	 아람소프트 컴포넌트에 오신 것을 환영합니다. !!!<br/>
    <a href="${pageContext.request.contextPath}/uat/uia/loginUsr.do?targetUrl=/UnitContent.do"><b>로그인</b></a>
    <br/><br/>
</c:if>

<c:if test="${loginVO != null}">
	${loginVO.name}님 환영합니다. !!! <br/>
	<c:if test="${loginVO.userSe == 'GNR' }">
 	<a href="${pageContext.request.contextPath}/uss/umt/editMber.do?uniqId=${loginVO.uniqId}"><b>일반회원수정</b></a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${loginVO.userSe == 'ENT' }">
	<a href="${pageContext.request.contextPath}/uss/umt/editEntrprsMber.do?uniqId=${loginVO.uniqId}"><b>기업회원수정</b></a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${loginVO.userSe == 'USR' }">
	<a href="${pageContext.request.contextPath}/uss/umt/editUser.do?uniqId=${loginVO.uniqId}"><b>업무회원수정</b></a>&nbsp;&nbsp;
	</c:if>
	<a href="${pageContext.request.contextPath}/uat/uia/actionLogout.do?targetUrl=/UnitContent.do"><b>로그아웃</b></a>
	<br/><br/>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	사용자  메인화면
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/sym/mnu/mpm/MainMenuHome.do" target="_new">
      		사용자  메인화면(포탈)
      	</a>
      </td>
    </tr>
</table>

<a href="/uat/uia/listActiveUsers.do">Active Users</a>
</c:if>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	게시판 목록
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/1/list">
      		공지사항 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/11/list">
      		일반게시판 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/21/list">
      		유효게시판 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/31/list">
      		익명게시판 
      	</a>
      </td>
    </tr>
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/41/list">
      		프로그램팁 
      	</a>
      </td>
    </tr>
<!-- 
    <tr> 
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/board/51/list">
      		버그리포트
      	</a>
      </td>
    </tr>
-->
</table>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	커뮤니티 목록
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/apps/opensource" target="_new">
      		오픈소스모임
      	</a>
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	홈페이지 목록
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<a href="${pageContext.request.contextPath}/home/sample/main.do" target="_new">
      		샘플 홈페이지
      	</a>
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td class="title_left">
      	<img src="${pageContext.request.contextPath}/images/cmm/icon/tit_icon.gif" width="16" height="16" hspace="3" align="middle" alt="gpki_icon">
      	KOMORAN 
      </td>
    </tr>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	한글 형태소 분석기 테스트
      </td>
    </tr>
</table>

<div style="margin-top:10px; width:100%"></div>

<form id="commandForm" method="post" action="/UnitContent.do">
  <table>
  	<tr style="margin-bottom:50px;">
      <td class="title_left"> 문장입력&nbsp;&nbsp;</td>
	  <td><input id="inputText" name="inputText" type="text" size="100"  value="${inputText}" /></td>
	</tr>
	<tr style="margin-bottom:50px;">
      <td class="title_left"> &nbsp;&nbsp;</td>
	  <td><input id="btnTest" type="submit" value="제출" /></td>
 	</tr>   
    <tr>
      <td class="title_left"> 분석결과&nbsp;&nbsp;</td>
	  <td>${resultText}</td>
	</tr>
  </table>	
</form>

<div style="margin-top:10px; width:100%"></div>

<table>
    <tr>
      <td valign="top">&nbsp;-&nbsp;
      	<B>품사표</B> : <a href="https://docs.komoran.kr/firststep/postypes.html" target="new">https://docs.komoran.kr/firststep/postypes.html</a>
      </td>
    </tr>
</table>
<p>KOMORAN은 <a class="reference external" href="https://ithub.korean.go.kr/user/introductionManager.do">21세기 세종계획</a> 의
        품사 기준을 따르고 있습니다. 전체 품사들은 아래 표와 같으며, 세분류를 기준으로 출력됩니다.</p>
<table class="table-list" style="width:50%" >
  	<colgroup>
   		<col width="18%"/>
   		<col width="41%"/>
   		<col width="41%"/>
  	</colgroup>
  	<thead valign="bottom">
    	<tr class="row-odd">
      		<th class="head">대분류</th>
      		<th class="head">소분류</th>
      		<th class="head">세분류</th>
    	</tr>
  	</thead>
  	<tbody valign="top">
    	<tr class="row-even">
      		<td rowspan="5">체언</td>
            <td rowspan="3">명사NN</td>
            <td>일반명사NNG</td>
        </tr>
        <tr class="row-odd">
           	<td>고유명사NNP</td>
        </tr>
        <tr class="row-even">
            <td>의존명사NNB</td>
        </tr>
        <tr class="row-odd">
            <td rowspan="2">대명사NP</td>
            <td>대명사NP</td>
        </tr>
        <tr class="row-even">
            <td>수사NR</td>
        </tr>
        <tr class="row-odd">
            <td rowspan="5">용언</td>
            <td>동사VV</td>
            <td>동사VV</td>
        </tr>
        <tr class="row-even">
            <td>형용사VA</td>
            <td>형용사VA</td>
        </tr>
        <tr class="row-odd">
            <td>보조용언VX</td>
            <td>보조용언VX</td>
        </tr>
        <tr class="row-even">
            <td rowspan="2">지정사VC</td>
            <td>긍정지정사VCP</td>
        </tr>
        <tr class="row-odd">
            <td>부정지정사VCN</td>
        </tr>
        <tr class="row-even">
            <td rowspan="3">수식언</td>
            <td>관형사MM</td>
            <td>관형사MM</td>
        </tr>
        <tr class="row-odd">
            <td rowspan="2">부사MA</td>
            <td>일반부사MAG</td>
        </tr>
        <tr class="row-even">
            <td>접속부사MAJ</td>
        </tr>
        <tr class="row-odd">
            <td>독립언</td>
            <td>감탄사IC</td>
            <td>감탄사IC</td>
        </tr>
        <tr class="row-even">
            <td rowspan="9">관계언</td>
            <td rowspan="7">격조사JK</td>
            <td>주격조사JKS</td>
        </tr>
        <tr class="row-odd">
            <td>보격조사JKC</td>
        </tr>
        <tr class="row-even">
            <td>관형격조사JKG</td>
        </tr>
        <tr class="row-odd">
            <td>목적격조사JKO</td>
        </tr>
        <tr class="row-even">
            <td>부사격조사JKB</td>
        </tr>
        <tr class="row-odd">
            <td>호격조사JKV</td>
        </tr>
        <tr class="row-even">
            <td>인용격조사JKQ</td>
        </tr>
        <tr class="row-odd">
            <td>보조사JX</td>
            <td>보조사JX</td>
        </tr>
        <tr class="row-even">
            <td>접속조사JC</td>
            <td>접속조사JC</td>
        </tr>
        <tr class="row-odd">
            <td rowspan="10">의존형태</td>
            <td rowspan="5">어미E</td>
            <td>선어말어미EP</td>
        </tr>
        <tr class="row-even">
            <td>종결어미EF</td>
        </tr>
        <tr class="row-odd">
            <td>연결어미EC</td>
        </tr>
        <tr class="row-even">
            <td>명사형전성어미ETN</td>
        </tr>
        <tr class="row-odd">
            <td>관형형전성어미ETM</td>
        </tr>
        <tr class="row-even">
            <td>접두사XP</td>
            <td>체언접두사XPN</td>
        </tr>
        <tr class="row-odd">
            <td rowspan="3">접미사XS</td>
            <td>명사파생접미사XSN</td>
        </tr>
        <tr class="row-even">
            <td>동사파생접미사XSV</td>
        </tr>
        <tr class="row-odd">
            <td>형용사파생접미사XSA</td>
        </tr>
        <tr class="row-even">
            <td>어근XR</td>
            <td>어근XR</td>
        </tr>
        <tr class="row-odd">
            <td rowspan="12">기호</td>
            <td>마침표,물음표,느낌표SF</td>
            <td>마침표,물음표,느낌표SF</td>
        </tr>
        <tr class="row-even">
            <td>쉼표,가운뎃점,콜론,빗금SP</td>
            <td>쉼표,가운뎃점,콜론,빗금SP</td>
        </tr>
        <tr class="row-odd">
            <td>따옴표,괄호표,줄표SS</td>
            <td>따옴표,괄호표,줄표SS</td>
        </tr>
        <tr class="row-even">
            <td>줄임표SE</td>
            <td>줄임표SE</td>
        </tr>
        <tr class="row-odd">
            <td>붙임표(물결,숨김,빠짐)SO</td>
            <td>붙임표(물결,숨김,빠짐)SO</td>
        </tr>
        <tr class="row-even">
            <td>외국어SL</td>
            <td>외국어SL</td>
        </tr>
        <tr class="row-odd">
            <td>한자SH</td>
            <td>한자SH</td>
        </tr>
        <tr class="row-even">
            <td>기타기호(논리수학기호,화폐기호)SW</td>
            <td>기타기호(논리수학기호,화폐기호)SW</td>
        </tr>
        <tr class="row-odd">
            <td>명사추정범주NF</td>
            <td>명사추정범주NF</td>
        </tr>
        <tr class="row-even">
            <td>용언추정범주NV</td>
            <td>용언추정범주NV</td>
        </tr>
        <tr class="row-odd">
            <td>숫자SN</td>
            <td>숫자SN</td>
        </tr>
        <tr class="row-even">
            <td>분석불능범주NA</td>
            <td>분석불능범주NA</td>
        </tr>
    </tbody>
</table>
</DIV>

