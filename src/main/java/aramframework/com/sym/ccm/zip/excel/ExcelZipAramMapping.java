package aramframework.com.sym.ccm.zip.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import aramframework.com.sym.ccm.zip.domain.ZipAramVO;
import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

/**
 * Excel 우편번호 매핑 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ExcelZipAramMapping extends EgovExcelMapping {

	/**
	 * 우편번호 엑셀파일 맵핑
	 */
	@Override
	public Object mappingColumn(Row row) {
		Cell cell00 = row.getCell((int) 0);
		Cell cell01 = row.getCell((int) 1);
		Cell cell02 = row.getCell((int) 2);
		Cell cell03 = row.getCell((int) 3);
		Cell cell04 = row.getCell((int) 4);
		Cell cell05 = row.getCell((int) 5);
		Cell cell06 = row.getCell((int) 6);
		Cell cell07 = row.getCell((int) 7);
		Cell cell08 = row.getCell((int) 8);
		Cell cell09 = row.getCell((int) 9);
		Cell cell10 = row.getCell((int) 10);
		Cell cell11 = row.getCell((int) 11);
		Cell cell12 = row.getCell((int) 12);
		Cell cell13 = row.getCell((int) 13);
		Cell cell14 = row.getCell((int) 14);
		Cell cell15 = row.getCell((int) 15);
		Cell cell16 = row.getCell((int) 16);

		ZipAramVO vo = new ZipAramVO();

		vo.setZip(EgovExcelUtil.getValue(cell00));
		vo.setSn(EgovExcelUtil.getValue(cell01));
		vo.setCtprvnNm(EgovExcelUtil.getValue(cell02));
		vo.setSignguNm(EgovExcelUtil.getValue(cell03));
		vo.setEmdNm(EgovExcelUtil.getValue(cell04));

		if (cell05 != null) {
			vo.setLiNm(EgovExcelUtil.getValue(cell05));
		}
		if (cell06 != null) {
			vo.setDoseoNm(EgovExcelUtil.getValue(cell06));
		}
		if (cell07 != null) {
			vo.setSanYn(EgovExcelUtil.getValue(cell07));
		}
		if (cell08 != null ) {
			vo.setStBunjiM(EgovExcelUtil.getValue(cell08));
		}
		if (cell09 != null) {
			vo.setStBunjiS(EgovExcelUtil.getValue(cell09));
		}
		if (cell10 != null) {
			vo.setEdBunjiM(EgovExcelUtil.getValue(cell10));
		}
		if (cell11 != null) {
			vo.setEdBunjiS(EgovExcelUtil.getValue(cell11));
		}
		if (cell12 != null) {
			vo.setBuldNm(EgovExcelUtil.getValue(cell12));
		}
		if (cell13 != null) {
			vo.setStDong(EgovExcelUtil.getValue(cell13));
		}
		if (cell14 != null) {
			vo.setEdDong(EgovExcelUtil.getValue(cell14));
		}
		vo.setFixDate(EgovExcelUtil.getValue(cell15));
		vo.setAddress(EgovExcelUtil.getValue(cell16));

		return vo;
	}
}
