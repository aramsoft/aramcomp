package aramframework.com.cmm.com.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aramframework.com.cmm.com.domain.HanjaDicVO;

/**
 * Excel 매핑 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ExcelHanjaDicMapping {

	private int cells = 0;	// column 수
	
	public void setCells(Row row) {
		this.cells = row.getPhysicalNumberOfCells();
	}
	
	public Object mappingColumn(Row row) {
		
		HanjaDicVO vo = new HanjaDicVO();

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
				case 0:		// id(순번)
					vo.setId((int)Double.parseDouble(value));
					break;
				case 1:		// hanja(한자)
					vo.setHanja(value);
					break;
				case 2:		// hanja_dic(한자사전)
					vo.setHanjaDic(value);
					break;
			}
		}

		return vo;
	}
	
}
