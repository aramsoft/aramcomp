package aramframework.com.sym.cal.dao;

import java.util.List;

import aramframework.com.sym.cal.domain.RestdeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 휴일에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface RestdeManageMapper {

	/**
	 * 휴일 목록을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectRestdeList(RestdeVO restdeVO);

	/**
	 * 휴일 목록 총 갯수를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public int selectRestdeListCnt(RestdeVO restdeVO);

	/**
	 * 휴일 상세항목을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public RestdeVO selectRestdeDetail(RestdeVO restdeVO);

	/**
	 * 휴일을 등록한다.
	 * 
	 * @param restdeVO
	 */
	public void insertRestde(RestdeVO restdeVO);

	/**
	 * 휴일을 수정한다.
	 * 
	 * @param restdeVO
	 */
	public void updateRestde(RestdeVO restdeVO);

	/**
	 * 휴일을 삭제한다.
	 * 
	 * @param restdeVO
	 */
	public void deleteRestde(RestdeVO restdeVO);

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalRestdePopup(RestdeVO restdeVO);

	/**
	 * 일반달력 일간 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalDayCal(RestdeVO restdeVO);

	/**
	 * 일반달력 일간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalDayRestde(RestdeVO restdeVO);

	/**
	 * 일반달력 월간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalMonthRestde(RestdeVO restdeVO);

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministRestdePopup(RestdeVO restdeVO);

	/**
	 * 행정달력 일간 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministDayCal(RestdeVO restdeVO);

	/**
	 * 행정달력 일간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministDayRestde(RestdeVO restdeVO);

	/**
	 * 행정달력 월간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministMonthRestde(RestdeVO restdeVO);

}
