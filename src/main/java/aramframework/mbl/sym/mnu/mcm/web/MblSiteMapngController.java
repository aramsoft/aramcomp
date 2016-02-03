package aramframework.mbl.sym.mnu.mcm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.sym.mnu.mcm.domain.MenuCreateVO;
import aramframework.com.sym.mnu.mcm.domain.MenuSiteMapVO;
import aramframework.com.sym.mnu.mcm.service.MenuCreateService;

/**
 * 모바일 사이트맵 조회 처리를 하는 비즈니스 구현 클래스
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
public class MblSiteMapngController {
	
	@Autowired
    private MenuCreateService menuCreateService;	

    /**
     * 사이트맵 화면을 출력한다. 
     * 
     */
    @RequestMapping(value="/sym/mnu/mcm/SiteMapng.mdo")
    public String SiteMapng() {
        return "aramframework/mbl/com/sym/mnu/mcm/SiteMap";
    }
	
    /**
     * 사이트맵을 조회한다. 
     * 
     */
    @RequestMapping(value="/sym/mnu/mcm/SiteMapngJson.mdo")
    public ModelAndView SiteMapngJson() {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	MenuCreateVO menuCreteVO = new MenuCreateVO();
    	
    	LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();   
    	menuCreteVO.setSearchKeyword(loginVO.getId()); 	

    	MenuCreateVO vo = menuCreateService.selectAuthorByUsr(menuCreteVO);

    	MenuSiteMapVO menuSiteMapVO = new MenuSiteMapVO();
    	menuSiteMapVO.setAuthorCode(vo.getAuthorCode());
    	modelAndView.addObject("menulist", menuCreateService.selectMenuCreateSiteMapList(menuSiteMapVO));
    	
        return modelAndView;
    }
    
    /**
     * 사이트맵을 출력한다. (Web Style) 
     * 
     */
    @RequestMapping(value="/sym/mnu/mcm/WebSiteMap.mdo")
    public String WebSiteMap() {
        return "aramframework/mbl/sym/mnu/mcm/WebSiteMap";
    }
    
}
