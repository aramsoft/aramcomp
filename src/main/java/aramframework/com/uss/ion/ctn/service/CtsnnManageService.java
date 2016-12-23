package aramframework.com.uss.ion.ctn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.ctn.dao.CtsnnManageMapper;
import aramframework.com.uss.ion.ctn.domain.CtsnnManageVO;
import aramframework.com.uss.ion.ism.domain.InfrmlSanctnVO;
import aramframework.com.uss.ion.ism.service.InfrmlSanctnService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 경조관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class CtsnnManageService extends EgovAbstractServiceImpl {

	@Autowired
	private CtsnnManageMapper ctsnnManageMapper;
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService ctsnnManageIdGnrService;

	@Autowired
	protected InfrmlSanctnService infrmlSanctnService;

	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public List<EgovMap> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO) {
		return ctsnnManageMapper.selectCtsnnManageList(ctsnnManageVO);
	}

	/**
	 * 경조관리목록 총 갯수를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public int selectCtsnnManageListCnt(CtsnnManageVO ctsnnManageVO) {
		return ctsnnManageMapper.selectCtsnnManageListCnt(ctsnnManageVO);
	}

	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO) {
		CtsnnManageVO resultVo = ctsnnManageMapper.selectCtsnnManage(ctsnnManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(ctsnnManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void insertCtsnnManage(CtsnnManageVO ctsnnManageVO) {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		String sYear = Integer.toString(cal.get(java.util.Calendar.YEAR));
		String sMonth = Integer.toString(cal.get(java.util.Calendar.MONTH) + 1);
		if (sMonth.length() == 1)
			sMonth = "0" + sMonth;
		String sDay = Integer.toString(cal.get(java.util.Calendar.DATE));
		if (sDay.length() == 1)
			sDay = "0" + sDay;
		ctsnnManageVO.setReqstDe(sYear + sMonth + sDay);

		/*
		 * 경조 승인처리 신청
		 */
		// InfrmlSanctn infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn("001", ctsnnManage);
		InfrmlSanctnVO infrmlSanctn = infrmlSanctnService.insertInfrmlSanctn(converToInfrmlSanctnObject(ctsnnManageVO)); // 신청

		ctsnnManageVO.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId());
		ctsnnManageVO.setConfmAt(infrmlSanctn.getConfmAt());

		try {
			ctsnnManageVO.setCtsnnId(ctsnnManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		ctsnnManageMapper.insertCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void updateCtsnnManage(CtsnnManageVO ctsnnManageVO) {
		ctsnnManageMapper.updateCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void deleteCtsnnManage(CtsnnManageVO ctsnnManageVO) {
		/*
		 * 포상 승인처리 삭제
		 * infrmlSanctnService.deleteInfrmlSanctn("000", vcatnManage);
		 */
		infrmlSanctnService.deleteInfrmlSanctn(converToInfrmlSanctnObject(ctsnnManageVO)); 
		// 삭제
		// infrmlSanctnService.deleteInfrmlSanctn("001", ctsnnManage);
		ctsnnManageMapper.deleteCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public List<EgovMap> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO) {
		return ctsnnManageMapper.selectCtsnnManageConfmList(ctsnnManageVO);
	}

	/**
	 * 경조승인목록 총 갯수를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public int selectCtsnnManageConfmListCnt(CtsnnManageVO ctsnnManageVO) {
		return ctsnnManageMapper.selectCtsnnManageConfmListCnt(ctsnnManageVO);
	}

	/**
	 * 경조정보를 승인처리 한다.
	 * 
	 * @param ctsnnManageVO
	 */
	public void updtCtsnnManageConfm(CtsnnManageVO ctsnnManageVO) {
		InfrmlSanctnVO infrmlSanctnVO = new InfrmlSanctnVO();
		if (ctsnnManageVO.getConfmAt().equals("C")) {
			/*
			 * 승인처리
			 */
			infrmlSanctnVO = infrmlSanctnService.updateInfrmlSanctnConfm(converToInfrmlSanctnObject(ctsnnManageVO)); // 승인
			// infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnConfm("001", ctsnnManage);
		} else if (ctsnnManageVO.getConfmAt().equals("R")) {
			/*
			 * 반려처리
			 */
			// infrmlSanctn = infrmlSanctnService.updateInfrmlSanctnReturn("001", ctsnnManage);
			infrmlSanctnVO = infrmlSanctnService.updateInfrmlSanctnReturn(converToInfrmlSanctnObject(ctsnnManageVO));
		}
		ctsnnManageVO.setSanctnDt(infrmlSanctnVO.getSanctnDt());
		ctsnnManageVO.setConfmAt(infrmlSanctnVO.getConfmAt());

		ctsnnManageMapper.updtCtsnnManageConfm(ctsnnManageVO);
	}

	/**
	 * CtsnnManage model을 InfrmlSanctn model로 변환한다.
	 * 
	 * @param ctsnnManageVO
	 */
	private InfrmlSanctnVO converToInfrmlSanctnObject(CtsnnManageVO ctsnnManageVO) {
		InfrmlSanctnVO infrmlSanctnVO = new InfrmlSanctnVO();
		infrmlSanctnVO.setJobSeCode("001"); // 업무구분코드 (공통코드 COM75)
		infrmlSanctnVO.setApplcntId(ctsnnManageVO.getUsid()); // 사용자ID
		infrmlSanctnVO.setReqstDe(ctsnnManageVO.getReqstDe()); // 신청일자
		infrmlSanctnVO.setSanctnerId(ctsnnManageVO.getSanctnerId()); // 결재자ID
		infrmlSanctnVO.setConfmAt(ctsnnManageVO.getConfmAt()); // 승인구분
		infrmlSanctnVO.setSanctnDt(ctsnnManageVO.getSanctnDt()); // 결재일시
		infrmlSanctnVO.setReturnResn(ctsnnManageVO.getReturnResn()); // 반려사유
		infrmlSanctnVO.setFrstRegisterId(ctsnnManageVO.getFrstRegisterId());
		infrmlSanctnVO.setFrstRegisterPnttm(ctsnnManageVO.getFrstRegisterPnttm());
		infrmlSanctnVO.setLastUpdusrId(ctsnnManageVO.getLastUpdusrId());
		infrmlSanctnVO.setLastUpdusrPnttm(ctsnnManageVO.getLastUpdusrPnttm());
		infrmlSanctnVO.setInfrmlSanctnId(ctsnnManageVO.getInfrmlSanctnId());// 약식결재ID
		return infrmlSanctnVO;
	}

}
