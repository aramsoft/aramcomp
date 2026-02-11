<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovQADetail.jsp
  * @Description : 샘플화면 - 묻고답하기 상세조회(sample)
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
					<li>묻고답하기</li>
					<li>&gt;</li>
					<li><strong>Q&amp;A상세조회</strong></li>
				</ul>
			</div>	
			<!-- //현재위치 네비게이션 끝 -->
			<!-- 타이틀 이미지 -->			
			<div id="content_img_div">
				<img src="${pageContext.request.contextPath}/images/home/subtitle/img_subtitle03-02.gif" width="776" height="230" alt="">					
			</div>
			
			<!-- main content 시작 -->
			<div class="content_header">
				<h2>Q&amp;A 상세조회</h2>
			</div>
			<!-- //main content 끝 -->			
			 
			<div class="content_field">
				<div id="qna_detailtable">
					<table summary="Q&amp;A상세조회" >
						<colgroup> 
							<col width="120"></col>
							<col width=""></col>
							<col width="120"></col>
							<col width=""></col>
						</colgroup>
						<tbody>
						<tr>
							<th>제목</th>
							<td>jsp파일을 못찼습니다.</td>
							<th>이메일</th>
							<td>agits@nate.com</td>
						</tr>
						<tr>
							
						</tr>
						<tr>
							<th>이메일답변여부</th> 
							<td>답변요청</td>
							<th>등록일자</th>
							<td>2011-08-08 11:11:11</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>박성환</td>
							<th>전 화</th>
							<td>011-000-0000</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>2011-07-22</td>
							<th>조회</th>
							<td>1222</td>
						</tr>
						<tr>
							<th>처리상태</th>
							<td colspan="3">접수대기</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3">log.txt [85,320 byte]</td>
						</tr>
						</tbody>
					</table>	
				</div>				

				<div class="qa_detail_header">
					<h3>질문</h3>
				</div>	
				<div class="qa_1st_wrap">
					<p>안녕하세요 웹호스팅에 올렸더니 jsp파일에서 이런에러로그가 남았는데요 jsp파일을 못찾는것같습니다? xml을 수정해야하나요?</p>
					<p>심각: Servlet.service() for servlet action threw exception</p>
					<p>javax.servlet.ServletException: Could not get RequestDispatcher for [/WEB-INF/jsp/aramframework//main/main.jsp]: check that this file exists within
						your WAR at org.springframework.web.servlet.view.InternalResourceView.renderMergedOutputModel(InternalResourceView.java:217)</p>
				</div>	
				
				<div class="qa_detail_header">
					<h3>답변</h3>
				</div>	
				<div class="qa_answer"> 
					<ul>
						<li>chanjin님의 답변</li>
						<li>2011-08-08 12:33:33</li>
						<li>
							<p>심각: Servlet.service() for servlet action threw exception은 jsp파일을 열어서 보셔야합니다.</p>
							<p>javax.servlet.ServletException: Could not get RequestDispatcher for [/WEB-INF/jsp/aramframework//main/main.jsp]: check that this file exists within your WAR</p>
						</li>
						<li><div class="qa_btn_delete">삭제하기</div></li>
					</ul>
				</div>
				<div class="qa_answer">
					<ul>
						<li>sunrise님의 답변</li>
						<li>2011-08-07 11:11:11</li>
						<li>
							<p>tomcat서버를 재시동해보세요. 전 그렇게 하니깐 되던데요.</p>
						</li>
						<li><div class="qa_btn_delete">삭제하기</div></li>
					</ul>
				</div>
				<div class="qa_answer"> 
					<ul>
						<li>auto님의 답변</li>
						<li>2011-08-07 11:11:11</li>
						<li>
							<p>제가 살펴볼께요 메일로 주세요. test@naver.com</p>
						</li>
						<li><div class="qa_btn_delete">삭제하기</div></li>
					</ul>
				</div>
				<div class="qa_write_wrap"> 
					<div class="qa_write_label">
						<label><strong>답변달기</strong></label>
						<textarea rows="2" cols="80" class="qa_write_txtarea"></textarea>					
						<input type="image" src="${pageContext.request.contextPath}/images/home/btn/btn_regist.gif" alt="등록">
					</div>
				</div>
			</div>
		</div>
