package aramframework.com.sym.cal.service;

import java.util.List;

import aramframework.com.sym.cal.domain.RestdeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 휴일에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface RestdeManageService {

	/**
	 * 휴일 목록을 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectRestdeList(RestdeVO restdeVO);

	/**
	 * 휴일 총 갯수를 조회한다.
	 * 
	 * @param restdeVO
	 */
	int selectRestdeListCnt(RestdeVO restdeVO);

	/**
	 * 휴일 상세항목을 조회한다.
	 * 
	 * @param restdeVO
	 */
	RestdeVO selectRestdeDetail(RestdeVO restdeVO);

	/**
	 * 휴일을 등록한다.
	 * 
	 * @param restdeVO
	 */
	void insertRestde(RestdeVO restdeVO);

	/**
	 * 휴일을 수정한다.
	 * 
	 * @param restdeVO
	 */
	void updateRestde(RestdeVO restdeVO);

	/**
	 * 휴일을 삭제한다.
	 * 
	 * @param restdeVO
	 */
	void deleteRestde(RestdeVO restdeVO);

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectNormalRestdePopup(RestdeVO restdeVO);

	/**
	 * 일반달력 일간 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectNormalDayCal(RestdeVO restdeVO);

	/**
	 * 일반달력 일간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectNormalDayRestde(RestdeVO restdeVO);

	/**
	 * 일반달력 월간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectNormalMonthRestde(RestdeVO restdeVO);

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectAdministRestdePopup(RestdeVO restdeVO);

	/**
	 * 행정달력 일간 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectAdministDayCal(RestdeVO restdeVO);

	/**
	 * 행정달력 일간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectAdministDayRestde(RestdeVO restdeVO);

	/**
	 * 행정달력 월간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	List<EgovMap> selectAdministMonthRestde(RestdeVO restdeVO);

}
