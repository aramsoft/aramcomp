package aramframework.com.uss.ion.ism.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.ism.domain.InfrmlSanctnVO;
import aramframework.com.uss.ion.ism.domain.SanctnerVO;
import aramframework.com.uss.ion.ism.service.InfrmlSanctnService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 약식결재관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 약식결재관리에 대한 등록, 승인, 반려, 삭제기능을 제공한다. 
 *         - 결재자에 대한 목록조회기능을 제공한다.
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
public class InfrmlSanctnController {

	@Autowired
	private InfrmlSanctnService infrmlSanctnService;

	/**
	 * 결재자 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param sanctnerVO
	 */
	@RequestMapping("/uss/ion/ism/listSanctnerPopup.do")
	public String selectSanctnerList(
			@ModelAttribute SanctnerVO sanctnerVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		sanctnerVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", infrmlSanctnService.selectSanctnerList(sanctnerVO));

		int totCnt = infrmlSanctnService.selectSanctnerListCnt(sanctnerVO);
		sanctnerVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/ism/SanctnerListPopup");
	}

	/**
	 * 약식결재 정보의 상세화면으로 이동한다.
	 * 
	 * @param infrmlSanctnVO
	 */
	@RequestMapping("/uss/ion/ism/detailSanctner.do")
	public String detailSanctner(
			@ModelAttribute InfrmlSanctnVO infrmlSanctnVO) {

		infrmlSanctnService.selectInfrmlSanctn(infrmlSanctnVO);

		return WebUtil.adjustViewName("/uss/ion/ism/InfrmlSanctnDetail");
	}

	/**
	 * 약식결재 반려처리 화면을 호출한다.
	 * 
	 */
	@RequestMapping("/uss/ion/ism/ReturnPopup.do")
	public String selectReturnPopup() {
		return "aramframework/com/uss/ion/ism/ReturnPopup";
	}

	/**
	 * 약식결재 승인처리 화면을 호출한다.
	 * 
	 */
	@RequestMapping("/uss/ion/ism/ConfmPopup.do")
	public String selectConfmPopup() {
		return "aramframework/com/uss/ion/ism/ConfmPopup";
	}

}