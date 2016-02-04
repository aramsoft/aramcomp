package aramframework.com.cop.cmy.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import egovframework.rte.fdl.excel.EgovExcelMapping;

/**
 * Excel 동문 매핑 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

public class ExcelCmyMenuMapping extends EgovExcelMapping {

	/**
	 * 동문 엑셀파일 맵핑
	 */
	@Override
	public Object mappingColumn(Row row) {
		Cell cell0 = row.getCell((int) 0);		// MENU_NO(매뉴번호)
		Cell cell1 = row.getCell((int) 1);		// MENU_NM(메뉴명)
		Cell cell2 = row.getCell((int) 2);		// PROGRM_FILE_NM(프로그램명)
		Cell cell3 = row.getCell((int) 3);		// MENU_DC(메뉴설명)
		Cell cell4 = row.getCell((int) 4);		// USE_AT(사용여부)
		Cell cell5 = row.getCell((int) 5);		// MGR_AT(관리자메뉴여부)
		Cell cell6 = row.getCell((int) 6);		// DIRECT_URL(바로가기url)
		Cell cell7 = row.getCell((int) 7);		// TOPMENU_AT(톱메뉴여부)
		Cell cell8 = row.getCell((int) 8);		// MENU_ALIAS(메뉴별명)
		String value = "";
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		String trgetId = (String) requestAttributes.getAttribute("curTrgetId", RequestAttributes.SCOPE_REQUEST);
		CommunityMenuVO vo = new CommunityMenuVO();
		vo.setTrgetId(trgetId);

//		log.debug("name = " + cell1.getRichStringCellValue().toString().trim());
		if( cell0.getCellType() == Cell.CELL_TYPE_NUMERIC  ) {				// 매뉴번호
			vo.setMenuNo((int)cell0.getNumericCellValue());	
		} else {
			vo.setMenuNo(Integer.parseInt(cell0.getRichStringCellValue().toString().trim()));
		}

		if (cell1 != null) {	// 메뉴명
			value = cell1.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setMenuNm(value);
			}	
		}
		if (cell2 != null) {	// 프로그램명
			value = cell2.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setProgrmFileNm(value);
			}	
		}
		if (cell3 != null) {	// 메뉴설명
			value = cell3.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setMenuDc(value);
			}	
		}
		if (cell4 != null) {	// 사용여부
			value = cell4.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setUseAt(value);
			}	
		}
		if (cell5 != null) {	// 관리자메뉴여부
			value = cell5.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setMgrAt(value);
			}	
		}
		if (cell6 != null) {	// 바로가기url
			value = cell6.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setDirectUrl(value);
			}	
		}
		if (cell7 != null) {	// 톱메뉴여부
			value = cell7.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setTopMenuAt(value);
			}	
		}
		if (cell8 != null) {	// 메뉴별명
			value = cell8.getRichStringCellValue().toString().trim();
			if( value.length() != 0 ) {
				vo.setMenuAlias(value);
			}	
		}

		return vo;
	}
}
