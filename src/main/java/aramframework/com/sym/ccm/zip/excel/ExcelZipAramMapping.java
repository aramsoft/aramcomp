package aramframework.com.sym.ccm.zip.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aramframework.com.sym.ccm.zip.domain.ZipAramVO;

/**
 * Excel 우편번호 매핑 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ExcelZipAramMapping {

	private int cells = 0;	// column 수
	
	public void setCells(Row row) {
		this.cells = row.getPhysicalNumberOfCells();
	}
	
	/**
	 * 우편번호 엑셀파일 맵핑
	 */
	public Object mappingColumn(Row row) {

		ZipAramVO vo = new ZipAramVO();

		String value = "";
		for (short c = 0; c < cells; c = (short)(c + 1)) {
			Cell cell = row.getCell(c);
			if (cell != null) {
				switch (cell.getCellType()) {
					case 0:
						value = String.valueOf(cell.getNumericCellValue());
						break;
					case 1:
						value = cell.getStringCellValue();
						break;
					case 2:
						value = cell.getCellFormula();
						break;
					case 3:
						value = null;
						break;
					case 4:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					case 5:
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
					vo.setSn(value);
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
					vo.setLiNm(value);
					break;
				case 6:		
					vo.setDoseoNm(value);
					break;
				case 7:		
					vo.setSanYn(value);
					break;
				case 8:		
					vo.setStBunjiM(value);
					break;
				case 9:		
					vo.setStBunjiS(value);
					break;
				case 10:		
					vo.setEdBunjiM(value);
					break;
				case 11:		
					vo.setEdBunjiS(value);
					break;
				case 12:		
					vo.setBuldNm(value);
					break;
				case 13:		
					vo.setStDong(value);
					break;
				case 14:		
					vo.setEdDong(value);
					break;
				case 15:		
					vo.setFixDate(value);
					break;
				case 16:		
					vo.setAddress(value);
					break;
			}
		}

		return vo;
	}
}
