package aramframework.com.ssi.syi.iis.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.ssi.syi.iis.domain.CntcInsttVO;
import aramframework.com.ssi.syi.iis.domain.CntcServiceVO;
import aramframework.com.ssi.syi.iis.domain.CntcSystemVO;

/**
 * 연계기관에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface CntcInsttMapper {

	/**
	 * 연계기관 목록을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	public List<CntcInsttVO> selectCntcInsttList(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관 총 갯수를 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	public int selectCntcInsttListCnt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관 상세항목을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	public CntcInsttVO selectCntcInsttDetail(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관을 등록한다.
	 * 
	 * @param cntcInsttVO
	 */
	public void insertCntcInstt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관을 수정한다.
	 * 
	 * @param cntcInsttVO
	 */
	public void updateCntcInstt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관을 삭제한다.
	 * 
	 * @param cntcInsttVO
	 */
	public void deleteCntcInstt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계시스템 목록을 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	public List<CntcSystemVO> selectCntcSystemList(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템 총 갯수를 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	public int selectCntcSystemListCnt(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템 상세항목을 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	public CntcSystemVO selectCntcSystemDetail(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템을 등록한다.
	 * 
	 * @param cntcSystemVO
	 */
	public void insertCntcSystem(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템을 수정한다.
	 * 
	 * @param cntcSystemVO
	 */
	public void updateCntcSystem(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템을 삭제한다.
	 * 
	 * @param cntcSystemVO
	 */
	public void deleteCntcSystem(CntcSystemVO cntcSystemVO);

	/**
	 * 연계서비스 목록을 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	public List<CntcServiceVO> selectCntcServiceList(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스 총 갯수를 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	public int selectCntcServiceListCnt(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스 상세항목을 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	public CntcServiceVO selectCntcServiceDetail(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스를 등록한다.
	 * 
	 * @param cntcServiceVO
	 */
	public void insertCntcService(CntcServiceVO cntcServiceVO);

	/**
	 * 연계시스템을 수정한다.
	 * 
	 * @param cntcServiceVO
	 */
	public void updateCntcService(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스를 삭제한다.
	 * 
	 * @param cntcServiceVO
	 */
	public void deleteCntcService(CntcServiceVO cntcServiceVO);

}
