package aramframework.com.uss.olp.mgt.service;

import java.util.List;

import aramframework.com.cmm.SearchVO;
import aramframework.com.uss.olp.mgt.domain.MeetingManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 회의관리를 처리하기 위한 Service 구현 Class
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

public interface MeetingManageService {

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectDeptListPopup(SearchVO searchVO);

	/**
	 * 아이디 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectEmpLyrListPopup(SearchVO searchVO);

	/**
	 * 회의정보 목록을 조회한다.
	 * 
	 * @param meetingManageVO
	 */
	public List<EgovMap> selectMeetingManageList(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보를 목록 전체 건수를 조회한다.
	 * 
	 * @param meetingManageVO
	 */
	public int selectMeetingManageListCnt(MeetingManageVO meetingManageVOO);

	/**
	 * 회의정보를 상세조회 한다.
	 * 
	 * @param meetingManageVO
	 */
	public MeetingManageVO selectMeetingManageDetail(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보를 등록한다.
	 * 
	 * @param meetingManageVO
	 */
	void insertMeetingManage(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보를 수정한다.
	 * 
	 * @param meetingManageVO
	 */
	void updateMeetingManage(MeetingManageVO meetingManageVO);

	/**
	 * 회의정보를 삭제한다.
	 * 
	 * @param meetingManageVO
	 */
	void deleteMeetingManage(MeetingManageVO meetingManageVO);

}
