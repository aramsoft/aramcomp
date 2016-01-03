package aramframework.com.uss.olp.opr.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.olp.opr.service.OnlinePollResultService;
import aramframework.com.uss.olp.opr.service.OnlinePollResultVO;

/**
 * 온라인POLL결과를 처리하는 Controller Class 구현
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
public class OnlinePollResultController {

	@Resource(name = "onlinePollResultService")
	private OnlinePollResultService onlinePollResultService;

	/**
	 * 온라인POLL결과 목록을 조회한다.
	 * 
	 * @param onlinePollResultVO
	 */
	@RequestMapping(value = "/uss/olp/opr/listOnlinePollResult.do")
	public String listOnlinePollResult(
			@ModelAttribute OnlinePollResultVO onlinePollResultVO, 
			ModelMap model) {

		model.addAttribute("resultList", onlinePollResultService.selectOnlinePollResultList(onlinePollResultVO));

		return WebUtil.adjustViewName("/uss/olp/opr/OnlinePollResultList");
	}

	/**
	 * 온라인POLL결과 를 삭제한다.
	 * 
	 * @param onlinePollResultVO
	 */
	@RequestMapping(value = "/uss/olp/opr/deleteOnlinePollResult.do")
	public String deleteOnlinePollResult(
			@ModelAttribute OnlinePollResultVO onlinePollResultVO, 
			ModelMap model) {

		onlinePollResultService.deleteOnlinePollResult(onlinePollResultVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/opr/listOnlinePollResult.do?pollId="+onlinePollResultVO.getPollId());
	}

}
