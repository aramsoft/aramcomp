<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : AdministrationWordUserList.jsp
 * @Description : 행정전문용어사전 목록
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
	<h2>행정전문용어사전 목록</h2>
	<a href="#" onclick="javascript:fn_aram_help(); return false;">
		<img src="${pageContext.request.contextPath}/images/aramframework/com/cmm/icon/icon_help.gif" width="16" height="16" style="vertical-align:middle;" alt="">
	</a>
</div>

<form:form commandName="administrationWordVO" action="" method="post">
<input type="hidden" name="curTrgetId" value="${curTrgetId}" />
<input type="hidden" name="curMenuNo" value="${curMenuNo}" />

<input name="administWordId" type="hidden" value="">

<div id="search_area">
	<div class="button_area">
      	<span class="button"><a href="#" onclick="javascript:fn_aram_search(); return false;"><spring:message code="button.inquire" /></a></span>
	</div>
	<div class="keyword_area">
    	<form:select path="searchCondition" title="조회조건 선택">
	   		<form:option value='' label="--선택하세요--" />
	   		<form:option value="ADMINIST_WORD_NM" label="행정용어명" />			   
	   		<form:option value="ADMINIST_WORD_ENG_NM" label="행정용어영문명" />			   
	   		<form:option value="ADMINIST_WORD_ABRV_NM" label="행정용어약어명" />			   
	   		<form:option value="ADMINIST_WORD_DFN" label="행정용어정의" />			   
	   		<form:option value="ADMINIST_WORD_DC" label="행정용어설명" />			   
   		</form:select>
   		<form:input path="searchKeyword" size="35" maxlength="35" onkeypress="javascript:press(event);" title="검색어 입력" />
		<form:select path="recordPerPage" class="select" onchange="fn_aram_search();" >
	   		<form:option value="10" label="10" />
	   		<form:option value="20" label="20" />
	   		<form:option value="30" label="30" />
	   		<form:option value="50" label="50" />
		</form:select>
	</div>
</div>

<form:hidden path="choseongSe" />
<form:hidden path="choseongA" />
<form:hidden path="choseongB" />
<form:hidden path="pageIndex" />
</form:form>

<!--  검색버튼 -->
<table border="0" style="width:100%">
	<tr>
		<td colspan="5" height="2" bgcolor="#479B0F"></td>
	</tr>
	<tr>
		<td bgcolor="#DCFAC9" width="20px"></td>
		<td bgcolor="#DCFAC9"><b>한글</b></td>
		<td bgcolor="#DCFAC9">
		<table border="0">
			<tr>
				<td <c:if test="${choseongA eq '가' && choseongB eq '나'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('가', '나', 'H'); return false;">ㄱ</a></td>
				<td <c:if test="${choseongA eq '나' && choseongB eq '다'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('나', '다', 'H'); return false;">ㄴ</a></td>
				<td <c:if test="${choseongA eq '다' && choseongB eq '라'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('다', '라', 'H'); return false;">ㄷ</a></td>
				<td <c:if test="${choseongA eq '라' && choseongB eq '마'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('라', '마', 'H'); return false;">ㄹ</a></td>
				<td <c:if test="${choseongA eq '마' && choseongB eq '바'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('마', '바', 'H'); return false;">ㅁ</a></td>
				<td <c:if test="${choseongA eq '바' && choseongB eq '사'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('바', '사', 'H'); return false;">ㅂ</a></td>
				<td <c:if test="${choseongA eq '사' && choseongB eq '아'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('사', '자', 'H'); return false;">ㅅ</a></td>
				<td <c:if test="${choseongA eq '아' && choseongB eq '자'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('사', '자', 'H'); return false;">ㅇ</a></td>
				<td <c:if test="${choseongA eq '자' && choseongB eq '차'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('자', '차', 'H'); return false;">ㅈ</a></td>
				<td <c:if test="${choseongA eq '차' && choseongB eq '카'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('차', '카', 'H'); return false;">ㅊ</a></td>
				<td <c:if test="${choseongA eq '카' && choseongB eq '타'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('카', '타', 'H'); return false;">ㅋ</a></td>
				<td <c:if test="${choseongA eq '타' && choseongB eq '파'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('타', '파', 'H'); return false;">ㅌ</a></td>
				<td <c:if test="${choseongA eq '파' && choseongB eq '하'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('파', '하', 'H'); return false;">ㅍ</a></td>
				<td <c:if test="${choseongA eq '하' && choseongB eq '힣'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('하', '힣', 'H')">ㅎ</a></td>
			</tr>
		</table>
		</td>
		<td bgcolor="#DCFAC9"><b>영문</b></td>
		<td bgcolor="#DCFAC9">
		<table border="0">
			<tr>
				<td <c:if test="${choseongA eq 'A' && choseongB eq 'E'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('A', 'E', 'E'); return false;">A-E</a></td>
				<td width="3"></td>
				<td <c:if test="${choseongA eq 'F' && choseongB eq 'J'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('F', 'J', 'E'); return false;">F-J</a></td>
				<td width="3"></td>
				<td <c:if test="${choseongA eq 'K' && choseongB eq 'O'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('K', 'O', 'E'); return false;">K-O</a></td>
				<td width="3"></td>
				<td <c:if test="${choseongA eq 'P' && choseongB eq 'T'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('P', 'T', 'E'); return false;">P-T</a></td>
				<td width="3"></td>
				<td <c:if test="${choseongA eq 'U' && choseongB eq 'Z'}">class="choseongTxt"</c:if>><a href="#" onClick="fn_aram_choseong('U', 'Z', 'E'); return false;">U-Z</a></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="5" height="2" bgcolor="#479B0F"></td>
	</tr>
</table>

<!--  줄간격조정  -->
<div style="margin-top:5px; width:100%"></div>

<!--  리스트영역 -->
<table class="table-list" >
<thead>
  	<tr>
    	<th scope="col" width="7%" >No.</th>
    	<th scope="col" width="10%">구분</th>
    	<th scope="col" width="10%">주제영역</th>
    	<th scope="col"            >용어명</th>
    	<th scope="col" width="20%">영문약어명</th>
    	<th scope="col" width="15%">등록일자</th>
  	</tr>
</thead>
<tbody>
	<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	
 	<%-- 데이터를 화면에 출력해준다 --%>
 	<c:set var="searchVO" value="${administrationWordVO}"/>
 	<c:set var="startIndex" value="${(searchVO.pageIndex-1) * searchVO.recordPerPage}"/>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr class="link" onclick="javascript:fn_aram_detail('<c:out value="${result.administWordId}"/>'); return false;">
 
 		<c:set var="index" value="${startIndex + status.count}"/>
		<c:set var="reverseIndex" value="${searchVO.totalRecordCount - index + 1}"/>
		<td class="lt_text3"><c:out value="${reverseIndex}"/></td>

    	<td class="lt_text3">
    		<c:if test="${result.wordSe == '1'}">표준어</c:if>
    		<c:if test="${result.wordSe == '2'}">동의어</c:if>
    	</td>
    	<td class="lt_text3"><c:out value="${result.themaRelm}"/></td>

    	<td class="lt_text3"><c:out value="${result.administWordNm}"/></td>
    	<td class="lt_text3"><c:out value="${result.administWordAbrvNm}"/></td>
    	<td class="lt_text3"><fmt:formatDate value="${result.frstRegisterPnttm}" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>

<div id="page_navigation">
	<ui:pagination paginationInfo="${paginationInfo}"	type="image" jsFunction="fn_aram_linkPage" />
</div>

</DIV>

<script type="text/javascript">

function press(event) {
	if (event.keyCode==13) {
		fn_aram_search();
	}
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_aram_linkPage(pageNo){
    var varForm = document.getElementById("administrationWordVO");
    varForm.pageIndex.value = pageNo;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/awm/listAdministrationWordUser.do";
    varForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_aram_search(){
    var varForm = document.getElementById("administrationWordVO");
    varForm.choseongA.value = '';
    varForm.choseongB.value = '';
    varForm.choseongSe.value = '';
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/awm/listAdministrationWordUser.do";
    varForm.submit();
}

/* ********************************************************
* 초성검색
******************************************************** */
function fn_aram_choseong(choseongA, choseongB, choseongSe){
    var varForm = document.getElementById("administrationWordVO");
    varForm.choseongA.value = choseongA;
    varForm.choseongB.value = choseongB;
    varForm.choseongSe.value = choseongSe;
    varForm.pageIndex.value = 1;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/awm/listAdministrationWordUser.do";
    varForm.submit();
}

/* ********************************************************
 * 상세화면 처리 함수
 ******************************************************** */
function fn_aram_detail(administWordId){
    var varForm = document.getElementById("administrationWordVO");
    varForm.administWordId.value = administWordId;
    varForm.action = "${pageContext.request.contextPath}/uss/olh/awm/detailAdministrationWordUser.do";
    varForm.submit();
}

/*********************************************************
 * 도움말 처리 함수
 ******************************************************** */
function fn_aram_help() {
	var url = "http://www.egovframe.org/wiki/doku.php\?id=egovframework:com:v2:uss:" + encodeURIComponent("행정전문용어사전관리");	
	window.open(url, "도움말");
}
</script>
