package aramframework.com.cop.bbs.service.impl;

import java.util.List;

import aramframework.com.cop.bbs.service.BoardUseInfVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시판 이용정보를 관리하기 위한 데이터 접근 클래스
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
 
@Mapper("bbsUseInfoMapper")
public interface BBSUseInfoMapper {

	/**
	 * 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfs(BoardUseInfVO boardUseInfVO);

	/**
	 * 게시판 사용정보 대한 전체 건수를 조회한다.
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
	 * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 전체 건수를 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public int selectBBSUseInfsCntByTrget(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티에 사용되는 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfByCmmnty(BoardUseInfVO boardUseInfVO);

	/**
	 * 동호회에 사용되는 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	public List<EgovMap> selectBBSUseInfByClub(BoardUseInfVO boardUseInfVO);

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
	 * 게시판에 대한 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteAllBBSUseInfByCmmnty(BoardUseInfVO boardUseInfVO);

	/**
	 * 동호회에 사용되는 모든 게시판 사용정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void deleteAllBBSUseInfByClub(BoardUseInfVO boardUseInfVO);
	
}
