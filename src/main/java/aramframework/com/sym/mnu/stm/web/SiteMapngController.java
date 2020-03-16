package aramframework.com.sym.mnu.stm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.mnu.stm.domain.SiteMapngVO;
import aramframework.com.sym.mnu.stm.service.SiteMapngService;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 사이트맵 조회 처리를 하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SiteMapngController {

	@Autowired
	private SiteMapngService siteMapngService;

	/* 사이트맵조회 */
	/**
	 * 사이트맵 화면을 조회한다.
	 * 
	 */
//	@IncludedInfo(name = "사이트맵", order = 6160, gid = 60)
	@RequestMapping(value = "/sym/mnu/stm/siteMapng.do")
	@Secured("ROLE_USER")
	public String selectSiteMapng(ModelMap model) {
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		SearchVO searchVO = new SearchVO();
		searchVO.setSearchKeyword(loginVO.getUniqId());
		SiteMapngVO siteMapngVO = siteMapngService.selectSiteMapng(searchVO);

		if (siteMapngVO == null) {
			model.addAttribute("message", "사이트맵을 생성해 주세요.");
			return WebUtil.adjustViewName("sym/mnu/stm/SiteMapng");
		}

		model.addAttribute("siteMapngVO", siteMapngVO);

		return WebUtil.adjustViewName("sym/mnu/stm/SiteMapng");
	}
	
}