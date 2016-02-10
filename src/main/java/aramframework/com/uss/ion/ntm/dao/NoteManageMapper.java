package aramframework.com.uss.ion.ntm.dao;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.uss.ion.ntm.domain.NoteManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 쪽지 관리(보내기)를 처리하는 Dao Class 구현
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
public interface NoteManageMapper {

	/**
	 * 쪽지관리 정보를 조회한다.
	 * 
	 * @param noteManageVO
	 */
	public EgovMap selectNoteManage(NoteManageVO noteManageVO);

	/**
	 * 쪽지 관리(보내기)를(을) 등록한다.
	 * 
	 * @param noteManageVO
	 */
	public void insertNoteManage(NoteManageVO noteManageVO);

	/**
	 * 보낸쪽지를 등록한다.
	 * 
	 * @param noteManageVO
	 */
	public void insertNoteTrnsmit(NoteManageVO noteManageVO);

	/**
	 * 받은쪽지를 등록한다.
	 * 
	 * @param noteManageVO
	 */
	public void insertNoteRecptn(NoteManageVO noteManageVO);

	/**
	 * 수신자/참조자선택팝업 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectNoteEmpList(SearchVO searchVO);

	/**
	 * 수신자/참조자선택팝업 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectNoteEmpListCnt(SearchVO searchVO);
	
}
