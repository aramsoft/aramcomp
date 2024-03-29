package aramframework.com.sym.ccm.cde.dao;

import java.util.List;

import aramframework.com.sym.ccm.cde.domain.CmmnDetailCodeVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통상세코드에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CmmnDetailCodeManageMapper {

	/**
	 * 공통상세코드 목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public List<EgovMap> selectCmmnDetailCodeList(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public int selectCmmnDetailCodeListCnt(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드 엑셀목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public List<EgovMap> selectCmmnDetailCodeListExcel(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public CmmnDetailCodeVO selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드를 등록한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드를 수정한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

	/**
	 * 공통상세코드를 삭제한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

}
