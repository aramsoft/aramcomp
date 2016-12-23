package aramframework.com.uss.ion.ecc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.ecc.dao.EventCmpgnMapper;
import aramframework.com.uss.ion.ecc.domain.EventCmpgnVO;
import aramframework.com.uss.ion.ecc.domain.TnextrlHrInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행사/이벤트/캠페인을 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class EventCmpgnService extends EgovAbstractServiceImpl {

	@Autowired
	private EventCmpgnMapper eventCmpgnMapper;	

	@Autowired
	private EgovIdGnrService eventInfoIdGnrService; 

	@Autowired
	private EgovIdGnrService extrlhrInfoIdGnrService; 

	/**
	 * 행사/이벤트/캠페인를(을) 목록을 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public List<EgovMap> selectEventCmpgnList(EventCmpgnVO eventCmpgnVO) {
		return eventCmpgnMapper.selectEventCmpgnList(eventCmpgnVO);
	}

	/**
	 * 행사/이벤트/캠페인를(을) 전체 갯수를 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public int selectEventCmpgnListCnt(EventCmpgnVO eventCmpgnVO) {
		return (Integer) eventCmpgnMapper.selectEventCmpgnListCnt(eventCmpgnVO);
	}

	/**
	 * 행사/이벤트/캠페인를(을) 상세조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO) {
		EventCmpgnVO resultVo = eventCmpgnMapper.selectEventCmpgnDetail(eventCmpgnVO);
		// searchVO 이전 
		resultVo.setSearchVO(eventCmpgnVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 행사/이벤트/캠페인를(을) 입력한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		try {
			eventCmpgnVO.setEventId(eventInfoIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		eventCmpgnMapper.insertEventCmpgn(eventCmpgnVO);
	}

	/**
	 * 행사/이벤트/캠페인를(을) 수정한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public void updateEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		eventCmpgnMapper.updateEventCmpgn(eventCmpgnVO);
	}

	/**
	 * 행사/이벤트/캠페인를(을) 삭제한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		eventCmpgnMapper.deleteEventCmpgnTnextrlHrInfo(eventCmpgnVO);
		eventCmpgnMapper.deleteEventCmpgn(eventCmpgnVO);
	}

	/**
	 * 외부인사정보를(을) 목록을 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public List<EgovMap> selectTnextrlHrInfoList(TnextrlHrInfoVO tnextrlHrInfoVO) {
		return eventCmpgnMapper.selectTnextrlHrInfoList(tnextrlHrInfoVO);
	}

	/**
	 * 외부인사정보를(을) 전체 갯수를 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public int selectTnextrlHrInfoListCnt(TnextrlHrInfoVO tnextrlHrInfoVO) {
		return (Integer) eventCmpgnMapper.selectTnextrlHrInfoListCnt(tnextrlHrInfoVO);
	}

	/**
	 * 외부인사정보를(을) 상세조회 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public TnextrlHrInfoVO selectTnextrlHrInfoDetail(TnextrlHrInfoVO tnextrlHrInfoVO) {
		TnextrlHrInfoVO resultVo = eventCmpgnMapper.selectTnextrlHrInfoDetail(tnextrlHrInfoVO);
		// searchVO 이전 
		resultVo.setSearchVO(tnextrlHrInfoVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 외부인사정보를(을) 등록한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public void insertTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO) {
		try {
			tnextrlHrInfoVO.setExtrlHrId(extrlhrInfoIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		eventCmpgnMapper.insertTnextrlHrInfo(tnextrlHrInfoVO);
	}

	/**
	 * 외부인사정보를(을) 수정한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public void updateTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO) {
		eventCmpgnMapper.updateTnextrlHrInfo(tnextrlHrInfoVO);
	}

	/**
	 * 외부인사정보를(을) 삭제한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public void deleteTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO) {
		eventCmpgnMapper.deleteTnextrlHrInfo(tnextrlHrInfoVO);
	}
	
}
