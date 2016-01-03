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
<%@ page import = "javax.servlet.*, javax.servlet.http.*, java.io.*, org.apache.lucene.analysis.*, org.apache.lucene.analysis.standard.StandardAnalyzer, org.apache.lucene.document.*, org.apache.lucene.index.*, org.apache.lucene.store.*, org.apache.lucene.search.*, org.apache.lucene.queryParser.*, java.net.URLEncoder, org.apache.lucene.util.Version" %>

<%
/*
        Author: Andrew C. Oliver, SuperLink Software, Inc. (acoliver2@users.sourceforge.net)

        This jsp page is deliberatly written in the horrible java directly embedded 
        in the page style for an easy and concise demonstration of Lucene.
        Due note...if you write pages that look like this...sooner or later
        you'll have a maintenance nightmare.  If you use jsps...use taglibs
        and beans!  That being said, this should be acceptable for a small
        page demonstrating how one uses Lucene in a web app. 

        This is also deliberately overcommented. ;-)

*/
%>
<%!
public String escapeHTML(String s) {
  s = s.replaceAll("&", "&amp;");
  s = s.replaceAll("<", "&lt;");
  s = s.replaceAll(">", "&gt;");
  s = s.replaceAll("\"", "&quot;");
  s = s.replaceAll("'", "&apos;");
  return s;
}
%>
<%@include file="header.jsp"%>
<%
        boolean error = false;                  //used to control flow for error messages
        String indexName = indexLocation;       //local copy of the configuration variable
        IndexSearcher searcher = null;          //the searcher used to open/search the index
        Query query = null;                     //the Query created by the QueryParser
        TopDocs hits = null;                    //the search results
        int startindex = 0;                     //the first index displayed on this page
        int maxpage    = 20;                    //the maximum items displayed on this page
        String queryString = null;              //the query entered in the previous page
        String startVal    = null;              //string version of startindex
        String maxresults  = null;              //string version of maxpage
        int thispage = 0;                       //used for the for/next either maxpage or
                                                //hits.totalHits - startindex - whichever is
                                                //less

        try {
          IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexName)), true); // only searching, so read-only=true
          searcher = new IndexSearcher(reader); //create an indexSearcher for our page
                                                //NOTE: this operation is slow for large
                                                //indices (much slower than the search itself)
                                                //so you might want to keep an IndexSearcher 
                                                //open
                                                        
        } catch (Exception e) {                 //any error that happens is probably due
                                                //to a permission problem or non-existant
                                                //or otherwise corrupt index
%>
                <p>ERROR opening the Index - contact sysadmin! <%=indexName%></p>
                <p>Error message: <%=escapeHTML(e.getMessage())%></p>   
<%                error = true;                                  //don't do anything up to the footer
        }
%>
<% 
       if (error == false) {                                           //did we open the index?
            queryString = request.getParameter("query");            //get the search criteria
            startVal    = request.getParameter("startat");         //get the start index
            maxresults  = request.getParameter("maxresults");      //get max results per page
            try {
                maxpage    = Integer.parseInt(maxresults);     //parse the max results first
                startindex = Integer.parseInt(startVal);       //then the start index  
            } catch (Exception e) { } //we don't care if something happens we'll just start at 0
                                      //or end at 50

            if (queryString == null)
                    throw new ServletException("no query specified");
            //if you don't have a query then you probably played on the 
            //query string so you get the treatment

            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);           //construct our usual analyzer
            try {
                QueryParser qp = new QueryParser("keywords", analyzer);
				qp.setDefaultOperator(QueryParser.AND_OPERATOR);
                query = qp.parse(queryString); 				   //parse the query and construct the Query object
            } catch (ParseException e) {                           //if it's just "operator error" send them a nice error HTML
%>
                <p>Error while parsing query: <%=escapeHTML(e.getMessage())%></p>
<%
                error = true;                                  //don't bother with the rest of the page
            }
        }
%>
<%
        if (error == false && searcher != null) {                      // if we've had no errors
                                                                       // searcher != null was to handle
                                                                       // a weird compilation bug 
                thispage = maxpage;                                    // default last element to maxpage
                hits = searcher.search(query, maxpage + startindex);   // run the query 
                if (hits.totalHits == 0) {                             // if we got no results tell the user
%>
                <p align="center"> 
                	<a href="./index.jsp"><b>[홈]</b></a> &nbsp;&nbsp;&nbsp;
                	검색어 [<%=queryString%>]에 해당하는 문서는 없습니다!
                </p>
<%
                	error = true;                                      // don't bother with the rest of the page
                }
        }

        if (error == false && searcher != null) {                   
%>
                <p align="center"> 
                	<a href="./index.jsp"><b>[홈]</b></a> &nbsp;&nbsp;&nbsp;
                	검색어 : <b><%=queryString%></b>,  &nbsp;&nbsp;&nbsp;총 건수 : <%=hits.totalHits%> 건 
                </p>
                <div align="center">
                <table border="1">
                <tr>
                        <th width="50">No.</th>
                        <th>우편번호</th>
                        <th align="center">도로명 주소</th>
                        <th align="center">지번 주소</th>
                </tr>
<%
                if ((startindex + maxpage) > hits.totalHits) {
                        thispage = hits.totalHits - startindex;      // set the max index to maxpage or last
                }                                                    // actual search result whichever is less

                for (int i = startindex; i < (thispage + startindex); i++) {  // for each element
%>
                <tr>
<%
                        Document doc = searcher.doc(hits.scoreDocs[i].doc);                    //get the next document 
                        String zipcode = doc.get("zipcode");         //get its zipcode
                        String address1 = doc.get("address1");       //get its address1 field
                        String address2 = doc.get("address2");       //get its address1 field
%>
                        <td align="center"><%=i+1%></td>
                        <td align="center"><%=zipcode.substring(0,3)%>-<%=zipcode.substring(3,6)%></td>
                        <td><%=address1%></td>
                        <td><%=address2%></td>
                </tr>
<%
                }
%>
<%                if ( (startindex + maxpage) < hits.totalHits) {    //if there are more results...display 
                                                                     //the more link

                        String moreurl="results.jsp?query=" + queryString + //construct the "more" link
                                       "&maxresults=" + maxpage + 
                                       "&startat=" + (startindex + maxpage);
%>
                <tr>
                        <td></td><td><a href="#" onclick="document.forms[0].submit(); return false;">More Results&gt;&gt;</a></td>
                </tr>
<%
                }
%>
                </table>
                </div>
	<form name="search" action="results.jsp" method="post">
		<input type="hidden" name="query" value="<%=queryString%>" size="44"/>
		<input type="hidden" name="maxresults" value="<%=maxpage%>" size="4" />
		<input type="hidden" name="startat" value="<%=(startindex + maxpage)%>" size="4" />
    </form>

<%       }                                            //then include our footer.
         if (searcher != null)
                searcher.close();
%>
<%@include file="footer.jsp"%>        
