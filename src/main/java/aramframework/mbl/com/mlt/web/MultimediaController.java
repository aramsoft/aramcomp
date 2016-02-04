package aramframework.mbl.com.mlt.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.WebUtil;
import aramframework.mbl.com.mlt.domain.MultimediaVO;
import aramframework.mbl.com.mlt.service.MultimediaService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 멀티미디어 제어에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 멀티미디어에 대한 등록, 수정, 삭제, 조회 요청 사항을 Service와 매핑 처리한다.
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
public class MultimediaController {

	@Autowired
    private MultimediaService multimediaService;

	@Autowired
    private FileMngService fileMngService;

	@Autowired
    private FileMngUtil fileUtil;

	@Autowired
    private CmmUseService cmmUseService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 모바일 멀티미디어 목록 화면으로 이동한다.
     * 
     */
    @RequestMapping(value = "/mbl/com/mlt/listMobileMultimedia.mdo")
    public String listMobileMultimedia() {
        return "aramframework/mbl/com/mlt/MobileMultimediaList";
    }

    /**
     * 모바일 멀티미디어 목록 조회 Service interface 호출 및 결과를
     * 반환한다.(JSON 통신)
     * 
     */
    @RequestMapping(value = "/mbl/com/mlt/listMobileMultimediaJson.mdo")
    public ModelAndView listMobileMultimediaJson() {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        MultimediaVO multimediaVO = new MultimediaVO();

        multimediaVO.setFirstIndex(0);
        multimediaVO.setRecordPerPage(1000);

        modelAndView.addObject("resultList", multimediaService.selectMultimediaList(multimediaVO));
        modelAndView.addObject("fileInfoList", multimediaService.getMultimediaFileInfoFromXML());

        return modelAndView;
    }

    /**
     * 멀티미디어 파일 목록 조회 Service interface 호출 및 결과를
     * 반환한다.(JSON 통신)
     * 
     */
    @RequestMapping(value = "/mbl/com/mlt/listMultimediaFileJson.mdo")
    public ModelAndView listMultimediaFileJson(
            @RequestParam String atchFileId) {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        modelAndView.addObject("fileList", fileMngService.selectFileList(fvo));

        modelAndView.addObject("relativePath", "multimedia/");

        return modelAndView;
    }

	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
    /**
     * 멀티미디어 목록 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param multimediaVO
     */
	@IncludedInfo(name = "멀티미디어 정보", order = 10030, gid = 100)
    @RequestMapping(value = "/mbl/com/mlt/listMultimedia.do")
	@Secured("ROLE_ADMIN")
    public String listMultimedia(
            @ModelAttribute MultimediaVO multimediaVO, 
            ModelMap model) {
    	
        PaginationInfo paginationInfo = new PaginationInfo();
        multimediaVO.fillPageInfo(paginationInfo);

        model.addAttribute("resultList", multimediaService.selectMultimediaList(multimediaVO));

        int totCnt = multimediaService.selectMultimediaListCnt(multimediaVO);
        multimediaVO.setTotalRecordCount(totCnt);

        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mlt/MultimediaList");
     }

    /**
     * 멀티미디어 상세정보 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param multimediaVO
     */
    @RequestMapping(value = "/mbl/com/mlt/detailMultimedia.do")
	@Secured("ROLE_USER")
    public String detailMultimedia(
            @ModelAttribute MultimediaVO multimediaVO) {
    	
        multimediaService.selectMultimedia(multimediaVO);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mlt/MultimediaDetail");
    }

    /**
     * 멀티미디어 정보 등록 화면으로 이동한다.
     * 
     * @param multimediaVO
     */
    @RequestMapping(value = "/mbl/com/mlt/registMultimedia.do")
	@Secured("ROLE_USER")
    public String registMultimedia(
            @ModelAttribute MultimediaVO multimediaVO) {
    	
		cmmUseService.populateCmmCodeList("COM077", "COM077_mltmdCmmCode");

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mlt/MultimediaRegist");
    }

    /**
     * 멀티미디어 정보 등록 Service interface 호출 및 결과를 반환한다.
     * 
     * @param multimediaVO
     */
    @RequestMapping(value = "/mbl/com/mlt/insertMultimedia.do")
	@Secured("ROLE_USER")
    public String insertMultimedia(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute MultimediaVO multimediaVO, 
            BindingResult bindingResult, 
            ModelMap model) 
    throws Exception {

        beanValidator.validate(multimediaVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mlt/MultimediaRegist");
        }

        // 첨부파일 관련 첨부파일ID 생성
        String atchFileId = "";

        Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
        	List<FileVO> result = fileUtil.parseFileInf(files, "MLT_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result); 
  
            // 파일을 상대 경로에 저장한다.
            multimediaService.copyFileToRelativePath(result);

            // 파일 구분을 조회한다.
            String mltmdNm = cmmUseService.selectCmmCode("COM077", multimediaVO.getMltmdCode()).getCodeNm();
            multimediaVO.setMltmdNm(mltmdNm);

            // 파일 확장자를 통해 지원브라우저를 조회한다.
            List<String> extList = new ArrayList<String>();

            for (int i = 0; i < result.size(); i++) {
                extList.add(result.get(i).getFileExtsn());
            }
            multimediaVO.setBrowserNm(multimediaService.getBrowserInfoFromXML(mltmdNm, extList));
        }
        multimediaVO.setAtchFileId(atchFileId); // 첨부파일ID

        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        multimediaVO.setMberId(loginVO.getId());

        multimediaService.insertMultimedia(multimediaVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mlt/listMultimedia.do");
    }

    /**
     * 멀티미디어 정보 수정 화면으로 이동한다.
     * 
     * @param multimediaVO
     */
    @RequestMapping(value = "/mbl/com/mlt/editMultimedia.do")
	@Secured("ROLE_USER")
    public String editMultimedia(
            @ModelAttribute MultimediaVO multimediaVO) {
    	
        multimediaService.selectMultimedia(multimediaVO);

		cmmUseService.populateCmmCodeList("COM077", "COM077_mltmdCmmCode");

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mlt/MultimediaEdit");
    }

    /**
     * 멀티미디어 정보 수정 Service interface 호출 및 결과를 반환한다.
     * 
     * @param multimediaVO
     */
    @RequestMapping(value = "/mbl/com/mlt/updateMultimedia.do")
	@Secured("ROLE_USER")
    public String updateMultimedia(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute MultimediaVO multimediaVO, 
            BindingResult bindingResult, 
            ModelMap model) 
    throws Exception {
    	
        // Validation
        beanValidator.validate(multimediaVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mlt/MultimediaEdit");
        }

        // 첨부파일 관련 첨부파일ID 생성
        List<FileVO> result = null;
        String atchFileId = multimediaVO.getAtchFileId();

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            if ("".equals(atchFileId)) {
            	result = fileUtil.parseFileInf(files, "MLT_", 0, atchFileId, "");
                atchFileId = fileMngService.insertFileInfs(result);
                multimediaVO.setAtchFileId(atchFileId); // 첨부파일 ID
            } else {
                FileVO fvo = new FileVO();
                fvo.setAtchFileId(atchFileId);
                int cnt = fileMngService.getMaxFileSN(fvo);
                result = fileUtil.parseFileInf(files, "MLT_", cnt, atchFileId, "");
                fileMngService.addFileInfs(result);
            }

            // 파일을 상대 경로에 저장한다.
            multimediaService.copyFileToRelativePath(result);

            // 파일 구분을 조회한다.
            String mltmdNm = cmmUseService.selectCmmCode("COM077", multimediaVO.getMltmdCode()).getCodeNm();

            // 파일 확장자를 통해 지원브라우저를 조회한다.
            FileVO fvo = new FileVO();
            fvo.setAtchFileId(atchFileId);
            List<FileVO> multimediaFileList = fileMngService.selectFileList(fvo);

            List<String> extList = new ArrayList<String>();

            for (int i = 0; i < multimediaFileList.size(); i++) {
                extList.add(((FileVO) multimediaFileList.get(i)).getFileExtsn());
            }
            multimediaVO.setBrowserNm(multimediaService.getBrowserInfoFromXML(mltmdNm, extList));
        }
        
        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        multimediaVO.setMberId(loginVO.getId());

        multimediaService.updateMultimedia(multimediaVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mlt/listMultimedia.do");
    }

    /**
     * 멀티미디어 정보 삭제 Service interface 호출 및 결과를 반환한다.
     * 
     * @param multimediaVO
     */
    @RequestMapping(value = "/mbl/com/mlt/deleteMultimedia.do")
	@Secured("ROLE_USER")
    public String deleteMultimedia(
            @ModelAttribute MultimediaVO multimediaVO, 
            ModelMap model) {
    	
    	// 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        multimediaVO.setMberId(loginVO.getId());
        
        multimediaService.deleteMultimedia(multimediaVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mlt/listMultimedia.do");
    }

}
