package aramframework.com.cop.cmy.web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import aramframework.com.cop.cmy.excel.ExcelCmyMenuView;
import aramframework.com.cop.cmy.service.CmyMenuManageService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.sym.prm.service.ProgrmManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 커뮤니티메뉴정보를 관리하기 위한 컨트롤러 클래스
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
public class CmyMenuManageController {

	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

	@Autowired 
	private CmyMenuManageService cmyMenuManageService;

	@Autowired 
	private ProgrmManageService progrmManageService;

	@Autowired 
	private UserInfService userInfService; // 커뮤니티 사용자 확인

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	private void checkAuthorityManager() {
		if( userInfService.checkCommunityManager().equals("N") ){
			throw new RuntimeException("해당 커뮤니티 관리자만 사용하실 수 있습니다.");
		}
	}

	/**
	 * 메뉴목록 리스트조회한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/listMenu.do")
	@Secured("ROLE_USER")
	public String listMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		// check trgetId empty
		if( communityMenuVO.getTrgetId() == null 
			|| communityMenuVO.getTrgetId().equals("") ) {
			communityMenuVO.setTrgetId(WebUtil.getCurTrgetId());
		}
		
		// 내역 조회
		PaginationInfo paginationInfo = new PaginationInfo();
		communityMenuVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmyMenuManageService.selectMenuManageList(communityMenuVO));

		int totCnt = cmyMenuManageService.selectMenuManageListCnt(communityMenuVO);
		communityMenuVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/cmy/CmyMenuList");
	}

	/**
	 * 메뉴정보 등록화면으로 이동  한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/registMenu.do")
	@Secured("ROLE_USER")
	public String registMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO) {

		checkAuthorityManager(); // server-side 권한 확인

		return WebUtil.adjustViewName("/cop/cmy/CmyMenuRegist");
	}

	/**
	 * 메뉴정보를 등록 한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/insertMenu.do")
	@Secured("ROLE_USER")
	public String insertMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(communityMenuVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/cmy/CmyMenuRegist");
		}

		if (cmyMenuManageService.selectMenuNoByPk(communityMenuVO) != 0) {
			model.addAttribute("message", MessageHelper.getMessage("common.isExist.msg"));
			return WebUtil.adjustViewName("/cop/cmy/CmyMenuRegist");
		}
		
		if (communityMenuVO.getProgrmFileNm() != null
			&& !communityMenuVO.getProgrmFileNm().equals("")	
			&& progrmManageService.selectProgrmNMTotCnt(communityMenuVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
			return "aramframework/com/cop/cmy/CmyMenuRegist";
		} 
		
		cmyMenuManageService.insertMenuManage(communityMenuVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
	}

	/**
	 * 메뉴정보 수정화면으로 이동  한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/editMenu.do")
	@Secured("ROLE_USER")
	public String editMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO) {
	
		checkAuthorityManager(); // server-side 권한 확인

		// check trgetId empty
		if( communityMenuVO.getTrgetId() == null 
				|| communityMenuVO.getTrgetId().equals("") ) {
			communityMenuVO.setTrgetId(WebUtil.getCurTrgetId());
		}
		
		cmyMenuManageService.selectMenuManage(communityMenuVO);

		return WebUtil.adjustViewName("/cop/cmy/CmyMenuEdit");
	}

	/**
	 * 메뉴정보를 수정 한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/updateMenu.do")
	@Secured("ROLE_USER")
	public String updateMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(communityMenuVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/cmy/CmyMenuEdit");
		}
		
		if( communityMenuVO.getNewMenuNo() != 0 ) {
			CommunityMenuVO newCommunityMenuVO = new CommunityMenuVO();
			newCommunityMenuVO.setMenuNo(communityMenuVO.getNewMenuNo());
			newCommunityMenuVO.setTrgetId(communityMenuVO.getTrgetId());
			if (cmyMenuManageService.selectMenuNoByPk(newCommunityMenuVO) != 0) {
				model.addAttribute("message", MessageHelper.getMessage("common.isExist.msg"));
				return WebUtil.adjustViewName("/cop/cmy/CmyMenuEdit");
			}
		}
		
		if (communityMenuVO.getProgrmFileNm() != null
				&& !communityMenuVO.getProgrmFileNm().equals("")	
				&& progrmManageService.selectProgrmNMTotCnt(communityMenuVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.update"));
			return WebUtil.adjustViewName("/cop/cmy/CmyMenuEdit");
		} 
		
		cmyMenuManageService.updateMenuManage(communityMenuVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
	}

	/**
	 * 메뉴정보를 삭제 한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/deleteMenu.do")
	@Secured("ROLE_USER")
	public String deleteMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {
		
		cmyMenuManageService.deleteMenuManage(communityMenuVO);

		String _MenuNm = "%";
		communityMenuVO.setMenuNm(_MenuNm);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
	}

	/**
	 * 메뉴목록 멀티 삭제한다.
	 * 
	 * @param checkedMenuNoForDel
	 * @param communityMenuVO
	 */
	@RequestMapping("/cop/cmy/deleteListMenu.do")
	@Secured("ROLE_USER")
	public String deleteListMenu(
			@RequestParam String checkedMenuNoForDel,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {

		String[] delMenuNo = checkedMenuNoForDel.split(",");
		if (delMenuNo == null || (delMenuNo.length == 0)) {
			model.addAttribute("message",  MessageHelper.getMessage("fail.common.delete"));
			return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
		} 

		cmyMenuManageService.deleteMenuManageList(communityMenuVO.getTrgetId(), checkedMenuNoForDel);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
	}
	
	/**
	 * 커뮤니티 메뉴 목록을 엑셀 다운로드한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/downMenuExcel.do")
	@Secured("ROLE_USER")
	public ModelAndView downMenuExcel(
			@ModelAttribute CommunityMenuVO communityMenuVO) {

		ModelAndView modelAndView = new ModelAndView(new ExcelCmyMenuView());

		modelAndView.addObject("resultList", cmyMenuManageService.selectMenuListExcel(communityMenuVO));

		return modelAndView;
	}
	
	/**
	 * 커뮤니티 메뉴 엑셀 등록 전처리 화면으로 이동한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/registMenuExcel.do")
	@Secured("ROLE_USER")
	public String registZipExcel(
			@ModelAttribute CommunityMenuVO communityMenuVO) {
		
		return WebUtil.adjustViewName("/cop/cmy/CmyMenuExcelRegist");
	}

	/**
	 * 엑셀파일을 업로드하여 커뮤니티 메뉴을 등록한다.
	 * 
	 * @param multiRequest
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/insertMenuExcel.do")
	@Secured("ROLE_USER")
	public String insertMenuExcel(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) 
	throws Exception {

		InputStream fis = null; // 2011.11.1 보안점검 후속조치

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			if (!"".equals(file.getOriginalFilename())) {
				// 2011.10.07 업로드 파일에 대한 확장자를 체크
				if (file.getOriginalFilename().endsWith(".xls") 
						|| file.getOriginalFilename().endsWith(".xlsx") 
						|| file.getOriginalFilename().endsWith(".XLS")
						|| file.getOriginalFilename().endsWith(".XLSX")) {
					try { 
						fis = file.getInputStream();
						cmyMenuManageService.insertExcelMenu(fis, communityMenuVO.getTrgetId());
					} catch (Exception e) {
						throw e;
					} finally {
						if (fis != null)	// 2011.11.1 보안점검 후속조치
							fis.close();
					}

				} else {
					model.addAttribute("message", "xls, xlsx 파일 타입만 등록이 가능합니다.");
					return WebUtil.adjustViewName("/cop/cmy/CmyMenuExcelRegist");
				}
			}
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
	}
	
    /**
     * 캐쉬된 커뮤니티 메뉴를 청소한다.
     *      
     * @param communityMenuVO
     */    
	@SuppressWarnings("unchecked")
	@RequestMapping("/cop/cmy/clearCacheMenu.do")
    public String clearCacheMenu(
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {

		HashMap<String, Object> cacheMap = null;
		
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.CMY_PREFIX + communityMenuVO.getTrgetId());
        if( cacheMap == null ) {
    		model.addAttribute("message", MessageHelper.getMessage("fail.common.delete"));
			return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
        }
        
        // clear cache
	    for(String key: cacheMap.keySet()){
	    	cacheMap.put(key, null);
	    }
    	cacheMap = null;
	    
		return WebUtil.redirectJsp(model, "/cop/cmy/listMenu.do");
    }
    
}