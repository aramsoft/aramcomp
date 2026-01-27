package aramframework.com.cop.ems.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import aramframework.cmm.annotation.IncludedInfo;
import aramframework.cmm.constant.Globals;
import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cmm.com.domain.SearchVO;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.ems.domain.SndngMailVO;
import aramframework.com.cop.ems.service.SndngMailService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 발송메일 내역을 조회하는 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SndngMailController {

	@Autowired 
	private SndngMailService sndngMailService;

	@Autowired 
	private FileMngUtil fileUtil;

    /** 파일구분자 */
    static final char FILE_SEPARATOR = File.separatorChar;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 발송메일 내역을 조회한다
	 * 
	 * @param sndngMailVO
	 */
	@IncludedInfo(name = "발송메일관리", order = 4500, gid = 45)
	@RequestMapping(value = "/cop/ems/listSndngMail.do")
	@Secured("ROLE_USER")
	public String listSndngMail(
			@ModelAttribute SndngMailVO sndngMailVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		sndngMailVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", sndngMailService.selectSndngMailList(sndngMailVO));
		int totCnt = sndngMailService.selectSndngMailListCnt(sndngMailVO);

		sndngMailVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/ems/MailList";
	}

	/**
	 * 발송메일목록을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/deleteSndngMailList.do")
	@Secured("ROLE_USER")
	public String deleteSndngMailList(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SndngMailVO sndngMailVO, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] mssageIds = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		mssageIds = request.getParameterValues("uniqIds");

		sndngMailService.deleteSndngMails(mssageIds);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/ems/listSndngMail.do");
	    return "com/cmm/redirect";
	}
	
	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/detailSndngMail.do")
	@Secured("ROLE_USER")
	public String detailSndngMail(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SndngMailVO sndngMailVO, 
			ModelMap model) {

		// 1. 발송메일을 상세 조회한다.
		model.addAttribute(sndngMailService.selectSndngMail(sndngMailVO));

		// 발송메일 상세조회 화면 이동
		return "com/cop/ems/MailDetail";
	}

	/**
	 * XML형태의 발송요청메일을 조회한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/detailSndngMailXml.do")
	@Secured("ROLE_USER")
	public void detailSndngMailXml(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SndngMailVO sndngMailVO, 
			HttpServletResponse response) 
	throws Exception {
		String xmlFile = Globals.MAIL_REQUEST_PATH + sndngMailVO.getMssageId() + ".xml";
		File uFile = new File(xmlFile);
		int fSize = (int) uFile.length();

		// 2011.10.10 보안점검 후속 조치
		if (fSize > 0) {
			String mimetype = "application/x-msdownload;charset=UTF-8";

			// response.setBufferSize(fSize);
			response.setContentType(mimetype);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + uFile.getName() + "\"");
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				FileCopyUtils.copy(in, response.getOutputStream());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						LOG.debug("IGNORED: " + ignore.getMessage());
					}
				}
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + WebUtil.clearXSSMinimum(xmlFile) + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

    /**
	 * 발송메일 등록화면으로 들어간다
	 * 
	 * @param sndngMailVO 
	 */
    @RequestMapping(value="/cop/ems/registSndngMail.do")
	@Secured("ROLE_USER")
    public String registSndngMail(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SndngMailVO sndngMailVO, 
			ModelMap model) {
    	
		return "com/cop/ems/MailRegist";
    }
    
    /**
	 * 발송메일을 등록한다
	 * 
	 * @param multiRequest 
	 * @param sndngMailVO 
	 */
    @RequestMapping(value="/cop/ems/insertSndngMail.do")
	@Secured("ROLE_USER")
	public String insertSndngMail(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SndngMailVO sndngMailVO,
			MultipartHttpServletRequest multiRequest,
			ModelMap model) 
    throws Exception {
    	
    	String link = "N";
    	if (sndngMailVO != null 
    			&& sndngMailVO.getLink() != null 
    			&& !sndngMailVO.getLink().equals("")) {
    		link = sndngMailVO.getLink();
    	}
    	
		// 첨부파일 관련 첨부파일ID 생성
    	sndngMailVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "EMS"));
    	
    	LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	sndngMailVO.setDsptchPerson(loginVO.getUserId());
    	
    	// 발송메일을 등록한다.
    	boolean result = sndngMailService.insertSndngMail(sndngMailVO);
    	if (!result) {
			return "com/cmm/error/egovError";
    	}
    	
		if (link.equals("N")) {
			model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
			model.addAttribute("redirectURL", "/cop/ems/listSndngMail.do");
		    return "com/cmm/redirect";
		} else {
			model.addAttribute("closeYn", "Y");
			return "com/cop/ems/MailRegist";
		}
	}
    
	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param sndngMailVO
	 */
	@RequestMapping(value = "/cop/ems/deleteSndngMail.do")
	@Secured("ROLE_USER")
	public String deleteSndngMail(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SndngMailVO sndngMailVO, 
			ModelMap model) {

		sndngMailService.deleteSndngMail(sndngMailVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/ems/listSndngMail.do");
	    return "com/cmm/redirect";
	}

}