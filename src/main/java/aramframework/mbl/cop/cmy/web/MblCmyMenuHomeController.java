package aramframework.mbl.cop.cmy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BoardMasterVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.clb.service.ClubManageService;
import aramframework.com.cop.cmy.service.CommunityVO;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.cop.com.service.ConfirmService;

/**
 * 커뮤니티 정보를 관리하기 위한 컨트롤러 클래스
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

@Controller
public class MblCmyMenuHomeController {
	 
	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

    @Resource(name = "bbsMasterService")
    private BBSMasterService bbsMasterService;
    
    @Resource(name = "communityManageService")
    private CommunityManageService cmmntyService;

    @Resource(name = "clubManageService")
    private ClubManageService clubService;

    @Resource(name = "confirmService")
    private ConfirmService confmService;

	/**
	 * 커뮤니티 홈페이지를 조회한다.
	 * 
	 * @param appId
	 */
	@RequestMapping(value="/apps/mbl/{appId}", method=RequestMethod.GET)
	public String directCmmntyHomePage(
			@PathVariable String appId,			
			HttpServletRequest request, 
			ModelMap model) {

		String homeUrl = "/apps/" + appId;

		String cmmntyId = cmmntyService.selectCommntyHomeUrl(homeUrl);
		if( cmmntyId == null ) {
			throw new RuntimeException("cmmntyId not found");
		}

		return  cmmntyMainPageHandler(cmmntyId, model);
	}

	/**
     * 커뮤니티 메인페이지를 조회한다.
     * 
     * @param cmmntyId
	 */
	@RequestMapping(value="/content/mbl/apps/{cmmntyId}")
	public String directCmmntyMainPage(
			@PathVariable String cmmntyId,			
			ModelMap model) {

		cmmntyId = WebUtil.getOriginalId(cmmntyId, "CMMNTY_");

		return  cmmntyMainPageHandler(cmmntyId, model);
	}

	/**
     * 커뮤니티 메인페이지를 조회한다.
     * 
     * @param cmmntyId
	 */
    @RequestMapping("/cop/cmy/CmmntyMainPage.mdo")
	public String CmmntyMainPage(
			@RequestParam(value="cmmntyId", required=true) String cmmntyId, 
			ModelMap model) {

		return cmmntyMainPageHandler(cmmntyId, model);
	}

	/**
	 * 커뮤니티에 대한 목록을 조회한다.(공통사용-내부함수)
	 * 
	 * @param cmmntyId
	 */
	@SuppressWarnings("unchecked")
	private String cmmntyMainPageHandler(String cmmntyId, ModelMap model) {
    	
		if( cmmntyId == null || cmmntyId.equals("") ) {
			throw new RuntimeException("cmmntyId not found");
		}
		
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
    		cmmntyService.selectCommunityInf(communityVO);
    		
        	cacheMap.put(CacheKey.CMY_HOME, communityVO);
        }

        model.addAttribute("communityVO", communityVO);

        // --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
	    List<BoardMasterVO> bbsList = (List<BoardMasterVO>) cacheMap.get(CacheKey.CMY_BBSLIST);
        if( bbsList == null ) {
        	BoardMasterVO boardMasterVO = new BoardMasterVO();
        	boardMasterVO.setTrgetId(communityVO.getCmmntyId());
 
        	bbsList = bbsMasterService.selectAllBdMstrByTrget(boardMasterVO);
    		BoardMasterVO bMasterVO[] = new BoardMasterVO[6];
    		
    		// 방명록 제외 처리
    		for (int i = 0; i < bbsList.size(); i++) {
    			if (bbsList.get(i).getBbsTyCode().equals(BBSBoardService.BBS_TYPE_VISIT)) {
    				bbsList.remove(i);
    			}
    		}
    		
    		for (int i = 0; i < bbsList.size(); i++) {
    			bMasterVO[i] = bbsList.get(i);
    		}
    		
    		bbsList.clear();
    		bbsList.add(bMasterVO[0]); // 공지게시판
    		bbsList.add(bMasterVO[3]); // 자료실
    		bbsList.add(bMasterVO[1]); // 갤러리
    		bbsList.add(bMasterVO[2]); // 자유게시판
    		
        	cacheMap.put(CacheKey.CMY_BBSLIST, bbsList);
        }
        
		model.addAttribute("bbsList", bbsList);
/*
		String tmplatCours = cmmntyService.selectCmmntyTemplat(communityVO);

		if ("".equals(tmplatCours) || tmplatCours == null) {
			tmplatCours = "aramframework/com/cop/tpl/templet/CmmntyBaseTmpl";
		}
		// 모바일 접근의 경우 mbl/com/을 추가하여 모바일 템플릿으로 경로를 변경한다.
		/*
		 * 모바일용 커뮤니티를 사용하기 위해서는 웹용 커뮤니티 템플릿 외에 모바일용 커뮤니티 템플릿이  필요함
		 * 경로는 기존 커뮤니티 템플릿이 있는 위치와 동일한 mbl하위의 경로에 추가함
		 * 예::웹용 템플릿 url : aramframework/com/cop/tpl/CmmntyBaseTmpl
		 *   모바일용 템플릿 url : aramframework/mbl/cop/tpl/CmmntyBaseTmpl
		 *
		tmplatCours = tmplatCours.replace("/com/", "/mbl/");
*/		
		String tmplatCours = "aramframework/mbl/cop/tpl/templet/CmmntyBaseTmpl";
		return tmplatCours;
    }
	
}
