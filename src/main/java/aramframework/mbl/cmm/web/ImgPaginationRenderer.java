/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aramframework.mbl.cmm.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * 
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class ImgPaginationRenderer extends AbstractPaginationRenderer {
	
    /**
    * PaginationRenderer
	* 
    * @see 개발프레임웍크 실행환경 개발팀
    */
	public ImgPaginationRenderer() {

		//String strWebDir = "/aramframework.guideprogram.basic/images/aramframework/cmmn/"; // localhost
		//String strWebDir = "/###ARTIFACT_ID###/images/aramframework/cmmn/";
		/*String strWebDir = "/images/aramframework/cmmn/";
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
							"<image src='" + strWebDir + "btn_page_pre10.gif' border=0/></a>&#160;"; 
        previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
        						"<image src='" + strWebDir + "btn_page_pre1.gif' border=0/></a>&#160;";
        currentPageLabel = "<strong>{0}</strong>&#160;";
        otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
        nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
        					"<image src='" + strWebDir + "btn_page_next10.gif' border=0/></a>&#160;";
        lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
        					"<image src='" + strWebDir + "btn_page_next1.gif' border=0/></a>&#160;";*/
		
		firstPageLabel = "<a href=\"javascript:{0}({1}); return false;\" class=\"first\" onclick=\"{0}({1}); return false;\">" + "</a>&#160;"; 
        previousPageLabel = "<a href=\"javascript:{0}({1}); return false;\" class=\"prev\" onclick=\"{0}({1}); return false;\">" + "</a>&#160;";
        currentPageLabel = "<span class=\"num\"><strong>{0}</strong></span>&#160;";
        otherPageLabel = "<span><a href=\"javascript:{0}({1});return false;\" onclick=\"{0}({1});return false;\">{2}</a></span>&#160;";
        nextPageLabel = "<a href=\"javascript:{0}({1}); return false;\" class=\"next\" onclick=\"{0}({1}); return false;\">" + "</a>&#160;";
        lastPageLabel = "<a href=\"javascript:{0}({1}); return false;\" class=\"last\" onclick=\"{0}({1}); return false;\">" + "</a>&#160;";
        
	}
}
