package aramframework.com.cop.adb.web;

import java.util.ArrayList;
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

import aramframework.cmm.domain.SearchVO;
import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.code.domain.SearchCodeVO;
import aramframework.com.cmm.code.service.CmmUseService;
import aramframework.com.cmm.com.annotation.IncludedInfo;
import aramframework.com.cop.adb.domain.AdressBookUserVO;
import aramframework.com.cop.adb.domain.AdressBookVO;
import aramframework.com.cop.adb.service.AdressBookService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
		adressBookVO.setWrterId(loginVO.getUserId());
		adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());
		model.addAttribute("userId", loginVO.getUserId());

		PaginationInfo paginationInfo = new PaginationInfo();
		adressBookVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", adressBookService.selectAdressBookList(adressBookVO));

		int totCnt = adressBookService.selectAdressBookListCnt(adressBookVO);
		adressBookVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "com/cop/adb/AdressBookList";
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
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {
		
		fill_common_code(model);
		return "com/cop/adb/AdressBookRegist";
	}

	/**
	 * 주소록 정보를 등록한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/insertAdressBook.do")
	@Secured("ROLE_USER")
	public String insertAdressBook(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AdressBookVO adressBookVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(adressBookVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/adb/AdressBookRegist";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setWrterId(loginVO.getUserId());
		adressBookVO.setFrstRegisterId(loginVO.getUserId());

		String[] tempId = adressBookVO.getUserIds().split(",");
		List<AdressBookUserVO> userList = new ArrayList<AdressBookUserVO>();
		for (int i = 0; i < tempId.length; i++) {
			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				userList.add(adbkUser);
			}
		}
		adressBookVO.setAdbkUserList(userList);
		adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());

		adressBookService.insertAdressBook(adressBookVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/adb/editAdressBook.do?adbkId=" + adressBookVO.getAdbkId());
	    return "com/cmm/redirect";
	}

	/**
	 * 주소록수정 화면으로 이동한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/editAdressBook.do")
	@Secured("ROLE_USER")
	public String editAdressBook(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {

		adressBookVO = adressBookService.selectAdressBook(adressBookVO);

		String userIds = "";
		List<AdressBookUserVO> userList = new ArrayList<AdressBookUserVO>();
		AdressBookUserVO adbkUser = null;
		for (int i = 0; i < adressBookVO.getAdbkUserList().size(); i++) {
			adbkUser = adressBookVO.getAdbkUserList().get(i);
			if (adbkUser.getEmplyrId().equals("") 
					&& !adbkUser.getNcrdId().equals("")) {
				userIds += adbkUser.getNcrdId() + ",";
				adbkUser = adressBookService.selectAdbkUser(adbkUser.getNcrdId());
			} 
			if (adbkUser.getNcrdId().equals("") 
					&& !adbkUser.getEmplyrId().equals("")) {
				userIds += adbkUser.getEmplyrId() + ",";
				adbkUser = adressBookService.selectAdbkUser(adbkUser.getEmplyrId());
			}
			userList.add(adbkUser);
		}
		adressBookVO.setUserIds(userIds);
		adressBookVO.setAdbkUserList(userList);

		boolean writer = false;
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (adressBookVO.getWrterId().equals(loginVO.getUserId())) {
			writer = true;
		}
		model.addAttribute("writer", writer);
		model.addAttribute("adressBookVO", adressBookVO);

		fill_common_code(model);
		return "com/cop/adb/AdressBookEdit";
	}

	/**
	 * 주소록 정보를 수정한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/updateAdressBook.do")
	@Secured("ROLE_USER")
	public String updateAdressBook(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AdressBookVO adressBookVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(adressBookVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/adb/AdressBookEdit";
		}

		String[] tempId = adressBookVO.getUserIds().split(",");
		List<AdressBookUserVO> userList = new ArrayList<AdressBookUserVO>();
		for (int i = 0; i < tempId.length; i++) {
			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				userList.add(adbkUser);
			}
		}
		adressBookVO.setAdbkUserList(userList);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setLastUpdusrId(loginVO.getUserId());
		adressBookVO.setUseAt("Y");

		adressBookService.updateAdressBook(adressBookVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/adb/editAdressBook.do?adbkId=" + adressBookVO.getAdbkId());
	    return "com/cmm/redirect";
	}
	
	/**
	 * 주소록을 삭제한다.
	 * 
	 * @param adressBookVO
	 */
	@RequestMapping("/cop/adb/deleteAdressBook.do")
	@Secured("ROLE_USER")
	public String deleteAdressBook(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AdressBookVO adressBookVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		adressBookVO.setUseAt("N");
		adressBookVO.setLastUpdusrId(loginVO.getUserId());

		adressBookService.deleteAdressBook(adressBookVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/adb/listAdressBook.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 주소록의 구성원을 갱신한다.
	 * 
	 * @param adressBookVO
	 * @param checkCnd
	 */
	@RequestMapping("/cop/adb/refreshAdressBookUser.do")
	@Secured("ROLE_USER")
	public String refreshAdressBookUser(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AdressBookVO adressBookVO, 
			@RequestParam String source, 
			ModelMap model) {

		String[] tempId = adressBookVO.getUserIds().split(",");
		List<AdressBookUserVO> userList = new ArrayList<AdressBookUserVO>();
		for (int i = 0; i < tempId.length; i++) {
			if (!tempId[i].equals("")) {
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				userList.add(adbkUser);
			}
		}
		adressBookVO.setAdbkUserList(userList);

		fill_common_code(model);
		if (source.equals("regist"))
			return "com/cop/adb/AdressBookRegist";
		else {
			model.addAttribute("writer", true);
			return "com/cop/adb/AdressBookEdit";
		}
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

		return "com/cop/adb/AdressBookPopup";
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
		adressBookVO.setWrterId(loginVO.getUserId());
		adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());

		adressBookVO.setFirstIndex(0);
		adressBookVO.setRecordPerPage(5);

		PaginationInfo paginationInfo = new PaginationInfo();
		adressBookVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", adressBookService.selectAdressBookList(adressBookVO));

		return "com/cop/adb/AdressBookMainPage";
	}

}
