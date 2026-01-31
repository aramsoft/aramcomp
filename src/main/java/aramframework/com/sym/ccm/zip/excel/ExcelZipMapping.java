package aramframework.com.sym.ccm.zip.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aramframework.com.sym.ccm.zip.domain.ZipVO;

/**
 * Excel 우편번호 매핑 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class ExcelZipMapping {

	private int cells = 0;	// column 수
	
	public void setCells(Row row) {
		this.cells = row.getPhysicalNumberOfCells();
	}
	
	/**
	 * 우편번호 엑셀파일 맵핑
	 */
	public Object mappingColumn(Row row) {
		
		ZipVO vo = new ZipVO();

		String value = "";
		for (short c = 0; c < cells; c = (short)(c + 1)) {
			Cell cell = row.getCell(c);
			if (cell != null) {
				switch (cell.getCellTypeEnum()) {
				case NUMERIC:
					value = String.valueOf(cell.getNumericCellValue());
					break;
				case STRING:
					value = cell.getStringCellValue();
					break;
				case FORMULA:
					value = cell.getCellFormula();
					break;
				case BLANK:
					value = null;
					break;
				case BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case ERROR:
					value = String.valueOf(cell.getErrorCellValue());
				default:
					break;
				}
			}
			else {
				value = "";
			}

			switch (c) {
				case 0:		
					vo.setZip(value);
					break;
				case 1:		
					vo.setSn(Integer.parseInt(value));
					break;
				case 2:		
					vo.setCtprvnNm(value);
					break;
				case 3:		
					vo.setSignguNm(value);
					break;
				case 4:		
					vo.setEmdNm(value);
					break;
				case 5:		
					vo.setLiBuldNm(value);
					break;
				case 6:		
					vo.setLnbrDongHo(value);
					break;
				case 7:		
					vo.setFrstRegisterId(value);
					break;
			}
		}

		return vo;
	}
}
