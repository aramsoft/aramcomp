package aramframework.com.sym.ccm.zip.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aramframework.com.sym.ccm.zip.domain.ZipVO;
import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

/**
 * Excel 우편번호 매핑 클래스
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

public class ExcelZipMapping extends EgovExcelMapping {

	/**
	 * 우편번호 엑셀파일 맵핑
	 */
	@Override
	public Object mappingColumn(Row row) {
		Cell cell0 = row.getCell((int) 0);
		Cell cell1 = row.getCell((int) 1);
		Cell cell2 = row.getCell((int) 2);
		Cell cell3 = row.getCell((int) 3);
		Cell cell4 = row.getCell((int) 4);
		Cell cell5 = row.getCell((int) 5);
		Cell cell6 = row.getCell((int) 6);
		Cell cell7 = row.getCell((int) 7);

		ZipVO vo = new ZipVO();

		vo.setZip(EgovExcelUtil.getValue(cell0));
		vo.setSn(Integer.parseInt(EgovExcelUtil.getValue(cell1)));
		vo.setCtprvnNm(EgovExcelUtil.getValue(cell2));
		vo.setSignguNm(EgovExcelUtil.getValue(cell3));
		vo.setEmdNm(EgovExcelUtil.getValue(cell4));
		vo.setFrstRegisterId(EgovExcelUtil.getValue(cell7));

		if (cell5 != null) {
			vo.setLiBuldNm(EgovExcelUtil.getValue(cell5));
		}
		if (cell6 != null) {
			vo.setLnbrDongHo(EgovExcelUtil.getValue(cell6));
		}

		return vo;
	}
}
