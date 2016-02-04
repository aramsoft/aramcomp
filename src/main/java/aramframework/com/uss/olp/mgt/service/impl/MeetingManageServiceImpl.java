package aramframework.com.uss.olp.mgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olp.mgt.domain.MeetingManageVO;
import aramframework.com.uss.olp.mgt.service.MeetingManageService;
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

@Service("meetingManageService")
public class MeetingManageServiceImpl extends EgovAbstractServiceImpl implements MeetingManageService {

	@Autowired
	private MeetingManageMapper meetingManageMapper;	

	@Autowired
	private EgovIdGnrService meetingIdGnrService; 

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectDeptListPopup(SearchVO searchVO) {
		return meetingManageMapper.selectDeptListPopup(searchVO);
	}

	/**
	 * 아이디 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectEmpLyrListPopup(SearchVO searchVO) {
		return meetingManageMapper.selectEmpLyrListPopup(searchVO);
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
		MeetingManageVO resultVo = meetingManageMapper.selectMeetingManageDetail(meetingManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, meetingManageVO); 
		return resultVo;
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
