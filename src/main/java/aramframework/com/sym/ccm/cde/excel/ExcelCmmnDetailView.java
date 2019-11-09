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
package aramframework.com.sym.ccm.cde.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
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
 */
public class ExcelCmmnDetailView extends AbstractExcelView {

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

		String fileName = "CommonCode.xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 

		HSSFSheet sheet = wb.createSheet("Codes List");
		sheet.setDefaultColumnWidth(12);

		// set header information
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("No.");
		row.createCell(1).setCellValue("코드ID");
		row.createCell(2).setCellValue("코드ID명");
		row.createCell(3).setCellValue("코드");
		row.createCell(4).setCellValue("코드명");
		row.createCell(5).setCellValue("사용여부");

		List<EgovMap> resultList = (List<EgovMap>) model.get("resultList");

		for (int i = 0; i < resultList.size(); i++) {
			EgovMap result = resultList.get(i);

			row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(Integer.toString(i + 1));
			row.createCell(1).setCellValue((String)result.get("codeId"));
			row.createCell(2).setCellValue((String)result.get("codeIdNm"));
			row.createCell(3).setCellValue((String)result.get("code"));
			row.createCell(4).setCellValue((String)result.get("codeNm"));
			row.createCell(5).setCellValue((String)result.get("useAt"));
		}
	}
	
}	

