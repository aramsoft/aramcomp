package aramframework.com.cop.bbs.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.bbs.dao.BBSBoardMapper;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시물 관리를 위한 서비스 구현 클래스
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
public class BBSBoardService extends EgovAbstractServiceImpl {

	static public String BBS_TYPE_BOARD 	= "BBST01";	//일반게시판
	static public String BBS_TYPE_ANONYMOUS = "BBST02";	//익명게시판
	static public String BBS_TYPE_NOTICE 	= "BBST03";	//공지게시판
	static public String BBS_TYPE_VISIT 	= "BBST04";	//방명록

	static public String BBS_ATTRB_LIMIT 	= "BBSA01";	//유효게시판
	static public String BBS_ATTRB_GALARY 	= "BBSA02";	//갤러리
	static public String BBS_ATTRB_GENERAL 	= "BBSA03";	//일반게시판
	
	@Autowired 
	private BBSBoardMapper bbsBoardMapper;	

	@Autowired 
	private FileMngUtil fileUtil;

	@Autowired 
	private EgovIdGnrService nttNoGnrService; 

	/**
	 * 조건에 맞는 게시물 목록을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public List<EgovMap> selectBoardArticleList(BoardVO boardVO) {
		List<EgovMap> list = bbsBoardMapper.selectBoardArticleList(boardVO);
		String attrbFlag = boardVO.getBoardMasterVO().getBbsAttrbCode();
		if (attrbFlag.equals(BBSBoardService.BBS_ATTRB_LIMIT)) {
			// 유효게시판 임
			String today = DateUtil.getToday();

			EgovMap vo;
			Iterator<EgovMap> iter = list.iterator();
			while (iter.hasNext()) {
				vo = (EgovMap) iter.next();

				if (!"".equals(vo.get("ntceBgnde")) || !"".equals(vo.get("ntceEndde"))) {
					if (DateUtil.getDaysDiff(today, vo.get("ntceBgnde").toString()) > 0 || DateUtil.getDaysDiff(today, vo.get("ntceEndde").toString()) < 0) {
						// 시작일이 오늘날짜보다 크거나, 종료일이 오늘 날짜보다 작은 경우
						vo.put("isExpired", "Y");
					}
				}
			}
		} 
		return list;
	}

	/**
	 * 게시물 총갯수을 조회한다.
	 * 
	 * @param boardVO
	 */
	public int selectBoardArticleListCnt(BoardVO boardVO) {
		return bbsBoardMapper.selectBoardArticleListCnt(boardVO);
	}

	/**
	 * 게시물 대하여 상세 내용을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public BoardVO selectBoardArticle(BoardVO boardVO) {
		BoardVO resultVo = bbsBoardMapper.selectBoardArticle(boardVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, boardVO); 
		return resultVo;
	}

	/**
	 * 게시판에 게시물 또는 답변 게시물을 등록 한다.
	 * 
	 * @param boardVO
	 */
	public void insertBoardArticle(BoardVO boardVO){
		// SORT_ORDR는 부모글의 소트 오더와 같게, NTT_NO는 순서대로 부여
		try {
			boardVO.setNttId(nttNoGnrService.getNextIntegerId());		// 2011.09.22
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		
		if ("Y".equals(boardVO.getAnswerAt())) {
			// 답글인 경우
			// 1.Parnts를 세팅, 
			// 2.Parnts의 sortOrdr을 현재글의 sortOrdr로 가져오도록,
			// 3.nttNo는 현재 게시판의 순서대로

			bbsBoardMapper.replyBoardArticle(boardVO);
			// ----------------------------------------------------------
			// 현재 글 이후 게시물에 대한 NTT_NO를 증가 (정렬을 추가하기 위해)
			// ----------------------------------------------------------
			int threadNo = bbsBoardMapper.getParentThreadNo(boardVO);

			boardVO.setThreadNo(threadNo);
			bbsBoardMapper.updateOtherThreadNo(boardVO);

			boardVO.setThreadNo(threadNo + 1);
			bbsBoardMapper.updateThreadNo(boardVO);
		} else {
			// 답글이 아닌경우 Parnts = 0, replyLc는 = 0, sortOrdr = nttNo(Query에서 처리)
			bbsBoardMapper.insertBoardArticle(boardVO);
		}
	}

	/**
	 * 게시물 한 건의 내용을 수정 한다.
	 * 
	 * @param boardVO
	 */
	public void updateBoardArticle(BoardVO boardVO) {
		bbsBoardMapper.updateBoardArticle(boardVO);
	}

	/**
	 * 게시물에 대한 조회 건수를 수정 한다.
	 * 
	 * @param boardVO
	 */
	public void updateRdcnt(BoardVO boardVO) {
		bbsBoardMapper.updateRdcnt(boardVO);
	}

	/**
	 * 게시물 한 건을 삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void deleteBoardArticle(BoardVO boardVO) {
		boardVO.setNttSj("이 글은 작성자에 의해서 삭제되었습니다.");
		bbsBoardMapper.deleteBoardArticle(boardVO);
	}

	/**
	 * 게시물 한 건을 완전삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void eraseBoardArticle(BoardVO boardVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(boardVO.getAtchFileId());
		bbsBoardMapper.eraseScrap(boardVO);
		bbsBoardMapper.eraseComment(boardVO);
		bbsBoardMapper.eraseSatisfaction(boardVO);
		bbsBoardMapper.eraseBoardArticle(boardVO);
	}

	/**
	 * 방명록에 대한 목록을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public List<BoardVO> selectGuestList(BoardVO boardVO) {
		return bbsBoardMapper.selectGuestList(boardVO);
	}

	/**
	 * 방명록에 대한 목록 총갯수을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public int selectGuestListCnt(BoardVO boardVO) {
		return bbsBoardMapper.selectGuestListCnt(boardVO);
	}

	/**
	 * 방명록에 대한 패스워드를 조회 한다.
	 * 
	 * @param boardVO
	 */
	public String getPasswordInf(BoardVO boardVO) {
		return bbsBoardMapper.getPasswordInf(boardVO);
	}
	
	/**
	 * 방명록 내용을 삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void deleteGuestList(BoardVO boardVO) {
		bbsBoardMapper.deleteGuestList(boardVO);
	}

}
