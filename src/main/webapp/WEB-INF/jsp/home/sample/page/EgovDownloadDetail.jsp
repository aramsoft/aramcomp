<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovDownloadDetail.jsp
  * @Description : 샘플화면 - 자료실 상세조회(sample)
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
  *  @author 아람컴포넌트 조헌철
  *  @since 2014.11.11
  *  @version 1.0
  *
  */
%>
		<div id="content">
			<!-- 현재위치 네비게이션 시작 -->
			<div id="cur_loc">
				<ul>
					<li>HOME</li>
                    <li>&gt;</li>
                    <li>고객지원</li>
                    <li>&gt;</li>
                    <li><strong>자료실</strong></li>
                </ul>    
			</div>	
			<!-- //현재위치 네비게이션 끝 -->
			<!-- 타이틀 이미지 -->			
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/sample/subtitle/img_subtitle03-01.gif" width="776" height="230" alt="">
			</div>

 			<div class="content_header">
				<h2>자료상세설명</h2>
			</div>

			<div id="download_div02">
				<div class="downloadDetail_title">
					<span class="downloadDetail_titletxt">egovframe installer v1.03</span>
					<span>작성자 : innovate</span>
					<span>2011-08-01 23:22:11</span> 
				</div>
				<div class="sum_img_div_wrap">
					<div class="sum_img_div_loc"> 
						<img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_download.gif">						
					</div> 
					<div class="download_btn_area">
						<a href="#"><img src="${pageContext.request.contextPath}/images/home/sample/btn/btn_download.gif"  alt="download"></a>
					</div>
				</div>				
				<div id="download_detailtable" style="display:inline;">
					<table summary="권장사항" >
						<colgroup> 
							<col width="100"></col>
							<col width=""></col>
						</colgroup>
						<tbody>
						<tr>
							<th>운영체제 </th>
							<td>Win95/Win98/WinME/WinNT/Win2000/WinXP/WinVISTA/Win7/</td>
						</tr>
						<tr>
							<th>권장사양</th>
							<td>펜티엄3</td>
						</tr>
						<tr>
							<th>파일정보</th> 
							<td>7MB (총 1 개)/ aramframework-common-all.zip [15,083,713 byte]</td>
						</tr>
						<tr>
							<th>등록일자</th>
							<td>2011-08-08 11:11:11</td>
						</tr>
						<tr>
							<th>언어</th>
							<td>영어</td>
						</tr>
						</tbody>
					</table>	
				</div>

				<div class="download_modify_txtarea_wrap">
					<div class="download_modify_content">
	안녕하세요..
	
	공통컴포넌트 전체 소스입니다.
	
	관련된 내용은 다음 가이드를 참조하십시오.
	
	http://www.egovframe.org/wiki/doku.php\?id=egovframework:com
	
	감사합니다.				
					</div>
				</div>
				<div class="buttons"><a href="${pageContext.request.contextPath}/home/sample/main/content/EgovDownload'/>">목록</a></div> 
				<div class="btm_prev">이전글 egovframe installer v1.03</div>
				<div class="btm_next">다음글 egovframe installer v1.03 </div> 	
			</div>			
		</div>				
