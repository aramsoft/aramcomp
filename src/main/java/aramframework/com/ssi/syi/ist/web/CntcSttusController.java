package aramframework.com.ssi.syi.ist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.ssi.syi.ist.service.CntcSttusVO;
import aramframework.com.ssi.syi.ist.service.CntcSttusService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 연계현황 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
public class CntcSttusController {

	@Autowired
	private CntcSttusService cntcSttusService;

	/**
	 * 연계현황 목록을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	@IncludedInfo(name = "연계현황관리", order = 7030, gid = 70)
	@RequestMapping(value = "/ssi/syi/ist/listCntcSttus.do")
	@Secured("ROLE_ADMIN")
	public String selectCntcSttusLogList(
			@ModelAttribute CntcSttusVO cntcSttusVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		cntcSttusVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cntcSttusService.selectCntcSttusList(cntcSttusVO));

		int totCnt = cntcSttusService.selectCntcSttusListCnt(cntcSttusVO);
		cntcSttusVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/ssi/syi/ist/CntcSttusList");
	}

	/**
	 * 연계현황 상세내역을 조회한다.
	 * 
	 * @param cntcSttusVO
	 */
	@RequestMapping(value = "/ssi/syi/ist/detailCntcSttus.do")
	@Secured("ROLE_ADMIN")
	public String selectCntcSttusLogDetail(
			@ModelAttribute CntcSttusVO cntcSttusVO) {
		
		cntcSttusService.selectCntcSttusDetail(cntcSttusVO);

		return WebUtil.adjustViewName("/ssi/syi/ist/CntcSttusDetail");
	}

}