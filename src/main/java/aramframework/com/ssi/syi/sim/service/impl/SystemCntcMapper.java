package aramframework.com.ssi.syi.sim.service.impl;

import java.util.List;

import aramframework.com.ssi.syi.sim.service.SystemCntcVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템연계에 대한 데이터 접근 클래스를 정의한다
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

@Mapper("systemCntcMapper")
public interface SystemCntcMapper {

	/**
	 * 시스템연계 목록을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	public List<EgovMap> selectSystemCntcList(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계 총 갯수를 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	public int selectSystemCntcListCnt(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계 상세항목을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	public SystemCntcVO selectSystemCntcDetail(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계를 등록한다.
	 * 
	 * @param systemCntcVO
	 */
	public void insertSystemCntc(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계를 수정한다.
	 * 
	 * @param systemCntcVO
	 */
	public void updateSystemCntc(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계를 삭제한다.
	 * 
	 * @param systemCntcVO
	 */
	public void deleteSystemCntc(SystemCntcVO systemCntcVO);

	/**
	 * 시스템연계 승인/승인취소한다.
	 * 
	 * @param systemCntcVO
	 */
	public void confirmSystemCntc(SystemCntcVO systemCntcVO);

}
