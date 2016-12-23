package aramframework.mbl.cop.scp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.scp.domain.ScrapVO;
import aramframework.com.cop.scp.service.BBSScrapService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 스크랩관리 서비스 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class MblBBSScrapController {
	
	@Autowired
    private BBSScrapService bbsScrapService;
    
	@Autowired
    private BBSBoardService boardService;
        
	@Autowired
	private BBSMasterService bbsMasterService;

    @Autowired
    private DefaultBeanValidator beanValidator;
    
    /**
     * 스크랩관리 목록 조회를 제공한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/listScrap.mdo")
	@Secured("ROLE_USER")
    public String listScrap(
			@ModelAttribute ScrapVO scrapVO, 
    		ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		scrapVO.getSearchVO().fillPageInfo(paginationInfo);
	
    	LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setUniqId(loginVO.getUniqId());
		
		model.addAttribute("resultList", bbsScrapService.selectScrapList(scrapVO));
		int totCnt = bbsScrapService.selectScrapListCnt(scrapVO);

		scrapVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);
	
		return "aramframework/mbl/cop/scp/ScrapList";
    }

    /**
     * 스크랩에 대한 상세정보를 조회한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/detailScrap.mdo")
	@Secured("ROLE_USER")
    public String detailScrap(
			ScrapVO scrapVO, 
    		ModelMap model)  {
    	
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("sessionUniqId", loginVO.getUniqId());
		
		scrapVO = bbsScrapService.selectScrap(scrapVO);
	
		//-------------------------------------
		//게시판 내용 취득
		//-------------------------------------
		model.addAttribute(getBoardInfo(scrapVO));
		model.addAttribute(	scrapVO);
		
		return "aramframework/mbl/cop/scp/ScrapDetail";
    }
    
	/**
	 * 스크랩된 원 게시판 내용을 조회한다.
	 * 
	 * @param scrapVO
	 */
	private BoardVO getBoardInfo(ScrapVO scrapVO) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(scrapVO.getBbsId());
		boardVO.setNttId(scrapVO.getNttId());

		boardVO.setBoardMasterVO(bbsMasterService.selectBBSMasterInf(boardVO.getBbsId()));
		return boardService.selectBoardArticle(boardVO);
	}

    /**
     * 스크랩 등록을 위한 등록 페이지로 이동한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/registScrap.mdo")
	@Secured("ROLE_USER")
    public String registScrap(
			@ModelAttribute ScrapVO scrapVO, 
    		ModelMap model) {
	
		//-------------------------------------
		//게시판 내용 취득
		//-------------------------------------
		model.addAttribute(getBoardInfo(scrapVO));
		
		return "aramframework/mbl/cop/scp/ScrapRegist";
    }

    /**
     * 스크랩을 등록한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/insertScrap.mdo")
	@Secured("ROLE_USER")
    public String insertScrap(
			@ModelAttribute ScrapVO scrapVO, 
    		BindingResult bindingResult, 
    		ModelMap model) {

		beanValidator.validate(scrapVO, bindingResult);
		if (bindingResult.hasErrors()) {
			//-------------------------------------
			//게시판 내용 취득
			//-------------------------------------
			model.addAttribute(getBoardInfo(scrapVO));
			
		    return "aramframework/mbl/cop/scp/ScrapRegist";
		}
		
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setFrstRegisterId(loginVO.getUniqId());

		bbsScrapService.insertScrap(scrapVO);
	
		return "redirect:/cop/scp/listScrap.mdo";
    }
    
    /**
     * 스크랩 수정 페이지로 이동한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/editScrap.mdo")
	@Secured("ROLE_USER")
    public String editScrap(
			ScrapVO scrapVO, 
    		ModelMap model) {
    	
    	scrapVO = bbsScrapService.selectScrap(scrapVO);
	
		//-------------------------------------
		//게시판 내용 취득
		//-------------------------------------
		model.addAttribute(getBoardInfo(scrapVO));
		model.addAttribute(scrapVO);
		
		return "aramframework/mbl/cop/scp/ScrapEdit";
    }
    
    /**
     * 스크랩을 수정한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/updateScrap.mdo")
	@Secured("ROLE_USER")
    public String updateScrap(
			@ModelAttribute ScrapVO scrapVO, 
    		BindingResult bindingResult, 
    		ModelMap model) {

		beanValidator.validate(scrapVO, bindingResult);
		if (bindingResult.hasErrors()) {
			//-------------------------------------
			//게시판 내용 취득
			//-------------------------------------
			model.addAttribute(getBoardInfo(scrapVO));

		    return "aramframework/mbl/cop/scp/ScrapEdit";
		}
	
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setLastUpdusrId(loginVO.getUniqId());

		bbsScrapService.updateScrap(scrapVO);
	
		return "redirect:/cop/scp/listScrap.mdo";
    }
    
    /**
     * 스크랩을 삭제한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/deleteScrap.mdo")
	@Secured("ROLE_USER")
    public String deleteScrap(
			@ModelAttribute ScrapVO scrapVO, 
    		ModelMap model) {

    	bbsScrapService.deleteScrap(scrapVO);
		
		return "redirect:/cop/scp/listScrap.mdo";
    }
    
    /**
     * 마이페이지용 스크랩관리 목록 조회를 제공한다.
     * 
     * @param scrapVO
     */
    @RequestMapping("/cop/scp/listScrapMainPage.mdo")
	@Secured("ROLE_USER")
    public String listScrapMainPage(
			@ModelAttribute ScrapVO scrapVO, 
    		ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setUniqId(loginVO.getUniqId());
		scrapVO.getSearchVO().setSizeAndOffset(5, 0);

		model.addAttribute("resultList",bbsScrapService.selectScrapList(scrapVO));
		model.addAttribute("resultCnt", bbsScrapService.selectScrapListCnt(scrapVO));

		return "aramframework/mbl/cop/scp/ScrapMainList";
    }
    
}
