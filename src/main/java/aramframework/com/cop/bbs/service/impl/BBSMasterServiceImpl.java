package aramframework.com.cop.bbs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.ComponentChecker;
import aramframework.com.cop.bbs.service.BoardMasterVO;
import aramframework.com.cop.bbs.service.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
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

@Service("bbsMasterService")
public class BBSMasterServiceImpl extends EgovAbstractServiceImpl implements BBSMasterService {

	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

	@Autowired 
	private BBSMasterMapper bbsMasterMapper;		

	@Autowired 
	private BBSUseInfoMapper bbsUseInfoMapper;	

	@Autowired 
	private EgovIdGnrService bbsMasterIdGnrService; 

	// ---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	// ---------------------------------
	@Autowired 
	private BBSAddedOptionsMapper bbsAddedOptionsMapper;	

	// //-------------------------------

	/**
	 * 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectAllBBSMasteInf(BoardMasterVO boardMasterVO) {
		return bbsMasterMapper.selectAllBBSMasteInf(boardMasterVO);
	}

	/**
	 * 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		return bbsMasterMapper.selectBBSMasterInfs(boardMasterVO);
	}

	/**
	 * 게시판 속성 정보의 목록 총갯수을 조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return bbsMasterMapper.selectBBSMasterInfsCnt(boardMasterVO);
	}

	/**
	 * 게시판 속성정보 한 건을 상세조회한다.
	 * 
	 * @param bbsId
	 */
	public BoardMasterVO selectBBSMasterInf(String bbsId) {
		return bbsMasterMapper.selectBBSMasterInf(bbsId);
	}

	/**
	 * 게시판 속성정보 한 건을 상세조회한다.
	 * 
	 * @param boardMasterVO
	 */
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) {
		// ---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		// ---------------------------------

		BoardMasterVO resultVo = bbsMasterMapper.selectBBSMasterInf(boardMasterVO);

		// String flag = AramProperties.getProperty("Globals.addedOptions");
		// if (flag != null && flag.trim().equalsIgnoreCase("true")) {
		if (ComponentChecker.hasComponent("bbsCommentService") 
				|| ComponentChecker.hasComponent("bbsSatisfactionService")) {// 2011.09.15
			BoardMasterVO options = bbsAddedOptionsMapper.selectAddedOptionsInf(boardMasterVO.getBbsId());

			if (options != null) {
				if (options.getCommentAt().equals("Y")) {
					resultVo.setOption("comment");
				}

				if (options.getStsfdgAt().equals("Y")) {
					resultVo.setOption("stsfdg");
				}
			} else {
				resultVo.setOption("na"); // 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
			}
		}
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, boardMasterVO); 
		return resultVo;
	}

	/**
	 * 신규 게시판 속성정보를 생성한다.
	 * 
	 * @param boardMasterVO
	 */
	public String insertBBSMastetInf(BoardMasterVO boardMasterVO) {

		String bbsId = null;
		try {
			bbsId = bbsMasterIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		boardMasterVO.setBbsId(bbsId);

		bbsMasterMapper.insertBBSMasterInf(boardMasterVO);

		// ---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		// ---------------------------------
		if (boardMasterVO.getOption().equals("comment") 
				|| boardMasterVO.getOption().equals("stsfdg")) {
			bbsAddedOptionsMapper.insertAddedOptionsInf(boardMasterVO);
		}
		// //-------------------------------

		if ("Y".equals(boardMasterVO.getBbsUseFlag())) {
			BoardUseInfVO boardUseInfVO = new BoardUseInfVO();

			boardUseInfVO.setBbsId(bbsId);
			boardUseInfVO.setTrgetId(boardMasterVO.getTrgetId());
			boardUseInfVO.setRegistSeCode(boardMasterVO.getRegistSeCode());
			boardUseInfVO.setFrstRegisterId(boardMasterVO.getFrstRegisterId());
			boardUseInfVO.setUseAt("Y");
			boardUseInfVO.setPublicAt("Y");

			bbsUseInfoMapper.insertBBSUseInf(boardUseInfVO);  // 커뮤니티 또는 동호회 Target
		}
		return bbsId;
	}

	/**
	 * 게시판 속성정보를 수정한다.
	 * 
	 * @param boardMasterVO
	 */
	public void updateBBSMasterInf(BoardMasterVO boardMasterVO) {

		bbsMasterMapper.updateBBSMasterInf(boardMasterVO);

		// ---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		// ---------------------------------

		if (boardMasterVO.getOption().equals("comment") 
				|| boardMasterVO.getOption().equals("stsfdg")) {
			if (boardMasterVO.getOption().equals("na")) {
				return;
			}
			BoardMasterVO options = bbsAddedOptionsMapper.selectAddedOptionsInf(boardMasterVO.getBbsId());

			if (options == null) {
				boardMasterVO.setFrstRegisterId(boardMasterVO.getLastUpdusrId());
				bbsAddedOptionsMapper.insertAddedOptionsInf(boardMasterVO);
			} else {
				// 수정 기능 제외 (새롭게 선택사항을 지정한 insert만 처리함)
				// addedOptionsDAO.updateAddedOptionsInf(boardMaster);
				egovLogger.debug("BBS Master update ignored...");
			}
		}

		// remove master bbs cache
		cacheDictionary.remove(CacheKey.BBS_PREFIX + boardMasterVO.getBbsId());
		// //-------------------------------
	}

	/**
	 * 등록된 게시판 속성정보를 삭제한다.
	 * 
	 * @param boardMasterVO
	 */
	public void deleteBBSMasterInf(BoardMasterVO boardMasterVO) {

		bbsMasterMapper.deleteBBSMasterInf(boardMasterVO);

		BoardUseInfVO boardUseInfVO = new BoardUseInfVO();

		boardUseInfVO.setBbsId(boardMasterVO.getBbsId());
		boardUseInfVO.setLastUpdusrId(boardMasterVO.getLastUpdusrId());

		bbsUseInfoMapper.deleteBBSUseInf(boardUseInfVO);

		// remove master bbs cache
		cacheDictionary.remove(CacheKey.BBS_PREFIX + boardMasterVO.getBbsId());
	}

	/**
	 * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO boardMasterVO) {
		return bbsMasterMapper.selectAllBdMstrByTrget(boardMasterVO);
	}

	/**
	 * 사용중인 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public List<EgovMap> selectBdMstrListByTrget(BoardMasterVO boardMasterVO) {
		return bbsMasterMapper.selectBdMstrListByTrget(boardMasterVO);
	}

	/**
	 * 사용중인 게시판 속성 정보의 목록 총갯수을 조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	public int selectBdMstrListCntByTrget(BoardMasterVO boardMasterVO) {
		return bbsMasterMapper.selectBdMstrListCntByTrget(boardMasterVO);
	}

	/**
	 * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectNotUsedBdMstrList(SearchVO searchVO) {
		return bbsMasterMapper.selectNotUsedBdMstrList(searchVO);
	}
	
	/**
	 * 사용중이지 않은 게시판 속성 정보의 목록 총갯수을 조회 한다.
	 * 
	 * @param searchVO
	 */
	public int selectNotUsedBdMstrListCnt(SearchVO searchVO) {
		return bbsMasterMapper.selectNotUsedBdMstrListCnt(searchVO);
	}
	
	/**
	 * 템플릿의 유효여부를 점검한다.
	 * 
	 * @param searchVO
	 */
	public void validateTemplate(BoardMasterVO searchVO) {
		egovLogger.debug("validateTemplate method ignored...");
	}

}
