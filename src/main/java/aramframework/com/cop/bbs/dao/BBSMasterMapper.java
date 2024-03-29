package aramframework.com.cop.bbs.dao;

import java.util.List;

import aramframework.com.cop.bbs.domain.BoardMasterVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시판 속성정보 관리를 위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface BBSMasterMapper  {

	/**
	 * 유효한 게시판 목록을 불러온다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectAllBBSMasteInf(BoardMasterVO boardMasterVO);
	// 커뮤니티, 동호회의 게시판이 나오지 않도록 COMTN_BBS_USE 테이블과 Join 필요

	/**
	 * 게시판 속성정보 목록을 조회한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectBBSMasterInfs(BoardMasterVO boardMasterVO);

	/**
	 * 게시판 속성정보 목록 숫자를 조회한다
	 * 
	 * @param boardMasterVO
	 */
	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO);

	/**
	 * 게시판 속성정보 한 건을 상세조회 한다.
	 * 
	 * @param bbsId
	 */
	public BoardMasterVO selectBBSMasterInf(String bbsId);

	/**
	 * 게시판 속성정보 한 건을 상세조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO);

	/**
	 * 신규 게시판 속성정보를 등록한다.
	 * 
	 * @param boardMasterVO
	 */
	public void insertBBSMasterInf(BoardMasterVO boardMasterVO);

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
	 * 커뮤니티, 동호회등 게시판 사용등록이 된 게시판 목록 전체를 불러온다.
	 * 
	 * @param boardMasterVO
	 */
	public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO boardMasterVO);

	/**
	 * 사용중인 게시판 속성정보 목록을 조회한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectBdMstrListByTrget(BoardMasterVO boardMasterVO);

	/**
	 * 사용중인 게시판 속성정보 목록 숫자를 조회한다
	 * 
	 * @param boardMasterVO
	 */
	public int selectBdMstrListCntByTrget(BoardMasterVO boardMasterVO);

	/**
	 * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO);

	/**
	 * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
	 * 
	 * @param boardMasterVO
	 */
	public int selectNotUsedBdMstrListCnt(BoardMasterVO boardMasterVO);

	/**
	 * 템플릿의 유효여부를 점검한다.
	 * 
	 * @param boardMasterVO
	 */
	public boolean validateTemplate(BoardMasterVO boardMasterVO);

}
