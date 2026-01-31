package aramframework.com.sym.ccm.ccc.dao;

import java.util.List;

import aramframework.com.sym.ccm.ccc.domain.CmmnClCodeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통분류코드에 대한 데이터 접근 클래스를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CmmnClCodeManageMapper {

	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public List<EgovMap> selectCmmnClCodeList(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public int selectCmmnClCodeListCnt(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public CmmnClCodeVO selectCmmnClCodeDetail(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드를 등록한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드를 수정한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

	/**
	 * 공통분류코드를 삭제한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO);

}
