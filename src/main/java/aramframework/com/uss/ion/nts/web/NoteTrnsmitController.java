package aramframework.com.uss.ion.nts.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.nts.domain.NoteTrnsmitVO;
import aramframework.com.uss.ion.nts.service.NoteTrnsmitService;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 보낸쪽지함관리를 처리하는 Controller Class 구현
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
public class NoteTrnsmitController {

	@Autowired
	private NoteTrnsmitService noteTrnsmitService;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 보낸쪽지함관리 목록을 조회한다.
	 * 
	 * @param commandMap
	 * @param noteTrnsmitVO
	 */
	@IncludedInfo(name = "보낸쪽지함관리", order = 5252, gid = 50)
	@RequestMapping(value = "/uss/ion/nts/listNoteTrnsmit.do")
	@Secured("ROLE_USER")
	public String listNoteTrnsmit(
			@CommandMap Map<String, Object> commandMap, 
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO,
			ModelMap model) 
	throws Exception {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 변수 설정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		// 삭제 모드로 실행시
		if (sCmd.equals("del")) {
			// 한개의 값으로 삭제가 넘어올때 처리
			if (commandMap.get("checkList") instanceof String) {
				String sCheckList = (String) commandMap.get("checkList");

				String[] sArrCheckListValue = sCheckList.split(",");

				log.debug("==================================EgovNoteTrnsmitList");
				log.debug("checkList" + sCheckList);
				log.debug("sArrCheckListValue[0]>" + sArrCheckListValue[0]);
				log.debug("sArrCheckListValue[1]>" + sArrCheckListValue[1]);

				noteTrnsmitVO.setFrstRegisterId(loginVO.getUniqId());
				noteTrnsmitVO.setLastUpdusrId(loginVO.getUniqId());
				noteTrnsmitVO.setNoteId(sArrCheckListValue[0]);
				noteTrnsmitVO.setNoteTrnsmitId(sArrCheckListValue[1]);

				noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);
			}

			// 여러개의 값으로 삭제가 넘어올때 처리
			if (commandMap.get("checkList") instanceof String[]) {
				String[] sArrCheckList = (String[]) commandMap.get("checkList");
				log.debug("sArrCheckList" + sArrCheckList);

				for (int i = 0; i < sArrCheckList.length; i++) {
					String[] sArrCheckListValue = sArrCheckList[i].split(",");

					noteTrnsmitVO.setFrstRegisterId(loginVO.getUniqId());
					noteTrnsmitVO.setLastUpdusrId(loginVO.getUniqId());
					noteTrnsmitVO.setNoteId(sArrCheckListValue[0]);
					noteTrnsmitVO.setNoteTrnsmitId(sArrCheckListValue[1]);

					noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);
				}
			}

			// 삭제후 페이지 인덱스 설정
			noteTrnsmitVO.getSearchVO().setPageIndex(1);
		}

		// 발신자설정
		noteTrnsmitVO.setTrnsmiterId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		noteTrnsmitVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", noteTrnsmitService.selectNoteTrnsmitList(noteTrnsmitVO));

		int totCnt = (Integer) noteTrnsmitService.selectNoteTrnsmitListCnt(noteTrnsmitVO);
		noteTrnsmitVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/nts/NoteTrnsmitList");
	}

	/**
	 * 보낸쪽지함관리 목록을 상세조회 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 * @param sCmd
	 */
	@RequestMapping(value = "/uss/ion/nts/detailNoteTrnsmit.do")
	public String detailNoteTrnsmit(
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO, 
			@RequestParam(value="cmd", required=false) String sCmd,
			ModelMap model)  {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		if (sCmd.equals("del")) {
			noteTrnsmitVO.setFrstRegisterId(loginVO.getUniqId());
			noteTrnsmitVO.setLastUpdusrId(loginVO.getUniqId());
			// log.debug("EgovNoteTrnsmitDetail searchVO>"+searchVO);
			noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);

			return "redirect:/uss/ion/nts/listNoteTrnsmit.do";
		} 
		
		model.addAttribute("noteTrnsmit", noteTrnsmitService.selectNoteTrnsmitDetail(noteTrnsmitVO));
		model.addAttribute("resultRecptnEmp", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmitVO));
		
		return WebUtil.adjustViewName("/uss/ion/nts/NoteTrnsmitDetail");
	}

	/**
	 * 수신자목록을 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 * @param sCmd
	 */
	@RequestMapping(value = "/uss/ion/nts/confirmNoteTrnsmit.do")
	public String confirmNoteTrnsmit(
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO, 
			@RequestParam(value="cmd", required=false) String sCmd,
			ModelMap model) {

		if (sCmd.equals("del")) {
			noteTrnsmitService.deleteNoteRecptn(noteTrnsmitVO);
		}

		model.addAttribute("resultList", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmitVO));

		return WebUtil.adjustViewName("/uss/ion/nts/NoteTrnsmitCnfirm");
	}

}
