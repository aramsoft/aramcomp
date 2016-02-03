package aramframework.com.uss.ion.ntr.service;

import java.util.List;

import aramframework.com.uss.ion.ntr.domain.NoteRecptnVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 받은쪽지함관리를 처리하는 Service Class 구현
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

public interface NoteRecptnService {

	/**
	 * 받은쪽지함관리 목록을 조회한다.
	 * 
	 * @param noteRecptnVO
	 */
	public List<EgovMap> selectNoteRecptnList(NoteRecptnVO noteRecptnVO);

	/**
	 * 받은쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param noteRecptnVO
	 */
	public int selectNoteRecptnListCnt(NoteRecptnVO noteRecptnVO);

	/**
	 * 받은쪽지함관리를(을) 상세조회 한다.
	 * 
	 * @param noteRecptnVO
	 */
	public EgovMap selectNoteRecptnDetail(NoteRecptnVO noteRecptnVO);

	/**
	 * 받은쪽지함관리를(을) 삭제한다.
	 * 
	 * @param noteRecptnVO
	 */
	void deleteNoteRecptn(NoteRecptnVO noteRecptnVO);

}
