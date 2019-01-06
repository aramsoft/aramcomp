package aramframework.com.uss.olp.qri.web;

import java.util.Map;

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
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olp.qri.domain.QustnrRespondInfoVO;
import aramframework.com.uss.olp.qri.service.QustnrRespondInfoService;
import aramframework.com.uss.olp.qrm.domain.QustnrRespondManageVO;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageService;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문조사 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class QustnrRespondInfoController {

	@Autowired
	private QustnrRespondManageService qustnrRespondManageService;

	@Autowired
	private QustnrRespondInfoService qustnrRespondInfoService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 설문조사(설문등록) 목록을 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@IncludedInfo(name = "설문참여", order = 5144, gid = 50)
	@RequestMapping(value = "/uss/olp/qri/listQustnrRespondInfoUser.do")
	public String listQustnrRespondInfoUser(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrRespondInfoVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrRespondInfoService.selectQustnrRespondInfoManageList(qustnrRespondInfoVO));
		int totCnt = (Integer) qustnrRespondInfoService.selectQustnrRespondInfoManageListCnt(qustnrRespondInfoVO);

		qustnrRespondInfoVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoUserList");
	}

	/**
	 * 설문조사(설문등록)를 등록한다.
	 * 
	 * @param qestnrId
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/registQustnrRespondInfoUser.do")
	public String registQustnrRespondInfoUser(
			@RequestParam(value="qestnrId", required=true) String qestnrId,
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		String uniqId = "";
		if (loginVO != null) {
			uniqId = loginVO.getUniqId();	
		} 
		
		// 사용자정보
		model.addAttribute("emplyrinfo", qustnrRespondInfoService.selectQustnrRespondEmplyrinfo(uniqId));

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		// 설문지정보ID 설정
		model.addAttribute("qestnrId", qestnrId);
		// 설문정보
		model.addAttribute("comtnqestnrinfo", qustnrRespondInfoService.selectQustnrRespondQestnrInfo(qestnrId));
		// 문항정보
		model.addAttribute("comtnqustnrqesitm", qustnrRespondInfoService.selectQustnrRespondQustnrQesitm(qestnrId));
		// 항목정보
		model.addAttribute("comtnqustnriem", qustnrRespondInfoService.selectQustnrRespondQustnrIem(qestnrId));

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoUserRegist");
	}

	/**
	 * 설문템플릿을 적용한다.
	 * 
	 * @param commandMap
	 */
	@RequestMapping(value = "/uss/olp/qri/template/template.do")
	public String egovQustnrRespondInfoManageTemplate(
			@CommandMap Map<String, Object> commandMap,
			ModelMap model) {

		String sTemplateUrl = (String) commandMap.get("templateUrl");

//		log.debug("qestnrId=>" + commandMap.get("qestnrId"));
//		log.debug("qestnrTmplatId=>" + commandMap.get("qestnrTmplatId"));
//		log.debug("templateUrl=>" + commandMap.get("templateUrl"));

		// 설문지정보ID 설정
		String qestnrId = commandMap.get("qestnrId") == null ? "" : (String) commandMap.get("qestnrId");
		model.addAttribute("qestnrId", qestnrId);
		
		// 설문정보
		model.addAttribute("Comtnqestnrinfo", qustnrRespondInfoService.selectQustnrRespondQestnrInfo(qestnrId));
		// 문항정보
		model.addAttribute("Comtnqustnrqesitm", qustnrRespondInfoService.selectQustnrRespondQustnrQesitm(qestnrId));
		// 항목정보
		model.addAttribute("Comtnqustnriem", qustnrRespondInfoService.selectQustnrRespondQustnrIem(qestnrId));

		return sTemplateUrl;
	}

	/**
	 * 설문조사(설문등록)를 등록한다.
	 * 
	 * @param commandMap
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/insertQustnrRespondInfoUser.do")
	public String insertQustnrRespondInfoUser(
			@CommandMap Map<String, Object> commandMap, 
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			ModelMap model) {

		// 설문조사 처리 START
		String sKey = "";
		String sVal = "";
		for (Object key : commandMap.keySet()) {

			sKey = key.toString();

			// 설문문항정보 추출
			if (sKey.length() > 6 && sKey.substring(0, 6).equals("QQESTN")) {

				// 설문조사 등록
				// 객관식 답안 처리
				if (((String) commandMap.get("TY_" + key)).equals("1")) {

					if (commandMap.get(key) instanceof String) {
						sVal = (String) commandMap.get(key);

						QustnrRespondInfoVO vo = new QustnrRespondInfoVO();

						vo.setQestnrId((String) commandMap.get("qestnrId"));
						vo.setQestnrQesitmId(sKey);
						vo.setQustnrIemId(sVal);

						vo.setRespondAnswerCn("");

						vo.setRespondNm((String) commandMap.get("respondNm"));
						vo.setEtcAnswerCn((String) commandMap.get("ETC_" + sVal));

						vo.setFrstRegisterId("SYSTEM");

						qustnrRespondInfoService.insertQustnrRespondInfo(vo);
					} else {
						String[] arrVal = (String[]) commandMap.get(key);
						for (int g = 0; g < arrVal.length; g++) {
							// ("QQESTN arr :" + arrVal[g]);
							QustnrRespondInfoVO vo = new QustnrRespondInfoVO();

							vo.setQestnrId((String) commandMap.get("qestnrId"));
							vo.setQestnrQesitmId(sKey);
							vo.setQustnrIemId(arrVal[g]);

							vo.setRespondAnswerCn("");

							vo.setRespondNm((String) commandMap.get("respondNm"));
							vo.setEtcAnswerCn((String) commandMap.get("ETC_" + arrVal[g]));

							vo.setFrstRegisterId("SYSTEM");

							qustnrRespondInfoService.insertQustnrRespondInfo(vo);
						}
					}

					// 주관식 답안 처리
				} else if (((String) commandMap.get("TY_" + key)).equals("2")) {
					QustnrRespondInfoVO vo = new QustnrRespondInfoVO();

					vo.setQestnrId((String) commandMap.get("qestnrId"));
					vo.setQestnrQesitmId(sKey);
					vo.setQustnrIemId(null);

					vo.setRespondAnswerCn((String) commandMap.get(sKey));

					vo.setRespondNm((String) commandMap.get("respondNm"));
					vo.setEtcAnswerCn(null);

					vo.setFrstRegisterId("SYSTEM");

					qustnrRespondInfoService.insertQustnrRespondInfo(vo);
				}
			}
		}

		// 설문응답자 처리
		QustnrRespondManageVO vo = new QustnrRespondManageVO();

		vo.setQestnrId((String) commandMap.get("qestnrId"));

		vo.setSexdstnCode((String) commandMap.get("sexdstnCode"));
		vo.setOccpTyCode((String) commandMap.get("occpTyCode"));
		vo.setBrth((String) commandMap.get("brth"));
		vo.setRespondNm((String) commandMap.get("respondNm"));

		vo.setFrstRegisterId("SYSTEM");

		qustnrRespondManageService.insertQustnrRespondManage(vo);

		String ReusltScript = "설문참여에 응해주셔서 감사합니다";

		model.addAttribute("message", ReusltScript);
	    return WebUtil.redirectJsp(model, "/uss/olp/qri/listQustnrRespondInfoUser.do");
	}
	
	/**
	 * 설문조사 전체 통계를 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 * @param commandMap
	 */
	@RequestMapping(value = "/uss/olp/qri/statisticsQustnrRespondInfo.do")
	public String statisticsQustnrRespondInfo(
			@CommandMap Map<String, Object> commandMap,
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			ModelMap model) {

		// 설문지정보ID 설정
		String qestnrId = commandMap.get("qestnrId") == null ? "" : (String) commandMap.get("qestnrId");
		model.addAttribute("qestnrId", qestnrId);
		
		// 설문정보
		model.addAttribute("Comtnqestnrinfo", qustnrRespondInfoService.selectQustnrRespondQestnrInfo(qestnrId));
		// 문항정보
		model.addAttribute("Comtnqustnrqesitm", qustnrRespondInfoService.selectQustnrRespondQustnrQesitm(qestnrId));
		// 항목정보
		model.addAttribute("Comtnqustnriem", qustnrRespondInfoService.selectQustnrRespondQustnrIem(qestnrId));

		// 객관식통계 답안
		model.addAttribute("qestnrStatistic1", qustnrRespondInfoService.selectQustnrRespondStatistics1(qestnrId));
		// 주관식통계 답안
		model.addAttribute("qestnrStatistic2", qustnrRespondInfoService.selectQustnrRespondStatistics2(qestnrId));

		// 이전 주소
		model.addAttribute("returnUrl", commandMap.get("returnUrl") == null ? "" : (String) commandMap.get("returnUrl"));

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoStatistics");
	}

	/* 설문조사 (관리자 모드)*/
	/**
	 * 응답자결과(설문조사) 목록을 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/listQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String listQustnrRespondInfo(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			ModelMap model) {

		// 설문지정보에서 넘어오면 자동검색 설정
		if (qustnrRespondInfoVO.getSearchMode().equals("Y")) {
			qustnrRespondInfoVO.setSearchCondition("QESTNR_ID");
			qustnrRespondInfoVO.setSearchKeyword(qustnrRespondInfoVO.getQestnrId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrRespondInfoVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrRespondInfoService.selectQustnrRespondInfoList(qustnrRespondInfoVO));
		int totCnt = (Integer) qustnrRespondInfoService.selectQustnrRespondInfoListCnt(qustnrRespondInfoVO);

		qustnrRespondInfoVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoList");
	}

	/**
	 * 응답자결과(설문조사) 목록을 상세조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/detailQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String detailQustnrRespondInfo(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO,
			ModelMap model) {

		model.addAttribute(qustnrRespondInfoService.selectQustnrRespondInfoDetail(qustnrRespondInfoVO));

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoDetail");
	}

	/**
	 * 응답자결과(설문조사)를 등록화면으로 이동한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/registQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String registQustnrRespondInfo(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO) {

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoRegist");
	}
	
	/**
	 * 응답자결과(설문조사)를 등록한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/insertQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String insertQustnrRespondInfo(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrRespondInfoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrRespondInfoVO.setFrstRegisterId(loginVO.getUniqId());

		qustnrRespondInfoService.insertQustnrRespondInfo(qustnrRespondInfoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qri/listQustnrRespondInfo.do");
	}
	
	/**
	 * 응답자결과(설문조사)를 수정화면으로 이동한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/editQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String editQustnrRespondInfo(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO,
			ModelMap model) {

		model.addAttribute(qustnrRespondInfoService.selectQustnrRespondInfoDetail(qustnrRespondInfoVO));

		return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoEdit");
	}

	/**
	 * 응답자결과(설문조사)를 수정한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/updateQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String updateQustnrRespondInfo(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrRespondInfoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qri/QustnrRespondInfoEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrRespondInfoVO.setLastUpdusrId(loginVO.getUniqId());

		qustnrRespondInfoService.updateQustnrRespondInfo(qustnrRespondInfoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qri/listQustnrRespondInfo.do");
	}

	/**
	 * 응답자결과(설문조사) 목록을 삭제한다.
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value = "/uss/olp/qri/deleteQustnrRespondInfo.do")
	@Secured("ROLE_USER")
	public String deleteQustnrRespondInfo(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			ModelMap model) {

		qustnrRespondInfoService.deleteQustnrRespondInfo(qustnrRespondInfoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qri/listQustnrRespondInfo.do");
	}

}
