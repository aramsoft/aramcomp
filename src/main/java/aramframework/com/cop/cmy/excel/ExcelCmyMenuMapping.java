package aramframework.com.cop.cmy.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aramframework.com.cop.cmy.domain.CommunityMenuVO;

/**
 * Excel 매핑 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class ExcelCmyMenuMapping {

	private int cells = 0;	// column 수
	
	public void setCells(Row row) {
		this.cells = row.getPhysicalNumberOfCells();
	}
	
	public Object mappingColumn(Row row) {
		
		CommunityMenuVO vo = new CommunityMenuVO();

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
				case 0:		// MENU_NM(메뉴명)
					vo.setMenuNm(value);
					break;
				case 1:		// MENU_KNM(한글메뉴명)
					vo.setMenuKnm(value);
					break;
				case 2:		// MENU_POS(메뉴위치)
					vo.setMenuPos(value);
					break;
				case 3:		// PROGRM_FILE_NM(프로그램명)
					vo.setProgrmFileNm(value);
					break;
				case 4:		// DIRECT_URL(바로가기url)
					vo.setDirectUrl(value);
					break;
				case 5:		// MENU_DC(메뉴설명)
					vo.setMenuDc(value);
					break;
				case 6:		// TOPMENU_AT(톱메뉴여부)
					vo.setTopMenuAt(value);
					break;
				case 7:		// MGR_AT(관리자메뉴여부)
					vo.setMgrAt(value);
					break;
				case 8:		// USE_AT(사용여부)
					vo.setUseAt(value);
					break;
			}
		}

		return vo;
	}
	
}
