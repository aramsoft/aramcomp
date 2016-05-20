package aramframework.mbl.com.mpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.mbl.com.mpa.domain.PhotoVO;
import aramframework.mbl.com.mpa.service.PhotoService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 사진 앨범에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 사진에 대한 등록, 수정, 삭제, 조회 요청 사항을 Service와 매핑 처리한다.
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
public class PhotoController {

	@Autowired
    private PhotoService photoService;

	@Autowired
    private FileMngUtil fileUtil;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 모바일 사진 목록 화면으로 이동한다.
     * 
     */
    @RequestMapping(value = "/mbl/com/mpa/listMobilePhoto.mdo")
    public String listMobilePhoto() {
        return "aramframework/mbl/com/mpa/MobilePhotoList";
    }

    /**
     * 모바일 사진 목록 조회 Service interface 호출 및 결과를
     * 반환한다.(JSON 통신)
     * 
     */
    @RequestMapping(value = "/mbl/com/mpa/listMobilePhotoJson.mdo")
    public ModelAndView listMobilePhotoJson() {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        PhotoVO photoVO = new PhotoVO();

        photoVO.getSearchVO().setFirstIndex(0);
        photoVO.getSearchVO().setRecordPerPage(1000);

        modelAndView.addObject("resultList", photoService.selectPhotoList(photoVO));

        return modelAndView;
    }

	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
    /**
     * 사진 목록 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param photoVO
     */
	@IncludedInfo(name = "사진 정보", order = 10050, gid = 100)
    @RequestMapping(value = "/mbl/com/mpa/listPhoto.do")
	@Secured("ROLE_ADMIN")
    public String listPhoto(
    		@ModelAttribute PhotoVO photoVO,
            ModelMap model) {
    	
        PaginationInfo paginationInfo = new PaginationInfo();
        photoVO.getSearchVO().fillPageInfo(paginationInfo);

        model.addAttribute("resultList", photoService.selectPhotoList(photoVO));
        int totCnt = photoService.selectPhotoListCnt(photoVO);

        photoVO.getSearchVO().setTotalRecordCount(totCnt);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute(paginationInfo);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mpa/PhotoList");
    }

    /**
     * 사진 상세정보 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param photoVO
     */
    @RequestMapping(value = "/mbl/com/mpa/detailPhoto.do")
	@Secured("ROLE_USER")
    public String detailPhoto(
    		PhotoVO photoVO,
            ModelMap model) {
    	
    	model.addAttribute(photoService.selectPhoto(photoVO));

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mpa/PhotoDetail");
    }

    /**
     * 사진 정보 등록 화면으로 이동한다.
     * 
     * @param photoVO
     */
    @RequestMapping(value = "/mbl/com/mpa/registPhoto.do")
	@Secured("ROLE_USER")
    public String registPhoto(
    		@ModelAttribute PhotoVO photoVO) {

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mpa/PhotoRegist");
    }

    /**
     * 사진 정보 등록 Service interface 호출 및 결과를 반환한다.
     * 
     * @param photoVO
     */
    @RequestMapping(value = "/mbl/com/mpa/insertPhoto.do")
	@Secured("ROLE_USER")
    public String insertPhoto(
    		final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute PhotoVO photoVO,
            BindingResult bindingResult,
            ModelMap model) 
    throws Exception {

        beanValidator.validate(photoVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mpa/PhotoRegist");
        }

		// 첨부파일 관련 첨부파일ID 생성
        photoVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "MPA_"));

        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO =(LoginVO) UserDetailsHelper.getAuthenticatedUser();
        photoVO.setMberId(loginVO.getId());

        photoService.insertPhoto(photoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mpa/listPhoto.do");
    }

    /**
     * 사진 정보 수정 화면으로 이동한다.
     * 
     * @param photoVO
      */
    @RequestMapping(value = "/mbl/com/mpa/editPhoto.do")
	@Secured("ROLE_USER")
    public String editPhoto(
       		PhotoVO photoVO,
            ModelMap model) {
    	
    	model.addAttribute(photoService.selectPhoto(photoVO));

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mpa/PhotoEdit");
    }

    /**
     * 사진 정보 수정 Service interface 호출 및 결과를 반환한다.
     * 
     * @param photoVO
     */
    @RequestMapping(value = "/mbl/com/mpa/updatePhoto.do")
	@Secured("ROLE_USER")
    public String updatePhoto(
    		final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute PhotoVO photoVO,
            BindingResult bindingResult,
            ModelMap model) 
    throws Exception {

        // Validation
        beanValidator.validate(photoVO, bindingResult);
        if (bindingResult.hasErrors()) {
        	multiRequest.setAttribute("jspPrefix", "aramframework/mbl");
    		return WebUtil.adjustViewName("/com/mpa/PhotoEdit");
        }

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = photoVO.getAtchFileId();
		photoVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "MPA_", atchFileId));
         
        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        photoVO.setMberId(loginVO.getId());
        
        photoService.updatePhoto(photoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mpa/listPhoto.do");
    }

    /**
     * 사진 정보 삭제 Service interface 호출 및 결과를 반환한다.
     * 
     * @param photoVO
     */
    @RequestMapping(value = "/mbl/com/mpa/deletePhoto.do")
	@Secured("ROLE_USER")
    public String deletePhoto(
    		@ModelAttribute("photoVO") PhotoVO photoVO,
            ModelMap model) {
    	
    	// 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        photoVO.setMberId(loginVO.getId());
        
        photoService.deletePhoto(photoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mpa/listPhoto.do");
    }
    
}
