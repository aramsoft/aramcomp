package aramframework.com.cop.cmy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BBSUseInfoService;
import aramframework.com.cop.cmy.dao.CmyMenuManageMapper;
import aramframework.com.cop.cmy.dao.CommunityManageMapper;
import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.domain.CommunityVO;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 정보를 관리하기 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class CommunityManageService extends EgovAbstractServiceImpl {

	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

	@Autowired 
	private BBSMasterService bbsMasterService;

	@Autowired 
	private BBSUseInfoService bbsUseService;

	@Autowired 
	private CommunityManageMapper communityManageMapper;	

	@Autowired 
	private EgovIdGnrService cmmntyIdGnrService; 

	@Autowired 
	private CmyMenuManageMapper cmyMenuManageMapper;	

	/**
	 * 모든 커뮤니티 목록을 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectAllCmmnty(CommunityVO communityVO) {
		return communityManageMapper.selectAllCmmnty(communityVO);
	}

	/**
	 * 커뮤니티 정보 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCommunityList(CommunityVO communityVO) {
		return communityManageMapper.selectCommunityList(communityVO);
	}

	/**
	 * 커뮤니티 정보 목록 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCommunityListCnt(CommunityVO communityVO) {
		return communityManageMapper.selectCommunityListCnt(communityVO);
	}
	
	/**
	 * 포트릿을 위한 커뮤니티 정보 목록 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCmmntyListPortlet(CommunityVO communityVO) {
		return communityManageMapper.selectCmmntyListPortlet(communityVO);
	}

	/**
	 * 커뮤니티에 대한  정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public CommunityVO selectCommunityInf(CommunityVO communityVO) {
		CommunityVO resultVo = communityManageMapper.selectCommunityInf(communityVO);
		// searchVO 이전 
		resultVo.setSearchVO(communityVO.getSearchVO()); 
		return resultVo;
	}
	
	/**
	 * HomeUrl로 부터 커뮤니티 ID를 조회한다.
	 * 
	 * @param homeUrl
	 */
	public String selectCommntyHomeUrl(String homeUrl) {
		return communityManageMapper.selectCommntyHomeUrl(homeUrl);
	}

	/**
	 * 커뮤니티 관리자 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public CommunityUserVO selectCommunityManagerInf(CommunityVO communityVO) {

		List<EgovMap> managers = communityManageMapper.selectCommunityManagerInfs(communityVO);

		CommunityUserVO communityUserVO = new CommunityUserVO();
		if (managers.size() == 1) {
			communityUserVO.setEmplyrId(managers.get(0).get("emplyrId").toString());
			communityUserVO.setEmplyrNm(managers.get(0).get("emplyrNm").toString());
		} else if (managers.size() > 1) {
			communityUserVO.setEmplyrId(managers.get(0).get("emplyrId").toString());
			communityUserVO.setEmplyrNm(managers.get(0).get("emplyrNm").toString() + "외 " + (managers.size() - 1) + "명");
		} else {
			// no-op
			egovLogger.debug("No managers...");
		}

		return communityUserVO;
	}
	
	/**
	 * 커뮤니티 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityBBSUseInf(CommunityVO communityVO) {
		return communityManageMapper.selectCommunityBBSUseInf(communityVO);
	}

	/**
	 * 커뮤니티용 템플릿 경로명을 조회한다.
	 * 
	 * @param communityVO
	 */
	public String selectCmmntyTemplat(CommunityVO communityVO) {
		return communityManageMapper.selectCmmntyTemplat(communityVO);
	}

	/**
	 * 커뮤니티에 대한 정보를 등록한다.
	 * 
	 * @param communityVO
	 */
	public void insertCommunityInf(CommunityVO communityVO) {
		/*
		 * 커뮤니티 생성 시 기본 게시판으로 
		 * 1. 공지게시판, 2.자료실, 3.갤러리, 4.자유게시판, 5. 방명록을 자동 생성하고
		 * 사용이 가능하도록 사용등록 처리한다.
		 */

		try {
			communityVO.setCmmntyId(cmmntyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		communityManageMapper.insertCommunityInf(communityVO);

		CommunityUserVO communityUserVO = new CommunityUserVO();
		communityUserVO.setCmmntyId(communityVO.getCmmntyId());
		communityUserVO.setEmplyrId(communityVO.getEmplyrId());
		communityUserVO.setMngrAt("Y");
		communityUserVO.setUseAt("Y");
		communityUserVO.setFrstRegisterId(communityVO.getFrstRegisterId());

		communityManageMapper.insertCommunityUserInf(communityUserVO);

		CommunityMenuVO communityMenuVO = new CommunityMenuVO();
		communityMenuVO.setTrgetId(communityVO.getCmmntyId());
		communityMenuVO.setMenuNo(100000);
		communityMenuVO.setMenuNm("게시판");
		communityMenuVO.setUseAt("Y");
		communityMenuVO.setMgrAt("N");
		communityMenuVO.setDirectUrl("/cop/cmy/CmmntyMainContents.do");
		communityMenuVO.setTopMenuAt("Y");
		communityMenuVO.setMenuAlias("100000");
		cmyMenuManageMapper.insertMenuManage(communityMenuVO);
		
		List<BoardMasterVO> result = makeBdMstrListforCmmnty(communityVO);

		BoardMasterVO boardMasterVO;
		// BoardUseInf bdUseInf;

		Iterator<BoardMasterVO> iter = result.iterator();
		while (iter.hasNext()) {
			// 게시판 생성
			boardMasterVO = (BoardMasterVO) iter.next();

			@SuppressWarnings("unused")
			String bbsId = bbsMasterService.insertBBSMastetInf(boardMasterVO);

			/*
			 * //게시판 이용정보 생성 
			 * bdUseInf = new BoardUseInf();
			 * bdUseInf.setBbsId(_bbsId);
			 * bdUseInf.setTrgetId(cmmnty.getCmmntyId());
			 * bdUseInf.setRegistSeCode("REGC06");  	// 커뮤니티 게시판 등록
			 * bdUseInf.setUseAt("Y");
			 * //커뮤니티 생성 시 기본 게시판을 이용정보로 등록하는 것이므로 생성시 사용으로 등록
			 * bdUseInf.setFrstRegisterId(cmmnty.getFrstRegisterId());
			 * 
			 * bbsUseService.insertBBSUseInf(bdUseInf); //
			 */

			/*
			 * 이미 bbsAttrbService.insertBBSMastetInf() 부분에서 REGC07을 등록함...
			 * bdUseInf = new BoardUseInf(); 
			 * bdUseInf.setBbsId(_bbsId);
			 * bdUseInf.setTrgetId(cmmnty.getEmplyrId());
			 * bdUseInf.setRegistSeCode("REGC07"); 		// 게시판 사용자 등록
			 * bdUseInf.setUseAt("Y");
			 * //커뮤니티 생성 시 생성된 기본 게시판을 최초등록 운영자에게 부여한다
			 * bdUseInf.setFrstRegisterId(cmmnty.getFrstRegisterId());
			 * 
			 * bbsUseService.insertBBSUseInf(bdUseInf); //
			 */
		}
	}

	/**
	 * 커뮤니티에 대한 게시판 사용정보를 등록한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void insertCommunityBBSUseInf(BoardUseInfVO boardUseInfVO) {
		// cmmntyDAO.insertCommunityBBSUseInf(bdUseInf);
		// 커뮤니티에 게시판을 하나 추가하게 되면 - _- 해당 게시판이 등록된 커뮤니티의
		// 모든 소속사용자에게 사용 권한을 줘야하나 - _-? 일단 그렇게 진행
	}

	/**
	 * 커뮤니티용 게시판 정보를 생성한다.
	 * 
	 * @param communityVO
	 */
	private List<BoardMasterVO> makeBdMstrListforCmmnty(CommunityVO communityVO) {
		/*
		 * 공지게시판(답변불가/파일첨부가능/유기한) 0 
		 * 자료실(답변불가/파일첨부가능) 1
		 * 갤러리(답변불가/파일첨부가능) 2 
		 * 자유게시판(답변가능/파일첨부불가) 3 
		 * 방명록(답변불가/파일첨부불가) 4
		 */
		
		ArrayList<BoardMasterVO> result = new ArrayList<BoardMasterVO>();

		BoardMasterVO boardMasterVO;
		for (int i = 0; i < 5; i++) {
			boardMasterVO = new BoardMasterVO();

			boardMasterVO.setFrstRegisterId(communityVO.getFrstRegisterId());
			boardMasterVO.setUseAt("Y");
			boardMasterVO.setBbsUseFlag("Y");
			boardMasterVO.setTrgetId(communityVO.getCmmntyId());
			boardMasterVO.setRegistSeCode("REGC06");	// 커뮤니티 게시판 등록

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

			boardMasterVO.setBbsIntrcn(communityVO.getCmmntyNm() + " - " + boardMasterVO.getBbsNm());

			result.add(boardMasterVO);
		}

		return result;
	}

	/**
	 * 커뮤니티 정보를 수정한다.
	 * 
	 * @param communityVO
	 */
	public void updateCommunityInf(CommunityVO communityVO) {

		communityManageMapper.updateCommunityInf(communityVO);

		CommunityUserVO communityUserVO = new CommunityUserVO();
		communityUserVO.setCmmntyId(communityVO.getCmmntyId());
		communityUserVO.setEmplyrId(communityVO.getEmplyrId());

		CommunityUserVO cUserVO = communityManageMapper.selectCommunityUserInf(communityUserVO);

		communityUserVO.setUseAt("Y");
		communityUserVO.setMngrAt("Y");

		if( cUserVO != null) {
			communityUserVO.setLastUpdusrId(communityVO.getLastUpdusrId());
			communityManageMapper.updateCommunityUserInf(communityUserVO);
		} else {
			communityUserVO.setFrstRegisterId(communityVO.getLastUpdusrId());
			communityManageMapper.insertCommunityUserInf(communityUserVO);
		}	
	}

	/**
	 * 커뮤니티 게시판 사용정보를 수정한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void updateCommunityBBSUseInf(BoardUseInfVO boardUseInfVO) {
		communityManageMapper.updateCommunityBBSUseInf(boardUseInfVO);
	}

	/**
	 * 커뮤니티에 대한 정보를 삭제한다.
	 * 
	 * @param communityVO
	 */
	public void deleteCommunityInf(CommunityVO communityVO) {

		BoardUseInfVO boardUseInfVO = new BoardUseInfVO();
		boardUseInfVO.setLastUpdusrId(communityVO.getLastUpdusrId());
		boardUseInfVO.setCmmntyId(communityVO.getCmmntyId());

		bbsUseService.deleteAllBBSUseInfByCmmnty(boardUseInfVO);

		// 커뮤티니 사용자도 삭제처리해야 한다.
		CommunityUserVO communityUserVO = new CommunityUserVO();
		communityUserVO.setSecsnDe(DateUtil.getToday());
		communityUserVO.setCmmntyId(communityVO.getCmmntyId());
		communityUserVO.setLastUpdusrId(communityVO.getLastUpdusrId());

		communityManageMapper.deleteAllCommunityUserInf(communityUserVO);
	
		communityManageMapper.deleteCommunityInf(communityVO);
	}
	
	// 메뉴 정보
	/**
	 * 커뮤니티에 대한 일반 메뉴 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityTopMenuInfs(CommunityVO communityVO) {
		return communityManageMapper.selectCommunityTopMenuInfs(communityVO);
	}

	/**
	 * 커뮤니티에 대한 관리자 메뉴 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityMgrMenuInfs(CommunityVO communityVO) {
		return communityManageMapper.selectCommunityMgrMenuInfs(communityVO);
	}

	/**
	 * 커뮤니티에 대한 서브  메뉴 정보를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectCommunitySubMenuInfs(CommunityMenuVO communityMenuVO) {
		return communityManageMapper.selectCommunitySubMenuInfs(communityMenuVO);
	}

	// 사용자 정보

	/**
	 * 커뮤니티에 대한 특정 사용자 정보를 조회한다.
	 * 
	 * @param communityUserVO
	 */
	public CommunityUserVO selectCommunityUserInf(CommunityUserVO communityUserVO) {
		CommunityUserVO resultVo = communityManageMapper.selectCommunityUserInf(communityUserVO);
		// searchVO 이전 
		resultVo.setSearchVO(communityUserVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 커뮤니티 사용자 정보를 확인한다.
	 * 
	 * @param communityUserVO
	 */
	public String checkCommunityUserInf(CommunityUserVO communityUserVO) {
		// 회원가입 승인처리 적용시 기존 insertCommunityUserInf 대신 사용자 확인만 확인
		if (communityManageMapper.checkExistUser(communityUserVO) == 0) {
			return "";
		} else {
			return "EXIST";
		}
	}
	
	/**
	 * 관리자 여부를 확인한다.
	 * 
	 * @param communityUserVO
	 */
	public boolean isManager(CommunityUserVO communityUserVO) {
		CommunityUserVO resultVo = communityManageMapper.selectCommunityUserInf(communityUserVO);

		if( resultVo == null ) return false;
		
		if (resultVo.getMngrAt().equals("Y") 
				&& resultVo.getUseAt().equals("Y")) {
			return true;
		}
		return false;
	}

	/**
	 * 커뮤니티 사용자 정보를 등록한다.
	 * 
	 * @param communityUserVO
	 */
	public String insertCommunityUserInf(CommunityUserVO communityUserVO) {
		// cmmntyId
		CommunityVO communityVO = new CommunityVO();

		communityVO.setCmmntyId(communityUserVO.getCmmntyId());

		String retVal = "";
		int cnt = communityManageMapper.checkExistUser(communityUserVO);
		if (cnt == 0) {
			communityManageMapper.insertCommunityUserInf(communityUserVO);
/*			
			List<CommunityVO> tmpList = cmmntyDAO.selectCommunityBBSUseInf(communityVO);
			
			BoardUseInfVO boardUseInfVO = new BoardUseInfVO();;

			Iterator<CommunityVO> iter = tmpList.iterator();
			while (iter.hasNext()) {

				boardUseInfVO.setFrstRegisterId(communityUserVO.getFrstRegisterId());
				boardUseInfVO.setBbsId(((CommunityVO) iter.next()).getBbsId());
				boardUseInfVO.setTrgetId(communityUserVO.getEmplyrId());
				boardUseInfVO.setRegistSeCode("REGC07");	// 게시판 사용자 등록
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
	 * 커뮤니티 사용자 정보를 수정한다.
	 * 
	 * @param communityUserVO
	 */
	public void updateCommunityUserInf(CommunityUserVO communityUserVO) {
		communityManageMapper.updateCommunityUserInf(communityUserVO);
	}

	/**
	 * 커뮤니티 사용자정보를 삭제한다.
	 * 
	 * @param communityUserVO
	 */
	public void deleteCommunityUserInf(CommunityUserVO communityUserVO) {
		communityManageMapper.deleteCommunityUserInf(communityUserVO);
	}

	/**
	 * 커뮤니티 사용자정보를 삭제한다.
	 * 
	 * @param communityUserVO
	 */
	public void eraseCommunityUserInf(CommunityUserVO communityUserVO) {
		communityManageMapper.eraseCommunityUserInf(communityUserVO);
	}
	
	/**
	 * 캐쉬로부터 커뮤니티 정보 및 메뉴정보를 가져온다.
	 * 
	 * @param cmmntyId
	 * @param menuId
	 */
	@SuppressWarnings("unchecked")
	public CommunityVO getCommunityInfo(String cmmntyId, String menuId) {
		HashMap<String, Object> cacheMap = null;
		
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.CMY_PREFIX + cmmntyId);
        if( cacheMap == null ) {
        	cacheMap = new HashMap<String, Object>();
        	cacheDictionary.put(CacheKey.CMY_PREFIX + cmmntyId, cacheMap);
        }
        
        // --------------------------------
		// 커뮤니티 메인
		// --------------------------------
        CommunityVO communityVO = (CommunityVO) cacheMap.get(CacheKey.CMY_HOME);
        if( communityVO == null ) {
        	communityVO = new CommunityVO();
    		communityVO.setCmmntyId(cmmntyId);
    		
    		communityVO = selectCommunityInf(communityVO);
    		
        	cacheMap.put(CacheKey.CMY_HOME, communityVO);
        }

		// --------------------------------
		// 메뉴 목록 정보
		// --------------------------------
		List<EgovMap> topMenuList = communityVO.getTopMenuList();
        if( topMenuList == null ) {
    		topMenuList = selectCommunityTopMenuInfs(communityVO);
    		
    		communityVO.setTopMenuList(topMenuList);
        }

		List<EgovMap> mgrMenuList = communityVO.getMgrMenuList();
        if( mgrMenuList == null ) {
        	mgrMenuList = selectCommunityMgrMenuInfs(communityVO);

        	communityVO.setMgrMenuList(mgrMenuList);
        }

        if( topMenuList.size() == 0 ) return communityVO;
        
		CommunityMenuVO communityMenuVO = new CommunityMenuVO();
		communityMenuVO.setTrgetId(communityVO.getCmmntyId());
		if( "".equals(menuId) ) {
			communityMenuVO.setMenuNo(((BigDecimal)topMenuList.get(0).get("menuNo")).intValue());
		} else {
			communityMenuVO.setMenuNo(Integer.parseInt(menuId.substring(0,1)+"00000"));
		}
		
		String subMenuKey = CacheKey.CMY_SUBLIST+communityMenuVO.getMenuNo();
		List<EgovMap> subMenuList = (List<EgovMap>) cacheMap.get(subMenuKey);
        if( subMenuList == null ) {
    		subMenuList = selectCommunitySubMenuInfs(communityMenuVO);
    		cacheMap.put(subMenuKey, subMenuList);
        }
    	communityVO.setSubMenuList(subMenuList);

		return communityVO;
	}

	/**
	 * 캐쉬로부터 커뮤니티 정보 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@SuppressWarnings("unchecked")
	public CommunityVO getCommunityInfo(String cmmntyId) {
		HashMap<String, Object> cacheMap = null;
		
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.CMY_PREFIX + cmmntyId);
        if( cacheMap == null ) {
        	cacheMap = new HashMap<String, Object>();
        	cacheDictionary.put(CacheKey.CMY_PREFIX + cmmntyId, cacheMap);
        }
        
        // --------------------------------
		// 커뮤니티 메인
		// --------------------------------
        CommunityVO communityVO = (CommunityVO) cacheMap.get(CacheKey.CMY_HOME);
        if( communityVO == null ) {
        	communityVO = new CommunityVO();
    		communityVO.setCmmntyId(cmmntyId);
    		selectCommunityInf(communityVO);
    		
        	cacheMap.put(CacheKey.CMY_HOME, communityVO);
        }

		return communityVO;
	}

	/**
	 * 캐쉬로부터 커뮤니티 사용자정보를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	public CommunityUserVO getCommunityUserInfo(String cmmntyId) {

		CommunityUserVO communityUserVO = new CommunityUserVO();

		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (isAuthenticated) {
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			
			communityUserVO.setCmmntyId(cmmntyId);
			communityUserVO.setEmplyrId(loginVO.getUniqId());
			
			String result = checkCommunityUserInf(communityUserVO);
			if( result.equals("EXIST")) {
				communityUserVO.setAuthenticatedAt("Y");
			} else {
				communityUserVO.setAuthenticatedAt("N");
			}
		} else {
			communityUserVO.setAuthenticatedAt("N");
		}
		
		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			communityUserVO.setMngrAt("Y");
		} 
		
		return communityUserVO;
	}
	
}
