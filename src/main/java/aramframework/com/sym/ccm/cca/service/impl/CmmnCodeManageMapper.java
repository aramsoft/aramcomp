package aramframework.com.sym.ccm.cca.service.impl;

import java.util.List;

import aramframework.com.sym.ccm.cca.domain.CmmnCodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통코드에 대한 데이터 접근 클래스를 정의한다
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

@Mapper
public interface CmmnCodeManageMapper {

	/**
	 * 공통코드 목록을 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public List<EgovMap> selectCmmnCodeList(CmmnCodeVO cmmnCodeVO);

	/**
	 * 공통코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public int selectCmmnCodeListCnt(CmmnCodeVO cmmnCodeVO);


	/**
	 * 공통코드 상세항목을 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCodeVO);

	/**
	 * 공통코드를 등록한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public void insertCmmnCode(CmmnCodeVO cmmnCodeVO);

	/**
	 * 공통코드를 수정한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public void updateCmmnCode(CmmnCodeVO cmmnCodeVO);

	/**
	 * 공통코드를 삭제한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public void deleteCmmnCode(CmmnCodeVO cmmnCodeVO);

}
