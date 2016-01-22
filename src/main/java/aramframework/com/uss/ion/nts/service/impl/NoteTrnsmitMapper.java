package aramframework.com.uss.ion.nts.service.impl;

import java.util.List;

import aramframework.com.uss.ion.nts.service.NoteTrnsmitVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 보낸쪽지함관리를 처리하는 Dao Class 구현
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
public interface NoteTrnsmitMapper {

	/**
	 * 보낸쪽지함관리를(을) 목록을 한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public List<EgovMap> selectNoteTrnsmitList(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 보낸쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public int selectNoteTrnsmitListCnt(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 보낸쪽지함관리를(을) 상세조회 한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public EgovMap selectNoteTrnsmitDetail(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 보낸쪽지함관리를(을) 삭제한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public void deleteNoteTrnsmit(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 받은쪽지함를(을) 삭제한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public void deleteNoteRecptn(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 쪽지를(을) 삭제한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public void deleteNoteManage(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 쪽지관리/보낸족지함삭제
	 * 
	 * @param noteTrnsmitVO
	 */
	public void deleteNoteTrnsmitRelation(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 받은편지함 건수를 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public int selectTrnsmitRelationCnt(NoteTrnsmitVO noteTrnsmitVO);

	/**
	 * 수신자목록을 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 */
	public List<EgovMap> selectNoteTrnsmitCnfirm(NoteTrnsmitVO noteTrnsmitVO);
	
}
