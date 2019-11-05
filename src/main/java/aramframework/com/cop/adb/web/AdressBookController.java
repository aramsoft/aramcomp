package aramframework.com.cop.adb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchCodeVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.adb.domain.AdressBookUserVO;
import aramframework.com.cop.adb.domain.AdressBookVO;
import aramframework.com.cop.adb.service.AdressBookService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 주소록정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class AdressBookController {

	@Autowired
	private AdressBookService adressBookService;
	
	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 주소록 정보에 대한 목록을 조회한다.
	 * 
	 * @param adressBookVO
	 */
	@IncludedInfo(name = "주소록관리", order = 4080, gid = 40)
	@RequestMapping("/cop/adb/listAdressBook.do")
	@Secured("ROLE_USER")
	public String listAdressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setWrterId(loginVO.getId());
		adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());
		model.addAttribute("userId", loginVO.getId());

		PaginationInfo paginationInfo = new PaginationInfo();
		adressBookVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", adressBookService.selectAdressBookList(adressBookVO));

		int totCnt = adressBookService.selectAdressBookListCnt(adressBookVO);
		adressBookVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/adb/AdressBookList");
	}

	private void fill_common_code(ModelMap model) {
		// 조직정보를 조회 - ORGNZT_ID정보
		SearchCodeVO vo = new SearchCodeVO();
		vo.setTableNm("COMTN_ORGNZT_INFO");
		model.addAttribute("orgnztList", cmmUseService.selectOgrnztIdList(vo));
	}
	
	/**
	 * 주소록등록 화면으로 이동한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/registAdressBook.do")
	@Secured("ROLE_USER")
	public String registAdressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {
		
		fill_common_code(model);
		return WebUtil.adjustViewName("/cop/adb/AdressBookRegist");
	}

	/**
	 * 주소록 정보를 등록한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/insertAdressBook.do")
	@Secured("ROLE_USER")
	public String insertAdressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(adressBookVO, bindingResult);
		if (bindingResult.hasErrors()) {
			fill_common_code(model);
			return WebUtil.adjustViewName("/cop/adb/AdressBookRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setWrterId(loginVO.getId());
		adressBookVO.setFrstRegisterId(loginVO.getId());

		String[] tempId = adressBookVO.getUserIds().split(",");
		for (int i = 0; i < tempId.length; i++) {
			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				adressBookVO.getAdbkMan().add(adbkUser);
			}
		}
		adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());

		adressBookService.insertAdressBook(adressBookVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, adressBookVO, "/cop/adb/listAdressBook.do");
	}

	/**
	 * 주소록수정 화면으로 이동한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/editAdressBook.do")
	@Secured("ROLE_USER")
	public String editAdressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {

		adressBookService.selectAdressBook(adressBookVO);

		String id = "";
		AdressBookUserVO adbkUser = null;
		for (int i = 0; i < adressBookVO.getAdbkMan().size(); i++) {
			adbkUser = adressBookVO.getAdbkMan().get(i);
			if (adbkUser.getNcrdId() == null) {
				adbkUser.setNcrdId("");
			}
			if (adbkUser.getEmplyrId() == null) {
				adbkUser.setEmplyrId("");
			}
		}
		for (int i = 0; i < adressBookVO.getAdbkMan().size(); i++) {
			adbkUser = adressBookVO.getAdbkMan().get(i);
			if (adbkUser.getEmplyrId().equals("")) {
				id += adbkUser.getNcrdId() + ",";
			} else {
				id += adbkUser.getEmplyrId() + ",";
			}
		}
		adressBookVO.setUserIds(id);

		boolean writer = false;
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (adressBookVO.getWrterId().equals(loginVO.getId())) {
			writer = true;
		}
		model.addAttribute("writer", writer);

		fill_common_code(model);
		return WebUtil.adjustViewName("/cop/adb/AdressBookEdit");
	}

	/**
	 * 주소록 정보를 수정한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/updateAdressBook.do")
	@Secured("ROLE_USER")
	public String updateAdressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(adressBookVO, bindingResult);
		if (bindingResult.hasErrors()) {
			fill_common_code(model);
			return WebUtil.adjustViewName("/cop/adb/AdressBookEdit");
		}

		String[] tempId = adressBookVO.getUserIds().split(",");

		for (int i = 0; i < tempId.length; i++) {
			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				adressBookVO.getAdbkMan().add(adbkUser);
			}
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setLastUpdusrId(loginVO.getId());
		adressBookVO.setUseAt("Y");

		adressBookService.updateAdressBook(adressBookVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, adressBookVO, "/cop/adb/listAdressBook.do");
	}
	
	/**
	 * 주소록을 삭제한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/deleteAdressBook.do")
	@Secured("ROLE_USER")
	public String deleteAdressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {

		adressBookService.selectAdressBook(adressBookVO);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setUseAt("N");
		adressBookVO.setLastUpdusrId(loginVO.getId());

		adressBookService.deleteAdressBook(adressBookVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, adressBookVO, "/cop/adb/listAdressBook.do");
	}

	/**
	 * 주소록 등록가능한 구성원을 조회한다.
	 * 
	 * @param adressBookUserVO
	 */
	@RequestMapping("/cop/adb/listUserPopup.do")
	@Secured("ROLE_USER")
	public String listAdressBookUser(
			@ModelAttribute AdressBookUserVO adressBookUserVO, 
			ModelMap model) {

		if (adressBookUserVO.getSearchCondition() == null 
			|| adressBookUserVO.getSearchCondition().equals("")) {
			adressBookUserVO.setSearchCondition("USERLIST");
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		adressBookUserVO.fillPageInfo(paginationInfo);

		List<EgovMap> resultList = null;
		int totCnt = 0;
		if (adressBookUserVO.getSearchCondition().equals("USERLIST")) {
			resultList = adressBookService.selectManList(adressBookUserVO);
			totCnt = adressBookService.selectManListCnt(adressBookUserVO);
		} else {
			resultList = adressBookService.selectCardList(adressBookUserVO);
			totCnt = adressBookService.selectCardListCnt(adressBookUserVO);
		}
		model.addAttribute("resultList", resultList);

		adressBookUserVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/adb/AdressBookPopup");
	}

	/**
	 * 주소록의 구성원을 추가한다.
	 * 
	 * @param adressBookVO
	 * @param checkCnd
	 */
	@RequestMapping("/cop/adb/insertAdressBookUser.do")
	@Secured("ROLE_USER")
	public String insertAdressBookUser(
			@ModelAttribute AdressBookVO adressBookVO, 
			@RequestParam String checkCnd, 
			ModelMap model) {

		String[] tempId = adressBookVO.getUserIds().split(",");
		for (int i = 0; i < tempId.length; i++) {
			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				adressBookVO.getAdbkMan().add(adbkUser);
			}
		}

		fill_common_code(model);

		if (checkCnd.equals("regist"))
			return WebUtil.adjustViewName("/cop/adb/AdressBookRegist");
		else {
			model.addAttribute("writer", true);
			return WebUtil.adjustViewName("/cop/adb/AdressBookEdit");
		}
	}

	/**
	 * 주소록의 구성원을 삭제한다.
	 * 
	 * @param adressBookVO
	 * @param checkWord
	 * @param checkCnd
	 */
	@RequestMapping("/cop/adb/deleteAdressBookUser.do")
	@Secured("ROLE_USER")
	public String deleteAdressBookUser(
			@ModelAttribute AdressBookVO adressBookVO, 
			@RequestParam String checkWord, 
			@RequestParam String checkCnd, 
			ModelMap model) {

		String[] tempId = adressBookVO.getUserIds().split(",");

		String id = "";
		for (int i = 0; i < tempId.length; i++) {

			if (tempId[i].equals(checkWord)) {
				continue;
			}

			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				adressBookVO.getAdbkMan().add(adbkUser);
			}
			id += tempId[i] + ",";
		}
		adressBookVO.setUserIds(id);

		fill_common_code(model);

		if (checkCnd.equals("regist"))
			return WebUtil.adjustViewName("/cop/adb/AdressBookRegist");
		else {
			model.addAttribute("writer", true);
			return WebUtil.adjustViewName("/cop/adb/AdressBookEdit");
		}
	}

	/**
	 * 주소록 정보에 대한 목록을 조회한다.(마이페이지 적용)
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/listAdressBookMainPage.do")
	@Secured("ROLE_USER")
	public String listAdressBookMainPage(
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setWrterId(loginVO.getId());
		adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());

		adressBookVO.setFirstIndex(0);
		adressBookVO.setRecordPerPage(5);

		PaginationInfo paginationInfo = new PaginationInfo();
		adressBookVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", adressBookService.selectAdressBookList(adressBookVO));

		return WebUtil.adjustViewName("/cop/adb/AdressBookMainPage");
	}

}
