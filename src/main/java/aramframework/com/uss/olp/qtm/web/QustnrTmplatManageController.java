package aramframework.com.uss.olp.qtm.web;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olp.qtm.domain.QustnrTmplatManageVO;
import aramframework.com.uss.olp.qtm.service.QustnrTmplatManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문템플릿 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class QustnrTmplatManageController {

	@Autowired
	private QustnrTmplatManageService qustnrTmplatManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 설문템플릿 목록을 조회한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@IncludedInfo(name = "설문템플릿관리", order = 5140, gid = 50)
	@RequestMapping(value = "/uss/olp/qtm/listQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String listQustnrTmplat(
			@ModelAttribute QustnrTmplatManageVO qustnrTmplatManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrTmplatManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrTmplatManageService.selectQustnrTmplatManageList(qustnrTmplatManageVO));
		int totCnt = (Integer) qustnrTmplatManageService.selectQustnrTmplatManageListCnt(qustnrTmplatManageVO);

		qustnrTmplatManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qtm/QustnrTmplatList");
	}

	/**
	 * 설문템플릿 이미지 목록을 상세조회 한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/getQustnrTmplatImage.do")
	public void getQustnrTmplatImage(
			@ModelAttribute QustnrTmplatManageVO qustnrTmplatManageVO, 
			HttpServletResponse response) 
	throws Exception {

		QustnrTmplatManageVO resultVO = qustnrTmplatManageService.selectQustnrTmplatManageTmplatImage(qustnrTmplatManageVO);
		byte[] img = resultVO.getQestnrTmplatImageInfo();

		String type = "image/jpeg";

		response.setHeader("Content-Type", type);
		response.setHeader("Content-Length", "" + img.length);
		response.setHeader("Cache-Control", "max-age=86400");
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	/**
	 * 설문템플릿 목록을 상세조회한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/detailQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String detailQustnrTmplat(
			QustnrTmplatManageVO qustnrTmplatManageVO,
			ModelMap model) {

		model.addAttribute(qustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO));

		return WebUtil.adjustViewName("/uss/olp/qtm/QustnrTmplatDetail");
	}

	/**
	 * 설문템플릿를 등록한다. / 초기등록페이지
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/registQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String registQustnrTmplat(
			@ModelAttribute QustnrTmplatManageVO qustnrTmplatManageVO) {

		return WebUtil.adjustViewName("/uss/olp/qtm/QustnrTmplatRegist");
	}

	/**
	 * 설문템플릿를 등록 처리 한다. / 등록처리
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/insertQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String insertQustnrTmplat(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute QustnrTmplatManageVO qustnrTmplatManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(qustnrTmplatManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qtm/QustnrTmplatRegist");
		}

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			log.info("getName =>" + file.getName());
			if (file.getName().equals("qestnrTmplatImage")) {
				qustnrTmplatManageVO.setQestnrTmplatImageInfo(file.getBytes());
			}
		}

		// log.info("qestnrTmplatImagepathnm =>" + qustnrTmplatManageVO.getQestnrTmplatImagepathnm() );

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrTmplatManageVO.setFrstRegisterId(loginVO.getUniqId());

		qustnrTmplatManageService.insertQustnrTmplatManage(qustnrTmplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qtm/listQustnrTmplat.do");
	}

	/**
	 * 설문템플릿를 수정화면으로 이동한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/editQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String editQustnrTmplat(
			QustnrTmplatManageVO qustnrTmplatManageVO,
			ModelMap model) {

		model.addAttribute(qustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO));

		return WebUtil.adjustViewName("/uss/olp/qtm/QustnrTmplatEdit");
	}

	/**
	 * 설문템플릿를 수정처리 한다.
	 * 
	 * @param multiRequest
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/updateQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String updateQustnrTmplat(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute QustnrTmplatManageVO qustnrTmplatManageVO, 
			BindingResult bindingResult, 
			ModelMap model)
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(qustnrTmplatManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qtm/QustnrTmplatEdit");
		}

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			log.info("getName =>" + file.getName());
			// 파일 수정여부 확인
			if (file.getOriginalFilename() != "") {
				if (file.getName().equals("qestnrTmplatImage")) {
					qustnrTmplatManageVO.setQestnrTmplatImageInfo(file.getBytes());
				}
			}
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrTmplatManageVO.setLastUpdusrId(loginVO.getUniqId());

		qustnrTmplatManageService.updateQustnrTmplatManage(qustnrTmplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qtm/listQustnrTmplat.do");
	}

	/**
	 * 설문템플릿 목록을 삭제한다.
	 * 
	 * @param qustnrTmplatManageVO
	 */
	@RequestMapping(value = "/uss/olp/qtm/deleteQustnrTmplat.do")
	@Secured("ROLE_ADMIN")
	public String deleteQustnrTmplat(
			@ModelAttribute QustnrTmplatManageVO qustnrTmplatManageVO, 
			ModelMap model) {

		qustnrTmplatManageService.deleteQustnrTmplatManage(qustnrTmplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qtm/listQustnrTmplat.do");
	}

}
