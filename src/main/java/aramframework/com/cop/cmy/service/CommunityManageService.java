package aramframework.com.cop.cmy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.cmm.constant.CacheKey;
import aramframework.cmm.security.userdetails.UserDetailsHelper;
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
import aramframework.com.cop.cmy.domain.MenuVO;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.DateUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 정보를 관리하기 위한 서비스 구현 클래스
 * 
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
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityList(CommunityVO communityVO) {
		return communityManageMapper.selectCommunityList(communityVO);
	}

	/**
	 * 커뮤니티 정보 목록 총갯수을 조회한다.
	 * 
	 * @param communityVO
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
		return communityManageMapper.selectCommunityInf(communityVO);
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
		communityMenuVO.setMenuNm("board");
		communityMenuVO.setMenuKnm("게시판");
		communityMenuVO.setDirectUrl("/cop/cmy/CmmntyMainContents.do");
		communityMenuVO.setTopMenuAt("Y");
		communityMenuVO.setMgrAt("N");
		communityMenuVO.setUseAt("Y");
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
		}
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
		
		// cache clear
		removeCommunityCacheInfo(communityVO.getCmmntyId());
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
		
		// cache clear
		removeCommunityCacheInfo(communityVO.getCmmntyId());
	}
	
	// 사용자 정보

	/**
	 * 커뮤니티에 대한 특정 사용자 정보를 조회한다.
	 * 
	 * @param communityUserVO
	 */
	public CommunityUserVO selectCommunityUserInf(CommunityUserVO communityUserVO) {
		return communityManageMapper.selectCommunityUserInf(communityUserVO);
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
	
	// 메뉴 정보
	/**
	 * 커뮤니티에 대한 서브  메뉴 정보를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public List<MenuVO> selectCommunitySubMenuInfs(CommunityMenuVO communityMenuVO) {
		return communityManageMapper.selectCommunitySubMenuInfs(communityMenuVO);
	}

	/**
	 * 캐쉬로부터 커뮤니티 정보를 제거한다.
	 * 
	 * @param cmmntyId
	 * @param menuId
	 */
	@SuppressWarnings("unchecked")
	private void removeCommunityCacheInfo(String cmmntyId) {
		HashMap<String, Object> cacheMap = null;
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.CMY_PREFIX + cmmntyId);
        if( cacheMap != null ) {
        	cacheDictionary.remove(CacheKey.CMY_PREFIX + cmmntyId);
        }
	}	
	
	/**
	 * 캐쉬로부터 커뮤니티 정보 및 메뉴정보를 가져온다.
	 * 
	 * @param cmmntyId
	 * @param menuId
	 */
	@SuppressWarnings("unchecked")
	public CommunityVO getCommunityLayoutInfo(String cmmntyId, String menuPos) {
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
		List<MenuVO> topMenuList = communityVO.getTopMenuList();
        if( topMenuList == null ) {
    		topMenuList = communityManageMapper.selectCommunityTopMenuInfs(communityVO);
    		
    		communityVO.setTopMenuList(topMenuList);
        }

		List<MenuVO> mgrMenuList = communityVO.getMgrMenuList();
        if( mgrMenuList == null ) {
        	mgrMenuList = communityManageMapper.selectCommunityMgrMenuInfs(communityVO);

        	communityVO.setMgrMenuList(mgrMenuList);
        }

        if( topMenuList.size() == 0 ) return communityVO;
        
		CommunityMenuVO communityMenuVO = new CommunityMenuVO();
		communityMenuVO.setTrgetId(communityVO.getCmmntyId());
		if( "".equals(menuPos) ) {
			communityMenuVO.setMenuPos(topMenuList.get(0).getMenuPos().substring(0,2));
		} else {
			communityMenuVO.setMenuPos(menuPos.substring(0,2));
		}
		
		String subMenuKey = CacheKey.CMY_SUBMENU+communityMenuVO.getMenuPos()+"0000";
		List<MenuVO> subMenuList = (List<MenuVO>) cacheMap.get(subMenuKey);
        if( subMenuList == null ) {
    		subMenuList = this.selectCommunitySubMenuInfs(communityMenuVO);
    		cacheMap.put(subMenuKey, subMenuList);
        }
		// submenu는 cache에서 읽어와서 항상 교체함.
    	communityVO.setSubMenuList(subMenuList);

		MenuVO menuVO = this.getMenuInfo(communityVO, menuPos);
		if( menuVO != null ) {
			communityVO.setCurMenuNm(menuVO.getMenuNm());
		}	
		return communityVO;
	}

	/**
	 * 캐쉬로부터 커뮤니티 정보 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@SuppressWarnings("unchecked")
	public CommunityVO getCommunityOnlyInfo(String cmmntyId) {
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
	 * 캐쉬로부터 메뉴 정보 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	public MenuVO getMenuInfo(CommunityVO communityVO, String menuPos) {
		
		if( menuPos == null || menuPos.equals("") ) return null;
		
		// check topMenuList
		List<MenuVO> menuList = communityVO.getTopMenuList();
		MenuVO menuVO = findMenu(menuPos, menuList);
		if( menuVO != null ) {
			return menuVO;
		} 
		
		// check mgrMenuList
		menuList = communityVO.getMgrMenuList();
		menuVO = findMenu(menuPos, menuList);
		if( menuVO != null ) {
			return menuVO;
		} 

		// check subMenuList
		menuList = communityVO.getSubMenuList();
		menuVO = findMenu(menuPos, menuList);
		if(  menuVO != null ) {
			return menuVO;
		} 
		
		return null;
	}
	
	private MenuVO findMenu(String menuPos, List<MenuVO> menuList) {

		for (MenuVO menuVO : menuList) {
			if( menuPos.equals(menuVO.getMenuPos())) {
				return menuVO;
			}
		}
		return null;
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
			communityUserVO.setEmplyrId(loginVO.getUserId());
			
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
