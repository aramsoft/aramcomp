<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovDownload.jsp
  * @Description : 샘플화면 - 자료실목록조회(sample)
  * @Modification Information
  * @
  * @ 수정일              수정자          수정내용
  * @ ----------   ------   ---------------------------
  * @ 2014.11.11   조헌철          최초 생성
  *
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
				<h2>자료실</h2>
			</div>
 
            <!-- 검색 필드 박스 시작 -->
			<div id="search_field">
				<form action="form_action.jsp" method="post">
				  	<fieldset>
				  	<legend>조건정보 영역</legend>	  
					<div class="sf_start">
                        <ul id="search_first_ul">
                            <li>
								<select name="search_select" id="search_select">
								    <option value="0" selected="selected">전체</option>
								    <option value="1">제목</option>
								    <option value="2">제목/내용</option>
								    <option value="3">작성자</option>
								</select>	
				  				<input type="text" name="st_date">
                            </li>       
                         </ul>
                         <ul id="search_second_ul">
                            <li>
				  				<div class="buttons">
									<a href="#"><img src="${pageContext.request.contextPath}/images/home/sample/img_search.gif" alt="search">검색 </a>
								</div>
                             </li>
                         </ul>           
					</div>			
					</fieldset>
				</form>
			</div>
			<!-- //검색 필드 박스 끝 -->		

			<!-- 추천다운로드 시작 -->
			<div id="download_div01">
				<h3>추천 다운로드 자료</h3>
				<div class="download_loc">
					<div class="download_content_wrap">
						<div class="download_img_loc">
							<img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_download.gif" alt="">
						</div>
						<div class="download_text_loc">
							<ul>
								<li><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">egovframe installer v1.03</a></li>
								<li>egovframe의 템플릿 설치를 도와주는 인스톨러.....egovframe의 템플릿 설치를 도와주는 인스톨러</li>
							</ul>	
						</div>
					</div>
					<div class="download_content_wrap">
						<div class="download_img_loc">
							<img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_download.gif" alt="">
						</div>
						<div class="download_text_loc">
							<ul>
								<li><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">egovframe installer v1.03</a></li>
								<li>egovframe의 템플릿 설치를 도와주는 인스톨러.....egovframe의 템플릿 설치를 도와주는 인스톨러</li>
							</ul>	
						</div>
					</div>
					<div class="download_content_wrap" style="clear:both;">
						<div class="download_img_loc">
							<img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_download.gif" alt="">
						</div>
						<div class="download_text_loc">
							<ul>
								<li><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">egovframe installer v1.03</a></li>
								<li>egovframe의 템플릿 설치를 도와주는 인스톨러.....egovframe의 템플릿 설치를 도와주는 인스톨러</li>
							</ul>	
						</div>
					</div>
					<div class="download_content_wrap">
						<div class="download_img_loc">
							<img src="${pageContext.request.contextPath}/images/home/sample/img_content/img_download.gif" alt="">
						</div>
						<div class="download_text_loc">
							<ul>
								<li><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">egovframe installer v1.03</a></li>
								<li>egovframe의 템플릿 설치를 도와주는 인스톨러.....egovframe의 템플릿 설치를 도와주는 인스톨러</li>
							</ul>	
						</div>
					</div>					
				</div>
			</div>
			<!-- //추천다운로드 끝-->

			<!-- 최신등록자료 시작 -->
			<div id="top10_div">
				<h3>최신등록자료</h3>
				<div class="top10_loc">
					<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>	
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>	
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>				
				</div>				
				<div class="top10_loc">						
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>	
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>	
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>	
				 	<ol>
				 		<li>
				 			<img src="${pageContext.request.contextPath}/images/home/sample/num/ico_number01.gif" alt="">
				 			<a class="top10_name" href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">2011년도 표준프레임워크 기술지원 안내</a>
				 			<span class="top10_date">2011-06-03</span>
				 		</li>
				 	</ol>		
					</div>
			</div>
			<!-- //최신등록자료 끝 -->

			<!-- 검색결과 시작 -->
			<div id="page_info"></div>					
			<!-- table add start -->
			<div class="default_tablestyle">
				<table summary="사용자목록관리" >
				<caption>사용자목록관리</caption>
				<colgroup>
					<col width="50">
					<col >  
					<col width="50">
					<col width="50">
					<col width="100">
				</colgroup>
				<thead>
				<tr>
					<th scope="col" class="f_field">번호</th>
					<th scope="col">소프트웨어명</th>
					<th scope="col">다운</th>
					<th scope="col">크기</th>
					<th scope="col">등록일</th>
				</tr>
				</thead>
				<tbody>		  			
				<!-- loop 시작 -->								
				<tr>
					<td><strong>1</strong></td>
					<td class="align_left_text"><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">전자정부표준프레임워크 인스톨러(Egovframework installer) V1.037</a></td>
					<td>100</td>
					<td>16MB</td>
					<td>2011-04-04</td>
				</tr>
				<tr>
					<td><strong>2</strong></td>
					<td class="align_left_text"><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">전자정부표준프레임워크 인스톨러(Egovframework installer) V1.037</a></td>
					<td>100</td>
					<td>16MB</td>
					<td>2011-04-04</td>
				</tr>
				<tr>
					<td><strong>3</strong></td>
					<td class="align_left_text"><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">전자정부표준프레임워크 인스톨러(Egovframework installer) V1.037</a></td>
					<td>100</td>
					<td>16MB</td>
					<td>2011-04-04</td>
				</tr>	
				<tr>
					<td><strong>4</strong></td>
					<td class="align_left_text"><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">전자정부표준프레임워크 인스톨러(Egovframework installer) V1.037</a></td>
					<td>100</td>
					<td>16MB</td>
					<td>2011-04-04</td>
				</tr>	
				<tr>
					<td><strong>5</strong></td>
					<td class="align_left_text"><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadDetail.do">전자정부표준프레임워크 인스톨러(Egovframework installer) V1.037</a></td>
					<td>100</td>
					<td>16MB</td>
					<td>2011-04-04</td>
				</tr>													
				</tbody>
				</table> 
			</div>
			<!-- //검색결과 끝 -->
			<!-- 페이지 네비게이션 시작 -->
			<div id="paging_div">
				<ul class="paging_align">
					<li class="first"><img src="${pageContext.request.contextPath}/images/home/sample/btn/btn_prev.gif" alt="prev"></li>
					<li><a href="#">1</a></li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
					<li class="first"><img src="${pageContext.request.contextPath}/images/home/sample/btn/btn_next.gif" alt="next"></li>
				</ul>
			</div>	
			<!-- //페이지 네비게이션 끝 -->
			<div class="buttons"><a href="${pageContext.request.contextPath}/home/sample/EgovDownloadModify.do">자료올리기 </a></div> 						
		</div>
