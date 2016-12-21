package aramframework.com.uss.ion.nts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.nts.dao.NoteTrnsmitMapper;
import aramframework.com.uss.ion.nts.domain.NoteTrnsmitVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 보낸쪽지함관리를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class NoteTrnsmitService extends EgovAbstractServiceImpl {

	@Autowired
	private NoteTrnsmitMapper noteTrnsmitMapper;	

	/**
	 * 보낸쪽지함관리를(을) 목록을 조회 한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public List<EgovMap> selectNoteTrnsmitList(NoteTrnsmitVO noteTrnsmitVO) {
		return noteTrnsmitMapper.selectNoteTrnsmitList(noteTrnsmitVO);
	}

	/**
	 * 보낸쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public int selectNoteTrnsmitListCnt(NoteTrnsmitVO noteTrnsmitVO) {
		return noteTrnsmitMapper.selectNoteTrnsmitListCnt(noteTrnsmitVO);
	}

	/**
	 * 보낸쪽지함관리를(을) 상세조회 한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public EgovMap selectNoteTrnsmitDetail(NoteTrnsmitVO noteTrnsmitVO) {
		return noteTrnsmitMapper.selectNoteTrnsmitDetail(noteTrnsmitVO);
	}

	/**
	 * 보낸쪽지함관리를(을) 삭제한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public void deleteNoteTrnsmit(NoteTrnsmitVO noteTrnsmitVO) {

		// 보낸쪽지함 건수를 조회함
		int nCnt = (Integer) noteTrnsmitMapper.selectTrnsmitRelationCnt(noteTrnsmitVO);

		if (nCnt == 0) {
			// 받은쪽지/쪽지관리 삭제 처리
			noteTrnsmitMapper.deleteNoteTrnsmitRelation(noteTrnsmitVO);
			// 쪽지정보를 삭제한다.
			noteTrnsmitMapper.deleteNoteManage(noteTrnsmitVO);
		} else {
			noteTrnsmitMapper.deleteNoteTrnsmit(noteTrnsmitVO);
		}
	}

	/**
	 * 보낸쪽지함관리를(을) 삭제한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public void deleteNoteRecptn(NoteTrnsmitVO noteTrnsmitVO) {
		noteTrnsmitMapper.deleteNoteRecptn(noteTrnsmitVO);
	}

	/**
	 * 수신자목록을 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public List<EgovMap> selectNoteTrnsmitCnfirm(NoteTrnsmitVO noteTrnsmitVO) {
		return noteTrnsmitMapper.selectNoteTrnsmitCnfirm(noteTrnsmitVO);
	}
	
}
