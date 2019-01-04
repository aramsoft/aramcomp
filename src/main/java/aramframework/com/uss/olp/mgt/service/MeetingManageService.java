package aramframework.com.uss.olp.mgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.uss.olp.mgt.dao.MeetingManageMapper;
import aramframework.com.uss.olp.mgt.domain.MeetingManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 회의관리를 처리하기 위한 ServiceImpl 구현 Class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class MeetingManageService extends EgovAbstractServiceImpl {

	@Autowired
	private MeetingManageMapper meetingManageMapper;	

	@Autowired
	private EgovIdGnrService meetingIdGnrService; 

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectDeptListPopup(BaseVO baseVO) {
		return meetingManageMapper.selectDeptListPopup(baseVO);
	}

	/**
	 * 아이디 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectEmpLyrListPopup(BaseVO baseVO) {
		return meetingManageMapper.selectEmpLyrListPopup(baseVO);
	}

	/**
	 * 회의정보 목록을 조회한다.
	 * 
	 * @param meetingManageVO
	 */
	public List<EgovMap> selectMeetingManageList(MeetingManageVO meetingManageVO) {
		return meetingManageMapper.selectMeetingManageList(meetingManageVO);
	}

	/**
	 * 회의정보를 목록 전체 건수를 조회한다.
	 * 
	 * @param meetingManageVO
	 */
	public int selectMeetingManageListCnt(MeetingManageVO meetingManageVO) {
		return (Integer) meetingManageMapper.selectMeetingManageListCnt(meetingManageVO);
	}

	/**
	 * 회의정보를 상세조회 한다.
	 * 
	 * @param meetingManageVO
	 */
	public MeetingManageVO selectMeetingManageDetail(MeetingManageVO meetingManageVO) {
		return meetingManageMapper.selectMeetingManageDetail(meetingManageVO);
	}

	/**
	 * 회의정보를 등록한다.
	 * 
	 * @param meetingManageVO
	 */
	public void insertMeetingManage(MeetingManageVO meetingManageVO) {
		try {
			meetingManageVO.setMtgId(meetingIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		meetingManageMapper.insertMeetingManage(meetingManageVO);
	}

	/**
	 * 회의정보를 수정한다.
	 * 
	 * @param meetingManageVO
	 */
	public void updateMeetingManage(MeetingManageVO meetingManageVO) {
		meetingManageMapper.updateMeetingManage(meetingManageVO);
	}

	/**
	 * 회의정보를 삭제한다.
	 * 
	 * @param meetingManageVO
	 */
	public void deleteMeetingManage(MeetingManageVO meetingManageVO) {
		meetingManageMapper.deleteMeetingManage(meetingManageVO);
	}
	
}
