<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 -->
<%@include file="header.jsp"%>
<% /* Author: Andrew C. Oliver (acoliver2@users.sourceforge.net) */ %>
<center> 
	<form name="search" action="results.jsp" method="post">
		<p>
			검색어 : <input name="query" size="44"/>
		</p>
		<p>
			페이지당 건수 : <input name="maxresults" size="4" value="20"/>
			<input type="submit" value="검색"/>
		</p>
    </form>
    <p>
		사용법 : 검색대상 =&gt; [시도]<sup>*1)</sup> [시군구] [읍면동] [지번본번] [도로명] [건물본번] [건물명] [상세건물명]
	</p>
	<table border="1">
		<tr>
			<td>AND 검색   :</td>
			<td>A AND B<br/>A B<sup>*2)</sup></td>
			<td>A 이고 B 인 경우 </td>
		</tr>
		<tr>
			<td>OR 검색   :</td>
			<td>A OR B</td>
			<td>A 또는 B 인 경우 </td>
		</tr>
		<tr>
			<td>NOT 검색    :</td>
			<td>A AND NOT B<br/>+A -B</td>
			<td>A 이지만 B 가 아닌 경우 </td>
		</tr>
		<tr>
			<td>중첩검색    :</td>
			<td>(A OR B) AND C</td>
			<td>A 또는 B 이고 C 인 경우  </td>
		</tr>
		<tr>
			<td>와일드카드검색    :</td>
			<td>A*</td>
			<td>A로 시작하는 경우  </td>
		</tr>
		<tr>
			<td>완전일치검색    :</td>
			<td>"A B C"</td>
			<td>A B C 가 순서대로 나오는 경우  </td>
		</tr>
	</table>
	*1) [시도]는 약어검색이 가능합니다. 예)서울특별시 =&gt; 서울, 서울시<br> 
	*2) 원래 연산자 없이 키워드를 나열하면 기본이 or 연산으로 수행되지만  <br>검색범위를 축소하기 위해 and 연산으로 바꿨습니다.
</center>
<%@include file="footer.jsp"%>
