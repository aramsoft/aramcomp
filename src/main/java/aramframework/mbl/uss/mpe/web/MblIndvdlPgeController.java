package aramframework.mbl.uss.mpe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.uss.mpe.domain.IndvdlPgeCntntsVO;
import aramframework.com.uss.mpe.domain.IndvdlPgeConfVO;
import aramframework.com.uss.mpe.service.IndvdlPgeService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 모바일 마이페이지를 처리 하는 Mobile Controller 클래스
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
public class MblIndvdlPgeController {
	
	@Autowired
    private IndvdlPgeService indvdlPgeService;
    	
	/**
	 * 사용자가 마이페이지를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	@RequestMapping(value="/uss/mpe/selectIndvdlpgeResult.mdo")
	@Secured("ROLE_USER")
	public String selectIndvdlpgeResult(
			@ModelAttribute IndvdlPgeConfVO indvdlPgeConfVO,
			ModelMap model) {
		
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(indvdlPgeConfVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(10);
        paginationInfo.setPageSize(1);
				
        indvdlPgeConfVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        indvdlPgeConfVO.setLastIndex(paginationInfo.getLastRecordIndex());
        indvdlPgeConfVO.setRecordPerPage(paginationInfo.getRecordCountPerPage());
		
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeConfVO.setUserId(loginVO.getId());

		// 마이페이지 상세목록설정
		model.addAttribute("indvdlPgeDetailList", indvdlPgeService.selectIndvdlpgeResultDetail(indvdlPgeConfVO));
		
        // 마이페이지 상세목록 카운트 설정
        int totDetailCnt = indvdlPgeService.selectIndvdlpgeResultDetailCnt(indvdlPgeConfVO);       
        model.addAttribute("indvdlPgeDetailListCount", totDetailCnt);

        paginationInfo.setTotalRecordCount(totDetailCnt);
		model.addAttribute("paginationInfo", paginationInfo);
        
        return "aramframework/mbl/uss/mpe/IndvdlpgeDetail";
	}
	
	/**
	 * 마이페이지에서  컨텐츠를 바로 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value="/uss/mpe/deleteIndvdlpgeCntntsJson.mdo")
	@Secured("ROLE_USER")
	public ModelAndView deleteIndvdlpgeCntntsJson(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		
		ModelAndView modelAndView =  new ModelAndView("jsonView");
		
		//ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeCntntsVO.setUserId(loginVO.getId());
		
		//디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		try {
			indvdlPgeService.delIndvdlpgeCntnts(indvdlPgeCntntsVO);
			modelAndView.addObject("message", MessageHelper.getMessage("success.common.delete"));
		}
		catch (Exception ex) {
			modelAndView.addObject("message", MessageHelper.getMessage("fail.common.insert"));
		}
			
		return modelAndView;
	}
	
	/**
	 * 마이페이지에 컨텐츠를 추가 위한 목록조회 화면을 출력한다.
	 * 
	 */
	@RequestMapping(value="/uss/mpe/listIndvdlpgeAddCntnts.mdo")
	@Secured("ROLE_USER")
	public String listIndvdlpgeAddCntnts() {
		
		return "aramframework/mbl/uss/mpe/IndvdlpgeList";
	}
	
	/**
	 * 마이페이지에 컨텐츠를 추가 위해 등록된 마이페이지 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value="/uss/mpe/listIndvdlpgeAddCntntsJson.mdo")
	@Secured("ROLE_USER")
	public ModelAndView listIndvdlpgeAddCntntsJson(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		//ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeCntntsVO.setUserId(loginVO.getId());
		
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	indvdlPgeCntntsVO.fillPageInfo(paginationInfo);
		
		// 사용자가 마이페이지에 컨텐츠를 추가하기 위해 등록되어 있는 마이페이지 목록을 조회한다.
		modelAndView.addObject("indvdlCntntsList", indvdlPgeService.addIndvdlpgeCntntsList(indvdlPgeCntntsVO));
        
        // 목록의 페이징을 위해 등록되어 있는 마이페이지 개수를 조회한다.
        int totCnt = indvdlPgeService.addIndvdlpgeCntntsListCnt(indvdlPgeCntntsVO);
        indvdlPgeCntntsVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		modelAndView.addObject("paginationInfo", paginationInfo);

        return modelAndView;
	}
	
	/**
	 * 마이페이지 컨텐츠의 미리보기를 위한 jsp URL을 리턴한다.
	 * 
	 * @param indvdlPgeCntntsVO 
	 */
	@RequestMapping(value="/uss/mpe/indvdlCntntsPreview.mdo")
	public String indvdlCntntsPreview(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		
		indvdlPgeService.selectIndvdlpgeCntnts(indvdlPgeCntntsVO);

		return "aramframework/mbl/uss/mpe/IndvdlpgeInfoDetail";
	}
	
	/**
	 * 마이페이지에  컨텐츠를 바로 추가한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value="/uss/mpe/insertIndvdlpgeCntntsJson.mdo")
	@Secured("ROLE_USER")
	public ModelAndView insertIndvdlpgeCntntsJson(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		//ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeCntntsVO.setUserId(loginVO.getId());

		//디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		try {
			indvdlPgeService.addIndvdlpgeCntnts(indvdlPgeCntntsVO);
			modelAndView.addObject("message", MessageHelper.getMessage("success.common.insert"));
		} 
		catch (Exception ex) {
			modelAndView.addObject("message", MessageHelper.getMessage("fail.common.insert"));
		}
		
		return modelAndView;
	}
	
}
