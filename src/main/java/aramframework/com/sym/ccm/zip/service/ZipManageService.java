package aramframework.com.sym.ccm.zip.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import aramframework.com.sym.ccm.zip.domain.ZipVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 우편번호에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface ZipManageService {

	/**
	 * 도로명 목록을 조회 한다.
	 * 
	 * @param zipVO
	 */
	public Map<String, Object> selectRdNmList(ZipVO zipVO);

	/**
	 * 우편번호 목록을 조회한다.
	 * 
	 * @param zipVO
	 */
	List<EgovMap> selectZipList(ZipVO zipVO);

	/**
	 * 우편번호 총 갯수를 조회한다.
	 * 
	 * @param zipVO
	 */
	int selectZipListCnt(ZipVO zipVO);

	/**
	 * 우편번호 상세항목을 조회한다.
	 * 
	 * @param zipVO
	 */
	ZipVO selectZipDetail(ZipVO zipVO);

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	void insertZip(ZipVO zipVO);

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * 
	 * @param file
	 */
	void insertExcelZip(InputStream file);

	/**
	 * 우편번호 엑셀파일을 등록한다.(아람버전)
	 * 
	 * @param file
	 */
	void insertExcelZipAram(InputStream file);

	/**
	 * 우편번호를 수정한다.
	 * 
	 * @param zipVO
	 */
	void updateZip(ZipVO zipVO);

	/**
	 * 우편번호를 삭제한다.
	 * 
	 * @param zipVO
	 */
	void deleteZip(ZipVO zipVO);

	/**
	 * 우편번호 전체를 삭제한다.
	 * 
	 */
	void deleteAllZip();

}
