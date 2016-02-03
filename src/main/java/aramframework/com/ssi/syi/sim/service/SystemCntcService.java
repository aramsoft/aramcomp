package aramframework.com.ssi.syi.sim.service;

import java.util.List;

import aramframework.com.ssi.syi.sim.domain.SystemCntcVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템연계에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface SystemCntcService {

	/**
	 * 시스템연계 목록을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	List<EgovMap> selectSystemCntcList(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계 총 갯수를 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	int selectSystemCntcListCnt(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계 상세항목을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	SystemCntcVO selectSystemCntcDetail(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계를 등록한다.
	 * 
	 * @param systemCntcVO
	 */
	void insertSystemCntc(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계를 수정한다.
	 * 
	 * @param systemCntcVO
	 */
	void updateSystemCntc(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계를 삭제한다.
	 * 
	 * @param systemCntcVO
	 */
	void deleteSystemCntc(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계 승인/승인취소한다.
	 * 
	 * @param systemCntcVO
	 */
	void confirmSystemCntc(SystemCntcVO systemCntcVO);

}
