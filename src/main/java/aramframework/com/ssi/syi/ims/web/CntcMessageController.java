package aramframework.com.ssi.syi.ims.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.ssi.syi.ims.domain.CntcMessageItemVO;
import aramframework.com.ssi.syi.ims.domain.CntcMessageVO;
import aramframework.com.ssi.syi.ims.service.CntcMessageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 연계메시지 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
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
public class CntcMessageController {

	@Autowired
	private CntcMessageService cntcMessageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 연계메시지 목록을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	@IncludedInfo(name = "연계메시지관리", order = 7010, gid = 70)
	@RequestMapping(value = "/ssi/syi/ims/listCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String listCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		cntcMessageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

		int totCnt = cntcMessageService.selectCntcMessageListCnt(cntcMessageVO);
		cntcMessageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageList");
	}

	/**
	 * 연계메시지 상세내역을 조회한다.
	 * 
	 * @param cntcMessageVO
	 * @param cntcMessageItemVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/detailCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String detailCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			@ModelAttribute CntcMessageItemVO cntcMessageItemVO, 
			ModelMap model) {

		/* 연계메시지 상세 */
		cntcMessageService.selectCntcMessageDetail(cntcMessageVO);

		/* 연계메시지항목 리스트 */
		cntcMessageItemVO.setRecordPerPage(9999999);
		cntcMessageItemVO.setFirstIndex(0);
		cntcMessageItemVO.setSearchCondition("CodeList");

		model.addAttribute("cntcMessageItemList", cntcMessageService.selectCntcMessageItemList(cntcMessageItemVO));

		return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageDetail");
	}

	/**
	 * 연계메시지 등록화면으로 이동한다.
	 * 
	 * @param cntcMessageVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/registCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String registCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			ModelMap model) {

		// 연계메시지 리스트박스 데이터
		CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
		searchCntcMessageVO.setRecordPerPage(999999);
		searchCntcMessageVO.setFirstIndex(0);
		searchCntcMessageVO.setSearchCondition("CodeList");
		
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

		return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageRegist");
	}

	/**
	 * 연계메시지를 등록한다.
	 * 
	 * @param cntcMessageVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/insertCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String insertCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcMessageVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계메시지 리스트박스 데이터
			CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");
			
			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcMessageVO.setFrstRegisterId(loginVO.getUniqId());

		cntcMessageService.insertCntcMessage(cntcMessageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/ssi/syi/ims/listCntcMessage.do");
	}

	/**
	 * 연계메시지 수정화면으로 이동한다.
	 * 
	 * @param cntcMessageVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/editCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String editCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			ModelMap model) {

		// 연계메시지 리스트박스 데이터
		CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
		searchCntcMessageVO.setRecordPerPage(999999);
		searchCntcMessageVO.setFirstIndex(0);
		searchCntcMessageVO.setSearchCondition("CodeList");

		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

		cntcMessageService.selectCntcMessageDetail(cntcMessageVO);

		return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageEdit");
	}

	/**
	 * 연계메시지를 수정한다.
	 * 
	 * @param cntcMessageVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/updateCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String updateCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcMessageVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계메시지 리스트박스 데이터
			CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");

			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcMessageVO.setLastUpdusrId(loginVO.getUniqId());

		cntcMessageService.updateCntcMessage(cntcMessageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/ssi/syi/ims/listCntcMessage.do");
	}

	/**
	 * 연계메시지를 삭제한다.
	 * 
	 * @param cntcMessageVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/deleteCntcMessage.do")
	@Secured("ROLE_ADMIN")
	public String deleteCntcMessage(
			@ModelAttribute CntcMessageVO cntcMessageVO,
			ModelMap model) {

		cntcMessageService.deleteCntcMessage(cntcMessageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/ssi/syi/ims/listCntcMessage.do");
	}

	/**
	 * 연계메시지 항목 등록화면으로 이동한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/registCntcMessageItem.do")
	@Secured("ROLE_ADMIN")
	public String registCntcMessageItem(
			@ModelAttribute CntcMessageItemVO cntcMessageItemVO, 
			ModelMap model) {

		// 연계메시지 리스트박스 데이터
		CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
		searchCntcMessageVO.setRecordPerPage(999999);
		searchCntcMessageVO.setFirstIndex(0);
		searchCntcMessageVO.setSearchCondition("CodeList");
		
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

		return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageItemRegist");
	}

	/**
	 * 연계메시지 항목을 등록한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/insertCntcMessageItem.do")
	@Secured("ROLE_ADMIN")
	public String insertCntcMessageItem(
			@ModelAttribute CntcMessageItemVO cntcMessageItemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcMessageItemVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계메시지 리스트박스 데이터
			CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");
			
			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageItemRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcMessageItemVO.setFrstRegisterId(loginVO.getUniqId());

		cntcMessageService.insertCntcMessageItem(cntcMessageItemVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/ssi/syi/ims/detailCntcMessage.do";
	}

	/**
	 * 연계메시지 항목 수정화면으로 이동한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/editCntcMessageItem.do")
	@Secured("ROLE_ADMIN")
	public String editCntcMessageItem(
			@ModelAttribute CntcMessageItemVO cntcMessageItemVO, 
			ModelMap model) {

		// 연계메시지 리스트박스 데이터
		CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
		searchCntcMessageVO.setRecordPerPage(999999);
		searchCntcMessageVO.setFirstIndex(0);
		searchCntcMessageVO.setSearchCondition("CodeList");
		
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

		cntcMessageService.selectCntcMessageItemDetail(cntcMessageItemVO);

		return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageItemEdit");
	}

	/**
	 * 연계메시지항목을 수정한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/updateCntcMessageItem.do")
	@Secured("ROLE_ADMIN")
	public String updateCntcMessageItem(
			@ModelAttribute CntcMessageItemVO cntcMessageItemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcMessageItemVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계메시지 리스트박스 데이터
			CntcMessageVO searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");
			
			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/ims/CntcMessageItemEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcMessageItemVO.setLastUpdusrId(loginVO.getUniqId());

		cntcMessageService.updateCntcMessageItem(cntcMessageItemVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/ssi/syi/ims/detailCntcMessage.do";
	}

	/**
	 * 연계메시지항목을 삭제한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	@RequestMapping(value = "/ssi/syi/ims/deleteCntcMessageItem.do")
	@Secured("ROLE_ADMIN")
	public String deleteCntcMessageItem(
			@ModelAttribute CntcMessageItemVO cntcMessageItemVO) {

		cntcMessageService.deleteCntcMessageItem(cntcMessageItemVO);

		return "forward:/ssi/syi/ims/detailCntcMessage.do";
	}

}