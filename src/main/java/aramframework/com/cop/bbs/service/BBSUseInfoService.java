package aramframework.com.cop.bbs.service;

import java.util.List;

import aramframework.com.cop.bbs.domain.BoardUseInfVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시판 이용정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface BBSUseInfoService {

	/**
	 * 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfs(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용정보 목록 총갯수을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int selectBBSUseInfsCnt(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfsByTrget(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록 총갯수을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int selectBBSUseInfsCntByTrget(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용정보에 대한 상세정보를 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public BoardUseInfVO selectBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용정보에 대한 존재를 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int existBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용정보를 등록한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void insertBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용정보를 수정한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void updateBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용 정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티에 사용되는 게시판 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteBBSUseInfByCmmnty(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteAllBBSUseInfByCmmnty(BoardUseInfVO boardUseInfVO);

}
