/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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
package aramframework.com.cop.cmy.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 엑셀파일을 생성하는 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class ExcelCmyMenuView extends AbstractExcelView {

	/**
	 * 엑셀파일을 설정하고 생성한다.
	 * 
	 * @param model
	 * @param wb
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileName = "메뉴.xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 

		HSSFCell cell = null;

		HSSFSheet sheet = wb.createSheet("메뉴 List");
		sheet.setDefaultColumnWidth(20);

		// set header information
		setText(getCell(sheet, 0, 0), "매뉴번호");
		setText(getCell(sheet, 0, 1), "메뉴명");
		setText(getCell(sheet, 0, 2), "프로그램명");
		setText(getCell(sheet, 0, 3), "메뉴설명");
		setText(getCell(sheet, 0, 4), "사용여부");
		setText(getCell(sheet, 0, 5), "관리자여부");
		setText(getCell(sheet, 0, 6), "바로가기url");
		setText(getCell(sheet, 0, 7), "톱메뉴여부");
		setText(getCell(sheet, 0, 8), "메뉴별명");
		
		List<EgovMap> resultList = (List<EgovMap>) model.get("resultList");

		for (int i = 0; i < resultList.size(); i++) {
			EgovMap result = resultList.get(i);

			cell = getCell(sheet, 1 + i, 0);
			setText(cell, String.valueOf(result.get("menuNo")));

			cell = getCell(sheet, 1 + i, 1);
			setText(cell, (String)result.get("menuNm"));

			cell = getCell(sheet, 1 + i, 2);
			setText(cell, (String)result.get("progrmFileNm"));

			cell = getCell(sheet, 1 + i, 3);
			setText(cell, (String)result.get("menuDc"));

			cell = getCell(sheet, 1 + i, 4);
			setText(cell, (String)result.get("useAt"));
			
			cell = getCell(sheet, 1 + i, 5);
			setText(cell, (String)result.get("mgrAt"));
			
			cell = getCell(sheet, 1 + i, 6);
			setText(cell, (String)result.get("directUrl"));
			
			cell = getCell(sheet, 1 + i, 7);
			setText(cell, (String)result.get("topMenuAt"));
			
			cell = getCell(sheet, 1 + i, 8);
			setText(cell, (String)result.get("menuAlias"));
		}
	}
}	

