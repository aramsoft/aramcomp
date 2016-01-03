<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovDownloadModify.jsp
  * @Description : 샘플화면 - 자료실 등록(sample)
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
				<h2>자료등록</h2>
			</div>
			<div id="download_regdiv">
				<div class="downloadDetail_title">
					<div class="detail_inputtitle">
						<label for="writer"><strong>프로그램명</strong></label>
						<input type="text" name="writer" />
					</div>
					<span>작성자 : innovate</span>
					<span>2011-08-01 23:22:11</span> 
				</div>
				<div class="download_reg_loc">
					<div class="download_reg_img"> 
						<img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_download.gif" alt="">							
					</div> 					
					<div class="download_reg_imgtext"><p>썸네일 이미지는 width:160px, height:109px 크기의 이미지를 올려주세요</p></div>
				</div>
				<div class="download_tbstatus">
					<div class="download_regtable">
					<table summary="권장사항" >
						<colgroup> 
							<col width="100"></col>
							<col width=""></col>
						</colgroup>
						<tbody>
						<tr>
							<th>운영체제 </th>
							<td><input type="text" /></td>
						</tr>
						<tr>
							<th>권장사양</th>
							<td><input type="text" /></td>
						</tr>
						<tr>
							<th>파일정보</th> 
							<td><input type="file" name="datafile" class="input_file" size="60" /></td>
						</tr>
						<tr>
							<th>파일크기</th> 
							<td><input type="text" value="13.0MB (13,670,274 바이트)" /></td>
						</tr>
						<tr>
							<th>등록일자</th>
							<td><input type="text" /></td>
						</tr>
						<tr>
							<th>언어</th>
							<td><input type="text" /></td>
						</tr>
						</tbody>
					</table>	
					</div>				
				</div>
			</div>
			
			<div class="datafield_textarea">  
				<textarea rows="14" cols="120" style="padding:5px;border:1px solid #dddddd;"></textarea>		 
			</div>
			<div class="buttons" style="clear:both; float:right; padding:10px;"><a href="#">등록 </a></div> 
		</div>			
