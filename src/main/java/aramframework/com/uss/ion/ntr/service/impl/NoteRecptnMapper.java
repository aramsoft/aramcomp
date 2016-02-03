package aramframework.com.uss.ion.ntr.service.impl;

import java.util.List;

import aramframework.com.uss.ion.ntr.domain.NoteRecptnVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 받은쪽지함관리를 처리하는 Dao Class 구현
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

@Mapper
public interface NoteRecptnMapper {

	/**
	 * 받은쪽지함관리를(을) 목록을 한다.
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
	 * 받은쪽지함관리를 개봉으로 상태를 바꾼다.
	 * 
	 * @param noteRecptnVO
	 */
	public void updateNoteRecptnRelationOpenYn(NoteRecptnVO noteRecptnVO);

	/**
	 * 받은쪽지함관리를(을) 상세조회 한다.
	 * 
	 * @param noteRecptnVO
	 */
	public EgovMap selectNoteRecptnDetail(NoteRecptnVO noteRecptnVO);

	/**
	 * 쪽지관리/쪽지관리,보낸보낸쪽지함, 받은쪽지함 삭제
	 * 
	 * @param noteRecptnVO
	 */
	public void deleteNoteRecptnRelation(NoteRecptnVO noteRecptnVO);

	/**
	 * 받은쪽지함관리를(을) 삭제한다.
	 * 
	 * @param noteRecptnVO
	 */
	public void deleteNoteRecptn(NoteRecptnVO noteRecptnVO);

	/**
	 * 쪽지관리/보낸족지함삭제
	 * 
	 * @param noteRecptnVO
	 */
	public void deleteNoteTrnsmit(NoteRecptnVO noteRecptnVO);

	/**
	 * 쪽지관리/쪽지관리삭제
	 * 
	 * @param noteRecptnVO
	 */
	public void deleteNoteManage(NoteRecptnVO noteRecptnVO);

	/**
	 * 보낸쪽지함관리 건수를 조회한다.
	 * 
	 * @param noteRecptnVO
	 */
	public int selectNoteTrnsmitRelationCnt(NoteRecptnVO noteRecptnVO);

	/**
	 * 받은쪽지함관리 건수를 조회한다.
	 * 
	 * @param noteRecptnVO
	 */
	public int selectNoteRecptnRelationCnt(NoteRecptnVO noteRecptnVO);
	
}
