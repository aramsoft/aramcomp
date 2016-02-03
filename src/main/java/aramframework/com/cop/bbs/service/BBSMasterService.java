package aramframework.com.cop.bbs.service;

import java.util.List;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
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

public interface BBSMasterService {

	/**
	 * 유효한 게시판 마스터 정보를 호출한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectAllBBSMasteInf(BoardMasterVO boardMasterVO);

	/**
	 * 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param BoardMasterVO
	 */
	public List<EgovMap> selectBBSMasterInfs(BoardMasterVO boardMasterVO);

	/**
	 * 게시판 속성 정보의 목록 총갯수을 조회 한다.
	 * 
	 * @param BoardMasterVO
	 */
	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO);

	/**
	 * 게시판 속성정보 한 건을 상세조회한다.
	 * 
	 * @param bbsId
	 */
	public BoardMasterVO selectBBSMasterInf(String bbsId);

	/**
	 * 게시판 속성정보 한 건을 상세조회한다.
	 * 
	 * @param BoardMasterVO
	 */
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO);

	/**
	 * 신규 게시판 속성정보를 생성한다.
	 * 
	 * @param BoardMasterVO
	 */
	public String insertBBSMastetInf(BoardMasterVO boardMasterVO);

	/**
	 * 게시판 속성정보를 수정한다.
	 * 
	 * @param boardMasterVO
	 */
	public void updateBBSMasterInf(BoardMasterVO boardMasterVO);

	/**
	 * 등록된 게시판 속성정보를 삭제한다.
	 * 
	 * @param boardMasterVO
	 */
	public void deleteBBSMasterInf(BoardMasterVO boardMasterVO);

	/**
	 * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
	 * 
	 * @param BoardMasterVO
	 */
	public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO boardMasterVO);

	/**
	 * 사용중인 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param BoardMasterVO
	 */
	public List<EgovMap> selectBdMstrListByTrget(BoardMasterVO boardMasterVO);

	/**
	 * 사용중인 게시판 속성 정보의 목록 총갯수을 조회 한다.
	 * 
	 * @param BoardMasterVO
	 */
	public int selectBdMstrListCntByTrget(BoardMasterVO boardMasterVO);

	/**
	 * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectNotUsedBdMstrList(SearchVO searchVO);

	/**
	 * 사용중이지 않은 게시판 속성 정보의 목록 총갯수을 조회 한다.
	 * 
	 * @param searchVO
	 */
	public int selectNotUsedBdMstrListCnt(SearchVO searchVO);

	/**
	 * 템플릿의 유효여부를 점검한다.
	 * 
	 * @param BoardMasterVO
	 */
	public void validateTemplate(BoardMasterVO boardMasterVO);
	
}
