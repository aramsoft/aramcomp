package aramframework.com.sym.cal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.cal.dao.RestdeManageMapper;
import aramframework.com.sym.cal.domain.RestdeVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 휴일에 대한 서비스 구현클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class RestdeManageService extends EgovAbstractServiceImpl {

	@Autowired
	private RestdeManageMapper restdeManageMapper;
	
	@Autowired
	private EgovIdGnrService restDeIdGnrService; 

	/**
	 * 휴일 목록을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectRestdeList(RestdeVO restdeVO) {
		return restdeManageMapper.selectRestdeList(restdeVO);
	}

	/**
	 * 휴일 총 갯수를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public int selectRestdeListCnt(RestdeVO restdeVO) {
		return restdeManageMapper.selectRestdeListCnt(restdeVO);
	}

	/**
	 * 휴일 상세항목을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public RestdeVO selectRestdeDetail(RestdeVO restdeVO) {
		return restdeManageMapper.selectRestdeDetail(restdeVO);
	}

	/**
	 * 휴일을 등록한다.
	 * 
	 * @param restdeVO
	 */
	public void insertRestde(RestdeVO restdeVO) {
		try {
			restdeVO.setRestdeNo(restDeIdGnrService.getNextIntegerId() % 1000000);
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		restdeManageMapper.insertRestde(restdeVO);
	}

	/**
	 * 휴일을 수정한다.
	 * 
	 * @param restdeVO
	 */
	public void updateRestde(RestdeVO restdeVO) {
		restdeManageMapper.updateRestde(restdeVO);
	}

	/**
	 * 휴일을 삭제한다.
	 * 
	 * @param restdeVO
	 */
	public void deleteRestde(RestdeVO restdeVO) {
		restdeManageMapper.deleteRestde(restdeVO);
	}

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalRestdePopup(RestdeVO restdeVO) {
		return restdeManageMapper.selectNormalRestdePopup(restdeVO);
	}

	/**
	 * 일반달력 일간 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalDayCal(RestdeVO restdeVO) {
		return restdeManageMapper.selectNormalDayCal(restdeVO);
	}

	/**
	 * 일반달력 일간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalDayRestde(RestdeVO restdeVO) {
		return restdeManageMapper.selectNormalDayRestde(restdeVO);
	}

	/**
	 * 일반달력 월간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectNormalMonthRestde(RestdeVO restdeVO) {
		return restdeManageMapper.selectNormalMonthRestde(restdeVO);
	}

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministRestdePopup(RestdeVO restdeVO) {
		return restdeManageMapper.selectAdministRestdePopup(restdeVO);
	}

	/**
	 * 행정달력 일간 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministDayCal(RestdeVO restdeVO) {
		return restdeManageMapper.selectAdministDayCal(restdeVO);
	}

	/**
	 * 행정달력 일간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministDayRestde(RestdeVO restdeVO) {
		return restdeManageMapper.selectAdministDayRestde(restdeVO);
	}

	/**
	 * 행정달력 월간 휴일을 조회한다.
	 * 
	 * @param restdeVO
	 */
	public List<EgovMap> selectAdministMonthRestde(RestdeVO restdeVO) {
		return (List<EgovMap>) restdeManageMapper.selectAdministMonthRestde(restdeVO);
	}

}
