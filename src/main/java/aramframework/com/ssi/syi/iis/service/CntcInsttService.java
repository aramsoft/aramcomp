package aramframework.com.ssi.syi.iis.service;

import java.util.List;

/**
 * 연계기관에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface CntcInsttService {

	/**
	 * 연계기관 목록을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	List<CntcInsttVO> selectCntcInsttList(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관 총 갯수를 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	int selectCntcInsttListCnt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관 상세항목을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	CntcInsttVO selectCntcInsttDetail(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관을 등록한다.
	 * 
	 * @param cntcInsttVO
	 */
	void insertCntcInstt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관을 수정한다.
	 * 
	 * @param cntcInsttVO
	 */
	void updateCntcInstt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계기관을 삭제한다.
	 * 
	 * @param cntcInsttVO
	 */
	void deleteCntcInstt(CntcInsttVO cntcInsttVO);

	/**
	 * 연계시스템 목록을 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	List<CntcSystemVO> selectCntcSystemList(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템 총 갯수를 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	int selectCntcSystemListCnt(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템 상세항목을 조회한다.
	 * 
	 * @param cntcSystemVO
	 */
	CntcSystemVO selectCntcSystemDetail(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템을 등록한다.
	 * 
	 * @param cntcSystemVO
	 */
	void insertCntcSystem(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템을 수정한다.
	 * 
	 * @param cntcSystemVO
	 */
	void updateCntcSystem(CntcSystemVO cntcSystemVO);

	/**
	 * 연계시스템을 삭제한다.
	 * 
	 * @param cntcSystemVO
	 */
	void deleteCntcSystem(CntcSystemVO cntcSystemVO);

	/**
	 * 연계서비스 목록을 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	List<CntcServiceVO> selectCntcServiceList(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스 총 갯수를 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	int selectCntcServiceListCnt(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스 상세항목을 조회한다.
	 * 
	 * @param cntcServiceVO
	 */
	CntcServiceVO selectCntcServiceDetail(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스를 등록한다.
	 * 
	 * @param cntcServiceVO
	 */
	void insertCntcService(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스 수정한다.
	 * 
	 * @param cntcServiceVO
	 */
	void updateCntcService(CntcServiceVO cntcServiceVO);

	/**
	 * 연계서비스를 삭제한다.
	 * 
	 * @param cntcServiceVO
	 */
	void deleteCntcService(CntcServiceVO cntcServiceVO);

}
