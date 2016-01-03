package aramframework.com.sym.ccm.cde.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통상세코드에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface CmmnDetailCodeManageService {

	/**
	 * 공통상세코드 목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	List<EgovMap> selectCmmnDetailCodeList(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	int selectCmmnDetailCodeListCnt(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드 엑셀목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	List<EgovMap> selectCmmnDetailCodeListExcel(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	CmmnDetailCodeVO selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드를 등록한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드를 수정한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드를 삭제한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

}
