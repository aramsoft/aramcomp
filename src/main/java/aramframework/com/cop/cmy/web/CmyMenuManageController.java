package aramframework.com.cop.cmy.web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.com.domain.SearchVO;
import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import aramframework.com.cop.cmy.excel.ExcelCmyMenuView;
import aramframework.com.cop.cmy.service.CmyMenuManageService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.sym.prm.service.ProgrmManageService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 커뮤니티메뉴정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
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
		communityMenuVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmyMenuManageService.selectMenuManageList(communityMenuVO));
		int totCnt = cmyMenuManageService.selectMenuManageListCnt(communityMenuVO);

		communityMenuVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/cmy/CmyMenuList";
	}

	/**
	 * 메뉴정보 등록화면으로 이동  한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/registMenu.do")
	@Secured("ROLE_USER")
	public String registMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		return "com/cop/cmy/CmyMenuRegist";
	}

	/**
	 * 메뉴정보를 등록 한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/insertMenu.do")
	@Secured("ROLE_USER")
	public String insertMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(communityMenuVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/cmy/CmyMenuRegist";
		}

		if (cmyMenuManageService.selectMenuNmByPk(communityMenuVO) != 0) {
			model.addAttribute("message", MessageHelper.getMessage("common.isExist.msg"));
			return "com/cop/cmy/CmyMenuRegist";
		}
		
		if (communityMenuVO.getProgrmFileNm() != null
			&& !communityMenuVO.getProgrmFileNm().equals("")	
			&& progrmManageService.selectProgrmNMTotCnt(communityMenuVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
			return "com/cop/cmy/CmyMenuRegist";
		} 
		
		cmyMenuManageService.insertMenuManage(communityMenuVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴정보 수정화면으로 이동  한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/editMenu.do")
	@Secured("ROLE_USER")
	public String editMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO,
			ModelMap model) {
	
		checkAuthorityManager(); // server-side 권한 확인

		// check trgetId empty
		if( communityMenuVO.getTrgetId() == null 
				|| communityMenuVO.getTrgetId().equals("") ) {
			communityMenuVO.setTrgetId(WebUtil.getCurTrgetId());
		}
		
		model.addAttribute(cmyMenuManageService.selectMenuManage(communityMenuVO));

		return "com/cop/cmy/CmyMenuEdit";
	}

	/**
	 * 메뉴정보를 수정 한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/updateMenu.do")
	@Secured("ROLE_USER")
	public String updateMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(communityMenuVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/cmy/CmyMenuEdit";
		}
		
		if( !"".equals(communityMenuVO.getNewMenuNm()) ) {
			CommunityMenuVO newCommunityMenuVO = new CommunityMenuVO();
			newCommunityMenuVO.setMenuNm(communityMenuVO.getNewMenuNm());
			newCommunityMenuVO.setTrgetId(communityMenuVO.getTrgetId());
			if (cmyMenuManageService.selectMenuNmByPk(newCommunityMenuVO) != 0) {
				model.addAttribute("message", MessageHelper.getMessage("common.isExist.msg"));
				return "com/cop/cmy/CmyMenuEdit";
			}
		}
		
		if (communityMenuVO.getProgrmFileNm() != null
				&& !communityMenuVO.getProgrmFileNm().equals("")	
				&& progrmManageService.selectProgrmNMTotCnt(communityMenuVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.update"));
			return "com/cop/cmy/CmyMenuEdit";
		} 
		
		cmyMenuManageService.updateMenuManage(communityMenuVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴정보를 삭제 한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/deleteMenu.do")
	@Secured("ROLE_USER")
	public String deleteMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {
		
		cmyMenuManageService.deleteMenuManage(communityMenuVO);

		String _MenuNm = "%";
		communityMenuVO.setMenuNm(_MenuNm);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
	    return "com/cmm/redirect";
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
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] delMenuNos = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		delMenuNos = request.getParameterValues("uniqIds");

		if (delMenuNos == null || (delMenuNos.length == 0)) {
			model.addAttribute("message",  MessageHelper.getMessage("fail.common.delete"));
			model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
		    return "com/cmm/redirect";
		} 

		cmyMenuManageService.deleteMenuManageList(communityMenuVO.getTrgetId(), delMenuNos);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
	    return "com/cmm/redirect";
	}
	
	/**
	 * 커뮤니티 메뉴 목록을 엑셀 다운로드한다.
	 * 
	 * @param communityMenuVO
	 */
	@RequestMapping(value = "/cop/cmy/downMenuExcel.do")
	@Secured("ROLE_USER")
	public ModelAndView downMenuExcel(
			@ModelAttribute CommunityMenuVO communityMenuVO,
			ModelMap model) {

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
	public String registMenuExcel(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO,
			ModelMap model) {
		
		return "com/cop/cmy/CmyMenuExcelRegist";
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
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		InputStream fis = null; // 2011.11.1 보안점검 후속조치

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			if (!"".equals(file.getOriginalFilename())) {
				// 2011.10.07 업로드 파일에 대한 확장자를 체크
				if (file.getOriginalFilename().toUpperCase().endsWith(".XLSX")) {
					try { 
						fis = file.getInputStream();
						cmyMenuManageService.syncExcelMenu(communityMenuVO, fis);
					} catch (Exception e) {
						throw e;
					} finally {
						if (fis != null)	// 2011.11.1 보안점검 후속조치
							fis.close();
					}

				} else {
					model.addAttribute("message", "xlsx 파일 타입만 등록이 가능합니다.");
					return "com/cop/cmy/CmyMenuExcelRegist";
				}
			}
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
	    return "com/cmm/redirect";
	}
	
    /**
     * 캐쉬된 커뮤니티 메뉴를 청소한다.
     *      
     * @param communityMenuVO
     */    
	@SuppressWarnings("unchecked")
	@RequestMapping("/cop/cmy/clearCacheMenu.do")
    public String clearCacheMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityMenuVO communityMenuVO, 
			ModelMap model) {

		HashMap<String, Object> cacheMap = null;
		
		cacheMap = (HashMap<String, Object>) cacheDictionary.get(CacheKey.CMY_PREFIX + communityMenuVO.getTrgetId());
        if( cacheMap == null ) {
    		model.addAttribute("message", MessageHelper.getMessage("fail.common.delete"));
    		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
    	    return "com/cmm/redirect";
        }
        
        // clear cache
	    for(String key: cacheMap.keySet()){
	    	cacheMap.put(key, null);
	    }
    	cacheMap = null;
	    
		model.addAttribute("redirectURL", "/cop/cmy/listMenu.do");
	    return "com/cmm/redirect";
    }
    
}