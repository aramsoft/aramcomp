package aramframework.com.uss.ion.evt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.ion.evt.service.EventManageService;
import aramframework.com.uss.ion.evt.service.EventAtdrnVO;
import aramframework.com.uss.ion.evt.service.EventManageVO;
import aramframework.com.uss.ion.ism.service.InfrmlSanctnService;
import aramframework.com.uss.ion.ism.service.InfrmlSanctnVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 행사관리에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("eventManageService")
public class EventManageServiceImpl extends EgovAbstractServiceImpl implements EventManageService {

	@Autowired
	private EventManageMapper eventManageMapper;
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService eventManageIdGnrService; 

	@Autowired
	protected InfrmlSanctnService infrmlSanctnService;

	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public List<EgovMap> selectEventManageList(EventManageVO eventManageVO) {
		return eventManageMapper.selectEventManageList(eventManageVO);
	}

	/**
	 * 행사관리목록 총 갯수를 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public int selectEventManageListCnt(EventManageVO eventManageVO) {
		return eventManageMapper.selectEventManageListCnt(eventManageVO);
	}

	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public EventManageVO selectEventManage(EventManageVO eventManageVO) {
		EventManageVO resultVo = eventManageMapper.selectEventManage(eventManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, eventManageVO); 
		return resultVo;
	}

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * 
	 * @param eventManageVO
	 */
	public void insertEventManage(EventManageVO eventManageVO) {
		try {
			eventManageVO.setEventId(eventManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		eventManageMapper.insertEventManage(eventManageVO);
	}

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * 
	 * @param eventManageVO
	 */
	public void updateEventManage(EventManageVO eventManageVO) {
		eventManageMapper.updateEventManage(eventManageVO);
	}

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * 
	 * @param eventManageVO
	 */
	public void deleteEventManage(EventManageVO eventManageVO) {
		eventManageMapper.deleteEventManage(eventManageVO);
	}

	/*** 행사접수관리 ****/
	/**
	 * 행사접수관리정보를 관리하기 위해 등록된 행사접수관리 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public List<EgovMap> selectEventAtdrnList(EventAtdrnVO eventAtdrnVO) {
		return eventManageMapper.selectEventAtdrnList(eventAtdrnVO);
	}

	/**
	 * 행사접수관리목록 총 갯수를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public int selectEventAtdrnListCnt(EventAtdrnVO eventAtdrnVO) {
		return eventManageMapper.selectEventAtdrnListCnt(eventAtdrnVO);
	}

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public List<EgovMap> selectEventRceptConfmList(EventAtdrnVO eventAtdrnVO) {
		return eventManageMapper.selectEventRceptConfmList(eventAtdrnVO);
	}

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록 총 갯수를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public int selectEventRceptConfmListCnt(EventAtdrnVO eventAtdrnVO) {
		return eventManageMapper.selectEventRceptConfmListCnt(eventAtdrnVO);
	}

	/**
	 * 행사일자, 행사구분 조건에 따른 행사명 목록을 조회한다.
	 * 
	 * @param eventManageVO
	 */
	public List<EgovMap> selectEventNmList(EventManageVO eventManageVO) {
		return eventManageMapper.selectEventNmList(eventManageVO);
	}

	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public EventAtdrnVO selectEventAtdrn(EventAtdrnVO eventAtdrnVO) {
		EventAtdrnVO resultVo = eventManageMapper.selectEventAtdrn(eventAtdrnVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, eventAtdrnVO); 
		return resultVo;
	}

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public void insertEventAtdrn(EventAtdrnVO eventAtdrnVO) {
		/*
		 * 행사접수 승인처리 신청 
		 * infrmlSanctnService.insertInfrmlSanctn("000", vcatnManage);
		 */
		InfrmlSanctnVO infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(eventAtdrnVO)); // 신청
		// InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("004", eventAtdrn);
		eventAtdrnVO.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		eventAtdrnVO.setConfmAt(infrmlSanctn.getConfmAt());
		eventManageMapper.insertEventAtdrn(eventAtdrnVO);
	}

	/**
	 * 기 등록된 행사접수관리정보를 삭제한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public void deleteEventAtdrn(EventAtdrnVO eventAtdrnVO) {
		/*
		 * 행사접수 승인처리 삭제
		 */
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(eventAtdrnVO)); // 삭제
		eventManageMapper.deleteEventAtdrn(eventAtdrnVO);
	}

	/**
	 * 기 등록된 행사접수관리정보를 승인/반려처리한다.
	 * 
	 * @param eventAtdrnVO
	 * @param checkedEventRceptForConfm
	 */
	public void updateEventAtdrn(EventAtdrnVO eventAtdrnVO, String checkedEventRceptForConfm) {

		// MtgPlaceFxtrs mtgPlaceFxtrs; 
		// 2011.8.11 수정분 mtg(회의실관리 컴포넌트)와의 의존성 제거
		// int insertCnt = 0; // 2011.8.11 수정분
		String[] eventRceptValues = checkedEventRceptForConfm.split("[$]");
		String[] sTempEventRcept;
		String sTemp = null;
		for (int i = 0; i < eventRceptValues.length; i++) {
			sTemp = eventRceptValues[i];
			sTempEventRcept = sTemp.split(",");
			eventAtdrnVO.setEventId(sTempEventRcept[0]);
			eventAtdrnVO.setApplcntId(sTempEventRcept[1]);
			eventAtdrnVO.setInfrmlSanctnId(sTempEventRcept[2]);
			eventAtdrnVO.setReqstDe(sTempEventRcept[3]);
			InfrmlSanctnVO infrmlSanctn = new InfrmlSanctnVO();

			if (eventAtdrnVO.getConfmAt().equals("C")) {
				/*
				 * 승인처리
				 */
				infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(eventAtdrnVO)); // 승인
				// infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm("004", eventAtdrn);
			} else if (eventAtdrnVO.getConfmAt().equals("R")) {
				/*
				 * 반려처리
				 */
				infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(eventAtdrnVO)); // 반려
				// infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn("004", eventAtdrn);
			}
			eventAtdrnVO.setSanctnDt(infrmlSanctn.getSanctnDt());
			eventAtdrnVO.setConfmAt(infrmlSanctn.getConfmAt());

			eventManageMapper.updateEventAtdrn(eventAtdrnVO);
		}
	}

	/**
	 * 행사접수자 정보를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public List<EgovMap> selectEventReqstAtdrnList(EventAtdrnVO eventAtdrnVO) {
		return eventManageMapper.selectEventReqstAtdrnList(eventAtdrnVO);
	}

	/**
	 * 행사접수자 목록 총 갯수를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	public int selectEventReqstAtdrnListCnt(EventAtdrnVO eventAtdrnVO) {
		return eventManageMapper.selectEventReqstAtdrnListCnt(eventAtdrnVO);
	}

	/**
	 * CtsnnManage model을 InfrmlSanctn model로 변환한다.
	 * 
	 * @param eventAtdrnVO
	 */
	private InfrmlSanctnVO converToInfrmlSanctnObject(EventAtdrnVO eventAtdrnVO) {
		InfrmlSanctnVO infrmlSanctnVO = new InfrmlSanctnVO();
		infrmlSanctnVO.setJobSeCode("004"); // 업무구분코드 (공통코드 COM75)
		infrmlSanctnVO.setApplcntId(eventAtdrnVO.getApplcntId()); // 사용자ID
		infrmlSanctnVO.setReqstDe(eventAtdrnVO.getReqstDe()); // 신청일자
		infrmlSanctnVO.setSanctnerId(eventAtdrnVO.getSanctnerId()); // 결재자ID
		infrmlSanctnVO.setConfmAt(eventAtdrnVO.getConfmAt()); // 승인구분
		infrmlSanctnVO.setSanctnDt(eventAtdrnVO.getSanctnDt()); // 결재일시
		infrmlSanctnVO.setReturnResn(eventAtdrnVO.getReturnResn()); // 반려사유
		infrmlSanctnVO.setFrstRegisterId(eventAtdrnVO.getFrstRegisterId());
		infrmlSanctnVO.setFrstRegisterPnttm(eventAtdrnVO.getFrstRegisterPnttm());
		infrmlSanctnVO.setLastUpdusrId(eventAtdrnVO.getLastUpdusrId());
		infrmlSanctnVO.setLastUpdusrPnttm(eventAtdrnVO.getLastUpdusrPnttm());
		infrmlSanctnVO.setInfrmlSanctnId(eventAtdrnVO.getInfrmlSanctnId());// 약식결재ID
		return infrmlSanctnVO;
	}

}
