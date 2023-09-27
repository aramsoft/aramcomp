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

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 엑셀파일을 생성하는 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ExcelCmyMenuView extends AbstractXlsxView {

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
			Workbook wb, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileName = "메뉴.xlsx"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 

		Sheet sheet = wb.createSheet("메뉴 List");
		sheet.setDefaultColumnWidth(20);

		// set header information
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("매뉴명");
		row.createCell(1).setCellValue("메뉴한글명");
		row.createCell(2).setCellValue("메뉴위치");
		row.createCell(3).setCellValue("프로그램명");
		row.createCell(4).setCellValue("바로가기url");
		row.createCell(5).setCellValue("메뉴설명");
		row.createCell(6).setCellValue("상위메뉴여부");
		row.createCell(7).setCellValue("관리자여부");
		row.createCell(8).setCellValue("사용여부");
		
		List<EgovMap> resultList = (List<EgovMap>) model.get("resultList");

		for (int i = 0; i < resultList.size(); i++) {
			EgovMap result = resultList.get(i);

			row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue((String)result.get("menuNm"));
			row.createCell(1).setCellValue((String)result.get("menuKnm"));
			row.createCell(2).setCellValue((String)result.get("menuPos"));
			row.createCell(3).setCellValue((String)result.get("progrmFileNm"));
			row.createCell(4).setCellValue((String)result.get("directUrl"));
			row.createCell(5).setCellValue((String)result.get("menuDc"));
			row.createCell(6).setCellValue((String)result.get("topMenuAt"));
			row.createCell(7).setCellValue((String)result.get("mgrAt"));
			row.createCell(8).setCellValue((String)result.get("useAt"));
		}
	}
}	

