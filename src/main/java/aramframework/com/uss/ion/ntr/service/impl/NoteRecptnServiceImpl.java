package aramframework.com.uss.ion.ntr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.ntr.service.NoteRecptnService;
import aramframework.com.uss.ion.ntr.service.NoteRecptnVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 받은쪽지함관리를 처리하는 ServiceImpl Class 구현
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

@Service
public class NoteRecptnServiceImpl extends EgovAbstractServiceImpl implements NoteRecptnService {

	@Autowired
	private NoteRecptnMapper noteRecptnMapper;	

	/**
	 * 받은쪽지함관리를(을) 목록을 조회 한다.
	 * 
	 * @param noteRecptnVO
	 */
	public List<EgovMap> selectNoteRecptnList(NoteRecptnVO noteRecptnVO) {
		return noteRecptnMapper.selectNoteRecptnList(noteRecptnVO);
	}

	/**
	 * 받은쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param noteRecptnVO
	 */
	public int selectNoteRecptnListCnt(NoteRecptnVO noteRecptnVO) {
		return noteRecptnMapper.selectNoteRecptnListCnt(noteRecptnVO);
	}

	/**
	 * 받은쪽지함관리를(을) 상세조회 한다.
	 * 
	 * @param noteRecptnVO
	 */
	public EgovMap selectNoteRecptnDetail(NoteRecptnVO noteRecptnVO) {
		// 받은쪽지함관리를 개봉으로 상태를 바꾼다.
		noteRecptnMapper.updateNoteRecptnRelationOpenYn(noteRecptnVO);
		return noteRecptnMapper.selectNoteRecptnDetail(noteRecptnVO);
	}

	/**
	 * 받은쪽지함관리를(을) 삭제한다.
	 * 
	 * @param noteRecptnVO
	 */
	public void deleteNoteRecptn(NoteRecptnVO noteRecptnVO) {

		// 보낸쪽지함 건수를 조회함
		int nNoteTrnsmitCnt = (Integer) noteRecptnMapper.selectNoteTrnsmitRelationCnt(noteRecptnVO);

		// 받은쪽지함 건수를 조회함
		int nNoteRecptnCnt = (Integer) noteRecptnMapper.selectNoteRecptnRelationCnt(noteRecptnVO);

		if (nNoteTrnsmitCnt == 1 && nNoteRecptnCnt == 1) {
			// 받은쪽지/쪽지관리 삭제 처리
			// dao.deleteNoteRecptnRelation(noteRecptn);
			// 받은쪽지함삭제
			noteRecptnMapper.deleteNoteRecptn(noteRecptnVO);
			// 보낸쪽지함삭제
			noteRecptnMapper.deleteNoteTrnsmit(noteRecptnVO);
			// 쪽지관리삭제
			noteRecptnMapper.deleteNoteManage(noteRecptnVO);
		} else {
			// 받은쪽지 삭제
			noteRecptnMapper.deleteNoteRecptn(noteRecptnVO);
		}
	}

}
