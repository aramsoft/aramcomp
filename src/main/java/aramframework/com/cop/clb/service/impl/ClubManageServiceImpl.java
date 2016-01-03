package aramframework.com.cop.clb.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.bbs.service.BoardMasterVO;
import aramframework.com.cop.bbs.service.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BBSUseInfoService;
import aramframework.com.cop.clb.service.ClubUserVO;
import aramframework.com.cop.clb.service.ClubVO;
import aramframework.com.cop.clb.service.ClubManageService;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 동호회 정보를 관리하기 위한 서비스 구현 클래스
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

@Service("clubManageService")
public class ClubManageServiceImpl extends EgovAbstractServiceImpl implements ClubManageService {

	@Resource(name = "bbsMasterService")
	private BBSMasterService bbsMasterService;

	@Resource(name = "bbsUseInfoService")
	private BBSUseInfoService bbsUseService;

	@Resource(name = "clubManageMapper")
	private ClubManageMapper clubManageMapper;	

	@Resource(name = "clbIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 동호회에 대한 목록을 조회한다.
	 * 
	 * @param clubUserVO
	 */
	public List<EgovMap> selectClubInfs(ClubVO clubVO) {
		return clubManageMapper.selectClubInfs(clubVO);
	}

	/**
	 * 동호회에 대한 목록 총갯수을 조회한다.
	 * 
	 * @param clubUserVO
	 */
	public int selectClubInfsCnt(ClubVO clubVO) {
		return clubManageMapper.selectClubInfsCnt(clubVO);
	}
	
	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubInfsByCmmntyId(ClubVO clubVO) {
		return clubManageMapper.selectClubInfsByCmmntyId(clubVO);
	}

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록 총갯수을 조회한다.
	 * 
	 * @param clubVO
	 */
	public int selectClubInfsCntByCmmntyId(ClubVO clubVO) {
		return clubManageMapper.selectClubInfsCntByCmmntyId(clubVO);
	}
	
	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubInfsByTrgetId(ClubVO clubVO) {
		return clubManageMapper.selectClubInfsByTrgetId(clubVO);
	}

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록 총갯수을 조회한다.
	 * 
	 * @param clubVO
	 */
	public int selectClubInfsCntByTrgetId(ClubVO clubVO) {
		return clubManageMapper.selectClubInfsCntByTrgetId(clubVO);
	}

	/**
	 * 커뮤니티에 사용되는 동호회 목록을 조회 한다(포틀릿 형식용).
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectClubListPortlet(SearchVO searchVO) {
		return clubManageMapper.selectClubListPortlet(searchVO);
	}

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회 한다(포틀릿 형식용).
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubListPortletByTrget(ClubVO clubVO) {
		return clubManageMapper.selectClubListPortletByTrget(clubVO);
	}

	/**
	 * 동호회에 대한 정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public ClubVO selectClubInf(ClubVO clubVO) {
		ClubVO resultVo = clubManageMapper.selectClubInf(clubVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, clubVO); 
		return resultVo;
	}
	
	/**
	 * 특정 동호회를 사용하는 커뮤니터 정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public ClubVO selectCmmntyInfByClbId(ClubVO clubVO) {
		ClubVO resultVo = clubManageMapper.selectCmmntyInfByClbId(clubVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, clubVO); 
		return resultVo;
	}

	/**
	 * 동호회에서 사용하는 게시판 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubUseBBSInfs(ClubVO clubVO) {
		return clubManageMapper.selectClubUseBBSInfs(clubVO);
	}

	/**
	 * 동호회 운영자 정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public ClubUserVO selectClubOperatorInf(ClubVO clubVO) {

		ClubUserVO clubUserVO = new ClubUserVO();

		List<EgovMap> operators = clubManageMapper.selectClubOperatorInfs(clubVO);
		if (operators.size() == 1) {
			clubUserVO.setEmplyrId(operators.get(0).get("emplyrId").toString());
			clubUserVO.setEmplyrNm(operators.get(0).get("emplyrNm").toString());
		} else if (operators.size() > 1) {
			clubUserVO.setEmplyrId(operators.get(0).get("emplyrId").toString());
			clubUserVO.setEmplyrNm(operators.get(0).get("emplyrNm").toString() + "외 " + (operators.size() - 1) + "명");
		} else {
			// no-op
			egovLogger.debug("No operators...");
		}

		return clubUserVO;
	}

	/**
	 * 동호회 정보를 등록한다.
	 * 
	 * @param clubVO
	 */
	public void insertClubInf(ClubVO clubVO) {
		/*
		 * 동호회 생성 시 기본 게시판으로 1. 공지게시판, 2.자료실, 3.갤러리, 4.자유게시판, 5. 방명록을 자동 생성하고
		 * 사용이 가능하도록 사용등록 처리한다. 공지게시판(답변불가/파일첨부가능/유기한) 0 자료실(답변불가/파일첨부가능) 1
		 * 갤러리(답변불가/파일첨부가능) 2 자유게시판(답변가능/파일첨부불가) 3 방명록(답변불가/파일첨부불가) 4
		 */

		try {
			clubVO.setClbId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}

		clubManageMapper.insertClubInf(clubVO);

		ClubUserVO clubUserVO = new ClubUserVO();

		clubUserVO.setClbId(clubVO.getClbId());
		clubUserVO.setCmmntyId(clubVO.getCmmntyId());
		clubUserVO.setEmplyrId(clubVO.getEmplyrId());
		clubUserVO.setOprtrAt("Y");
		clubUserVO.setUseAt("Y");
		clubUserVO.setFrstRegisterId(clubVO.getFrstRegisterId());

		clubManageMapper.insertClubUserInf(clubUserVO);

		List<BoardMasterVO> result = makeBdMstrListforCmmnty(clubVO);

		BoardMasterVO boardMasterVO;
		// BoardUseInf bdUseInf;

		Iterator<BoardMasterVO> iter = result.iterator();
		while (iter.hasNext()) {
			// 게시판 생성
			boardMasterVO = iter.next();

			@SuppressWarnings("unused")
			String bbsId = bbsMasterService.insertBBSMastetInf(boardMasterVO);

			/*
			 * //게시판 이용정보 생성 
			 * bdUseInf = new BoardUseInf();
			 * bdUseInf.setBbsId(bbsId); 
			 * bdUseInf.setTrgetId(club.getClbId());
			 * bdUseInf.setRegistSeCode("REGC05"); 
			 * bdUseInf.setUseAt("Y"); 
			 * //동호회 생성 시 기본 게시판을 이용정보로 등록하는 것이므로 생성시 사용으로 등록
			 * bdUseInf.setFrstRegisterId(club.getFrstRegisterId());
			 * 
			 * bbsUseService.insertBBSUseInf(bdUseInf); //
			 */

			/*
			 * bdUseInf = new BoardUseInf();
			 * 
			 * bdUseInf.setBbsId(bbsId);
			 * bdUseInf.setTrgetId(club.getEmplyrId());
			 * bdUseInf.setRegistSeCode("REGC07"); 
			 * bdUseInf.setUseAt("Y"); 
			 * //동호회 생성 시 생성 시 생성된 기본 게시판을 최초등록 운영자에게 부여한다
			 * bdUseInf.setFrstRegisterId(club.getFrstRegisterId());
			 * 
			 * bbsUseService.insertBBSUseInf(bdUseInf); //
			 */
		}
	}

	/**
	 * 클럽 사용을 위한 게시판 정보를 생성한다.
	 * 
	 * @param clubVO
	 */
	private List<BoardMasterVO> makeBdMstrListforCmmnty(ClubVO clubVO) {

		ArrayList<BoardMasterVO> result = new ArrayList<BoardMasterVO>();

		BoardMasterVO boardMasterVO;
		for (int i = 0; i < 5; i++) {
			boardMasterVO = new BoardMasterVO();

			boardMasterVO.setFrstRegisterId(clubVO.getFrstRegisterId());
			boardMasterVO.setUseAt("Y");
			boardMasterVO.setBbsUseFlag("Y");
			boardMasterVO.setTrgetId(clubVO.getClbId());
			boardMasterVO.setRegistSeCode("REGC05");

			if (i == 0) {
				boardMasterVO.setBbsTyCode(BBSBoardService.BBS_TYPE_VISIT);
				boardMasterVO.setReplyPosblAt("N");
				boardMasterVO.setFileAtchPosblAt("N");
				boardMasterVO.setBbsAttrbCode(BBSBoardService.BBS_ATTRB_GENERAL);
				boardMasterVO.setBbsNm("방명록");
			} else if (i == 1) {
				boardMasterVO.setBbsTyCode(BBSBoardService.BBS_TYPE_NOTICE);
				boardMasterVO.setReplyPosblAt("N");
				boardMasterVO.setFileAtchPosblAt("Y");
				boardMasterVO.setPosblAtchFileNumber(3);
				boardMasterVO.setBbsAttrbCode(BBSBoardService.BBS_ATTRB_LIMIT);
				boardMasterVO.setBbsNm("공지게시판");
			} else if (i == 3) {
				boardMasterVO.setBbsTyCode(BBSBoardService.BBS_TYPE_BOARD);
				boardMasterVO.setReplyPosblAt("Y");
				boardMasterVO.setFileAtchPosblAt("N");
				boardMasterVO.setBbsAttrbCode(BBSBoardService.BBS_ATTRB_GENERAL);
				boardMasterVO.setBbsNm("자유게시판");
			} else if (i == 4) {
				boardMasterVO.setBbsTyCode(BBSBoardService.BBS_TYPE_BOARD);
				boardMasterVO.setReplyPosblAt("N");
				boardMasterVO.setFileAtchPosblAt("Y");
				boardMasterVO.setPosblAtchFileNumber(5);
				boardMasterVO.setBbsAttrbCode(BBSBoardService.BBS_ATTRB_GENERAL);
				boardMasterVO.setBbsNm("자료실");
			} else {
				boardMasterVO.setBbsTyCode(BBSBoardService.BBS_TYPE_BOARD);
				boardMasterVO.setReplyPosblAt("N");
				boardMasterVO.setFileAtchPosblAt("Y");
				boardMasterVO.setPosblAtchFileNumber(5);
				boardMasterVO.setBbsAttrbCode(BBSBoardService.BBS_ATTRB_GALARY);
				boardMasterVO.setBbsNm("갤러리");
			}

			boardMasterVO.setBbsIntrcn(clubVO.getClbNm() + " - " + boardMasterVO.getBbsNm());

			result.add(boardMasterVO);
		}

		return result;
	}

	/**
	 * 동호회 내용 및 사용자 정보를 수정한다.
	 * 
	 * @param clubVO
	 */
	public void updateClubInf(ClubVO clubVO) {
		ClubUserVO clubUserVO = new ClubUserVO();

		clubUserVO.setUseAt("Y");
		clubUserVO.setOprtrAt("Y");
		clubUserVO.setClbId(clubVO.getClbId());
		clubUserVO.setEmplyrId(clubVO.getEmplyrId());

		clubManageMapper.updateClubInf(clubVO);
		clubManageMapper.updateClubUserInf(clubUserVO);
	}

	/**
	 * 동호회 정보를 삭제한다.
	 * 
	 * @param clubVO
	 */
	public void deleteClubInf(ClubVO clubVO) {

		// 여기에 추가로 해당 동호회에 속한 사용자를 사용중지하고 해당 동호회에 속한 게시판을 모두 사용중지 시킨다.
		BoardUseInfVO boardUseInfVO = new BoardUseInfVO();

		boardUseInfVO.setLastUpdusrId(clubVO.getLastUpdusrId());
		boardUseInfVO.setCmmntyId(clubVO.getClbId());

		bbsUseService.deleteAllBBSUseInfByClub(boardUseInfVO);

		ClubUserVO clubUserVO = new ClubUserVO();

		clubUserVO.setSecsnDe(DateUtil.getToday());
		clubUserVO.setClbId(clubVO.getClbId());
		clubUserVO.setLastUpdusrId(clubVO.getLastUpdusrId());

		clubManageMapper.deleteAllClubUserInf(clubUserVO);

		clubManageMapper.deleteClubInf(clubVO);
	}

	/**
	 * 동호회 템플릿 정보를 조회한다.
	 */
	public String selectClubTemplat(ClubVO clubVO) {
		return clubManageMapper.selectClubTemplat(clubVO);
	}

	// 사용자 정보
	
	/**
	 * 동호회에 대한 특정 사용자 정보를 조회한다.
	 * 
	 * @param clubUserVO
	 */
	public ClubUserVO selectClubUserInf(ClubUserVO clubUserVO) {
		ClubUserVO resultVo = clubManageMapper.selectClubUserInf(clubUserVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, clubUserVO); 
		return resultVo;
	}

	/**
	 * 동호회 사용자 정보를 확인한다.
	 * 
	 * @param clubUserVO
	 */
	public String checkClubUserInf(ClubUserVO clubUserVO) {
		if (clubManageMapper.checkExistUser(clubUserVO) == 0) {
			return "";
		} else {
			return "EXIST";
		}
	}

	/**
	 * 운영자 여부를 조회한다.
	 * 
	 * @param clubUserVO
	 */
	public boolean isOperator(ClubUserVO clubUserVO) {
		ClubUserVO resultVo = clubManageMapper.selectClubUserInf(clubUserVO);

		if( resultVo == null ) return false;
		
		if (resultVo.getOprtrAt().equals("Y") 
				&& resultVo.getUseAt().equals("Y")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 동호회 사용자 정보를 생성한다.
	 * 
	 * @param clubUserVO
	 */
	public String insertClubUserInf(ClubUserVO clubUserVO) {
		String retVal = "";

		ClubVO clubVO = new ClubVO();

		clubVO.setClbId(clubUserVO.getClbId());

		int cnt = clubManageMapper.checkExistUser(clubUserVO);

		if (cnt == 0) {
			clubManageMapper.insertClubUserInf(clubUserVO);
/*			
			List<ClubVO> tmpList = clubDAO.selectClubUseBBSInfs(clubVO);

			BoardUseInfVO boardUseInfVO;
			// 신규로 추가된 사용자에 대해서 현재 동호회의 모든 게시판에 대한 사용정보를 입력한다.

			Iterator<ClubVO> iter = tmpList.iterator();
			while (iter.hasNext()) {
				boardUseInfVO = new BoardUseInfVO();
				boardUseInfVO.setFrstRegisterId(clubUserVO.getFrstRegisterId());
				boardUseInfVO.setBbsId(((ClubVO) iter.next()).getBbsId());
				boardUseInfVO.setTrgetId(clubUserVO.getEmplyrId());
				boardUseInfVO.setRegistSeCode("REGC07");
				boardUseInfVO.setUseAt("Y");

				bbsUseService.insertBBSUseInf(boardUseInfVO);
			}
*/
		} else {
			retVal = "EXIST";
		}

		return retVal;
	}

	/**
	 * 동호회에 대한 사용자 내용을 수정한다.
	 * 
	 * @param clubUserVO
	 */
	public void updateClubUserInf(ClubUserVO clubUserVO) {
		clubManageMapper.updateClubUserInf(clubUserVO);
	}

	/**
	 * 동호회 사용자 정보를 삭제한다.
	 * 
	 * @param clubUserVO
	 */
	public void deleteClubUserInf(ClubUserVO clubUserVO) {
		clubManageMapper.deleteClubUserInf(clubUserVO);

	}

	/**
	 * 동호회 사용자 정보를 제거한다.
	 * 
	 * @param clubUserVO
	 */
	public void eraseClubUserInf(ClubUserVO clubUserVO) {
		clubManageMapper.eraseClubUserInf(clubUserVO);

	}
}
