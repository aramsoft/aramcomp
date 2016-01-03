package aramframework.com.uss.ion.ecc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.ion.ecc.service.EventCmpgnService;
import aramframework.com.uss.ion.ecc.service.EventCmpgnVO;
import aramframework.com.uss.ion.ecc.service.TnextrlHrInfoVO;
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

@Service("eventCmpgnService")
public class EventCmpgnServiceImpl extends EgovAbstractServiceImpl implements EventCmpgnService {

	@Resource(name = "eventCmpgnMapper")
	private EventCmpgnMapper eventCmpgnMapper;	

	@Resource(name = "eventInfoIdGnrService")
	private EgovIdGnrService idgenService1;

	@Resource(name = "extrlhrInfoIdGnrService")
	private EgovIdGnrService idgenService2;

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
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, eventCmpgnVO); 
		return resultVo;
	}

	/**
	 * 행사/이벤트/캠페인를(을) 입력한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		try {
			eventCmpgnVO.setEventId(idgenService1.getNextStringId());
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
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, tnextrlHrInfoVO); 
		return resultVo;
	}

	/**
	 * 외부인사정보를(을) 등록한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public void insertTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO) {
		try {
			tnextrlHrInfoVO.setExtrlHrId(idgenService2.getNextStringId());
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
