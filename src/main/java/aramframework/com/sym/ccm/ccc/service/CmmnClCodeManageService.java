package aramframework.com.sym.ccm.ccc.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통분류코드에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface CmmnClCodeManageService {

	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	List<EgovMap> selectCmmnClCodeList(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	int selectCmmnClCodeListCnt(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	CmmnClCodeVO selectCmmnClCodeDetail(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드를 등록한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드를 수정한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드를 삭제한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

}
